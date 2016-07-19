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
package de.robertschuette.speedread.gui;

import de.robertschuette.speedread.logic.Speed;
import de.robertschuette.speedread.logic.Text;
import de.robertschuette.speedread.logic.Word;
import java.awt.Container;
import java.awt.KeyboardFocusManager;
import javax.swing.BoxLayout;
import javax.swing.JFrame;

/**
 *
 * @author Robert Schütte
 */
public class Gui extends JFrame {

    private final WordBox wordBox;
    private final TextBox textBox;
    private final Menu menu;
    private final Text text;
    private boolean run = false;

    public Gui() {
        super("SpeedReader");
        setSize(300, 400);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container pane = getContentPane();
        setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));

        //MenuBar
        System.getProperties().put("apple.laf.useScreenMenuBar", "true");
        menu = new Menu(this);
        setJMenuBar(menu);

        //Elements
        wordBox = new WordBox();
        pane.add(wordBox);

        textBox = new TextBox();
        pane.add(textBox);

        //Add Keboard Dispatcher
        KeyboardFocusManager kfm = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        kfm.addKeyEventDispatcher(new KeyboardDispatcher(this));

        text = new Text("");
        setText("Hallo Welt");

        pack();
        setVisible(true);
    }

    public void run() {
        Word word = new Word("");

        while (text.getNextWord() != null) {
            while (run == false) {
                sleep(menu.getWPM());
            }

            //Get the new word from the file
            word.setWord(text.getActualWord());

            //Show the word
            
            wordBox.repaint();
            wordBox.writeWord(word);
            textBox.markWord(text.getWordStart(), text.getWordEnd());
            sleep(menu.getWPM());

            //when the word can't display at once,
            //show the other parts from the word
            while (!word.getOverlapp().equals("")) {
                word.setWord(word.getOverlapp());

                wordBox.writeWord(word);
                textBox.markWord(text.getWordStart(), text.getWordEnd());
                sleep(menu.getWPM());
            }
        }

        text.resetPosition();
        stopReading();
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(Speed.getSleepTime(millis));
        } catch (InterruptedException ex) {
        }
    }

    public void setText(String text) {
        this.text.setText(text);
        textBox.setText(this.text.getText());
    }

    public void startReading() {
        run = true;
    }

    public void stopReading() {
        run = false;
    }

    public void changeReadingStatus() {
        run = run == false;
    }

    public boolean getReadingStatus() {
        return run;
    }
}
