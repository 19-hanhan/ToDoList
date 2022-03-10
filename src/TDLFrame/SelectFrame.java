package TDLFrame;

import Dao.UpcomingDao;
import Entity.Upcoming;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class SelectFrame extends JFrame {

    private int width = 300;
    private int height = 100;
    private MainFrame mFrame; // 主窗口
    private String text;

    public SelectFrame(MainFrame mFrame, String title, String text) {

        // 传递变量初始化
        this.mFrame = mFrame;
        this.text = text; // 获取更新或删除的时间

        this.setTitle(title);

        initMainArea();

        this.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        this.setLocation(220, 200);
        this.setSize(width, height);
        this.setVisible(true);

    }

    JButton updateBtn = new JButton("update"); // 数据更新按钮
    JButton deleteBtn = new JButton("delete"); // 删除数据按钮
    JButton completeBtn = new JButton("complete"); // 完成按钮
    public void initMainArea() {

        // 设置背景样式
        this.getContentPane().setBackground(Color.decode("#eef5fd"));

        // 设置按钮样式
        updateBtn.setBorder(BorderFactory.createRaisedBevelBorder());
        updateBtn.setFont(new java.awt.Font("consola",  5,  18));
        deleteBtn.setBorder(BorderFactory.createRaisedBevelBorder());
        deleteBtn.setFont(new java.awt.Font("consola",  5,  18));
        completeBtn.setBorder(BorderFactory.createRaisedBevelBorder());
        completeBtn.setFont(new java.awt.Font("consola",  5,  18));

        // 添加按钮
        this.setLayout(new FlowLayout());
        this.add(updateBtn);
        this.add(deleteBtn);
        this.add(completeBtn);

        // 添加按钮监听器
        updateBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MessageFrame cFrame = new MessageFrame(mFrame, "update", text);
                SelectFrame.super.dispose();
            }
        });

        deleteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // 在数据库中删除数据
                UpcomingDao dao = new UpcomingDao();
                Upcoming item = new Upcoming();
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                try {
                    item.setTime(df.parse(text.split(" : ")[0]));
                } catch (ParseException parseException) {
                    parseException.printStackTrace();
                }
                dao.deleteUpcoming(item);

                // 刷新列表
                mFrame.refleshFrame();

                // 关闭窗口
                SelectFrame.super.dispose();

            }
        });

        completeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SelectFrame.super.dispose();
            }
        });

    }

}
