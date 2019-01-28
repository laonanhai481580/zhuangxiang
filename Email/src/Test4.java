

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.RandomAccessFile;

public class Test4 {
   static int  size = 1;// 主要是为了控制循环的次数，因为是定时刷，每次刷的文件行数可能不一样
   static long chars = 0;// chars指的是字符数
   private static  ScheduledExecutorTest steg;

	/**
	 * 读取文件内容
	 * 
	 * @param fileName
	 */
	public static String readANDwrite(String fileName) {
		// 一次session的访问记录集合
		File file = new File(fileName);
		// java提供的一个可以分页读取文件的类,此类的实例支持对随机访问文件的读取和写入
		RandomAccessFile rf = null;
		String tempString = null;
		steg =new ScheduledExecutorTest();
		int j = 0;
		try {
			while (true) {
				// 初始化RandomAccessFile，参数一个为文件路径，一个为权限设置，这点与Linux类似，r为读，w为写
				rf = new RandomAccessFile(fileName, "rw");
				// 设置到此文件开头测量到的文件指针偏移量，在该位置发生下一个读取或写入操作
				rf.seek(chars);
				// 获取文件的行数
				int fileSize = getTotalLines(file);
				for (int i = size - 1; i < fileSize; i++) {// 从上一次读取结束时的文件行数到本次读取文件时的总行数中间的这个差数就是循环次数
					// 一行一行读取
					tempString = rf.readLine();
					// System.out.println(tempString);
					String strs = "Cannot get a connection, pool error Timeout waiting for idle object";
					if (tempString != null && tempString.indexOf(strs) != -1) {
						System.out.println("qis连接出现故障" + tempString + j++);
						steg.timerTwo();
						try {
							Thread.sleep(61000);
						} catch (InterruptedException e){
							e.printStackTrace();
						}
					}
				}
				// 返回此文件中的当前偏移量。
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

	// 获取文件的行数
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
