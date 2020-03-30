/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package konzola;

import domen.Prevod;
import domen.Rec;
import domen.Recnik;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import logika.Kontroler;
import niti.PokretanjeServera;

/**
 *
 * @author Bron Zilar
 */
public class Konzola {
    public static void main(String[] args) {
        PokretanjeServera ps = new PokretanjeServera();
        ps.start();
        
        srediKonzolu();
    }

    private static void srediKonzolu() {
        ArrayList<Recnik> recnici = Kontroler.getInstanca().vratiRecnike();
        System.out.println("Izaberite jezik: ");
        for(int i = 0; i < recnici.size(); i++) {
            System.out.println(recnici.get(i).getJezik() + " - " + (i+1));
        } 
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        int broj = 0;
        try {
            broj = Integer.parseInt(br.readLine());
        } catch (IOException ex) {
            Logger.getLogger(Konzola.class.getName()).log(Level.SEVERE, null, ex);
        }
        ArrayList<Rec> reci = Kontroler.getInstanca().vratiReciUslov(broj);
        
//        ispisiSve(reci);
        
        for(Rec rec : reci) {
            ArrayList<Prevod> sortiraniPrevodi = sortirajPrevode(rec.getPrevodi());
            rec.setPrevodi(sortiraniPrevodi);
        }
        
        ispisiSve(reci);

//        ispisiZaglavlje();
//        ispisiOstatak(reci);
    }

    private static void ispisiSve(ArrayList<Rec> reci) {
        for (Rec rec : reci) {
            System.out.println("Rec: "+rec);
            for(Prevod p : rec.getPrevodi()) {
                System.out.println("\t\tPrevod: " + p + "\t\tNa jezik: " + p.getNaJezik());
            }
        }
    }

    private static ArrayList<Prevod> sortirajPrevode(ArrayList<Prevod> prevodi) {
        ArrayList<Recnik> recnici = Kontroler.getInstanca().vratiRecnike();
        ArrayList<Prevod> sortiraniPrevodi = new ArrayList<>();
//        while(sortiraniPrevodi.size()<prevodi.size()) {
//            for (Recnik recnik : recnici) {
//                for (Prevod prevod : prevodi) {
//                    if(prevod.getNaJezik().equals(recnik) && !sortiraniPrevodi.contains(prevod)) {
//                        sortiraniPrevodi.add(prevod);
//                        break;
//                    }
//                }
//            }
//        }
        while(sortiraniPrevodi.size()<prevodi.size()) {
            for (int i = 0; i < recnici.size(); i++) {
                for (int j = 0; j < prevodi.size(); j++) {
                    if(prevodi.get(j).getNaJezik().equals(recnici.get(i)) && !sortiraniPrevodi.contains(prevodi.get(j))) {
                        prevodi.get(j).setRed(i);
                        sortiraniPrevodi.add(prevodi.get(j));
                        break;
                    }
                }
            }
        }
        return sortiraniPrevodi;
    }

    private static void ispisiZaglavlje() {
        ArrayList<Recnik> recnici = Kontroler.getInstanca().vratiRecnike();
        System.out.print("Rec\t\t");
        for (Recnik recnik : recnici) {
            System.out.print(recnik.getJezik() + "\t\t");
        }
        System.out.println("");
        System.out.println("-------------------------------------------------------------------------------");
    }

    private static void ispisiOstatak(ArrayList<Rec> reci) {
        
    }

    
}

    

    