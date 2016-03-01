package CourseRegulatorNew;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainFrame extends JFrame {

	private JPanel contentPane;
	private BufferedImage image;
	private JTextField textField;
	private List<Course> courselist ;
	private List<Faculty> facultylist;
	private List<Participant> participantlist;
	private CourseDAO courseDAO = new CourseDAO();
	private JTextField textField_1;
	private JTextField textField_2;
	private JTable table_2;
	private JTable table_1;
	private JTable table;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 * @throws ParseException 
	 */
	public MainFrame() throws IOException, ClassNotFoundException, ParseException {
		courselist = courseDAO.getCourseList();
		facultylist = courseDAO.getFacultyList();
		participantlist = courseDAO.getParticipantList();
		image = ImageIO.read(getClass().getResource("/IIT.png"));
		setIconImage(image);
		setTitle("Course Management");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setMinimumSize(new Dimension(1000,604));
		setBounds(100, 100, 1000, 604);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("Verdana", Font.BOLD, 16));
		tabbedPane.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Course Management", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 0, 255)));
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Course", null, panel, null);
		tabbedPane.setForegroundAt(0, Color.BLACK);
		tabbedPane.setBackgroundAt(0, Color.MAGENTA);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_3 = new JPanel();
		panel.add(panel_3, BorderLayout.NORTH);
		
		textField = new JTextField();
		panel_3.add(textField);
		textField.setColumns(30);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = textField.getText();
				if(name.trim().length()!=0){
					try {
						table.setModel(new CourseTableModel(courseDAO.search_course(name)));
					} catch (ClassNotFoundException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btnSearch.setFont(new Font("Verdana", Font.BOLD, 14));
		panel_3.add(btnSearch);
		
		JButton btnShowAll = new JButton("Show All");
		btnShowAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					table.setModel(new CourseTableModel(courseDAO.getCourseList()));
				} catch (ClassNotFoundException | IOException | ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnShowAll.setFont(new Font("Verdana", Font.BOLD, 14));
		panel_3.add(btnShowAll);
		
		JPanel panel_4 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_4.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panel.add(panel_4, BorderLayout.SOUTH);
		
		JButton btnViewParticipants = new JButton("View Participants");
		btnViewParticipants.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					int row = table.getSelectedRow();
					if(row<0){
						throw new Exception();
					}
					Course course = courseDAO.getCourseList().get(row);
					try {
						ShowParticipants dialog = new ShowParticipants(course.getParticipantlist());
						dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
						dialog.setVisible(true);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}catch(Exception e1){
					JOptionPane.showMessageDialog(null, "Please Select a Row", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnViewParticipants.setFont(new Font("Verdana", Font.BOLD, 16));
		panel_4.add(btnViewParticipants);
		
		JButton btnViewInstructors = new JButton("View Instructors");
		btnViewInstructors.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					int row = table.getSelectedRow();
					if(row<0){
						throw new Exception();
					}
					Course course = courseDAO.getCourseList().get(row);
					try {
						ShowInstructors dialog = new ShowInstructors(course.getInstructorlist());
						dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
						dialog.setVisible(true);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}catch(Exception e1){
					JOptionPane.showMessageDialog(null, "Please Select a Row", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnViewInstructors.setFont(new Font("Verdana", Font.BOLD, 16));
		panel_4.add(btnViewInstructors);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					int row = table.getSelectedRow();
					if(row<0){
						throw new Exception();
					}
					Course course = courseDAO.getCourseList().get(row);
					try {
						UpdateCourse dialog = new UpdateCourse(course);
						dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
						dialog.setVisible(true);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}catch(Exception e1){
					JOptionPane.showMessageDialog(null, "Please Select A Row", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnUpdate.setFont(new Font("Verdana", Font.BOLD, 14));
		panel_4.add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					int row = table.getSelectedRow();
					if(row<0){
						throw new Exception();
					}
					courseDAO.delete_the_course(row);
					JOptionPane.showMessageDialog(null, "The Course Deleted , Please Click Show ALL To See ALl Courses");
				}catch(Exception e1){
					JOptionPane.showMessageDialog(null, "Please Select A Row", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnDelete.setFont(new Font("Verdana", Font.BOLD, 14));
		panel_4.add(btnDelete);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					AddCourse dialog = new AddCourse();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnAdd.setFont(new Font("Verdana", Font.BOLD, 14));
		panel_4.add(btnAdd);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnClose.setFont(new Font("Verdana", Font.BOLD, 14));
		panel_4.add(btnClose);
		
		table = new JTable();
		CourseTableModel model = new CourseTableModel(courselist);
		table.setModel(model);
		table.getTableHeader().setFont(new Font("verdana",Font.BOLD,12));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JScrollPane scrollPane = new JScrollPane(table);
		panel.add(scrollPane, BorderLayout.CENTER);
		table.setFillsViewportHeight(true);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(204, 255, 204));
		tabbedPane.addTab("Faculty", null, panel_1, null);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_5 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_5.getLayout();
		flowLayout_1.setAlignment(FlowLayout.RIGHT);
		panel_1.add(panel_5, BorderLayout.SOUTH);
		
		JButton btnUpdate_1 = new JButton("Update");
		btnUpdate_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					int row = table_1.getSelectedRow();
					if(row<0){
						throw new Exception();
					}
					try {
						UpdateFaculty dialog = new UpdateFaculty(row,courseDAO.getFacultyList().get(row));
						dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
						dialog.setVisible(true);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}catch(Exception e1){
					JOptionPane.showMessageDialog(null, "Please Select a row", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnUpdate_1.setFont(new Font("Verdana", Font.BOLD, 14));
		panel_5.add(btnUpdate_1);
		
		JButton btnAdd_1 = new JButton("Add");
		btnAdd_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					AddFaculty dialog = new AddFaculty();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnAdd_1.setFont(new Font("Verdana", Font.BOLD, 14));
		panel_5.add(btnAdd_1);
		
		JButton btnClose_1 = new JButton("Close");
		btnClose_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnClose_1.setFont(new Font("Verdana", Font.BOLD, 14));
		panel_5.add(btnClose_1);
		
		JPanel panel_6 = new JPanel();
		panel_1.add(panel_6, BorderLayout.NORTH);
		
		textField_1 = new JTextField();
		panel_6.add(textField_1);
		textField_1.setColumns(30);
		
		JButton btnSearch_1 = new JButton("Search");
		btnSearch_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = textField_1.getText();
				if(name.trim().length()!=0){
					try {
						table_1.setModel(new FacultyTableModel(courseDAO.search_faculty(name)));
					} catch (ClassNotFoundException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btnSearch_1.setFont(new Font("Verdana", Font.BOLD, 14));
		panel_6.add(btnSearch_1);
		
		JButton btnShowAll_1 = new JButton("Show All");
		btnShowAll_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					table_1.setModel(new FacultyTableModel(courseDAO.getFacultyList()));
				} catch (ClassNotFoundException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnShowAll_1.setFont(new Font("Verdana", Font.BOLD, 14));
		panel_6.add(btnShowAll_1);
		
		table_1 = new JTable();
		FacultyTableModel model_1 = new FacultyTableModel(facultylist);
		table_1.setModel(model_1);
		table_1.getTableHeader().setFont(new Font("verdana",Font.BOLD,12));
		table_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JScrollPane scrollPane_1 = new JScrollPane(table_1);
		panel_1.add(scrollPane_1, BorderLayout.CENTER);
		table_1.setFillsViewportHeight(true);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Participants", null, panel_2, null);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_7 = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panel_7.getLayout();
		flowLayout_2.setAlignment(FlowLayout.RIGHT);
		panel_2.add(panel_7, BorderLayout.SOUTH);
		
		JButton btnUpdate_2 = new JButton("Update");
		btnUpdate_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					int row = table_2.getSelectedRow();
					if(row<0){
						throw new Exception();
					}
					try {
						UpdateParticipant dialog = new UpdateParticipant(row,courseDAO.getParticipantList().get(row));
						dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
						dialog.setVisible(true);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}catch(Exception e1){
					JOptionPane.showMessageDialog(null, "Please select a row", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnUpdate_2.setFont(new Font("Verdana", Font.BOLD, 14));
		panel_7.add(btnUpdate_2);
		
		JButton btnDelete_2 = new JButton("Delete");
		btnDelete_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
				int row = table_2.getSelectedRow();
				if(row<0){
					throw new Exception();
				}
				courseDAO.delete_the_participant(row);
				JOptionPane.showMessageDialog(null, "The Participant has been deleted, Please click show all to see all participants");
				}catch(Exception e1){
					JOptionPane.showMessageDialog(null, "Please select a row", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnDelete_2.setFont(new Font("Verdana", Font.BOLD, 14));
		panel_7.add(btnDelete_2);
		
		JButton btnAdd_2 = new JButton("Add");
		btnAdd_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					AddParticipant dialog = new AddParticipant();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnAdd_2.setFont(new Font("Verdana", Font.BOLD, 14));
		panel_7.add(btnAdd_2);
		
		JButton btnClose_2 = new JButton("Close");
		btnClose_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnClose_2.setFont(new Font("Verdana", Font.BOLD, 14));
		panel_7.add(btnClose_2);
		
		JPanel panel_8 = new JPanel();
		panel_2.add(panel_8, BorderLayout.NORTH);
		

		table_2 = new JTable();
		ParticipantTableModel model_2 = new ParticipantTableModel(participantlist);
		table_2.setModel(model_2);
		table_2.getTableHeader().setFont(new Font("verdana",Font.BOLD,12));
		table_2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JScrollPane scrollPane_2 = new JScrollPane(table_2);
		panel_2.add(scrollPane_2, BorderLayout.CENTER);
		table_2.setFillsViewportHeight(true);
		
		textField_2 = new JTextField();
		panel_8.add(textField_2);
		textField_2.setColumns(30);
		
		JButton btnSearch_2 = new JButton("Search");
		btnSearch_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = textField_2.getText();
				if(name.trim().length()!=0){
					try {
						table_2.setModel(new ParticipantTableModel(courseDAO.search_participant(name)));
					} catch (ClassNotFoundException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btnSearch_2.setFont(new Font("Verdana", Font.BOLD, 14));
		panel_8.add(btnSearch_2);
		

		
		JButton btnShowAll_2 = new JButton("Show All");
		btnShowAll_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					table_2.setModel(new ParticipantTableModel(courseDAO.getParticipantList()));
				} catch (ClassNotFoundException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnShowAll_2.setFont(new Font("Verdana", Font.BOLD, 14));
		panel_8.add(btnShowAll_2);
		
	}

}

