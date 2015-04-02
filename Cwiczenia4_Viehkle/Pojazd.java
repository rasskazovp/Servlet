package Cwiczenia4_Viehkle;

import java.time.LocalDate;

public class Pojazd {
    public String marka, model;
    public int RokProdukcji;
    protected LocalDate DataRejestracji;


    public Pojazd(String M, String Mod, int RP, int yDR, int mDR, int dDR)throws Exception{
        DataRejestracji = LocalDate.of(yDR, mDR, dDR);
        if(DataRejestracji.isAfter(LocalDate.now())){
            throw new Exception("Blendnie podana Data Rejestracji Samochodu");
        }
        marka = M;
        model = Mod;
        RokProdukcji = RP;
    }
    public boolean czyPosiadaWaznyPrzyglond(){
            return true;
    }
    public boolean czyWazneUbiezpieczenie(){
        return true;
    }
    public boolean czyWaznyTaksometr(){
        return true;
    }
}
