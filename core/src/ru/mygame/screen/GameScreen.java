package ru.mygame.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Align;

import java.util.List;

import ru.mygame.base.BaseScreen;
import ru.mygame.base.Font;
import ru.mygame.math.Rect;
import ru.mygame.pool.BonusPool;
import ru.mygame.pool.BulletPool;
import ru.mygame.pool.EnemyPool;
import ru.mygame.pool.ExplosionPool;
import ru.mygame.pool.PlanetPool;
import ru.mygame.sprite.Background;
import ru.mygame.sprite.Bonus;
import ru.mygame.sprite.Bullet;
import ru.mygame.sprite.ButtonNewGame;
import ru.mygame.sprite.EnemyShip;
import ru.mygame.sprite.EngineTrace;
import ru.mygame.sprite.HPbar;
import ru.mygame.sprite.MainShip;
import ru.mygame.sprite.MessageGameOver;
import ru.mygame.sprite.ShildsBar;
import ru.mygame.sprite.Star;
import ru.mygame.sprite.TrackingStar;
import ru.mygame.utils.BackgraundPlanetGenerator;
import ru.mygame.utils.BonusGenerator;
import ru.mygame.utils.EnemyGenerator;

public class GameScreen extends BaseScreen {

    private static final float FONT_PADDING = 0.01f;
    private static final float FONT_SIZE = 0.025f;

    private static final String FRAGS = "Frags: ";
    private static final String HP = "HP: ";
    private static final String SHP = "SHP: ";
    private static final String LEVEL = "Level: ";
    private Texture ETtexture;
    private PlanetPool planetPool;

    private enum State {PLAYING, GAME_OVER}

   // public Planet planet;
    private Texture bg;
    private TextureAtlas atlas;
    private TextureAtlas atlas2;
    private TextureAtlas planetAtlas;
    private Texture hp;
    private Texture shp;
    private TextureRegion et;
    private HPbar HPbar;
    private ShildsBar SHPbar;

    private Background background;
    private TrackingStar[] stars;

    private MainShip mainShip;
    private EngineTrace engineTrace;

    private BonusPool bonusPool;
    private BulletPool bulletPool;
    private EnemyPool enemyPool;
    private ExplosionPool explosionPool;

    private Music music;
    private Sound laserSound;
    private Sound bulletSound;
    private Sound explosionSound;

    private EnemyGenerator enemyGenerator;
    private BonusGenerator bonusGenerator;
    private BackgraundPlanetGenerator backgraundPlanetGenerator;

    private State state;

    private MessageGameOver messageGameOver;
    private ButtonNewGame buttonNewGame;

    private int frags;

    private Font font;
    private StringBuilder sbFrags;
    private StringBuilder sbHp;
    private StringBuilder sbLevel;

    @Override
    public void show() {
        super.show();
        frags = 0;
        bg = new Texture("textures/bg.png");
        hp = new Texture("textures/HPbar.png");
        shp = new Texture("textures/SHPbar.png");
        ETtexture = new Texture("textures/et.png");
        et = new TextureRegion(ETtexture);
        background = new Background(new TextureRegion(bg));
        atlas = new TextureAtlas(Gdx.files.internal("textures/mainAtlas.tpack"));
        atlas2 = new TextureAtlas(Gdx.files.internal("textures/gameAtlas.pack"));
        planetAtlas = new TextureAtlas(Gdx.files.internal("textures/planets.pack"));
        laserSound = Gdx.audio.newSound(Gdx.files.internal("sounds/laser.wav"));
        bulletSound = Gdx.audio.newSound(Gdx.files.internal("sounds/bullet.wav"));
        explosionSound = Gdx.audio.newSound(Gdx.files.internal("sounds/explosion.wav"));
        music = Gdx.audio.newMusic(Gdx.files.internal("sounds/music.mp3"));
        bulletPool = new BulletPool();
        bonusPool = new BonusPool(worldBounds);
        explosionPool = new ExplosionPool(atlas, explosionSound);
        enemyPool = new EnemyPool(bulletPool, explosionPool, bulletSound, worldBounds);
        mainShip = new MainShip(atlas, bulletPool, explosionPool, laserSound);
        engineTrace = new EngineTrace(et, mainShip);
        HPbar = new HPbar(new TextureRegion(hp), mainShip, this);
        SHPbar = new ShildsBar(new TextureRegion(shp), mainShip, this);
        planetPool = new PlanetPool(worldBounds,mainShip);
        backgraundPlanetGenerator=new BackgraundPlanetGenerator(planetAtlas, planetPool,worldBounds);
        enemyGenerator = new EnemyGenerator(atlas, enemyPool, worldBounds);
        bonusGenerator = new BonusGenerator(atlas2, bonusPool, worldBounds);
        messageGameOver = new MessageGameOver(atlas);
        buttonNewGame = new ButtonNewGame(atlas, this);
        font = new Font("font/font.fnt", "font/font.png");
        font.setSize(FONT_SIZE);
        sbFrags = new StringBuilder();
        sbHp = new StringBuilder();
        sbLevel = new StringBuilder();
        stars = new TrackingStar[64];
        for (int i = 0; i < stars.length; i++) {
            stars[i] = new TrackingStar(atlas, mainShip.getV());
        }
        music.setLooping(true);
        music.play();
        state = State.PLAYING;
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update(delta);
        checkCollisions();
        freeAllDestroyed();
        draw();
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        background.resize(worldBounds);
        for (Star star : stars) {
            star.resize(worldBounds);
        }
        mainShip.resize(worldBounds);
        messageGameOver.resize(worldBounds);
        buttonNewGame.resize(worldBounds);
        HPbar.resize(worldBounds);
        SHPbar.resize(worldBounds);
        engineTrace.resize(worldBounds);
    }

