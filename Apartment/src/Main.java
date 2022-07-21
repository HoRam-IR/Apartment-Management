import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JOptionPane;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;


class CreateLoginForm extends JFrame implements ActionListener
{
    //initialize button, panel, label, and text field
    JButton b1;
    JPanel newPanel;
    JLabel userLabel, passLabel;
    final JTextField  textField1, textField2;

    //calling constructor
    CreateLoginForm()
    {

        //create label for username
        userLabel = new JLabel();
        userLabel.setText("Username");      //set label value for textField1

        //create text field to get username from the user
        textField1 = new JTextField(15);    //set length of the text

        //create label for password
        passLabel = new JLabel();
        passLabel.setText("Password");      //set label value for textField2

        //create text field to get password from the user
        textField2 = new JPasswordField(15);    //set length for the password

        //create submit button
        b1 = new JButton("SUBMIT"); //set label to button

        //create panel to put form elements
        newPanel = new JPanel(new GridLayout(3, 1));
        newPanel.add(userLabel);    //set username label to panel
        newPanel.add(textField1);   //set text field to panel
        newPanel.add(passLabel);    //set password label to panel
        newPanel.add(textField2);   //set text field to panel
        newPanel.add(b1);           //set button to panel

        //set border to panel
        add(newPanel, BorderLayout.CENTER);

        //perform action on button click
        b1.addActionListener(this);     //add action listener to button
        setTitle("LOGIN FORM");         //set title to the login form
    }

    public void adminThread(){
        JFrame a = new JFrame("Admin Menu");
        JButton b = new JButton("مشاهده پرداختی ها");
        JButton c = new JButton("پنل جستجو");
        JButton d = new JButton("لیست صاحبین خانه");
        JButton e = new JButton("لیست مستاجران");
        b.setBounds(200,80,150,35);
        c.setBounds(200,120,150,35);
        d.setBounds(200,160,150,35);
        e.setBounds(200,40,150,35);
        a.add(b);
        a.add(c);
        a.add(d);
        a.add(e);
        a.setSize(300,300);
        a.setBounds(600, 280, 600, 300);
        a.setLayout(null);
        a.setVisible(true);
    }

    public void userThread(){
        JFrame a = new JFrame("User Menu");
        JButton b = new JButton("مدیریت هزینه ها");
        JButton c = new JButton("لیست درخواست ها");
        JButton d = new JButton("درخواست ثبت واحد");
        JButton e = new JButton("لیست قرارداد ها");
        b.setBounds(200,80,150,35);
        c.setBounds(200,120,150,35);
        d.setBounds(200,160,150,35);
        e.setBounds(200,40,150,35);
        a.add(b);
        a.add(c);
        a.add(d);
        a.add(e);
        a.setSize(300,300);
        a.setBounds(600, 280, 600, 300);
        a.setLayout(null);
        a.setVisible(true);
    }

    //define abstract method actionPerformed() which will be called on button click
    public void actionPerformed(ActionEvent ae)     //pass action listener as a parameter
    {
        String userValue = textField1.getText();        //get user entered username from the textField1
        String passValue = textField2.getText();        //get user entered pasword from the textField2
        String isAdmin = null;
        int counter = 0;
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/essentialmode","root","");
            Statement selectStmt = connection.createStatement();
            ResultSet rs = selectStmt.executeQuery("SELECT username, password, isAdmin FROM users WHERE username = '"+userValue+"' AND password = '"+passValue+"'");
            while(rs.next())
            {
                counter = counter + 1;
                isAdmin = rs.getString(3);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if(counter>0){
            if (isAdmin.equals("true")){
                setVisible(false);
                adminThread();
            }else{
                userThread();
            }
        }else{
            //show error message
            JOptionPane.showMessageDialog(null, "نام کاربری یا رمز عبور وارد شده صحیح نیست");
        }
    }
}
//create the main class

class NewPage extends JFrame
{
    //constructor
    NewPage()
    {
        setDefaultCloseOperation(javax.swing.
                WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Welcome");
        setSize(400, 200);
    }
}
class Java11HttpClientExample {

