/*
 * WSO2 API Manager - Publisher API
 * This specifies a **RESTful API** for WSO2 **API Manager** - Publisher.  Please see [full swagger definition](https://raw.githubusercontent.com/wso2/carbon-apimgt/v6.0.4/components/apimgt/org.wso2.carbon.apimgt.rest.api.publisher/src/main/resources/publisher-api.yaml) of the API which is written using [swagger 2.0](http://swagger.io/) specification. 
 *
 * OpenAPI spec version: v1.0.0
 * Contact: architecture@wso2.com
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package org.wso2.carbon.apimgt.rest.integration.tests.api;

import org.wso2.carbon.apimgt.rest.integration.tests.ApiCallback;
import org.wso2.carbon.apimgt.rest.integration.tests.ApiClient;
import org.wso2.carbon.apimgt.rest.integration.tests.ApiException;
import org.wso2.carbon.apimgt.rest.integration.tests.ApiResponse;
import org.wso2.carbon.apimgt.rest.integration.tests.Configuration;
import org.wso2.carbon.apimgt.rest.integration.tests.Pair;
import org.wso2.carbon.apimgt.rest.integration.tests.ProgressRequestBody;
import org.wso2.carbon.apimgt.rest.integration.tests.ProgressResponseBody;

import com.google.gson.reflect.TypeToken;

import java.io.IOException;


import org.wso2.carbon.apimgt.rest.integration.tests.model.API;
import org.wso2.carbon.apimgt.rest.integration.tests.model.APIList;
import org.wso2.carbon.apimgt.rest.integration.tests.model.Error;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class APICollectionApi {
    private ApiClient apiClient;

    public APICollectionApi() {
        this(Configuration.getDefaultApiClient());
    }

    public APICollectionApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /* Build call for apisGet */
    private com.squareup.okhttp.Call apisGetCall(Integer limit, Integer offset, String query, String accept, String ifNoneMatch, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        Object localVarPostBody = null;
        
        // create path and map variables
        String localVarPath = "/apis".replaceAll("\\{format\\}","json");

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        if (limit != null)
        localVarQueryParams.addAll(apiClient.parameterToPairs("", "limit", limit));
        if (offset != null)
        localVarQueryParams.addAll(apiClient.parameterToPairs("", "offset", offset));
        if (query != null)
        localVarQueryParams.addAll(apiClient.parameterToPairs("", "query", query));

        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        if (accept != null)
        localVarHeaderParams.put("Accept", apiClient.parameterToString(accept));
        if (ifNoneMatch != null)
        localVarHeaderParams.put("If-None-Match", apiClient.parameterToString(ifNoneMatch));

        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) localVarHeaderParams.put("Accept", localVarAccept);

        final String[] localVarContentTypes = {
            "application/json"
        };
        final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        if(progressListener != null) {
            apiClient.getHttpClient().networkInterceptors().add(new com.squareup.okhttp.Interceptor() {
                @Override
                public com.squareup.okhttp.Response intercept(com.squareup.okhttp.Interceptor.Chain chain) throws IOException {
                    com.squareup.okhttp.Response originalResponse = chain.proceed(chain.request());
                    return originalResponse.newBuilder()
                    .body(new ProgressResponseBody(originalResponse.body(), progressListener))
                    .build();
                }
            });
        }

        String[] localVarAuthNames = new String[] {  };
        return apiClient.buildCall(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAuthNames, progressRequestListener);
    }
    
    @SuppressWarnings("rawtypes")
    private com.squareup.okhttp.Call apisGetValidateBeforeCall(Integer limit, Integer offset, String query, String accept, String ifNoneMatch, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        
        
        com.squareup.okhttp.Call call = apisGetCall(limit, offset, query, accept, ifNoneMatch, progressListener, progressRequestListener);
        return call;

        
        
        
        
    }

    /**
     * Retrieve/Search APIs 
     * This operation provides you a list of available APIs qualifying under a given search condition.  Each retrieved API is represented with a minimal amount of attributes. If you want to get complete details of an API, you need to use **Get details of an API** operation. 
     * @param limit Maximum size of resource array to return.  (optional, default to 25)
     * @param offset Starting point within the complete list of items qualified.  (optional, default to 0)
     * @param query **Search condition**.  You can search in attributes by using an **\&quot;&lt;attribute&gt;:\&quot;** modifier.  Eg. \&quot;provider:wso2\&quot; will match an API if the provider of the API is exactly \&quot;wso2\&quot;.  Additionally you can use wildcards.  Eg. \&quot;provider:wso2*\&quot; will match an API if the provider of the API starts with \&quot;wso2\&quot;.  Supported attribute modifiers are [**version, context, lifeCycleStatus, description, subcontext, doc, provider**]  If no advanced attribute modifier has been specified, search will match the given query string against API Name.  (optional)
     * @param accept Media types acceptable for the response. Default is application/json.  (optional, default to application/json)
     * @param ifNoneMatch Validator for conditional requests; based on the ETag of the formerly retrieved variant of the resourec.  (optional)
     * @return APIList
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public APIList apisGet(Integer limit, Integer offset, String query, String accept, String ifNoneMatch) throws ApiException {
        ApiResponse<APIList> resp = apisGetWithHttpInfo(limit, offset, query, accept, ifNoneMatch);
        return resp.getData();
    }

    /**
     * Retrieve/Search APIs 
     * This operation provides you a list of available APIs qualifying under a given search condition.  Each retrieved API is represented with a minimal amount of attributes. If you want to get complete details of an API, you need to use **Get details of an API** operation. 
     * @param limit Maximum size of resource array to return.  (optional, default to 25)
     * @param offset Starting point within the complete list of items qualified.  (optional, default to 0)
     * @param query **Search condition**.  You can search in attributes by using an **\&quot;&lt;attribute&gt;:\&quot;** modifier.  Eg. \&quot;provider:wso2\&quot; will match an API if the provider of the API is exactly \&quot;wso2\&quot;.  Additionally you can use wildcards.  Eg. \&quot;provider:wso2*\&quot; will match an API if the provider of the API starts with \&quot;wso2\&quot;.  Supported attribute modifiers are [**version, context, lifeCycleStatus, description, subcontext, doc, provider**]  If no advanced attribute modifier has been specified, search will match the given query string against API Name.  (optional)
     * @param accept Media types acceptable for the response. Default is application/json.  (optional, default to application/json)
     * @param ifNoneMatch Validator for conditional requests; based on the ETag of the formerly retrieved variant of the resourec.  (optional)
     * @return ApiResponse&lt;APIList&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ApiResponse<APIList> apisGetWithHttpInfo(Integer limit, Integer offset, String query, String accept, String ifNoneMatch) throws ApiException {
        com.squareup.okhttp.Call call = apisGetValidateBeforeCall(limit, offset, query, accept, ifNoneMatch, null, null);
        Type localVarReturnType = new TypeToken<APIList>(){}.getType();
        return apiClient.execute(call, localVarReturnType);
    }

    /**
     * Retrieve/Search APIs  (asynchronously)
     * This operation provides you a list of available APIs qualifying under a given search condition.  Each retrieved API is represented with a minimal amount of attributes. If you want to get complete details of an API, you need to use **Get details of an API** operation. 
     * @param limit Maximum size of resource array to return.  (optional, default to 25)
     * @param offset Starting point within the complete list of items qualified.  (optional, default to 0)
     * @param query **Search condition**.  You can search in attributes by using an **\&quot;&lt;attribute&gt;:\&quot;** modifier.  Eg. \&quot;provider:wso2\&quot; will match an API if the provider of the API is exactly \&quot;wso2\&quot;.  Additionally you can use wildcards.  Eg. \&quot;provider:wso2*\&quot; will match an API if the provider of the API starts with \&quot;wso2\&quot;.  Supported attribute modifiers are [**version, context, lifeCycleStatus, description, subcontext, doc, provider**]  If no advanced attribute modifier has been specified, search will match the given query string against API Name.  (optional)
     * @param accept Media types acceptable for the response. Default is application/json.  (optional, default to application/json)
     * @param ifNoneMatch Validator for conditional requests; based on the ETag of the formerly retrieved variant of the resourec.  (optional)
     * @param callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     */
    public com.squareup.okhttp.Call apisGetAsync(Integer limit, Integer offset, String query, String accept, String ifNoneMatch, final ApiCallback<APIList> callback) throws ApiException {

        ProgressResponseBody.ProgressListener progressListener = null;
        ProgressRequestBody.ProgressRequestListener progressRequestListener = null;

        if (callback != null) {
            progressListener = new ProgressResponseBody.ProgressListener() {
                @Override
                public void update(long bytesRead, long contentLength, boolean done) {
                    callback.onDownloadProgress(bytesRead, contentLength, done);
                }
            };

            progressRequestListener = new ProgressRequestBody.ProgressRequestListener() {
                @Override
                public void onRequestProgress(long bytesWritten, long contentLength, boolean done) {
                    callback.onUploadProgress(bytesWritten, contentLength, done);
                }
            };
        }

        com.squareup.okhttp.Call call = apisGetValidateBeforeCall(limit, offset, query, accept, ifNoneMatch, progressListener, progressRequestListener);
        Type localVarReturnType = new TypeToken<APIList>(){}.getType();
        apiClient.executeAsync(call, localVarReturnType, callback);
        return call;
    }
    /* Build call for apisHead */
    private com.squareup.okhttp.Call apisHeadCall(String query, String accept, String ifNoneMatch, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        Object localVarPostBody = null;
        
        // create path and map variables
        String localVarPath = "/apis".replaceAll("\\{format\\}","json");

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        if (query != null)
        localVarQueryParams.addAll(apiClient.parameterToPairs("", "query", query));

        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        if (accept != null)
        localVarHeaderParams.put("Accept", apiClient.parameterToString(accept));
        if (ifNoneMatch != null)
        localVarHeaderParams.put("If-None-Match", apiClient.parameterToString(ifNoneMatch));

        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) localVarHeaderParams.put("Accept", localVarAccept);

        final String[] localVarContentTypes = {
            "application/json"
        };
        final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        if(progressListener != null) {
            apiClient.getHttpClient().networkInterceptors().add(new com.squareup.okhttp.Interceptor() {
                @Override
                public com.squareup.okhttp.Response intercept(com.squareup.okhttp.Interceptor.Chain chain) throws IOException {
                    com.squareup.okhttp.Response originalResponse = chain.proceed(chain.request());
                    return originalResponse.newBuilder()
                    .body(new ProgressResponseBody(originalResponse.body(), progressListener))
                    .build();
                }
            });
        }

        String[] localVarAuthNames = new String[] {  };
        return apiClient.buildCall(localVarPath, "HEAD", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAuthNames, progressRequestListener);
    }
    
    @SuppressWarnings("rawtypes")
    private com.squareup.okhttp.Call apisHeadValidateBeforeCall(String query, String accept, String ifNoneMatch, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        
        
        com.squareup.okhttp.Call call = apisHeadCall(query, accept, ifNoneMatch, progressListener, progressRequestListener);
        return call;

        
        
        
        
    }

    /**
     * Check given API attibute name is already exist 
     * Using this operation, you can check a given API context is already used. You need to provide the context name you want to check. 
     * @param query **Search condition**.  You can search in attributes by using an **\&quot;&lt;attribute&gt;:\&quot;** modifier.  Eg. \&quot;provider:wso2\&quot; will match an API if the provider of the API is exactly \&quot;wso2\&quot;.  Additionally you can use wildcards.  Eg. \&quot;provider:wso2*\&quot; will match an API if the provider of the API starts with \&quot;wso2\&quot;.  Supported attribute modifiers are [**version, context, lifeCycleStatus, description, subcontext, doc, provider**]  If no advanced attribute modifier has been specified, search will match the given query string against API Name.  (optional)
     * @param accept Media types acceptable for the response. Default is application/json.  (optional, default to application/json)
     * @param ifNoneMatch Validator for conditional requests; based on the ETag of the formerly retrieved variant of the resourec.  (optional)
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public void apisHead(String query, String accept, String ifNoneMatch) throws ApiException {
        apisHeadWithHttpInfo(query, accept, ifNoneMatch);
    }

    /**
     * Check given API attibute name is already exist 
     * Using this operation, you can check a given API context is already used. You need to provide the context name you want to check. 
     * @param query **Search condition**.  You can search in attributes by using an **\&quot;&lt;attribute&gt;:\&quot;** modifier.  Eg. \&quot;provider:wso2\&quot; will match an API if the provider of the API is exactly \&quot;wso2\&quot;.  Additionally you can use wildcards.  Eg. \&quot;provider:wso2*\&quot; will match an API if the provider of the API starts with \&quot;wso2\&quot;.  Supported attribute modifiers are [**version, context, lifeCycleStatus, description, subcontext, doc, provider**]  If no advanced attribute modifier has been specified, search will match the given query string against API Name.  (optional)
     * @param accept Media types acceptable for the response. Default is application/json.  (optional, default to application/json)
     * @param ifNoneMatch Validator for conditional requests; based on the ETag of the formerly retrieved variant of the resourec.  (optional)
     * @return ApiResponse&lt;Void&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ApiResponse<Void> apisHeadWithHttpInfo(String query, String accept, String ifNoneMatch) throws ApiException {
        com.squareup.okhttp.Call call = apisHeadValidateBeforeCall(query, accept, ifNoneMatch, null, null);
        return apiClient.execute(call);
    }

    /**
     * Check given API attibute name is already exist  (asynchronously)
     * Using this operation, you can check a given API context is already used. You need to provide the context name you want to check. 
     * @param query **Search condition**.  You can search in attributes by using an **\&quot;&lt;attribute&gt;:\&quot;** modifier.  Eg. \&quot;provider:wso2\&quot; will match an API if the provider of the API is exactly \&quot;wso2\&quot;.  Additionally you can use wildcards.  Eg. \&quot;provider:wso2*\&quot; will match an API if the provider of the API starts with \&quot;wso2\&quot;.  Supported attribute modifiers are [**version, context, lifeCycleStatus, description, subcontext, doc, provider**]  If no advanced attribute modifier has been specified, search will match the given query string against API Name.  (optional)
     * @param accept Media types acceptable for the response. Default is application/json.  (optional, default to application/json)
     * @param ifNoneMatch Validator for conditional requests; based on the ETag of the formerly retrieved variant of the resourec.  (optional)
     * @param callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     */
    public com.squareup.okhttp.Call apisHeadAsync(String query, String accept, String ifNoneMatch, final ApiCallback<Void> callback) throws ApiException {

        ProgressResponseBody.ProgressListener progressListener = null;
        ProgressRequestBody.ProgressRequestListener progressRequestListener = null;

        if (callback != null) {
            progressListener = new ProgressResponseBody.ProgressListener() {
                @Override
                public void update(long bytesRead, long contentLength, boolean done) {
                    callback.onDownloadProgress(bytesRead, contentLength, done);
                }
            };

            progressRequestListener = new ProgressRequestBody.ProgressRequestListener() {
                @Override
                public void onRequestProgress(long bytesWritten, long contentLength, boolean done) {
                    callback.onUploadProgress(bytesWritten, contentLength, done);
                }
            };
        }

        com.squareup.okhttp.Call call = apisHeadValidateBeforeCall(query, accept, ifNoneMatch, progressListener, progressRequestListener);
        apiClient.executeAsync(call, callback);
        return call;
    }
    /* Build call for apisPost */
    private com.squareup.okhttp.Call apisPostCall(API body, String contentType, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        Object localVarPostBody = body;
        
        // create path and map variables
        String localVarPath = "/apis".replaceAll("\\{format\\}","json");

        List<Pair> localVarQueryParams = new ArrayList<Pair>();

        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        if (contentType != null)
        localVarHeaderParams.put("Content-Type", apiClient.parameterToString(contentType));

        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) localVarHeaderParams.put("Accept", localVarAccept);

        final String[] localVarContentTypes = {
            "application/json"
        };
        final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        if(progressListener != null) {
            apiClient.getHttpClient().networkInterceptors().add(new com.squareup.okhttp.Interceptor() {
                @Override
                public com.squareup.okhttp.Response intercept(com.squareup.okhttp.Interceptor.Chain chain) throws IOException {
                    com.squareup.okhttp.Response originalResponse = chain.proceed(chain.request());
                    return originalResponse.newBuilder()
                    .body(new ProgressResponseBody(originalResponse.body(), progressListener))
                    .build();
                }
            });
        }

        String[] localVarAuthNames = new String[] {  };
        return apiClient.buildCall(localVarPath, "POST", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAuthNames, progressRequestListener);
    }
    
    @SuppressWarnings("rawtypes")
    private com.squareup.okhttp.Call apisPostValidateBeforeCall(API body, String contentType, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        
        // verify the required parameter 'body' is set
        if (body == null) {
            throw new ApiException("Missing the required parameter 'body' when calling apisPost(Async)");
        }
        
        // verify the required parameter 'contentType' is set
        if (contentType == null) {
            throw new ApiException("Missing the required parameter 'contentType' when calling apisPost(Async)");
        }
        
        
        com.squareup.okhttp.Call call = apisPostCall(body, contentType, progressListener, progressRequestListener);
        return call;

        
        
        
        
    }

    /**
     * Create a new API
     * This operation can be used to create a new API specifying the details of the API in the payload. The new API will be in &#x60;CREATED&#x60; state.  There is a special capability for a user who has &#x60;APIM Admin&#x60; permission such that he can create APIs on behalf of other users. For that he can to specify &#x60;\&quot;provider\&quot; : \&quot;some_other_user\&quot;&#x60; in the payload so that the API&#39;s creator will be shown as &#x60;some_other_user&#x60; in the UI. 
     * @param body API object that needs to be added  (required)
     * @param contentType Media type of the entity in the body. Default is application/json.  (required)
     * @return API
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public API apisPost(API body, String contentType) throws ApiException {
        ApiResponse<API> resp = apisPostWithHttpInfo(body, contentType);
        return resp.getData();
    }

    /**
     * Create a new API
     * This operation can be used to create a new API specifying the details of the API in the payload. The new API will be in &#x60;CREATED&#x60; state.  There is a special capability for a user who has &#x60;APIM Admin&#x60; permission such that he can create APIs on behalf of other users. For that he can to specify &#x60;\&quot;provider\&quot; : \&quot;some_other_user\&quot;&#x60; in the payload so that the API&#39;s creator will be shown as &#x60;some_other_user&#x60; in the UI. 
     * @param body API object that needs to be added  (required)
     * @param contentType Media type of the entity in the body. Default is application/json.  (required)
     * @return ApiResponse&lt;API&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ApiResponse<API> apisPostWithHttpInfo(API body, String contentType) throws ApiException {
        com.squareup.okhttp.Call call = apisPostValidateBeforeCall(body, contentType, null, null);
        Type localVarReturnType = new TypeToken<API>(){}.getType();
        return apiClient.execute(call, localVarReturnType);
    }

    /**
     * Create a new API (asynchronously)
     * This operation can be used to create a new API specifying the details of the API in the payload. The new API will be in &#x60;CREATED&#x60; state.  There is a special capability for a user who has &#x60;APIM Admin&#x60; permission such that he can create APIs on behalf of other users. For that he can to specify &#x60;\&quot;provider\&quot; : \&quot;some_other_user\&quot;&#x60; in the payload so that the API&#39;s creator will be shown as &#x60;some_other_user&#x60; in the UI. 
     * @param body API object that needs to be added  (required)
     * @param contentType Media type of the entity in the body. Default is application/json.  (required)
     * @param callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     */
    public com.squareup.okhttp.Call apisPostAsync(API body, String contentType, final ApiCallback<API> callback) throws ApiException {

        ProgressResponseBody.ProgressListener progressListener = null;
        ProgressRequestBody.ProgressRequestListener progressRequestListener = null;

        if (callback != null) {
            progressListener = new ProgressResponseBody.ProgressListener() {
                @Override
                public void update(long bytesRead, long contentLength, boolean done) {
                    callback.onDownloadProgress(bytesRead, contentLength, done);
                }
            };

            progressRequestListener = new ProgressRequestBody.ProgressRequestListener() {
                @Override
                public void onRequestProgress(long bytesWritten, long contentLength, boolean done) {
                    callback.onUploadProgress(bytesWritten, contentLength, done);
                }
            };
        }

        com.squareup.okhttp.Call call = apisPostValidateBeforeCall(body, contentType, progressListener, progressRequestListener);
        Type localVarReturnType = new TypeToken<API>(){}.getType();
        apiClient.executeAsync(call, localVarReturnType, callback);
        return call;
    }
}
