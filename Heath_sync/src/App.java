
import java.awt.*;
import javax.swing.*;


public class App {
    public static void main(String[] args)  {
      Frame frame =new Frame("Heath Sync");
        frame.setTitle("Heath Sync");
        frame.setSize(500,500);
        
       JTabbedPane jtp =new JTabbedPane();
       jtp.addTab("patient",new Patientpanel());
       jtp.addTab("doctor",new doctorpanel());
      
      frame.add(jtp);
      frame.setVisible(true);

       




    }
}
class Patientpanel extends JPanel{
    Patientpanel(){
        JButton a=new JButton("Welcome");
        add(a);
    }
}
class doctorpanel extends JPanel{
    doctorpanel(){
        JButton b=new JButton("Welcome");
        add(b);
    }
}