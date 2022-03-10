package Util;

import java.sql.ResultSet;

public interface ResultSetHandler<T> {

    T getData(ResultSet resSet); // 将结果集合成为<T>返回

}
