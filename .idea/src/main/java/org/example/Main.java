package org.example;

import java.util.Scanner;
/**
 * Главный класс для игры "Виселица" с использованием чат-бота.
 */
public class Main {
    /**
     * Метод main - точка входа в программу. Запускает игрового чат-бота "Виселица".
     */
    public static void main(String[] args) {
        System.out.println("Добро пожаловать в игрового чат-бота Виселица.\nБот загадывает слово, а вам нужно его угадать.\nДля начала игры напишите /play");
        Scanner input = new Scanner(System.in);
        Bot bot = new Bot();
        while (bot.isStartAndStop()) System.out.println(bot.start(input.nextLine()));
    }
}