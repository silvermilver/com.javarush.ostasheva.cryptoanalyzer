package com.javarush.ostasheva.cryptoanalyzer.service;

import com.javarush.ostasheva.cryptoanalyzer.util.FileManager;
import com.javarush.ostasheva.cryptoanalyzer.util.Validation;

import java.io.File;
import java.util.List;

import static com.javarush.ostasheva.cryptoanalyzer.util.Validation.keyValidation;

public class Encryptor {

    //Алфавит 40 символов
    private static final char[] ALPHABET = {'а', 'б', 'в', 'г', 'д', 'е', 'ж', 'з',
            'и','к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ',
            'ъ', 'ы', 'ь', 'э', 'я', '.', ',', '«', '»', '"', '\'', ':', '!', '?', ' '};


    private static int symbolPlaceInAlphabet(char symbol) {
        int place = -1;
        int i = 0;
        for(char c : ALPHABET){
            if(symbol == c){
                place = i;
                break;
            }
            i++;
        }
        return place;
    }

    public static void encryptFile(File file, String keyStr){
        Validation.validateFile(file.getPath());
        int key = keyValidation(keyStr);
        encryptAndSaveNewFile(file.getPath(), key, "encryptedFile.txt");
    }

    public static void decryptFile(File file, String keyStr){
        int key = keyValidation(keyStr);
        encryptAndSaveNewFile(file.getPath(), -key, "decryptedFile.txt");
    }

    private static void encryptAndSaveNewFile(String filePath, int key, String fileNameToSave){
        List<String> listStrings = FileManager.readFile(filePath);
        for(String stringToEncrypt : listStrings) {
            String encryptedStr = encryptString(stringToEncrypt, key);
            FileManager.saveFileOnDisk(fileNameToSave, encryptedStr);
        }
    }

    private static String encryptString(String stringToEncrypt, int key){
        StringBuilder cryptoStr = new StringBuilder();
        int initialSymbolPlace;
        for(char symbol : stringToEncrypt.toCharArray()){
            initialSymbolPlace = symbolPlaceInAlphabet(symbol);
            if (initialSymbolPlace == -1){
                continue;
            }
            int finalSymbolPlace = initialSymbolPlace + key;
            if(finalSymbolPlace > 39){
                finalSymbolPlace = finalSymbolPlace % 40;
            } else if(finalSymbolPlace < 0 && finalSymbolPlace > -41 && finalSymbolPlace != 0){
                finalSymbolPlace = 40 + finalSymbolPlace;
            } else if (finalSymbolPlace <= -41){
                finalSymbolPlace = 40 + finalSymbolPlace % 40;
            }
            int encryptedSymbolPlace = 0;
            for(char charFromAlphabet : ALPHABET){
                if(encryptedSymbolPlace == finalSymbolPlace){
                    cryptoStr.append(charFromAlphabet);
                }
                encryptedSymbolPlace++;
            }
        }
        return cryptoStr.toString();
    }
}
