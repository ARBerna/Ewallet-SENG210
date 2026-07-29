package fileFix;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class AuthDialog extends JDialog {
    // Simulated simple in-memory User Database 
    private static final Map<String, User> userDatabase = new HashMap<>();
    
    static {
        userDatabase.put("test", new User("test", "123"));
    }

    private final JTextField txtUser = new JTextField(15);
    private final JPasswordField txtPass = new JPasswordField(15);
    private final JButton btnAction = new JButton("Login");
    private final JButton btnToggle = new JButton("Need an account? Register");
    
    private boolean isLoginMode = true;
    private User authenticatedUser = null;

    public AuthDialog(JFrame parent) {
        super(parent, "Welcome to E-Wallet", true);
        setSize(320, 220);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Vertical layout structural grid form panel 
        JPanel formPanel = new JPanel(new GridLayout(2, 2, 5, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        formPanel.add(new JLabel("Username:"));
        formPanel.add(txtUser);
        formPanel.add(new JLabel("Password:"));
        formPanel.add(txtPass);
        add(formPanel, BorderLayout.CENTER);

        JPanel actionPanel = new JPanel(new GridLayout(2, 1, 5, 5));
        actionPanel.setBorder(BorderFactory.createEmptyBorder(0, 15, 15, 15));
        actionPanel.add(btnAction);
        actionPanel.add(btnToggle);
        add(actionPanel, BorderLayout.SOUTH);

        btnAction.addActionListener(e -> handleAuth());
        btnToggle.addActionListener(e -> toggleMode());
    }

    private void toggleMode() {
        isLoginMode = !isLoginMode;
        btnAction.setText(isLoginMode ? "Login" : "Register");
        btnToggle.setText(isLoginMode ? "Need an account? Register" : "Back to Login");
    }

    private void handleAuth() {
        String username = txtUser.getText().trim();
        String password = new String(txtPass.getPassword()).trim();

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Fields cannot be blank.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (isLoginMode) {
            // LOGIN EVALUATION
            User foundUser = userDatabase.get(username);
            if (foundUser != null && foundUser.pwd.equals(password)) {
                this.authenticatedUser = foundUser;
                dispose(); 
            } else {
                JOptionPane.showMessageDialog(this, "Invalid credentials.", "Auth Failed", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            // REGISTRATION EVALUATION 
            if (userDatabase.containsKey(username)) {
                JOptionPane.showMessageDialog(this, "Username already exists.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                User newUser = new User(username, password);
                
                newUser.addWage(new Wage("Welcome Bonus", 10.0, "Initial"));
                
                userDatabase.put(username, newUser);
                JOptionPane.showMessageDialog(this, "Registration successful! You can now log in.");
                toggleMode();
            }
        }
    }

    public User getAuthenticatedUser() {
        return authenticatedUser;
    }
}
