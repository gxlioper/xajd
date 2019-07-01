/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-8-2 ����10:14:04 
 */
package com.zfsoft.xgxt.xpjpy.tsxs;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.sf.ezmorph.bean.MorphDynaBean;
import xgxt.action.Base;
import xgxt.form.User;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.JsonUtil;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ���������Ź���ģ��
 * @�๦������: ����ѧ��ά��
 * @���ߣ�CQ [���ţ�785]
 * @ʱ�䣺 2013-8-2 ����10:14:04
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class TsxsService extends SuperServiceImpl<TsxsModel, TsxsDao> {

	private TsxsDao dao = new TsxsDao();

	public TsxsService() {
		super.setDao(dao);
	}
	
	/**
	 * 
	 * @����:����ѧ��δ����б�
	 * @���ߣ�tgj[���ţ�1075]
	 * @���ڣ�2017-7-11 ����08:45:07
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getTsxsDtjPageList(TsxsModel model, User user)
	throws Exception {
		return dao.getTsxsDtjPageList(model, user);
	}
	
	/**
	 * 
	 * @����:��ȡ����ѧ�����ʹ��롢����
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2013-8-2 ����04:08:33
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getTslxList() {
		return getTslxList(null);
	}
	
	/**
	 * 
	 * @����:��ȡ����ѧ�����ʹ��롢����,  lxsx,1:������2����Χ
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2013-8-2 ����04:08:33
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getTslxList(String lxsx) {
		List<HashMap<String, String>> list = dao.getTslx(lxsx);
		return list;
	}

	/**
	 * 
	 * @����:��������ѧ��
	 * @���ߣ�ligl
	 * @���ڣ�2013-8-15 ����04:10:59
	 * @�޸ļ�¼:
	 * @param model
	 * @param user
	 * @return
	 * @throws SQLException boolean ��������
	 * @throws
	 */
	public boolean saveTsxs(TsxsModel model, User user) throws Exception {

		boolean isSuccess = true;// ���Բ���ʧ�ܽ��д���
		String tsxsxh = model.getTsxsxh();

		if (!StringUtil.isNull(tsxsxh)) {
			String[] tsxsxhs = tsxsxh.replaceAll("\r", " ").replaceAll("\n",
					" ").split(" ");
			List<String> tsxsxhList = new ArrayList<String>();
			for (int i = 0; i < tsxsxhs.length; i++) {
				if (!StringUtil.isNull(tsxsxhs[i])) {
					tsxsxhList.add(tsxsxhs[i].trim());
				}
				if (i > 0 && i % 800 == 0) {// ÿ500����һ�β�����
					isSuccess = saveTsxsForParams(model, user, tsxsxhList);
					tsxsxhList = new ArrayList<String>();// ����
				}
			}
			isSuccess = saveTsxsForParams(model, user, tsxsxhList);

		}
		return isSuccess;
	}

	/*
	 * ���ݲ����������ݽ��д���
	 */
	private boolean saveTsxsForParams(TsxsModel model, User user,
			List<String> tsxsxhList) throws Exception {
		String xh = null;
		String[] param = null;
		boolean isSuccess = true;
		String lxdm = model.getLxdm();
		List<String[]> params = new ArrayList<String[]>();

		String userName = user.getUserName();

		List<HashMap<String, String>> tsxsInfoList = dao.sctsxs(tsxsxhList,
				model);
		if (tsxsInfoList != null && tsxsInfoList.size() > 0) {
			for (HashMap<String, String> map : tsxsInfoList) {
				xh = map.get("xh");
				param = new String[] { xh, model.getXn(), model.getXq(), lxdm,
						userName };
				params.add(param);
			}
			if (params.size() > 0) {
				isSuccess = dao.saveTsxs(params);// ��������
			}
		}

		return isSuccess;
	}

	/**
	 * @����:ɾ��������Ա
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2013-8-5 ����07:02:04
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param values
	 * @return boolean ��������
	 * @throws
	 */
	public boolean delTsxs(String values) {

		if (values == null || "".equalsIgnoreCase(values)) {
			logger.error("δѡ��ѡ������Ա��Ϣ��");
			throw new NullPointerException();
		}

		boolean delTsxs = false;

		// ɾ��������Ա��Ϣ
		delTsxs = dao.delTsxs(values);

		return delTsxs;
	}
	
	/**
	 * 
	 * @����:����ѧ��ѧ�����ͣ�����ɾ��
	 * @���ߣ�ligl
	 * @���ڣ�2013-8-16 ����10:42:37
	 * @�޸ļ�¼: 
	 * @param values
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean tsxsScForLx(String json)  throws Exception {

		if (json == null || json.trim().equals("")) {
			logger.error("δ��ѡ���ݣ�");
			throw new NullPointerException();
		}

		List<String[]> params = new ArrayList<String[]>();
		String[] param = null;
	
		boolean isSuccess = false;
		List list = JsonUtil.jsonToList(json);
		String xn = null;
		String xq = null;
		String lxdm = null;
		for (Object object : list) {
			MorphDynaBean bean = (MorphDynaBean)object;
			xn = (String)bean.get("xn");
			xq = (String)bean.get("xq");
			lxdm = (String)bean.get("lxdm");
			param = new String[] { xn,xq,lxdm};
			params.add(param);
		}
		
		if (params.size() > 0) {
			isSuccess = dao.tsxsScForLx(params);// 
		}
		return isSuccess;

	}
	
	/**
	 * 
	 * @����:��ѯ������ѧ��
	 * @���ߣ�ligl
	 * @���ڣ�2013-8-16 ����03:44:06
	 * @�޸ļ�¼: 
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getXnList() throws Exception{
		List<HashMap<String, String>> list = dao.getXnList();
		return list;
	}
	
	/**
	 * 
	 * @����:�õ�ѧ�ڴ���
	 * @���ߣ�ligl
	 * @���ڣ�2013-8-17 ����11:22:32
	 * @�޸ļ�¼: 
	 * @param xq
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getXqmc(String xq){
		String xqmc = null;
		List<HashMap<String, String>> xqList = Base.getXqList();
		for (HashMap<String, String> map : xqList) {
			if (xq.equals(map.get("xqdm"))) {
				xqmc = map.get("xqmc");
				break;
			}
		}
		return xqmc;
	}
	
	/**
	 * 
	 * @����:�õ���������
	 * @���ߣ�ligl
	 * @���ڣ�2013-8-17 ����11:23:56
	 * @�޸ļ�¼: 
	 * @param lxdm
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getLxmc(String lxdm) throws Exception{
		return dao.getNameById(lxdm);
	}
	
	/**
	 * @throws SQLException  
	 * @����:������������ѧ��(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-12-26 ����09:30:25
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean PlbcTsxs(String ids,String xn,String xq,String lxdm,String userName) throws SQLException{
		return dao.plbcTsxs(ids, xn, xq, lxdm, userName);
	}
}
