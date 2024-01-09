package io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class demo {
    public static void main(String[] args) {
        File file = new File("books.txt");
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write("abc");
            bufferedWriter.close();
        } catch (Exception e) {
            System.err.println("loi ghi file");
            throw new RuntimeException(e);
        }


    }
}
