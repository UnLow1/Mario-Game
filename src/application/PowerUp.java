package application;

import javafx.scene.image.Image;

public class PowerUp {
    private int x;
    private int y;
    private int moveLength;
    private Image image = new Image("images/powerUp/powerUp.png");
    private int jumpHeight;
    private boolean goLeft;
    private boolean falling;

    public PowerUp(int x, int y) {
        super();
        this.x = x;
        this.y = y;
        this.falling = false;
        this.goLeft = false;
        this.moveLength = 2;
        this.jumpHeight = 8;
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
        return image;
    }

    boolean getFalling() {
        return falling;
    }

    void setFalling(boolean falling) {
        this.falling = falling;
    }

    void setMoveLength(int moveLength) {
        this.moveLength = moveLength;
    }

    void move() {
        if (goLeft) {
            if (x > 0)
                x -= moveLength;
            else {
                goLeft = false;
            }
        } else if (x < 800 - image.getWidth()) {
            x += moveLength;
        } else {
            goLeft = true;
        }
        if (falling) {
            y += jumpHeight;
            if (Math.abs(550 - 85 - image.getHeight() - y) <= 10) {
                falling = false;
                y = 550 - 85 - (int) image.getHeight();
            }
        }
    }

    void collision(Pipe pipe) {
        // collision with point (x + image.width, y + image.height)
        if (x + image.getWidth() >= pipe.getX() && x + image.getWidth() <= pipe.getX() + pipe.getImage().getWidth()
                && y + image.getHeight() >= pipe.getY()
                && y + image.getHeight() <= pipe.getY() + pipe.getImage().getHeight()) {
            changeMovingSite();
        }
        // collision with point (x, y + image.height)
        else if (x >= pipe.getX() && x <= pipe.getX() + pipe.getImage().getWidth()
                && y + image.getHeight() >= pipe.getY()
                && y + image.getHeight() <= pipe.getY() + pipe.getImage().getHeight()) {
            changeMovingSite();
        }
        // collision with point (x + image.width, y)
        else if (x + image.getWidth() >= pipe.getX() && x + image.getWidth() <= pipe.getX() + pipe.getImage().getWidth()
                && y >= pipe.getY() && y <= pipe.getY() + pipe.getImage().getHeight()) {
            changeMovingSite();
        }
        // collision with point (x, y)
        else if (x >= pipe.getX() && x <= pipe.getX() + pipe.getImage().getWidth() && y >= pipe.getY()
                && y <= pipe.getY() + pipe.getImage().getHeight()) {
            changeMovingSite();
        }
    }

    boolean collision(Box box) {
        // collision with point (x + image.width, y + image.height)
        if (x + image.getWidth() >= box.getX() && x + image.getWidth() <= box.getX() + box.getImage().getWidth()
                && y + image.getHeight() >= box.getY()
                && y + image.getHeight() <= box.getY() + box.getImage().getHeight()) {
            return true;
        }
        // collision with point (x, y + image.height)
        else if (x >= box.getX() && x <= box.getX() + box.getImage().getWidth() && y + image.getHeight() >= box.getY()
                && y + image.getHeight() <= box.getY() + box.getImage().getHeight()) {
            return true;
        }
        // collision with point (x + image.width, y)
        else if (x + image.getWidth() >= box.getX() && x + image.getWidth() <= box.getX() + box.getImage().getWidth()
                && y >= box.getY() && y <= box.getY() + box.getImage().getHeight()) {
            return true;
        }
        // collision with point (x, y)
        else if (x >= box.getX() && x <= box.getX() + box.getImage().getWidth() && y >= box.getY()
                && y <= box.getY() + box.getImage().getHeight()) {
            return true;
        }
        return false;
    }

    private void changeMovingSite() {
        goLeft = !goLeft;
    }
}
