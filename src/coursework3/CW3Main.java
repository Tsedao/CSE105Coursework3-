
package coursework3;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;
import javax.swing.*;

/*READ ME: Use this class as your main class, and create your menu here
Your menu should then call the appropriate methods in the SkillSorter class
You need to complete the other classes, including the empty methods.
/*
*/
public class CW3Main implements ActionListener{
    
    private CommunityGroup group1 = new CommunityGroup("Group1");
    private CommunityGroup group2 = new CommunityGroup("Group2");
    private CommunityGroup group3 = new CommunityGroup("Group3");
    private CommunityGroup group4 = new CommunityGroup("Group4");
    private CommunityGroup group5 = new CommunityGroup("Group5");
    private File file1;
    private File file2;
    private File file3;
    private File file4;
    private File file5;
    private static JFrame frame;
    private static JPanel myPanel;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JButton button6;
    private JButton button7;
    private JLabel label1; 
    SkillSorter skillsorter = new SkillSorter(group1,group2,group3,group4,group5);
    
    public static void main(String[] args){
        CW3Main cw3 = new CW3Main();
        frame = new JFrame("CommunityGroups Management System");
        frame.setLocation(500, 200);
        frame.setSize(860, 480);
        frame.setContentPane(myPanel);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
    
    }
    
    public CW3Main(){
        init();
    }
    
