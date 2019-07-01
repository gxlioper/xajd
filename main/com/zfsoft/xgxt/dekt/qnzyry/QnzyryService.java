/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2017-7-19 ����08:54:03 
 */  
package com.zfsoft.xgxt.dekt.qnzyry;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: ����־Ը��Աservice(������һ�仰��������������) 
 * @���ߣ� ����[����:1282]
 * @ʱ�䣺 2017-7-19 ����08:54:03 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class QnzyryService extends SuperServiceImpl<QnzyryForm, QnzyryDao>{
	private static String YTG = "1";
	private static String TH = "2";
	private QnzyryDao dao = new QnzyryDao();
	
	
	/** 
	 * @����:�����ѧ���Ƿ��ѱ�������Ŀ(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-7-19 ����01:44:03
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param from
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean isExist(QnzyryForm from){
		if(dao.countJl(from)>0){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * @throws Exception  
	 * @����:��ȡ��Ŀ��Ա(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-7-21 ����11:43:52
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param hdid
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String,String>> getXmryList(QnzyryForm form) throws Exception{
		return dao.getXmryList(form);
	}
	
	/**
	 * @throws SQLException  
	 * @����:������˱���״̬(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-7-21 ����05:28:49
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ids
	 * @param bmzt
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public String plshBmzt(QnzyryForm form) throws SQLException{
		if(form.getBmzt().equalsIgnoreCase(QnzyryAction.BMSHTG)){
			//��ȡ����Ŀ�޶������ͱ�����ͨ������
			HashMap<String,String> map = dao.countYtgbmAndXdrs(form.getHdid());
			Integer tgs = form.getIds().length;
			//�޶�����
			Integer xdrs = Integer.valueOf(map.get("xdrs"));
			//�����ͨ��������
			Integer tgrs = Integer.valueOf(map.get("tgrs"));
			if(tgs+tgrs > xdrs){
				return MessageKey.JHZT_DEKT_BM_OVERSIZE;
			}
		}
		return dao.plshBmzt(form.getIds(), form.getBmzt())?MessageKey.SYS_SAVE_SUCCESS:MessageKey.SYS_SAVE_FAIL;
	}
	
	/** 
	 * @����:��ȡ־Ը�������Ϣ(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-7-26 ����10:16:06
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param id
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws 
	 */
	public HashMap<String,String> getZyhdbmxx(String id){
		return dao.getZyhdbmxx(id);
	}
	
	public boolean upDateRy(QnzyryForm form) throws Exception{
		return dao.upDateRy(form);
	}
	
	/**
	 * @throws SQLException  
	 * @����:���������(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-7-26 ����03:59:58
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param form
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean plsh(QnzyryForm form) throws SQLException{
		String[] ids = form.getIds();
		String gsshzt = form.getGsshzt();
		String gsshyj = form.getGsshyj();
		//String sftj = form.getSftj();
		String[] fwjgs = form.getFwjg().split("_");
		String js = fwjgs[1];
		List<HashMap<String,String>> fsList = dao.getJbfByIds(ids);
		for(HashMap<String,String> map:fsList){
			String gs = String.valueOf(gsjs(map.get("jbfwgs"),js));
			map.put("gs", gs);
			map.put("gsshzt", gsshzt);
			map.put("gsshyj", gsshyj);
			map.put("fwjg", fwjgs[0]);
		}
		//form.setFwjg(fwjgs[0]);
		return dao.plsh(fsList);
	}
	
	/** 
	 * @����:��ȡ���(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-7-27 ����09:21:43
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getJgPageList(QnzyryForm t, User user) throws Exception{
		return dao.getJgPageList(t, user);
	}
	
	/*
	      ����: @see com.zfsoft.xgxt.base.service.impl.SuperServiceImpl#runUpdate(java.lang.Object)
	 */
	public boolean runUpdate(QnzyryForm t) throws Exception{
		//���Ϊͨ��
		if(t.getGsshzt().equals(QnzyryAction.GSSHTG)){
			String[] fwjgs = t.getFwjg().split("_");
			String jbgs = t.getJbfwgs();
			t.setFwjg(fwjgs[0]);
			long jg = gsjs(jbgs,fwjgs[1]);
			t.setGs(String.valueOf(jg));
		}else{
			t.setFwjg(null);
		}
		return super.runUpdate(t);
	}
	
	/** 
	 * @����:��ʱ����(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-9-29 ����11:32:30
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param jbgs
	 * @param js
	 * @return
	 * int �������� 
	 * @throws 
	 */
	public long gsjs(String jbgs,String js){
		int a = Integer.valueOf(jbgs);
		double b = Double.valueOf(js);
		long jg = Math.round(a*b);
		return jg;
		
	}
	
	/** 
	 * @����:��������(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-9-30 ����05:35:50
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ids
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws 
	 */
	public boolean plcx(String[] ids) throws Exception{
		//��ȡ��Ӧ־Ը�߻��Ӧ�Ļ�������
		List<HashMap<String,String>> list = dao.getJbfByIds(ids);
		return dao.plcx(list);
	}
	
	/** 
	 * @����:����ѧ��(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-10-10 ����02:36:44
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws 
	 */
	public String countXf(String xh) throws Exception{
		return dao.countTotalXf(xh);
	}
	
	/**
	 * @description	������־Ը������޸�
	 * @author 		�� ������1282��
	 * @date 		��2017-12-18 ����09:13:08
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public boolean runUpdateForPf(QnzyryForm model) throws Exception{
		return super.runUpdate(model);
	}
}
