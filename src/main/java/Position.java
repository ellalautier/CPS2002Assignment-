public class Position {
    int x, y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Copy constructor.
     * @param p Position to copy.
     */
    public Position(Position p) {
        this.x = p.x;
        this.y = p.y;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }


    /**
     * Two Position objects are considered equal if their x and y coordinates match.
     * @param obj Position to check if equal
     * @return true if equal, false if not equal, or if obj is not of type Position, or is null.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj == this) return true;
        if (!(obj instanceof Position))
            return false;
        Position position = (Position) obj;
        return x == position.x && y == position.y;
    }
}
