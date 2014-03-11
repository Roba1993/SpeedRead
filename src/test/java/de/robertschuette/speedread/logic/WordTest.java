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

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Robert Schütte
 */
public class WordTest {

    @Test
    public void testNull() {
        Word word = new Word(null);

        assertEquals("", word.getFirst());
        assertEquals("", word.getFocus());
        assertEquals("", word.getLast());
    }

    @Test
    public void testNullSings() {
        Word word = new Word("");

        assertEquals("", word.getFirst());
        assertEquals("", word.getFocus());
        assertEquals("", word.getLast());
    }

    @Test
    public void TestOneSign() {
        Word word = new Word("A");

        assertEquals("", word.getFirst());
        assertEquals("A", word.getFocus());
        assertEquals("", word.getLast());
    }

    @Test
    public void TestTwoSigns() {
        Word word = new Word("AB");

        assertEquals("A", word.getFirst());
        assertEquals("B", word.getFocus());
        assertEquals("", word.getLast());
    }

    @Test
    public void TestThreeSigns() {
        Word word = new Word("ABC");

        assertEquals("A", word.getFirst());
        assertEquals("B", word.getFocus());
        assertEquals("C", word.getLast());
    }

    @Test
    public void testfiveSigns() {
        Word word = new Word("Bauer");

        assertEquals("Ba", word.getFirst());
        assertEquals("u", word.getFocus());
        assertEquals("er", word.getLast());
    }

    @Test
    public void testSixSigns() {
        Word word = new Word("Robert");

        assertEquals("Ro", word.getFirst());
        assertEquals("b", word.getFocus());
        assertEquals("ert", word.getLast());
    }

    @Test
    public void testLongWord() {
        Word word = new Word("RobertSchuette");

        assertEquals("Rob", word.getFirst());
        assertEquals("e", word.getFocus());
        assertEquals("rtSc", word.getLast());
        assertEquals("huette", word.getOverlapp());

        word.setWord(word.getOverlapp());

        assertEquals("hu", word.getFirst());
        assertEquals("e", word.getFocus());
        assertEquals("tte", word.getLast());
        assertEquals("", word.getOverlapp());
    }

    @Test
    public void testLongWord2() {
        Word word = new Word("keyboards");

        assertEquals("key", word.getFirst());
        assertEquals("b", word.getFocus());
        assertEquals("oard", word.getLast());
        assertEquals("s", word.getOverlapp());

        word.setWord(word.getOverlapp());

        assertEquals("", word.getFirst());
        assertEquals("s", word.getFocus());
        assertEquals("", word.getLast());
        assertEquals("", word.getOverlapp());
    }
    
    
}
