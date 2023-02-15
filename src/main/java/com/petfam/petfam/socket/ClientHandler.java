package com.petfam.petfam.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler extends Thread{

  private final Socket socket;
  private final BufferedReader in;
  private final PrintWriter out;

  public ClientHandler(Socket socket) throws IOException {
    this.socket = socket;
    this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    this.out = new PrintWriter(socket.getOutputStream(), true);
  }

  @Override
  public void run() {
    try {
      while (!socket.isClosed()) {
        if (in.ready()) {
          String message = in.readLine();
          System.out.println(message);
          if (message.equals("종료")) {
            Thread.currentThread().interrupt();
            socket.close();
            break;
          }
          broadcast(message);
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }


  private void broadcast(String message) {
    for (ClientHandler handler : SocketServerHandler.CLIENTS) {
      if (handler != this) {
        handler.out.println(message);
      }
    }
  }
}
