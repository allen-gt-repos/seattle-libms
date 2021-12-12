package gt.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import gt.model.User;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
/**
 * User password verification page
 * @author Wang, Yinuo
 *
 */
public class VerifyAccountDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			VerifyAccountDialog dialog = new VerifyAccountDialog(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public VerifyAccountDialog(User user) {
		setBounds(100, 100, 450, 181);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblPasswordForaccount = new JLabel("PLease Enter Password");
			lblPasswordForaccount.setBounds(118, 24, 258, 15);
			contentPanel.add(lblPasswordForaccount);
		}
		{
			textField = new JTextField();
			textField.setBounds(114, 55, 209, 19);
			contentPanel.add(textField);
			textField.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (textField.getText().equals(user.getPassword())) {
							
							dispose();
							ReaderUpdateInfoFrame readerUpdateInfoFrame = new ReaderUpdateInfoFrame(user);
							readerUpdateInfoFrame.setLocationRelativeTo(null);
							readerUpdateInfoFrame.setVisible(true);
							
						}
						else {
							dispose();
							JOptionPane.showMessageDialog(null, "Password is Wrong");
							return;
						}
					}
				});
				okButton.setActionCommand("OK");;
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
