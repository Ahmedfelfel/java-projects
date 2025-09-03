
import java.awt.*;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        DateTimeApp app = new DateTimeApp();
        Frame frame = new Frame("Date and Time Applet");
        frame.add(app);
        frame.setSize(300, 200);
        frame.setVisible(true);
        app.init();
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                System.exit(0);
        }
        });
    }
}