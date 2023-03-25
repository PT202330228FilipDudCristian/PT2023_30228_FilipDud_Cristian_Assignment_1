package org.example;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PolinomTest {
    @Test
    public void sumTest()
    {
        Polinom p1 = new Polinom();
        p1.addMonom(new Monom(1, 3.0));
        p1.addMonom(new Monom(2, 2.0));
        p1.addMonom(new Monom(3, 1.0));

        Polinom p2 = new Polinom();

        p2.addMonom(new Monom(1, 3.0));
        p2.addMonom(new Monom(2, 2.5));
        p2.addMonom(new Monom(3, 1.0));
        p2.addMonom(new Monom(5, 10.0));

        Polinom rezultat = new Polinom();
        rezultat.addMonom(new Monom(1,6.0));
        rezultat.addMonom(new Monom(2,4.5));
        rezultat.addMonom(new Monom(3,2.0));
        rezultat.addMonom(new Monom(5,10.0));

        assertEquals(Polinom.sumPolinom(p1,p2),rezultat);
    }
    @Test
    public void substractTest()
    {
        Polinom p1 = new Polinom();
        p1.addMonom(new Monom(1, 5.0));
        p1.addMonom(new Monom(2, 4.0));
        p1.addMonom(new Monom(3, 2.0));

        Polinom p2 = new Polinom();

        p2.addMonom(new Monom(1, 3.0));
        p2.addMonom(new Monom(2, 2.5));
        p2.addMonom(new Monom(3, 1.0));
        p2.addMonom(new Monom(5, 10.0));

        Polinom rezultat = new Polinom();
        rezultat.addMonom(new Monom(1,-2.0));
        rezultat.addMonom(new Monom(2,-1.5));
        rezultat.addMonom(new Monom(3,-1.0));
        rezultat.addMonom(new Monom(5,10.0));

        assertEquals(Polinom.substractPolinom(p2,p1),rezultat);
    }

    @Test
    public void multiplyTest()
    {
        Polinom p1 = new Polinom();
        p1.addMonom(new Monom(1, -2.0));
        p1.addMonom(new Monom(2, 3.0));
        p1.addMonom(new Monom(3, 1.0));

        Polinom p2 = new Polinom();
        p2.addMonom(new Monom(0,5.0));
        p2.addMonom(new Monom(2,1.0));

        Polinom rezultat = new Polinom();
        rezultat.addMonom(new Monom(1,-10.0));
        rezultat.addMonom(new Monom(2,15.0));
        rezultat.addMonom(new Monom(3,3.0));
        rezultat.addMonom(new Monom(4,3.0));
        rezultat.addMonom(new Monom(5,1.0));

        assertEquals(Polinom.multiplyPolinom(p1,p2),rezultat);
    }

    @Test
    public void divideTest() throws Exception {
        Polinom p1 = new Polinom();
        p1.addMonom(new Monom(0, -2.0));
        p1.addMonom(new Monom(2, -12.0));
        p1.addMonom(new Monom(3, -4.0));
        p1.addMonom(new Monom(4, 5.0));
        p1.addMonom(new Monom(6,32.0));

        Polinom p2 = new Polinom();
        p2.addMonom(new Monom(0,4.0));
        p2.addMonom(new Monom(1,-1.0));
        p2.addMonom(new Monom(2,-2.0));
        p2.addMonom(new Monom(3,1.0));

        Polinom rezultatCat = new Polinom();
        rezultatCat.addMonom(new Monom(0,262.0));
        rezultatCat.addMonom(new Monom(1,165.0));
        rezultatCat.addMonom(new Monom(2,64.0));
        rezultatCat.addMonom(new Monom(3,32.0));

        Polinom rezultatRest = new Polinom();
        rezultatRest.addMonom(new Monom(0,-1050.0));
        rezultatRest.addMonom(new Monom(1,-398.0));
        rezultatRest.addMonom(new Monom(2,421.0));

        ArrayList<Polinom> rezultatImpartire = Polinom.dividePolinom(p1,p2);
        assertEquals(rezultatImpartire.get(0),rezultatCat);
        assertEquals(rezultatImpartire.get(1),rezultatRest);
    }


    @Test
    public void derivateTest()
    {
        Polinom p1 = new Polinom();
        p1.addMonom(new Monom(0, -5.0));
        p1.addMonom(new Monom(1, 2.0));
        p1.addMonom(new Monom(2, 1.3));
        p1.addMonom(new Monom(3, 5.0));
        p1.addMonom(new Monom(5, -10.0));
        p1.addMonom(new Monom(6, 7.0));

        Polinom rezultat = new Polinom();
        rezultat.addMonom(new Monom(0, 2.0));
        rezultat.addMonom(new Monom(1, 2.6));
        rezultat.addMonom(new Monom(2, 15.0));
        rezultat.addMonom(new Monom(4, -50.0));
        rezultat.addMonom(new Monom(5, 42.0));

        assertEquals(Polinom.derivativePolinom(p1),rezultat);
    }

    @Test
    public void integrateTest()
    {
        Polinom p1 = new Polinom();
        p1.addMonom(new Monom(0, 5.0));
        p1.addMonom(new Monom(2, 6.0));
        p1.addMonom(new Monom(3, 1.0));

        Polinom rezultat = new Polinom();
        rezultat.addMonom(new Monom(1, 5.0));
        rezultat.addMonom(new Monom(3, 2.0));
        rezultat.addMonom(new Monom(4, 0.25));

        assertEquals(Polinom.integratePolinom(p1),rezultat);
    }
}
