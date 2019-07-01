package com.zfsoft.xgxt.base.util.dbf;

import java.util.ArrayList;
import java.util.List;

public class DBFTest {

	/**
	 * 读DBF文件
	 */
	private static void readDBF() {
		try {
			System.out.println("正在读取文件！");

			DBFReader dbfreader = new DBFReader("d:/aa.dbf");
			for (int b = 0; b < dbfreader.getFieldCount(); b++) {
				if (b > 0)
					System.out.print(",");
				System.out.print(dbfreader.getField(b).getName().trim()
						.toUpperCase());

				if (b == (dbfreader.getFieldCount() - 1))
					System.out.print("\n");

			}
			for (int i = 0; dbfreader.hasNextRecord(); i++) {
				String aobj[] = dbfreader.nextRecordString();
				for (int b = 0; b < dbfreader.getFieldCount(); b++) {
					if (b > 0)
						System.out.print(",");
					System.out.print(aobj[b].trim());

					if (b == (dbfreader.getFieldCount() - 1))
						System.out.print("\n");

				}
			}
			System.out.println("读取文件成功！");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 读DBF文件
	 */
	private static void readDBF2() {
		try {
			System.out.println("正在读取文件！");
			List<String[]> list = new ArrayList<String[]>();
			DBFReader dbfreader = new DBFReader("d:/aa.dbf");

			String[] dataStrs = new String[dbfreader.getFieldCount()];
			// 表头字段
			for (int b = 0; b < dbfreader.getFieldCount(); b++) {
				dataStrs[b] = dbfreader.getField(b).getName();
			}
			list.add(dataStrs);
			for (int i = 0; dbfreader.hasNextRecord(); i++) {
				String[] aobj = dbfreader.nextRecordString();
				list.add(aobj);
			}
			System.out.println("读取文件成功！");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 写DBF文件
	 */
	private static void writeDBF() {

		try {
			JDBField[] ajdbfield = new JDBField[4];
			ajdbfield[0] = new JDBField("字段1afas  ", 'C', 255, 0);
			ajdbfield[1] = new JDBField("ieldajdbfield段字段", 'C', 255, 0);
			ajdbfield[2] = new JDBField("fidfleadffdad3", 'C', 20, 0);
			ajdbfield[3] = new JDBField("fildfed4", 'C', 20, 0);

			System.out.println("正在生成DBF字段！");
			/*
			 * 生成 DBF 文件
			 */
			DBFWriter dbfwriter = new DBFWriter("d:/aa.dbf", ajdbfield);
			Object[] aobj = new Object[4];
			aobj[0] = "bfi据！";
			aobj[1] = "1";
			aobj[2] = "1";
			aobj[3] = "1";

			System.out.println("正在写入数据！");

			dbfwriter.addRecord(aobj);
			dbfwriter.addRecord(aobj);
			dbfwriter.close();

			System.out.println("执行成功！");
		} catch (JDBFException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		writeDBF();
		readDBF2();
	}
}
