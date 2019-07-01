/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-12-8 ����09:24:35 
 */
package com.zfsoft.xgxt.axcs.wpsq.js;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.axcs.wpsz.WpszDao;
import com.zfsoft.xgxt.axcs.wpsz.WpszForm;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ���ĳ��й���ģ��
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� xiaxia [����:1104]
 * @ʱ�䣺 2014-12-8 ����09:24:35
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class WpsqJsService extends SuperServiceImpl<WpsqJsForm, WpsqJsDao> {
	private WpsqJsDao dao = new WpsqJsDao();
	private ShlcInterface shlc = new CommShlcImpl();

	/**
	 * 
	 * @����:��Ʒ������Ϣ
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2014-12-8 ����11:03:31
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * @throws Exception
	 *             Map<String,Object> ��������
	 * @throws
	 */
	public Map<String, Object> getWpsqInfoList(String xh) throws Exception {
		// ��ǰѧ��δ�������Ʒ
		List<HashMap<String, String>> wsqList = dao.getKsqInfoList(xh);
		// ��ǰѧ�����������Ʒ
		List<HashMap<String, String>> ysqList = dao.getYsqInfoList(xh);
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		// ��֤����
		for (HashMap<String, String> wp : wsqList) {
			List<HashMap<String, String>> results = checkTj(wp.get("xmdm"), xh);
			resultMap.put(wp.get("xmdm"), results);
		}
		resultMap.put("wsqList", wsqList);
		resultMap.put("ysqList", ysqList);
		return resultMap;
	}

	public List<HashMap<String, String>> checkTj(String xmdm, String xh) throws Exception {
		WpszDao wpszDao = new WpszDao();
		StringBuffer sqtj = new StringBuffer();
		List<HashMap<String, String>> checkResult = new ArrayList<HashMap<String, String>>();
		List<HashMap<String, String>> tjList = wpszDao.getWpTjSz(xmdm);
		HashMap<String, String> xsJtxxMap = wpszDao.getJtxx(xh);
		HashMap<String,String> condition = new HashMap<String,String>();
		if (tjList == null || tjList.size() == 0) {
			return null;
		}
		// ��֤
		boolean success = false;
		for (HashMap<String, String> tjMap : tjList) {

			String dcxxTj = tjMap.get("tjz");
			if (tjMap.get("dcdm").equals(xsJtxxMap.get("rddc"))) {
				success = true;
				if (!StringUtils.isNull(dcxxTj)) {
					String[] tjInfo = dcxxTj.split(";");
					for (int i = 0; i < tjInfo.length; i++) {
						if (!"1".equals(xsJtxxMap.get(tjInfo[i]))) {
							success = false;
						}
					}
				}
			}
			sqtj.append(tjMap.get("sqtj")).append(" ��");
		}
		condition.put("sqtj", sqtj.substring(0, sqtj.length()-1));
		condition.put("result", String.valueOf(success));
		checkResult.add(condition);
		return checkResult;
	}

	/**
	 * 
	 * @����:ѧ��������Ʒ����
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2014-12-8 ����11:44:34
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xmdm
	 * @param t
	 * @return
	 * @throws Exception boolean ��������
	 * @throws
	 */
	public boolean wpsqBc(String[] xmdm, WpsqJsForm t) throws Exception {
		if (xmdm == null || xmdm.length == 0) {
			return false;
		}
		for (String wp : xmdm) {
			WpsqJsForm model = new WpsqJsForm();
			model.setXh(t.getXh());
			model.setSqly(t.getSqly());
			model.setXmdm(wp);
			model.setType(t.getType());
			model.setSqid(t.getSqid());
			saveWpsq(model, t.getXh());
		}
		return true;
	}

	/**
	 * 
	 * @����:��Ʒ���뵥������
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2014-12-8 ����11:40:21
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param userName
	 * @return
	 * @throws Exception boolean ��������
	 * @throws
	 */
	public boolean saveWpsq(WpsqJsForm model, String userName) throws Exception {
		String sqid = UniqID.getInstance().getUniqIDHash();
		WpszDao wpszDao = new WpszDao();
		WpszForm wpszForm = wpszDao.getModel(model.getXmdm());
		String splc = wpszForm.getSplc();
		model.setSqr(userName);
		model.setXn(wpszForm.getXn());
		model.setSqid(sqid);
		model.setSplc(splc);
		// ��������������趨��ʼֵ
		if ("submit".equals(model.getType())) {
			model.setShzt(Constants.YW_SHZ);// �����
		} else {
			model.setShzt(Constants.YW_WTJ);// δ�ύ
		}
		// ����������Ϣ
		boolean result = dao.runInsert(model);
		// ���������Ϣ
		if (!"save".equals(model.getType())) {
			if (result) {
				result = shlc.runSubmit(sqid, splc, model.getXh(), "axcs_axcswpsh.do", "axcs_axcswpsq_tea.do");
			}
		}
		return result;
	}

	/**
	 * 
	 * @����:ֻ�и��ύ���ҵ�һ��δ��˵�ǰ���£������˿��Գ���
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2014-12-8 ����01:48:22
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ywid
	 * @param lcid
	 * @return
	 * @throws Exception boolean ��������
	 * @throws
	 */
	public boolean cancelRecord(String ywid, String lcid) throws Exception {
		return shlc.firstStepCancle(ywid, lcid);
	}

}
