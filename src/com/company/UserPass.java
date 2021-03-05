package com.company;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashSet;
import java.util.Scanner;

public class UserPass implements Runnable {

    public static HashSet<String> users = new HashSet<String>();

    private Runner runner;
    // исходящее сообщение
    private PrintWriter outMessage;
    // входящее собщение
    private Scanner inMessage;
    private static final String HOST = "localhost";
    private static final int PORT = 3443;
    // клиентский сокет
    private Socket clientSocket = null;
    // количество клиента в чате, статичное поле
    private static int clients_count = 0;

    // конструктор, который принимает клиентский сокет и сервер
    public UserPass(Socket socket, Runner runner) {
        try {
            clients_count++;
            this.runner = runner;
            this.clientSocket = socket;
            this.outMessage = new PrintWriter(socket.getOutputStream());
            this.inMessage = new Scanner(socket.getInputStream());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            /*while (true) {
                // сервер отправляет сообщение
                server.sendMessageToAllClients("Новый участник вошёл в чат!");
                server.sendMessageToAllClients("Клиентов в чате = " + clients_count);
                break;
            }*/
            int waiting=0;
            while (true) {
                // Если от клиента пришло сообщение
                if (inMessage.hasNext()) {
                    String clientMessage = inMessage.nextLine();

                  //  clientMessage.indexOf("log:")
                    if(clientMessage.contains("log:") && clientMessage.contains("pass:"))
                    System.out.println(
                            "Log:"+ clientMessage.substring(4,clientMessage.indexOf("pass:")-1)+
                            "\npasss:"+clientMessage.substring(clientMessage.indexOf("pass:")+5,clientMessage.length())
                    );




                    // если клиент отправляет данное сообщение, то цикл прерывается и
                    // клиент выходит из чата
                    /*if (clientMessage.equalsIgnoreCase("##session##end##")) {
                        break;
                    }*/
                    // выводим в консоль сообщение (для теста)
                   // System.out.println(clientMessage);
                    // отправляем данное сообщение всем клиентам
                   // server.sendMessageToAllClients(clientMessage);
                }else {
                    System.out.println("NOPE");
                    waiting++;
                    if(waiting>10)
                    break;
                }

                // останавливаем выполнение потока на 100 мс
                Thread.sleep(200);
            }
            System.out.println("EndCon");
        }
        catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        finally {
           // this.close();
        }
    }
}
