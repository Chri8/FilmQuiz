/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filmquiz;

import org.json.simple.JSONArray;

/**
 *
 * @author Christian
 */
public class GUI extends javax.swing.JFrame {

    Quiz quiz_ = new Quiz(this);
    Time timeFrame;
    name nameFrame;
    Bestenliste rankedFrame;
    Einstellungen einstellungsFrame = new Einstellungen(quiz_);
    
    JSONArray array2 = new JSONArray();

    /**
     * Creates new form GUI
     */
    public GUI() {
        
        
        quiz_.start();
        einstellungsFrame.runAtStart();

        timeFrame = new Time(quiz_);

        quiz_.timer();
        quiz_.c.startCountdown();
        quiz_.c.setPause(true);
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        showMe = new javax.swing.JPopupMenu();
        newGame = new javax.swing.JButton();
        A = new javax.swing.JButton();
        B = new javax.swing.JButton();
        C = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        stopbtn = new javax.swing.JToggleButton();
        playbtn1 = new javax.swing.JToggleButton();
        jLabel2 = new javax.swing.JLabel();
        bestenliste = new javax.swing.JButton();
        settings = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SchunterKino MovieQuiz");
        setResizable(false);

        newGame.setText("Neues Spiel");
        newGame.setName("Neues Spiel"); // NOI18N
        newGame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newGameActionPerformed(evt);
            }
        });

        A.setText("A");
        A.setName(""); // NOI18N
        A.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AActionPerformed(evt);
            }
        });

        B.setText("B");
        B.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BActionPerformed(evt);
            }
        });

        C.setText("C");
        C.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CActionPerformed(evt);
            }
        });

        jLabel1.setText("Noch : " + quiz_.TIMER + " Sek.");

        stopbtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/filmquiz/player_stop.png"))); // NOI18N
        stopbtn.setEnabled(false);
        stopbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stopbtnActionPerformed(evt);
            }
        });

        playbtn1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/filmquiz/player_play.png"))); // NOI18N
        playbtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playbtn1ActionPerformed(evt);
            }
        });

        jLabel2.setText("Frage : " + quiz_.max_Question + " / "+ quiz_.MAX_QUESTION);
        jLabel2.setToolTipText("");

        bestenliste.setText("Bestenliste");
        bestenliste.setAutoscrolls(true);
        bestenliste.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bestenlisteActionPerformed(evt);
            }
        });

        settings.setText("Einstellungen");
        settings.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                settingsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(bestenliste, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(settings, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(newGame, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(C, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(B, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(A, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(playbtn1, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(16, 16, 16)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(16, 16, 16)
                                .addComponent(stopbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 9, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(stopbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(playbtn1, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(20, 20, 20)
                .addComponent(A, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(B, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(C, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(newGame, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bestenliste, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(settings, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void newGameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newGameActionPerformed
        if (evt.getSource() == newGame) {
            nameFrame = new name(quiz_, this);
        }
    }//GEN-LAST:event_newGameActionPerformed


    private void AActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AActionPerformed
        String antwort = A.getText();
        if (evt.getSource() == A) {
            quiz_.check(antwort);
        }
    }//GEN-LAST:event_AActionPerformed

    private void BActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BActionPerformed
        String antwort = B.getText();
        if (evt.getSource() == B) {
            quiz_.check(antwort);
        }
    }//GEN-LAST:event_BActionPerformed

    private void stopbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stopbtnActionPerformed
        if (evt.getSource() == stopbtn) {
            quiz_.stopMusic();

            playbtn1.setEnabled(true);
            stopbtn.setEnabled(false);
        }
    }//GEN-LAST:event_stopbtnActionPerformed

    private void playbtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playbtn1ActionPerformed
        if (evt.getSource() == playbtn1) {
            quiz_.replayMusic();

            playbtn1.setEnabled(false);
            stopbtn.setEnabled(true);
        }
    }//GEN-LAST:event_playbtn1ActionPerformed

    private void CActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CActionPerformed
        String antwort = C.getText();
        if (evt.getSource() == C) {
            quiz_.check(antwort);
        }
    }//GEN-LAST:event_CActionPerformed

    private void bestenlisteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bestenlisteActionPerformed
        if (evt.getSource() == bestenliste) {
            quiz_.c.reset();
            rankedFrame = new Bestenliste();
            rankedFrame.setVisible(true);
        }
    }//GEN-LAST:event_bestenlisteActionPerformed

    private void settingsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_settingsActionPerformed
        if (evt.getSource() == settings) {
            einstellungsFrame.setVisible(true);
        }
    }//GEN-LAST:event_settingsActionPerformed
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
                new GUI().setVisible(true);
            }
        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton A;
    public javax.swing.JButton B;
    public javax.swing.JButton C;
    private javax.swing.JButton bestenliste;
    public javax.swing.JLabel jLabel1;
    public javax.swing.JLabel jLabel2;
    private javax.swing.JButton newGame;
    public javax.swing.JToggleButton playbtn1;
    private javax.swing.JToggleButton settings;
    public javax.swing.JPopupMenu showMe;
    public javax.swing.JToggleButton stopbtn;
    // End of variables declaration//GEN-END:variables

}
