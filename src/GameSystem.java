import java.util.ArrayList;
import java.util.List;

public class GameSystem {
    //랜덤숫자 만들기
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

    // 스트라이크 갯수
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

    // 볼 갯수
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