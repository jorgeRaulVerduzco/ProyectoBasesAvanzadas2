/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.presentacionbdavanzadas2;

import DTO.PersonaDTO;
import DTO.TramiteDTO;
import DatosAleatorios.PersonaSeleccionada;
import Dominio.Persona;
import INegocio.IObtenerPersonaPorRFC;
import INegocio.IPersonaAñoNacimientoBO;
import INegocio.IPersonaCurpBO;
import INegocio.IPersonaNombreBO;
import Negocio.ObtenerPersonaPorRFC;
import Negocio.PersonaAñoNacimientoBO;
import Negocio.PersonaCurpBO;
import Negocio.PersonaNombreBO;
import com.mycompany.presentacionbdavanzadas2.frmInicio;
import java.awt.Color;
import java.util.Calendar;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Ruzzky
 */
public class frmHistorialTramites extends javax.swing.JFrame {

    private IObtenerPersonaPorRFC personaRFC;
    private IPersonaCurpBO  personaCURP;
    private IPersonaNombreBO personaNombre;
    private IPersonaAñoNacimientoBO personaAnioNacimiento;
    private TramiteDTO tramite;

    /**
     * Creates new form frmHistorialTramites
     */
    public frmHistorialTramites() {
        initComponents();
        personaRFC = new ObtenerPersonaPorRFC();
        personaCURP = new PersonaCurpBO();
        personaNombre = new PersonaNombreBO();
        personaAnioNacimiento = new PersonaAñoNacimientoBO();
        tramite = new TramiteDTO();
        tabla();
        llenarTabla();
    }

    public void tabla() {
        tblConsultas.setDefaultRenderer(Object.class, new RenderTabla());

        DefaultTableModel modeloTabla = new DefaultTableModel();
        tblConsultas.setModel(modeloTabla);

        tblConsultas.setRowHeight(40);

        // Definición de las columnas y sus encabezados
        String[] encabezados = {"Nombre", "Apellido Paterno", "Apellido Materno", "CURP", "RFC", "Fecha de Nacimiento", "Teléfono", "Discapacidad", "ID"};
        modeloTabla.setColumnIdentifiers(encabezados);

        // Configuración del ancho preferido de las columnas
        int[] anchos = {100, 100, 100, 100, 100, 100, 100, 100, 50}; // Ajusta el ancho de la última columna
        for (int i = 0; i < anchos.length; i++) {
            tblConsultas.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
        }
    }

    public void llenarTabla() {
        String datosPersona = txtDatos.getText().trim();
    Persona persona = null;
    DefaultTableModel modeloTabla = (DefaultTableModel) tblConsultas.getModel();
    modeloTabla.setRowCount(0);

    if (jComboBoxConsulta.getSelectedItem().equals("RFC")) {
        persona = personaRFC.obtenerPersonaPorRFC(datosPersona);
    } else if (jComboBoxConsulta.getSelectedItem().equals("NOMBRE")) {
        List<Persona> personas = personaNombre.buscarPersonasPorNombre(datosPersona);
        agregarPersonasATabla(personas, modeloTabla);
        return;
    } else if (jComboBoxConsulta.getSelectedItem().equals("CURP")) {
        List<Persona> personas = personaCURP.buscarPersonasPorCURP(datosPersona);
        agregarPersonasATabla(personas, modeloTabla);
        return;
    } else if (jComboBoxConsulta.getSelectedItem().equals("AÑO NACIMIENTO")) {
        int año = Integer.parseInt(datosPersona);
        List<Persona> personas = personaAnioNacimiento.buscarPersonasPorAñoNacimiento(año);
        agregarPersonasATabla(personas, modeloTabla);
        return;
    }

    if (persona != null) {
        Object[] datos = new Object[modeloTabla.getColumnCount()];
        datos[0] = persona.getNombres();
        datos[1] = persona.getApellidoPaterno();
        datos[2] = persona.getApellidoMaterno();
        datos[3] = persona.getCurp();
        datos[4] = persona.getRfc();
        datos[5] = (persona.getFechaNacimiento() != null) ? persona.getFechaNacimiento().get(Calendar.DAY_OF_MONTH) + "/"
                + (persona.getFechaNacimiento().get(Calendar.MONTH) + 1) + "/"
                + persona.getFechaNacimiento().get(Calendar.YEAR) : "";
        datos[6] = persona.getTelefono();
        datos[7] = persona.getDiscapacidad();
        datos[8] = (persona.getIdPersona() != null) ? persona.getIdPersona().toString() : ""; // Convertir el ID a String
        modeloTabla.addRow(datos);
    }
    }

    /**
 * Agrega las personas de la lista proporcionada a un modelo de tabla.
 * Cada persona se agrega como una fila en la tabla.
 *
 * @param personas    La lista de personas a agregar a la tabla.
 * @param modeloTabla El modelo de tabla al que se agregarán las personas.
 */
    
