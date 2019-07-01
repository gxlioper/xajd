package xsgzgl.pjpy.cdtyxy.djbg;

import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.studentInfo.service.StudentInfoService;
import xgxt.studentInfo.service.XsxxglService;
import xsgzgl.pjpy.general.djbg.PjpyDjbgModel;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��������_�ǼǱ��_�㶫����ְҵ����ѧԺ_Service��
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

public class PjpyDjbgService extends xsgzgl.pjpy.general.djbg.PjpyDjbgService {

	PjpyDjbgDAO dao = new PjpyDjbgDAO();

	/**
	 * �ǼǱ������
	 * 
	 * @author qlj
	 */
	public HashMap<String, Object> getDjbgInfo(PjpyDjbgModel model) {

		// ѧ��
		String xh = model.getXh();
		// ��Ŀ����
		String xmmc = model.getXmmc();
		// ѧ��
		String xn = model.getXn();
		// ѧ��
		String xq = model.getXq();

		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("xh", xh);
		map.put("xn", xn);
		map.put("xmmc", xmmc);

		// ����ѧ����Ϣ
		setXsxxInfo(map);
		// ѧ����ϸ��Ϣ
		getXsxxInfo(map);
		
		setXszcInfo(map);
		
		getZdcjInfo(map);
		// ��ȡ����������
		HashMap<String,String>knsjbMap=getKnsjb(xh, xn);
		map.putAll(knsjbMap);
		
		HashMap<String,String>xspjInfo=getXspjInfo(xh, xn, xmmc);
		map.putAll(xspjInfo);
		
		HashMap<String,String>xspjcjList=getXspjcjInfo(xh, xn);
		map.putAll(xspjcjList);
		
		String xmjb="";
		if("ѧԺ��ͥ��������ѧ��һ�Ƚ�".equalsIgnoreCase(xmmc) 
				|| "ѧԺ��ͥ��������ѧ�����Ƚ�".equalsIgnoreCase(xmmc) 
				|| "ѧԺ��ͥ��������ѧ�����Ƚ�".equalsIgnoreCase(xmmc)){
			xmjb=xmmc.substring(10,13);
		}else if("ѧԺѧ��һ�Ƚ�ѧ��".equalsIgnoreCase(xmmc) 
				|| "ѧԺѧ�����Ƚ�ѧ��".equalsIgnoreCase(xmmc) 
				|| "ѧԺѧ�����Ƚ�ѧ��".equalsIgnoreCase(xmmc)){
			xmjb=xmmc.substring(4,7);
		}
		map.put("sqdj", xmjb);
		
		return map;
	}
	
	/**
	 * ��ȡ��ӡ·��
	 * @author qlj
	 */
	public String getPrintJspForward(PjpyDjbgModel model) throws Exception {
		
		String xmmc=model.getXmmc();
		
		String url ="";
		
		
		if("ѧԺ��ͥ��������ѧ��һ�Ƚ�".equalsIgnoreCase(xmmc) 
				|| "ѧԺ��ͥ��������ѧ�����Ƚ�".equalsIgnoreCase(xmmc) 
				|| "ѧԺ��ͥ��������ѧ�����Ƚ�".equalsIgnoreCase(xmmc)){
			url="/xsgzgl/pjpy/gdjszyjsxy/knsjxj/knsjxj.jsp";
		}
		
		return url;
	}

	/**
	 * ѧ����Ϣ
	 * 
	 * @author qlj
	 */
	private void setXsxxInfo(HashMap<String, Object> map) {

		// ѧ��
		String xh = (String) map.get("xh");
		// ѧ��
		String xn = (String) map.get("xn");

		HashMap<String, String> xsxx = dao.setXsxxInfo(xh, xn);

		map.putAll(xsxx);
	}
	
	/**
	 * ��ѯѧ����ϸ��Ϣ
	 * @param map
	 * @return
	 */
	private void getXsxxInfo(HashMap<String, Object> map){

		XsxxglService xsxxglService=new XsxxglService(); 
		//ѧ��
		String xh = (String) map.get("xh");
		
		// ѧ����ϸ��Ϣ
		HashMap<String,String>xsxxInfo= xsxxglService.selectStuinfo(xh);
		
		map.putAll(xsxxInfo);
		
	}
	
	/**
	 * ��ѯѧ��������Ϣ
	 * @param map
	 * @return
	 */
	private HashMap<String,String> getXspjInfo(String xh,String xn,String xmmc){

		// ѧ����ϸ��Ϣ
		HashMap<String,String>xspjInfo= dao.getXspjInfo(xh,xn,xmmc);
		
		return xspjInfo;
		
	}
	
	/**
	 * ��ȡѧ������������
	 * 
	 * @author qlj
	 */
	public HashMap<String, String> getKnsjb(String xh, String xn ) {
		
		HashMap<String,String>knsjbMap=dao.getKnsjb(xh, xn);

		return knsjbMap;
	}
	// =============================�ɼ���Ϣ begin ============================
	/**
	 * ѧ��ƽ���ɼ�
	 * 
	 * @author qlj
	 */
	public HashMap<String, String> getXspjcjInfo(String xh, String xn ) {
		
		// ѧ��ƽ���ɼ�
		HashMap<String,String>xspjInfo= dao.getXspjcjInfo(xh,xn);
		
		return xspjInfo;
		
	}
	

	/**
	 * ��ȡѧ���۲���Ϣ
	 * 
	 * @author qlj
	 */
	private void setXszcInfo(HashMap<String, Object> map) {

		// ѧ��
		String xh = (String) map.get("xh");
		// ѧ��
		String xn = (String) map.get("xn");

		HashMap<String, String> xsxx = dao.getXszcInfo(xh, xn);

		map.putAll(xsxx);
	}
	
	/**
	 * ��ͳɼ�
	 * 
	 * @author qlj
	 */
	public void getZdcjInfo(HashMap<String, Object> map) {
		
		// ѧ��
		String xh = (String) map.get("xh");
		// ѧ��
		String xn = (String) map.get("xn");

		HashMap<String, String> zdcj = dao.getZdcjInfo(xh, xn);

		map.putAll(zdcj);
	}
	// =============================�ɼ���Ϣ end ============================
	
	
}
