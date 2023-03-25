package org.example;

import java.text.DecimalFormat;
import java.util.*;

public class Polinom {

    private Map<Integer, Monom> poli = new HashMap<Integer, Monom>();


    public Polinom(Map<Integer, Monom> poli) {
        this.poli = poli;
    }

    public Polinom() {
    }

    public Map<Integer, Monom> getPoli() {
        return poli;
    }

    public void setPoli(Map<Integer, Monom> poli) {
        this.poli = poli;
    }

    public void addMonom(Monom monom) {
        poli.put(monom.getGrad(), monom);
    }

    private static void copyPolinom(Polinom source, Polinom destination) {
        for (Map.Entry<Integer, Monom> m : source.getPoli().entrySet()) {
            destination.addMonom(new Monom(m.getValue().getGrad(), m.getValue().getCoeficient()));
        }
    }

    private static Polinom cleanPolinom(Polinom p) {
        Polinom clean = new Polinom();
        for (Map.Entry<Integer, Monom> m : p.getPoli().entrySet()) {
            if (m.getValue().getCoeficient() != 0) {
                clean.addMonom(new Monom(m.getValue().getGrad(), m.getValue().getCoeficient()));
            }
        }
        return clean;
    }

    public Monom getMaxGradeMonom() {
        boolean firstIteration = true;
        int maxGrade = 0;
        Monom maxGradeMonom = new Monom(0, 0.0);

        for (Map.Entry<Integer, Monom> m : poli.entrySet()) {
            if (firstIteration) {
                maxGrade = m.getValue().getGrad();
                maxGradeMonom = m.getValue();
                firstIteration = false;
            }
            if (maxGrade < m.getValue().getGrad()) {
                maxGrade = m.getKey();
                maxGradeMonom = m.getValue();
            }
        }
        return maxGradeMonom;
    }

    public static Polinom sumPolinom(Polinom p1, Polinom p2) {

        Polinom p3 = new Polinom();
        Polinom.copyPolinom(p1, p3);

        for (Map.Entry<Integer, Monom> m : p2.getPoli().entrySet()) {

            if (p3.getPoli().containsKey(m.getKey())) {
                double newCoeficient = p3.getPoli().get(m.getKey()).getCoeficient() + m.getValue().getCoeficient();
                p3.getPoli().get(m.getKey()).setCoeficient(newCoeficient);
            } else
                p3.getPoli().put(m.getKey(), new Monom(m.getKey(), m.getValue().getCoeficient()));
        }
        return cleanPolinom(p3);
    }

    public static Polinom substractPolinom(Polinom p1, Polinom p2) {

        Polinom p3 = new Polinom();
        p3.getPoli().putAll(p1.getPoli());
        //Polinom.copyPolinom(p1, p3);

        for (Map.Entry<Integer, Monom> m : p2.getPoli().entrySet()) {
            if (p3.getPoli().containsKey(m.getKey())) {
                p3.getPoli().get(m.getKey()).setCoeficient(p3.getPoli().get(m.getKey()).getCoeficient() - m.getValue().getCoeficient());
            } else {
                p3.getPoli().put(m.getKey(), new Monom(m.getKey(), -m.getValue().getCoeficient()));
            }

            /*if (p3.getPoli().get(m.getKey()).getCoeficient() == 0) {
                p3.getPoli().remove(p3.getPoli().get(m.getKey()).getGrad());
            }*/
        }
        return cleanPolinom(p3);
    }

    public static Polinom multiplyPolinom(Polinom p1, Polinom p2) {
        Polinom p3 = new Polinom();

        for (Map.Entry<Integer, Monom> m1 : p1.getPoli().entrySet()) {
            for (Map.Entry<Integer, Monom> m2 : p2.getPoli().entrySet()) {
                int exponent = m1.getValue().getGrad() + m2.getValue().getGrad();
                double coeficient = m1.getValue().getCoeficient() * m2.getValue().getCoeficient();
                if (p3.getPoli().containsKey(exponent)) {
                    p3.getPoli().get(exponent).setCoeficient(p3.getPoli().get(exponent).getCoeficient() + coeficient);
                } else {
                    p3.getPoli().put(exponent, new Monom(exponent, coeficient));
                }
            }
        }
        return cleanPolinom(p3);
    }


