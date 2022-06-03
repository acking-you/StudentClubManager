package view;

import model.entity.ClubMember;
import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;
import service.ClubMemberService;
import util.ExcleUtil;
import util.MessageUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

public class QueryMemberInfo extends JFrame implements ActionListener {
    private static QueryMemberInfo instance;
    private final JScrollPane panel;
    //进行具体操作的按钮
    private final JButton query;
    private final JButton queryAll;
    private final JButton queryClubsBtn;
    private final JButton addBtn;
    private final JButton updateBtn;
    private final JButton deleteBtn;
    private final JButton exportExcelBtn;
    private final JButton importExcleBtn;
    private final JButton backBtn;
    private final JTable table;
    public String[] columnNames = {"成员所属id", "学号", "姓名", "性别", "年龄", "专业", "兴趣爱好"};
    public DefaultTableModel model = null;//默认的表格控制模型
    JLabel labelName;
    JTextField inputName;
    private int clubId;

    private QueryMemberInfo() {
        this.setIconImage(Toolkit.getDefaultToolkit().getImage("src/images/logo.png"));
        this.setSize(1140, 680);
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");// 使用windows外观
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.setResizable(false);

        Font font = new Font("楷体", Font.BOLD, 12);

        labelName = new JLabel("学生姓名：");
        labelName.setBounds(180, 30, 100, 30);
        labelName.setFont(font);

        inputName = new JTextField();
        inputName.setBounds(250, 30, 200, 30);


        query = new JButton("查询");
        query.setUI(new BEButtonUI()
                .setNormalColor(BEButtonUI.NormalColor.lightBlue));
        query.setBounds(500, 30, 95, 30);

        query.setForeground(Color.blue);
        ImageIcon icon1 = new ImageIcon("src/images/query2.png");
        query.setIcon(icon1);

        queryAll = new JButton("查询所有成员");
        queryAll.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.lightBlue));
        queryAll.setBounds(700, 30, 150, 30);
        queryAll.setForeground(Color.blue);

        table = new JTable();
        panel = new JScrollPane();//设置滚动条
        panel.setViewportView(table);
        panel.setBounds(42, 136, 950, 420);


        queryClubsBtn = new JButton("查询所参加社团");
        queryClubsBtn.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.red));
        queryClubsBtn.setBounds(100, 570, 120, 30);

        updateBtn = new JButton("更新");
        updateBtn.setUI(new BEButtonUI()
                .setNormalColor(BEButtonUI.NormalColor.green));
        updateBtn.setBounds(270, 570, 90, 30);

        addBtn = new JButton("添加");
        addBtn.setUI(new BEButtonUI()
                .setNormalColor(BEButtonUI.NormalColor.green));
        addBtn.setBounds(410, 570, 90, 30);

        deleteBtn = new JButton("删除");
        deleteBtn.setUI(new BEButtonUI()
                .setNormalColor(BEButtonUI.NormalColor.green));
        deleteBtn.setBounds(550, 570, 90, 30);

        importExcleBtn = new JButton("从Excel导入数据");
        importExcleBtn.setUI(new BEButtonUI()
                .setNormalColor(BEButtonUI.NormalColor.green));
        importExcleBtn.setBounds(690, 570, 120, 30);

        exportExcelBtn = new JButton("导出到Excel");
        exportExcelBtn.setUI(new BEButtonUI()
                .setNormalColor(BEButtonUI.NormalColor.green));
        exportExcelBtn.setBounds(860, 570, 120, 30);

        backBtn = new JButton("返回");
        backBtn.setUI(new BEButtonUI()
                .setNormalColor(BEButtonUI.NormalColor.green));
        backBtn.setBounds(1020, 570, 90, 30);

        query.addActionListener(this);
        queryAll.addActionListener(this);
        updateBtn.addActionListener(this);
        addBtn.addActionListener(this);
        deleteBtn.addActionListener(this);
        exportExcelBtn.addActionListener(this);
        backBtn.addActionListener(this);
        queryClubsBtn.addActionListener(this);
        importExcleBtn.addActionListener(this);

        this.getContentPane().setLayout(null);
        this.getContentPane().add(addBtn);
        this.getContentPane().add(importExcleBtn);
        this.getContentPane().add(queryClubsBtn);
        this.getContentPane().add(panel);
        this.getContentPane().add(labelName);
        this.getContentPane().add(inputName);
        this.getContentPane().add(updateBtn);
        this.getContentPane().add(deleteBtn);
        this.getContentPane().add(exportExcelBtn);
        this.getContentPane().add(backBtn);
        this.getContentPane().add(query);
        this.getContentPane().add(queryAll);


        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }

    public static QueryMemberInfo getInstance(int clubId) {
        if (instance == null) {
            instance = new QueryMemberInfo();
        }
        instance.setClubId(clubId);
        return instance;
    }

    public static void main(String[] args) {
        getInstance(49).reset();
    }

    public void setClubId(int clubId) {
        this.clubId = clubId;
    }

    //在每次getInstance后需要reset
    public void reset() {
        showAll();
        inputName.setText("");
        //根据所选id设置对应的标题
        setTitle(String.format("社团ID为%d的成员", clubId));
        setVisible(true);
    }

    // 根据List数据生成对应的Object数据
    public Object[][] getData(List<ClubMember> clubMembers) {
        if (clubMembers.size() > 0) {
            Object[][] data = new Object[clubMembers.size()][columnNames.length];
            for (int i = 0; i < clubMembers.size(); i++) {
                ClubMember s = clubMembers.get(i);
                Object[] a = {s.getId(), s.getStudentId(), s.getName(), s.getSex(), s.getAge(), s.getMajor(), s.getInterest()};
                data[i] = a;//把数组的值赋给二维数组的一行
            }
            return data;
        }
        return null;
    }

    ClubMember getClubMemberByChoose() {
        ClubMember c = new ClubMember();
        int row = table.getSelectedRow();
        if (row == -1) {
            throw new RuntimeException("请选择你要操作的对象");
        }
        int id = Integer.parseInt(table.getValueAt(row, 0).toString());
        String studentId = table.getValueAt(row, 1).toString();//会抛运行时异常
        String name = table.getValueAt(row, 2).toString();
        String sex = table.getValueAt(row, 3).toString();
        int age = Integer.parseInt(table.getValueAt(row, 4).toString());
        String major = table.getValueAt(row, 5).toString();
        String interest = table.getValueAt(row, 6).toString();
        c.setId(id).setStudentId(studentId).setName(name).setSex(sex).setAge(age)
                .setMajor(major).setInterest(interest);
        System.out.println(c);
        return c;
    }

    public void showAll() {
        List<ClubMember> list = ClubMemberService.QueryClubMembersByClubId(clubId);
        System.out.println(list);
        Object[][] data = getData(list);
        createTable(data);
    }

    public void createTable(Object[][] data) {
        if (model == null) {
            DefaultTableCellRenderer r = new DefaultTableCellRenderer();
            r.setHorizontalAlignment(JLabel.CENTER);
            table.setDefaultRenderer(Object.class, r);
        }

        //让id字段不可编辑
        model = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column != 0;
            }
        };
        table.setModel(model);
        table.setRowHeight(20);
    }


    //所有对应的回调
    private void doQuery() {
        String name = inputName.getText();
        try {
            createTable(getData(ClubMemberService.QueryClubMemberByName(clubId, name)));
        } catch (RuntimeException e) {
            e.printStackTrace();
            MessageUtil.Warning(e.getMessage());
        }
    }


    //TODO
    private void doQueryClubs() {

    }

    private void doUpdate() {
        try {
            ClubMemberService.UpdateClubMember(getClubMemberByChoose());
        } catch (RuntimeException e) {
            e.printStackTrace();
            MessageUtil.Warning(e.getMessage());
            return;
        }
        MessageUtil.Info("更新成功");
    }

    private void doDelete() {
        try {
            ClubMemberService.DeleteClubMemberById(clubId, getClubMemberByChoose().getId());
        } catch (RuntimeException e) {
            e.printStackTrace();
            MessageUtil.Warning(e.getMessage());
            showAll();
            return;
        }
        MessageUtil.Info("删除成功");
    }

    private void doAdd() {
        AddMemberInfo.getInstance(clubId).reset();
    }

    private void doImportExcle() throws IOException {
        ExcleUtil.ImportClubMemberInfoByExcleUI(clubId);
    }

    private void doExportExcle() throws IOException {
        ExcleUtil.ExportClubMemberToExcleUI(clubId);
    }

    /**
     * 按钮事件
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();

        if (button.equals(query)) {
            System.out.println("=============>开始根据名称模糊查询...");
            doQuery();
        } else if (button.equals(queryAll)) {
            System.out.println("=============>开始查询所有成员...");
            showAll();
        } else if (button.equals(queryClubsBtn)) {
            System.out.println("==============================>开始查询该所参加的社团");
            doQueryClubs();
        } else if (button.equals(updateBtn)) {
            System.out.println("===============================>开始更新成员信息");
            doUpdate();
            showAll();
        } else if (button.equals(addBtn)) {
            System.out.println("===============================>开始增加成员");
            doAdd();
            showAll();
        } else if (button.equals(deleteBtn)) {
            System.out.println("=============================>开始删除操作");
            doDelete();
            showAll();
        } else if (button.equals(importExcleBtn)) {
            System.out.println("=============================>开始导入Excle信息");
            try {
                doImportExcle();
            } catch (IOException | RuntimeException exception) {
                exception.printStackTrace();
                MessageUtil.Warning(exception.getMessage());
            }
        } else if (button.equals(exportExcelBtn)) {
            System.out.println("=============================>开始导出Excle信息");
            try {
                doExportExcle();
            } catch (IOException | RuntimeException exception) {
                exception.printStackTrace();
                MessageUtil.Warning(exception.getMessage());
            }
        } else if (button.equals(backBtn)) {
            setVisible(false);
        }
    }
}
