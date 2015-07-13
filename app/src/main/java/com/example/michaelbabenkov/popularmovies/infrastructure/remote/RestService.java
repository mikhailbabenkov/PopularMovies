package com.example.michaelbabenkov.popularmovies.infrastructure.remote;

import com.example.michaelbabenkov.popularmovies.BuildConfig;
import com.example.michaelbabenkov.popularmovies.infrastructure.utils.Constants;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;

/**
 * Created by michaelbabenkov on 9/07/15.
 */
public enum RestService {
    INSTANCE;

    private MovieApi mRestService;

    public MovieApi get(){
        if(mRestService == null) {
            final RestAdapter restAdapter = new RestAdapter.Builder()
                    .setLogLevel(BuildConfig.DEBUG ?
                            RestAdapter.LogLevel.FULL : RestAdapter.LogLevel.NONE)
                    .setEndpoint(Constants.API_ENDPOINT)
                    .setRequestInterceptor(new ApiRequestInterceptor())
                    .build();

            mRestService = restAdapter.create(MovieApi.class);
        }

        return mRestService;
    }

    private class ApiRequestInterceptor implements RequestInterceptor {
        @Override
        public void intercept(RequestFacade request) {
            request.addQueryParam("api_key", BuildConfig.API_KEY);

        }
    }
}