    public static ArrayList<Polinom> dividePolinom(Polinom dividend, Polinom divisor) throws Exception {
        Polinom quotient = new Polinom();
        Polinom remainder = new Polinom();
        remainder.getPoli().putAll(dividend.getPoli());
        ArrayList<Polinom> divisionResult = new ArrayList<Polinom>();
        if (divisor.getMaxGradeMonom().getGrad() == 0 && divisor.getMaxGradeMonom().getCoeficient() == 0) {
            System.out.println("Impartire cu polinom nul");
            throw new Exception("Impartire cu nul");
        } else {
            if (dividend.getMaxGradeMonom().getGrad() < divisor.getMaxGradeMonom().getGrad()) {
                Polinom nullQuotient = new Polinom();
                nullQuotient.addMonom(new Monom(0, 0.0));

                divisionResult.add(nullQuotient);
                divisionResult.add(remainder);
                return divisionResult;
            }

            while (remainder.getMaxGradeMonom().getGrad() >= divisor.getMaxGradeMonom().getGrad()) {
                double coeficient = (remainder.getMaxGradeMonom().getCoeficient() / divisor.getMaxGradeMonom().getCoeficient());
                int grade = remainder.getMaxGradeMonom().getGrad() - divisor.getMaxGradeMonom().getGrad();

                quotient.addMonom(new Monom(grade, coeficient));

                Polinom polinomialTerm = new Polinom();
                polinomialTerm.addMonom(new Monom(grade, coeficient));
                Polinom temp = Polinom.multiplyPolinom(polinomialTerm, divisor);
                remainder = Polinom.substractPolinom(remainder, temp);
            }
        }
        divisionResult.add(cleanPolinom(quotient));
        divisionResult.add(cleanPolinom(remainder));
        return divisionResult;
    }



    public static Polinom derivativePolinom(Polinom p) {

        Polinom derivative = new Polinom();

        for (Map.Entry<Integer, Monom> m : p.getPoli().entrySet()) {

            double coeficient = m.getValue().getCoeficient() * m.getValue().getGrad();
            int grade = m.getValue().getGrad() - 1;
            if (grade >= 0) {
                Monom newMonom = new Monom(grade, coeficient);
                Polinom tempPoli = new Polinom();
                tempPoli.getPoli().put(grade, newMonom);

                derivative = Polinom.sumPolinom(derivative, tempPoli);
            }
        }

        return cleanPolinom(derivative);
    }

    public static Polinom integratePolinom(Polinom p) {

        Polinom integral = new Polinom();
        Polinom.copyPolinom(p, integral);

        for (Map.Entry<Integer, Monom> m : integral.getPoli().entrySet()) {
            m.getValue().setGrad(m.getValue().getGrad() + 1);
            m.getValue().setCoeficient(m.getValue().getCoeficient() / m.getValue().getGrad());
        }
        return cleanPolinom(integral);
    }

    @Override
    public String toString() {
        DecimalFormat format = new DecimalFormat("0.##");
        String toPrint = "";

        if (poli.isEmpty()) {
            return "0";
        }

        boolean firstIteration = true;
        for (Map.Entry<Integer, Monom> m : poli.entrySet()) {

            if (firstIteration) {
                firstIteration = false;
                //-------------------------DACA PRIMU ELEMENT ARE  GRADU 0----------------------------
                if (m.getValue().getGrad() == 0)
                    toPrint += format.format(m.getValue().getCoeficient());
                else if (m.getValue().getCoeficient() == 1)
                    toPrint += "x^" + m.getValue().getGrad();
                else
                    toPrint += format.format(m.getValue().getCoeficient()) + "x^" + m.getValue().getGrad();

            } else {
                toPrint += (m.getValue().getCoeficient() >= 0) ? " +" : " ";
                if (m.getValue().getCoeficient() == 1)
                    toPrint += "x^" + m.getValue().getGrad();
                else
                    toPrint += format.format(m.getValue().getCoeficient()) + "x^" + m.getValue().getGrad();
            }

        }
        return toPrint;
    }

    @Override
    public boolean equals(Object polinom) {

        for (Map.Entry<Integer, Monom> m : this.poli.entrySet()) {
            if (!((Polinom) polinom).getPoli().containsKey(m.getKey()))
                return false;
            if (!m.getValue().getCoeficient().equals(((Polinom) polinom).getPoli().get(m.getKey()).getCoeficient()))
                return false;
        }
        return true;
    }
}
