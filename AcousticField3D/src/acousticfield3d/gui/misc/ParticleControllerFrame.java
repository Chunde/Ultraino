/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acousticfield3d.gui.misc;

import acousticfield3d.gui.MainForm;
import acousticfield3d.scene.Entity;
import acousticfield3d.utils.Parse;
import java.util.ArrayList;
import javax.swing.JTextField;
import net.java.games.input.Component;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;

/**
 *
 * @author am14010
 */
public class ParticleControllerFrame extends javax.swing.JFrame {
    final MainForm mf;
    
    private final ArrayList<Controller> controllers = new ArrayList<>();
    
    private Controller currentController = null;
    private final ArrayList<Component> currentComponents = new ArrayList<>();
    private Component xComponent = null;
    private Component yComponent = null;
    private Component zComponent = null;
    private Component rComponent = null;
    private Component uComponent = null; boolean uPrev = false;
    private Component dComponent = null; boolean dPrev = false;
    
    public ParticleControllerFrame(MainForm mf) {
        this.mf = mf;
        initComponents();
    }

    public void tick(){
        if (! activeCheck.isSelected()){ return; }
        
        //poll the devices
        if (currentController != null) { currentController.poll(); }
        else { return; }
        
        //update the axes
        final float x = updateAxis(xComponent, xOffsetText, xGainText, xRawText, xFilteredText);
        final float y = updateAxis(yComponent, yOffsetText, yGainText, yRawText, yFilteredText);
        final float z = updateAxis(zComponent, zOffsetText, zGainText, zRawText, zFilteredText);
        final float r = updateAxis(rComponent, null, null, rRawText, null);
        final float u = updateAxis(uComponent, null, null, uRawText, null);
        final float d = updateAxis(dComponent, null, null, dRawText, null);
         
        //reset button
        if (r >= 1.0f){
            mf.movePanel.resetParticlePos();
        }
        
        //next or prev particle
        if (u >= 1.0f){
            if (!uPrev){
                mf.movePanel.incBeadSelNumber(1);
            }
            uPrev = true;
        }else{
            uPrev = false;
        }
        if (d >= 1.0f){
            if (!dPrev){
                mf.movePanel.incBeadSelNumber(-1);
            }
            dPrev = true;
        }else{
            dPrev = false;
        }
        
        //move the particle
        final Entity e = mf.movePanel.getBeadEntity();
        if (e == null){ return;}
        e.getTransform().getTranslation().addLocal(x, y, z);
        
        
      
        
        //run the algorithm
        mf.movePanel.doAutoCalcAndSend();
        
        //send and update
        mf.needUpdate();
    }
    
