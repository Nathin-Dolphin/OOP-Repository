
/**
 * @author Nathin Wascher
 * @version 1.3
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
    private String tempString;
    private ArrayList<String> urlContents, urlIndexContents, urlList;

    public URLReader() {
    }

    public ArrayList<String> getParsedURLContents(String url) {
        urlContents = readURL(url);
        return parseJSON(urlContents);
    }

    public boolean isValidURL(String url) {
        try {
            openURL = new URL(url);
            return true;
        } catch (MalformedURLException e) {
            return false;
        }
    }

    public ArrayList<String> readURL(String url, boolean printContents) {
        System.out.println("\nREADING URL:  " + url);
        urlContents = new ArrayList<String>();

        if (isValidURL(url)) {
            try {
                br = new BufferedReader(new InputStreamReader(openURL.openStream()));
                while ((tempString = br.readLine()) != null) {
                    urlContents.add(tempString);
                    if (printContents)
                        System.out.println(tempString);
                }
                br.close();

            } catch (IOException e) {
                System.out.println("\nERROR: I/O EXCEPTION");
            }
        } else
            System.out.println("\nERROR: INVALID URL");
        return urlContents;
    }

    public ArrayList<String> readURLIndex(String url, boolean printContents) {
        readURL(url, printContents);
        getURLList();
        urlIndexContents = new ArrayList<String>();

        try {
            for (String s : urlList)
                urlIndexContents.addAll(readURL(s, printContents));
        } catch (Exception e) {
            System.out.println("Warning: No Valid URL Links");
        }
        return urlIndexContents;
    }

    public ArrayList<String> getURLList() {
        urlList = new ArrayList<String>();
        urlContents = parseJSON(urlContents);
        
        for (String tempString : urlContents) {
            try {
                new URL(tempString);
                urlList.add(tempString);
            } catch (MalformedURLException e) {
            }
        }
        return urlList;
    }

    // Overloaded Methods
    public ArrayList<String> readURLIndex(String url) {
        return readURLIndex(url, false);
    }

    public ArrayList<String> readURL(String url) {
        return readURL(url, false);
    }
}