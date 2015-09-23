package matriisilaskin.logic;

/**
 * Murtolukuja mallintava luokka
 */
public class Fraction {
    /**
    * Murtoluvun osoittaja
    */
    int num;
    
    /**
     * Murtoluvun nimittäjä
     */
    
    int den;
    
    /**
     * Luo ja sieventää murtoluvun, jonka osoittaja ja nimittäjä saadaan syötteistä. Syötteenä annetun nimittäjän oletetaan poikkeavan nollasta.
     * 
     * @param n Murtoluvun osoittaja
     * @param d Murtoluvun nimittäjä
     */
    
    public Fraction(int n, int d) {
        num = n;
        den = d;
        simplify();
    }
    
    public Fraction(int n) {
        num = n;
        den = 1;
    }
    
    public int getNum() {
        return num;
    }
    
    public int getDen() {
        return den;
    }
    
    /**
     * Palauttaa syötteenä annetun luvun itseisarvon
     * 
     * @param x Kokonaisluku
     * @return Syötteenä annetun kokonaisluvun itseisarvo
     */
    
    public static int abs(int x) {
        if (x < 0) return -x;
        return x;
    }
    
    /**
     * Laskee syötteenä annettujen lukujen suurimman yhteisen tekijän Eukleideen algoritmin avulla
     * 
     * @param n Kokonaisluku
     * @param d Kokonaisluku
     * 
     * @return Syötteenä annettujen kokonaislukujen suurin yhteinen tekijä
     */
    
    public static int gcd(int n,int d) {
        int temp;
        int aN = abs(n);
        int aD = abs(d);
        while (aN > 0) {
            temp = aN;
            aN = aD % aN;
            aD = temp;
        }
        return aD;
    }
    
    /**
     * Sieventää murtoluvun jakamalla osoittajan ja nimittäjän näiden suurimmalla yhteisellä tekijällä
     */
    
    private void simplify() {
        int gcd = gcd(num,den);
        num /= gcd;
        den /= gcd;
        if (den < 0) {
            num = -num;
            den = -den;
        } 
    }
    
    /**
     * Lisää syötteenä annetun murtoluvun tähän lukuun ja palauttaa lopputuloksena syntyvän murtoluvun
     * @param f Yhteenlaskettava murtoluku
     * @return Murtoluku, joka sisältää yhteenlaskun tuloksen
     */
    
    public Fraction add(Fraction f) {
        int n = f.getNum();
        int d = f.getDen();
        int retNum = (num * d) + (n * den);
        int retDen = d * den;
        return new Fraction(retNum,retDen);
    }
    
    /**
     * Vähentää syötteenä annetun murtoluvun tästä luvusta ja palauttaa lopputuloksena syntyvän murtoluvun
     * 
     * @param f Vähennettävä murtoluku
     * @return Murtoluku, joka sisältää vähennyslaskun tuloksen
     */
    
    public Fraction sub(Fraction f) {
        int n = f.getNum();
        int d = f.getDen();
        int retNum = (num * d) - (n * den);
        int retDen = d * den;
        return new Fraction(retNum,retDen);
    }
    
    /**
     * Kertoo murtoluvun syötteenä annetulla murtoluvulla ja palauttaa lopputuloksena syntyvän murtoluvun
     * 
     * @param f Murtoluvu, jolla kerrotaan
     * @return Murtoluku, joka sisältää kertolaskun tuloksen
     */
    
    public Fraction mul(Fraction f) {
        int n = f.getNum();
        int d = f.getDen();
        int retNum = n * num;
        int retDen = d * den;
        return new Fraction(retNum,retDen);
    }
    
    /**
     * Jakaa murtoluvun syötteenä annetulla murtoluvulla ja palauttaa lopputuloksena syntyvän murtoluvun
     * 
     * @param f Murtoluvu, jolla jaetaan
     * @return Murtoluku, joka sisältää jakolaskun tuloksen
     */
    
    public Fraction div(Fraction f) {
        int n = f.getNum();
        int d = f.getDen();
        int retNum = num * d;
        int retDen = den * n;
        return new Fraction(retNum,retDen);
    }
    
    @Override
    public String toString() {
        if (den == 1 || num == 0) return num + "";
        return num + "/" + den;
    }
    
    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (getClass() != o.getClass()) return false;
        Fraction f = (Fraction) o;
        int n = f.getNum();
        int d = f.getDen();
        if (num != n || den != d) return false;
        return true;
    }
}
