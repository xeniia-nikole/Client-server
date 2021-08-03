import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            // Открываем сокет
            Socket socket = new Socket("127.0.0.1", 55535);

            // Получаем входящий и исходящий потоки информации
            try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                 PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
                 Scanner scanner = new Scanner(System.in)) {
                while (true) {
                    System.out.println("Введите порядковый номер искомого числа Фибоначчи или '0': ");
                    String number = scanner.next();
                    out.println(number);
                    if (number.equals("0")) break;
                    System.out.println("Ответ от сервера: " + in.readLine());
                    System.out.println();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