    // one instance, reuse
    private final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .build();


    public void sendPost(String phone, int number) throws Exception {
        String prm = "[ { \"name\": \"Code\" } ]";
        String last = prm.replace("\"", "");
        System.out.println(last);
        // form parameters
        Map<Object, Object> data = new HashMap<>();

        HttpRequest request = HttpRequest.newBuilder()
                .POST(buildFormDataFromMap(data))
                .uri(URI.create("http://api.horamsh.ir:30120/authenticate/"+phone+"/"+number+""))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        // print status code

    }

    private static HttpRequest.BodyPublisher buildFormDataFromMap(Map<Object, Object> data) {
        var builder = new StringBuilder();
        for (Map.Entry<Object, Object> entry : data.entrySet()) {
            if (builder.length() > 0) {
                builder.append("&");
            }
            builder.append(URLEncoder.encode(entry.getKey().toString(), StandardCharsets.UTF_8));
            builder.append("=");
            builder.append(URLEncoder.encode(entry.getValue().toString(), StandardCharsets.UTF_8));
        }
        System.out.println(builder.toString());
        return HttpRequest.BodyPublishers.ofString(builder.toString());
    }

}

class MyFrame extends JFrame implements ActionListener {

    // Components of the Form
    private Container c;
    private JLabel title;
    private JLabel name;
    private JTextField tname;
    private JLabel lname;

    private JLabel type;
    private JComboBox na;
    private JTextField lsname;
    private JLabel mno;
    private JTextField tmno;
    private JLabel gender;
    private JComboBox male;
    private JRadioButton female;
    private ButtonGroup gengp;
    private JLabel dob;
    private JComboBox date;
    private JComboBox month;
    private JComboBox year;

    private JLabel add;
    private JTextArea tadd;
    private JCheckBox term;
    private JButton sub;
    private JButton reset;
    private JTextArea tout;
    private JLabel res;
    private JTextArea resadd;

    private String data[] = {
            "مرد", "زن"
    };
    private String dates[]
            = { "۱", "۲", "۳", "۴", "۵",
            "۶", "۷", "۸", "۹", "۱۰",
            "۱۱", "۱۲", "۱۳", "۱۴", "۱۵",
            "۱۶", "۱۷", "۱۸", "۱۹", "۲۰",
            "۲۱", "۲۲", "۲۳", "۲۴", "۲۵",
            "۲۶", "۲۷", "۲۸", "۲۹", "۳۰",
            "۳۱" };
    private String months[]
            = { "فروردین", "اردیبهشت", "خرداد", "تیر",
            "مرداد", "شهریور", "مهر", "آبان",
            "آذر", "دی", "بهمن", "اسفند" };
    private String years[] = {
            "۱۳۵۹", "۱۳۶۰",
            "۱۳۶۱", "۱۳۶۲", "۱۳۶۳", "۱۳۶۴",
            "۱۳۶۵", "۱۳۶۶", "۱۳۶۷", "۱۳۶۸",
            "۱۳۶۹",
            "۱۳۷۰", "۱۳۷۱", "۱۳۷۲", "۱۳۷۳",
            "۱۳۷۴", "۱۳۷۵", "۱۳۷۶", "۱۳۷۷",
            "۱۳۷۸", "۱۳۷۹", "۱۳۸۰", "۱۳۸۱",
            "۱۳۸۲", "۱۳۸۳"
    };

    private String naghsh[] = {
            "صاحب خانه", "مستاجر",
    };

