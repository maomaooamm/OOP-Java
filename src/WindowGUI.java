
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

class WindowGUI extends JFrame {
    private final Canvas canvas;
    private final JButton buttonClear = new JButton("Clear ALL");
    private final JButton buttonFixCar = new JButton("Fix Car");
    private final JButton buttonAdd10 = new JButton("Add 10");
    private final MyCar car = new MyCar();
    private final int SIZE = 20;
    private final ArrayList<MyShape> actionFigures = new ArrayList();

    public WindowGUI() {
        Container cp = getContentPane();  
        
        canvas = new Canvas();
        canvas.setBackground(Color.BLACK);
        cp.add(canvas, "Center");
        
        KeyListener keyListener = new KeyListener();
        canvas.setFocusable(true);
        canvas.addKeyListener(keyListener);
        
        JPanel southPanel = new JPanel();
        southPanel.add(this.buttonAdd10);
        southPanel.add(this.buttonClear);
        southPanel.add(this.buttonFixCar);
        cp.add(southPanel, "South");
        
        ButtonListener listener = new ButtonListener();
        
        buttonAdd10.addActionListener(listener);
        buttonAdd10.setFocusable(false);
        buttonClear.addActionListener(listener);
        buttonClear.setFocusable(false);
        buttonFixCar.addActionListener(listener);
        buttonFixCar.setFocusable(false);
    }

    private class Canvas extends JPanel{

        public Canvas() {
        }

        @Override
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D)g;
            
            car.draw(g2);
            actionFigures.stream().forEach((s) -> {
                s.draw(g2);
            });
        }
    }

    private class KeyListener extends KeyAdapter{

        public KeyListener() {
        }
        
        @Override
        public void keyPressed(KeyEvent e){
            switch (e.getKeyCode()){
                case 40: 
                    car.move(0, 1);
                    break;
                case 38: 
                    car.move(0, -1);
                    break;
                case 37: 
                    car.move(-1, 0);
                    break;
                case 39: 
                    car.move(1, 0);
            }
            detectCollision();
            canvas.repaint();
        }
    }

      private void detectCollision(){
          ArrayList<MyShape> remove = new ArrayList();
          actionFigures.stream().map((s) -> {
              if (car.rightTire.getCollisionBox().intersects(s.getCollisionBox())){
                  car.rightTire.setColor(car.broken);
                  s.setColor(car.broken);
              }else if(car.leftTire.getCollisionBox().intersects(s.getCollisionBox())){
                  car.leftTire.setColor(car.broken);
                  s.setColor(car.broken);
              }else if(car.rightBody.getCollisionBox().intersects(s.getCollisionBox())){
                  car.rightBody.setColor(car.broken);
                  s.setColor(car.broken);
              }else if(car.leftBody.getCollisionBox().intersects(s.getCollisionBox())){
                  car.leftBody.setColor(car.broken);
                  s.setColor(car.broken);
              }else if(car.topBody.getCollisionBox().intersects(s.getCollisionBox())){
                  this.car.topBody.setColor(car.broken);
                  s.setColor(car.broken);
              }
            return s;
        }).filter((s) -> (s.getColor() == car.broken)).forEach((s) -> {
            remove.add(s);
        });
          actionFigures.removeAll(remove);
      }

    
    private class ButtonListener implements ActionListener{

        public ButtonListener() {
        }
        
        @Override
        public void actionPerformed(ActionEvent e){
            Object source = e.getSource();
            if(source == buttonClear){
                actionFigures.clear();
            }else if(source == buttonFixCar){
                car.leftTire.setColor(car.fixed);
                car.rightTire.setColor(car.fixed);
                car.rightBody.setColor(car.fixed);
                car.leftBody.setColor(car.fixed);
                car.topBody.setColor(car.fixed);
            }else if(source == buttonAdd10){
                AddShapes();
            }
            canvas.repaint();
        }
    }
    
    private void AddShapes(){
        Random random = new Random();
        for (int i = 0; i < 5; i++){
            int x = random.nextInt(650);
            int y = random.nextInt(650);
            actionFigures.add(new Circle(x, y, Color.WHITE, 20));
            x = random.nextInt(650);
            y = random.nextInt(400);
            actionFigures.add(new Square(x, y, Color.WHITE, 20));
        }
    }
}
