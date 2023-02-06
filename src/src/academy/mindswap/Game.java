package src.academy.mindswap;
import com.googlecode.lanterna.terminal.Terminal;
import src.academy.mindswap.gameobjects.fruit.Fruit;
import src.academy.mindswap.gameobjects.snake.Direction;
import src.academy.mindswap.gameobjects.snake.Snake;
import com.googlecode.lanterna.input.Key;
import src.academy.mindswap.field.Field;


public class Game {
    private Snake snake;
    private Fruit fruit;
    private int delay;
    private int score = 0;


    public Game(int cols, int rows, int delay) {
        Field.init(cols, rows);
        snake = new Snake();
        this.delay = delay;
    }

    public void start() throws InterruptedException {

        generateFruit();

        while (snake.isAlive()) {
            Thread.sleep(delay);
            Field.clearTail(snake);
            moveSnake();
            checkCollisions();
            Field.drawScore(score);
            Field.drawSnake(snake);
        }

    }

    private void generateFruit() {
        this.fruit = new Fruit();
        Field.drawFruit(fruit);
    }

    private void moveSnake() {

        Key k = Field.readInput();

        if (k != null) {
            switch (k.getKind()) {
                case ArrowUp:
                    snake.move(Direction.UP);
                    return;

                case ArrowDown:
                    snake.move(Direction.DOWN);
                    return;

                case ArrowLeft:
                    snake.move(Direction.LEFT);
                    return;

                case ArrowRight:
                    snake.move(Direction.RIGHT);
                    return;
            }
        }
        snake.move();
    }

    private void checkCollisions() {

        if (snake.getHead().equals(fruit.getPosition())) {
            snake.increaseSize();
            score++;
            fruit = null;
            generateFruit();

        }

        for (int i = 1; i < snake.getSnakeSize(); i++) {
            if (snake.getHead().equals(snake.getFullSnake().get(i))) {
                snake.die();
            }
        }

        if (snake.getHead().getCol() == 1 || snake.getHead().getRow() == Field.getHeight() - 2 ||
                snake.getHead().getRow() == 1 || snake.getHead().getCol() == Field.getWidth() - 2) {
            snake.die();
        }
    }

}
