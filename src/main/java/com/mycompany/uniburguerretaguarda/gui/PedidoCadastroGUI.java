/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.uniburguerretaguarda.gui;

import com.mycompany.uniburguerretaguarda.model.Pedido;
import com.mycompany.uniburguerretaguarda.model.PedidoItem;
import com.mycompany.uniburguerretaguarda.service.PedidosService;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class PedidoCadastroGUI extends javax.swing.JFrame {
    
    private final Pedido pedido;
    private final PedidosService pedidosService = new PedidosService();

    public PedidoCadastroGUI(Pedido pedido) {
        initComponents();
        
        this.setLocationRelativeTo(null);
        
        this.pedido = pedido;
        
        consultar();
    }
    
    private void consultar() {
        if (pedido.getId() > 0) {
            pedido.setPedidoItens(pedidosService.getItensPedido(pedido.getId()));
        }
        
        popularTabela();
        popularCampos();
    }
    
    private void popularCampos() {        
        txtNomeCliente.setText(pedido.getNome_cliente());
        txtValorTotal.setText(pedido.getValorTotalPedido());
        
        cboSituacao.setSelectedItem(pedido.isFinalizado() ? "FINALIZADO" : "NÃO FINALIZADO");
        btnFinalizar.setEnabled(!pedido.isFinalizado());
        btnConferirItem.setEnabled(!pedido.isFinalizado());
        
        if (tblProduto.getSelectedRow() == -1) {
            txtObs.setText("");
            return;
        }
        
        popularCampoObs(getItemSelecionado());
    }
    
    private void conferirItem() {        
        if (tblProduto.getSelectedRow() == -1) {
            return;
        }
        
        pedido.getPedidoItens().remove(getItemSelecionado());
        
        popularTabela();
    }
    
    private PedidoItem getItemSelecionado() {
        if (tblProduto.getSelectedRow() == -1) {
            return null;
        }
        
        return pedido.getPedidoItens().get(tblProduto.getSelectedRow());
    }
    
    private void popularCampoObs(PedidoItem pedidoItem) {        
        txtObs.setText(pedidoItem.getObservacao());
    }
    
    private void popularTabela() {
        if (pedido.getQtdPedidoItens() <= 0) {
        JOptionPane.showMessageDialog(this, 
                "Lista de produtos vazia!", "", JOptionPane.INFORMATION_MESSAGE);
        }
    
        Object info[][] = new Object[pedido.getQtdPedidoItens()][4];

        int i = 0;
        for (PedidoItem pedidoItem : pedido.getPedidoItens()) {

         info[i][Tabela.NUMERO_PRODUTO.getId()] = pedidoItem.getId_produto();
         info[i][Tabela.NOME_PRODUTO.getId()] = pedidoItem.getNome();
         info[i][Tabela.OBSERVACAO.getId()] = pedidoItem.getObservacao();
         info[i][Tabela.PRECO.getId()] = pedidoItem.getPrecoFormatado();

         i++;
     }

     setModel(info);
    } 
    
    private void finalizar() throws Exception {
        
        if (tblProduto.getRowCount() != 0) {
            Object[] options = { "Sim", "Não" };
            int desejaFinalizar = JOptionPane.showOptionDialog(this, "Existem produtos ainda não conferidos. Deseja finalizar?",
                    "Confirma?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
            
            if (!getBooleanByInt(desejaFinalizar)) {
                return;
            }
            
        }
        
        pedido.setFinalizado(true);
        
        pedidosService.putOrder(pedido);
        popularCampos();
    }
    
    private boolean getBooleanByInt(int i) {
        if (i == 1) {
            return false;
        }
        
        return true;
    }
    
    private void setModel(Object info[][]) {
     String colunas[] = {
        "Número", "Produto", "Observação", "Preço"
     };

     tblProduto.setModel(new DefaultTableModel(
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblProduto = new javax.swing.JTable();
        cboSituacao = new javax.swing.JComboBox<>();
        btnFinalizar = new javax.swing.JButton();
        txtNomeCliente = new javax.swing.JTextField();
        txtValorTotal = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtObs = new javax.swing.JTextArea();
        btnConferirItem = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tblProduto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tblProduto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProdutoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblProduto);

        cboSituacao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "FINALIZADO", "NÃO FINALIZADO" }));
        cboSituacao.setSelectedIndex(-1);
        cboSituacao.setEnabled(false);

        btnFinalizar.setText("Finalizar");
        btnFinalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFinalizarActionPerformed(evt);
            }
        });

        txtNomeCliente.setEditable(false);

        txtValorTotal.setEditable(false);

        txtObs.setEditable(false);
        txtObs.setColumns(20);
        txtObs.setRows(5);
        jScrollPane2.setViewportView(txtObs);

        btnConferirItem.setText("Conferir Item");
        btnConferirItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConferirItemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 576, Short.MAX_VALUE)
                    .addComponent(btnConferirItem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cboSituacao, 0, 230, Short.MAX_VALUE)
                    .addComponent(btnFinalizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtNomeCliente)
                    .addComponent(txtValorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 499, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnConferirItem))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtNomeCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtValorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(cboSituacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnFinalizar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnFinalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFinalizarActionPerformed
        try {
            finalizar();
        } catch (Exception ex) {
            Logger.getLogger(PedidoCadastroGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnFinalizarActionPerformed

    private void tblProdutoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProdutoMouseClicked
        popularCampoObs(getItemSelecionado());
    }//GEN-LAST:event_tblProdutoMouseClicked

    private void btnConferirItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConferirItemActionPerformed
        conferirItem();
    }//GEN-LAST:event_btnConferirItemActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConferirItem;
    private javax.swing.JButton btnFinalizar;
    private javax.swing.JComboBox<String> cboSituacao;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblProduto;
    private javax.swing.JTextField txtNomeCliente;
    private javax.swing.JTextArea txtObs;
    private javax.swing.JTextField txtValorTotal;
    // End of variables declaration//GEN-END:variables

    private enum Tabela {
        
        NUMERO_PRODUTO(0),
        NOME_PRODUTO(1),
        OBSERVACAO(2),
        PRECO(3);

        private final int id;
        
        Tabela(int id) {
            this.id = id;
        }
        
        public int getId(){
            return this.id;
            
        }
    }
}