    //Construct and run your menu here.
    //You MUST call methods in SkillSorter from your menu
    //and complete the methods in SkillSorter 
    //DO NOT write the methods, eg addVolunteer, in THIS class.
    //Call and use the ones in SkillSorter.
    
    
    public void init(){
        /**
         * initiate the varible
         */
        Font font1 = new Font("Tahoma",Font.BOLD, 20);
        file1 = new File("Group1.txt");
        file2 = new File("Group2.txt");
        file3 = new File("Group3.txt");
        file4 = new File("Group4.txt");
        file5 = new File("Group5.txt");
        this.loadData(file1, group1);
        this.loadData(file2, group2);
        this.loadData(file3, group3);
        this.loadData(file4, group4);
        this.loadData(file5, group5);
        myPanel = new JPanel();
        myPanel.setLayout(null);
        button1 = new JButton("Add a Volunteer");
        button2 = new JButton("Move a Volunteer");
        button3 = new JButton("Delete a Volunteer");
        button4 = new JButton("Delete all Volunteers");
        button5 = new JButton("Display all Groups");
        button6 = new JButton("Save and Exit");
        //button7 = new JButton("Add 500 Volunteers (test)");
        label1 = new JLabel("Welcome, please make a choice:");
        button1.addActionListener(this);
        button2.addActionListener(this);
        button3.addActionListener(this);
        button4.addActionListener(this);
        button5.addActionListener(this);
        button6.addActionListener(this);
        //button7.addActionListener(this);
        myPanel.add(button1);
        myPanel.add(button2);
        myPanel.add(button3);
        myPanel.add(button4);
        myPanel.add(button5);
        myPanel.add(button6);
        //myPanel.add(button7);
        myPanel.add(label1);
        button1.setBounds(50, 110, 200, 100);
        button2.setBounds(320, 110, 200, 100);
        button3.setBounds(590, 110, 200, 100);
        button4.setBounds(50, 250, 200, 100);
        button5.setBounds(320, 250, 200, 100);
        button6.setBounds(590, 250, 200, 100);
        //button7.setBounds(590, 400, 200, 20);
        label1.setBounds(50, 5, 400, 100);
        label1.setFont(font1);
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
            String buttonName = e.getActionCommand();
            
            if (buttonName.equals("Add a Volunteer")){                     //if the "Add a Volunteer" is clicked
                String skill = JOptionPane.showInputDialog(null,"Please input the skill of the volunteer you want to add\n","Add a volunteer",JOptionPane.PLAIN_MESSAGE);
                try{                                                       // check whether skill is null not
                    String skillvalid = this.validateVolunteer(skill);     // validate the input of skill and return a valid one
                    skillvalid = this.permutation(skillvalid);             // permutate the skill in the alphebet order
                    Volunteer vol = new Volunteer(skillvalid);             // instantiate the volunteer
                    skillsorter.addVolunteer(vol);                         // add the volunteer
                }catch(NullPointerException event){
                    
                }
            }
            
            else if(buttonName.equals("Move a Volunteer")){
                
                boolean condition3 = false;
                String moveskillvalid = null;
                String moveskill = null;
                
                Object[] obj2 ={"Group1", "Group2", "Group3","Group4","Group5"};
                String groupfrom = (String) JOptionPane.showInputDialog(null,"From which group do you want to move the volunteer?\n", //get the group input
                                    "Move a volunteer", JOptionPane.INFORMATION_MESSAGE, null, obj2, obj2[0]);
                if (groupfrom != null){                                                                                                 
                    CommunityGroup fromgroup = this.StringToGroup(groupfrom);                                                          //convert String groupfrom to CommuityGroup fromGroup
                    while (!condition3){
                        moveskill = JOptionPane.showInputDialog(null,this.DisPlayVol(fromgroup)+"\n" 
                                +"What's the skill of the volunnteer you want to move from this group?\n","Check volunteer",JOptionPane.PLAIN_MESSAGE);
                        try{                                                                                                            //check whether moveskill is null not
                            moveskillvalid = this.validateVolunteer(moveskill,fromgroup, 0);                                            // validate the volunteer input and return a string of valid skill
                            moveskillvalid = this.permutation(moveskillvalid);                                                          // permutate the skill in the alphebet order
                            condition3 = this.CheckVolInGroup(moveskillvalid, fromgroup);                                               //check whether fromgroup has the input skill, if it not it will continue this loop
                        }catch(NullPointerException event){                                                                             //if it does have it will break the loop
                            break;                                                                                  
                        }
                    }
                    if (condition3){                                                                                                    //if the validskill is in the fromgroup
                        String groupto = (String) JOptionPane.showInputDialog(null,"To which group do you want to move the volunteer?\n", //get the group input
                                "Move a volunteer", JOptionPane.INFORMATION_MESSAGE, null, obj2, obj2[0]);
                        if (groupto != null){                                                                                         //check whether group is null or not
                            CommunityGroup togroup = this.StringToGroup(groupto);                                                     //convert String groupfrom to CommuityGroup fromGroup
                            this.skillsorter.moveVolunteer(moveskillvalid, fromgroup, togroup);                                     //excutive the movement 
                        }
                    }
                }
            }
            else if(buttonName.equals("Delete a Volunteer")){
                
                boolean condition = false;
                String deleteskill = null;
                String deleteskillvalid = null;
                Object[] obj2 ={"Group1", "Group2", "Group3","Group4","Group5"};
                
                String groupfrom = (String) JOptionPane.showInputDialog(null,"From which group do you want to delete the volunteer?\n",   //get the group input
                        "Delete a volunteer", JOptionPane.INFORMATION_MESSAGE, null, obj2, obj2[0]);
                if (groupfrom != null){                                                                                                   //check the group is null or not
                    CommunityGroup fromgroup = this.StringToGroup(groupfrom);                                                             //convert the datatype from String to CommunityGroup
                    while (!condition){                                                                                                   //while the group does not have the skill
                        deleteskill = JOptionPane.showInputDialog(null,this.DisPlayVol(fromgroup)+"\n" +                                //get the skill input
                                "What's the skill of the volunteer you want to delete from this group?\n","Check volunteer",
                                JOptionPane.PLAIN_MESSAGE);                                                                               
                        try{                                                                                                            //check whether moveskill is null not
                            deleteskillvalid = this.validateVolunteer(deleteskill,fromgroup, 1);                                        //validate the volunteer input and return a string of valid skill
                            deleteskillvalid = this.permutation(deleteskillvalid);                                                      //permutate the skill in the alphebet order
                            condition = this.CheckVolInGroup(deleteskillvalid, fromgroup);                                          //check whether fromgroup has the input skill, if it not it will continue this loop
                        }catch(NullPointerException event){                                                                         //if it does have it will break the loop
                            break;                                                                                                  //if no input in the blank, it will break the loop also
                        }
                    }
                    if (condition){
                        this.skillsorter.deleteVolunteer(deleteskillvalid, fromgroup);                                              //delete the volunteer
                    }
                }                            
            }
            
            else if(buttonName.equals("Delete all Volunteers")){
                int n = JOptionPane.showConfirmDialog(null, "Are you sure to remove all the volunteers", "Confirmation",JOptionPane.YES_NO_OPTION);
                if (n == 0){
                    skillsorter.deleteAllVolunteers();        
                }
                
            }
            
            else if(buttonName.equals("Display all Groups")){
                Table myTable = new Table();                                //intantiate the table
                this.loadToTable(myTable.getLine1(), group1);               //load group1's data to the table in line1
                this.loadToTable(myTable.getLine2(), group2);               //load group2's data to the table in line2
                this.loadToTable(myTable.getLine3(), group3);               //load group3's data to the table in line3
                this.loadToTable(myTable.getLine4(), group4);               //load group4's data to the table in line4
                this.loadToTable(myTable.getLine5(), group5);               //load group5's data to the table in line5
                myTable.setVisible(true);
            }
            
            else if(buttonName.equals("Save and Exit")){
                int n = JOptionPane.showConfirmDialog(null, "Are you sure to save it and exit the programme?", "Confirmation",JOptionPane.YES_NO_OPTION);
                if (n == 0){
                    FileHandler.WriteVolunteer(this.group1.getVolunteerList(), file1);     
                    FileHandler.WriteVolunteer(this.group2.getVolunteerList(), file2);
                    FileHandler.WriteVolunteer(this.group3.getVolunteerList(), file3);
                    FileHandler.WriteVolunteer(this.group4.getVolunteerList(), file4);
                    FileHandler.WriteVolunteer(this.group5.getVolunteerList(), file5);
                    JOptionPane.showMessageDialog(null, "All the data has been written to: \n" + file1.getPath() + "  " + file2.getPath() + "  " + file3.getPath() + "  " + file4.getPath() + " and "
                                                + file5.getPath() + " successfully");
                    System.exit(0);
                }
            }
            
            else if(buttonName.equals("Add 500 Volunteers (test)")){
                for (int i = 0; i < 500; i++){
                    Volunteer vol7 = new Volunteer(permutation(randomSkill()));    //permutate the skill first
                    this.skillsorter.addVolunteer(vol7);
                }
            }
        }
    
