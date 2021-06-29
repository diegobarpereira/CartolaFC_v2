package com.diegopereira.cartolafc.league;


import java.util.Comparator;

public class PontosComparator implements Comparator<Times> {

    @Override
    public int compare(Times o1, Times o2) {
        if (o2.getTotal() != null && o1.getTotal() != null) {
            return o2.getTotal().compareTo(o1.getTotal());
        }
        return 1;
    }


}
