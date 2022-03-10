package TDLFrame;

import TDLPane.MainLayeredPane;

import javax.swing.*;

public class MainFrame extends JFrame {

    MainLayeredPane ulp; // 定义分层窗格

    public MainFrame() { // 无参构造方法

        // 初始化分层窗格
        ulp = new MainLayeredPane();
        ulp.setmFrame(this);

        // 给窗体添加组件
        this.add(ulp);

    }

    public void refleshFrame() {

        ulp.refleshJlist();

    } // 刷新窗口

}
