import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ENROLMENT")
public class Enrolment {

	@Id 
	@Column(name = "CLASS_CODE")
	private int classCode;

	@Column(name = "STU_ID")
	private int stuId;
	
	@Column(name = "EN_GRADE")
	private String enGrade;
	
	@Column(name = "EN_COMMENTS")
	private String enComments;
	
	public Enrolment(){}

	public int getClassCode() {
		return classCode;
	}

	public void setClassCode(int classCode) {
		this.classCode = classCode;
	}

	public int getStuId() {
		return stuId;
	}

	public void setStuId(int stuId) {
		this.stuId = stuId;
	}

	public String getEnGrade() {
		return enGrade;
	}

	public void setEnGrade(String enGrade) {
		this.enGrade = enGrade;
	}

	public String getEnComments() {
		return enComments;
	}

	public void setEnComments(String enComments) {
		this.enComments = enComments;
	};
	
	
}