package Utils;
import java.io.*;
import java.util.*;

public class Utils {
    public static void InputStart () {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the path to the text file: ");
        String filePath = scanner.nextLine();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));

            // Read first line -> Board size and number of pieces
            String[] firstLine = reader.readLine().split(filePath, 0);
            int N = Integer.parseInt(firstLine[0]);
            int M = Integer.parseInt(firstLine[1]);
            int numPieces = Integer.parseInt(firstLine[2]);

            // Read second line -> Game configuration
            String gameConfig = reader.readLine().trim();
            
            // Read the rest of the lines -> Pieces shapes
            char[][][] pieces = new char[numPieces][][];
            
            for (int i = 0; i < numPieces; i++) {
                List<char[]> currentPiece = new ArrayList<>();
                String line;
                while ((line = reader.readLine()) != null) {
                    String trimmedLine = line.trim();
                    if (trimmedLine.charAt(0) == (char)(65+i)) {
                        currentPiece.add(line.trim().toCharArray());
                    } else {
                        break;
                    }
                }
                pieces[i] = currentPiece.toArray(new char[0][]);
            }

            reader.close();
            
            
            // List<char[][]> pieces = new ArrayList<>();
            // for (int i = 0; i < numPieces; i++) {
            //     List<char[]> currentPiece = new ArrayList<>();
            //     String line = reader.readLine().trim();
            //     while (line != null) {
                    
            //     }
            // }


        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }
}
