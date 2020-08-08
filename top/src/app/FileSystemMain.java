package app;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component("fsm")
public class FileSystemMain {

	@Autowired
	private Configuration conf;

	public static void main(String[] args) {
		// Component: �ڵ��޸� �Ҵ�
		// Autowired : �ּҰ� ����

		ApplicationContext app = new ClassPathXmlApplicationContext("myspring.xml");
		FileSystemMain fm = (FileSystemMain) app.getBean("fsm");
		try {
			FileSystem fs = FileSystem.get(fm.conf);
			fs.copyFromLocalFile(new Path("/hennie.txt"), new Path("/usr/local/hennie.txt"));
			fs.close();

			System.out.println("�������ۿϷ�");
			fs.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("���۽���!");
		}

	}
}