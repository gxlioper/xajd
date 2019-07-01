/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-2-6 ����04:36:55 
 */
package com.zfsoft.xgxt.xszy.xsqshf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;
import xgxt.form.User;
import xgxt.utils.GetTime;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.xszy.qsppgl.XszyQsppDao;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� xiaxia[����:1104]
 * @ʱ�䣺 2015-2-6 ����04:36:55
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class XszyQshfService extends
		SuperServiceImpl<XszyQshfForm, XszyQshfDao> {
	private XszyQshfDao dao = new XszyQshfDao();
	private XszyQsppDao qsppDao = new XszyQsppDao();
	private static final String FPZT="0";
	private static final String THZT="1";
	private static final String DATE_FORMAT = "yyyy-MM-dd hh24:mm:ss";

	/**
	 * 
	 * @����:��ȡ�����б�
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-2-9 ����03:16:22
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getBmList() {
		return dao.getBmList();
	}
	public List<HashMap<String, String>> getXyList() {
		return dao.getXyList();
	}
	

	/**
	 * 
	 * @����:��ȡ������Ϣ
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-2-10 ����11:27:08
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return
	 * @throws Exception
	 *             HashMap<String,String> ��������
	 * @throws
	 */
	public HashMap<String, String> getQsxx(XszyQshfForm t) throws Exception {
		return dao.getQsxx(t);
	}

	/**
	 * 
	 * @����:��ѯ��סѧ��
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-2-10 ����11:30:59
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return
	 * @throws Exception
	 *             HashMap<String,String> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getRzxsList(XszyQshfForm t)
			throws Exception {
		return dao.getRzxsList(t);
	}
	
	public List<HashMap<String, String>> getRzxsListOfLxk(XszyQshfForm t)
			throws Exception {
		return dao.getRzxsListOfLxk(t);
	}

	public List<HashMap<String, String>> getXsBzrList(XszyQshfForm t)
			throws Exception {
		return dao.getXsBzrList(t);
	}

	public List<HashMap<String, String>> getXsFdyList(XszyQshfForm t)
			throws Exception {
		return dao.getXsFdyList(t);
	}

	/**
	 * 
	 * @����:�鿴��������
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-2-11 ����10:38:17
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getFpxq(XszyQshfForm t,User user)
			throws Exception {
		return dao.getFpxq(t,user);
	}
	/**
	 * 
	 * @����:ѧԺ����ͳ��
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2015-5-22 ����01:54:06
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getFptj(XszyQshfForm t,User user)
	throws Exception {
	return dao.getFptj(t,user);
	}

	/**
	 * 
	 * @����:ȫУ������Ϣ
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-2-11 ����10:55:54
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws Exception
	 *             HashMap<String,String> ��������
	 * @throws
	 */
	public HashMap<String, String> getQsxxAll(XszyQshfForm myForm) throws Exception {
		return dao.getQsxxAll(myForm);
	}

	/**
	 * 
	 * @����:���ҷ������
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-2-9 ����04:41:36
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception boolean ��������
	 * @throws
	 */
	public boolean editFpcz(XszyQshfForm model, User user) throws Exception {
		boolean result = true;
		model.setCzsj(GetTime.getTimeByFormat(DATE_FORMAT));
		model.setFpczr(user.getUserName());
		model.setSsyxmc(dao.getBmmc(model.getSsyxdm()));
		if ("save".equals(model.getType())) {
			String id = UniqID.getInstance().getUniqIDHash();
			model.setId(id);
			dao.delQsfp(model);
			result = dao.runInsert(model);
			//�����Ի�ѧ԰�˻ظ�����ѧԺ
			if("90".equals(model.getSsyxdm())){
				model.setFpzt(THZT);
				model.setThxy(model.getSsyxdm());
				model.setThr(user.getRealName());
			}else{
				model.setFpzt(FPZT);
			}
			result=dao.updateQsxxZt(model);
		} else {
			result = dao.runUpdate(model);
		}
		return result;
	}

	/**
	 * 
	 * @����:��ȡ��������
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-2-10 ����02:54:25
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception boolean ��������
	 * @throws
	 */
	public HashMap<String, String> getQss(XszyQshfForm model) throws Exception {
		HashMap<String, String> qssMap = new HashMap<String, String>();

		String[] qshArr = model.getQsh().split(",");
		String[] sfhhqs = model.getSfhhqs().split(",");
		String[] qsxbArr = model.getQsxb().split(",");
		// ���������
		int hhqss = 0;
		// ����������
		int mqss = 0;
		for (int i = 0; i < qshArr.length; i++) {
			if (0 != sfhhqs.length && "��".equals(sfhhqs[i])) {
				hhqss++;
			}
			if ("��".equals(qsxbArr[i])) {
				mqss++;
			}

		}
		qssMap.put("qss", String.valueOf(qshArr.length));
		qssMap.put("hhqss", String.valueOf(hhqss));
		qssMap.put("mqss", String.valueOf(mqss));
		// Ů��������
		qssMap.put("wqss", String.valueOf(qsxbArr.length - mqss));
		return qssMap;
	}

	/**
	 * 
	 * @����:�������䱣��
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-2-10 ����02:19:13
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception boolean ��������
	 * @throws
	 */
	public boolean savePlfp(XszyQshfForm model, User user) throws Exception {
		String thsj=GetTime.getTimeByFormat(DATE_FORMAT);
		model.setCzsj(GetTime.getTimeByFormat(DATE_FORMAT));
		model.setFpczr(user.getUserName());
		
		model.setSsyxmc(dao.getBmmc(model.getSsyxdm()));
		String[] lddmArr = model.getLddm().split(",");
		String[] qshArr = model.getQsh().split(",");
		List<String[]> qshfList = new ArrayList<String[]>();
		String[] qshfxx = null;
		List<String[]> qsxxList = new ArrayList<String[]>();
		List<String[]> qsxxdelList = new ArrayList<String[]>();
		String[] qsxx = null;
		String[] qsxxdel = null;
		if(""!=model.getLddm()){
		for (int i = 0; i < lddmArr.length; i++) {
			qsxx=new String[6];
			qsxxdel=new String[3];
			qshfxx = new String[7];
			qshfxx[0] = lddmArr[i];
			qshfxx[1] = qshArr[i];
			qshfxx[2] = Base.currNd;
			qshfxx[3] = model.getSsyxdm();
			qshfxx[4] = model.getSsyxmc();
			qshfxx[5] = model.getFpczr();
			qshfxx[6] = model.getCzsj();
			//ѧԺ���Ի��˻ظ�����ѧԺ
			if("90".equals(model.getSsyxdm())){
				qsxx[0]=THZT;
				qsxx[1]=user.getRealName();
				qsxx[2]=thsj;
				qsxx[3]=model.getSsyxdm();
				
			}else{
				qsxx[0]=FPZT;
				qsxx[1]="";
				qsxx[2]="";
				qsxx[3]="";
				
			}
			qsxx[4]=lddmArr[i];
			qsxx[5]=qshArr[i];
			qsxxdel[0]=lddmArr[i];
			qsxxdel[1]=qshArr[i];
			qsxxdel[2]=Base.currNd;
			qsxxList.add(qsxx);
			qshfList.add(qshfxx);
			qsxxdelList.add(qsxxdel);
		}
		}
		qsppDao.qsPlth(qsxxList);
		
		boolean result = dao.qshfPlbc(qshfList,qsxxdelList);
		return result;
	}

}
