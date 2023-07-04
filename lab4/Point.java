public class Point {
    private float x;
    private float y;

    private Boolean isControl;

    public Point(float x, float y, Boolean isControl) {
        this.x = x;
        this.y = y;
        this.isControl = isControl;
    }

    public Boolean getControl() {
        return isControl;
    }

    public void setControl(Boolean control) {
        isControl = control;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
}
