import java.util.Random;

public class Game {
    private String wordToGuess;
    private StringBuilder guessedWord;
    private int attemptsLeft;
    private Bot bot;

    public Game(Bot bot) {
        this.bot = bot;
    }

    public void startGame() {
        wordToGuess = generateRandomWord();
        guessedWord = new StringBuilder("_".repeat(wordToGuess.length()));
        attemptsLeft = 6;
    }

    private String generateRandomWord() {
        String[] words = {"арбуз", "яблоко", "банан", "апельсин", "компьютер", "автомобиль", "дом"};
        Random random = new Random();
        return words[random.nextInt(words.length)].toLowerCase();
    }

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

    public String getGuessedWord() {
        return guessedWord.toString();
    }

}
