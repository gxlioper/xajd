/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-8-12 ����10:06:05 
 */  
package com.zfsoft.xgxt.szdw.xsgbgl.gbdw;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import xgxt.form.User;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ˼���������ģ��
 * @�๦������:ѧ���ɲ����� ����ά��
 * @���ߣ� zhangjw
 * @ʱ�䣺 2013-8-12 ����10:05:35 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class DwwhService extends SuperServiceImpl<DwwhForm,DwwhDAO>{

	private DwwhDAO dao = new DwwhDAO();
	
	/*
	 * ����id��ȡ�༶�ɲ�
	 */
	public List<HashMap<String , String>> getZwListByBjdm(String bjdm) throws Exception{
		return dao.getZwListByBjdm(bjdm);
	}
	
	public DwwhService() {
		super.setDao(dao);
	}
	
	
	public boolean getZfmcExits(DwwhForm from){
		String num = dao.getZfmcExits(from);
		if(num.equals("0")){
			return false;
		}
		return true;
	}
	
	/**
	 * 
	 * @����:����ѧ�Ų�ѯѧ����ǰ���εĸɲ�ְ��
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-8-25 ����02:00:26
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getZwForXh(String xh){
		return dao.getZwForXh(xh);
	}
	
	/**
	 * ����ѧ�������ѯְ�����磺2013.09.01 - 2014.06.30�ڼ䵣��ְ��
	 */
	public List<HashMap<String , String>> getZwForXhXn(String xh, String xn){
		return dao.getZwForXhXn(xh, xn);
	}
	
	/**
	 * @����:����ѧ�Ų�ѯѧ����ǰ���ε�����ְ��
	 * @���ߣ���ˮ��[���ţ�1150]
	 * @���ڣ�2014-10-13 ����03:35:44
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return List<HashMap<String , String>> ��������
	 */
	public List<HashMap<String , String>> getAllZwListByXh(String xh){
		return dao.getAllZwListByXh(xh);
	}
	
	/**
	 * 
	 * @����:��ȡѧ���������ε�ְ��
	 * @���ߣ�ChenQ[���ţ�856]
	 * @���ڣ�2015-6-11 ����02:14:19
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getCrZwForXh(String xh){
		return dao.getCrZwForXh(xh);
	}
	
	/**
	 * 
	 * @����:��ȡѧ�����е��ε�ְ��
	 * @���ߣ�ChenQ[���ţ�856]
	 * @���ڣ�2015-6-11 ����02:14:19
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * String �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getZwxx(String xh){
		return dao.getZwxx(xh);
	}
	
	
	public List<HashMap<String,String>> getXsxxList(DwwhForm myForm) throws Exception {
		return dao.getXsxxList(myForm);
	}

	public boolean save(DwwhForm myForm) throws Exception {
		List<DwwhForm> list = new ArrayList<DwwhForm>(5);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date now = new Date();
		String[] zwids = myForm.getZwids();
		String[] xh = myForm.getXhs();
		DwwhForm form;
		String lrsj = sdf.format(now);
		for(int i=0;i<zwids.length;i++){
			if(!StringUtil.isNull(xh[i])){
				form = new DwwhForm();
				form.setZwid(zwids[i]);
				form.setXh(xh[i]);
				form.setLrsj(lrsj);
				list.add(form);
			}
		}

		boolean flag = false;
		for(int i=0;i<list.size();i++){
			flag = dao.insert(list.get(i));
		}

		return flag;
	}

	public HashMap<String,String> getBjxx(String bjdm){
		return dao.getBjxx(bjdm);
	}

	public List<HashMap<String,String>> getBgbData(String bjdm){
		return dao.getBgbData(bjdm);
	}

	public boolean update(DwwhForm myForm) throws Exception {
		boolean flag = delete(myForm.getBjdm());
		if(flag){
			flag = save(myForm);
		}
		return flag;
	}

	public boolean delete(String bjdm) throws Exception {
		return dao.delete(bjdm);
	}

	public boolean batchDelete(String[] bjdms) throws Exception {
		return dao.batchDelete(bjdms);
	}

	public List<HashMap<String,String>> export(DwwhForm model, User user)throws Exception {
		return dao.export(model,user);
	}
}