    private CommunityGroup StringToGroup(String string){
        /**
         * This method is to convert the datatype from String to CommunityGroup 
         */
        CommunityGroup fromgroup = null;
        for (CommunityGroup group: skillsorter.getCommunityGroups()){
            if (group.getName().equals(string)){
                fromgroup = group;
            }
        }
        return fromgroup;
    }
    
    private String DisPlayVol(CommunityGroup group){
        /**
         * return a string like: Volunteers available in this group are: AAB BBC
         * AAB BBC include all the possible skills in this group
         */
        String string1 = "Volunteers available in this group are: ";
        for (String string2: group.volsSkill()){
            string1 += string2;
            string1 += " ";
        }
        return string1;
    }
    
    private String validateVolunteer(String string){
        /**
         * this is also a recursivly defined method, it is aimmed to validate the input and then return the valid input
         * for example, if the input for a volunteer's skill is AFG, then this method will ask you to input again
         */
        boolean condition = true;
        if (string.length() != 3){
            condition = false;
        }
        for (int i = 0; i<string.length(); i++){
            char cha = string.charAt(i);
            if (cha != 'A' && cha != 'B' && cha != 'C' && cha != 'D' && cha != 'E'){
                condition = false;
            }
        }
        if (!condition){
            JOptionPane.showMessageDialog(null, "Invalid input, please try again (like: \"AAA\")", "Warning",JOptionPane.WARNING_MESSAGE);
            String skill = JOptionPane.showInputDialog(null,"Please input the skill of the volunteer you want to add\n","Add a volunteer",JOptionPane.PLAIN_MESSAGE);
            return validateVolunteer(skill);
        }
        return string;
    }
    
