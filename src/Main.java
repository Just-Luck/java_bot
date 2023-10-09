import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Добро пожаловать в игрового чат-бота Виселица.\nБот загадывает слово, а вам нужно его угадать.\nДля начала игры напишите /play");
        Scanner input = new Scanner(System.in);
        Bot bot = new Bot();
        while (bot.isStartAndStop()) System.out.println(bot.start(input.nextLine()));
    }
}
