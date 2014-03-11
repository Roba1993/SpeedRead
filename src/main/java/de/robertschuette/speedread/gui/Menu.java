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

import de.robertschuette.speedread.logic.Book;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 *
 * @author Robert Schütte
 */
public class Menu extends JMenuBar {

    private JMenu speed;
    private JMenu load;
    private Gui gui;

    public Menu(Gui gui) {
        this.gui = gui;
        addSpeedMenu();
        addLoadMenu();
    }

    private void addSpeedMenu() {
        speed = new JMenu("Speed 200WPM");

        JMenuItem speed200 = new JMenuItem("200 WPM");
        speed200.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                speed.setText("Speed 200WPM");
            }
        });

        JMenuItem speed300 = new JMenuItem("300 WPM");
        speed300.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                speed.setText("Speed 300WPM");
            }
        });

        JMenuItem speed400 = new JMenuItem("400 WPM");
        speed400.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                speed.setText("Speed 400WPM");
            }
        });

        JMenuItem speed500 = new JMenuItem("500 WPM");
        speed500.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                speed.setText("Speed 500WPM");
            }
        });

        speed.add(speed200);
        speed.add(speed300);
        speed.add(speed400);
        speed.add(speed500);
        add(speed);
    }

    public int getWPM() {
        String s = speed.getText();

        switch (s) {
            case "Speed 200WPM":
                return 200;
            case "Speed 300WPM":
                return 300;
            case "Speed 400WPM":
                return 400;
            case "Speed 500WPM":
                return 500;
        }
        return 200;
    }

    public void addLoadMenu() {
        load = new JMenu("Load Text");

        JMenuItem lfile = new JMenuItem("Load File");
        lfile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fc = new JFileChooser();
                fc.addChoosableFileFilter(new BookFileFilter());
                fc.setAcceptAllFileFilterUsed(false);

                int returnVal = fc.showOpenDialog(gui);

                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File file = fc.getSelectedFile();
                    Book book = Book.getBook(file);
                    gui.setText(book.getText());
                }
            }
        });

        load.add(lfile);
        add(load);
    }
}
