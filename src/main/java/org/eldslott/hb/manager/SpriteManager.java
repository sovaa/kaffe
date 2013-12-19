/*
 * This software is the confidential and proprietary information of
 * Sigma Systems Innovation. ("Confidential Information"). You shall
 * not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Sigma Systems Innovation.
 *
 * COPYRIGHT (C) 2014 SIGMA SYSTEMS INNOVATION AB.
 * All rights reserved.
 */
package org.eldslott.hb.manager;

import org.eldslott.hb.engine.Sprite;
import org.eldslott.hb.engine.TextureLoader;

/**
 * @author <a href="mailto:oscar.eriksson@sigma.se">Oscar Eriksson</a>
 * @date 12/20/13
 */
public class SpriteManager {
    private static TextureLoader textureLoader = new TextureLoader();

    public static final Sprite getSprite(String ref) {
        return new Sprite(textureLoader, ref);
    }
}
