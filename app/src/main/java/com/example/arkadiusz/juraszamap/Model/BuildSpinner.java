package com.example.arkadiusz.juraszamap.Model;

public class BuildSpinner {
    private String mbudynek, mopisBudynku;

    public BuildSpinner(){}

    public void  setBudynek(String budynek){this.mbudynek=budynek;}
    public void  setOpisBudynku(String opisbudynku){this.mopisBudynku=opisbudynku;}

    public String getBudynek(){
        return mbudynek;
    }
    public String getOpisBudynku(){
        return mopisBudynku;
    }
}
