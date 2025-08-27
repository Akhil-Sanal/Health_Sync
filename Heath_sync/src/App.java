import javax.swing.*;
import java.awt.*;

public class App extends JFrame {
    JButton patient;
    JButton doctor;

    public App() {
        super("HEALTH SYNC");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel front = new JPanel();
        front.setLayout(new BoxLayout(front, BoxLayout.Y_AXIS));
        front.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 5));
        front.setBackground(new Color(0x0658A8));

        JLabel welcome = new JLabel("WELCOME", SwingConstants.CENTER);
        welcome.setAlignmentX(Component.CENTER_ALIGNMENT);
        welcome.setForeground(new Color(0xFFE30B));
        welcome.setFont(new Font("Times New Roman", Font.BOLD, 30));

        patient = new JButton("Patient Login");
        doctor = new JButton("Doctor Login");

        patient.addActionListener(e -> new PatientLogin());
        doctor.addActionListener(e -> new DoctorLogin());

        patient.setAlignmentX(Component.CENTER_ALIGNMENT);
        doctor.setAlignmentX(Component.CENTER_ALIGNMENT);

        front.add(Box.createRigidArea(new Dimension(0, 100)));
        front.add(welcome);
        front.add(Box.createRigidArea(new Dimension(0, 50)));
        front.add(patient);
        front.add(Box.createRigidArea(new Dimension(0, 30)));
        front.add(doctor);

        add(front);
        setLocationRelativeTo(null); // center
        setVisible(true);
    }

    public static void main(String[] args) {
        new App();
    }
}

class PatientLogin {
    PatientLogin() {
        JFrame patientPage = new JFrame("Patient Login");
        patientPage.getContentPane().setBackground(new Color(0xD5F6F3));
        patientPage.setSize(400, 300);
        patientPage.setLayout(null);  // absolute positioning
        patientPage.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        JLabel title = new JLabel("PATIENT LOGIN", SwingConstants.CENTER);
        title.setForeground(new Color(0));
        title.setFont(new Font("JetBrains Mono", Font.BOLD, 20));
        title.setBounds(100, 20, 200, 30);

        JLabel user = new JLabel("Username:");
        user.setForeground(new Color(0));
        user.setBounds(50, 80, 100, 25);

        JTextField userId = new JTextField();
        userId.setBounds(150, 80, 180, 25);

        JLabel pass = new JLabel("Password:");
        pass.setForeground(new Color(0));
        pass.setBounds(50, 120, 100, 25);

        JPasswordField passwd = new JPasswordField();
        passwd.setBounds(150, 120, 180, 25);

        JButton loginBtn = new JButton("Login");
        loginBtn.setBounds(50, 180, 80, 30);

        JButton registerBtn = new JButton("Register");
        registerBtn.setBounds(150, 180, 100, 30);

        JButton forgotBtn = new JButton("Forgot?");
        forgotBtn.setBounds(270, 180, 80, 30);


        patientPage.add(title);
        patientPage.add(user);
        patientPage.add(userId);
        patientPage.add(pass);
        patientPage.add(passwd);
        patientPage.add(loginBtn);

        patientPage.add(registerBtn);
        patientPage.add(forgotBtn);


        patientPage.setLocationRelativeTo(null);
        patientPage.setVisible(true);
    }
}

class DoctorLogin {
    DoctorLogin() {
        JFrame Doctorpage = new JFrame("Login in");
        Doctorpage.getContentPane().setBackground(new Color(0xD5F6F3));
        Doctorpage.setSize(400, 300);
        Doctorpage.setLayout(null);  // absolute positioning
        Doctorpage.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        JLabel title = new JLabel("DOCTOR LOGIN", SwingConstants.CENTER);
        title.setForeground(new Color(0));
        title.setFont(new Font("JetBrains Mono", Font.BOLD, 20));
        title.setBounds(100, 20, 200, 30);

        JLabel user = new JLabel("Username:");
        user.setForeground(new Color(0));
        user.setBounds(50, 80, 100, 25);

        JTextField userId = new JTextField();
        userId.setBounds(150, 80, 180, 25);

        JLabel pass = new JLabel("Password:");
        pass.setForeground(new Color(0));
        pass.setBounds(50, 120, 100, 25);

        JPasswordField passwd = new JPasswordField();
        passwd.setBounds(150, 120, 180, 25);

        JButton loginBtn = new JButton("Login");
        loginBtn.setBounds(50, 180, 80, 30);

        JButton registerBtn = new JButton("Register");
        registerBtn.setBounds(150, 180, 100, 30);

        JButton forgotBtn = new JButton("Forgot?");
        forgotBtn.setBounds(270, 180, 80, 30);


        Doctorpage.add(title);
        Doctorpage.add(user);
        Doctorpage.add(userId);
        Doctorpage.add(pass);
        Doctorpage.add(passwd);
        Doctorpage.add(loginBtn);

        Doctorpage.add(registerBtn);
        Doctorpage.add(forgotBtn);


        Doctorpage.setLocationRelativeTo(null);
        Doctorpage.setVisible(true);

    }
}
