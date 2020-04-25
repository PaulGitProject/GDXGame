package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class CustomRectangle {

    private ShapeRenderer shapeRenderer = new ShapeRenderer();

    public void creerRectangle(int positionX, int posistionY, int sizeX, int sizeY, Color color){
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(color);
        shapeRenderer.rect(positionX,posistionY,sizeX,sizeY);
        shapeRenderer.end();
    }
}
