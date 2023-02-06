package src.academy.mindswap.gameobjects.fruit;

import src.academy.mindswap.field.Field;
import src.academy.mindswap.field.Position;


public class Fruit {
    private  int row = (int) (Math.random() * ((Field.getHeight() - 2) - 2) + 1 ) +2;
    private int col = (int) (Math.random() * ((Field.getWidth() - 2) - 2) + 1) + 2;


    public Position getPosition() {
        return new Position(this.col, this.row);
    }
}
