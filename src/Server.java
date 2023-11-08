import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public String createRandomNumber(int n){
        String num = "";
        List<Integer> arr = new ArrayList<>(List.of(0,1,2,3,4,5,6,7,8,9));
        while(n > 0){
            int random = (int)(Math.random() * arr.size());
            num += String.valueOf(arr.get(random));
            arr.remove(random);
            n--;
        }
        return num;
    }
    public int strike(String target,String guess){
        int count = 0;
        int n = target.length();
        for(int i = 0; i < n; i ++){
            if(target.charAt(i) == guess.charAt(i)){
                count += 1;
            }
        }
        return count;
    }
    public int ball(String target,String guess){
        int count = 0;
        int n = target.length();
        for(int i = 0; i < n; i ++){
            if(guess.contains(String.valueOf(target.charAt(i)))){
                count += 1;
            }
        }
        return count;
    }
}