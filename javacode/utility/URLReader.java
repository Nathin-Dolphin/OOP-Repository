
/**
 * @author Nathin Wascher
 * @version 1.0
 * @since March 26, 2020
 */

package utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.net.MalformedURLException;
import java.net.URL;

import java.util.ArrayList;

public class URLReader extends JSONParser {
    private BufferedReader br;
    private URL openURL;
    private String baseURL, tempString;
    private ArrayList<String> urlContents, urlList;

    public URLReader() {
        urlContents = urlList = new ArrayList<String>();
    }

    public URLReader(String url) {
        baseURL = url;
        urlContents = urlList = new ArrayList<String>();
    }

    public ArrayList<String> getRawURLContents() {
        return urlContents;
    }

    public ArrayList<String> getParsedURLContents() {
        return parseJSON(urlContents);
    }

    public ArrayList<String> getURLList() {
        return getURLList(urlContents);
    }

    public boolean isValidURL(String url) {
        try {
            openURL = new URL(url);
            return true;
        } catch (MalformedURLException e) {
            return false;
        }
    }

    public void readURL(String url, boolean printContents) {
        if (isValidURL(url)) {
            try {
                br = new BufferedReader(new InputStreamReader(openURL.openStream()));
                // lines are being printed twice
                // lines are being printed twice
                // just like this
                // just like this
                while ((tempString = br.readLine()) != null) {
                    urlContents.add(tempString);
                    System.out.println(tempString);
                    if (printContents)
                        System.out.println(tempString);
                }
                br.close();

            } catch (IOException e) {
                System.out.println("ERROR: I/O EXCEPTION");
            }
        } else
            System.out.println("ERROR: INVALID URL");
    }

    public void readURLIndex(String url, boolean printContents) {
        readURL(url, false);
        urlList = getURLList();
        try {
            for (String s : urlList) {
                readURL(s, printContents);
            }
        } catch (Exception e) {
            System.out.println("Warning: No Valid URL Links");
        }
    }

    public boolean isValidURL() {
        return isValidURL(baseURL);
    }

    public void readURLIndex() {
        readURLIndex(baseURL, false);
    }

    public void readURLIndex(boolean printContents) {
        readURLIndex(baseURL, printContents);
    }

    public void readURL() {
        readURL(baseURL, false);
    }

    public void readURL(boolean printContents) {
        readURL(baseURL, printContents);
    }
}