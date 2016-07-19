/*
 * The MIT License
 *
 * Copyright 2014 Robert Schütte.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package de.robertschuette.speedread.logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Robert Schütte
 */
public class Text {

    private String text;
    private int start;
    private int end;

    //Constructor
    public Text(String text) {
        constructor(text);
    }

    private void constructor(String text) {
        setText(text);
    }

    //logic
    public String getNextWord() {
        start = end;

        while (true) {
            ++end;
            if (this.text.length() <= end) {
                return null;
            }

            if (this.text.charAt(start) == ' ') {
                start = end;
                continue;
            }

            int nextHyphen = nextDelimiter(text, "-", end);
            int nextSpace = nextDelimiter(text, "\n\t ", end);
            end = Math.min(nextHyphen+1, nextSpace);
            String word = this.text.substring(start, end).trim();

            if (!"".equals(word)) {
                return word;
            }
        }
    }

    private int nextDelimiter(String text, String delimiters, int startPosition) {
      List<Integer> positions = new ArrayList<>();
      for (char delimiter: delimiters.toCharArray()) {
        int pos = text.indexOf(delimiter, startPosition);
        positions.add(pos>0?pos:text.length());
      }  
      return Collections.min(positions);
    }

    //Getter & Setter
    public void setText(String text) {
        this.text = text;
        this.start = 0;
        this.end = 0;
    }

    public String getText() {
        return this.text;
    }

    public String getActualWord() {
        return this.text.substring(start, end).trim();
    }

    public int getWordStart() {
        return start;
    }

    public int getWordEnd() {
        return end;
    }
    
    public void resetPosition() {
        this.start = 0;
        this.end = 0;
    }
}
