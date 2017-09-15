package ru.geekbrains.stargame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class StarGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture spaceship;
	Texture asteroid;
	Texture bullet;
	float xS;
	float yS;
	float xsS;
	float ysS;
	float xdS;
	float ydS;
	float vxS;
	float vyS;

	float xA;
	float yA;
	float xsA;
	float ysA;
	float xdA;
	float ydA;
	float vxA;
	float vyA;

	float xB;
	float yB;
	float xsB;
	float ysB;
	float xdB;
	float ydB;
	float vxB;
	float vyB;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		spaceship = new Texture("spaceship.png");
		asteroid = new Texture("asteroid.png");
		bullet = new Texture("bullet.png");
		xsS = 640.0f;
		ysS = 400.0f;
		xS = xsS;
		yS = ysS;
		xdS = (float)Math.random()*1280;
		ydS = (float)Math.random()*720;
		vxS = (float)Math.random()*100;
		vyS = (float)Math.random()*100;

		xsA = 640.0f;
		ysA = 400.0f;
		xA = xsA;
		yA = ysA;
		xdA = (float)Math.random()*1280;
		ydA = (float)Math.random()*720;
		vxA = (float)Math.random()*100;
		vyA = (float)Math.random()*100;

		xsB = 640.0f;
		ysB = 400.0f;
		xB = xsB;
		yB = ysB;
		xdB = (float)Math.random()*1280;
		ydB = (float)Math.random()*720;
		vxB = (float)Math.random()*100;
		vyB = (float)Math.random()*100;

	}

	@Override
	public void render () {
		float dt = Gdx.graphics.getDeltaTime();
		update(dt);
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(spaceship, xS, yS);
		batch.draw(asteroid, xA, yA);
		batch.draw(bullet, xB, yB);
		batch.end();
	}

	public void update (float dt){
		if(xdS > xsS){
			xS += vxS * dt;
		}

		if(xdS < xsS){
			xS -= vxS * dt;
		}

		if (Gdx.graphics.getHeight() - ydS > ysS + 32){
			yS += vyS * dt;
		}

		if (Gdx.graphics.getHeight() - ydS < ysS + 32){
			yS -= vyS * dt;
		}

		if(xdA > xsA){
			xA += vxA * dt;
		}

		if(xdA < xsA){
			xA -= vxA * dt;
		}

		if (Gdx.graphics.getHeight() - ydA > ysA + 128){
			yA += vyA * dt;
		}

		if (Gdx.graphics.getHeight() - ydA < ysA + 128){
			yA -= vyA * dt;
		}

		if(xdB > xsB){
			xB += vxB * dt;
		}

		if(xdB < xsB){
			xB -= vxB * dt;
		}

		if (Gdx.graphics.getHeight() - ydB > ysB + 8){
			yB += vyB * dt;
		}

		if (Gdx.graphics.getHeight() - ydB < ysB + 8){
			yB -= vyB * dt;
		}
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		spaceship.dispose();
		asteroid.dispose();
		bullet.dispose();
	}
}
