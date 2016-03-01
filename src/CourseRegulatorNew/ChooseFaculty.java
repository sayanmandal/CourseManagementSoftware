package CourseRegulatorNew;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

public class ChooseFaculty extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTable table;
	private CourseDAO courseDAO = new CourseDAO();
	private JTextField textField_1;
	private BufferedImage image;
	private Faculty thefaculty;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ChooseFaculty dialog = new ChooseFaculty();
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
	public ChooseFaculty() throws ClassNotFoundException, IOException {
		image = ImageIO.read(getClass().getResource("/courses.png"));
		this.setIconImage(image);
		this.setMinimumSize(new Dimension(719,447));
		setTitle("Choose Faculty");
		setBounds(100, 100, 719, 447);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			table = new JTable();
			table.setModel(new FacultyTableModel(courseDAO.getFacultyList()));
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			
			JScrollPane scrollPane = new JScrollPane(table);
			contentPanel.add(scrollPane, BorderLayout.CENTER);
			table.setFillsViewportHeight(true);
			{
				JPanel panel = new JPanel();
				scrollPane.setColumnHeaderView(panel);
				{
					textField = new JTextField();
					panel.add(textField);
					textField.setColumns(30);
				}
				{
					JButton btnSearch = new JButton("Search");
					panel.add(btnSearch);
				}
				{
					JButton btnShowAll = new JButton("Show All");
					panel.add(btnShowAll);
				}
			}
		}
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.NORTH);
			{
				textField_1 = new JTextField();
				panel.add(textField_1);
				textField_1.setColumns(30);
			}
			{
				JButton btnSearch_1 = new JButton("Search");
				btnSearch_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String name = textField_1.getText();
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
				panel.add(btnSearch_1);
			}
			{
				JButton btnShowAll_1 = new JButton("Show All");
				btnShowAll_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							table.setModel(new FacultyTableModel(courseDAO.getFacultyList()));
						} catch (ClassNotFoundException | IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				});
				panel.add(btnShowAll_1);
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
							int row = table.getSelectedRow();
							if(row<0){
								throw new Exception();
							}
							Faculty faculty = courseDAO.getFacultyList().get(row);
							thefaculty = faculty;
							AddCourse.textField_3.setText(faculty.getName());
							AddCourse.faculty = faculty;
							dispose();
						}catch(Exception e1){
							JOptionPane.showMessageDialog(null, "Please Select a Row", "Error", JOptionPane.ERROR_MESSAGE);
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
	
	public Faculty getFaculty(){
		return thefaculty;
	}

}
