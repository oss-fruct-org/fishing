package org.fruct.oss.kareliafishing;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Hashtable;

/**
 *
 * @author Nikita Davydovskii
 * date: 24.07.2013
 * This class used for loading string according to locale of the device.
 */
public class Localization {
    
    private Hashtable strings;
    private String locale;
    /** The character used for separating the key from the value. */
    private static final char SEPARATOR_CHAR = '|';
    private static final String DEFAULT_LOCALE = "/strings-en.txt";
    
    public static final String ENGLISH_LOCALE = "en";
    public static final String RUSSIAN_LOCALE = "ru-RU";
    
    /**
     * Constructor.
     */
    public Localization() {
        // Obtain the system locale
        locale = System.getProperty("microedition.locale");
        //locale = "ru-RU";
        System.out.println("Locale: " + locale);
        String filename = "/strings-" + locale + ".txt";
        try {
            loadStrings(filename);
        } catch (IOException ex) {
            // TODO: Exception handling
            System.out.println("IO exp localization");
        } catch (Exception ex) {
            System.out.println("Exp localization");
        }
    }
 
    /**
     * Stores the strings from the text file into a hash table as key-value
     * pairs.
     * @param filename
     * @throws java.lang.Exception
     */
    private void loadStrings(final String filename) throws Exception {
        strings = new Hashtable(50);
        InputStreamReader reader = new InputStreamReader(
            getClass().getResourceAsStream(filename), "UTF-8");
            //getClass().getResourceAsStream(filename), "windows-1251");    
        String line = null;
        // Read a single line from the file. null represents the EOF.
        while ((line = readLine(reader)) != null) {
            // Empty lines are ignored
            if (line.trim().equals("")) {
                continue;
            }
            // Separate the key from the value and put the strings into the
            // hash table
            int separatorPos = line.indexOf(SEPARATOR_CHAR);
            if (separatorPos == -1) {
                throw new Exception("Separator character not found.");
            }
            String key = line.substring(0, separatorPos);
            String value = line.substring(separatorPos + 1);
            strings.put(key, value);
        }
        reader.close();
    }
 
    /**
     * Reads a single line using the specified reader.
     * @throws java.io.IOException if an exception occurs when reading the line
     */
    private String readLine(InputStreamReader reader) throws IOException {
        // Test whether the end of file has been reached. If so, return null.
        int readChar = reader.read();
        if (readChar == -1) {
            return null;
        }
        StringBuffer string = new StringBuffer("");
        // Read until end of file or new line
        while (readChar != -1 && readChar != '\n') {
            // Append the read character to the string. Some operating systems
            // such as Microsoft Windows prepend newline character ('\n') with
            // carriage return ('\r'). This is part of the newline character and
            // therefore an exception that should not be appended to the string.
            if (readChar != '\r') {
                string.append((char)readChar);
            }
            // Read the next character
            readChar = reader.read();
        }
        return string.toString();
    }
    
    /**
     * Localizes a string.
     * @param key the key used for identifying a specific value in the text
     *      file
     * @param def the default value in case "key" is not found from the text
     *      file
     * @return the message localized; "def" if the key is not found
     */
    public String localize(String key, String def) {
        // Obtain the localized string from
        String value = (String)strings.get(key);
        if (value == null) {
            value = def;
        }
        return value;
    }

    public String getLocale() {
        return locale;
    }  
}
