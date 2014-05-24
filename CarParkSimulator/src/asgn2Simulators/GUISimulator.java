
package asgn2Simulators;

import asgn2CarParks.CarPark;
import asgn2Exceptions.SimulationException;
import java.awt.TextField;
import java.io.IOException;
import javax.swing.JTextField;

public class GUISimulator extends javax.swing.JFrame {


    private CarPark carPark;
    private Simulator sim;
    private Log log;
    private SimulationRunner sr;
    
    public GUISimulator() {
        initComponents();
        
        maxCarSpacesField.setText(String.valueOf(Constants.DEFAULT_MAX_CAR_SPACES));
        maxSmallCarSpacesField.setText(String.valueOf(Constants.DEFAULT_MAX_SMALL_CAR_SPACES));
        maxMotorCycleSpacesField.setText(String.valueOf(Constants.DEFAULT_MAX_MOTORCYCLE_SPACES));
        maxQueueLengthField.setText(String.valueOf(Constants.DEFAULT_MAX_QUEUE_SIZE));
       
        simulationSeedField.setText(String.valueOf(Constants.DEFAULT_SEED));
        meanStayDurationField.setText(String.valueOf(Constants.DEFAULT_INTENDED_STAY_MEAN));
        standardStayDurationField.setText(String.valueOf(Constants.DEFAULT_INTENDED_STAY_SD));
    }
   
    public GUISimulator(String[] args) {
        initComponents();
    }
    
    //Sets up a CarPark with the given arguments
    private CarPark setUpCarParkFromUiFields()
    {
            final int maxCarSpaces = Integer.parseInt(this.maxCarSpacesField.getText());
            final int maxSmallCarSpaces = Integer.parseInt(this.maxSmallCarSpacesField.getText());
            final int maxMotorCycleSpaces = Integer.parseInt(this.maxMotorCycleSpacesField.getText());
            final int maxQueueSize = Integer.parseInt(this.maxQueueLengthField.getText());

            return new CarPark(maxCarSpaces, maxSmallCarSpaces, maxMotorCycleSpaces, maxQueueSize);
    }

    // Sets up a CarPark with the given arguments
    private Simulator setUpSimulatorFromUFields() throws SimulationException
    {
            final int seed = Integer.parseInt(this.simulationSeedField.getText());
            final double meanStay = Double.parseDouble(this.meanStayDurationField.getText());
            final double sdStay = Double.parseDouble(this.standardStayDurationField.getText());
            
            final double carProb = this.carArrivalProbSlider.getValue() / 100.0f;
            final double smallCarProb = this.smallCarArrivalProbSlider.getValue() / 100.0f;
            final double motorCycleProb = this.motorCycleArrivalProbSlider.getValue() / 100.0f;

            return new Simulator(seed, meanStay, sdStay, carProb, smallCarProb, motorCycleProb);
    }
    
    private void checkIfFieldIntegerValueIsHigherOrEqualTo(JTextField field, int value) throws Exception
    {  
        int fieldValue;
        
        try {
            fieldValue = Integer.parseInt(field.getText());
        } catch(NumberFormatException e){
            throw new Exception("Value for the " + field.getToolTipText() + " field must be a valid integer");
        }
        
        if(fieldValue < value)
            throw new Exception("Value for the " + field.getToolTipText() + " field must be higher than " + value);
    }
    
    private void checkIfFieldDoubleValueIsHigherOrEqualTo(JTextField field, double value) throws Exception
    {  
        double fieldValue;
        
        try {
            fieldValue = Double.parseDouble(field.getText());
        } catch(NumberFormatException e){
            throw new Exception("Value for the " + field.getToolTipText() + " field must be a valid integer");
        }
        
        if(fieldValue < value)
            throw new Exception("Value for the " + field.getToolTipText() + " field must be higher than " + value);
    }
    
