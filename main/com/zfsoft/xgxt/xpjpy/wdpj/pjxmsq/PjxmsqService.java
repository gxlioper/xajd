/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-12-9 ����11:26:03 
 */  
package com.zfsoft.xgxt.xpjpy.wdpj.pjxmsq;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import java.util.List;

import xgxt.form.User;
import xgxt.utils.String.StringUtils;
import xsgzgl.xtwh.wdgz.Job;
import xsgzgl.xtwh.wdgz.MyJobsManager;
import xsgzgl.xtwh.wdgz.impl.MyJobsImpl;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.check.CheckCondition;
import com.zfsoft.xgxt.base.check.impl.CheckStudentCondition;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
import com.zfsoft.xgxt.xpjpy.cssz.CsszDao;
import com.zfsoft.xgxt.xpjpy.cssz.CsszModel;
import com.zfsoft.xgxt.xpjpy.wdpj.sqsh.SqshModel;
import com.zfsoft.xgxt.xpjpy.xmsz.tjsz.TjszService;
import com.zfsoft.xgxt.xpjpy.xmsz.xmwh.XmwhDao;
import com.zfsoft.xgxt.xpjpy.xmsz.xmwh.XmwhModel;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������:  
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2013-12-9 ����11:26:03 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class PjxmsqService extends SuperServiceImpl<PjxmsqModel, PjxmsqDao> {

	private PjxmsqDao dao = new PjxmsqDao();

	/**
	 * @���� ��TODO�����µ�ǰ���췽��
	 */
	public PjxmsqService() {
		super.setDao(dao);
	}
	
	
	public List<HashMap<String , Object>> getSqXmList(PjxmsqModel model) throws Exception{
		
		String xh = model.getXh();
		
		String queryType = model.getQueryType();
		
		List<HashMap<String , String>> retData = dao.getPageList(model);
		
		List<HashMap<String,Object>> finalRet = new ArrayList<HashMap<String,Object>>();
		
		if(StringUtils.isEqual("wsq", queryType)){
			TjszService tjszServcie = new TjszService();
			//��֤����
			for (HashMap<String,String> pjxm : retData){
				//���ؽ��
				HashMap<String,Object> resultMap = new HashMap<String,Object>();			
				resultMap.putAll(pjxm);
				List<HashMap<String, String>> conditions = tjszServcie.getTjszGl(pjxm.get("xmdm"),xh);
				// У�����������ز�������������Ŀ���ơ�
				CheckCondition check = new CheckStudentCondition();
				//У����
				List<HashMap<String, String>> results = check.checkCondition(xh, conditions);
				
				String checks = "true";
				if(results != null){
					for (HashMap<String, String> hashMap : results) {
						String val = hashMap.get("result");
						if(StringUtils.isEqual(val, "false")){
							checks = "false";
							break;
						}
					}
				}
				
				resultMap.put("checkable", checks);
				
				resultMap.put("conditionCheckResult", results);
				
				finalRet.add(resultMap);
			}
		}else if(StringUtils.isEqual("ysq", queryType)){
			
			for (HashMap<String,String> pjxm : retData){
				//���ؽ��
				HashMap<String,Object> resultMap = new HashMap<String,Object>();
				resultMap.putAll(pjxm);
				finalRet.add(resultMap);
			}
		}
		return finalRet;
	}
	
/**
 * 	
 * @����:TODO(������һ�仰�����������������)
 * @���ߣ���С��[����:1036]
 * @���ڣ�2013-12-10 ����05:12:01
 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
 * @param xmdm
 * @param t
 * @return
 * @throws Exception
 * boolean �������� 
 * @throws
 */
	public boolean saveJxsq(String[] xmdm ,PjxmsqModel t) throws Exception{
		
		
		if (xmdm == null || xmdm.length==0){
			return false;
		}
		
		for (String pjxm : xmdm){
			
			PjxmsqModel model = new PjxmsqModel();
			model.setXh(t.getXh());
			model.setSqly(t.getSqly());
			model.setYlzd5(t.getYlzd5()); // ����id
			model.setXmdm(pjxm);
			model.setTzhxmdm(pjxm);
			saveJxsb(model, t.getXh());
		}
		return true;
	}

	/**
	 * 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2013-12-10 ����05:16:19
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param userName
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveJxsb(PjxmsqModel model, String userName) throws Exception{
		
		String sqid = UniqID.getInstance().getUniqIDHash();
		
		//��Ŀ��Ϣ
		XmwhDao xmwhDao = new XmwhDao();
		XmwhModel xmwhModel = xmwhDao.getModel(model.getXmdm());
		String splc = xmwhModel.getShlc();
		
		//����������Ϣ
		CsszDao csszDao = new CsszDao();
		CsszModel csszModel = csszDao.getModel();
		
		model.setSqr(userName);
		model.setXn(csszModel.getXn());
		model.setXq(csszModel.getXq());
		model.setSqid(sqid);
		model.setSplc(splc);
		//����������Ϣ
		boolean result = dao.runInsert(model);
		
		if (result) {
			ShlcDao shlcDao = new ShlcDao();
			//��ѯ��һ����λID
			String firstGwid = shlcDao.getFirstGwid(splc);
			//�����һ�������¼
			dao.insertDbjd(sqid, firstGwid);
			
			// ���칤��
			Job job = Job.getJobInstance(sqid,
					model.getXh(), firstGwid, "pj_jxsh.do",
					"pj_pjxmsq.do", "��������", xmwhModel.getXmmc());
			MyJobsManager manager = new MyJobsImpl();
			manager.pushJob(job);
		}
		return result;
	}


	/*
	      ����: @see com.zfsoft.xgxt.base.service.impl.SuperServiceImpl#getModel(java.lang.Object)
	 */
	
	@Override
	public PjxmsqModel getModel(PjxmsqModel t) throws Exception {
		// TODO �Զ����ɷ������
		return super.getModel(t);
	}
	
	/**
	 * 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2013-12-10 ����07:14:53
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param sqid
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getSpjlList(String sqid){
		return dao.getSpjlList(sqid);
	}
	
	
	
	/**
	 * 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2013-12-10 ����07:15:43
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param sqid
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getDsgw(String sqid){
		
		if (StringUtil.isNull(sqid)){
			return null;
		}
		
		return dao.getDsgw(sqid);
	}


	/** 
	 * @����:��ǰϵͳʱ��
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-3-10 ����03:13:57
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param dateFormat
	 * @return
	 * Object �������� 
	 * @throws 
	 */
	public String getCurrTime(String dateFormat){
		DateFormat df = new SimpleDateFormat(dateFormat);
		return df.format(new Date());
	}


	/** 
	 * @����:�ж�ѧ���Ƿ���ڲ�������
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-5-16 ����05:16:42
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param csszModel
	 * @param user
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean isExistCpz(CsszModel csszModel, String xh) {
		
		String num = dao.isExistCpz(csszModel, xh);
		
		return Integer.valueOf(num)>0;
	}
	
	
	/**
	 * 
	 * @����:�õ�ĳѧ����������Ŀ����ϸ��Ϣ (copy from mobile)
	 * @���ߣ�ligl
	 * @���ڣ�2014-3-26 ����04:28:45
	 * @�޸ļ�¼: 
	 * @param xh
	 * @param xmdm
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String,String> getYsqxmDetail(String xh,String xmdm)throws Exception {
		return dao.getYsqxmDetail(xh, xmdm);
	}
	
	
	/**
	 * 
	 * @����:��ѯ�������������Ŀ(�����) copy from mobile
	 * @���ߣ�ligl
	 * @���ڣ�2014-3-26 ����03:33:46
	 * @�޸ļ�¼: 
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getYsqxmAllYsh(SqshModel model,User user)
			throws Exception {
		
		return dao.getYsqxmAllYsh(model,user);
	}	
	
	
	/**
	 * 
	 * @����:��ѯ�������������Ŀ
	 * @���ߣ�ligl
	 * @���ڣ�2014-3-26 ����03:33:46
	 * @�޸ļ�¼: 
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getYsqxmAll(SqshModel model,User user)
			throws Exception {
		
		return dao.getYsqxmAll(model,user);
	}
	
	
	/**
	 * 
	 * @����: ������˼�������������
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-3-16 ����08:36:57
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param xn
	 * @param xq
	 * @param xmmc
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getAllShyjList(String xh, String xn, String xq, String xmdm) {
		return dao.getAllShyjList(xh, xn, xq, xmdm);
	}
	
	/**
	 * @��������ȡ ѧ�ꡢ��1ѧ�ڡ���2ѧ�� ����
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2016��11��15�� 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * HashMap<String,String> ��������
	 */
	public HashMap<String,String> getJd_10277(String xh,String xn){
		return dao.getJd_10277(xh, xn);
	}
	
	/**
	 * 
	 * @��������ȡ��ߺ�����Ļ��γɼ�
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-11-29 ����05:03:31
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param xn
	 * @param xq
	 * @return
	 * String �������� 
	 * @throws
	 */
	public  HashMap<String,String> getMaxOrMinWfkCj(String xh,String xn ,String xq){
		return dao.getMaxOrMinWfkCj(xh, xn, xq);
	}
	
	/**
	 * 
	 * @����: ���غ�ʱ��ҵ����
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2018-4-28 ����02:32:37
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getHsbyjs(String xh){
		return dao.getHsbyjs(xh);
	}
}
