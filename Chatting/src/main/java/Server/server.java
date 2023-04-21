package Server;

import javafx.scene.control.Alert;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.sql.*;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class server {

    public static void main(String[] args) {
        ArrayList<Socket> clients = new ArrayList<>();
        HashMap<Socket, String> clientNameList = new HashMap<Socket, String>();
        try (ServerSocket serversocket = new ServerSocket(5000)) {
            System.out.println("Server is started...");
            while (true) {
                Socket socket = serversocket.accept();
                clients.add(socket);
                ThreadServer ThreadServer = new ThreadServer(socket, clients, clientNameList);
                ThreadServer.start();
            }
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }
    }
}


class ThreadServer extends Thread {

    private Socket socket;
    private ArrayList<Socket> clients;
    private HashMap<Socket, String> clientNameList;

    private Connection connection;

    public ThreadServer(Socket socket, ArrayList<Socket> clients, HashMap<Socket, String> clientNameList) {
        this.socket = socket;
        this.clients = clients;
        this.clientNameList = clientNameList;
    }

    @Override
    public void run() {
        try {
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            while (true) {
                String outputString = input.readLine();

                System.out.println(outputString);
                // login
                if (outputString.startsWith("[+-]login")){
                    String[] in = outputString.split("-==-");
                    if(login(in[1],in[2])==true){
                        PrintWriter sender = new PrintWriter(socket.getOutputStream(), true);
                        sender.println("登录成功");

                    }
                    else{
                        PrintWriter sender = new PrintWriter(socket.getOutputStream(), true);
                        sender.println("登录失败");

                    }



                    continue;
                }

                // register
                if(outputString.startsWith("[+-]register")){
                    String[] in = outputString.split("-==-");
//                    register(in[1],in[2]);
                    if(register(in[1],in[2])==true){
                        PrintWriter sender = new PrintWriter(socket.getOutputStream(), true);
                        sender.println("注册成功");

                    }
                    else
                    {
                        PrintWriter sender = new PrintWriter(socket.getOutputStream(), true);
                        sender.println("注册失败");


                    }
                    continue;
                }


                if (outputString.equals("logout")) {
                    throw new SocketException();
                }
                if (!clientNameList.containsKey(socket)) {
                    String[] messageString = outputString.split(":", 2);
                    clientNameList.put(socket, messageString[0]);
                    System.out.println(messageString[0] + messageString[1]);
                    showMessageToAllClients(socket, messageString[0] + messageString[1]);
                } else {
                    System.out.println(outputString);
                    showMessageToAllClients(socket, outputString);
                }
            }
        } catch (SocketException e) {
            String printMessage = clientNameList.get(socket) + " left the chat room";
            System.out.println(printMessage);
            showMessageToAllClients(socket, printMessage);
            clients.remove(socket);
            clientNameList.remove(socket);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showMessageToAllClients(Socket sender, String outputString) {
        Socket socket;
        PrintWriter printWriter;
        int i = 0;
        while (i < clients.size()) {
            socket = clients.get(i);
            i++;
            try {
//                if (socket != sender) {
                    printWriter = new PrintWriter(socket.getOutputStream(), true);
                    printWriter.println(outputString);
//                }
            } catch (IOException ex) {
                System.out.println(ex);
            }
        }
    }

    public void Connection() {
        try {
            // 连接数据库
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
            Statement statement = connection.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean login(String username, String password) {
        try {
            // 查询用户信息
            Connection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM usermessage WHERE username=? AND password=?");
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                // 登录成功
                //System.out.println("登录成功,欢迎回来"+username);
                //showAlert(Alert.AlertType.INFORMATION, "登录成功", "欢迎回来, " + username + "!");
                return true;
            } else {
                // 登录失败
                //showAlert(Alert.AlertType.ERROR, "登录失败", "用户名或密码错误！");
                //System.out.println("登录失败,用户名或密码错误！");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    private boolean register(String username, String password){
        try {
            Connection();
            // 查询用户名是否已存在
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM usermessage WHERE username=?");
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                // 用户名已存在
                System.out.println("注册失败,用户名已存在！");
//                showAlert(Alert.AlertType.ERROR, "注册失败", "用户名已存在！");
                return false;
            } else {
                // 插入新用户信息
                PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO usermessage (username, password) VALUES (?, ?)");
                insertStatement.setString(1, username);
                insertStatement.setString(2, password);
                insertStatement.executeUpdate();
                System.out.println("恭喜您，注册成功！");
//                showAlert(Alert.AlertType.INFORMATION, "注册成功", "恭喜您，注册成功！");
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
