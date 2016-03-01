package CourseRegulatorNew;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

public class UpdateCourse extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	static JTextField textField_3;
	static JTextField textField_4;
	static JTextField textField_5;
	private String text = " ";
	private String text_1 = " ";
	static Faculty faculty = null;
	static List<Faculty> updatedInstructorList = new ArrayList<Faculty>();
	static List<Participant> updatedParticipantList = new ArrayList<Participant>();
	private CourseDAO courseDAO = new CourseDAO();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
	}

	/**
	 * Create the dialog.
	 * @throws ParseException 
	 */
	BigDecimal Change_From_String_To_BigDecimal(String number){
		double num = Double.valueOf(number);
		return BigDecimal.valueOf(num);
	}
	
	
	public UpdateCourse(Course course) throws ParseException {
		faculty = course.getFaculty();
		if(updatedInstructorList.size()==0){
		for(int i = 0 ; i < course.getInstructorlist().size();i++){
			updatedInstructorList.add(course.getInstructorlist().get(i));
		}
		}
		if(updatedParticipantList.size()==0){
		for(int i = 0 ; i < course.getParticipantlist().size() ; i++){
			updatedParticipantList.add(course.getParticipantlist().get(i));
		}
		}
		setTitle("Update Course Information");
		setBounds(100, 100, 737, 502);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Name");
			lblNewLabel.setBounds(10, 11, 46, 14);
			contentPanel.add(lblNewLabel);
		}
		{
			textField = new JTextField();
			textField.setBounds(79, 8, 632, 20);
			contentPanel.add(textField);
			textField.setColumns(10);
			if(course!=null){
				textField.setText(course.getName());
			}
		}
		{
			JLabel lblFee = new JLabel("Fee");
			lblFee.setBounds(10, 62, 46, 14);
			contentPanel.add(lblFee);
		}
		{
			textField_1 = new JTextField();
			textField_1.setBounds(79, 59, 139, 20);
			contentPanel.add(textField_1);
			textField_1.setColumns(10);
			if(course!=null){
				textField_1.setText(String.valueOf(course.getFee()));
			}
		}
		{
			JLabel lblStartDate = new JLabel("Start Date");
			lblStartDate.setBounds(10, 112, 69, 14);
			contentPanel.add(lblStartDate);
		}
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(84, 112, 177, 20);
		contentPanel.add(dateChooser);
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		if(course!=null){
			dateChooser.setDate(formatter.parse(course.getStartdate()));
		}
		
		JLabel lblDuration = new JLabel("Duration");
		lblDuration.setBounds(10, 165, 57, 14);
		contentPanel.add(lblDuration);
		
		textField_2 = new JTextField();
		textField_2.setBounds(85, 162, 176, 20);
		contentPanel.add(textField_2);
		textField_2.setColumns(10);
		if(course!=null){
		textField_2.setText(course.getDuration());
		}
		
		JLabel lblFaculty = new JLabel("Faculty");
		lblFaculty.setBounds(10, 220, 46, 14);
		contentPanel.add(lblFaculty);
		
		textField_3 = new JTextField();
		textField_3.setToolTipText("Click The Button Beside It");
		textField_3.setEditable(false);
		textField_3.setBounds(93, 217, 186, 20);
		contentPanel.add(textField_3);
		textField_3.setColumns(10);
		if(course!=null){
			textField_3.setText(course.getFaculty().getName());
		}
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					SelectFaculty dialog = new SelectFaculty(course);
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(285, 216, 46, 23);
		contentPanel.add(btnNewButton);
		
		JLabel lblInstructors = new JLabel("Instructors");
		lblInstructors.setBounds(10, 284, 69, 14);
		contentPanel.add(lblInstructors);
		
		textField_4 = new JTextField();
		textField_4.setToolTipText("Click The Button Beside It");
		textField_4.setEditable(false);
		textField_4.setBounds(93, 270, 546, 65);
		contentPanel.add(textField_4);
		textField_4.setColumns(10);
		if(course!=null){
		for(int i = 0 ; i < course.getInstructorlist().size(); i++ ){
			text = text + course.getInstructorlist().get(i).getName()+",";
		}
		textField_4.setText(text);
		}
		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					UpdateInstructor dialog = new UpdateInstructor(course);
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_1.setBounds(649, 280, 46, 23);
		contentPanel.add(btnNewButton_1);
		
		JLabel lblParticipants = new JLabel("Participants");
		lblParticipants.setBounds(10, 373, 69, 23);
		contentPanel.add(lblParticipants);
		
		textField_5 = new JTextField();
		textField_5.setToolTipText("Click The Button Beside It");
		textField_5.setEditable(false);
		textField_5.setBounds(100, 360, 539, 59);
		contentPanel.add(textField_5);
		textField_5.setColumns(10);
		if(course!=null){
			for(int  i = 0 ; i < course.getParticipantlist().size() ; i++){
				text_1 = text_1 + course.getParticipantlist().get(i).getName()+" , ";
			}
			textField_5.setText(text_1);
		}
		
		JButton btnNewButton_2 = new JButton("New button");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					SelectParticipants dialog = new SelectParticipants(course);
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_2.setBounds(649, 373, 46, 23);
		contentPanel.add(btnNewButton_2);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
						String name = textField.getText();
						BigDecimal fee = Change_From_String_To_BigDecimal(textField_1.getText());
						String startdate = formatter.format(dateChooser.getDate());
						String duration = textField_2.getText();
						Course updatedCourse = new Course(course.getId(),name,fee,startdate,duration,faculty,updatedInstructorList,updatedParticipantList);
						int index = -1;
						try {
							for(int i = 0 ; i < courseDAO.getCourseList().size() ; i++){
								if(course.getId() == courseDAO.getCourseList().get(i).getId()){
									index = i;
								}
							}
						} catch (ClassNotFoundException | IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						try {
							courseDAO.update_the_course(index, updatedCourse);
						} catch (ClassNotFoundException | IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						dispose();
						JOptionPane.showMessageDialog(null, "Course Updated , Please Open Again to see ");
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

