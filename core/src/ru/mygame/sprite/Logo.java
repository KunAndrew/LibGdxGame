package ru.mygame.sprite;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.mygame.base.Sprite;
import ru.mygame.math.Rect;

public class Logo extends Sprite {
    public float V_LEN = 0.001f;

    public Logo(TextureRegion region) {
        super(region);
    }

    @Override
    public void resize(Rect worldBounds) {
        setHeightProportion(worldBounds.getHeight() * 0.5f);
        pos.set(worldBounds.pos);
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        this.move = true;
        this.v = touch.cpy().sub(this.posSprite).setLength(V_LEN);
        this.posTouch = touch;
        return false;
    }


    public boolean keyDown(int keycode) {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
            this.posSprite.x -= 0.25f;
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
            this.posSprite.x += 0.25f;
        if (Gdx.input.isKeyPressed(Input.Keys.UP))
            this.posSprite.y += 0.25f;
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN))
            this.posSprite.y -= 0.25f;
        return false;
    }

    @Override
    public void moveChek() {
        posSprite.add(v);
        if (move & (Math.abs(posTouch.x - posSprite.x) < 0.001f & Math.abs(posTouch.y - posSprite.y) < 0.001f)) {
            v.set(0, 0);
            posSprite = posTouch.cpy();
            posTouch = new Vector2();
            move = false;
        }
    }
}
