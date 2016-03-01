package CourseRegulatorNew;

import java.util.List;

import javax.swing.table.AbstractTableModel;

public class FacultyTableModel extends AbstractTableModel{
	
	String ColumnName[] = {"Name","Department","Address","Mobile","Email Address"};
	
	private List<Faculty> thelist;
	
	FacultyTableModel(List<Faculty> list){
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
		default:
			return thefaculty;
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
