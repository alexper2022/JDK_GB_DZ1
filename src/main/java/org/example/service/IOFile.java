package org.example.service;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class IOFile {

    public static String fileRead(String path) {
        try {
            List<String> file = Files.readAllLines(Path.of(path));

            StringBuilder stringBuilder = new StringBuilder();
            for (String str : file) {
                stringBuilder.append(str + "\n");
            }
            return stringBuilder.toString();
        } catch (FileNotFoundException e) {
            return "Нет сообщений.\n";
        } catch (IOException e) {
            return "Нет сообщений.\n";
        }
    }

    public static boolean fileWrite(String path, String writeString) {
        try (FileWriter fw = new FileWriter(path, true)) {
            fw.write(writeString);
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
