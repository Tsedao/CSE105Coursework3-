//DO NOT CHANGE THIS PACKAGE
package coursework3;

import cw3interfaces.SkillSorterInterface;
import java.util.ArrayList;
import java.util.ListIterator;
import javax.swing.JOptionPane;




//DO NOT CHANGE THIS NAME
public class SkillSorter implements SkillSorterInterface{
    private ArrayList<CommunityGroup> myGroups = new ArrayList<>();
    
    //COMPLETE THIS CLASS
    public SkillSorter(CommunityGroup V,CommunityGroup W,CommunityGroup X,CommunityGroup Y,CommunityGroup Z){
        myGroups.add(V);
        myGroups.add(W);
        myGroups.add(X);
        myGroups.add(Y);
        myGroups.add(Z);
    }
    
    
//these public methods need to form the interface 
// DO NOT CHANGE ANY OF THESE METHOD NAMES, RETURN VALUES, OR ARGUMENTS
    @Override
    public void addVolunteer(Volunteer vol){
        //add a volunteer to a Community Group USING YOUR SORTING ALGORITHM
        /**
         * In this method, we do five trials which means we put a volunteer in  group1 first
         * and then calculate its varSum, the sum of the varience of total and the sum of varience of the number of different skills from different groups
         * then store the varSum in a list, and remove this volunteer from group1, then put this volunteer in group2 and do these things again
         * After 5 trails, we can achieve a five length list, we check the smallest one and find its index i, 
         * Therefore, its sumVar is smallest, this means if we put the volunteer into this group, it will cause less varience
         */
        double[] varSumList = new double[5];
        for (int i = 0; i< myGroups.size(); i++){
            myGroups.get(i).addVolunteer(vol);                                       
            double varSum = 0;
            varSum += this.VarForSkills(myGroups);     
            varSum += this.VarForTotal(myGroups);
            varSumList[i] = varSum;                                                 //here we get a list of sum of variance
            myGroups.get(i).removeVolunteer(vol);
        }
                                    
        double[] smallestIndexAndNum = this.smallest(varSumList, 0);
        int smallestIndex = (int) smallestIndexAndNum[0];                           //we find the index of the smallest sum
        double smallest = smallestIndexAndNum[1];                                                       

        double[] secsmallestIndexAndNum = this.smallest(varSumList, smallest);
        int secsmallestIndex = (int) secsmallestIndexAndNum[0];                     // the index of the second smallest sum
        double secsmallest = secsmallestIndexAndNum[1];
        
        double[] thrsmallestIndexAndNum = this.smallest(varSumList, secsmallest);
        int thrsmallestIndex = (int) thrsmallestIndexAndNum[0];                     //the index of the third smallest sum
        double thrsmallest = thrsmallestIndexAndNum[1];
        
        double[] forsmallestIndexAndNum = this.smallest(varSumList, thrsmallest);
        int forsmallestIndex = (int) forsmallestIndexAndNum[0];                     //the index of the forth smallest sum
        double forsmallest = forsmallestIndexAndNum[1];
        
        double[] fifsmallestIndexAndNum = this.smallest(varSumList, forsmallest);
        int fifsmallestIndex = (int) fifsmallestIndexAndNum[0];                     //the index of the fifth smallest sum
        
        CommunityGroup thisgroup1 = myGroups.get(smallestIndex);                    //get the group with the smallest sum of var
        CommunityGroup thisgroup2 = myGroups.get(secsmallestIndex);                 //get the group with the second smallest sum of var
        CommunityGroup thisgroup3 = myGroups.get(thrsmallestIndex);                 //get the group with the third smallest sum of var
        CommunityGroup thisgroup4 = myGroups.get(forsmallestIndex);                //get the group with the forth smallest sum of var
        CommunityGroup thisgroup5 = myGroups.get(fifsmallestIndex);                //get the group with the fifth smallest sum of var
        
        if(this.tryaddvol(thisgroup1, vol)){            // try to add the vol from thisgroup1 which has the smallest variance to thisgroup5
                                                        //which has the biggest variance
        }
        else if (this.tryaddvol(thisgroup2, vol)){
            
        }
        else if (this.tryaddvol(thisgroup3, vol)){
            
        }
        else if (this.tryaddvol(thisgroup4, vol)){
            
        }
        else if (this.tryaddvol(thisgroup5, vol)){
            
        }
        else{                                               // if all the above is false, then all the groups are full
            JOptionPane.showMessageDialog(null, "All the group is full", "Warning",JOptionPane.WARNING_MESSAGE);
        }
        
    }
    
