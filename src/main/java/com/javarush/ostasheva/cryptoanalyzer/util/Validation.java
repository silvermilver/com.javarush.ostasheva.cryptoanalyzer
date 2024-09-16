package com.javarush.ostasheva.cryptoanalyzer.util;

import com.javarush.ostasheva.cryptoanalyzer.util.exception.ValidationException;

import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.List;

public class Validation {

    private static final List<String> FORBIDDEN_DIRS_FILES = List.of(
            ".bash_history", ".bash_profile", "etc", "proc");

    public static int keyValidation(String keyStr){
        if(keyStr.isBlank()){
            throw new ValidationException("Не указан ключ");
        }
        try {
            return Integer.parseInt(keyStr.trim());
        } catch (NumberFormatException e){
            System.out.println("Неккоректно указан ключ: " + keyStr + ". Пожалуйста, введите число.");
            throw new ValidationException("Неккоректно указан ключ: " + keyStr + ". Пожалуйста, введите число.");
        }
    }

    public static void validateFile(String filename){
        Path path = validatePath(filename);
        if(Files.notExists(path)){
            throw new ValidationException("File does not exist");
        }
        if(Files.isDirectory(path)){
            throw new ValidationException("File is directory");
        }
    }

    private static Path validatePath(String fileName){
        for(String pathPart : fileName.split(System.getProperty("file.separator"))){
            if(FORBIDDEN_DIRS_FILES.contains(pathPart)){
                throw new ValidationException("Path contains forbidden part: " + pathPart);
            }
        }
        try {
            Path path = Path.of(fileName);
            return path;
        } catch (InvalidPathException e){
            throw new ValidationException("Invalid path. Reason: " + e.getMessage(), e);
        }
    }
}
