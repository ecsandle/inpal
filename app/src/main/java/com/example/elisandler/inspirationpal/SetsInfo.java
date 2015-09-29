package com.example.elisandler.inspirationpal;

import java.util.ArrayList;

/**
 * Created by elisandler on 7/16/15.
 */
public class SetsInfo {

    ArrayList<Integer> reps = new ArrayList<>();
    ArrayList<Integer> weight = new ArrayList<>();

    public ArrayList<Integer> getReps(){
        return reps;
    }

    public ArrayList<Integer> getWeight(){
        return weight;
    }

    public void addRep(Integer integer){
        reps.add(integer);
    }

    public void addWeight(Integer integer){
        weight.add(integer);
    }


}
