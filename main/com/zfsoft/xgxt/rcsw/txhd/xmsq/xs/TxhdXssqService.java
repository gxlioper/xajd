/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-6-24 ����09:40:22 
 */
package com.zfsoft.xgxt.rcsw.txhd.xmsq.xs;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import xgxt.action.base.BaseDAO;
import xgxt.form.User;
import xgxt.utils.GetTime;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� ����[����:1104]
 * @ʱ�䣺2014-6-25 ����13:08:17
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class TxhdXssqService extends
		SuperServiceImpl<TxhdXssqForm, TxhdXssqDao> {
	private ShlcInterface shlc = new CommShlcImpl();
	public static String _BCZSCID = "-1";
	TxhdXssqDao dao = new TxhdXssqDao();

	@Override
	public List<HashMap<String, String>> getPageList(TxhdXssqForm t, User user)
			throws Exception {
		return getHdxx(super.getPageList(t, user));
	}

	public List<HashMap<String, String>> getHdxx(
			List<HashMap<String, String>> list) {
		return list;
	}

	/**
	 * 
	 * @����:������޸�
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2014-6-25 ����13:08:17
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception boolean ��������
	 * @throws
	 */
	public boolean update(TxhdXssqForm model) throws Exception {
			boolean updateResult = super.runUpdate(model);
			if(updateResult && Constants.YW_SHZ.equalsIgnoreCase(model.getShzt())) {
				updateResult = shlc.runSubmit(model.getSqid(), model.getSplc(), model.getXh(), "rcsw_txhd_xmsq_js.do", "rcsw_txhd_xmsh.do");
			}
			return updateResult;
		}

	/**
	 * 
	 * @����:����
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2014-06-24����14:21:59
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception boolean ��������
	 * @throws
	 */
	public boolean save(TxhdXssqForm model) throws Exception {
		model.setSqsj(GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss"));
		return super.runInsert(model);
	}

	/**
	 * ��ȡ����
	 */
	public List<HashMap<String, String>> getXmsqPageListXs(TxhdXssqForm model,
			User user) throws Exception {
		return dao.getXmsqPageListXs(model, user);
	}

	/**
	 * 
	 * @����:��������Ŀ
	 * @���ߣ�����[���ţ�1104]
	 */
	public List<HashMap<String, String>> getXmsqPageListXsYsq(
			TxhdXssqForm model, User user) throws Exception {
		return dao.getXmsqPageListXsYsq(model, user);
	}

	/**
	 * @throws Exception
	 * 
	 * @����:ɾ��
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2014-6-27 ����13:08:17
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ids
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 *             String[] �������� String���� 0Ϊ�ɹ�ɾ������Ϊ����ɾ����
	 * @throws
	 */
	public String[] delete(String[] ids) throws Exception {
		// StringBuffer del=new StringBuffer();
		List<String> delId = new ArrayList<String>();// ��ɾ����id����
		List<String> delSqId = new ArrayList<String>();// ��Ӧ��ɾ������������id
		if (null == ids || ids.length <= 0) {
			return null;
		}
		for (String str : ids) {
			delId.add(str);// ��¼ɾ��id
			delSqId.add(getModel(str).getSqid());
		}

		int i = delId.size() > 0 ? runDelete(delId.toArray(new String[] {}))
				: 0;
		return new String[] { String.valueOf(i) };
	}

	@Override
	public TxhdXssqForm getModel(TxhdXssqForm t) throws Exception {
		t = super.getModel(t);
		if (t != null) {
			// ��ѧ�ڴ���ת��Ϊѧ������
			t.setXqmc(BaseDAO.getInstance().getXqmcForXqdm(t.getXq()));
		}
		return t;
	}
	
	public HashMap<String, String> getCount(String xmdm, String xq, String xn) {
		return dao.getCount(xmdm, xq, xn);
	}

	/**
	 * 
	 * @����:TODO �������뵥
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2014-06-26 ����19:21:59
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception boolean ��������
	 * @throws
	 */
	public boolean saveSq(TxhdXssqForm model) throws Exception {
		String sqid = UniqID.getInstance().getUniqIDHash();
		model.setSqid(sqid);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		model.setSqsj(sdf.format(new Date()));
		model.setShzt(Constants.YW_WTJ);
		// ��ȡ��������
		String splc = dao.getSplcID(model.getXmdm());
		model.setSplc(splc);
		boolean insertResult = super.runInsert(model);
		return insertResult;
	}

	public boolean submitRecord(String sqid, String splc, String xh)
			throws Exception {
		boolean result = false;
		result = shlc.runSubmit(sqid, splc, xh, "rcsw_txhd_xmsq_js.do",
				"rcsw_txhd_xmsh.do");
		return result;
	}

	/**
	 * 
	 * @����:TODO(�ύ����)
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2014-6-27 ����08:34:19
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param type
	 * @return
	 * @throws Exception
	 *             String ��������
	 * @throws
	 */

	public boolean saveSq(TxhdXssqForm model, String type) throws Exception {
		String sqid = UniqID.getInstance().getUniqIDHash();
		model.setSqid(sqid);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		model.setSqsj(sdf.format(new Date()));
		model.setShzt(Constants.YW_WTJ);
		// ��ȡ��������
		String splc = dao.getSplcID(model.getXmdm());
		model.setSplc(splc);
		// ��������������趨��ʼֵ
		if (splc != null && !"".equals(splc)) {
			model.setShzt(Constants.YW_SHZ);// �����
		}
		boolean insertResult = super.runInsert(model);
		if (insertResult) {
			insertResult = shlc.runSubmit(sqid, model.getSplc(), model.getXh(),
					"rcsw_txhd_xmsq_js.do", "rcsw_txhd_xmsh.do");
		}
		return insertResult;
	}

	public boolean updateModel(TxhdXssqForm model) throws Exception {
		return super.runUpdate(model);
	}
	
	/** 
	 * @����:����
	 * @���ߣ����� [���ţ�1104]
	 * @���ڣ�2014-6-27 ����01:28:22
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param values
	 * @param lcid
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean cancleRecord(String ywid,String lcid) throws Exception{
		return shlc.firstStepCancle(ywid,lcid);
	}

}
