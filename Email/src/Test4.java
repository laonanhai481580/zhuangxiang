

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.RandomAccessFile;

public class Test4 {
   static int  size = 1;// ��Ҫ��Ϊ�˿���ѭ���Ĵ�������Ϊ�Ƕ�ʱˢ��ÿ��ˢ���ļ��������ܲ�һ��
   static long chars = 0;// charsָ�����ַ���
   private static  ScheduledExecutorTest steg;

	/**
	 * ��ȡ�ļ�����
	 * 
	 * @param fileName
	 */
	public static String readANDwrite(String fileName) {
		// һ��session�ķ��ʼ�¼����
		File file = new File(fileName);
		// java�ṩ��һ�����Է�ҳ��ȡ�ļ�����,�����ʵ��֧�ֶ���������ļ��Ķ�ȡ��д��
		RandomAccessFile rf = null;
		String tempString = null;
		steg =new ScheduledExecutorTest();
		int j = 0;
		try {
			while (true) {
				// ��ʼ��RandomAccessFile������һ��Ϊ�ļ�·����һ��ΪȨ�����ã������Linux���ƣ�rΪ����wΪд
				rf = new RandomAccessFile(fileName, "rw");
				// ���õ����ļ���ͷ���������ļ�ָ��ƫ�������ڸ�λ�÷�����һ����ȡ��д�����
				rf.seek(chars);
				// ��ȡ�ļ�������
				int fileSize = getTotalLines(file);
				for (int i = size - 1; i < fileSize; i++) {// ����һ�ζ�ȡ����ʱ���ļ����������ζ�ȡ�ļ�ʱ���������м�������������ѭ������
					// һ��һ�ж�ȡ
					tempString = rf.readLine();
					// System.out.println(tempString);
					String strs = "Cannot get a connection, pool error Timeout waiting for idle object";
					if (tempString != null && tempString.indexOf(strs) != -1) {
						System.out.println("qis���ӳ��ֹ���" + tempString + j++);
						steg.timerTwo();
						try {
							Thread.sleep(61000);
						} catch (InterruptedException e){
							e.printStackTrace();
						}
					}
				}
				// ���ش��ļ��еĵ�ǰƫ������
				chars = rf.getFilePointer();
				// System.out.println("chars=" + chars);
				System.out
						.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
				size = fileSize;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (rf != null) {
				try {
					rf.close();
				} catch (IOException e1) {
				}
			}
		}
		return null;
	}

	// ��ȡ�ļ�������
	public static int getTotalLines(File file) throws IOException {
		FileReader in = new FileReader(file);
		LineNumberReader reader = new LineNumberReader(in);
		String s = reader.readLine();
		int lines = 0;
		while (s != null) {
			lines++;
			s = reader.readLine();
		}
		reader.close();
		in.close();
		return lines;
	}
	
	public static void main(String[] args) throws IOException {
		//FileOpreateUtils.timer2();
		//String name = "E:\\app1\\d\\jiangling.log";
		String name="C:\\Users\\N000226\\Desktop\\c.txt";
		readANDwrite(name);
	}
}
