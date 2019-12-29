package ru.mygame.sprite;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;


import ru.mygame.base.Sprite;
import ru.mygame.math.Rect;
import ru.mygame.math.Rnd;
import ru.mygame.pool.BulletPool;


public class Ship extends Sprite {

    private Rect worldBounds;
    private float height;
    private static float V_LEN=0.001f;
    private Vector2 v;
    private Vector2 endPoint;
    private Vector2 buff;
    private Vector2 vR;
    private Vector2 vL;
    private Vector2 vUp;
    private Vector2 vDown;
    private boolean touched=false;

    private BulletPool bulletPool;
    private TextureRegion bulletRegion;
    private float bulletHeight;
    private Vector2 bulletV;
    private int damage;
    private float bulletAnimateTimer;
    private float bulletAnimateInterval;


    public Ship(TextureAtlas atlas, BulletPool bulletPool) {
        super(new TextureRegion(atlas.findRegion("main_ship").getTexture(),
                atlas.findRegion("main_ship").getRegionX(),
                atlas.findRegion("main_ship").getRegionY(),
                atlas.findRegion("main_ship").getRegionWidth() / 2,
                atlas.findRegion("main_ship").getRegionHeight()));

        this.bulletPool = bulletPool;
        bulletRegion = atlas.findRegion("bulletMainShip");
        bulletHeight = 0.01f;
        bulletV = new Vector2(0, 0.5f);
        damage = 1;
        height = 0.2f;
        v=new Vector2(0,0f);
        vR=new Vector2(0.1f,0f);
        vL=new Vector2(-0.1f,0f);
        vUp=new Vector2(0f,0.1f);
        vDown=new Vector2(0f,-0.1f);
        endPoint=new Vector2();
        buff=new Vector2();
        bulletAnimateInterval = 0.3f;
        bulletAnimateTimer = 0f;
    }


    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        this.worldBounds = worldBounds;
        pos.set(0, -0.5f + height-0.09f);
        setHeightProportion(height);
    }

    @Override
    public void update(float delta) {
        pos.mulAdd(v, delta);
        if (touched) {
            buff.set(endPoint);
            if (buff.sub(pos).len() > V_LEN) {
                pos.add(v);
            } else {
                pos.set(endPoint);
                touched=false;
            }
        }
        bulletAnimateTimer += delta;
        if (bulletAnimateTimer > bulletAnimateInterval) {
            bulletAnimateTimer = 0f;
            //height = 0.01f;
            shoot();
        }
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer, int button) {
        System.out.println("touchUP");
        return false;
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        this.v = touch.cpy().sub(pos).setLength(V_LEN);
        this.endPoint = touch;
        touched=true;
        return false;
    }
    public boolean keyDown(int keycode) {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
            moveLeft();
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
            moveRight();
        if (Gdx.input.isKeyPressed(Input.Keys.UP))
            moveUp();
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN))
            moveDown();
        return false;
    }
    public void keyUp(int keycode) {
        stop();
    }

private void moveRight() {
        v.set(vR);
}

    private void moveLeft() {
        v.set(vL);
    }
    private void moveUp() {
        v.set(vUp);
        //shoot();
    }
    private void moveDown() {
        v.set(vDown);
    }

    private void stop() {
        v.setZero();
    }

    private void shoot() {
        Bullet bullet = bulletPool.obtain();
        bullet.set(this, bulletRegion, pos, bulletV, bulletHeight, worldBounds, damage);
    }


}
