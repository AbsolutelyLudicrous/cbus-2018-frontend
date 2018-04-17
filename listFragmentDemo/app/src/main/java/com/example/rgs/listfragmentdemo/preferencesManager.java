package com.example.rgs.listfragmentdemo;
import java.io.*;
import android.content.Context;
import java.util.ArrayList;


public class preferencesManager {
    //deals with the file IO when storing username, realname and password. Creates the file if it does not exist,
    //writes our default test-user username and

    public static String getPassword() throws IOException {
        //will overwrite later
        return "password";
    }

    public static String getUsername() throws IOException {
        //will overwrite later
        return "demo-user";
    }

    public static void append_tag(Context context, String tags) throws IOException {
        try {

            FileInputStream fis = new FileInputStream("Tags.txt");
            int c;
            String input = "";

            while ((c = fis.read()) != -1) {
                input += Character.toString((char) c);
            }

            input += ", " + tags;

            fis.close();

            FileOutputStream fos = new FileOutputStream("Tags.txt", false);

            fos.write(input.getBytes());
            fos.close();

        } catch (FileNotFoundException e) {
            //create the file
            FileOutputStream fout = new FileOutputStream(new File(context.getFilesDir(), "Tags.txt"));
            fout.write(tags.getBytes());
            fout.close();

        }

    }

    public static void append_event(Context context, String PUUID) throws IOException {
        try {
            FileOutputStream fos = new FileOutputStream("Events.txt", true);
            String input = " " + PUUID;
            fos.write(input.getBytes());
            fos.close();
        } catch (FileNotFoundException e) {
            FileOutputStream fos = new FileOutputStream(new File(context.getFilesDir(), "Events.txt"));
            String input = " " + PUUID;
            fos.write(input.getBytes());
            fos.close();
        }

    }

    public static String get_tags() throws IOException {
        try {
            FileInputStream fis = new FileInputStream("Tags.txt");
            int c;
            String output = "";

            while ((c = fis.read()) != -1) {
                output += Character.toString((char) c);
            }

            fis.close();
            return output;
        } catch (FileNotFoundException e) {
            return "";
        }
    }

    public static String[] get_events() throws IOException {
        try {
            FileInputStream fis = new FileInputStream("Tags.txt");
            int c;
            String output = "";

            while ((c = fis.read()) != -1) {
                output += Character.toString((char) c);
            }

            fis.close();
            //probably want to break up output into an arraylist, figure out the length of that arrayList and copy that over
            //into our returnArr
            ArrayList<String> mutable_list = new ArrayList<>();

            while (output.length() > 0) {
                if (output.indexOf(" ") != -1) {
                    mutable_list.add(output.substring(0, output.indexOf(" ")));
                    output = output.substring(output.indexOf(" ") + 1);
                } else {
                    //have the very last PUUID to deal with
                    mutable_list.add(output);
                }

            }

            String[] returnArr = new String[mutable_list.size()];
            for (int i = 0; i < mutable_list.size(); i++) {
                returnArr[i] = mutable_list.get(i);
            }

            return returnArr;
        } catch (FileNotFoundException e) {
            String[] returnArr = {};
            return returnArr;
        }
    }

}
