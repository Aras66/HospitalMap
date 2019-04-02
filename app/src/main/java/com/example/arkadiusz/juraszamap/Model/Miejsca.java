package com.example.arkadiusz.juraszamap.Model;

public class Miejsca {

    private int  pietro;
    private String budynek;
    private String opis;
    private String uwagi;
    private String opisBudynku;
    private int id ;
public Miejsca(){}


    public int getPietro() {
        return pietro;
    }

    public void setPietro(int pietro) {
        this.pietro = pietro;
    }

    public String getBudynek() {
        return budynek;
    }

    public void setBudynek(String budynek) {
        this.budynek = budynek;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public String getUwagi() {
        return uwagi;
    }

    public void setUwagi(String uwagi) {
        this.uwagi = uwagi;
    }

    public String getOpisBudynku() {
        return opisBudynku;
    }

    public void setOpisBudynku(String opisBudynku) {
        this.opisBudynku = opisBudynku;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

  /*  public Miejsca(int pietro, String budynek, String opis, String uwagi, String opisBudynku) {

        this.pietro = pietro;
        this.budynek = budynek;
        this.opis = opis;
        this.uwagi = uwagi;
        this.opisBudynku = opisBudynku;
    }
*/

}
