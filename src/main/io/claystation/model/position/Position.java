package io.claystation.model.position;

public class Position {

    private final int x;
    private final int y;
    private final Direction direction;

    public Position(final int x, final int y, final Direction direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Direction getDirection() {
        return direction;
    }

    public Position getForward() {
        int newX = x;
        int newY = y;
        if (Direction.NORTH.equals(this.direction)) {
            newY--;
        } else if (Direction.EAST.equals(this.direction)) {
            newX++;
        } else if (Direction.SOUTH.equals(this.direction)) {
            newY++;
        } else {
            newX--;
        }
        return new Position(newX, newY, this.direction);
    }

    public Position getLeft() {
        return new Position(x, y, direction.getLeft());
    }

    public Position getRight() {
        return new Position(x, y, direction.getRight());
    }

    @Override
    public String toString() {
        return x + " " + y + " " + direction.getDirection();
    }
}
