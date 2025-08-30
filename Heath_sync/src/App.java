

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.sql.ResultSet;
import java.util.Objects;

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
        loginBtn.addActionListener(e->new patientDatabase(userId.getText(),passwd.getText(),patientPage));

        JButton registerBtn = new JButton("Register");
        registerBtn.setBounds(150, 180, 100, 30);
        registerBtn.addActionListener(e->new registerpatient(patientPage));

        JButton forgotBtn = new JButton("Forgot?");
        forgotBtn.setBounds(270, 180, 80, 30);

        loginBtn.addActionListener(e -> {
            String username = userId.getText().trim();
            String password = new String(passwd.getPassword());

            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(patientPage, "Please enter Username and Password");
            } else if (username.equals("admin") && password.equals("1234")) {
                JOptionPane.showMessageDialog(patientPage, "Login Successful!");
                // 👉 here you can open the patient dashboard
                // new PatientDashboard();
            } else {
                JOptionPane.showMessageDialog(patientPage, "Invalid Username or Password");
            }
        });


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
class PatientRegister {
    PatientRegister() {
        JFrame regPage = new JFrame("Patient Registration");
        regPage.setSize(450, 400);
        regPage.setLayout(null);
        regPage.getContentPane().setBackground(new Color(0xF0F8FF));

        JLabel title = new JLabel("PATIENT REGISTRATION", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 18));
        title.setBounds(80, 10, 300, 30);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(50, 60, 120, 25);
        JTextField userField = new JTextField();
        userField.setBounds(180, 60, 200, 25);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(50, 100, 120, 25);
        JTextField nameField = new JTextField();
        nameField.setBounds(180, 100, 200, 25);

        JLabel secQLabel = new JLabel("Security Q:");
        secQLabel.setBounds(50, 140, 120, 25);
        JTextField secQField = new JTextField();
        secQField.setBounds(180, 140, 200, 25);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setBounds(50, 180, 120, 25);
        JPasswordField passField = new JPasswordField();
        passField.setBounds(180, 180, 200, 25);

        JLabel confirmLabel = new JLabel("Confirm Password:");
        confirmLabel.setBounds(50, 220, 120, 25);
        JPasswordField confirmField = new JPasswordField();
        confirmField.setBounds(180, 220, 200, 25);

        JLabel mobileLabel = new JLabel("Mobile No:");
        mobileLabel.setBounds(50, 260, 120, 25);
        JTextField mobileField = new JTextField();
        mobileField.setBounds(180, 260, 200, 25);

        JButton submitBtn = new JButton("Submit");
        submitBtn.setBounds(160, 310, 100, 30);

        // Submit button logic
        submitBtn.addActionListener(e -> {
            String username = userField.getText().trim();
            String name = nameField.getText().trim();
            String secQ = secQField.getText().trim();
            String pass = new String(passField.getPassword());
            String confirm = new String(confirmField.getPassword());
            String mobile = mobileField.getText().trim();

            if (username.isEmpty() || name.isEmpty() || secQ.isEmpty() || pass.isEmpty() || confirm.isEmpty() || mobile.isEmpty()) {
                JOptionPane.showMessageDialog(regPage, "Please fill all fields");
            } else if (!pass.equals(confirm)) {
                JOptionPane.showMessageDialog(regPage, "Passwords do not match");
            } else if (!mobile.matches("\\d{10}")) {
                JOptionPane.showMessageDialog(regPage, "Enter valid 10-digit mobile number");
            } else {
                JOptionPane.showMessageDialog(regPage, "Registration Successful!");
                regPage.dispose(); // close after successful registration
            }
        });

        // Add everything
        regPage.add(title);
        regPage.add(userLabel); regPage.add(userField);
        regPage.add(nameLabel); regPage.add(nameField);
        regPage.add(secQLabel); regPage.add(secQField);
        regPage.add(passLabel); regPage.add(passField);
        regPage.add(confirmLabel); regPage.add(confirmField);
        regPage.add(mobileLabel); regPage.add(mobileField);
        regPage.add(submitBtn);

