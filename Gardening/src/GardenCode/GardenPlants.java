package GardenCode;

import java.util.concurrent.ScheduledExecutorService;

/**
 * Created by suryaduggi on 2/14/16.
 */
public abstract class GardenPlants implements SystemInterface {
    protected float height;
    protected float lifeSpan;
    protected String name;
    protected int positionRow;
    protected int positionCol;
    protected long delay = 0;
    protected int tempCount = 0;
    protected long Secinterval = 1;
    protected ScheduledExecutorService bgthread;

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getLifeSpan() {
        return lifeSpan;
    }

    public void setLifeSpan(float lifeSpan) {
        this.lifeSpan = lifeSpan;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPositionRow() {
        return positionRow;
    }

    public void setPositionRow(int positionRow) {
        this.positionRow = positionRow;
    }

    public int getPositionCol() {
        return positionCol;
    }

    public void setPositionCol(int positionCol) {
        this.positionCol = positionCol;
    }
}
