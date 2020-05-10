/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import java.awt.Color;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;

/**
 *
 * @author Jesus Gonzalez
 */
public class OfertasEmpleoFiltradoFrame extends javax.swing.JFrame {

    public Connection connection;
    public List<String> ofertasEmpleo = new ArrayList();
    public List<Integer> id_ofertasEmpleo = new ArrayList();
    
    Menu_Principal menuframe;
    
    private int option; 
    
        
    public OfertasEmpleoFiltradoFrame(Connection connection, Menu_Principal menuframe, int option) {
               
        initComponents();  
        
               
        setLocationRelativeTo(null);
        getRootPane().setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));
        
        if(option == 3)
            verseleccionados_button.setVisible(false);
        
        this.option = option;
        this.connection = connection;
        this.menuframe = menuframe;
        
        getOfertasTrabajo();
        
        fijarOfertasEmpleoMenu();
      
    }

 // Funcion para obtener de la base de datos todas las ofertas de trabajo existente con su ID y descripcion.
    public void getOfertasTrabajo(){
    
        try {
            ResultSet rs = connection.prepareStatement("SELECT * FROM oferta_empleo").executeQuery();
                
            while(rs.next()){
                id_ofertasEmpleo.add(Integer.parseInt(rs.getString(1)));
                ofertasEmpleo.add(rs.getString(10));
            }
                
        } catch (SQLException ex) {
            Logger.getLogger(Formulario_Postulante_Conocimientos.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
    
   // Metodo para fijar las ofertas de empleo en la lista de la interfaz.
   public void fijarOfertasEmpleoMenu(){
       DefaultListModel model = new DefaultListModel();
       
       for(int counter = 0; counter < ofertasEmpleo.size(); counter++)
           model.addElement(counter+1 + ". " + ofertasEmpleo.get(counter));
       
       ofertas_list.setModel(model);
       
       if(!ofertasEmpleo.isEmpty())
           ofertas_list.setSelectedIndex(0);
   }
   
   // Metodo para encontrar la existencia del postulante.
   public boolean verificarExistenciaPostulante(){
   
         try {
            ResultSet rs = connection.prepareStatement("SELECT * FROM postulantes").executeQuery();
                
            while(rs.next())
                return true;
                
        } catch (SQLException ex) {
            Logger.getLogger(Formulario_Postulante_Conocimientos.class.getName()).log(Level.SEVERE, null, ex);
        }
       
      return false;
   }

     // Metodo para verificar si ya todos los postulantes para esa oferta de empleo seleccionada ya han sido evaluados.
   public boolean postulantesEvaluadosOfertaEmpleo(int idoferta_empleo){
   
    List<Integer> idpostulantes = new ArrayList();
    int counter = 0;
    
        try {
            ResultSet rs = connection.prepareStatement("SELECT * FROM filtrado_puntajes WHERE idoferta_empleo = " + idoferta_empleo).executeQuery();
                
            while(rs.next())
                idpostulantes.add(rs.getInt(7));
                
        } catch (SQLException ex) {
            Logger.getLogger(Formulario_Postulante_Conocimientos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // Ya que tenemos todos los idpostulantes filtrados, ahora vamos a verificar si efectivamente todos han realizado su prueba de evaluacion.
        try {
            ResultSet rs = connection.prepareStatement("SELECT * FROM evaluaciongeneral WHERE idoferta_empleo = " + idoferta_empleo).executeQuery();
                
            while(rs.next())
                if(idpostulantes.contains(rs.getInt(2)))
                   counter++;
                
        } catch (SQLException ex) {
            Logger.getLogger(Formulario_Postulante_Conocimientos.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        if(counter == idpostulantes.size())
            return true;
            
      return false;
   }

   
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPIngreso = new javax.swing.JPanel();
        jPPage1 = new javax.swing.JPanel();
        jInfPersonal = new javax.swing.JLabel();
        jPanelOfertasEmpleo = new javax.swing.JPanel();
        jScrollPaneLista = new javax.swing.JScrollPane();
        ofertas_list = new javax.swing.JList<>();
        filtrar_button = new javax.swing.JButton();
        atras_button = new javax.swing.JButton();
        verseleccionados_button = new javax.swing.JButton();
        jBackGround = new javax.swing.JLabel();
        jPanelSuperior = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jPanelLateral = new javax.swing.JPanel();
        jButtonClose = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1000, 700));
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPIngreso.setBackground(new java.awt.Color(3, 153, 129));
        jPIngreso.setPreferredSize(new java.awt.Dimension(950, 670));
        jPIngreso.setLayout(null);

        jPPage1.setBackground(new java.awt.Color(255, 255, 255));
        jPPage1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPPage1.setOpaque(false);
        jPPage1.setPreferredSize(new java.awt.Dimension(950, 670));
        jPPage1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jInfPersonal.setFont(new java.awt.Font("Dosis", 1, 36)); // NOI18N
        jInfPersonal.setText("Filtrado ofertas de empleo");
        jPPage1.add(jInfPersonal, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 90, -1, 60));

        jPanelOfertasEmpleo.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "seleccione la oferta a filtrar", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Berlin Sans FB", 0, 24), new java.awt.Color(0, 102, 102))); // NOI18N
        jPanelOfertasEmpleo.setOpaque(false);
        jPanelOfertasEmpleo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ofertas_list.setFont(new java.awt.Font("Berlin Sans FB", 0, 18)); // NOI18N
        ofertas_list.setForeground(new java.awt.Color(0, 153, 240));
        jScrollPaneLista.setViewportView(ofertas_list);

        jPanelOfertasEmpleo.add(jScrollPaneLista, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 610, 250));

        filtrar_button.setFont(new java.awt.Font("Berlin Sans FB", 0, 18)); // NOI18N
        filtrar_button.setForeground(new java.awt.Color(0, 102, 102));
        filtrar_button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons8_filter_80px.png"))); // NOI18N
        filtrar_button.setText("filtrar");
        filtrar_button.setBorderPainted(false);
        filtrar_button.setContentAreaFilled(false);
        filtrar_button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        filtrar_button.setFocusPainted(false);
        filtrar_button.setFocusable(false);
        filtrar_button.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        filtrar_button.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons8_filter_80px_2.png"))); // NOI18N
        filtrar_button.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons8_filter_80px_2.png"))); // NOI18N
        filtrar_button.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons8_filter_80px_2.png"))); // NOI18N
        filtrar_button.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        filtrar_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filtrar_buttonActionPerformed(evt);
            }
        });
        jPanelOfertasEmpleo.add(filtrar_button, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 20, 90, 110));

        atras_button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons8_cancel_2_80px_1.png"))); // NOI18N
        atras_button.setBorderPainted(false);
        atras_button.setContentAreaFilled(false);
        atras_button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        atras_button.setFocusPainted(false);
        atras_button.setFocusable(false);
        atras_button.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons8_cancel_2_80px.png"))); // NOI18N
        atras_button.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons8_cancel_2_80px.png"))); // NOI18N
        atras_button.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons8_cancel_2_80px.png"))); // NOI18N
        atras_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                atras_buttonActionPerformed(evt);
            }
        });
        jPanelOfertasEmpleo.add(atras_button, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 240, 70, 60));

        verseleccionados_button.setFont(new java.awt.Font("Berlin Sans FB", 0, 14)); // NOI18N
        verseleccionados_button.setForeground(new java.awt.Color(0, 102, 102));
        verseleccionados_button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons8_bookmark_80px.png"))); // NOI18N
        verseleccionados_button.setText("seleccionados");
        verseleccionados_button.setAlignmentY(0.0F);
        verseleccionados_button.setBorderPainted(false);
        verseleccionados_button.setContentAreaFilled(false);
        verseleccionados_button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        verseleccionados_button.setFocusPainted(false);
        verseleccionados_button.setFocusable(false);
        verseleccionados_button.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        verseleccionados_button.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons8_bookmark_80px_1.png"))); // NOI18N
        verseleccionados_button.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons8_bookmark_80px_1.png"))); // NOI18N
        verseleccionados_button.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons8_bookmark_80px_1.png"))); // NOI18N
        verseleccionados_button.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        verseleccionados_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                verseleccionados_buttonActionPerformed(evt);
            }
        });
        jPanelOfertasEmpleo.add(verseleccionados_button, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 130, 110, 100));

        jPPage1.add(jPanelOfertasEmpleo, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 180, 770, 320));

        jPIngreso.add(jPPage1);
        jPPage1.setBounds(0, 0, 950, 670);

        jBackGround.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/bg3.gif"))); // NOI18N
        jBackGround.setMaximumSize(new java.awt.Dimension(950, 650));
        jBackGround.setMinimumSize(new java.awt.Dimension(950, 650));
        jBackGround.setPreferredSize(new java.awt.Dimension(950, 700));
        jPIngreso.add(jBackGround);
        jBackGround.setBounds(0, 0, 950, 670);

        getContentPane().add(jPIngreso, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 30, 950, 670));

        jPanelSuperior.setBackground(new java.awt.Color(255, 255, 255));
        jPanelSuperior.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanelSuperior.setPreferredSize(new java.awt.Dimension(950, 30));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons8_minimize_window_30px.png"))); // NOI18N
        jButton1.setBorder(null);
        jButton1.setContentAreaFilled(false);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.setFocusPainted(false);
        jButton1.setFocusable(false);
        jButton1.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons8_minimize_window_30px_2.png"))); // NOI18N
        jButton1.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons8_minimize_window_30px_2.png"))); // NOI18N
        jButton1.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons8_minimize_window_30px_2.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelSuperiorLayout = new javax.swing.GroupLayout(jPanelSuperior);
        jPanelSuperior.setLayout(jPanelSuperiorLayout);
        jPanelSuperiorLayout.setHorizontalGroup(
            jPanelSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelSuperiorLayout.createSequentialGroup()
                .addContainerGap(894, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(24, 24, 24))
        );
        jPanelSuperiorLayout.setVerticalGroup(
            jPanelSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSuperiorLayout.createSequentialGroup()
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanelSuperior, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 0, 950, 30));

        jPanelLateral.setBackground(new java.awt.Color(0, 51, 51));
        jPanelLateral.setPreferredSize(new java.awt.Dimension(50, 700));

        jButtonClose.setBackground(new java.awt.Color(0, 51, 51));
        jButtonClose.setForeground(new java.awt.Color(0, 51, 51));
        jButtonClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons8_close_window_70px.png"))); // NOI18N
        jButtonClose.setBorder(null);
        jButtonClose.setBorderPainted(false);
        jButtonClose.setContentAreaFilled(false);
        jButtonClose.setPreferredSize(new java.awt.Dimension(50, 50));
        jButtonClose.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons8_close_window_70px_1.png"))); // NOI18N
        jButtonClose.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons8_close_window_70px_1.png"))); // NOI18N
        jButtonClose.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons8_close_window_70px_1.png"))); // NOI18N
        jButtonClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCloseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelLateralLayout = new javax.swing.GroupLayout(jPanelLateral);
        jPanelLateral.setLayout(jPanelLateralLayout);
        jPanelLateralLayout.setHorizontalGroup(
            jPanelLateralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLateralLayout.createSequentialGroup()
                .addComponent(jButtonClose, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelLateralLayout.setVerticalGroup(
            jPanelLateralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLateralLayout.createSequentialGroup()
                .addContainerGap(609, Short.MAX_VALUE)
                .addComponent(jButtonClose, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41))
        );

        getContentPane().add(jPanelLateral, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 50, 700));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void filtrar_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filtrar_buttonActionPerformed
        Dialogs d;
        // Verificamos si hay postulantes registrados en la base de datos.
        if(verificarExistenciaPostulante()){
            FiltradoPostulantesFrame fpf = new FiltradoPostulantesFrame(connection, menuframe, id_ofertasEmpleo.get(ofertas_list.getAnchorSelectionIndex()), option);
            fpf.setVisible(true);
            dispose();
        }
        else
        { d = new Dialogs(12);
        d.setVisible(true);}
    }//GEN-LAST:event_filtrar_buttonActionPerformed

    private void atras_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_atras_buttonActionPerformed
        menuframe.setVisible(true);
        dispose();
    }//GEN-LAST:event_atras_buttonActionPerformed

    private void verseleccionados_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_verseleccionados_buttonActionPerformed
        Dialogs d;
        if(!postulantesEvaluadosOfertaEmpleo(id_ofertasEmpleo.get(ofertas_list.getAnchorSelectionIndex()))){
            d = new Dialogs(13);
            d.setVisible(true);
            return;
        }

        SeleccionadosFrame sf = new SeleccionadosFrame(this, id_ofertasEmpleo.get(ofertas_list.getAnchorSelectionIndex()), connection);
        sf.setVisible(true);
        dispose();
    }//GEN-LAST:event_verseleccionados_buttonActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.setState(Formulario_Postulante_Info_Personal.ICONIFIED);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButtonCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCloseActionPerformed
        Dialogs d = new Dialogs(1);
        d.setVisible(true);
    }//GEN-LAST:event_jButtonCloseActionPerformed

    

    
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton atras_button;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton filtrar_button;
    private javax.swing.JLabel jBackGround;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonClose;
    private javax.swing.JLabel jInfPersonal;
    private javax.swing.JPanel jPIngreso;
    private javax.swing.JPanel jPPage1;
    private javax.swing.JPanel jPanelLateral;
    private javax.swing.JPanel jPanelOfertasEmpleo;
    private javax.swing.JPanel jPanelSuperior;
    private javax.swing.JScrollPane jScrollPaneLista;
    private javax.swing.JList<String> ofertas_list;
    private javax.swing.JButton verseleccionados_button;
    // End of variables declaration//GEN-END:variables
}