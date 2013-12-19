package org.eldslott.hb.entity;

import org.eldslott.hb.manager.SpriteManager;
import org.eldslott.hb.manager.SpriteReference;
import org.eldslott.hb.ship.EngineSystem;
import org.eldslott.hb.ship.HullSystem;
import org.eldslott.hb.ship.ShieldSystem;
import org.eldslott.hb.ship.WeaponSystem;

/**
 * @author <a href="mailto:oscar.eriks@gmail.com">Oscar Eriksson</a>
 * @date 12/18/13
 */
public class Ship extends Entity {
    private EngineSystem engine;
    private HullSystem hull;
    private ShieldSystem shield;
    private WeaponSystem weapon;

    public Ship() {
        sprite = SpriteManager.getSprite(SpriteReference.SHIP_SPRITE);
    }

    @Override
    public void move(long delta) {
    }
}
