package ru.mygame.sprite;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import ru.mygame.base.ScaledButton;
import ru.mygame.math.Rect;
import ru.mygame.screen.GameScreen;

public class ButtonNewGame extends ScaledButton {

    private Game game;

    public ButtonNewGame(TextureAtlas atlas, Game game) {
        super(atlas.findRegion("button_new_game"));
        this.game=game;

    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        setHeightProportion(0.05f);
        setLeft(worldBounds.getLeft()+worldBounds.getWidth()/4+0.02f);
        setBottom(worldBounds.getBottom()+worldBounds.getHeight()*0.45f);
    }
    @Override
    public void action() {
        game.setScreen(new GameScreen(game));
    }
}
