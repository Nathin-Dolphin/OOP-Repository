
/**
 * @author Nathin Wascher
 * @version 1.4.1
 * @since March 27, 2020
 */

// [!] parseJSON(AR<S>) does account for values without quotation marks (ex. "number": 12,) [!]

package utility.json;

import java.net.MalformedURLException;
import java.net.URL;

import java.util.ArrayList;

public class JSONParser {
    private ArrayList<String> parsedList, urls, tempArrayList;
    private String[] parsedLine;
    private String string;

    public JSONParser() {
    }

    public JSONParser(ArrayList<String> jsonContents) {
        parseJSON(jsonContents);
    }

    public ArrayList<String> parseJSON(ArrayList<String> jsonContents) {
        if (jsonContents.size() <= 2)
            return jsonContents;

        parsedList = new ArrayList<String>();
        for (int h = 0; h < jsonContents.size(); h++) {
            parsedLine = jsonContents.get(h).split("\"");

            for (int i = 0; i < parsedLine.length; i++) {
                string = parsedLine[i];
                string = string.replaceAll("\t", "");

                // Removes the colons between the names and their values.
                // Also removes some commas not considered part of a name or value.
                if ((i - 2) % 4 == 0 & string.startsWith(":")) {
                    if (string.endsWith("[{")) {
                        string = "[{";

                    } else if (string.endsWith("{")) {
                        string = "{";

                    } else if (string.endsWith("[")) {
                        string = "[";

                    } else {
                        string = "";
                    }
                } else if (string.equals(",") || string.equals(", ")) {
                    string = "";
                } else if (i % 2 == 0) {
                    string = string.replaceAll(" ", "");
                }

                // Fixes inconsistencies with (object and array) brackets.
                if (string.startsWith("}")) {
                    if (string.equals("},")) {
                        string = "}";

                    } else if (string.equals("},{")) {
                        string = "}";
                        parsedList.add(string);
                        string = "{";

                    } else if (string.equals("}],")) {
                        string = "}]";
                    }
                } else if (string.equals(",{")) {
                    string = "{";

                } else if (string.startsWith("],")) {
                    string = "]";
                }

                boolean debug = true;
                // combines brackets if possible.
                // [?] Necessary? [?]
                if (parsedList.size() != 0 & debug) {
                    if (parsedList.get(parsedList.size() - 1).equals("[") & string.equals("{")) {
                        parsedList.remove(parsedList.size() - 1);
                        string = "[{";

                    } else if (parsedList.get(parsedList.size() - 1).equals("}") & string.equals("]")) {
                        parsedList.remove(parsedList.size() - 1);
                        string = "}]";

                    } else if (parsedList.get(parsedList.size() - 1).equals("}") & string.equals("{")) {
                        parsedList.remove(parsedList.size() - 1);
                        string = "}{";

                    }
                }

                if (!string.equals("")) {
                    parsedList.add(string);
                }
            }
        }
        return parsedList;
    }

    public ArrayList<String> getURLList(ArrayList<String> jsonContents) {
        urls = new ArrayList<String>();
        tempArrayList = parseJSON(jsonContents);
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