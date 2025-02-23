package Game;
import Utils.Utils;
import java.util.*;

public class Solver {
    
    // Fungsi utama untuk menyelesaikan 
    public static boolean puzzleSolver(char[][] board, List<Piece> pieces, int idxPiece, int numCases) {
        // Kasus basis jika semua puzzle sudah terisi pada board
        if (idxPiece == pieces.size()) return true;

        // Kasus rekursif
        Piece currPiece = pieces.get(idxPiece);
        char pieceName = currPiece.getPieceName();
        List<List<int[]>> pieceCombinations = Utils.getPieceCombinations(currPiece);
        
        for (int idxRow = 0; idxRow < board.length; idxRow++) {
            for (int idxCol = 0; idxCol < board[0].length; idxCol++) {
                for (List<int[]> currComb : pieceCombinations) { // Iterasi setiap kemungkinan piece
                    if (Utils.isPosValid(board, currComb, idxRow, idxCol)) {
                        Utils.placePiece(board, currComb, idxRow, idxCol, pieceName);
                        numCases++;
                        if (puzzleSolver(board, pieces, idxPiece+1, numCases)) {
                            return true;
                        } else Utils.removePiece(board, currComb, idxRow, idxCol); // Kasus backtrack jika piece berikutnya tidak bisa ditaruh
                    }
                }
            }
        }
        return false; // Tidak ada solusi yang memenuhi
    }
}
