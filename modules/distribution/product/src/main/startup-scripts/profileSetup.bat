@echo off
rem ----------------------------------------------------------------------------
rem  Copyright 2018 WSO2, Inc. http://www.wso2.org
rem
rem  Licensed under the Apache License, Version 2.0 (the "License");
rem  you may not use this file except in compliance with the License.
rem  You may obtain a copy of the License at
rem
rem      http://www.apache.org/licenses/LICENSE-2.0
rem
rem  Unless required by applicable law or agreed to in writing, software
rem  distributed under the License is distributed on an "AS IS" BASIS,
rem  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
rem  See the License for the specific language governing permissions and
rem  limitations under the License.

set userLocation=%cd%
set pathToApiManagerXML=..\repository\conf\api-manager.xml
set pathToAxis2XML=..\repository\conf\axis2\axis2.xml
set pathToAxis2XMLTemplate=..\repository\resources\conf\template\repository\conf\axis2\axis2.xml.j2
set pathToRegistry=..\repository\resources\conf\template\repository\conf\registry.xml.j2
set pathToRegistryTemplate=..\repository\conf\registry.xml
set pathToInboundEndpoints=..\repository\deployment\server\synapse-configs\default\inbound-endpoints
set pathToWebapps=..\repository\deployment\server\webapps
set pathToJaggeryapps=..\repository\deployment\server\jaggeryapps
set pathToSynapseConfigs=..\repository\deployment\server\synapse-configs\default
set pathToAxis2TMXml=..\repository\conf\axis2\axis2_TM.xml
set pathToAxis2TMXmlTemplate=..\repository\resources\conf\template\repository\conf\axis2\axis2_TM.xml.j2
set pathToRegistryTM=..\repository\conf\registry_TM.xml
set pathToRegistryTMTemplate=..\repository\resources\conf\template\repository\conf\registry_TM.xml.j2
set axis2XMLBackup=axis2backup.xml
set axis2XMLBackupTemplate=axis2.backup
set registryBackup=registryBackup.xml
set registryBackupTemplate=registry.backup
set axis2XML=axis2.xml
set axis2XMLTemplate=axis2.xml.j2
set registryXML=registry.xml
set registryXMLTemplate=registry.xml.j2

cd /d %~dp0

rem ----- Process the input commands (two args only)-------------------------------------------
if ""%1""==""-Dprofile"" (
	if ""%2""==""api-key-manager"" 	goto keyManager
	if ""%2""==""api-publisher"" 	goto publisher
	if ""%2""==""api-devportal"" 	goto devportal
	if ""%2""==""traffic-manager"" 	goto trafficManager
	if ""%2""==""gateway-worker"" 	goto gatewayWorker
)
echo Profile is not specified properly, please try again
goto end

:keyManager
echo Starting to optimize API Manager for the Key Manager profile
call :disableDataPublisher
call :disableJMSConnectionDetails
call :disablePolicyDeployer
call :disableBlockConditionRetriever
call :disableTransportSenderWS
call :disableTransportSenderWSS
call :removeWebSocketInboundEndpoint
call :removeSecureWebSocketInboundEndpoint
call :removeSynapseConfigs
rem ---removing webbapps which are not required for this profile--------
for /f %%i in ('dir %pathToWebapps% /b ^| findstr /v "client-registration#v.*war authenticationendpoint accountrecoveryendpoint oauth2.war throttle#data#v.*war api#identity#consent-mgt#v.*war"') do (
	del /f %pathToWebapps%\%%i
	call :Timestamp value
	echo %value% INFO - Removed the %%i file from %pathToWebapps%
	setlocal enableDelayedExpansion
	set folderName=%%i
	set folderName=!folderName:.war=!
	if exist %pathToWebapps%\!folderName!\ (
		rmdir /s /q %pathToWebapps%\!folderName!
		call :Timestamp value
		echo %value% INFO - Removed the !folderName! directory from %pathToWebapps%
	)
	endlocal
)
rem ---removing jaggeryapps which are not required for this profile--------
for /f %%i in ('dir "%pathToJaggeryapps%" /A:D /b') do (
	rmdir /s /q %pathToJaggeryapps%\%%i
	call :Timestamp value
	echo %value% INFO - Removed the %%i directory from %pathToJaggeryapps%
)
goto finishOptimization

