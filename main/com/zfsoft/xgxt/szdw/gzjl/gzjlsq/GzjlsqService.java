/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-6-25 ����04:40:09 
 */  
package com.zfsoft.xgxt.szdw.gzjl.gzjlsq;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.action.Base;
import xgxt.form.User;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.rcsw.zwzxkqgl.zwzxkqjg.ZwzxKqjgForm;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����[����:1104]
 * @ʱ�䣺 2015-6-25 ����04:40:09 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class GzjlsqService extends SuperServiceImpl<GzjlsqForm, GzjlsqDao> {
	private static final String DATE_FORMAT = "yyyy-MM-dd hh24:mm:ss";
	private ShlcInterface shlc = new CommShlcImpl();
	GzjlsqDao dao = new GzjlsqDao();
	/**
	 * 
	 * @����:������¼���뱣��
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2015-6-29 ����05:10:33
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveGzjlsq(GzjlsqForm model) throws Exception {
		
		String sqid = UniqID.getInstance().getUniqIDHash();
		String splc = dao.getShlcID();
		model.setSqid(sqid);
		model.setSplc(splc);
		// ��������������趨��ʼֵ
		if ("submit".equals(model.getType())) {
			model.setShzt(Constants.YW_SHZ);// �����
		} else {
			model.setShzt(Constants.YW_WTJ);// δ�ύ
		}
		// ����������Ϣ
		boolean result = dao.runInsert(model);
		// ���������Ϣ
		if (!"save".equals(model.getType())) {
			if (result) {
				result = shlc.runSubmit(sqid, splc, model.getZgh(), "gzjl_gzjlsh.do", "gzjl_gzjlsq.do");
			}
		}
		return result;
	}
	/**
	 * 
	 * @����:������¼�޸ı���
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2015-6-29 ����05:21:49
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveEditGzjlsq(HttpServletRequest request, GzjlsqForm model) throws Exception {
		boolean result = false;
		// ����ύ���������״̬
		if ("submit".equalsIgnoreCase(model.getType()) || "tj".equalsIgnoreCase(model.getType())) {
			if ("tj".equalsIgnoreCase(model.getType())) {
				String values = request.getParameter("values");
				model.setSqid(values);
				//���˴�ѧ���Ի�����
				if("11842".equals(Base.xxdm)){
					GzjlsqForm form = dao.getModel(model);
					model.setXh(form.getXh());
				}
			} 
			model.setShzt(Constants.YW_SHZ);// �����
			result = runUpdate(model);
			String splc = dao.getShlcID();
			ShlcInterface shlc = new CommShlcImpl();
			if (result) {
				result = shlc.runSubmit(model.getSqid(), splc, model.getZgh(), "gzjl_gzjlsh.do", "gzjl_gzjlsq.do");
			}
		} else {
			
			result = runUpdate(model);
		}
		return result;
	}
	/**
	 * 
	 * @����:ֻ�и��ύ���ҵ�һ��δ��˵�ǰ���£������˿��Գ���
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2015-6-29 ����05:09:43
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ywid
	 * @param lcid
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean cancelRecord(String ywid, String lcid) throws Exception {
		return shlc.firstStepCancle(ywid, lcid);
	}
	
	
	/** 
	 * @����:������
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-1-18 ����10:47:02
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getLks() {
		return dao.getLksList();
	}
	
	/** 
	 * @����:����̸������
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-1-18 ����10:47:02
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getXsxxList(GzjlsqForm model, User user,HttpServletRequest request) throws Exception {
		String xhArr = request.getParameter("xhArr");
		if("".equals(xhArr)){
			model.setXhArr(new String[]{});
		}else{
		String[] xhs = xhArr.split(",");
		model.setXhArr(xhs);
		}
		return dao.getXsxxList(model, user);
	}
	
	/** 
	 * @����:�õ�̸������
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-1-18 ����10:47:02
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String,String>> getThdxList(String[] xhArr) {
		return dao.getThdxList(xhArr);
		
	}

}