    private void agregarPersonasATabla(List<Persona> personas, DefaultTableModel modeloTabla) {
        // Recorre cada persona en la lista de personas proporcionada
    for (Persona persona : personas) {
        // Crea un arreglo de objetos para almacenar los datos de la persona
        Object[] datos = new Object[modeloTabla.getColumnCount()];
        // Asigna los datos de la persona al arreglo de datos
        datos[0] = persona.getNombres();
        datos[1] = persona.getApellidoPaterno();
        datos[2] = persona.getApellidoMaterno();
        datos[3] = persona.getCurp();
        datos[4] = persona.getRfc();
        // Verifica si la fecha de nacimiento de la persona no es nula
        datos[5] = (persona.getFechaNacimiento() != null) ? persona.getFechaNacimiento().get(Calendar.DAY_OF_MONTH) + "/"
                // Formatea la fecha de nacimiento como "dd/MM/yyyy" y la asigna a la columna 5
                + (persona.getFechaNacimiento().get(Calendar.MONTH) + 1) + "/"
                + persona.getFechaNacimiento().get(Calendar.YEAR) : "";
        datos[6] = persona.getTelefono();
        datos[7] = persona.getDiscapacidad();
        datos[8] = (persona.getIdPersona() != null) ? persona.getIdPersona().toString() : ""; // Convertir el ID a String
         // Agrega los datos de la persona como una nueva fila al modelo de la tabla
        modeloTabla.addRow(datos);
    }
}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblConsultas = new javax.swing.JTable();
        btnBuscar = new javax.swing.JButton();
        txtDatos = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        btnRegresar = new javax.swing.JButton();
        jComboBoxConsulta = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel3.setBackground(new java.awt.Color(0, 51, 51));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel7.setFont(new java.awt.Font("SimSun", 0, 60)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Consultas");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(184, 184, 184))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.setBackground(new java.awt.Color(242, 239, 230));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jScrollPane3.setBackground(new java.awt.Color(227, 227, 227));
        jScrollPane3.setFocusable(false);

        tblConsultas = new javax.swing.JTable(){
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        tblConsultas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblConsultas.getTableHeader().setReorderingAllowed(false);
        tblConsultas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblConsultasMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblConsultas);

        btnBuscar.setBackground(new java.awt.Color(204, 204, 204));
        btnBuscar.setText("🔎    Buscar");
        btnBuscar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnBuscar.setFocusPainted(false);
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        txtDatos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDatosActionPerformed(evt);
            }
        });
        txtDatos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDatosKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDatosKeyTyped(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("CONSULTA:");

        btnRegresar.setBackground(new java.awt.Color(255, 102, 102));
        btnRegresar.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        btnRegresar.setText("Regresar");
        btnRegresar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnRegresar.setFocusPainted(false);
        btnRegresar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnRegresarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnRegresarMouseExited(evt);
            }
        });
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });

        jComboBoxConsulta.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "RFC", "NOMBRE", "CURP", "AÑO NACIMIENTO" }));
        jComboBoxConsulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxConsultaActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("DATOS:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 682, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBoxConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(txtDatos, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(btnRegresar, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(82, 82, 82)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(534, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBoxConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtDatos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnRegresar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(9, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(50, 50, 50)
                    .addComponent(jLabel4)
                    .addContainerGap(441, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        frmInicio frminicio = new frmInicio();
        frminicio.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnRegresarActionPerformed

    private void btnRegresarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRegresarMouseExited
        // TODO add your handling code here:
        btnRegresar.setBackground(new Color(255, 102, 102));
    }//GEN-LAST:event_btnRegresarMouseExited

    private void btnRegresarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRegresarMouseEntered
        // TODO add your handling code here:
        btnRegresar.setBackground(new Color(255, 51, 51));
    }//GEN-LAST:event_btnRegresarMouseEntered

    private void txtDatosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDatosKeyTyped

    }//GEN-LAST:event_txtDatosKeyTyped

    private void txtDatosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDatosKeyReleased

    }//GEN-LAST:event_txtDatosKeyReleased

    private void txtDatosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDatosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDatosActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        llenarTabla();
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void tblConsultasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblConsultasMouseClicked
        int filaSeleccionada = tblConsultas.getSelectedRow();
        if (filaSeleccionada != -1) {
            DefaultTableModel model = (DefaultTableModel) tblConsultas.getModel();
            PersonaDTO personaSeleccionada = new PersonaDTO();

            personaSeleccionada.setNombres((String) model.getValueAt(filaSeleccionada, 0));
            personaSeleccionada.setApellidoPaterno((String) model.getValueAt(filaSeleccionada, 1));
            personaSeleccionada.setApellidoMaterno((String) model.getValueAt(filaSeleccionada, 2));
            personaSeleccionada.setCurp((String) model.getValueAt(filaSeleccionada, 3));
            personaSeleccionada.setRfc((String) model.getValueAt(filaSeleccionada, 4));
            personaSeleccionada.setIdPersona(Long.valueOf((String) model.getValueAt(filaSeleccionada, 8))); // Asignar el ID de la persona

            PersonaSeleccionada.setPersonaSeleccionada(personaSeleccionada);

            String[] opciones = {"Placas", "Licencia"};
            int opcionSeleccionada = JOptionPane.showOptionDialog(
                this,
                "Seleccione el historial que desea ver:",
                "Seleccione",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opciones,
                opciones[0]);

            if (opcionSeleccionada == 0) {
                frmHistorialPlacas historialPlacasForm = new frmHistorialPlacas();
                historialPlacasForm.setVisible(true);
                this.dispose();
            } else if (opcionSeleccionada == 1) {
                frmHistorialLicencia historialLicenciaForm = new frmHistorialLicencia();
                historialLicenciaForm.setVisible(true);
                this.dispose();
            }
        }
    }//GEN-LAST:event_tblConsultasMouseClicked

    private void jComboBoxConsultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxConsultaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxConsultaActionPerformed

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
            java.util.logging.Logger.getLogger(frmHistorialTramites.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmHistorialTramites.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmHistorialTramites.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmHistorialTramites.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmHistorialTramites().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JComboBox<String> jComboBoxConsulta;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tblConsultas;
    private javax.swing.JTextField txtDatos;
    // End of variables declaration//GEN-END:variables
}
