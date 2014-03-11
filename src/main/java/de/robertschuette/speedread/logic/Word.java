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

/**
 *
 * @author Robert Schütte
 */
public class Word {

    private String first;
    private String focus;
    private String last; 
    private String overlapp;

    public Word(String word) {
        splitWord(word);
    }

    private void splitWord(String word) {
        overlapp = "";
        
        if (word == null || word.length() == 0) {
            this.first = "";
            this.focus = "";
            this.last = "";
            return;
        }

        if (word.length() == 1) {
            this.first = "";
            this.focus = word;
            this.last = "";
            return;
        }

        if (word.length() == 2) {
            this.first = word.substring(0 , 1);
            this.focus = word.substring(1);
            this.last = "";
            return;
        }

        if(word.length() > 8) {
            overlapp = word.substring(8);
            word = word.substring(0, 8);
        }
        
        //when there are enough symbols to split them correct
        int mid = word.length() / 2;
        if(word.length() % 2 == 0) {
            mid--;
        }
        this.first = word.substring(0, mid);
        this.focus = word.substring(mid, mid+1);
        this.last = word.substring(mid + 1);
    }

    public void setWord(String word) {
        splitWord(word);
    }
    
    public String getFirst() {
        return first;
    }

    public String getFocus() {
        return focus;
    }

    public String getLast() {
        return last;
    }
    
    public String getOverlapp() {
        return overlapp.trim();
    }
}
