/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2017-4-19 ����01:59:41 
 */  
package com.zfsoft.xgxt.szdw.bfjs.bfjsgl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.UniqID;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: ��罨��service(������һ�仰��������������) 
 * @���ߣ� ����[����:1282]
 * @ʱ�䣺 2017-4-19 ����01:59:41 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class BfjsglService extends SuperServiceImpl<BfjsglForm, BfjsglDao>{
	
	BfjsglDao dao = new BfjsglDao();
	
	public boolean runInsertForZj(BfjsglForm t) throws Exception {
		String jcid = UniqID.getInstance().getUniqIDHash();
		t.setJcid(jcid);
		return runInsert(t);
	}
	
	/** 
	 * @����:��ȡ�༶�б�(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-4-21 ����11:33:58
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getBjList(BfjsglForm t,User user) throws Exception{
		return dao.getBjList(t, user);
	}
	
	/** 
	 * @����:��ȡѧ����Ϣ(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-4-21 ����03:06:03
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getXsxxList(BfjsglForm model, User user) throws Exception{
		return dao.getXsxxList(model, user);
	} 
	
	/**
	 * @throws SQLException  
	 * @����:���Ӽ����ϸ(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-4-19 ����02:16:27
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean insertJcmx(BfjsglForm t) throws SQLException {
		String[] qjs = t.getQjs();//��ȡ��ٵ�ѧ��
		String[] qqs = t.getQqs();//��ȡȱ�ڵ�ѧ��
		String[] cds = t.getCds();//��ȡ�ٵ���ѧ��
		String[] zts = t.getZts();//��ȡ���˵�ѧ��
		List<String[]> list = new ArrayList<String[]>();
		if(null != qjs && qjs.length>0){
			for(int i = 0;i<qjs.length;i++){
				String[] qjstr = qjs[i].split("_");
				list.add(qjstr);
			}
		}
		if(null != qqs && qqs.length>0){
			for(int i = 0;i<qqs.length;i++){
				String[] qqstr = qqs[i].split("_");
				list.add(qqstr);
			}
		}
		if(null != cds && cds.length>0){
			for(int i = 0;i<cds.length;i++){
				String[] cdstr = cds[i].split("_");
				list.add(cdstr);
			}
		}
		if(null != zts && zts.length>0){
			for(int i = 0;i<zts.length;i++){
				String[] ztstr = zts[i].split("_");
				list.add(ztstr);
			}
		}
		if(list.size()>0){			
			return dao.insertJcmx(list, t);
		}else{
			return true;
		}
	}
	
	/** 
	 * @����:�жϰ༶�Ƿ��м���ճ�(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-4-24 ����08:38:59
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param form
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean isBjExist(BfjsglForm form) {
		return dao.isBjExist(form);
	}
	
	/**
	 * @throws Exception  
	 * @����:��ȡ������Ա��Ϣ(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-4-24 ����09:57:34
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param form
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public BfjsglForm getKqRyxxList(BfjsglForm form) throws Exception{
		HashMap<String,String> modelMap = dao.getModelMap(form);
		form.setBjdm(modelMap.get("bjdm"));
		form.setBjmc(modelMap.get("bjmc"));
		form.setJcrq(modelMap.get("jcrq"));
		List<HashMap<String,String>> list = dao.getKqRyxxList(form);
		if(null != list && list.size()>0){			
			List<HashMap<String,String>> zcqqList = new ArrayList<HashMap<String,String>>();//�����Աȱ����Ϣ
			List<HashMap<String,String>> zdqqList = new ArrayList<HashMap<String,String>>();//�����Աȱ����Ϣ
			List<HashMap<String,String>> skqqList = new ArrayList<HashMap<String,String>>();//�Ͽ���Աȱ����Ϣ
			List<HashMap<String,String>> wzxqqList = new ArrayList<HashMap<String,String>>();//������ȱ����Ϣ
			List<HashMap<String,String>> zccqList = new ArrayList<HashMap<String,String>>();//�����Ա������Ϣ
			List<HashMap<String,String>> zdcqList = new ArrayList<HashMap<String,String>>();//�����Ա������Ϣ
			List<HashMap<String,String>> skcqList = new ArrayList<HashMap<String,String>>();//�Ͽ���Ա������Ϣ
			List<HashMap<String,String>> wzxcqList = new ArrayList<HashMap<String,String>>();//�����޳�����Ϣ
			for(HashMap<String,String> map : list){
				if("zc".equalsIgnoreCase(map.get("jclx"))){
					if(map.get("qqlxdm").equalsIgnoreCase("cq")){
						zccqList.add(map);
					}else{
						zcqqList.add(map);
					}
				}else if("zd".equalsIgnoreCase(map.get("jclx"))){
					if(map.get("qqlxdm").equalsIgnoreCase("cq")){
						zdcqList.add(map);
					}else{
						zdqqList.add(map);
					}
				}else if("sk".equalsIgnoreCase(map.get("jclx"))){
					if(map.get("qqlxdm").equalsIgnoreCase("cq")){
						skcqList.add(map);
					}else{
						skqqList.add(map);
					}
				}else if("wzx".equalsIgnoreCase(map.get("jclx"))){
					if(map.get("qqlxdm").equalsIgnoreCase("cq")){
						wzxcqList.add(map);
					}else{
						wzxqqList.add(map);
					}
				}
			}
			form.setZccqList(zccqList);
			form.setZcqqList(zcqqList);
			form.setZdcqList(zdcqList);
			form.setZdqqList(zdqqList);
			form.setSkcqList(skcqList);
			form.setSkqqList(skqqList);
			form.setWzxcqList(wzxcqList);
			form.setWzxqqList(wzxqqList);
		}
		return form;
	}
	
	/** 
	 * @����:ɾ����罨��(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-4-24 ����04:21:39
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ids
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws 
	 */
	public boolean delBfjs(String[] ids) throws Exception{
		return dao.delBfjs(ids);
	}
	
	/** 
	 * @����:����Ҫɾ����Ҫ�޸ĵ�ѧ��(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-4-25 ����08:55:13
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param form
	 * void �������� 
	 * @throws 
	 */
	public List<String[]> setDelXh(BfjsglForm form){
		List<String[]> list = new ArrayList<String[]>();
		String[] qjs = form.getQjs();//��ȡ��ٵ�ѧ��
		String[] qqs = form.getQqs();//��ȡȱ�ڵ�ѧ��
		String[] cds = form.getCds();//��ȡ�ٵ���ѧ��
		String[] zts = form.getZts();//��ȡ���˵�ѧ��
		if(null != qjs && qjs.length>0){
			for(int i = 0;i<qjs.length;i++){
				String[] qjstr = qjs[i].split("_");
				String[] qjstrr = new String[3];
				qjstrr[0] = form.getJcid();
				qjstrr[1] = qjstr[1];
				qjstrr[2] = qjstr[0];
				list.add(qjstrr);
			}
		}
		if(null != qqs && qqs.length>0){
			for(int i = 0;i<qqs.length;i++){
				String[] qqstr = qqs[i].split("_");
				String[] qqstrr = new String[3];
				qqstrr[0] = form.getJcid();
				qqstrr[1] = qqstr[1];
				qqstrr[2] = qqstr[0];
				list.add(qqstrr);
			}
		}
		if(null != cds && cds.length>0){
			for(int i = 0;i<cds.length;i++){
				String[] cdstr = cds[i].split("_");
				String[] cdstrr = new String[3];
				cdstrr[0] = form.getJcid();
				cdstrr[1] = cdstr[1];
				cdstrr[2] = cdstr[0];
				list.add(cdstrr);
			}
		}
		if(null != zts && zts.length>0){
			for(int i = 0;i<zts.length;i++){
				String[] ztstr = zts[i].split("_");
				String[] ztstrr = new String[3];
				ztstrr[0] = form.getJcid();
				ztstrr[1] = ztstr[1];
				ztstrr[2] = ztstr[0];
				list.add(ztstrr);
			}
		}
		String[] dels = form.getDels();
		if(null != dels && dels.length>0){
			for(int i = 0;i<dels.length;i++){
				list.add(dels[i].split("_"));
			}
		}
		return list;
	}
	
	/**
	 * @throws SQLException  
	 * @����:����ѧ�š�jcID���������ɾ��������ϸ(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-4-25 ����09:38:33
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param list
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public void delXh(BfjsglForm form) throws SQLException{
		List<String[]> list = setDelXh(form);
		if(null != list && list.size()>0){
			dao.delXh(list);
		}
	}
}
