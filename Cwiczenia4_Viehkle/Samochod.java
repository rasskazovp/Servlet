package Cwiczenia4_Viehkle;

import java.time.LocalDate;

public class Samochod extends Pojazd {
    protected LocalDate DataOstatniegoPrzeglondu;
    protected LocalDate DataWaznosciUbezpieczenia;

    public Samochod(String M, String Mod, int RP, int yDR, int mDR, int dDR, int yDOP, int mDOP, int dDOP, int yDWU, int mDWU, int dDWU) throws Exception {
        super(M, Mod, RP, yDR, mDR, dDR);
        DataOstatniegoPrzeglondu = LocalDate.of(yDOP, mDOP, dDOP);
        if(DataOstatniegoPrzeglondu.isAfter(LocalDate.now())){
            throw new Exception("Blendnie podana Data ostatniegopreglondu samochodu");
        }
        DataWaznosciUbezpieczenia = LocalDate.of(yDWU, mDWU, dDWU);
    }
    public void setDataOstatniegoPrzeglondu(int ymDOP,int mnDOP, int dnDOP ) throws Exception{
        DataOstatniegoPrzeglondu = LocalDate.of(ymDOP, mnDOP, dnDOP);
        if(DataOstatniegoPrzeglondu.isAfter(LocalDate.now())){
            throw new Exception("***");
        }
    }
    public void setDataWaznosciUbezpieczenia(int ynDWU, int mnDWU, int dnDWU){
        DataWaznosciUbezpieczenia = LocalDate.of(ynDWU, mnDWU, dnDWU);
    }

    public boolean czyPosiadaWaznyPrzyglond(){
        return !DataOstatniegoPrzeglondu.plusDays(365).isAfter(LocalDate.now());
    }
    public boolean czyWazneUbiezpieczenie(){
        return DataWaznosciUbezpieczenia.isBefore(LocalDate.now());
    }
}
