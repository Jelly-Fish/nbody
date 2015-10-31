package com.jellyfish.jfgnbody.gui;

import com.jellyfish.jfgnbody.nbody.NBody;
import com.jellyfish.jfgnbody.nbody.space.SpatialArea;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;

/**
 *
 * @author thw
 */
public class MainFrame extends javax.swing.JFrame {

    /**
     * Panel for graphic display.
     */
    private final NBody panel;
    
    /**
     * Creates new form MainFrame
     * @param panel
     */
    public MainFrame(final NBody panel) {
        
        initComponents();
        this.setBackground(new Color(250,250,250));
        this.getContentPane().setBackground(new Color(250,250,250));
        Image icon = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB_PRE);
        this.setIconImage(icon);
        this.panel = panel;
        this.setLayout(new BorderLayout());
        this.add(this.panel, BorderLayout.CENTER);
        this.pack();
        this.panel.spatialArea = new SpatialArea(0, 0, this.panel.getWidth(), this.panel.getHeight());
        this.setLocationRelativeTo(null);
        this.setExtendedState(this.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        this.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        simulationMenu = new javax.swing.JMenu();
        newSimulationMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("NBody - Gravity simulation");
        setBackground(new java.awt.Color(51, 51, 51));
        setIconImages(null);
        setName("mainframe"); // NOI18N
        setPreferredSize(new java.awt.Dimension(800, 600));
        addWindowStateListener(new java.awt.event.WindowStateListener() {
            public void windowStateChanged(java.awt.event.WindowEvent evt) {
                formWindowStateChanged(evt);
            }
        });

        jMenuBar1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        simulationMenu.setBorder(null);
        simulationMenu.setText("Simulation");

        newSimulationMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        newSimulationMenuItem.setText("Start a new simulation");
        newSimulationMenuItem.setBorder(null);
        newSimulationMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newSimulationMenuItemActionPerformed(evt);
            }
        });
        simulationMenu.add(newSimulationMenuItem);

        jMenuBar1.add(simulationMenu);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 710, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 478, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    private void newSimulationMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newSimulationMenuItemActionPerformed
        new NewSimulationDialog(this);
    }//GEN-LAST:event_newSimulationMenuItemActionPerformed

    private void formWindowStateChanged(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowStateChanged
        this.panel.spatialArea.updateSize(this.panel.getWidth(), this.panel.getHeight());
    }//GEN-LAST:event_formWindowStateChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem newSimulationMenuItem;
    private javax.swing.JMenu simulationMenu;
    // End of variables declaration//GEN-END:variables
    
    public void resetSimulation(final int n, final int iSpeed) {
        this.panel.restart(n, iSpeed);
    }
    
}
