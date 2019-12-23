package ru.mygame.base;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import java.awt.Event;

import ru.mygame.math.Rect;

public class Sprite extends Rect {

    protected float angle;
    protected float scale = 1f;
    protected TextureRegion[] regions;
    protected int frame;
    public Vector2 v = new Vector2(0f, 0f);
    public Vector2 posSprite = new Vector2(0f, 0f);
    public boolean move = false;
    public Vector2 posTouch = new Vector2(0f, 0f);


    public Sprite(TextureRegion region) {
        if (region == null) {
            throw new NullPointerException("Region is null");
        }
        regions = new TextureRegion[1];
        regions[0] = region;
    }

    public void setHeightProportion(float height) {
        setHeight(height);
        float aspect = regions[frame].getRegionWidth() / (float) regions[frame].getRegionHeight();
        setWidth(height * aspect);
    }

    public void draw(SpriteBatch batch) {
        moveChek();

        batch.draw(
                regions[frame],
                getLeft() + posSprite.x, getBottom() + posSprite.y,
                halfWidth, halfHeight,
                getWidth(), getHeight(),
                scale, scale,
                angle
        );

    }

    public void resize(Rect worldBounds) {

    }

    public void update(float delta) {

    }

    public boolean touchDown(Vector2 touch, int pointer, int button) {
        return false;
    }

    public boolean touchUp(Vector2 touch, int pointer, int button) {
        return false;
    }

    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public void moveChek() {
    }
}
