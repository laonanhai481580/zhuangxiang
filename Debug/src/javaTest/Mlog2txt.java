package javaTest;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Date;

public class Mlog2txt {
	public static void main(String[] args) {
		try {
			PrintStream mytxt = new PrintStream("./log.txt");
			PrintStream out = System.out;
			System.setOut(mytxt);
			System.out.println("�ĵ�ִ�е�������:"+new Date());
			System.setOut(out);
			System.out.println("���ڱ������");
		} catch (FileNotFoundException e){
			e.printStackTrace();
		}

	}
}
