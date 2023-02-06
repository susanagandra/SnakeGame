package src.academy.mindswap.gameobjects.snake;

import src.academy.mindswap.field.Field;
import src.academy.mindswap.field.Position;
import java.util.LinkedList;

public class Snake {
    private final static int SNAKE_INITIAL_SIZE = 3;
    private Direction direction;
    private boolean alive;
    private LinkedList<Position> snakePieces;

    public Snake() {
        snakePieces = new LinkedList<>();
        this.direction = Direction.RIGHT;
        this.alive = true;

       for (int i = 0; i < SNAKE_INITIAL_SIZE; i++) {
           snakePieces.add(new Position((Field.getWidth() / 2) - i, (Field.getHeight() / 2 ) - i));
       }
    }

    public void increaseSize() {
       getFullSnake().addLast(new Position(getTail().getCol(), getTail().getRow()));
    }

    public void move(Direction direction) {
        if (checkDirection(direction)) return;

        this.direction = direction;
        
        switch (direction) {
            case RIGHT:
               getFullSnake().removeLast();
               getFullSnake().addFirst(new Position(getHead().getCol() + 1, getHead().getRow()));
               break;
            case LEFT:
                getFullSnake().removeLast();
                getFullSnake().addFirst(new Position(getHead().getCol() - 1, getHead().getRow()));
                break;
            case UP:
                getFullSnake().removeLast();
                getFullSnake().addFirst(new Position(getHead().getCol(), getHead().getRow() - 1));
                break;
            case DOWN:
                getFullSnake().removeLast();
                getFullSnake().addFirst(new Position(getHead().getCol(), getHead().getRow() + 1));
                break;
        }
    }

    private boolean checkDirection(Direction direction) {
        if (this.direction == Direction.RIGHT && direction == Direction.LEFT) {
            return true;
        }
        if (this.direction == Direction.LEFT && direction == Direction.RIGHT) {
            return true;
        }
        if (this.direction == Direction.DOWN && direction == Direction.UP) {
            return true;
        }
        if (this.direction == Direction.UP && direction == Direction.DOWN) {
            return true;
        }
        return false;
    }

    public void move(){
        move(direction);
    }

    public void die() {
        alive = false;
    }

    public boolean isAlive() {
        return alive;
    }

    public Position getHead() {
        return getFullSnake().getFirst();
    }

    public Position getTail() {
        return getFullSnake().getLast();
    }

    public LinkedList<Position> getFullSnake(){
        return snakePieces;
    }

    public int getSnakeSize() {
        return snakePieces.size();
    }
}

