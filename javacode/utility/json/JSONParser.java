
/**
 * Copyright (c) 2020 Nathin-Dolphin.
 * 
 * This file is part of the utility library and is under the MIT License.
 */

package utility.json;

import java.util.ArrayList;

/**
 * Parses the information from an {@code .json} file into a
 * {@code ArrayList<String>}. For reading a {@code .json} file from an url,
 * check {@link URLReader}. For locating and reading directly from a
 * {@code .json} file, check {@link JSONReader}.
 * 
 * <p>
 * <b>Planned Features:</b>
 * <p>
 * Implement a {@code toString()} to output the parsed {@code .json} data as a
 * single {@code String}.
 * <p>
 * Implement threading.
 * <p>
 * Have 2 {@code ArrayList<String>}, where one includes brackets and the other
 * does not.
 * 
 * <p>
 * <b>WARNING:</b> A very messy {@code .json} file may not get parsed correctly.
 * 
 * @author Nathin Wascher
 * @version 1.7
 * @since March 27, 2020
 * 
 * @see JSONReader
 * @see URLReader
 */

public class JSONParser {
    private ArrayList<String> parsedArrayList;
    private String[] parsedLine;
    private String tempString, tempStr2;

    /**
     * Contains a list of brackets found in parsed {@code .json} data:
     * <p>
     * <code>[{</code>, <code>[</code>, <code>{</code>, <code>}{</code>,
     * <code>}</code>, <code>]</code>, <code>}]</code>
     */
    public String[] bracketList;

    public JSONParser() {
        String[] brackets = { "[{", "[", "{", "}{", "}", "]", "}]" };
        bracketList = brackets;
    }

    /**
     * Parses data from a {@code .json} file and puts it into a
     * {@code ArrayList<String>}.
     * 
     * @param jsonContents    The raw data from a single {@code .json} file
     * @param includeBrackets If {@code true}, the parsed {@code .json} data should
     *                        keep the brackets else if {@code false}, remove them
     * @return An {@code ArrayList<String>} consisting of the {@code .json} data
     */
    public ArrayList<String> parseJSON(ArrayList<String> jsonContents, boolean includeBrackets) {
        tempStr2 = null;
        parsedArrayList = new ArrayList<String>();

        for (int h = 0; h < jsonContents.size(); h++) {
            parsedLine = jsonContents.get(h).split("\"");

            for (int i = 0; i < parsedLine.length; i++) {
                tempString = parsedLine[i];

                // TODO: Fix so that spaces in names and values do not get deleted
                if (!tempString.equals("")) {
                    tempString = tempString.replaceAll(" ", "").replaceAll("\t", "");
                    if (tempString.equals(""))
                        tempString = null;
                }

                // TODO: Implement print to terminal feature here

                if (i % 2 == 0) {
                    if (tempString != null)
                        sortJSONComponents(i, includeBrackets);
                    if (tempString != null) {
                        parsedArrayList.add(tempString);
                        if (tempStr2 != null) {
                            parsedArrayList.add(tempStr2);
                            tempStr2 = null;
                        }
                    }
                } else
                    parsedArrayList.add(tempString);
            }
        }
        return parsedArrayList;
    }

    // Sorts the json into names, values, and brackets as well as erase the colons
    // and commas between them.
    private void sortJSONComponents(int i, boolean includeBrackets) {
        tempString.replaceAll(" ", "");

        // Removes the colons between the names and their values.
        // Also removes some commas not considered part of a name or value.
        if (tempString.startsWith(":")) {
            if (tempString.contains("[{")) {
                tempString = "[{";

            } else if (tempString.contains("{")) {
                tempString = "{";

            } else if (tempString.contains("[")) {
                tempString = "[";

            } else {
                // Checks if this string contains a numerical (or unquoted) value
                tempString = tempString.replaceAll(":", "").replaceAll(",", "");
                if (!tempString.equals("")) {
                    if (tempString.contains("}]")) {
                        tempString.replaceAll("}]", "");
                        tempStr2 = "}]";

                    } else if (tempString.contains("}")) {
                        tempString.replaceAll("}", "");
                        tempStr2 = "}";

                    } else if (tempString.contains("]")) {
                        tempString.replaceAll("]", "");
                        tempStr2 = "]";

                    } else
                        try {
                            Integer.parseInt(tempString);
                        } catch (NumberFormatException e) {
                            tempString = null;
                        }
                } else
                    tempString = null;
            }
        } else if (tempString.equals(",") || tempString.equals(", ")) {
            tempString = null;
        }

        if (tempString != null) {
            // Simplifies strings containing brackets.
            if (tempString.contains("}")) {
                if (tempString.contains("},{")) {
                    tempString = "}{";

                } else if (tempString.contains("},")) {
                    tempString = "}";

                } else if (tempString.contains("}],")) {
                    tempString = "}]";
                }
            } else if (tempString.contains(",{")) {
                tempString = "{";

            } else if (tempString.startsWith("],")) {
                tempString = "]";
            }

            if (includeBrackets) {
                // combines brackets if possible.
                int maxIndex = parsedArrayList.size() - 1;
                if (maxIndex > 1) {
                    if (parsedArrayList.get(maxIndex).equals("[") & tempString.equals("{")) {
                        parsedArrayList.remove(maxIndex);
                        tempString = "[{";

                    } else if (parsedArrayList.get(maxIndex).equals("}") & tempString.equals("]")) {
                        parsedArrayList.remove(maxIndex);
                        tempString = "}]";

                    } else if (parsedArrayList.get(maxIndex).equals("}") & tempString.equals("{")) {
                        parsedArrayList.remove(maxIndex);
                        tempString = "}{";
                    }
                }
            } else {
                tempStr2 = null;
                for (String s : bracketList) {
                    if (tempString.equals(s)) {
                        tempString = null;
                    }
                }
            }
        }
    }
}