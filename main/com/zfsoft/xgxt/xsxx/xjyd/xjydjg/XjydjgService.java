package com.zfsoft.xgxt.xsxx.xjyd.xjydjg;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.xsxx.xjyd.XjydForm;
import com.zfsoft.xgxt.xsxx.xjyd.XjydService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import common.Globals;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��Ԣ����
 * @�๦������:ѧ���춯���
 * @���ߣ� qilm
 * @ʱ�䣺2013-9-29
 * @�汾�� V1.0
 */
public class XjydjgService extends SuperServiceImpl<XjydjgForm, XjydjgDAO> {

	/**
	 * ����ʯ�ʹ�ѧ����༶�������춯���
	 */
	public static final String DBSYDX_TSBJTZ      = "99";
	private XjydjgDAO dao = new XjydjgDAO();
	
	public XjydjgService() {
		super.setDao(dao);
		
	}

	/** 
	 * @����:ѧ�������һ��ѧ���춯��Ϣ
	 * @���ߣ�Qilm[���ţ�964]
	 * @���ڣ�2013-12-4 ����10:04:32
	 * @param myForm
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public HashMap<String, String> getXsydInfo(XjydjgForm myForm) {
		return dao.getXsydInfo(myForm);
	}
	
	/**
	 * 
	 * @����:��ȡmap
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2013-12-31 ����08:54:55
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param keyValue
	 * @return
	 * @throws Exception
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String , String> getModelInfoMap(String keyValue) throws Exception{
		return dao.getModelInfoMap(keyValue);
	}
	/**
	 * ����ѧ�š��춯�ĺŲ���id
	 */
	public String queryExistId(XjydjgForm myForm, String type) throws Exception{
		return dao.queryExistId(myForm, type);
	}
	
