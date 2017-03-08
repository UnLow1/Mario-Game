package application;

import javafx.scene.image.Image;

public class Pipe {
	private int x;
	private int y;
	private Image image;

	public Pipe(int x, Image image) {
		super();
		this.x = x;
		this.image = image;
		this.y = (int) (550 - 85 - image.getHeight());
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public boolean collision(Pipe pipe) {
		// collision with point (x + image.width, y + image.height)
		if (x + image.getWidth() >= pipe.getX() && x + image.getWidth() <= pipe.getX() + pipe.getImage().getWidth()
				&& y + image.getHeight() >= pipe.getY()
				&& y + image.getHeight() <= pipe.getY() + pipe.getImage().getHeight()) {
			return true;
		}
		// collision with point (x, y + image.height)
		else if (x >= pipe.getX() && x <= pipe.getX() + pipe.getImage().getWidth()
				&& y + image.getHeight() >= pipe.getY()
				&& y + image.getHeight() <= pipe.getY() + pipe.getImage().getHeight()) {
			return true;
		}
		return false;
	}

	public boolean collision(Enemy enemy) {
		// collision with point (x + image.width, y + image.height)
		if (x + image.getWidth() >= enemy.getX() && x + image.getWidth() <= enemy.getX() + enemy.getImage().getWidth()
				&& y + image.getHeight() >= enemy.getY()
				&& y + image.getHeight() <= enemy.getY() + enemy.getImage().getHeight()) {
			return true;
		}
		// collision with point (x, y + image.height)
		else if (x >= enemy.getX() && x <= enemy.getX() + enemy.getImage().getWidth()
				&& y + image.getHeight() >= enemy.getY()
				&& y + image.getHeight() <= enemy.getY() + enemy.getImage().getHeight()) {
			return true;
		}
		// avoid situation when enemy is in pipe
		else if (x <= enemy.getX() && x + image.getWidth() >= enemy.getX() + enemy.getImage().getWidth()
				&& y + image.getHeight() >= enemy.getY()
				&& y + image.getHeight() <= enemy.getY() + enemy.getImage().getHeight()) {
			return true;
		}
		return false;
	}

	public boolean collision(Coin coin) {
		// collision with point (x + image.width, y + image.height)
		if (x + image.getWidth() >= coin.getX() && x + image.getWidth() <= coin.getX() + coin.getImage().getWidth()
				&& y + image.getHeight() >= coin.getY()
				&& y + image.getHeight() <= coin.getY() + coin.getImage().getHeight()) {
			return true;
		}
		// collision with point (x, y + image.height)
		else if (x >= coin.getX() && x <= coin.getX() + coin.getImage().getWidth()
				&& y + image.getHeight() >= coin.getY()
				&& y + image.getHeight() <= coin.getY() + coin.getImage().getHeight()) {
			return true;
		}
		// avoid situation when coin is in pipe
		else if (x <= coin.getX() && x + image.getWidth() >= coin.getX() + coin.getImage().getWidth()
				&& y + image.getHeight() >= coin.getY()
				&& y + image.getHeight() <= coin.getY() + coin.getImage().getHeight()) {
			return true;
		}
		// collision with point (x + image.width / 2, y + image.height)
		else if (x + image.getWidth() / 2 >= coin.getX()
				&& x + image.getWidth() / 2 <= coin.getX() + coin.getImage().getWidth()
				&& y + image.getHeight() >= coin.getY()
				&& y + image.getHeight() <= coin.getY() + coin.getImage().getHeight()) {
			return true;
		}
		// collision with point (x + image.width / 2, y)
		else if (x + image.getWidth() / 2 >= coin.getX()
				&& x + image.getWidth() / 2 <= coin.getX() + coin.getImage().getWidth() && y >= coin.getY()
				&& y <= coin.getY() + coin.getImage().getHeight()) {
			return true;
		}
		// collision with point (x, y + image.height / 2)
		else if (x >= coin.getX() && x <= coin.getX() + coin.getImage().getWidth()
				&& y + image.getHeight() / 2 >= coin.getY()
				&& y + image.getHeight() / 2 <= coin.getY() + coin.getImage().getHeight()) {
			return true;
		}
		// collision with point (x + image.width / 2, y + image.height / 2)
		else if (x + image.getWidth() / 2 >= coin.getX()
				&& x + image.getWidth() / 2 <= coin.getX() + coin.getImage().getWidth()
				&& y + image.getHeight() / 2 >= coin.getY()
				&& y + image.getHeight() / 2 <= coin.getY() + coin.getImage().getHeight()) {
			return true;
		}
		return false;
	}
}
