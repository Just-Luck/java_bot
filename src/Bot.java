public class Bot {
    private boolean startAndStop = true;
    private boolean inGame;
    private Game currentGame;

    public Bot() {
        currentGame = new Game(this);
    }

    //asdasdasdasdasdasdasdasdddddddddddddddddddddddddddddddddddddddddddddd
    public String start(String request) {
        if (currentGame == null) {
            currentGame = new Game(this);
        }

        switch (request) {
            case ("/help"):
                return "Список команд:\n/play: Начать игру\n/stop: Завершить игру";
            case ("/play"):
                if (inGame) {
                    return "Вы уже в игре";
                } else {
                    inGame = true;
                    currentGame.startGame(); // Запуск новой игры без возвращения значения
                    return "Новая игра началась. Угадайте слово: " + currentGame.getGuessedWord() + "\nВведите букву.";
                }
            case ("/stop"):
                if (inGame) {
                    inGame = false;
                    currentGame = null; // Завершаем текущую игру
                    return "Игра завершена. Напишите /play, чтобы начать новую игру.";
                } else {
                    startAndStop = false;
                    return "bot off.";
                }
            default:
                if (inGame) {
                    return currentGame.play(request);
                } else {
                    return "Такой команды не существует\nНапишите /help, чтобы узнать список команд";
                }
        }
    }

    public boolean isStartAndStop() {
        return startAndStop;
    }

    public void setInGame(boolean inGame) {
        this.inGame = inGame;
    }
}