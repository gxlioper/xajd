package xgxt.pjpy.comm.pjpy;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import xgxt.action.Base;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��������ҳ����ת-action��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author ΰ�����
 * 
 * @version 1.0
 */

public class PjpyDispatchAction extends DispatchAction {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		// ģ������
		String mklx = mapping.getParameter();
		// ѧУ����
		String xxdm = Base.xxdm;
		// ��ת·��
		String forward = "";

		if ("pjpy_pjjbsz".equalsIgnoreCase(mklx)) {// ������������
			forward = getPjjbszForward(xxdm);
		} else if ("pjpy_pjlcsz".equalsIgnoreCase(mklx)) {// ������������
			forward = getPjlcszForward(xxdm);
		} else if ("pjpy_pjryqd".equalsIgnoreCase(mklx)) {// ������Աȷ��
			forward = getPjryqdForward(xxdm);
		} else if ("pjpy_pjxmsz".equalsIgnoreCase(mklx)) {// ������Ŀ����
			forward = getPjxmszForward(xxdm);
		}
		return new ActionForward(forward, false);
	}

	/**
	 * �����������������ת·��
	 * 
	 * @param xxdm
	 * @return
	 * 
	 * @author ΰ�����
	 */
	private String getPjjbszForward(String xxdm) {

		String forward = "";
		// ��ø��Ի�ѧУ��ת
		HashMap<String, String> pathMap = PjpyGlobalsData.getPjjbszPath();
		forward = pathMap.get(xxdm);
		if (Base.isNull(forward)) {
			// ͨ����ת
			forward = PjpyGlobalsData.PJJBSZ_PATH;
		}

		return forward;
	}

	/**
	 * �����������������ת·��
	 * 
	 * @param xxdm
	 * @return
	 * 
	 * @author ΰ�����
	 */
	private String getPjlcszForward(String xxdm) {

		String forward = "";
		// ��ø��Ի�ѧУ��ת
		HashMap<String, String> pathMap = PjpyGlobalsData.getPjlcszPath();
		forward = pathMap.get(xxdm);
		if (Base.isNull(forward)) {
			// ͨ����ת
			forward = PjpyGlobalsData.PJLCSZ_PATH;
		}

		return forward;
	}
	
	/**
	 * ���������Աȷ����ת·��
	 * 
	 * @param xxdm
	 * @return
	 * 
	 * @author Qiu
	 */
	private String getPjryqdForward(String xxdm) {

		String forward = "";
		// ��ø��Ի�ѧУ��ת
		HashMap<String, String> pathMap = PjpyGlobalsData.getPjryqdPath();
		forward = pathMap.get(xxdm);
		if (Base.isNull(forward)) {
			// ͨ����ת
			forward = PjpyGlobalsData.PJRYQD_PATH;
		}

		return forward;
	}
	
	/**
	 * ���������Ŀ������ת·��
	 * 
	 * @param xxdm
	 * @return
	 * 
	 * @author Qiu
	 */
	private String getPjxmszForward(String xxdm) {

		String forward = "";
		// ��ø��Ի�ѧУ��ת
		HashMap<String, String> pathMap = PjpyGlobalsData.getPjxmszPath();
		forward = pathMap.get(xxdm);
		if (Base.isNull(forward)) {
			// ͨ����ת
			forward = PjpyGlobalsData.PJXMSZ_PATH;
		}

		return forward;
	}
}
