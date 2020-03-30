
/**
 * @author Nathin Wascher
 * @version 1.2
 * @since March 27, 2020
 */

package utility.json;

import java.net.MalformedURLException;
import java.net.URL;

import java.util.ArrayList;

public class JSONParser {
    private ArrayList<String> parsedList, urls, tempArrayList;
    private String[] parsedLine;
    private String string, singleChar;

    public JSONParser() {
    }

    public ArrayList<String> parseJSON(ArrayList<String> urlContents) {
        if (urlContents.size() <= 2)
            return urlContents;

        parsedList = new ArrayList<String>();
        for (int h = 0; h < urlContents.size(); h++) {
            parsedLine = urlContents.get(h).split("\"");

            for (int i = 0; i < parsedLine.length; i++) {
                string = parsedLine[i];
                string = string.replaceAll("\t", "");

                if ((i - 2) % 4 == 0 & string.startsWith(":")) {
                    if (string.endsWith("[{")) {
                        string = "[{";
                    } else if (string.endsWith("{")) {
                        string = "{";
                    } else {
                        string = "";
                    }
                } else if (string.equals(",") || string.equals(", ")) {
                    string = "";
                } else if (i % 2 == 0) {
                    string = string.replaceAll(" ", "");
                }

                if (string.startsWith("}")) {
                    if (string.equals("},")) {
                        string = "}";

                    } else if (string.equals("},{")) {
                        string = "}";
                        parsedList.add(string);
                        string = "{";

                    } else if (string.equals("}]")) {
                        string = "}";
                        parsedList.add(string);
                        string = "]";
                    }
                } else if (string.endsWith("{")) {
                    if (string.equals(",{")) {
                        string = "{";

                    } else if (string.equals("[{")) {
                        string = "[";
                        parsedList.add(string);
                        string = "{";
                    }
                }

                if (!string.equals("")) {
                    parsedList.add(string);
                }
            }
        }
        return parsedList;

    }

    public ArrayList<String> getURLList(ArrayList<String> urlContents) {
        urls = new ArrayList<String>();
        tempArrayList = parseJSON(urlContents);
        for (String tempString : tempArrayList) {
            try {
                new URL(tempString);
                urls.add(tempString);
            } catch (MalformedURLException e) {
            }
        }
        return urls;
    }
}