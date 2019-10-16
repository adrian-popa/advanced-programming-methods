package Model;

public class Cylinder implements IObject {
    private int volume;

    public Cylinder(int volume) {
        this.volume = volume;
    }

    @Override
    public int getVolume() {
        return this.volume;
    }

    @Override
    public void setVolume(int volume) {
        this.volume = volume;
    }

    @Override
    public String toString() {
        return this.volume + "cm^3";
    }
}
