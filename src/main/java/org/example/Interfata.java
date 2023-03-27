package org.example;

import java.awt.EventQueue;

import javax.swing.*;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Interfata {

    private JFrame frame;
    private JLabel Titlu;
    private JLabel Jlabel_pol1;
    private JLabel Jlabel_pol2;
    private JLabel txtAlegetiAplicatia;
    private JTextField text_polinom1;
    private JTextField text_polinom2;
    private JTextField text_rezultat;
    private JLabel lblNewLabel;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Interfata window = new Interfata();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public Interfata() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.getContentPane().setBackground(new Color(0, 0, 0));
        frame.setBounds(100, 100, 550, 426);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        Jlabel_pol1 = new JLabel();
        Jlabel_pol1.setFont(new Font("Tahoma", Font.BOLD, 12));
        Jlabel_pol1.setForeground(new Color(200, 230, 227));
        Jlabel_pol1.setBackground(new Color(248, 186, 135));
        Jlabel_pol1.setText("Introduceti primul polinom");
        Jlabel_pol1.setBounds(64, 97, 171, 19);
        frame.getContentPane().add(Jlabel_pol1);
        Jlabel_pol1.setLabelFor(text_polinom1);

        Titlu = new JLabel();
        Titlu.setForeground(new Color(200, 230, 227));
        Titlu.setBackground(new Color(200, 230, 227));
        Titlu.setFont(new Font("Tahoma", Font.BOLD, 20));
        Titlu.setText("   Calculator polinoame");
        Titlu.setBounds(134, 35, 240, 27);
        frame.getContentPane().add(Titlu);

        final JLabel rest = new JLabel("Rest: ");
        rest.setVisible(false);

        Jlabel_pol2 = new JLabel();
        Jlabel_pol2.setFont(new Font("Tahoma", Font.BOLD, 12));
        Jlabel_pol2.setForeground(new Color(200, 230, 227));
        Jlabel_pol2.setBackground(new Color(200, 230, 227));
        Jlabel_pol2.setText("Introduceti cel de al doilea polinom");
        Jlabel_pol2.setBounds(265, 97, 219, 19);
        frame.getContentPane().add(Jlabel_pol2);

        txtAlegetiAplicatia = new JLabel();
        txtAlegetiAplicatia.setForeground(new Color(248, 186, 135));
        txtAlegetiAplicatia.setBackground(new Color(248, 186, 135));
        txtAlegetiAplicatia.setFont(new Font("Tahoma", Font.BOLD, 14));
        txtAlegetiAplicatia.setText("Alegeti operatia pe care doriti sa o efectuati");
        txtAlegetiAplicatia.setBounds(95, 256, 347, 31);
        frame.getContentPane().add(txtAlegetiAplicatia);

        text_polinom1 = new JTextField();
        text_polinom1.setBackground(new Color(200, 230, 227));
        text_polinom1.setBounds(36, 123, 219, 27);
        frame.getContentPane().add(text_polinom1);
        text_polinom1.setColumns(10);

        text_polinom2 = new JTextField();
        Jlabel_pol2.setLabelFor(text_polinom2);
        text_polinom2.setColumns(10);
        text_polinom2.setBackground(new Color(200, 230, 227));
        text_polinom2.setBounds(265, 123, 219, 27);
        frame.getContentPane().add(text_polinom2);

        JButton buton_adunare = new JButton("Adunare");
        buton_adunare.setBackground(new Color(248, 186, 135));
        buton_adunare.setBounds(28, 297, 155, 19);
        buton_adunare.setOpaque(true);
        buton_adunare.setBorderPainted(false);
        frame.getContentPane().add(buton_adunare);

        buton_adunare.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Polinom p1 = new Polinom();
                Polinom p2 = new Polinom();
                try {

                    p1 = Parsing.parse(text_polinom1.getText());
                    p2 = Parsing.parse(text_polinom2.getText());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Wrong Pattern!", "Error", JOptionPane.WARNING_MESSAGE);
                }

                Polinom p3 = Polinom.sumPolinom(p1, p2);
                text_rezultat.setText(p3.toString());
                rest.setVisible(false);
            }
        });

        JButton buton_scadere = new JButton("Scadere");
        buton_scadere.setBackground(new Color(248, 186, 135));
        buton_scadere.setBounds(193, 297, 155, 19);
        buton_scadere.setOpaque(true);
        buton_scadere.setBorderPainted(false);
        frame.getContentPane().add(buton_scadere);
        buton_scadere.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Polinom p1 = new Polinom();
                Polinom p2 = new Polinom();
                try {
                    p1 = Parsing.parse(text_polinom1.getText());
                    p2 = Parsing.parse(text_polinom2.getText());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
                }


                Polinom p3 = Polinom.substractPolinom(p1, p2);
                text_rezultat.setText(p3.toString());
                rest.setVisible(false);
            }
        });


        JButton buton_inmultire = new JButton("Inmultire");
        buton_inmultire.setBackground(new Color(248, 186, 135));
        buton_inmultire.setBounds(358, 297, 155, 19);
        buton_inmultire.setOpaque(true);
        buton_inmultire.setBorderPainted(false);
        frame.getContentPane().add(buton_inmultire);
        buton_inmultire.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Polinom p1 = new Polinom();
                Polinom p2 = new Polinom();
                try {
                    p1 = Parsing.parse(text_polinom1.getText());
                    p2 = Parsing.parse(text_polinom2.getText());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
                }

                Polinom p3 = Polinom.multiplyPolinom(p1, p2);
                text_rezultat.setText(p3.toString());
                rest.setVisible(false);
            }
        });

        rest.setFont(new Font("Tahoma", Font.PLAIN, 14));
        rest.setForeground(new Color(248, 186, 135));
        rest.setBackground(new Color(248, 211, 137));
        rest.setBounds(107, 227, 267, 27);
        frame.getContentPane().add(rest);

        JButton buton_impartire = new JButton("Impartire");
        buton_impartire.setBackground(new Color(248, 186, 135));
        buton_impartire.setBounds(28, 326, 155, 19);
        buton_impartire.setOpaque(true);
        buton_impartire.setBorderPainted(false);
        frame.getContentPane().add(buton_impartire);
        buton_impartire.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Polinom p1 = new Polinom();
                Polinom p2 = new Polinom();
                try {
                    p1 = Parsing.parse(text_polinom1.getText());
                    p2 = Parsing.parse(text_polinom2.getText());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Wrong Pattern!", "Error", JOptionPane.WARNING_MESSAGE);
                }
                try {
                    ArrayList<Polinom>rezultateImpartire = new ArrayList<Polinom>();
                    rezultateImpartire = Polinom.dividePolinom(p1, p2);
                    text_rezultat.setText(rezultateImpartire.get(0).toString());
                    rest.setVisible(true);
                    rest.setText("Rest: " + rezultateImpartire.get(1));
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        JButton butonDerivare = new JButton("Derivare");
        butonDerivare.setBackground(new Color(248, 186, 135));
        butonDerivare.setBounds(193, 326, 155, 19);
        butonDerivare.setOpaque(true);
        butonDerivare.setBorderPainted(false);
        frame.getContentPane().add(butonDerivare);
        butonDerivare.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Polinom p1 = new Polinom();
                try {
                    p1 = Parsing.parse(text_polinom1.getText());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
                }

                Polinom p3 = Polinom.derivativePolinom(p1);
                text_rezultat.setText(p3.toString());
                rest.setVisible(false);
            }
        });


        JButton butonIntegrare = new JButton("Integrare");
        butonIntegrare.setBackground(new Color(248, 186, 135));
        butonIntegrare.setBounds(358, 326, 155, 19);
        butonIntegrare.setOpaque(true);
        butonIntegrare.setBorderPainted(false);
        frame.getContentPane().add(butonIntegrare);
        butonIntegrare.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Polinom p1 = new Polinom();
                try {
                    p1 = Parsing.parse(text_polinom1.getText());
                } catch (Exception ex) {

                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
                }

                Polinom p3 = Polinom.integratePolinom(p1);
                text_rezultat.setText(p3.toString() + " +C");
                rest.setVisible(false);
            }
        });

        text_rezultat = new JTextField();
        text_rezultat.setColumns(10);
        text_rezultat.setBackground(new Color(200, 230, 227));
        text_rezultat.setBounds(144, 190, 219, 27);
        frame.getContentPane().add(text_rezultat);

        lblNewLabel = new JLabel("Rezultatul operatiei");
        lblNewLabel.setLabelFor(text_rezultat);
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblNewLabel.setForeground(new Color(200, 230, 227));
        lblNewLabel.setBounds(182, 164, 155, 16);
        frame.getContentPane().add(lblNewLabel);
    }
}

