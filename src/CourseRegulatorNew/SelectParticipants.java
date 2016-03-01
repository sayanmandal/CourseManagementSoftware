package CourseRegulatorNew;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class SelectParticipants extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTable table;
	private CourseDAO courseDAO = new CourseDAO();
	private ChooseParticipantTableModel model ;
	private String text = " ";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			SelectParticipants dialog = new SelectParticipants(null);
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
	public SelectParticipants(Course course) throws ClassNotFoundException, IOException {
		setTitle("Update ParticipantList");
		setBounds(100, 100, 739, 380);
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
								table.setModel(new ChooseParticipantTableModel(courseDAO.search_participant(name)));
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
							table.setModel(new ChooseParticipantTableModel(courseDAO.getParticipantList()));
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
			model = new ChooseParticipantTableModel(courseDAO.getParticipantList());
			table.setModel(model);
			
			if(course!=null){
				for(int i = 0 ; i < courseDAO.getParticipantList().size() ; i++){
					for(int j = 0 ; j < course.getParticipantlist().size() ; j++){
					if(courseDAO.getParticipantList().get(i).getId() == course.getParticipantlist().get(j).getId()){
						model.setValueAt(true, i, 5);
					}	
					}
				}
				}
			
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
						text = " ";
						try{
							if(UpdateCourse.updatedParticipantList.size()!=0){
								for(int i = 0 ; i < UpdateCourse.updatedParticipantList.size() ; ){
									UpdateCourse.updatedParticipantList.remove(0);
								}
							}
							for(int i = 0 ; i < model.getRowCount() ; i++){
								if(model.data[i]){
									try {
										UpdateCourse.updatedParticipantList.add(courseDAO.getParticipantList().get(i));
									} catch (ClassNotFoundException | IOException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
								}
							}
							if(UpdateCourse.updatedParticipantList.size()>5){
								throw new Exception();
							}
							for(int i = 0 ; i < UpdateCourse.updatedParticipantList.size() ; i++){
								 text = text+" " +UpdateCourse.updatedParticipantList.get(i).getName()+",";
							}
							UpdateCourse.textField_5.setText(text);
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
