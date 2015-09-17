
package matriisilaskin;
import matriisilaskin.logic.*;

public class Matriisilaskin {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int[][] matrix = {{-3,-6,3},{-33,3,5},{3,2,53}};
        Matrix m = new Matrix(matrix);
        LUPDecomposition LU = new LUPDecomposition(m);
        Fraction[][] L = LU.getLU();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3 ; j++) {
                System.out.print(L[i][j] + " ");
            }
            System.out.println();
        }
        
        int[] P = LU.getP();
        Fraction[][] inverse = LU.inverse();
        
         
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    System.out.print(inverse[i][j] + " ");
                }
                System.out.println();
            }
            
            System.out.println(LU.signP());
            System.out.println(LU.determinant());
            
       
        
    }
    
}
