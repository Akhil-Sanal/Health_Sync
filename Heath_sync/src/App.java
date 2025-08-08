
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class App {
    public static void main(String[] args)  {
      JFrame frame =new JFrame("Heath Sync");
        frame.setTitle("Heath Sync");
        frame.setSize(500,500);
        frame.setBackground(Color.BLACK);
        
       JTabbedPane jtp =new JTabbedPane();
       jtp.addTab("patient",new Patientpanel(jtp));
       jtp.addTab("doctor",new doctorpanel(jtp));
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.add(jtp);
      frame.setVisible(true);

    




    }
}
class Patientpanel extends JPanel {
    Patientpanel(JTabbedPane parentTabbedPane) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.cyan);
        
        JButton welcomeButton = new JButton("Welcome Patient");
        JButton registerButton = new JButton("Register Patient");

        welcomeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        registerButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(Box.createVerticalGlue());
        add(welcomeButton);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(registerButton);
        add(Box.createVerticalGlue());

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int existingTabIndex = parentTabbedPane.indexOfTab("Patient Registration");
                if (existingTabIndex == -1) {
                    PatientRegistrationPanel regPanel = new PatientRegistrationPanel();
                    
                } else {
                    parentTabbedPane.setSelectedIndex(existingTabIndex);
                }
            }
        });
    }
}

class PatientRegistrationPanel  {
    PatientRegistrationPanel() {
        Frame pat= new Frame();
        pat.setBackground(Color.CYAN);
        pat.setSize(500,500);
        pat.setVisible(true);
       
        

    }
}
class doctorpanel extends JPanel{
       private JTabbedPane parentTabbedPane;
       doctorpanel(JTabbedPane jtp) {
        setBackground(Color.CYAN);
        this.parentTabbedPane = jtp;
       
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JButton welcomeButton = new JButton("Welcome Doctor");
        JButton registerButton = new JButton("Register Doctor");
        welcomeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        registerButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(Box.createVerticalGlue());
        add(welcomeButton);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(registerButton);
        add(Box.createVerticalGlue());
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int existingTabIndex = parentTabbedPane.indexOfTab("Doctor Registration");

                if (existingTabIndex == -1) {
                    RegistrationPanel regPanel = new RegistrationPanel();
                    
                } else {
                    parentTabbedPane.setSelectedIndex(existingTabIndex);
                }
            }
        });
    }
}
class RegistrationPanel  {
    RegistrationPanel() {
        Frame newframe= new Frame();        
        newframe.setBackground(Color.CYAN);
        newframe.setSize(500,500);
        newframe.setVisible(true);
        
        Label name =new Label();
    
        
    }
}