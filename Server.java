import java.io.*;
import java.net.*;
import java.util.*;
import java.lang.*;
public class Server {
    public static void main(String[] args) {
        String keyString = "";
        String myTry = "";
        int opponentTried = 0;
        int tried = 0;
        int strikeBall = 0;
        int key = 0;
        try {
            System.out.println("상대방을 찾고 있습니다.");
            ServerSocket serverSocket = new ServerSocket(7700);
            Socket socket = serverSocket.accept();
            BufferedReader Received = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter MessageSend = new PrintWriter(socket.getOutputStream(), true);
            Scanner scan = new Scanner(System.in);
            while (true) {
                System.out.println("세 자리 수를 입력하십시오.");
                try {
                    key = scan.nextInt();
                } catch(Exception e) {
                    System.out.println("수를 입력해야 합니다.");
                    scan.nextLine();
                    continue;
                }
                if (Integer.toString(key).length() != 3) {
                    System.out.println("세 자리 수를 입력해야 합니다.");
                }
                else {
                    keyString = Integer.toString(key);
                    MessageSend.println(keyString);
                    break;
                }
            }
            System.out.println("상대의 입력을 기다리고 있습니다...");
            String keyOpponent = Received.readLine();
            while (true) {
                String clientMessage = Received.readLine();
                opponentTried ++;
                Game result = new Game();
                strikeBall = result.play(keyString, clientMessage);
                MessageSend.println(strikeBall);
                System.out.printf("상대가 %d 번 시도했습니다.", opponentTried);
                if (strikeBall == 30) {
                    System.out.println("상대가 정답을 맞췄습니다!");
                    System.out.printf("상대가 정한 수는 %s 이었습니다.", keyOpponent);
                    System.out.println("연결을 종료합니다.");
                    break;
                }
                while (true) {
                    System.out.println("당신의 차례입니다!");
                    try {
                        key = scan.nextInt();
                    } catch (Exception e) {
                        System.out.println("수를 입력해야 합니다.");
                        scan.nextLine();
                        continue;
                    }

                    if (Integer.toString(key).length() != 3) {
                        System.out.println("세 자리 수를 입력해야 합니다.");
                    } else {
                        myTry = Integer.toString(key);
                        MessageSend.println(myTry);
                        break;
                    }
                }
                String ClientResult = Received.readLine();
                strikeBall = Integer.parseInt(ClientResult);
                tried ++;
                System.out.printf("당신은 %d 번 시도했습니다. %d 스트라이크 %d 볼입니다.", tried, strikeBall / 10, strikeBall % 10);
                if (strikeBall == 30) {
                    System.out.println("당신이 정답을 맞췄습니다!");
                    System.out.println("연결을 종료합니다.");
                    break;
                }
            }
            Received.close();
            MessageSend.close();
            socket.close();
            serverSocket.close();
            scan.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}