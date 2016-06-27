/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leapspike;

import com.leapmotion.leap.Controller;
import java.io.IOException;

/**
 *
 * @author munif
 */
public class LeapSpike {

    public static void main(String[] args) {
        // Create a sample listener and controller
        LeapTela lp=new LeapTela();
        OuvinteController listener = new OuvinteController();
        listener.setTela(lp);
        Controller controller = new Controller();

        // Have the sample listener receive events from the controller
        controller.addListener(listener);

        // Keep this process running until Enter is pressed
        System.out.println("Press Enter to quit...");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Remove the sample listener when done
        controller.removeListener(listener);
    }

}
