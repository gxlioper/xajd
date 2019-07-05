/**
 * <p>Coyright (R) 2014 ��������ɷ����޹�˾��<p>
 */
package com.zfsoft.xgxt.hdgl.hdbljg;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.hdgl.hdblsq.HdblsqshService;
import org.apache.commons.beanutils.BeanUtils;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.transaction.TransactionControl;

/**
 * @className	�� HdbljgService
 * @description	�� TODO(��������������)
 * @author 		��������1282��
 * @date		�� 2018-1-22 ����09:55:30
 * @version 	V1.0 
 */

public class HdbljgService extends SuperServiceImpl<HdbljgForm, HdbljgDao> {
	/**
	 * @description	������
	 * @author 		��������1282��
	 * @date 		��2018-1-22 ����11:07:17
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@TransactionControl
	public boolean runInsert(HdbljgForm model) throws Exception{
		
		String[] hdbqs = model.getHdbqs();/*��ȡ���ǩ*/
		String[] nlbqs = model.getNlbqs();/*��ȡ������ǩ*/
		String jgid = model.getJgid();
		
		boolean result = dao.runInsertNotCommit(model);
		/*������ǩ*/
		if(null != hdbqs && hdbqs.length > 0){			
			result = dao.BatchInsertHdbqx(jgid, hdbqs);
		}
		/*����������ǩ*/
		if(null != nlbqs && nlbqs.length > 0){
			result = dao.BatchInsertNlbqx(jgid, nlbqs);
		}

		return result;
	}
	
	/**
	 * @description	�� ɾ��
	 * @author 		�� ������1282��
	 * @date 		��2018-1-22 ����05:30:25
	 * @param values
	 * @return
	 * @throws Exception
	 */
	@TransactionControl
	public boolean runDeleteJg(String[] values) throws Exception {
		
		boolean result = true;
		/*ɾ�����ǩ������*/
		result = dao.deleteHdbq(values);
		/*ɾ��������ǩ������*/
		result = dao.deleteNlbq(values);
		if(result){
			return dao.deleteHdjg(values);
		}
		return result;
		
	}
	
	/**
	 * @description	�� �޸�
	 * @author 		��������1282��
	 * @date 		��2018-1-23 ����03:23:30
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@TransactionControl
	public boolean runUpdate(HdbljgForm model) throws Exception{
		boolean result = true;
		/*ɾ�����ǩ������*/
		result = dao.deleteHdbq(new String[]{model.getJgid()});
		/*ɾ��������ǩ������*/
		result = dao.deleteNlbq(new String[]{model.getJgid()});
		if(result){
			String[] hdbqs = model.getHdbqs();/*��ȡ���ǩ*/
			String[] nlbqs = model.getNlbqs();/*��ȡ������ǩ*/
			String jgid = model.getJgid();
			if(hdbqs != null && hdbqs.length>0){
				/*������ǩ*/
				result = dao.BatchInsertHdbqx(jgid, hdbqs);
			}
			if(nlbqs != null && nlbqs.length>0){
				/*����������ǩ*/
				result = dao.BatchInsertNlbqx(jgid, nlbqs);
			}
			if(result){
				return dao.runUpdate(model);
			}
		}
		return result;
	}
	
	/**
	 * @description	�� ��������id��ȡ���id
	 * @author 		�� ������1282��
	 * @date 		��2018-1-23 ����03:18:17
	 * @param sqid
	 * @return
	 */
	public String getJgidBySqid(String sqid){
		return dao.getJgidBySqid(sqid);
	}
	
	/**
	 * @description	�� ��ȡmodel
	 * @author 		��������1282��
	 * @date 		��2018-1-23 ����04:14:05
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public HdbljgForm getModelForJg(HdbljgForm model) throws Exception{
		HdbljgForm model2 = getModel(model);
		if(null==model2){
			model2 = new HdbljgForm();
		}
		if(StringUtils.isNotNull(model.getXh())){ //ֻ��������¼ʱ�����вŻ�Я��xh
			model2.setXh(model.getXh());
		}
		HashMap<String, String> modelInfo = dao.getModelInfo(model);
		if(null!=modelInfo){
			if(StringUtils.isNotNull(model.getLy())){
				HdblsqshService service = new HdblsqshService();
				HashMap<String,String> map = service.gethdInfo(model.getHdid());
				map.remove("fjpath");//���滻ԭ�и���
				modelInfo.putAll(map);
			}
			BeanUtils.copyProperties(model2, StringUtils.formatData(modelInfo));
		}
		/*ѧ������*/
		if(StringUtils.isNotNull(modelInfo.get("xqmc"))){
			model2.setXqmc(modelInfo.get("xqmc"));
		}
		/*���������*/
		if(StringUtils.isNotNull(modelInfo.get("hdlxmc"))){
			model2.setHdlxmc(modelInfo.get("hdlxmc"));
		}
		/*������������*/
		if(StringUtils.isNotNull(modelInfo.get("jzlxmc"))){
			model2.setJzlxmc(modelInfo.get("jzlxmc"));
		}
		if(StringUtils.isNotNull(modelInfo.get("zxkclxmc"))){
			model2.setZxkclxmc(modelInfo.get("zxkclxmc"));
		}
		/*���ǩ*/
		if(StringUtils.isNotNull(modelInfo.get("hdbq"))){
			model2.setHdbq(modelInfo.get("hdbq"));
			model2.setHdbqs(modelInfo.get("hdbq").split(","));
			model2.setHdbqmc(modelInfo.get("hdbqmc"));
		}
		/*������ǩ*/
		if(StringUtils.isNotNull(modelInfo.get("nlbq"))){
			model2.setNlbq(modelInfo.get("nlbq"));
			model2.setNlbqs(modelInfo.get("nlbq").split(","));
			model2.setNlbqmc(modelInfo.get("nlbqmc"));
		}
		return model2;
	}
	
	/**
	 * @����: ��ȡ���ǩ�б�
	 * @���ߣ� MengWei[���ţ�1186]
	 * @���ڣ� 2018-6-6 ����10:12:21
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getHdbqList(){
		return dao.getHdbqList();
	}
	
	/**
	 * @����: ��ȡ���������б�
	 * @���ߣ� MengWei[���ţ�1186]
	 * @���ڣ� 2018-6-6 ����10:12:21
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getJzlxList(){
		return dao.getJzlxList();
	}
	public List<HashMap<String,String>> getZxkcDmList(){
		return dao.getZxkcDmList();
	}
	/**
	 * @����: ��ȡ������ǩ�б�
	 * @���ߣ� MengWei[���ţ�1186]
	 * @���ڣ� 2018-6-6 ����10:12:21
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getAbilityLabelList(){
		return dao.getAbilityLabelList();
	}
}
