/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-11-27 ����10:01:49 
 */
package com.zfsoft.xgxt.base.export.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ����ת��sqlΪ �ַ�����ʽ
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2013-11-27 ����10:01:49
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class MybatisToJdbc {
	public void getSimpleSql() {
		StringBuffer newStr = new StringBuffer();
		StringBuffer sb = new StringBuffer();
		try {
			System.out
					.println("**********************���ڿ���̨����Ҫת�������*************************");
			System.out
					.println("**********************ÿ��������������س���*************************");
			System.out
					.println("**********************������������� end ���س���ȡת�����sql*************************");
			BufferedReader br = new BufferedReader(new InputStreamReader(
					System.in));
			String tx = null;
			while (!"end".equals((tx = br.readLine()))) {
				sb.append("sb.append(\"");
				sb.append(tx);
				sb.append("\");");
			}
		} catch (Exception e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}
		if (sb.length() > 0) {
			newStr.append("StringBuffer sb=new StringBuffer();");
			newStr.append(sb);
		}

		System.out.println(newStr.toString());
	}

	public static void main(String[] args) {
		new MybatisToJdbc().getSimpleSql();
	}
}
