package CalculateFibonacci;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/*BlockingIO т.к. расчет N-го числа Фибоначи ведется с помощью теоремы Бине.
Расчет быстрый и блокировка не заметна.*/

public class Server {
    public static void main(String[] args) throws IOException {
        // Занимаем порт, определяя серверный сокет
        ServerSocket servSocket = new ServerSocket(55535);

        while (true) {
            // Ждем подключения клиента и получаем потоки для дальнейшей работы
            try (Socket socket = servSocket.accept();
                 PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                 BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                String line;
                while ((line = in.readLine()) != null) {
                    // Пишем ответ
                    int number = Integer.parseInt(line);
                    long result = calculateFibonacci(number);
                    out.printf("Число Фибоначчи под номером %d -> %d\n", number, result);
                    // Выход если от клиента получили end
                    if (line.equals("0")) break;
                }
            } catch (IOException ex) {
                ex.printStackTrace(System.out);
            }
        }
    }

    private static long calculateFibonacci(int number) {
        double sqrtFive = Math.sqrt(5);
        double a = (1 + sqrtFive) / 2;
        double b = (1 - sqrtFive) / 2;
        return (long) ((Math.pow(a, number) - Math.pow(b, number)) / sqrtFive);
    }
}

