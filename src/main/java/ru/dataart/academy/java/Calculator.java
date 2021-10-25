package ru.dataart.academy.java;

import java.io.*;
import java.util.Enumeration;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class Calculator {
    /**
     * @param zipFilePath -  path to zip archive with text files
     * @param character   - character to find
     * @return - how many times character is in files
     */
    public Integer getNumberOfChar(String zipFilePath, char character) throws IOException {
        ZipFile zipFile = null;
        int count = 0;
        try {
            zipFile = new ZipFile(zipFilePath);
            Enumeration<? extends ZipEntry> entries = zipFile.entries();

            while (entries.hasMoreElements()) {
                ZipEntry entry = entries.nextElement();
                InputStream inputStream = zipFile.getInputStream(entry);
                int c;
                while ((c = inputStream.read()) != -1) {
                    if (c == character) {
                        count++;
                    }

                }
            }

        } finally {
            if (zipFile != null) {
                zipFile.close();
            }
        }
        return count;

    }

    /**
     * @param zipFilePath - path to zip archive with text files
     * @return - max length
     */

    public Integer getMaxWordLength(String zipFilePath) throws IOException {
        ZipFile zipFile = null;
        int maxLength = 0;
        try {
            zipFile = new ZipFile(zipFilePath);
            Enumeration<? extends ZipEntry> entries = zipFile.entries();

            while (entries.hasMoreElements()) {
                ZipEntry entry = entries.nextElement();
                InputStream inputStream = zipFile.getInputStream(entry);

                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                Scanner scanner = new Scanner(inputStreamReader);

                scanner.useDelimiter("(\\n|\\s)+");
                String line;

                while (scanner.hasNext()) {
                    line = scanner.next();
                    if (line.length() > maxLength) {
                        maxLength = line.length();
                    }
                }
            }

        } finally {
            if (zipFile != null) {
                zipFile.close();
            }
        }
        return maxLength;

    }
}