/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-7-13 ����11:40:07 
 */
package com.zfsoft.xgxt.khgl.khdxgl.pfzgl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.UniqID;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXX����ģ��
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� xiaxia [����:1104]
 * @ʱ�䣺 2015-8-10 ����11:40:07
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class PfzglService extends SuperServiceImpl<PfzglForm, PfzglDao> {
	private static final String DATE_FORMAT = "yyyy-MM-dd hh24:mm:ss";
	private static final String SCZT = "1";//1:ɾ��
	private PfzglDao dao = new PfzglDao();
	/**
	 * 
	 * @����:�ж��������Ƿ��Ѵ���
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-8-10 ����03:15:33
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean isHave(PfzglForm model) {
		return dao.isHave(model);
	}
	/**
	 * 
	 * @����:�Ƿ�ʹ��
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-8-19 ����04:35:47
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param values
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean isUsed(String values) {
		boolean flag = false;
		if (values != null) {
			String[] pfzArr = values.split(",");
			for (int i = 0; i < pfzArr.length; i++) {
				flag = dao.isUsed(pfzArr[i]);
				if (flag) {
					break;
				}
			}
		}
		return flag;
	}
	/**
	 * @throws Exception 
	 * 
	 * @����:ɾ��������
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-8-10 ����03:15:33
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * boolean �������� 
	 * @throws
	 */
//	public boolean delPfz(String[] ids) throws Exception {
//		List<String[]> PfzList = new ArrayList<String[]>();
//		String[] Pfz = null;
//		for (int i = 0; i < ids.length; i++) {
//			Pfz = new String[2];
//			Pfz[0]=SCZT;
//			Pfz[1]=ids[i];
//			PfzList.add(Pfz);
//		}
//		return dao.delPfz(PfzList);
//	}

	/**
	 * 
	 * @����:�����鱣��
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-8-10 ����03:34:11
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean editPfz(PfzglForm model) throws Exception {
		boolean result = true;
		if ("save".equals(model.getType())) {
			String Pfzid = UniqID.getInstance().getUniqIDHash();
			model.setPfzid(Pfzid);
			result = dao.runInsert(model);
		} else {
			result = dao.runUpdate(model);
		}
		return result;
		
	}

	/**
	 * @throws Exception
	 * 
	 * @����:��ȡ������
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-7-13 ����04:40:36
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return HashMap<String,String> ��������
	 * @throws
	 */
	public HashMap<String,String> getPfzgl(PfzglForm t) throws Exception {
		return dao.getPfzgl(t);
	}
	
	public List<HashMap<String, String>> getKhdxList(PfzglForm model, User user)
	throws Exception{
		if("1".equals(model.getKhlx())){
			return dao.getStuList(model,user);
		}else{
			return dao.getTeaList(model,user);
		}
		
	}
	/**
	 * 
	 * @����:��ѯ���ֳ�Ա�б�
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-8-12 ����11:38:33
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getPfcyList(PfzglForm model, User user)
	throws Exception{
		return dao.getPfcyList(model, user);
	}
	
	/**
	 * @���������ֳ�Ա�༶
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2016��10��29�� 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> ��������
	 */
	public List<HashMap<String, String>> getPfcybjList(PfzglForm model)
	throws Exception{
		return dao.getPfcybjList(model);
	}
	
	public List<HashMap<String, String>> getViewPfcyList(PfzglForm model, User user)
	throws Exception{
		return dao.getViewPfcyList(model, user);
	}
	
	public List<HashMap<String, String>> getPfzList(String khdxid)
	throws Exception{
		return dao.getPfzList(khdxid);
	}
	/**
	 * @throws Exception 
	 * 
	 * @����:��������䱣��
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-8-11 ����08:55:42
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
	public boolean savePfcy(PfzglForm model,String value,String khdxr,User user) throws Exception{
		String[] pfcys=value.split(",");
		String[] khdxrs=khdxr==""?null:khdxr.split(",");
		boolean result = true;
		model.getPages().setPageSize(Integer.MAX_VALUE);
		List<HashMap<String,String>> khdxList = getKhdxList(model,user);
		//���˶���ȫѡ
		if("1".equals(model.getSfqx())&&null==khdxrs){
			khdxrs= new String[khdxList.size()];
			for (int i = 0; i < khdxList.size(); i++) {
				khdxrs[i]=null==khdxList.get(i).get("xh")?khdxList.get(i).get("zgh"):khdxList.get(i).get("xh");
			}
		}
		List<String[]> pfzcyList = new ArrayList<String[]>();
		String[] Pfz = null;
		List<String[]> pfzdelList = new ArrayList<String[]>();
		String[] Pfzdel = null;
		for (int i = 0; i < khdxrs.length; i++) {
			for (int j = 0; j < pfcys.length; j++) {
				Pfz = new String[3];
				Pfz[0]=model.getPfzid();
				Pfz[2]=pfcys[j];
				Pfz[1]=khdxrs[i];
				pfzcyList.add(Pfz);
			}
			Pfzdel=new String[3];
			Pfzdel[0]=model.getPfzid();
			Pfzdel[1]=khdxrs[i];
			Pfzdel[2]=model.getPfzid();
			pfzdelList.add(Pfzdel);
		}
		if("1".equals(model.getPflx())){ //���뿼����Ϊ�Ķ���Ϊѧ��
			//��ɾ���ڲ���
			if("1".equals(model.getSfqx())){
				result=dao.pfxsPlDel(pfzdelList);//���˶����ѡ�������ԭ��������
			}
			else{
				result=dao.pfxsDel(pfzcyList);
			}
			//��������
			if("save".equals(model.getType())){
				result= dao.pfxsInsert(pfzcyList);
			}
		}else{ //���뿼����Ϊ�Ķ���Ϊ��ʦ
			if("1".equals(model.getSfqx())){
				result=dao.pfjsPlDel(pfzdelList);
			}
			else{
				result=dao.pfjsDel(pfzcyList);
			}
			if("save".equals(model.getType())){
				result=dao.pfjsInsert(pfzcyList);
			}
		}
		return result;
	}
	
	/**
	 * @�������������ֳ�Ա�༶
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2016��10��31�� 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param values
	 * @param khdx
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean ��������
	 */
	public boolean savePfcybj(PfzglForm model,String values,String khdx,User user) throws Exception{
		String pfzid=model.getPfzid();
		String[] classids=values.split(",");
		//ƴ��classid 
		StringBuilder classid=new StringBuilder();
		classid.append("'");
		for(int i=0;i<classids.length-1;i++){
			classid.append(classids[i]);
			classid.append("','");
		}
		classid.append(classids[classids.length-1]);
		classid.append("'");
		boolean result = true;
		result=dao.pfxsbjDel(pfzid,khdx,classid.toString());
		//��������
		if("save".equals(model.getType())){
			result= dao.pfxsbjInsert(pfzid,khdx,classid.toString());
		}
		return result;
	}
	
	
	/**
	 * 
	 * @����:������ȡ������
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-8-11 ����09:23:01
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param value
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean savePfzQxPf(PfzglForm model,String value) throws Exception{
		String[] values=value.split(",");
		List<String[]> PfzList = new ArrayList<String[]>();
		String[] Pfz = null;
		for (int i = 0; i < values.length; i++) {
			Pfz = new String[2];
			Pfz[0]=model.getPfzid();
			Pfz[1]=values[i];
			PfzList.add(Pfz);
		}
		if("1".equals(model.getPflx())){
			return dao.pfxsDel(PfzList);//����ѧ��ȡ������
		}else{
			return dao.pfjsDel(PfzList);//���˽�ʦȡ������
		}
	}
	/** 
	 * @������
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2016��10��31�� 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * boolean �������� 
	 * @throws Exception 
	 */
	public boolean pfcydrSave(PfzglForm model, User user) throws Exception {
		boolean isSuccess = true;
		String xh = model.getXh();
		StringBuilder value=new StringBuilder();
		if (!StringUtil.isNull(xh)) {
			String[] xhs = xh.replaceAll("\r", " ").replaceAll("\n"," ").split(" ");
			for(int i=0;i<xhs.length-1;i++){
				if(StringUtils.isNull(xhs[i])){
					continue;
				}
				value.append(xhs[i].trim());
				value.append(",");
			}
			value.append(xhs[xhs.length-1].trim());
			isSuccess=savePfcy(model, value.toString(), model.getZgh(), user);

		}
		return isSuccess;
	}

	
	
	



}
