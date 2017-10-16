import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ManageDB {
	private static SessionFactory factory; 
	public static void main(String[] args) {
		factory = new Configuration().configure().buildSessionFactory();
	}
}
