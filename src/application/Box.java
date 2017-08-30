package application;

import javafx.scene.image.Image;

public class Box {
    private int x;
    private int y;
    private Image[] image = new Image[4];
    private BoxCoin boxCoin;
    private PowerUp powerUp;
    private int i; // how long does take one animation

    public Box(int x, int y) {
        super();
        this.boxCoin = null;
        this.powerUp = null;
        this.x = x;
        this.y = y;
        image[0] = new Image("images/box/box0.png");
        image[1] = new Image("images/box/box1.png");
        image[2] = new Image("images/box/box2.png");
        image[3] = new Image("images/box/box3.png"); // box was used
        this.i = 0;
    }

    void setPowerUp(PowerUp powerUp) {
        this.powerUp = powerUp;
    }

    void setBoxCoin(BoxCoin boxCoin) {
        this.boxCoin = boxCoin;
    }

    BoxCoin getBoxCoin() {
        return boxCoin;
    }

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }

    void setI(int i) {
        this.i = i;
    }

    int getI() {
        return i;
    }

    void setX(int x) {
        this.x = x;
    }

    PowerUp getPowerUp() {
        return powerUp;
    }

    Image getImage() {
        if (i < 15)
            return image[0];
        else if (i < 30)
            return image[1];
        else if (i < 45)
            return image[2];
        else
            return image[3];
    }

    void animate() {
        i++;
        if (i == 45)
            i = 0;
        if (i > 46)
            i--;
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

}
