public class Bot {
    private boolean startAndStop = true;
    private boolean inGame;
    private Game currentGame;

    /**
     * Конструктор бота. Инициализирует объект бота и связывает его с игрой.
     */
    public Bot() {
        currentGame = new Game(this);
    }

    /**
     * Метод для обработки пользовательских запросов и управления игровым процессом.
     *
     * @param request Запрос, который отправлен пользователем.
     * @return Ответ бота на запрос пользователя.
     */
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
    /**
     * Проверяет, разрешено ли боту продолжать выполнение.
     *
     * @return true, если бот может продолжать работу, в противном случае - false.
     */
    public boolean isStartAndStop() {
        return startAndStop;
    }

    /**
     * Устанавливает состояние игры (в процессе игры или нет).
     *
     * @param inGame true, если игра находится в процессе, в противном случае - false.
     */
    public void setInGame(boolean inGame) {
        this.inGame = inGame;
    }
}