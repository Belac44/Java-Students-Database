import javax.swing.*;
import java.sql.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.util.ArrayList;

public class GUI {


    public static void main(String[] args) throws Exception
    {

        JFrame frame = new JFrame("E023-01-0872/2019");
        frame.setSize(400,350);
        JPanel log_in_panel = new JPanel();
        JPanel registarion_panel = new JPanel();
        JPanel users_panel = new JPanel();

//        frame.add(log_in_panel);
        frame.add(registarion_panel);
//        frame.add(users_panel);

        registarion_panel.setLayout(null);
        log_in_panel.setLayout(null);
        users_panel.setLayout(null);

//        log_in_panel.setVisible(false);
//        users_panel.setVisible(false);
//        registarion_panel.setVisible(true);



        JLabel regUsernameLabel = new JLabel("Username: ");
        regUsernameLabel.setBounds(70, 50, 80,25);
        registarion_panel.add(regUsernameLabel);

        JTextField regUsername = new JTextField();
        regUsername.setBounds(170, 50, 165, 25);
        registarion_panel.add(regUsername);

        //Reg No
        JLabel regNoLabel = new JLabel("Reg No: ");
        regNoLabel.setBounds(70, 80, 80,25);
        registarion_panel.add(regNoLabel);

        JTextField regNo = new JTextField();
        regNo.setBounds(170, 80, 165, 25);
        registarion_panel.add(regNo);

        // Department
        JLabel DepartmentLabel = new JLabel("Department: ");
        DepartmentLabel.setBounds(70, 110, 80,25);
        registarion_panel.add(DepartmentLabel);

        JTextField Department = new JTextField();
        Department.setBounds(170, 110, 165, 25);
        registarion_panel.add(Department);

        //ID NO.
        JLabel IdNumberLabel = new JLabel("Id Number: ");
        IdNumberLabel.setBounds(70, 140, 80,25);
        registarion_panel.add(IdNumberLabel);

        JTextField IdNumber = new JTextField();
        IdNumber.setBounds(170, 140, 165, 25);
        registarion_panel.add(IdNumber);

        //Password
        JLabel PasswordLabel = new JLabel("Password: ");
        PasswordLabel.setBounds(70, 170, 80,25);
        registarion_panel.add(PasswordLabel);

        JTextField Password = new JPasswordField();
        Password.setBounds(170, 170, 165, 25);
        registarion_panel.add(Password);

        //Repeat password
        JLabel repeatPasswordLabel = new JLabel("Password Conf: ");
        repeatPasswordLabel.setBounds(70, 200, 95,25);
        registarion_panel.add(repeatPasswordLabel);

        JTextField repeatPassword = new JPasswordField();
        repeatPassword.setBounds(170, 200, 165, 25);
        registarion_panel.add(repeatPassword);

        //Register

        JButton register = new JButton("Register");
        register.setBounds(120, 240, 180, 25);
        registarion_panel.add(register);

        // LogIn
        JButton login = new JButton("LogIn");
        login.setBounds(120, 270, 180, 25);
        registarion_panel.add(login);

        //..........................Users Panel ...................................................//
        JLabel Users = new JLabel("Username");
        Users.setBounds(10, 20, 90, 25 );
        users_panel.add(Users);

        JLabel NumberReg = new JLabel("Reg No");
        NumberReg.setBounds(110, 20, 90, 25 );
        users_panel.add(NumberReg);

        JLabel depart = new JLabel("Department");
        depart.setBounds(200, 20, 110, 25 );
        users_panel.add(depart);

        JLabel NumberId = new JLabel("Id No");
        NumberId.setBounds(310, 20, 90, 25 );
        users_panel.add(NumberId);

        ArrayList<String[]> query = new ArrayList<>();
        query = QueryDatabase();

        int y = 40; //We will +20 each loop;

        for (String[] rows : query){
            int x[] = {10, 90, 210, 310};
            int w = 90;
            int h = 25;
            for(int i = 0; i < 4; i ++) {
                JLabel label = new JLabel(rows[i]);
                label.setBounds(x[i], y, w, h);
                users_panel.add(label);

            }
            y+=20;


        }
        


        //.........................Login Panel Page ................................................//
        JLabel Usernamelabel = new JLabel("Username: ");
        Usernamelabel.setBounds(70,100,80,25);
        log_in_panel.add(Usernamelabel);

        JTextField usernameField = new JTextField();
        usernameField.setBounds(170,100,165,25);
        log_in_panel.add(usernameField);

        JLabel Passwordlabel = new JLabel("Password: ");
        Passwordlabel.setBounds(70,130,80,25);
        log_in_panel.add(Passwordlabel);

        JPasswordField PasswordField = new JPasswordField();
        PasswordField.setBounds(170,130,165,25);
        log_in_panel.add(PasswordField);

        JButton submitButton = new JButton("Sign In");
        submitButton.setBounds(70, 160, 270,25);
        log_in_panel.add(submitButton);

        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = PasswordField.getText();

                if (CheckCredentials(username, password)) {
                    JOptionPane.showMessageDialog(null,"LogIn Successful!!");
                    frame.getContentPane().removeAll();
                    if (frame.getContentPane().getComponentCount() == 0) {
                        frame.add(users_panel);
                    } else {
                        frame.add(log_in_panel);
                    }
                    frame.revalidate();
                    frame.repaint();

                }
                else {
                    JOptionPane.showMessageDialog(null, "Invalid Credentials. Try Again");
                }
            }
        });

        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll();
                if (frame.getContentPane().getComponentCount() == 0) {
                    frame.add(log_in_panel);
                } else {
                    frame.add(registarion_panel);
                }
                frame.revalidate();
                frame.repaint();
            }
        });

        register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = regUsername.getText();
                String reg = regNo.getText();
                String department = Department.getText();
                String Id = IdNumber.getText();
                String password = Password.getText();
                String rePassword = repeatPassword.getText();

