/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.presentacionbdavanzadas2;

import INegocio.IBuscarTramitesBO;
import Negocio.BuscarTramitesBO;
import java.awt.Color;
import java.awt.Font;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Ruzzky
 */
public class frmReporte extends javax.swing.JFrame {

    IBuscarTramitesBO buscarTramiteBO;

    /**
     * Creates new form frmReporte
     */
    public frmReporte() {
        buscarTramiteBO = new BuscarTramitesBO();
        initComponents();
        tabla();
    }

    public void tabla() {
        tblReporte.setDefaultRenderer(Object.class, new RenderTabla());

        DefaultTableModel modeloTabla = new DefaultTableModel();
        tblReporte.setModel(modeloTabla);

        tblReporte.setRowHeight(40);

        // Definición de las columnas y sus encabezados
        String[] encabezados = {"Fecha", "Tipo", "Nombres", "Apellido Paterno", "Apellido Materno", "Costo"};
        modeloTabla.setColumnIdentifiers(encabezados);

        // Configuración del ancho preferido de las columnas
        int[] anchos = {100, 100, 100, 100, 100, 100};
        for (int i = 0; i < anchos.length; i++) {
            tblReporte.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
        }
    }

    public void llenarTabla() {
        String nombres = txtNombres.getText();
    String apellidoP = txtApellidoPaterno.getText();
    String apellidoM = txtApellidoMaterno.getText();
    String tipo = jComboBoxTramite.getSelectedItem().toString();

    LocalDate fechaInicio = datePickerFechaInicio.getDate();
    LocalDate fechaFin = datePickerFechaFin.getDate();

    // Verificar si ninguna de las fechas está seleccionada
    if (fechaInicio == null && fechaFin == null) {
        // Si ninguna fecha está seleccionada, entonces obtener todos los registros
        List<Object[]> resultados = buscarTramiteBO.buscarTramites(tipo, null, null, nombres, apellidoP, apellidoM);

        DefaultTableModel modeloTabla = (DefaultTableModel) tblReporte.getModel();
        modeloTabla.setRowCount(0); // Limpiar la tabla antes de agregar nuevos datos

        // Iterar sobre los resultados y agregarlos a la tabla
        for (Object[] resultado : resultados) {
            // Formatear la fecha
            String fechaFormateada = formatearFecha(resultado[0]);

            // Crear una nueva fila con la fecha formateada y los otros datos
            Object[] fila = {
                fechaFormateada, // Fecha formateada
                resultado[1], // Tipo
                resultado[2], // Nombres
                resultado[3], // Apellido Paterno
                resultado[4], // Apellido Materno
                resultado[5] // Costo
            };
            modeloTabla.addRow(fila);
        }
    } else {
        // Si al menos una fecha está seleccionada, realizar la búsqueda con las fechas especificadas
        Calendar fechaCalendarInicio = null;
        if (fechaInicio != null) {
            fechaCalendarInicio = GregorianCalendar.from(fechaInicio.atStartOfDay(ZoneId.systemDefault()));
        }

        Calendar fechaCalendarFin = null;
        if (fechaFin != null) {
            fechaCalendarFin = GregorianCalendar.from(fechaFin.atStartOfDay(ZoneId.systemDefault()));
        }

        List<Object[]> resultados = buscarTramiteBO.buscarTramites(tipo, fechaCalendarInicio, fechaCalendarFin, nombres, apellidoP, apellidoM);

        DefaultTableModel modeloTabla = (DefaultTableModel) tblReporte.getModel();
        modeloTabla.setRowCount(0); // Limpiar la tabla antes de agregar nuevos datos

        // Iterar sobre los resultados y agregarlos a la tabla
        for (Object[] resultado : resultados) {
            // Formatear la fecha
            String fechaFormateada = formatearFecha(resultado[0]);

            // Crear una nueva fila con la fecha formateada y los otros datos
            Object[] fila = {
                fechaFormateada, // Fecha formateada
                resultado[1], // Tipo
                resultado[2], // Nombres
                resultado[3], // Apellido Paterno
                resultado[4], // Apellido Materno
                resultado[5] // Costo
            };
            modeloTabla.addRow(fila);
        }
    }
    }
private String formatearFecha(Object fecha) {
    String fechaFormateada = "";
    if (fecha instanceof Calendar) {
        Calendar cal = (Calendar) fecha;
        Date date = cal.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        fechaFormateada = dateFormat.format(date);
    }
    return fechaFormateada;
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
        jPanel5 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblReporte = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        txtNombres = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtApellidoPaterno = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtApellidoMaterno = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jComboBoxTramite = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        datePickerFechaInicio = new com.github.lgooddatepicker.components.DatePicker();
        jLabel8 = new javax.swing.JLabel();
        datePickerFechaFin = new com.github.lgooddatepicker.components.DatePicker();
        btnBuscar = new javax.swing.JButton();
        btnGenerarPDF = new javax.swing.JButton();
        btnCancelar1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(242, 239, 230));