:publisher
echo Starting to optimize API Manager for the API Publisher profile
call :disableJMSConnectionDetails
call :disableBlockConditionRetriever
call :disableTransportSenderWS
call :disableTransportSenderWSS
call :removeWebSocketInboundEndpoint
call :removeSecureWebSocketInboundEndpoint
rem ---removing webbapps which are not required for this profile--------
for /f %%i in ('dir %pathToWebapps% /b ^| findstr /v "api#am#publisher#v.*war api#am#publisher.war client-registration#v.*war authenticationendpoint accountrecoveryendpoint oauth2.war api#am#admin#v.*war"') do (
	del /f %pathToWebapps%\%%i
	call :Timestamp value
	echo %value% INFO - Removed the %%i file from %pathToWebapps%
	setlocal enableDelayedExpansion
	set folderName=%%i
	set folderName=!folderName:.war=!
	if exist %pathToWebapps%\!folderName!\ (
		rmdir /s /q %pathToWebapps%\!folderName!
		call :Timestamp value
		echo %value% INFO - Removed the !folderName! directory from %pathToWebapps%
	)
	endlocal
)
rem ---removing jaggeryapps which are not required for this profile--------
for /f %%i in ('dir "%pathToJaggeryapps%" /A:D /b ^| findstr /v "publisher admin"') do (
	rmdir /s /q %pathToJaggeryapps%\%%i
	call :Timestamp value
	echo %value% INFO - Removed the %%i directory from %pathToJaggeryapps%
)
goto finishOptimization

:devportal
echo Starting to optimize API Manager for the Developer Portal profile
call :disableDataPublisher
call :disableJMSConnectionDetails
call :disableBlockConditionRetriever
call :disablePolicyDeployer
call :disableTransportSenderWS
call :disableTransportSenderWSS
call :removeWebSocketInboundEndpoint
call :removeSecureWebSocketInboundEndpoint
rem ---removing webbapps which are not required for this profile--------
for /f %%i in ('dir %pathToWebapps% /b ^| findstr /v "api#am#store#v.*war api#am#store.war client-registration#v.*war authenticationendpoint accountrecoveryendpoint oauth2.war api#am#admin#v.*war"') do (
	del /f %pathToWebapps%\%%i
	call :Timestamp value
	echo %value% INFO - Removed the %%i file from %pathToWebapps%
	setlocal enableDelayedExpansion
	set folderName=%%i
	set folderName=!folderName:.war=!
	if exist %pathToWebapps%\!folderName!\ (
		rmdir /s /q %pathToWebapps%\!folderName!
		call :Timestamp value
		echo %value% INFO - Removed the !folderName! directory from %pathToWebapps%
	)
	endlocal
)
rem ---removing jaggeryapps which are not required for this profile--------
for /f %%i in ('dir "%pathToJaggeryapps%" /A:D /b ^| findstr /v "devportal"') do (
	rmdir /s /q %pathToJaggeryapps%\%%i
	call :Timestamp value
	echo %value% INFO - Removed the %%i directory from %pathToJaggeryapps%
)
goto finishOptimization

:trafficManager
echo Starting to optimize API Manager for the Traffic Manager profile
call :replaceAxis2File
call :replaceRegistryXMLFile
call :replaceAxis2TemplateFile
call :replaceRegistryXMLTemplateFile
call :removeWebSocketInboundEndpoint
call :removeSecureWebSocketInboundEndpoint
call :disableIndexingConfiguration
call :removeSynapseConfigs
rem ---removing webbapps which are not required for this profile--------
for /f %%i in ('dir %pathToWebapps% /b') do (
	del /f %pathToWebapps%\%%i
	call :Timestamp value
	echo %value% INFO - Removed the %%i file from %pathToWebapps%
	setlocal enableDelayedExpansion
	set folderName=%%i
	set folderName=!folderName:.war=!
	if exist %pathToWebapps%\!folderName!\ (
		rmdir /s /q %pathToWebapps%\!folderName!
		call :Timestamp value
		echo %value% INFO - Removed the !folderName! directory from %pathToWebapps%
	)
	endlocal
)
rem ---removing jaggeryapps which are not required for this profile--------
for /f %%i in ('dir "%pathToJaggeryapps%" /A:D /b') do (
	rmdir /s /q %pathToJaggeryapps%\%%i
	call :Timestamp value
	echo %value% INFO - Removed the %%i directory from %pathToJaggeryapps%
)
goto finishOptimization

:gatewayWorker
echo Starting to optimize API Manager for the Gateway worker profile
call :disablePolicyDeployer
call :disableIndexingConfiguration
rem ---removing webbapps which are not required for this profile--------
for /f %%i in ('dir %pathToWebapps% /b ^| findstr /v "am#sample#pizzashack#v.*war"') do (
	del /f %pathToWebapps%\%%i
	call :Timestamp value
	echo %value% INFO - Removed the %%i file from %pathToWebapps%
	setlocal enableDelayedExpansion
	set folderName=%%i
	set folderName=!folderName:.war=!
	if exist %pathToWebapps%\!folderName!\ (
		rmdir /s /q %pathToWebapps%\!folderName!
		call :Timestamp value
		echo %value% INFO - Removed the !folderName! directory from %pathToWebapps%
	)
	endlocal
)
rem ---removing jaggeryapps which are not required for this profile--------
for /f %%i in ('dir "%pathToJaggeryapps%" /A:D /b') do (
	rmdir /s /q %pathToJaggeryapps%\%%i
	call :Timestamp value
	echo %value% INFO - Removed the %%i directory from %pathToJaggeryapps%
)
goto finishOptimization

