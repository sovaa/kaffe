package org.eldslott.hb.entity;

import org.eldslott.hb.manager.SpriteManager;
import org.eldslott.hb.manager.SpriteReference;

/**
 * @author <a href="mailto:oscar.eriks@gmail.com">Oscar Eriksson</a>
 * @date 12/18/13
 */
public class Player extends Entity {
    public Player() {
        sprite = SpriteManager.getSprite(SpriteReference.PLAYER_SPRITE);
        x = 500;
        y = 500;
    }

    @Override
    public void move(long delta) {
        // TODO
    }
}
