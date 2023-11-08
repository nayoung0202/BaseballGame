import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Input {
    public List<Integer> playerNumber() {
        System.out.print("숫자를 입력해 주세요 : ");
        Scanner sc = new Scanner(System.in);
        List<Integer> playerNum = new ArrayList<>();
        String input = sc.next();

        for (String number : input.split("")) {
            playerNum.add(Integer.parseInt(number));
        }
        return playerNum;
    }
}