    private boolean checkUiFieldsValidity()
    {
        try {
            checkIfFieldIntegerValueIsHigherOrEqualTo(maxCarSpacesField, 0);
            checkIfFieldIntegerValueIsHigherOrEqualTo(maxSmallCarSpacesField, 0);
            checkIfFieldIntegerValueIsHigherOrEqualTo(maxMotorCycleSpacesField, 0);
            checkIfFieldIntegerValueIsHigherOrEqualTo(maxQueueLengthField, 0);
            
            checkIfFieldDoubleValueIsHigherOrEqualTo(simulationSeedField, 0.0f);
            checkIfFieldDoubleValueIsHigherOrEqualTo(meanStayDurationField, 0.0f);
            checkIfFieldDoubleValueIsHigherOrEqualTo(standardStayDurationField, 0.0f);

        } catch(Exception e) {
            outputToTextArea(e.getMessage());
            return false;
        }
        
        return true;
    }
   
    private void launchCarParkSimulation()
    {
        if(!checkUiFieldsValidity())
            return;
        
        outputToTextArea("Launching simulation...");

        try {
            carPark =  setUpCarParkFromUiFields();
            sim = setUpSimulatorFromUFields();
            log = new Log();
        } catch (IOException | SimulationException e1) {
            e1.printStackTrace();
            outputToTextArea(e1.getMessage());    
        }
		
        //Run the simulation 
        sr = new SimulationRunner(carPark, sim, log);
        try {
            sr.runSimulation();
        } catch (Exception e) {
            e.printStackTrace();
            outputToTextArea(e.getMessage());
        }
        
        outputToTextArea("Simulation completed !");
    }
    
