package othello;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Piece {


    private Circle _circle;

    //creates a circle of radius 20 and sets the color to Magenta
    public Piece(){

        _circle = new Circle(20);
        _circle.setFill(Color.MAGENTA);


    }


    //returns the circle
    public Circle getCircle(){
        return _circle;
    }


    //sets the Color of a piece
    public void setColor(Color color){
        _circle.setFill(color);
    }

    //takes in the array index of where the circle needs to be set and sets it graphically
    public void setLocation(int row, int col){

        _circle.setCenterX(col*50 +25);
        _circle.setCenterY(row*50 + 25);
    }


    //returns the Color of a circle piece
    public Color getColor(){
       return (Color) _circle.getFill();
    }


}
