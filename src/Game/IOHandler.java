package Game;
import java.io.*;
import java.util.*;

public class IOHandler {
    
    // Input file handler
    public static File inputFile() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Masukkan nama file input: "); // Input cukup nama saja (tidak perlu path)
            String fileName = scanner.nextLine(); 

            File filePath = new File("test/" + fileName); 

            if (!filePath.exists()) { // Validasi file
                System.out.println("File tidak ditemukan.");
                return null;
            }

            return filePath;
        }
    }

    // Baca input file
    public static void readInput(File filePath, BoardConfig boardConfig) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath));) {
            String line;

            // Baca konfigurasi papan
            line = reader.readLine();
            String[] firstLine = line.split(" ");
            int N = Integer.parseInt(firstLine[0]);
            int M = Integer.parseInt(firstLine[1]);
            int P = Integer.parseInt(firstLine[2]);

            line = reader.readLine();
            String S = line.trim();

            // Baca pieces
            List<Piece> pieces = new ArrayList<>();
            for (int i = 0; i < P; i++) {
                line = reader.readLine();
                String lineChar = line.trim();
                char pieceChar = lineChar.charAt(0);
                char tempChar = pieceChar;
                List<int[]> pieceCells = new ArrayList<>(); // List berisi koordinat sel piece
                int idxRow = 0;

                while (tempChar == pieceChar) {
                    String[] pieceInfo = line.split(" ");
                    for (int idxCol = 0; idxCol < pieceInfo.length; idxCol++) {
                        if (pieceInfo[idxCol].equals("")) continue;
                        else {
                            int[] pieceCell = new int[]{idxRow, idxCol};
                            pieceCells.add(pieceCell);
                        }
                    }
                    idxRow++;
                    line = reader.readLine();
                    String tempLineChar = line.trim();
                    tempChar = tempLineChar.charAt(0);
                }
                pieces.add(new Piece(pieceCells, pieceChar)); // Memasukkan list ke dalam objek Piece
            }
            // Konstruksi objek konfigurasi papan
            boardConfig.setBoardCongig(N, M, P, pieces, S);
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }   
    }
}
