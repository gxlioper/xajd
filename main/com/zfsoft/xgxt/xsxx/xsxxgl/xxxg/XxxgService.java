/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-8-5 ����11:11:11
 */
package com.zfsoft.xgxt.xsxx.xsxxgl.xxxg;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;
import xgxt.utils.Pages;
import xsgzgl.xsxx.general.jcsz.XsxxJcszService;
import xsgzgl.xsxx.general.qzxgmdsz.QzxgmdszService;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.export.util.DateUtils;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.transaction.TransactionControl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.JsonUtil;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.xsxx.xsxxgl.xxgl.XsxxglModel;
import com.zfsoft.xgxt.xsxx.xsxxgl.xxgl.XsxxglService;

/**
 * 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ѧ����Ϣ
 * @�๦������: ѧ����Ϣ�޸�
 * @���ߣ� ligl
 * @ʱ�䣺 2013-11-23 ����11:12:32
 * @�汾�� V1.0
 * @�޸ļ�¼:
 */
public class XxxgService extends SuperServiceImpl<XsxxglModel, XxxgDao> {

	private XxxgDao dao = new XxxgDao();
	private ShlcInterface shlc = new CommShlcImpl();

	public XxxgService() {
		super.setDao(dao);
	}

	/**
	 * 
	 * @����:��ȡѧ����Ϣ����б�
	 * @���ߣ�ligl
	 * @���ڣ�2013-11-23 ����11:46:35
	 * @�޸ļ�¼:
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getXsxxList(XsxxglModel model,
			User user) throws Exception {
		return dao.getPageList(model, user);
	}

	/**
	 * @throws Exception 
	 * @throws Exception 
	 * 
	 * @����:�ύ�޸�������Ϣ
	 * @���ߣ�ligl
	 * @���ڣ�2013-12-6 ����10:34:13
	 * @�޸ļ�¼:
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public boolean saveXgsq(XgsqModel model) throws Exception{
		boolean result = true;
//		try {
		String shzSqid = getShzDataByXh(model.getXh());
		if(!StringUtil.isNull(shzSqid)){
			model.setShjg(Constants.YW_SHZ);
			model.setSqid(shzSqid);
		}
		//�����״̬��Ϊ���˻ء�ʱ�������µ�sqid
		if(!(Constants.SH_TH.equals(model.getShjg())
				|| Constants.SHZ.equals(model.getShjg()))){
			String guid = UniqID.getInstance().getUniqIDHash();
			guid = guid.toUpperCase();
			model.setSqid(guid);
		}
		//ɾ��δ�ύ�����̼�¼
		dao.deleteShlc(model);
		//ɾ��δ�ύ�������¼
		dao.deleteXgsq(model);

		model.setXgsj(DateUtils.getCustomFomratCurrentDate("1"));

		String shlid = null;
		HashMap<String, String> splMap = new XsxxJcszService().getOnesCssz();
		shlid = splMap.get("shlid");
		
		String xgzd = model.getXgzd();
		List<XgzdModel> xgzdList = null;
		if(xgzd != null && !xgzd.equals("")){
			xgzdList = JsonUtil.jsonToList(xgzd, XgzdModel.class);
		}
		// /////ɾ���޸��ֶ�////////////////
		if (xgzdList != null && xgzdList.size() > 0) {
			if(Constants.SH_TH.equals(model.getShjg())
					|| Constants.SHZ.equals(model.getShjg())){
				dao.delXgzdb(model.getSqid());
			}
			dao.insertXgzd(xgzdList, model.getSqid());
		}
		if (shlid == null || shlid.equals("")  || shlid.equals("wxsh")  ) {// �������
			model.setShjg(Constants.YW_TG);
			XsxxglService xsxxglService = new XsxxglService();
			List<HashMap<String, String>> xsxgzdList = null;
			if(xgzd != null && !xgzd.equals("")){
				xsxgzdList = JsonUtil.jsonToList(xgzd, HashMap.class);
			}
			// �޸�ѧ����Ϣ
			result = xsxxglService
					.updateRecordForStu(model.getSqid(),model.getXh(), false, false,xsxgzdList);
		} else {
			if (!Constants.SHZ.equals(model.getShjg())) {
				result = shlc.runSubmit(model.getSqid(), shlid, model.getXh(),
						"xsxx_xsxxxg_xgsh.do", "xsxx_xsxxxg_xgsq.do");
			}
		}

		//�����״̬��Ϊ���˻ء�ʱ���Ž��в���
		if(!(Constants.SH_TH.equals(model.getShjg())
				|| Constants.SHZ.equals(model.getShjg()))){
			if (shlid == null || shlid.equals("")  || shlid.equals("wxsh")  ) {// �������
				model.setShjg(Constants.YW_TG);
			} else {
				model.setShjg(Constants.YW_SHZ);
			}
//			String shzSqid =getShzDataByXh(model.getXh());
			dao.insertXgsq(model);	//���������¼
		} else {
			model.setShjg(Constants.YW_SHZ);
			dao.updateXgsqZt(model);  //�������������״̬
		}
		
		new QzxgmdszService().xgQzxgzt(model.getXh());// �޸�ǿ���޸�����
		return result;
//		} catch (Exception e) {
//			logger.debug(e.getMessage());
//			return false;
//		}
	}
	
	
	//�����޸�������Ϣ
	@SuppressWarnings("unchecked")
	public boolean saveXgsqDemo(XgsqModel model) throws Exception {
		boolean result = true;
		//�����״̬��Ϊ���˻ء�ʱ���Ž���ɾ��ԭ��������̺������¼���������µ�sqid,�������״̬�Ȳ���
		if(!Constants.SH_TH.equals(model.getShjg())){
			String guid = UniqID.getInstance().getUniqIDHash();
			guid = guid.toUpperCase();
			model.setSqid(guid);
			model.setShjg(Constants.YW_WTJ);
		}
		dao.deleteShlc(model);
		dao.deleteXgsq(model);
		
		model.setXgsj(DateUtils.getCustomFomratCurrentDate("1"));
		String xgzd = model.getXgzd();
		List<XgzdModel> xgzdList = null;
		if(xgzd != null && !xgzd.equals("")){
			xgzdList = JsonUtil.jsonToList(xgzd, XgzdModel.class);
		}
		// /////ɾ���޸��ֶ�////////////////
		if (xgzdList != null && xgzdList.size() > 0) {
			if(Constants.SH_TH.equals(model.getShjg())){
				dao.delXgzdb(model.getSqid());
			}
			dao.insertXgzd(xgzdList, model.getSqid());
		}
		//�����״̬��Ϊ���˻ء�ʱ���Ž��в���
		if(!Constants.SH_TH.equals(model.getShjg())){
			dao.insertXgsq(model);//���������¼
		}
		return result;
	}
	/**
	 * 
	 * @����:��˼�¼
	 * @���ߣ�ligl
	 * @���ڣ�2013-12-11 ����01:31:37
	 * @�޸ļ�¼:
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getWclPageList(XsxxglModel model,
			User user) throws Exception {
		return dao.getWclPageList(model, user);
	}

	/**
	 * 
	 * @����:�޸�����״̬
	 * @���ߣ�ligl
	 * @���ڣ�2013-12-9 ����09:42:04
	 * @�޸ļ�¼:
	 * @param model
	 * @return
	 * @throws Exception boolean ��������
	 * @throws
	 */
	public boolean updateXgsqZt(XgsqModel model) throws Exception {
		return dao.updateXgsqZt(model);
	}

