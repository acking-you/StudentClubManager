package view;

import util.ExcleUtil;
import util.LinkLabel;
import util.MessageUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;


/*
 * 登陆成功后主界面
 */
public class MainMenu extends JFrame implements ActionListener {
    static String IMG_PATH = "src/images/background";
    private static MainMenu instance;
    ImageIcon[] images = null;
    private JMenuItem addClubInfo, queryClubInfo, modifyPassword, Reload;
    private JMenuItem importExcel, outputExcel;
    private JMenuItem aboutSystem, help;

    private MainMenu() {
        super("欢迎使用社团信息管理系统 made by L_B__");
        initImages();
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");// 使用windows外观
        } catch (Exception e) {
            e.printStackTrace();
        }
        setSize(1200, 785);
        initUI();
    }

    public static MainMenu getInstance() {
        if (instance == null) {
            instance = new MainMenu();
        }
        return instance;
    }

    public static void main(String[] args) {
        getInstance().reset();
    }

    public void reset() {
        setVisible(true);
    }

    private void initImages() {
        if (images == null) {
            File file = new File(IMG_PATH);
            File[] files = file.listFiles();    //列出所有图片并存下
            assert files != null;
            images = new ImageIcon[files.length];
            for (int i = 0; i < files.length; i++) {
                images[i] = new ImageIcon(files[i].getPath());
            }
        }
    }

    public void initUI() {
        setIconImage(Toolkit.getDefaultToolkit().getImage("src/images/logo.png"));
        Color bgk_color = new Color(0xE6F7FA);
        getContentPane().setBackground(bgk_color);
        //用于画出轮播壁纸的画板
        MyJPanel myJPanel = new MyJPanel();
        myJPanel.setBounds(0, 220, 1200, 500);
        setResizable(false);//窗口不可变
        getContentPane().add(myJPanel);


        //各钟组件的添加
        JLabel welcome_text = new JLabel("欢迎使用社团信息管理系统");
        Font font_welcome = new Font("楷体", Font.BOLD, 40);
        welcome_text.setFont(font_welcome);
        welcome_text.setBounds(350, 50, 500, 50);
        welcome_text.setForeground(Color.DARK_GRAY);
        getContentPane().add(welcome_text);

        JLabel jLabel1 = new JLabel();
        Font font_author = new Font("楷体", Font.PLAIN, 14);
        jLabel1.setFont(font_author);
        jLabel1.setBounds(20, 20, 50, 20);
        jLabel1.setForeground(Color.gray);
        jLabel1.setText("作者：");
        getContentPane().add(jLabel1);

        JPanel myWebsiteLink = LinkLabel.getInstance(" @ L_B__", "https://acking-you.gitee.io/");
        myWebsiteLink.setBackground(bgk_color);
        myWebsiteLink.setFont(new Font("Arial", Font.PLAIN, 14));
        myWebsiteLink.setBounds(50, 18, 50, 20);
        getContentPane().add(myWebsiteLink);

        LinkLabel myGithub = LinkLabel.getInstance(new ImageIcon("src/images/github.png"), "https://github.com/ACking-you");
        myGithub.setBounds(550, 17, 24, 24);
        getContentPane().add(myGithub);

        LinkLabel myBilibili = LinkLabel.getInstance(new ImageIcon("src/images/bilibili.png"), "https://space.bilibili.com/24264499");
        myBilibili.setBounds(590, 17, 24, 24);
        getContentPane().add(myBilibili);

        JLabel author_text = new JLabel();
        author_text.setText("本系统由 L_B__ 于 2022.06.3 完成");
        author_text.setFont(new Font("楷体", Font.PLAIN, 15));
        author_text.setForeground(Color.LIGHT_GRAY);
        author_text.setBounds(450, 100, 400, 20);
        getContentPane().add(author_text);

        JLabel webSite_description = new JLabel();
        webSite_description.setText("更多信息可进入我的个人网站查看：");
        webSite_description.setFont(new Font("楷体", Font.PLAIN, 14));
        webSite_description.setForeground(Color.GRAY);
        webSite_description.setBounds(420, 130, 230, 20);
        getContentPane().add(webSite_description);

        JPanel webSiteLink = LinkLabel.getInstance("acking-you.gitee.io", "https://acking-you.gitee.io/");
        webSiteLink.setBounds(640, 128, 120, 20);
        webSiteLink.setBackground(bgk_color);
        webSiteLink.setFont(new Font("Arial", Font.PLAIN, 15));
        getContentPane().add(webSiteLink);

        JLabel sponsor_text = new JLabel();
        sponsor_text.setText("喜欢的话可以扫描右边的二维码进行赞助^_^");
        sponsor_text.setFont(new Font("楷体", Font.PLAIN, 16));
        sponsor_text.setForeground(Color.GRAY);
        sponsor_text.setBounds(420, 160, 380, 20);
        getContentPane().add(sponsor_text);


        //wechat收款码
        JLabel wechat = new JLabel();
        wechat.setIcon(new ImageIcon("src/images/wechat.png"));
        wechat.setBounds(980, 50, 119, 119);
        getContentPane().add(wechat);

        //Menu的添加
        JMenu menuFile1 = new JMenu("基本操作(0)");
        Font font = new Font("楷体", Font.BOLD, 16);
        menuFile1.setFont(font);
        menuFile1.setIcon(new ImageIcon("src/images/icons/base1.png"));
        menuFile1.setMnemonic('O');
        add(menuFile1);

        JMenuBar menuBar = new JMenuBar();
        addClubInfo = new JMenuItem("增加", new ImageIcon("src/images/icons/add.png"));
        addClubInfo.setMnemonic('A');
        addClubInfo.setAccelerator(KeyStroke.getKeyStroke('A', InputEvent.CTRL_DOWN_MASK));
        menuFile1.add(addClubInfo);

        queryClubInfo = new JMenuItem("查询", new ImageIcon("src/images/icons/query.png"));
        queryClubInfo.setMnemonic('Q');
        queryClubInfo.setAccelerator(KeyStroke.getKeyStroke('Q', InputEvent.CTRL_DOWN_MASK));
        menuFile1.add(queryClubInfo);

        modifyPassword = new JMenuItem("密码修改", new ImageIcon("src/images/icons/modifyPassword.png"));
        modifyPassword.setMnemonic('M');
        modifyPassword.setAccelerator(KeyStroke.getKeyStroke('M', InputEvent.CTRL_DOWN_MASK));
        menuFile1.add(modifyPassword);

        Reload = new JMenuItem("重新登录", new ImageIcon("src/images/icons/reload.png"));
        Reload.setMnemonic('R');
        Reload.setAccelerator(KeyStroke.getKeyStroke('R', InputEvent.CTRL_DOWN_MASK));
        menuFile1.add(Reload);

        menuBar.add(menuFile1);

        //====================>导入导出
        JMenu menuFile2 = new JMenu("导入导出(1)");
        menuFile2.setFont(font);
        menuFile2.setIcon(new ImageIcon("src/images/icons/base2.png"));
        importExcel = new JMenuItem("从excel导入", new ImageIcon("src/images/icons/import.png"));
        importExcel.setMnemonic('C');
        importExcel.setAccelerator(KeyStroke.getKeyStroke('C', InputEvent.CTRL_DOWN_MASK));
        menuFile2.add(importExcel);


        outputExcel = new JMenuItem("导出excle", new ImageIcon("src/images/icons/import1.png"));
        outputExcel.setMnemonic('L');
        outputExcel.setAccelerator(KeyStroke.getKeyStroke('L', InputEvent.CTRL_DOWN_MASK));
        menuFile2.add(outputExcel);


        menuBar.add(menuFile2);
        JMenu menuFile3 = new JMenu("帮助(2)");
        menuFile3.setFont(font);
        menuFile3.setIcon(new ImageIcon("src/images/icons/base3.png"));
        aboutSystem = new JMenuItem("关于本系统", new ImageIcon("src/images/icons/about.png"));
        aboutSystem.setMnemonic('B');
        aboutSystem.setAccelerator(KeyStroke.getKeyStroke('B', InputEvent.CTRL_DOWN_MASK));
        menuFile3.add(aboutSystem);

        help = new JMenuItem("系统帮助", new ImageIcon("src/images/icons/help.png"));
        help.setMnemonic('H');
        help.setAccelerator(KeyStroke.getKeyStroke('H', InputEvent.CTRL_DOWN_MASK));
        menuFile3.add(help);
        menuBar.add(menuFile3);
        setJMenuBar(menuBar);

        //统一管理事件监听
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent arg0) {
                System.exit(1);
            }
        });
        addClubInfo.addActionListener(this);
        queryClubInfo.addActionListener(this);
        modifyPassword.addActionListener(this);
        importExcel.addActionListener(this);
        outputExcel.addActionListener(this);
        aboutSystem.addActionListener(this);
        help.addActionListener(this);
        Reload.addActionListener(this);

        //壁纸轮播
        final Timer timer = new Timer(8000, (e) -> {
            myJPanel.repaint();
        });
        timer.start();
        setLocation(40, 0);
    }

    //统一进行事件的监听回调
    @Override
    public void actionPerformed(ActionEvent event) {
        JMenuItem item = (JMenuItem) event.getSource();
        if (item.equals(addClubInfo)) {
            System.out.println("=======>用户选择了‘添加社团信息’菜单项");
            AddClubInfo.getInstance().reset();
        } else if (item.equals(queryClubInfo)) {
            System.out.println("=======>用户选择了‘查询社团信息’菜单项");
            QueryClubInfo.getInstance().reset();
        } else if (item.equals(modifyPassword)) {
            System.out.println("=======>用户选择了‘修改密码’菜单项");
            ModifyPasswordInfo.getInstance().reset();
        } else if (item.equals(importExcel)) {
            System.out.println("=======>用户选择了‘导入到excel’菜单项");
            try {
                ExcleUtil.ImportClubInfoByExcleUI();
            } catch (IOException | RuntimeException e) {
                e.printStackTrace();
                MessageUtil.Warning(e.getMessage());
            }
        } else if (item.equals(outputExcel)) {
            System.out.println("=======>用户选择了‘导出到excel’菜单项");
            try {
                ExcleUtil.ExportClubToExcleUI();
            } catch (IOException | RuntimeException e) {
                e.printStackTrace();
                MessageUtil.Warning(e.getMessage());
            }
        } else if (item.equals(aboutSystem)) {
            System.out.println("=======>用户选择了‘关于系统’菜单项");
            AboutSystem.getInstance().reset();
        } else if (item.equals(help)) {
            System.out.println("=======>用户选择了‘帮助’菜单项");
            Help.getInstance().reset();
        } else if (item.equals(Reload)) {
            System.out.println("=======>用户选择了‘重新登录'菜单项");
            setVisible(false);
            Login.getInstance().reset();
        }
    }

    //用于循环轮播的Panel
    class MyJPanel extends JPanel {
        int index = 0;

        @Override
        public void paint(Graphics g) {
            if (index > 100000)
                index = 0;
            super.paint(g);
            g.drawImage(images[index % images.length].getImage(), 0, 0, this);
            index++;
        }
    }
}
