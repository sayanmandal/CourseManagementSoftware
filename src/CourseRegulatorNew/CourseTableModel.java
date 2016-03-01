package CourseRegulatorNew;

import java.util.List;

import javax.swing.table.AbstractTableModel;

public class CourseTableModel extends AbstractTableModel{
	
	String ColumnName[] = {"Name","Fee","Start Date","Duration","Faculty"};
	private List<Course> thelist;
	
	CourseTableModel(List<Course> list){
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
		Course thecourse = thelist.get(row);
		switch(col){
		case 0:
			return thecourse.getName();
		case 1:
			return thecourse.getFee();
		case 2:
			return thecourse.getStartdate();
		case 3:
			return thecourse.getDuration();
		case 4:
			return thecourse.getFaculty().getName();
		case 5:
			return thecourse.getInstructorlist();
		case 6:
			return thecourse.getParticipantlist();
		default:
			return thecourse;
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

