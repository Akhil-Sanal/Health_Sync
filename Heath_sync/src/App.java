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
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/HealthSync", "root", "Akhil");
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

        Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/HealthSync", "root", "Akhil");
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
    patientDashboard(String user) {
        JFrame page1 = new JFrame("DASHBOARD");


        JPanel pan = new JPanel();
        pan.setLayout(null);
        pan.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 5));
        pan.setBackground(new Color(133, 195, 255));
        pan.setBounds(0, 0, 720, 500);


        JLabel welcome = new JLabel("WELCOME " + user, SwingConstants.CENTER);
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

        JButton o = new JButton("Date 1");
        o.setBounds(50, 150, 300, 50);
        pan.add(o);

        JButton p = new JButton("Date 2");
        p.setBounds(50, 210, 300, 50);
        pan.add(p);


        page1.add(pan);

        page1.setSize(720, 500);
        page1.setLayout(null);
        page1.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        page1.setLocationRelativeTo(null);
        page1.setVisible(true);
    }
}

class patientDataset{
    {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/HealthSync", "root", "Akhil");
            Statement st= con.createStatement();
            String s="select * from patientdataset;";
            ResultSet rs=st.executeQuery(s);


        } catch (SQLException e) {
            System.out.println("Not connected");
        }
    }
}
