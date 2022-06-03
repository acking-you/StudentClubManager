package view;

import javax.swing.*;
import java.awt.*;


public class AboutSystem extends JFrame {

    private static AboutSystem instance;

    private AboutSystem() {
        setTitle("关于本系统");//设置容器标题
        setSize(600, 450);//设置容器大小
        setLocationRelativeTo(null);//将容器显示在屏幕中央
        initUI();
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);//点击右上角的关闭，只关闭本窗口，不影响住窗口
        setResizable(true);//设置窗口大小可以改变
    }

    public static AboutSystem getInstance() {
        if (instance == null) {
            instance = new AboutSystem();
        }
        return instance;
    }

    public static void main(String[] args) {
        new AboutSystem();
    }

    public void reset() {
        setVisible(true);
    }

    private void initUI() {
        setLayout(null);//以绝对布局的方式布局
        this.setIconImage(Toolkit.getDefaultToolkit().getImage("src/images/logo.png"));
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");// 使用windows外观
        } catch (Exception e) {
            e.printStackTrace();
        }
        Font font = new Font("微软雅黑", Font.PLAIN, 20);
        JLabel jlabel1 = new JLabel("学生管理系统");
        jlabel1.setBounds(230, 50, 150, 50);
        jlabel1.setFont(font);
        JLabel jlabel2 = new JLabel("班级：20计科2班");
        jlabel2.setBounds(200, 100, 450, 50);
        jlabel2.setFont(font);
        JLabel jLabel3 = new JLabel("作者：刘剑波");
        jLabel3.setBounds(200, 150, 450, 50);
        jLabel3.setFont(font);
        JLabel jLbel4 = new JLabel("完成日期：2022-6-2");
        jLbel4.setBounds(200, 200, 450, 50);
        jLbel4.setFont(font);

        add(jlabel1);
        add(jlabel2);
        add(jLabel3);
        add(jLbel4);
    }
}
