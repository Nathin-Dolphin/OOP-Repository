
/**
 * @author Nathin Wascher
 * @version 1.2.1
 * @since March 26, 2020
 */

package utility;

import utility.json.JSONParser;

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
    private boolean baseURLRead;
    private ArrayList<String> urlContents, urlList;

    public URLReader() {
        baseURLRead = false;
        urlContents = urlList = new ArrayList<String>();
    }

    public URLReader(String url) {
        baseURLRead = false;
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
        if (urlList == null)
            return getURLList(urlContents);
        else
            return urlList;
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
        System.out.println("\nREADING URL:  " + url);
        if (isValidURL(url)) {
            try {
                br = new BufferedReader(new InputStreamReader(openURL.openStream()));
                while ((tempString = br.readLine()) != null) {
                    urlContents.add(tempString);
                    if (printContents)
                        System.out.println(tempString);
                }
                br.close();
                if (baseURL == url)
                    baseURLRead = true;

            } catch (IOException e) {
                System.out.println("ERROR: I/O EXCEPTION");
            }
        } else
            System.out.println("ERROR: INVALID URL");
    }

    public void readURLIndex(String url, boolean printContents) {
        if (!baseURLRead)
            readURL(url, printContents);
        urlList = getURLList(urlContents);
        urlContents = new ArrayList<String>();

        try {
            for (String s : urlList)
                readURL(s, printContents);
        } catch (Exception e) {
            System.out.println("Warning: No Valid URL Links");
        }
    }

    // methods beyond this point are overloaded and call themselves with filled
    // parameters
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