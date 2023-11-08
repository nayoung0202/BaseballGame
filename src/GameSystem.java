import java.util.ArrayList;
import java.util.List;

public class GameSystem {
    private int gameNum;
    private String target = "";
    private String guess = "";
    private boolean play = true;
    private int times;

    public int getGameNum() {
        return gameNum;
    }

    public void setGameNum(int gameNum) {
        this.gameNum = gameNum;
    }

    public boolean isPlay() {
        return play;
    }

    public void setPlay(boolean play) {
        this.play = play;
    }

    public String createRandomNumber(int n) {
        String num = "";
        List<Integer> arr = new ArrayList<>(List.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9));
        while (n > 0) {
            int random = (int) (Math.random() * arr.size());
            num += String.valueOf(arr.get(random));
            arr.remove(random);
            n--;
        }
        return num;
    }

    public int[] strikeBall(String target, String guess) {
        int strikeCount = 0;
        int ballCount = 0;
        int n = target.length();
        for (int i = 0; i < n; i++) {
            if (target.charAt(i) == guess.charAt(i)) {
                strikeCount += 1;
            }
            if (guess.contains(String.valueOf(target.charAt(i)))) {
                ballCount += 1;
            }
        }
        return new int[]{strikeCount, ballCount - strikeCount};
    }

    public void gameSet(User user) {
        user.write("1");
        user.write("숫자자리 입력 : ");
        this.gameNum = Integer.parseInt(user.read());
        this.target = createRandomNumber(gameNum);
        this.times = 1;
    }

    public void guessNumber(User user) {
        user.write("1");
        user.write(this.times + "회. " + user.getId() + "의 턴 -> ");
        this.guess = user.read();
    }

    public void printNumber(List<User> users) {
        for(User user : users) {
            user.write("2");
            if (user.getTurn() == (this.times - 1) % 2) {
                user.write(strikeBall(this.target, this.guess)[0] + " S " +
                        strikeBall(this.target, this.guess)[1] + " B ");
            } else {
                user.write(this.times + "회. 상대의 턴 -> " + guess + "\n" +
                        strikeBall(this.target, this.guess)[0] + " S " +
                        strikeBall(this.target, this.guess)[1] + " B ");
            }
        }
        this.times++;
    }

    public void winner(List<User> users) {
        if (strikeBall(target, guess)[0] == gameNum) {
            for (User user : users) {
                user.write("1");
                if (user.getTurn() == (this.times - 1) % 2) {
                    user.write(user.getId() + "의 패배! Re game? (Y/N) >> ");
                } else {
                    user.write(user.getId() + "의 승리! Re game? (Y/N) >> ");
                }
            }
            for (User user : users) {
                user.setPlay(user.read().equalsIgnoreCase("Y"));
            }
            this.play = false;
        }
    }

    public int getTimes() {
        return this.times;
    }
}