    private void outputToTextArea(String str)
    {
        simulationResultsTextArea.append(str + "\n");
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        maxCarSpacesLbl = new javax.swing.JLabel();
        maxSmallCarSpacesLbl = new javax.swing.JLabel();
        maxMotorCycleSpacesLbl = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        maxQueueLengthLbl = new javax.swing.JLabel();
        maxCarSpacesField = new javax.swing.JTextField();
        maxSmallCarSpacesField = new javax.swing.JTextField();
        maxMotorCycleSpacesField = new javax.swing.JTextField();
        maxQueueLengthField = new javax.swing.JTextField();
        simulationSeedLbl = new javax.swing.JLabel();
        carArrivalProbLbl = new javax.swing.JLabel();
        smallCarArrivalProbLbl = new javax.swing.JLabel();
        motorCyleArrivalProbLbl = new javax.swing.JLabel();
        meanStayLbl = new javax.swing.JLabel();
        carArrivalProbSlider = new javax.swing.JSlider();
        smallCarArrivalProbSlider = new javax.swing.JSlider();
        motorCycleArrivalProbSlider = new javax.swing.JSlider();
        simulationSeedField = new javax.swing.JTextField();
        meanStayDurationField = new javax.swing.JTextField();
        launchSimBtn = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        simulationResultsTextArea = new javax.swing.JTextArea();
        standardStayLbl = new javax.swing.JLabel();
        standardStayDurationField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        maxCarSpacesLbl.setText("Maximum car spaces:");

        maxSmallCarSpacesLbl.setText("Maximum small car spaces:");

        maxMotorCycleSpacesLbl.setText("Maximum motorcycle spaces:");

        maxQueueLengthLbl.setText("Maximum queue length:");

        maxCarSpacesField.setToolTipText("Max Car Spaces");

        maxSmallCarSpacesField.setToolTipText("Max Small Car Spaces");

        maxMotorCycleSpacesField.setToolTipText("Max Motorcyle Spaces");

        maxQueueLengthField.setToolTipText("Max Queue Length");

        simulationSeedLbl.setText("Simulation seed:");

        carArrivalProbLbl.setText("Car arrival probability");

        smallCarArrivalProbLbl.setText("Small car arrival probability:");

        motorCyleArrivalProbLbl.setText("Motorcycle arrival probability:");

        meanStayLbl.setText("Mean stay duration:");

        carArrivalProbSlider.setPaintTicks(true);

        smallCarArrivalProbSlider.setPaintTicks(true);

        motorCycleArrivalProbSlider.setPaintTicks(true);

        meanStayDurationField.setToolTipText("Mean Stay Duration");

        launchSimBtn.setText("Launch Simulation");
        launchSimBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                launchSimBtnActionPerformed(evt);
            }
        });

        simulationResultsTextArea.setColumns(20);
        simulationResultsTextArea.setRows(5);
        jScrollPane1.setViewportView(simulationResultsTextArea);

        standardStayLbl.setText("Standard stay duration:");

        standardStayDurationField.setToolTipText("Standard Stay Duration");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(carArrivalProbLbl)
                            .addComponent(smallCarArrivalProbLbl)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(standardStayLbl)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(meanStayDurationField, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(standardStayDurationField, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addComponent(motorCycleArrivalProbSlider, javax.swing.GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
                            .addComponent(smallCarArrivalProbSlider, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(carArrivalProbSlider, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jSeparator1)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(maxMotorCycleSpacesLbl)
                                            .addComponent(maxCarSpacesLbl)
                                            .addComponent(maxSmallCarSpacesLbl)
                                            .addComponent(maxQueueLengthLbl))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(maxCarSpacesField, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                                            .addComponent(maxQueueLengthField)
                                            .addComponent(maxMotorCycleSpacesField)
                                            .addComponent(maxSmallCarSpacesField)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(simulationSeedLbl)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(simulationSeedField, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(motorCyleArrivalProbLbl, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(meanStayLbl, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(launchSimBtn))
                            .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(maxCarSpacesLbl)
                    .addComponent(maxCarSpacesField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(maxSmallCarSpacesLbl)
                    .addComponent(maxSmallCarSpacesField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(maxMotorCycleSpacesLbl)
                    .addComponent(maxMotorCycleSpacesField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(maxQueueLengthLbl)
                    .addComponent(maxQueueLengthField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(simulationSeedLbl)
                    .addComponent(simulationSeedField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(carArrivalProbLbl)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(carArrivalProbSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(smallCarArrivalProbLbl)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(smallCarArrivalProbSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(motorCyleArrivalProbLbl)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(motorCycleArrivalProbSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(meanStayLbl)
                    .addComponent(meanStayDurationField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(standardStayLbl)
                    .addComponent(standardStayDurationField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(launchSimBtn)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 450, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 670, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void launchSimBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_launchSimBtnActionPerformed
        launchCarParkSimulation();
    }//GEN-LAST:event_launchSimBtnActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUISimulator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUISimulator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUISimulator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUISimulator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUISimulator().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel carArrivalProbLbl;
    private javax.swing.JSlider carArrivalProbSlider;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JButton launchSimBtn;
    private javax.swing.JTextField maxCarSpacesField;
    private javax.swing.JLabel maxCarSpacesLbl;
    private javax.swing.JTextField maxMotorCycleSpacesField;
    private javax.swing.JLabel maxMotorCycleSpacesLbl;
    private javax.swing.JTextField maxQueueLengthField;
    private javax.swing.JLabel maxQueueLengthLbl;
    private javax.swing.JTextField maxSmallCarSpacesField;
    private javax.swing.JLabel maxSmallCarSpacesLbl;
    private javax.swing.JTextField meanStayDurationField;
    private javax.swing.JLabel meanStayLbl;
    private javax.swing.JSlider motorCycleArrivalProbSlider;
    private javax.swing.JLabel motorCyleArrivalProbLbl;
    private javax.swing.JTextArea simulationResultsTextArea;
    private javax.swing.JTextField simulationSeedField;
    private javax.swing.JLabel simulationSeedLbl;
    private javax.swing.JLabel smallCarArrivalProbLbl;
    private javax.swing.JSlider smallCarArrivalProbSlider;
    private javax.swing.JTextField standardStayDurationField;
    private javax.swing.JLabel standardStayLbl;
    // End of variables declaration//GEN-END:variables
}
