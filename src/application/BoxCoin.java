package application;

import javafx.scene.image.Image;

public class BoxCoin {
    private int x;
    private int y;
    private Image[] image = new Image[4];
    private int i;
    private int jumpHeight;
    private int jumping;
    private int falling;

    public BoxCoin(int x, int y) {
        super();
        this.x = x;
        this.y = y;
        this.i = 0;
        this.jumpHeight = 5;
        this.jumping = 15;
        this.falling = 0;
        image[0] = new Image("images/boxCoin/boxCoin0.png");
        image[1] = new Image("images/boxCoin/boxCoin1.png");
        image[2] = new Image("images/boxCoin/boxCoin2.png");
        image[3] = new Image("images/boxCoin/boxCoin3.png");
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
        if (i < 3)
            return image[0];
        else if (i < 6)
            return image[1];
        else if (i < 9)
            return image[2];
        else
            return image[3];
    }

    void animate() {
        i++;
        if (i == 12)
            i = 0;
        if (jumping > 0) {
            y -= jumpHeight;
            jumping--;
            if (jumping == 0)
                falling = 15;
        } else if (falling > 0) {
            y += jumpHeight;
            falling--;
        } else
            x = -50;
    }

}
