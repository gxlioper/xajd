
package xgxt.wjcf.wjcfutils;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.actions.DispatchAction;

import xgxt.action.Base;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: Υ�ʹ���ͨ��Action</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: ����</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-06-06</p>
 */
public class WjcfActionUtils extends DispatchAction {

	/**
	 * Υ�ʹ���ͨ�÷���������ҳ��������б�
	 * @param request
	 * @param commenBean
	 * @throws Exception
	 */
	public  void appendProperties(HttpServletRequest request, WjcfCommenBean commenBean) throws Exception {
		String xy = "";
		String zy = "";
		String njLocal = commenBean.getNj();
		xy = xy==null ? "": commenBean.getXydm(); 
		zy = zy==null ? "": commenBean.getZydm(); 
		njLocal = commenBean.getNj() == null ? "": commenBean.getNj();
		String zyKey = xy==null ? "":xy; 
		String bjKey = xy+"!!"+zy+"!!"+njLocal;
		/*String realTable = "cjb";
		String tableName = "view_cjb";*/
		String userType = request.getSession().getAttribute("userType").toString();
		String userName = request.getSession().getAttribute("userName").toString();
		request.setAttribute("writeAble", "yes");//�ж��û���дȨ
		/*request.setAttribute("tableName", tableName);//��ѯ��Ϊ��ͼ��
		request.setAttribute("realTable", realTable);//����
*/		request.setAttribute("xqList", Base.getXqList());//ѧ���б�
		request.setAttribute("xnList", Base.getXnndList());//ѧ���б�
		request.setAttribute("njList", Base.getNjList());//�꼶�б�
		request.setAttribute("xyList", Base.getXyList());//ѧԺ�б�
		request.setAttribute("zyList", Base.getZyMap().get(zyKey));//רҵ�б�
		request.setAttribute("bjList", Base.getBjMap().get(bjKey));//�༶�б�
		request.setAttribute("userType", userType);//�û�����
		request.setAttribute("userName", userName);//�û���
	}
}
