package ru.mygame.sprite;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import ru.mygame.base.Sprite;
import ru.mygame.math.Rect;

public class HPbar extends Sprite {
    private final float FULL_HP = 0.95f;
    private int hp;
    private MainShip mainship;
    public HPbar(TextureRegion region, MainShip ship) {
        super(region);
        this.hp=ship.getHP();
        this.mainship=ship;
    }

    public void resize(Rect worldBounds) {
        pos.set(worldBounds.pos);
        setHeightProportion(0.020f);
        setBottom(worldBounds.getBottom() + 0.01f);
        this.setWidth(FULL_HP);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        hp=mainship.getHP();
    }

    public int getHp() {
        return hp;
    }

}
