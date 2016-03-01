package CourseRegulatorNew;

import java.util.List;

import javax.swing.table.AbstractTableModel;

public class ChooseParticipantTableModel extends AbstractTableModel{
	
	String ColumnName[] = {"Name","Address","Mobile No.","Organization","Email Address","Select"};
	private List<Participant> thelist;
	 Boolean[] data;
	
	ChooseParticipantTableModel(List<Participant> list){
		thelist = list;
		data = new Boolean[thelist.size()];
		for(int i = 0 ; i < thelist.size() ; i++){
			data[i] = Boolean.FALSE;
		}
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
		case 5:
			return data[row];
		default:
			return theparticipant;
		}
	}

	@Override
	public void setValueAt(Object val, int row, int col) {
		data[row] = (Boolean) val;
		fireTableCellUpdated(row,col);
	}

	@Override
	public boolean isCellEditable(int row, int col) {
		return (col == 5);
	}

	@Override
	public Class<?> getColumnClass(int col) {
		if(col == 5){
			return Boolean.class;
		}else{
			return String.class;
		}
	}

	@Override
	public String getColumnName(int col) {
		return ColumnName[col];
	}


}

