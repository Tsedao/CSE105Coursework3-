//DO NOT CHANGE THIS PACKAGE
package coursework3;

import cw3interfaces.VolunteerInterface;



//DO NOT CHANGE THIS NAME
public class Volunteer implements VolunteerInterface {
    private String Skill;

    
//COMPLETE THIS CLASS      
    public Volunteer(String string){
        this.Skill = string;
    }
//these public methods need to form the interface 
// DO NOT CHANGE ANY OF THESE METHOD NAMES, RETURN VALUES, OR ARGUMENTS   
    @Override
    public String getSkillSet(){
        //COMPLETE CODE HERE
        //returns a String of this volunteers skills, eg BBB, ABC, CDD etc
        return Skill;
    }
    
}
