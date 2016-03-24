
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;


public abstract class MyShape implements CarCollision{
    private final Point2D.Double location;
    private Color color;
    private final int size;
	
    public MyShape(Point2D.Double location, Color color, int size) {
        this.location = location;
        this.color = color;
        this.size = size;
    }
    
    public Point2D.Double getLocation() {
        return location;
    }
    
    public Color getColor() {
        return color;
    }
    
    public void setColor(Color color){
        this.color = color;
    }
    
    public int getSize() {
        return size;
    }
    
    public void translate(int x, int y){
        this.location.x += x;
        this.location.y += y;
    }
    
    public abstract void draw(Graphics2D g2);

    	
}
