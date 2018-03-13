/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coursework3;

import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author acer
 */
public class Table extends JFrame {
    private Vector rowData, columnName;
    private JTable jt = null;
    private JScrollPane jsp = null;
    private Vector line1;
    private Vector line2;
    private Vector line3;
    private Vector line4;
    private Vector line5;
            
    
    public Table(){
        init();
    }
    
    public void init(){
        columnName = new Vector();
        columnName.add("Group Name");   //add the title of the table
        columnName.add("Skill A");
        columnName.add("Skill B");
        columnName.add("Skill C");
        columnName.add("Skill D");
        columnName.add("Skill E");
        columnName.add("Total");
        
        rowData = new Vector();
        
        line1 = new Vector();
        line1.add("Group1");
        for (int i = 0; i<5; i++){
            line1.add("0");
        }
        
        line2 = new Vector();
        line2.add("Group2");
        
        
        line3 = new Vector();
        line3.add("Group3");
        
        
        line4 = new Vector();
        line4.add("Group4");
       
        
        line5 = new Vector();
        line5.add("Group5");
        
        
        rowData.add(line1);
        rowData.add(line2);
        rowData.add(line3);
        rowData.add(line4);
        rowData.add(line5);
        
        jt = new JTable(rowData, columnName);
        jsp = new JScrollPane(jt);
        this.add(jsp);
        this.setTitle("Group display");
        this.setSize(600,140);
        this.setLocation(600, 300);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(false);
    }
    
    public Vector getLine1(){
        return this.line1;
    }
    
    public Vector getLine2(){
        return this.line2;
    }
    
    public Vector getLine3(){
        return this.line3;
    }
    
    public Vector getLine4(){
        return this.line4;
    }
    
    public Vector getLine5(){
        return this.line5;
    }
    
}
