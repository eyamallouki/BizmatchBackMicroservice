package com.esprit.Bizmatch.Forum.Services;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

@RequiredArgsConstructor
public class BadWords {
    private static final Logger log = LoggerFactory.getLogger(BadWords.class);
    //@Value("${config.file.path}")
    //private static String file ;


    public static boolean verfiyWord(String contents) throws IOException {

        Throwable var2 = null;
        Object var3 = null;

        //Le code est placé dans un bloc try pour intercepter les exceptions.
        try {



            Scanner scanner = new Scanner(new File("C:/Users/HP/PIDev_Mobility_SpringBoot/src/main/java/tn/esprit/pithepowerplayers/utile/word.txt"));

            try {
                while(scanner.hasNext()) { // lire tq il ya  des lignes
                    String line = scanner.nextLine();
                    String[] words = contents.split(" ");  //Découpe la chaîne contents en un tableau de mots
                    String[] var10 = words;
                    int var9 = words.length;

                    //Pour chaque mot w du tableau words, vérifie si la ligne courante line contient le mot
                    for(int var8 = 0; var8 < var9; ++var8) {
                        String w = var10[var8];
                        if (line.toUpperCase().contains(w.toUpperCase())) {
                            log.info("bad word");
                            return true;
                        }
                    }
                }

                return false;  //si aucun mot interdit n'est trouvé
            } finally {
                if (scanner != null) {
                    scanner.close();
                }

            }
        } catch (Throwable var16) {
            if (var2 == null) {
                var2 = var16;  //Si var2 est null, il est initialisé avec l'exception
            } else if (var2 != var16) {
                var2.addSuppressed(var16);
            }

            throw new RuntimeException("exepction lors de la lecture du fichier texte"+ var2);
        }
    }
}
