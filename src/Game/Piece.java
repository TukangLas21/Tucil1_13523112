package Game;
import java.util.*;

public class Piece {
    List<int[]> cells;
    char pieceName;

    public Piece(List<int[]> cells, char name) {
        this.cells = cells;
        this.pieceName = name;
    }

    // Method untuk merotasikan piece 90 derajat clockwise
    public void rotatePiece90() {
        List<int[]> rotatedCells = new ArrayList<>();

        // Rotasi setiap koordinat piece
        for (int[] coordinate : cells) {
            // (x,y) -> (-y, x)
            int[] newCoordinate = new int[]{-coordinate[1], coordinate[0]};
            rotatedCells.add(newCoordinate);
        }

        // Cari nilai minimum x dan y 
        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;
        for (int[] coordinate : rotatedCells) {
            minX = Math.min(minX, coordinate[0]);
            minY = Math.min(minY, coordinate[1]);
        }

        // Sesuaikan koordinat agar nilai x dan y minimum menjadi 0
        List<int[]> normalizedCells = new ArrayList<>();
        for (int[] coordinate : rotatedCells) {
            normalizedCells.add(new int[]{coordinate[0] - minX, coordinate[1] - minY});
        }

        this.cells = normalizedCells;
    }
    
    // Method untuk mencerminkan koordinat piece
    public void mirrorPiece() {
        List<int[]> mirroredCells = new ArrayList<>();

        // Mirror setiap koordinat piece
        for (int[] coordinate : cells) {
            // (x,y) -> (-x, y)
            int[] mirroredCoordinate = new int[]{-coordinate[0], coordinate[1]};
            mirroredCells.add(mirroredCoordinate);
        }

        // Cari nilai minimum row sebagai offset
        int minRow = Integer.MAX_VALUE;
        for (int[] coordinate : mirroredCells) {
            minRow = Math.min(minRow, coordinate[0]);
        }

        // Sesuaikan koordinat agar nilai row minimum menjadi 0
        List<int[]> normalizedMirroredCells = new ArrayList<>();
        for (int[] coordinate : mirroredCells) {
            normalizedMirroredCells.add(new int[]{coordinate[0] - minRow, coordinate[1]});
        }

        this.cells = normalizedMirroredCells;
    }

    // Method untuk mendapatkan atribut piece
    public List<int[]> getCells() {
        return this.cells;
    }
    public char getPieceName() {
        return this.pieceName;
    }

    // Method untuk set atribut piece
    public void setCells(List<int[]> cells) {
        this.cells = cells;
    }
}