    // constructor, to initialize the components
    // with default values.
    public MyFrame()
    {
        setTitle("ایجاد حساب کاربری");
        setBounds(600, 280, 850, 550);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        c = getContentPane();
        c.setLayout(null);

        title = new JLabel("ایجاد حساب کاربری");
        title.setFont(new Font("Arial", Font.PLAIN, 30));
        title.setSize(300, 30);
        title.setLocation(350, 30);
        c.add(title);

        name = new JLabel("نام:");
        name.setFont(new Font("Arial", Font.PLAIN, 20));
        name.setSize(100, 20);
        name.setLocation(730, 130);
        c.add(name);

        tname = new JTextField();
        tname.setFont(new Font("Arial", Font.PLAIN, 15));
        tname.setSize(100, 20);
        tname.setLocation(630, 130);
        tname.setHorizontalAlignment(SwingConstants.RIGHT);
        c.add(tname);

        lname = new JLabel("نام خانوادگی:");
        lname.setFont(new Font("Arial", Font.PLAIN, 20));
        lname.setSize(100, 20);
        lname.setLocation(730, 180);
        c.add(lname);

        lsname = new JTextField();
        lsname.setFont(new Font("Arial", Font.PLAIN, 15));
        lsname.setSize(100, 20);
        lsname.setLocation(630, 180);
        lsname.setHorizontalAlignment(SwingConstants.RIGHT);
        c.add(lsname);

        mno = new JLabel("جنسیت:");
        mno.setFont(new Font("Arial", Font.PLAIN, 20));
        mno.setSize(100, 20);
        mno.setLocation(730, 230);
        c.add(mno);

        male = new JComboBox(data);
        male.setFont(new Font("Arial", Font.PLAIN, 15));
        male.setSize(100, 20);
        male.setLocation(630, 230);
        c.add(male);

        dob = new JLabel("تاریخ تولد:");
        dob.setFont(new Font("Arial", Font.PLAIN, 20));
        dob.setSize(100, 20);
        dob.setLocation(730, 280);
        c.add(dob);

        date = new JComboBox(dates);
        date.setFont(new Font("Arial", Font.PLAIN, 15));
        date.setSize(70, 20);
        date.setLocation(660, 280);
        c.add(date);

        month = new JComboBox(months);
        month.setFont(new Font("Arial", Font.PLAIN, 15));
        month.setSize(80, 20);
        month.setLocation(580, 280);
        c.add(month);

        type = new JLabel("نقش:");
        type.setFont(new Font("Arial", Font.PLAIN, 20));
        type.setSize(100, 20);
        type.setLocation(730, 340);
        c.add(type);

        na = new JComboBox(naghsh);
        na.setFont(new Font("Arial", Font.PLAIN, 15));
        na.setSize(100, 20);
        na.setLocation(630, 340);
        c.add(na);

        tadd = new JTextArea();
        tadd.setFont(new Font("Arial", Font.PLAIN, 15));
        tadd.setSize(350, 355);
        tadd.setLocation(120, 90);
        tadd.setLineWrap(true);
        tadd.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        tadd.append("۱- شما فقط اختیار واحدی را دارید که مالک آن هستید, از قوانین مهم آپارتمان نشینی این است که بدانید حق دخالت در زندگی دیگر واحد ها را ندارید و نباید در زندگی همسایگان سرک بکشید.");
        tadd.append("\n");
        tadd.append("۲- حفظ نظافت آپارتمان از قوانین آپارتمان نشینی است. در مکان های مشترک مانند حیاط، راهرو و آسانسور آشغال نریزید.");
        tadd.append("\n");
        tadd.append("۳- از دیگر قوانین آپارتمان نشینی این است که اگر می خواهید به دلایلی از آب، برق یا گاز به مقدار زیادی استفاده کنید، مثلا می خواهید غذای نذری بپزید، از بقیه همسایه\u200Cها اجازه بگیرید. این عمل به خصوص زمانی اهمیت پیدا می کند که برای هر واحد قبض جدا وجود نداشته باشد در نتیجه سهم هر واحد بدون توجه به مقدار مصرف محاسبه شود.");
        tadd.append("\n");
        tadd.append("۴- در قوانین آپارتمان نشینی، نگهداری حیوانات خانگی در قسمت\u200Cهای مشترک مجاز نیست. همچنین در مورد نگه داری از حیوانات خانگی در واحد خودتان هم قوانینی وجود دارد. تنها زمانی مجاز به این کار هستید که نه تنها با قوانین آن آپارتمان تناقضی نداشته باشد بلکه بو، صدا و حضور حیوان خانگی در واحد شما به هیچ ترتیبی مزاحمتی برای دیگران ایجاد نکند.");
        tadd.setEditable(false);
        c.add(tadd);

        year = new JComboBox(years);
        year.setFont(new Font("Arial", Font.PLAIN, 15));
        year.setSize(70, 20);
        year.setLocation(510, 280);
        c.add(year);

        term = new JCheckBox("قوانین نوشته شده در کادر بالا را میپذیرم");
        term.setFont(new Font("Arial", Font.PLAIN, 15));
        term.setSize(250, 20);
        term.setLocation(250, 455);
        c.add(term);

        sub = new JButton("ثبت اطلاعات");
        sub.setFont(new Font("Arial", Font.PLAIN, 15));
        sub.setSize(100, 20);
        sub.setLocation(600, 400);
        sub.addActionListener(this);
        c.add(sub);

        /*reset = new JButton("Reset");
        reset.setFont(new Font("Arial", Font.PLAIN, 15));
        reset.setSize(100, 20);
        reset.setLocation(270, 450);
        reset.addActionListener(this);
        c.add(reset);
         */

        res = new JLabel("");
        res.setFont(new Font("Arial", Font.PLAIN, 20));
        res.setSize(500, 25);
        res.setLocation(100, 500);
        c.add(res);



        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/essentialmode","root","");
        } catch (SQLException ex) {

        }
        if (e.getSource() == sub) {
            if (term.isSelected()) {
                String esm = tname.getText();
                String famili = lsname.getText();
                String jensiat = (String)male.getSelectedItem();
                String saal = (String)year.getSelectedItem();
                String maah = (String)month.getSelectedItem();
                String rooz = (String)date.getSelectedItem();
                String role = (String)na.getSelectedItem();
                if(esm.length() > 1 && famili.length() > 1){
                    setVisible(false);
                    Statement st = null;
                    try {
                        st = conn.createStatement();
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                    String query = "INSERT INTO users (userName, Password, firstName, lastName, Gender, DOB, Role, isAdmin) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                    PreparedStatement preparedStmt = null;
                    Random random = new Random();
                    int number = random.nextInt(9999);
                    int passw = random.nextInt(9999999);
                    try {
                        preparedStmt = conn.prepareStatement(query);
                        preparedStmt.setString (1, "user" + number);
                        preparedStmt.setString (2, Integer.toString(passw));
                        preparedStmt.setString (3, esm);
                        preparedStmt.setString (4, famili);
                        preparedStmt.setString (5, jensiat);
                        preparedStmt.setString (6, saal+"/"+rooz+"/"+maah);
                        preparedStmt.setString (7, role);
                        preparedStmt.setString (8, "false");
                        preparedStmt.execute();
                        conn.close();
                        CreateLoginForm form = new CreateLoginForm();
                        form.setSize(300,100);  //set size of the frame
                        form.setVisible(true);
                        setVisible(false);
                        form.setBounds(600, 280, 300, 125);
                        JOptionPane.showMessageDialog(null, "حساب کاربری شما ساخته شد\n Username: "+"user" + number+"\n Password: "+ passw);
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                    // execute the preparedstatement
                }else {
                    JOptionPane.showMessageDialog(null, "نام و نام خانوادگی وارد شده صحیح نیست");
                }
            } else {
                JOptionPane.showMessageDialog(null, "لطفا قوانین را خوانده و تیک آن را فعال کنید");
            }
        }
    }
}

class LoginFrame extends JFrame implements ActionListener {

