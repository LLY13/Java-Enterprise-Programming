import javax.persistence.*;

@Entity
@Table(name = "CLASS")
public class Class {
	@Id 
	@Column(name = "CLASS_CODE")
	private int classCode;

	@Column(name = "CRS_CODE")
	private int crsCode;
	
	@Column(name = "CLASS_SECTION")
	private String classSection;
	
	@Column(name = "CLASS_TIME")
	private String classtime;

	@Column(name = "CLASS_START_DATE")
	private String classStartDate;
	
	@Column(name = "CLASS_ROOM")
	private String classRoom;
	
	public Class(){}

	public int getClassCode() {
		return classCode;
	}

	public void setClassCode(int classCode) {
		this.classCode = classCode;
	}

	public int getCrsCode() {
		return crsCode;
	}

	public void setCrsCode(int crsCode) {
		this.crsCode = crsCode;
	}

	public String getClassSection() {
		return classSection;
	}

	public void setClassSection(String classSection) {
		this.classSection = classSection;
	}

	public String getClasstime() {
		return classtime;
	}

	public void setClasstime(String classtime) {
		this.classtime = classtime;
	}

	public String getClassStartDate() {
		return classStartDate;
	}

	public void setClassStartDate(String classStartDate) {
		this.classStartDate = classStartDate;
	}

	public String getClassRoom() {
		return classRoom;
	}

	public void setClassRoom(String classRoom) {
		this.classRoom = classRoom;
	};
	
	
}
	