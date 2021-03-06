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

package net.nexttext;

/**
 * A utility class used to traverse the Glyph children of a TextObjectGroup.
 */
/* $Id: TextObjectGlyphIterator.java 22 2008-04-20 12:25:25Z prisonerjohn $ */
public class TextObjectGlyphIterator {

    // It buffers the next Glyph to be returned.  This is necessary to do
    // hasNext() properly.
    TextObjectGlyph next;

    // It can't extend TextObjectIterator, since it needs to change the return
    // type of next().  Instead it wraps it.
    TextObjectIterator iterator;

    TextObjectGlyphIterator(TextObjectGroup group) {
        iterator = new TextObjectIterator(group);
        bufferNextGlyph();
    }

    private void bufferNextGlyph() {
        while (iterator.hasNext()) {
            TextObject to = iterator.next();
            if (to instanceof TextObjectGlyph) {
                next = (TextObjectGlyph) to;
                return;
            }
        }
        next = null;
    }

	/** If the traversal is complete. */
	public boolean hasNext() { return next != null; }

	/** Get the next node in the traversal. */
	public TextObjectGlyph next() {
        TextObjectGlyph current = next;
        bufferNextGlyph();
        return current;
	}
}
