//DO NOT CHANGE THIS PACKAGE
package coursework3;

import cw3interfaces.CommunityGroupInterface;
import java.util.ArrayList;
import java.util.ListIterator;
import javax.swing.JOptionPane;


//DO NOT CHANGE THIS NAME
public class CommunityGroup implements CommunityGroupInterface {
    private ArrayList<Volunteer> VolunteerList = new ArrayList<>();
    private String name;
    private boolean full;

    
    
 //COMPLETE THIS CLASS    
    public CommunityGroup(String string){
        this.name = string;
        this.full = false;
    }
 //these public methods need to form the interface 
// DO NOT CHANGE ANY OF THESE METHOD NAMES, RETURN VALUES, OR ARGUMENTS
    

    
    public String getName(){
        return this.name;
    }
    
    public boolean getFull(){
        return this.full;
    }
    
    public ArrayList<Volunteer> getVolunteerList(){
        return this.VolunteerList;
    }
    public void addVolunteer(Volunteer vol){
        if (VolunteerList.size() < 500){        // check whether the group is full or not
            VolunteerList.add(vol);
        }
        else{
            full = true;
        }
    }
    public void removeVolunteer(Volunteer vol){
        VolunteerList.remove(vol);
    }
    
    @Override
    public int howManyVolunteers(){
        //return the total number of volunteers in this community group
        //COMPLETE CODE HERE
        return this.VolunteerList.size();
    }
    
    @Override
    public String getSkillsTotals(){
        // return the total number of each skill in a String, example:
        //Skill A: 13, Skill B: 20, Skill C: 23, Skill D: 5, Skill E: 41
        //COMPLETE CODE HERE
        String SkillsTotals = " Skill A: " + this.getSkillsList()[0] + "  Skill B: " + this.getSkillsList()[1] + "  Skill C: " + this.getSkillsList()[2] + "  Skill D: "
                + this.getSkillsList()[3] + "  Skill E: " + this.getSkillsList()[4] + "  Total: " + this.howManyVolunteers();
        return SkillsTotals;
    }
            
    public int[] getSkillsList(){
        /*
        return a list of number which corresponding to the number of each letter, example:
         A   B   C   D   E
        [11, 44, 54, 10, 41]
        */
        int[] SkillsList = new int[5];
        int counterA = 0;
        int counterB = 0;
        int counterC = 0;
        int counterD = 0;
        int counterE = 0;
        ListIterator<Volunteer> lit = this.getVolunteerList().listIterator();
        Volunteer vol = null;
        while (lit.hasNext()){
            vol = lit.next();
            String SkillSet = vol.getSkillSet();
            for (int j = 0; j < SkillSet.length(); j++){
                if (SkillSet.charAt(j) == 'A'){
                    counterA ++;
                }
                else if (SkillSet.charAt(j) == 'B'){
                    counterB ++;
                }
                else if (SkillSet.charAt(j) == 'C'){
                    counterC ++;
                }
                else if (SkillSet.charAt(j) == 'D'){
                    counterD ++;
                }
                else if (SkillSet.charAt(j) == 'E'){
                    counterE ++;
                }
            }
        }
        SkillsList[0] = counterA; SkillsList[1] = counterB; SkillsList[2] = counterC; SkillsList[3] = counterD; SkillsList[4] = counterE;
        return SkillsList;
    }
    
    public ArrayList<String> volsSkill(){
        /*
        return a list of skills which cover the whole group, example:
        if group1 has AAA AAA BBB BBB four volunteers,
        this list is [AAA, BBB]
        */
        ArrayList<String> volskill = new ArrayList<>();
        for (Volunteer vol: this.getVolunteerList()){
            boolean condition = true;
            for (String string: volskill){
                if(vol.getSkillSet().equals(string)){
                    condition = false;
                }
            }
            if (condition){
                volskill.add(vol.getSkillSet());
            }
        }
        return volskill;
        
    }
    
    
    
    

}
