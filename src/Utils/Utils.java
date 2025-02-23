package Utils;
import Game.*;
import java.util.*;

public class Utils {
    
    // Method untuk mencatat semua kemungkinan rotasi dan pencerminan piece
    public static List<List<int[]>> getPieceCombinations(Piece piece) {
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

    // Map berisi pasangan huruf dengan warna
    public static final Map<Character, String> letterColorMap = new HashMap<>();
    static {
        letterColorMap.put('A', "\033[38;5;196m");
        letterColorMap.put('B', "\033[38;5;202m");
        letterColorMap.put('C', "\033[38;5;214m");
        letterColorMap.put('D', "\033[38;5;226m");
        letterColorMap.put('E', "\033[38;5;154m");
        letterColorMap.put('F', "\033[38;5;082m");
        letterColorMap.put('G', "\033[38;5;050m");
        letterColorMap.put('H', "\033[38;5;039m");
        letterColorMap.put('I', "\033[38;5;027m");
        letterColorMap.put('J', "\033[38;5;021m");
        letterColorMap.put('K', "\033[38;5;057m");
        letterColorMap.put('L', "\033[38;5;129m");
        letterColorMap.put('M', "\033[38;5;201m");
        letterColorMap.put('N', "\033[3m\033[38;5;196m");
        letterColorMap.put('O', "\033[3m\033[38;5;202m");
        letterColorMap.put('P', "\033[3m\033[38;5;214m");
        letterColorMap.put('Q', "\033[3m\033[38;5;226m");
        letterColorMap.put('R', "\033[3m\033[38;5;154m");
        letterColorMap.put('S', "\033[3m\033[38;5;082m");
        letterColorMap.put('T', "\033[3m\033[38;5;050m");
        letterColorMap.put('U', "\033[3m\033[38;5;039m");
        letterColorMap.put('V', "\033[3m\033[38;5;027m");
        letterColorMap.put('W', "\033[3m\033[38;5;021m");
        letterColorMap.put('X', "\033[3m\033[38;5;057m");
        letterColorMap.put('Y', "\033[3m\033[38;5;129m");
        letterColorMap.put('Z', "\033[3m\033[38;5;201m");
    }

}
