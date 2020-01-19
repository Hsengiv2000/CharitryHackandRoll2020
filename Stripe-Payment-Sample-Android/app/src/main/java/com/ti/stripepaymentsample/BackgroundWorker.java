package com.ti.stripepaymentsample;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;



class BackgroundWorker extends AsyncTask<String, String, String> {
    Context context;
    public  BackgroundWorker(Context ct){context = ct;}
    @Override
    protected String doInBackground(String... params) {
        String name = params[0];
        String myurl = "http://10.0.2.2:1234/pay";
        Log.i("jinsdf", "fsgdfg");

        try {
            /*
            URL url = new URL(myurl);
            HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
          // httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            String post_data = URLEncoder.encode(myurl,"UTF-8");
            Log.i("POSTDATA", post_data);
            bufferedWriter.write(post_data);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();
            Log.i("hero","h");

        } catch (MalformedURLException e) {
            e.printStackTrace();
            Log.i("hero","g");
        } catch (IOException e) {
            e.printStackTrace();
            Log.i("hero","i");
        }
        return new String("Done");*/

            Log.i("works i guess","i guess");
            RequestQueue queue = Volley.newRequestQueue(context);
            queue.start();
            String url = "http://10.0.2.2:1234/"+name;

// Request a string response from the provided URL.
            StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // Display the first 500 characters of the response string.
                            Log.i("hehehehehehe", "Response is: " + response.substring(0, 500));
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.i("here", "only");
                }
            });
            queue.add(stringRequest);

// Add the request to the RequestQueue.

        } catch (Exception e) {

            Log.i("fff", "d");
        }

        return new String("heyeyeye");
    }
}
