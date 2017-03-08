package application;

import javafx.scene.image.Image;

public class Wall {
	private int x;
	private int y;
	private Image image = new Image("wall.png");

	public Wall(int x, int y) {
		super();
		this.x = x;
		this.y = y;
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

	public void setY(int y) {
		this.y = y;
	}

	public Image getImage() {
		return image;
	}

	public boolean collision(Box box) {
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

	public boolean collision(Wall wall) {
		// collision with point (x + image.width, y + image.height)
		if (x + image.getWidth() >= wall.getX() && x + image.getWidth() <= wall.getX() + wall.getImage().getWidth()
				&& y + image.getHeight() >= wall.getY()
				&& y + image.getHeight() <= wall.getY() + wall.getImage().getHeight()) {
			return true;
		}
		// collision with point (x, y + image.height)
		else if (x >= wall.getX() && x <= wall.getX() + wall.getImage().getWidth()
				&& y + image.getHeight() >= wall.getY()
				&& y + image.getHeight() <= wall.getY() + wall.getImage().getHeight()) {
			return true;
		}
		// collision with point (x + image.width, y)
		else if (x + image.getWidth() >= wall.getX() && x + image.getWidth() <= wall.getX() + wall.getImage().getWidth()
				&& y >= wall.getY() && y <= wall.getY() + wall.getImage().getHeight()) {
			return true;
		}
		// collision with point (x, y)
		else if (x >= wall.getX() && x <= wall.getX() + wall.getImage().getWidth() && y >= wall.getY()
				&& y <= wall.getY() + wall.getImage().getHeight()) {
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
		// collision with point (x + image.width, y)
		else if (x + image.getWidth() >= coin.getX() && x + image.getWidth() <= coin.getX() + coin.getImage().getWidth()
				&& y >= coin.getY() && y <= coin.getY() + coin.getImage().getHeight()) {
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
