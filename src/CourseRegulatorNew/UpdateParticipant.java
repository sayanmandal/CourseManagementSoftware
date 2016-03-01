package CourseRegulatorNew;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;

public class UpdateParticipant extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private CourseDAO courseDAO = new CourseDAO();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
	}

	/**
	 * Create the dialog.
	 */
	public UpdateParticipant(int index,Participant participant) {
		setTitle("Update Participants");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		{
			JLabel lblName = new JLabel("Name");
			contentPanel.add(lblName, "2, 2, right, default");
		}
		{
			textField = new JTextField();
			contentPanel.add(textField, "4, 2, fill, default");
			textField.setColumns(10);
			if(participant!=null){
				textField.setText(participant.getName());
			}
		}
		{
			JLabel lblAddress = new JLabel("Address");
			contentPanel.add(lblAddress, "2, 4, right, default");
		}
		{
			textField_1 = new JTextField();
			contentPanel.add(textField_1, "4, 4, fill, default");
			textField_1.setColumns(10);
			if(participant!=null){
				textField_1.setText(participant.getAddress());
			}
		}
		{
			JLabel lblMobileNo = new JLabel("Mobile No.");
			contentPanel.add(lblMobileNo, "2, 6, right, default");
		}
		{
			textField_2 = new JTextField();
			contentPanel.add(textField_2, "4, 6, fill, default");
			textField_2.setColumns(10);
			if(participant!=null){
				textField_2.setText(participant.getMobilenumber());
			}
		}
		{
			JLabel lblOrganization = new JLabel("Organization");
			contentPanel.add(lblOrganization, "2, 8, right, default");
		}
		{
			textField_3 = new JTextField();
			contentPanel.add(textField_3, "4, 8, fill, default");
			textField_3.setColumns(10);
			if(participant!=null){
				textField_3.setText(participant.getOrganization());
			}
		}
		{
			JLabel lblEmailAddress = new JLabel("Email Address");
			contentPanel.add(lblEmailAddress, "2, 10, right, default");
		}
		{
			textField_4 = new JTextField();
			contentPanel.add(textField_4, "4, 10, fill, default");
			textField_4.setColumns(10);
			if(participant!=null){
				textField_4.setText(participant.getEmailaddress());
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try{
						String name = textField.getText();
						String address = textField_1.getText();
						String mobnumber = textField_2.getText();
						if(!Pattern.matches("[0-9]{10}", mobnumber)){
							throw new Exception();
						}
						String organization = textField_3.getText();
						String emailaddress = textField_4.getText();
						Participant updatedparticipant = new Participant(participant.getId(),name,address,mobnumber,organization,emailaddress);
						courseDAO.update_the_participant(index, updatedparticipant);
						dispose();
						JOptionPane.showMessageDialog(null, "The participant updated , please click show all to see the participants");
						}catch(Exception e1){
							JOptionPane.showMessageDialog(null, "PLease Check the input");
						}
					}
				});
				okButton.setActionCommand("OK");
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

