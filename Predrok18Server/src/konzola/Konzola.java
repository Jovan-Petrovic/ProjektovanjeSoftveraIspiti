/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package konzola;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import logika.Kontroler;
import niti.PokretanjeServera;
import pomocne.PomocnaKlasa;

/**
 *
 * @author Bron Zilar
 */
public class Konzola {
    
    static PokretanjeServera ps;
    
    public static void main(String[] args) throws IOException {
        
        while(true) {
        
            System.out.println("*** Glavni meni ***");
            System.out.println("1. pokreni server ");
            System.out.println("2. zaustavi server ");
            System.out.println("3. filtriraj po pravnom/fizickom licu ");
            System.out.println("4. kraj rada ");

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int broj = 0;
            try {
                broj = Integer.parseInt(br.readLine());
            } catch (IOException ex) {
                Logger.getLogger(Konzola.class.getName()).log(Level.SEVERE, null, ex);
            }
        
            switch(broj) {
                case 1:
                    if(ps == null || !ps.isAlive()) {
                        ps = new PokretanjeServera();
                        ps.start();
                    }
                    break;
                case 2:
                    if(ps != null && ps.getSs().isBound()) {
                        ps.zaustaviServerskuNit();
                    }
                    break;
                case 3:
                    System.out.println("*** Unesite filter ***");
                    BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));
                    String filter = br1.readLine();
                    if(!filter.equalsIgnoreCase("pravno") && !filter.equalsIgnoreCase("fizicko")) {
                        System.out.println("Morate uneti pravno ili fizicko. pokusajte ponovo. ");
                        BufferedReader br2 = new BufferedReader(new InputStreamReader(System.in));
                        filter = br2.readLine();
                    }
                    ArrayList<PomocnaKlasa> lista = Kontroler.getInstanca().vratiZaServer(filter);
                    srediKonzolu(lista);
                    break;
            }
        }
    }

    private static void srediKonzolu(ArrayList<PomocnaKlasa> lista) {
        System.out.format("%16s%32s%16s%16s", "Obveznik ID", "Naziv", "Vrednost","Dug");
        System.out.println("");
        for (PomocnaKlasa pk : lista) {
            //System.out.println(pk.getObveznikID() + "    " + pk.getIme() + "    " + pk.getGodina() + "    " + pk.getDug());
            System.out.format("%16s%32s%16s%16s", pk.getObveznikID(), pk.getIme(), pk.getGodina(), pk.getDug());
            System.out.println("");
        }
    }
}
