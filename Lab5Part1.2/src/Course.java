// default package
// Generated 2017-5-4 21:20:27 by Hibernate Tools 3.4.0.CR1

/**
 * Course generated by hbm2java
 */
public class Course implements java.io.Serializable {

	private int crsCode;
	private String crsCredit;
	private String crsDescription;

	public Course() {
	}

	public Course(int crsCode) {
		this.crsCode = crsCode;
	}

	public Course(int crsCode, String crsCredit, String crsDescription) {
		this.crsCode = crsCode;
		this.crsCredit = crsCredit;
		this.crsDescription = crsDescription;
	}

	public int getCrsCode() {
		return this.crsCode;
	}

	public void setCrsCode(int crsCode) {
		this.crsCode = crsCode;
	}

	public String getCrsCredit() {
		return this.crsCredit;
	}

	public void setCrsCredit(String crsCredit) {
		this.crsCredit = crsCredit;
	}

	public String getCrsDescription() {
		return this.crsDescription;
	}

	public void setCrsDescription(String crsDescription) {
		this.crsDescription = crsDescription;
	}

}