        jPanel5.setBackground(new java.awt.Color(0, 51, 51));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel11.setText("Reporte");
        jLabel11.setFont(new java.awt.Font("SimSun", 0, 60)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 562, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jScrollPane1.setFocusable(false);

        tblReporte = new javax.swing.JTable(){
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        tblReporte.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblReporte.setRowHeight(25);
        tblReporte.setSelectionBackground(new java.awt.Color(232, 57, 95));
        tblReporte.getTableHeader().setReorderingAllowed(false);
        tblReporte.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblReporteMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblReporte);
        tblReporte.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tblReporte.getTableHeader().setOpaque(false);
        tblReporte.getTableHeader().setBackground(new Color(102,89,222));
        tblReporte.getTableHeader().setForeground(new Color(255,255,255));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Nombres:");

        txtNombres.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombresActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Apellido Materno:");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("Apellido Paterno:");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("Tipo Tramite:");

        jComboBoxTramite.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Licencia", "Placas" }));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("Fecha Inicio:");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setText("Fecha Fin:");

        btnBuscar.setBackground(new java.awt.Color(204, 204, 204));
        btnBuscar.setText("🔎    Buscar");
        btnBuscar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnBuscar.setFocusPainted(false);
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        btnGenerarPDF.setText("Generar PDF");
        btnGenerarPDF.setBackground(new java.awt.Color(255, 102, 102));
        btnGenerarPDF.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnGenerarPDF.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        btnGenerarPDF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarPDFActionPerformed(evt);
            }
        });

        btnCancelar1.setText("Cancelar");
        btnCancelar1.setBackground(new java.awt.Color(255, 102, 102));
        btnCancelar1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnCancelar1.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        btnCancelar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelar1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(btnCancelar1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(218, 218, 218)
                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnGenerarPDF, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(63, 63, 63))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBoxTramite, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNombres, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtApellidoPaterno, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(datePickerFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtApellidoMaterno, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(datePickerFechaFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 875, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(94, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtNombres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(txtApellidoPaterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(txtApellidoMaterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jComboBoxTramite, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7)
                        .addComponent(datePickerFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel8)
                        .addComponent(datePickerFechaFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(btnGenerarPDF, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelar1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 627, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblReporteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblReporteMouseClicked

    }//GEN-LAST:event_tblReporteMouseClicked

    private void txtNombresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombresActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombresActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        llenarTabla();
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnGenerarPDFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarPDFActionPerformed
        try {

            // Ruta de salida del archivo PDF
            String outputPdfPath = "reporte.pdf";
            PdfWriter writer = new PdfWriter(outputPdfPath);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf, PageSize.A4);

            PdfFont fontTitle = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);
            Paragraph title = new Paragraph("Reporte de Trámites").setFont(fontTitle).setFontSize(20).setFontColor(new DeviceRgb(0, 0, 255)) // Color azul
                    .setHorizontalAlignment(HorizontalAlignment.CENTER);
            document.add(title);

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            String currentDate = dateFormat.format(new Date());
            Paragraph dateParagraph = new Paragraph("Fecha de generación: " + currentDate);
            document.add(dateParagraph);

            PdfFont pageNumberFont = PdfFontFactory.createFont(StandardFonts.HELVETICA);
            pdf.addEventHandler(PdfDocumentEvent.END_PAGE, new PageNumberEventHandler(pageNumberFont));
            Table table = new Table(6);

            String[] encabezados = {"Fecha", "Tipo", "Nombres", "Apellido Paterno", "Apellido Materno", "Costo"};
            for (String encabezado : encabezados) {
                table.addCell(new Cell().add(new Paragraph(encabezado)).setFontColor(new DeviceRgb(255, 255, 255)) // Color blanco para el texto
                        .setBackgroundColor(new DeviceRgb(0, 51, 51)));
            }
            DefaultTableModel modeloTabla = (DefaultTableModel) tblReporte.getModel();
            for (int i = 0; i < modeloTabla.getRowCount(); i++) {
                for (int j = 0; j < modeloTabla.getColumnCount(); j++) {
                    Object value = modeloTabla.getValueAt(i, j);
                    String cellValue;
                    if (value instanceof GregorianCalendar) {
                        GregorianCalendar calendar = (GregorianCalendar) value;
                        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
                        cellValue = dateFormatter.format(calendar.getTime());
                    } else {
                        cellValue = value == null ? "" : value.toString();
                    }
                    table.addCell(new Cell().add(new Paragraph(cellValue)).setBackgroundColor(new DeviceRgb(242, 239, 230))); // Color de fondo RGB(242, 239, 230)
                }
            }
            document.add(table);
            document.close();
            JOptionPane.showMessageDialog(null, "Informe generado correctamente en: " + outputPdfPath);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_btnGenerarPDFActionPerformed

    private void btnCancelar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelar1ActionPerformed
        frmInicio tramitesss = new frmInicio();
        tramitesss.setVisible(true);
        this.dispose();

    }//GEN-LAST:event_btnCancelar1ActionPerformed

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
            java.util.logging.Logger.getLogger(frmReporte.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmReporte.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmReporte.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmReporte.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmReporte().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCancelar1;
    private javax.swing.JButton btnGenerarPDF;
    private com.github.lgooddatepicker.components.DatePicker datePickerFechaFin;
    private com.github.lgooddatepicker.components.DatePicker datePickerFechaInicio;
    private javax.swing.JComboBox<String> jComboBoxTramite;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblReporte;
    private javax.swing.JTextField txtApellidoMaterno;
    private javax.swing.JTextField txtApellidoPaterno;
    private javax.swing.JTextField txtNombres;
    // End of variables declaration//GEN-END:variables
}
