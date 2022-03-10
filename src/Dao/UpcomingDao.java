package Dao;

import Entity.Upcoming;
import Util.JDBCUtil;
import Util.ListResultSetProcessor;

import java.util.ArrayList;

public class UpcomingDao {

    // 功能函数
    public int createUpcoming() {
        String sql = "CREATE TABLE Upcoming(" +
                "time datetime primary key," +
                "context text," +
                "complete tinyint(1)" +
                ")";
        int numOfColumn = JDBCUtil.update(sql);

        return numOfColumn;
    }

    public int insertUpcoming(Upcoming item) { // 插入待办

        String sql = "insert into Upcoming(time, context, complete) value(?,?,?)";
        int numOfColumn = JDBCUtil.update(sql, item.getTime(), item.getContext(), item.getComplete());

        return numOfColumn;

    }

    public int updateUpcoming(Upcoming item) { // 更新待办信息

        String sql = "update Upcoming set context = ? where time = ?";
        int numOfColumn = JDBCUtil.update(sql, item.getContext(), item.getTime());

        return numOfColumn;

    }

    public int deleteUpcoming(Upcoming item) { // 删除待办信息

        String sql = "delete from Upcoming where time = ?";
        int numOfColumn = JDBCUtil.update(sql, item.getTime());

        return numOfColumn;

    }

    public ArrayList<Upcoming> findAllUpcoming() { // 查询所有待办

        String sql = "select * from Upcoming";

        ArrayList<Upcoming> ls = JDBCUtil.query(sql, new ListResultSetProcessor());

        return ls;

    }

}
