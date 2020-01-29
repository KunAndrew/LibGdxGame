package ru.mygame.sprite;

import com.badlogic.gdx.graphics.g2d.TextureRegion;


import ru.mygame.base.Sprite;
import ru.mygame.math.Rect;

public class Background extends Sprite {

    public Background(TextureRegion region) {
        super(region);
    }

    @Override
    public void resize(Rect worldBounds) {
        setHeightProportion(worldBounds.getHeight());
        setWidth(worldBounds.getWidth());
    }
}