	/**
	 * @throws Exception  
	 * @����:ѧ������ĸ���ѧ���춯��Ϣ
	 * @���ߣ�Qilm[���ţ�964]
	 * @���ڣ�2013-12-4 ����10:04:32
	 * @param myForm
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getXsydList(XjydjgForm myForm) throws Exception {
		return dao.getXsydList(myForm);
	}

	/**
	 * @����: ѧ���춯��������
	 * @���ߣ�Qilm[���ţ�964]
	 * @���ڣ�2013-12-4 ����02:36:14
	 * @param myForm
	 * @param user
	 * @return boolean ��������
	 * @throws Exception
	 */
	public boolean xjydPlcl(XjydjgForm myForm, User user) {

		// û��ָ��ѡ��ѧ���򷵻�
		if (StringUtils.isNull(myForm.getXzxsKey())) {
			return false;
		}

		try {
			// ȡ��ѧ���б�
			XsxxService xsService = new XsxxService();
			List<HashMap<String, String>> xsList;
			xsList = xsService.getSelectedStudents(myForm.getXzxsKey());

			String xh = "";
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String date = sdf.format(new Date());

			for (HashMap<String, String> xs : xsList) {
				xh = xs.get("xh");

				String guid = UniqID.getInstance().getUniqIDHash();
				myForm.setXjydjgid(guid);
				myForm.setXh(xh);
				myForm.setJrsj(date);
				myForm.setSqr(user.getUserName());

				// ����ѧ��������Ϣ
				HashMap<String, String> xsjbxx = xsService.getXsjbxxMore(xh);
				myForm.setYdqnj(xsjbxx.get("nj"));
				myForm.setYdqxydm(xsjbxx.get("xydm"));
				myForm.setYdqzydm(xsjbxx.get("zydm"));
				myForm.setYdqbjdm(xsjbxx.get("bjdm"));
				myForm.setYdqxjlb(xsjbxx.get("xjlbdm"));//ѧ��������
				myForm.setYdqxjlbmc(xsjbxx.get("xjlb"));//ѧ�����
				myForm.setYdqsfyxjmc(xsjbxx.get("xjztm"));//�Ƿ���ѧ��
				myForm.setYdqsfzxmc(xsjbxx.get("sfzx"));//�Ƿ���У

				// ������Դ
				myForm.setSjly(Constants.SJLY_JGK);

				// ѧУ����
				String xxdm = Base.xxdm;	
				
				// �|��ʯ�ʹ�W�����Дࣨ�Ƿ�����⮐����������ֻ���༶����רҵ����
				if(DBSYDX_TSBJTZ.equals(myForm.getYdlbdm()) &&  Globals.XXDM_DBSYDX.equals(xxdm)){
					
					// רҵѧԺ����
					myForm.setYdhxydm(xsjbxx.get("xydm"));
					myForm.setYdhzydm(xsjbxx.get("zydm"));
					 
				}
				String xjydjgidTemp = queryExistId(myForm, "add");
				if (!"".equals(xjydjgidTemp)){
					myForm.setXjydjgid(xjydjgidTemp);
					dao.runUpdate(myForm);
				}else{
					dao.runInsert(myForm);
				}
				updateXsxx(myForm);
			}
			// ɾ����ʱ��
			xsService.runDelSelectAll(myForm.getXzxsKey());
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * 
	 * @����:ɾ���������Ӧ��ѧ���춯�����
	 * @���ߣ�qilm
	 * @���ڣ�2013-10-10
	 * @param sjydsqid
	 * @return
	 * @throws Exception
	 * int �������� 
	 * @throws
	 */
	public int runDeleteYdjg(String sjydsqid) throws Exception{
		return dao.runDeleteYdjg(sjydsqid);
	}
	
	/**
	 * 
	 * @����: ����ѧ����Ϣ
	 * @���ߣ�Qilm[���ţ�964]
	 * @���ڣ�2013-12-12 ����03:11:21
	 * @param myForm
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean updateXsxx(XjydjgForm myForm)throws Exception {
		
		XjydService xjyd = new  XjydService();
		XjydForm model = new XjydForm();
		
		model.setXjlbdm(myForm.getYdlbdm());
		model = xjyd.getModelShpz(model);
		// ѧ���������
		String xjlbmc = model.getXjlbmc();
		
		//�Ƿ���ѧ��(��ѧ��/��ѧ��)
		String xjztm = model.getSfyxjmc();
		
		//�Ƿ���У(��/��)
		String sfzx = StringUtils.isNotNull(model.getSfzxmc())?model.getSfzxmc():"��У";
		
		if("10511".equalsIgnoreCase(Base.xxdm)) {
			return dao.updateXsxxHZSF(model.getXjlbdm(),xjlbmc, xjztm, sfzx, myForm.getSfsfs(), myForm.getDqztdm(), myForm.getYdhnj(),myForm.getYdhxydm(), myForm.getYdhzydm(),myForm.getYdhbjdm(),myForm.getXh());
		}else {
			return dao.updateXsxx(model.getXjlbdm(),xjlbmc, xjztm, sfzx, myForm.getYdhnj(),myForm.getYdhxydm(), myForm.getYdhzydm(),myForm.getYdhbjdm(),myForm.getXh());
		}
	}

	/**
	 * 
	 * @����: ȡ��ѧ���춯�����Ϣ
	 * @���ߣ�Qilm[���ţ�964]
	 * @���ڣ�2013-12-12 ����04:15:46
	 * @param xjydsqid
	 * @return
	 * @throws Exception
	 * XjydjgForm �������� 
	 * @throws
	 */
	public XjydjgForm getModelBySqid(String xjydsqid) throws Exception{
		return dao.getModelBySqid(xjydsqid);
	}
	
	
	/**
	 * 
	 * @����: �ع�ѧ����Ϣ
	 * @���ߣ�Qilm[���ţ�964]
	 * @���ڣ�2013-12-12 ����03:11:21
	 * @param myForm
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean rollBackXsxx(String values)throws Exception {
		
		boolean bolFlg = false;
		for(String v :values.split(",")){
			XjydjgForm myForm = dao.getModel(v);

			bolFlg = dao.updateXsxx(myForm.getYdqxjlb(), myForm.getYdqxjlbmc(), myForm.getYdqsfyxjmc(), myForm.getYdqsfzxmc(),
					myForm.getYdqnj(),myForm.getYdqxydm(), myForm.getYdqzydm(),myForm.getYdqbjdm(), myForm.getXh());
			if(!bolFlg) return bolFlg;
		}
		return bolFlg;
	}
	/**
	 * 
	 * @����: ȡ��ѧ���춯����б�
	 * @���ߣ�Qilm[���ţ�964]
	 * @���ڣ�2013-12-27 ����04:10:56
	 * @param xh
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<String[]> getXsydListByXh(String xh)
			throws Exception {
		return dao.getXsydListByXh(xh);
	}
	
	/**
	 * 
	 * @����: ȡ��ѧ���춯����б�
	 * @���ߣ�Qilm[���ţ�964]
	 * @���ڣ�2013-12-27 ����04:10:56
	 * @param xh
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getXsydList(String xh)
			throws Exception {
		return dao.getXsydList(xh);
	}

	/**
	 * @����:��У����Ϣѧ������ӡ��ȡѧ���춯��Ϣ��������ͨ��ѧ��ѧԺ��
	 * @���ߣ�lgx [���ţ�1553]
	 * @���ڣ�2018/8/22 13:50
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param: [xh, n]
	 * @return: java.util.List<java.util.HashMap<java.lang.String,java.lang.String>>
	 */
	public List<HashMap<String, String>> getXsydListForWord(String xh,int n)
			throws Exception {
		List<HashMap<String, String>> list = dao.getXsydListForWord(xh,String.valueOf(n));
		int m=n-list.size();
		for (int i = 0; i <m; i++) {
			list.add(new HashMap<String, String>());
		}
		return list;
	}
}
