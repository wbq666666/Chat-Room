package Client;// Java implementation for multithreaded chat client
// Save file as Client.Client.java

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.SocketException;

import ChatMajorView.ChatViewController;
import ChatMajorView.Main_2;
import Client.login.Main;
import Client.login.testController;
import javafx.application.Platform;
import javafx.scene.control.Alert;

public class Client {
    public Socket client;
    public static Main login;
    public static PrintWriter sender = null;
    public static testController controller_ = null;
    public static String username = null;
    public static Main_2 main;
    public static ChatViewController controller_2 = null;

    public void start(String[] args) {
        String name = "empty";
        String reply = "empty";
        Scanner sc = new Scanner(System.in);
//        System.out.println("Enter your username used to login: ");
//        reply = sc.nextLine();
        name = reply;

        try (Socket socket = new Socket("localhost", 5000)) {
            PrintWriter cout = new PrintWriter(socket.getOutputStream(), true); // send msg

            ThreadClient threadClient = new ThreadClient(socket);
            new Thread(threadClient).start(); // start thread to receive message


            if(sender==null) {
                sender = cout;
                Platform.setImplicitExit(false);
                login = new Main();

                new Thread(()->login.run(args)).start();
            }


//            cout.println(reply + ": has joined chat-room.");
            do {
                String message = (name + " : ");
                reply = sc.nextLine();
                if (reply.equals("logout")) {
                    cout.println("logout");
                    break;
                }
                cout.println(message + reply); // send msg
            } while (!reply.equals("logout"));
        } catch (Exception e) {
//            System.out.println(e.getStackTrace());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Client c = new Client();
        c.start(args);
    }
}


/**
 * Thread for clients
 */
class ThreadClient implements Runnable {

    private Socket socket;
    private BufferedReader cin;

    public ThreadClient(Socket socket) throws IOException {
        this.socket = socket;
        this.cin = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    public void popMsg(String title,String content){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                //更新JavaFX的主线程的代码放在此处
                Client.controller_.showAlert(Alert.AlertType.INFORMATION, title, content);
            }
        });
    }

    @Override
    public void run() {
        try {
            while (true) {
                String message = cin.readLine();
                if (message.equals("注册成功")){
                    popMsg("注册成功","快去登录吧");
                    continue;
                }
                if (message.equals("注册失败")){
                    popMsg("注册失败","注册失败，请检查用户名");
                    continue;
                }
                if (message.equals("登录成功")){
//                    Client.login.stop();
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Client.main = new Main_2();
                                Client.main.newStage();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                    continue;
                }
                if (message.equals("登录失败")){
                    popMsg("登录失败","登录失败，请检查用户名和密码");
                    continue;
                }

                System.out.println(message);
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        Client.controller_2.addMsg(message);
                    }
                });
            }
        } catch (SocketException e) {
            System.out.println("You left the chat-room");
        } catch (IOException exception) {
            System.out.println(exception);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                cin.close();
            } catch (Exception exception) {
                System.out.println(exception);
            }
        }
    }
}