package Game;
import java.util.*;

public class Piece {
    List<int[]> cells;
    char pieceName;
    int rotationTimes;

    public Piece(List<int[]> cells, char name) {
        this.cells = cells;
        this.pieceName = name;
        this.rotationTimes = 0;
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
    public int getRotationTimes() {
        return this.rotationTimes;
    }

    // Method untuk set atribut piece
    public void setCells(List<int[]> cells) {
        this.cells = cells;
    }

    // Method untuk mendapatkan koordinat maksimum dan minimum piece
    public int getMaxRow() {
        int maxRow = Integer.MIN_VALUE;
        for (int[] coordinate : cells) {
            maxRow = Math.max(maxRow, coordinate[0]);
        }
        return maxRow;
    }
    public int getMinRow() {
        int minRow = Integer.MAX_VALUE;
        for (int[] coordinate : cells) {
            minRow = Math.min(minRow, coordinate[0]);
        }
        return minRow;
    }
    public int getMaxCol() {
        int maxCol = Integer.MIN_VALUE;
        for (int[] coordinate : cells) {
            maxCol = Math.max(maxCol, coordinate[1]);
        }
        return maxCol;
    }
    public int getMinCol() {
        int minCol = Integer.MAX_VALUE;
        for (int[] coordinate : cells) {
            minCol = Math.min(minCol, coordinate[1]);
        }
        return minCol;
    }

    // Method untuk sort koordinat piece berdasarkan baris atau kolomnya
    public void sortPieceByX() {
        Collections.sort(cells, (a,b) -> Integer.compare(a[0], b[0]));
    }

    public void sortPieceByY() {
        Collections.sort(cells, (a,b) -> Integer.compare(a[1], b[1]));
    }

    // Method untuk menambahkan koordinat piece sebesar offset tertentu
    public void addOffset(int rowOffset, int colOffset) {
        List<int[]> newCells = new ArrayList<>();
        for (int[] coordinate : cells) {
            newCells.add(new int[]{coordinate[0] + rowOffset, coordinate[1] + colOffset});
        }
        this.cells = newCells;
    }
}
