
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

class Square extends MyShape {

    public Square(int x, int y, Color color, int size) {
        super(new java.awt.geom.Point2D.Double(x, y), color, size);
    }
    
    @Override
    public void draw(Graphics2D g2){
        g2.setColor(getColor());
        g2.fill(new java.awt.geom.Rectangle2D.Double(getLocation().x - (double)
                (getSize() / 2), getLocation().y - (double)(getSize() / 2)
                , getSize(), getSize()));
    }
    
    public Rectangle2D.Double getCollisionBox(){
    return new Rectangle2D.Double(getLocation().x - getSize() / 2, 
            getLocation().y - getSize() / 2, getSize(), getSize());
    }
}
