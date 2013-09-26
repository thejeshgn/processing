/*
  This file is part of the NextText project.
  http://www.nexttext.net/

  Copyright (c) 2004-08 Obx Labs / Jason Lewis

  NextText is free software: you can redistribute it and/or modify it under
  the terms of the GNU General Public License as published by the Free Software 
  Foundation, either version 2 of the License, or (at your option) any later 
  version.

  NextText is distributed in the hope that it will be useful, but WITHOUT ANY
  WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR 
  A PARTICULAR PURPOSE.  See the GNU General Public License for more details.

  You should have received a copy of the GNU General Public License along with 
  NextText.  If not, see <http://www.gnu.org/licenses/>.
*/

package net.nexttext.behaviour.standard;

import java.awt.Color;

import net.nexttext.TextObject;
import net.nexttext.behaviour.AbstractAction;
import net.nexttext.property.ColorProperty;
import net.nexttext.property.NumberProperty;

/**
 * Fades the color of an object to a new color over time.
 */
/* $Id: Colorize.java 81 2009-11-10 02:34:30Z bits.in.shambles $ */
public class Colorize extends AbstractAction {
     
    protected boolean applyToFill = true;
    protected boolean applyToStroke = false;
    
    /**
     * The Colorize action will only influence the fill colour.
     *
     * <p>This constructor is kept for code consistency with code that was
     * using Colorize prior to the implementation of the stroke property. </p>
     *
     * @param color The target color
     * @param speed The speed factor at which the colorization is applied
     */
    public Colorize ( Color color, float speed ) {        
        this(color, speed, true, false);
    }
    
    /**
     * The Colorize action is applied to the given glyph colour component
     * (i.e. the stroke and/or the fill)
     * 
     * @param color The target color
     * @param speed The speed factor at which the colorization is applied
     * @param fill Indicates if the action has to be processed on the fill
     * @param stroke Indicates if the action has to be processed on the stroke
     */
    public Colorize ( Color color, float speed, boolean fill, boolean stroke ) {        
        applyToFill = fill;
        applyToStroke = stroke;
        if (fill) {
            properties().init("ColorFill", new ColorProperty(color) );
            properties().init("SpeedFill", new NumberProperty(speed) );
        }
        if (stroke) {
            properties().init("ColorStroke", new ColorProperty(color) );
            properties().init("SpeedStroke", new NumberProperty(speed) );
        }
    }
    
    /**
     * The Colorize action is applied to the fill and the stroke colours,
     * according to the given values.
     * 
     * @param colorFill The target color of the fill
     * @param speedFill The colorization speed for the fill
     * @param colorStroke The target color of the stroke
     * @param speedStroke The colorization speed for the stroke
     */
    public Colorize ( Color colorFill, float speedFill, Color colorStroke, float speedStroke ) {
        applyToFill = true;
        applyToStroke = true;
        properties().init("ColorFill", new ColorProperty(colorFill) );
        properties().init("SpeedFill", new NumberProperty(speedFill) );
        properties().init("ColorStroke", new ColorProperty(colorStroke) );
        properties().init("SpeedStroke", new NumberProperty(speedStroke) );
    }
    
    public ActionResult behave(TextObject to) {
        
        boolean doneFill = false;
        boolean doneStroke = false;
        
        if (applyToFill) {
            doneFill =  fadeTo(to.getColor(), 
                    ((ColorProperty)properties().get("ColorFill")).get(), 
                    (int)((NumberProperty)properties().get("SpeedFill")).get());
        }
        
        if (applyToStroke) {
            doneStroke =  fadeTo(to.getStrokeColor(), 
                    ((ColorProperty)properties().get("ColorStroke")).get(), 
                    (int)((NumberProperty)properties().get("SpeedStroke")).get());
        }
        
        if ((applyToFill==doneFill) && (applyToStroke==doneStroke))
            return new ActionResult (true, true, false);
        
        return new ActionResult(false, true, false);
    }
    
    protected boolean fadeTo( ColorProperty currentProp, Color target, int speed ) {
        
        Color currentCol = currentProp.get();
        
        int tR = target.getRed();
        int tG = target.getGreen();
        int tB = target.getBlue();
        int tA = target.getAlpha();

        int newR = fadeComponentTo( currentCol.getRed(),   tR, speed );
        int newG = fadeComponentTo( currentCol.getGreen(), tG, speed );
        int newB = fadeComponentTo( currentCol.getBlue(),  tB, speed );
        int newA = fadeComponentTo( currentCol.getAlpha(), tA, speed );

        currentProp.set(new Color(newR, newG, newB, newA));

        return (newR == tR && newG == tG && newB == tB && newA == tA);
    }
    
    private int fadeComponentTo( int component, int target, int speed ) {
        
        if ( component < target ) {
            component += speed;
            if ( component > target ) {
                component = target;
            }
        }
        else {
            component -= speed;
            if ( component < target ) {
                component = target;
            }
        }
        return component;
    }
}
