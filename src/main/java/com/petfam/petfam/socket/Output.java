package com.petfam.petfam.socket;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

class Output extends Thread {

  private final Socket socket;
  private final PrintWriter out;

  Output(Socket socket) throws IOException {
    this.socket = socket;
    this.out = new PrintWriter(socket.getOutputStream(), true);
  }

  @Override
  public void run() {
    try (Scanner scanner = new Scanner(System.in)) {
      while (!socket.isClosed() && !Thread.currentThread().isInterrupted()) {
        String input = scanner.nextLine();
        out.println(input);
      }
    }
  }
}
