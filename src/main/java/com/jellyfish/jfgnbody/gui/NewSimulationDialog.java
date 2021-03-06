package com.jellyfish.jfgnbody.gui;

import com.jellyfish.jfgnbody.interfaces.Writable;
import com.jellyfish.jfgnbody.nbody.NBody;
import com.jellyfish.jfgnbody.nbody.NBodyOpt;
import com.jellyfish.jfgnbody.nbody.NbodyCollection;
import com.jellyfish.jfgnbody.nbody.constants.NBodySimulations;
import com.jellyfish.jfgnbody.nbody.simulations.*;
import com.jellyfish.jfgnbody.starter.Starter;
import com.jellyfish.jfgnbody.utils.Rand2DCUtils;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSpinner.DefaultEditor;

/**
 *
 * @author thw
 */
public class NewSimulationDialog extends javax.swing.JDialog {

    /**
     * Creates new form NewSimulationDialog.
     *
     * @param parent
     */
    public NewSimulationDialog(java.awt.Frame parent) {
        super(parent, true);
        initComponents();
        ((DefaultEditor) this.nbodyCountSpinner.getEditor()).getTextField().setEditable(false);
        ((DefaultEditor) this.iSpeedSpinner.getEditor()).getTextField().setEditable(false);
        this.simulationComboBox.setModel(new DefaultComboBoxModel(NBodySimulations.sims));
        this.simulationComboBox.setEditable(false);
        this.simulationComboBox.setSelectedIndex(0);
        this.randStyleComboBox.setModel(new DefaultComboBoxModel<>(Rand2DCUtils.Layout.values()));
        this.randStyleComboBox.setSelectedIndex(0);
        this.setLocationRelativeTo(null);
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

        nbodyCountLabel = new javax.swing.JLabel();
        iterationSpeddLabel = new javax.swing.JLabel();
        newSimulationSubtitleLabel = new javax.swing.JLabel();
        newSimulationStartButton = new javax.swing.JButton();
        newSimulationCancelButton = new javax.swing.JButton();
        nbodyCountSpinner = new javax.swing.JSpinner();
        iSpeedSpinner = new javax.swing.JSpinner();
        simulationLabel = new javax.swing.JLabel();
        simulationComboBox = new javax.swing.JComboBox();
        randStyleComboBox = new javax.swing.JComboBox();
        randStyleLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("New NBody simulation");
        setModalExclusionType(java.awt.Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
        setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
        setResizable(false);

        nbodyCountLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        nbodyCountLabel.setText("Enter a body amount >= 100 & <= 1 410 065 408 : ");

        iterationSpeddLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        iterationSpeddLabel.setText("Iteration speed in milliseconds >= 10 & <= 1000 : ");

        newSimulationSubtitleLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        newSimulationSubtitleLabel.setText("Please submit a body amout (N = solor mass count) and iteration\\nspeed for a new simulation.");

        newSimulationStartButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        newSimulationStartButton.setText("Start simulation");
        newSimulationStartButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        newSimulationStartButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newSimulationStartButtonActionPerformed(evt);
            }
        });

        newSimulationCancelButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        newSimulationCancelButton.setText("Cancel");
        newSimulationCancelButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        newSimulationCancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newSimulationCancelButtonActionPerformed(evt);
            }
        });

        nbodyCountSpinner.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        nbodyCountSpinner.setModel(new javax.swing.SpinnerNumberModel(5000, 100, 1410065408, 1000));
        nbodyCountSpinner.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        nbodyCountSpinner.setDoubleBuffered(true);

        iSpeedSpinner.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        iSpeedSpinner.setModel(new javax.swing.SpinnerNumberModel(10, 10, 1000, 10));
        iSpeedSpinner.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        iSpeedSpinner.setDoubleBuffered(true);

        simulationLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        simulationLabel.setText("Select simulation :");

        simulationComboBox.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        simulationComboBox.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        simulationComboBox.setMaximumSize(new java.awt.Dimension(431, 32767));

        randStyleComboBox.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        randStyleComboBox.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        randStyleLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        randStyleLabel.setText("Select random generation style :");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(376, 376, 376)
                        .addComponent(newSimulationCancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(newSimulationStartButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(newSimulationSubtitleLabel)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(iterationSpeddLabel)
                                    .addComponent(nbodyCountLabel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(nbodyCountSpinner, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
                                    .addComponent(iSpeedSpinner)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(randStyleLabel)
                                    .addComponent(simulationLabel))
                                .addGap(32, 32, 32)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(simulationComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(randStyleComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                .addGap(35, 35, 35))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(newSimulationSubtitleLabel)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nbodyCountLabel)
                    .addComponent(nbodyCountSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(iterationSpeddLabel)
                    .addComponent(iSpeedSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(simulationLabel)
                    .addComponent(simulationComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(randStyleComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(randStyleLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 68, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(newSimulationStartButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(newSimulationCancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void newSimulationCancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newSimulationCancelButtonActionPerformed
        this.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_newSimulationCancelButtonActionPerformed

    @SuppressWarnings("UnnecessaryBoxing")
    private void newSimulationStartButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newSimulationStartButtonActionPerformed
        
        /**
         * FIXME : clean up this mess.
         */
        
        if (((MainFrame) this.getParent()).getnBodyPanel() instanceof NBody) {
            ((MainFrame) this.getParent()).getnBodyPanel().getForceUpdater().getMbs().clear();
            final AbstractSimulation sim = 
                (AbstractSimulation) this.simulationComboBox.getItemAt(this.simulationComboBox.getSelectedIndex());
            Starter.start((MainFrame) this.getParent(), (int) this.nbodyCountSpinner.getValue(),
                    (int) this.iSpeedSpinner.getValue(), sim);
            this.setVisible(false);
            final Writable w = ((MainFrame) this.getParent()).getnBodyPanel().getWriter();
            if (w != null) w.writeln(
                    String.format("--------------------------------------------------------\n" + 
                        "-    New simulation :\n-    Body count = %d\n-    Iteration speed = %d\n" +
                        "--------------------------------------------------------", 
                    (int) this.nbodyCountSpinner.getValue(), (int) this.iSpeedSpinner.getValue()));
            this.dispose();
        }
        
        if (((MainFrame) this.getParent()).getnBodyPanel() instanceof NBodyOpt) {
            ((MainFrame) this.getParent()).getnBodyPanel().getForceUpdater().getMbs().clear();
            final AbstractSimulation sim = 
                (AbstractSimulation) this.simulationComboBox.getItemAt(this.simulationComboBox.getSelectedIndex());

            ((MainFrame) this.getParent()).getnBodyPanel().setNCollection( 
                    new NbodyCollection((int) this.nbodyCountSpinner.getValue()));
            sim.start(((MainFrame) this.getParent()).getnBodyPanel(), (int) this.nbodyCountSpinner.getValue(),
                ((MainFrame) this.getParent()).getnBodyPanel().getNCollection(), 
                (Rand2DCUtils.Layout) this.randStyleComboBox.getItemAt(this.randStyleComboBox.getSelectedIndex()));

            this.setVisible(false);
            final Writable w = ((MainFrame) this.getParent()).getnBodyPanel().getWriter();
            if (w != null) w.writeln(
                    String.format("--------------------------------------------------------\n" + 
                        "-    New simulation :\n-    Body count = %d\n-    Iteration speed = %d\n" +
                        "--------------------------------------------------------", 
                    (int) this.nbodyCountSpinner.getValue(), (int) this.iSpeedSpinner.getValue()));
            this.dispose();
        }
    }//GEN-LAST:event_newSimulationStartButtonActionPerformed
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSpinner iSpeedSpinner;
    private javax.swing.JLabel iterationSpeddLabel;
    private javax.swing.JLabel nbodyCountLabel;
    private javax.swing.JSpinner nbodyCountSpinner;
    private javax.swing.JButton newSimulationCancelButton;
    private javax.swing.JButton newSimulationStartButton;
    private javax.swing.JLabel newSimulationSubtitleLabel;
    private javax.swing.JComboBox randStyleComboBox;
    private javax.swing.JLabel randStyleLabel;
    private javax.swing.JComboBox simulationComboBox;
    private javax.swing.JLabel simulationLabel;
    // End of variables declaration//GEN-END:variables

}
