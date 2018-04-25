package com.example.rgs.listfragmentdemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;





public class EventsManager {

    //note to self, fix line 54 apy.py from PUIID to PUUID
    //You really need to write a get_event_by_PUIID in the API

    public static void addUser(String username, String realname, String password) throws IOException {

        URL obj = new URL("https://cutie-computie.org:808/signup");
        JSONObject theJSON = new JSONObject();


        try {
            theJSON.put("username",username);
            theJSON.put("realname", realname);
            theJSON.put("password", password);



        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", "Safari/11.0.3");
        con.setRequestProperty("Content-Type", "application/json");
        con.setDoOutput(true);
        OutputStream os = con.getOutputStream();


        //os.write(POST_PARAMS.getBytes());
        os.write(theJSON.toString().getBytes());
        os.flush();
        os.close();

        int responseCode = con.getResponseCode();
        System.out.println("POST Response Code :: " + responseCode);
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            System.out.println(response.toString());

        } else {
            System.out.println("Add user did not work");
        }


    }

    public static void addPost(String username, String password, String title, String contents, String tags) throws IOException {

        URL obj = new URL("https://cutie-computie.org/add-post");
        JSONObject theJSON = new JSONObject();


        try {
            theJSON.put("username",username);
            theJSON.put("password", password);
            theJSON.put("title", title);
            theJSON.put("contents", contents);
            theJSON.put("tags", tags);



        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("POST");
        //con.setRequestProperty("User-Agent", "Safari/11.0.3");
        con.setRequestProperty("Content-Type", "application/json");
        con.setDoOutput(true);
        OutputStream os = con.getOutputStream();
        final String POST_PARAMS = "Raghav";

        //os.write(POST_PARAMS.getBytes());
        os.write(theJSON.toString().getBytes());
        os.flush();
        os.close();

        int responseCode = con.getResponseCode();
        System.out.println("POST Response Code :: " + responseCode);
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            System.out.println(response.toString());

        } else {
            System.out.println("adding the post did not work");
        }


    }

    public static void RSVP(String username, String password, String PUUID) throws IOException {

        URL obj = new URL("https://cutie-computie.org/RSVP");
        JSONObject theJSON = new JSONObject();


        try {
            theJSON.put("username",username);
            theJSON.put("password", password);
            theJSON.put("PUUID", PUUID);
            //note to self, fix line 54 apy.py from PUIID to PUUID



        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", "Safari/11.0.3");
        con.setRequestProperty("Content-Type", "application/json");
        con.setDoOutput(true);
        OutputStream os = con.getOutputStream();
        final String POST_PARAMS = "Raghav";

        //os.write(POST_PARAMS.getBytes());
        os.write(theJSON.toString().getBytes());
        os.flush();
        os.close();

        int responseCode = con.getResponseCode();
        System.out.println("POST Response Code :: " + responseCode);
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            System.out.println(response.toString());

        } else {
            System.out.println("RSVPing did not work");
        }


    }

    public static void comment(String username, String password, String PUUID, String comments) throws IOException {

        URL obj = new URL("https://cutie-computie.org/comment");
        JSONObject theJSON = new JSONObject();


        try {
            theJSON.put("username",username);
            theJSON.put("password", password);
            theJSON.put("PUUID", PUUID);
            theJSON.put("comments", comments);
            //note to self, fix line 54 apy.py from PUIID to PUUID



        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", "Safari/11.0.3");
        con.setRequestProperty("Content-Type", "application/json");
        con.setDoOutput(true);
        OutputStream os = con.getOutputStream();
        final String POST_PARAMS = "Raghav";

        //os.write(POST_PARAMS.getBytes());
        os.write(theJSON.toString().getBytes());
        os.flush();
        os.close();

        int responseCode = con.getResponseCode();
        //System.out.println("POST Response Code :: " + responseCode);
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            System.out.println(response.toString());

        } else {
            System.out.println("commenting did not work");
        }


    }

    public static JSONArray getEventsByTags(String tags) throws IOException {
        URL obj = new URL("https://cutie-computie.org/get-events-by-tag/tags=" + tags);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", "Safari/11.0.3");
        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);
        StringBuilder response = new StringBuilder();
        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;


            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine + "\n");
            }
            in.close();

            // print result
            System.out.println(response.toString());
        } else {
            System.out.println("GET request not worked");
        }

        JSONArray ja = new JSONArray();
        try {
            ja = new JSONArray(response.toString());
        } catch (JSONException e){
            System.out.println(e);
        }


        return ja;

    }

    public static JSONObject getEventByPUUID(String PUUID) throws IOException {
        URL obj = new URL("https://cutie-computie.org/get-event/PUUID=" + PUUID);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", "Safari/11.0.3");
        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);
        StringBuilder response = new StringBuilder();
        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;


            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine + "\n");
            }
            in.close();

            // print result
            System.out.println(response.toString());
        } else {
            System.out.println("GET request not worked");
        }

        JSONObject jo = new JSONObject();
        try {
            jo = new JSONObject(response.toString());
        } catch (JSONException e){
            System.out.println(e);
        }


        return jo;

    }

}
