package com.example.effcode.simplehttpget;


import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.Buffer;
import java.nio.channels.Channels;
import java.nio.channels.WritableByteChannel;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class MainActivity {

public static void main(String a[])throws  Exception
{
    MainActivity mobj = new MainActivity();
    mobj.GET();
    mobj.POST();

}

    public void GET()throws Exception
    {
        String BASE_URL="http://192.168.100.6:8000";
        URL url = new URL(BASE_URL);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        int responseCode=con.getResponseCode();

        System.out.println("your given url is"+url);
        System.out.println("your response code is"+responseCode);

        BufferedReader br=new BufferedReader(new InputStreamReader(con.getInputStream()));
        String container;
        StringBuffer response=new StringBuffer();

        while((container=br.readLine())!=null)
        {
            response.append(container);
        }
        br.close();

        System.out.println(response);
    }
    public void POST()throws Exception {
        String BASE_URL = "http://192.168.100.6:8000";
        URL url = new URL(BASE_URL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);

        conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        conn.getURL();
        System.out.println(conn.getURL());
        conn.setDoOutput(true);

        byte[] out = "{\"subject\":\"Angular\",\"date\":\"15/3/2018\"}".getBytes(StandardCharsets.UTF_8);
        int length = out.length;
        conn.setFixedLengthStreamingMode(length);

        try (OutputStream os = conn.getOutputStream()) {
            os.write(out);
        }catch (Exception e)
        {
            e.printStackTrace();
        }


        System.out.println("Your given url is"+url);

        int responseCode=conn.getResponseCode();
        System.out.println("your Responsecode is:"+responseCode);

        BufferedReader br=new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String Container;
        StringBuffer response=new StringBuffer();
        while((Container=br.readLine())!=null)
        {
            response.append(Container);
        }

        System.out.println(response);

    }
}
