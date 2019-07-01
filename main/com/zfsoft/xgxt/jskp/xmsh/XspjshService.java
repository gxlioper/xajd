/**
 * @����:ѧ����Ʒ(1)��
 * @���ڣ�2018-4-16 ����07:34:58 
 */  
package com.zfsoft.xgxt.jskp.xmsh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;
import org.jfree.util.Log;

import xgxt.form.User;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.transaction.TransactionControl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.jskp.pjjg.XspjjgDao;
import com.zfsoft.xgxt.jskp.pjjg.XspjjgForm;
import com.zfsoft.xgxt.jskp.pjjg.XspjjgService;
import com.zfsoft.xgxt.jskp.zzsq.XspjsqDao;
import com.zfsoft.xgxt.jskp.zzsq.XspjsqForm;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ѧ�����۹���ģ��
 * @�๦������: ѧ���������
 * @���ߣ� Meng.Wei[����:1186]
 * @ʱ�䣺 2018-4-16 ����07:34:58 
 * @�汾�� V5.18.26
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XspjshService extends SuperServiceImpl<XspjshForm,XspjshDao>{
	private ShlcInterface shlc = new CommShlcImpl();
	/*0��δѡ�У�1��ѡ��*/
	private static final String FFGZ_Y = "1"; 
	
	/**
	 * @����: ѧ���������
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ� 2018-4-17 ����03:40:20
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return
	 * @throws Exception
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String,String> getXspjshInfo(XspjshForm t) throws Exception{
		return dao.getXspjshInfo(t);
	}
	
	/**
	 * @����: ȡ���״̬���е�����һ����¼�ķ���
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ� 2018-4-17 ����04:27:26
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return
	 * @throws Exception
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String,String> getLevelXxBySqid(XspjshForm t) throws Exception{
		return dao.getLevelXxBySqid(t);
	}
	
	/**
	 * @����: ������˱���
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ� 2018-4-18 ����12:01:33
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	@TransactionControl
	public boolean xspjshSingleSave(XspjshForm form,User user)throws Exception{
		
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
		model.setTzlj("xspj_xspj_xspjsh.do");
		model.setTzljsq("xspj_xspj_xspjsq.do");
		boolean reuslt = false;
		try{
			String zhzt = shlc.runAuditingNotCommit(model);
			XspjshForm xspjshForm = new XspjshForm();
			xspjshForm.setSqid(form.getSqid());
			xspjshForm.setShzt(zhzt);
			reuslt = dao.runUpdateNotCommit(xspjshForm, form.getSqid());
			/*���浽�����*/
			if(zhzt.equalsIgnoreCase(Constants.YW_TG)){
				XspjjgForm xspjjgForm = new XspjjgForm();
				XspjjgService xspjjgService = new XspjjgService();
				XspjsqForm xspjsqForm = new XspjsqDao().getModel(form.getSqid());
				BeanUtils.copyProperties(xspjjgForm, StringUtils.formatData(xspjsqForm));
				xspjjgForm.setGuid(xspjsqForm.getSqid());
				xspjjgForm.setFz(form.getFs());
				/*��ȡ��ǰ����ʱ��������У���ϲ����˹��ŷ�ֹ��ʦˣ��*/
				String DATE_FORMAT = "yyyy-MM-dd hh24:mi:ss";
				xspjjgForm.setSjlrsj(GetTime.getTimeByFormat(DATE_FORMAT));
				/*������Դ��1:������ˡ�2:������ӡ�3:���롿*/
				xspjjgForm.setSjly("1");
				reuslt = xspjjgService.runInsert(xspjjgForm);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return reuslt;
	}
	
	/**
	 * @����: ��������
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ� 2018-4-19 ����09:43:57
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * String �������� 
	 * @throws
	 */
	@TransactionControl
	public String xspjshBatchSave(XspjshForm t,User user)throws Exception{
		XspjshForm model = new XspjshForm();
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
			boolean isSuccess = xspjshSingleSave(model, user);
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
	 * @����: ����ID��ѯ�����ж���������λ
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ� 2018-4-18 ����04:56:04
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param id
	 * @return
	 * HashMap<String,String> �������� 
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
	 * @����: ���һ������
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ� 2018-4-18 ����05:28:07
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param myForm
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean cancel(XspjshForm myForm) throws Exception {
		myForm.setShzt(Constants.YW_SHZ);
		boolean result = dao.runUpdate(myForm, myForm.getSqid());
		if(result){
			XspjjgDao xspjjgDao = new XspjjgDao();
			xspjjgDao.delShjgById(myForm.getSqid());
		}
		return result;
	}
	
	/**
	 * @����: ����id��ȡǰһ�η���
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ� 2018-4-19 ����09:52:35
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ids
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
	 * @����: �޹�ѡ������˵��ø�ҳ�淽�����ø�ҳ��(��ѯҳ��)�����ĸ߼���ѯ������ѯ����
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ� 2018-5-23 ����02:51:55
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> xspjPlshxx(XspjshForm t, User user) throws Exception{
		return dao.xspjPlshxx(t,user);
	}
	
	/**
	 * @����: ���ݸ߼���ѯ������ѯ��������(�޹�ѡ)����˲����������
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ� 2018-5-23 ����03:00:28
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @param resultList
	 * @return
	 * @throws Exception
	 * String �������� 
	 * @throws
	 */
	@TransactionControl
	public String xspjshPlshSave(XspjshForm t, User user,List<HashMap<String, String>> resultList) throws Exception {
		if(null == resultList || 0 == resultList.size()){
			return MessageUtil.getText("��ѯ���Ϊ��");
		}
		
		String[] ids = new String[resultList.size()];
		String[] gwids = new String[resultList.size()];
		String[] xhs = new String[resultList.size()];
		String[] splcs = new String[resultList.size()];
		
		for (int i = 0; i < resultList.size(); i++) {
			ids[i] = resultList.get(i).get("sqid");
			gwids[i] = resultList.get(i).get("gwid");
			xhs[i] = resultList.get(i).get("xh");
			splcs[i] = resultList.get(i).get("splcid");
		}
		
		List<String> failXhs = new ArrayList<String>();
		for (int i = 0, n = ids.length; i < n; i++) {
			t.setSqid(ids[i]);
			t.setXh(xhs[i]);
			t.setSplcid(splcs[i]);
			t.setYwid(ids[i]);
			t.setGwid(gwids[i]);
			t.setSqid(ids[i]);
			t.setShyj(t.getShyj());
			t.setShjg(t.getShzt());
			t.setXh(xhs[i]);
			t.setFs(t.getFs());
			boolean isSuccess = xspjshSingleSave(t, user);
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
}
