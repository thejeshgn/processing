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

package net.nexttext.behaviour.control;

import net.nexttext.TextObject;
import net.nexttext.behaviour.Action;
import net.nexttext.behaviour.standard.DoNothing;
import processing.core.PApplet;

/**
 * A Condition which is true when the mouse moves over the PApplet i.e. a 
 * single true result is returned if the mouse was off of the PApplet and
 * moved over it.
 */
/* $Id: OnMouseInApplet.java 22 2008-04-20 12:25:25Z prisonerjohn $ */
public class OnMouseInApplet extends OnMouseOverApplet {
    
    private boolean isOver;
    private boolean wasOver;
    
    /**
     * Creates an OnMouseInApplet which performs the given Action when the mouse
     * moves over the PApplet.
     *
     * @param p the parent PApplet
     * @param trueAction the Action to perform when the mouse is over the PApplet
     */
    public OnMouseInApplet(PApplet p, Action trueAction) {
        super(p, trueAction, new DoNothing());
        
        isOver = false;
        wasOver = false;
    }

    /**
     * Checks whether or not the mouse is over the given PApplet.
     * 
     * @param to the TextObject to consider (not used)
     * 
     * @return the outcome of the condition
     */
    public boolean condition(TextObject to) {
    	wasOver = isOver;
        isOver = super.condition(to);
        if (!wasOver && isOver) {
            return true;
        }
        return false;
    }
}
