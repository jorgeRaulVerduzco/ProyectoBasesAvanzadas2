/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.presentacionbdavanzadas2;

import DTO.PersonaDTO;
import DatosAleatorios.PersonaSeleccionada;
import Dominio.Persona;
import INegocio.IActualizarDiscapacidadPorRFCBO;
import INegocio.IAgregarLicenciaBO;
import INegocio.IObtenerPersonaDiscapacitadaBO;
import Negocio.ActualizarDiscapacidadPorRFCBO;
import Negocio.AgregarLicencioBO;
import Negocio.obtenerPersonaDiscapacitadaBO;
import java.awt.Color;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author Ruzzky
 */
public class frmCrudPersona extends javax.swing.JFrame {

    IActualizarDiscapacidadPorRFCBO actualizarDiscapacidadPorRfc;
    IObtenerPersonaDiscapacitadaBO personaDiscapacitada;

    /**
     * Creates new form frmRegistroPersona
     */
    public frmCrudPersona() {
        personaDiscapacitada = new obtenerPersonaDiscapacitadaBO();
        actualizarDiscapacidadPorRfc = new ActualizarDiscapacidadPorRFCBO();
        initComponents();
        tabla();
        llenarTabla();
    }

    /**
     *
     * Esta es para volver discapacitada una persona
     */
    public void tabla() {
        tblRegistrosPersonas.setDefaultRenderer(Object.class, new RenderTabla());

        DefaultTableModel modeloTabla = new DefaultTableModel();
        tblRegistrosPersonas.setModel(modeloTabla);

        tblRegistrosPersonas.setRowHeight(40);

        // Definición de las columnas y sus encabezados
        String[] encabezados = {"Nombre", "Apellido Paterno", "Apellido Materno", "CURP", "RFC", "Fecha de Nacimiento", "Teléfono", "Discapacidad"};
        modeloTabla.setColumnIdentifiers(encabezados);

        // Configuración del ancho preferido de las columnas
        int[] anchos = {100, 100, 100, 100, 100, 100, 100, 100};
        for (int i = 0; i < anchos.length; i++) {
            tblRegistrosPersonas.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
        }
    }

