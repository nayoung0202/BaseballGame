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

        while(userList.stream().allMatch(User::isPlay)) gm.game(userList);
        for(User user : userList){
            user.close();
        }
    }
}
