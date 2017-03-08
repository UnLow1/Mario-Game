package application;

import javafx.scene.image.Image;

public class Enemy {
	private int x;
	private int y;
	private boolean goLeft; // enemy going to left side
	private int dead; // how long dead enemy have to be on screen
	private int moveLength; // how long is enemy step
	private Image[] image = new Image[2];
	private int i; // how long does take one animation
	private int floor;

	public Enemy() {
		super();
		image[0] = new Image("enemy1.png");
		image[1] = new Image("enemy2.png");
		this.x = 800;
		this.y = (int) (550 - 85 - image[0].getHeight());
		this.floor = y;
		this.goLeft = true;
		this.dead = 0;
		this.moveLength = 2;
		this.i = 0;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Image getImage() {
		if (i < 20)
			return image[0];
		else
			return image[1];
	}

	public void setMoveLength(int moveLength) {
		this.moveLength = moveLength;
	}

	public void setGoLeft(boolean goLeft) {
		this.goLeft = goLeft;
	}

	public void setX(int x) {
		this.x = x;
	}

	public boolean getGoLeft() {
		return goLeft;
	}

	public void move() {
		if (dead > 0) {
			if (dead == 30) {
				x = -50;
				dead = 0;
			} else {
				dead++;
			}
		} else if (goLeft) {
			if (x > 0)
				x -= moveLength;
			else {
				goLeft = false;
			}
		} else if (x < 800 - image[0].getWidth()) {
			x += moveLength;
		} else {
			goLeft = true;
		}
		i++;
		if (i == 40) {
			i = 0;
		}
	}

	public void killed() {
		image[0] = new Image("enemyDead.png");
		image[1] = new Image("enemyDead.png");
		floor = (int) (550 - 85 - image[0].getHeight());
		moveLength = 0;
		y = floor;
		dead = 1;
	}

	public void collision(Enemy enemy) {
		// collision with point (x + image.width, y + image.height)
		if (x + image[0].getWidth() >= enemy.getX()
				&& x + image[0].getWidth() <= enemy.getX() + enemy.getImage().getWidth()
				&& y + image[0].getHeight() >= enemy.getY()
				&& y + image[0].getHeight() <= enemy.getY() + enemy.getImage().getHeight()) {
			changeMovingSite(enemy);
		}
		// collision with point (x, y + image.height)
		else if (x >= enemy.getX() && x <= enemy.getX() + enemy.getImage().getWidth()
				&& y + image[0].getHeight() >= enemy.getY()
				&& y + image[0].getHeight() <= enemy.getY() + enemy.getImage().getHeight()) {
			changeMovingSite(enemy);
		}
		// collision with point (x + image.width, y)
		else if (x + image[0].getWidth() >= enemy.getX()
				&& x + image[0].getWidth() <= enemy.getX() + enemy.getImage().getWidth() && y >= enemy.getY()
				&& y <= enemy.getY() + enemy.getImage().getHeight()) {
			changeMovingSite(enemy);
		}
		// collision with point (x, y)
		else if (x >= enemy.getX() && x <= enemy.getX() + enemy.getImage().getWidth() && y >= enemy.getY()
				&& y <= enemy.getY() + enemy.getImage().getHeight()) {
			changeMovingSite(enemy);
		}
	}

	public void collision(Pipe pipe) {
		// collision with point (x + image.width, y + image.height)
		if (x + image[0].getWidth() >= pipe.getX()
				&& x + image[0].getWidth() <= pipe.getX() + pipe.getImage().getWidth()
				&& y + image[0].getHeight() >= pipe.getY()
				&& y + image[0].getHeight() <= pipe.getY() + pipe.getImage().getHeight()) {
			changeMovingSite(null);
		}
		// collision with point (x, y + image.height)
		else if (x >= pipe.getX() && x <= pipe.getX() + pipe.getImage().getWidth()
				&& y + image[0].getHeight() >= pipe.getY()
				&& y + image[0].getHeight() <= pipe.getY() + pipe.getImage().getHeight()) {
			changeMovingSite(null);
		}
		// collision with point (x + image.width, y)
		else if (x + image[0].getWidth() >= pipe.getX()
				&& x + image[0].getWidth() <= pipe.getX() + pipe.getImage().getWidth() && y >= pipe.getY()
				&& y <= pipe.getY() + pipe.getImage().getHeight()) {
			changeMovingSite(null);
		}
		// collision with point (x, y)
		else if (x >= pipe.getX() && x <= pipe.getX() + pipe.getImage().getWidth() && y >= pipe.getY()
				&& y <= pipe.getY() + pipe.getImage().getHeight()) {
			changeMovingSite(null);
		}
	}

	public void changeMovingSite(Enemy enemy) {
		if (goLeft) {
			goLeft = false;
			if (enemy != null) {
				if (!enemy.getGoLeft())
					enemy.setGoLeft(true);
				else
					enemy.setGoLeft(false);
			}

		} else {
			goLeft = true;
			if (enemy != null) {
				if (!enemy.getGoLeft())
					enemy.setGoLeft(true);
				else
					enemy.setGoLeft(false);
			}
		}
	}
}
