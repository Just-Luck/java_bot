package org.example;

import java.util.Random;
/**
 * Класс, представляющий игровую логику для игры "Виселица".
 */
public class Game {
    /**
     * Загаданное слово, которое игрок должен угадать.
     */
    private String wordToGuess;
    /**
     * Строитель, представляющий текущее угаданное слово с открытыми буквами.
     */
    private StringBuilder guessedWord;
    /**
     * Количество попыток, оставшихся у игрока.
     */
    private int attemptsLeft;
    /**
     * Объект Bot, который управляет игровым процессом.
     */
    private Bot bot;

    /**
     * Конструктор класса Game, инициализирует игровой объект с заданным ботом.
     *
     * @param bot Объект Bot, который управляет игровым процессом.
     */
    public Game(Bot bot) {
        this.bot = bot;
    }

    /**
     * Начинает новую игру, инициализируя загаданное слово, текущее угаданное слово и количество попыток.
     */
    public void startGame() {
        wordToGuess = generateRandomWord();
        guessedWord = new StringBuilder("_".repeat(wordToGuess.length()));
        attemptsLeft = 6;
    }
    /**
     * Генерирует случайное слово из предопределенного списка.
     *
     * @return Случайно выбранное слово в нижнем регистре.
     */
    private String generateRandomWord() {
        String[] words = {"арбуз", "яблоко", "банан", "апельсин", "компьютер", "автомобиль", "дом"};
        Random random = new Random();
        return words[random.nextInt(words.length)].toLowerCase();
    }
    /**
     * Обрабатывает ввод пользователя и управляет игровым процессом.
     *
     * @param input Введенная пользователем буква.
     * @return Результат игрового хода, включая текущее состояние игры.
     */
    public String play(String input) {
        if (attemptsLeft <= 0) {
            bot.setInGame(false); // Устанавливаем, что игра завершена
            return "Игра завершена. Загаданное слово было: " + wordToGuess + "\nНапишите /play, чтобы начать новую игру.";
        }

        if (input.length() != 1 || !Character.isLetter(input.charAt(0))) return "Пожалуйста, введите одну букву.";

        char letter = input.charAt(0);
        boolean correctGuess = false;

        for (int i = 0; i < wordToGuess.length(); i++) {
            if (wordToGuess.charAt(i) == letter && guessedWord.charAt(i) == '_') {
                guessedWord.setCharAt(i, letter);
                correctGuess = true;
            }
        }

        if (!correctGuess) attemptsLeft--;

        if (wordToGuess.equals(guessedWord.toString())) {
            bot.setInGame(false); // Устанавливаем, что игра завершена
            return "Поздравляем! Вы угадали слово: " + wordToGuess + "\nНапишите /play, чтобы начать новую игру.";
        }

        if (attemptsLeft == 0) {
            bot.setInGame(false); // Устанавливаем, что игра завершена
            return "Извините, вы исчерпали все попытки. Загаданное слово было: " + wordToGuess + "\nНапишите /play, чтобы начать новую игру.";
        }

        return correctGuess
                ? "Правильная буква! Текущее состояние слова: " + guessedWord
                : "Неверная буква. Осталось попыток: " + attemptsLeft + "\nТекущее состояние слова: " + guessedWord;
    }
    /**
     * Получает текущее состояние угаданного слова.
     *
     * @return Строка, представляющая текущее состояние угаданного слова.
     */
    public String getGuessedWord() {
        return guessedWord.toString();
    }

}
