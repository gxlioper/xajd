/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-10-22 ����11:48:50 
 */  
package com.zfsoft.xgxt.wjcf.cfsb;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import xgxt.action.Base;
import xgxt.utils.String.StringUtils;
import xgxt.utils.date.DateUtils;
import xsgzgl.wjcf.general.WjcfGeneralForm;

import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.DateTranCnDate;
import com.zfsoft.xgxt.base.util.FreeMarkerUtil;
import com.zfsoft.xgxt.base.util.HtmlUtil;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.base.util.DateTranCnDate.FomartDateType;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.rcsw.qjgl.BaseDbcz;
import com.zfsoft.xgxt.wjcf.cflbdmwh.CflbdmwhForm;
import com.zfsoft.xgxt.wjcf.cflbdmwh.CflbdmwhService;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: Υ�ʹ��ֹ���ģ��
 * @�๦������: (�����ϱ�����) 
 * @���ߣ�������[����:913]
 * @ʱ�䣺 2013-10-22 ����11:48:50 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class CfsbglService extends SuperServiceImpl<CfsbglForm, CfsbglDao> {
	
	private CfsbglDao dao=new CfsbglDao();
	private ShlcInterface shlc = new CommShlcImpl();
	public static final String SUBMIT = "submit";
	public static final String BACK = "back";
	
	public CfsbglService(){
		this.setDao(dao);
	}

	/** 
	 * @����:(���洦���ϱ�)
	 * @���ߣ�cmj[���ţ�913]
	 * @���ڣ�2013-10-22 ����04:04:28
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * String �������� 
	 * @throws 
	 */
	public String save(CfsbglForm model) throws Exception{
		String guid = UniqID.getInstance().getUniqIDHash();
		model.setCfid(guid);
		
		String splcid=getSplcid(model);
		if (null == splcid||"".equalsIgnoreCase(splcid)) {
			return MessageKey.SYS_SELECT_SHLC_FAIL;
		}
		model.setSplcid(splcid);
		//���ó�ʼ���״ֵ̬
		if(splcid!=null&&!"".equalsIgnoreCase(splcid)){
			if(model.getType().equals("submit")){
				model.setSbjg(Constants.YW_SHZ);//�����
			}else{
				model.setSbjg(Constants.YW_WTJ);//δ�ύ
			}
		}
		boolean result = super.runInsert(model);
		if(result){
			if (result && SUBMIT.equalsIgnoreCase(model.getType())) {
				result = shlc.runSubmit(guid, splcid,model.getXh(),"wjcf_cfsh.do?method=cxCfshList","wjcf_cfsbgl.do?method=cxCfsbList");
			}
		}
		
		return String.valueOf(result);
	}

	
	/** 
	 * @����:(��ȡ��������id)
	 * @���ߣ�������[���ţ�913]
	 * @���ڣ�2013-10-22 ����04:18:10
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * String �������� 
	 * @throws 
	 */
	private String getSplcid(CfsbglForm model) {
		
		return dao.getSplcid(model);
	}

	/**
	 * 
	 * @����:(�����ϱ��޸ı���)
	 * @���ߣ�cmj[���ţ�913]
	 * @���ڣ�2013-10-22 ����03:40:47
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
	 * @throws
	 */
	public String updateSave(CfsbglForm model) throws Exception{
		String cfid=model.getCfid();
		
		CfsbglForm modelGet = getModel(cfid);

		String splcid = "";
		
		// �˻ؼ�¼ȡ�������
		if(Constants.YW_YTH.equals(modelGet.getSbjg())){
			model.setCflbdm(modelGet.getCflbdm());
			splcid = modelGet.getSplcid();
		}else{
			splcid = getSplcid(model);
		}
		
		if (null == splcid||"".equalsIgnoreCase(splcid)) {
			return MessageKey.SYS_SELECT_SHLC_FAIL;
		}
		model.setSplcid(splcid);
		
		//���ó�ʼ���״ֵ̬
		if(SUBMIT.equalsIgnoreCase(model.getType())){
			model.setSbjg(Constants.SHZ); 
		}
		boolean result = dao.runUpdate(model);
		if(result && SUBMIT.equalsIgnoreCase(model.getType())){
			result = shlc.runSubmit(cfid, splcid,model.getXh(),"wjcf_cfsh.do?method=cxCfshList","wjcf_cfsbgl.do?method=cxCfsbList");
		
		}
		return String.valueOf(result);
	}
	
	
	/**
	 * 
	 * @����:(�����ϱ�ɾ��)
	 * @���ߣ�cmj[���ţ�913]
	 * @���ڣ�2013-10-22 ����03:40:47
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
	 * @throws
	 */
	@Override
	public int runDelete(String[] values) throws Exception {
		String[] ids=getCancelIds(values);
		if(ids.length==0)
			return 0;
		int result=dao.runDelete(ids);
		for (String id : ids) {
			if(shlc.deleteShjl(id)){
				BaseDbcz dbcz=new BaseDbcz();
				dbcz.setGnmkMc("Υ�ʹ������");
				dbcz.remove(new String[]{id});
			}
		}
		return result;
	}
	
	/**
	 * @throws Exception  
	 * @����:(��ȡ����ɾ����id)
	 * @���ߣ�������[���ţ�913]
	 * @���ڣ�2013-10-29 ����07:01:49
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param values
	 * @return
	 * String[] �������� 
	 * @throws 
	 */
	private String[] getCancelIds(String[] values) throws Exception {
		
		return dao.getCancelIds(values);
	}
	
	/**
	 * 
	 * @����:TODO(�ύ����)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2013-12-7 ����03:07:01
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean submitCfsb(CfsbglForm model) throws Exception{
		
		model.setSbjg(Constants.YW_SHZ);
		boolean resultCfsb = dao.updateCfsbsbjg(model);
		boolean result = false;
		if(resultCfsb ){
			//�����������
			result = shlc.runSubmit(model.getCfid(), model.getSplcid(),model.getXh(),"wjcf_cfsh.do?method=cxCfshList","wjcf_cfsbgl.do?method=cxCfsbList");
		}
		//if(!BACK.equalsIgnoreCase(model.getReturnflag())){
			return result;
		/*}else{
			return resultCfsb;
		}*/
		
	}
	/**
	 * 
	 * @����:TODO(ȡ����¼)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2013-12-7 ����03:07:14
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ywid
	 * @param lcid
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean cancelRecord(String ywid,String lcid) throws Exception{
		return shlc.firstStepCancle(ywid,lcid);
	}
	/**
	 * 
	 * @����:TODO(���¼�¼)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2013-12-7 ����03:07:29
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean updateCfsbModel(CfsbglForm model) throws Exception {
			boolean resultCfsb = dao.updateCfsbsbjg(model);
			return resultCfsb;
	}

	/** 
	 * @����:��֤�����ڽ�������Ƿ���� ����֤������ѧ�š�������𡢴���ʱ��
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-5-14 ����04:31:51
	 * @param myForm
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean checkExistCfjg(CfsbglForm myForm) {
		
		String num = dao.checkExistCfjg(myForm);
		return Integer.valueOf(num) > 0;
	}
	public boolean checkExistCfwh(CfsbglForm myForm) {
		
		String num = dao.checkExistCfwh(myForm);
		return Integer.valueOf(num) > 0;
	}
	
	/** 
	 * @����:��֤�������ϱ��ͽ���⵱���Ƿ���� ����֤������ѧ�š�������𡢴���ʱ��
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-5-14 ����04:31:51
	 * @param myForm
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean checkExistCfsbjg(CfsbglForm myForm) {
		String num = dao.checkExistCfsbjg(myForm);
		return Integer.valueOf(num) > 0;
	}
	
	/**
	 * ��ȡ������Ϣ����ӡ��ר�ã�
	 */
	public HashMap<String,String> getDjbModel(String cfid) throws Exception{
		return dao.getDjbModel(cfid);
	}
	
	/**
	 * ����ID��ȡ������Ϣ
	 */
	public List<HashMap<String, String>> getCfjdXxByIds(String[] ids) {
		return dao.getCfjdXxByIds(ids);
	}
	
	/**
	 * ���ݴ������ϲ����ֽ����¼�������������ͬ����ͬһList��
	       ��List�����Ϊkey����Map
	 */
	public Map<String, List<HashMap<String, String>>> getCfjdListMap(List<HashMap<String,String>> cfjdList) {
		
		Map<String, List<HashMap<String, String>>> cfjdListMap = new HashMap<String, List<HashMap<String, String>>>();
		
		for(HashMap<String,String> cfjg:cfjdList){
			String key = cfjg.get("cflbmc")+cfjg.get("cfyymc");
			List<HashMap<String,String>> list = cfjdListMap.get(key);
			if(list==null){
				list = new ArrayList<HashMap<String, String>>();
				cfjdListMap.put(key, list);
			}
			list.add(cfjg);
		}
		return cfjdListMap;
	}
	
	/**
	 * @����: ���ݺϲ���¼���map������word�ļ����飺���־�����
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2017-8-17 ����04:44:22
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param cfjgListMap
	 * @return
	 * File[] �������� 
	 * @throws
	 */
	public File[] getCfjdsFiles(Map<String, List<HashMap<String, String>>> cfjgListMap) {
		
		List<File> fileList = new ArrayList<File>();
		
		Collection<List<HashMap<String, String>>> collection = cfjgListMap.values();
		for(List<HashMap<String, String>> list:collection){
			HashMap<String,Object> data = new HashMap<String,Object>();
			/*ѧ���б�*/
			List<String> xhList = new LinkedList<String>();
			/*�����б�*/
			List<String> nameList = new LinkedList<String>();
			/*�ı�ת��*/
			for(int i=0;i<list.size();i++){
				HashMap<String, String> map = list.get(i);
				map.put("cfwh", HtmlUtil.xmlZy(map.get("cfwh")));
				map.put("cflbmc", HtmlUtil.xmlZy(map.get("cflbmc")));
				map.put("cfyymc", HtmlUtil.xmlZy(map.get("cfyymc")));
				
				if(StringUtils.isNotNull(map.get("xm"))){
					nameList.add(map.get("xm"));
				}
				if(StringUtils.isNotNull(map.get("xh"))){
					xhList.add(map.get("xh"));
				}
				String fh = "��";
				String jh = "��";
				if(i != list.size() - 1){
					map.put("fh",fh);
				}else{
					map.put("fh",jh);
				}
			}
			//�����˰���������
			int count = list.size();
			data.put("count", count);
			//�����˴�д
			String zhNumber = StringUtils.formatChNum(String.valueOf(count));
			zhNumber = "��".equals(zhNumber)?"��":zhNumber;
			data.put("zhNumber", zhNumber);
			//�����������
			String cflbmc = list.get(0).get("cflbmc");
			data.put("cflbmc", cflbmc);
			//����ԭ������
			String cfyymc = list.get(0).get("cfyymc");
			data.put("cfyymc", cfyymc);
			//�����ĺ�
			String cfwh = list.get(0).get("cfwh");
			if(cfwh.matches("^[0-9]{8}$")){
				data.put("cfwh", "��"+cfwh.substring(0,4)+"��"+cfwh.substring(4,8));
			}else{
				data.put("cfwh",cfwh);
			}
			//����ʱ�䡾����ʱ�䡿
			String cfsj = DateTranCnDate.fomartDateToCn(list.get(0).get("cfsj"),FomartDateType.day);
			data.put("cfsj", cfsj);
			/*ѧ��*/
			String xhs = org.apache.commons.lang.StringUtils.join(xhList, '��');
			//����
			String names = org.apache.commons.lang.StringUtils.join(nameList, '��');
			data.put("names", names);
			//ϵͳ��ǰʱ��
			String dyrq = DateTranCnDate.fomartDateToCn(new SimpleDateFormat("yyyy-MM-dd").format(new Date()),FomartDateType.day);
			data.put("dyrq", dyrq);
			
			data.put("cfjgList", list);
			String mbmc = "cfjds_" + Base.xxdm + ".xml";
			String fileName = "[" + cflbmc + "]" + "-" + "��" + names + "��";
			fileName += "���־�����";
			File wordFile = FreeMarkerUtil.getWordFile(data, "classpath://templates//wjcf", mbmc, fileName);
			fileList.add(wordFile);
		}
		return fileList.toArray(new File[]{});
	}

    public HashMap<String,String> getjcid(String cfid) {
		return dao.getjcid(cfid);
    }
}
