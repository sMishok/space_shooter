package com.geek.spaceshooter.game;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class Rocket implements Poolable{
    GameScreen game;
    private Vector2 position;
    private Vector2 velocity;
    private boolean active;
    private final Vector2 tV = new Vector2(0, 0);

    public Vector2 getPosition() {
        return position;
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public boolean isActive() {
        return active;
    }

    public Rocket() {
        this.position = new Vector2(0.0f, 0.0f);
        this.velocity = new Vector2(0.0f, 0.0f);
        this.active = false;
    }

    public void deactivate() {
        this.active = false;
    }

    public void activate(GameScreen game, float x, float y, float vx, float vy) {
        this.game = game;
        position.set(x, y);
        velocity.set(vx, vy);
        active = true;
    }

    public void update(float dt) {
        tV.x = game.getPlayer().position.x - position.x;
        tV.y = game.getPlayer().position.y - position.y;
        tV.nor();
        velocity.x += tV.x * dt * 100;
        velocity.y += tV.y * dt * 100;

        position.mulAdd(velocity, dt);


        if (position.x > SpaceGame.SCREEN_WIDTH || position.x < 0 || position.y < 0 || position.y > SpaceGame.SCREEN_HEIGHT) {
            deactivate();
        }
        if (velocity.x < 0.0f) {
            float size = velocity.len() / 120.0f;
            game.getParticleEmitter().setup(position.x + 16, position.y, -MathUtils.random(5.0f, 20.0f), MathUtils.random(-24.0f, 24.0f), 0.5f, size, 0.6f, 1.0f, 1.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f);
        }
    }
}
