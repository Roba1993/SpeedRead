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

import de.robertschuette.speedread.logic.Word;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Robert Schütte
 */
public class WordBox extends JPanel {
    private final JLabel firstLabel;
    private final JLabel focusLabel;
    private final JLabel lastLabel;
    private final Font font;

    public WordBox() {
        setLayout(null);
        setSize(300, 48);
        setPreferredSize(new Dimension(300, 48));

        font = new Font(Font.DIALOG, Font.BOLD, 25);

        firstLabel = new JLabel();
        firstLabel.setFont(font);
        add(firstLabel);

        focusLabel = new JLabel();
        focusLabel.setFont(font);
        focusLabel.setForeground(Color.red);
        add(focusLabel);

        lastLabel = new JLabel();
        lastLabel.setFont(font);
        add(lastLabel);

        setBackground(Color.white);
    }

    public void writeWord(Word word) {
        int focusPos = 100 - (getTextWidth(word.getFocus()) / 2);
        int firstPos = focusPos - getTextWidth(word.getFirst());
        int lastPos = 100 + (getTextWidth(word.getFocus()) / 2);

        firstLabel.setText(word.getFirst());
        firstLabel.setBounds(firstPos, 4, 100, 40);

        focusLabel.setText(word.getFocus());
        focusLabel.setBounds(focusPos, 4, 40, 40);
        
        lastLabel.setText(word.getLast());
        lastLabel.setBounds(lastPos, 4, 200, 40);

        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        //top and bot line
        g.fillRect(0, 00, 300, 3);
        g.fillRect(0, 45, 300, 3);

        //char line
        g.fillRect(100, 3, 3, 7);
        g.fillRect(100, 38, 3, 7);
    }

    private int getTextWidth(String text) {
        FontMetrics metrics = getGraphics().getFontMetrics(font);
        return metrics.stringWidth(text);
    }
}
