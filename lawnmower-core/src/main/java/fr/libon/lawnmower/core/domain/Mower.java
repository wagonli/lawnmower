package fr.libon.lawnmower.core.domain;

public class Mower {
    private Position position;

    public Mower() {
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "Mower{" +
                "position=" + position +
                '}';
    }
}
