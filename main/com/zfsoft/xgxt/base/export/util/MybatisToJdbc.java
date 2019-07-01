/**
 * @部门:学工产品事业部
 * @日期：2013-11-27 上午10:01:49 
 */
package com.zfsoft.xgxt.base.export.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 快速转换sql为 字符串格式
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： 张昌路[工号:982]
 * @时间： 2013-11-27 上午10:01:49
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class MybatisToJdbc {
	public void getSimpleSql() {
		StringBuffer newStr = new StringBuffer();
		StringBuffer sb = new StringBuffer();
		try {
			System.out
					.println("**********************请在控制台输入要转换的语句*************************");
			System.out
					.println("**********************每句语句结束请输入回车键*************************");
			System.out
					.println("**********************输入完毕请输入 end 并回车获取转换后的sql*************************");
			BufferedReader br = new BufferedReader(new InputStreamReader(
					System.in));
			String tx = null;
			while (!"end".equals((tx = br.readLine()))) {
				sb.append("sb.append(\"");
				sb.append(tx);
				sb.append("\");");
			}
		} catch (Exception e) {
			// TODO 自动生成 catch 块
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
