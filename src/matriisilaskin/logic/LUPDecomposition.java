package matriisilaskin.logic;
import java.util.ArrayList;

/**
 *
 * LUP-hajotelman ja sitä käyttävät algoritmit sisältävä luokka
 */
public class LUPDecomposition {
    
    /**
     * Luonnin yhteydessä syötteenä annetun neliömatriisin koko
     */
    
    int n;
    
    /**
     * LUP-hajotelman alakolmiomatriisin L ja yläkolmiomatriisin U sisältävä taulukko. Taulukon aladiagonaalilla olevat alkiot ovat L:n alkioita ja ylädiagonaalilla olevat alkiot, mukaanlukien itse diagonaali, U:n alkioita
     */
            
    private Fraction[][] LU;
    
    /**
     * LUP-hajotelman permutaatiomatriisia P kuvaava taulukko. Jos P[i] == j, niin matriisin P i:s rivi sisältää ykkösen sarakkeessa j, ts. permutaatiomatriisi on esitetty syklimuodossa.
     */
    
    private int[] P;
    
    /**
     * Boolean, joka kertoo onko syötteenä annettu matriisi singulaarinen
     */
    
    private boolean singular;
    
    /**
     * Laskee syötteenä annetun neliömatriisin LUP-hajotelman. Algoritmi etsii siis alakolmiomatriisin L, yläkolmiomatriisin U  ja 
     * permutaatiomatriisin P, joille pätee PA = LU, jossa A on syötteenä annettu matriisi. L-matriisi normitetaan siten, että sen diagonaalilla on vain ykkösiä.
     * Jos matriisi on singulaarinen, lopetetaan LUP-hajotelman etsiminen.
     * @param m Neliömatriisi, jonka LUP-hajotelmaa ollaan laskemassa
     */
    
    public LUPDecomposition(Matrix m) {
        n = m.getRows();
        singular = false;
        int[][] matrix = m.getMatrix();
        LU = new Fraction[n][n];
        P = new int[n];
        
        /*
        Alustetaan LU-matriisi syötematriisiksi ja permutaatiomatriisi identiteettimatriisiksi.
        */
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                LU[i][j] = new Fraction(matrix[i][j]);
                
            }
            P[i] = i;
        }
        
        /*
        Etsitään jokaisesta sarakkeesta jokin nollasta poikkeava alkio diagonaalilta tai sen alapuolelta
        */
        
        for(int k = 0; k < n;  k++) {
            if (singular) break;
            int k1 = -1;
            boolean s = true;
            for (int i = k; i < n; i++) {
                if (LU[i][k].getNum() != 0) {
                    s = false;
                    k1 = i;
                }
            }
            
            /*
            Jos nollasta poikkeavaa alkiota ei löydy, todetaan matriisi singulaariseksi
            */
            
            if (s) {
                singular = true;
                break;
            }
            
            /*
            Vaihdetaan k:nnet ja k1:nnet rivit keskenään ja merkitään tehty permutaatio permutaatiomatriisiin
            */
            
            int temp;
            temp = P[k1];
            P[k1] = P[k];
            P[k] = temp;
            for (int i = 0; i < n; i++) {
                int n = LU[k][i].getNum();
                int d = LU[k][i].getDen();
                int n1 = LU[k1][i].getNum();
                int d1 = LU[k1][i].getDen();
                LU[k][i] = new Fraction(n1,d1);
                LU[k1][i] = new Fraction(n,d);
            }
            
            /*
            Lasketaan matriisin Schur complement alkion LU[k][1] suhteen ja lähdetään laskemaan sen LUP-hajotelmaa ensi iteraatiokierroksella
            */
            
            for (int i = k+1; i < n; i++) {
                LU[i][k] = LU[i][k].div(LU[k][k]);
                for (int j = k+1; j < n; j++) {
                    LU[i][j] = LU[i][j].sub(LU[i][k].mul(LU[k][j]));
                }
            }
        }
        
    }
    
    public Fraction[][] getLU() {
        return LU;
    }
    
    public int[] getP() {
        return P;
    }
    
    public boolean getSingular() {
        return singular;
    }
    
    /**
     * Metodi laskee LUP-hajotelman permutaatiomatriisin P merkin.
     * @return -1, jos permutaatio sisältää parittoman määrän parillisen pituisia syklejä. Muuten 1.
     */
    
    public int signP() {
        ArrayList<Integer> visited = new ArrayList<Integer>();
        boolean odd = false;
        for (int i = 0; i < n; i++) {
            if (visited.contains(i)) continue;
            else {
                int curr = i;
                int length = 0;
                while (!visited.contains(curr)) {
                    curr = P[curr];
                    visited.add(i);
                    length++;
                }
                if (length % 2 == 0) odd = !odd;
            }
        }
        if (odd) return -1;
        else return 1;
    }
    
    /**
     * Laskee matriisin determinantin lasketun LUP-hajotelman avulla. Determinantti saadaan kertomalla U-matriisin diagonaalilla 
     * olevat alkiot keskenään ja kertomalla tämän lopputuloksen permutaatiomatriisin P merkillä. Jos matriisi on singulaarinen, palautetaan 0.
     * 
     * @return Sen matriisin determinantti, jonka LUP-hajotelma laskettiin. 
     */
    
    public int determinant() {
        if (singular) return 0;
        int det = 1;
        Fraction f = LU[0][0];
        for (int i = 1; i < n; i++) {
            f = f.mul(LU[i][i]);
        }
        det *= f.getNum();
        det *= signP();
        return det;
    }
    
    
    
}
