package com.mycompany.uniburguerretaguarda.gui.login;

import com.mycompany.uniburguerretaguarda.model.Categoria;
import com.mycompany.uniburguerretaguarda.service.CategoriaService;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class LoginConsultaGUI extends javax.swing.JFrame {
    
    private List<Categoria> categorias = new ArrayList<>();
    private final CategoriaService categoriaService = new CategoriaService();

    public LoginConsultaGUI() {
        initComponents();
        this.setLocationRelativeTo(null);
    }
    
    private void consultar() {        
        categorias = categoriaService.getCategorias();
        
        categorias = categorias.stream().sorted(Comparator.comparing(Categoria::getDescricao)).collect(Collectors.toList());
        
        popularTabela();
    }
    
    private void popularTabela() {
        if (categorias.isEmpty()) {
        JOptionPane.showMessageDialog(this, 
                "Lista de Categorias vazia!", "", JOptionPane.INFORMATION_MESSAGE);
        }
    
        Object info[][] = new Object[categorias.size()][4];

        int i = 0;
        for (Categoria categoria : categorias) {

         info[i][Tabela.ID.getId()] = categoria.getId();
         info[i][Tabela.DESCRICAO.getId()] = categoria.getDescricao();

         i++;
     }

     setModel(info);
    } 

 private void setModel(Object info[][]) {
     String colunas[] = {
        "ID", "Descrição"
     };

     tblCategorias.setModel(new DefaultTableModel(
             info,
             colunas
     ) {
         boolean[] canEdit = new boolean[]{
             false,false
         };

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return canEdit[columnIndex];
            }
        }
        );
    }
 
    private void excluir() throws Exception {
        if (tblCategorias.getSelectedRow() == -1) {
            return;
        }
        
        categoriaService.excluirCategoria(getCategoriaSelecionada());
    }
 
    private Categoria getCategoriaSelecionada() {        
        return categorias.get(tblCategorias.getSelectedRow());
    }

    private void visualizar() {
        if (tblCategorias.getSelectedRow() == -1) {
            return;
        }
        
        LoginCadastroGUI form = new LoginCadastroGUI();
        form.setCategoria(getCategoriaSelecionada());
        form.setVisible(true);
    }
 
    private void incluir() {
        LoginCadastroGUI form = new LoginCadastroGUI();
        form.incluir();
        form.setVisible(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblCategorias = new javax.swing.JTable();
        btnConsultar = new javax.swing.JButton();
        btnVisualizar = new javax.swing.JButton();
        btnIncluir = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tblCategorias.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tblCategorias.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCategoriasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblCategorias);

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

        btnIncluir.setText("Incluir");
        btnIncluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIncluirActionPerformed(evt);
            }
        });

        btnExcluir.setText("Excluir");
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
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
                    .addComponent(btnConsultar, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
                    .addComponent(btnVisualizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnIncluir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnExcluir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnIncluir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnVisualizar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnExcluir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnConsultar))
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

    private void tblCategoriasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCategoriasMouseClicked
        if (evt.getClickCount() == 2) {
            visualizar();
        }
    }//GEN-LAST:event_tblCategoriasMouseClicked

    private void btnIncluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIncluirActionPerformed
        incluir();
    }//GEN-LAST:event_btnIncluirActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        try {
            excluir();
        } catch (Exception ex) {
            Logger.getLogger(LoginConsultaGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnExcluirActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConsultar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnIncluir;
    private javax.swing.JButton btnVisualizar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblCategorias;
    // End of variables declaration//GEN-END:variables

    private enum Tabela {
        
        ID(0),
        DESCRICAO(1);

        private final int id;
        
        Tabela(int id) {
            this.id = id;
        }
        
        public int getId(){
            return this.id;
            
        }
    }
}

