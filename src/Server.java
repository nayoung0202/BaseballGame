import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    static ArrayList<User> userList = new ArrayList<>();
    static GameSystem gm = new GameSystem();
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(50001);
            while (userList.size() < 2) {
                System.out.println("대기중...");
                Socket socket = serverSocket.accept();
                User user = new User(socket);
                userList.add(user);
                user.setTurn(userList.indexOf(user));
                user.setId(user.read());
            }
        } catch (Exception e){
            throw new RuntimeException(e);
        }

        while(userList.stream().allMatch(User::isPlay)) game(userList);
        for(User user : userList){
            user.close();
        }
    }

    static void game(List<User> users){
        gm.setPlay(true);
        for(User user : users){
            user.write("3");
            user.write("게임을 시작합니다.");
        }
        gm.gameSet(users.get(0));
        while(gm.isPlay()){
            gm.guessNumber(userList.get((gm.getTimes()-1) % 2));
            gm.printNumber(userList);
            gm.winner(userList);
        }
    }
}
