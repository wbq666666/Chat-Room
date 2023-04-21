//package Server;
//import java.sql.*;
//
//public class DB {
//
//    // 这个是保存数据库连接的，这样就可以在这个类的其他方法中使用这个连接了，只要import这个类，然后 DB.conn 就可以使用了这个连接了
//    public static Connection conn = null;
//
//    // MySQL 8.0 以上版本 - JDBC 驱动名及数据库 URL
//    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
//    static final String DB_URL = "jdbc:mysql://localhost:3306/test?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
//
//    // 数据库的用户名与密码，需要根据自己的设置
//    static final String USER = "root";
//    static final String PASS = "root";
//
//    DB(String[] args) {
//        Statement stmt = null;
//        try {
//            // 注册 JDBC 驱动
//            Class.forName(JDBC_DRIVER);
//
//            // 打开链接
//            System.out.println("连接数据库...");
//            conn = DriverManager.getConnection(DB_URL, USER, PASS);
//            // 将conn这个保存到这个类的conn中
//
//            // ======= 下面的代码就是可以在其他类中使用的代码了，只要import这个类，然后 DB.conn 就可以使用了这个连接了
//
//            // 在开头import 这个DB类
//
//            // 执行查询
//             Connection conn = DB.conn; // 从DB类中获取conn
//             System.out.println(" 实例化Statement对象...");
//             stmt = conn.createStatement();
//             String sql;
//             sql = "SELECT id, name, url FROM websites";
//             ResultSet rs = stmt.executeQuery(sql);
//
//            // // 展开结果集数据库
//             while(rs.next()){
//            // // 通过字段检索
//             int id = rs.getInt("id");
//             String name = rs.getString("name");
//             String url = rs.getString("url");
//
//            // // 输出数据
//             System.out.print("ID: " + id);
//             System.out.print(", 站点名称: " + name);
//             System.out.print(", 站点 URL: " + url);
//             System.out.print("\n");
//             }
//             rs.close();
//             stmt.close();
//
//            // 关闭数据库 以防要用到
//             conn.close();
//        } catch (SQLException se) {
//            // 处理 JDBC 错误
//            se.printStackTrace();
//        } catch (Exception e) {
//            // 处理 Class.forName 错误
//            e.printStackTrace();
//        } finally {
//            // 关闭资源
//            try {
//                if (stmt != null)
//                    stmt.close();
//            } catch (SQLException se2) {
//            } // 什么都不做
//            try {
//                if (conn != null)
//                    conn.close();
//            } catch (SQLException se) {
//                se.printStackTrace();
//            }
//        }
//        System.out.println("Goodbye!");
//    }
//}