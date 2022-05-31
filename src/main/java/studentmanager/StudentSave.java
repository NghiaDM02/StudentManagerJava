package studentmanager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.List;

/**
 * StudentSave
 */
public class StudentSave {

	public String filePath;

	public StudentSave(String filePath) {
        this.filePath = filePath;
    }

    public List<Student> read(List<Student> students) {
		try {
			InputStream is = new FileInputStream(new File(filePath));
			ObjectInputStream ois = new ObjectInputStream(is);

			students = (List<Student>) ois.readObject();

			ois.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return students;
	}

	public void write(List<Student> students) {
		try {
			OutputStream os = new FileOutputStream(new File(filePath));
			ObjectOutputStream oos = new ObjectOutputStream(os);

			oos.writeObject(students);

			oos.flush();
			oos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
