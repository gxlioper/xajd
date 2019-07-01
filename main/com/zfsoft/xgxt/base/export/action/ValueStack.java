/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-11-28 ����11:35:34 
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
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������:ģ��struts2
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2013-11-28 ����11:35:34
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
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
	 * @����:��ȡ·��
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-12-2 ����10:27:04
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param path
	 * @return String ��������
	 */
	public static String getBasePath(String path) {
		if (null == servlet) {
			throw new RuntimeException(
					"action��������ValueStack.servlet,��������ʹ�ô˷�����");
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
				throw new RuntimeException("������ת��json����");
			}
		} else {
			request.setAttribute(str, o);
		}
	}

	/**
	 * 
	 * @����:ʹ�����������
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-12-2 ����09:33:02
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param file
	 *            void ��������
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
			throw new RuntimeException("�ļ�·�������޷���ȡ��", e);
		} catch (IOException e) {
			throw new RuntimeException("�ļ�����ת��Ϊ�����������", e);
		}
	}
}