    Container container = getContentPane();
    JTextField userTextField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JButton loginButton = new JButton("ورود به حساب کاربری");
    JButton resetButton = new JButton("ثبت نام");

    LoginFrame() {
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();

    }

    public void setLayoutManager() {
        container.setLayout(null);
    }

    public void setLocationAndSize() {
        loginButton.setBounds(90, 65, 200, 30);
        resetButton.setBounds(90, 120, 200, 30);


    }

    public void addComponentsToContainer() {
        container.add(userTextField);
        container.add(passwordField);
        container.add(loginButton);
        container.add(resetButton);
    }

    public void addActionEvent() {
        loginButton.addActionListener(this);
        resetButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            CreateLoginForm form = new CreateLoginForm();
            form.setSize(300,100);  //set size of the frame
            form.setVisible(true);
            setVisible(false);
            form.setBounds(600, 280, 300, 125);
        }
        if (e.getSource() == resetButton) {
            setVisible(false);
            phoneNumber s = new phoneNumber();
        }
    }
}

class verify extends JFrame implements ActionListener {
    // Components of the Form
    private Container c;
    private JLabel title;
    private JLabel name;
    private JTextField tname;
    private JLabel lname;

    private JTextField lsname;
    private JLabel mno;
    private JTextField tmno;
    private JLabel gender;
    private JComboBox male;
    private JRadioButton female;
    private ButtonGroup gengp;
    private JLabel dob;
    private JComboBox date;
    private JComboBox month;
    private JComboBox year;

