package ru.mygame.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import ru.mygame.base.BaseScreen;

public class MenuScreen extends BaseScreen {
    // SpriteBatch batch;


    private Texture img;
    private Texture background;
    private Vector2 pos;
    private Vector2 pos2;
    private Vector2 v;
    boolean move = false;

    @Override
    public void show() {
        super.show();
        background = new Texture("back.png");
        img = new Texture("badlogic.jpg");
        pos = new Vector2();
        pos2 = new Vector2();
        v = new Vector2(0f, 0f);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(background, 0, 0);
        batch.draw(img, pos.x, pos.y);
        if (move & Math.abs(pos.x - pos2.x) < 1 & Math.abs(pos.y - pos2.y) < 1) {
            v.set(0, 0);
            move = false;
        }
        pos.add(v);
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        img.dispose();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        pos2.set(screenX, Gdx.graphics.getHeight() - screenY);
        v = pos2.cpy().sub(pos);
        v.nor();
        move = true;
        return false;
    }

    @Override
    public boolean keyDown(int keycode) {
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT))
            pos.x-=10;
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT))
            pos.x+=10;
        if(Gdx.input.isKeyPressed(Input.Keys.UP))
            pos.y+=10;
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN))
            pos.y-=10;
        return false;
    }
}
