package ru.mygame.sprite;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.mygame.base.Sprite;
import ru.mygame.math.Rect;

public class EngineTrace extends Sprite {
    private final MainShip ship;
    private final Vector2 v;
    private Rect worldBounds;
    protected float starAnimateInterval;
    protected float starAnimateTimer;
    private float x=1f;

    public EngineTrace(TextureRegion region,MainShip ship) {
        super(region);
        this.ship=ship;
        starAnimateInterval=1f;
        starAnimateTimer = 0.1f;
        float vy =  -0.3f;
        v = new Vector2(0, vy);
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        setHeightProportion(0.01f);
       // setBottom(ship.getBottom());
        setCenter(ship.pos.x-0.005f, ship.pos.y-0.05f);

        //setLeft(ship.getLeft()+ship.getHalfWidth());
    }
    @Override
    public void update(float delta) {
        super.update(delta);
        pos.mulAdd(v, delta);
       // setCenter(ship.pos.x-0.005f, ship.pos.y-0.05f);
        x-=delta;
        setScale(x);
        starAnimateTimer += delta*30;
        if (starAnimateTimer > starAnimateInterval) {
            starAnimateTimer = 0f;
            setCenter(ship.pos.x-0.005f, ship.pos.y-0.05f);
            x=1f;
        }

    }
    @Override
    public void destroy() {
        super.destroy();
    }
}
