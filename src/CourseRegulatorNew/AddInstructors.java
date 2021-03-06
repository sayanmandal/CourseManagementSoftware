package CourseRegulatorNew;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class AddInstructors extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private CourseDAO courseDAO= new CourseDAO();
	private JTable table;
	private InstructorTableModel model;
	private List<Faculty> instructorlist ;
	private String text = " ";
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AddInstructors dialog = new AddInstructors();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	public AddInstructors() throws ClassNotFoundException, IOException {
		instructorlist = courseDAO.getFacultyList();
		this.setMinimumSize(new Dimension(800,439));
		setTitle("Add Instructors");
		setBounds(100, 100, 800, 439);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.NORTH);
			{
				textField = new JTextField();
				panel.add(textField);
				textField.setColumns(30);
			}
			{
				JButton btnSearch = new JButton("Search");
				btnSearch.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String name = textField.getText();
						if(name.trim().length()!=0){
						try {
							table.setModel(new InstructorTableModel(courseDAO.search_faculty(name)));
						} catch (ClassNotFoundException | IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						}
					}
				});
				panel.add(btnSearch);
			}
			{
				JButton btnShowAll = new JButton("Show All");
				btnShowAll.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							table.setModel(new InstructorTableModel(courseDAO.getFacultyList()));
						} catch (ClassNotFoundException | IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				});
				panel.add(btnShowAll);
			}
		}
		{
			table = new JTable();
		    model = new InstructorTableModel(courseDAO.getFacultyList());
			table.setModel(model);
			
			JScrollPane scrollPane = new JScrollPane(table);
			contentPanel.add(scrollPane, BorderLayout.CENTER);
			table.setFillsViewportHeight(true);
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
						if(AddCourse.instructorlist.size()!=0){
							for(int i = 0 ; i < AddCourse.instructorlist.size() ; i++){
								AddCourse.instructorlist.remove(i);
							}
						}
						for(int i = 0 ; i < model.getRowCount() ; i++){
							if(model.data[i]){
								try {
									AddCourse.instructorlist.add(courseDAO.getFacultyList().get(i));
								} catch (ClassNotFoundException | IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							}
						}
						if(AddCourse.instructorlist.size()>5){
							throw new Exception();
						}
						for(int i = 0 ; i < AddCourse.instructorlist.size() ; i++){
							 text = text+" " +AddCourse.instructorlist.get(i).getName()+",";
						}
						AddCourse.textField_4.setText(text);
						dispose();
						}catch(Exception e1){
							JOptionPane.showMessageDialog(null, "Please Choose Max. 5 Instructors");
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
