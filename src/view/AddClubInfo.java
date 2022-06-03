package view;

import model.entity.Club;
import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;
import service.ClubService;
import util.MessageUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddClubInfo extends JFrame implements ActionListener {
    static AddClubInfo instance;
    public static final String[] categoryOptions = {"文化艺术类", "学术科技类", "志愿公益类", "创新创业类", "自律互助类", "体育类"};
    JCheckBox category1;
    JCheckBox category2;
    JCheckBox category3;
    JCheckBox category4;
    JCheckBox category5;
    JCheckBox category6;
    private JTextField inputName;
    private JButton Save, Cancel, Return;//1.保存按钮，2.取消按钮,3.返回按钮
    private Font font1, font2, font3;//字体设置

    private AddClubInfo() {
        this.setIconImage(Toolkit.getDefaultToolkit().getImage("src/images/logo.png"));
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");// 使用windows外观
        } catch (Exception e) {
            e.printStackTrace();
        }
        setTitle("添加社团信息");//设置容器标题
        setSize(650, 350);//设置容器大小
        setLocationRelativeTo(null);//将容器显示在屏幕中央
        initUI();//布局界面函数
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);//点击右上角的关闭，只关闭本窗口，不影响住窗口
        setResizable(true);//设置窗口大小可以改变
    }

    public static AddClubInfo getInstance() {
        if (instance == null) {
            instance = new AddClubInfo();
        }
        return instance;
    }

    public static void main(String[] args) {
        getInstance().reset();
    }

    public void reset() {
        clearText();
        setVisible(true);
    }

    public void clearText() {
        inputName.setText("");//清空输入框
        category1.setSelected(false);//取消选择
        category2.setSelected(false);//取消选择
        category3.setSelected(false);//取消选择
        category4.setSelected(false);//取消选择
        category5.setSelected(false);//取消选择
        category6.setSelected(false);//取消选择
    }

    private void initUI() {
        setLayout(null);//以绝对布局的方式布局
        font1 = new Font("楷体", Font.PLAIN, 20);
        font3 = new Font("微软雅黑", Font.PLAIN, 18);
        //设置各个组件的位置，字体
        int left = 120;
        JLabel labelName = new JLabel("社团名称:");
        labelName.setBounds(left, 30, 150, 50);
        labelName.setFont(font1);
        inputName = new JTextField();
        inputName.setBounds(new Rectangle(left + 100, 40, 150, 30));
        inputName.setFont(font1);


        JLabel labelCategory = new JLabel("社团分类：");
        labelCategory.setBounds(left, 100, 150, 50);
        labelCategory.setFont(font1);
        //创建兴趣复选框面板
        JPanel checkbox = new JPanel();
        checkbox.setLayout(new GridLayout(2, 3));
        category1 = new JCheckBox(categoryOptions[0]);
        category1.setFont(font3);
        checkbox.add(category1);

        category2 = new JCheckBox(categoryOptions[1]);
        category2.setFont(font3);
        checkbox.add(category2);
        System.out.println(category2.getText());
        category3 = new JCheckBox(categoryOptions[2]);
        category3.setFont(font3);
        checkbox.add(category3);

        category4 = new JCheckBox(categoryOptions[3]);
        category4.setFont(font3);
        checkbox.add(category4);

        category5 = new JCheckBox(categoryOptions[4]);
        category5.setFont(font3);
        checkbox.add(category5);

        category6 = new JCheckBox(categoryOptions[5]);
        category6.setFont(font3);
        checkbox.add(category6);

        checkbox.setBounds(left + 100, 100, 400, 80);


        font2 = new Font("标楷体", Font.PLAIN, 15);
        Save = new JButton("保存");
        Save.setUI(new BEButtonUI()
                .setNormalColor(BEButtonUI.NormalColor.blue));//设置按钮的背景颜色
        Save.setBounds(new Rectangle(left, 250, 70, 30));
        Save.setFont(font2);
        Cancel = new JButton("取消");
        Cancel.setUI(new BEButtonUI()
                .setNormalColor(BEButtonUI.NormalColor.red));
        Cancel.setBounds(new Rectangle(left + 100, 250, 70, 30));
        Cancel.setFont(font2);
        Return = new JButton("返回");
        Return.setUI(new BEButtonUI()
                .setNormalColor(BEButtonUI.NormalColor.green));
        Return.setBounds(new Rectangle(left + 200, 250, 70, 30));
        Return.setFont(font2);

        //将各个组将添加到容器里面
        add(labelName);
        add(inputName);


        add(labelCategory);
        add(checkbox);

        add(Save);
        add(Cancel);
        add(Return);

        //为各个组件添加事件监听器
        inputName.addActionListener(this);
        category1.addActionListener(this);
        category2.addActionListener(this);
        category3.addActionListener(this);
        category4.addActionListener(this);
        category5.addActionListener(this);
        category6.addActionListener(this);
        Save.addActionListener(this);
        Cancel.addActionListener(this);
        Return.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();//得到按钮上的标签
        switch (cmd) {
            case "保存":
                if (inputName.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "社团名称不能为空！", "消息提示", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if (!category1.isSelected() && !category2.isSelected() &&
                        !category3.isSelected() && !category4.isSelected() &&
                        !category5.isSelected() && !category6.isSelected()) {
                    JOptionPane.showMessageDialog(null, "社团分类不能为空！", "消息提示", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                StringBuilder stringBuilder = new StringBuilder();
                if (category1.isSelected()) {
                    stringBuilder.append(category1.getText()).append(",");
                }
                if (category2.isSelected()) {
                    stringBuilder.append(category2.getText()).append(",");
                }
                if (category3.isSelected()) {
                    stringBuilder.append(category3.getText()).append(",");
                }
                if (category4.isSelected()) {
                    stringBuilder.append(category4.getText()).append(",");
                }
                if (category5.isSelected()) {
                    stringBuilder.append(category5.getText()).append(",");
                }
                if (category6.isSelected()) {
                    stringBuilder.append(category6.getText()).append(",");
                }
                stringBuilder.deleteCharAt(stringBuilder.length() - 1);
                Club club = new Club().setMemberCount(0).setCategory(stringBuilder.toString()).setName(inputName.getText());

                try {
                    ClubService.AddClub(club);
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