    private String validateVolunteer(String string, CommunityGroup group, int num){
        /**
         * this method is overloading the former method
         * and it is used to open 2 types of joptionpane depends on the integer number
         */
        String skill;
        boolean condition = true;
        if (string.length() != 3){
            condition = false;
        }
        for (int i = 0; i<string.length(); i++){
            char cha = string.charAt(i);
            if (cha != 'A' && cha != 'B' && cha != 'C' && cha != 'D' && cha != 'E'){
                condition = false;
            }
        }
        if (!condition){
            JOptionPane.showMessageDialog(null, "Invalid input, please try again (like: \"AAA\")", "Warning",JOptionPane.WARNING_MESSAGE);
            if (num == 0){
                skill = JOptionPane.showInputDialog(null,this.DisPlayVol(group)+"\n" +"What's the skill of the volunnteer you want to move in this group?\n","Check volunteer",JOptionPane.PLAIN_MESSAGE);
            }
            else{
                skill = JOptionPane.showInputDialog(null,this.DisPlayVol(group)+"\n" +"What's the skill of the volunnteer you want to delete in this group?\n","Check volunteer",JOptionPane.PLAIN_MESSAGE);
            }
            return validateVolunteer(skill, group, num);
        }
        return string;
    }
    
    public static String permutation(String string){        
     /** 
      * this method is very important!!!!!!   
      * Every volunteer's skill should first be permutated in alphebet order
      * then the volunteer can be added to the groups
      * this method is used to permutate valid skill in the alphebet order
      * for example change CBA to ABC
      */
        StringBuffer sb = new StringBuffer();
        char[] charlist = string.toCharArray();
        for (int i = 0; i<charlist.length; i++){
            if (charlist[i] == 'A'){
                sb.append("A");
            }
        }
        for (int i = 0; i<charlist.length; i++){
            if (charlist[i] == 'B'){
                sb.append("B");
            }
        }
        for (int i = 0; i<charlist.length; i++){
            if (charlist[i] == 'C'){
                sb.append("C");
            }
        }
        for (int i = 0; i<charlist.length; i++){
            if (charlist[i] == 'D'){
                sb.append("D");
            }
        }
        for (int i = 0; i<charlist.length; i++){
            if (charlist[i] == 'E'){
                sb.append("E");
            }
        }
        String out = sb.toString();
        return out;
    }
    
    private static String randomSkill(){
        /*
        this method is to generate the skill of a volunteer randomly
        */
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i<3; i++){
            int random = (int) (Math.random()*5 + 1);
            if (random == 1){
                sb.append("A");
            }
            else if (random == 2){
                sb.append("B");
            }
            else if (random == 3){
                sb.append("C");
            }
            else if (random == 4){
                sb.append("D");
            }
            else{
                sb.append("E");
            }
        }
        return sb.toString();
    }
    
    private boolean CheckVolInGroup(String skill, CommunityGroup group){
        /*
        this method is to check whether a group have a specific skill or not
        */
        boolean condition = false;
        for (String string: group.volsSkill()){
            if (string.equals(skill)){
                condition = true;
                return true;
            }
        }
        if (!condition){
            JOptionPane.showMessageDialog(null, "No such volunteer with skill " + skill + " found in " + group.getName() + ", please try again", "Warning",JOptionPane.WARNING_MESSAGE);
        }
        return false;
    }
      
    private void loadData(File file, CommunityGroup group){
        /**
         * this method is used to load historical data from files to groups
         */
        ArrayList<Volunteer> vols = FileHandler.ReadFile(file);
        for (Volunteer vol: vols){
            group.addVolunteer(vol);
        } 
    }
    
    private void loadToTable(Vector vector, CommunityGroup group){
        /**
         * this method is used to load group's volunteer to table
         */
        for (int i = 0; i<5; i++){
            vector.set(i+1, group.getSkillsList()[i]);
        }
        vector.set(6, group.howManyVolunteers());
    }
  
}
