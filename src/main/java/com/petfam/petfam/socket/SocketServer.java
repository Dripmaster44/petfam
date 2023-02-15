package com.petfam.petfam.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {

  public static void main(String[] args) {
    try (ServerSocket serverSocket = new ServerSocket(8888)) {
      System.out.println("서버가 준비 완료되었습니다.");
      while (true) {
        Socket socket = serverSocket.accept();
        System.out.println("클라이언트가 접속했습니다:" + socket.getInetAddress().getHostAddress());

        ClientHandler handler = new ClientHandler(socket);
        SocketServerHandler.CLIENTS.add(handler);
        handler.start();

        Input input = new Input(socket);
        Output output = new Output(socket);

        input.start();
        output.start();

      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}

