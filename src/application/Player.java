package application;

import java.util.ArrayList;
import java.util.Random;

import javafx.scene.image.Image;

public class Player {

	private int x;
	private int y;
	private int floor; // minimum y he can reach
	private boolean jumping; // true if he is jumping
	private boolean falling; // true when he end jump and he is falling down
	private int moveLength; // how long does take one step
	private int jumpHeight;
	private Image[] imageRight = new Image[5]; // images have same size
	private Image[] imageLeft = new Image[5];
	private int i, j; // how long does take one animation
	private boolean gameOver;
	private int counter;
	private int lvl;

	public Player() {
		super();
		this.lvl = 0;
		for (int k = 0; k < 5; k++) {
			imageRight[k] = new Image(("marioRight" + k + "Lvl" + lvl + ".png"));
			imageLeft[k] = new Image(("marioLeft" + k + "Lvl" + lvl + ".png"));
		}
		this.x = 100;
		this.floor = (int) (550 - 85 - imageRight[0].getHeight()); // 85 is
																	// height of
																	// the
																	// ground
		this.y = floor;
		this.i = 0;
		this.j = 0;
		this.moveLength = 5;
		this.jumping = false;
		this.falling = false;
		this.jumpHeight = 5;
		this.gameOver = false;
		this.counter = 0;
	}

