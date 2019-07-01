package xgxt.studentInfo.bdzc;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.action.Base;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.FormModleCommon;
import xgxt.xszz.zgdzdx.XszzZgdzdxService;

public class BdzcService {
	
	BdzcDAO dao = new BdzcDAO();
	
	
	/**
	 * ע��ʱ������
	 * @param pkValue
	 * @param zckssj
	 * @param zcjssj
	 * @param hzcjssj
	 * @return
	 * @throws Exception 
	 */
	public boolean saveSjsz(String[] pkValue, String[] zckssj, String[] zcjssj,
			String[] hzcjssj) throws Exception {
		return dao.saveSjsz(pkValue, zckssj, zcjssj, hzcjssj);
	}

	
	
	/**
	 * ѧ��������Ϣ
	 * @param xh
	 * @return
	 */
	public HashMap<String, String> getStuInfo(String xh) {
		return CommonQueryDAO.getStuInfo(xh);
	}
	
	
	/**
	 * �����б�
	 * @param request
	 * @param flg
	 */
	public void setList(HttpServletRequest request,String flg) {
		
		if ("sh".equalsIgnoreCase(flg)) {
			//����ԭ��
			List shztList = dao.getSelectList("shzt");
			request.setAttribute("shztList", shztList);
		}  else if ("zczt".equalsIgnoreCase(flg)) {
			List zcztList = dao.getSelectList("zczt") ;
			request.setAttribute("zcztList", zcztList);
			
			List hzcList = dao.getSelectList("ishzc");
			request.setAttribute("hzcList", hzcList);
			
			List xq = dao.getXq();
			request.setAttribute("xq", xq);
			
			request.setAttribute("yzc", "��ע��");
			request.setAttribute("wzc", "δע��");
		} else if ("tjjg".equals(flg)) {
			List tjList = dao.getSelectList("tjjg") ;
			request.setAttribute("tjList", tjList);
		} else if ("xq".equals(flg)) {
			List xq = dao.getXq();
			request.setAttribute("xq", xq);
		}
		
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		FormModleCommon.setNdXnXqList(request);
	}
	
	
	
	/**
	 * ��ʼ��ע��״̬
	 * @return
	 * @throws Exception
	 */
	public boolean zctb() throws Exception {
		
		return dao.zctb();
	}
	
	
	
	/**
	 * ��������ע��״̬
	 * @param xh
	 * @param sfzc
	 * @return
	 * @throws SQLException
	 */
	public HashMap<String, String> saveZczt(String[] pkValue, String[] xn,
			String[] xq, String zczt) throws SQLException {
		return dao.saveZczt(pkValue, xn, xq, zczt);

	}
	
	/**
	 * ע��״̬ͬ��ѧ����Ϣ
	 * @param xh
	 * @param sfzc
	 * @return
	 * @throws Exception 
	 */
	public boolean tbZczt() throws Exception {
		return dao.tbZczt();

	}
	
	
	
	/**
	 * ��ȡ�༶������Ϣ
	 * @param bjdm
	 * @return
	 */
	public HashMap<String, String> getClassInfo(String bjdm) {
		
		return dao.getClassInfo(bjdm);
	}
	

	/**
	 * ��ȡע��ʱ���
	 * @param pkValue
	 * @return
	 */
	public String getZcsj(String pkValue) {
		
		return dao.getZcsj(pkValue);
	}
	
	
	
	/**
	 * ����ע��
	 * @param pkValue
	 * @return
	 * @throws SQLException
	 */
	public boolean updateZczt(String pkValue) throws SQLException {
		return dao.updateZczt(pkValue);
	}
	
	
	/**
	 * �Ƿ�ע��ʱ���
	 * @param xh
	 * @return
	 */
	public String isHzcsj(String xh) {
		String isHzcsj = dao.isHzcsj(xh);
		
		if (Base.isNull(isHzcsj)) {
			isHzcsj="false";
		}
		return isHzcsj;
	}
	
	
	/**
	 * ������ѧ���������Ϣ
	 * @param xh
	 * @return
	 */
	public HashMap<String, String> getZxdk(String xh) {
		XszzZgdzdxService service = new XszzZgdzdxService();
		
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("fkxn", service.getFkcs(xh));
		try {
			map.putAll(service.getGjzxdkxx(xh)) ;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return map;
	}
	
	
	/**
	 * �Ƿ�Ƿ��
	 * @param xh
	 * @param xn
	 * @return
	 */
	public HashMap<String, String> getSfqf(String xh,String xn,String xq) {
		
		if (!Base.isNull(xn) && xn.length()>4)
			return dao.getSfqf(xh, xn,xq);
		else 
			return null;
	}
	
	/**
	 * 
	 * ���� ���� 2010-8-27
	 * �������Ƿ��нɷ�
	 * @param xh
	 * @param nd
	 * @return
	 */
	public boolean checkStusfJf(String xh, String nd) {
		return dao.checkStusfJf(xh, nd);
	}
}
