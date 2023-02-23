package game;

import game.enums.PlayerSymbol;

/**
 * Игровое поле
 */
public class GameField {

    /**
     * Массив, содержащий игровое поле
     */
    private char[][] field;

    /**
     * Значение для пустой ячейки
     */
    private final char EMPTY_CELL = '.';

    /**
     * Конструктор
     */
    public GameField() {
        initialize();
    }

    /**
     * Первоначальное заполнение игрового поля
     */
    public void initialize() {
        field = new char[3][3];
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++) {
                field[i][j] = EMPTY_CELL;
            }
        }
    }

    /**
     * Вывести игровое поле в консоль
     */
    public void repaint() {
        for (char[] row : field) {
            for (char cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }

    /**
     * Проставить символ игрока в нужное поле
     *
     * @param symbol      символ игрока (Х или О)
     * @param coordinates координаты в виде строки
     *                    Координаты должны передаваться в виде строки с разделителем-пробелом, пример - "2 3".
     * @return            true, если ход выполнен успешно, символ игрока проставлен в поле
     */
    public boolean setSymbol(PlayerSymbol symbol, String coordinates) {

        // Получаем массив, содержащий отдельно координату строки и столбца.
        String[] coordinatesValues = coordinates.split(" ");

        // Проверка. Если размер массива не 2, значит переданы некорректные координаты.
        if (coordinatesValues.length != 2) {
            return false;
        }

        // Парсим координаты в числовой тип. Если в процессе парсинга возникает ошибка,
        // значит переданы некорректные координаты.
        int coordinateX, coordinateY;
        try {
            coordinateX = Integer.parseInt(coordinatesValues[0]) - 1;
            coordinateY = Integer.parseInt(coordinatesValues[1]) - 1;
        } catch (Exception e) {
            return false;
        }

        // Проверяем, не занята ли уже указанная ячейка.
        try {
            if (field[coordinateX][coordinateY] != EMPTY_CELL) {
                return false;
            }
            // Заполняем указанную ячейку символом игрока.
            field[coordinateX][coordinateY] = symbol.getValue();
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    /**
     * Проверка, заполнено ли поле
     *
     * @return true, если поле полностью заполнено символами игроков
     */
    boolean isFieldFull() {
        for (char[] row : field) {
            for (char cell : row) {
                if (cell == EMPTY_CELL) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Проверка, присутствует ли на поле выигрышная комбинация
     *
     * @param symbol    символ игрока
     * @return          true, если выигрышная комбинация присутствует на поле
     */
    boolean isWin(char symbol) {
        return checkRows(symbol) || checkColumns(symbol) || checkDiagonals(symbol);
    }

    /**
     * Проверка, присутствует ли в каком-либо ряду выигрышная комбинация
     *
     * @param symbol    символ игрока
     * @return          true, если выигрышная комбинация присутствует в каком-либо ряду
     */
    boolean checkRows(char symbol) {
        for (int i = 0; i < 3; i++) {
            if (field[i][0] == symbol && field[i][1] == symbol && field[i][2] == symbol) {
                return true;
            }
        }
        return false;
    }

    /**
     * Проверка, присутствует ли в каком-либо столбце выигрышная комбинация
     *
     * @param symbol    символ игрока
     * @return          true, если выигрышная комбинация присутствует в каком-либо столбце
     */
    boolean checkColumns(char symbol) {
        for (int i = 0; i < 3; i++) {
            if (field[0][i] == symbol && field[1][i] == symbol && field[2][i] == symbol) {
                return true;
            }
        }
        return false;
    }

    /**
     * Проверка, присутствует ли в какой-либо диагонали выигрышная комбинация
     *
     * @param symbol    символ игрока
     * @return          true, если выигрышная комбинация присутствует в какой-либо диагонали
     */
    boolean checkDiagonals(char symbol) {
        return (field[0][0] == symbol && field[1][1] == symbol && field[2][2] == symbol)
                ||
                (field[0][2] == symbol && field[1][1] == symbol && field[2][0] == symbol);
    }
}