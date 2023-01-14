import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import static java.nio.file.StandardOpenOption.CREATE;

public class ProductReader {
    public static void main(String[] args) {
        JFileChooser chooser = new JFileChooser();
        ArrayList<String> items = new ArrayList<>();
        File selectedFile;
        String[] fields;
        String ID, Name, Description, rec;
        double Cost;

        try {
            File workingDirectory= new File(System.getProperty("user.dir"));
            chooser.setCurrentDirectory(workingDirectory);

            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                selectedFile = chooser.getSelectedFile();
                Path file = selectedFile.toPath();

                InputStream in =
                        new BufferedInputStream(Files.newInputStream(file, CREATE));
                BufferedReader reader =
                        new BufferedReader(new InputStreamReader(in));

                while (reader.ready()) {
                    rec = reader.readLine();

                    items.add(rec);
                }
                reader.close();

                System.out.println("ID#     Name        Description              Cost");
                System.out.println("=================================================");

                for (String item : items) {
                    fields = item.split(",");

                    if (fields.length == 4) {
                        ID = fields[0].trim();
                        Name = fields[1].trim();
                        Description = fields[2].trim();
                        Cost = Double.parseDouble(fields[3].trim());


                        System.out.printf("%-8s", ID);
                        System.out.printf("%-12s", Name);
                        System.out.printf("%-25s", Description);
                        System.out.println(Cost);
                    }
                }
            } else {
                System.out.println("Failed to choose a file.");
                System.exit(0);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}