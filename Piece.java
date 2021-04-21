package othello;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Piece {


    private Circle _circle;
    public Piece(){

        _circle = new Circle(20);
        _circle.setFill(Color.MAGENTA);


    }

    public Circle getCircle(){
        return _circle;
    }

    public void setColor(Color color){
        _circle.setFill(color);
    }
    //takes in the array index of where the circle needs to be set
    public void setLocation(int x, int y){

        _circle.setCenterX(x*50 +25);
        _circle.setCenterY(y*50 + 25);
    }

    public int getXLocation(){

        return (int) _circle.getCenterX();
    }

    public int getYLocation(){

        return (int) _circle.getCenterY();

    }

    public Color getColor(){
        return (Color) _circle.getFill();
    }

    public void switchColor(Color color){

        _circle.setFill(color);
    }
}
