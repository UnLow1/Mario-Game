package application;

import javafx.scene.image.Image;

public class Coin {
    private int x;
    private int y;
    private Image[] image = new Image[3];
    private int i;

    public Coin(int x, int y) {
        super();
        this.x = x;
        this.y = y;
        this.i = 0;
        image[0] = new Image("images/coin/coin0.png");
        image[1] = new Image("images/coin/coin1.png");
        image[2] = new Image("images/coin/coin2.png");
    }

    int getX() {
        return x;
    }

    void setX(int x) {
        this.x = x;
    }

    int getY() {
        return y;
    }

    void setY(int y) {
        this.y = y;
    }

    Image getImage() {
        if (i < 28)
            return image[0];
        else if (i < 37)
            return image[1];
        else
            return image[2];
    }

    void animate() {
        i++;
        if (i == 50)
            i = 0;
    }

    boolean collision(Box box) {
        // collision with point (x + image.width, y + image.height)
        if (x + image[0].getWidth() >= box.getX() && x + image[0].getWidth() <= box.getX() + box.getImage().getWidth()
                && y + image[0].getHeight() >= box.getY()
                && y + image[0].getHeight() <= box.getY() + box.getImage().getHeight()) {
            return true;
        }
        // collision with point (x, y + image.height)
        else if (x >= box.getX() && x <= box.getX() + box.getImage().getWidth()
                && y + image[0].getHeight() >= box.getY()
                && y + image[0].getHeight() <= box.getY() + box.getImage().getHeight()) {
            return true;
        }
        // collision with point (x + image.width, y)
        else if (x + image[0].getWidth() >= box.getX()
                && x + image[0].getWidth() <= box.getX() + box.getImage().getWidth() && y >= box.getY()
                && y <= box.getY() + box.getImage().getHeight()) {
            return true;
        }
        // collision with point (x, y)
        else if (x >= box.getX() && x <= box.getX() + box.getImage().getWidth() && y >= box.getY()
                && y <= box.getY() + box.getImage().getHeight()) {
            return true;
        }
        return false;
    }

    boolean collision(Wall wall) {
        // collision with point (x + image.width, y + image.height)
        if (x + image[0].getWidth() >= wall.getX()
                && x + image[0].getWidth() <= wall.getX() + wall.getImage().getWidth()
                && y + image[0].getHeight() >= wall.getY()
                && y + image[0].getHeight() <= wall.getY() + wall.getImage().getHeight()) {
            return true;
        }
        // collision with point (x, y + image.height)
        else if (x >= wall.getX() && x <= wall.getX() + wall.getImage().getWidth()
                && y + image[0].getHeight() >= wall.getY()
                && y + image[0].getHeight() <= wall.getY() + wall.getImage().getHeight()) {
            return true;
        }
        // collision with point (x + image.width, y)
        else if (x + image[0].getWidth() >= wall.getX()
                && x + image[0].getWidth() <= wall.getX() + wall.getImage().getWidth() && y >= wall.getY()
                && y <= wall.getY() + wall.getImage().getHeight()) {
            return true;
        }
        // collision with point (x, y)
        else if (x >= wall.getX() && x <= wall.getX() + wall.getImage().getWidth() && y >= wall.getY()
                && y <= wall.getY() + wall.getImage().getHeight()) {
            return true;
        }
        return false;
    }

    boolean collision(Coin coin) {
        // collision with point (x + image.width, y + image.height)
        if (x + image[0].getWidth() >= coin.getX()
                && x + image[0].getWidth() <= coin.getX() + coin.getImage().getWidth()
                && y + image[0].getHeight() >= coin.getY()
                && y + image[0].getHeight() <= coin.getY() + coin.getImage().getHeight()) {
            return true;
        }
        // collision with point (x, y + image.height)
        else if (x >= coin.getX() && x <= coin.getX() + coin.getImage().getWidth()
                && y + image[0].getHeight() >= coin.getY()
                && y + image[0].getHeight() <= coin.getY() + coin.getImage().getHeight()) {
            return true;
        }
        // collision with point (x + image.width, y)
        else if (x + image[0].getWidth() >= coin.getX()
                && x + image[0].getWidth() <= coin.getX() + coin.getImage().getWidth() && y >= coin.getY()
                && y <= coin.getY() + coin.getImage().getHeight()) {
            return true;
        }
        // collision with point (x, y)
        else if (x >= coin.getX() && x <= coin.getX() + coin.getImage().getWidth() && y >= coin.getY()
                && y <= coin.getY() + coin.getImage().getHeight()) {
            return true;
        }
        return false;
    }

    boolean collision(Pipe pipe) {
        // collision with point (x + image.width, y + image.height)
        if (x + image[0].getWidth() >= pipe.getX()
                && x + image[0].getWidth() <= pipe.getX() + pipe.getImage().getWidth()
                && y + image[0].getHeight() >= pipe.getY()
                && y + image[0].getHeight() <= pipe.getY() + pipe.getImage().getHeight()) {
            return true;
        }
        // collision with point (x, y + image.height)
        else if (x >= pipe.getX() && x <= pipe.getX() + pipe.getImage().getWidth()
                && y + image[0].getHeight() >= pipe.getY()
                && y + image[0].getHeight() <= pipe.getY() + pipe.getImage().getHeight()) {
            return true;
        }
        // collision with point (x + image.width, y)
        else if (x + image[0].getWidth() >= pipe.getX()
                && x + image[0].getWidth() <= pipe.getX() + pipe.getImage().getWidth() && y >= pipe.getY()
                && y <= pipe.getY() + pipe.getImage().getHeight()) {
            return true;
        }
        // collision with point (x, y)
        else if (x >= pipe.getX() && x <= pipe.getX() + pipe.getImage().getWidth() && y >= pipe.getY()
                && y <= pipe.getY() + pipe.getImage().getHeight()) {
            return true;
        }
        return false;
    }

}
