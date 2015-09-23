
package matriisilaskin.logic;

/**
 * 
 * Matriisin peruslaskutoimitukset sisältävä luokka
 */

public class Matrix {
    /**
     * Matriisin korkeus
     */
    private int rows;
    
    /**
     * Matriisin leveys
     */
    private int columns;
    
    /**
     * Kaksiulotteinen kokonaislukutaulukko, joka sisältää matriisin arvot
     */
    private int[][] matrix;
    
    public Matrix(int[][] matrix) {
        this.matrix = matrix;
        this.rows = matrix.length;
        this.columns = matrix[0].length;
    }
    
    public int getRows() {
        return rows;
    }
    
    public int getColumns() {
        return columns;
    }
    
    public int[][] getMatrix() {
        return matrix;
    }
    
    /**
     * Laskee syötteenä annetun matriisin yhteen tämän matriisin kanssa ja palauttaa uuden Matrix-olion, joka sisältää yhteenlaskun tuloksen. Palauttaa nullin,
     * jos laskutoimitus ei ole mahdollinen, eli kun matriisit ovat erikokoisia.
     * 
     * @param m Yhteenlaskettava matriisi
     * @return Yhteenlaskun tuloksen sisältävä matriisi
     */
    
    public Matrix add(Matrix m) {
        int rows2 = m.getRows();
        int columns2 = m.getColumns();
        if (rows != rows2 || columns != columns2) return null;
        int [][] matrix2 = m.getMatrix();
        int [][] resultMatrix = add(matrix, matrix2);
        return new Matrix(resultMatrix);
    }
    
