/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-7-20 ����01:59:23 
 */  
package com.zfsoft.xgxt.xpjpy.cpxz;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.xpjpy.cpmd.CpmdService;
import com.zfsoft.xgxt.xpjpy.cssz.CsszDao;
import com.zfsoft.xgxt.xpjpy.cssz.CsszModel;
import com.zfsoft.xgxt.xpjpy.cssz.CsszService;
import com.zfsoft.xgxt.xpjpy.zhcp.zcfs.ComputeCxpm;
import com.zfsoft.xgxt.xpjpy.zhcp.zcfs.ZcfsDao;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �������Ź���ģ��
 * @�๦������: ��������2013-����С�� 
 * @���ߣ� Penghui.Qu [���ţ�445]
 * @ʱ�䣺 2013-7-20 ����01:59:23 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class CpxzService extends SuperServiceImpl<CpxzModel, CpxzDao> {
	
	public static final String CPZ_CSH_NJZY = "1";//�������ʼ����ʽ--�꼶��רҵ
	public static final String CPZ_CSH_BJ = "2";//�������ʼ����ʽ--�༶
	public static final String CPZ_CSH_SY = "3";//�������ʼ����ʽ--��Ժ
	
	private CpxzDao dao = new CpxzDao();

	public CpxzService(){
		super.setDao(dao);
	}
	
	
	/**
	 * 
	 * @����: ��ʼ������С�飨���������õ��ã�
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-7-23 ����03:52:55
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * void ��������
	 * @throws Exception 
	 */
	public void initCpxz(User user) throws Exception{
		
		CsszDao csszDao = new CsszDao();
		CsszModel csszModel = csszDao.getModel();
		
		String cpzcsh = csszModel.getCpzcsh();
		String xn = csszModel.getXn();
		String xq = csszModel.getXq();
		
		//��ǰ�����Ƿ��Ѿ��в���С�飬�У�return , û�У� ��ʼ��
		boolean isHaveCpxz = dao.isHaveCpxz(xn, xq);
		
		if (isHaveCpxz){
			return ;
		}
		
		//ɾ�������ڷ����ύ��¼
		ZcfsDao zcfsDao = new ZcfsDao();
		zcfsDao.delTjjl(xn, xq, user);
		
		if (CPZ_CSH_BJ.equals(cpzcsh)){
			//��ʼ������������
			dao.createCpxzByBj();
			//��ʼ���༶����������
			dao.initCpxzByBj(xn, xq,user);
		} else if(CPZ_CSH_NJZY.equals(cpzcsh)){
			//��ʼ������������
			dao.createCpxzByNjZy();
			//��ʼ���༶����������
			dao.initCpxzByNjzy(xn, xq,user);
		} else if(CPZ_CSH_NJZY.equals(cpzcsh)){
			//��ʼ������������
			dao.createCpxzBySy();
			//��ʼ���༶����������
			dao.initCpxzBySy(xn,xq,user);
		} else {
			dao.createCpxzBySy();
			dao.initCpxzBySy(xn,xq,user);
		}

		//���ѧ�����������ѧ��༶����������
		if(CsszService.getZczq()){
			dao.initXnfsjt(xn);
		}
		
	}
	
	/**
	 * @��������ʼ�������顢��������
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2016��12��23�� 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param user
	 * @throws Exception
	 * void ��������
	 */
	public boolean initCpzCpmd(User user) throws Exception{
		
		CsszDao csszDao = new CsszDao();
		CsszModel csszModel = csszDao.getModel();
		
		String cpzcsh = csszModel.getCpzcsh();
		String xn = csszModel.getXn();
		String xq = csszModel.getXq();
		
		
		ZcfsDao zcfsDao = new ZcfsDao();
		//�����������ύ�����ύ���뵽��ʱ�� 
		if(!zcfsDao.bakTjjl(xn, xq, user)){
			logger.error("�����������ύ�����ύ���뵽��ʱ��  ʧ��");
			return false;
		}
		//ɾ�������ڷ����ύ��¼
		if(!zcfsDao.delTjjl(xn, xq, user)){
			logger.error("ɾ�������ڷ����ύ��¼ ʧ��");
			return false;
		}
		if (CPZ_CSH_BJ.equals(cpzcsh)){
			//��ʼ������������
			if(!dao.createCpxzByBj()){
				logger.error("��ʼ������������ ʧ��");
				return false;
			}
			//��ʼ���༶����������
			if(!dao.initCpxzByBj(xn, xq,user)){
				logger.error("��ʼ���༶���������� ʧ��");
				return false;
			}
		} else {
			//��ʼ������������
			if(!dao.createCpxzByNjZy()){
				logger.error("��ʼ������������ ʧ��");
				return false;
			}
			//��ʼ���༶����������
			if(!dao.initCpxzByNjzy(xn, xq,user)){
				logger.error("��ʼ���༶���������� ʧ��");
				return false;
			}
		}
		
		//���ѧ�����������ѧ��༶����������
		if(CsszService.getZczq()){
			if(!dao.initXnfsjt(xn)){
				logger.error("���ѧ��༶���������� ʧ��");
				return false;
			}
		}
		
		//���±��������ύ������¼Ϊȡ���ύ
		if(!zcfsDao.updTjjl()){
			logger.error("���ѧ��༶���������� ʧ��");
			return false;
		}
		
		CpmdService cpmdService = new CpmdService();
		cpmdService.init();
		
		//��ѧ���۲⣬��ʼ��ѧ��������Ա
		if(CsszService.getZczq()){
			cpmdService.xnInit();
		}
		
		//ɾ���������
		if(!zcfsDao.delDyjl()){
			logger.error("ɾ��������� ʧ��");
			return false;
		}
		
		return true;
	}
	
	
	/**
	 * 
	 * @����: ��ʼ������С�飨�ڲ���С��˵����ã�
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-7-23 ����05:18:05
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param user
	 * @param cpzcsh
	 * @throws Exception
	 * void ��������
	 */
	public boolean initCpxz(User user , String cpzcsh) throws Exception{
		
		String userType = user.getUserType();
		if (!("admin".equals(userType) || "xx".equals(userType) || "xy".equals(userType))){
			//����С���ʼ�����û�Ȩ�޲��㡣ֻ��ѧУ��ѧԺ�û���ʼ��
			throw new SystemException(MessageKey.PJPY_CPXZCSH_QXBZ);
		}
		

		CsszDao csszDao = new CsszDao();
		CsszModel csszModel = csszDao.getModel();
		
		String xn = csszModel.getXn();
		String xq = csszModel.getXq();
		
		//��ǰ�����Ƿ��Ѿ��в���С�飬�У����� , û�У� ��ʼ��
		boolean isHaveCpxz = dao.isHaveCpxz(xn, xq);
		
		if (CPZ_CSH_BJ.equals(cpzcsh)){
			//��ʼ������������
			dao.createCpxzByBj();
			
			if (isHaveCpxz){
				//�����ύ��¼���а༶����Ӧ�Ĳ���С��
				dao.updateCpxzByBj(xn, xq, user);
			} else {
				dao.initCpxzByBj(xn, xq,user);
			}
		} else if(CPZ_CSH_NJZY.equals(cpzcsh)) {
			//��ʼ������������
			dao.createCpxzByNjZy();
			
			if (isHaveCpxz){
				//�����ύ��¼���а༶����Ӧ�Ĳ���С��
				dao.updateCpxzByNjzy(xn, xq, user);
			} else {
				dao.initCpxzByNjzy(xn, xq,user);
			}
		} else {
			//��ʼ������������
			dao.createCpxzBySy();

			if (isHaveCpxz){
				dao.updateCpxzBySy(xn,xq,user);
			} else {
				//��ʼ���༶����������
				dao.initCpxzBySy(xn,xq,user);
			}
		}

		//���¼�������
		new Thread(new ComputeCxpm(xn,xq)).start();
		
		return isHaveCpxz;
		
	}


	/** 
	 * @����:��ѯ�༶��Ϣ
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2013-8-7 ����04:38:14
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ids
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getBjInfo(String ids) {
		
		if (StringUtil.isNull(ids)){
			logger.error("ѡ�а༶Ϊ��");
			throw new NullPointerException();
		}
		
		String[] id = ids.split(",");
		
		return dao.getBjInfo(id);
	}


	/**
	 * @throws Exception  
	 * @����:��������������
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2013-8-13 ����10:23:06
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param bjdm
	 * @param pmz
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean initCpzsz(String bjdm, String pmz) throws Exception {
		
		CsszDao csszDao = new CsszDao();
		CsszModel csszModel = csszDao.getModel();
		String xn = csszModel.getXn();
		String xq = csszModel.getXq();
		
		String[] bjdms = bjdm.split(",");
		
		String cpzdm = dao.getIdByName(pmz);
		boolean haveCpxz = false;
		if(cpzdm == null || cpzdm.equals("")){//�����µĲ�����
			cpzdm = UniqID.getInstance().getUniqIDHash();
			haveCpxz = dao.insertCpz(cpzdm,pmz);
		}
		
			//���°༶����Ӧ�Ĵ���
		haveCpxz = dao.updateBj(xn,xq,bjdms,cpzdm);
		
		//���¼�������
		new Thread(new ComputeCxpm(xn,xq)).start();
		
		// TODO �Զ����ɷ������
		return haveCpxz;
	}

	public List<HashMap<String,String>> getCpzglyList(CpxzModel model, User user) throws Exception {
		return dao.getCpzglyList(model, user);
	}

	public List<HashMap<String,String>> getGlyList(CpxzModel model, User user)throws Exception {

		return dao.getGlyList(model,user);
	}

	public boolean saveFp(CpxzModel model)throws Exception{
		return dao.savefp(model).length>0;
	}

	public boolean cancelfp(CpxzModel model)throws Exception{
		return dao.cancelFp(model);
	}
}
