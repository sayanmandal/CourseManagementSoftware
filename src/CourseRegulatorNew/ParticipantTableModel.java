package CourseRegulatorNew;

import java.util.List;

import javax.swing.table.AbstractTableModel;

public class ParticipantTableModel extends AbstractTableModel{
	
	String ColumnName[] = {"Name","Address","Mobile No.","Organization","Email Address"};
	private List<Participant> thelist;
	
	ParticipantTableModel(List<Participant> list){
		thelist = list;
	}

	@Override
	public int getColumnCount() {
		return ColumnName.length;
	}

	@Override
	public int getRowCount() {
		return thelist.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		Participant theparticipant = thelist.get(row);
		switch(col){
		case 0:
			return theparticipant.getName();
		case 1:
			return theparticipant.getAddress();
		case 2:
			return theparticipant.getMobilenumber();
		case 3:
			return theparticipant.getOrganization();
		case 4:
			return theparticipant.getEmailaddress();
		default:
			return theparticipant;
		}
	}

	@Override
	public Class<?> getColumnClass(int col) {
		return getValueAt(0,col).getClass();
	}

	@Override
	public String getColumnName(int col) {
		return ColumnName[col];
	}

}
