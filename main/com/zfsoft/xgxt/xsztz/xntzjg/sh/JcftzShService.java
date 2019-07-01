/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-3-30 ����04:50:37 
 */  
package com.zfsoft.xgxt.xsztz.xntzjg.sh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;
import xgxt.form.User;

import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.xsztz.xntzjg.jg.JcftzJgDao;
import com.zfsoft.xgxt.xsztz.xntzjg.jg.JcftzJgForm;
import com.zfsoft.xgxt.xsztz.xntzjg.jg.JcftzJgService;
import com.zfsoft.xgxt.xsztz.xntzjg.sq.JcftzSqDao;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����[����:1282]
 * @ʱ�䣺 2016-3-30 ����04:50:37 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class JcftzShService extends SuperServiceImpl<JcftzShForm, JcftzShDao>{
	private JcftzShDao dao = new JcftzShDao();
	private ShlcInterface shlc = new CommShlcImpl();
	private JcftzJgDao jcftzJgDao = new JcftzJgDao();
	private JcftzJgService jcftzJgService = new JcftzJgService();
	private JcftzSqDao jcftzSqDao = new JcftzSqDao();
	
	/** 
	 * @����:�������
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-3-31 ����11:53:56
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param form
	 * @param user
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean saveSh(JcftzShForm form, User user) {
		ShxxModel model = new ShxxModel();
		// �������ID
		model.setShlc(form.getRdsplc());
		// �����
		model.setShr(user.getUserName());
		// ������
		model.setShyj(form.getShyj());
		// ���״̬
		model.setShzt(form.getShjg());
		model.setThgw(form.getThgw());
		// ��˸�λID
		model.setGwid(form.getGwid());
		// ҵ��ID(��Ϊ����ID)
		model.setYwid(form.getRdsqid());
		model.setSqrid(form.getXmdm());
		model.setTzlj("sztz_jcftz_sh.do");
		model.setTzljsq("sztz_jcftz_sq.do");
		boolean result = false;
		try {
			String zhzt = shlc.runAuditing(model);
			JcftzShForm sbshForm = new JcftzShForm();
			sbshForm.setRdsqid(form.getRdsqid());
			sbshForm.setXfrdsqzt(zhzt);
			result = dao.runUpdate(sbshForm, form.getRdsqid());
			// ���浽�����
			if (zhzt.equalsIgnoreCase(Constants.YW_TG)) {
				JcftzJgForm jcftzJgForm = new JcftzJgForm();
				jcftzJgForm.setLylcywid(form.getRdsqid());
				jcftzJgForm.setSjly("1");
				jcftzJgForm.setXmdm(form.getXmdm());
				jcftzJgForm.setXfrdjgzt("1");
				jcftzJgDao.updateRenDing(jcftzJgForm);
				if(jcftzJgService.isHaveRecord(jcftzJgForm)){
					//���������д������ݣ���ɾ���ٲ���
					jcftzJgService.delForSq(jcftzJgForm);
					if("1".equals(form.getCsms())){
						updateRyxx(jcftzJgForm);
					}else if("2".equals(form.getCsms())){
						updateTtxx(jcftzJgForm);
					}else{
						return false;
					}
										
				}else if("1".equals(form.getCsms())){
					updateRyxx(jcftzJgForm);
				}else if("2".equals(form.getCsms())){
					updateTtxx(jcftzJgForm);
				}else{
					return false;
				}
								
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/** 
	 * @����:�ڽ�����в��������Ա�ĵ����������
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-3-31 ����02:25:38
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param jcftzJgForm
	 * @throws Exception
	 * void �������� 
	 * @throws 
	 */
	public void updateRyxx(JcftzJgForm jcftzJgForm) throws Exception{
		List<HashMap<String,String>> list = jcftzSqDao.getJcfTzRyxx(jcftzJgForm.getXmdm());
		for(HashMap<String,String> map :list){
			jcftzJgForm.setJxdm(map.get("jxdm"));
			jcftzJgForm.setTzhjcf(map.get("tzhjcf"));
			jcftzJgForm.setSfqq(map.get("sfqq"));
			jcftzJgForm.setXh(map.get("xh"));
			jcftzJgForm.setXmdm(map.get("xmdm"));
			//��ע1-5
			jcftzJgForm.setBz1(map.get("bz1"));
			jcftzJgForm.setBz2(map.get("bz2"));
			jcftzJgForm.setBz3(map.get("bz3"));
			jcftzJgForm.setBz4(map.get("bz4"));
			jcftzJgForm.setBz5(map.get("bz5"));
			jcftzJgService.runInsert(jcftzJgForm);			
		}
	}
	
	/** 
	 * @����:����
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-3-31 ����04:11:53
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ywid
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * String �������� 
	 * @throws 
	 */
	public String cxshnew(String ywid, JcftzShForm model, User user) throws Exception {
		ShlcInterface service = new CommShlcImpl();
		String shzt = Constants.YW_SHZ;
		String cancelFlag = service.runCancelNew(user.getUserName(), model.getShid(), model.getRdsplc());
		dao.updateSqjl(ywid, shzt);
		return cancelFlag;
	}
	
	/** 
	 * @����:���һ������
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-3-31 ����04:28:34
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param myForm
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws 
	 */
	public boolean cancel(JcftzShForm myForm) throws Exception {
		myForm.setXfrdsqzt(Constants.YW_SHZ);
		boolean result = dao.runUpdate(myForm,myForm.getRdsqid());
			// ɾ��������е�����
		result = jcftzJgDao.delForSqBylcId(myForm.getRdsqid());
		return result;
	}
	
	/** 
	 * @����:������˱���
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-3-31 ����05:34:17
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * String �������� 
	 * @throws 
	 */
	public String savePlsh(JcftzShForm t, User user) throws Exception{
		String[] ids = t.getId();
		String[] gwid = t.getGwids();
		List<String> failXms = new ArrayList<String>();
		for (int i = 0, n = ids.length; i < n; i++) {
			JcftzShForm form = dao.getModel(ids[i]);
			JcftzShForm model = new JcftzShForm();
			model.setRdsplc(form.getRdsplc());
			model.setYwid(ids[i]);
			model.setGwid(gwid[i]);
			model.setRdsqid(ids[i]);
			model.setXmdm(form.getXmdm());
			model.setShyj(t.getShyj());
			model.setShjg(t.getShzt());
			model.setCsms(form.getCsms());
			boolean isSuccess = saveSh(model, user);
			if (!isSuccess) {
				failXms.add(ids[i]);
			}
		}
		JSONArray json = JSONArray.fromObject(failXms);
		if (failXms.isEmpty()) {
			return MessageUtil.getText(MessageKey.SYS_AUD_SUCCESS);
		} else if (Constants.SH_TH.equals(t.getShzt())) {
			return MessageUtil.getText(MessageKey.SYS_BACK_FAIL, json.toString());
		} else {
			return MessageUtil.getText(MessageKey.SYS_PLSH_FAIL, json.toString());
		}
	}
	
	/**
	 * 
	 * @����: ���ͨ��֮�󣬲�������
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-8-4 ����05:27:37
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param jcftzJgForm
	 * @throws Exception
	 * void �������� 
	 * @throws
	 */
	public void updateTtxx(JcftzJgForm jcftzJgForm) throws Exception{
		List<HashMap<String,String>> list = jcftzSqDao.getJcfTzTtCyxx(jcftzJgForm.getXmdm());
		ArrayList<String[]> paralist = new ArrayList<String[]>();
		for(HashMap<String,String> map :list){
			paralist.add(new String[]{map.get("xmdm"),map.get("xh"),map.get("tzhjcf"),map.get("jxdm"),map.get("sfqq"),jcftzJgForm.getLylcywid(),"1",
					map.get("bz1"),map.get("bz2"),map.get("bz3"),map.get("bz4"),map.get("bz5")});
		}
		dao.updateTtxx(jcftzJgForm, paralist);
	}
}
