package CourseRegulatorNew;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class CourseDAO {
	
	RandomAccessFile file;
	RandomAccessFile file_1;
	RandomAccessFile file_2;
	
	List<Course> CourseList = new ArrayList<Course>();
	List<Faculty> FacultyList = new ArrayList<Faculty>();
	List<Participant> ParticipantList = new ArrayList<Participant>();
	List<Course> courselist = new ArrayList<Course>();
	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	
	
	public static void main(String[] args) throws ClassNotFoundException, IOException, ParseException{
		
	}
	
	
	
	@SuppressWarnings({ "unchecked" })
	void Read_from_Course() throws IOException, ClassNotFoundException{
		file = new RandomAccessFile("CourseData.txt","rw");
		FileInputStream fin = new FileInputStream("CourseData.txt");
		if(file.length()!=0){
		ObjectInputStream ois = new ObjectInputStream(fin);
		CourseList = (ArrayList<Course>)ois.readObject();
		ois.close();
		}
		fin.close();
	}
	void write_to_Course() throws IOException{
		
		FileOutputStream fos = new FileOutputStream("CourseData.txt");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(CourseList);
		oos.close();
		fos.close();
	}
	
	
	@SuppressWarnings({ "unchecked" })
	 void Read_from_Faculty() throws IOException, ClassNotFoundException{
		file_1 = new RandomAccessFile("FacultyData.txt","rw");
		FileInputStream fin = new FileInputStream("FacultyData.txt");
		if(file_1.length()!=0){
		ObjectInputStream ois = new ObjectInputStream(fin);
		FacultyList = (ArrayList<Faculty>)ois.readObject();
		ois.close();
		}
		fin.close();
	
	}
	
	void write_to_Faculty() throws IOException{
		
		FileOutputStream fos = new FileOutputStream("FacultyData.txt");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(FacultyList);
		oos.close();
		fos.close();
	}
	

	@SuppressWarnings({ "unchecked" })
	void Read_from_Participant() throws IOException, ClassNotFoundException{
		file_2 = new RandomAccessFile("ParticipantData.txt","rw");
		FileInputStream fin = new FileInputStream("ParticipantData.txt");
		if(file_2.length()!=0){
		ObjectInputStream ois = new ObjectInputStream(fin);
		ParticipantList = (ArrayList<Participant>)ois.readObject();
		ois.close();
		}
		fin.close();
	}
	
	void write_to_participant() throws IOException{
		
		FileOutputStream fos = new FileOutputStream("ParticipantData.txt");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(ParticipantList);
		oos.close();
		fos.close();
	}
	
	List<Course> getCourseList() throws ClassNotFoundException, IOException, ParseException{
		Read_from_Course();
		Calendar cal = Calendar.getInstance();
		if(courselist.size()==0){
		for(int i = 0 ; i < CourseList.size(); i++){
			Date date = formatter.parse(CourseList.get(i).getStartdate());
			cal.add(Calendar.YEAR, -5);
			if(cal.getTime().before(date)){
				courselist.add(CourseList.get(i));
			}else{
				CourseList.remove(i);
				i--;
			}
		}}
		write_to_Course();
		return courselist;
		
	}
	
	List<Faculty> getFacultyList() throws ClassNotFoundException, IOException{
		Read_from_Faculty();
		return FacultyList;
	}
	
	List<Participant> getParticipantList() throws ClassNotFoundException, IOException{
		Read_from_Participant();
		return ParticipantList;
	}
	
	int max_id_course() throws ClassNotFoundException, IOException{
		Read_from_Course();
		int max = 0;
		for(int i = 0 ; i < CourseList.size(); i++){
			Course thecourse = CourseList.get(i);
			if(thecourse.getId()>max){
				max = thecourse.getId();
			}
		}
		return max;
	}
	
	int max_id_faculty() throws ClassNotFoundException, IOException{
		Read_from_Faculty();
		int max = 0 ;
		for(int  i = 0 ; i < FacultyList.size() ; i++){
			Faculty faculty = FacultyList.get(i);
			if(faculty.getId()>max){
				max = faculty.getId();
			}
		}
		return max;
	}
	
	int max_id_participant() throws ClassNotFoundException, IOException{
		Read_from_Participant();
		int max = 0 ;
		for(int i = 0 ; i < ParticipantList.size() ; i++){
			Participant theparticipant = ParticipantList.get(i);
			if(max < theparticipant.getId()){
				max = theparticipant.getId();
			}
		}
		return max;
	}
	
	void add_Course(Course course) throws ClassNotFoundException, IOException{
		Read_from_Course();
		CourseList.add(course);
		write_to_Course();
	}
	
	void add_Faculty(Faculty faculty) throws ClassNotFoundException, IOException{
		Read_from_Faculty();
		FacultyList.add(faculty);
		write_to_Faculty();
	}
	
	void add_Participant(Participant participant) throws ClassNotFoundException, IOException{
		Read_from_Participant();
		ParticipantList.add(participant);
		write_to_participant();
	}
	
	List<Course> search_course(String name) throws ClassNotFoundException, IOException{
		Read_from_Course();
		List<Course> templist = new ArrayList<Course>();
		for(int i = 0 ; i < CourseList.size() ; i++){
			if(CourseList.get(i).getName().contains(name)){
				templist.add(CourseList.get(i));
			}
		}
		return templist;
	}
	
	List<Participant> search_participant(String name) throws ClassNotFoundException, IOException{
		Read_from_Participant();
		List<Participant> templist = new ArrayList<Participant>();
		for(int i = 0 ; i < ParticipantList.size(); i++){
			if(ParticipantList.get(i).getName().contains(name)){
				templist.add(ParticipantList.get(i));
			}
		}
		return templist;
	}
	
	
	List<Faculty> search_faculty(String name) throws ClassNotFoundException, IOException{
		Read_from_Faculty();
		List<Faculty> templist = new ArrayList<Faculty>();
		for(int i = 0 ; i < FacultyList.size(); i++){
			if(FacultyList.get(i).getName().contains(name)){
				templist.add(FacultyList.get(i));
			}
		}
		return templist;
	}
	
	void delete_the_course(int index) throws IOException, ClassNotFoundException, ParseException{
		Read_from_Course();
		CourseList.remove(index);
		write_to_Course();
		}
	
	void delete_the_participant(int index) throws ClassNotFoundException, IOException{
		Read_from_Course();
		Read_from_Participant();
		Participant part = ParticipantList.get(index);
		ParticipantList.remove(index);
		for(int i = 0 ; i < CourseList.size() ; i++){
			Course tempcourse = CourseList.get(i);
			for(int j = 0 ; j < tempcourse.getParticipantlist().size() ; j++){
				if(tempcourse.getParticipantlist().get(j).getId() == part.getId()){
					tempcourse.getParticipantlist().remove(j);
				}
			}
			CourseList.set(i, tempcourse);
		}
		write_to_Course();
		write_to_participant();
	}
	
	
	void request_for_faculty_delete() throws IOException, ClassNotFoundException{
		
	}
	
	void update_the_course(int index,Course course) throws IOException, ClassNotFoundException{
		Read_from_Course();
		CourseList.set(index, course);
		write_to_Course();
		}
			
	
	void update_the_faculty(int index , Faculty faculty ) throws IOException, ClassNotFoundException{
		Read_from_Faculty();
		Read_from_Course();
		FacultyList.set(index, faculty);
		for(int i = 0 ; i < CourseList.size(); i++){
			Course tempcourse = CourseList.get(i);
			if(tempcourse.getFaculty().getId() == faculty.getId()){
				tempcourse.setFaculty(faculty);
			}
			for(int j = 0 ; j < tempcourse.getInstructorlist().size() ; j++){
				if(tempcourse.getInstructorlist().get(j).getId() == faculty.getId()){
					tempcourse.getInstructorlist().set(j, faculty);
				}
			}
			CourseList.set(i, tempcourse);
		}
		write_to_Faculty();
		write_to_Course();
	}
	
	void update_the_participant(int index , Participant participant) throws IOException, ClassNotFoundException{
		Read_from_Participant();
		Read_from_Course();
		ParticipantList.set(index, participant);
		for(int i = 0 ; i < CourseList.size() ; i++){
			Course tempcourse = CourseList.get(i);
			for(int j = 0 ; j < tempcourse.getParticipantlist().size() ; j++){
				if(tempcourse.getParticipantlist().get(j).getId() == participant.getId()){
					tempcourse.getParticipantlist().set(j, participant);
				}
			}
			CourseList.set(i, tempcourse);
		}
		write_to_participant();
		write_to_Course();
	}

}
