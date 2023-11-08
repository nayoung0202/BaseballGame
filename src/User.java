import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class User {
    private Socket socket;
    private String id;
    private boolean play = true;
    private boolean go = true;

    private int turn;

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public User(Socket socket){
        this.socket = socket;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isPlay() {
        return play;
    }

    public void setPlay(boolean play) {
        this.play = play;
    }

    public void write(String s){
        try {
            DataOutputStream dos = new DataOutputStream(this.socket.getOutputStream());
            dos.writeUTF(s);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String read(){
        String input;
        try {
            DataInputStream dis = new DataInputStream(this.socket.getInputStream());
            input = dis.readUTF();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return input;
    }

    public void close(){
        try {
            this.socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
