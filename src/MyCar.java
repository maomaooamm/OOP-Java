import java.awt.Color;
import java.awt.Graphics2D;

public class MyCar{
    Circle rightTire;
    Circle leftTire;
    Square rightBody;
    Square leftBody;
    Square topBody;
    final Color fixed = Color.YELLOW;
    final Color broken = Color.RED;
    
    public MyCar(){
        this.rightBody = new Square(45, 45, this.fixed, 30);
        this.leftBody = new Square(15, 45, this.fixed, 30);
        this.topBody = new Square(30, 15, this.fixed, 30);
        this.rightTire = new Circle(45, 70, this.fixed, 20);
        this.leftTire = new Circle(15, 70, this.fixed, 20);
    }
    
    public void move(int dx, int dy){
        this.rightBody.translate(dx * 10, dy * 10);
        this.leftBody.translate(dx * 10, dy * 10);
        this.topBody.translate(dx * 10, dy * 10);
        this.rightTire.translate(dx * 10, dy * 10);
        this.leftTire.translate(dx * 10, dy * 10);
    }
    
    public void fix(){
        this.rightBody.setColor(this.fixed);
        this.leftBody.setColor(this.fixed);
        this.topBody.setColor(this.fixed);
        this.rightTire.setColor(this.fixed);
        this.leftTire.setColor(this.fixed);
    }
    
    public void draw(Graphics2D g2){
        this.rightTire.draw(g2);
        this.leftTire.draw(g2);
        this.rightBody.draw(g2);
        this.leftBody.draw(g2);
        this.topBody.draw(g2);
    }
}