    @Override
    public void dispose() {
        atlas.dispose();
        atlas2.dispose();
        planetAtlas.dispose();
        bg.dispose();
        bulletPool.dispose();
        explosionPool.dispose();
        enemyPool.dispose();
        bonusPool.dispose();
        planetPool.dispose();
        music.dispose();
        laserSound.dispose();
        bulletSound.dispose();
        explosionSound.dispose();
        hp.dispose();
        shp.dispose();
        ETtexture.dispose();
        super.dispose();
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        if (state == State.PLAYING) {
            mainShip.touchDown(touch, pointer, button);
        } else if (state == State.GAME_OVER) {
            buttonNewGame.touchDown(touch, pointer, button);
        }
        return false;
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer, int button) {
        if (state == State.PLAYING) {
            mainShip.touchUp(touch, pointer, button);
        } else if (state == State.GAME_OVER) {
            buttonNewGame.touchUp(touch, pointer, button);
        }
        return false;
    }

    @Override
    public boolean keyDown(int keycode) {
        if (state == State.PLAYING) {
            mainShip.keyDown(keycode);
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (state == State.PLAYING) {
            mainShip.keyUp(keycode);
        }
        return false;
    }

    public void startNewGame() {
        enemyGenerator.setLevel(1);
        frags = 0;
        state = State.PLAYING;
        mainShip.starNewGame();
        bulletPool.freeAllActiveObjects();
        explosionPool.freeAllActiveObjects();
        enemyPool.freeAllActiveObjects();
        bonusPool.freeAllActiveObjects();
        planetPool.freeAllActiveObjects();
    }

    private void update(float delta) {
        for (Star star : stars) {
            star.update(delta);
        }
        planetPool.updateActiveSprites(delta);
        explosionPool.updateActiveSprites(delta);
        if (planetPool.getActiveObjects().isEmpty()){
            backgraundPlanetGenerator.generate();
        }
        if (state == State.PLAYING) {
            mainShip.update(delta);
            engineTrace.update(delta);
            bulletPool.updateActiveSprites(delta);
            enemyPool.updateActiveSprites(delta);
            bonusPool.updateActiveSprites(delta);
            enemyGenerator.generate(delta, frags);
            bonusGenerator.generate(delta);
        }
    }

    private void checkCollisions() {
        if (state != State.PLAYING) {
            return;
        }
        List<EnemyShip> enemyShipList = enemyPool.getActiveObjects();
        for (EnemyShip enemyShip : enemyShipList) {
            float minDist = enemyShip.getHalfWidth() + mainShip.getHalfWidth();
            if (enemyShip.pos.dst(mainShip.pos) < minDist) {
                enemyShip.destroy();
                frags++;
                mainShip.damage(enemyShip.getDamage());
            }
        }
        List<Bullet> bulletList = bulletPool.getActiveObjects();
        for (Bullet bullet : bulletList) {
            if (bullet.isDestroyed()) {
                continue;
            }
            if (bullet.getOwner() != mainShip) {
                if (mainShip.isBulletCollision(bullet)) {
                    mainShip.damage(bullet.getDamage());
                    bullet.destroy();
                }
            } else {
                for (EnemyShip enemyShip : enemyShipList) {
                    if (enemyShip.isBulletCollision(bullet)) {
                        enemyShip.damage(bullet.getDamage());
                        if (enemyShip.isDestroyed()) {
                            frags++;
                        }
                        bullet.destroy();
                    }
                }
            }
        }
        List<Bonus> bonustList = bonusPool.getActiveObjects();
        for (Bonus bonus : bonustList) {
            if (mainShip.isBonusCollision(bonus)) {
                mainShip.collisionBonus(bonus);
                bonus.destroy();
            }
        }

        if (mainShip.isDestroyed()) {
            state = State.GAME_OVER;
            engineTrace.destroy();
        }
    }

    private void freeAllDestroyed() {
        bulletPool.freeAllDestroyedActiveObjects();
        explosionPool.freeAllDestroyedActiveObjects();
        enemyPool.freeAllDestroyedActiveObjects();
        bonusPool.freeAllDestroyedActiveObjects();
        planetPool.freeAllDestroyedActiveObjects();
    }

    private void draw() {
        Gdx.gl.glClearColor(0.0f, 0.0f, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        background.draw(batch);
        for (Star star : stars) {
            star.draw(batch);
        }
        planetPool.drawActiveSprites(batch);
        explosionPool.drawActiveSprites(batch);
        if (state == State.PLAYING) {
            mainShip.draw(batch);
            engineTrace.draw(batch);
            bulletPool.drawActiveSprites(batch);
            enemyPool.drawActiveSprites(batch);
            bonusPool.drawActiveSprites(batch);
        } else if (state == State.GAME_OVER) {
            messageGameOver.draw(batch);
            buttonNewGame.draw(batch);
        }
        printInfo();
        batch.end();
    }

    private void printInfo() {
        sbFrags.setLength(0);
        sbHp.setLength(0);
        sbLevel.setLength(0);
        font.draw(batch, sbFrags.append(FRAGS).append(frags),
                worldBounds.getLeft() + FONT_PADDING, worldBounds.getTop() - FONT_PADDING);
        /*font.draw(batch, sbHp.append(HP).append(mainShip.getHP()),
                worldBounds.pos.x, worldBounds.getTop() - FONT_PADDING, Align.center); */ //Count hp view for debug
        HPbar.draw(batch);
        SHPbar.draw(batch);
        font.draw(batch, sbLevel.append(LEVEL).append(enemyGenerator.getLevel()),
                worldBounds.getRight() - FONT_PADDING, worldBounds.getTop() - FONT_PADDING, Align.right);
    }

    public static float getFontPadding() {
        return FONT_PADDING;
    }
}