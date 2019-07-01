/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-4-29 ����03:37:22 
 */  
package com.zfsoft.xgxt.rcsw.cdgl.jg;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import xgxt.utils.date.DateUtils;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.rcsw.cdgl.cdxxwh.CdxxwhForm;
import com.zfsoft.xgxt.rcsw.cdgl.cdxxwh.CdxxwhService;
import com.zfsoft.xgxt.rcsw.cdgl.sq.CdsqForm;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ճ��������ģ��
 * @�๦������: �ճ�����������Service
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2014-4-29 ����03:37:22 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class CdjgService extends SuperServiceImpl<CdjgForm, CdjgDao> {

	/**
	 * @���� ������Ϣ����ӿ�
	 */
	private CdxxwhService cdxxwhService = new CdxxwhService();
	
	public CdjgService(){
		setDao(new CdjgDao());
	}
	
	/**
	 * 	��ȡ���ؽ����Ϣ			
	 * 
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-4-29 ����10:18:01
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param sqid
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String , String> getCdjgxx(String jgid){
		if(StringUtils.isNotBlank(jgid)){
			return dao.getCdjgxx(jgid);
		}
		return null;
	}
	/**
	 * 
	 * @����:��������idɾ�����������
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-4-30 ����09:11:55
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param sqid
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean deleteJgBySqid(String sqid) throws Exception{
		return dao.deleteJgBySqid(sqid);
	}
	
	
	
	/**
	 * @throws Exception 
	 * 
	 * @����:У�������ʱ��� > 1.�����������ʱ�����û�б����������룡
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-4-25 ����10:41:10
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean checkSqSjd(CdjgForm model) throws Exception{
		boolean booleaVal = true;
		
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
		
		//�����������ʱ�����û�б�����������
		if(cdxxModel != null){
			List<HashMap<String , String>> yscdsqList = dao.getYxCdsq(cdxxModel.getCdid()); //��ȡ�Ѿ�����ĳ���ʹ���б�
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

	public boolean updatePjxx(CdjgForm model) throws Exception {
		return dao.updatePjxx(model);
	}

	public boolean isExistPj(CdjgForm model) {
		return dao.isExistPj(model);
	}
}
