
package com.mycompany.uniburguerretaguarda.gui;

import com.mycompany.uniburguerretaguarda.model.Orders;
import com.mycompany.uniburguerretaguarda.service.PedidosService;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class PedidosGUI extends javax.swing.JFrame {
    
    private List<Orders> orders = new ArrayList<>();
    private final PedidosService pedidosService = new PedidosService();

    public PedidosGUI() {
        initComponents();
        this.setLocationRelativeTo(null);
        
        orders = pedidosService.getOrders();
        popularTabela();
    }
    
    private void popularTabela() {
        if (orders.isEmpty()) {
        JOptionPane.showMessageDialog(this, 
                "Lista de Pedidos vazia!", "", JOptionPane.INFORMATION_MESSAGE);
        }

    
        Object info[][] = new Object[orders.size()][4];

        int i = 0;
        for (Orders orders : orders) {


         info[i][Tabela.ID.getId()] = orders.getId();
         info[i][Tabela.FINALIZADO.getId()] = orders.isFinalizado() ? "SIM" : "NÃO";
         info[i][Tabela.NOME_CLIENTE.getId()] = orders.getNome_cliente();
         info[i][Tabela.DATA_HORA.getId()] = orders.getData_hora();


         i++;
     }

     setModel(info);
    }
    
    private void finalizar() throws Exception {
        Orders orderSelecionado = orders.get(tblOrders.getSelectedRow());
        
        orderSelecionado.setFinalizado(true);
        
        pedidosService.putOrder(orderSelecionado);
        
        orders.remove(orderSelecionado);
        popularTabela();
    }

 

 private void setModel(Object info[][]) {
     String colunas[] = {
        "ID", "Finalizado", "Nome do Cliente", "Data e Hora"
     };

     tblOrders.setModel(new DefaultTableModel(
             info,
             colunas
     ) {
         boolean[] canEdit = new boolean[]{
             false,false, false, false
         };

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return canEdit[columnIndex];
            }
        }
        );
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblOrders = new javax.swing.JTable();
        btnFinalizado = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tblOrders.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblOrders);

        btnFinalizado.setText("Finalizado");
        btnFinalizado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFinalizadoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 303, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnFinalizado)
                .addGap(10, 10, 10))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnFinalizado)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnFinalizadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFinalizadoActionPerformed
        try {
            finalizar();
        } catch (Exception ex) {
            Logger.getLogger(PedidosGUI.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
        }
    }//GEN-LAST:event_btnFinalizadoActionPerformed


    
    public static void main(String args[]) {

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
            java.util.logging.Logger.getLogger(PedidosGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PedidosGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PedidosGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PedidosGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PedidosGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFinalizado;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblOrders;
    // End of variables declaration//GEN-END:variables


    private enum Tabela {
        
        ID(0),
        FINALIZADO(1),
        NOME_CLIENTE(2),
        DATA_HORA(3);

        
        
        private final int id;
        
        Tabela(int id) {
            this.id = id;
        }
        
        public int getId(){
            return this.id;
            
        }
    }
}

