package Util;

import Entity.Upcoming;

import java.sql.ResultSet;
import java.util.ArrayList;

public class ListResultSetProcessor implements ResultSetHandler<ArrayList<Upcoming>>{

    @Override
    public ArrayList<Upcoming> getData(ResultSet resSet) {

        ArrayList<Upcoming> ls = new ArrayList<Upcoming>();

        try {

            while (resSet.next()) { // 将结果集信息录入待办对象

                Upcoming item = new Upcoming();
                item.setTime(resSet.getTimestamp("time"));
                item.setContext(resSet.getString("context"));
                item.setComplete(resSet.getBoolean("complete"));

                ls.add(item);

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return ls;

    }

}
