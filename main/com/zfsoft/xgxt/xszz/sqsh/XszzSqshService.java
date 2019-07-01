/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-4-25 ����02:27:01 
 */  
package com.zfsoft.xgxt.xszz.sqsh;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.check.CheckCondition;
import com.zfsoft.xgxt.base.check.impl.CheckStudentCondition;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.xszz.xmwh.rssz.XmwhRsszDao;
import com.zfsoft.xgxt.xszz.xmwh.tjsz.XmwhTjszService;
import com.zfsoft.xgxt.xszz.xmwh.xmwh.XmwhDao;
import com.zfsoft.xgxt.xszz.xmwh.xmwh.XmwhForm;
import com.zfsoft.xgxt.xszz.xszzbjpy.jgcx.JgcxDao;
import com.zfsoft.xgxt.xszz.xszzbjpy.jgcx.JgcxForm;
import com.zfsoft.xgxt.xszz.zzxmjg.ZzxmjgDao;
import com.zfsoft.xgxt.xszz.zzxmjg.ZzxmjgForm;
import com.zfsoft.xgxt.xszz.zzxmjg.ZzxmjgService;
import net.sf.json.JSONArray;
import xgxt.action.Base;
import xgxt.form.User;
import xgxt.utils.GetTime;
import xgxt.utils.Pages;
import xgxt.utils.String.StringUtils;
import xsgzgl.xtwh.wdgz.MyJobsManager;
import xsgzgl.xtwh.wdgz.impl.MyJobsImpl;

