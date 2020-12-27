package com.company;

import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.awt.event.*;
import javax.swing.text.*;

public class SimpleNotepad extends JFrame implements ActionListener {
    JTextArea textArea;     // Deklarasi teks area
    JFrame frame;           // Deklarasi frame

    public SimpleNotepad(){
        // Buat instance untuk frame dan text area
        frame = new JFrame("Simple Notepad");
        textArea = new JTextArea();

        // Membuat komponen menu
        JMenuBar menuBar = new JMenuBar();

        JMenuItem mOpen = new JMenuItem("open");
        mOpen.addActionListener(this);

        JMenuItem mNew = new JMenuItem("new");
        mNew.addActionListener(this);

        JMenuItem mSave = new JMenuItem("save");
        mSave.addActionListener(this);

        JMenuItem mCopy = new JMenuItem("copy");
        mCopy.addActionListener(this);

        JMenuItem mPaste = new JMenuItem("paste");
        mPaste.addActionListener(this);

        JMenuItem mCut = new JMenuItem("cut");
        mCut.addActionListener(this);

        JMenuItem mBold = new JMenuItem("bold");
        mBold.addActionListener(this);

        JMenuItem mItalic = new JMenuItem("italic");
        mItalic.addActionListener(this);

        JMenuItem mPlain = new JMenuItem("plain");
        mPlain.addActionListener(this);

        JMenuItem mBlue = new JMenuItem("blue");
        mBlue.addActionListener(this);

        JMenuItem mRed = new JMenuItem("red");
        mRed.addActionListener(this);

        JMenuItem mBlack = new JMenuItem("black");
        mBlack.addActionListener(this);

        JMenuItem mGreen = new JMenuItem("green");
        mGreen.addActionListener(this);

        // Membuat SubMenu File
        JMenu fileMenu = new JMenu("File");
        fileMenu.addActionListener(this);
        fileMenu.add(mNew);
        fileMenu.add(mOpen);
        fileMenu.add(mSave);
        menuBar.add(fileMenu);

        // Membuat SubMenu Tools
        JMenu toolsMenu = new JMenu("Tools");
        toolsMenu.addActionListener(this);
        toolsMenu.add(mCopy);
        toolsMenu.add(mPaste);
        toolsMenu.add(mCut);
        menuBar.add(toolsMenu);

        // Membuat SubMenu Style
        JMenu styleMenu = new JMenu("Style");
        styleMenu.addActionListener(this);
        styleMenu.add(mBold);
        styleMenu.add(mItalic);
        styleMenu.add(mPlain);
        menuBar.add(styleMenu);

        // Membuat SubMenu Color
        JMenu colorMenu = new JMenu("Color");
        colorMenu.addActionListener(this);
        colorMenu.add(mBlue);
        colorMenu.add(mRed);
        colorMenu.add(mBlack);
        colorMenu.add(mGreen);
        menuBar.add(colorMenu);

        // Set Menubar
        frame.setJMenuBar(menuBar);


        // Menampilkan window
        frame.add(textArea);
        frame.setSize(600, 600);
        frame.show();
   }

    @Override
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();

        // Mencocokan dari action event
        if (s.equals("copy")){
            textArea.copy();
        }else if (s.equals("paste")){
            textArea.paste();
        }else if (s.equals("cut")){
            textArea.cut();
        }else if(s.equals("save")){
            JFileChooser fileChooser = new JFileChooser("f:");

            int r = fileChooser.showSaveDialog(null);

            if(r == JFileChooser.APPROVE_OPTION){
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                try{
                    // Operasi I/O
                    FileWriter fileWriter = new FileWriter(file, false);
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

                    bufferedWriter.write(textArea.getText());
                    bufferedWriter.flush();
                    bufferedWriter.close();
                }catch(Exception exc){
                    JOptionPane.showMessageDialog(frame, exc.getMessage());
                }
            }
        }else if(s.equals("open")){
            JFileChooser fileChooser = new JFileChooser("f:");

            int r = fileChooser.showOpenDialog(null);

            if(r == JFileChooser.APPROVE_OPTION){
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath());

                try{
                    // Operasi I/O
                    String s1 = "";
                    String sl = "";

                    FileReader fileReader = new FileReader(file);
                    BufferedReader bufferedReader = new BufferedReader(fileReader);

                    sl = bufferedReader.readLine();

                    while((s1 = bufferedReader.readLine()) != null){
                        sl = sl + "\n" + s1;
                    }

                    textArea.setText(sl);
                }catch (Exception exc){
                    JOptionPane.showMessageDialog(frame, exc.getMessage());
                }
            }
        }else if(s.equals("new")){
            textArea.setText("");
        }else if(s.equals("bold")){
            Font curr  = textArea.getFont();
            Font bold  = null;
            int  style = curr.getStyle();
            if (style == Font.PLAIN) {
                bold = curr.deriveFont(Font.BOLD);
            } else if (style == Font.ITALIC) {
                bold = curr.deriveFont(Font.ITALIC | Font.BOLD);
            }
            if (bold != null) {
                textArea.setFont(bold);
            }
        }else if(s.equals("italic")){
            Font curr  = textArea.getFont();
            Font italic  = null;
            int  style = curr.getStyle();
            if (style == Font.PLAIN) {
                italic = curr.deriveFont(Font.ITALIC);
            } else if (style == Font.BOLD) {
                italic = curr.deriveFont(Font.ITALIC | Font.BOLD);
            }
            if (italic != null) {
                textArea.setFont(italic);
            }
        }else if(s.equals("plain")){
            Font plain = textArea.getFont().deriveFont(Font.PLAIN);
            textArea.setFont(plain);
        }else if(s.equals("blue")){
            textArea.setForeground(Color.BLUE);
        }else if(s.equals("red")){
            textArea.setForeground(Color.RED);
        }else if(s.equals("black")){
            textArea.setForeground(Color.BLACK);
        }else if(s.equals("green")){
            textArea.setForeground(Color.GREEN);
        }
    }

    public static void main(String[] args) {
        SimpleNotepad snp = new SimpleNotepad();
    }
}
