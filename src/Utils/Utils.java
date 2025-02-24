package Utils;
import Game.*;
import java.util.*;
import java.awt.Color;

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

    // Method untuk menempatkan piece pada posisi (idxCol, idxRow)
    public static void placePiece(char[][] board, List<int[]> pieceCells, int idxRow, int idxCol, char pieceName) {
        for (int[] coordinate : pieceCells) {
            int row = idxRow + coordinate[0];
            int col = idxCol + coordinate[1];
            board[row][col] = pieceName;
        }
    }

    // Method untuk menghapuskan piece dari posisi (idxCol, idxRow)
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
        letterColorMap.put('F', "\033[38;5;046m");
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

    // Map berisi pasangan huruf dengan warna untuk image
    public static final Map<Character, Color> imageColorMap = new HashMap<>();
    static {
        imageColorMap.put('A', new Color(255, 0, 0));
        imageColorMap.put('B', new Color(255, 102, 0));
        imageColorMap.put('C', new Color(255, 204, 0));
        imageColorMap.put('D', new Color(255, 255, 0));
        imageColorMap.put('E', new Color(0, 255, 102));
        imageColorMap.put('F', new Color(102, 255, 204));
        imageColorMap.put('G', new Color(0, 255, 0));
        imageColorMap.put('H', new Color(0, 204, 204));
        imageColorMap.put('I', new Color(0, 0, 255));
        imageColorMap.put('J', new Color(0, 0, 128));
        imageColorMap.put('K', new Color(0, 128, 128));
        imageColorMap.put('L', new Color(255, 102, 204));
        imageColorMap.put('M', new Color(255, 51, 153));
        imageColorMap.put('N', new Color(196, 0, 0));
        imageColorMap.put('O', new Color(202, 0, 0));
        imageColorMap.put('P', new Color(214, 0, 0));
        imageColorMap.put('Q', new Color(226, 0, 0));
        imageColorMap.put('R', new Color(154, 0, 0));
        imageColorMap.put('S', new Color(82, 0, 0));
        imageColorMap.put('T', new Color(50, 0, 0));
        imageColorMap.put('U', new Color(39, 0, 0));
        imageColorMap.put('V', new Color(27, 0, 0));
        imageColorMap.put('W', new Color(21, 0, 0));
        imageColorMap.put('X', new Color(57, 0, 0));
        imageColorMap.put('Y', new Color(129, 0, 0));
        imageColorMap.put('Z', new Color(201, 0, 0));
    }

    public static boolean isBoardFull(char[][] board) {
        for (int idxRow = 0; idxRow < board.length; idxRow++) {
            for (int idxCol = 0; idxCol < board[0].length; idxCol++) {
                if (board[idxRow][idxCol] == '.') return false;
            }
        }
        return true;
    }


    // Debug method untuk print pieces
    public static void printPieces(List<Piece> pieces) {
        for (Piece piece : pieces) {
            System.out.println("Piece: " + piece.getPieceName());
            for (int[] cell : piece.getCells()) {
                System.out.println(cell[0] + " " + cell[1]);
            }
        }
    }

    
    
}
