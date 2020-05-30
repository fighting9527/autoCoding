package com.example.autocoding.demo;

import org.springframework.util.StringUtils;
import java.lang.reflect.Field;

/**
 * @author caoqi
 * 2020/5/29 4:48 PM
 */
public class SQLUtil {

    public static void main(String[] args) {
        System.out.println(createTable(User.class));
    }

    public static String createTable(Class<?> cl) {
        Table table = cl.getAnnotation(Table.class);
        if (table == null) {
            return "";
        }

        StringBuilder sql = new StringBuilder("create table ");
        String tableName;
        if (StringUtils.isEmpty(table.value())) {
            tableName = cl.getName().toUpperCase();
        } else {
            tableName = table.value();
        }
        sql.append(tableName + "(");

        for (Field field : cl.getDeclaredFields()) {
            Column column = field.getAnnotation(Column.class);
            if (column == null) {
                continue;
            }

            String columnName;
            if (StringUtils.isEmpty(column.value())) {
                columnName = field.getName();
            } else {
                columnName = column.value();
            }
            sql.append("\n  " + columnName + " " + column.type());

            PrimaryKey primaryKey = field.getAnnotation(PrimaryKey.class);
            if (primaryKey == null) {
                sql.append(",");
                continue;
            }

            sql.append(" primary key,");
        }

        String result = sql.toString().substring(0, sql.toString().length() - 1);
        result += "\n);";
        return result;
    }
}
