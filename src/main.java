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

        while (true) {

            System.out.println("Silakan pilih:");
            System.out.println("1. Mulai permainan");
            System.out.println("2. Keluar");

            System.out.print("Pilihan: ");
            String userChoice = scanner.nextLine();

            if (userChoice.equals("2")) {
                break;
            }
            else if (!userChoice.equals("1")) {
                System.out.println("Pilihan tidak valid. Silakan coba lagi.");
                continue;
            }

            // Input file
            File filePath = IOHandler.inputFile();
            GameConfig gameConfig = new GameConfig(0, 0, 0,"");
            // System.out.println("Membaca file input...");
            IOHandler.readInput(filePath, gameConfig);

            // Utils.printPieces(gameConfig.getPieces()); // Debug

            boolean isFileInputValid = gameConfig.isInputValid();
            if (!isFileInputValid) {
                System.out.println("Terdapat kesalahan dalam file input Anda. Silakan coba lagi.");
                continue;
            }
            
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
                    String fileName = IOHandler.getFileName();
                    IOHandler.writeOutputFile(fileName, gameConfig, runTime, numCases[0]);
                    if (isSolved) {
                        boolean validInput = false;
                        System.out.print("Apakah ingin menyimpan solusi dalam bentuk gambar juga? (y/n): ");
                        while (!validInput) {
                            String imageChoice = scanner.nextLine();
                            if (imageChoice.equals("y") || imageChoice.equals("Y")) {
                                IOHandler.outputAsImage(boardPuzzle, 100, fileName);
                                validInput = true;
                            } else if (imageChoice.equals("n") || imageChoice.equals("N")) {
                                validInput = true;
                            } else {
                                System.out.println("Masukkan tidak valid. Silakan coba lagi.");
                                System.out.print("Apakah ingin menyimpan solusi dalam bentuk gambar juga? (y/n): ");
                            }
                        }
                    }
                    validChoice = true;
                } else if (choice.equals("n") || choice.equals("N")) {
                    validChoice = true;
                } else {
                    System.out.println("Masukkan tidak valid. Silakan coba lagi.");
                    System.out.print("Apakah Anda ingin menyimpan solusi? (y/n): ");
                    choice = scanner.nextLine();
                }
            }
        }
        System.out.println("Terima kasih sudah bermain!");
    }
}