    public void llenarTabla() {
        String rfc = txtBuscar.getText();
        Persona persona = personaDiscapacitada.obtenerPersonaDiscpacitadaPorRFC(rfc);

        DefaultTableModel defa = (DefaultTableModel) tblRegistrosPersonas.getModel();
        defa.setRowCount(0);

        if (persona != null) {
            Object[] datos = new Object[defa.getColumnCount()];

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

            defa.addRow(datos);
        } else {
            System.out.println("No se encontró una persona con ese RFC");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblRegistrosPersonas = new javax.swing.JTable();
        txtBuscar = new javax.swing.JTextField();
        btnatras = new javax.swing.JButton();
        BtnRegistar20Personas = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel3.setBackground(new java.awt.Color(0, 51, 51));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setFont(new java.awt.Font("SimSun", 0, 60)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText(" Personas");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(156, 156, 156)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(242, 239, 230));
        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(5, 0, 0));
        jLabel2.setText("Busqueda:");

        jScrollPane1.setFocusable(false);

        tblRegistrosPersonas = new javax.swing.JTable(){
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        tblRegistrosPersonas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblRegistrosPersonas.setRowHeight(25);
        tblRegistrosPersonas.setSelectionBackground(new java.awt.Color(232, 57, 95));
        tblRegistrosPersonas.getTableHeader().setReorderingAllowed(false);
        tblRegistrosPersonas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblRegistrosPersonasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblRegistrosPersonas);
        tblRegistrosPersonas.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tblRegistrosPersonas.getTableHeader().setOpaque(false);
        tblRegistrosPersonas.getTableHeader().setBackground(new Color(102,89,222));
        tblRegistrosPersonas.getTableHeader().setForeground(new Color(255,255,255));

        txtBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuscarActionPerformed(evt);
            }
        });
        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBuscarKeyTyped(evt);
            }
        });

        btnatras.setBackground(new java.awt.Color(255, 102, 102));
        btnatras.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        btnatras.setForeground(new java.awt.Color(2, 2, 2));
        btnatras.setText("Regresar");
        btnatras.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnatras.setFocusPainted(false);
        btnatras.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnatrasMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnatrasMouseExited(evt);
            }
        });
        btnatras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnatrasActionPerformed(evt);
            }
        });

        BtnRegistar20Personas.setBackground(new java.awt.Color(0, 0, 0));
        BtnRegistar20Personas.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        BtnRegistar20Personas.setForeground(new java.awt.Color(255, 255, 255));
        BtnRegistar20Personas.setText("Registrar 20");
        BtnRegistar20Personas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnRegistar20PersonasActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 204, 204));
        jLabel3.setText("CAMBIAR DISCAPCIDAD PERSONA");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(27, 27, 27)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 609, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(btnatras, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(95, 95, 95)
                        .addComponent(BtnRegistar20Personas, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(204, 204, 204)))
                .addContainerGap(28, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(139, 139, 139))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnatras, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnRegistar20Personas, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(58, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnatrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnatrasActionPerformed
        frmInicio inicio = new frmInicio();
        inicio.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnatrasActionPerformed

    private void tblRegistrosPersonasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblRegistrosPersonasMouseClicked
        int selectedRow = tblRegistrosPersonas.getSelectedRow();
        if (selectedRow != -1) {
            DefaultTableModel model = (DefaultTableModel) tblRegistrosPersonas.getModel();
            PersonaDTO personaSeleccionada = new PersonaDTO();

            personaSeleccionada.setNombres((String) model.getValueAt(selectedRow, 0));
            personaSeleccionada.setApellidoPaterno((String) model.getValueAt(selectedRow, 1));
            personaSeleccionada.setApellidoMaterno((String) model.getValueAt(selectedRow, 2));
            personaSeleccionada.setCurp((String) model.getValueAt(selectedRow, 3));
            personaSeleccionada.setRfc((String) model.getValueAt(selectedRow, 4));
            personaSeleccionada.setDiscapacidad((String) model.getValueAt(selectedRow, 7));
            PersonaSeleccionada.setPersonaSeleccionada(personaSeleccionada);
            String rfc = (String) model.getValueAt(selectedRow, 4);
            String[] opciones = {"SI", "NO"};

            int opcionSeleccionada = JOptionPane.showOptionDialog(this,
                    "¿se hizo discapacitado?",
                    "Seleccione una opción",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    opciones,
                    opciones[0]);

            if (opcionSeleccionada == 0) {
                actualizarDiscapacidadPorRfc.actualizarDiscapacidadPorRFC(rfc);
                frmInicio frmcOMIENZO = new frmInicio();
                frmcOMIENZO.setVisible(true);
                this.dispose();
            } else if (opcionSeleccionada == 1) {
                frmInicio frmTramite = new frmInicio();
                frmTramite.setVisible(true);
                this.dispose();
            }
        }


    }//GEN-LAST:event_tblRegistrosPersonasMouseClicked

    private void txtBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarActionPerformed

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
        llenarTabla();

    }//GEN-LAST:event_txtBuscarKeyReleased

    private void txtBuscarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyTyped

    }//GEN-LAST:event_txtBuscarKeyTyped

    private void btnatrasMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnatrasMouseEntered
        // TODO add your handling code here:

        btnatras.setBackground(new Color(255, 51, 51));
    }//GEN-LAST:event_btnatrasMouseEntered

    private void btnatrasMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnatrasMouseExited
        // TODO add your handling code here:
        btnatras.setBackground(new Color(255, 102, 102));
    }//GEN-LAST:event_btnatrasMouseExited

    private void BtnRegistar20PersonasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnRegistar20PersonasActionPerformed
        IAgregarLicenciaBO agregarLicenciaBO = new AgregarLicencioBO();
        agregarLicenciaBO.incersionMasiva();
        JOptionPane.showMessageDialog(null, "Se han registrado 20 personas de forma masiva.", "Información", JOptionPane.INFORMATION_MESSAGE);

    }//GEN-LAST:event_BtnRegistar20PersonasActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnRegistar20Personas;
    private javax.swing.JButton btnatras;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblRegistrosPersonas;
    private javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables
}