    public int num;

    private JLabel add;
    private JTextArea tadd;
    private JCheckBox term;
    private JButton sub;
    private JButton reset;
    private JTextArea tout;
    private JLabel res;
    private JTextArea resadd;

    private String data[] = {
            "مرد", "زن"
    };
    private String dates[]
            = { "+۹۸" };
    private String months[]
            = { "فروردین", "اردیبهشت", "خرداد", "تیر",
            "مرداد", "شهریور", "مهر", "آبان",
            "آذر", "دی", "بهمن", "اسفند" };
    private String years[] = {
            "۱۳۵۹", "۱۳۶۰",
            "۱۳۶۱", "۱۳۶۲", "۱۳۶۳", "۱۳۶۴",
            "۱۳۶۵", "۱۳۶۶", "۱۳۶۷", "۱۳۶۸",
            "۱۳۶۹",
            "۱۳۷۰", "۱۳۷۱", "۱۳۷۲", "۱۳۷۳",
            "۱۳۷۴", "۱۳۷۵", "۱۳۷۶", "۱۳۷۷",
            "۱۳۷۸", "۱۳۷۹", "۱۳۸۰", "۱۳۸۱",
            "۱۳۸۲", "۱۳۸۳"
    };

    // constructor, to initialize the components
    // with default values.
    public verify(int number)
    {
        setTitle("ثبت شماره تلفن همراه");
        setBounds(600, 280, 600, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        c = getContentPane();
        c.setLayout(null);

        name = new JLabel("کد ارسال شده:");
        name.setFont(new Font("Arial", Font.PLAIN, 20));
        name.setSize(100, 20);
        name.setLocation(330, 130);
        c.add(name);

        tname = new JTextField();
        tname.setFont(new Font("Arial", Font.PLAIN, 15));
        tname.setSize(100, 24);
        tname.setLocation(230, 130);
        c.add(tname);

        num = number;

        sub = new JButton("بررسی کد وارد شده");
        sub.setFont(new Font("Arial", Font.PLAIN, 15));
        sub.setSize(150, 20);
        sub.setLocation(210, 180);
        sub.addActionListener(this);
        c.add(sub);

        res = new JLabel("");
        res.setFont(new Font("Arial", Font.PLAIN, 20));
        res.setSize(500, 25);
        res.setLocation(100, 500);
        c.add(res);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == sub) {
            String Phone = (String)tname.getText();
            System.out.print(Phone);
            if (Phone.length() > 2){
                if (Integer.parseInt(Phone) == num){
                    setVisible(false);
                    MyFrame f = new MyFrame();
                }else{
                    JOptionPane.showMessageDialog(null, "کد وارد شده صحیح نیست");
                }
            }
            else {
                JOptionPane.showMessageDialog(null, "کد وارد شده صحیح نیست");
            }
        }
    }
}

class phoneNumber extends JFrame implements ActionListener {
    // Components of the Form
    private Container c;
    private JLabel title;
    private JLabel name;
    private JTextField tname;
    private JLabel lname;

