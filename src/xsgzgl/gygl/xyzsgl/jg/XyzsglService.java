package xsgzgl.gygl.xyzsgl.jg;

import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;
import xgxt.form.User;
import xgxt.utils.GetTime;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
/**
 * 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����Դ[����:1206]
 * @ʱ�䣺 2015-5-20 ����01:51:42 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class XyzsglService  extends SuperServiceImpl<XyzsglForm, XyzsglDao> {
	XyzsglDao dao = new XyzsglDao();
	private static final String DATE_FORMAT = "yyyy-MM-dd hh24:mi:ss";
	public List<HashMap<String,String>> getZyjzxx(XyzsglForm model){
		return dao.getZyjzxx(model);
	}
	
	/**
	 * �жϵ�ǰѧ���Ƿ���У��ס�޽��
	 */
	public boolean checkExistForSave(XyzsglForm model){
		return dao.checkExistForSave(model);
	}

	/**
	 * 
	 * �������ӽ��
	 */
	public boolean saveZsjg(XyzsglForm model, User user) throws Exception {
		model.setXn(Base.currXn);
		model.setSqsj(GetTime.getTimeByFormat(DATE_FORMAT));
//		boolean flag = dao.saveZsjg(model, user);
		boolean flag = dao.runInsert(model);
		return flag;
	}
	/**
	 * 
	 * �����޸Ľ��
	 */
	public boolean saveZsjgUpdate(XyzsglForm model, User user) throws Exception {
		boolean flag = dao.runUpdate(model);
		return flag;
	}
	/*
	 * ����������ʱ���ȡѧ��������Ϣ�Լ�ס��������Ϣ
	 */
	public HashMap<String, String> getXyzsxxMap(XyzsglForm model, User user) throws Exception {
		
		HashMap<String, String> xsxxmap = dao.getXyzsxxMap(model, user);
	
		return xsxxmap;
	}
	
   public HashMap<String, String> getXyzsxxMap1(XyzsglForm model, User user) throws Exception {
		
		HashMap<String, String> xsxxmap = dao.getXyzsxxMap1(model, user);
	
		return xsxxmap;
	}
	
	/**
	 *��ȡѧ�������ѧ������map,��������ʹ��
     *
	 */
	public List<HashMap<String, String>> getXl(XyzsglForm model) {
		return dao.getXl(model);
	}
	
	/**
	 * �鿴ʱ��ʾѧ������
	 */
	public HashMap<String, String> getXlCk(XyzsglForm model) {
		return dao.getXlCk(model);
	}
	
	/**
	 * �����סԭ��
	 */
	public HashMap<String, String> getXyZsyy(XyzsglForm model) {
		return dao.getXyZsyy(model);
	}
}
