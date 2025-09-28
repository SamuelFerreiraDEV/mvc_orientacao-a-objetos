package projetomvc.views;

import projetomvc.views.interfaces.EntityView;
import projetomvc.views.interfaces.ViewNavigator;

/**
 *
 * @author samuel
 */
public class MainView<T, K> extends javax.swing.JFrame implements ViewNavigator<T, K> {

    private EntityView<T> authorsView;
    private EntityView<K> booksView;

    private javax.swing.JLabel labelHeader;
    private javax.swing.JButton manageAuthorsButton;
    private javax.swing.JButton manageBooksButton;
    private javax.swing.JPanel pannelButtons;

    public MainView() {
        initComponents();
    }

    public void showMainView() {
        this.setVisible(true);
    }

    public void showBooksView() {
        this.setVisible(false);
        this.booksView.setVisible(true);
    }

    public void showAuthorsView() {
        this.setVisible(false);
        this.authorsView.setVisible(true);
    }

    public void setBooksView(EntityView<K> booksView) {
        this.booksView = booksView;
    }

    public void setAuthorsView(EntityView<T> authorsView) {
        this.authorsView = authorsView;
    }

    private void manageAuthorsButtonActionPerformed(java.awt.event.ActionEvent evt) {
        this.showAuthorsView();
    }

    private void manageBooksButtonActionPerformed(java.awt.event.ActionEvent evt) {
        this.showBooksView();
    }

    private void initComponents() {

        pannelButtons = new javax.swing.JPanel();
        manageBooksButton = new javax.swing.JButton();
        manageAuthorsButton = new javax.swing.JButton();
        labelHeader = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pannelButtons.setBackground(new java.awt.Color(255, 255, 255));

        manageBooksButton.setText("Gerenciar Livros");
        manageBooksButton.setAlignmentX(0.5F);
        manageBooksButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        manageBooksButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manageBooksButtonActionPerformed(evt);
            }
        });

        manageAuthorsButton.setText("Gerenciar Autores");
        manageAuthorsButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        manageAuthorsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manageAuthorsButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pannelButtonsLayout = new javax.swing.GroupLayout(pannelButtons);
        pannelButtons.setLayout(pannelButtonsLayout);
        pannelButtonsLayout.setHorizontalGroup(
            pannelButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(manageBooksButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(manageAuthorsButton, javax.swing.GroupLayout.DEFAULT_SIZE, 390, Short.MAX_VALUE)
        );
        pannelButtonsLayout.setVerticalGroup(
            pannelButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pannelButtonsLayout.createSequentialGroup()
                .addComponent(manageBooksButton, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(manageAuthorsButton, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE))
        );

        labelHeader.setFont(new java.awt.Font("Liberation Sans", 1, 36)); // NOI18N
        labelHeader.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelHeader.setText("Sistema de Biblioteca");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelHeader, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(268, 268, 268)
                .addComponent(pannelButtons, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(319, 319, 319))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelHeader, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(101, 101, 101)
                .addComponent(pannelButtons, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(166, 166, 166))
        );

        pack();
    }
}
