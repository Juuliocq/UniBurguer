
package com.mycompany.uniburguerretaguarda.gui;

import com.mycompany.uniburguerretaguarda.model.Pedido;
import com.mycompany.uniburguerretaguarda.service.PedidosService;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class PedidosConsultaGUI extends javax.swing.JFrame {
    
    private List<Pedido> orders = new ArrayList<>();
    private final PedidosService pedidosService = new PedidosService();

    public PedidosConsultaGUI() {
        initComponents();
        this.setLocationRelativeTo(null);
    }
    
    private void consultar() {
        int filtro = cboSituacao.getSelectedIndex();
        
        orders = pedidosService.getOrders();
        
        if (filtro == 0) {
            orders = orders.stream().filter(i -> !i.isFinalizado()).collect(Collectors.toList());
        } else if (filtro == 1) {
            orders = orders.stream().filter(i -> i.isFinalizado()).collect(Collectors.toList());
        }
        
        popularTabela();
    }
    
    private void popularTabela() {
        if (orders.isEmpty()) {
        JOptionPane.showMessageDialog(this, 
                "Lista de Pedidos vazia!", "", JOptionPane.INFORMATION_MESSAGE);
        }
    
        Object info[][] = new Object[orders.size()][4];

        int i = 0;
        for (Pedido pedido : orders) {

         info[i][Tabela.ID.getId()] = pedido.getId();
         info[i][Tabela.FINALIZADO.getId()] = pedido.isFinalizado() ? "SIM" : "NÃO";
         info[i][Tabela.NOME_CLIENTE.getId()] = pedido.getNome_cliente();
         info[i][Tabela.DATA_HORA.getId()] = pedido.getData_hora();

         i++;
     }

     setModel(info);
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
 
    private void visualizar() {
        Pedido pedido = orders.get(tblOrders.getSelectedRow());
        
        PedidoCadastroGUI pedidoForm = new PedidoCadastroGUI(pedido);
        pedidoForm.setVisible(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblOrders = new javax.swing.JTable();
        cboSituacao = new javax.swing.JComboBox<>();
        btnConsultar = new javax.swing.JButton();
        btnVisualizar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tblOrders.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tblOrders.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblOrdersMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblOrders);

        cboSituacao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "NÃO FINALIZADO", "FINALIZADO", "TODOS" }));

        btnConsultar.setText("Consultar");
        btnConsultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultarActionPerformed(evt);
            }
        });

        btnVisualizar.setText("Visualizar");
        btnVisualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVisualizarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 613, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cboSituacao, 0, 156, Short.MAX_VALUE)
                    .addComponent(btnConsultar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnVisualizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cboSituacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnConsultar)
                        .addGap(33, 33, 33)
                        .addComponent(btnVisualizar)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 527, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultarActionPerformed
        consultar();
    }//GEN-LAST:event_btnConsultarActionPerformed

    private void btnVisualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVisualizarActionPerformed
        visualizar();
    }//GEN-LAST:event_btnVisualizarActionPerformed

    private void tblOrdersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblOrdersMouseClicked
        if (evt.getClickCount() == 2) {
            visualizar();
        }
    }//GEN-LAST:event_tblOrdersMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConsultar;
    private javax.swing.JButton btnVisualizar;
    private javax.swing.JComboBox<String> cboSituacao;
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

