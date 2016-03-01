package CourseRegulatorNew;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ChooseParticipants extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private CourseDAO courseDAO = new CourseDAO();
	List<Participant> list;
	private JTable table;
	private ChooseParticipantTableModel model;
	private String text = " ";
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ChooseParticipants dialog = new ChooseParticipants();
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
	public ChooseParticipants() throws ClassNotFoundException, IOException {
		list = courseDAO.getParticipantList();
		setTitle("Choose Participants");
		setBounds(100, 100, 635, 421);
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
						table.setModel(new ChooseParticipantTableModel(list));
					}
				});
				panel.add(btnShowAll);
			}
		}
		{
			table = new JTable();
			model = new ChooseParticipantTableModel(list);
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
						if(AddCourse.participantlist.size()!=0){
						for(int i = 0 ; i < AddCourse.participantlist.size(); i++){
							AddCourse.participantlist.remove(i);
						}		
						}
						for(int i = 0 ; i <model.getRowCount() ; i++){
							if(model.data[i]){
								AddCourse.participantlist.add(list.get(i));
								
							}else{
								AddCourse.textField_5.setText(" ");
							}
						}
						for(int i = 0 ; i < AddCourse.participantlist.size();i++){
							text = text + AddCourse.participantlist.get(i).getName()+", ";
						}
						AddCourse.textField_5.setText(text);
						dispose();
						}catch(Exception e1){
							JOptionPane.showMessageDialog(null, "Please Choose max. 5 Participants");
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
