/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-2-11 ����11:25:34 
 */  
package com.zfsoft.xgxt.xszy.xszygl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.form.User;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.xszy.xsqshf.XszyQshfDao;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� xiaxia[����:1104]
 * @ʱ�䣺 2015-2-11 ����11:25:34 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XszyglService extends SuperServiceImpl<XszyglForm, XszyglDao>{
	private XszyglDao dao = new XszyglDao();
	/**
	 * 
	 * @����:ҳ�������ʼ��
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-2-11 ����03:54:37
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * void �������� 
	 * @throws
	 */
	public void initParam(HttpServletRequest request,User user){
		XszyQshfDao qshfDao  = new XszyQshfDao();
		DAO dao = new DAO();
		List<HashMap<String, String>> dwList = new ArrayList<HashMap<String, String>>();
		HashMap<String,String> dwMap = new HashMap<String,String>();
		if("xy".equals(user.getUserStatus())){
			dwMap.put("xydm", user.getUserDep());
			dwMap.put("xymc", qshfDao.getBmmc(user.getUserDep()));
			dwList.add(dwMap);
		}else{
			dwList=qshfDao.getBmList();
		}
		request.setAttribute("dwList", dwList);
		request.setAttribute("zzmmList", dao.getZzmmList());
		
	}
	public boolean isHaveXszy(XszyglForm model) {
		model.setNj(Base.currNd);
		return dao.isHaveXszy(model);
	}
	/**
	 * 
	 * @����:����֮�ѱ���
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-2-11 ����05:07:26
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean editXszy(XszyglForm model) throws Exception {
		boolean result = true;
		if ("save".equals(model.getType())) {
			model.setNj(Base.currNd);
			String id = UniqID.getInstance().getUniqIDHash();
			model.setId(id);
			result = dao.runInsert(model);
		} else {
			result = dao.runUpdate(model);
		}
		return result;
	}
	
	
	/**
	 * 
	 * @����:��ѯ����֮����Ϣ
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-2-12 ����09:45:57
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return
	 * @throws Exception
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String,String> getXszy(XszyglForm t) throws Exception {
		return dao.getXszy(t);
	}
	/**
	 * 
	 * @����:��Ժϵ���
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-2-12 ����03:00:08
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean kyxbj(XszyglForm model,HttpServletRequest request) throws Exception {
		String[] ids = request.getParameter("ids").split(",");
		List<String[]> xszyList = new ArrayList<String[]>();
		String[] xszyxx = null;
		for (int i = 0; i < ids.length; i++) {
			xszyxx = new String[2];
			xszyxx[0] = model.getKyxbj();
			xszyxx[1] = ids[i];
			xszyList.add(xszyxx);
		}
		boolean result = dao.kyxbj(xszyList);
		return result;
	}
	/**
	 * 
	 * @����:Ժϵ����
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-2-12 ����03:00:29
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean fpyx(XszyglForm model,HttpServletRequest request) throws Exception {
		String[] ids = request.getParameter("ids").split(",");
		List<String[]> xszyList = new ArrayList<String[]>();
		String[] xszyxx = null;
		for (int i = 0; i < ids.length; i++) {
			xszyxx = new String[2];
			xszyxx[0] = model.getBjyx();
			xszyxx[1] = ids[i];
			xszyList.add(xszyxx);
		}
		boolean result = dao.bjyx(xszyList);
		return result;
	}
}
