package leapspike;

import com.leapmotion.leap.Controller;
import com.leapmotion.leap.Frame;
import com.leapmotion.leap.Gesture;
import com.leapmotion.leap.Gesture.State;
import com.leapmotion.leap.GestureList;
import com.leapmotion.leap.Listener;
import com.leapmotion.leap.ScreenTapGesture;
import com.leapmotion.leap.SwipeGesture;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author munif
 */
class OuvinteController extends Listener {

    private Robot robot;
    private long last;
    private LeapTela lp;

    public OuvinteController() {
        try {
            robot = new Robot();
            status("Robot OK");
        } catch (AWTException ex) {
            Logger.getLogger(OuvinteController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void onInit(Controller controller) {
        status("Iniciado");
    }

    public void onConnect(Controller controller) {
        status("Conectado");
        controller.enableGesture(Gesture.Type.TYPE_SWIPE);
        controller.enableGesture(Gesture.Type.TYPE_CIRCLE);
        controller.enableGesture(Gesture.Type.TYPE_SCREEN_TAP);
        controller.enableGesture(Gesture.Type.TYPE_KEY_TAP);
    }

    public void onDisconnect(Controller controller) {
        //Note: not dispatched when running in a debugger.
        System.out.println("Disconnected");
    }

    public void onExit(Controller controller) {
        System.out.println("Exited");
    }

    public void onFrame(Controller controller) {

        // Get the most recent frame and report some basic information
        Frame frame = controller.frame();

//        for (Hand hand : frame.hands()) {
//            if (hand.isLeft()) {
//                if (hand.grabStrength() > 0.8 && (System.currentTimeMillis() - last) > 500) {
//                    robot.keyPress(KeyEvent.VK_ESCAPE);
//                    robot.keyRelease(KeyEvent.VK_ESCAPE);
//                    System.out.println("Esquerda " + hand.grabStrength());
//                    last = System.currentTimeMillis();
//                }
//            }
//            if (hand.isRight()) {
//                if (hand.grabStrength() > 0.8 && (System.currentTimeMillis() - last) > 500) {
//                    System.out.println("Direita " + hand.grabStrength());
//                    robot.keyPress(KeyEvent.VK_ENTER);
//                    robot.keyRelease(KeyEvent.VK_ENTER);
//                    last = System.currentTimeMillis();
//                }
//
//            }
//
//        }
//
//        FingerList fingers = frame.fingers();
//
//        
//        
//        for (Finger f : fingers) {
//            System.out.print("-----> "+f);
//            for (Bone.Type boneType : Bone.Type.values()) {
//                Bone bone = f.bone(boneType);
//                System.out.print(" "+bone.type());
//            }
//            System.out.println("");
//        }
//        System.out.println("Frame id: " + frame.id()
//                + ", timestamp: " + frame.timestamp()
//                + ", hands: " + frame.hands().count()
//                + ", fingers: " + frame.fingers().count()
//               + ", tools: " + frame.tools().count()
//               + ", gestures " + frame.gestures().count());
        GestureList gestures = frame.gestures();
        for (int i = 0; i < gestures.count(); i++) {
            Gesture gesture = gestures.get(i);
            switch (gesture.type()) {
                case TYPE_SWIPE:
                    SwipeGesture swipe = new SwipeGesture(gesture);

                    if (swipe.state().equals(State.STATE_STOP)) {

                        if (swipe.direction().get(0) > 0 && (System.currentTimeMillis() - last) > 100) {
                            status("Direita");
                            robot.keyPress(KeyEvent.VK_RIGHT);
                            robot.keyRelease(KeyEvent.VK_RIGHT);
                            last = System.currentTimeMillis();
                        } else if (swipe.direction().get(0) < 0 && (System.currentTimeMillis() - last) > 100) {
                            status("Esquerda");
                            robot.keyPress(KeyEvent.VK_LEFT);
                            robot.keyRelease(KeyEvent.VK_LEFT);
                            last = System.currentTimeMillis();
                        }
                    }

                    break;

                case TYPE_CIRCLE:
                    ScreenTapGesture circle = new ScreenTapGesture(gesture);

                    if (System.currentTimeMillis() - last > 500) {
                        status("Circle" + circle.progress());
                        robot.keyPress(KeyEvent.VK_R);
                        robot.keyRelease(KeyEvent.VK_R);
                        last = System.currentTimeMillis();
                    }
                    break;

            }

        }

    }

    public void setTela(LeapTela lp) {
        this.lp = lp;
    }

    public void status(String s) {
        if (lp != null) {
            lp.atualizaStatus(s);
        } else {
            System.out.println("Leap--->" + s);
        }

    }
}




//         rrrrr rrrrrr  rrrrrr                              