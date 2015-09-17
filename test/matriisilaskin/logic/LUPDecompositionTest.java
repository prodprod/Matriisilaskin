package matriisilaskin.logic;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Prodi
 */
public class LUPDecompositionTest {
    
    public LUPDecompositionTest() {
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
    public void eiSingulaaristaMatriisiaEiTodetaSingulaariseksi() {
        int[][] test1 = {{4,7,-2},{3,21,-66},{3,-3,0}};
        Matrix testMatrix = new Matrix(test1);
        LUPDecomposition testLUP = new LUPDecomposition(testMatrix);
         boolean result = testLUP.getSingular();
        assertFalse(result);
    }
    
    @Test
    public void singulaarinenMatriisiTodetaanSingulaariseksi() {
        int[][] test1 = {{1,0,0},{-2,0,0},{4,6,1}};
        Matrix testMatrix = new Matrix(test1);
        LUPDecomposition testLUP = new LUPDecomposition(testMatrix);
        boolean result = testLUP.getSingular();
        assertTrue(result);
    }

    @Test
    public void singulaarisenMatriisinDeterminanttiOn0() {
        int[][] test1 = {{1,0,0},{-2,0,0},{4,6,1}};
        Matrix testMatrix = new Matrix(test1);
        LUPDecomposition testLUP = new LUPDecomposition(testMatrix);
        int result = testLUP.determinant();
        assertEquals(result, 0);
    }
    
    @Test
    public void determinantAntaaOikeanTuloksen() {
        int[][] test1 = {{4,7,-2},{3,21,-66},{3,-3,0}};
        Matrix testMatrix = new Matrix(test1);
        LUPDecomposition testLUP = new LUPDecomposition(testMatrix);
        int result = testLUP.determinant();
        assertEquals(result, -2034);
    }
    
    @Test
    public void inversePalauttaaNullJosMatriisiSingulaarinen() {
        int[][] test1 = {{2,4},{4,8}};
        Matrix testMatrix = new Matrix(test1);
        LUPDecomposition testLUP = new LUPDecomposition(testMatrix);
        Fraction[][] inverse = testLUP.inverse();
        assertNull(inverse);
    }
    
    @Test
    public void inverseAntaaOikeanTuloksen() {
        int[][] test1 = {{-4,-2},{9,1}};
        Matrix testMatrix = new Matrix(test1);
        LUPDecomposition testLUP = new LUPDecomposition(testMatrix);
        Fraction[][] inverse = testLUP.inverse();
        Fraction[][] expected = {{new Fraction(1,14),new Fraction(1,7)},{new Fraction(-9,14), new Fraction(-2,7)}};
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                assertEquals(inverse[i][j],expected[i][j]);
            }
        }
    }
}
