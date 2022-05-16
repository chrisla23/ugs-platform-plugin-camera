/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.pelrun.ugs.nbm;

import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.windows.TopComponent;
import org.openide.util.NbBundle.Messages;
import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamDiscoveryEvent;
import com.github.sarxos.webcam.WebcamDiscoveryListener;
import com.github.sarxos.webcam.WebcamImageTransformer;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import javax.swing.JColorChooser;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(
        dtd = "-//net.pelrun.ugs.nbm.camera//Camera//EN",
        autostore = false
)
@TopComponent.Description(
        preferredID = "cameraTopComponent",
        //iconBase="SET/PATH/TO/ICON/HERE",
        persistenceType = TopComponent.PERSISTENCE_ALWAYS
)
@TopComponent.Registration(mode = "editor", openAtStartup = false)

@ActionID(category = "Window", id = "net.pelrun.ugs.nbm.cameraTopComponent")
@ActionReference(path = "Menu/Window/Plugins" /*, position = 333 */)
@TopComponent.OpenActionRegistration(
        displayName = "Camera",
        preferredID = "cameraTopComponent"
)
@Messages({
    "CTL_cameraAction=camera",
    "CTL_cameraTopComponent=Camera Window",
    "HINT_cameraTopComponent=This is a camera window"
})

public final class cameraTopComponent extends TopComponent implements WebcamDiscoveryListener, ItemListener, WebcamImageTransformer {

    private Color crosshairColour = Color.GREEN;

