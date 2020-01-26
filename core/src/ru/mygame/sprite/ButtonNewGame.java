package ru.mygame.sprite;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import ru.mygame.base.ScaledButton;
import ru.mygame.math.Rect;
import ru.mygame.screen.GameScreen;

public class ButtonNewGame extends ScaledButton {

    private static final float HEIGHT = 0.06f;
    private static final float BOTTOM = -0.05f;

    private GameScreen gameScreen;

    public ButtonNewGame(TextureAtlas atlas, GameScreen gameScreen) {
        super(atlas.findRegion("button_new_game"));
        this.gameScreen = gameScreen;
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        setHeightProportion(HEIGHT);
        setBottom(BOTTOM);
    }

    @Override
    public void action() {
        gameScreen.startNewGame();
    }
}
