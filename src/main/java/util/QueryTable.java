package util;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

public class QueryTable {
    public static <E> List<E> queryTable(Class<E> eClass, String sql, Object... args) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            conn = JDBCUtils.getConnection();
            preparedStatement = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i + 1, args[i]);
            }
            resultSet = preparedStatement.executeQuery();
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int count = resultSetMetaData.getColumnCount();
            List<E> eArrayList = new ArrayList<>();
            while (resultSet.next()) {
                E e = eClass.getDeclaredConstructor().newInstance();
                for (int i = 0; i < count; i++) {
                    Object value = resultSet.getObject(i + 1);
                    String columnName = resultSetMetaData.getColumnName(i + 1);
                    Field[] fields = eClass.getDeclaredFields();
                    Field field=null;
                    for (Field f : fields) {
                        if (f.getAnnotation(Column.class).value().equals(columnName)) {
                            field =f;
                            break;
                        }
                    }
                    field.setAccessible(true);
                    field.set(e, value);
                }
                eArrayList.add(e);
            }
            return eArrayList;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, preparedStatement, resultSet);
        }
        return null;
    }
}
