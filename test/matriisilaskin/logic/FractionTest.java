package matriisilaskin.logic;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class FractionTest {
    
    public FractionTest() {
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
    public void absToimii() {
        int ret = Fraction.abs(-3);
        int ret2 = Fraction.abs(1);
        int ret3 = Fraction.abs(0);
        assertEquals(ret, 3);
        assertEquals(ret2, 1);
        assertEquals(ret3, 0);
    }

    @Test
    public void gcdToimii() {
        int ret = Fraction.gcd(4, 6);
        assertEquals(ret, 2);
    }
    
    @Test
    public void simplifyToimii() {
        Fraction test = new Fraction(-3,6);
        int n = test.getNum();
        int d = test.getDen();
        assertEquals(n, -1);
        assertEquals(d, 2);
    }
    
    @Test
    public void josOsoittajaJaNimittajaOvatNegatiivisiaLuotuMurtolukuOnPositiivinen() {
        Fraction test = new Fraction(-4,-6);
        int n = test.getNum();
        int d = test.getDen();
        assertTrue(n > 0);
        assertTrue(d > 0);
    }
    
    @Test
    public void addToimii() {
        Fraction test = new Fraction(2,5);
        Fraction test2 = new Fraction(6,7);
        Fraction ret = test.add(test2);
        assertEquals(ret, new Fraction(44,35));
    }
    
    @Test
    public void subToimii() {
        Fraction test = new Fraction(2,5);
        Fraction test2 = new Fraction(6,7);
        Fraction ret = test.sub(test2);
        assertEquals(ret, new Fraction(-16,35));
    }
    
    @Test
    public void mulToimii() {
        Fraction test = new Fraction(2,5);
        Fraction test2 = new Fraction(6,7);
        Fraction ret = test.mul(test2);
        assertEquals(ret, new Fraction(12,35));
    }
    
    @Test
    public void divToimii() {
        Fraction test = new Fraction(2,5);
        Fraction test2 = new Fraction(6,7);
        Fraction ret = test.div(test2);
        assertEquals(ret, new Fraction(7,15));
    }
}
