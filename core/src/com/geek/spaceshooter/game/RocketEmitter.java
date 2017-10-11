package com.geek.spaceshooter.game;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class RocketEmitter extends ObjectPool<Rocket> {
    private GameScreen game;
    private TextureRegion rocketTexture;

    @Override
    protected Rocket newObject() {
        return new Rocket();
    }

    public RocketEmitter(GameScreen game, TextureRegion rocketTexture, int size) {
        super(size);
        this.game = game;
        this.rocketTexture = rocketTexture;
    }

    public void render(SpriteBatch batch) {
        for (int i = 0; i < activeList.size(); i++) {
            batch.draw(rocketTexture, activeList.get(i).getPosition().x - 32, activeList.get(i).getPosition().y - 24, 24, 24, 48, 48, 0.7f, 0.7f, activeList.get(i).getVelocity().y / 20*-1);
        }
    }

    public void update(float dt) {
        for (int i = 0; i < activeList.size(); i++) {
            activeList.get(i).update(dt);
        }
    }

    public void setup(float x, float y, float vx, float vy) {
        Rocket r = getActiveElement();
        r.activate(game, x, y, vx, vy);
    }
}
