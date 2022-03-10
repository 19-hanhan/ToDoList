package TDLPane;

import Dao.UpcomingDao;
import Entity.Upcoming;
import TDLFrame.MainFrame;
import TDLFrame.MessageFrame;
import TDLFrame.SelectFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class MainLayeredPane extends JLayeredPane {

    private MainFrame mFrame; // 主窗口

    private JScrollPane jsc = new JScrollPane(); // 滚动窗格
    private JList jls = new JList(); // 列表
    private String[] strs; // 数据库列表数据
    private JButton createBtn = new JButton("+"); // 添加待办按钮
    private JButton viewBtn = new JButton("√"); // 查看已完成待办按钮

    public MainLayeredPane() {

        initDefault();
        initDrag();

        this.add(jsc, JLayeredPane.DEFAULT_LAYER);
        this.add(viewBtn, JLayeredPane.DRAG_LAYER);
        this.add(createBtn, JLayeredPane.DRAG_LAYER);

    }

    public void initDefault() {

        initUpcomingList(); // 初始化待办列表

        // 列表初始化&样式制作
        jls = new JList(strs);
        jls.setFont(new java.awt.Font("consola",  1,  16));
        jls.setBackground(Color.decode("#eef5fd"));
        jls.setSelectionBackground(Color.decode("#bcdff5"));
        jls.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {

                String text = (String)jls.getSelectedValue();

                SelectFrame dFrame = new SelectFrame(mFrame, "select", text);

            }
        });

        // 将列表加入滚动窗格
        jsc = new JScrollPane(jls);
        jsc.setBounds(0, 0, 400, 600);
        jsc.setVisible(true);
        jsc.setOpaque(true);

    } // 初始化待办层

    public void initUpcomingList() {

        ArrayList<String> jlsContext = new ArrayList<String>(); // 列表内容

        // 读取数据库内容
        UpcomingDao dao = new UpcomingDao();
        dao.createUpcoming(); // 建表，有表则打印错误然后继续
        ArrayList<Upcoming> ls = dao.findAllUpcoming();

        // 将数据库信息插入列表
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (Upcoming item: ls) {
            jlsContext.add(df.format(item.getTime()) + " : " + item.getContext());
        }
        int size = jlsContext.size();
        strs = (String[])jlsContext.toArray(new String[size]);

    } // 初始化待办列表

    public void initDrag() {

        // 按钮初始化&美化
        createBtn.setBounds(320, 520, 60, 60);
        createBtn.setOpaque(true);
        createBtn.setBorder(BorderFactory.createRaisedBevelBorder());
        createBtn.setFont(new java.awt.Font("consola",  5,  30));
        createBtn.setBackground(Color.decode("#54aceb"));

        createBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                MessageFrame cFrame = new MessageFrame(mFrame, "create", "");

            }
        }); // 添加待办按钮监听器

        // 按钮初始化&美化
        viewBtn.setBounds(320, 440, 60, 60);
        viewBtn.setOpaque(true);
        viewBtn.setBorder(BorderFactory.createRaisedBevelBorder());
        viewBtn.setFont(new java.awt.Font("consola",  5,  30));
        viewBtn.setBackground(Color.decode("#54aceb"));

        viewBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                MessageFrame cFrame = new MessageFrame(mFrame, "create", "");

            }
        }); // 添加待办按钮监听器

    } // 初始化按钮层

    public void refleshJlist() {

        initUpcomingList();

        jls.setListData(strs);

    }

    // get/set方法
    public MainFrame getmFrame() { return mFrame; }
    public void setmFrame(MainFrame mFrame) { this.mFrame = mFrame; }

}