	public int getY() {
		return y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getMoveLength() {
		return moveLength;
	}

	public Image getImage() {
		if (i < 10)
			return imageRight[0];
		else if (i < 20)
			return imageRight[1];
		else if (i < 30)
			return imageRight[2];
		else if (i < 40)
			return imageRight[3];
		else if (i < 50)
			return imageRight[4];
		else if (j < 10)
			return imageLeft[0];
		else if (j < 20)
			return imageLeft[1];
		else if (j < 30)
			return imageLeft[2];
		else if (j < 40)
			return imageLeft[3];
		else
			return imageLeft[4];
	}

	public boolean getFalling() {
		return falling;
	}

	public void setJumping(boolean jumpUpper) {
		this.jumping = jumpUpper;
	}

	public void setFalling(boolean jumpDown) {
		this.falling = jumpDown;
	}

	public boolean getGameOver() {
		return gameOver;
	}

	public int getJumpHeight() {
		return jumpHeight;
	}

	public int getFloor() {
		return floor;
	}

	public void setFloor(int floor) {
		this.floor = floor;
	}

	public boolean getJumping() {
		return jumping;
	}

	public void move(ArrayList<String> input) {
		if (input.contains("LEFT") && x > 0) {
			x -= moveLength;
			i = 50;
			j++;
			if (j >= 50)
				j = 10;
		}
		if (input.contains("RIGHT") && x < 800 - imageRight[0].getWidth()) {
			x += moveLength;
			j = 50;
			i++;
			if (i >= 50)
				i = 10;
		}
		if (input.contains("UP") && jumping == false && falling == false) {
			jumping = true;
		}
		if (input.isEmpty()) {
			if (j == 50) // last move was in right side
				i = 0;
			else // last move was in left side
				j = 0;
		}
		if (input.contains("DOWN")) {
			counter = 0;
			floor = (int) (550 - 85 - imageRight[0].getHeight());
			y = floor;
		}
	}

	public void checkJumping() {
		for (int i = 0; i < 2; i++) {
			if (jumping == true) {
				y -= jumpHeight;
				counter++;
				if (counter >= 62) {
					jumping = false;
					falling = true;
				}
			} else if (falling == true) {
				counter = 0;
				y += jumpHeight;
				if (y >= floor) {
					falling = false;
				}
			}
		}
	}

	public void collision(Enemy enemy) {
		// collision with point (x + image.width, y + image.height)
		if (x + imageRight[0].getWidth() >= enemy.getX()
				&& x + imageRight[0].getWidth() <= enemy.getX() + enemy.getImage().getWidth()
				&& y + imageRight[0].getHeight() >= enemy.getY()
				&& y + imageRight[0].getHeight() <= enemy.getY() + enemy.getImage().getHeight()) {
			killOrBeKilled(enemy);
		}
		// collision with point (x, y + image.height)
		else if (x >= enemy.getX() && x <= enemy.getX() + enemy.getImage().getWidth()
				&& y + imageRight[0].getHeight() >= enemy.getY()
				&& y + imageRight[0].getHeight() <= enemy.getY() + enemy.getImage().getHeight()) {
			killOrBeKilled(enemy);
		}
		// avoid situation when enemy is in player
		// collision with point (x + image.width / 2, y + image.height)
		else if (x + imageRight[0].getWidth() / 2 >= enemy.getX()
				&& x + imageRight[0].getWidth() / 2 <= enemy.getX() + enemy.getImage().getWidth()
				&& y + imageRight[0].getHeight() >= enemy.getY()
				&& y + imageRight[0].getHeight() <= enemy.getY() + enemy.getImage().getHeight()) {
			killOrBeKilled(enemy);
		}
	}

	private void killOrBeKilled(Enemy enemy) {
		if (falling) {
			enemy.killed();
			falling = false;
			jumping = true;
		} else {
			if (lvl == 0) {
				imageRight[0] = new Image("marioDead.png");
				moveLength = 0;
				floor = 550;
				jumping = true;
				gameOver = true;
				i = 0;
			} else {
				lvl--;
				if (enemy.getGoLeft()) {
					enemy.setGoLeft(false);
				} else {
					enemy.setGoLeft(true);
				}
				for (int k = 0; k < 5; k++) {
					imageRight[k] = new Image(("marioRight" + k + "Lvl" + lvl + ".png"));
					imageLeft[k] = new Image(("marioLeft" + k + "Lvl" + lvl + ".png"));
				}
			}

		}
	}

	public void collision(Box box) {
		// standing on box
		// collision with point (x + image.width, y + image.height)
		if (x + imageRight[0].getWidth() >= box.getX()
				&& x + imageRight[0].getWidth() <= box.getX() + box.getImage().getWidth()
				&& y + imageRight[0].getHeight() >= box.getY()
				&& y + imageRight[0].getHeight() <= box.getY() + box.getImage().getHeight()) {
			floor = (int) (box.getY() - imageRight[0].getHeight());
			if (y > floor) // to avoid stacking in box
				y = floor;
		}
		// collision with point (x, y + image.height)
		else if (x >= box.getX() && x <= box.getX() + box.getImage().getWidth()
				&& y + imageRight[0].getHeight() >= box.getY()
				&& y + imageRight[0].getHeight() <= box.getY() + box.getImage().getHeight()) {
			floor = (int) (box.getY() - imageRight[0].getHeight());
			if (y > floor) // to avoid stacking in box
				y = floor;
		}
		// avoid situation when box is in player
		// collision with point (x + image.width / 2, y + image.height)
		else if (x + imageRight[0].getWidth() / 2 >= box.getX()
				&& x + imageRight[0].getWidth() / 2 <= box.getX() + box.getImage().getWidth()
				&& y + imageRight[0].getHeight() >= box.getY()
				&& y + imageRight[0].getHeight() <= box.getY() + box.getImage().getHeight()) {
			floor = (int) (box.getY() - imageRight[0].getHeight());
			if (y > floor) // to avoid stacking in box
				y = floor;
		}
		// hitting by left or right side
		// collision with point (x, y + image.height / 2)
		else if (x >= box.getX() && x <= box.getX() + box.getImage().getWidth()
				&& y + imageRight[0].getHeight() / 2 >= box.getY()
				&& y + imageRight[0].getHeight() / 2 <= box.getY() + box.getImage().getHeight()) {
			x += moveLength;
		}
		// collision with point (x + image.width, y + image.height / 2)
		else if (x + imageRight[0].getWidth() >= box.getX()
				&& x + imageRight[0].getWidth() <= box.getX() + box.getImage().getWidth()
				&& y + imageRight[0].getHeight() / 2 >= box.getY()
				&& y + imageRight[0].getHeight() / 2 <= box.getY() + box.getImage().getHeight()) {
			x -= moveLength;
		}
		// hitting by head
		// collision with point (x + image.width, y)
		else if (x + imageRight[0].getWidth() >= box.getX()
				&& x + imageRight[0].getWidth() <= box.getX() + box.getImage().getWidth() && y >= box.getY()
				&& y <= box.getY() + box.getImage().getHeight()) {
			checkIfBoxWasHit(box);
		}
		// collision with point (x, y)
		else if (x >= box.getX() && x <= box.getX() + box.getImage().getWidth() && y >= box.getY()
				&& y <= box.getY() + box.getImage().getHeight()) {
			checkIfBoxWasHit(box);
		}
		// collision with point (x + image.width / 2, y)
		else if (x + imageRight[0].getWidth() / 2 >= box.getX()
				&& x + imageRight[0].getWidth() / 2 <= box.getX() + box.getImage().getWidth() && y >= box.getY()
				&& y <= box.getY() + box.getImage().getHeight()) {
			checkIfBoxWasHit(box);
		}
		// fall to the floor from box
		if (Math.abs(y - floor) <= 5 && !falling && !jumping && floor != (int) (550 - 85 - imageRight[0].getHeight())) {
			floor = (int) (550 - 85 - imageRight[0].getHeight());
			falling = true;
		}
	}

	// making BoxCoin or PowerUp
	private void checkIfBoxWasHit(Box box) {
		if (Math.abs(y - box.getY() - box.getImage().getHeight()) <= 10 && jumping) {
			// making BoxCoin or PowerUp above Box
			if (box.getI() < 46) { // box is hit first time
				Random rand = new Random();
				// 20% for PowerUp and 80% for BoxCoin
				if (rand.nextInt(10) <= 1) {
					PowerUp powerUp = new PowerUp(box.getX(), box.getY());
					powerUp.setY(powerUp.getY() - (int) powerUp.getImage().getHeight());
					box.setPowerUp(powerUp);
				} else {
					BoxCoin boxCoin = new BoxCoin(box.getX() + (int) (box.getImage().getWidth() / 4), box.getY());
					boxCoin.setY(boxCoin.getY() - (int) boxCoin.getImage().getHeight());
					box.setBoxCoin(boxCoin);
				}
			}
			box.setI(47); // image - box was used
			falling = true;
			jumping = false;
		}
		// avoid head being in box
		else if (x <= box.getX()) {
			x -= moveLength;
		} else
			x += moveLength;
	}

	public void collision(Wall wall) {
		// standing on wall
		// collision with point (x + image.width, y + image.height)
		if (x + imageRight[0].getWidth() >= wall.getX()
				&& x + imageRight[0].getWidth() <= wall.getX() + wall.getImage().getWidth()
				&& y + imageRight[0].getHeight() >= wall.getY()
				&& y + imageRight[0].getHeight() <= wall.getY() + wall.getImage().getHeight()) {
			floor = (int) (wall.getY() - imageRight[0].getHeight());
			if (y > floor) // to avoid stacking in box
				y = floor;
		}
		// collision with point (x, y + image.height)
		else if (x >= wall.getX() && x <= wall.getX() + wall.getImage().getWidth()
				&& y + imageRight[0].getHeight() >= wall.getY()
				&& y + imageRight[0].getHeight() <= wall.getY() + wall.getImage().getHeight()) {
			floor = (int) (wall.getY() - imageRight[0].getHeight());
			if (y > floor) // to avoid stacking in box
				y = floor;
		}
		// avoid situation when wall is in player
		// collision with point (x + image.width / 2, y + image.height)
		else if (x + imageRight[0].getWidth() / 2 >= wall.getX()
				&& x + imageRight[0].getWidth() / 2 <= wall.getX() + wall.getImage().getWidth()
				&& y + imageRight[0].getHeight() >= wall.getY()
				&& y + imageRight[0].getHeight() <= wall.getY() + wall.getImage().getHeight()) {
			floor = (int) (wall.getY() - imageRight[0].getHeight());
			if (y > floor) // to avoid stacking in box
				y = floor;
		}
		// hitting by left or right side
		// collision with point (x, y + image.height / 2)
		else if (x >= wall.getX() && x <= wall.getX() + wall.getImage().getWidth()
				&& y + imageRight[0].getHeight() / 2 >= wall.getY()
				&& y + imageRight[0].getHeight() / 2 <= wall.getY() + wall.getImage().getHeight()) {
			x += moveLength;
		}
		// collision with point (x + image.width, y + image.height / 2)
		else if (x + imageRight[0].getWidth() >= wall.getX()
				&& x + imageRight[0].getWidth() <= wall.getX() + wall.getImage().getWidth()
				&& y + imageRight[0].getHeight() / 2 >= wall.getY()
				&& y + imageRight[0].getHeight() / 2 <= wall.getY() + wall.getImage().getHeight()) {
			x -= moveLength;
		}
		// hitting by head
		// collision with point (x + image.width, y)
		else if (x + imageRight[0].getWidth() >= wall.getX()
				&& x + imageRight[0].getWidth() <= wall.getX() + wall.getImage().getWidth() && y >= wall.getY()
				&& y <= wall.getY() + wall.getImage().getHeight()) {
			if (jumping) {
				jumping = false;
				falling = true;
				// player with level 0 can't destroy wall
				if (lvl > 0) {
					wall.setX(-50);
				}
			}
			// avoid head being in box
			else if (x <= wall.getX()) {
				x -= moveLength;
			} else
				x += moveLength;
		}
		// collision with point (x, y)
		else if (x >= wall.getX() && x <= wall.getX() + wall.getImage().getWidth() && y >= wall.getY()
				&& y <= wall.getY() + wall.getImage().getHeight()) {
			if (jumping) {
				jumping = false;
				falling = true;
				// player with level 0 can't destroy wall
				if (lvl > 0) {
					wall.setX(-50);
				}
			}
			// avoid head being in box
			else if (x <= wall.getX()) {
				x -= moveLength;
			} else
				x += moveLength;
		}
		// collision with point (x + image.width / 2, y)
		else if (x + imageRight[0].getWidth() / 2 >= wall.getX()
				&& x + imageRight[0].getWidth() / 2 <= wall.getX() + wall.getImage().getWidth() && y >= wall.getY()
				&& y <= wall.getY() + wall.getImage().getHeight()) {
			if (jumping) {
				jumping = false;
				falling = true;
				// player with level 0 can't destroy wall
				if (lvl > 0) {
					wall.setX(-50);
				}
			}
			// avoid head being in box
			else if (x <= wall.getX()) {
				x -= moveLength;
			} else
				x += moveLength;
		}
		// fall to the floor from box
		if (Math.abs(y - floor) <= 5 && !falling && !jumping && floor != (int) (550 - 85 - imageRight[0].getHeight())) {
			floor = (int) (550 - 85 - imageRight[0].getHeight());
			falling = true;
		}
	}

	public void collision(Pipe pipe) {
		// hitting by body
		// collision with point (x + image.width, y + image.height)
		if (x + imageRight[0].getWidth() >= pipe.getX()
				&& x + imageRight[0].getWidth() <= pipe.getX() + pipe.getImage().getWidth()
				&& y + imageRight[0].getHeight() >= pipe.getY()
				&& y + imageRight[0].getHeight() <= pipe.getY() + pipe.getImage().getHeight()) {
			// player standing on pipe
			if (Math.abs(y + imageRight[0].getHeight() - pipe.getY()) <= 10) {
				floor = (int) (pipe.getY() - imageRight[0].getHeight());
				y = floor; // to avoid stacking in pipe
			} else {
				x -= moveLength; // player is blocked by pipe
				if (i == 50) // setting good image (image doesn't change)
					j--;
				else
					i--;
			}
		}
		// collision with point (x, y + image.height)
		else if (x >= pipe.getX() && x <= pipe.getX() + pipe.getImage().getWidth()
				&& y + imageRight[0].getHeight() >= pipe.getY()
				&& y + imageRight[0].getHeight() <= pipe.getY() + pipe.getImage().getHeight()) {
			// player standing on pipe
			if (Math.abs(y + imageRight[0].getHeight() - pipe.getY()) <= 15) {
				floor = (int) (pipe.getY() - imageRight[0].getHeight());
				y = floor; // to avoid stacking in pipe
			} else {
				x += moveLength; // player is blocked by pipe
				if (i == 50) // setting good image (image doesn't change)
					j--;
				else
					i--;
			}
		}
		// fall to the floor from box
		if (Math.abs(y - floor) <= 5 && !falling && !jumping && floor != (int) (550 - 85 - imageRight[0].getHeight())) {
			floor = (int) (550 - 85 - imageRight[0].getHeight());
			falling = true;
		}
	}

	public void collision(Coin coin) {
		// collision with point (x + image.width, y + image.height)
		if (x + imageRight[0].getWidth() >= coin.getX()
				&& x + imageRight[0].getWidth() <= coin.getX() + coin.getImage().getWidth()
				&& y + imageRight[0].getHeight() >= coin.getY()
				&& y + imageRight[0].getHeight() <= coin.getY() + coin.getImage().getHeight()) {
			coin.setX(-50);
		}
		// collision with point (x, y + image.height)
		else if (x >= coin.getX() && x <= coin.getX() + coin.getImage().getWidth()
				&& y + imageRight[0].getHeight() >= coin.getY()
				&& y + imageRight[0].getHeight() <= coin.getY() + coin.getImage().getHeight()) {
			coin.setX(-50);
		}
		// collision with point (x + image.width / 2, y + image.height)
		else if (x + imageRight[0].getWidth() / 2 >= coin.getX()
				&& x + imageRight[0].getWidth() / 2 <= coin.getX() + coin.getImage().getWidth()
				&& y + imageRight[0].getHeight() >= coin.getY()
				&& y + imageRight[0].getHeight() <= coin.getY() + coin.getImage().getHeight()) {
			coin.setX(-50);
		}
		// collision with point (x + image.width, y)
		else if (x + imageRight[0].getWidth() >= coin.getX()
				&& x + imageRight[0].getWidth() <= coin.getX() + coin.getImage().getWidth() && y >= coin.getY()
				&& y <= coin.getY() + coin.getImage().getHeight()) {
			coin.setX(-50);
		}
		// collision with point (x, y)
		else if (x >= coin.getX() && x <= coin.getX() + coin.getImage().getWidth() && y >= coin.getY()
				&& y <= coin.getY() + coin.getImage().getHeight()) {
			coin.setX(-50);
		}
		// collision with point (x + image.width / 2, y)
		else if (x + imageRight[0].getWidth() / 2 >= coin.getX()
				&& x + imageRight[0].getWidth() / 2 <= coin.getX() + coin.getImage().getWidth() && y >= coin.getY()
				&& y <= coin.getY() + coin.getImage().getHeight()) {
			coin.setX(-50);
		}
		// collision with point (x, y + image.height / 2)
		else if (x >= coin.getX() && x <= coin.getX() + coin.getImage().getWidth()
				&& y + imageRight[0].getHeight() / 2 >= coin.getY()
				&& y + imageRight[0].getHeight() / 2 <= coin.getY() + coin.getImage().getHeight()) {
			coin.setX(-50);
		}
		// collision with point (x + image.width, y + image.height / 2)
		else if (x + imageRight[0].getWidth() >= coin.getX()
				&& x + imageRight[0].getWidth() <= coin.getX() + coin.getImage().getWidth()
				&& y + imageRight[0].getHeight() / 2 >= coin.getY()
				&& y + imageRight[0].getHeight() / 2 <= coin.getY() + coin.getImage().getHeight()) {
			coin.setX(-50);
		}
	}

	public void collision(PowerUp powerUp) {
		// collision with point (x + image.width, y + image.height)
		if (x + imageRight[0].getWidth() >= powerUp.getX()
				&& x + imageRight[0].getWidth() <= powerUp.getX() + powerUp.getImage().getWidth()
				&& y + imageRight[0].getHeight() >= powerUp.getY()
				&& y + imageRight[0].getHeight() <= powerUp.getY() + powerUp.getImage().getHeight()) {
			afterCollisionWithPowerUp(powerUp);
		}
		// collision with point (x, y + image.height)
		else if (x >= powerUp.getX() && x <= powerUp.getX() + powerUp.getImage().getWidth()
				&& y + imageRight[0].getHeight() >= powerUp.getY()
				&& y + imageRight[0].getHeight() <= powerUp.getY() + powerUp.getImage().getHeight()) {
			afterCollisionWithPowerUp(powerUp);
		}
		// collision with point (x + image.width / 2, y + image.height)
		else if (x + imageRight[0].getWidth() / 2 >= powerUp.getX()
				&& x + imageRight[0].getWidth() / 2 <= powerUp.getX() + powerUp.getImage().getWidth()
				&& y + imageRight[0].getHeight() >= powerUp.getY()
				&& y + imageRight[0].getHeight() <= powerUp.getY() + powerUp.getImage().getHeight()) {
			afterCollisionWithPowerUp(powerUp);
		}
		// collision with point (x + image.width, y)
		else if (x + imageRight[0].getWidth() >= powerUp.getX()
				&& x + imageRight[0].getWidth() <= powerUp.getX() + powerUp.getImage().getWidth() && y >= powerUp.getY()
				&& y <= powerUp.getY() + powerUp.getImage().getHeight()) {
			afterCollisionWithPowerUp(powerUp);
		}
		// collision with point (x, y)
		else if (x >= powerUp.getX() && x <= powerUp.getX() + powerUp.getImage().getWidth() && y >= powerUp.getY()
				&& y <= powerUp.getY() + powerUp.getImage().getHeight()) {
			afterCollisionWithPowerUp(powerUp);
		}
		// collision with point (x + image.width / 2, y)
		else if (x + imageRight[0].getWidth() / 2 >= powerUp.getX()
				&& x + imageRight[0].getWidth() / 2 <= powerUp.getX() + powerUp.getImage().getWidth()
				&& y >= powerUp.getY() && y <= powerUp.getY() + powerUp.getImage().getHeight()) {
			afterCollisionWithPowerUp(powerUp);
		}
		// collision with point (x, y + image.height / 2)
		else if (x >= powerUp.getX() && x <= powerUp.getX() + powerUp.getImage().getWidth()
				&& y + imageRight[0].getHeight() / 2 >= powerUp.getY()
				&& y + imageRight[0].getHeight() / 2 <= powerUp.getY() + powerUp.getImage().getHeight()) {
			afterCollisionWithPowerUp(powerUp);
		}
		// collision with point (x + image.width, y + image.height / 2)
		else if (x + imageRight[0].getWidth() >= powerUp.getX()
				&& x + imageRight[0].getWidth() <= powerUp.getX() + powerUp.getImage().getWidth()
				&& y + imageRight[0].getHeight() / 2 >= powerUp.getY()
				&& y + imageRight[0].getHeight() / 2 <= powerUp.getY() + powerUp.getImage().getHeight()) {
			afterCollisionWithPowerUp(powerUp);
		}
	}

	public void afterCollisionWithPowerUp(PowerUp powerUp) {
		powerUp.setX(-50);
		powerUp.setMoveLength(0);
		if (lvl < 2) {
			lvl++;
			for (int k = 0; k < 5; k++) {
				imageRight[k] = new Image(("marioRight" + k + "Lvl" + lvl + ".png"));
				imageLeft[k] = new Image(("marioLeft" + k + "Lvl" + lvl + ".png"));
			}
			floor = (int) (550 - 85 - imageRight[0].getHeight());
			if (y > floor)
				y = floor;

		}
	}
}
