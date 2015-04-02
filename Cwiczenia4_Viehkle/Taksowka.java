package Cwiczenia4_Viehkle;

import java.time.LocalDate;

public class Taksowka extends Samochod {
    protected LocalDate DataWaznosciTaksometru;

    public Taksowka(String M, String Mod, int RP, int yDR, int mDR, int dDR, int yDOP, int mDOP, int dDOP, int yDWU, int mDWU, int dDWU, int yDWT, int mDWT, int dDWT) throws Exception {
        super(M, Mod, RP, yDR, mDR, dDR, yDOP, mDOP, dDOP, yDWU, mDWU, dDWU);
        DataWaznosciTaksometru = LocalDate.of(yDWT, mDWT, dDWT);
    }

    public void setDataWaznosciTaksometru(int ynDWT, int mnDWT, int dnDWT)throws Exception{
        DataWaznosciTaksometru = LocalDate.of(ynDWT, mnDWT, dnDWT);
    }
    public boolean czyPosiadaWaznyPrzyglond(){
        return !DataOstatniegoPrzeglondu.plusMonths(6).isAfter(LocalDate.now());
    }
    public boolean czyWaznyTaksometr(){
        return DataWaznosciTaksometru.isBefore(LocalDate.now());
    }
}
