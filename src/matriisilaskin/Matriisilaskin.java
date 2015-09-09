
package matriisilaskin;
import matriisilaskin.logic.*;

public class Matriisilaskin {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int[][] matrix = {{4,3},{6,3}};
        Matrix m = new Matrix(matrix);
        LUPDecomposition LU = new LUPDecomposition(m);
        Fraction[][] L = LU.getLU();
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2 ; j++) {
                System.out.print(L[i][j] + " ");
            }
            System.out.println();
        }
        
        int[] P = LU.getP();
        
         
            for (int j = 0; j < 2 ; j++) {
                System.out.print(P[j]+ " ");
            }
            
            System.out.println(LU.signP());
            System.out.println(LU.determinant());
            
       
        
    }
    
}
