
/**
 * @author Nathin Wascher
 * @version 1.1
 * @since March 27, 2020
 */

package utility.json;

import java.net.MalformedURLException;
import java.net.URL;

import java.util.ArrayList;

public class JSONParser {
    private ArrayList<String> parsedList, urls, tempArrayList;
    private String[] parsedLine;
    private String currentLine, s;

    public JSONParser() {
    }

    public ArrayList<String> parseJSON(ArrayList<String> urlContents) {
        if (urlContents.size() <= 2)
            return urlContents;

        parsedList = new ArrayList<String>();
        for (int a = 0; a < urlContents.size(); a++) {
            currentLine = urlContents.get(a);
            parsedLine = currentLine.split("\"");

            for (int b = 0; b < parsedLine.length; b++) {
                s = parsedLine[b];
                if (s.equals(":") || s.equals(": ") || s.equals(", ") || s.equals(","))
                    b++;

                if (b < parsedLine.length)
                    parsedList.add(parsedLine[b]);
            }
        }
        return parsedList;
    }

    public ArrayList<String> getURLList(ArrayList<String> urlContents) {
        urls = new ArrayList<String>();
        tempArrayList = parseJSON(urlContents);
        for (String s : tempArrayList) {
            try {
                new URL(s);
                urls.add(s);
            } catch (MalformedURLException e) {
            }
        }
        return urls;
    }
}