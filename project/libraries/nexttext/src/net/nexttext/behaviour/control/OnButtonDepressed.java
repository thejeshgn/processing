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

import net.nexttext.input.Mouse;

/**
 * A Condition which is true when a mouse button is depressed.
 */
/* $Id: OnButtonDepressed.java 22 2008-04-20 12:25:25Z prisonerjohn $ */
public class OnButtonDepressed extends Condition {
    
    Mouse mouse;
    int button;

    /**
     * @param button is a value from net.nexttext.input.Mouse
     */
    public OnButtonDepressed(Mouse mouse,
                             int button,
                             Action trueAction,
                             Action falseAction) {
        super(trueAction, falseAction);
        this.mouse = mouse;
        this.button = button;
    }

    /** 
     * @return the outcome of the condition.
     */
    public boolean condition( TextObject to ) {
        return mouse.isPressed(button);
    };
}
