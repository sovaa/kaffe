package org.eldslott.hb.entity;

import org.eldslott.hb.engine.Sprite;

/**
 * @author <a href="mailto:oscar.eriks@gmail.com">Oscar Eriksson</a>
 * @date 12/18/13
 */
public abstract class Entity implements Moveable {
    protected int x = 0;
    protected int y = 0;
    protected Sprite sprite;

    public void draw() {
        sprite.draw(x, y);
    }
}
