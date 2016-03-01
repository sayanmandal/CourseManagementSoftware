package CourseRegulatorNew;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SelectFaculty extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private CourseDAO courseDAO = new CourseDAO();
	private JTable table;
	private FacultyTableModel model;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			SelectFaculty dialog = new SelectFaculty(null);
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
	public SelectFaculty(Course course) throws ClassNotFoundException, IOException {
		setTitle("Choose Coordinator");
		setBounds(100, 100, 740, 369);
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
								table.setModel(new FacultyTableModel(courseDAO.search_faculty(name)));
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
							table.setModel(new FacultyTableModel(courseDAO.getFacultyList()));
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
			model = new FacultyTableModel(courseDAO.getFacultyList());
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
						int row = table.getSelectedRow();
						if(row>=0){
							try {
								UpdateCourse.faculty = courseDAO.getFacultyList().get(row);
							} catch (ClassNotFoundException | IOException e2) {
								// TODO Auto-generated catch block
								e2.printStackTrace();
							}
							try {
								UpdateCourse.textField_3.setText(courseDAO.getFacultyList().get(row).getName());
							} catch (ClassNotFoundException | IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							JOptionPane.showMessageDialog(null, "Faculty Changed");
						}
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
