/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-4-23 ����04:52:00 
 */  
package com.zfsoft.xgxt.rcsw.cdgl.sq;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.date.DateUtils;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.dtjs.dtxxgl.dtxxsq.DtxxsqForm;
import com.zfsoft.xgxt.dtjs.dtxxgl.shlcpz.ShlcpzForm;
import com.zfsoft.xgxt.dtjs.dtxxgl.shlcpz.ShlcpzService;
import com.zfsoft.xgxt.rcsw.cdgl.cdxxwh.CdxxwhForm;
import com.zfsoft.xgxt.rcsw.cdgl.cdxxwh.CdxxwhService;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������:�ճ��������ģ��
 * @�๦������: ��������Service
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2014-4-23 ����04:52:00 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class CdsqService extends SuperServiceImpl<CdsqForm, CdsqDao> {

	/**
	 * @���� ����������ӿ�
	 */
	private ShlcInterface shlc = new CommShlcImpl();
	
	/**
	 * @���� ������Ϣ����ӿ�
	 */
	private CdxxwhService cdxxwhService = new CdxxwhService();
	
	public static final String PATH_SH = "rcsw_cdgl_cdshgl.do";
	
	public static final String PATH_SQ = "rcsw_cdgl_cdsqgl.do";
	
	/**
	 * 	��ȡ����������Ϣ			
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-4-29 ����10:18:01
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param sqid
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String , String> getCdsqxx(String sqid){
		if(StringUtils.isNotBlank(sqid)){
			return dao.getCdsqxx(sqid);
		}
		return null;
	}
	
	public List<HashMap<String, String>> getPageListOfSqjl(CdsqForm t, User user)
	throws Exception {
	
    return dao.getPageListOfSqjl(t,user);
	}
	/**
	 * 
	 * 
	 * @����:���泡������ݸ�
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-4-24 ����05:28:17
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 
	 * @throws
	 */
	public HashMap<String , Object> saveCdsqNoSubmit(CdsqForm model) throws Exception{
		HashMap<String , Object> result = new HashMap<String, Object>(); //���ض���
				
		model.setShzt(Constants.YW_WTJ);//�������״̬ δ�ύ
		model.setSqsj(DateUtils.getCurrTime()); //��������ʱ��
		boolean isSuccess = runInsert(model); //��������
		result.put("ISSUCCESS", isSuccess);
		return result;
	}
	
	/**
	 * 
	 * @����:�ύ��������
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-4-25 ����02:52:46
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean submitCdsq(CdsqForm form) throws Exception{
		CdsqForm model = null;
		boolean checkConditions = false;
		if(form.getSqid() != null){
			model = getModel(form.getSqid());
		}
		if(model != null){
			checkConditions = checkSqSjd(model); //�����������
		}

		if(!checkConditions){
			return false;
		}
		
		boolean isSuccess = false;
		model.setSplcid(form.getSplcid());
		model.setQxfw(form.getQxfw());
		
		if(model != null && StringUtils.isNotBlank(model.getSqid())){
			isSuccess = shlc.runSubmit(model.getSqid(), model.getSplcid(), model.getXh(), CdsqService.PATH_SH, CdsqService.PATH_SQ);
			if(isSuccess){
				model.setShzt(Constants.YW_SHZ);
				isSuccess = runUpdate(model);
			}
		}
		return isSuccess;
	}
	/**
	 * ���³�������
	 */
	public boolean updateCdsqNoSubmit(CdsqForm form) throws Exception{
		boolean checkConditions = checkSqSjd(form); //�����������
		if(!checkConditions){
			return false;
		}
		boolean isSuccess = runUpdate(form);
		return isSuccess;
	}
	
	/**
	 * 
	 * @����:ɾ����������
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-4-25 ����03:01:02
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param sqids
	 * @return
	 * @throws Exception
	 * int �������� 
	 * @throws
	 */
	public int deleteCdsq(String[] sqids) throws Exception{
		return dao.runDelete(sqids);
	}
	
	/**
	 * 
	 * @����:������������
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-4-25 ����03:34:26
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean cancelCdsq(String sqid) throws Exception{
		boolean isSuccess = false;
		CdsqForm model = null;
		if(sqid != null){
			model = getModel(sqid);
		}
		
		if(model != null){
			isSuccess = shlc.firstStepCancle(model.getSqid(), model.getSplcid());
		}
		
		if(isSuccess){
			ShlcDao shlcdao = new ShlcDao();
			if(Integer.valueOf(shlcdao.getExistTh(model.getSqid()))>0){
				model.setShzt(Constants.YW_YTH);
			}else{
				model.setShzt(Constants.YW_WTJ);
			}
			isSuccess = runUpdate(model);
		}
		
		return isSuccess;
	}
	
	/**
	 * 
	 * 
	 * @����:���泡�����벢�ύ����
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-4-24 ����05:28:17
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 
	 * @throws
	 */
	public HashMap<String , Object> saveCdsqWithSubmit(CdsqForm model) throws Exception{
		
		HashMap<String , Object> result = new HashMap<String, Object>(); //���ض���
		
		boolean checkConditions = checkSqSjd(model); //�����������
		
		if(!checkConditions)
		{
			result.put("CODE", "CHK_ERROR");
			result.put("ISSUCCESS", Boolean.FALSE);
			return result;
		}
		
		model.setSqid(UniqID.getInstance().getUniqIDHash()); //����sqid
		
		boolean isSuccess = (Boolean)saveCdsqNoSubmit(model).get("ISSUCCESS"); //��������
		isSuccess = submitCdsq(model);				 //�ύ����
		
		result.put("ISSUCCESS", isSuccess);
		return result;
	}
	
	
	
	/**
	 * @throws Exception 
	 * 
	 * @����:У�������ʱ��� > 1.����ʱ���ѡ���ڿ���ʱ����2�������������ʱ�����û�б����������룡
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-4-25 ����10:41:10
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean checkSqSjd(CdsqForm model) throws Exception{
		boolean booleaVal = true;
		//У�� 1.����ʱ���ѡ���ڿ���ʱ�����
		CdxxwhForm cdxxModel = null ;
		if(model != null && StringUtils.isNotBlank(model.getCdid())){
			cdxxModel = cdxxwhService.getModel(model.getCdid());
		}
		if(null != cdxxModel){
			String dwkfkssj = cdxxModel.getDwkfsjkssj(); //���⿪ʼ����ʱ��
			String dwkfjssj = cdxxModel.getDwkfsjjssj(); //���⿪ʼ����ʱ��
			
			String sqkssj = StringUtils.substringAfter(model.getSqsjdkssj(), " "); //���뿪ʼʱ��
			String sqjssj = StringUtils.substringAfter(model.getSqsjdjssj(), " "); //�������ʱ��
			//�Ƚ�ʱ��� ������ʱ��α���Ҫ���ڿ�������ʱ�����
			if(!((DateUtils.compareTimes(sqkssj, dwkfkssj) >= 0) && (DateUtils.compareTimes(dwkfjssj, sqjssj) >= 0))){
				booleaVal = false;
			}
		}
		
		//У�� 2�������������ʱ�����û�б�����������
		if(cdxxModel != null){
			List<HashMap<String , String>> yscdsqList = dao.getYxCdsq(cdxxModel.getCdid(), model.getSqid()); //��ȡ�Ѿ�����ĳ���ʹ���б�
			String sqkssj = model.getSqsjdkssj();
			String sqjssj = model.getSqsjdjssj();
			for (HashMap<String, String> cdsq : yscdsqList) {
				String sqsjdkssj = cdsq.get("sqsjdkssj");
				String sqsjdjssj = cdsq.get("sqsjdjssj");
				if(DateUtils.checkTimepriodDuplicate(sqkssj, sqjssj, sqsjdkssj, sqsjdjssj)){
					booleaVal = false;
					break;
				}
			}
		}
		
		return booleaVal;
	}
	
	/**
	 * 
	 * @����:У������������״̬
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-4-25 ����10:42:11
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean checkSqrSqzt(String xh){
		List<HashMap<String , String>> data = null;
		if(StringUtils.isNotBlank(xh)){
			data = dao.getCdsqInSpcByXh(xh);
		}
		if(data != null && data.size() >= 1){
			return false;
		}
		
		return true;
	}
	
	
	
	/**
	 * @���� ����ʼ��Dao
	 */
	public CdsqService() {
		super();
		super.setDao(new CdsqDao());
	}


	
	/** 
	 * @����:��֤�Ƿ���ύ
	 * @���ߣ�qlm
	 * @���ڣ�2014-5-26 ����11:18:56
	 * @param model
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean checkSfktj(CdsqForm model) {
		String num = dao.checkSfktj(model.getCdid());
		return Integer.valueOf(num) > 0;
	}

}
