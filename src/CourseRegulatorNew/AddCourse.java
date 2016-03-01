package CourseRegulatorNew;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

public class AddCourse extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private BufferedImage image;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	static JTextField textField_3;
	static JTextField textField_4;
	static JTextField textField_5;
	ChooseFaculty dialog = null;
	static Faculty faculty= null;
	static List<Faculty> instructorlist = new ArrayList<Faculty>();
	static List<Participant> participantlist = new ArrayList<Participant>();
	private CourseDAO courseDAO = new CourseDAO();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AddCourse dialog = new AddCourse();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 * @throws IOException 
	 */
	public BigDecimal Change_From_String_To_BigDecimal(String number){
		double num = Double.valueOf(number);
		return BigDecimal.valueOf(num);
	}
	
	public AddCourse() throws IOException {
		image = ImageIO.read(getClass().getResource("/courses.png"));
		this.setIconImage(image);
		setTitle("Add Course");
		setBounds(100, 100, 699, 497);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(10, 11, 46, 14);
		contentPanel.add(lblName);
		
		textField = new JTextField();
		textField.setBounds(66, 8, 347, 20);
		contentPanel.add(textField);
		textField.setColumns(10);
		
		JLabel lblFee = new JLabel("Fee");
		lblFee.setBounds(10, 36, 46, 14);
		contentPanel.add(lblFee);
		
		textField_1 = new JTextField();
		textField_1.setBounds(65, 33, 151, 20);
		contentPanel.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblStartDate = new JLabel("Start Date");
		lblStartDate.setBounds(10, 72, 72, 14);
		contentPanel.add(lblStartDate);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setDateFormatString("dd/MM/yyyy");
		dateChooser.setBounds(66, 66, 198, 25);
		contentPanel.add(dateChooser);
		
		JLabel lblDuration = new JLabel("Duration");
		lblDuration.setBounds(10, 117, 57, 14);
		contentPanel.add(lblDuration);
		
		textField_2 = new JTextField();
		textField_2.setBounds(66, 109, 296, 30);
		contentPanel.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblFaculty = new JLabel("Faculty");
		lblFaculty.setBounds(10, 162, 46, 14);
		contentPanel.add(lblFaculty);
		
		textField_3 = new JTextField();
		textField_3.setEditable(false);
		textField_3.setToolTipText("To Choose Faculty , Click the button beside the textfield");
		textField_3.setBounds(66, 159, 150, 20);
		contentPanel.add(textField_3);
		textField_3.setColumns(10);
		
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					dialog = new ChooseFaculty();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(224, 158, 37, 23);
		contentPanel.add(btnNewButton);
		
		JLabel lblInstructors = new JLabel("Instructors");
		lblInstructors.setBounds(10, 232, 72, 20);
		contentPanel.add(lblInstructors);
		
		textField_4 = new JTextField();
		textField_4.setEditable(false);
		textField_4.setToolTipText("To Choose Instructors Click the Button Beside Textfield");
		textField_4.setBounds(73, 197, 502, 103);
		contentPanel.add(textField_4);
		textField_4.setColumns(10);
		
		JLabel lblParticipants = new JLabel("Participants");
		lblParticipants.setBounds(10, 356, 72, 25);
		contentPanel.add(lblParticipants);
		
		textField_5 = new JTextField();
		textField_5.setEditable(false);
		textField_5.setToolTipText("To Choose Participants, Click the button beside the textfield");
		textField_5.setBounds(92, 311, 512, 103);
		contentPanel.add(textField_5);
		textField_5.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("...");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					AddInstructors dialog = new AddInstructors();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_1.setBounds(584, 219, 66, 46);
		contentPanel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("...");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ChooseParticipants dialog = new ChooseParticipants();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_2.setBounds(616, 350, 57, 37);
		contentPanel.add(btnNewButton_2);
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
						BigDecimal fee = Change_From_String_To_BigDecimal(textField_1.getText());
						SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
						String startdate = formatter.format(dateChooser.getDate());
						String duration = textField_2.getText();
						Course course = null;
						try {
							course = new Course(courseDAO.max_id_course()+1,name, fee, startdate, duration, faculty, instructorlist, participantlist);
						} catch (ClassNotFoundException | IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						try {
							courseDAO.add_Course(course);
						} catch (ClassNotFoundException | IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						dispose();
						JOptionPane.showMessageDialog(null, "Course Added , please Open Again To See the Courses");
						}catch(Exception e1){
							JOptionPane.showMessageDialog(null, "Please Check The Input", "Error", JOptionPane.ERROR_MESSAGE);
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
