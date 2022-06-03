package view;

import model.entity.Club;
import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;
import service.ClubService;
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

public class QueryClubInfo extends JFrame implements ActionListener {
    private static QueryClubInfo instance;
    private final JScrollPane panel;
    //进行具体操作的按钮
    private final JButton normalOrder;
    private final JButton reverseOrder;
    private final JButton query;
    private final JButton queryAll;
    private final JButton queryMembers;
    private final JButton updateBtn;
    private final JButton deleteBtn;
    private final JButton exportExcelBtn;
    private final JButton backBtn;
    private final JTable table;
    public String[] columnNames = {"社团id", "社团名称", "社团类型", "社团总人数"};
    public DefaultTableModel model = null;//默认的表格控制模型
    JLabel labelName;
    JTextField inputName;

    private QueryClubInfo() {
        super("社团信息查询统计");
        this.setIconImage(Toolkit.getDefaultToolkit().getImage("src/images/logo.png"));
        this.setSize(1040, 680);
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");// 使用windows外观
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.setResizable(false);

        Font font = new Font("楷体", Font.BOLD, 12);

        labelName = new JLabel("社团名称：");
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

        queryAll = new JButton("查询所有社团");
        queryAll.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.lightBlue));
        queryAll.setBounds(700, 30, 150, 30);
        queryAll.setForeground(Color.blue);

        table = new JTable();
        panel = new JScrollPane();//设置滚动条
        panel.setViewportView(table);
        panel.setBounds(42, 136, 950, 420);


        queryMembers = new JButton("查询社团成员");
        queryMembers.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.red));
        queryMembers.setBounds(400, 570, 110, 30);

        normalOrder = new JButton("升序排序");
        normalOrder.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.normal));
        normalOrder.setBounds(300, 570, 90, 30);

        reverseOrder = new JButton("降序排序");
        reverseOrder.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.normal));

        reverseOrder.setBounds(200, 570, 90, 30);

        updateBtn = new JButton("更新");
        updateBtn.setUI(new BEButtonUI()
                .setNormalColor(BEButtonUI.NormalColor.green));
        updateBtn.setBounds(524, 570, 90, 30);

        deleteBtn = new JButton("删除");
        deleteBtn.setUI(new BEButtonUI()
                .setNormalColor(BEButtonUI.NormalColor.green));
        deleteBtn.setBounds(644, 570, 90, 30);

        exportExcelBtn = new JButton("导出到Excel");
        exportExcelBtn.setUI(new BEButtonUI()
                .setNormalColor(BEButtonUI.NormalColor.green));
        exportExcelBtn.setBounds(764, 570, 120, 30);
        backBtn = new JButton("返回");
        backBtn.setUI(new BEButtonUI()
                .setNormalColor(BEButtonUI.NormalColor.green));
        backBtn.setBounds(908, 570, 90, 30);

        query.addActionListener(this);
        queryAll.addActionListener(this);
        updateBtn.addActionListener(this);
        deleteBtn.addActionListener(this);
        exportExcelBtn.addActionListener(this);
        backBtn.addActionListener(this);
        queryMembers.addActionListener(this);
        reverseOrder.addActionListener(this);
        normalOrder.addActionListener(this);

        this.getContentPane().setLayout(null);
        this.getContentPane().add(queryMembers);
        this.getContentPane().add(panel);
        this.getContentPane().add(labelName);
        this.getContentPane().add(inputName);
        this.getContentPane().add(updateBtn);
        this.getContentPane().add(deleteBtn);
        this.getContentPane().add(exportExcelBtn);
        this.getContentPane().add(backBtn);
        this.getContentPane().add(reverseOrder);
        this.getContentPane().add(normalOrder);
        this.getContentPane().add(query);
        this.getContentPane().add(queryAll);


        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }

    public static QueryClubInfo getInstance() {
        if (instance == null) {
            instance = new QueryClubInfo();
        }
        return instance;
    }

    public static void main(String[] args) {
        getInstance().reset();
    }

    public void reset() {
        showAll();
        inputName.setText("");
        setVisible(true);
    }

    /**
     * 根据List数据生成对应的Object数据
     */
    public Object[][] getData(List<Club> clubs) {
        if (clubs.size() > 0) {
            Object[][] data = new Object[clubs.size()][columnNames.length];
            for (int i = 0; i < clubs.size(); i++) {
                Club s = clubs.get(i);
                Object[] a = {s.getId(), s.getName(), s.getCategory(), s.getMemberCount()};
                data[i] = a;//把数组的值赋给二维数组的一行
            }
            return data;
        }
        return null;
    }


    public void showAll() {
        List<Club> list = ClubService.QueryAllClub();
        Object[][] data = getData(list);
        createTable(data);
    }

    /**
     * 初始化表格数据
     */
    public void createTable(Object[][] data) {
        if (model == null) {
            DefaultTableCellRenderer r = new DefaultTableCellRenderer();
            r.setHorizontalAlignment(JLabel.CENTER);
            table.setDefaultRenderer(Object.class, r);
        }

        //让id不可编辑
        model = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column != 0;
            }
        };
        table.setModel(model);
        table.setRowHeight(20);
    }

    private void doQuery() {
        String name = inputName.getText();
        try {
            createTable(getData(ClubService.QueryClubsByName(name)));
        } catch (RuntimeException e) {
            e.printStackTrace();
            MessageUtil.Warning(e.getMessage());
        }
    }

    Club getClubByChoose() {
        Club c = new Club();
        int row = table.getSelectedRow();
        if (row == -1) {
            throw new RuntimeException("请选择你要操作的对象");
        }
        int id = Integer.parseInt(table.getValueAt(row, 0).toString());//会抛运行时异常
        String name = table.getValueAt(row, 1).toString();
        String category = table.getValueAt(row, 2).toString();
        int count = Integer.parseInt(table.getValueAt(row, 3).toString());
        c.setId(id).setName(name).setCategory(category).setMemberCount(count);
        System.out.println(c);
        return c;
    }

    private void doQueryMember() {
        int row = table.getSelectedRow();
        if (row == -1) {
            throw new RuntimeException("请选择需要查看的社团");
        }
        int id = Integer.parseInt(table.getValueAt(row, 0).toString());//会抛运行时异常
        QueryMemberInfo.getInstance(id).reset();
    }

    private void doUpdate() {
        try {
            ClubService.UpdateClub(getClubByChoose());
        } catch (RuntimeException e) {
            e.printStackTrace();
            MessageUtil.Warning(e.getMessage());
            return;
        }
        MessageUtil.Info("更新成功");
    }

    private void doDelete() {
        try {
            ClubService.DeleteClub(getClubByChoose());
        } catch (RuntimeException e) {
            e.printStackTrace();
            MessageUtil.Warning(e.getMessage());
            showAll();
            return;
        }
        MessageUtil.Info("删除成功");
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
            System.out.println("=============>开始查询所有学生...");
            showAll();
        } else if (button.equals(normalOrder)) {
            System.out.println("===============>开始顺序排序");
            try {
                createTable(getData(ClubService.QueryAllClubByOrder(true)));
            } catch (RuntimeException exception) {
                exception.printStackTrace();
                MessageUtil.Warning(exception.getMessage());
            }
        } else if (button.equals(reverseOrder)) {
            System.out.println("=======================>开始降序排序");
            try {
                createTable(getData(ClubService.QueryAllClubByOrder(false)));
            } catch (RuntimeException exception) {
                exception.printStackTrace();
                MessageUtil.Warning(exception.getMessage());
            }
        } else if (button.equals(queryMembers)) {
            System.out.println("==============================>开始查询社团的成员");
            try {
                doQueryMember();
            } catch (RuntimeException exception) {
                exception.printStackTrace();
                MessageUtil.Warning(exception.getMessage());
            }
        } else if (button.equals(updateBtn)) {
            System.out.println("===============================>开始更新社团信息");
            doUpdate();
            showAll();
        } else if (button.equals(deleteBtn)) {
            System.out.println("===========================>开始删除操作");
            doDelete();
            showAll();
        } else if (button.equals(exportExcelBtn)) {
            System.out.println("===========================>开始导出Excle信息");
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

    private void doExportExcle() throws IOException {
        ExcleUtil.ExportClubToExcleUI();
    }
}
