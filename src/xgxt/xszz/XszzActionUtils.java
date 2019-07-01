
package xgxt.xszz;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.actions.DispatchAction;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.utils.String.StringUtils;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: ѧ������ͨ��Action</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: ����</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-07-08</p>
 */
public class XszzActionUtils extends DispatchAction {

	/**
	 * ѧ������ͨ�÷���������ҳ��������б�
	 * @param request
	 * @param commenBean
	 * @throws Exception
	 */
	public void appendProperties(HttpServletRequest request,
			XszzCommenBean commenBean, boolean sfFDY) throws Exception {
		DAO dao = new DAO();
		String xy = "";
		String zy = "";
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userName = request.getSession().getAttribute("userName")
				.toString();
		String userDep = request.getSession().getAttribute("userDep")
				.toString();

		String njLocal = commenBean.getNj();
		xy = xy == null ? "" : commenBean.getXydm();
		zy = zy == null ? "" : commenBean.getZydm();
		if (userType.equalsIgnoreCase("xy")) {
			xy = userDep;
			commenBean.setXydm(xy);
		}
		njLocal = commenBean.getNj() == null ? "" : commenBean.getNj();
		String zyKey = xy == null ? "" : xy;
		String bjKey = (xy == null ? "" : xy) + "!!" + (zy == null ? "" : zy)
				+ "!!" + (njLocal == null ? "" : njLocal);

		request.setAttribute("writeAble", "yes");// �ж��û���дȨ
		request.setAttribute("xqList", Base.getXqList());// ѧ���б�
		request.setAttribute("xnList", Base.getXnndList());// ѧ���б�
		request.setAttribute("ndList", Base.getXnndList());// ѧ���б�
		request.setAttribute("njList", Base.getNjList());// �꼶�б�
		request.setAttribute("xyList", Base.getXyList());// ѧԺ�б�
		request.setAttribute("zyList", Base.getZyMap().get(zyKey));// רҵ�б�
		request.setAttribute("bjList", Base.getBjMap().get(bjKey));// �༶�б�
		if ("xx".equalsIgnoreCase(userType)
				|| "admin".equalsIgnoreCase(userType)) {
			request.setAttribute("userType", "xx");// �û�����
		} else if (StringUtils.isEqual(userType, "stu")
				|| StringUtils.isEqual(userType, "student")) {
			request.setAttribute("userType", "stu");// �û�����
		} else {
			request.setAttribute("userType", "xy");// �û�����
		}
		if (sfFDY) {
			ArrayList<String> userBj = new ArrayList<String>();
			userBj = dao.getUserBj(userName);

			if ("xx".equalsIgnoreCase(userType)
					|| "admin".equalsIgnoreCase(userType)) {
				request.setAttribute("userType", "xx");// �û�����
			} else if (StringUtils.isEqual(userType, "stu")
					|| StringUtils.isEqual(userType, "student")) {
				request.setAttribute("userType", "stu");// �û�����
			} else {
				if (userBj.size() == 0) {
					request.setAttribute("userType", "xy");// �û�����
				} else {
					request.setAttribute("userType", "fdy");// �û�����
					request.setAttribute("bjList", dao.getBjList(xy, zy, null,
							userBj));
				}
			}
		}
		request.setAttribute("userName", userName);//�û���
	}
}
