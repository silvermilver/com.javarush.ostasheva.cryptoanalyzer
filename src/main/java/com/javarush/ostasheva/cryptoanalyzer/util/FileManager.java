package com.javarush.ostasheva.cryptoanalyzer.util;

import com.javarush.ostasheva.cryptoanalyzer.util.exception.FileProcessingException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;

public class FileManager {

    public static List<String> readFile(String fileName) {
        try {
            Path filePath = Path.of(fileName);
            return Files.readAllLines(filePath);
        } catch (IOException | InvalidPathException e) {
            throw new FileProcessingException("Ошибка чтения файла");
        }
    }

    public static void saveFileOnDisk(String fileName, String data) {
        try {
            Path filePath = Path.of(fileName);
            Files.write(filePath, (data + System.lineSeparator()).getBytes(UTF_8), StandardOpenOption.APPEND, StandardOpenOption.CREATE);
        } catch (IOException e) {
            throw new FileProcessingException("Ошибка записи файла");
        }
    }
}
