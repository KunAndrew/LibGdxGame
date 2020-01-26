package ru.mygame.sprite;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import ru.mygame.base.Sprite;
import ru.mygame.math.Rect;


public class MessageGameOver extends Sprite {

    private static final float HEIGHT = 0.08f;
    private static final float TOP = 0.13f;

    public MessageGameOver(TextureAtlas atlas) {
        super(atlas.findRegion("message_game_over"));
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        setHeightProportion(HEIGHT);
        setTop(TOP);
    }
}
