/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-3-28 ����05:20:11 
 */  
package com.zfsoft.xgxt.xsxx.cxdd.sb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import xgxt.action.Base;
import xgxt.form.User;
import xsgzgl.gygl.xyzsgl.sq.XyzsSqDao;
import xsgzgl.gygl.xyzsgl.sq.XyzsSqForm;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.xsxx.cxdd.cssz.CsszService;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� yxy[����:1206]
 * @ʱ�䣺 2016-3-28 ����05:20:11 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class CxddSbService extends SuperServiceImpl<CxddSbForm, CxddSbDao> {
	CxddSbDao dao = new CxddSbDao();
//	private static final String DATE_FORMAT = "yyyy-MM-dd hh24:mi:ss";
	private ShlcInterface shlc = new CommShlcImpl();
	public List<HashMap<String, String>> getXsPageList(CxddSbForm t, User user) 
	throws Exception{
		return dao.getXsPageList(t, user);
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����:���°༶������¼����ֹ�༶���࣬���ݷ�ΧΪδ�ύ�������˻ص�����
	 * @����:Ϊ�˺�����չ���ܣ�������������༶�������߲�ѡ�༶������,�÷����Ǵ������������
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-4-27 ����02:02:47
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean UpdateBjtzRecode(String[] bjdms) throws Exception{
		return dao.UpdateBjtzRecode(bjdms);
	}
	
	//�ύ
	public boolean submitBusi(CxddSbForm model, User user)  throws Exception {
		model.setShzt(Constants.YW_SHZ);
		boolean flag = dao.runUpdate(model);
		String splc =  new CsszService().getModel().getSplc();
		model.setSplc(splc);
		if(flag){
			 shlc.runSubmit(model.getBjid(), model.getSplc(), model.getXh(), "xsxx_cxdd_pysh.do", "xsxx_cxdd_pysb.do");
		}
		return flag;
	}
	
	//����
	public boolean cancelRecord(String ywid, String lcid) throws Exception {
		return shlc.firstStepCancle(ywid, lcid);
	}
	
	//�Ƿ���Ȩ���ύ
	public boolean isHaveQxTj(String bjdm){
		return dao.isHaveQxTj(bjdm);
	}
	
	public  List<HashMap<String, String>> getCxdjdmList(){
		return dao.getCxdjdmList();
	}
	
	public boolean saveDataXs(CxddSbForm t,String type) throws Exception{
		return dao.saveDataXs(t, type);
	}
	
	//�ύʱɾ��ѧ���ϱ����в���У��������
	public boolean delCxddbzx(String bjdm) throws Exception{
		return dao.delCxddbzx(bjdm);
	}
}
