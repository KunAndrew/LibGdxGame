package ru.mygame.sprite;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import ru.mygame.base.BaseScreen;
import ru.mygame.base.Sprite;
import ru.mygame.math.Rect;

public class ShildsBar extends Sprite {
    private final float FULL_SHP = 0.8f;
    private int shp;
    private MainShip mainship;
    public ShildsBar(TextureRegion region, MainShip ship, BaseScreen screen) {
        super(region);
        this.shp=ship.getSHP();
        this.mainship=ship;
    }

    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        setHeightProportion(0.025f);
        setBottom(worldBounds.getBottom() + 0.04f);
        setLeft(worldBounds.getLeft());
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        shp=mainship.getHP();
    }
    @Override
    public void draw(SpriteBatch batch) {
        super.drawWithPercent(batch,mainship.getSHP()/(float)mainship.SHP);
    }

}

