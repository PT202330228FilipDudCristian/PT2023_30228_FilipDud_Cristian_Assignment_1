package org.example;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parsing {

    private static final Pattern wrongPattern1 = Pattern.compile("[^\\d+x\\^+-]");
    private static final Pattern wrongPattern2= Pattern.compile("[+-]{2,}");
    private static final Pattern wrongPattern3= Pattern.compile("[\\^]{2,}");
    private static final Pattern wrongPattern4= Pattern.compile("[x]{2,}");
    private static final Pattern pattern = Pattern.compile("([+-]?(?:(?:\\d+x\\^\\d+)|(?:x\\^\\d+)|(?:\\d+x)|(?:\\d+)|(?:x)))");
    private static Matcher matcher;

    private static Monom monomMatcher(String string) {
        double coeficient;
        int grad;

        if (string.contains("x^")) {
            String[] parts = string.split("x\\^");
            grad = Integer.parseInt(parts[1]);
            coeficient = (parts[0].equals("") || parts[0].equals("+")) ? 1.0
                    : (parts[0].equals("-") ? -1.0 : Double.parseDouble(parts[0]));
        } else if (string.contains("x")) {
            String[] parts = string.split("x");
            grad = 1;
            coeficient = (parts.length == 0 || parts[0].equals("+")) ? 1.0
                    : (parts[0].equals("-") ? -1.0 : Double.parseDouble(parts[0]));
        } else {
            coeficient = Double.parseDouble(string);
            grad = 0;
        }
        return new Monom(grad, coeficient);
    }

    public static Polinom parse(String polinomString) throws Exception {
        matcher = wrongPattern1.matcher(polinomString);
        if(matcher.find())
            throw new Exception("Wrong pattern!");
        matcher = wrongPattern2.matcher(polinomString);
        if(matcher.find())
            throw new Exception("Wrong pattern!");
        matcher = wrongPattern3.matcher(polinomString);
        if(matcher.find())
            throw new Exception("Wrong pattern!");
        matcher = wrongPattern4.matcher(polinomString);
        if(matcher.find())
            throw new Exception("Wrong pattern!");
        matcher = pattern.matcher(polinomString);
        Polinom polinomFinal = new Polinom();
        while (matcher.find()) {
            Polinom polinom = new Polinom();

            polinom.addMonom(monomMatcher(matcher.group()));
            polinomFinal = Polinom.sumPolinom(polinomFinal, polinom);
        }
        return polinomFinal;
    }
}
