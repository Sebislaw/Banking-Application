package application.component;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.Objects;

public class Header extends javax.swing.JPanel {

    public Header() {
        initComponents();
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs.create();
        //HEADER COLOR
        g2.setPaint(new GradientPaint(0, 0, new Color(5, 70, 161), 0, getHeight(), new Color(2, 85, 201)));
        g2.fill(new Rectangle2D.Double(0, 0, getWidth(), getHeight()));
        g2.dispose();
        super.paintComponent(grphcs);
    }

    private void initComponents() {
        javax.swing.JLabel jLabel1 = new javax.swing.JLabel();
        jLabel1.setFont(new java.awt.Font("sansserif", Font.BOLD, 18));
        jLabel1.setForeground(new java.awt.Color(237, 237, 237));
        jLabel1.setText("Banking Application");
        jLabel1.setIcon(new ImageIcon(new javax.swing.ImageIcon(
                Objects.requireNonNull(getClass().getResource("/application/component/pictures/header.png")))
                .getImage().getScaledInstance(50, 45, Image.SCALE_SMOOTH)));
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1)
                                .addContainerGap(215, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                                .addContainerGap())
        );
    }

}