/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ� 2013-12-18 ����08:52:03 
 */  
package com.zfsoft.xgxt.rcsw.dxsylbx.ylbxsh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;

import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.transaction.TransactionControl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.rcsw.dxsylbx.ylbxjg.YlbxjgDao;
import com.zfsoft.xgxt.rcsw.dxsylbx.ylbxjg.YlbxjgForm;


/**
 * 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ѧ��֤�������ģ��
 * @�๦������: TODO(ѧ��֤����-�������) 
 * @���ߣ�Dlq[����:995]
 * @ʱ�䣺 2013-12-18 ����08:52:03 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class YlbxshService extends SuperServiceImpl<YlbxshForm, YlbxshDao> {

	private YlbxshDao dao = new YlbxshDao();
	private ShlcInterface shlc = new CommShlcImpl();
	
	public YlbxshService() {
		super.setDao(dao);
	}
	
	/**
	 * 
	 * @����:��ѯ��ȡ������Ϣ
	 * @���ߣ�Dlq [���ţ�995]
	 * @���ڣ�2013-8-13 ����04:53:42
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getYlbxshInfo(YlbxshForm model) {
		if (StringUtil.isNull(model.getYlsqid())) {
			logger.error("����ID����Ϊ�գ�");
			throw new NullPointerException();
		}
		return dao.getYlbxshInfo(model);
	}
	
	/**
	 * @throws Exception 
	 * 
	 * ����ѧ��֤������� 
	 * @���ߣ�dlq [���ţ�995]
	 * @���ڣ�2013-8-6 ����06:58:14
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param form
	 * @param user
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	@TransactionControl
	public boolean saveSh(YlbxshForm form,User user) throws Exception{
		
		ShxxModel model = new ShxxModel();
		// �������ID
		model.setShlc(form.getSplc());
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
		model.setYwid(form.getYlsqid());
		model.setSqrid(form.getXh());
		model.setTzlj("rcsw_dxsylbx_ylbxsh.do");
		model.setTzljsq("rcsw_dxsylbx_ylbxsq.do");
		boolean reuslt = false;
		
			String zhzt = shlc.runAuditingNotCommit(model);
			YlbxshForm upForm = new YlbxshForm();
			upForm.setYlsqid(form.getYlsqid());
			upForm.setShzt(zhzt);
			reuslt = dao.runUpdateNotCommit(upForm, form.getYlsqid());
			//���״̬Ϊͨ�������ճ���Ϊ������б����������
			if(zhzt.equalsIgnoreCase(Constants.YW_TG)){
				YlbxjgForm ylbxjgForm = new YlbxjgForm();
        		BeanUtils.copyProperties(ylbxjgForm, StringUtils.formatData(form));
        		ylbxjgForm.setYljgid(form.getYlsqid());
        		ylbxjgForm.setSjly("1");
        		ylbxjgForm.setYlsqid(form.getYlsqid());
        		YlbxjgDao ylbxjgDao = new YlbxjgDao();
        		ylbxjgDao.runInsertNotCommit(ylbxjgForm);	
			}	
		
		return reuslt;
	}
	
	/**
	 * 
	 * @����:TODO(��ȡ����ȫ�����Ա���)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-1-10 ����09:28:32
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param values
	 * @return
	 * @throws Exception
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>>  getCzqebzrymcList(String[] values) throws Exception {
		return dao.getCzqebzrymcList(values);
	}
	
	/**
	 * 
	 * @����:TODO(��ȡ�α�״������)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-1-10 ����09:37:35
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param values
	 * @return
	 * @throws Exception
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>>  getCbzkdmcsList(String[] values) throws Exception {
		return dao.getCbzkdmcsList(values);
	}
	
	
	/**
	 * 
	 * @����:TODO(����ҽ�Ʊ������)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-1-10 ����03:05:09
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean newCancelSh(YlbxshForm model){
		boolean resultYlbx = false;
		boolean resultYlbxjg = false;
		try {
			//�����ճ���Ϊ��Ϣά��
			resultYlbx = dao.updateYlbxsq(model.getYlsqid(), Constants.YW_SHZ);
			if(resultYlbx){
				//ɾ���ճ���Ϊ����еļ�¼
				resultYlbxjg = dao.deleteYlbxjg(model.getYlsqid());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultYlbxjg;
	}

	/**
	 * @throws Exception  
	 * @����:������˱���
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-4-28 ����09:32:34
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * String �������� 
	 * @throws 
	 */
	@TransactionControl
	public String savePlsh(YlbxshForm t, User user) throws Exception {
		
		String[] ids = t.getId();
		String[] gwid = t.getGwids();
		String[] xhs = t.getXhs();
		String[] splcs = t.getSplcs();

		List<String> failXhs = new ArrayList<String>();

		for (int i = 0, n = ids.length; i < n; i++) {
			
			
			YlbxshForm model = new YlbxshForm();
			model.setSplc(splcs[i]);
			model.setYlsqid(ids[i]);
			model.setGwid(gwid[i]);
			model.setShyj(t.getShyj());
			model.setShjg(t.getShzt());
			model.setXh(xhs[i]);
			
			boolean isSuccess = saveSh(model, user);

			if (!isSuccess) {
				failXhs.add(xhs[i]);
			}
		}
		

		JSONArray json = JSONArray.fromObject(failXhs);

		if (failXhs.isEmpty()) {
			return MessageUtil.getText(MessageKey.SYS_AUD_SUCCESS);
		} else if (Constants.SH_TH.equals(t.getShzt())) {
			return MessageUtil.getText(MessageKey.SYS_BACK_FAIL, json
					.toString());
		} else {
			return MessageUtil.getText(MessageKey.SYS_PLSH_FAIL, json
					.toString());
		}
	}
	


}
