
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class App {
    public static void main(String[] args)  {
      Frame frame =new Frame("Heath Sync");
        frame.setTitle("Heath Sync");
        frame.setSize(500,500);
        
       JTabbedPane jtp =new JTabbedPane();
       jtp.addTab("patient",new Patientpanel(jtp));
       jtp.addTab("doctor",new doctorpanel(jtp));
      
      frame.add(jtp);
      frame.setVisible(true);

    




    }
}
class Patientpanel extends JPanel {
    Patientpanel(JTabbedPane parentTabbedPane) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

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
                    parentTabbedPane.addTab("Patient Registration", regPanel);
                    parentTabbedPane.setSelectedComponent(regPanel);
                } else {
                    parentTabbedPane.setSelectedIndex(existingTabIndex);
                }
            }
        });
    }
}

class PatientRegistrationPanel extends JPanel {
    PatientRegistrationPanel() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.EAST;

        gbc.gridx = 0; gbc.gridy = 0;
        add(new JLabel("Name:"), gbc);
        gbc.gridx = 1;
        JTextField nameField = new JTextField(20);
        add(nameField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        add(new JLabel("Phone Number:"), gbc);
        gbc.gridx = 1;
        JTextField phoneNoField = new JTextField(20);
        add(phoneNoField, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        add(new JLabel("Password:"), gbc);
        gbc.gridx = 1;
        JPasswordField passwordField = new JPasswordField(20);
        add(passwordField, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        add(new JLabel("Confirm Password:"), gbc);
        gbc.gridx = 1;
        JPasswordField confirmPasswordField = new JPasswordField(20);
        add(confirmPasswordField, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        add(new JLabel("What is your favourite colour?"), gbc);
        gbc.gridx = 1;
        JTextField securityQField = new JTextField(20);
        add(securityQField, gbc);

        gbc.gridx = 0; gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton submitButton = new JButton("Submit");
        add(submitButton, gbc);
    }
}
   

class doctorpanel extends JPanel{
       private JTabbedPane parentTabbedPane;
       doctorpanel(JTabbedPane jtp) {
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
                    parentTabbedPane.addTab("Doctor Registration", regPanel);
                    parentTabbedPane.setSelectedComponent(regPanel);
                } else {
                    parentTabbedPane.setSelectedIndex(existingTabIndex);
                }
            }
        });
    }
}
class RegistrationPanel extends JPanel {
    RegistrationPanel() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        add(new JLabel("Name:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        JTextField nameField = new JTextField(20);
        add(nameField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        add(new JLabel("Phone Number:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        JTextField phoneNoField = new JTextField(20);
        add(phoneNoField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        add(new JLabel("Password:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        JTextField passwordField = new JTextField(20);
        add(passwordField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        add(new JLabel("Conform Password:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        JTextField conformpasswordField = new JTextField(20);
        add(conformpasswordField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        JButton submitButton = new JButton("Submit");
        add(submitButton, gbc);
    }
}