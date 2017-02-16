package com.yjs.ganhuo.net;

import java.io.Serializable;

/**
 * Created by yangjingsong on 16/6/15.
 */
public class ApiResponse<T> implements Serializable {
    private boolean error;
    private T results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public T getResults() {
        return results;
    }

    public void setResults(T results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "ApiResponse{" +
                "error=" + error +
                ", results=" + results +
                '}';
    }
}
