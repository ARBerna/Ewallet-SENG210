package src.fileFix;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class AuthDialog extends JDialog {

    private final JTextField txtUser = new JTextField(15);
    private final JPasswordField txtPass = new JPasswordField(15);
    private final JTextField txtEmail = new JTextField(15);   // NEW EMAIL FIELD

    private final JButton btnAction = new JButton("Login");
    private final JButton btnToggle = new JButton("Need an account? Register");

    private boolean isLoginMode = true;
    private User authenticatedUser = null;

    public AuthDialog(JFrame parent) {
        super(parent, "Welcome to E-Wallet", true);
        setSize(320, 260);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Form panel
        JPanel formPanel = new JPanel(new GridLayout(3, 2, 5, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        formPanel.add(new JLabel("Username:"));
        formPanel.add(txtUser);

        formPanel.add(new JLabel("Password:"));
        formPanel.add(txtPass);

        formPanel.add(new JLabel("Email:"));
        formPanel.add(txtEmail);
        txtEmail.setVisible(false); // hidden in login mode

        add(formPanel, BorderLayout.CENTER);

        // Buttons panel
        JPanel actionPanel = new JPanel(new GridLayout(2, 1, 5, 5));
        actionPanel.setBorder(BorderFactory.createEmptyBorder(0, 15, 15, 15));
        actionPanel.add(btnAction);
        actionPanel.add(btnToggle);
        add(actionPanel, BorderLayout.SOUTH);

        btnAction.addActionListener(e -> handleAuth());
        btnToggle.addActionListener(e -> toggleMode());

        java.net.URL iconURL = getClass().getResource("modified-noun-purse-3362985.png");
		System.out.println("Icon URL: " + iconURL);

		if (iconURL != null) {
		    ImageIcon icon = new ImageIcon(iconURL);
		    setIconImage(icon.getImage());
		} else {
		    System.out.println("Icon not found");
		}
    }

    private void toggleMode() {
        isLoginMode = !isLoginMode;

        btnAction.setText(isLoginMode ? "Login" : "Register");
        btnToggle.setText(isLoginMode ? "Need an account? Register" : "Back to Login");

        txtEmail.setVisible(!isLoginMode);  // show email only in register mode
        pack(); // resize window to fit new field
    }

    private void handleAuth() {
        String username = txtUser.getText().trim();
        String password = new String(txtPass.getPassword()).trim();
        String email = txtEmail.getText().trim();

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Fields cannot be blank.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (isLoginMode) {
            // LOGIN USING DATABASE
            if (UserDAO.validateLogin(username, password)) {
                DatabaseUser dbUser = UserDAO.getUserByUsername(username);

                // ⭐ FIXED: Pass userID into the GUI User object
                User guiUser = new User(
                    dbUser.getUserID(),
                    dbUser.getUsername(),
                    dbUser.getPasswordHash()
                );

                this.authenticatedUser = guiUser;
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid credentials.", "Auth Failed", JOptionPane.ERROR_MESSAGE);
            }

        } else {
            // REGISTRATION USING DATABASE
            if (email.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Email cannot be blank.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            DatabaseUser newDBUser = new DatabaseUser(username, password, email);

            if (UserDAO.addUser(newDBUser)) {
                JOptionPane.showMessageDialog(this, "Registration successful! You can now log in.");
                toggleMode();
            } else {
                JOptionPane.showMessageDialog(this, "Error creating account.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public User getAuthenticatedUser() {
        return authenticatedUser;
    }
}
