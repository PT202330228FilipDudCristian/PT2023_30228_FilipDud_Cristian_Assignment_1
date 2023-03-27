package org.example;

import java.util.NavigableMap;
import java.util.Objects;
import java.util.TreeMap;

public class Monom {

    private Integer grad;
    private Double coeficient;

    public Monom(Integer grad, Double coeficient) {
        this.grad = grad;
        this.coeficient = coeficient;
    }

    public Integer getGrad() {
        return grad;
    }

    public void setGrad(Integer grad) {
        this.grad = grad;
    }

    public Double getCoeficient() {
        return coeficient;
    }

    public void setCoeficient(Double coeficient) {
        this.coeficient = coeficient;
    }

    @Override
    public String toString() {
        return coeficient+"x^"+grad;
    }

    @Override
    public boolean equals(Object obj) {
        return Objects.equals(this.grad, ((Monom) obj).getGrad()) && Objects.equals(this.coeficient, ((Monom) obj).getCoeficient());
    }

}



