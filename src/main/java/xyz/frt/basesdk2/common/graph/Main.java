package xyz.frt.basesdk2.common.graph;

import java.io.*;

public class Main {

    private void printHead() {
        BufferedReader reader = null;
        File file = new File("head.txt");
        try {
            String line;
            reader = new BufferedReader(new FileReader(file));
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {

    }

}
