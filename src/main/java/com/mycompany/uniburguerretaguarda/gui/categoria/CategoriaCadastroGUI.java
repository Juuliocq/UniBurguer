package com.mycompany.uniburguerretaguarda.gui.categoria;

import com.mycompany.uniburguerretaguarda.model.Categoria;
import com.mycompany.uniburguerretaguarda.service.CategoriaService;

public class CategoriaCadastroGUI extends javax.swing.JFrame {
    
    Categoria categoria;
    private final CategoriaService categoriaService = new CategoriaService();

    public CategoriaCadastroGUI() {
        initComponents();
        
        this.setLocationRelativeTo(null);
    }
    
    public void incluir() {
        categoria = new Categoria();
    }
    
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
        
        txtDescricao.setText(categoria.getDescricao());
    }
    
    private void salvar() {
        String nomeAnterior = categoria.getDescricao();
        try {
            categoria.setDescricao(txtDescricao.getText());
            categoriaService.salvar(categoria);
        } catch (Exception ex) {
            txtDescricao.setText(nomeAnterior);
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

        txtDescricao = new javax.swing.JTextField();
        btnSalvar = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDescricao, javax.swing.GroupLayout.DEFAULT_SIZE, 365, Short.MAX_VALUE)
                    .addComponent(btnSalvar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                .addComponent(btnSalvar)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        salvar();
    }//GEN-LAST:event_btnSalvarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton btnSalvar;
    private javax.swing.JTextField txtDescricao;
    // End of variables declaration//GEN-END:variables
}
