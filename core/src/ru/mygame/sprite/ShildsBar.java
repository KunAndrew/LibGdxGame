package ru.mygame.sprite;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import ru.mygame.base.BaseScreen;
import ru.mygame.base.Sprite;
import ru.mygame.math.Rect;
import ru.mygame.screen.GameScreen;

public class ShildsBar extends Sprite {
    private MainShip mainship;

    public ShildsBar(TextureRegion region, MainShip ship, BaseScreen screen) {
        super(region);
        this.mainship = ship;
    }

    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        setHeightProportion(0.025f);
        setWidth(worldBounds.getWidth() - 2 * GameScreen.getFontPadding());
        setBottom(worldBounds.getBottom() + 0.04f);
        setLeft(worldBounds.getLeft() + GameScreen.getFontPadding());
    }

    @Override
    public void update(float delta) {
        super.update(delta);
    }

    @Override
    public void draw(SpriteBatch batch) {
        super.drawWithPercent(batch, mainship.getSHP() / (float) mainship.SHP);
    }

}