    private float updateAxis(Component component, JTextField offsetText, JTextField gainText, JTextField rawText, JTextField filteredText) {
        if (component == null){ return 0; }
        
        final float val = component.getPollData();
        final float offset = offsetText != null ? Float.parseFloat( offsetText.getText() ) : 0.0f;
        final float gain = gainText != null ? Float.parseFloat( gainText.getText() ) : 1.0f;
       
        final float filteredVal = (val - offset) * gain;
        
        if (rawText != null) { rawText.setText( mf.formats.dc4( val ) ); }
        if (filteredText != null) { filteredText.setText( mf.formats.dc4( filteredVal ) ); }
        
        return filteredVal;
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        controllersCombo = new javax.swing.JComboBox();
        updateButton = new javax.swing.JButton();
        pickButton = new javax.swing.JButton();
        activeCheck = new javax.swing.JCheckBox();
        jLabel2 = new javax.swing.JLabel();
        xCombo = new javax.swing.JComboBox();
        xOffsetText = new javax.swing.JTextField();
        xGainText = new javax.swing.JTextField();
        xRawText = new javax.swing.JTextField();
        xFilteredText = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        yCombo = new javax.swing.JComboBox();
        yOffsetText = new javax.swing.JTextField();
        yGainText = new javax.swing.JTextField();
        yRawText = new javax.swing.JTextField();
        yFilteredText = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        zCombo = new javax.swing.JComboBox();
        zOffsetText = new javax.swing.JTextField();
        zGainText = new javax.swing.JTextField();
        zRawText = new javax.swing.JTextField();
        zFilteredText = new javax.swing.JTextField();
        cleanButton = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        waitMsText = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        rCombo = new javax.swing.JComboBox();
        rRawText = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        uCombo = new javax.swing.JComboBox();
        uRawText = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        dCombo = new javax.swing.JComboBox();
        dRawText = new javax.swing.JTextField();

        setTitle("Control Particles");

        jLabel1.setText("Controllers:");

        controllersCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Click update to get controllers" }));

        updateButton.setText("Update");
        updateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateButtonActionPerformed(evt);
            }
        });

        pickButton.setText("Pick");
        pickButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pickButtonActionPerformed(evt);
            }
        });

        activeCheck.setText("active");
        activeCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                activeCheckActionPerformed(evt);
            }
        });

        jLabel2.setText("X:");

        xCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        xCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                xComboActionPerformed(evt);
            }
        });
        xCombo.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                xComboPropertyChange(evt);
            }
        });

        xOffsetText.setText("0.0");

        xGainText.setText("1.0");
        xGainText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                xGainTextActionPerformed(evt);
            }
        });

        xRawText.setEditable(false);
        xRawText.setText("rawValue");

        xFilteredText.setEditable(false);
        xFilteredText.setText("movement");

        jLabel3.setText("Y:");

        yCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        yCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                yComboActionPerformed(evt);
            }
        });
        yCombo.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                yComboPropertyChange(evt);
            }
        });

        yOffsetText.setText("0.0");

        yGainText.setText("1.0");

        yRawText.setEditable(false);
        yRawText.setText("rawValue");

        yFilteredText.setEditable(false);
        yFilteredText.setText("movement");

        jLabel4.setText("Z:");

        zCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        zCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                zComboActionPerformed(evt);
            }
        });
        zCombo.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                zComboPropertyChange(evt);
            }
        });

        zOffsetText.setText("0.0");

        zGainText.setText("1.0");

        zRawText.setEditable(false);
        zRawText.setText("rawValue");

        zFilteredText.setEditable(false);
        zFilteredText.setText("movement");

        cleanButton.setText("Clean");
        cleanButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cleanButtonActionPerformed(evt);
            }
        });

        jLabel5.setText("wait ms:");

        waitMsText.setText("33");

        jLabel6.setText("R:");

        rCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        rCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rComboActionPerformed(evt);
            }
        });
        rCombo.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                rComboPropertyChange(evt);
            }
        });

        rRawText.setEditable(false);
        rRawText.setText("rawValue");

        jLabel7.setText("U:");

        uCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        uCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uComboActionPerformed(evt);
            }
        });
        uCombo.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                uComboPropertyChange(evt);
            }
        });

        uRawText.setEditable(false);
        uRawText.setText("rawValue");

        jLabel8.setText("D:");

        dCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        dCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dComboActionPerformed(evt);
            }
        });
        dCombo.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dComboPropertyChange(evt);
            }
        });

        dRawText.setEditable(false);
        dRawText.setText("rawValue");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(yCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(yOffsetText)
                                        .addGap(4, 4, 4)
                                        .addComponent(yGainText)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(yRawText, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(xCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(xOffsetText, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(xGainText, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(xRawText))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(controllersCombo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(activeCheck)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(waitMsText, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(pickButton)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(zCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(zOffsetText, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(zGainText, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(zRawText, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(4, 4, 4)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(xFilteredText)
                            .addComponent(yFilteredText)
                            .addComponent(zFilteredText)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(updateButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cleanButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(164, 164, 164)
                                .addComponent(rRawText, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(uCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(164, 164, 164)
                                .addComponent(uRawText, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(164, 164, 164)
                                .addComponent(dRawText, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(activeCheck)
                    .addComponent(jLabel5)
                    .addComponent(waitMsText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(controllersCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(updateButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pickButton)
                    .addComponent(cleanButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(xCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(xOffsetText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(xGainText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(xRawText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(xFilteredText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(yCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(yOffsetText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(yGainText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(yRawText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(yFilteredText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(zCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(zOffsetText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(zGainText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(zRawText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(zFilteredText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(rCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rRawText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(uCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(uRawText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(dCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dRawText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void xGainTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_xGainTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_xGainTextActionPerformed

    private void updateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateButtonActionPerformed
        controllersCombo.removeAllItems();
        
        final ControllerEnvironment ce = ControllerEnvironment.getDefaultEnvironment();
	final Controller[] ca = ce.getControllers();
        for(Controller c : ca){
            addController(c);
        }
    }//GEN-LAST:event_updateButtonActionPerformed

    private void cleanButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cleanButtonActionPerformed
        currentController = null;
        currentComponents.clear();
        xCombo.removeAllItems();
        yCombo.removeAllItems();
        zCombo.removeAllItems();
        rCombo.removeAllItems();
        uCombo.removeAllItems();
        dCombo.removeAllItems();
        xComponent = null;
        yComponent = null;
        zComponent = null;
        rComponent = null;
        uComponent = null;
        dComponent = null;
    }//GEN-LAST:event_cleanButtonActionPerformed

    private void pickButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pickButtonActionPerformed
        final int index = controllersCombo.getSelectedIndex();
        if (index == -1){ return; }
        cleanButtonActionPerformed(evt);
        currentController = controllers.get( index );
        
        expandCurrentController();
    }//GEN-LAST:event_pickButtonActionPerformed

    private void xComboPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_xComboPropertyChange
        
    }//GEN-LAST:event_xComboPropertyChange

    private void yComboPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_yComboPropertyChange
        
    }//GEN-LAST:event_yComboPropertyChange

    private void zComboPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_zComboPropertyChange
        
    }//GEN-LAST:event_zComboPropertyChange

    private void xComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_xComboActionPerformed
        final int selectedIndex = xCombo.getSelectedIndex();
        if (selectedIndex == -1 || currentComponents.isEmpty()){ return; }
        xComponent = currentComponents.get(selectedIndex);
    }//GEN-LAST:event_xComboActionPerformed

    private void yComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_yComboActionPerformed
        final int selectedIndex = yCombo.getSelectedIndex();
        if (selectedIndex == -1 || currentComponents.isEmpty()){ return; }
        yComponent = currentComponents.get(selectedIndex);
    }//GEN-LAST:event_yComboActionPerformed

    private void zComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_zComboActionPerformed
        final int selectedIndex = zCombo.getSelectedIndex();
        if (selectedIndex == -1 || currentComponents.isEmpty()){ return; }
        zComponent = currentComponents.get(selectedIndex);
    }//GEN-LAST:event_zComboActionPerformed

    private void activeCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_activeCheckActionPerformed
        mf.updateThread.setMilliSeconds( Parse.toInt( waitMsText.getText() ));
        mf.updateThread.setPlaying( activeCheck.isSelected() );
        mf.updateThread.playOrPause();
    }//GEN-LAST:event_activeCheckActionPerformed

    private void rComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rComboActionPerformed
        final int selectedIndex = rCombo.getSelectedIndex();
        if (selectedIndex == -1 || currentComponents.isEmpty()){ return; }
        rComponent = currentComponents.get(selectedIndex);
    }//GEN-LAST:event_rComboActionPerformed

    private void rComboPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_rComboPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_rComboPropertyChange

    private void uComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uComboActionPerformed
        final int selectedIndex = uCombo.getSelectedIndex();
        if (selectedIndex == -1 || currentComponents.isEmpty()){ return; }
        uComponent = currentComponents.get(selectedIndex);
    }//GEN-LAST:event_uComboActionPerformed

    private void uComboPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_uComboPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_uComboPropertyChange

    private void dComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dComboActionPerformed
        final int selectedIndex = dCombo.getSelectedIndex();
        if (selectedIndex == -1 || currentComponents.isEmpty()){ return; }
        dComponent = currentComponents.get(selectedIndex);
    }//GEN-LAST:event_dComboActionPerformed

    private void dComboPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dComboPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_dComboPropertyChange
    
    private void expandCurrentController(){
        //populate the combo boxes and the list of components
        final Component[] components = currentController.getComponents();
        
        for (Component c : components){
            final String name = c.getName();
            xCombo.addItem( name );
            yCombo.addItem( name );
            zCombo.addItem( name );
            rCombo.addItem( name );
            uCombo.addItem( name );
            dCombo.addItem( name );
            currentComponents.add(c);
        }
    }
    
    private void addController(final Controller c){
        Controller[] subControllers = c.getControllers();
        if (subControllers.length == 0) {
            controllersCombo.addItem( c.getName() );
            controllers.add( c );
        } else {
            for (Controller subC : subControllers) {
                addController( subC );
            }
        }
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox activeCheck;
    private javax.swing.JButton cleanButton;
    private javax.swing.JComboBox controllersCombo;
    private javax.swing.JComboBox dCombo;
    private javax.swing.JTextField dRawText;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JButton pickButton;
    private javax.swing.JComboBox rCombo;
    private javax.swing.JTextField rRawText;
    private javax.swing.JComboBox uCombo;
    private javax.swing.JTextField uRawText;
    private javax.swing.JButton updateButton;
    private javax.swing.JTextField waitMsText;
    private javax.swing.JComboBox xCombo;
    private javax.swing.JTextField xFilteredText;
    private javax.swing.JTextField xGainText;
    private javax.swing.JTextField xOffsetText;
    private javax.swing.JTextField xRawText;
    private javax.swing.JComboBox yCombo;
    private javax.swing.JTextField yFilteredText;
    private javax.swing.JTextField yGainText;
    private javax.swing.JTextField yOffsetText;
    private javax.swing.JTextField yRawText;
    private javax.swing.JComboBox zCombo;
    private javax.swing.JTextField zFilteredText;
    private javax.swing.JTextField zGainText;
    private javax.swing.JTextField zOffsetText;
    private javax.swing.JTextField zRawText;
    // End of variables declaration//GEN-END:variables

    
}
