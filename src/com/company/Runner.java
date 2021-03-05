package com.company;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Runner implements Runnable {
    private ServerSocket serverSocket;
    private Socket socket;
    private Scanner scanner;
    private PrintWriter printWriter;


    public Runner(int port ) {
      //  CommandSetter=0;
        try {
            serverSocket = new ServerSocket(port);


            /*socket = serverSocket.accept();
           // socket.getLocalAddress()
            scanner = new Scanner(socket.getInputStream());
            System.out.println( socket.getLocalAddress());
            printWriter = new PrintWriter(socket.getOutputStream(),true);*/
//printWriter.println("bla");/*

            Socket  clientSocket = serverSocket.accept();
            // создаём обработчик клиента, который подключился к серверу
            // this - это наш сервер
            UserPass client = new UserPass(clientSocket, this);
            //clients.add(client);
            // каждое подключение клиента обрабатываем в новом потоке
            new Thread(client).start();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while(true){


            /*int foo;

            try {
               String Date= scanner.nextLine().toString();
               System.out.println("date:"+Date);
            }
            catch (NumberFormatException e)
            {
                e.printStackTrace();
               // CommandSetter = 0;
            }*/
            Socket  clientSocket = null;
            try {
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                e.printStackTrace();
            }
            // создаём обработчик клиента, который подключился к серверу
            // this - это наш сервер
            UserPass client = new UserPass(clientSocket, this);
            //clients.add(client);
            // каждое подключение клиента обрабатываем в новом потоке
            new Thread(client).start();


        }
    }
}
