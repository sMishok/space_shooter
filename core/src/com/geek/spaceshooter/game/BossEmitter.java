package com.geek.spaceshooter.game;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class BossEmitter extends ObjectPool<Boss> {
    private GameScreen game;
    private TextureRegion bossTexture;

    @Override
    protected Boss newObject() {
        return new Boss(game, bossTexture);
    }

    public BossEmitter(GameScreen game, TextureRegion bossTexture, int size) {
        super();
        this.game = game;
        this.bossTexture = bossTexture;
        for (int i = 0; i < size; i++) {
            freeList.add(newObject());
        }
    }

    public void render(SpriteBatch batch) {
        for (int i = 0; i < activeList.size(); i++) {
            activeList.get(i).render(batch);
        }
    }

    public void update(float dt) {
        for (int i = 0; i < activeList.size(); i++) {
            activeList.get(i).update(dt);
        }
    }

    public void setup() {
        Boss b = getActiveElement();
        b.activate();
    }
}
