import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
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
        patientPage.getContentPane().setBackground(new Color(0x85C3FF));
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
        forgotBtn.addActionListener(e -> new ForgotPassword(true));



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
        Doctorpage.getContentPane().setBackground(new Color(0x85C3FF));
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
        forgotBtn.addActionListener(e -> new ForgotPassword(true));

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
class UpdatePassword {
    UpdatePassword(String user, String phone, String security, String newPass, boolean isPatient, JFrame frameToDispose) {
        String tableName = isPatient ? "patientlogin" : "doctorlogin";
        String sqlSelect = "SELECT * FROM " + tableName + " WHERE username = ? AND phoneno = ? AND security_answer = ?";
        String sqlUpdate = "UPDATE " + tableName + " SET password = ? WHERE username = ?";
        
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/HealthSync", "root", "Akhil")) {
            // Check if credentials are correct
            try (PreparedStatement selectSt = con.prepareStatement(sqlSelect)) {
                selectSt.setString(1, user);
                selectSt.setString(2, phone);
                selectSt.setString(3, security);
                
                ResultSet rs = selectSt.executeQuery();
                if (rs.next()) {
                    // Update the password
                    try (PreparedStatement updateSt = con.prepareStatement(sqlUpdate)) {
                        updateSt.setString(1, newPass);
                        updateSt.setString(2, user);
                        int rowsAffected = updateSt.executeUpdate();
                        
                        if (rowsAffected > 0) {
                            JOptionPane.showMessageDialog(null, "Password updated successfully! ✅");
                            frameToDispose.dispose();
                        } else {
                            JOptionPane.showMessageDialog(null, "Failed to update password.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid username, phone number, or security answer.", "Login Failed", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Database Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
}
class ForgotPassword extends JFrame {
     ForgotPassword(boolean isPatient) {
        super(isPatient ? "Patient Forgot Password" : "Doctor Forgot Password");
        getContentPane().setBackground(new Color(0x85C3FF));
        setSize(500, 450);
        setLayout(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel title = new JLabel("Forgot Password", SwingConstants.CENTER);
        title.setFont(new Font("JetBrains Mono", Font.BOLD, 20));
        title.setBounds(100, 20, 300, 30);
        add(title);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(50, 70, 100, 25);
        add(userLabel);
        JTextField usernameField = new JTextField();
        usernameField.setBounds(200, 70, 200, 25);
        add(usernameField);

        JLabel phoneLabel = new JLabel("Phone No.:");
        phoneLabel.setBounds(50, 110, 100, 25);
        add(phoneLabel);
        JTextField phoneField = new JTextField();
        phoneField.setBounds(200, 110, 200, 25);
        add(phoneField);

        JLabel securityLabel = new JLabel("Favorite color:");
        securityLabel.setBounds(50, 150, 120, 25);
        add(securityLabel);
        JTextField securityField = new JTextField();
        securityField.setBounds(200, 150, 200, 25);
        add(securityField);

        JLabel newPassLabel = new JLabel("New Password:");
        newPassLabel.setBounds(50, 190, 150, 25);
        add(newPassLabel);
        JPasswordField newPassField = new JPasswordField();
        newPassField.setBounds(200, 190, 200, 25);
        add(newPassField);

        JLabel confirmPassLabel = new JLabel("Confirm Password:");
        confirmPassLabel.setBounds(50, 230, 150, 25);
        add(confirmPassLabel);
        JPasswordField confirmPassField = new JPasswordField();
        confirmPassField.setBounds(200, 230, 200, 25);
        add(confirmPassField);

        JButton submitBtn = new JButton("Submit");
        submitBtn.setBounds(150, 280, 200, 30);
        add(submitBtn);

        submitBtn.addActionListener(e -> {
            String username = usernameField.getText();
            String phone = phoneField.getText();
            String securityAnswer = securityField.getText();
            String newPassword = new String(newPassField.getPassword());
            String confirmPassword = new String(confirmPassField.getPassword());

            if (newPassword.equals(confirmPassword)) {
                new UpdatePassword(username, phone, securityAnswer, newPassword, isPatient, this);
            } else {
                JOptionPane.showMessageDialog(this, "Passwords do not match.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        setLocationRelativeTo(null);
        setVisible(true);
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
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/HealthSync", "root", "sreehari025");
                Statement st = con.createStatement();
                String q = "select * from patientlogin;";
                ResultSet rs = st.executeQuery(q);



                while (rs.next()) {
                    String a = rs.getString("username");
                    String b = rs.getString("password");
                    String c= rs.getString("name");

                    if (Objects.equals(user, a) && Objects.equals(password, b)) {
                        new patientDashboard(c);
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
                con.close();


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
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/HealthSync", "root", "sreehari025");
                Statement st = con.createStatement();
                String q = "select * from doctorlogin;";
                ResultSet rs = st.executeQuery(q);
                boolean found = false;

                while (rs.next()) {
                    String a = rs.getString("username");
                    String b = rs.getString("password");
                    String c= rs.getString("name");

                    if (Objects.equals(user, a) && Objects.equals(password, b)) {
                        new doctorDashboard(c);
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
                con.close();


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
            new registerDatabase(name1.getText(),num.getText(),userId.getText(),passwd.getText(),q.getText(),'y');
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
            new registerDatabase(name1.getText(),num.getText(),userId.getText(),passwd.getText(),q.getText(),'n');
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

class registerDatabase{
    registerDatabase(String name,String no,String userId,String Pass,String qu,char ans){
        try{

        Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/HealthSync", "root", "sreehari025");
        Statement st = con.createStatement();
        if(ans=='y') {
            String q = "insert into patientlogin values(?,?,?,?,?)";


            PreparedStatement ps = con.prepareStatement(q);
            ps.setString(1, name);
            ps.setString(2, no);
            ps.setString(3, userId);
            ps.setString(4, Pass);
            ps.setString(5, qu);
            ps.executeUpdate();
            con.close();
        }
        else if(ans=='n'){
            String q = "insert into doctorlogin values(?,?,?,?,?)";


            PreparedStatement ps = con.prepareStatement(q);
            ps.setString(1, name);
            ps.setString(2, no);
            ps.setString(3, userId);
            ps.setString(4, Pass);
            ps.setString(5, qu);
            ps.executeUpdate();
            con.close();

        }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        }
}









class patientDashboard {
    patientDashboard(String userId) {
        JFrame page1 = new JFrame("DASHBOARD");

        JPanel pan = new JPanel();
        pan.setLayout(null);
        pan.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 5));
        pan.setBackground(new Color(133, 195, 255));
        pan.setBounds(0, 0, 720, 500);

        JLabel welcome = new JLabel("WELCOME " + userId, SwingConstants.CENTER);
        welcome.setForeground(new Color(38, 2, 50));
        welcome.setFont(new Font("Times New Roman", Font.PLAIN, 28));
        welcome.setBounds(200, 20, 300, 40);
        pan.add(welcome);

        JSeparator sep = new JSeparator();
        sep.setBounds(50, 80, 600, 2);
        sep.setForeground(Color.BLACK);
        pan.add(sep);

        JLabel list = new JLabel("Prescription List");
        list.setFont(new Font("Times New Roman", Font.ITALIC, 20));
        list.setBounds(50, 100, 200, 30);
        pan.add(list);

        // Fetch prescriptions only for this userId
        try {
            String url = "jdbc:mysql://localhost:3306/healthsync";
            String username = "root";
            String password = "sreehari025";

            Connection conn = DriverManager.getConnection(url, username, password);

            String sql = "SELECT prescription_id, prescription_date, patient_name, symptoms, diagnosis, medicines " +
                    "FROM prescriptions WHERE userId = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userId);
            ResultSet rs = pstmt.executeQuery();

            int y = 150; // button vertical position

            boolean found = false;
            while (rs.next()) {
                found = true;
                int id = rs.getInt("prescription_id");
                String date = rs.getString("prescription_date");
                String patientName = rs.getString("patient_name");
                String symptoms = rs.getString("symptoms");
                String diagnosis = rs.getString("diagnosis");
                String medicines = rs.getString("medicines");

                JButton btn = new JButton(date);
                btn.setBounds(50, y, 300, 40);

                pan.add(btn);
                y += 60;

                btn.addActionListener(e -> {
                    // Create a new frame for the prescription details
                    JFrame detailsFrame = new JFrame("Prescription Details");
                    detailsFrame.setSize(500, 400);
                    detailsFrame.setLocationRelativeTo(page1);
                    detailsFrame.setLayout(new BorderLayout());

                    // Panel styled like a card/box
                    JPanel box = new JPanel();
                    box.setLayout(new BoxLayout(box, BoxLayout.Y_AXIS));
                    box.setBorder(BorderFactory.createCompoundBorder(
                            BorderFactory.createLineBorder(Color.DARK_GRAY, 2),
                            BorderFactory.createEmptyBorder(20, 20, 20, 20)
                    ));
                    box.setBackground(new Color(240, 248, 255)); // light background

                    // Add labels with prescription details
                    JLabel dateLabel = new JLabel("Date: " + date);
                    dateLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));

                    JLabel patientLabel = new JLabel("Patient: " + patientName);
                    patientLabel.setFont(new Font("Times New Roman", Font.PLAIN, 16));

                    JLabel symptomsLabel = new JLabel("Symptoms: " + symptoms);
                    symptomsLabel.setFont(new Font("Times New Roman", Font.PLAIN, 16));

                    JLabel diagLabel = new JLabel("Diagnosis: " + diagnosis);
                    diagLabel.setFont(new Font("Times New Roman", Font.PLAIN, 16));

                    JLabel medsLabel = new JLabel("<html>Medicines:<br>" + medicines.replaceAll(";", "<br>") + "</html>");
                    medsLabel.setFont(new Font("Times New Roman", Font.PLAIN, 16));

                    // Add components to box
                    box.add(dateLabel);
                    box.add(Box.createVerticalStrut(10));
                    box.add(patientLabel);
                    box.add(Box.createVerticalStrut(10));
                    box.add(symptomsLabel);
                    box.add(Box.createVerticalStrut(10));
                    box.add(diagLabel);
                    box.add(Box.createVerticalStrut(10));
                    box.add(medsLabel);

                    // Add panel to frame
                    detailsFrame.add(box, BorderLayout.CENTER);

                    // Show frame
                    detailsFrame.setVisible(true);
                });

            }

            if (!found) {
                JLabel noData = new JLabel("No prescriptions found.", SwingConstants.CENTER);
                noData.setBounds(50, 150, 400, 30);
                noData.setFont(new Font("Times New Roman", Font.PLAIN, 18));
                pan.add(noData);
            }

            rs.close();
            pstmt.close();
            conn.close();

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(page1, "Database error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        page1.add(pan);
        page1.setSize(720, 500);
        page1.setLayout(null);
        page1.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        page1.setLocationRelativeTo(null);
        page1.setVisible(true);
    }
}


class doctorDashboard {
    doctorDashboard(String user) {
        JFrame page1 = new JFrame("DASHBOARD");
        page1.setLayout(null);


        JPanel pan = new JPanel();
        pan.setLayout(null); // allow absolute positioning
        pan.setBackground(new Color(133, 195, 255));
        pan.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 5));
        pan.setBounds(0, 0, 720, 500);
        page1.add(pan);


        JLabel welcome = new JLabel("WELCOME " + user);
        welcome.setFont(new Font("Times New Roman", Font.BOLD, 28));
        welcome.setForeground(new Color(38, 2, 50));
        welcome.setBounds(270, 20, 400, 40);
        pan.add(welcome);


        JSeparator sep = new JSeparator();
        sep.setBounds(50, 80, 600, 2);
        sep.setForeground(Color.BLACK);
        pan.add(sep);


        JButton o = new JButton("Upload prescription");
        o.setBounds(150, 120, 400, 60);
        o.addActionListener(e->new uplopre());
        pan.add(o);

        JButton p = new JButton("Disease tracker");
        p.setBounds(150, 200, 400, 60);

        pan.add(p);

        JButton b = new JButton("Patient tracker");
        b.setBounds(150, 280, 400, 60);
        pan.add(b);


        page1.setSize(720, 500);
        page1.setLocationRelativeTo(null);
        page1.setVisible(true);
        page1.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }
}

class patientDataset{
    {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/HealthSync", "root", "sreehari025");
            Statement st= con.createStatement();
            String s="select * from patientdataset;";
            ResultSet rs=st.executeQuery(s);


        } catch (SQLException e) {
            System.out.println("Not connected");
        }
    }
}




class uplopre {
    private final ArrayList<JTextField> medicineFields = new ArrayList<>();

    uplopre() {
        JFrame page3 = new JFrame("UPLOAD PRESCRIPTION");
        page3.setLayout(null);

        JPanel pan = new JPanel();
        pan.setLayout(null);
        pan.setBackground(new Color(133, 195, 255));
        pan.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 5));
        pan.setBounds(0, 0, 700, 600);
        page3.add(pan);

        JLabel prescription = new JLabel("UPLOAD PRESCRIPTION");
        prescription.setFont(new Font("Times New Roman", Font.BOLD, 26));
        prescription.setForeground(new Color(38, 2, 50));
        prescription.setBounds(180, 20, 400, 40);
        pan.add(prescription);

        JSeparator sep = new JSeparator();
        sep.setBounds(50, 70, 600, 2);
        sep.setForeground(Color.BLACK);
        pan.add(sep);

        // User ID
        JLabel userIdLabel = new JLabel("User ID:");
        userIdLabel.setBounds(50, 90, 100, 25);
        pan.add(userIdLabel);
        JTextField userIdField = new JTextField();
        userIdField.setBounds(180, 90, 200, 25);
        pan.add(userIdField);

        // Date
        JLabel date = new JLabel("Date:");
        date.setBounds(50, 130, 100, 25);
        pan.add(date);
        JTextField dateField = new JTextField();
        dateField.setBounds(180, 130, 200, 25);
        pan.add(dateField);

        // Patient Name
        JLabel patient = new JLabel("Patient Name:");
        patient.setBounds(50, 170, 100, 25);
        pan.add(patient);
        JTextField patientField = new JTextField();
        patientField.setBounds(180, 170, 200, 25);
        pan.add(patientField);

        // Symptoms
        JLabel symptomLabel = new JLabel("Symptoms:");
        symptomLabel.setBounds(50, 210, 100, 25);
        pan.add(symptomLabel);
        JTextField symptomField = new JTextField();
        symptomField.setBounds(180, 210, 400, 25);
        pan.add(symptomField);

        // Diagnosis
        JLabel diagLabel = new JLabel("Diagnosis:");
        diagLabel.setBounds(50, 250, 100, 25);
        pan.add(diagLabel);
        JTextField diagnosisField = new JTextField();
        diagnosisField.setBounds(180, 250, 400, 25);
        pan.add(diagnosisField);

        // Medicine Label
        JLabel medLabel = new JLabel("Medicines and dosage:");
        medLabel.setBounds(180, 290, 400, 25);
        pan.add(medLabel);

        // Panel for medicine fields
        JPanel medicinePanel = new JPanel();
        medicinePanel.setLayout(new BoxLayout(medicinePanel, BoxLayout.Y_AXIS));
        medicinePanel.setBackground(new Color(133, 195, 255));

        JScrollPane scrollPane = new JScrollPane(medicinePanel);
        scrollPane.setBounds(180, 320, 400, 120);
        pan.add(scrollPane);

        // Add first medicine field by default
        addMedicineField(medicinePanel);

        // Add Medicine Button
        JButton addMedBtn = new JButton("+ Add Medicine");
        addMedBtn.setBounds(180, 450, 160, 30);
        pan.add(addMedBtn);

        addMedBtn.addActionListener(e -> addMedicineField(medicinePanel));

        // Submit Button
        JButton submitBtn = new JButton("Submit");
        submitBtn.setBounds(270, 500, 150, 35);
        pan.add(submitBtn);

        submitBtn.addActionListener(e -> {
            String userId = userIdField.getText().trim();
            String date1 = dateField.getText().trim();
            String patientName = patientField.getText().trim();
            String symptoms = symptomField.getText().trim();
            String diagnosis = diagnosisField.getText().trim();

            if (userId.isEmpty() || date1.isEmpty() || patientName.isEmpty() || symptoms.isEmpty() || diagnosis.isEmpty()) {
                JOptionPane.showMessageDialog(page3, "User ID, Date, Patient Name, Symptoms, and Diagnosis are required.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            ArrayList<String> meds = new ArrayList<>();
            for (JTextField field : medicineFields) {
                String med = field.getText().trim();
                if (!med.isEmpty()) {
                    meds.add(med);
                }
            }

            if (meds.isEmpty()) {
                JOptionPane.showMessageDialog(page3, "Please enter at least one medicine.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String medicines = String.join("; ", meds);

            // Save to DB with userId
            savePrescription(userId, date1, patientName, symptoms, diagnosis, medicines);

            JOptionPane.showMessageDialog(page3, "Prescription saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            page3.dispose();
        });

        // JFrame Setup
        page3.setSize(700, 600);
        page3.setLocationRelativeTo(null);
        page3.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        page3.setVisible(true);
    }

    private void addMedicineField(JPanel panel) {
        JTextField newField = new JTextField();
        newField.setMaximumSize(new Dimension(350, 30));
        medicineFields.add(newField);
        panel.add(newField);
        panel.revalidate();
        panel.repaint();
    }

    private void savePrescription(String userId, String date1, String patientName, String symptoms, String diagnosis, String medicines) {
        try {
            String url = "jdbc:mysql://localhost:3306/healthsync";
            String user = "root";
            String password = "sreehari025";

            Connection conn = DriverManager.getConnection(url, user, password);

            String sql = "INSERT INTO prescriptions ( prescription_date, patient_name, symptoms, diagnosis, medicines,userId) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(6, userId);
            pstmt.setString(1, date1);
            pstmt.setString(2, patientName);
            pstmt.setString(3, symptoms);
            pstmt.setString(4, diagnosis);
            pstmt.setString(5, medicines);

            pstmt.executeUpdate();

            pstmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Database error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

