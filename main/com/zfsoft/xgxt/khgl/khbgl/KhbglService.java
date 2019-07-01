/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-7-13 ����11:40:07 
 */
package com.zfsoft.xgxt.khgl.khbgl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import xgxt.form.User;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.khgl.khbgl.khnrgl.KhnrglDao;
import com.zfsoft.xgxt.khgl.khbgl.khnrgl.KhnrglForm;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXX����ģ��
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� xiaxia [����:1104]
 * @ʱ�䣺 2015-8-11 ����11:40:07
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class KhbglService extends SuperServiceImpl<KhbglForm, KhbglDao> {
	private static final String DATE_FORMAT = "yyyy-MM-dd";
	private static final String SCZT = "1";//1:ɾ��
	private static final String KHB_TY="2";//���˱�Ĭ��ͣ��
	private KhbglDao dao = new KhbglDao();
	private KhnrglDao khnrDao = new KhnrglDao();
	/**
	 * 
	 * @����:�жϿ��˱��Ƿ��Ѵ���
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-8-11 ����03:15:33
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean isHave(KhbglForm model) {
		return dao.isHave(model);
	}
	/**
	 * 
	 * @����:�жϿ��˱��Ƿ�ʹ��
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-8-18 ����11:36:26
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param values
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String isUsed(String values) {
		String message=null;
		String[] valArr = values.split(",");
		StringBuffer notDel = new StringBuffer();
		for (int i = 0; i < valArr.length; i++) {
			HashMap<String,String> khbMap = dao.isUsed(valArr[i]);
			if(null!=khbMap.get("num")&&!"".equals(khbMap.get("num"))){
				notDel.append("["+khbMap.get("khbmc")+"]");
			}
		}
		if(0!=notDel.length()){
			message= "<font color='red'>"+notDel+"</font>"+"�ѱ�ʹ�ã�������ɾ����";
		}
		return message;
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����:���˱�����״̬����
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-8-11 ����03:15:33
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean qySz(String[] ids,String qyzt) throws Exception {
		List<String[]> KhbList = new ArrayList<String[]>();
		String[] Khb = null;
		for (int i = 0; i < ids.length; i++) {
			Khb = new String[2];
			Khb[0]=qyzt;
			Khb[1]=ids[i];
			KhbList.add(Khb);
		}
		return dao.qySz(KhbList);
	}
	/**
	 * 
	 * @����:ɾ�����˱�
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-8-14 ����12:00:57
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ids
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean delKhb(String[] ids) throws Exception {
		 dao.runDelete(ids);
		//ɾ����������
		return dao.delKhnr(ids);
	}

	/**
	 * 
	 * @����:���˱���
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-8-11 ����03:34:11
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean editKhb(KhbglForm model) throws Exception {
		boolean result = true;
		if ("save".equals(model.getType())) {
			String Khbid = UniqID.getInstance().getUniqIDHash();
			model.setKhbid(Khbid);
			model.setSfqy(KHB_TY);//���˱�Ĭ��ͣ��
			model.setCjsj(GetTime.getTimeByFormat(DATE_FORMAT));
			result = dao.runInsert(model);
		} else {
			model.setXgsj(GetTime.getTimeByFormat(DATE_FORMAT));
			result = dao.runUpdate(model);
		}
		return result;
		
	}
	/**
	 * @throws Exception
	 * 
	 * @����:��ȡ���˱�
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-8-11����04:40:36
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return HashMap<String,String> ��������
	 * @throws
	 */
	public HashMap<String,String> getKhb(String khbid) throws Exception {
		return dao.getKhb(khbid);
	}
	public List<HashMap<String, String>> getKhnrList(KhbglForm model)
			throws Exception {
	return dao.getKhnrList(model);
	}
	/**
	 * 
	 * @����:���˱��б�
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-8-11 ����03:25:01
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return
	 * @throws Exception
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getKhbList(String khdxid) throws Exception {
		return dao.getKhbList(khdxid);
	}
	
	public List<HashMap<String, String>> getKhbList(KhbglForm model, User user)
	throws Exception{
		return null;
		
	}
	/**
	 * 
	 * @����:���˱���
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-8-14 ����10:08:15
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean khbfz(KhbglForm model) throws Exception {
		String fzid = UniqID.getInstance().getUniqIDHash();
		KhbglForm fzForm = new KhbglForm();
		KhnrglForm khnrForm = new KhnrglForm();
		khnrForm.setKhbid(model.getKhbid());
		KhbglForm khbForm = dao.getModel(model);//�����ƿ��˱�
		List<HashMap<String, String>> khnrList = khnrDao.getKhnrList(model.getKhbid());//�����ƿ��˱�������
		BeanUtils.copyProperties(fzForm, StringUtils.formatData(khbForm));
		fzForm.setKhbid(fzid);
		fzForm.setKhbmc(model.getKhbmc());
		dao.runInsert(fzForm);
		List<String[]> fznrList= new ArrayList<String[]>();
		String[] fznrArr=null;
		for (HashMap<String, String> khnrMap : khnrList) {
			fznrArr=new String[9];
			fznrArr[0]=fzid;
			fznrArr[1]=khnrMap.get("yjzb");
			fznrArr[2]=khnrMap.get("ejzb");
			fznrArr[3]=khnrMap.get("khnr");
			fznrArr[4]=khnrMap.get("fzlx");
			fznrArr[5]=khnrMap.get("fzzgf");
			fznrArr[6]=khnrMap.get("fzzdf");
			fznrArr[7]=khnrMap.get("xssx");
			fznrArr[8]=khnrMap.get("pflx");
			fznrList.add(fznrArr);
		}
		return dao.khnrFz(fznrList);
	}
}
