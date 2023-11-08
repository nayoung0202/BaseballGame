import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;

public class Server {
    static ArrayList<User> userList = new ArrayList<>();
    static GameSystem gm = new GameSystem();
    static boolean cont;
    static boolean cont1;
    static boolean cont2;
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(50001);
            while (userList.size() < 2) {
                System.out.println("대기중...");
                Socket socket = serverSocket.accept();
                User user = new User(socket);
                userList.add(user);
                user.setTurn(userList.indexOf(user));
                new Thread(() -> {
                    game(user);
                }).start();
            }
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    static void game(User user){
        user.setId(user.read());
        while(user.isPlay()){
            cont = true;
            while (cont) {

                cont1 = true;
                gm.setTimes(0);
                gm.setPlay(true);
                gm.gameSet(user);
                while(cont1) {
                    if(userList.stream().allMatch(User::isGo)){
                        cont1 = false;
                    }
                }
                while (gm.isPlay()){
                    cont1 = true;
                    gm.timeUp(user);
                    while(cont1) {
                        if(userList.stream().allMatch(User::isGo)){
                            cont1 = false;
                        }
                    }

                    cont2 = true;
                    gm.guessNumber(user);
                    while(cont2) {
                        if(userList.stream().allMatch(User::isGo)){
                            cont2 = false;
                        }
                    }
                    gm.printNumber(user);
                    gm.winner(user);


                }

                if(userList.stream().allMatch(User::isPlay)){
                    cont = false;
                }
            }

        }
    }
}