:disableDataPublisher
for /f %%i in ('powershell -Command "$xml = [xml] (Get-Content %pathToApiManagerXML%); $xml.APIManager.ThrottlingConfigurations.DataPublisher.Enabled;"') do (
	if %%i==true (
		powershell -Command "$xml = [xml] (Get-Content %pathToApiManagerXML%); $xml.APIManager.ThrottlingConfigurations.DataPublisher.Enabled='false'; $xml.Save('%pathToApiManagerXML%');"
		call :Timestamp value
		echo %value% INFO - Disabled the ^<DataPublisher^> from api-manager.xml file
	)
)
EXIT /B 0

:disableJMSConnectionDetails
for /f %%i in ('powershell -Command "$xml = [xml] (Get-Content %pathToApiManagerXML%); $xml.APIManager.ThrottlingConfigurations.JMSConnectionDetails.Enabled;"') do (
	if %%i==true (
		powershell -Command "$xml = [xml] (Get-Content %pathToApiManagerXML%); $xml.APIManager.ThrottlingConfigurations.JMSConnectionDetails.Enabled='false'; $xml.Save('%pathToApiManagerXML%');"
		call :Timestamp value
		echo %value% INFO - Disabled the ^<JMSConnectionDetails^> from api-manager.xml file
	)
)
EXIT /B 0

:disablePolicyDeployer
for /f %%i in ('powershell -Command "$xml = [xml] (Get-Content %pathToApiManagerXML%); $xml.APIManager.ThrottlingConfigurations.PolicyDeployer.Enabled;"') do (
	if %%i==true (
		powershell -Command "$xml = [xml] (Get-Content %pathToApiManagerXML%); $xml.APIManager.ThrottlingConfigurations.PolicyDeployer.Enabled='false'; $xml.Save('%pathToApiManagerXML%');"
		call :Timestamp value
		echo %value% INFO - Disabled the ^<PolicyDeployer^> from api-manager.xml file
	)
)
EXIT /B 0

:disableBlockConditionRetriever
for /f %%i in ('powershell -Command "$xml = [xml] (Get-Content %pathToApiManagerXML%); $xml.APIManager.ThrottlingConfigurations.BlockCondition.Enabled;"') do (
	if %%i==true (
		powershell -Command "$xml = [xml] (Get-Content %pathToApiManagerXML%); $xml.APIManager.ThrottlingConfigurations.BlockCondition.Enabled='false'; $xml.Save('%pathToApiManagerXML%');"
		call :Timestamp value
		echo %value% INFO - Disabled the ^<BlockCondition^> from api-manager.xml file
	)
)
EXIT /B 0

:disableTransportSenderWS
for /f %%i in ('powershell -Command "& {$xml = [xml] (Get-Content %pathToAxis2XML%); $xml.selectSingleNode('//transportSender[@name=\"ws\"]'); }" ') do (
	powershell -Command "& { $xml = [xml] (Get-Content %pathToAxis2XML%); $xml.selectNodes('//transportSender[@name=\"ws\"]') | ForEach-Object { $node = $_; $comment = $xml.CreateComment($node.OuterXml); $node=$node.ParentNode.ReplaceChild($comment, $node);}; $xml.Save('%pathToAxis2XML%');}"
	call :Timestamp value
	echo %value% INFO - Disabled the ^<transportSender name="ws" class="org.wso2.carbon.websocket.transport.WebsocketTransportSender"^> from axis2.xml file
	goto skipLoop1
)
:skipLoop1
EXIT /B 0

:disableTransportSenderWSS
for /f %%i in ('powershell -Command "& {$xml = [xml] (Get-Content %pathToAxis2XML%); $xml.SelectSingleNode('//transportSender[@name=\"wss\"]'); }" ') do (
	powershell -Command "& { $xml = [xml] (Get-Content %pathToAxis2XML%); $xml.selectNodes('//transportSender[@name=\"wss\"]') | ForEach-Object { $node = $_; $comment = $xml.CreateComment($node.OuterXml); $node=$node.ParentNode.ReplaceChild($comment, $node);}; $xml.Save('%pathToAxis2XML%');}"
	call :Timestamp value
	echo %value% INFO - Disabled the ^<transportSender name="wss" class="org.wso2.carbon.websocket.transport.WebsocketTransportSender"^> from axis2.xml file
	goto skipLoop2
	)
