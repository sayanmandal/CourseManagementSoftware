package CourseRegulatorNew;

import java.util.List;

import javax.swing.table.AbstractTableModel;

public class InstructorTableModel extends AbstractTableModel{

String ColumnName[] = {"Name","Department","Address","Mobile","Email Address","Select"};
Boolean[] data;
	
	private List<Faculty> thelist;
	
	InstructorTableModel(List<Faculty> list){
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
		Faculty thefaculty = thelist.get(row);
		switch(col){
		case 0:
			return thefaculty.getName();
		case 1:
			return thefaculty.getDepartment();
		case 2:
			return thefaculty.getAddress();
		case 3:
			return thefaculty.getMobile();
		case 4:
			return thefaculty.getEmailAddress();
		case 5:
			return data[row];
		default:
			return thefaculty;
		}
	}

	@Override
	public void setValueAt(Object val, int row, int col) {
		data[row] = (Boolean) val;
		fireTableCellUpdated(row,col);
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
	public boolean isCellEditable(int row, int col) {
		return (col == 5);
	}

	@Override
	public String getColumnName(int col) {
		return ColumnName[col];
	}

}
