package Game;
import java.util.*;

public class BoardConfig {
    // Atribut
    int boardHeight; // N
    int boardWidth; // M
    int numPieces; // P
    String puzzleType; // S
    List<Piece> pieces;
    char[][] board;

    // Konstruktor konfigurasi papan
    public BoardConfig(int N, int M, int P, List<Piece> piecesList, String S) {
        this.boardHeight = N;
        this.boardWidth = M;
        this.numPieces = P;
        this.puzzleType = S;
        this.pieces = piecesList;
        this.board = new char[N][M];

        // Inisialisasi papan
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                this.board[i][j] = '.';
            }
        }
    }

    // Prosedur konstruktor
    public void setBoardCongig(int N, int M, int P, List<Piece> piecesList, String S) {
        this.boardHeight = N;
        this.boardWidth = M;
        this.numPieces = P;
        this.puzzleType = S;
        this.pieces = piecesList;
        this.board = new char[N][M];

        // Inisialisasi papan
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                this.board[i][j] = '.';
            }
        }
    }

    // Fungsi cek apakah tidak terdapat suatu piece di posisi (i, j)
    public boolean isPosEmpty(int i, int j) {
        return this.board[i][j] == '.';
    }

    // Fungsi-fungsi untuk mengambil atribut objek papan
    public int getBoardHeight() {
        return this.boardHeight;
    }
    public int getBoardWidth() {
        return this.boardWidth;
    }
    public int getNumPieces() {
        return this.numPieces;
    }
    public List<Piece> getPieces() {
        return this.pieces;
    }
    public String getPuzzleType() {
        return this.puzzleType;
    }
    public char[][] getBoard() {
        return this.board;
    }
}