        regPage.setLocationRelativeTo(null);
        regPage.setVisible(true);
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
        loginBtn.addActionListener(e->new doctorDatabase(userId.getText(),passwd.getText(),Doctorpage));
        loginBtn.setBounds(50, 180, 80, 30);

        JButton registerBtn = new JButton("Register");
        registerBtn.setBounds(150, 180, 100, 30);
        registerBtn.addActionListener(e->new registerdoctor(Doctorpage));

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
class patientDatabase {
    Connection con;
    String user, password;
    JFrame loginpage;
    boolean found= true;
    patientDatabase(String u, String p,JFrame loginpage) {
        this.user = u;
        this.password = p;
        this.loginpage=loginpage;


        {
            try {
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/HealthSync", "root", "Akhil");
                Statement st = con.createStatement();
                String q = "select * from patientlogin;";
                ResultSet rs = st.executeQuery(q);



                while (rs.next()) {
                    String a = rs.getString("username");
                    String b = rs.getString("password");

                    if (Objects.equals(user, a) && Objects.equals(password, b)) {
                        new patientDashboard(user);
                        loginpage.dispose();
                        found=false;
                    }

                    }
                if (found) {
                    JOptionPane.showMessageDialog(null,
                            "Invalid Username or Password",
                            "Login Failed",
                            JOptionPane.ERROR_MESSAGE);
                }


            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
class doctorDatabase {
    Connection con;
    String user, password;
    JFrame loginpage;

    doctorDatabase(String u, String p, JFrame loginpage) {
        this.user = u;
        this.password = p;
        this.loginpage = loginpage;


        {
            try {
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/HealthSync", "root", "Akhil");
                Statement st = con.createStatement();
                String q = "select * from doctorlogin;";
                ResultSet rs = st.executeQuery(q);
                boolean found = false;

                while (rs.next()) {
                    String a = rs.getString("username");
                    String b = rs.getString("password");

                    if (Objects.equals(user, a) && Objects.equals(password, b)) {
                        new patientDashboard(user);
                        found = true;
                        loginpage.dispose();
                    }

                }
                if (!found) {
                    JOptionPane.showMessageDialog(null,
                            "Invalid Username or Password",
                            "Login Failed",
                            JOptionPane.ERROR_MESSAGE);
                }


            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }
}


class registerpatient {
    registerpatient(JFrame patientpage) {
        patientpage.dispose();


        JFrame page2 = new JFrame("Register");
        page2.setLocation(400,200);
        page2.getContentPane().setBackground(new Color(133, 195, 255));

        page2.setLayout(null);


        page2.setSize(500, 600);

        JLabel title = new JLabel("REGISTER");
        title.setForeground(new Color(0));
        title.setFont(new Font("JetBrains Mono", Font.BOLD, 20));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setBounds(140, 20, 200, 30);

        JLabel name = new JLabel("Name:");
        name.setBounds(50, 80, 100, 25);
        JTextField name1 = new JTextField(20);
        name1.setBounds(180, 80, 200, 25);

        JLabel phone = new JLabel("Mobile no:");
        phone.setBounds(50, 130, 100, 25);
        JTextField num = new JTextField(10);
        num.setBounds(180, 130, 200, 25);

        JLabel user = new JLabel("Username:");
        user.setBounds(50, 180, 100, 25);
        JTextField userId = new JTextField(20);
        userId.setBounds(180, 180, 200, 25);

        JLabel pass1 = new JLabel("Password:");
        pass1.setBounds(50, 230, 100, 25);
        JPasswordField passwd = new JPasswordField(20);
        passwd.setBounds(180, 230, 200, 25);

        JLabel pass2 = new JLabel("Confirm Password:");
        pass2.setBounds(50, 280, 120, 25);
        JPasswordField confirm = new JPasswordField(20);
        confirm.setBounds(180, 280, 200, 25);

        JLabel sec = new JLabel("Favorite color:");
        sec.setBounds(50, 330, 120, 25);
        JTextField q = new JTextField(20);
        q.setBounds(180, 330, 200, 25);

        JButton s =new JButton("SUBMIT");
        s.setBounds(130,400,210,35);
        s.addActionListener(e->{
            new PatientLogin();
            page2.dispose();});



        page2.add(title);
        page2.add(name);
        page2.add(name1);
        page2.add(phone);
        page2.add(num);
        page2.add(user);
        page2.add(userId);
        page2.add(pass1);
        page2.add(passwd);
        page2.add(pass2);
        page2.add(confirm);
        page2.add(sec);
        page2.add(q);
        page2.add(s);

        page2.setVisible(true);
        page2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }


}
class registerdoctor {
    registerdoctor(JFrame Doctorpage) {
        Doctorpage.dispose();


        JFrame page2 = new JFrame("Register");
        page2.setLocation(400,200);
        page2.getContentPane().setBackground(new Color(133, 195, 255));

        page2.setLayout(null);


        page2.setSize(500, 600);

        JLabel title = new JLabel("REGISTER");
        title.setForeground(new Color(0));
        title.setFont(new Font("JetBrains Mono", Font.BOLD, 20));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setBounds(140, 20, 200, 30);

        JLabel name = new JLabel("Name:");
        name.setBounds(50, 80, 100, 25);
        JTextField name1 = new JTextField(20);
        name1.setBounds(180, 80, 200, 25);

        JLabel phone = new JLabel("Mobile no:");
        phone.setBounds(50, 130, 100, 25);
        JTextField num = new JTextField(10);
        num.setBounds(180, 130, 200, 25);

        JLabel user = new JLabel("Username:");
        user.setBounds(50, 180, 100, 25);
        JTextField userId = new JTextField(20);
        userId.setBounds(180, 180, 200, 25);

        JLabel pass1 = new JLabel("Password:");
        pass1.setBounds(50, 230, 100, 25);
        JPasswordField passwd = new JPasswordField(20);
        passwd.setBounds(180, 230, 200, 25);

        JLabel pass2 = new JLabel("Confirm Password:");
        pass2.setBounds(50, 280, 120, 25);
        JPasswordField confirm = new JPasswordField(20);
        confirm.setBounds(180, 280, 200, 25);

        JLabel sec = new JLabel("Favorite color:");
        sec.setBounds(50, 330, 120, 25);
        JTextField q = new JTextField(20);
        q.setBounds(180, 330, 200, 25);

        JButton s =new JButton("SUBMIT");
        s.setBounds(130,400,210,35);
        s.addActionListener(e->{
            new DoctorLogin();
            page2.dispose();});



        page2.add(title);
        page2.add(name);
        page2.add(name1);
        page2.add(phone);
        page2.add(num);
        page2.add(user);
        page2.add(userId);
        page2.add(pass1);
        page2.add(passwd);
        page2.add(pass2);
        page2.add(confirm);
        page2.add(sec);
        page2.add(q);
        page2.add(s);

        page2.setVisible(true);
        page2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }


}







class patientDashboard{
    patientDashboard(String user){
        JFrame page1=new JFrame("DASHBOARD");
        JLabel welcome = new JLabel("WELCOME " + user);
        welcome.setFont(new Font("Times New Roman", Font.BOLD, 28));
        page1.add(welcome);

        page1.setSize(500,500);
        page1.setVisible(true);
        page1.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }



}
class doctorDashboard {
    doctorDashboard(String user) {
        JFrame page1 = new JFrame("DASHBOARD");
        JLabel welcome = new JLabel("WELCOME " + user);
        welcome.setFont(new Font("Times New Roman", Font.BOLD, 28));
        page1.add(welcome);

        page1.setSize(500, 500);
        page1.setVisible(true);
        page1.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }
}