:skipLoop2
EXIT /B 0

:disableIndexingConfiguration
for /f %%i in ('powershell -Command "$xml = [xml] (Get-Content %pathToRegistry%); $xml.wso2registry.indexingConfiguration.startIndexing;"') do (
	if %%i==true (
		powershell -Command "$xml = [xml] (Get-Content %pathToRegistry%); $xml.wso2registry.indexingConfiguration.startIndexing='false'; $xml.Save('%pathToRegistry%');"
		call :Timestamp value
		echo %value% INFO - Disabled the ^<indexingConfiguration^> from registry.xml file
	)
)
EXIT /B 0

:removeWebSocketInboundEndpoint
if exist %pathToInboundEndpoints%\WebSocketInboundEndpoint.xml (
	del /f %pathToInboundEndpoints%\WebSocketInboundEndpoint.xml
	call :Timestamp value
	echo %value% INFO - Removed the WebSocketInboundEndpoint.xml file from %pathToInboundEndpoints%
)
EXIT /B 0

:removeSecureWebSocketInboundEndpoint
if exist %pathToInboundEndpoints%\SecureWebSocketInboundEndpoint.xml (
	del /f %pathToInboundEndpoints%\SecureWebSocketInboundEndpoint.xml
	call :Timestamp value
	echo %value% INFO - Removed the SecureWebSocketInboundEndpoint.xml file from %pathToInboundEndpoints%
)
EXIT /B 0

:removeSynapseConfigs
rem ----removing directories if exists ----
for /f %%i in ('dir "%pathToSynapseConfigs%" /A:D /b') do (
	rmdir /s /q %pathToSynapseConfigs%\%%i
	call :Timestamp value
	echo %value% INFO - Removed the %%i directory from %pathToSynapseConfigs%
)
rem ----removing the files if exists ------
for /f %%i in ('dir "%pathToSynapseConfigs%" /A:-D /b ^| find /v "synapse.xml"') do (
	del /f %pathToSynapseConfigs%\%%i
	call :Timestamp value
	echo %value% INFO - Removed the %%i file from %pathToSynapseConfigs%
)
EXIT /B 0

:replaceAxis2File
if exist %pathToAxis2XML% (
	if exist %pathToAxis2TMXml% (
		ren %pathToAxis2XML% %axis2XMLBackup%
		call :Timestamp value
		echo %value% INFO - Rename the existing %pathToAxis2XML% file as %axis2XMLBackup%
		ren %pathToAxis2TMXml% %axis2XML%
		call :Timestamp value
		echo %value% INFO - Rename the existing %pathToAxis2TMXml% file as %axis2XML%
	)
)
EXIT /B 0

:replaceRegistryXMLFile
if exist %pathToRegistry% (
	if exist %pathToRegistryTM% (
        ren %pathToRegistry% %registryBackup%
        call :Timestamp value
        echo %value% INFO - Rename the existing %pathToRegistry% file as %registryBackup%
        ren  %pathToRegistryTM% %registryXML%
        call :Timestamp value
        echo %value% INFO - Rename the existing %pathToRegistryTM% file as %registryXML%
	)
)
EXIT /B 0

:replaceAxis2TemplateFile
if exist %pathToAxis2XMLTemplate% (
	if exist %pathToAxis2TMXmlTemplate% (
		ren %pathToAxis2XMLTemplate% %axis2XMLBackupTemplate%
		call :Timestamp value
		echo %value% INFO - Rename the existing %pathToAxis2XML% file as %axis2XMLBackupTemplate%
		ren %pathToAxis2TMXmlTemplate% %pathToAxis2XMLTemplate%
		call :Timestamp value
		echo %value% INFO - Rename the existing %pathToAxis2TMXmlTemplate% file as %axis2XMLTemplate%
	)
)
EXIT /B 0

:replaceRegistryXMLTemplateFile
if exist %pathToRegistryTemplate% (
	if exist %pathToRegistryTMTemplate% (
        ren %pathToRegistryTemplate% %registryBackupTemplate%
        call :Timestamp value
        echo %value% INFO - Rename the existing %pathToRegistryTemplate% file as %registryBackupTemplate%
        ren  %pathToRegistryTM% %registryXMLTemplate%
        call :Timestamp value
        echo %value% INFO - Rename the existing %pathToRegistryTMTemplate% file as %registryXMLTemplate%
	)
)
EXIT /B 0


:Timestamp
set "%~1=[%date:~10,14%-%date:~4,2%-%date:~7,2% %time%]"
EXIT /B 0

:finishOptimization
echo Finished the optimizations
goto end

:end
cd /d %userLocation%