	/**
	 * 
	 * @����:����˻�
	 * @���ߣ�ligl
	 * @���ڣ�2013-12-9 ����09:42:04
	 * @�޸ļ�¼:
	 * @param model
	 * @return
	 * @throws Exception boolean ��������
	 * @throws
	 */
	public boolean thXgsqZt(String sqid) throws Exception {
		XgsqModel model = new XgsqModel();
		model.setSqid(sqid);
		XsxxglService service = new XsxxglService();
		boolean result = service.updateRecordForStu(sqid, true);
		if (result) {
			model.setShjg(Constants.YW_SHZ);
			result = dao.updateXgsqZt(model);
		}
		return result;
	}
	
	public String updateSqzt(String sqid,String shid,String splc,User user) throws Exception {
		ShlcInterface service = new CommShlcImpl();
		XgsqModel model = new XgsqModel();
		model.setSqid(sqid);
		//�ж��������
		HashMap<String,String> shxx =ShlcDao.getDqjbByCondition(sqid, user.getUserName(), splc, "cx");
		String cancelFlag= service.runCancelNew(user.getUserName(), shid, splc);
		if ("1".equals(shxx.get("xh"))) {
			model.setShjg(Constants.YW_WTJ);
		}
		else{
			model.setShjg(Constants.YW_SHZ);
		}
		dao.updateXgsqZt(model);
		return cancelFlag;
	}

