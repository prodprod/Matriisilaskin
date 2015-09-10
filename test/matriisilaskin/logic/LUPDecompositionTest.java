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
}
