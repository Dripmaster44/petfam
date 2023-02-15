package com.petfam.petfam.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

class Input extends Thread {

  private final Socket socket;
  private final BufferedReader in;

  Input(Socket socket) throws IOException {
    this.socket = socket;
    this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
  }

  @Override
  public void run() {
    try{
      while (!socket.isClosed() && !Thread.currentThread().isInterrupted()) {
        if (in.ready()) {
          System.out.println(in.readLine());
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}