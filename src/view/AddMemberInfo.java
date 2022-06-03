package view;

import model.entity.ClubMember;
import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;
import service.ClubMemberService;
import util.MessageUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class AddMemberInfo extends JFrame implements ActionListener {
    static AddMemberInfo instance = null;
    public static final String[] majors =
            {"计算机科学与技术", "软件工程", "土木工程", "光电信息科学与工程",
                    "法学", "数学与应用数学", "人工智能", "日语"};
    private JTextField inputStudentId, inputName, inputAge;
    private ButtonGroup sexGroup;//创建按钮组
    private JComboBox<String> comboBoxMajor;//省份下拉列表框
    private JCheckBox hobby1, hobby2, hobby3, hobby4, hobby5, hobby6;
    private int clubId;
    //界面容器和工具按钮
    private JButton Save, Cancel, Return;//1.保存按钮，2.取消按钮,3.返回按钮
    private JLabel labelStudentId, labelName, labelSex, labelAge, labelMajor, labelInterest, Sphone;//1.显示“编号”文字，
    private JRadioButton radioBtnMale, radioBtnFemale;//性别单选按钮
    private JPanel checkbox;//创建兴趣复选框面板
    private Font font1, font2, font3;//字体设置

    private AddMemberInfo() {
        this.setIconImage(Toolkit.getDefaultToolkit().getImage("src/images/logo.png"));
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");// 使用windows外观
        } catch (Exception e) {
            e.printStackTrace();
        }
        setTitle("添加学生信息");//设置容器标题
        setSize(850, 650);//设置容器大小
        setLocationRelativeTo(null);//将容器显示在屏幕中央
        initUI();//布局界面函数
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);//点击右上角的关闭，只关闭本窗口，不影响住窗口
        setResizable(true);//设置窗口大小可以改变
    }

    public static AddMemberInfo getInstance(int clubId) {
        if (instance == null) {
            instance = new AddMemberInfo();
        }
        instance.clubId = clubId;
        return instance;
    }

    public static void main(String[] args) {
        getInstance(48).reset();
    }

    public void reset() {
        clearText();
        setVisible(true);
    }

    public void clearText() {
        inputStudentId.setText("");//清空输入框
        inputName.setText("");//清空输入框
        sexGroup.clearSelection();//清空输入框
        inputAge.setText("");//清空输入框
        comboBoxMajor.setSelectedIndex(0);//取消选择
        hobby1.setSelected(false);//取消选择
        hobby2.setSelected(false);//取消选择
        hobby3.setSelected(false);//取消选择
        hobby4.setSelected(false);//取消选择
        hobby5.setSelected(false);//取消选择
        hobby6.setSelected(false);//取消选择
    }

    private void initUI() {
        setLayout(null);//以绝对布局的方式布局
        font1 = new Font("楷体", Font.PLAIN, 20);
        font3 = new Font("微软雅黑", Font.PLAIN, 18);
        //设置各个组件的位置，字体
        labelStudentId = new JLabel("学号:");//设置Sid的文字
        labelStudentId.setBounds(250, 30, 150, 50);//设置文在所在的位置
        labelStudentId.setFont(font1);//设置字体
        inputStudentId = new JTextField();//设置输入框
        inputStudentId.setBounds(new Rectangle(350, 40, 150, 30));//设置位置
        inputStudentId.setFont(font1);//设置字体

        labelName = new JLabel("姓名:");
        labelName.setBounds(250, 90, 150, 50);
        labelName.setFont(font1);
        inputName = new JTextField();
        inputName.setBounds(new Rectangle(350, 100, 150, 30));
        inputName.setFont(font1);


        labelSex = new JLabel("性别:");
        labelSex.setBounds(250, 150, 150, 50);
        labelSex.setFont(font1);
        sexGroup = new ButtonGroup();
        radioBtnMale = new JRadioButton("男");
        radioBtnMale.setBounds(350, 160, 50, 30);
        radioBtnMale.setFont(font1);
        radioBtnFemale = new JRadioButton("女");
        radioBtnFemale.setBounds(450, 160, 50, 30);
        radioBtnFemale.setFont(font1);


        labelAge = new JLabel("年龄:");
        labelAge.setBounds(250, 210, 150, 50);
        labelAge.setFont(font1);
        inputAge = new JTextField();
        inputAge.setBounds(350, 220, 150, 30);
        inputAge.setFont(font1);

        labelMajor = new JLabel("所在专业:");
        labelMajor.setBounds(250, 270, 150, 50);
        labelMajor.setFont(font1);
        comboBoxMajor = new JComboBox<>(majors);
        comboBoxMajor.setBounds(350, 280, 250, 30);
        comboBoxMajor.setFont(font1);


        labelInterest = new JLabel("兴趣爱好:");
        labelInterest.setBounds(250, 330, 200, 50);
        labelInterest.setFont(font1);


        hobby1 = new JCheckBox("看C++ Primer");
        hobby1.setFont(font3);

        hobby2 = new JCheckBox("玩Rust游戏");
        hobby2.setFont(font3);

        hobby3 = new JCheckBox("泡一杯Java");
        hobby3.setFont(font3);

        hobby4 = new JCheckBox("打Go鼠");
        hobby4.setFont(font3);

        hobby5 = new JCheckBox("捉Python");
        hobby5.setFont(font3);

        hobby6 = new JCheckBox("脚踢JS");
        hobby6.setFont(font3);

        checkbox = new JPanel();
        checkbox.setLayout(new GridLayout(2, 3));
        checkbox.add(hobby1);
        checkbox.add(hobby2);
        checkbox.add(hobby3);
        checkbox.add(hobby4);
        checkbox.add(hobby5);
        checkbox.add(hobby6);
        checkbox.setBounds(350, 325, 500, 130);


        //下面的操作按钮
        font2 = new Font("标楷体", Font.PLAIN, 15);
        Save = new JButton("保存");
        Save.setUI(new BEButtonUI()
                .setNormalColor(BEButtonUI.NormalColor.blue));//设置按钮的背景颜色
        Save.setBounds(new Rectangle(250, 520, 70, 30));
        Save.setFont(font2);
        Cancel = new JButton("取消");
        Cancel.setUI(new BEButtonUI()
                .setNormalColor(BEButtonUI.NormalColor.red));
        Cancel.setBounds(new Rectangle(350, 520, 70, 30));
        Cancel.setFont(font2);
        Return = new JButton("返回");
        Return.setUI(new BEButtonUI()
                .setNormalColor(BEButtonUI.NormalColor.green));
        Return.setBounds(new Rectangle(450, 520, 70, 30));
        Return.setFont(font2);

        //组件添加到容器
        add(labelStudentId);
        add(inputStudentId);
        add(labelName);
        add(inputName);
        sexGroup.add(radioBtnMale);
        sexGroup.add(radioBtnFemale);
        add(labelSex);
        add(radioBtnMale);
        add(radioBtnFemale);
        add(labelAge);
        add(inputAge);
        add(labelMajor);
        add(comboBoxMajor);
        add(labelInterest);
        add(checkbox);
        add(Save);
        add(Cancel);
        add(Return);

        //为各个组件添加事件监听器
        inputStudentId.addActionListener(this);
        inputName.addActionListener(this);
        inputAge.addActionListener(this);
        Save.addActionListener(this);
        Cancel.addActionListener(this);
        Return.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();//得到按钮上的标签
        switch (cmd) {
            case "保存":
                String sid = inputStudentId.getText();
                String name = inputName.getText();
                int age;
                try {
                    age = Integer.parseInt(inputAge.getText());
                } catch (RuntimeException exception) {
                    exception.printStackTrace();
                    MessageUtil.Warning("年龄请输入数字");
                    return;
                }
                if (sid.equals("")) {
                    MessageUtil.Warning("学号不能为空");
                    return;
                }
                if (name.equals("")) {
                    MessageUtil.Warning("姓名不能为空");
                    return;
                }
                if (!radioBtnMale.isSelected() && !radioBtnFemale.isSelected()) {//isSelected()，如果按钮被选中返回true
                    MessageUtil.Warning("请选择性别");
                    return;
                }
                if (!hobby1.isSelected() && !hobby2.isSelected() &&
                        !hobby3.isSelected() && !hobby4.isSelected() &&
                        !hobby5.isSelected() && !hobby6.isSelected()) {
                    MessageUtil.Warning("兴趣爱好不能为空");
                    return;
                }


                ClubMember clubMember = new ClubMember();
                clubMember.setStudentId(sid);
                clubMember.setName(name);
                clubMember.setAge(age);

                if (radioBtnMale.isSelected()) {//获取单选按钮选中的情况，保存到student中
                    clubMember.setSex(radioBtnMale.getText());
                } else {
                    clubMember.setSex(radioBtnFemale.getText());
                }
                clubMember.setMajor((String) comboBoxMajor.getSelectedItem());//得到下拉列表中的内容保存到student中


                StringBuilder hobby = new StringBuilder();
                if (hobby1.isSelected()) {
                    hobby.append(hobby1.getText()).append("，");
                }
                if (hobby2.isSelected()) {
                    hobby.append(hobby2.getText()).append("，");
                }
                if (hobby3.isSelected()) {
                    hobby.append(hobby3.getText()).append("，");
                }
                if (hobby4.isSelected()) {
                    hobby.append(hobby4.getText()).append("，");
                }
                if (hobby5.isSelected()) {
                    hobby.append(hobby5.getText()).append("，");
                }
                if (hobby6.isSelected()) {
                    hobby.append(hobby6.getText()).append("，");
                }
                if (hobby.charAt(hobby.length() - 1) == '，') {//判断复选框最后一个选择了什么，然后去掉“，”
                    hobby.deleteCharAt(hobby.length() - 1);
                }

                clubMember.setInterest(hobby.toString());

                System.out.println(clubMember);
                try {
                    ClubMemberService.AddClubMember(clubId, clubMember);
                } catch (RuntimeException exception) {
                    exception.printStackTrace();
                    MessageUtil.Warning(exception.getMessage());
                    return;
                }
                MessageUtil.Info("添加成功");
                setVisible(false);
                break;
            case "取消":
                clearText();//点击取消按钮，清空输入框和选择

                setVisible(false);
                break;
            case "返回":
                setVisible(false);
                break;
        }
    }
}