    private static int[][] add(int[][] A, int[][] B) {
        int rows = A.length;
        int columns = A[0].length;
        int[][] C = new int[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns ; j++) {
                C [i][j] = A[i][j] + B[i][j];
            }
        }
        return C;
    }
    
    /**
     * Vähentää syötteenä annetun matriisin tästä matriisista ja palauttaa uuden Matrix-olion, joka sisältää vähennyslaskun tuloksen. Palauttaa nullin,
     * jos laskutoimitus ei ole mahdollinen, eli kun matriisit ovat erikokoisia.
     * 
     * @param m Vähennettävä matriisi
     * @return Vähennyslaskun tuloksen sisältävä matriisi
     */
    
    public Matrix sub(Matrix m) {
        int rows2 = m.getRows();
        int columns2 = m.getColumns();
        if (rows != rows2 || columns != columns2) return null;
        int [][] matrix2 = m.getMatrix();
        int [][] resultMatrix = sub(matrix,matrix2);
        return new Matrix(resultMatrix);
    }
    
    private static int[][] sub(int[][] A, int[][] B) {
        int r = A.length;
        int c = A[0].length;
        int[][] C = new int[r][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                C[i][j] = A[i][j] - B[i][j];
            }
        }
        return C;
    }
    
    /**
     * Kertoo tämän matriisin syötteenä annetulla matriisilla ja palauttaa uuden Matrix-olion, joka sisältää kertolaskun tuloksen. Palauttaa nullin silloin
     * kun kertolasku ei ole määritelty, eli kun ensimmäisen matriisin sarakkeiden määrä on eri kuin jälkimmäisen matriisin rivien määrä.
     * 
     * @param m Matriisi, jolla kerrotaan
     * @return Kertolaskun tuloksen sisältävä matriisi
     */
    
    public Matrix mul(Matrix m) {
        int rows2 = m.getRows();
        int columns2 = m.getColumns();
        if (columns != rows2) return null;
        int [][] matrix2 = m.getMatrix();
        int [][] resultMatrix = mul(matrix,matrix2);
        return new Matrix(resultMatrix);
    }
    
    private static int[][] mul(int[][] A, int[][] B) {
        int r = A.length;
        int c = A[0].length;
        int c2 = B[0].length;
        int[][] C = new int[r][c2];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c2; j++) {
                int sum = 0;
                for (int k = 0; k < c; k++) {
                    sum += A[i][k] * B[k][j];
                }
                C[i][j] = sum;
            }
        }
        return C;
    }
    
    /**
     * Kertoo tämän matriisin syötteenä annetulla skalaarilla ja palauttaa uuden Matrix-olion, joka sisältää skalaarikertolaskun tuloksen
     * 
     * @param a Skalaari, jolla ollaan kertomassa
     * @return Skalaarikertolaskun tuloksen sisältävä matriisi
     */
    
    public Matrix scalarMul(int a) {
        int[][] resultMatrix = scalarMul(a, matrix);
        return new Matrix(resultMatrix);
    }
    
    private static int[][] scalarMul(int a, int[][] A) {
        int r = A.length;
        int c = A[0].length;
        int[][] C = new int[r][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                C[i][j] = a*A[i][j];
            }
        }
        return C;
    }
    
    /**
     * Palauttaa syötelukua seuraavan kakkosen potenssin
     * 
     * @param n Kokonaisluku
     * @return Kokonaislukua seuraava kakkosen potenssi
     */
    
    public static int nextPowerOfTwo(int n) {
        int log2 = (int) Math.ceil(Math.log(n) / Math.log(2));
        return (int) Math.pow(2, log2);
    }
    
    /**
     * Kertoo matriisin syötematriisilla Strassenin algoritmia käyttäen. Toteutus toimii vain samankokoisille neliömatriiseille
     * 
     * @param B Matriisi, jolla kerrotaan
     * @param limit Raja, jonka alapuolella käytetään tavallista matriisikertolaskua
     * @return Kertolaskun tulos. Null, jos kerrottavat matriisit eivät ole samankokoisia neliömatriiseja.
     */
    
    public Matrix strassenMul(Matrix B, int limit) {
        int n = rows;
        if (columns != rows || rows != B.getRows() || rows != B.getColumns()) return null;
        
        // Täydennetään matriisit A ja B 2^m-kokoisiksi matriiseiksi, jotta voidaan käyttää Strassenin algoritmia
        
        int m = nextPowerOfTwo(n);
        int[][] aMatrix = this.getMatrix();
        int[][] bMatrix = B.getMatrix();
        int[][] aStrassen = new int[m][m];
        int[][] bStrassen = new int[m][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                aStrassen[i][j] = aMatrix[i][j];
                bStrassen[i][j] = bMatrix[i][j];
            }
        }
        int[][] cStrassen = strassenMul(aStrassen,bStrassen,limit);
        int[][] C = new int[n][n];
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                C[i][j] = cStrassen[i][j];
            }
        }
        
        return new Matrix(C);
    }
    
    /**
     * Metodi laskee matriisien A ja B tulon Strassenin algoritmia käyttäen. Matriisien kokojen oletetaan olevan kakkosen potensseja. Algoritmissa hajotetaan matriisit A ja B neljään eri alimatriisiin. Näitä neljää
     * alimatriisia yhteenlaskemalla ja rekursiivisesti Strassenin algoritmia käyttäen kertomalla saadaan 7 matriisia p_k. Tulomatriisi saadaan laskemalla näitä 7
     * matriisia p_k yhteen. Tällöin jokaisella rekursioaskeleella tarvitsee tehdä vain 7 rekursiivista alikutsua, jolloin algoritmin aikavaativuus on loppujen lopuksi luokkaa O(n^(log_2 7)).
     * 
     * @param A Kokonaislukumatriisi
     * @param B Kokonaislukumatriisi
     * @param limit Raja, jonka alapuolella käytetään tavallista kertolaskua
     * @return Tulomatriisi kokonaislukutaulukkona
     */
    
    public int[][] strassenMul(int[][] A, int[][] B, int limit) {
        int n = A.length;
        if (n <= limit) {
            return mul(A,B);
        }
        /**
         * Initialisoidaan alimatriisit
         */
        
        int newN = n/2;
        
        int[][] a11 = new int[newN][newN];
        int[][] a12 = new int[newN][newN];
        int[][] a21 = new int[newN][newN];
        int[][] a22 = new int[newN][newN];

        int[][] b11 = new int[newN][newN];
        int[][] b12 = new int[newN][newN];
        int[][] b21 = new int[newN][newN];
        int[][] b22 = new int[newN][newN];
        
        int[][] aRet = new int[newN][newN];
        int[][] bRet = new int[newN][newN];

        
        for (int i = 0; i < newN; i++) {
            for (int j = 0; j < newN; j++) {
                a11[i][j] = A[i][j]; // vasen yläkulma
                a12[i][j] = A[i][j + newN]; // oikea yläkulma
                a21[i][j] = A[i + newN][j]; // vasen alakulma
                a22[i][j] = A[i + newN][j + newN]; // oikea alakulma

                b11[i][j] = B[i][j]; // vasen yläkulma
                b12[i][j] = B[i][j + newN]; // oikea yläkulma
                b21[i][j] = B[i + newN][j]; // vasen alakulma
                b22[i][j] = B[i + newN][j + newN]; // oikea alakulma
            }
        }
      
      //p1 = (a11+a22) * (b11+b22)
      
      aRet = add(a11,a22);
      bRet = add(b11,b22);
      int[][] p1 = strassenMul(aRet,bRet,limit);
      
      //p2 = (a21+a22) * b11
      
      aRet = add(a21,a22);
      int[][] p2 = strassenMul(aRet, b11, limit);
      
      //p3 = a11 * (b12 - b22)
      
      bRet = sub(b12,b22);
      int[][] p3 = strassenMul(a11,bRet,limit);
      
      //p4 = a22 * (b21-b11)
      bRet = sub(b21,b11);
      int[][]p4 = strassenMul(a22,bRet,limit);
      
      //p5 = (a11+a12) * b22
      aRet = add(a11,a12);
      int[][] p5 = strassenMul(aRet,b22,limit);
      
      //p6 = (a21-a11) * (b11+b12)
      
      aRet = sub(a21,a11);
      bRet = add(b11,b12);
      int[][] p6 = strassenMul(aRet,bRet,limit);
      
      //p7 = (a12-a22) * (b21+b22)
      
      aRet = sub(a12,a22);
      bRet = add(b21,b22);
      int[][] p7 = strassenMul(aRet,bRet,limit);
      
      //c12 = p3 + p5, c21 = p2 + p4
      
      int[][] c12 = add(p3,p5);
      int[][] c21 = add(p2,p4);
      
      // c11 = p1 + p4 - p5 + p7
      
      aRet = add(p1,p4);
      bRet = sub(p7,p5);
      int[][] c11 = add(aRet,bRet);
      
      // c22 = p1 + p3 - p2 + p6
      
      aRet = add(p1,p3);
      bRet = sub(p6,p2);
      int[][] c22 = add(aRet,bRet);
      
      // Tulomatriisi lasketaan yhdistämällä matriisit c11,c12,c21 ja c22
      
      int[][] C = new int[n][n];
        for (int i = 0; i < newN; i++) {
            for (int j = 0; j < newN; j++) {
                C[i][j] = c11[i][j];
                C[i][j + newN] = c12[i][j];
                C[i + newN][j] = c21[i][j];
                C[i + newN][j + newN] = c22[i][j];
            }
        }
        
        return C;
     
    }
    
}
