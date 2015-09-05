
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
        int [][] resultMatrix = new int[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns ; j++) {
                resultMatrix [i][j] = matrix[i][j] + matrix2[i][j];
            }
        }
        return new Matrix(resultMatrix);
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
        int [][] resultMatrix = new int[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns ; j++) {
                resultMatrix [i][j] = matrix[i][j] - matrix2[i][j];
            }
        }
        return new Matrix(resultMatrix);
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
        int [][] resultMatrix = new int[rows][columns2];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns2; j++) {
                int sum = 0;
                for (int k = 0; k < columns; k++) {
                    sum += matrix[i][k] * matrix2[k][j];
                }
                resultMatrix[i][j] = sum;
            }
        }
        return new Matrix(resultMatrix);
    }
    
}