	/**
	 * 
	 * @����:ͨ������id���õ��޸��ֶμ��޸ĺ�ֵ
	 * @���ߣ�ligl
	 * @���ڣ�2013-12-9 ����01:45:09
	 * @�޸ļ�¼:
	 * @param sqid
	 * @return
	 * @throws
	 */
	public List<HashMap<String, String>> getXgzdList(String sqid)
			throws Exception {
		return dao.getXgzdList(sqid);
	}

	public List<HashMap<String, String>> getXgjgPageList(XsxxglModel model,
			User user) throws Exception {
		return dao.getXgjgPageList(model, user);
	}

	/**
	 * 
	 * @����:����ѧ�ţ���ȡ���µ�һ������еļ�¼
	 * @���ߣ�ligl
	 * @���ڣ�2013-12-16 ����03:57:35
	 * @�޸ļ�¼:
	 * @param xh
	 * @return
	 * @throws Exception
	 * @throws
	 */
	public String getShzDataByXh(String xh) throws Exception {
		HashMap<String, String> map = dao.getDataByXh(xh, Constants.YW_SHZ);
		String sqid = "";
		if (map != null) {
			sqid = map.get("sqid");
		}
		return sqid;
	}
	/**
	 * 
	 * @����:����ѧ�Ż�ȡ���µ������Ϣ
	 * @���ߣ�xiaxia [���ţ�1104]
	 * @���ڣ�2014-11-17 ����01:44:51
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * @throws Exception
	 * String �������� 
	 * @throws
	 */
	public HashMap<String, String> getShxxByXh(String xh) throws Exception {
		return dao.getShxxByXh(xh);
	}
	public HashMap<String, String> getShztByXh(String xh) throws Exception {
		return dao.getShztByXh(xh);
	}
	/**
	 * 
	 * @����:����ѧ�ţ���ȡ���µ�һ������˵ļ�¼
	 * @���ߣ�ligl
	 * @���ڣ�2013-12-16 ����03:57:35
	 * @�޸ļ�¼:
	 * @param xh
	 * @return
	 * @throws Exception
	 * @throws
	 */
	public String getDshDataByXh(String xh) throws Exception {
		HashMap<String, String> map = dao.getDshByXh(xh);
		String sqid = "";
		if (map != null) {
			sqid = map.get("sqid");
		}
		return sqid;
	}
	
	
	/**
	 * 
	 * @����:ͨ������id ��ȡѧ��
	 * @���ߣ�ligl
	 * @���ڣ�2013-12-24 ����09:59:20
	 * @�޸ļ�¼: 
	 * @param sqid
	 * @return
	 * @throws Exception
	 * String �������� 
	 * @throws
	 */
	public String getXhBySqid(String sqid) throws Exception {
		return dao.getXhBySqid(sqid);
	}
	
	//��˵�������ҳ���÷���
	public List<HashMap<String,String>> getAllListSh(XsxxglModel t, User user) throws Exception {
			
		Pages pages = (Pages) t.getClass().getMethod("getPages").invoke(t);
		pages.setPageSize(Integer.MAX_VALUE);
		
		t.getClass().getMethod("setPages",Pages.class).invoke(t, pages);
		
		return dao.getWclPageList(t, user);
	}
	
	/**
	 * @�����������������ҳ
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2016��10��21�� 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> ��������
	 */
	public List<HashMap<String,String>> getAllListJg(XsxxglModel t, User user) throws Exception {
			
		Pages pages = (Pages) t.getClass().getMethod("getPages").invoke(t);
		pages.setPageSize(Integer.MAX_VALUE);
		t.getClass().getMethod("setPages",Pages.class).invoke(t, pages);
		return dao.getXgjgPageList(t, user);
	}
	
	//�����״̬Ϊ�˻�ʱ������ݸ�����ύʱ��Ҫɾ��xgzdb�е�����
	public boolean delXgzdb(String sqid) throws Exception{
		return dao.delXgzdb(sqid);
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����:������ҽҩ���˻񽱸����ϴ���ѯ
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-9-26 ����02:55:42
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getGrfjscCx(XsxxglModel model,
			User user) throws Exception{
		return dao.getGrfjscCx(model, user);
	}
	
	/**
	 * 
	 * @����: ��ȡѧ������Ϣ
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-9-26 ����04:57:06
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getXshjXx(String hjid){
		return dao.getXshjXx(hjid);
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @������������ҽҩ�ϴ�����������gid
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-9-26 ����05:58:22
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param hjid
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean updateGrhjxxGid(String hjid,String gid) throws Exception{
		return dao.updateGrhjxxGid(hjid, gid);
	}
}