    private JTextField lsname;
    private JLabel mno;
    private JTextField tmno;
    private JLabel gender;
    private JComboBox male;
    private JRadioButton female;
    private ButtonGroup gengp;
    private JLabel dob;
    private JComboBox date;
    private JComboBox month;
    private JComboBox year;

    private JLabel add;
    private JTextArea tadd;
    private JCheckBox term;
    private JButton sub;
    private JButton reset;
    private JTextArea tout;
    private JLabel res;
    private JTextArea resadd;

    private String data[] = {
            "مرد", "زن"
    };
    private String dates[]
            = { "+۹۸" };
    private String months[]
            = { "فروردین", "اردیبهشت", "خرداد", "تیر",
            "مرداد", "شهریور", "مهر", "آبان",
            "آذر", "دی", "بهمن", "اسفند" };
    private String years[] = {
            "۱۳۵۹", "۱۳۶۰",
            "۱۳۶۱", "۱۳۶۲", "۱۳۶۳", "۱۳۶۴",
            "۱۳۶۵", "۱۳۶۶", "۱۳۶۷", "۱۳۶۸",
            "۱۳۶۹",
            "۱۳۷۰", "۱۳۷۱", "۱۳۷۲", "۱۳۷۳",
            "۱۳۷۴", "۱۳۷۵", "۱۳۷۶", "۱۳۷۷",
            "۱۳۷۸", "۱۳۷۹", "۱۳۸۰", "۱۳۸۱",
            "۱۳۸۲", "۱۳۸۳"
    };

    // constructor, to initialize the components
    // with default values.
    public phoneNumber()
    {
        setTitle("تایید شماره تلفن همراه");
        setBounds(600, 280, 600, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        c = getContentPane();
        c.setLayout(null);

        title = new JLabel("تایید شماره تلفن همراه");
        title.setFont(new Font("Arial", Font.PLAIN, 30));
        title.setSize(250, 30);
        title.setLocation(200, 30);
        c.add(title);

        name = new JLabel("شماره موبایل:");
        name.setFont(new Font("Arial", Font.PLAIN, 20));
        name.setSize(100, 20);
        name.setLocation(330, 130);
        c.add(name);

        tname = new JTextField();
        tname.setFont(new Font("Arial", Font.PLAIN, 15));
        tname.setSize(100, 24);
        tname.setLocation(230, 130);
        c.add(tname);

        date = new JComboBox(dates);
        date.setFont(new Font("Arial", Font.PLAIN, 15));
        date.setSize(50, 20);
        date.setLocation(170, 130);
        c.add(date);

        sub = new JButton("ارسال کد تایید");
        sub.setFont(new Font("Arial", Font.PLAIN, 15));
        sub.setSize(150, 20);
        sub.setLocation(210, 180);
        sub.addActionListener(this);
        c.add(sub);

        res = new JLabel("");
        res.setFont(new Font("Arial", Font.PLAIN, 20));
        res.setSize(500, 25);
        res.setLocation(100, 500);
        c.add(res);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == sub) {
            String Phone = tname.getText();
            System.out.print(Phone);
            if (Phone.length() > 2){
                Java11HttpClientExample obj = new Java11HttpClientExample();
                Random random = new Random();
                int number = random.nextInt(9999999);
                System.out.print(number);
                try {
                    obj.sendPost(Phone, number);
                    setVisible(false);
                    verify g = new verify(number);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
            else {
                JOptionPane.showMessageDialog(null, "شماره وارد شده صحیح نیست");
            }
        }
    }
}

public class Main {
    public static void main(String[] args) throws Exception {
        LoginFrame frame = new LoginFrame();
        frame.setTitle("نرم افزار یکپارچه مدیریت آپارتمان");
       frame.setVisible(true);
        frame.setBounds(600, 280, 400, 250);
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
    }
}
