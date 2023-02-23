package game;

import game.enums.PlayerSymbol;
import game.interfaces.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Игра "Крестики-нолики"
 *
 * @version 1.0
 * @author Andrey Pomelov
 */
public class TicTacToe {

    private static final List<Player> PLAYERS = new ArrayList<>();
    private static boolean isGameOver;

    public static void main(String[] args) {

        System.out.println("Игра \"Крестики-нолики\"");

        // Создаём поле для игры.
        GameField field = new GameField();

        // Создаём и добавляем в игру двоих игроков.
        PLAYERS.add(new HumanPlayer("Игрок 1", PlayerSymbol.X));
        PLAYERS.add(new HumanPlayer("Игрок 2", PlayerSymbol.O));

        // Отрисовываем игровое поле.
        field.repaint();

        // Игровой цикл продолжается до тех пор, пока isGameOver == false
        while (!isGameOver) {
            for (Player player : PLAYERS) {
                PlayerSymbol symbol = player.getSymbol();
                String coordinates;
                boolean isMoveSuccess;

                // Цикл выбора координат.
                // Продолжает работу до тех пор, пока игрок не введёт корректные координаты.
                do {
                    coordinates = player.makeMove();

                    // Проверяем, успешно ли сделан ход.
                    isMoveSuccess = field.setSymbol(symbol, coordinates);
                } while (!isMoveSuccess);

                // Отрисовываем игровое поле.
                field.repaint();

                // Проверяем, не выиграл ли игрок в результате своего хода.
                if (field.isWin(symbol.getValue())) {
                    isGameOver = true;
                    System.out.printf("Конец игры. Побеждает %s.\n", player.getName());
                    break;
                }

                // Проверяем, не заполнено ли игровое поле после хода игрока.
                if (field.isFieldFull()) {
                    isGameOver = true;
                    System.out.println("Конец игры. Ничья.");
                    break;
                }
            }
        }
    }
}