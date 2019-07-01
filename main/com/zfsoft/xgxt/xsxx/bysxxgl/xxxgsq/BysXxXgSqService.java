/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-7-8 ����04:31:56 
 */
package com.zfsoft.xgxt.xsxx.bysxxgl.xxxgsq;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.export.util.DateUtils;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.JsonUtil;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.xsxx.bysxxgl.bysxx.BysXxForm;
import com.zfsoft.xgxt.xsxx.bysxxgl.bysxx.BysXxService;
import com.zfsoft.xgxt.xsxx.bysxxgl.cssz.BysXxCsSzService;
import com.zfsoft.xgxt.xsxx.xsxxgl.xxgl.XsxxglService;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� ����[����:1104]
 * @ʱ�䣺 2014-7-8 ����04:31:56
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class BysXxXgSqService extends SuperServiceImpl<BysXxForm, BysXxXgSqDao> {
	private ShlcInterface shlc = new CommShlcImpl();

	/**
	 * 
	 * @����:�鿴�����޸��б�
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2014-7-8 ����06:39:24
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getXgSqPageList(BysXxForm model,
			User user) throws Exception {
		return dao.getXgSqPageList(model, user);
	}

	/**
	 * 
	 * @����:�鿴�����޸��б�(ѧ��)
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2014-7-8 ����06:41:32
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getXgSqPageListByStu(BysXxForm model,
			User user) throws Exception {
		return dao.getXgSqPageListByStu(model, user);
	}

	/**
	 * 
	 * @����:������Ϣ�޸����뵥
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2014-7-9 ����02:25:51
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception boolean ��������
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public boolean saveXgSq(BysXxXgSqForm model) throws Exception {
		boolean result = true;
		String guid = UniqID.getInstance().getUniqIDHash();
		guid = guid.toUpperCase();
		model.setSqid(guid);
		model.setShjg(Constants.YW_WTJ);
		model.setXgsj(DateUtils.getCustomFomratCurrentDate("1"));
		String shlid = null;
		HashMap<String, String> splMap = new BysXxCsSzService().getCssz();
		shlid = splMap.get("shlid");
		model.setSplc(shlid);
		dao.deleteShlc(model);
		dao.deleteXgsq(model);
		String xgzd = model.getXgzd();
		List<BysXxXgZdForm> xgzdList = null;
		if (xgzd != null && !xgzd.equals("")) {
			xgzdList = JsonUtil.jsonToList(xgzd, BysXxXgZdForm.class);
		}
		// /////�����޸��ֶ�////////////////
		if (xgzdList != null && xgzdList.size() > 0) {
			dao.insertXgzd(xgzdList, model.getSqid());
		}
		result = dao.insertXgsq(model); // ���������¼
		return result;
	}
	/**
	 * 
	 * @����:TODO��Ϣ�����޸ı���
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2014-7-14 ����08:37:33
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public boolean sqXgSave(BysXxXgSqForm model) throws Exception {
		boolean result = true;
		model.setShjg(Constants.YW_WTJ);
		model.setXgsj(DateUtils.getCustomFomratCurrentDate("1"));
		dao.sqXgUpdate(model);
		String xgzd = model.getXgzd();
		List<BysXxXgZdForm> xgzdList = null;
		if (xgzd != null && !xgzd.equals("")) {
			xgzdList = JsonUtil.jsonToList(xgzd, BysXxXgZdForm.class);
		}
		// /////�����޸��ֶ�////////////////
		if (xgzdList != null && xgzdList.size() > 0) {
			dao.updateXgzd(xgzdList, model.getSqid());
		}
		return result;
	}

	/**
	 * 
	 * @����:�ύ��Ϣ�޸�����
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2014-7-9 ����05:18:27
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception boolean ��������
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public boolean tjXgSq(BysXxXgSqForm model, BysXxForm form,
			HashMap<String, String> bysXxvalueMap) throws Exception {
		boolean result = true;
		String guid = UniqID.getInstance().getUniqIDHash();
		guid = guid.toUpperCase();
		model.setSqid(guid);
		model.setShjg(Constants.YW_SHZ);
		model.setXgsj(DateUtils.getCustomFomratCurrentDate("1"));

		String shlid = null;
		HashMap<String, String> splMap = new BysXxCsSzService().getCssz();
		shlid = splMap.get("shlid");
		model.setSplc(shlid);
		dao.deleteShlc(model);
		dao.deleteXgsq(model);
		String xgzd = model.getXgzd();
		List<BysXxXgZdForm> xgzdList = null;
		if (xgzd != null && !xgzd.equals("")) {
			xgzdList = JsonUtil.jsonToList(xgzd, BysXxXgZdForm.class);
		}
		// /////�����޸��ֶ�////////////////
		if (xgzdList != null && xgzdList.size() > 0) {
			dao.insertXgzd(xgzdList, model.getSqid());
		}
		if (shlid == null || shlid.equals("") || shlid.equals("wxsh")) {// �������
			model.setShjg(Constants.YW_TG);
			XsxxglService xsxxglService = new XsxxglService();
			// �޸�ѧ����Ϣ
			result = xsxxglService.updateRecordForStu(model.getSqid(), model
					.getXh(), false,true);
			if (result) {
				BysXxService bysxxService = new BysXxService();
				result = bysxxService.updateBysXx(form, bysXxvalueMap);
			}
		}
		result = dao.insertXgsq(model); // ���������¼
		if (result) {
			result = shlc.runSubmit(model.getSqid(), model.getSplc(),
					model.getXh(), "xsxx_new_bysxx_xxxgsh.do",
					"xsxx_new_bysxx_xxxgsq.do");
		}
		return result;
	}
	/**
	 * 
	 * @����:��Ϣ�޸������޸��ύ
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2014-7-14 ����09:09:50
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param form
	 * @param bysXxvalueMap
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public boolean sqXgTj(BysXxXgSqForm model, BysXxForm form,
			HashMap<String, String> bysXxvalueMap) throws Exception {
		boolean result = true;
		model.setShjg(Constants.YW_SHZ);
		model.setXgsj(DateUtils.getCustomFomratCurrentDate("1"));
		dao.sqXgUpdate(model);
		String xgzd = model.getXgzd();
		List<BysXxXgZdForm> xgzdList = null;
		if (xgzd != null && !xgzd.equals("")) {
			xgzdList = JsonUtil.jsonToList(xgzd, BysXxXgZdForm.class);
		}
		// /////�����޸��ֶ�////////////////
		if (xgzdList != null && xgzdList.size() > 0) {
			dao.updateXgzd(xgzdList, model.getSqid());
		}
		HashMap<String, String> splMap = new BysXxCsSzService().getCssz();
		String shlid = splMap.get("shlid");
		if (shlid == null || shlid.equals("") || shlid.equals("wxsh")) {// �������
			model.setShjg(Constants.YW_TG);
			XsxxglService xsxxglService = new XsxxglService();
			// �޸�ѧ����Ϣ
			result = xsxxglService.updateRecordForStu(model.getSqid(), model
					.getXh(), false,true);
			if (result) {
				BysXxService bysxxService = new BysXxService();
				result = bysxxService.updateBysXx(form, bysXxvalueMap);
			}
		}
		if (result) {
			result = shlc.runSubmit(model.getSqid(), model.getSplc(),
					model.getXh(), "xsxx_new_bysxx_xxxgsh.do",
					"xsxx_new_bysxx_xxxgsq.do");
		}
		return result;
	}
	/**
	 * 
	 * @����:TODO��ȡ����id
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2014-7-11 ����04:52:04
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * String �������� 
	 * @throws
	 */
	public HashMap<String, String>  getSqXxByXh(String xh) throws Exception{
		return dao.getSqXxByXh(xh);
	}
	/**
	 * 
	 * @����:ͨ��sqid��ȡѧ��
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2014-7-14 ����04:04:16
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param sqid
	 * @return
	 * @throws Exception
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String>  getXhBySqid(String sqid) throws Exception{
		return dao.getXhBySqid(sqid);
	}

	/**
	 * 
	 * @����:��ѯ��ҵ��
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2014-7-9 ����10:54:05
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getBysXxList(BysXxForm model, User user)
			throws Exception {
		return dao.getBysXxList(model, user);

	}
	/**
	 * �ύ����
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2014-7-10 ����09:38:16
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param sqid
	 * @param splc
	 * @param xh
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean submitRecord(String sqid, String splc, String xh)
			throws Exception {
		boolean result = false;
		result = shlc.runSubmit(sqid, splc, xh, "xsxx_new_bysxx_xxxgsq.do",
				"xsxx_new_bysxx_xxxgsh.do");
		return result;
	}
	/**
	 * 
	 * @����:��������
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2014-7-10 ����10:23:33
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
	/**
	 * 
	 * @����:��������״̬
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2014-7-10 ����10:29:17
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * int �������� 
	 * @throws
	 */
	public boolean updateXgSq(BysXxXgSqForm model)throws Exception{
		return dao.updateXgSq(model);
	}

	/**
	 * 
	 * @����:ɾ���ֶ��޸���Ϣ
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2014-7-10 ����09:02:31
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param values
	 * @return boolean ��������
	 * @throws
	 */
	public boolean xgZdDel(String[] values) throws Exception {
		return dao.xgZdDel(values);
	}
	public boolean sqXxDel(String sqid) throws Exception{
		return dao.sqxxDel(sqid);
	}

}
