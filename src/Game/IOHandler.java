package Game;
import Utils.Utils;
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
    public static void readInput(File filePath, GameConfig gameConfig) {
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
                        if (pieceInfo[idxCol].equals("")); // Skip (do nothing)
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
            gameConfig.setGameCongig(N, M, P, pieces, S);
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }   
    }

    // Output hasil ke dalam file
    public static void writeOutputFile(File filePath, char[][] board, int runTime, int numCases, boolean statusSolved) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));) {
            if (statusSolved) {
                for (int idxRow = 0; idxRow < board.length; idxRow++) {
                    for (int idxCol = 0; idxCol < board[0].length; idxCol++) {
                        char letter = board[idxRow][idxCol];
                        String letterColor = Utils.letterColorMap.get(letter);
                        // Validasi warna pada map
                        if (letterColor != null) {
                            writer.write(letterColor + letter);
                        } else writer.write("\033[0m" + letter); // Jika for some reason tidak valid, di write dengan warna default
                    }
                    writer.newLine();
                }
            } else writer.write("Tidak ada solusi yang memenuhi.");

            writer.newLine();

            writer.write("Waktu pencarian: " + runTime + " ms");

            writer.newLine();

            writer.write("Banyak kasus yang ditinjau: " + numCases + " kasus");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Output hasil ke dalam terminal
    public static void writeOutputTerminal(GameConfig gameConfig, int runTime, int numCases) {
        char[][] board = gameConfig.getBoard();
        boolean statusSolved = gameConfig.getStatus();

        if (statusSolved) {
            for (int idxRow = 0; idxRow < board.length; idxRow++) {
                for (int idxCol = 0; idxCol < board[0].length; idxCol++) {
                    char letter = board[idxRow][idxCol];
                    String letterColor = Utils.letterColorMap.get(letter);
                        // Validasi warna pada map
                        if (letterColor != null) {
                            System.out.print(letterColor + letter);
                        } else System.out.print("\033[0m" + letter); // Jika for some reason tidak valid, di write dengan warna default
                }
                System.out.println();
            }
        } else {
            System.out.println("Tidak ada solusi yang memenuhi.");
        }

        System.out.println("Waktu pencarian: " + runTime + " ms");

        System.out.println("Banyak kasus yang ditinjau: " + numCases + " kasus");
    }

    // Fungsi untuk mendapatkan validasi untuk menyimpan output ke dalam file
    
}
