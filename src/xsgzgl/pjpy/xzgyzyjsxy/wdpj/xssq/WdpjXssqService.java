package xsgzgl.pjpy.xzgyzyjsxy.wdpj.xssq;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xsgzgl.comm.BasicModel;
import xsgzgl.comm.BasicService;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.inter.wdpj.WdpjXssqInterface;
import xsgzgl.pjpy.general.pjsz.pjxm.PjszPjxmDAO;
import xsgzgl.pjpy.general.pjsz.pjxm.PjszPjxmModel;
import xsgzgl.pjpy.general.pjsz.pjxm.PjszPjxmService;
import xsgzgl.pjpy.general.wdpj.xssq.WdpjXssqModel;
import xsgzgl.pjpy.xzgyzyjsxy.zhcp.PjpyZhcpDAO;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��������_�ҵ�����_ѧ������_ͨ��_Service��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author qlj
 * @version 1.0
 */

public class WdpjXssqService extends xsgzgl.pjpy.general.wdpj.xssq.WdpjXssqService {

	WdpjXssqDAO dao = new WdpjXssqDAO();


	/**
	 * ѧ������by������
	 * 
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception 
	 */
	public List<HashMap<String, String>> getXssqByZq(WdpjXssqModel model,
			User user) throws Exception {

		List<HashMap<String, String>> xssqList = dao.getXssqByZq(model, user);
		
		return xssqList;
	}

	/**
	 * ��ȡ�����۲���Ϣ
	 */
	public List<String[]> getXszcInfo(WdpjXssqModel model, User user) {
		// TODO �Զ����ɷ������
		String xh=user.getUserName();
		
		PjpyZhcpDAO dao=new PjpyZhcpDAO();
		
		return dao.getZcList(xh);
	}
	
	/**
	 * ��ȡѧ�������۲���Ϣ
	 */
	public List<Object> getZcxxByZq(WdpjXssqModel model, User user) throws Exception {
		
		String xh=user.getUserName();
		
		PjpyZhcpDAO dao=new PjpyZhcpDAO();
		
		return dao.getZcByZqList(xh);
	}
	

}
