package coursework3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ListIterator;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author acer
 */
public class FileHandler {
    public FileHandler(){
        
    }
    
    public static ArrayList<Volunteer> ReadFile(File file){
        /*
        this method is used to read the string from the file and use this string to instantiate the volunteer class
        */
        ArrayList<Volunteer> vols = new ArrayList<>();
        String line = "";
        try {
            BufferedReader fr = new BufferedReader(new FileReader(file));
            while ( (line = fr.readLine() ) != null) {        // read next line if it exists
                line = CW3Main.permutation(line);
                Volunteer vol = new Volunteer(line);
                vols.add(vol);
            }
            fr.close();     // close file
        }catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error reading file", "Error",JOptionPane.WARNING_MESSAGE); 
        }
        return vols;
    }
    
    public static void WriteVolunteer(ArrayList<Volunteer> vollist, File file){
        /*
        this method is to write the volunteer object to a specific file
        */
        try {
            BufferedWriter fw = new BufferedWriter(new FileWriter(file, false));
            ListIterator<Volunteer> lit = vollist.listIterator();
            Volunteer vol;
            while(lit.hasNext()){
                vol = lit.next();
                fw.write(vol.getSkillSet());
                fw.newLine();
                System.out.println("String written to file");
            }
            fw.flush();       
            fw.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error writing to file", "Error",JOptionPane.WARNING_MESSAGE); 
        }

    }
}
