
/**
 * @author Nathin Wascher
 * @version 1.0
 * @since March 27, 2020
 */

package utility;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class JSONParser {
    private ArrayList<String> parsedList, urls, tempArrayList;
    private String[] parsedLine;
    private String currentLine;

    JSONParser() {
    }

    public ArrayList<String> parseJSON(ArrayList<String> urlContents) {
        if (urlContents.size() <= 2)
            return urlContents;

        parsedList = new ArrayList<String>();
        for (int a = 1; a < urlContents.size() - 1; a++) {
            currentLine = urlContents.get(a);
            parsedLine = currentLine.split("\"");

            for (int b = 0; b < parsedLine.length; b++) {
                while (parsedLine[b].equals(":") || parsedLine[b].equals(",") || parsedLine[b].equals(", "))
                    b++;
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
                URL url = new URL(s);
                urls.add(s);
            } catch (MalformedURLException e) {
            }
        }
        return urls;
    }
}