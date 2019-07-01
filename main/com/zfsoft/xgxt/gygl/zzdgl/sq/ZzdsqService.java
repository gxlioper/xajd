/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-2-29 ����02:08:47 
 */  
package com.zfsoft.xgxt.gygl.zzdgl.sq;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.util.ResourceUtils;

import xgxt.action.Base;
import xgxt.form.User;
import xgxt.utils.GetTime;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.FreeMarkerUtil;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.gygl.zzdgl.cssz.CsszDao;
import com.zfsoft.xgxt.zxdk.ypzl.sq.YpzlSqForm;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����[����:1282]
 * @ʱ�䣺 2016-2-29 ����02:08:47 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZzdsqService extends SuperServiceImpl<ZzdsqForm, ZzdsqDao>{
	private ShlcInterface shlc = new CommShlcImpl();
	//private static final String DATE_FORMAT = "yyyy-MM-dd hh24:mi:ss";
	private ZzdsqDao dao = new ZzdsqDao();
	private CsszDao csszDao = new CsszDao();
	
	
	/** 
	 * @����:�ж��Ƿ�ס��
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-2-29 ����02:46:41
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param form
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean iszhusu(ZzdsqForm form){
		return dao.iszhusu(form);
	}
	
	/** 
	 * @����:�õ�ѧ������
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-2-29 ����02:47:34
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xqdm
	 * @return
	 * String �������� 
	 * @throws 
	 */
	public String getXqmc(String xqdm){
		return dao.getXqmc(xqdm);
	}
	
	/** 
	 * @����:��֤�Ƿ��������¼
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-2-29 ����05:17:58
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param form
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws 
	 */
	public boolean isHaveRecord(ZzdsqForm form) throws Exception{
		return dao.isHaveRecord(form);
	}
	
	/** 
	 * @����:������
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-2-29 ����05:26:02
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws 
	 */
	public boolean saveSqjg(ZzdsqForm model, User user) throws Exception {
		String zzdid = UniqID.getInstance().getUniqIDHash();
		model.setZzdid(zzdid);
		String sqid = model.getZzdid();		
		String splc = csszDao.getModel().getSplcid();
		model.setSplcid(splc);
		if(model.getType().equals("save")){
			model.setShzt(Constants.YW_WTJ);
		}else{
			model.setShzt(Constants.YW_SHZ);
		}
		boolean flag = dao.runInsert(model);
		if(model.getType().equals("submit")){
			if (flag) {
				flag = shlc.runSubmit(sqid, splc, model.getXh(), "xgygl_zzdgl_zdsh.do", "xgygl_zzdgl_zdsq.do");
			}
		}
		return flag;
	}
	
	
	/** 
	 * @����:�����޸�
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-2-29 ����05:28:06
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws 
	 */
	public boolean saveSqjgUpdate(ZzdsqForm model, User user) throws Exception {
		boolean result = false;
		// ����ύ���������״̬
		if ("updatesubmit".equalsIgnoreCase(model.getType())) {
			model.setShzt(Constants.YW_SHZ);// �����
			result = runUpdate(model);
			ShlcInterface shlc = new CommShlcImpl();
			if (result) {
				String splc = csszDao.getModel().getSplcid();
				model.setSplcid(splc);
				result = shlc.runSubmit(model.getZzdid(), model.getSplcid(), model.getXh(), "xgygl_zzdgl_zdsh.do", "xgygl_zzdgl_zdsq.do");
			}
		} else {
			result = runUpdate(model);
		}
		
		return result;
	}
	
	/**
	 * 
	 * �ύ
	 */
	public boolean submiZzdsq(ZzdsqForm model) throws Exception {
			boolean result = false;
			model.setShzt(Constants.YW_SHZ);// �����
			String splc = csszDao.getModel().getSplcid();
			model.setSplcid(splc);
			result = runUpdate(model);
			ShlcInterface shlc = new CommShlcImpl();
			if (result) {
				result = shlc.runSubmit(model.getZzdid(), splc, model.getXh(), "xgygl_zzdgl_zdsh.do", "xgygl_zzdgl_zdsq.do");
			}
			return result;
	}
	
	/**
	 * 
	 * ����
	 */
	public boolean cancelRecord(String ywid, String lcid) throws Exception {
		return shlc.firstStepCancle(ywid, lcid);
	}
	
	public boolean isGlyqr(ZzdsqForm form){
		return dao.isGlyqr(form);
	}
	
	/**
	 * 
	 * �õ�ԭ����
	 */
	public String getQxmForPrint(String xh){
		return dao.getQxmForPrint(xh);
	}
	
	/** 
	 * @����:��ӡ
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-3-3 ����06:16:37
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xsjbxx
	 * @return
	 * @throws Exception
	 * File �������� 
	 * @throws 
	 */
	public File printForWord(HashMap<String, String> xsjbxx) throws Exception{
		Map<String, Object> data = new HashMap<String, Object>();
		if(xsjbxx == null){
			xsjbxx = new HashMap<String, String>();
		}
		data.putAll(xsjbxx);
		return getWord(data);
	}
	
	/** 
	 * @����:����word�ļ�
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-3-3 ����06:17:14
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param data
	 * @return
	 * @throws FileNotFoundException
	 * File �������� 
	 * @throws 
	 */
	public File getWord(Map<String, Object> data) throws FileNotFoundException{
		//String templatePath = Constants.TEP_DIR+"zxdk/ypzl.xml";
		String templatePath = Constants.TEP_DIR+"gygl/gygl_zzd_"+Base.xxdm+".xml";
		
		File wordFile = null;
		
		try{
			ResourceUtils.getFile(templatePath);
			
			wordFile = FreeMarkerUtil.getWordFile(data, Constants.TEP_DIR + "gygl", "gygl_zzd_"+Base.xxdm+".xml", FreeMarkerUtil.getFileName(data.get("xh") +"["+data.get("xm")+"]"));
						
		}catch (Exception e) {
					
			wordFile = FreeMarkerUtil.getWordFile(data, Constants.TEP_DIR + "gygl", "gygl_zzd_"+Base.xxdm+".xml", FreeMarkerUtil.getFileName(data.get("xh") +"["+data.get("xm")+"]"));
		}

		return wordFile;
	}
}
