package com.geek.spaceshooter.game;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

public class Boss extends Ship implements Poolable {

    private float rocketFireRate;
    private float currentRocketFire;


    public Boss (GameScreen game, TextureRegion texture) {
        this.game = game;
        this.texture = texture;
        this.position = new Vector2(0, 0);
        this.velocity = new Vector2(0, 0);
        this.enginePower = 5.0f;
        this.currentFire = 0.0f;
        this.currentRocketFire = currentFire;
        this.fireRate = 0.1f;
        this.rocketFireRate = fireRate * 20.0f;
        this.hpMax = 0;
        this.hp = this.hpMax;
        this.hitArea = new Circle(position, 100);
        this.weaponDirection = new Vector2(-1.0f, 0.0f);
        this.isPlayer = false;
    }

    @Override
    public void render(SpriteBatch batch) {
        if (damageReaction > 0.01f) {
            batch.setColor(1.0f, 1.0f - damageReaction, 1.0f - damageReaction, 1.0f);
        }
        batch.draw(texture, position.x - 110, position.y - 128);
        if (damageReaction > 0.01f) {
            batch.setColor(1.0f, 1.0f, 1.0f, 1.0f);
        }
    }

    @Override
    public void update(float dt) {
        if (position.x < 950){
            velocity.x = 0.0f;
        }

        if (game.getPlayer().position.y < position.y){
            velocity.y -= enginePower * dt;
        }

        if (game.getPlayer().position.y > position.y){
            velocity.y += enginePower * dt;
        }

        pressFire(dt);
        currentRocketFire += dt;
        if (currentRocketFire > rocketFireRate) {
            currentRocketFire -= rocketFireRate;
            rocketFire();
        }

        if (position.y < 128) {
            position.y = 128;
            if (velocity.y < 0) velocity.y = 0;
        }
        if (position.y > SpaceGame.SCREEN_HEIGHT - 128) {
            position.y = SpaceGame.SCREEN_HEIGHT - 128;
            if (velocity.y > 0) velocity.y = 0;
        }

        damageReaction -= dt * 2.0f;
        if (damageReaction < 0.0f) damageReaction = 0.0f;
        position.mulAdd(velocity, dt);
        hitArea.setPosition(position);
    }

        public void activate() {
        fireRate = 1.0f;
        position.set(1300, 350);
        velocity.set(-100, 0);
        hitArea.setRadius(110);
        hpMax = 40 * game.getLevel();
        active = true;
        hp = hpMax;
    }

    @Override
    public void onDestroy() {
        deactivate();
    }

    @Override
    public void fire() {
        BulletType bt = BulletType.GREENRAY;
        game.getBulletEmitter().setup(bt, isPlayer, position.x - 50.0f, position.y + 35.0f, weaponDirection.x * 640, weaponDirection.y * 640);
        game.getBulletEmitter().setup(bt, isPlayer, position.x - 50.0f, position.y - 35.0f, weaponDirection.x * 640, weaponDirection.y * 640);
    }

    public void rocketFire() {
        game.getRocketEmitter().setup(position.x - 50.0f, position.y - 0.0f, weaponDirection.x * 100, weaponDirection.y * 640);
    }
}
