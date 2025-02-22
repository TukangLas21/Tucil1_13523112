package Utils;
import Game.*;
import java.util.*;

public class Utils {
    
    public static List<List<int[]>> pieceCombinations(Piece piece) {
        List<List<int[]>> combinations = new ArrayList<>(); // List berisi semua kemungkinan rotasi dan pencerminan piece
        
        // Rotasi piece
        for (int i = 0; i < 4; i++) {
            piece.rotatePiece90();
            List<int[]> rotatedPiece = piece.getCells();
            combinations.add(rotatedPiece);
        }

        // Pencerminan piece
        piece.mirrorPiece();

        // Piece dirotasikan kembali
        for (int i = 0; i < 4; i++) {
            piece.rotatePiece90();
            List<int[]> rotatedPiece = piece.getCells();
            combinations.add(rotatedPiece);
        }

        return combinations;
    }

    // Method untuk mengecek apakah piece dapat diletakan 
    public static boolean isPosValid(char[][] board, List<int[]> pieceCells, int idxRow, int idxCol) {
        for (int[] coordinate : pieceCells) {
            int row = idxRow + coordinate[0];
            int col = idxCol + coordinate[1];
            if (row < 0 || row >= board.length || col < 0 || col >= board[0].length || board[row][col] != '.') {
                return false;
            }
        }
        return true;
    }

    // Method untuk menempatkan piece pada posisi (idxRow, idxCol)
    public static void placePiece(char[][] board, List<int[]> pieceCells, int idxRow, int idxCol, char pieceName) {
        for (int[] coordinate : pieceCells) {
            int row = idxRow + coordinate[0];
            int col = idxCol + coordinate[1];
            board[row][col] = pieceName;
        }
    }

    // Method untuk menghapuskan piece dari posisi (idxRow, idxCol)
    public static void removePiece(char[][] board, List<int[]> pieceCells, int idxRow, int idxCol) {
        for (int[] coordinate : pieceCells) {
            int row = idxRow + coordinate[0];
            int col = idxCol + coordinate[1];
            board[row][col] = '.';
        }
    }
    
    
    // public static void InputStart () {
    //     Scanner scanner = new Scanner(System.in);
    //     System.out.println("Enter the path to the text file: ");
    //     String filePath = scanner.nextLine();

    //     try {
    //         BufferedReader reader = new BufferedReader(new FileReader(filePath));

    //         // Read first line -> Board size and number of pieces
    //         String[] firstLine = reader.readLine().split(filePath, 0);
    //         int N = Integer.parseInt(firstLine[0]);
    //         int M = Integer.parseInt(firstLine[1]);
    //         int numPieces = Integer.parseInt(firstLine[2]);

    //         // Read second line -> Game configuration
    //         String gameConfig = reader.readLine().trim();
            
    //         // Read the rest of the lines -> Pieces shapes
    //         char[][][] pieces = new char[numPieces][][];
            
    //         for (int i = 0; i < numPieces; i++) {
    //             List<char[]> currentPiece = new ArrayList<>();
    //             String line;
    //             while ((line = reader.readLine()) != null) {
    //                 String trimmedLine = line.trim();
    //                 if (trimmedLine.charAt(0) == (char)(65+i)) {
    //                     currentPiece.add(line.trim().toCharArray());
    //                 } else {
    //                     break;
    //                 }
    //             }
    //             pieces[i] = currentPiece.toArray(new char[0][]);
    //         }

    //         reader.close();
            
            
    //         // List<char[][]> pieces = new ArrayList<>();
    //         // for (int i = 0; i < numPieces; i++) {
    //         //     List<char[]> currentPiece = new ArrayList<>();
    //         //     String line = reader.readLine().trim();
    //         //     while (line != null) {
                    
    //         //     }
    //         // }


    //     } catch (IOException e) {
    //         System.out.println("Error: " + e.getMessage());
    //     }

    // }
}
