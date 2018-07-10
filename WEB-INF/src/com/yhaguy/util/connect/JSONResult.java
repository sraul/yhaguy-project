package com.yhaguy.util.connect;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class JSONResult {
	
	static final String URL_COTIZACIONES = "https://dolar.melizeche.com/api/1.0/";

	OkHttpClient client = new OkHttpClient();

    /**
     * Solicita y devuelve la respuesta del servidor http..
     */
	String run(String url) throws IOException {
	    Request request = new Request.Builder()
	        .url(url)
	        .build();

	    try (Response response = client.newCall(request).execute()) {
	      return response.body().string();
	    }
	  }	
    
    /**
     * get cotizaciones..
     */
    public String getCotizaciones() throws IOException {
        return this.run(URL_COTIZACIONES);
    }
}
