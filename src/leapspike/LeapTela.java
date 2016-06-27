/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leapspike;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author munif
 */
public class LeapTela extends JFrame{
    
    private SimpleDateFormat sdf=new SimpleDateFormat("HH:MM:ss");
    
    private JLabel status;
    
    public LeapTela(){
        setTitle("Gumga Leap");
        status=new JLabel("Iniciando............................................");
        add(status);
        pack();
        setVisible(true);
        setAlwaysOnTop (true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    public void atualizaStatus(String s){
        status.setText(sdf.format(new Date())+" "+s);
    }
    
    
    
}
