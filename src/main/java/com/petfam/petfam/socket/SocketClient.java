package com.petfam.petfam.socket;


import java.io.*;
import java.net.Socket;

public class SocketClient {

    public static void main(String[] args) {
        try {
            // host 부분에는 cmd -> ipconfig 에서 나온 자신의 ip 주소를 입력한다.
            Socket socket = new Socket("",8888);
            System.out.println("서버에 연결되었습니다.");
            Input input = new Input(socket);
            Output output = new Output(socket);

            input.start();
            output.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
