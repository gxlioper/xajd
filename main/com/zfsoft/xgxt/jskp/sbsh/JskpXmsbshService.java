package com.zfsoft.xgxt.jskp.sbsh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;
import org.jfree.util.Log;

import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.transaction.TransactionControl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.jskp.cssz.CsszService;
import com.zfsoft.xgxt.jskp.sbjg.JskpXmsbjgDao;
import com.zfsoft.xgxt.jskp.sbjg.JskpXmsbjgForm;
import com.zfsoft.xgxt.jskp.sbjg.JskpXmsbjgService;
import com.zfsoft.xgxt.jskp.xmsb.JskpXmsbDao;
import com.zfsoft.xgxt.jskp.xmsb.JskpXmsbForm;

/**
 * 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��ʵ����
 * @�๦������: �걨���
 * @���ߣ� xiaxia[����:1104]
 * @ʱ�䣺 2017-7-7 ����02:09:03 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class JskpXmsbshService extends SuperServiceImpl<JskpXmsbshForm, JskpXmsbshDao> {
	private ShlcInterface shlc = new CommShlcImpl();
	private JskpXmsbshDao dao = new JskpXmsbshDao();
	private static final String FFGZ_Y = "1"; //0��δѡ�У�1��ѡ��
	

	/**
	 * 
	 * @����:���������Ϣ
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2017-7-7 ����08:42:55
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return HashMap<String,String> ��������
	 * @throws
	 */
	public HashMap<String, String> getSbshInfo(JskpXmsbshForm t) {
		return dao.getSbshInfo(t);

	}
	/**
	 * 
	 * @����:��˱���
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2017-7-7 ����02:19:58
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param form
	 * @param user
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	@TransactionControl
	public boolean saveSh(JskpXmsbshForm form, User user) {
		ShxxModel model = new ShxxModel();
		// �������ID
		model.setShlc(form.getSplcid());
		// �����
		model.setShr(user.getUserName());
		// ������
		model.setShyj(form.getShyj());
		// ���״̬
		model.setShzt(form.getShjg());
		model.setThgw(form.getThgw());
		// ��˸�λID
		model.setGwid(form.getGwid());
		model.setZd1("����");
		model.setZd3(form.getFs());
		// ҵ��ID(��Ϊ����ID)
		model.setYwid(form.getSqid());
		model.setSqrid(form.getXh());
		model.setTzlj("jskp_xmsh.do");
		model.setTzljsq("jskp_xmsb.do");
		boolean reuslt = false;
		try {
			String zhzt = shlc.runAuditingNotCommit(model);
			JskpXmsbshForm khshForm = new JskpXmsbshForm();
			khshForm.setSqid(form.getSqid());
			khshForm.setShzt(zhzt);
			reuslt = dao.runUpdateNotCommit(khshForm, form.getSqid());
			// ���浽�����
			if (zhzt.equalsIgnoreCase(Constants.YW_TG)) {
				JskpXmsbjgForm sbjgForm = new JskpXmsbjgForm();
				JskpXmsbjgService sbjgService = new JskpXmsbjgService();
				JskpXmsbForm xmsbForm = new JskpXmsbDao().getModel(form.getSqid());
				BeanUtils.copyProperties(sbjgForm, StringUtils.formatData(xmsbForm));
				sbjgForm.setJgid(xmsbForm.getSqid());
				sbjgForm.setSqid(xmsbForm.getSqid());
				sbjgForm.setFs(form.getFs());
				sbjgForm.setSjly("1");
				/*��������Ϊ0�ǣ��������������롢��ˣ�ֱ�Ӳ���ͼƬ*/
				if("0".equals(new CsszService().getSfsh())){
					sbjgForm.setFjid(xmsbForm.getFjid());
				}
				reuslt = sbjgService.runInsert(sbjgForm);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reuslt;
	}

	/**
	 * 
	 * @����:������˱���
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2017-7-7 ����02:20:23
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * String �������� 
	 * @throws
	 */
	@TransactionControl
	public String savePlsh(JskpXmsbshForm t, User user) throws Exception {
		JskpXmsbshForm model = new JskpXmsbshForm();
		String[] ids = t.getId();
		String[] gwid = t.getGwids();
		String[] xhs = t.getXhs();
		HashMap<String,String> getBeforeMark = new HashMap<String, String>();
		if(StringUtils.isNotNull(t.getFfgz())&&FFGZ_Y.equals(t.getFfgz())){
			getBeforeMark = getBeforeMark(t.getId());
		}
		List<String> failXhs = new ArrayList<String>();
		for (int i = 0, n = ids.length; i < n; i++) {
			model.setSqid(ids[i]);
			model.setXh(xhs[i]);
			model.setSplcid(t.getSplcid());
			model.setYwid(ids[i]);
			model.setGwid(gwid[i]);
			model.setSqid(ids[i]);
			model.setShyj(t.getShyj());
			model.setShjg(t.getShzt());
			model.setXh(xhs[i]);
			String fs = StringUtils.isNull(getBeforeMark.get(ids[i]))? t.getFs():getBeforeMark.get(ids[i]);
			model.setFs(fs);
			boolean isSuccess = saveSh(model, user);
			if (!isSuccess) {
				failXhs.add(xhs[i]);
			}
		}
		JSONArray json = JSONArray.fromObject(failXhs);
		if (failXhs.isEmpty()) {
			return MessageUtil.getText(MessageKey.SYS_AUD_SUCCESS);
		} else if (Constants.SH_TH.equals(t.getShzt())) {
			return MessageUtil.getText(MessageKey.SYS_BACK_FAIL, json.toString().replaceAll(",", ", "));
		} else {
			return MessageUtil.getText(MessageKey.SYS_PLSH_FAIL, json.toString().replaceAll(",", ", "));
		}
	}

	

	/**
	 * 
	 * @����:���һ������
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2017-7-7 ����02:26:21
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param myForm
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean cancel(JskpXmsbshForm myForm) throws Exception {
		myForm.setShzt(Constants.YW_SHZ);
		boolean result = dao.runUpdate(myForm, myForm.getSqid());
		if (result) {
			JskpXmsbjgDao jgdao = new JskpXmsbjgDao();
			jgdao.delSbjgById(myForm.getSqid());
		}
		return result;
	}
	
	
	/**
	 * 
	 * @����:����ID��ѯ�����ж���������λ
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2017-8-21 ����05:44:03
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param id
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public HashMap<String, String> getShxhForId(String id){
		if(StringUtils.isNull(id)){
			Log.error("id ����Ϊ��");
			return null;
		}
		
		HashMap<String, String> shxhMap = new HashMap<String, String>();
		List<HashMap<String, String>> shxhList = dao.getShxhForId(id);
		
		//ת��map��ҳ��ô���
		for (HashMap<String, String> hashMap : shxhList) {
			shxhMap.put(hashMap.get("xh"), hashMap.get("count"));
		}
		
		return shxhMap;
	}
	
	
	/**
	 * 
	 * @����:����id��ȡǰһ�η���
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2017-8-22 ����04:27:52
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param id
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getBeforeMark(String[] ids){
		
		if(null==ids||ids.length==0){
			Log.error("id ����Ϊ��");
			return null;
		}
		
		HashMap<String, String> sjfzMap = new HashMap<String, String>();
		List<HashMap<String, String>> sjfzList = dao.getBeforeMark(ids);
		
		//ת��map��ҳ��ô���
		for (HashMap<String, String> hashMap : sjfzList) {
			sjfzMap.put(hashMap.get("sqid"), hashMap.get("sjfs"));
		}
		
		return sjfzMap;
	}
	
	/**
	 * @����: ��������idȡ���״̬���е�����һ�� �ķ�����zd3��
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2018-1-17 ����02:08:21
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return
	 * @throws Exception
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getLevelXxBySqid(JskpXmsbshForm t) throws Exception{
		return dao.getLevelXxBySqid(t);
	}
}
