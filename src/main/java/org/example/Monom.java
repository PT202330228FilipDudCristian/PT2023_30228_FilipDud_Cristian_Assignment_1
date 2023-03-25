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

    /*public Polinom impartire (Polinom a,Polinom b){
        NavigableMap<Integer, Double> rezultat = new TreeMap<Integer, Double>();
        Polinom impartire = new Polinom(rezultat);
        NavigableMap<Integer, Double> copieA = new TreeMap<Integer, Double>();
        NavigableMap<Integer, Double> copieB = new TreeMap<Integer, Double>();
        Polinom copie= new Polinom(copieA);
        if(a.getPolinom().lastKey()<b.getPolinom().lastKey())//daca gradul lui a mai mic decat gradul lui b
        { this.setPolinom(rezultat);}
        else{
            copieA.putAll(a.getPolinom());
            copieB.putAll(b.getPolinom());
            copie.setPolinom(copieA);
            NavigableMap<Integer, Double> catMap = new TreeMap<Integer, Double>();
            NavigableMap<Integer, Double> scazutM = new TreeMap<Integer, Double>();
            int iesire_b=0;
            Polinom cat= new Polinom(catMap);
            while(copie.getPolinom().isEmpty()==false&&copie.getPolinom().lastKey()>=copieB.lastKey()){//parcurgem de la cea mai mare la cea mai mica cheie
                Double coeficient = copie.getPolinom().get(copie.getPolinom().lastKey()) / copieB.get(copieB.lastKey());
                rezultat.put(copie.getPolinom().lastKey()-copieB.lastKey(),coeficient);
                catMap.put(copie.getPolinom().lastKey()-copieB.lastKey(),coeficient);
                cat.setPolinom(catMap);
                Polinom scazut=new Polinom(catMap);
                scazut.inmultire(scazut,b);
                catMap.remove(copie.getPolinom().lastKey()-copieB.lastKey(),coeficient);
                copie.scadere(copie,scazut);
            }
        }
        rezultat=impartire.elimina_zerouri();
        this.setPolinom(rezultat);
        return copie;
    }*/
}



