package Cwiczenia4_Viehkle;

import java.io.*;
import java.time.LocalDate;
import java.util.*;
import java.util.regex.*;

public class Test {

    protected static ArrayList<Pojazd> ListaPojazdow = new ArrayList<Pojazd>();

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scan = new Scanner(new File("E://GUI//src//Cwiczenia4_Viehkle//dane.txt"));
        int xml = 1;
        while (scan.hasNextLine()) {
            Scanner sc = new Scanner(scan.nextLine());
                ArrayList<String> tmpAL = new ArrayList<String>();
                while (sc.hasNext()) {
                    tmpAL.add(sc.next());
                }
                Pattern pat = Pattern.compile("\\d{1,2}-\\d{1,2}-\\d{4}");
                Matcher matcher;
                for (int i = 0; i < tmpAL.size(); i++) {
                    String[] str = tmpAL.get(i).split(",");
                    tmpAL.remove(i);
                    tmpAL.add(i , str[0]);
                    matcher = pat.matcher(tmpAL.get(i));
                    if (matcher.matches()) {
                        String[] tmp = tmpAL.get(i).split("-");
                        tmpAL.add(tmp[0]);
                        tmpAL.add(tmp[1]);
                        tmpAL.add(tmp[2]);
                    }
                }
            PrintWriter writer = null;
                try {
                    switch (tmpAL.size()) {
                        case 7:
                            int r = Integer.parseInt(tmpAL.get(2));
                            int d = Integer.parseInt(tmpAL.get(4));
                            int m = Integer.parseInt(tmpAL.get(5));
                            int y = Integer.parseInt(tmpAL.get(6));
                            ListaPojazdow.add(new Pojazd(tmpAL.get(0), tmpAL.get(1), r, y, m, d));
                            System.out.println("test sozdal Pojazd");
                        break;
                        case 15:
                            int r1 = Integer.parseInt(tmpAL.get(2));
                            int[] tab = new int[9];
                            int j = 0;
                            for (int i = 6; i <= 14; i++) {
                                tab[j] = Integer.parseInt(tmpAL.get(i));
                                j++;
                            }
                            ListaPojazdow.add(new Samochod(tmpAL.get(0), tmpAL.get(1), r1, tab[2], tab[1], tab[0], tab[5], tab[4], tab[3], tab[8], tab[7], tab[6]));
                            System.out.println("test sozdal Samochod");
                            break;
                        case 19:
                            int r2 = Integer.parseInt(tmpAL.get(2));
                            int[] tabt = new int[12];
                            int x = 0;
                            for (int i = 7; i <= 18; i++) {
                                tabt[x] = Integer.parseInt(tmpAL.get(i));
                                x++;
                            }
                            ListaPojazdow.add(new Taksowka(tmpAL.get(0), tmpAL.get(1), r2, tabt[2], tabt[1], tabt[0], tabt[5], tabt[4], tabt[3], tabt[8], tabt[7], tabt[6], tabt[11], tabt[10], tabt[9]));
                            System.out.println("test sozdal Taksi");
                            break;
                    }
                }catch(Exception ex){
                    System.out.println(ex);
                    try {
                        writer = new PrintWriter("blendy.txt", "UTF-8");
                        writer.println("W linji " + xml + " blendnie podana data");
                    if(ex.equals("Blendnie podana Data Rejestracji Samochodu")) {
                            writer.println("ex");
                    }
                    if(ex.equals("Blendnie podana Data ostatniegopreglondu samochodu")){
                            writer.println("ex");
                    }
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                    writer.close();
                }

            tmpAL.clear();
            xml++;
        }
        raport();
    }
//========================================================================================================================================================================================
        public static void raport(){
        PrintWriter writer;
        try {
            writer = new PrintWriter("pojazdy.txt", "UTF-8");
            writer.println("Raport (" + LocalDate.now() + ")");
            boolean[] test = {true, true, true};
            for (Pojazd p : ListaPojazdow) {
                writer.println("\nMarka: " + p.marka);
                writer.println("Model: " + p.model);
                writer.println("Rok produkcji: " + p.RokProdukcji);
                writer.println("Data rejestracji: " + p.DataRejestracji.getDayOfMonth() + " " + p.DataRejestracji.getMonth() + " " + p.DataRejestracji.getYear() + " (" + p.DataRejestracji.getDayOfWeek() + ")");
                if (p instanceof Samochod) {
                    writer.println("Data ostatniego przeglądu: " + ((Samochod) p).DataOstatniegoPrzeglondu.getDayOfMonth() + " " + ((Samochod) p).DataOstatniegoPrzeglondu.getMonth() + " " + ((Samochod) p).DataOstatniegoPrzeglondu.getYear() + " (" + ((Samochod) p).DataOstatniegoPrzeglondu.getDayOfWeek() + ")");
                    writer.println("Data ważności ubezpieczenia: " + ((Samochod) p).DataWaznosciUbezpieczenia.getDayOfMonth() + " " + ((Samochod) p).DataWaznosciUbezpieczenia.getMonth() + " " + ((Samochod) p).DataWaznosciUbezpieczenia.getYear() + " (" + ((Samochod) p).DataWaznosciUbezpieczenia.getDayOfWeek() + ")");
                    test[0] = p.czyPosiadaWaznyPrzyglond();
                    test[1] = p.czyWazneUbiezpieczenie();
                    if (p instanceof Taksowka) {
                        writer.println("Data ważności taksometru: " + ((Taksowka) p).DataWaznosciTaksometru.getDayOfMonth() + " " + ((Taksowka) p).DataWaznosciTaksometru.getMonth() + " " + ((Taksowka) p).DataWaznosciTaksometru.getYear() + " (" + ((Taksowka) p).DataWaznosciTaksometru.getDayOfWeek() + ")");
                        test[2] = p.czyWaznyTaksometr();
                        if (test[0] && test[1] && test[2]) {
                            writer.println("Taksówka dopuszczona do ruchu");
                        } else {
                            writer.println("Taksówka niedopuszczona do ruchu");
                        }
                    } else {
                        if (test[0] && test[1] && test[2]) {
                            writer.println("Samochod dopuszczony do ruchu");
                        } else {
                            writer.println("Samochod niedopuszczony do ruchu");
                        }
                    }

                } else {
                    if (test[0] && test[1] && test[2]) {
                        writer.println("Pojazd dopuszczony do ruchu");
                    } else {
                        writer.println("Pojazd niedopuszczony do ruchu");
                    }
                }
            }
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