    public boolean tryaddvol(CommunityGroup group, Volunteer vol){
        /*
        this method is to try whether the group is full or not, if it is not full, then add a vol to this group
        */
        if(group.getFull()){
            return false;
        }
        group.addVolunteer(vol);
        JOptionPane.showMessageDialog(null, "Volunteer with skill " + vol.getSkillSet() + " is successfully added to " + group.getName());
        return true;
    }
    
    public double[] smallest(double[] list, double compare){
        /*
        this method is for calculate the ralative small value bigger than compare in the double list 
        */
        double[] IndexAndNum = new double[2];
        double smallest = 100000; 
        int index = 0;
        for (int i = 0; i<list.length; i++){
            if (list[i] <= smallest && list[i] > compare){                  // if the number is smaller than or equal to the smallest and bigger than the compare
                smallest = list[i];
                index = i;
            }
        }
        IndexAndNum[0] = index;
        IndexAndNum[1] = smallest;
        return IndexAndNum;
    }
    
    
    @Override
    public void moveVolunteer(String skillSet, CommunityGroup from, CommunityGroup to){
        //move a volunteer with this skillset (eg AAA, BCD) from one CommunityGroup to another
        boolean condition = true;
        ListIterator<Volunteer> lit = from.getVolunteerList().listIterator();
        Volunteer vol = null;
        while(lit.hasNext() && condition){
            vol = lit.next();
            if(vol.getSkillSet().equals(skillSet)){
                lit.remove();
                condition = false;
            }
        }
        to.addVolunteer(vol);
        if(!to.getFull()){                                          //test whether the group is full or not
            JOptionPane.showMessageDialog(null,"Volunteer with skill: " + vol.getSkillSet() + " is moved successfully from " + from.getName() + " to " + to.getName());
        }
        else{
            from.addVolunteer(vol);
            JOptionPane.showMessageDialog(null, to.getName()+ " is full, please try another group", "Warning",JOptionPane.WARNING_MESSAGE);
        }
    }
    
    @Override
    public void deleteVolunteer(String skillSet, CommunityGroup from){
        //delete a volunteer with this skillset from this CommunityGroup
        ListIterator<Volunteer> lit = from.getVolunteerList().listIterator();
        Volunteer vol = null;
        while(lit.hasNext()){
            vol = lit.next();
            if(vol.getSkillSet().equalsIgnoreCase(skillSet)){
                lit.remove();
                JOptionPane.showMessageDialog(null,"Volunteer with skill: " + vol.getSkillSet() + " is removed successfully from " + from.getName());
                break;
            }
        }
    }
    
    @Override
    public void deleteAllVolunteers(){
        // delete all volunteers from all CommunityGroups
        for (CommunityGroup group: myGroups){
            group.getVolunteerList().clear();
        }
        JOptionPane.showMessageDialog(null,"All the volunteers are removed successfully");
    }

    @Override
    public ArrayList<CommunityGroup> getCommunityGroups(){
        //return an ArrayList of all this application's CommunityGroups
        return myGroups;
    }
   
   
   public double VarForSkills(ArrayList<CommunityGroup> groups){
       /**
        * We first let a1 denotes the varience of the number of different skills from group1
        * then similarly, a2, a3, a4, a5 can represent that from group2, group3, group4, group5
        * this method is to calculate the sum of these variences, that is a1 + a2 + a3 + a4 + a5
        */
       ListIterator<CommunityGroup> lit = groups.listIterator();
       double varSum = 0;
       CommunityGroup group;
       while (lit.hasNext()){
           group = lit.next();
           double var = CalculateVar(group.getSkillsList()); // the method of getSkillsList return a list of integers, which correspondingly represent the amount of skills: A B C D E
           varSum += var;
       }
       return varSum;
   }
  
   
   public double VarForTotal(ArrayList<CommunityGroup> groups){
       /**
        * this method is used to calculate the variance of total volunteers in each groups 
        */
        int[] volsForEachGroup = new int[5];
        int counter = 0;
        for (CommunityGroup group: groups){
            int num = group.howManyVolunteers();
            volsForEachGroup[counter] = num;
            counter ++;
        }
        double var = CalculateVar(volsForEachGroup);
        return var;
   }
   
   public static double CalculateVar(int[] intList){
       /**
        * this method is used to calculate the varience of the nums in a list
        */
        int sum = 0;
        int amount = intList.length;
        for (int i = 0; i<amount; i++){
            sum += intList[i];
        }
        double average = sum/amount;
        double Var = 0;
        for (int i = 0; i<amount; i++){
            Var += Math.pow((intList[i]-average), 2);
        }
        return Var/amount;
        
    }
   
}
