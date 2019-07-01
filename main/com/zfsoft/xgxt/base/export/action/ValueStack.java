/**
 * @部门:学工产品事业部
 * @日期：2013-11-28 上午11:35:34 
 */
package com.zfsoft.xgxt.base.export.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionServlet;

import com.zfsoft.xgxt.base.export.util.ImportConfig;

import net.sf.json.JSONObject;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称:模仿struts2
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： 张昌路[工号:982]
 * @时间： 2013-11-28 上午11:35:34
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class ValueStack {
	private HttpServletRequest request;
	private HttpServletResponse response;
	public static ActionServlet servlet;
	public static final String JSON = "json";

	public ValueStack(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
	}

	/**
	 * 
	 * @描述:获取路径
	 * @作者：张昌路[工号：982]
	 * @日期：2013-12-2 上午10:27:04
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param path
	 * @return String 返回类型
	 */
	public static String getBasePath(String path) {
		if (null == servlet) {
			throw new RuntimeException(
					"action必须设置ValueStack.servlet,才能正常使用此方法！");
		}
		path = servlet.getServletContext().getRealPath(path);
		return path;
	}

	public void set(String str, Object o) {
		if (JSON.equalsIgnoreCase(str)) {
			JSONObject json = JSONObject.fromObject(o);
			try {
				PrintWriter pw = response.getWriter();
				// System.out.println(json.toString());
				pw.print(json.toString());
				pw.flush();
				pw.close();
			} catch (IOException e) {
				throw new RuntimeException("导入结果转换json错误！");
			}
		} else {
			request.setAttribute(str, o);
		}
	}

	/**
	 * 
	 * @描述:使用流输出附件
	 * @作者：张昌路[工号：982]
	 * @日期：2013-12-2 上午09:33:02
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param file
	 *            void 返回类型
	 */
	public void attachmentStream(File file) {
		try {
			String path = file.getPath();
			String fileName = path.substring(path.lastIndexOf("\\") + 1, path
					.length());
			InputStream is = new FileInputStream(file);
			byte[] b = new byte[2048];
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition", "attachment;filename="
					+ fileName);
			OutputStream os = response.getOutputStream();
			int len;
			while ((len = is.read(b)) > 0) {
				os.write(b, 0, len);
			}
			os.flush();
			os.close();
			is.close();
		} catch (FileNotFoundException e) {
			throw new RuntimeException("文件路径错误，无法读取！", e);
		} catch (IOException e) {
			throw new RuntimeException("文件不能转换为流进行输出！", e);
		}
	}
}