    public cameraTopComponent() {
        initComponents();
        setName(Bundle.CTL_cameraTopComponent());
        //setToolTipText(Bundle.HINT_cameraTopComponent());

        initCamera(webcamPicker1.getSelectedWebcam());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cameraLabel = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        webcamPicker1 = new com.github.sarxos.webcam.WebcamPicker();
        crosshairComboBox = new javax.swing.JComboBox<>();
        crosshairColourButton = new javax.swing.JButton();

        setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        setDisplayName(org.openide.util.NbBundle.getMessage(cameraTopComponent.class, "cameraTopComponent.displayName")); // NOI18N
        setMinimumSize(new java.awt.Dimension(340, 200));

        org.openide.awt.Mnemonics.setLocalizedText(cameraLabel, org.openide.util.NbBundle.getMessage(cameraTopComponent.class, "cameraTopComponent.cameraLabel.text")); // NOI18N

        jPanel1.setLayout(new java.awt.BorderLayout());

        crosshairComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "No Crosshair", "90 degree", "45 degree" , "90 degree with circles", "45 degree with circles" }));

        org.openide.awt.Mnemonics.setLocalizedText(crosshairColourButton, org.openide.util.NbBundle.getMessage(cameraTopComponent.class, "cameraTopComponent.crosshairColourButton.text")); // NOI18N
        crosshairColourButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crosshairColourButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cameraLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(webcamPicker1, javax.swing.GroupLayout.DEFAULT_SIZE, 472, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(crosshairComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(crosshairColourButton)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cameraLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(webcamPicker1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 311, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(crosshairComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(crosshairColourButton))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void crosshairColourButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_crosshairColourButtonActionPerformed
        Color newColour = JColorChooser.showDialog(this,"Choose Crosshair Colour",crosshairColour);
        if (newColour != null) {
            crosshairColour = newColour;
        }
    }//GEN-LAST:event_crosshairColourButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel cameraLabel;
    private javax.swing.JButton crosshairColourButton;
    private javax.swing.JComboBox<String> crosshairComboBox;
    private javax.swing.JPanel jPanel1;
    private com.github.sarxos.webcam.WebcamPicker webcamPicker1;
    // End of variables declaration//GEN-END:variables

    private Webcam webcam;
    private WebcamPanel webcamPanel;

    private void startCamera() {
        Thread t = new Thread() {
            @Override
            public void run() {
                webcamPanel.start();
            }
        };
        t.setName("background-camera-start");
        t.setDaemon(true);
        t.start();
    }

    private void initCamera(Webcam w) {
        if (w != null)
        {
            webcam = w;
            webcam.setViewSize(WebcamResolution.HD.getSize());
            webcamPanel = new WebcamPanel(webcam, null, false);
            webcamPanel.setFitArea(true);

            jPanel1.add(webcamPanel, BorderLayout.CENTER);
            jPanel1.revalidate();
            jPanel1.repaint();

            webcam.setImageTransformer(this);
            Webcam.addDiscoveryListener(this);
            webcamPicker1.addItemListener(this);
        }
    }

    @Override
    public void componentOpened() {
        //initCamera(webcamPicker1.getSelectedWebcam());
        startCamera();
    }

    @Override
    public void componentClosed() {
        webcamPanel.stop();
        //webcam.close();
    }

    void writeProperties(java.util.Properties p) {
        // better to version settings since initial version as advocated at
        // http://wiki.apidesign.org/wiki/PropertyFiles
        p.setProperty("version", "1.0");

        p.setProperty("crosshairType", crosshairComboBox.getSelectedItem().toString());
        p.setProperty("crosshairColour", "0x" + Integer.toHexString(crosshairColour.getRGB() & 0xFFFFFF));
    }

    void readProperties(java.util.Properties p) {
        String version = p.getProperty("version");
        // TODO read your settings according to their version
        if (version == null || version.equals("1.0")) {
            crosshairComboBox.setSelectedItem(p.getProperty("crosshairType", "No Crosshair"));
            crosshairColour = Color.decode(p.getProperty("crosshairColour", "00FF00"));
        }
        else
        {
            crosshairComboBox.setSelectedItem("No Crosshair");
            crosshairColour = Color.GREEN;
        }
    }

    @Override
    public void webcamFound(WebcamDiscoveryEvent event) {
        if (webcamPicker1 != null) {
                webcamPicker1.addItem(event.getWebcam());
        }
    }

    @Override
    public void webcamGone(WebcamDiscoveryEvent event) {
        if (webcamPicker1 != null) {
                webcamPicker1.removeItem(event.getWebcam());
        }
    }

    @Override
    public void itemStateChanged(ItemEvent ie) {
        if (ie.getItem() != webcam) {
            if (webcam != null) {
                webcamPanel.stop();

                jPanel1.remove(webcamPanel);
                jPanel1.revalidate();
                jPanel1.repaint();

                Webcam.removeDiscoveryListener(this);
                webcam.close();
            }
            if (ie.getItem() != null)
            {
                initCamera(webcamPicker1.getSelectedWebcam());
                startCamera();
            }
        }
    }

    @Override
    public BufferedImage transform(BufferedImage bi) {
        int w = bi.getWidth();
        int h = bi.getHeight();
        int r = 10;

        Graphics2D g2 = bi.createGraphics();
        g2.setPaint(crosshairColour);
        switch(crosshairComboBox.getSelectedIndex())
        {
            case 1:
                g2.drawLine(0,h/2,w,h/2);
                g2.drawLine(w/2,0,w/2,h);
                break;
            case 2:
                g2.drawLine(0,0,w,h);
                g2.drawLine(0,h,w,0);
                break;
            case 3:
                g2.drawLine(0,h/2,w,h/2);
                g2.drawLine(w/2,0,w/2,h);
                g2.drawOval(w/2-2*r/2,h/2-2*r/2,2*r,2*r);
                g2.drawOval(w/2-8*r/2,h/2-8*r/2,8*r,8*r);
                g2.drawOval(w/2-16*r/2,h/2-16*r/2,16*r,16*r);
                g2.drawOval(w/2-24*r/2,h/2-24*r/2,24*r,24*r);
                break;
            case 4:
                g2.drawLine(0,0,w,h);
                g2.drawLine(0,h,w,0);
                g2.drawOval(w/2-2*r/2,h/2-2*r/2,2*r,2*r);
                g2.drawOval(w/2-8*r/2,h/2-8*r/2,8*r,8*r);
                g2.drawOval(w/2-16*r/2,h/2-16*r/2,16*r,16*r);
                g2.drawOval(w/2-24*r/2,h/2-24*r/2,24*r,24*r);
                break;
            default:
                break;
        }
        g2.dispose();

        bi.flush();

        return bi;
    }

}
