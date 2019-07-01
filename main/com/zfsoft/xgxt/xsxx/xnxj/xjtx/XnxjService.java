/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-12-19 ����01:50:46 
 */  
package com.zfsoft.xgxt.xsxx.xnxj.xjtx;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.xsxx.xnxj.jcsz.JcszForm;
import com.zfsoft.xgxt.xsxx.xnxj.jcsz.JcszService;
import com.zfsoft.xgxt.xsxx.xnxj.xjjg.XnxjJgDao;
import com.zfsoft.xgxt.xsxx.xnxj.xjjg.XnxjJgForm;
import com.zfsoft.xgxt.xsxx.xnxj.xjjg.XnxjJgService;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ѧ��С��ģ��
 * @�๦������: ѧ��С��service 
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2013-12-19 ����01:50:46 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XnxjService extends SuperServiceImpl<XnxjForm, XnxjDao> {

	private XnxjDao dao = new XnxjDao();
	
	private XnxjJgDao xnxjJgDao = new XnxjJgDao();
	
	private ShlcInterface shlc = new CommShlcImpl(); //����������ӿ�

	/**
	 * @���� ��TODO�����µ�ǰ���췽��
	 */
	public XnxjService() {
		super.setDao(dao);	
	}
	
	/**
	 * 
	 * @����:����ѧ�� ѧ�Ż�ȡѧ��С��
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2013-12-20 ����09:32:36
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return
	 * @throws Exception
	 * XnxjForm �������� 
	 * @throws
	 */
	public XnxjForm getModel(XnxjForm t) throws Exception{
		return dao.getModel(t, new String[]{t.getXh() , t.getXn()});
	}
	
	/**
	 * 
	 * @����:����ѧ��С��
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2013-12-31 ����05:28:07
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean updateXnxj(XnxjForm model) throws Exception{
		if(model == null) return false;
		
		if(model.getId()== null) return false;
		
		String xnxj = model.getXjnr();
		
		XnxjForm t = dao.getModel(model.getId());
		
		JcszService jcszService = new JcszService();
		JcszForm jcszModel = jcszService.getModel();
		
		if(t == null){ return false; }
		
		t.setXjnr(xnxj);
		
		return dao.runUpdate(t);
		
	}
	
	/**
	 * 
	 * @����:����ѧ��С��
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2013-12-20 ����09:34:06
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveXnxj(XnxjForm model) throws Exception{
		
		XnxjForm t = dao.getModel(model , new String[]{model.getXh() , model.getXn()}); //����ѧ�� ѧ���ѯ�Ƿ������������
		
		JcszService jcszService = new JcszService();
		
		JcszForm jcszModel = jcszService.getModel();
		
		boolean isSuccess = false;
		
		if(t == null){
			if(jcszModel != null && StringUtils.isNotNull(jcszModel.getSpl())){ 
				model.setSplid(jcszModel.getSpl()); 							//�����������������
			}
			model.setShjg(Constants.YW_WTJ);   									//����δ�ύ״̬
			model.setTxsj(GetTime.getTimeByFormat("YYYY-MM-DD hh24:mi:ss")); 	//����ʱ������
			isSuccess = dao.runInsert(model); 									//���������
			if(isSuccess){
				if(StringUtils.isEqual(model.getType(), "submit")){  			//�ж��Ƿ����ύ����
					String spl = jcszModel.getSpl();
																				//��ȡ������ѧ��С������
					XnxjForm xnxjModel = dao.getModel(new XnxjForm(), new String[]{model.getXh() , model.getXn()}); 
					if(StringUtils.isEqual("wxsh", spl)){						//�����������õ�������˲������ύ��������ֱ�Ӳ�������
						XnxjJgService xnxjjgService = new XnxjJgService();
						XnxjJgForm xnxjJgModel = new XnxjJgForm();
						xnxjJgModel.setSjly(Constants.SJLY_SPL);
						xnxjJgModel.setSqid(model.getId());
						xnxjJgModel.setTxsj(model.getTxsj());
						xnxjJgModel.setXh(model.getXh());
						xnxjJgModel.setXjnr(model.getXjnr());
						xnxjJgModel.setXn(model.getXn());
						isSuccess = xnxjjgService.runInsert(xnxjJgModel);
						if(isSuccess){
							xnxjModel.setShjg(Constants.YW_TG);
							dao.runUpdate(xnxjModel); 						//�������ɹ����޸�������״̬Ϊͨ��
						}
					}else{													//������˱�
						isSuccess = shlc.runSubmit(xnxjModel.getId(), jcszModel.getSpl(), model.getXh(), "xsxx_xnxj_xjsh.do", "xsxx_xnxj_xjtx.do");
						if(isSuccess){
							xnxjModel.setShjg(Constants.YW_SHZ);
							dao.runUpdate(xnxjModel); 						//�޸�Ϊ�����״̬
						}
					}
				}
			}
			return isSuccess;
		}else{
			
			if(StringUtils.isEqual("wxsh", t.getSplid())){						//�����������õ�������˲������ύ��������ֱ�Ӳ�������
				XnxjJgService xnxjjgService = new XnxjJgService();
				XnxjJgForm xnxjJgModel = new XnxjJgForm();
				xnxjJgModel.setSjly(Constants.SJLY_SPL);
				xnxjJgModel.setSqid(model.getId());
				xnxjJgModel.setTxsj(model.getTxsj());
				xnxjJgModel.setXh(model.getXh());
				xnxjJgModel.setXjnr(model.getXjnr());
				xnxjJgModel.setXn(model.getXn());
				isSuccess = xnxjjgService.runInsert(xnxjJgModel);
				if(isSuccess){
					t.setShjg(Constants.YW_TG);
					isSuccess = dao.runUpdate(t); 								//�������ɹ����޸�������״̬Ϊͨ��
				}
			}else{																//������˱�
				isSuccess = shlc.runSubmit(t.getId(), t.getSplid(), t.getXh(), "xsxx_xnxj_xjsh.do", "xsxx_xnxj_xjtx.do");
				if(isSuccess){
					t.setShjg(Constants.YW_SHZ);
					isSuccess = dao.runUpdate(t); 								//�޸�Ϊ�����״̬
				}
			}
			return isSuccess;
		}
		
	}
	
	/**
	 * 
	 * @����:ɾ��ѧ��С��
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2013-12-20 ����01:41:29
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param sqidList
	 * @return
	 * @throws Exception
	 * int �������� 
	 * @throws
	 */
	public int delXnxjsq(String[] sqidList) throws Exception{
		if(sqidList == null){
			return 0;
		}
		
		return dao.runDelete(sqidList);
		
	}
	
	/**
	 * 
	 * @����:�����ύѧ��С������
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2013-12-20 ����02:33:20
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param sqids
	 * @return
	 * @throws Exception
	 * int �������� 
	 * @throws
	 */
	public int submitXnxjs(String[] sqids) throws Exception{
		
		if(sqids == null || sqids.length == 0) return 0;
		
		int i=0;
		for (String string : sqids) {
			
			XnxjForm model = dao.getModel(string);
			
			String ywid = model.getId();
			String sqr = model.getXh();
			if(!Constants.YW_YTH.equals(model.getShjg())){ 
				
				JcszService jcszService = new JcszService();
				JcszForm jcszModel = jcszService.getModel();
				
				model.setSplid(jcszModel.getSpl());
			}
			String splid = model.getSplid();
			boolean isSuccess = shlc.runSubmit(ywid, splid, sqr, "xsxx_xnxj_xjsh.do", "xsxx_xnxj_xjtx.do");
			
			if(isSuccess){
				model.setShjg(Constants.YW_SHZ);
				isSuccess = dao.runUpdate(model);
			}
			i++;
		}
		
		return sqids.length;
		
	}
	
	/**
	 * 
	 * @����:��������
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2013-12-21 ����02:25:19
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param sqids
	 * @return
	 * @throws Exception
	 * int[] �������� 
	 * @throws
	 */
	public int[] cancelXnxjs(String[] sqids) throws Exception{
		
		int success = 0;
		
		int fail = 0;
		
		if(sqids == null || sqids.length == 0) return new int[]{success , fail};

		for (String string : sqids) {
			
			XnxjForm model = dao.getModel(string);
			
			String ywid = model.getId();
			String sqr = model.getXh();
			String splid = model.getSplid();
			
			boolean isSuccess;
			
			try {
				isSuccess = shlc.firstStepCancle(ywid, splid);
				
				if(isSuccess){
//					����������������˻ؼ�¼����״̬Ϊ�˻�
					if(Integer.valueOf(dao.getShzt(ywid))>0){
						model.setShjg(Constants.YW_YTH);
					}else{
						model.setShjg(Constants.YW_WTJ);
					}
					isSuccess = dao.runUpdate(model);
					if(isSuccess){
						success++;
					}else
						fail ++;
				}else
					fail ++;
				
			} catch (SystemException e) {
				fail ++;
			}
		}
		
		return new int[]{success , fail};
		
	}
	
	/**
	 * 
	 * @����:С����˲�ѯ
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2013-12-21 ����02:25:13
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String ,String>> getXnxjShPageList(XnxjForm t , User user) throws Exception{
		return dao.getXnxjShPageList(t, user);
	}
	
	/**
	 * 
	 * @����:����ѧ��С�����
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2013-12-23 ����11:57:06
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveXnxjSh(XnxjForm  t, User user) throws Exception{
		
		String ywid = t.getId(); //ҵ��ID
		String gwid = t.getXtgwid();//��λid
		String shjg = t.getShjg();//��˽��
		String shyj = t.getShyj();//������
		String splid = t.getSplid();//������id
		String sqr = t.getXh();//������
		String shr = user.getUserName();//�����
		String thgw = t.getThgw(); //�˻ظ�λid
		String xjnr = t.getXjnr(); //С������
		String txsj = t.getTxsj(); //��дʱ��
		String xh = t.getXh();
		String xn = t.getXn();
		
		
		ShxxModel model = new ShxxModel();
		model.setYwid(ywid);
		model.setShlc(splid);
		model.setShr(shr);
		model.setShyj(shyj);
		model.setShzt(shjg);
		model.setThgw(thgw);
		model.setGwid(gwid);
		model.setSqrid(sqr);
		model.setTzlj("xsxx_xnxj_xjsh.do");
		model.setTzljsq("xsxx_xnxj_xjtx.do");
		
		boolean isSuccess = false;
		
		try {
			String zhzt  = shlc.runAuditing(model);
			
			XnxjForm updateForm = dao.getModel(t.getId());
			
			updateForm.setShjg(zhzt);
			
			isSuccess = dao.runUpdate(updateForm); //���������
			
			if(isSuccess && Constants.SH_TG.equals(zhzt)){
				
				XnxjJgForm xnxjJgModel = new XnxjJgForm();
				xnxjJgModel.setSjly(Constants.SJLY_SPL);
				xnxjJgModel.setSqid(ywid);
				xnxjJgModel.setXjnr(xjnr);
				xnxjJgModel.setTxsj(txsj);
				xnxjJgModel.setXh(xh);
				xnxjJgModel.setXn(xn);
				isSuccess = xnxjJgDao.runInsert(xnxjJgModel); //��������
				
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		
		return isSuccess;
	}
	
	/**
	 * 
	 * @����:����ѧ��С�����
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2013-12-23 ����04:11:50
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ywid
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean cancelXnxjSh(String ywid) throws Exception{
		if(StringUtils.isNull(ywid)) return false;
		
		XnxjForm model = dao.getModel(ywid);
		
		boolean isSuccess = false;
		
		if(model != null){
			model.setShjg(Constants.YW_SHZ);
			isSuccess = dao.runUpdate(model);
		}
		int rownum = 0;
		if(isSuccess){
			rownum = xnxjJgDao.delXnxjg(ywid);
		}
		
		return true;
	}
	
	public HashMap<String , String> getXnxjInfo(String xh , String xn){
		return dao.getXnxjInfo(xh , xn);
	}
	/**
	 * ��ѯ���������������ʱ������
	 */
	public List<HashMap<String , String>> getXnxjShyjList(String id){
		return dao.getXnxjShyjList(id);
	}
	
}
