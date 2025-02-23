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

}
