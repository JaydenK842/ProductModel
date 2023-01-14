import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import static java.nio.file.StandardOpenOption.CREATE;

public class ProductWriter {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] fields;
        ArrayList<String> items = new ArrayList<>();
        boolean over = false;
        String newInfo, fileName;


        while (!over) {
            newInfo = SafeInput.getNonZeroLenString(sc, "Input one item at a time (E to exit): ");

            if (newInfo.equalsIgnoreCase("e")) {
                over = true;
            } else {
                items.add(newInfo);
            }
        }

        fileName = SafeInput.getNonZeroLenString(sc, "What would you like the file to be named?: ");

        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath() + "\\src\\" + fileName + ".txt");

        try {
            OutputStream out =
                    new BufferedOutputStream(Files.newOutputStream(file, CREATE));
            BufferedWriter writer =
                    new BufferedWriter(new OutputStreamWriter(out));

            for(String rec : items) {
                fields = rec.split(",");

                if (fields.length == 4) {
                    writer.write(rec, 0, rec.length());
                    writer.newLine();
                }

            }

            writer.close();
            System.out.println("Your file has been wrote!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
