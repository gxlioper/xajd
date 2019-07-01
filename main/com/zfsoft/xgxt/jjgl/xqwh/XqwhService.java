/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-8-27 ����04:22:03 
 */  
package com.zfsoft.xgxt.jjgl.xqwh;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.jjgl.common.JjxqztEnum;
import com.zfsoft.xgxt.jjgl.jjxq.JjxqForm;
import com.zfsoft.xgxt.jjgl.jjxq.JjxqService;
import com.zfsoft.xgxt.jjgl.xsxqsq.XsxqsqForm;
import com.zfsoft.xgxt.jjgl.xsxqsq.XsxqsqService;
import org.apache.commons.lang.StringUtils;
import xgxt.utils.date.DateUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2014-8-27 ����04:22:03 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XqwhService extends SuperServiceImpl<XqwhForm, XqwhDao> {

	/**
	 * 
	 * @����:��������ID��ȡ�ҽ�������Ϣ��������ǰ��״̬�ͼҽ���Ա��Ϣ
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-8-29 ����08:21:55
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xqid
	 * @return
	 * @throws Exception
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String , String> getXqxxDetailsByXqid(String xqid) throws Exception{
		return dao.getXqxxDetailsByXqid(xqid);
	}
	
	/**
	 * 
	 * @����:�ڸ������Ƽ��
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-8-28 ����03:46:41
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean checkZgs(String xh) throws Exception{
		return dao.checkZgs(xh);
	}
	
	/**
	 * 
	 * @����:��ȡ�ҽ�ѧ���������б�
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-8-28 ����11:36:58
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xqid
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String , String>> getXqXsSqList(String xqid)throws Exception {
		return dao.getXqXsSqList(xqid);
	}
	
	
	/**
	 * 
	 * @����:�ֹ��ҽ���ʦ����
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-8-28 ����03:26:56
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String , String>  assignManual(XqwhForm model) throws Exception{
		//ִ�н��
		HashMap<String , String> data = new HashMap<String, String>();
		//�������Ϸ���
		if(model == null || StringUtils.isBlank(model.getXqid()) || StringUtils.isBlank(model.getXh())){
			data.put("isSuccess", "0");
			data.put("message", "���������������ύ��");
			return data;
		}
		//��������״̬�Ϸ���
		HashMap<String, String> modelMap = this.getXqxxDetailsByXqid(model.getXqid());
		
		if(!StringUtils.equals("1", modelMap.get("shzt"))){
			data.put("isSuccess", "0");
			data.put("message", "�üҽ�������˲�ͨ�������ܷ���ҽ���ʦ��");
			return data;
		}else if(!StringUtils.equals(JjxqztEnum.WPC.getCode(), modelMap.get("jjzt"))){
			data.put("isSuccess", "0");
			data.put("message", "�üҽ�����״̬���ǡ�δ�ɳ���״̬�����ܷ���ҽ���ʦ��");
			return data;
		}else if(xgxt.utils.String.StringUtils.isNotNull(modelMap.get("xssqid"))){
			data.put("isSuccess", "0");
			data.put("message", "�üҽ������ѱ����룬���ܷ���ҽ���ʦ��");
			return data;
		}

		//��������˵ĵ����ڸ���
		boolean checekRs = checkZgs(model.getXh());
		if(!checekRs){
			data.put("isSuccess", "0");
			data.put("message", "��ѧ���ڸ����Ѵﵽ���ޣ���ѡ������ѧ����");
			return data;
		}

		//1.��������״̬
		JjxqForm jjxqModel = new JjxqForm();
		jjxqModel.setXqid(modelMap.get("xqid"));
		//����Ϊ����Э����
		jjxqModel.setJjzt(JjxqztEnum.DJXYS.getCode());
		//�������ݿ�
		boolean isSuccess = new JjxqService().runUpdate(jjxqModel);
		
		//��Ҫ���жϸ�ѧ���Ƿ��Ѿ�����üҽ�
		//����Ѿ�������,��ֱ���޸Ķ�Ӧ�������¼
		//�����½�һ����¼
		XsxqsqService xsxqsqService = new XsxqsqService();
		//�½�һ��ѧ���ҽ������¼��״̬Ϊ��Ч�����ͨ��
		XsxqsqForm xsxqsqModel = new XsxqsqForm();
		xsxqsqModel.setSqid(UniqID.getInstance().getUniqIDHash().toUpperCase());
		xsxqsqModel.setXh(model.getXh());
		xsxqsqModel.setXqid(model.getXqid());
		String currTime = DateUtils.getCurrTime();
		xsxqsqModel.setSqsj(currTime);
		xsxqsqModel.setKssj(currTime);
		xsxqsqModel.setZt("1");
		xsxqsqModel.setShzt("1");
		isSuccess = xsxqsqService.runInsert(xsxqsqModel);

		if(isSuccess){
			data.put("isSuccess", "1");
			data.put("message", "�����ɹ�!");
		}else{
			data.put("isSuccess", "0");
			data.put("message", "����ʧ��!");
		}
		
		return data;
	}
	
	/**
	 * 
	 * @����:�ҽ���ʦ����
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-8-28 ����03:26:56
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String , String>  assign(XqwhForm model) throws Exception{
		//ִ�н��
		HashMap<String , String> data = new HashMap<String, String>();
		//�������Ϸ���
		if(model == null || StringUtils.isBlank(model.getXqid()) || StringUtils.isBlank(model.getXh())){
			data.put("isSuccess", "0");
			data.put("message", "���������������ύ��");
			return data;
		}
		
		//��������״̬�Ϸ���
		JjxqService jjxqService = new JjxqService();
		HashMap<String, String> modelMap = jjxqService.getModelMap(model.getXqid());
		
		if(!StringUtils.equals("1", modelMap.get("shzt"))){
			data.put("isSuccess", "0");
			data.put("message", "�üҽ�������˲�ͨ�������ܷ���ҽ���ʦ��");
			return data;
		}else if(!StringUtils.equals(JjxqztEnum.WPC.getCode(), modelMap.get("jjzt"))){
			data.put("isSuccess", "0");
			data.put("message", "�üҽ�����״̬���ǡ�δ�ɳ���״̬�����ܷ���ҽ���ʦ��");
			return data;
		//����Ƿ�����ʦ���ڽ�ѧ��
		}else if(Integer.valueOf(modelMap.get("sfjjz")) > 0){
			data.put("isSuccess", "0");
			data.put("message", "�üҽ������Ѵ��ڽ�ѧ�У����ܷ���ҽ���ʦ��");
			return data;
		}
		
		//��������˵ĵ����ڸ���
		boolean checekRs = checkZgs(model.getXh());
		if(!checekRs){
			data.put("isSuccess", "0");
			data.put("message", "��ѧ���ڸ����Ѵﵽ���ޣ���ѡ������ѧ����");
			return data;
		}
		
		//1.��������״̬
		JjxqForm jjxqModel = jjxqService.getModel(modelMap.get("xqid"));
		//����Ϊ�Խ�״̬
		jjxqModel.setJjzt(JjxqztEnum.DJXYS.getCode());
		//�������ݿ�
		jjxqService.runUpdate(jjxqModel);
		
		//2.���õ�ǰѧ��Ϊͨ��״̬������ѧ��Ϊ��ͨ��״̬
		XsxqsqService xsxqsqService = new XsxqsqService();
		XsxqsqForm xsxqsqModel = xsxqsqService.getModelByXqidAndXh(model.getXqid(), model.getXh());
		xsxqsqModel.setShzt("1");
		xsxqsqModel.setKssj(DateUtils.getCurrTime());
		xsxqsqService.runUpdate(xsxqsqModel);
		
		
		//3.�޸����������ߵ�״̬Ϊ��ͨ��
		boolean isSuccess = dao.updateForAssign(model.getXqid(), model.getXh());
		
		
		if(isSuccess){
			data.put("isSuccess", "1");
			data.put("message", "�����ɹ�!");
			
		}else{
			data.put("isSuccess", "0");
			data.put("message", "����ʧ��!");
		}
		return data;
	}
	
	
	/**
	 * 
	 * @����:�޸ļҽ�״̬
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-8-28 ����03:26:56
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String , String>  changeJjzt(XqwhForm model) throws Exception{
		JjxqService jjxqService = new JjxqService();
		//ִ�н��
		HashMap<String , String> data = new HashMap<String, String>();
		//�������Ϸ���
		if(model == null || StringUtils.isBlank(model.getXqid()) || StringUtils.isBlank(model.getJjzt())){
			data.put("isSuccess", "0");
			data.put("message", "���������������ύ��");
			return data;
		}
		
		String xqid = model.getXqid();
		String jjzt = model.getJjzt();
		
		//1.��ȡ������ϸ��Ϣ
		HashMap<String , String> xqDetails = getXqxxDetailsByXqid(xqid);
		
		//�������״̬��ȣ���ֱ�ӷ��سɹ���
		if(StringUtils.equals(xqDetails.get("jjzt"),jjzt)){
			data.put("isSuccess", "1");
			data.put("message", "�ҽ�״̬���óɹ�!");
			return data;
		}
		/*******************************************************/

		//�رռҽ̣�
			//��Ҫ�жϹرյļҽ��Ƿ��б����룬�����Ƿ�ͨ��
				//���������������ͨ����ֻ��Ҫ�ļҽ�״̬
				//�������뵫δͨ������Ҫ�ļҽ�״̬��Ҫ���������Ϊ��ͨ��

		//�˼ҽ̺ͽ�Э�����ʱ��ѧ��������״̬�϶�����ͨ���� ��Ȼ����״̬��ͨ���Ͳ����ٶ��� ����ֻ�ı�ҽ�״̬ ѧ���������ҵļҽ̸�λ�п���
		//�˼ҽ̣�һ������Ϊ�Խ�ʧ��
			//��Ӧ�ҽ�״̬��Ϊδ�ɳ���ѧ��������ֱ���˻ص�������
			//�˼ҽ̺��Լ������ļҽ��г��ǣ���Ӧ�ҽ����˻� ��ǰ��������
			//�˼ҽ̺���˿����ļҽ��г��ǣ���Ӧ�ҽ�δ���� ��ǰ��������
			//�����˼ҽ̺󣺼ҽ̸�λ���Ƿ񿴵�  �Ƿ����ٴ�����

		//��Э���飺
			//��Ӧ�ҽ�״̬��Ϊ�ѽ�Э����
		
		//�����ͬ�������
		// ��һ:�����Ҫ�޸�״̬�����Խ̻�����Ч������Ҫ����ǰ�ҽ�ѧ�������¼�� ZT �ֶθ�Ϊ 0 ������Ч״̬��,ͬʱҲҪ������״̬��Ϊ��Ӧֵ
		// �ڶ��������Ҫ�޸ĵ�״̬Ϊ������ѧ�����߽�����ֻ��Ҫ�������״̬��Ϊ��Ӧֵ���ɣ�
		//
		if(StringUtils.equals(jjzt, JjxqztEnum.WPC.getCode())){
			String xssqid = xqDetails.get("xssqid");
			if(StringUtils.isNotBlank(xssqid)){
				XsxqsqService xsxqsqService = new XsxqsqService();
				XsxqsqForm xsxqsqModel = xsxqsqService.getModel(xssqid);
				//������Ч
				xsxqsqModel.setZt("0");
				xsxqsqService.runUpdate(xsxqsqModel);
			}
			JjxqForm jjxqModel = jjxqService.getModel(xqid);
			jjxqModel.setJjzt(JjxqztEnum.WPC.getCode());
			jjxqService.runUpdate(jjxqModel);
			
		}else if(StringUtils.equals(jjzt, JjxqztEnum.YGB.getCode())){
			JjxqForm jjxqModel = jjxqService.getModel(xqid);
			jjxqModel.setJjzt(JjxqztEnum.YGB.getCode());
			jjxqService.runUpdate(jjxqModel);
			
			String xssqid = xqDetails.get("xssqid");
			if(StringUtils.isNotBlank(xssqid)){
				XsxqsqService xsxqsqService = new XsxqsqService();
				XsxqsqForm xsxqsqModel = xsxqsqService.getModel(xssqid);
				//������Ч
				xsxqsqModel.setZt("0");
				xsxqsqService.runUpdate(xsxqsqModel);
			}
		}else if(StringUtils.equals(jjzt, JjxqztEnum.DJXYS.getCode()) ||
				StringUtils.equals(jjzt, JjxqztEnum.YGB.getCode()) ||
				StringUtils.equals(jjzt, JjxqztEnum.YJXYS.getCode())){
			JjxqForm jjxqModel = jjxqService.getModel(xqid);
			jjxqModel.setJjzt(jjzt);
			jjxqService.runUpdate(jjxqModel);
		}
		/**************************************************************/
		///����һ��״̬��ע��Ϣ///
		JjxqForm jjxqModel = jjxqService.getModel(xqid);
		jjxqModel.setZtbz(model.getZtbz());
		jjxqService.runUpdate(jjxqModel);
		///����һ��״̬��ע��Ϣ///
		
		
		data.put("isSuccess", "1");
		data.put("message", "�ҽ�״̬���óɹ�!");
		return data;
	}
	
	
	
	/**
	 * 
	 * @����:��ȡ�û�Ͷ����Ϣ
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-8-29 ����05:09:43
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xqid
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String , String>> getTsxxList(String xqid) throws Exception{
		return dao.getTsxxList(xqid);
	}
	
	/**
	 * 
	 * @����:��ȡ������Ϣ
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-8-29 ����05:39:39
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xqid
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public HashMap<String , String> getPjxxList(String xqid) throws Exception{
		return dao.getPjxxList(xqid);
	}
	
	
	/**
	 * 
	 * @����:����Ͷ����Ϣ
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-8-30 ����10:47:31
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean updateTsxx(XqwhForm model) throws Exception{
		return dao.updateTsxx(model) > 0;
	}

	/**
	 * �ҽ��������ӣ��ı���
	 * @param xqwhForm
	 * @return
	 */
    public boolean xqwhSaveForAdd(XqwhForm xqwhForm) throws Exception {

    	xqwhForm.setXqid(dao.getXqidAsKey());
    	xqwhForm.setSqsj(DateUtils.getCurrTime());
    	xqwhForm.setShzt("1");
    	xqwhForm.setJjzt("0");
		return dao.xqwhSaveForAdd(xqwhForm);
    }

	/**
	 * ��ѯ�����ҽ�������Ϣ
	 * @param xqid
	 * @return
	 */
	public Map<String,String> getXqwhMap(String xqid) {

    	return dao.getXqwhMap(xqid);
	}

	/**
	 * �ҽ������޸ģ��ı���
	 * @param xqwhForm
	 * @return
	 */
	public boolean xqwhSaveForEdit(XqwhForm xqwhForm) throws Exception {

		return dao.xqwhSaveForEdit(xqwhForm);
	}

	/**
	 * �ҽ�����ɾ��
	 * @param ids
	 * @return
	 */
	public int xqwhDel(String[] ids) throws Exception {

		return dao.xqwhDel(ids);
	}

	/**
	 * ���¼ҽ�״̬
	 * @param xqwhForm
	 * @return
	 */
	public boolean runUpdateJjzt(XqwhForm xqwhForm) throws Exception {

		return dao.runUpdateJjzt(xqwhForm);
	}

	/**
	 * ���¼ҽ�״̬�����һ�����ͨ��֮ǰ��״̬
	 * @param xqid
	 * @return
	 */
	public boolean updateJjztForCancel(String xqid) throws Exception {

		return dao.updateJjztForCancel(xqid);
	}

}
