/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-7-13 ����11:40:07 
 */
package com.zfsoft.xgxt.khgl.khxmgl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.khgl.khpf.KhpfService;
import common.Globals;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXX����ģ��
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� xiaxia [����:1104]
 * @ʱ�䣺 2015-8-15 ����11:40:07
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class KhxmglService extends SuperServiceImpl<KhxmglForm, KhxmglDao> {
	private static final String DATE_FORMAT = "yyyy-MM-dd";
	private static final String SCZT = "1";//1:ɾ��
	private KhpfService khpfService = new KhpfService();
	private KhxmglDao dao = new KhxmglDao();
	
	/**
	 * 
	 * @����:������Ŀ����
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-8-15 ����03:34:11
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean editKhxm(KhxmglForm model) throws Exception {
		boolean result = true;
		if ("save".equals(model.getType())) {
			String xmid="";
			if(Globals.XXDM_HNCSXY.equals(Base.xxdm)){
				xmid = Base.currXn;
			}else{
				xmid = UniqID.getInstance().getUniqIDHash();
			}
			model.setXmid(xmid);
			result = dao.runInsert(model);
		} else {
			result = dao.runUpdate(model);
		}
		return result;
	}
	public boolean isHave(KhxmglForm model) {
		return dao.isHave(model);
	}
	/**
	 * 
	 * @����:ɾ����Ӧ���ֶ�������
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-8-20 ����02:19:36
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ids
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean delKhxm(String[] ids) throws Exception {
		boolean result=true;
		 dao.runDelete(ids);
		//ɾ�����ֶ�������
		 result= dao.delPfdxSz(ids);
		 //ɾ����Ӧδ�ύ������
		 result=khpfService.delKhpfForXmid(ids);
		 return result;
		
		
	}
	public HashMap<String,String> getKhxmByXmmc(String xmmc) throws Exception {
		return dao.getKhxmByXmmc(xmmc);
	}
	/**
	 * 
	 * @����:�жϿ��˱��Ƿ�ʹ��
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-8-18 ����02:03:17
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
			HashMap<String,String> khxmMap = dao.isUsed(valArr[i]);
			if(null!=khxmMap.get("num")&&!"".equals(khxmMap.get("num"))){
				notDel.append("["+khxmMap.get("xmmc")+"]");
			}
		}
		if(0!=notDel.length()){
			message= "<font color='red'>"+notDel+"</font>"+"�����û����֣�������ɾ����";
		}
		return message;
	}
	/**
	 * @throws Exception
	 * 
	 * @����:��ȡ������Ŀ
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-8-15����04:40:36
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return HashMap<String,String> ��������
	 * @throws
	 */
	public HashMap<String,String> getKhxm(String xmid) throws Exception {
		return dao.getKhxm(xmid);
	}
	
	/**
	 * 
	 * @����:������Ŀ�б�
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-8-15 ����03:25:01
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return
	 * @throws Exception
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getKhxmList() throws Exception {
		return dao.getKhxmList();
	}
	
	public List<HashMap<String,String>> getpfdxXxList(String id) throws Exception {
		return dao.getpfdxXxList(id);
	}
	
	public List<HashMap<String,String>> getPffwList(String pfzid,String khlx) throws Exception {
		return dao.getPffwList(pfzid,khlx);
	}
	/**
	 * 
	 * @����:���ֶ������ñ���
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-8-15 ����05:49:29
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param qqxxList
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean savePfdxSz(KhxmglForm model, List<KhxmglForm> pfdxxxList) throws Exception {
		//ɾ�����ֶ�����Ϣ�ڲ���
		 dao.delPfdxSz(model.getXmid());
		return PfdxSzPlbc(model, pfdxxxList);
	}
	/**
	 * 
	 * @����:���ֶ���������������
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-8-15 ����05:52:07
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param qqxxList
	 * @return
	 * @throws SQLException
	 * boolean �������� 
	 * @throws
	 */
	private boolean PfdxSzPlbc(KhxmglForm model, List<KhxmglForm> pfdxxxList) throws SQLException {
		List<String[]> pfdxList = new ArrayList<String[]>();
		String[] pfdx = null;
		for (KhxmglForm pfdxForm : pfdxxxList) {
			pfdx = new String[7];
			pfdx[0] = model.getXmid();
			pfdx[1] = pfdxForm.getPfzid();
			pfdx[2] = pfdxForm.getSjfwdm();
			pfdx[3] = pfdxForm.getKhbid();
			pfdx[4] = pfdxForm.getSzqz();
			pfdx[5] = pfdxForm.getJsfs();
			pfdx[6] = pfdxForm.getJsfsbfb();
			pfdxList.add(pfdx);
		}
		return dao.PfdxSzPlbc(pfdxList);
	}
}
