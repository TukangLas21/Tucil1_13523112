package Game;
import Utils.Utils;
import java.io.*;
import java.util.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.Color;

public class IOHandler {

    private static Scanner scanner = new Scanner(System.in);

    // Input file handler
    public static File inputFile() {
        
        System.out.print("Masukkan nama file input: "); // Input cukup nama saja (tidak perlu path)
        String fileName = scanner.nextLine(); 

        File filePath = new File("../test/" + fileName); 

        while (!filePath.exists() || fileName.equals("")) {
            System.out.println("File tidak ditemukan. Silakan coba lagi.");
            System.out.print("Masukkan nama file input: ");
            fileName = scanner.nextLine();
            filePath = new File("../test/" + fileName);
        }

        // Debug input
        // System.out.println("Sukses!");

        return filePath;
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

            line = reader.readLine();

            // Baca pieces
            List<Piece> pieces = new ArrayList<>();
            for (int i = 0; i < P; i++) {
                if (line == null) break; 

                String lineChar = line.trim();
                char pieceChar = lineChar.charAt(0);
                char tempChar = pieceChar;
                List<int[]> pieceCells = new ArrayList<>(); // List berisi koordinat sel piece
                int idxRow = 0;

                while (tempChar == pieceChar) {
                    String[] pieceInfo = line.split("");
                    for (int idxCol = 0; idxCol < pieceInfo.length; idxCol++) {
                        if (pieceInfo[idxCol].equals("") || pieceInfo[idxCol].equals(" ")) continue; // Skip (do nothing)
                        else {
                            int[] pieceCell = new int[]{idxCol, idxRow};
                            pieceCells.add(pieceCell);
                        }
                    }
                    idxRow++;
                    line = reader.readLine();
                    if (line == null) break;
                    String tempLineChar = line.trim();
                    tempChar = tempLineChar.charAt(0);
                }
                pieces.add(new Piece(pieceCells, pieceChar)); // Memasukkan list ke dalam objek Piece
                if (line == null) break;
            }
            // Konstruksi objek konfigurasi papan
            gameConfig.setGameConfig(N, M, P, pieces, S);
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }   
    }

    // Output hasil ke dalam file
    public static void writeOutputFile(String fileName, GameConfig gameConfig, long runTime, int numCases) {
        char[][] board = gameConfig.getBoard();
        boolean statusSolved = gameConfig.getStatus();

        File filePath = new File("../test/results/", fileName + ".txt");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));) {
            if (statusSolved) {
                for (int idxRow = 0; idxRow < board.length; idxRow++) {
                    for (int idxCol = 0; idxCol < board[0].length; idxCol++) {
                        char letter = board[idxRow][idxCol];
                        writer.write(letter);
                    }
                    writer.newLine();
                }
            } else writer.write("Tidak ada solusi yang memenuhi.");

            writer.newLine();

            writer.write("Waktu pencarian: " + runTime + " ms");

            writer.newLine();

            writer.write("Banyak kasus yang ditinjau: " + numCases + " kasus");

            System.out.println("Solusi berhasil disimpan.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Output hasil ke dalam terminal
    public static void writeOutputTerminal(GameConfig gameConfig, long runTime, int numCases) {
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
            System.out.println("\033[0mTidak ada solusi yang memenuhi.");
        }

        System.out.println();

        System.out.println("\033[0mWaktu pencarian: " + runTime + " ms"); // Reset warna

        System.out.println();

        System.out.println("Banyak kasus yang ditinjau: " + numCases + " kasus");

        System.out.println();
    }

    // Method menyimpan sebagai gambar
    public static void outputAsImage(char[][] board, int squareSize, String fileName) {
        int imageHeight = board.length * squareSize;
        int imageWidth = board[0].length * squareSize;

        BufferedImage image = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = image.createGraphics();

        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, imageWidth, imageHeight);

        // Isi dengan kotak-kotak piece
        for (int idxRow = 0; idxRow < board.length; idxRow++) {
            for (int idxCol = 0; idxCol < board[0].length; idxCol++) {
                char letter = board[idxRow][idxCol];
                Color letterColor = Utils.imageColorMap.get(letter);

                graphics.setColor(letterColor);

                int x = idxCol * squareSize;
                int y = idxRow * squareSize;
                graphics.fillRect(x, y, squareSize, squareSize);
            }
        }

        graphics.dispose();

        File outputImage = new File("../test/results/", fileName + ".png");

        try {
            ImageIO.write(image, "PNG", outputImage);
            System.out.println("Gambar berhasil disimpan.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method untuk mendapatkan nama file output sekaligus memvalidasikan input pengguna
    public static String getFileName() {

        System.out.print("Masukkan nama file (tanpa ekstensi): ");
        String fileName = scanner.nextLine();
        File filePath = new File("../test/results/" + fileName + ".txt");

        // Cek apakah sudah ada file dengan nama yang sama
        while (filePath.exists() || fileName.equals("")) {
            System.out.println("File sudah ada atau masukan tidak valid. Silakan coba lagi.");
            System.out.print("Masukkan nama file (tanpa ekstensi): ");
            fileName = scanner.nextLine();
            filePath = new File("../test/results/" + fileName + ".txt");
        }
        
        return fileName;
    }

}
