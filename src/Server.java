import java.util.ArrayList;
import java.util.List;

public class Server {
    public static void main(String[] args) {
        System.out.println(new Server().createRandomNumber(5));
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
}