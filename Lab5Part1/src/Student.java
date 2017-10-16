import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "STUDENT")
public class Student {
	@Id 
	@Column(name = "STU_ID")
	private int stuId;

	@Column(name = "STU_LNAME")
	private String stuLname;
	
	@Column(name = "STU_FNAME")
	private String stuFname;
	
	@Column(name = "STU_ADD")
	private String stuAdd;

	@Column(name = "STU_AGE")
	private int stuAge;
	
	@Column(name = "STU_PHONE")
	private int stuPhone;
	
	@Column(name = "STU_EMAIL")
	private String stuEmail;
	
	public Student(){}

	public int getStuId() {
		return stuId;
	}

	public void setStuId(int stuId) {
		this.stuId = stuId;
	}

	public String getStuLname() {
		return stuLname;
	}

	public void setStuLname(String stuLname) {
		this.stuLname = stuLname;
	}

	public String getStuFname() {
		return stuFname;
	}

	public void setStuFname(String stuFname) {
		this.stuFname = stuFname;
	}

	public String getStuAdd() {
		return stuAdd;
	}

	public void setStuAdd(String stuAdd) {
		this.stuAdd = stuAdd;
	}

	public int getStuAge() {
		return stuAge;
	}

	public void setStuAge(int stuAge) {
		this.stuAge = stuAge;
	}

	public int getStuPhone() {
		return stuPhone;
	}

	public void setStuPhone(int stuPhone) {
		this.stuPhone = stuPhone;
	}

	public String getStuEmail() {
		return stuEmail;
	}

	public void setStuEmail(String stuEmail) {
		this.stuEmail = stuEmail;
	};
	
	
}