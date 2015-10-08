package matriisilaskin.ui;
import java.util.Scanner;
import matriisilaskin.logic.*;

public class CalculatorUI {
    
    public static Scanner scanner;
    
    public static void run() {
        scanner = new Scanner(System.in);
        Matrix matrix1;
        Matrix matrix2;
        LUPDecomposition LUP;
        
        while(true) {
        
        System.out.println("Valitse laskutoimitus");
        System.out.println("1 - yhteenlasku 2 - vähennyslasku 3 - skalaarikertolasku");
        System.out.println("4 - matriisikertolasku 5 - determinantti 6 - käänteismatriisi");
        
        String input = scanner.nextLine();
        
        switch (input) {
            case "1":
                matrix1 = readMatrix();
                if (matrix1 != null) {
                matrix2 = readMatrix();
                if (matrix2 != null) {
                    Matrix result = matrix1.add(matrix2);
                    if (result != null) {
                        System.out.println("Yhteenlaskun tulos on:");
                        printIntMatrix(result);
                    }
                    else System.out.println("Matriisien täytyy olla samankokoisia!");
                } else System.out.println("Virheellinen syöte!");
               }
                else System.out.println("Virheellinen syöte!");
                break;
            case "2":
                matrix1 = readMatrix();
                if (matrix1 != null) {
                matrix2 = readMatrix();
                if (matrix2 != null) {
                    Matrix result = matrix1.sub(matrix2);
                    if (result != null) {
                        System.out.println("Vähennyslaskun tulos on:");
                        printIntMatrix(result);
                    }
                    else System.out.println("Matriisien täytyy olla samankokoisia!");
                } else System.out.println("Virheellinen syöte!");
               }
               else System.out.println("Virheellinen syöte!");
                break;
            case "3":
                System.out.println("Anna skalaari:");
                int c = Integer.parseInt(scanner.nextLine());
                matrix1 = readMatrix();
                if (matrix1 != null) {
                    Matrix result = matrix1.scalarMul(c);
                    if (result != null) {
                        System.out.println("Skalaarikertolaskun tulos on:");
                        printIntMatrix(result);
                    }
                    else System.out.println("Virheellinen syöte!");
                }
                else System.out.println("Virheellinen syöte!");
                break;
                
            case "4":
                matrix1 = readMatrix();
                if (matrix1 != null) {
                matrix2 = readMatrix();
                if (matrix2 != null) {
                    Matrix result = matrix1.mul(matrix2);
                    if (result != null) {
                        System.out.println("Kertolaskun tulos on:");
                        printIntMatrix(result);
                    }
                    else System.out.println("Ensimmäisen matriisin sarakkeiden määrä pitää olla sama kuin toisen matriisin rivien määrä!");
                } else System.out.println("Virheellinen syöte!");
               }
                else System.out.println("Virheellinen syöte!");
                break;
                
            case "5":
                matrix1 = readMatrix();
                if (matrix1 != null) {
                if (matrix1.getRows() != matrix1.getColumns()) System.out.println("Determinantti voidaan laskea vain neliömatriiseille!");
                LUP = new LUPDecomposition(matrix1);
                System.out.println("Matriisin determinantti on " + LUP.determinant());
                }
                else System.out.println("Virheellinen syöte!");
                break;
                
            case "6":
                matrix1 = readMatrix();
                if (matrix1 != null) {
                    if (matrix1.getRows() != matrix1.getColumns()) System.out.println("Käänteismatriisi voidaan laskea vain neliömatriiseille!");
                LUP = new LUPDecomposition(matrix1);
                Fraction[][] inverse = LUP.inverse();
                if (inverse != null) {
                    System.out.println("Käänteismatriisi on:");
                    for (int i = 0; i < inverse.length; i++) {
                        for (int j = 0; j < inverse[0].length; j++) {
                            System.out.print(inverse[i][j] + " ");
                        }
                        System.out.println();
                    }
                }
                else System.out.println("Matriisi on singulaarinen!");
              }
              break;
            
            
            default: System.out.println("Virheellinen syöte!");
                
                
                
        }
        
        System.out.println("Jatketaanko laskemista? (k/e)");
                String input2 = scanner.nextLine();
                if (!input2.equals("k") && !input2.equals("K")) break;
        }
    }
    
    public static Matrix readMatrix() {
        
        try { 
            System.out.println("Anna matriisin rivien lukumäärä:");
            int r = Integer.parseInt(scanner.nextLine());
            System.out.println("Anna matriisin sarakkeiden lukumäärä:");
            int c = Integer.parseInt(scanner.nextLine());
            
            int[][] matrix = new int[r][c];
            
            for (int i = 0; i < r; i++) {
                System.out.println("Anna matriisin " + (i+1) + " rivi.");
                String input = scanner.nextLine();
                String[] numbers = input.split("[ ]+");
                for (int j = 0; j < c; j++) {
                    matrix[i][j] = Integer.parseInt(numbers[j]);
                }
            }
            
            return new Matrix(matrix);
                    
                } catch (Exception e) {
                    return null;
                }
        
    }
    
    public static void printIntMatrix(Matrix m) {
        int r = m.getRows();
        int c = m.getColumns();
        int[][] matrix = m.getMatrix();
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
    
    
}
