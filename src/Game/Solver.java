package Game;
import Utils.Utils;
import java.util.*;

public class Solver {
    
    // Fungsi utama untuk menyelesaikan 
    public static boolean puzzleSolver(char[][] board, List<Piece> pieces, int idxPiece, int[] numCases) {
        // Kasus basis jika semua puzzle sudah terisi pada board
        if (idxPiece == pieces.size() && Utils.isBoardFull(board)) return true;
        
        if (idxPiece == pieces.size() && !Utils.isBoardFull(board)) return false; // Semua piece ditaruh tapi papan belum penuh

        if (idxPiece != pieces.size() && Utils.isBoardFull(board)) return false; // Papan sudah penuh padahal piece belum semua ditaruh

        // Kasus rekursif
        Piece currPiece = pieces.get(idxPiece);
        char pieceName = currPiece.getPieceName();
        List<List<int[]>> pieceCombinations = Utils.getPieceCombinations(currPiece);
        
        for (int idxRow = 0; idxRow < board.length; idxRow++) {
            for (int idxCol = 0; idxCol < board[0].length; idxCol++) {
                for (List<int[]> currComb : pieceCombinations) { // Iterasi setiap kemungkinan piece
                    numCases[0] += 1;
                    if (Utils.isPosValid(board, currComb, idxCol, idxRow)) {
                        Utils.placePiece(board, currComb, idxCol, idxRow, pieceName);
                        if (puzzleSolver(board, pieces, idxPiece+1, numCases)) {
                            return true;
                        } else Utils.removePiece(board, currComb, idxCol, idxRow); // Kasus backtrack jika piece berikutnya tidak bisa ditaruh
                    }
                }
            }
        }
        return false;
    }   
}
