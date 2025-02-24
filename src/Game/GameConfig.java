package Game;
import java.util.*;

public class GameConfig {
    // Atribut
    int boardHeight; // N
    int boardWidth; // M
    int numPieces; // P
    String puzzleType; // S
    List<Piece> pieces;
    char[][] board;
    boolean isSolved;

    // Konstruktor konfigurasi papan
    public GameConfig(int N, int M, int P, String S) {
        this.boardHeight = N;
        this.boardWidth = M;
        this.numPieces = P;
        this.puzzleType = S;
        this.pieces = new ArrayList<>();
        this.board = new char[N][M];
        this.isSolved = false;

        // Inisialisasi papan
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                this.board[i][j] = '.';
            }
        }
    }

    // Prosedur konstruktor
    public void setGameCongig(int N, int M, int P, List<Piece> piecesList, String S) {
        this.boardHeight = N;
        this.boardWidth = M;
        this.numPieces = P;
        this.puzzleType = S;
        this.pieces = piecesList;
        this.board = new char[N][M];
        this.isSolved = false;

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

    // Method untuk mengambil atribut objek papan
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
    public boolean getStatus() {
        return this.isSolved;
    }

    // Method untuk menyimpan nilai atribut baru
    public void setPiecesList(List<Piece> piecesList) {
        this.pieces = piecesList;
    }
    public void setBoard(char[][] board) {
        this.board = board;
    }
    public void setStatus(boolean status) {
        this.isSolved = status;
    }
}
