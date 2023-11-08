import jdk.dynalink.beans.StaticClass;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    static int times;
    static int gameNum;
    static String target;
    static String guess;
    static boolean fit;
    static boolean finish;
    static String[] check = new String[]{"Y","Y"};
    static ServerSocket serverSocket = null;
    static ArrayList<Socket> socketList = new ArrayList<>();
    static GameSystem gm = new GameSystem();
    public static void main(String[] args) {
        try {
            serverSocket = new ServerSocket(50001);
            while(socketList.size() < 2){
                System.out.println("대기중...");
                Socket socket = serverSocket.accept();
                socketList.add(socket);
                new Thread(() -> {
                    while(check[0].equals("Y") && check[1].equals("Y")) {
                        game(socket);
                    }
                }).start();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void game(Socket socket){
        String id = "";
        try {
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            id = dis.readUTF();
            while (socketList.size() != 2){
                Thread.sleep(100);
            }
            while (socketList.size() == 2) {
                if (socketList.get(0).equals(socket)) {
                    dos.writeUTF("1");
                    times = 0;
                    gameSet(socket);
                }

                if (socketList.indexOf(socket) == times % 2) {
                    times++;
                    dos.writeUTF("2");
                    guessNumber(socket);
                }

                if (fit) {
                    dos.writeUTF("3");
                    printNumber(socket);
                }

                if (finish) {
                    dos.writeUTF("4");
                    winner(id, socket);
                }
            }
        } catch (Exception e) {
            try {
                System.out.println(id + " 님이 나가셨습니다.");
                socketList.remove(socket);
                socket.close();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public static void gameSet(Socket socket){
        try {
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            dos.writeUTF("숫자자리를 입력해주세요.");
            gameNum = Integer.parseInt(dis.readUTF());
            target = gm.createRandomNumber(gameNum);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void guessNumber(Socket socket){
        try {
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            dos.writeUTF(times + "회 : ");
            guess = dis.readUTF();
            fit = true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void printNumber(Socket socket){
        try {
            int guessStrike = gm.strike(target,guess);
            int guessBall = gm.ball(target,guess);
            if(guessStrike == 4){finish = true;};
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

            if(socketList.indexOf(socket) == times % 2) {
                dos.writeUTF(times + "회 : " + guess + "\n" +
                        guessStrike + " S " + guessBall + " B");
            } else{
                dos.writeUTF(guessStrike + " S " + guessBall + " B");
            }
            fit = false;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void winner(String id,Socket socket){
        try {
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

            if (socketList.indexOf(socket) == times % 2) {
                dos.writeUTF(id + "패배! Re game? (Y/N)");
            } else {
                dos.writeUTF(id + "승리! Re game? (Y/N)");
            }
            check[socketList.indexOf(socket)] = dis.readUTF();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}