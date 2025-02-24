import java.util.*;
import java.io.File;
import Game.*;
import Utils.Utils;

public class Main {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        
        System.out.println("------------------------------------------------------------------------");
        System.out.println("                    IQ Puzzler Pro (Java Edition)                       ");
        System.out.println("------------------------------------------------------------------------");

        // Input file
        File filePath = IOHandler.inputFile();
        GameConfig gameConfig = new GameConfig(0, 0, 0,"");
        // System.out.println("Membaca file input...");
        IOHandler.readInput(filePath, gameConfig);

        // Utils.printPieces(gameConfig.getPieces());
        
        char[][] boardPuzzle = gameConfig.getBoard();
        List<Piece> pieces = gameConfig.getPieces();
        int[] numCases = {0};

        // System.out.println("Menyelesaikan puzzle...");
        long startTime = System.currentTimeMillis();
        boolean isSolved = Solver.puzzleSolver(boardPuzzle, pieces, 0, numCases);
        long endTime = System.currentTimeMillis();

        gameConfig.setBoard(boardPuzzle);
        gameConfig.setStatus(isSolved);

        long runTime = endTime - startTime;

        // Output
        IOHandler.writeOutputTerminal(gameConfig, runTime, numCases[0]);

        // Pilihan menyimpan dalam file
        System.out.print("Apakah Anda ingin menyimpan solusi? (y/n): ");
        String choice = scanner.nextLine();
        boolean validChoice = false;

        while (!validChoice) {
            if (choice.equals("y") || choice.equals("Y")) {

                System.out.print("Masukkan nama file output: "); // Input cukup nama saja (tidak perlu path)
                String fileName = scanner.nextLine();

                IOHandler.writeOutputFile(fileName, gameConfig, runTime, numCases[0]);
                IOHandler.outputAsImage(boardPuzzle, 100, fileName);
                System.out.println("Solusi berhasil disimpan.");
                validChoice = true;
            } else if (choice.equals("n") || choice.equals("N")) {
                System.out.println("Terima kasih telah bermain!");
                validChoice = true;
            } else {
                System.out.println("Masukkan tidak valid. Silakan coba lagi.");
                System.out.print("Apakah Anda ingin menyimpan solusi? (y/n): ");
                choice = scanner.nextLine();
            }
        }
    }
}