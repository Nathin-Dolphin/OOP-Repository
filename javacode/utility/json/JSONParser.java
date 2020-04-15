
/**
 * @author Nathin Wascher
 * @version 1.5
 * @since March 27, 2020
 */

// [!] parseJSON(AR<S>) does account for values without quotation marks (ex. "number": 12,) [!]

package utility.json;

import java.util.ArrayList;

public class JSONParser {
    private ArrayList<String> parsedList;
    private String[] parsedLine;
    private String string;

    public JSONParser() {
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
                sortJSONComponents(i);

                // [!] Potential problem [!]
                if (!string.equals("")) {
                    parsedList.add(string);
                }
            }
        }
        return parsedList;
    }

    private void sortJSONComponents(int i) {
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
                string = "}{";

            } else if (string.equals("}],")) {
                string = "}]";
            }
        } else if (string.equals(",{")) {
            string = "{";

        } else if (string.startsWith("],")) {
            string = "]";
        }

        // combines brackets if possible.
        if (parsedList.size() != 0) {
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
    }
}