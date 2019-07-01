/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-7-20 ����01:34:47 
 */  
package com.zfsoft.xgxt.xpjpy.cpmd;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.xpjpy.cssz.CsszDao;
import com.zfsoft.xgxt.xpjpy.cssz.CsszModel;
import com.zfsoft.xgxt.xpjpy.cssz.CsszService;

import xgxt.form.User;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �������Ź���ģ��
 * @�๦������: ��������2013��-����ѧ������
 * @���ߣ� Penghui.Qu [���ţ�445]
 * @ʱ�䣺 2013-7-20 ����01:34:47 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class CpmdService extends SuperServiceImpl<CpmdModel, CpmdDao> {
	
	private CpmdDao dao = new CpmdDao();
	
	//�ύ״̬
	public static final String WTJ = "0";	//δ�ύ
	public static final String YTJ = "1";	//���ύ
	public static final String QXTJ = "2";	//ȡ���ύ

	
	public CpmdService(){
		super.setDao(dao);
	}

	/**
	 * @throws Exception  
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2013-7-23 ����02:16:48
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getTzjlList(CpmdModel model, User user) throws Exception {
		return dao.getTzjlList(model, user);
	}

	/**
	 * @throws Exception 
	 * @param user  
	 * @����: ����ȡ������
	 * @���ߣ�CQ [���ţ�785]
	 * @���ڣ�2013-7-24 ����02:46:57
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param values
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean qxcp(String values, User user) throws Exception {

		if(values == null || "".equalsIgnoreCase(values)){
			logger.error("δѡ��ȡ��������Ա��");
			throw new NullPointerException();
		}
		
			boolean qxcp = false;
		
			//���������¼
			qxcp=dao.insertTzjl(values,user);
		
			if(!qxcp){
				return false;
			}
			
			//����������Ա��
			qxcp=dao.updateCpmd(values,user);
			
			//ѧ���۲⣬�Զ�����ѧ���۲�,�����ѧ���۲⣬�򲻸���
			if(CsszService.getZczq()){
				dao.updateXncpmd(values,user);
			}
		
			return qxcp;
	}

	/**
	 * @throws Exception  
	 * @����:��ѧ����һ���༶��������һ���༶
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2013-7-26 ����10:44:33
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ids
	 * @param tzhbjdm
	 * @param user
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean bjtz(String tzhbjdm, User user,String xh) throws Exception {
		
		CsszDao csszDao = new CsszDao();
		CsszModel csszModel = csszDao.getModel();
		
		String xn = csszModel.getXn();
		String xq = csszModel.getXq();
		
		//�ж�ID�Ƿ��ڵ�ǰ��������������
		boolean isHavePjry = dao.isHavePjry(xh,xn,xq);
		
		//����༶������¼
		if(isHavePjry){
			
			 dao.insertbjTzjl(xh,xn,xq, tzhbjdm, user);
		}else{
			
			 dao.insertbjTzjl1(xn,xq,xh,tzhbjdm, user);
		}
		 
		//���°༶����������Ա��
		 if(isHavePjry){
			//��һ���༶��������һ���༶
			dao.updateBjtzCpmd(xh,xn,xq, tzhbjdm);
		 }else{
			//�Ӳ���������������
			dao.insertBjtzCpmd(xn,xq,xh,tzhbjdm);
		 }
		 
		 //�����ѧ���۲⣬�Զ�����ѧ���۲�
		 if(CsszService.getZczq()){
			 dao.updateXnzc(xn,xh);
		 }
		 
		return true;
		
	}
	
	/**
	 * 
	 * @����:��������ѧ��
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2015-2-12 ����02:46:29
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param tzhbjdm
	 * @param user
	 * @param xh
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean bjtzs(String tzhbjdm, User user,String ids) throws Exception {
		
		String [] id = ids.split(",");
		
		//û�е���д������ѭ����ǰ��
		for (int i = 0; i < id.length; i++) {
			bjtz(tzhbjdm, user, id[i]);
		}
		
		return true;
	}


	/** 
	 * @����:���ص�����Ϣ
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2013-7-31 ����08:51:16
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws 
	 */
	public HashMap<String, String> getTzxx(String xh) {
		
		if (StringUtil.isNull(xh)){
			logger.error("xh is null !");
			throw new NullPointerException();
		}

		return dao.getTzxx(xh);
	}

	/** 
	 * @����:�жϵ�ǰ�����������Ƿ��в�����Ա
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2013-7-31 ����02:39:33
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean getSfcz() {
		
		return dao.getSfcz();
		
	}

	/**
	 * @throws Exception  
	 * @����:������Ա��ִ�г�ʼ������
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2013-7-31 ����02:55:02
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * void �������� 
	 * @throws 
	 */
	public void init() throws Exception {
		
		if(dao.initDel()){
			dao.init();
		}
		
	}
	
	/**
	 * 
	 * @����:ѧ���۲⣬�Զ���ʼ��ѧ���������
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-8-5 ����03:51:22
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @throws Exception
	 * void �������� 
	 * @throws
	 */
	public void xnInit() throws Exception {
		dao.xnInit();
	}

	
	/**
	 * 
	 * @����: ��ѯ���Ӳ���ѧ���б�
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-8-14 ����11:30:37
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getAddCpxsList(CpmdModel model) throws Exception{
		return dao.getAddCpxsList(model);
	}
	
	/** 
	 * @����: ��ȡ�༶����
	 * @���ߣ���ˮ�� [���ţ�1150]
	 * @���ڣ�2014-9-29 ����10:25:14
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param bjdm
	 * @param xn
	 * @param xq
	 * @return
	 * String �������� 
	 * @throws 
	 */
	public String getBjrs(String bjdm, String xn, String xq){
		return dao.getBjrs(bjdm, xn, xq);
	}
	
	/**
	 * 
	 * @����:��ȡ����������
	 * @���ߣ�taogj[���ţ�1075]
	 * @���ڣ�2017-10-24 ����03:56:03
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param xn
	 * @param xq
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getCpzrs(String xh, String xn, String xq){
		return dao.getCpzrs(xh, xn, xq);
	}
	
	/**
	 * 
	 * @����: ��ѯ����ѧ������
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2016��10��14�� ����7:12:56
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param bjdmArr
	 * @param xn
	 * @param xq
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getCpmdList(String[] bjdmArr , String xn ,String xq){
		
		if (bjdmArr == null || bjdmArr.length == 0){
			return null;
		}
		return dao.getCpmdList(bjdmArr, xn, xq);
	}
	
}
