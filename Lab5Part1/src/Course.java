import javax.persistence.*;

@Entity
@Table(name = "COURSE")
public class Course {
	@Id 
	@Column(name = "CRS_CODE")
	private int crsCode;

	@Column(name = "CRS_DESCRIPTION")
	private String crsDescription;
	
	@Column(name = "CRS_CREDIT")
	private String crsCredit;
	
	public Course(){}

	public int getCrsCode() {
		return crsCode;
	}

	public void setCrsCode(int crsCode) {
		this.crsCode = crsCode;
	}

	public String getCrsDescription() {
		return crsDescription;
	}

	public void setCrsDescription(String crsDescription) {
		this.crsDescription = crsDescription;
	}

	public String getCrsCredit() {
		return crsCredit;
	}

	public void setCrsCredit(String crsCredit) {
		this.crsCredit = crsCredit;
	}

	
}
