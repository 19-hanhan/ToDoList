package TDLFrame;

import Dao.UpcomingDao;
import Entity.Upcoming;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MessageFrame extends JFrame {

    private int width = 300;
    private int height = 150;
    private MainFrame mFrame;
    private String title;
    private String text;

    public MessageFrame(MainFrame mFrame, String title, String text) {

        this.mFrame = mFrame;
        this.text = text;
        this.title = title;
        this.setTitle(title);

        initMainArea();

        this.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        this.setLocation(220, 200);
        this.setSize(width, height);
        this.setVisible(true);

    }

    public void initMainArea() {

        // 三个面板
        JPanel jpTime = new JPanel();
        jpTime.setBackground(Color.decode("#eef1fd"));
        JPanel jpContext = new JPanel();
        jpContext.setBackground(Color.decode("#eef5fd"));
        JPanel jpButton = new JPanel();
        jpButton.setBackground(Color.decode("#eef9fd"));

        // 文本框
        JLabel jlTime = new JLabel("时间");
        JTextField jtfTime = new JTextField(20);
        JLabel jlContext = new JLabel("内容");
        JTextField jtfContext = new JTextField(20);
        if (title.equals("create")) {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            jtfTime.setText(df.format(new Date(System.currentTimeMillis())));
            jtfContext.setText("hanhan's Upcoming ~");
        }
        else if (title.equals("update")) {
            jtfTime.setText(text.split(" : ")[0]);
            jtfContext.setText(text.split(" : ")[1]);
        }

        // 按钮
        JButton btnArgee = new JButton("确认");
        btnArgee.setBorder(BorderFactory.createRaisedBevelBorder());
        btnArgee.setFont(new java.awt.Font("consola",  5,  18));

        JButton btnCancel = new JButton("取消");
        btnCancel.setBorder(BorderFactory.createRaisedBevelBorder());
        btnCancel.setFont(new java.awt.Font("consola",  5,  18));

        btnArgee.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                System.out.println(title);
                if (title.equals("create")) {
                    createUpcoming(jtfTime, jtfContext);
                }
                else if (title.equals("update")) {
                    updateUpcoming(jtfTime, jtfContext);
                }

                // 刷新界面
                mFrame.refleshFrame();

                // 关闭窗口
                MessageFrame.super.dispose();
            }
        });

        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MessageFrame.super.dispose();
            }
        });

        // 放置面板
        this.setLayout(new GridLayout(3, 1));
        jpTime.add(jlTime); jpTime.add(jtfTime);
        jpContext.add(jlContext); jpContext.add(jtfContext);
        jpButton.add(btnArgee); jpButton.add(btnCancel);

        // 将面板放入窗口
        this.add(jpTime);
        this.add(jpContext);
        this.add(jpButton);

    }

    public void createUpcoming(JTextField jtfTime, JTextField jtfContext) {

        // 获取新待办数据
        String textTime = jtfTime.getText();
        String textContext = jtfContext.getText();

        // 构造待办对象
        Upcoming item = new Upcoming();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            item.setTime(df.parse(textTime));
        } catch (ParseException parseException) {
            parseException.printStackTrace();
        }
        item.setContext(textContext);

        // 更新待办内容
        UpcomingDao dao = new UpcomingDao();
        dao.insertUpcoming(item);

    }

    public void updateUpcoming(JTextField jtfTime, JTextField jtfContext) {


        // 获取新待办数据
        String textTime = jtfTime.getText();
        String textContext = jtfContext.getText();

        // 构造待办对象
        Upcoming item = new Upcoming();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            item.setTime(df.parse(textTime));
        } catch (ParseException parseException) {
            parseException.printStackTrace();
        }
        item.setContext(textContext);

        // 插入新待办
        UpcomingDao dao = new UpcomingDao();
        dao.updateUpcoming(item);
    }

}
