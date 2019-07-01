/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-2-22 ����11:19:50 
 */  
package com.zfsoft.xgxt.zxdk.ypzl.sq;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
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

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����[����:1282]
 * @ʱ�䣺 2016-2-22 ����11:19:50 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class YpzlSqService extends SuperServiceImpl<YpzlSqForm, YpzlSqDao>{
	private ShlcInterface shlc = new CommShlcImpl();
	private YpzlSqDao dao = new YpzlSqDao();
	private static final String DATE_FORMAT = "yyyy-MM-dd hh24:mi:ss";
	
	/**
	 * 
	 * @����:�õ�ѧ������
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-2-23 ����03:28:54
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
	 * @����:�ж�ͬһѧ�����Ƿ��������¼
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-2-23 ����03:34:06
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param form
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean isHaveRecord(YpzlSqForm form){
		return dao.isHaveRecord(form);
	}
	
	/** 
	 * @����:�������ӽ��
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-2-23 ����03:39:44
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws 
	 */
	public boolean saveSqjg(YpzlSqForm model, User user) throws Exception {
		String sqid = UniqID.getInstance().getUniqIDHash();
		model.setSqid(sqid);
		String splc = dao.getShlcID();
		model.setSqsj(GetTime.getTimeByFormat(DATE_FORMAT));
		model.setSplc(splc);
		if(model.getType().equals("save")){
			model.setShzt(Constants.YW_WTJ);
		}else{
			model.setShzt(Constants.YW_SHZ);
		}
		boolean flag = dao.runInsert(model);
		if(model.getType().equals("submit")){
			if (flag) {
				flag = shlc.runSubmit(sqid, splc, model.getXh(), "zxdk_ypzl_ypzlsh.do", "zxdk_ypzl_ypzlsq.do");
			}
		}
		return flag;
	}
	
	/**
	 * 
	 * �����޸Ľ��
	 */
	public boolean saveSqjgUpdate(YpzlSqForm model, User user) throws Exception {
		boolean result = false;
		// ����ύ���������״̬
		if ("updatesubmit".equalsIgnoreCase(model.getType())) {
			model.setShzt(Constants.YW_SHZ);// �����
			result = runUpdate(model);
			ShlcInterface shlc = new CommShlcImpl();
			if (result) {
				String splc = dao.getShlcID();
				model.setSplc(splc);
				result = shlc.runSubmit(model.getSqid(), model.getSplc(), model.getXh(), "zxdk_ypzl_ypzlsh.do", "zxdk_ypzl_ypzlsq.do");
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
	public boolean submitYpzlsq(YpzlSqForm model) throws Exception {
			boolean result = false;
			model.setShzt(Constants.YW_SHZ);// �����
			String splc = dao.getShlcID();
			model.setSplc(splc);
			result = runUpdate(model);
			ShlcInterface shlc = new CommShlcImpl();
			if (result) {
				result = shlc.runSubmit(model.getSqid(), splc, model.getXh(), "zxdk_ypzl_ypzlsh.do", "zxdk_ypzl_ypzlsq.do");
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
	
	
	/** 
	 * @����:�õ����ص�word�ļ�
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-2-26 ����08:40:40
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param data
	 * @return
	 * File �������� 
	 * @throws 
	 */
	public File getWord(Map<String, Object> data) throws FileNotFoundException{
		//String templatePath = Constants.TEP_DIR+"zxdk/ypzl.xml";
		String templatePath = Constants.TEP_DIR+"zxdk/ypzl_"+Base.xxdm+".xml";
		
		File wordFile = null;
		
		try{
			ResourceUtils.getFile(templatePath);
			
			wordFile = FreeMarkerUtil.getWordFile(data, Constants.TEP_DIR + "zxdk", "ypzl_"+Base.xxdm+".xml", FreeMarkerUtil.getFileName(data.get("xh") +"["+data.get("xm")+"]"));
						
		}catch (Exception e) {
					
			wordFile = FreeMarkerUtil.getWordFile(data, Constants.TEP_DIR + "zxdk", "ypzl.xml", FreeMarkerUtil.getFileName(data.get("xh") +"["+data.get("xm")+"]"));
		}

		return wordFile;
	}
	
	/** 
	 * @����:��ӡ
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-2-26 ����03:20:14
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
	 * @����:�õ���;���
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-3-4 ����10:56:12
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getYtlbList(){
		return dao.getYtlbList();
	}
	
	/** 
	 * @����:�õ���;����
	 * @���ߣ�����[���ţ�982]
	 * @���ڣ�2016-3-4 ����10:57:20
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ytdm
	 * @return
	 * String �������� 
	 * @throws 
	 */
	public String getYtmc(String ytdm){
		return dao.getYtmc(ytdm);
	}
	
	
	
}