//                if (password != rePassword){
//                    JOptionPane.showMessageDialog(null, "Password do not match. Try again");
//                }
                int choice = JOptionPane.showConfirmDialog(null, "Are you sure you want this information saved?", "Confirmation", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    UpdateDatabase(username, reg, department, password, Id);
                    JOptionPane.showMessageDialog(null, "Saved");
                    frame.getContentPane().removeAll();
                    if (frame.getContentPane().getComponentCount() == 0) {
                        frame.add(log_in_panel);
                    } else {
                        frame.add(registarion_panel);
                    }
                    frame.revalidate();
                    frame.repaint();
                } else if (choice == JOptionPane.NO_OPTION) {
                    JOptionPane.showMessageDialog(null, "Not saved");   }



            }
        });


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

    public static void UpdateDatabase(String username, String reg, String department, String password, String Id){
        String url = "jdbc:mysql://localhost:3306/students";
        String uname = "root";
        String pass = System.getenv("pass");;
        Connection conn = null;
        Statement st = null;

        try {
            conn = DriverManager.getConnection(url, uname, pass);

        }catch (Exception e){
            e.printStackTrace();
        }
        if (conn != null){
            System.out.println("Connected");
        }else {
            System.out.println("Not connected");
        }

        try{
            st = conn.createStatement();
            String sql = "INSERT INTO registrations(username, regNo, department, idNumber, password) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1,username );
            statement.setString(2,reg );
            statement.setString(3, department);
            statement.setString(4, Id);
            statement.setString(5, password);

            int rowsAffected = statement.executeUpdate();
            System.out.println("Rows affected "+rowsAffected);

        }catch (SQLException f){
            f.printStackTrace();
        }catch (Exception g){
            g.printStackTrace();
        }finally{
            try{
                if (st != null)
                    conn.close();
            }catch (SQLException h){
                h.printStackTrace();
            }
        }
        try {
            if (conn != null)
                conn.close();
        }catch (SQLException i){
            i.printStackTrace();
        }

    }


    public static ArrayList QueryDatabase(){
        String url = "jdbc:mysql://localhost:3306/students";
        String uname = "root";
        String pass = System.getenv("pass");;
        Connection conn = null;
        Statement st = null;

        ArrayList<String[]> items = new ArrayList<>();

        try {
            conn = DriverManager.getConnection(url, uname, pass);

        }catch (Exception e){
            e.printStackTrace();
        }
        if (conn != null){
            System.out.println("Connected");
        }else {
            System.out.println("Not connected");
        }

        try{
            st = conn.createStatement();
            String sql = "SELECT * FROM registrations";
            ResultSet rs = st.executeQuery(sql);

            // Extract data from result set
            while (rs.next()) {
                String username = rs.getString("username");
                String reg = rs.getString("regNo");
                String department = rs.getString("department");
                String Id = rs.getString("idNumber");
                String row[] = new String[4];
                row[0]=username;
                row[1]=reg;
                row[2]=department;
                row[3]=Id;

                items.add(row);
            }

        }catch (SQLException f){
            f.printStackTrace();
        }catch (Exception g){
            g.printStackTrace();
        }finally{
            try{
                if (st != null)
                    conn.close();
            }catch (SQLException h){
                h.printStackTrace();
            }
        }
        try {
            if (conn != null)
                conn.close();
        }catch (SQLException i){
            i.printStackTrace();
        }

        return items;

    }

    public static Boolean CheckCredentials(String username, String password){
        String url = "jdbc:mysql://localhost:3306/students";
        String uname = "root";
        String pass = System.getenv("pass");;
        Connection conn = null;
        Statement st = null;

        String database_pass = "";

        try {
            conn = DriverManager.getConnection(url, uname, pass);

        }catch (Exception e){
            e.printStackTrace();
        }
        if (conn != null){
            System.out.println("Connected");
        }else {
            System.out.println("Not connected");
        }

        try{
            st = conn.createStatement();
            String sql = "SELECT * FROM registrations WHERE username = ? ";

            try (PreparedStatement statement = conn.prepareStatement(sql)) {

                // set the key value as a parameter for the query
                statement.setString(1, username);

                try (ResultSet result = statement.executeQuery()) {

                    // Check if the result set contains a row
                    if (result.next()) {


                        database_pass = result.getString("password");
                        return database_pass.equals(password);

                    }
                }
            } catch (SQLException e) {
                System.err.println("Error querying database: " + e.getMessage());
            }

        }catch (SQLException f){
            f.printStackTrace();
        }catch (Exception g){
            g.printStackTrace();
        }finally{
            try{
                if (st != null)
                    conn.close();
            }catch (SQLException h){
                h.printStackTrace();
            }
        }
        try {
            if (conn != null)
                conn.close();
        }catch (SQLException i){
            i.printStackTrace();
        }

        return database_pass.equals(password);
    }
    }
