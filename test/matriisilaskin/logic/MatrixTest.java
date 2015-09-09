package matriisilaskin.logic;


import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class MatrixTest {
    
    public MatrixTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void addAntaaOikeanTuloksen() {
        int[][] test1 = {{1,3},{2,5}};
        int[][] test2 = {{6, -3},{22, -3}};
        Matrix matrix1 = new Matrix(test1);
        Matrix matrix2 = new Matrix(test2);
        Matrix result = matrix1.add(matrix2);
        int[][] result2 = result.getMatrix();
        int[][] expected = {{7,0},{24,2}};
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                assertEquals(result2[i][j],expected[i][j]);
            }
        }
    }
    
    @Test
    public void addPalauttaaNullinJosMatriisejaEiPystyLaskemaanYhteen() {
        int[][] test1 = {{1,3},{2,5}};
        int[][] test2 = {{6, -3},{22, -3},{5,5}};
        Matrix matrix1 = new Matrix(test1);
        Matrix matrix2 = new Matrix(test2);
        Matrix result = matrix1.add(matrix2);
        assertEquals(result,null);
    }
    
    @Test
    public void subAntaaOikeanTuloksen() {
        int[][] test1 = {{1,3},{2,5}};
        int[][] test2 = {{6, -3},{22, -3}};
        Matrix matrix1 = new Matrix(test1);
        Matrix matrix2 = new Matrix(test2);
        Matrix result = matrix1.sub(matrix2);
        int[][] result2 = result.getMatrix();
        int[][] expected = {{-5,6},{-20,8}};
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                assertEquals(result2[i][j],expected[i][j]);
            }
        }
    }
    
    @Test
    public void subPalauttaaNullinJosMatriisejaEiPystyLaskemaanYhteen() {
        int[][] test1 = {{1,3},{2,5}};
        int[][] test2 = {{6, -3},{22, -3},{5,5}};
        Matrix matrix1 = new Matrix(test1);
        Matrix matrix2 = new Matrix(test2);
        Matrix result = matrix1.sub(matrix2);
        assertEquals(result,null);
    }
    
    @Test
    public void mulAntaaOikeanTuloksen() {
        int[][] test1 = {{6, -3},{22, -3},{5,5}};
        int[][] test2 = {{1,3},{2,5}};
        Matrix matrix1 = new Matrix(test1);
        Matrix matrix2 = new Matrix(test2);
        Matrix result = matrix1.mul(matrix2);
        int[][] result2 = result.getMatrix();
        int[][] expected = {{0,3},{16,51},{15,40}};
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 2; j++) {
                assertEquals(result2[i][j],expected[i][j]);
            }
        }
    }
    
    @Test
    public void mulPalauttaaNullinJosKertolaskuEiOleMahdollinen() {
        int[][] test1 = {{1,3},{2,5}};
        int[][] test2 = {{6, -3},{22, -3},{5,5}};
        Matrix matrix1 = new Matrix(test1);
        Matrix matrix2 = new Matrix(test2);
        Matrix result = matrix1.mul(matrix2);
        assertEquals(result,null);
    }
    
    @Test
    public void scalarMulAntaaOikeanTuloksen() {
        int[][] test1 = {{6, -2},{4, 9}};
        Matrix matrix1 = new Matrix(test1);
        int a = -4;
        Matrix result = matrix1.scalarMul(a);
        int[][] result2 = result.getMatrix();
        int[][] expected = {{-24,8},{-16,-36}};
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                assertEquals(result2[i][j], expected[i][j]);
            }
        }
    }

}