import java.text.DecimalFormat;
import java.util.*;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: ѧ������2013��--��Ŀ������� 
 * @���ߣ� Penghui.Qu 
 * @ʱ�䣺 2013-4-25 ����02:27:01 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XszzSqshService extends SuperServiceImpl<XszzSqshForm, XszzSqshDao> implements Constants{
	// �Ƿ�༶����.1���ǣ�0����
	private static final boolean SFBJPY_Y = "1".equals(MessageUtil.getText("xszz.sfbjpy"));
	private static final String SQSH = "1";//�������
	
	private ShlcInterface shlc = new CommShlcImpl();
	private XszzSqshDao dao = new XszzSqshDao();
	private XmwhDao xmDao=new XmwhDao();
	private ZzxmjgDao zzjgDao = new ZzxmjgDao();
	
	public XszzSqshService(){
		super.setDao(dao);
	}
	
	public List<HashMap<String,String>> getXmsqInfoList(String xh){
		
		return dao.getXmsqInfoList(xh);
	}
	
	public List<HashMap<String , Object>> getXmsqInfoList(XszzSqshForm model) throws Exception{
		if(model == null || model.getXh() == null){
			throw new RuntimeException("ѧ�Ų���Ϊ��");
		}
		
		XmwhTjszService tjszService = new XmwhTjszService();
		
		List<HashMap<String , String>> xmsqInfoList = dao.getXmsqInfoList(model, model.getSqType()); //��Ŀ�б�
		
		List<HashMap<String,Object>> finalRet = new ArrayList<HashMap<String,Object>>(); //�践���б�
		
		if(StringUtils.isEqual("wsq", model.getSqType())){
			String xh = model.getXh();
			for (HashMap<String, String> xmxx : xmsqInfoList) {
				if(StringUtils.isNull(xmxx.get("shzt")) || 
						StringUtils.isEqual(Constants.YW_YTH, xmxx.get("shzt")) || 
						StringUtils.isEqual(Constants.YW_WTJ, xmxx.get("shzt"))){
					//���ؽ��
					HashMap<String,Object> resultMap = new HashMap<String,Object>();			
					resultMap.putAll(xmxx);
					String xmdm = (String)resultMap.get("xmdm");
					List<HashMap<String, String>> conditions = tjszService.getTjszGl(xmdm , xh);
					// У�����������ز�������������Ŀ���ơ�
					CheckCondition check = new CheckStudentCondition();
					List<HashMap<String, String>> faileds = check.checkCondition(xh, conditions);
					
					String checks = "true";
					if(faileds != null){
						for (HashMap<String, String> hashMap : faileds) {
							String val = hashMap.get("result");
							if(StringUtils.isEqual(val, "false")){
								checks = "false";
								break;
							}
						}
					}
					resultMap.put("checkable", checks);
					resultMap.put("conditionCheckResult", faileds);
					finalRet.add(resultMap);
				}
			}
		}else if(StringUtils.isEqual("ysq", model.getSqType())){
			for (HashMap<String,String> xmxx : xmsqInfoList){
				if(!(StringUtils.isNull(xmxx.get("shzt")) || StringUtils.isEqual(Constants.YW_YTH, xmxx.get("shzt")) || StringUtils.isEqual(Constants.YW_WTJ, xmxx.get("shzt")))){
					//���ؽ��
					HashMap<String,Object> resultMap = new HashMap<String,Object>();
					resultMap.putAll(xmxx);
					finalRet.add(resultMap);
				}
			}
		}
		
		return finalRet;
	}
	
	public List<HashMap<String , String>> getAllYsqXmList(XszzSqshForm model) throws Exception{
		List<HashMap<String , String>> xmsqInfoList = dao.getAllXmsqInfoList(model); //��Ŀ�б�
		
		List<HashMap<String,String>> finalRet = new ArrayList<HashMap<String,String>>(); //�践���б�
		for (HashMap<String,String> xmxx : xmsqInfoList){
			if(StringUtils.isEqual("1", xmxx.get("sfsq"))){
				//���ؽ��
				HashMap<String,String> resultMap = new HashMap<String,String>();
				resultMap.putAll(xmxx);
				finalRet.add(resultMap);
			}
		}
		return finalRet;
		
	}
	
	public boolean bcxgXmsq(XszzSqshForm model)throws Exception{
		if(model.getType().equals("submit")){
			if(SFBJPY_Y){
				model.setShzt(Constants.YW_BJPYZ);
			}else{
				model.setShzt(Constants.YW_SHZ);
			}
		}else{
			model.setShzt(YW_WTJ);
		}
		
		boolean isSuccess = dao.runUpdate(model);
		if (isSuccess){
			if(!SFBJPY_Y && model.getType().equals("submit")){
				XszzSqshForm sqModel = dao.getModel(model);
				//���칤��
				isSuccess = shlc.runSubmit(sqModel.getGuid(), sqModel.getShlc(), sqModel.getXh(), "xszz_sqsh_xmsh.do", "xszz_sqsh_xmsq_stu.do");
			}
		}
		return isSuccess;
	}
	
	/**
	 * @param ylzd1 
	 * 
	 * @����:������Ŀ���� 
	 * @���ߣ�Penghui.Qu
	 * @���ڣ�2013-4-26 ����09:58:03
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param xmdmArray
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveXmsq(XszzSqshForm model, String[] xmdmArray, String[] ylzd1) throws Exception{
		
		if (xmdmArray == null || xmdmArray.length == 0){
			logger.error("δѡ����Ŀ��");
			throw new NullPointerException();
		}
		
		model.setSqsj(GetTime.getTimeByFormat("YYYY-MM-DD hh24:mi:ss"));
		if(model.getType().equals("submit")){
			if(SFBJPY_Y){
				model.setShzt(YW_BJPYZ);
			}else{
				model.setShzt(YW_SHZ);
			}
		}else{
			model.setShzt(YW_WTJ);
		}
		
		for (int i = 0 , n = xmdmArray.length ; i < n ; i++){
			model.setXmdm(xmdmArray[i]);
			model.setDqxmdm(xmdmArray[i]);
			//������Ŀ��Ϣ
			XmwhDao xmwhDao = new XmwhDao();
			XmwhForm xmwhModel = new XmwhForm();
			xmwhModel.setXmdm(xmdmArray[i]);
			XmwhForm xmInfo = xmwhDao.getModel(xmwhModel);
			
			if (null != xmInfo && !StringUtil.isNull(xmInfo.getSplc())){
				String shlc = xmInfo.getSplc();
				model.setShlc(shlc);
				model.setXn(Base.currXn);
				model.setXq(Base.currXq);
				model.setPdxn(xmInfo.getPdxn());
				model.setPdxq(xmInfo.getPdxq());
				//ѧ����������������Ŀ������ѧ��������
				if(!"".equalsIgnoreCase(ylzd1[i])){
					model.setYlzd1(ylzd1[i]);
				}
			}
			//��������
			boolean isSuccess = dao.runInsert(model);
			
			if (isSuccess){
				if(!SFBJPY_Y && model.getType().equals("submit")){
					XszzSqshForm sqModel = dao.getModelByXhSqsj(model);
					//�������״̬
//					dao.insertShzt(sqModel, model.getShlc());
					
					//���칤��
					isSuccess = shlc.runSubmit(sqModel.getGuid(), model.getShlc(), sqModel.getXh(), "xszz_sqsh_xmsh.do", "xszz_sqsh_xmsq_stu.do");
	//				XtwhShlcDAO splcDao = new XtwhShlcDAO();
	//				String lcid = model.getShlc();		
	//				String[] spgw = splcDao.getSpgwByLcid(lcid);
	//
	//				if (null != spgw && spgw.length > 0){
	//					Job job = Job.getJobInstance(sqModel.getGuid(), model.getXh(), spgw[0], 
	//					"xszz_sqsh_xmsh.do", 
	//					"xszz_sqsh_xmsq.do","ѧ������", xmInfo.getXmmc());
	//					MyJobsManager manager = new MyJobsImpl();
	//					manager.pushJob(job);
	//				}
				}
			}
		}
		
		return true;
	}
	
	
	/**
	 * 
	 * @����:��˲�ѯ
	 * @���ߣ�Penghui.Qu
	 * @���ڣ�2013-4-26 ����10:31:48
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getShjlList(XszzSqshForm t, User user) throws Exception{
		
		return dao.getShjlList(t, user);
	}
	
	
	
	/**
	 * 
	 * @����:������Ŀ���
	 * @���ߣ�Penghui.Qu
	 * @���ڣ�2013-4-26 ����11:06:00
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveXmsh(XszzSqshForm t,User user) throws Exception{
		
		
		//�ж��������
		HashMap<String,String> shxx =ShlcDao.getDqjbByCondition(t.getGuid(), user.getUserName(), t.getShlc(), "sh");
		//���ǰһ�������Ŀ����
		String tzhxmdm = "";
		String rskzxh = dao.getRskzXh(t.getShxmdm());

		//��������
		if (TG.equals(t.getShjg()) && !StringUtil.isNull(shxx.get("xh")) && new Integer(shxx.get("xh")).intValue()>=new Integer(rskzxh).intValue()){
			tzhxmdm=t.getShxmdm();
			checkRskz(t, "sh");
		}
	
		XmwhForm xmForm = new XmwhForm();
		xmForm.setXmdm(t.getShxmdm());
		xmForm = xmDao.getModel(xmForm);
		
		//������
		if (TG.equals(t.getShjg()) && !StringUtil.isNull(xmForm.getJe()) 
				&& Double.valueOf(xmForm.getJe()) > 0 && !StringUtil.isNull(shxx.get("xh"))
				&& new Integer(shxx.get("xh")).intValue()>=new Integer(rskzxh).intValue()){
			//��ѯѧ������Ժϵ������Ŀ���Ѿ�ʹ�õĽ��
			//ѧ���ѻ���ܽ��
			Double ytgJe = Double.valueOf(dao.getYtgJe(t.getXh(), t.getShxmdm(), t.getXtgwid()));
			String xyjes = dao.getXmzJe(t.getXh(), t.getShxmdm());
			if(!"".equals(xyjes)){

				//ѧԺ�趨���
				Double xyJe = Double.valueOf(dao.getXmzJe(t.getXh(), t.getShxmdm()));
				Double shje;
				//1:��Ŀ���ɱ�
				if("1".equalsIgnoreCase(xmForm.getJesfxssq())){
					shje = Double.valueOf(t.getShxmje());
				} else {
					shje = Double.valueOf(xmForm.getJe());
				}
				if ((ytgJe+shje) > xyJe){
					throw new SystemException(MessageKey.XSZZ_JEKZ_FAIL, xyJe);
				}
			}
		}

		ShxxModel model = new ShxxModel();
		model.setYwid(t.getGuid());
		model.setShlc(t.getShlc());
		model.setShr(user.getUserName());
		model.setShyj(t.getShyj());
		model.setShzt(t.getShjg());
		model.setThgw(t.getThgw());
		model.setGwid(t.getXtgwid());
		model.setSqrid(t.getXh());
		model.setTzlj("xszz_sqsh_xmsh.do");
		model.setTzljsq("xszz_sqsh_xmsq_stu.do");

		if(t.getShjg().equals("1")){
			// �O��ҵ���ֶ�1[ҵ������]
			model.setZd1("����������Ŀ");
			// �O��ҵ���ֶ�2[ҵ��ID]
			model.setZd2(t.getShxmdm());
			// �O��ҵ���ֶ�3
			model.setZd3(xmForm.getXmmc());
		}
		//�����Ŀ�����ԣ�����ʦ��д�Ľ����뵽��˱��ҵ���ֶ���
		if("1".equalsIgnoreCase(xmForm.getJesfxssq())){
			model.setZd4("�������");
			model.setZd5(t.getShxmje());
			model.setZd6(t.getShxmje());
		}
		boolean result = false;
		
			//���ͨ��-���ж��Ƿ��ڽ�����и�ѧ���ĸ�ѧ��ѧ�ڵĸý���
			if(TG.equals(t.getShjg())){
				ZzxmjgForm zzjgForm = new ZzxmjgForm();
				XszzSqshForm xmzzSqshForm=new XszzSqshForm();
				xmzzSqshForm=dao.getModel(t.getGuid());
				zzjgForm.setXh(xmzzSqshForm.getXh());
				zzjgForm.setXn(xmzzSqshForm.getXn());
				zzjgForm.setXq(xmzzSqshForm.getXq());
				zzjgForm.setPdxn(xmzzSqshForm.getPdxn());
				zzjgForm.setPdxq(xmzzSqshForm.getPdxq());
				zzjgForm.setSqsj(xmzzSqshForm.getSqsj());
				zzjgForm.setXmmc(xmForm.getXmmc());//������Ŀ����
				
				String num = zzjgDao.checkExistForSave(zzjgForm);
				if(Integer.valueOf(num) > 0){
					return false;
				}
			}
						
			String shzt = shlc.runAuditing(model);
			//����������е����״̬
			XszzSqshForm xmzzSqshForm=new XszzSqshForm();
			xmzzSqshForm.setGuid(t.getGuid());
			xmzzSqshForm.setShzt(shzt);
			xmzzSqshForm.setTzhxmdm(tzhxmdm);
			xmzzSqshForm.setDqxmdm(t.getShxmdm());
			result=dao.runUpdate(xmzzSqshForm);
			// ���һ�����ͨ��ʱ
			if(result && shzt.equals(Constants.SH_TG)){
				//������ʽ��
				
				ZzxmjgForm zzjgForm = new ZzxmjgForm();
				xmzzSqshForm=dao.getModel(t.getGuid());
				
				zzjgForm.setXh(xmzzSqshForm.getXh());
				zzjgForm.setXn(xmzzSqshForm.getXn());
				zzjgForm.setXq(xmzzSqshForm.getXq());
				zzjgForm.setSqsj(xmzzSqshForm.getSqsj());
				zzjgForm.setSqly(xmzzSqshForm.getSqly());
				zzjgForm.setPdxn(xmzzSqshForm.getPdxn());
				zzjgForm.setPdxq(xmzzSqshForm.getPdxq());
				zzjgForm.setLylcywid(xmzzSqshForm.getGuid());
				zzjgForm.setXmmc(xmForm.getXmmc());//������Ŀ����
				zzjgForm.setLbdm(xmForm.getLbdm());//������Ŀ���
				XsxxService xsxxService = new XsxxService();
				HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(xmzzSqshForm.getXh());
				//����ʦ����ѧ���Ի�֤����
				if("10511".equals(Base.xxdm)){
					ZzxmjgService zzxmjgService = new ZzxmjgService();
					zzjgForm.setNj(xsjbxx.get("nj"));
					zzjgForm.setXmdm(zzxmjgService.getXmdm(zzjgForm));//ȡ��Ŀ����
					zzjgForm.setXydm(xsjbxx.get("xydm"));
					zzjgForm.setZsbm(zzxmjgService.getZsbm(zzjgForm));
				}
				zzjgForm.setSjly(SQSH);//����������Դ
				
				//�������ֵ����
				if("1".equalsIgnoreCase(xmForm.getJesfxssq())){
					//��Ŀ���ɱ�:��ǰ̨ȡ
					zzjgForm.setJe(t.getShxmje());
				}else{
					//��Ŀ���̶�������Ŀ��ȡ
					zzjgForm.setJe(xmForm.getJe());
				}
				zzjgForm.setYlzd5(t.getYlzd5()); // ����id
				result=zzjgDao.runInsert(zzjgForm);
			}
			
			
			// �˻� ������ʱ
			if(result && SFBJPY_Y && shzt.equals(Constants.SH_TH) && "-1".equals(t.getThgw())){
				XszzSqshForm xszzSqshModelOld = new XszzSqshForm();
				xszzSqshModelOld.setGuid(t.getGuid());
				XszzSqshForm xszzSqshModelNew = dao.getModel(xszzSqshModelOld);
				JgcxDao jgcxDao = new JgcxDao();
				// ���°༶�����
				boolean rs = jgcxDao.cxBjpyxzpy(xszzSqshModelNew.getXn(), xszzSqshModelNew.getXq(), xszzSqshModelNew.getXmdm(), xszzSqshModelNew.getXh());
				if(rs){
					// ���½����ѯ��
					JgcxForm jgcxForm = new JgcxForm();
					jgcxForm.setSqid(xszzSqshModelNew.getGuid());
					jgcxForm.setTjzt("0");
					jgcxForm.setTjsj(" ");
					jgcxForm.setShzt(" ");
					jgcxDao.runUpdate(jgcxForm);
				}
			}
		
		
//		HashMap<String,String> beforeSpxx = dao.getBeforeSpxx(t);
//		
//		//��ǰ��˸�λ�ڵ�һ�����������˻ز��������������߼�����������
//		if (StringUtil.isNull(beforeSpxx.get("sjgw")) && TH.equals(t.getShzt())){
//			return false;
//		}
		
		//��������Ŀ����Ϊ�գ���Ĭ��Ϊ�ϼ�ȷ�ϵ������Ŀ
//		if (StringUtil.isNull(t.getShxmdm())){
//			t.setShxmdm(beforeSpxx.get("shxm"));
//		}
		
//		int num = dao.runAuditing(t, user);
//		
//		if (num > 0){
//			String shzt = t.getShzt();
//			String lashShzt = SHZ;;//������е�״̬
//			
//			Job job = null;
//			
//			if (TG.equals(shzt)){
//				HashMap<String,String> nextSpxx = dao.getNextSpxx(t);
////				//ͨ�� ���� ��ǰ������λΪ��߼��������£�
////				// �� ���ݲ�����ʽ��;
////				// �� ����������״̬�����������θ���Ϊ����״̬
//				if (StringUtil.isNull(nextSpxx.get("xjxh"))){
//					lashShzt  = TG;
////					//������ʽ��
//					ZzxmjgDao zzjgDao = new ZzxmjgDao();
//					ZzxmjgForm zzjgForm = new ZzxmjgForm();
//					
//					XszzSqshForm sqshModel = dao.getModel(t);
//					
//					BeanUtils.copyProperties(zzjgForm, sqshModel);
//					
//					zzjgForm.setSjly(SQSH);
//					//���ջ��������Ŀ�����ơ����ȷ��
//					XmwhDao xmwhDao = new XmwhDao();
//					XmwhForm paramModel = new XmwhForm();
//					paramModel.setXmdm(t.getShxmdm());
//					XmwhForm xmwhModel = xmwhDao.getModel(paramModel);
//					zzjgForm.setXmmc(xmwhModel.getXmmc());
//					zzjgForm.setJe(xmwhModel.getJe());
//					zzjgForm.setLbdm(xmwhModel.getLbdm());
//					
//					String jgNum = zzjgDao.checkExistForSave(zzjgForm);
//					if (Integer.valueOf(jgNum) == 0){
//						zzjgDao.runInsert(zzjgForm);
//					}
//				}
//				
//				//Ҫ����һ��״̬��Ϊ"δ���"
//				if (!StringUtil.isNull(nextSpxx.get("xjgw"))){
//					dao.setShzt(t.getGuid(), nextSpxx.get("xjgw"), WSH);
//					
//				}
//				job = Job.getJobInstance(t.getGuid(),nextSpxx.get("xjgw"),
//						"xszz_sqsh_xmsh.do","ѧ������");
//			} else if (BTG.equals(shzt)){
////				//��ͨ������������������״̬��Ϊ����ͨ����
//				lashShzt  = BTG;
//				job = Job.getJobInstance(t.getGuid(),"ѧ������");
//			} else if (TH.equals(shzt)){
////				//�˻أ��������������״̬���䣨ǰ����������һ���������˻ز����������޲���;
////				//����һ�����״̬��Ϊ��������
//				dao.setShzt(t.getGuid(), beforeSpxx.get("sjgw"), XCS);
//				job = Job.getJobInstance(t.getGuid(),beforeSpxx.get("sjgw"),
//						"xszz_sqsh_xmsh.do","ѧ������");
//			}
////			
//			MyJobsManager manager = new MyJobsImpl();
//			manager.updateJob(job);
//			
////			//������е����״̬���϶����α��
//			XszzSqshForm model = new XszzSqshForm();
//			model.setGuid(t.getGuid());
//			model.setShzt(lashShzt);
//			dao.runUpdate(model);
//		}
		
		return result;
	}
	
	
	//�����������
	private void checkRskz(XszzSqshForm t, String type) throws Exception{
		
		XmwhDao xmwhDao = new XmwhDao();
		Map<String,String> xmwhMap = xmwhDao.getDataById(t.getShxmdm());
		//�������Ʒ�Χ/����/����
		String rskzfw = xmwhMap.get("rskzfw");
			XmwhRsszDao rsszDao = new XmwhRsszDao();
			//�����������Map
			Map<String,String> rsszMap = rsszDao.getRsszOne(t.getShxmdm(), t.getXh(), rskzfw);
			
			String xzrs = rsszMap.get("zzrs");
			//δ���þͲ�����
			if (StringUtil.isNull(xzrs)){
				return ;
			}
			
			String tgrs = null;
			
			if (RSKZFW_NJXY.equals(rskzfw)){
				tgrs = dao.getTgrsByNjxy(t, rsszMap);
			} else if (RSKZFW_NJZY.equals(rskzfw)){
				tgrs = dao.getTgrsByNjzy(t, rsszMap);
			} else if (RSKZFW_XY.equals(rskzfw)){
				tgrs = dao.getTgrsByXy(t, rsszMap);
			} else if(RSKZFW_SY.equals(rskzfw)){//����Ժͳ��ͨ������
				tgrs = dao.getTgrsBySy(t, rsszMap);
			} else{
				tgrs = dao.getTgrsByBj(t, rsszMap);
			}
			
			if (!(Integer.valueOf(tgrs) < Integer.valueOf(xzrs))){
				if("sh".equals(type)){
					throw new SystemException(MessageKey.RSKZ_FAIL,xzrs);
				}else if("cx".equals(type)){
					throw new SystemException(MessageKey.RSKZ_FAIL_CANCEL,xzrs);
				}

			}
		}

	
	
	
	/**
	 * 
	 * @����: ��ѯͨ������
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-6-8 ����02:22:47
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param rskzfw
	 * @param xmdm
	 * @param xn
	 * @param xq
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getShtgrs(String rskzfw,String xmdm,String xn,String xq){
		
		if (StringUtils.isNull(rskzfw)){
			return null;
		}
		if (RSKZFW_NJXY.equals(rskzfw)){
			return dao.getTgrsByNjxy(xmdm, xn, xq);
		} else if (RSKZFW_NJZY.equals(rskzfw)){
			return dao.getTgrsByNjzy(xmdm, xn, xq);
		} else if (RSKZFW_XY.equals(rskzfw)){
			return dao.getTgrsByXy(xmdm, xn, xq);
		} else if(RSKZFW_BJ.equals(rskzfw)){
			return dao.getTgrsByBj(xmdm, xn, xq);
		} else if(RSKZFW_SY.equals(rskzfw)){
			return dao.getTgrsBySy(xmdm, xn, xq);
		}
		return null;
	}
	
	
	
	/**
	 * 
	 * @����:���һ���������
	 * @���ߣ�Penghui.Qu
	 * @���ڣ�2013-4-26 ����11:09:57
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean cancelXmsh(XszzSqshForm t) throws Exception{
		
		XszzSqshForm xszzSqshForm=new XszzSqshForm();
		xszzSqshForm.setGuid(t.getGuid());
		xszzSqshForm.setTzhxmdm("");
		xszzSqshForm.setShzt(YW_SHZ);
		//�޸���˱�״̬
		boolean result=dao.runUpdate(xszzSqshForm);
		if(result){
			//ҵ��ع�
			zzjgDao.delByYwid(t.getGuid());
		}
		return result;
		
//		//��������¼Ŀǰ�����״̬
//		String currShzt = dao.getCurrunShzt(t);
//		
//		if (TG.equals(currShzt)){
//			HashMap<String,String> map = dao.getNextSpxx(t);
//			//���һ�������ҵ�ǰ��״̬�Ѿ���ͨ����
//			if (StringUtil.isNull(map.get("xjgw"))){
//				//�������¼�����������ѽ���
//				throw new SystemException(MessageKey.SYS_CANCEL_END);
//			}
//		}
//		
//		int num = dao.cancelAuditing(t);
//		
//		if (num > 0){
//			HashMap<String,String> beforeShxx = dao.getBeforeSpxx(t);
//			//�������Ϊ��һ������������е�״̬��Ϊ��δ��ˡ�
//			if (StringUtil.isNull(beforeShxx.get("sjgw"))){
//				XszzSqshForm model = new XszzSqshForm();
//				model.setGuid(t.getGuid());
//				model.setShzt(WSH);
//				dao.runUpdate(model);
//			}
//			
//			//�����˻ز�������ǰһ����״̬����Ϊͨ��
//			if (TH.equals(currShzt) ){
//				dao.setShzt(t.getGuid(), beforeShxx.get("sjgw"), TG);
//			} 
//			
//			//����
//			Job job = Job.getJobInstance(t.getGuid(),t.getXtgwid(),
//					"xszz_sqsh_xmsh.do","ѧ������");
//			MyJobsManager manager = new MyJobsImpl();
//			manager.updateJob(job);
//		}
//		
//		return num > 0;
	}
	
	
	
	/**
	 * 
	 * @����:��ȡ�����¼��Ӧ�ĸ���������Ϣ
	 * @���ߣ�Penghui.Qu
	 * @���ڣ�2013-4-26 ����04:18:03
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	@Deprecated
	public List<HashMap<String,String>> getSplcInfo(XszzSqshForm model){
		
		if (StringUtil.isNull(model.getGuid())){
			logger.error("����ID����Ϊ�գ�");
			throw new NullPointerException();
		}
		
		return dao.getSplcInfo(model);
	}

	
	
	//ɾ�� 
	public int runDelete(String[] values) throws Exception {
		
		//ɾ����˼�¼
		//dao.delShzt(values);
		
		//ɾ�������¼
		int delNum = dao.delXmsq(values);
		
		//ɾ����ش���
		String[] ids = dao.getExistsId(values); 
		
		//ȥ���ظ�
		List<String> valuesList = new ArrayList<String>(Arrays.asList(values));
		valuesList.removeAll(Arrays.asList(ids));
		
		String[] yscId = valuesList.toArray(new String[]{});
		
		if (yscId != null && yscId.length > 0){
			MyJobsManager manager = new MyJobsImpl();
			manager.removeJob(yscId, "ѧ������");
		}
		
		return delNum;
	}
	

	
	/**
	 * @����:���������ɾ����������Ϣ
	 * @���ߣ�Penghui.Qu
	 * @���ڣ�2013-5-6 ����01:55:24
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param values
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean delXmsqFromZzjg(String[] values) throws Exception{
		
		if (values == null || values.length ==0){
			return false;
		}
		
		
		int num = dao.delXmsqFromZzjg(values);
		
//		if (num > 0){
//			dao.delXmshFromZzjg(values);
//		}
		
		return num > 0;
	}
	

	
	/**
	 * 
	 * @����:�������
	 * @���ߣ�Penghui.Qu
	 * @���ڣ�2013-4-26 ����04:54:43
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * String �������� 
	 * @throws
	 */
	public String savePlsh(XszzSqshForm t,User user) throws Exception{
		
		String[] ids = t.getId();
		String[] gwid = t.getGwid();
		String[] xhs = t.getXhs();
		
		//���ж�ѡ���ѧ���������Ƿ񳬹�ѡ��ѧ��������Ŀ����������
		if(t.getShzt().equals("1")){
			String sqids = "";
			for(int i=0;i<ids.length;i++){
				sqids += "'"+ids[i]+"',";
			}
			if(sqids.length()>0){
				sqids=sqids.substring(0, sqids.length()-1);
			}
			
			List<HashMap<String,String>> results = dao.getXzdrs(sqids);
			StringBuilder sqshXmdmBuilder = new StringBuilder(); // ��ȡ������Ŀ���루����������
			for(int i=0;i<results.size();i++){
				HashMap<String,String> rmap = (HashMap<String,String>) results.get(i);
				if(new Integer(rmap.get("dqjb")).intValue()>=new Integer(rmap.get("kzjb")).intValue()){
					HashMap<String,String> tmap = dao.getKzrsTgrsByXmdm(rmap.get("xmdm"),rmap.get("dqjb"),rmap.get("cpbm"),rmap.get("rskzfw"));
					if(tmap!=null&&tmap.size()>0){
						if(new Integer(rmap.get("xzrs")).intValue()>new Integer(tmap.get("zzme")).intValue()-new Integer(tmap.get("ytggs")).intValue()){
							return MessageUtil.getText(MessageKey.SYS_AUD_PERS_OUT);
						}
					}
				}
				String sqshXmdm = rmap.get("xmdm");
				if(sqshXmdmBuilder.indexOf(sqshXmdm) == -1){
					sqshXmdmBuilder.append("'").append(sqshXmdm).append("',");
				}
			}
			String sqshXmdms = "";
			if(sqshXmdmBuilder.length()>0){
				sqshXmdms = sqshXmdmBuilder.substring(0, sqshXmdmBuilder.length()-1);
			}
			// �ж�ѧ�������ܽ���Ƿ񳬹�����ѧԺ���ܽ��
			 boolean jeFlag = dao.checkPlshJe(sqids, sqshXmdms);
			 if(!jeFlag){
				 return MessageUtil.getText(MessageKey.XSZZ_JEKZ_TEXT_FAIL);
			 }
		}
		List<String> failXhs = new ArrayList<String>();
		
		for (int i = 0 , n = ids.length ; i < n ; i++){
			XszzSqshForm model = new XszzSqshForm();
			model.setGuid(ids[i]);
			
			
			HashMap<String, String> tmpMap = shlc.getShxxByCondition(ids[i],gwid[i]);
			XszzSqshForm x = dao.getModel(model);
			if(tmpMap.get("xjzd2")==null||tmpMap.get("xjzd2").equals("")){
				model.setShxmdm(x.getDqxmdm());
			}else{
				model.setShxmdm(tmpMap.get("xjzd2"));
			}
			//�����Ի��жϣ�������˼��ϵ�������ֶ�
			if(t.getShxmje()==null||t.getShxmje().equals("")){
			if(tmpMap.get("xjzd5")==null||tmpMap.get("xjzd5").equals("")){
				XmwhDao xmdao = new XmwhDao();
				if(x.getYlzd1()==null||x.getYlzd1().equals("")){
					XmwhForm xm = new XmwhForm();
					xm.setXmdm(x.getXmdm());
					xm = xmdao.getModel(xm);
					model.setShxmje(xm.getJe());
				}else{
					model.setShxmje(x.getYlzd1());
				}
			}else{
				model.setShxmje(tmpMap.get("xjzd5"));
				}
			}else{
				model.setShxmje(t.getShxmje());
			}
			
			model.setXtgwid(gwid[i]);
			model.setShlc(tmpMap.get("lcid"));
			model.setShyj(t.getShyj());
			model.setShjg(t.getShzt());
			model.setXh(xhs[i]);
			model.setYlzd5(x.getYlzd5()); // ����id
			
			boolean isSuccess = saveXmsh(model, user);
			
			if (!isSuccess){
				failXhs.add(xhs[i]);
			}
		}
		
		JSONArray json = JSONArray.fromObject(failXhs); 
		
		if (failXhs.isEmpty()){
			return MessageUtil.getText(MessageKey.SYS_AUD_SUCCESS);
		} else if (TH.equals(t.getShzt())){
			return MessageUtil.getText(MessageKey.SYS_BACK_FAIL, json.toString());
		} else {
			return MessageUtil.getText(MessageKey.SYS_PLSH_FAIL, json.toString());
		}
	}
	

	
	/**
	 * 
	 * @����:�ж��Ƿ������������е�����
	 * @���ߣ�ligl
	 * @���ڣ�2013-5-10 ����09:24:40
	 * @�޸ļ�¼: 
	 * @param xmdm
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean isExistShlcData(String xmdm) throws Exception{
		int count = dao.getRsszCount(xmdm);
		return count > 0;
	}

	
	
	/**
	 * 
	 * @����: ������ͳ��
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-5-23 ����09:58:10
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param user
	 * @return
	 * List<HashMap<String,Object>> �������� 
	 */
	public Map<String,Object> getShqkInfo(User user,String xmdm){
		
		String xn = Base.currXn;
		String xq = Base.currXq;
		
		//����������
		int zrs = Integer.valueOf(dao.getSqrs(user, xn, xq,xmdm));
		//����������
		List<HashMap<String,String>> shqkInfoList = dao.getShqkTjxx(user, xn, xq,xmdm);
		//����������ͨ��������ͨ���ʡ��������ͳ�����
		Map<String,Object> resultMap = new HashMap<String, Object>();
		resultMap.put("zrs", zrs);
		
		List<HashMap<String,String>> shqkList = new ArrayList<HashMap<String,String>>();
		
		if (zrs == 0){
			resultMap.put("zztgrs","0");
			resultMap.put("zztgl", "0%");
		}
		
		for (int i = 0 , n = shqkInfoList.size() ; i < n ; i++){
			
			HashMap<String,String> map = new HashMap<String, String>();
			map.putAll(shqkInfoList.get(i));
			
//			double tgrs = Double.valueOf(shqkInfoList.get(i).get("tg"));
//			double bgtrs = Double.valueOf(shqkInfoList.get(i).get("btg"));
//			double dshrs = Double.valueOf(shqkInfoList.get(i).get("dsh"));
			DecimalFormat formater = new DecimalFormat("#.##%");
			
//			if (tgrs+bgtrs+dshrs == 0){
//				map.put("tgl",  "0%");
//				map.put("btgl", "0%");
//				map.put("dshl", "0%");
//			} else {
//				double tgl = tgrs/(tgrs+bgtrs+dshrs);
//				double btgl =bgtrs/(tgrs+bgtrs+dshrs);
//				double dshl = dshrs/(tgrs+bgtrs+dshrs);
//				
//				map.put("tgl", formater.format(tgl));
//				map.put("btgl", formater.format(btgl));
//				map.put("dshl", formater.format(dshl));
//			}
			
			double dcls = Double.valueOf(shqkInfoList.get(i).get("dcl"));
			double ycls = Double.valueOf(shqkInfoList.get(i).get("ycl"));
			
			if (dcls+ycls == 0){
			map.put("dcll",  "0%");
			map.put("ycll", "0%");
		} else {
			double dcll = dcls/(dcls+ycls);
			double ycll = ycls/(dcls+ycls);
			
			map.put("dcll", formater.format(dcll));
			map.put("ycll", formater.format(ycll));
		}			
			
			
			shqkList.add(map);
			
//			if (i == n-1){
//				//����ͨ����
//				double zztgl = tgrs/zrs;
//				resultMap.put("zztgrs",shqkInfoList.get(i).get("tg"));
//				resultMap.put("zztgl", formater.format(zztgl));
//			}
		}
		
		resultMap.put("shqkList", shqkList);
		return resultMap;
	}
	
	
	
	/**
	 *  
	 * @����: ���ͳ��ѧ���б�
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-6-8 ����02:29:15
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getStudentsList(XszzSqshForm model,User user) throws Exception{
		
		String xn = Base.currXn;
		String xq = Base.currXq;
		
		model.setXn(xn);
		model.setXq(xq);
		
		return dao.getStudentsFromShtj(model, user); 
	}

	/** 
	 * @����:�ύ
	 * @���ߣ�cmj[���ţ�913]
	 * @���ڣ�2013-12-4 ����05:21:54
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param values
	 * @param lcid
	 * @param xh
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean submitRecord(String pkValue,String lcid,String xh) throws Exception {
		return shlc.runSubmit(pkValue, lcid,xh,"xszz_sqsh_xmsh.do", "xszz_sqsh_xmsq_stu.do");
	}
	/**
	 * 
	 * @����:����
	 * @���ߣ�cmj[���ţ�913]
	 * @���ڣ�2013-12-4 ����05:53:09
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ywid
	 * @param lcid
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean cancleRecord(String ywid,String lcid) throws Exception{
		return shlc.firstStepCancle(ywid,lcid);
	}
	
	public boolean updateModel(XszzSqshForm model) throws Exception {
		return super.runUpdate(model);
	}

	/**
	 * @����:��˳���
	 * @���ߣ�cmj
	 * @���ڣ�2013-12-16 ����02:49:46
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
	public String cxshnew(String ywid, XszzSqshForm model, User user) throws Exception{
		ShlcInterface service = new CommShlcImpl();
		
		//�ж��������
		HashMap<String,String> shxx =ShlcDao.getDqjbByCondition(ywid, user.getUserName(), model.getShlc(), "cx");
		
		//���ǰһ�������Ŀ����
		String tzhxmdm = shxx.get("sjxmdm");
		String rskzxh = dao.getRskzXh(tzhxmdm);
		String shzt = Constants.YW_SHZ;
		String dqxmdm =null;
		if(null==shxx.get("sjxmdm")||"".equals(shxx.get("sjxmdm"))){
			 dqxmdm = dao.getXmdm(ywid);
		}else{
		     dqxmdm = shxx.get("sjxmdm"); 
		}
		
		//�����ǰ��¼����˲�ͨ����������˼�����ڵ��ڿ��Ƽ���
		int shxxXh = new Integer(shxx.get("xh"));
		if(Constants.SH_BTG.equals(shxx.get("shzt")) && shxxXh > 1){
			String spgw = new ShlcDao().getBeforeGwid(shxx.get("splc"), shxx.get("gwid"));
			model.setXtgwid(spgw);
			if(StringUtil.isNull(rskzxh)){
				model.setShxmdm(dqxmdm);
				checkRskz(model, "cx");
			}else{ 
				if(shxxXh>=new Integer(rskzxh).intValue()){
					model.setShxmdm(tzhxmdm);
					checkRskz(model, "cx");
				}
			}
		}
		
//		if(new Integer(shxx.get("xh")).intValue()<=new Integer(rskzxh).intValue()){
//			tzhxmdm = "";
//		}
	
		String cancelFlag= service.runCancelNew(user.getUserName(), model.getShid(), model.getShlc());
		//�ع�������еĵ�������Ŀ
		dao.updateSqjl(ywid, tzhxmdm, shzt,dqxmdm);
		return cancelFlag;

	}
	
	/**
	 * 
	 * @����:���������������ĳ����Ŀ������ɹ���¼��
	 * @���ߣ���־��
	 * @���ڣ�2014-5-4 ����03:16:42
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xmmc ��Ŀ����
	 * @param xh ѧ��
	 * @return String ��������
	 * @throws
	 */
	public String getTheSameZzxmNumber(String xmmc, String xh) {
		return dao.getTheSameZzxmNumber(xmmc,xh);
	}
	
	/**
	 * 
	 * @����:��ѧ�ڼ�ͬʱ����������(��)ѧ��
	 * @���ߣ���־��
	 * @���ڣ�2014-5-4 ����03:57:42
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xmmc ��Ŀ����
	 * @param xh ѧ��
	 * @return 
	 * @throws
	 */
	public List<HashMap<String,String>> getYwqtjxList(String xmmc, String xh){
		return dao.getYwqtjxList(xmmc, xh);
	}
	
	/**
	 * 
	 * @����:���ذ༶����
	 * @���ߣ���־��
	 * @���ڣ�2014-5-29 ����06:57:42
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh ѧ��
	 * @return int ��������
	 * @throws
	 */
	public String countBjRs(String xh) {
		return dao.countBjRs(xh);
	}
	
	//��˵�������ҳ���÷���
	public List<HashMap<String,String>> getAllListSh(XszzSqshForm t, User user) throws Exception {
		
		
		Pages pages = (Pages) t.getClass().getMethod("getPages").invoke(t);
		pages.setPageSize(Integer.MAX_VALUE);
		
		t.getClass().getMethod("setPages",Pages.class).invoke(t, pages);
		
		return dao.getShjlList(t, user);
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
	public List<HashMap<String, String>> getYsqxmAll(XszzSqshForm model,User user)
			throws Exception {
		
		return dao.getYsqxmAll(model,user);
	}	
	
	/**
	 * 
	 * @����:��ѯ�������������Ŀ(�����)
	 * @���ߣ�ligl
	 * @���ڣ�2014-3-26 ����03:33:46
	 * @�޸ļ�¼: 
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getYsqxmAllYsh(XszzSqshForm model,User user)
			throws Exception {
		
		return dao.getYsqxmAllYsh(model,user);
	}

	/** 
	 * @����:��ѯδ������Ŀ
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2015-10-8 ����10:10:36
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getWsqList(String xh) {
		return dao.getWsqList(xh);
	}

	/** 
	 * @����:����guid ��ѯjgsqzq
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2015-10-9 ����05:28:09
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param values
	 * @return
	 * String �������� 
	 * @throws 
	 */
	public String getJgsqzq(String values) {
		return dao.getJgsqzq(values);
	}
	
	
	/**
	 * 
	 * @����:���ز����ύ����Ŀ
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2015-10-10 ����10:57:53
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param values
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public HashMap<String, String> getFalseXmdm(String values){
		return dao.getFalseXmdm(values);
	}

	/**
	 * 
	 * @����:��ȡ��Ŀ���
	 * @���ߣ�Huang Chenji
	 * @���ڣ�2015-10-27 ����14:40:33
	 * @�޸ļ�¼:
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getXmlb() throws Exception {
		return dao.getXmlb();
	}

	/** 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ��Ƴ���
	 * @���ڣ�2015-12-8 ����12:25:25
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws 
	 */
	public HashMap<String, String> getGyxx(String xh) {
		if (StringUtil.isNull(xh)) {
			logger.error("xh is null !");
			throw new NullPointerException();
		}

		try {
			return dao.getGyxx(xh);
		} catch (Exception e) {
			logger.error("select to_char(to_date(a.csrq, 'yyyy-mm-dd')) from view_xg_gygl_new_cwxx a where xh ='"+xh+"'");
			throw new NullPointerException();
		}
	}
	
	/**
	 * @����: ��ʦ���ͨ�������ս��
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ�2016-11-25 ����11:36:13
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param xn
	 * @param xq
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String zzxmshtgJe(String xh, String xn, String xq) {
		return dao.zzxmshtgJe(xh,xn,xq);
	}

	/**
	 *  �쳣���ݴ���.
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2017-11-17 17:53
	 * @param
	 * @return java.util.Map<java.lang.String,java.lang.String>
	 * @throw Exception
	 */
	public Map<String,String> exceptionDataResolve() throws Exception {

		boolean success =true;
		String message = "����ɹ���";
		Map resultMap = new HashMap<String,Object>();
		List<HashMap<String,String>> exceptionDataList = dao.getExceptionDataList();
		if(exceptionDataList.size() != 0){
			for(HashMap<String,String> edMap:exceptionDataList){
				success = shlc.runSubmit(edMap.get("guid"), edMap.get("shlc"), edMap.get("xh"), "xszz_sqsh_xmsh.do", "xszz_sqsh_xmsq_stu.do");
				if(!success){
					message = "����ʧ�ܣ�";
				}
			}
		}else{
			message = "���쳣���ݣ�";
		}
		resultMap.put("success",success);
		resultMap.put("message",message);
		return resultMap;
	}
	/**
	 * 
	 * @����:��ȡѧ����ѧ������Ϣ
	 * @���ߣ�Penghui.Qu
	 * @���ڣ�2013-4-26 ����10:51:13
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return
	 * HashMap<String,String> �������� 
	 * @deprecated
	 * @throws
	 */
    public  String getXsDkxx(String xn,String xh){
    	return dao.getXsDkxx(xn,xh);
    }

	/**
	 * @����:�ӱ����Ĺ�����־��ѧ��ɼ�Ϊ����Ŀγ���
	 * @���ߣ�lgx [���ţ�1553]
	 * @���ڣ�2018/9/4 17:49
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param: [xh]
	 * @return: java.util.HashMap
	 */
	public String getYxksByXh_12389(String xh,String xn,String kcxz) {
		return dao.getYxksByXh_12389(xh,xn,kcxz);
	}
	/**
	 * @����:�ӱ����Ĺ�����־��ѧ��ɼ�Ϊ���Ŀγ���
	 * @���ߣ�lgx [���ţ�1553]
	 * @���ڣ�2018/9/4 17:49
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param: [xh]
	 * @return: java.util.HashMap
	 */
	public String getLkcsByXh_12389(String xh,String xn,String kcxz) {
		return dao.getLkcsByXh_12389(xh,xn,kcxz);
	}

	public String getCjpmXy(String xn, String xh, String zydm) {
		return dao.getCjpmXy(xn,xh,zydm);
	}

	public String getZyzrs(String zydm) {
		return dao.getZyzrs(zydm);
	}

    public List<HashMap<String,String>> getSqExportList(XszzSqshForm myForm, User user) throws Exception {
		return dao.getSqExportList(myForm,user);
    }

	public List<HashMap<String,String>> getShExportList(XszzSqshForm myForm, User user) throws Exception {
		return dao.getShExportList(myForm,user);
	}
}

