package ru.mygame.sprite;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import ru.mygame.base.Sprite;
import ru.mygame.math.Rect;

public class GameOver extends Sprite {

    public GameOver(TextureAtlas atlas) {
        super(atlas.findRegion("message_game_over"));
    }

    @Override
    public void resize(Rect worldBounds) {
        setHeightProportion(0.05f);
        setLeft(worldBounds.getLeft()+worldBounds.getWidth()/4 );
        setBottom(worldBounds.getBottom()+worldBounds.getHeight()*0.6f);
    }
}
