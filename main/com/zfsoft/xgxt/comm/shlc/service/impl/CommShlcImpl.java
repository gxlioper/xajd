/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-6-13 ����04:29:55 
 */  
package com.zfsoft.xgxt.comm.shlc.service.impl;


import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.util.ShlcUtil;
import com.zfsoft.xgxt.znxgl.wdznx.JgxxtsService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import xgxt.action.Base;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;
import xsgzgl.xtwh.wdgz.MyJobsManager;
import xsgzgl.xtwh.wdgz.impl.MyJobsImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ͨ�����ģ��
 * @�๦������: ͨ���������ʵ�֣���������Ի�ҵ��
 * @���ߣ� Penghui.Qu [���ţ�445]
 * @ʱ�䣺 2013-6-13 ����04:29:55 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public final class CommShlcImpl implements ShlcInterface,Constants {

	private ShlcDao dao = new ShlcDao();
	private Log logger = LogFactory.getLog(ShlcDao.class);
	
	
	/*
	 * 
	      ����: @see com.zfsoft.xgxt.comm.shlc.service.ShlcInterface#runAuditing(com.zfsoft.xgxt.comm.shlc.model.ShxxModel)
	 */
	//��֤�Ƿ�ɲ�������
//	public synchronized String canSubmit(ShxxModel model) throws Exception {
//		HashMap<String,String> dshMap = dao.getDshGwid(model.getYwid());
//		String dshGwid = dshMap.get("gwid");
//		// ��ѯ��һ��������λ
//		String xjgw = dao.getNextGwid(model.getShlc(), dshGwid);
//		String lastGwid = dao.getLastGwid(model.getShlc());
//		if(StringUtils.isNull(xjgw)&&lastGwid.equalsIgnoreCase(dshGwid)){
//			
//		}
//	}
	
	public synchronized String runAuditing(ShxxModel model) throws Exception {
		logger.info("���� CommShlcImpl.runAuditing ��ʼ");
		//�����¼��Ϣ~
		HashMap<String,String> dshMap = dao.getDshGwid(model.getYwid());
		String dshGwid = dshMap.get("gwid");
		logger.info("��֤��Ҫ��ĸ�λ������λ�Ƿ������������ywid��"+model.getYwid()+"����ѯgwid=" + dshGwid + " ʵ��ǰ̨����gwid=" + model.getGwid());
 		if (!model.getGwid().equals(dshGwid)){
 			//Ҫ��ĸ�λ������λ������˵�������ѱ����������
 			throw new SystemException(MessageKey.SYS_AUD_FAIL);
 		}
 		logger.info("�ж��Ƿ��Ѳ������״̬������ywid��"+model.getYwid()+"�� ʵ��ǰ̨����gwid=" + model.getGwid());
		//�ж��Ƿ��Ѳ������״̬��,��������ظ�����   ��ע�����Է��ִ˷������࣬д������ʱ�Ͳ�ɾ��
		if(dao.getExistForShzt(model.getYwid(),model.getGwid())){
			//�ظ��ύ
			throw new SystemException(MessageKey.SYS_AUD_DOUBLE);
		}
			
		// ��ѯ��һ��������λ
		String xjgw = dao.getNextGwid(model.getShlc(), dshGwid);
		logger.info("SH_TG ��ѯ��һ��������λ�� xjgw=" + xjgw + " Shlc=" + model.getShlc() + " dshGwid=" + dshGwid);
		//�жϵ�ǰ��˸�λ�Ƿ�Ϊ���һ��
		String lastGwid = dao.getLastGwid(model.getShlc());
		if (!lastGwid.equalsIgnoreCase(dshGwid)&&StringUtils.isNull(xjgw)) {
			//������쳣
 			throw new SystemException(MessageKey.SYS_AUD_FAIL);
		}
		
		logger.info("���±������״̬��ʼ��guid=" + dshMap.get("guid") + ",shzt=" + model.getShzt());
		
		// ���±������״̬
		//boolean isSuccess = dao.updateShxx(model.getShr(),model.getShzt(),model.getShyj(),dshMap.get("guid"));
		boolean isSuccess = dao.updateShxx(model,dshMap.get("guid"));
		logger.info("���±������״̬����");
		if (SH_BTG.equals(model.getShzt())) {
			logger.info("SH_BTG ��ͨ�������̽�ֹ");
			//����������Ϣ
			dao.insertShjgXxts(dshMap.get("guid"));
			//��ͨ�������̽�ֹ
			return SH_BTG;
		} else if (SH_TH.equals(model.getShzt())) {

			if (StringUtils.isNull(model.getThgw())||model.getThgw().equals("-1")) {
				logger.info("SH_TH �˻ظ�������");
				//����������Ϣ
				dao.insertShjgXxts(dshMap.get("guid"));
				//�˻ظ������� ��Ŀ���λ
				return SH_TH;
			}
			logger.info("SH_TH �˻ص�Ŀ���λ�����������(������)��ʼ��Ywid=" + model.getYwid() + " Thgw=" + model.getThgw() + " SH_XCS=" + SH_XCS + " Shlc=" + model.getShlc() + " Sqrid=" + model.getSqrid() + " Tzlj=" + model.getTzlj() + " Tzljsq=" + model.getTzljsq());
			// �˻ص�Ŀ���λ�����������(������)
			boolean result = dao.insertThjd(model.getYwid(), model.getThgw(),SH_XCS,model.getShlc(),model.getSqrid(),model.getTzlj(),model.getTzljsq());
			logger.info("SH_TH �˻ص�Ŀ���λ�����������(������)����");

			//TODO ���˻�Ŀ���λ������Ϣ
			sendDshMsg(model.getThgw(),model.getSqrid(),model.getShlc(),result);
			return YW_SHZ;

		} else if (isSuccess && SH_TG.equals(model.getShzt())) {
			
			if(StringUtils.isNull(xjgw)&&lastGwid.equalsIgnoreCase(dshGwid)){
				logger.info("SH_TG ���һ�����");
				//���һ�����
				return SH_TG;
			}
			logger.info("SH_TG ������һ������ʼ��Ywid=" + model.getYwid() + " xjgw=" + xjgw  + " Shlc=" + model.getShlc() + " Sqrid=" + model.getSqrid() + " Tzlj=" + model.getTzlj() + " Tzljsq=" + model.getTzljsq());
			// ������һ������
			boolean result = dao.insertDbjd(model.getYwid(), xjgw ,model.getShlc(),model.getSqrid(),model.getTzlj(),model.getTzljsq());
			logger.info("SH_TG ������һ���������");

			//TODO ����һ������˸�λ������Ϣ
			sendDshMsg(xjgw,model.getSqrid(),model.getShlc(),result);
		}
		logger.info("���� CommShlcImpl.runAuditing ����");
		return YW_SHZ;

	}
	public synchronized String runAuditingNotCommit(ShxxModel model) throws Exception {
		logger.info("���� CommShlcImpl.runAuditingNotCommit ��ʼ");
		//�����¼��Ϣ~
		HashMap<String,String> dshMap = dao.getDshGwid(model.getYwid());
		String dshGwid = dshMap.get("gwid");
		logger.info("��֤��Ҫ��ĸ�λ������λ�Ƿ������������ywid��"+model.getYwid()+"����ѯgwid=" + dshGwid + " ʵ��ǰ̨����gwid=" + model.getGwid());
 		if (!model.getGwid().equals(dshGwid)){
 			//Ҫ��ĸ�λ������λ������˵�������ѱ����������
 			throw new SystemException(MessageKey.SYS_AUD_FAIL);
 		}
 		logger.info("�ж��Ƿ��Ѳ������״̬������ywid��"+model.getYwid()+"�� ʵ��ǰ̨����gwid=" + model.getGwid());
		//�ж��Ƿ��Ѳ������״̬��,��������ظ�����   ��ע�����Է��ִ˷������࣬д������ʱ�Ͳ�ɾ��
		if(dao.getExistForShzt(model.getYwid(),model.getGwid())){
			//�ظ��ύ
			throw new SystemException(MessageKey.SYS_AUD_DOUBLE);
		}
			
		// ��ѯ��һ��������λ
		String xjgw = dao.getNextGwid(model.getShlc(), dshGwid);
		logger.info("SH_TG ��ѯ��һ��������λ�� xjgw=" + xjgw + " Shlc=" + model.getShlc() + " dshGwid=" + dshGwid);
		//�жϵ�ǰ��˸�λ�Ƿ�Ϊ���һ��
		String lastGwid = dao.getLastGwid(model.getShlc());
		if (!lastGwid.equalsIgnoreCase(dshGwid)&&StringUtils.isNull(xjgw)) {
			//������쳣
 			throw new SystemException(MessageKey.SYS_AUD_FAIL);
		}
		
		logger.info("���±������״̬��ʼ��guid=" + dshMap.get("guid") + ",shzt=" + model.getShzt());
		
		// ���±������״̬
		//boolean isSuccess = dao.updateShxx(model.getShr(),model.getShzt(),model.getShyj(),dshMap.get("guid"));
		boolean isSuccess = dao.updateShxxNotCommit(model,dshMap.get("guid"));
		logger.info("���±������״̬����");
		if (SH_BTG.equals(model.getShzt())) {
			logger.info("SH_BTG ��ͨ�������̽�ֹ");
			//��ͨ�������̽�ֹ
			return SH_BTG;
		} else if (SH_TH.equals(model.getShzt())) {

			if (StringUtils.isNull(model.getThgw())||model.getThgw().equals("-1")) {
				logger.info("SH_TH �˻ظ�������");
				//�˻ظ������� ��Ŀ���λ
				return SH_TH;
			}
			logger.info("SH_TH �˻ص�Ŀ���λ�����������(������)��ʼ��Ywid=" + model.getYwid() + " Thgw=" + model.getThgw() + " SH_XCS=" + SH_XCS + " Shlc=" + model.getShlc() + " Sqrid=" + model.getSqrid() + " Tzlj=" + model.getTzlj() + " Tzljsq=" + model.getTzljsq());
			// �˻ص�Ŀ���λ�����������(������)
			dao.insertThjdNotCommit(model.getYwid(), model.getThgw(),SH_XCS,model.getShlc(),model.getSqrid(),model.getTzlj(),model.getTzljsq());
			logger.info("SH_TH �˻ص�Ŀ���λ�����������(������)����");
			return YW_SHZ;

		} else if (isSuccess && SH_TG.equals(model.getShzt())) {
			
			if(StringUtils.isNull(xjgw)&&lastGwid.equalsIgnoreCase(dshGwid)){
				logger.info("SH_TG ���һ�����");
				//���һ�����
				return SH_TG;
			}
			logger.info("SH_TG ������һ������ʼ��Ywid=" + model.getYwid() + " xjgw=" + xjgw  + " Shlc=" + model.getShlc() + " Sqrid=" + model.getSqrid() + " Tzlj=" + model.getTzlj() + " Tzljsq=" + model.getTzljsq());
			// ������һ������
			dao.insertDbjdNotCommit(model.getYwid(), xjgw ,model.getShlc(),model.getSqrid(),model.getTzlj(),model.getTzljsq());
			logger.info("SH_TG ������һ���������");
		}
		logger.info("���� CommShlcImpl.runAuditing ����");
		return YW_SHZ;

	}

	
	/*
	      ����: @see com.zfsoft.xgxt.comm.shlc.service.ShlcInterface#runSubmit(java.lang.String, java.lang.String)
	 */
	public synchronized boolean runSubmit(String ywid, String shlc,String xh,String tzlj,String tzljsq) throws Exception {
		
		//�ж��Ƿ��Ѳ������״̬��,��������ظ�����
		if(dao.getExistForShzt(ywid)){
			//�ظ��ύ
			throw new SystemException(MessageKey.SYS_AUD_DOUBLE);
		}
		
		//��ѯ��һ����λID
		String firstGwid = dao.getFirstGwid(shlc);
		//�����һ������ڵ�
		boolean result = dao.insertDbjd(ywid,firstGwid,shlc,xh,tzlj,tzljsq);

		//TODO ���һ������˸�λ������Ϣ
		sendDshMsg(firstGwid,xh,shlc,result);
		return result;
	}

	
	/*
	      ����: @see com.zfsoft.xgxt.comm.shlc.service.ShlcInterface#runCancel(java.lang.String, java.lang.String, java.lang.String)
	 */
	public synchronized boolean runCancel(String shr,String ywid, String shlc, String gwid)
			throws Exception {
		/*
		 * -------------����Ƿ���Գ������-----------
		 * �Ⱦ���������ȷ���Լ��������������״̬
		 * ������⣺
		 * 
		 * �ٲ�ͨ��
		 * �� ͨ������ǰ����ļ�¼���Լ�����һ������δ���
		 * �� �˻� �����ܳ��������쳣
		 * 
		 */
		String lastShzt = dao.getLastShzt(shr, ywid, gwid);
		String nextSpgw = dao.getNextGwid(shlc, gwid);
		
//		if(StringUtils.isNull(nextSpgw)){
//			throw new SystemException(MessageKey.SYS_CANCEL_END);
//		}
		boolean isFhtj = false;
		if (SH_BTG.equals(lastShzt)){
			//��ͨ������ֱ�ӳ���
			isFhtj = true;
		} else if (SH_TG.equals(lastShzt)){
			isFhtj = true;
			//ɾ��������λ����һ���������ݣ�ɾ���ɹ��ɳ��������ܡ�
			if (!StringUtil.isNull(nextSpgw)){
				isFhtj = dao.delNextDsjl(ywid,nextSpgw);
			}
			
		} else if (SH_TH.equals(lastShzt)){
			//�˻�״̬������
			throw new SystemException(MessageKey.SYS_CANCEL_TH);
		} else {
			//��֪��״̬��������
			throw new SystemException(MessageKey.SYS_AUD_CANCEL_FAIL);
		}
		
		//���ϳ�������--���²�����λ��״̬Ϊ���������Ӳ�����λ��������
		if (isFhtj){
			dao.updateShzt(ywid, gwid, SH_DSH);
			//dao.insertDbjd(ywid, gwid);

			// �ҵĴ���ع�
			MyJobsManager manager = new MyJobsImpl();
			manager.cancelJob(ywid, gwid);
			
			return true;
			
		}else{
			throw new SystemException(MessageKey.SYS_CANCEL_FAIL);
		}
	}
	
	/**
	 * 
	 * @����:ֻ�и��ύ���ҵ�һ��δ��˵�ǰ���£������˿��Գ���
	 * @���ߣ�945
	 * @���ڣ�2013-11-25 ����06:41:47
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ywid
	 * @param lcid
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public synchronized boolean firstStepCancle(String ywid,String lcid) throws Exception{
		boolean isFhtj = false;
		List<HashMap<String, String>> firstZt = dao.getFirstShzt(ywid, lcid);
		if(firstZt!=null&&firstZt.size()>0){
			if(firstZt.size()>1){
				throw new SystemException(MessageKey.SYS_CANCEL_FAIL);
			}else{
				String shzt = firstZt.get(0).get("shzt");
//				String gwid = firstZt.get(0).get("gwid");
				
				if(shzt.equals("0")){
					isFhtj = true;
				}else{
					throw new SystemException(MessageKey.SYS_CANCEL_FAIL);
				}
				//���ϳ�������--ɾ�����ύ��¼�����Ӳ�����λ��������
				if (isFhtj){
					dao.delelteShjl(ywid);
//					// �ҵĴ���ع�
//					MyJobsManager manager = new MyJobsImpl();
//					manager.cancelJob(ywid, gwid);
					return true;
				}else{
					throw new SystemException(MessageKey.SYS_CANCEL_FAIL);
				}
			}
		}else{
			throw new SystemException(MessageKey.SYS_CANCEL_FAIL);
		}
	}
	

	/**
	 * ����: ������ˡ����һ�����ɳ�����
	 * @���ߣ�qilm
	 * @���ڣ�2013-10-11 
	 * @param shr �����
	 * @param shid ���ID��ϵͳά��-���״̬��GUID��
	 * @param shlc �������ID
	 */
	public synchronized boolean runCancel(String shr, String shid,
			String shlc) throws Exception {

		// ȡ��ҵ��ID/������λID
		HashMap<String, String> shxx = ShlcUtil.getShxx(shid);
		String ywid = shxx.get("ywid");
		String gwid = shxx.get("gwid");
		/*
		 * -------------����Ƿ���Գ������-----------
		 * �Ⱦ���������ȷ���Լ��������������״̬
		 * ������⣺
		 * 
		 * �ٲ�ͨ��
		 * �� ͨ������ǰ����ļ�¼���Լ�����һ������δ���
		 * �� �˻� �����ܳ��������쳣
		 * 
		 */
		String lastShzt = dao.getLastShzt(shr, ywid, gwid);
		String nextSpgw = dao.getNextGwid(shlc, gwid);
		
		if(StringUtils.isNull(nextSpgw)){
			throw new SystemException(MessageKey.SYS_CANCEL_END);
		}
		boolean isFhtj = false;
		if (SH_BTG.equals(lastShzt)){
			//��ͨ������ֱ�ӳ���
			isFhtj = true;
		} else if (SH_TG.equals(lastShzt)){
			//ɾ��������λ����һ���������ݣ�ɾ���ɹ��ɳ��������ܡ�
			isFhtj = dao.delNextDsjl(ywid,nextSpgw);
		} else if (SH_TH.equals(lastShzt)){
			//�˻�״̬������
			throw new SystemException(MessageKey.SYS_CANCEL_TH);
		} else {
			//��֪��״̬��������
			throw new SystemException(MessageKey.SYS_AUD_CANCEL_FAIL);
		}
		
		//���ϳ�������--���²�����λ��״̬Ϊ���������Ӳ�����λ��������
		if (isFhtj){
			 dao.updateShzt(shid, SH_DSH);
			 //dao.insertDbjd(ywid, gwid);

			// �ҵĴ���ع�
			MyJobsManager manager = new MyJobsImpl();
			manager.cancelJob(ywid, gwid);
			 
			return true;
		}else{
			throw new SystemException(MessageKey.SYS_CANCEL_FAIL);
		}
	}
	
	/**
	 * ����: ������ˡ����ɳ�����
	 * @���ߣ�qilm
	 * @���ڣ�2013-10-11 
	 * @param shr �����
	 * @param shid ���ID��ϵͳά��-���״̬��GUID��
	 * @param shlc �������ID
	 */
	public synchronized String runCancelNew(String shr, String shid,
			String shlc) throws Exception {
		
		// ȡ��ҵ��ID/������λID
		HashMap<String, String> shxx = ShlcUtil.getShxx(shid);
		String ywid = shxx.get("ywid");
		String gwid = shxx.get("gwid");
		String shqzt= shxx.get("shqzt");//��ȡ��ʼ���ʱ������״̬�����ڳ���ʱ���״̬�Ļع����Ż�����ʱĬ��״̬Ϊδ����е�bug
		/*
		 * -------------����Ƿ���Գ������----------- 
		 * �Ⱦ���������ȷ���Լ��������������״̬ ������⣺
		 * 
		 * �ٲ�ͨ�� 
		 * �� ͨ������һ��δ��� 
		 * �� �˻� �����ܳ��������쳣
		 */
		String lastShzt = dao.getLastShzt(shr, ywid, gwid);
		String nextSpgw = dao.getNextGwid(shlc, gwid);

		boolean isFhtj = false;
		if (SH_BTG.equals(lastShzt)) {
			// ��ͨ������ֱ�ӳ���
			isFhtj = true;
		} else if (SH_TG.equals(lastShzt)) {
			// ɾ��������λ����һ���������ݣ�ɾ���ɹ��ɳ��������ܡ�
			
			isFhtj = dao.delNextDsjl(ywid, nextSpgw);
		} else if (SH_TH.equals(lastShzt)) {
			// �˻�״̬������
			throw new SystemException(MessageKey.SYS_CANCEL_TH);
		} else {
			// ��֪��״̬��������
			throw new SystemException(MessageKey.SYS_AUD_CANCEL_FAIL);
		}

		// ���ϳ�������--���²�����λ��״̬Ϊ���������Ӳ�����λ��������
		if (isFhtj || StringUtils.isNull(nextSpgw)) {
			
				// �������״̬�����Ӳ�����λ��������
			if (dao.updateShzt(shid, shqzt) ) {
				
//				// �ҵĴ���ع�
//				MyJobsManager manager = new MyJobsImpl();
//				manager.cancelJob(ywid, gwid);
				
				// �ж��Ƿ����һ������
				if (StringUtils.isNull(nextSpgw) || SH_BTG.equals(lastShzt)) {
					return CANCLE_FLG_SUCCESS_ZHYJ;
				}else {
					return CANCLE_FLG_SUCCESS;
				}
			}
		} else {
			throw new SystemException(MessageKey.SYS_CANCEL_FAIL);
		}
		return CANCLE_FLG_ERROR;
	}

	
	/**
	 * @����:ɾ����˼�¼
	 * @���ߣ�zhangjw
	 * @���ڣ�2013-7-1 ����03:47:05
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ywid ҵ��ID
	 * @return
	 * @throws Exception
	 * boolean ��������
	 */
	public boolean deleteShjl(String ywid) throws Exception {
		return dao.delelteShjl(ywid);
	}


	
	
	/*
	      ����: @see com.zfsoft.xgxt.comm.shlc.service.ShlcInterface#getKthSpgw(java.lang.String, java.lang.String)
	 */
	
	public List<HashMap<String, String>> getKthSpgw(String splc, String gwid) {
		return dao.getKthSpgw(splc, gwid);
	}
	
	public HashMap<String, String> getShxxByCondition(String ywid,String gwid) {
		return dao.getShxxByCondition(ywid,gwid);
	}
	
	public boolean splcsfwc(String ywid){
		List<HashMap<String, String>> l = dao.splcsfwc(ywid);
		if(l!=null&&l.size()>0){
			return false;
		}else{
			return true;
		}
	}
	
	/*
	      ����: @see com.zfsoft.xgxt.comm.shlc.service.ShlcInterface#getShyjList(xgxt.form.User, java.lang.String)
	 */
	
	public List<HashMap<String, String>> getShyjList(User user, String gnid) {
		
		return dao.getShyjList(user, gnid);
	}
	
	
	
	/*
	 * 
	      ����: @see com.zfsoft.xgxt.comm.shlc.service.ShlcInterface#saveCyyj(xgxt.form.User, java.lang.String, java.lang.String[])
	 */
	public boolean saveCyyj(User user,String gnid,String[] shyj){
		
		if (shyj == null || shyj.length == 0 || StringUtil.isNull(gnid))
			return false;
		
		
		try {
			boolean b = dao.delCyyj(user, gnid);
			
			if (b){
				return dao.saveCyyj(user, gnid, shyj);
			}
			
			return b;
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * @����:����ҵ��id��ȡ��Ӧ�ĸ���λ��������ͨ��ʱ���������б�
	 * 		 �б�˳�򰴸�λ������ȵ���ÿ��HashMap����shr,shsj,shyj,shrmc
	 * 		 ͨ���������صǼǱ������ȡһ�������Ϣ�����������Ϣ...
	 *		 ���ﷵ�ص�list��Ĭ�ϴ�С>=7������ҵ������ȡ��Ӧ������Ϣ��������𲻴��ڣ�
	 *		����Ȼ����ȡ��HashMap����������Ϊempty��HashMap��ΪNULL���������г�ʼ������
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��5��16�� ����10:46:09
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ywid
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getShyjListByYwid(String ywid){
		
		List<HashMap<String,String>> list = dao.getShyjListByYwid(ywid);
		for(int i=list.size();i<7;i++){
			list.add(new HashMap<String,String>());
		}
		return list;
	}
	
  public synchronized boolean runSubmitBatchNotCommit(String[] ywids, String shlc,String[] xhs,String tzlj,String tzljsq) throws Exception {
	  //��ѯ��һ����λID
	    String firstGwid = dao.getFirstGwid(shlc);
	    List<String[]> paraList = new ArrayList<String[]>();
		//�ж��Ƿ��Ѳ������״̬��,��������ظ�����
		for (int i = 0; i < ywids.length; i++) {
			if(dao.getExistForShzt(ywids[i])){
				//�ظ��ύ
				throw new SystemException(MessageKey.SYS_AUD_DOUBLE);
			}
			paraList.add(new String[]{ywids[i],firstGwid,shlc,xhs[i],tzlj,tzljsq});
		}
		
		
		
		//�����һ������ڵ�
		return dao.insertDbjdBatchNotCommit(paraList);
	}

	/**
	 *  ����splc��ѯģ������.
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2017-10-12 19:07
	 * @param splc
	 * @return java.lang.String
	 * @throw
	 */
	public String getSsmkBySplc(String splc){

		return dao.getSsmkBySplc(splc);
	}

	/**
	 *  ����gwid��������xh��ѯ�����ְ�����б�.
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2017-10-12 19:09
	 * @param gwid
	 * @param sqr
	 * @return java.util.List<xgxt.form.User>
	 * @throw SQLException
	 */
	public List<String> getShrListByGwidAndSqr(String gwid,String sqr) throws SQLException {

		return dao.getShrListByGwidAndSqr(gwid,sqr);
	}

	/**
	 *  �������ͣ�����������ʹ�����Ϣ.
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2017-10-13 15:12
	 * @param gwid
	 * @param sqr
	 * @return void
	 * @throw SQLException
	 */
	public void sendDshMsg(String gwid,String sqr,String splc,boolean result) throws SQLException {

		//send jiguang   message
		if (result && "1".equalsIgnoreCase(Base.getInitProperties().get("isOpen"))) {//open
			List<String> zghList = getShrListByGwidAndSqr(gwid,sqr);
			String mkmc = getSsmkBySplc(splc);
			mkmc = StringUtils.isNull(mkmc)?"δ֪ģ��":mkmc;

			JgxxtsService js = new JgxxtsService();
			HashMap<String, String> paramMap = new HashMap<String, String>();
			paramMap.put("content", "���յ�һ������'"+mkmc+"'�Ĵ������Ϣ");
			paramMap.put("newsCount", "1");
			paramMap.put("webUrl", Base.getInitProperties().get("dbdz"));
			paramMap.put("webName", "ѧ��վ����");
			paramMap.put("tsry", sqr);

			for (String account : zghList) {
				paramMap.put("account", account);
				String msg = js.sendMsg(paramMap);
			}

		}
	}
}
