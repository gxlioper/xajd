/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-7-30 ����10:31:31 
 */
package com.zfsoft.xgxt.bzjl.wdbzjl.bzjlsqsh;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.check.CheckCondition;
import com.zfsoft.xgxt.base.check.impl.CheckStudentCondition;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.FreeMarkerUtil;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.bzjl.wdbzjl.bzjljg.BzjljgDao;
import com.zfsoft.xgxt.bzjl.wdbzjl.bzjljg.BzjljgModel;
import com.zfsoft.xgxt.bzjl.wdbzjl.bzjljg.BzjljgService;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.xpjpy.cssz.CsszDao;
import com.zfsoft.xgxt.xpjpy.cssz.CsszModel;
import com.zfsoft.xgxt.xpjpy.cssz.CsszService;
import com.zfsoft.xgxt.xpjpy.pjpybjpy.jgcx.JgcxDao;
import com.zfsoft.xgxt.xpjpy.pjpybjpy.jgcx.JgcxForm;
import com.zfsoft.xgxt.xpjpy.xmsz.jdsz.JdszDao;
import com.zfsoft.xgxt.xpjpy.xmsz.rssz.RsszDao;
import com.zfsoft.xgxt.xpjpy.xmsz.tjsz.TjszService;
import com.zfsoft.xgxt.xpjpy.xmsz.xmwh.XmwhDao;
import com.zfsoft.xgxt.xpjpy.xmsz.xmwh.XmwhModel;
import com.zfsoft.xgxt.xpjpy.zhcp.zcfs.ZcfsService;
import com.zfsoft.xgxt.xpjpy.zhcp.zcxm.ZcxmDao;
import jxl.Workbook;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.*;
import net.sf.json.JSONArray;
import xgxt.action.Base;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;
import xsgzgl.xtwh.wdgz.Job;
import xsgzgl.xtwh.wdgz.MyJobsManager;
import xsgzgl.xtwh.wdgz.impl.MyJobsImpl;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.*;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �������Ź���ģ��
 * @�๦������: �ҵ�����-�������
 * @���ߣ� Penghui.Qu [���ţ�445]
 * @ʱ�䣺 2013-7-30 ����10:31:31
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class BzjlsqshService extends SuperServiceImpl<BzjlsqshModel, BzjlsqshDao> implements
		Constants {
	// �Ƿ�༶����.1���ǣ�0����
	private static final boolean SFBJPY_Y = "1".equals(MessageUtil.getText("xpjpy.sfbjpy"));
	private BzjlsqshDao dao = new BzjlsqshDao();
	public static final String TJDW_CPZ = "cpz";
	public static final String TJDW_NJZY = "njzy";
	public static final String TJDW_BJ = "bj";
	public static final String DSH = "0";
	public static final String DEFAULT_PMFS = "bjpm";// Ĭ��������ʽ
	public static final String SQSH = "1";

	private ShlcInterface shlc = new CommShlcImpl();

	public BzjlsqshService() {
		super.setDao(dao);
	}

	/**
	 * 
	 * @����: �����ϱ���ѯ�б�
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-7-30 ����02:49:16
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> ��������
	 */
	public List<HashMap<String, String>> getJxsbList(BzjlsqshModel t, User user)
			throws Exception {

		String xmdm = t.getXmdm();

		// ������Ŀ��Ϣ
		XmwhDao xmwhDao = new XmwhDao();
		XmwhModel xmwhModel = xmwhDao.getModel(xmdm);

		CsszDao csszDao = new CsszDao();
		CsszModel csszModel = csszDao.getModel();

		ZcxmDao zcxmDao = new ZcxmDao();
		List<HashMap<String, String>> zcxmList = zcxmDao.getZcxmList(csszModel
				.getXn(), csszModel.getXq());
		// ǰ�����۲���Ŀ
		// List<HashMap<String,String>> zcxmList =
		// zcxmDao.getFirstAndSecondZcxm();
		// List<HashMap<String,String>> zcxmList = zcxmDao.getCurrentZfxm();

		String pmfs = xmwhModel.getZcfpm();

		if (StringUtil.isNull(pmfs)) {
			pmfs = DEFAULT_PMFS;
		}

		return dao.getJxsbList(t, user, pmfs, zcxmList);
	}
	public boolean updateModel(BzjlsqshModel model) throws Exception {
		return super.runUpdate(model);
	}
	/**
	 * 
	 * @����: �����ϱ�����
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-7-31 ����01:50:09
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception boolean ��������
	 */
	public boolean saveJxsb(BzjlsqshModel model, String userName) throws Exception {

		String sqid = UniqID.getInstance().getUniqIDHash();

		// ��Ŀ��Ϣ
		XmwhDao xmwhDao = new XmwhDao();
		XmwhModel xmwhModel = xmwhDao.getModel(model.getXmdm());
		String splc = xmwhModel.getShlc();

		// ����������Ϣ
		CsszDao csszDao = new CsszDao();
		CsszModel csszModel = csszDao.getModel();

		model.setSqr(userName);
		model.setXn(csszModel.getXn());
		model.setXq(CsszService.getPjzq().get("xq"));
		model.setSqid(sqid);
		model.setSplc(splc);

		// ��������������趨��ʼֵ
		if ("submit".equals(model.getType())) {
			if(SFBJPY_Y){
				model.setShzt(Constants.YW_BJPYZ);
			}else{
				model.setShzt(Constants.YW_SHZ);
			}
		} else {
			model.setShzt(Constants.YW_WTJ);// δ�ύ
		}

		// ����������Ϣ
		boolean result = dao.runInsert(model);

		// ���������Ϣ
		if (!"save".equals(model.getType())) {
			if (!SFBJPY_Y && result) {
				result = shlc.runSubmit(sqid, splc, model.getXh(),
						"pj_jxsh.do", "pj_pjxmsq.do");
			}
		}

		return result;
	}

	/**
	 * 
	 * @����: ��ѯ������������Ŀ�б�
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-8-7 ����11:22:36
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return List<HashMap<String,String>> ��������
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getYsqPjxmList(String xh, String xmdm)
			throws Exception {

		if (StringUtil.isNull(xh) || StringUtil.isNull(xmdm)) {
			return null;
		}

		// ��ѯ���ɼ����Ŀ
		JdszDao jdszDao = new JdszDao();
		List<HashMap<String, String>> bjdxmList = jdszDao.getBjdxm(xmdm);
		Set<String> bjdXmdm = new HashSet<String>();

		for (HashMap<String, String> map : bjdxmList) {
			bjdXmdm.add(map.get("xmdm"));
		}

		// ��������Ŀ�б�
		List<HashMap<String, String>> ysqList = dao.getYsqInfoList(xh);
		// ����������б����Ƿ��в������Ŀ

		for (HashMap<String, String> map : ysqList) {
			if (bjdXmdm.contains(map.get("xmdm"))) {
				// ���Ӳ��ɼ�ñ�ʶ
				map.put("bkjd", "yes");
			} else {
				map.put("bkjd", "no");
			}
		}

		return ysqList;
	}

	/**
	 * 
	 * @����: ��Ŀ������Ϣ
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-8-1 ����09:07:15
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * @throws Exception
	 *             Map<String,Object> ��������
	 */
	public Map<String, Object> getXmsqInfoList(String xh) throws Exception {

		// ����������û��������Ľ���
		List<HashMap<String, String>> wsqList = dao.getKsqInfoList(xh);
		// ��ǰѧ�ꡢѧ��������Ľ���
		List<HashMap<String, String>> ysqList = dao.getYsqInfoList(xh);
		// ���ؽ��
		HashMap<String, Object> resultMap = new HashMap<String, Object>();

		TjszService tjszServcie = new TjszService();

		// ��֤����
		for (HashMap<String, String> pjxm : wsqList) {

			List<HashMap<String, String>> conditions = tjszServcie.getTjszGl(
					pjxm.get("xmdm"), xh);
			// У�����������ز�������������Ŀ���ơ�
			CheckCondition check = new CheckStudentCondition();
			// У����
			List<HashMap<String, String>> results = check.checkCondition(xh,
					conditions);

			resultMap.put(pjxm.get("xmdm"), results);
			// pjxm.put("checkResult", results);
		}

		resultMap.put("wsqList", wsqList);
		resultMap.put("ysqList", ysqList);
		return resultMap;
	}

	/**
	 * 
	 * @����: �������潱������
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-8-1 ����10:05:33
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xmdm
	 * @param t
	 * @return
	 * @throws Exception boolean ��������
	 */
	public boolean saveJxsq(String[] xmdm, BzjlsqshModel t) throws Exception {

		if (xmdm == null || xmdm.length == 0) {
			return false;
		}

		for (String pjxm : xmdm) {

			BzjlsqshModel model = new BzjlsqshModel();
			model.setXh(t.getXh());
			model.setSqly(t.getSqly());
			model.setXmdm(pjxm);
			model.setDqxmdm(pjxm);
			model.setType(t.getType());
			model.setSqid(t.getSqid());
			model.setYlzd1(t.getYlzd1()); 
			model.setYlzd2(t.getYlzd2()); 
			model.setYlzd3(t.getYlzd3()); 
			model.setYlzd4(t.getYlzd4()); 
			model.setYlzd5(t.getYlzd5()); // ����id
			/**
			 * ����ҽҩ�ߵ�ר��ѧУ
			 */
			if("70002".equals(Base.xxdm)){
				model.setDjjl(t.getDjjl());
			}
			saveJxsb(model, t.getSqr());
		}
		return true;
	}

	/*
	 * 
	 * ����: @see
	 * com.zfsoft.xgxt.base.service.impl.SuperServiceImpl#runDelete(java
	 * .lang.String[])
	 */
	public int runDelete(String[] values) throws Exception {

		// ɾ�������¼
		int delNum = dao.delXmsq(values);

		if (0 != delNum) {
			// ɾ����˼�¼
			dao.delShzt(values);
		}

		return delNum;
	}

	/**
	 * 
	 * @����: ��ѯ������
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-8-2 ����08:38:13
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> ��������
	 * @deprecated
	 */
	public List<HashMap<String, String>> getShqkList(BzjlsqshModel t, User user)
			throws Exception {

		String tjdw = t.getTjdw();

		if (TJDW_BJ.equals(tjdw)) {
			return dao.getShqkByBj(t, user);
		} else if (TJDW_NJZY.equals(tjdw)) {
			return dao.getShqkByNjzy(t, user);
		} else {
			return dao.getShqkByCpz(t, user);
		}

	}

	/**
	 * 
	 * @����: �û�������λ
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-8-2 ����10:27:39
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param user
	 * @return List<HashMap<String,String>> ��������
	 */
	public List<HashMap<String, String>> getSpgwList(User user) {

		return dao.getSpgwList(user);
	}

	/**
	 * 
	 * @����: ��ѯ���ѧ���б�
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-8-2 ����03:07:42
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> ��������
	 */
	public List<HashMap<String, String>> getAudingList(BzjlsqshModel t, User user)
			throws Exception {

		CsszDao csszDao = new CsszDao();
		CsszModel csszModel = csszDao.getModel();

		ZcxmDao zcxmDao = new ZcxmDao();
		List<HashMap<String, String>> zcxmList = zcxmDao.getZcxmList(csszModel
				.getXn(), csszModel.getXq());

		return dao.getAudingList(t, user, DEFAULT_PMFS, zcxmList);
	}

	/**
	 * 
	 * @����:��ѯ����ѧ���б�
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-12-11 ����02:16:27
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getXssqList(BzjlsqshModel t, User user)
			throws Exception {

		CsszDao csszDao = new CsszDao();
		CsszModel csszModel = csszDao.getModel();

		ZcxmDao zcxmDao = new ZcxmDao();
		List<HashMap<String, String>> zcxmList = zcxmDao.getZcxmList(csszModel
				.getXn(), csszModel.getXq());

		return dao.getXssqList(t, user, DEFAULT_PMFS, zcxmList);
	}

	/**
	 * 
	 * @����:��ѯ����ѧ���б�
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2013-12-10 ����02:16:01
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> ��������
	 * @throws
	 */

	public List<HashMap<String, String>> getAudingListSingle(BzjlsqshModel t,
			User user) throws Exception {

		CsszDao csszDao = new CsszDao();
		CsszModel csszModel = csszDao.getModel();

		ZcxmDao zcxmDao = new ZcxmDao();
		List<HashMap<String, String>> zcxmList = zcxmDao.getZcxmList2(csszModel
				.getXn(), csszModel.getXq());

		return dao.getAudingListSingle(t, user, DEFAULT_PMFS, zcxmList);
	}

	/**
	 * 
	 * 
	 * @����:�õ���󼶵������Ϣ
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-12-5 ����09:12:53
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param u
	 * @param bjdms
	 * @param xn
	 * @param xq
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getLastCheckStatus(User u,
			String bjdms, String xn, String xq) throws Exception {

		String[] bjdm = bjdms.split(",");
		String params = "";
		for (int i = 0; i < bjdm.length; i++) {
			params += "'" + bjdm[i] + "',";
		}
		if (params.length() > 0) {
			params = params.substring(0, params.length() - 1);
		}

		return dao.getLastCheckStatus(u, params, xn, xq);
	}

	public List<HashMap<String, String>> getLastCheckStatus(String bjdms,
			String xn, String xq) throws Exception {

		String[] bjdm = bjdms.split(",");
		String params = "";
		for (int i = 0; i < bjdm.length; i++) {
			params += "'" + bjdm[i] + "',";
		}
		if (params.length() > 0) {
			params = params.substring(0, params.length() - 1);
		}

		return dao.getLastCheckStatus(params, xn, xq);
	}

	/**
	 * 
	 * @����:��󼶵ĳ���
	 * @���ߣ�obq[445]
	 * @���ڣ�2013-12-5 ����09:18:45
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param shlc
	 * @param ywid
	 * @return
	 * @throws Exception boolean ��������
	 * @throws
	 */
	public boolean cancel(String shlc, String ywid, User user) throws Exception {
		BzjlsqshModel upForm = new BzjlsqshModel();
		upForm.setSqid(ywid);
		upForm.setShzt(Constants.YW_SHZ);
		boolean result = dao.runUpdate(upForm, ywid);

		if (result) {
			// �ع�����������
			BzjljgDao jgdao = new BzjljgDao();
			jgdao.delJgb(ywid);
		}
		return result;
	}

	/**
	 * 
	 * @����: ��ѯ�����¼����Ӧ��˼�¼�б�
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-8-5 ����10:32:03
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param sqid
	 * @return List<HashMap<String,String>> ��������
	 */
	public List<HashMap<String, String>> getSpjlList(String sqid) {
		return dao.getSpjlList(sqid);
	}
	
	public String batchSave(BzjlsqshModel t, User user) throws Exception {
		String[] ids = t.getId();
		String[] gwids = t.getGwids();
		String[] xhs = t.getXhs();
		String[] splcs = t.getSplcs();
		
		
		
		//15-5-15 ������ɸ��ݸ߼���ѯ�����������
		if(!StringUtils.isArrayNotNull(ids)){
			String Shztl = t.getShzt();//����ʱ����֮ǰ���״̬��
			t.setShzt(WSH); //δ���
			t.getPages().setPageSize(Integer.MAX_VALUE);  //��ѯ����ҳ
			List<HashMap<String, String>> shjgList = getAudingListSingle(t,user);
			if(null==shjgList||shjgList.size()==0){
				return MessageUtil.getText("��ѯ���Ϊ��");
			}
			
			String[] idss = new String[shjgList.size()];
			String[] gwidss = new String[shjgList.size()];
			String[] xhss = new String[shjgList.size()];
			String[] splcss = new String[shjgList.size()];
			
			for (int i = 0; i < shjgList.size(); i++) {
				idss[i] = shjgList.get(i).get("sqid");
				gwidss[i] = shjgList.get(i).get("gwid");
				xhss[i] = shjgList.get(i).get("xh");
				splcss[i] = shjgList.get(i).get("splc");
			}
			//���¸���ѧ��
			ids=idss;
			gwids=gwidss;
			xhs=xhss;
			splcs=splcss;
			t.setShzt(Shztl);
		}

		// ���ж�ѡ���ѧ���������Ƿ񳬹�ѡ��ѧ��������Ŀ����������
		if (t.getShzt().equals("1")) {
			String sqids = "";
			for (int i = 0; i < ids.length; i++) {
				sqids += "'" + ids[i] + "',";
			}
			if (sqids.length() > 0) {
				sqids = sqids.substring(0, sqids.length() - 1);
			}

			List<HashMap<String, String>> results = dao.getXzdrs(sqids);

			for (int i = 0; i < results.size(); i++) {
				HashMap<String, String> rmap = (HashMap<String, String>) results
						.get(i);
				if (new Integer(rmap.get("dqjb")).intValue() >= new Integer(
						rmap.get("kzjb")).intValue()) {

					HashMap<String, String> tmap = dao.getKzrsTgrsByXmdm(rmap
							.get("xmdm"), rmap.get("dqjb"), rmap.get("cpbm"),
							rmap.get("rsfpfs"));
					if (tmap != null && tmap.size() > 0) {
						if (new Integer(rmap.get("xzrs")).intValue() > new Integer(
								tmap.get("zzme")).intValue()
								- new Integer(tmap.get("ytggs")).intValue()) {
							return MessageUtil
									.getText(MessageKey.SYS_AUD_PERS_OUT);
						}
					}
				}
			}
		}

		List<String> failXhs = new ArrayList<String>();
		for (int i = 0, n = ids.length; i < n; i++) {
			BzjlsqshModel model = new BzjlsqshModel();
			model.setSqid(ids[i]);
			//�����������ȡ�ϼ���˵����Ľ���
			String gwxh = dao.getXh(splcs[i], gwids[i]);
			HashMap<String, String> tmpMap  = new HashMap<String,String>();
			if(Integer.parseInt(gwxh)>1){
				//�ϼ���˸�λ���
				String sjgwxh = String.valueOf(Integer.parseInt(gwxh)-1);
				String sjgwid = dao.getSjGwid(splcs[i], sjgwxh);
				 tmpMap = shlc.getShxxByCondition(ids[i],
						sjgwid);
			}else{
			 tmpMap = shlc.getShxxByCondition(ids[i],gwids[i]);
			}
			BzjlsqshModel s = dao.getModel(model);
			if (tmpMap.get("xjzd2") == null || tmpMap.get("xjzd2").equals("")) {
				model.setXmdm(s.getXmdm());
				model.setPdjx(s.getDqxmdm());
			} else {
				model.setXmdm(tmpMap.get("xjzd2"));
				model.setPdjx(s.getDqxmdm());
			}

			model.setGwid(gwids[i]);
			model.setShyj(t.getShyj());
			model.setShjg(t.getShzt());
			model.setThgw(null);
			model.setSplc(tmpMap.get("lcid"));
			model.setXh(s.getXh());
			model.setXn(s.getXn());
			model.setXq(s.getXq());
			model.setYlzd5(s.getYlzd5()); // ����id

			String isSuccess = runAuding(model, user);

			if (!isSuccess.equals("true")) {
				failXhs.add(xhs[i]);
			}
		}

		JSONArray json = JSONArray.fromObject(failXhs);
		if (failXhs.isEmpty()) {
			return MessageUtil.getText(MessageKey.SYS_AUD_SUCCESS);
		} else {
			return MessageUtil.getText(MessageKey.SYS_PLSH_FAIL, json
					.toString());
		}
	}

	/**
	 * 
	 * @����:�������
	 * @���ߣ�445
	 * @���ڣ�2013-12-9 ����03:00:49
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 *             String ��������
	 * @throws
	 */
	public String savePlsh(BzjlsqshModel t, User user) throws Exception {
		String newXmdms = "";
		String newBjdms = "";
		if (t.getXmdms() != null && !t.getXmdms().equals("")) {
			String[] xmdms = t.getXmdms().split(",");

			for (int i = 0; i < xmdms.length; i++) {
				newXmdms += "'" + xmdms[i] + "',";
			}
			if (newXmdms.length() > 0) {
				newXmdms = newXmdms.substring(0, newXmdms.length() - 1);
			}

			String[] bjdms = t.getBjdms().split(",");
			for (int i = 0; i < bjdms.length; i++) {
				newBjdms += "'" + bjdms[i] + "',";
			}
			if (newXmdms.length() > 0) {
				newBjdms = newBjdms.substring(0, newBjdms.length() - 1);
			}

		} else {
			return "��ѡ����Ҫ������˵Ľ��";
		}

		// �õ���Ҫ������˵����ݼ�
		List<HashMap<String, String>> resultList = dao.getCanOperatData(t,
				newXmdms, newBjdms);
		
		List<String> failXhs = new ArrayList<String>();
		for (int i = 0; i < resultList.size(); i++) {
			HashMap<String, String> mp = (HashMap<String, String>) resultList
					.get(i);
			BzjlsqshModel model = new BzjlsqshModel();
			model.setSplc(mp.get("splc"));
			model.setThgw(null);
			model.setGwid(mp.get("gwid"));
			model.setSqid(mp.get("sqid"));
			model.setXh(mp.get("xh"));
			model.setShyj(t.getShyj());
			if (mp.get("zd2") == null || mp.get("zd2").equals("")) {
				model.setPdjx(mp.get("xmdm"));
			} else {
				model.setPdjx(mp.get("zd2"));
			}

			model.setXn(mp.get("xn"));
			model.setXq(mp.get("xq"));
			model.setShjg("1");
			model.setXmdm(mp.get("xmdm"));
			String isSuccess = runAuding(model, user);

			if (!isSuccess.equals("true")) {
				failXhs.add(mp.get("xh"));
			}
		}

		JSONArray json = JSONArray.fromObject(failXhs);
		if (failXhs.isEmpty()) {
			return MessageUtil.getText(MessageKey.SYS_AUD_SUCCESS);
		} else {
			return MessageUtil.getText(MessageKey.SYS_PLSH_FAIL, json
					.toString());
		}
	}

	/**
	 * 
	 * @����: ������˲���
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-8-5 ����10:35:33
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return boolean ��������
	 * @throws Exception
	 */
	public String runAuding(BzjlsqshModel t, User user) throws Exception{
		
		synchronized (user) {
			// ��˲���Model��ʼ��
			ShxxModel model = new ShxxModel();
			model.setShlc(t.getSplc());
			model.setShr(user.getUserName());
			model.setShyj(t.getShyj());
			model.setShzt(t.getShjg());
			model.setThgw(t.getThgw());
			model.setGwid(t.getGwid());
			model.setYwid(t.getSqid());
			
			model.setSqrid(t.getXh());
			model.setTzlj("pj_jxsh.do");
			model.setTzljsq("pj_pjxmsq.do");
			model.setZd1("��������");
			model.setZd2(t.getPdjx());
			XmwhModel dcForm = new XmwhModel();
			XmwhDao xmdao = new XmwhDao();
			dcForm.setXmdm(t.getPdjx());
			dcForm = xmdao.getModel(dcForm);
			model.setZd3(dcForm.getXmmc());
			
			if("11488".equalsIgnoreCase(Base.xxdm)){
				model.setZd4("��˽��");
				model.setZd6(t.getShje());
			}
						
			//�жϵ�ǰ�����Ƿ���������л�ͨ����¼
			String checkXm = dao.checkXhsqsh(t.getXn(), t.getXq(), t.getPdjx(), t.getXh(),t.getSqid());
			
			
			if(Integer.valueOf(checkXm) > 0){
				throw new SystemException(MessageKey.PJPY_FAIL);
				
			}
			
			boolean result = false;
				
				//�ж��������
				HashMap<String,String> shxx =ShlcDao.getDqjbByCondition(t.getSqid(), user.getUserName(), t.getSplc(), "sh");
				//���ǰһ�������Ŀ����
				String tzhxmdm = "";
				BzjlsqshDao sqshdao = new BzjlsqshDao();
				String rskzxh = sqshdao.getRskzXh(t.getPdjx());
				
				//������ͨ����������˼�����ڵ��ڿ��Ƽ��𣬸��µ�������Ŀ����
				if(Constants.SH_TG.equals(t.getShjg()) && (shxx.get("xh")!=null)
						&& new Integer(shxx.get("xh")).intValue()>=new Integer(rskzxh).intValue()){
					tzhxmdm = t.getPdjx();
					checkRskz(t.getGwid(),t.getPdjx(),t.getXh(),"sh");
				}
				
				//���ͨ��
				if(t.getShjg().equals(Constants.SH_TG)){
					BzjljgModel pjjgModel = new BzjljgModel();
					BzjljgDao pjjgDao = new BzjljgDao();
					BzjlsqshModel viewModel = dao.getModel(t.getSqid());
					XmwhModel xmwhModel = new XmwhDao().getModel(t.getPdjx());
					
					pjjgModel.setXmdm(t.getPdjx());
					pjjgModel.setXmje(xmwhModel.getXmje());
					pjjgModel.setXmmc(xmwhModel.getXmmc());
					pjjgModel.setXn(viewModel.getXn());
					if(!CsszService.XQKG.equalsIgnoreCase(viewModel.getXq())){
						pjjgModel.setXq(viewModel.getXq());
					}
					pjjgModel.setSqly(viewModel.getSqly());
					pjjgModel.setSqsj(viewModel.getSqsj());
					pjjgModel.setXh(viewModel.getXh());
					pjjgModel.setXzdm(xmwhModel.getXzdm());
					pjjgModel.setLxdm(xmwhModel.getLxdm());
					pjjgModel.setLylcywid(t.getSqid());
					//�жϵ�ǰ���������Ƿ����м�¼
					String checkJgxm = pjjgDao.checkExistForSave(pjjgModel);
					
					if(Integer.valueOf(checkJgxm) > 0){
						throw new SystemException(MessageKey.PJPY_FAIL);
					}
				}
				
				String zhzt = shlc.runAuditing(model);
				BzjlsqshModel upForm = new BzjlsqshModel();
				upForm.setSqid(t.getSqid());
				upForm.setShzt(zhzt);
				
				upForm.setTzhxmdm(tzhxmdm);
				upForm.setDqxmdm(t.getPdjx());
//				if("11488".equalsIgnoreCase(Base.xxdm)){
//					upForm.setYlzd1(upForm.getShje());
//				}
				result = dao.runUpdate(upForm, t.getSqid());
				if(result && zhzt.equals(Constants.SH_TG)){
					//���뵽�����
					BzjlsqshModel viewModel = dao.getModel(t.getSqid());
					//BeanUtils.copyProperties(pjjgModel, viewModel);
					XmwhModel xmwhModel = new XmwhDao().getModel(t.getPdjx());
					
					BzjljgModel pjjgModel = new BzjljgModel();
					pjjgModel.setSjly(SQSH);
					pjjgModel.setId(viewModel.getSqid());
					pjjgModel.setSqr(viewModel.getSqr());
					pjjgModel.setXmdm(t.getPdjx());
					pjjgModel.setXmje(xmwhModel.getXmje());
					pjjgModel.setXmmc(xmwhModel.getXmmc());
					pjjgModel.setXn(viewModel.getXn());
					if(!CsszService.XQKG.equalsIgnoreCase(viewModel.getXq())){
						pjjgModel.setXq(viewModel.getXq());
					}
					//����ҽҩ�ߵ�ר��ѧУ
					if("70002".equals(Base.xxdm)){
						pjjgModel.setDjjl(viewModel.getDjjl());
					}
					pjjgModel.setSqly(viewModel.getSqly());
					pjjgModel.setSqsj(viewModel.getSqsj());
					pjjgModel.setXh(viewModel.getXh());
					pjjgModel.setXzdm(xmwhModel.getXzdm());
					pjjgModel.setLxdm(xmwhModel.getLxdm());
					pjjgModel.setLylcywid(t.getSqid());
					//ѧ�������༶
					ZcfsService zcfsServcie = new ZcfsService();
					HashMap<String, String> cpbjxx = zcfsServcie.getCpbjListByXh(t.getXh(), t.getXn(), t.getXq());
					pjjgModel.setCpnj(cpbjxx.get("nj"));
					pjjgModel.setCpxymc(cpbjxx.get("xymc"));
					pjjgModel.setCpzymc(cpbjxx.get("zymc"));
					pjjgModel.setCpbjmc(cpbjxx.get("bjmc"));
					pjjgModel.setCpxydm(cpbjxx.get("xydm"));
					pjjgModel.setCpzydm(cpbjxx.get("zydm"));
					pjjgModel.setCpbjdm(cpbjxx.get("bjdm"));
					//����ʦ����ѧ���Ի�֤����
					if("10511".equals(Base.xxdm)){
						BzjljgService pjjgService = new BzjljgService();
						pjjgModel.setYlzd1(pjjgService.getZsbm(pjjgModel));
					}
					//����ѧԺ���Ի�
					if("11488".equals(Base.xxdm)){
						pjjgModel.setYlzd1(t.getShje());
					}
					pjjgModel.setYlzd1(viewModel.getYlzd1());
					pjjgModel.setYlzd3(viewModel.getYlzd3());
					pjjgModel.setYlzd4(viewModel.getYlzd4());
					
					pjjgModel.setYlzd5(t.getYlzd5()); // ����id
					BzjljgDao pjjgDao = new BzjljgDao();
					
						pjjgDao.runInsert(pjjgModel);
					
				}
				
				// �˻� ������ʱ
				if(result && SFBJPY_Y && zhzt.equals(Constants.SH_TH) && "-1".equals(t.getThgw())){
					BzjlsqshModel xszzSqshModelOld = new BzjlsqshModel();
					xszzSqshModelOld.setSqid(t.getSqid());
					BzjlsqshModel xszzSqshModelNew = dao.getModel(xszzSqshModelOld);
					JgcxDao jgcxDao = new JgcxDao();
					// ���°༶�����
					boolean rs = jgcxDao.cxBjpyxzpy(xszzSqshModelNew.getXn(), xszzSqshModelNew.getXq(), xszzSqshModelNew.getXmdm(), xszzSqshModelNew.getXh());
					if(rs){
						// ���½����ѯ��
						JgcxForm jgcxForm = new JgcxForm();
						jgcxForm.setSqid(xszzSqshModelNew.getSqid());
						jgcxForm.setTjzt("0");
						jgcxForm.setTjsj(" ");
						jgcxForm.setShzt(" ");
						jgcxDao.runUpdate(jgcxForm);
					}
				}
			return String.valueOf(result);
		}
		
	}

	// �������
	private void checkRskz(String gwid, String xmdm, String xh, String type)
			throws Exception {

		XmwhDao xmwhDao = new XmwhDao();
		Map<String, String> xmwhMap = xmwhDao.getDataById(xmdm);
		// �������Ʒ�Χ/����
		String rskzfw = xmwhMap.get("rsfpfs");
		String xn = xmwhMap.get("xn");
		String xq = xmwhMap.get("xq");

		RsszDao rsszDao = new RsszDao();
		// �����������Map
		Map<String, String> rsszMap = rsszDao.getRsszOne(xmdm, xh, rskzfw, xn,
				xq);

		String xzrs = rsszMap.get("zzme");
		// δ���þͲ�����
		if (StringUtil.isNull(xzrs)) {
			return;
		}

		String tgrs = null;

		if (RSKZFW_NJXY.equals(rskzfw)) { // �꼶ѧԺ
			tgrs = dao.getTgrsByNjxy(xn, xq, gwid, xmdm, xh);
		} else if (RSKZFW_NJZY.equals(rskzfw)) { // �꼶רҵ
			tgrs = dao.getTgrsByNjZy(xn, xq, gwid, xmdm, xh);
		} else if (RSKZFW_XY.equals(rskzfw)) { // ѧԺ
			tgrs = dao.getTgrsByXy(xn, xq, gwid, xmdm, xh);
		} else if (RSKZFW_CPZ.equals(rskzfw)) { // ������
			tgrs = dao.getTgrsByCpz(xn, xq, gwid, xmdm, xh);
		} else if (RSKZFW_BJ.equals(rskzfw)) { // �༶
			tgrs = dao.getTgrsByBj(xn, xq, gwid, xmdm, xh);
		} else { // ѧУ
			tgrs = dao.getTgrsByQx(xn, xq, gwid, xmdm, xh);
		}

		if (!(Integer.valueOf(tgrs) < Integer.valueOf(xzrs))) {
			if("sh".equals(type)){
				throw new SystemException(MessageKey.RSKZ_FAIL, tgrs);
			}else if("cx".equals(type)){
				throw new SystemException(MessageKey.RSKZ_FAIL_CANCEL, tgrs);
			}
		}

	}
	/**
	 * ������ȡ������Ϣ��ѧ����Ϣ
	 */
	public List<HashMap<String,String>> getPjxmXsxxList(String[] sqidArr) throws Exception{
		return dao.getPjxmXsxxList(sqidArr);
	}
	public HashMap<String,String> getPjxmXsxxMap(BzjlsqshModel t) throws Exception{
		return dao.getPjxmXsxxMap(t);
	}
	public List<HashMap<String,String>> getPjxmRsxx(String[] sqidArr) throws Exception{
		return dao.getPjxmRsxx(sqidArr);
	}
	public String getPjxmRsxxsx(String xmxx) throws Exception{
		return dao.getPjxmRsxxsx(xmxx);
	}
	public String getYsqXs(String xmxx) throws Exception{
		return dao.getYsqXs(xmxx);
	}
	
	public List<String> getXzjx() throws Exception{
		return dao.getXzjx();
	}


	/**
	 * 
	 * @����: ����ȡ����˲���
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-8-5 ����11:10:11
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception boolean ��������
	 */
	public boolean runCancel(BzjlsqshModel t, User user) throws Exception {

		BzjlsqshModel model = getModel(t.getSqid());

		// �Ȼ�ȡ����Ŀ�꽱��
		HashMap<String, String> shxx = ShlcDao.getDqjbByCondition(t.getSqid(),
				user.getUserName(), model.getSplc(), "cx");

		// �����Ŀ����͵�ǰ�û���Ŀ���벻һ�£��ж��Ƿ��������ͨ����¼
		String	checkXm = dao.checkXhsqsh(t.getXn(), t.getXq(), shxx.get("zd2"), t
					.getXh(),t.getSqid());

		if (Integer.valueOf(checkXm) > 0) {

			throw new SystemException(MessageKey.PJPY_FAIL);
		}

		boolean result = runCancel(user.getUserName(), t.getSqid(), model
				.getSplc(), t.getShid());

		// �ع�������е�������͵�ǰ����
		String tzhxmdm = shxx.get("zd2");
		BzjlsqshDao sqshdao = new BzjlsqshDao();
		String rskzxh = sqshdao.getRskzXh(tzhxmdm);
		String dqxmdm = shxx.get("zd2");
		String shzt = Constants.YW_SHZ;

		// �жϸ�λ�����Ƿ�С�ڵ��ڳ���Ŀ�꽱����������Ƽ���true ����յ������
		if (new Integer(shxx.get("xh")).intValue() <= new Integer(rskzxh)
				.intValue()) {
			tzhxmdm = "";
		}

		// �ع�������еĵ�������Ŀ
		sqshdao.updateSqjl(t.getSqid(), tzhxmdm, shzt, dqxmdm);

		// ����
		if (result) {
			Job job = Job.getJobInstance(t.getSqid(), t.getShid(),
					"pj_jxsh.do", "��������");
			MyJobsManager manager = new MyJobsImpl();
			manager.updateJob(job);
		}

		return result;
	}

	// ִ��ȡ����˲���
	private synchronized boolean runCancel(String shr, String ywid,
			String shlc, String gwid) throws Exception {
		/*
		 * -------------����Ƿ���Գ������----------- �Ⱦ���������ȷ���Լ��������������״̬ ������⣺
		 * 
		 * �ٲ�ͨ�� �� ͨ������ǰ����ļ�¼���Լ�����һ������δ��� �� �˻� �����ܳ��������쳣
		 */
		String lastShzt = dao.getLastShzt(shr, ywid, gwid);
		String nextSpgw = dao.getNextGwid(shlc, gwid);

		if (!BTG.equals(lastShzt) && StringUtil.isNull(nextSpgw)) {
			throw new SystemException(MessageKey.SYS_CANCEL_END);
		}

		boolean isFhtj = false;

		if (BTG.equals(lastShzt)) {
			// ��ͨ������ֱ�ӳ���
			isFhtj = true;
		} else if (TG.equals(lastShzt)) {
			// ɾ��������λ����һ���������ݣ�ɾ���ɹ��ɳ��������ܡ�
			isFhtj = dao.delNextDsjl(ywid, nextSpgw);
		} else {
			// �˻ػ��߲�֪��״̬��������
			throw new SystemException(MessageKey.SYS_AUD_CANCEL_FAIL);
		}

		// ���ϳ�������--���²�����λ��״̬Ϊ���������Ӳ�����λ��������
		if (isFhtj) {
			dao.updateShzt(ywid, gwid, CANCEL);
			return dao.insertDbjd(ywid, gwid);
		}

		return false;
	}

	/**
	 * 
	 * @����: ��ѯ��˰༶�б�
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-8-5 ����06:53:48
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return List<HashMap<String,String>> ��������
	 */
	public List<HashMap<String, String>> getShbjList(BzjlsqshModel t) {

		if (TJDW_BJ.equals(t.getTjdw())) {
			return dao.getBjInfo(t.getBmdm());
		} else if (TJDW_NJZY.equals(t.getTjdw())) {
			return dao.getBjListByNjzy(t.getBmdm());
		} else {
			return dao.getBjListByCpz(t.getBmdm());
		}

	}

	/**
	 * 
	 * @����: �������߼���ѯ����·��
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-8-6 ����01:45:58
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return String ��������
	 */
	public String getShqkSearchPath(BzjlsqshModel t) {

		String path = null;

		if (TJDW_BJ.equals(t.getTjdw())) {
			path = "pj_shqk_bj.do";
		} else if (TJDW_NJZY.equals(t.getTjdw())) {
			path = "pj_shqk_njzy.do";
		} else {
			path = "pj_shqk_cpz.do";
		}

		return path;
	}

	/**
	 * 
	 * @����: ��������ID���ش���˸�λID
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-8-8 ����10:24:32
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param sqid
	 * @return String ��������
	 * @throws
	 */
	public String getDsgw(String sqid) {

		if (StringUtil.isNull(sqid)) {
			return null;
		}

		return dao.getDsgw(sqid);
	}

	/**
	 * 
	 * @����: ������ͳ��getShqkInfo
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-5-23 ����09:58:10
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param user
	 * @return List<HashMap<String,Object>> ��������
	 */
	public Map<String, Object> getShqkInfo(User user, String xmdm) {

		// ����������
		int zrs = Integer.valueOf(dao.getSqrs(user, xmdm));
		// ����������
		List<HashMap<String, String>> shqkInfoList = dao
				.getShtjList(user, xmdm);
		// ����������ͨ��������ͨ���ʡ��������ͳ�����
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("zrs", zrs);

		List<HashMap<String, String>> shqkList = new ArrayList<HashMap<String, String>>();

		if (zrs == 0) {
			resultMap.put("zztgrs", "0");
			resultMap.put("zztgl", "0%");
		}

		for (int i = 0, n = shqkInfoList.size(); i < n; i++) {

			HashMap<String, String> map = new HashMap<String, String>();
			map.putAll(shqkInfoList.get(i));

			// double tgrs = Double.valueOf(shqkInfoList.get(i).get("tg"));
			// double bgtrs = Double.valueOf(shqkInfoList.get(i).get("btg"));
			// double dshrs = Double.valueOf(shqkInfoList.get(i).get("dsh"));
			double dcls = Double.valueOf(shqkInfoList.get(i).get("dcl"));
			double ycls = Double.valueOf(shqkInfoList.get(i).get("ycl"));
			DecimalFormat formater = new DecimalFormat("#.##%");

			// if (tgrs+bgtrs+dshrs == 0){
			// map.put("tgl", "0%");
			// map.put("btgl", "0%");
			// map.put("dshl", "0%");
			// } else {
			// double tgl = tgrs/(tgrs+bgtrs+dshrs);
			// double btgl =bgtrs/(tgrs+bgtrs+dshrs);
			// double dshl = dshrs/(tgrs+bgtrs+dshrs);
			//				
			// map.put("tgl", formater.format(tgl));
			// map.put("btgl", formater.format(btgl));
			// map.put("dshl", formater.format(dshl));
			// }

			if (dcls + ycls == 0) {
				map.put("dcll", "0%");
				map.put("ycll", "0%");
			} else {
				double dcll = dcls / (dcls + ycls);
				double ycll = ycls / (dcls + ycls);

				map.put("dcll", formater.format(dcll));
				map.put("ycll", formater.format(ycll));
			}

			shqkList.add(map);

			// if (i == n-1){
			// //����ͨ����
			// double zztgl = tgrs/zrs;
			// resultMap.put("zztgrs",shqkInfoList.get(i).get("tg"));
			// resultMap.put("zztgl", formater.format(zztgl));
			// }
		}

		resultMap.put("shqkList", shqkList);
		return resultMap;
	}

	/**
	 * 
	 * @����: ���ͳ��ѧ���б�
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-8-12 ����02:26:57
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getStudentsFromShtj(BzjlsqshModel model,
			User user) throws Exception {
		return dao.getStudentsFromShtj(model, user);
	}

	/**
	 * 
	 * @����: ���ָ����Ŀ�Ƿ���������˼�¼
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-8-14 ����09:06:30
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xmdm
	 * @return boolean ��������
	 * @throws
	 */
	public boolean isExistsFlowData(String xmdm) {

		return Integer.valueOf(dao.getFlowData(xmdm)) > 0;
	}

	public boolean cancleRecord(String ywid, String lcid) throws Exception {
		return shlc.firstStepCancle(ywid, lcid);
	}

	public String cxshnew(String ywid, BzjlsqshModel model, User user)
			throws Exception {

		ShlcInterface service = new CommShlcImpl();

		// �ж��������
		HashMap<String, String> shxx = ShlcDao.getDqjbByCondition(ywid, user
				.getUserName(), model.getSplc(), "cx");

		// ���ǰһ�������Ŀ����
		String tzhxmdm = shxx.get("zd2");
		BzjlsqshDao sqshdao = new BzjlsqshDao();
		String rskzxh = sqshdao.getRskzXh(tzhxmdm);
		String dqxmdm = shxx.get("sjxmdm"); // �ϼ������Ŀ����
		String shzt = Constants.YW_SHZ;

		// �����Ŀ����͵�ǰ�û���Ŀ���벻һ�£��ж��Ƿ��������ͨ����¼
		String checkXm = dao.checkXhsqsh(model.getXn(), model.getXq(), shxx
					.get("zd2"), model.getXh(),model.getSqid());

		if (Integer.valueOf(checkXm) > 0) {

			throw new SystemException(MessageKey.PJPY_FAIL);
		}
		
		//�����ǰ��¼����˲�ͨ����������˼�����ڵ��ڿ��Ƽ���
		int shxxXh = new Integer(shxx.get("xh"));
		if(Constants.SH_BTG.equals(shxx.get("shzt"))&& shxxXh > 1 && shxxXh >= new Integer(rskzxh).intValue()){
			String spgw = new ShlcDao().getBeforeGwid(shxx.get("splc"), shxx.get("gwid"));
			checkRskz(spgw,dqxmdm,model.getXh(), "cx");
		}
		
		if (new Integer(shxx.get("xh")).intValue() <= new Integer(rskzxh)
				.intValue()) {
			tzhxmdm = "";
		}


		String message = service.runCancelNew(user.getUserName(), model.getShid(), model
				.getSplc());
		
		// �ع�������еĵ�������Ŀ
		sqshdao.updateSqjl(ywid, tzhxmdm, shzt, dqxmdm);
		
		return message;

	}

	/**
	 * @throws Exception  
	 * @����:�㽭�����Ի����������ϸ
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2015-5-26 ����03:57:52
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getViweShmx(BzjlsqshModel model, User user) throws Exception {
		return dao.getViweShmx(model,user);
	}
	
	
	
	//�㽭��ѧ���Ի���ˣ�����ʲô�Ķ����ж�
	public String saveZdPlsh(BzjlsqshModel t, User user) throws Exception {
		
		List<HashMap<String, String>> shjgList = dao.getZdshjgList(t,user);
		if(null==shjgList||shjgList.size()==0){
			return MessageUtil.getText("��ѯ���Ϊ��");
		}
		
		String[] ids = new String[shjgList.size()];
		String[] gwids = new String[shjgList.size()];
		String[] xhs = new String[shjgList.size()];
		String[] splcs = new String[shjgList.size()];
		
		for (int i = 0; i < shjgList.size(); i++) {
			ids[i] = shjgList.get(i).get("sqid");
			gwids[i] = shjgList.get(i).get("gwid");
			xhs[i] = shjgList.get(i).get("xh");
			splcs[i] = shjgList.get(i).get("lcid");
		}
		
		List<String> failXhs = new ArrayList<String>();
		for (int i = 0, n = ids.length; i < n; i++) {
			BzjlsqshModel model = new BzjlsqshModel();
			model.setSqid(ids[i]);
			//�����������ȡ�ϼ���˵����Ľ���
			String gwxh = dao.getXh(splcs[i], gwids[i]);
			HashMap<String, String> tmpMap  = new HashMap<String,String>();
//			if(Integer.parseInt(gwxh)>1){
//				//�ϼ���˸�λ���
//				String sjgwxh = String.valueOf(Integer.parseInt(gwxh)-1);
//				String sjgwid = dao.getSjGwid(splcs[i], sjgwxh);
//				 tmpMap = shlc.getShxxByCondition(ids[i],
//						sjgwid);
//			}else{
			 tmpMap = shlc.getShxxByCondition(ids[i],gwids[i]);
			//}
			BzjlsqshModel s = dao.getModel(model);
			if (tmpMap.get("xjzd2") == null || tmpMap.get("xjzd2").equals("")) {
				model.setXmdm(s.getXmdm());
				model.setPdjx(s.getDqxmdm());
			} else {
				model.setXmdm(tmpMap.get("xjzd2"));
				model.setPdjx(s.getDqxmdm());
			}

			model.setGwid(gwids[i]);
			model.setShyj(t.getShyj());
			model.setShjg(t.getShzt());
			model.setThgw(null);
			model.setSplc(tmpMap.get("lcid"));
			model.setXh(s.getXh());
			model.setXn(s.getXn());
			model.setXq(s.getXq());
			model.setYlzd5(s.getYlzd5()); // ����id

			String isSuccess = runAudingNotCheck(model, user);
			if (!isSuccess.equals("true")) {
				failXhs.add(xhs[i]);
			}
		}

		JSONArray json = JSONArray.fromObject(failXhs);
		if (failXhs.isEmpty()) {
			return MessageUtil.getText(MessageKey.SYS_AUD_SUCCESS);
		} else {
			return MessageUtil.getText(MessageKey.SYS_PLSH_FAIL, json
					.toString());
		}
	}

	/** 
	 * @����:���ͳ�ƻ�ͳ��
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2015-5-28 ����01:40:03
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getHjtjb(BzjlsqshModel model) throws Exception {
		return dao.getHjtjb(model);
	}
	/**
	 * 
	 * @����:�����Ի��������
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2015-6-4 ����09:50:14
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * String �������� 
	 * @throws
	 */
	public String runAudingNotCheck(BzjlsqshModel t, User user) throws Exception{
		
		// ��˲���Model��ʼ��
		ShxxModel model = new ShxxModel();
		model.setShlc(t.getSplc());
		model.setShr(user.getUserName());
		model.setShyj(t.getShyj());
		model.setShzt(t.getShjg());
		model.setThgw(t.getThgw());
		model.setGwid(t.getGwid());
		model.setYwid(t.getSqid());
		
		model.setSqrid(t.getXh());
		model.setTzlj("pj_jxsh.do");
		model.setTzljsq("pj_pjxmsq.do");
		model.setZd1("��������");
		model.setZd2(t.getPdjx());
		XmwhModel dcForm = new XmwhModel();
		XmwhDao xmdao = new XmwhDao();
		dcForm.setXmdm(t.getPdjx());
		dcForm = xmdao.getModel(dcForm);
		model.setZd3(dcForm.getXmmc());
		
		
		//�жϵ�ǰ�����Ƿ���������л�ͨ����¼
		 String checkXm = dao.checkXhsqsh(t.getXn(), t.getXq(), t.getPdjx(), t.getXh(),t.getSqid());
			
			
		if(Integer.valueOf(checkXm) > 0){
			throw new SystemException(MessageKey.PJPY_FAIL);
			
		}
		
		boolean result = false;
		try {
			
			//�ж��������
			HashMap<String,String> shxx =ShlcDao.getDqjbByCondition(t.getSqid(), user.getUserName(), t.getSplc(), "sh");
			//���ǰһ�������Ŀ����
			String tzhxmdm = "";
			BzjlsqshDao sqshdao = new BzjlsqshDao();
			String rskzxh = sqshdao.getRskzXh(t.getPdjx());
			
			//������ͨ����������˼�����ڵ��ڿ��Ƽ��𣬸��µ�������Ŀ����
			if(Constants.SH_TG.equals(t.getShjg()) && (shxx.get("xh")!=null)
					&& new Integer(shxx.get("xh")).intValue()>=new Integer(rskzxh).intValue()){
				tzhxmdm = t.getPdjx();
			}
			
			String zhzt = shlc.runAuditing(model);
			BzjlsqshModel upForm = new BzjlsqshModel();
			upForm.setSqid(t.getSqid());
			upForm.setShzt(zhzt);
			
			upForm.setTzhxmdm(tzhxmdm);
			upForm.setDqxmdm(t.getPdjx());
			result = dao.runUpdate(upForm, t.getSqid());
			if(result && zhzt.equals(Constants.SH_TG)){
				//���뵽�����
				BzjlsqshModel viewModel = dao.getModel(t.getSqid());
				//BeanUtils.copyProperties(pjjgModel, viewModel);
				XmwhModel xmwhModel = new XmwhDao().getModel(t.getPdjx());
				
				BzjljgModel pjjgModel = new BzjljgModel();
				pjjgModel.setSjly(SQSH);
				pjjgModel.setId(viewModel.getSqid());
				pjjgModel.setXmdm(t.getPdjx());
				pjjgModel.setXmje(xmwhModel.getXmje());
				pjjgModel.setXmmc(xmwhModel.getXmmc());
				pjjgModel.setXn(viewModel.getXn());
				if(!CsszService.XQKG.equalsIgnoreCase(viewModel.getXq())){
					pjjgModel.setXq(viewModel.getXq());
				}
				pjjgModel.setSqly(viewModel.getSqly());
				pjjgModel.setSqsj(viewModel.getSqsj());
				pjjgModel.setXh(viewModel.getXh());
				pjjgModel.setXzdm(xmwhModel.getXzdm());
				pjjgModel.setLxdm(xmwhModel.getLxdm());
				pjjgModel.setLylcywid(t.getSqid());
				//ѧ�������༶
				ZcfsService zcfsServcie = new ZcfsService();
				HashMap<String, String> cpbjxx = zcfsServcie.getCpbjListByXh(t.getXh(), t.getXn(), t.getXq());
				pjjgModel.setCpnj(cpbjxx.get("nj"));
				pjjgModel.setCpxymc(cpbjxx.get("xymc"));
				pjjgModel.setCpzymc(cpbjxx.get("zymc"));
				pjjgModel.setCpbjmc(cpbjxx.get("bjmc"));
				pjjgModel.setCpxydm(cpbjxx.get("xydm"));
				pjjgModel.setCpzydm(cpbjxx.get("zydm"));
				pjjgModel.setCpbjdm(cpbjxx.get("bjdm"));
				pjjgModel.setYlzd5(t.getYlzd5()); // ����id
				BzjljgDao pjjgDao = new BzjljgDao();
				try {
					pjjgDao.runInsert(pjjgModel);
				} catch (Exception e) {
					logger.debug("������˲�����ʽ��ʧ�ܣ�"+pjjgModel.getId());
				}
			}
		
		} catch (Exception e) {
			return e.getMessage();
			
			/*e.printStackTrace();
			return false;*/
		}
		
		return String.valueOf(result);
	}
	
	/** 
	 * @����:������Ϣ������ѯ
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2015-11-20 ����04:04:47
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * void �������� 
	 * @throws 
	 */
	
	public HashMap<String, String> getPjInfo(String xh,String xn,String xq) {
		
		if (StringUtil.isNull(xh)) {
			logger.error("xh is null !");
			throw new NullPointerException();
		}
		
		return dao.getPjInfo(xh, xn, xq);
	}
	
	/** 
	 * @����:������Ŀ�����ȡ������
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-4-25 ����09:00:39
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xmdm
	 * @return
	 * String �������� 
	 * @throws 
	 */
	public String getXmje(String xmdm){
		return dao.getXmje(xmdm);
	}
	
	//���������ж�
	public boolean isExistsFlowDatajtpj(String xmdm) {

		return Integer.valueOf(dao.getFlowDataJtpj(xmdm)) > 0;
	}
	
	/**
	 * 
	 * @����: �ж��Ƿ񲻿ɼ��
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-8-10 ����03:43:14
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xmdm
	 * @param xn
	 * @param xq
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean checkIsNotbkjd(String xmdm,String xn,String xq,String xh){
		return dao.checkIsNotbkjd(xmdm, xn, xq, xh);
	}
	
	/**
	 * 
	 * @����: ��ȡ���ɼ����Ŀ����
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-8-10 ����03:54:15
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getbkjdMc(String xmdm){
		return dao.getbkjdMc(xmdm);
	}
	
	/**
	 * 
	 * @����: ��ȡ���޿���ͳɼ�
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-10-11 ����02:53:01
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xn
	 * @param xq
	 * @param bjdms
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getXsdmBxkMinCj(String xn,String xq,String bjdms){
		
		String[] bjdm = bjdms.split(",");
		String params = "";
		for(int i = 0;i<bjdm.length;i++){
			params +="'"+bjdm[i]+"',";
		}
		if(params.length()>0){
			params=params.substring(0, params.length()-1);
		}
		
		return dao.getXsdmBxkMinCj(xn, xq, params);
	}
	
	
	/**
	 * 
	 * @����: �����������������
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2016��10��15�� ����10:51:24
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param params
	 * @return
	 * @throws SQLException
	 * boolean �������� 
	 * @throws
	 */
	public boolean batchInsertCheckResult(List<String[]> params) throws SQLException{
		dao.deleteCheckResult(params);
		return dao.batchInsertCheckResult(params);
	}
	
	/**
	 * 
	 * @����: ��ѯ���������
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2016��10��15�� ����1:38:09
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xn
	 * @param xq
	 * @param bjdmArr
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getCheckResultList(String xn,String xq,String[] bjdmArr){
		return dao.getCheckResultList(xn, xq, bjdmArr);
	}
	
	/**
	 * @��������ȡӢ��ɼ�
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2017��3��21�� 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xn
	 * @param xq
	 * @param bjdms
	 * @return
	 * List<HashMap<String,String>> ��������
	 */
	public List<HashMap<String, String>> getEngScore_10279(String xn,String xq,String bjdms){
		return dao.getEngScore_10279(xn, xq, bjdms);
	}
	
	/**
	 * @�������������ܱ�
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2017��3��24�� 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * File ��������
	 */
	public File getHzbPrint(SearchModel searchmodel)throws Exception {
		//���ұ�����һ��xn��һ��xmmc
		String[] xns=searchmodel.getSearch_tj_xn();
		String[] xmmcs=searchmodel.getSearch_tj_pjsqxm();
		String xn=(xns==null?"":xns[0]);
		String xmmc=(xmmcs==null?"":xmmcs[0]);
		List<HashMap<String,String>> wshlist=dao.getwshList_10279(xn,xmmc);
		Map<String,Object> data = new HashMap<String,Object>();
		data.put("wshlist", wshlist);
		data.put("xn", xn);
		data.put("xmmc", xmmc);
		File excelFile = FreeMarkerUtil.getExcelFile(data,"classpath://templates//pjpy//excel", "pjpy_hzb_"+"10279"+".xml", xn+"ѧ����ܱ�");
		return excelFile;
	}

	/**
	 * @����:�㽭ͬ�ÿƼ�ְҵ����ѧԺ����ȡѧ���ɼ����ܵ���������
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��4��11�� ����3:44:36
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getXscjhzList(BzjlsqshModel model, User user) throws Exception {
		return dao.getXscjhzList(model,user);
	}


	/**
	 *  �������Ž��������ύ�����쳣����.
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2017-11-22 17:57
	 * @param
	 * @return java.util.Map<java.lang.String,java.lang.String>
	 * @throw Exception
	 */
    public Map<String,String> exceptionDataResolve() throws Exception {

		boolean success =true;
		String message = "����ɹ���";
		Map resultMap = new HashMap<String,Object>();
		List<HashMap<String,String>> exceptionDataList = dao.getExceptionDataList();
		if(exceptionDataList.size() != 0){
			for(HashMap<String,String> edMap:exceptionDataList){
				success = shlc.runSubmit(edMap.get("sqid"), edMap.get("splc"), edMap.get("xh"), "pj_jxsh.do", "pj_pjxmsq.do");
				if(!success){
					message = "����ʧ�ܣ�";
				}
			}
		}else{
			message = "���쳣���ݣ�";
		}
		resultMap.put("success",success);
		resultMap.put("message",message);
		return resultMap;
    }

    /**
     * @description	�� ��ȡѧ�����ཱ������
     * @author 		�� ������1282��
     * @date 		��2018-1-8 ����10:04:49
     * @return
     * @throws IOException
     */
    public List<File> getXsglmc(BzjlsqshModel t) throws Exception{
    	List<File> fileList = new ArrayList<File>();
    	List<HashMap<String,String>> list = dao.getXsglmdList(t);
    	String bjdm = null;
    	List<HashMap<String,String>> arrList = null;
    	if(list.size() > 0){
    		for(HashMap<String,String> map : list){
    			//��һ��ѭ����ȥ��ʱ�򣬳�ʼ���༶����
    			if(StringUtils.isNull(bjdm)){
    				bjdm = map.get("bjdm");
    				arrList = new ArrayList<HashMap<String,String>>();
    				setKcAndCj(map);
    				arrList.add(map);
    			}else{
    				//���Ϊͬһ�༶��ѧ��������map�����ҷ���ͬһ���༶��list
    				if(map.get("bjdm").equals(bjdm)){
    					setKcAndCj(map);
    					arrList.add(map);
    				}else{//�����Ϊͬһ�༶��ѧ��������excel�ļ������ҷ���FileList
    					File file = getXshjByBjExl(arrList);
    					fileList.add(file);
    					bjdm = map.get("bjdm");
    					arrList = new ArrayList<HashMap<String,String>>();
    					setKcAndCj(map);
        				arrList.add(map);
    				}
    			}
    		}
    		if(null != arrList && arrList.size() > 0){
    			File file = getXshjByBjExl(arrList);
    			fileList.add(file);
    		}
    	}
    	return fileList;
    }

    /**
     * @description	�� ��ȡѧ����excel�԰༶Ϊ��λ
     * @author 		�� ������1282��
     * @date 		��2018-1-8 ����02:59:15
     * @param list
     * @param bjxx
     * @return
     * @throws IOException
     * @throws WriteException
     */
    public File getXshjByBjExl(List<HashMap<String,String>> list) throws Exception{
    	HashMap<String,String> titleMap = list.get(0);
    	String xn = titleMap.get("xn");
    	String xqmc = titleMap.get("xqmc");
    	String xymc = titleMap.get("xymc");
    	String bjmc = titleMap.get("bjmc");
    	String bzr = titleMap.get("bzr");
    	String bjdm = titleMap.get("bjdm");
    	String xq = titleMap.get("xq");


		String fileName = bjmc + ".xls";
		File file = new File(System.getProperty("java.io.tmpdir"),fileName);
		if(!file.exists()){
			file.createNewFile();
		}
			 WritableWorkbook  wwb = Workbook.createWorkbook(file);
			 WritableSheet sheet = wwb.createSheet(bjmc, 0);

			 WritableFont tileFont = new WritableFont(WritableFont.ARIAL, 16, WritableFont.BOLD, false,UnderlineStyle.NO_UNDERLINE);//���ñ�������
			 WritableFont headFont = new WritableFont(WritableFont.ARIAL, 12, WritableFont.BOLD, false,UnderlineStyle.NO_UNDERLINE);//���ñ�ͷ����
			 WritableFont bodyFont = new WritableFont(WritableFont.ARIAL, 10,WritableFont.NO_BOLD,false,UnderlineStyle.NO_UNDERLINE);//������������

			 WritableCellFormat title_cf = new WritableCellFormat(tileFont);//���ñ��ⵥԪ������
			 WritableCellFormat head_cf = new WritableCellFormat(headFont);//�������ı�ͷ����
			 WritableCellFormat body_cf = new WritableCellFormat(bodyFont);//�������ĵ�Ԫ������
			 //WritableCellFormat title_secondary = new WritableCellFormat(bodyFont);//���õڶ������ⵥԪ������
			 WritableCellFormat remark_cf = new WritableCellFormat(bodyFont);//��ע
			 WritableCellFormat remarkk_cf = new WritableCellFormat(bodyFont);//��ע

			 title_cf.setAlignment(jxl.format.Alignment.CENTRE);//���ñ��ⵥԪ�����
			 title_cf.setVerticalAlignment(VerticalAlignment.CENTRE);
			 title_cf.setWrap(true);
			 //title_cf.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK);//���ñ��ⵥԪ��߿�


			 head_cf.setAlignment(jxl.format.Alignment.CENTRE);
			 head_cf.setVerticalAlignment(VerticalAlignment.CENTRE);//���ñ�ͷˮƽ����
			 head_cf.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK);
			 head_cf.setWrap(true);
			 
			 
			 body_cf.setAlignment(jxl.format.Alignment.CENTRE);//�������ĵ�Ԫ�����
			 body_cf.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK);//�������ĵ�Ԫ��߿�
			 body_cf.setVerticalAlignment(VerticalAlignment.CENTRE);
			 body_cf.setWrap(true);
			 remark_cf.setWrap(true);
			 remarkk_cf.setWrap(true);
			 
			 remark_cf.setAlignment(jxl.format.Alignment.LEFT);//���ñ�ע��Ԫ�����
			 remark_cf.setBorder(jxl.format.Border.NONE, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK);//���ñ�ע��Ԫ��߿�
				
			 remarkk_cf.setAlignment(jxl.format.Alignment.RIGHT);//���ñ�ע��Ԫ�����
			 remarkk_cf.setBorder(jxl.format.Border.NONE, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK);//���ñ�ע��Ԫ��߿�
			 
			 //title_secondary.setAlignment(jxl.format.Alignment.LEFT);
			 
			 //���������и�
			sheet.setRowView(0, 600, false);
			
			sheet.setRowView(1, 400, false);
			
			sheet.setRowView(2, 650, false);
			 
			 //���ø����п�
			 sheet.setColumnView(0, 6);
			 sheet.setColumnView(1, 8);
			 sheet.setColumnView(2, 6);
			 sheet.setColumnView(3, 6);
			 sheet.setColumnView(4, 6);
			 sheet.setColumnView(5, 6);
			 sheet.setColumnView(6, 7);
			 sheet.setColumnView(7, 7);
			 sheet.setColumnView(8, 7);
			 sheet.setColumnView(9, 7);
			 sheet.setColumnView(10, 7);
			 sheet.setColumnView(11, 7);
			 sheet.setColumnView(12, 7);
			 sheet.setColumnView(13, 7);
			 sheet.setColumnView(14, 7);
			 sheet.setColumnView(15, 12);
			 sheet.setColumnView(16, 8);
			 sheet.setColumnView(17, 8);
			 
			 sheet.mergeCells(0, 0, 17, 0);
			 Label label_title0 = new Label(0, 0, "ѧ���������������",title_cf);
			 sheet.addCell(label_title0);
			 
			 sheet.mergeCells(0, 1, 17, 1);
			 Label label_title1 = new Label(0,1,xn+"ѧ��      "+xqmc+"     "+xymc+"     �����Σ�"+(bzr==null?"":bzr)+"     �༶��"+bjmc,head_cf);
			 sheet.addCell(label_title1);
			 
			 sheet.mergeCells(0, 2, 0, 3);
			 sheet.mergeCells(1, 2, 1, 3);
			 sheet.mergeCells(2, 2, 2, 3);
			 sheet.mergeCells(3, 2, 3, 3);
			 sheet.mergeCells(4, 2, 4, 3);
			 
			 Label label_head0 = new Label(0, 2, "���",head_cf);
			 Label label_head1 = new Label(1, 2, "����",head_cf);
			 Label label_head2 = new Label(2, 2, "�Ա�",head_cf);
			 Label label_head3 = new Label(3, 2, "����ְ��",head_cf);
			 Label label_head4 = new Label(4, 2, "���еȵ�",head_cf);
			 sheet.addCell(label_head0);
			 sheet.addCell(label_head1);
			 sheet.addCell(label_head2);
			 sheet.addCell(label_head3);
			 sheet.addCell(label_head4);
			 
			 sheet.mergeCells(5, 2, 14, 2);
			 Label label_head5 = new Label(5, 2, "����ѧϰ�ɼ�",head_cf);
			 sheet.addCell(label_head5);
			 
			 //ȡ��10���γ̳ɼ�����
			 List<HashMap<String,String>> kcMapList = dao.getCjmcList(xn, xq, bjdm);
			 
			 List<String> kcmcList = new ArrayList<String>();
			 
			 //����ð༶ѧ���Ŀ��Կγ����ƴ���
			 if(null != kcMapList && kcMapList.size() > 0){
				 //��ȡȡ�õĿγ�����
				 int num = kcMapList.size();
				 
				 int balance = 10 - num;
				 
				 for(int i = 0;i < num; i++){
					 Label cjLabel = new Label(5+i,3, kcMapList.get(i).get("kcmc"),head_cf);
					 sheet.addCell(cjLabel);
					 kcmcList.add(kcMapList.get(i).get("kcmc"));
				 }
				 
				 //�������10���γ̣���ȫ�γ����Ƶ�Ԫ��
				 if(balance > 0){
					 for(int p = 0; p < balance; p++){
						 Label cjLabel = new Label(14-p,3, "",body_cf);
						 sheet.addCell(cjLabel);
					 }
				 }
			 }else{
				 for(int i = 0; i < 10; i++){
					 Label cjLabel = new Label(5+i,3, "",body_cf);
					 sheet.addCell(cjLabel);
				 }
			 }
			 
			 sheet.mergeCells(15, 2, 15, 3);
			 sheet.mergeCells(16, 2, 16, 3);
			 sheet.mergeCells(17, 2, 17, 3);
			 Label label_head15 = new Label(15, 2 ,"�걨���",head_cf);
			 Label label_head16 = new Label(16, 2 ,"ϵ�������",head_cf);
			 Label label_head17 = new Label(17, 2 ,"ѧ�������",head_cf);
			 sheet.addCell(label_head15);
			 sheet.addCell(label_head16);
			 sheet.addCell(label_head17);
			 			 
			int size = list.size();
			if(size > 0){
				 for(int i = 0;i<size;i++){
					 HashMap<String, String> map = list.get(i);
					 Label sx = new Label(0, 4+i,String.valueOf(i+1), body_cf);
					 Label xm = new Label(1, 4+i, map.get("xm"), body_cf);
					 Label xb = new Label(2, 4+i, map.get("xb"), body_cf);
					 Label zw = new Label(3, 4+i, map.get("zwmc"), body_cf);
					 Label cxdj = new Label(4, 4+i, map.get("cxdjmc"), body_cf);
					 
					 
					 sheet.addCell(sx);
					 sheet.addCell(xm);					 
					 sheet.addCell(xb);
					 sheet.addCell(zw);
					 sheet.addCell(cxdj);
					 
					 if(kcmcList.size() > 0){
						 int num = kcmcList.size();						 
						 int balance = 10 - num;
						 
						 //�жϿγ������Ƿ���ѧ���ĳɼ��γ��������
						 for(int j = 0;j < kcmcList.size(); j++){
							 if(map.containsKey(kcmcList.get(j))){
								 Label cjLabel = new Label(5+j,4+i,map.get(kcmcList.get(j)), body_cf);
								 sheet.addCell(cjLabel);
								 continue;
							 }else{
								 Label cjLabel = new Label(5+j,4+i,"", body_cf);
								 sheet.addCell(cjLabel);
							 }
						 }
						 
						 //�������10���γ�
						 if(balance > 0){
							 for(int p = 0; p < balance; p++){
								 Label cjLabel = new Label(14-p,4+i, "",body_cf);
								 sheet.addCell(cjLabel);
							 }
						 }
					 }else{
						 for(int p = 0;p<10;p++){
							 Label cjLabel = new Label(5+p,4+i, "",body_cf);
							 sheet.addCell(cjLabel);
						 }
					 }
					 
					 //������Ŀ
					 Label xmmc = new Label(15, 4+i, map.get("xmmc"), body_cf);
					 sheet.addCell(xmmc);
					 
					 //ϵ�������
					 Label xshyj = new Label(16, 4+i, "", body_cf);
					 sheet.addCell(xshyj);
					 
					 //ѧ�������
					 Label xscyj = new Label(17, 4+i, "", body_cf);
					 sheet.addCell(xscyj);
					 
					 //��ע_1
					 sheet.mergeCells(0, 4+size+2, 17, 4+size+2);
					 Label bz_1 = new Label(0, 4+size+2, "ע��1�����øֱʰ����Ҫ��ȫ����д�� 2��У������ѧ�ɲ��еȵڱ�׼Ϊ���㣬����ƽ���ɼ���80�����ϣ�����75������", remark_cf);
					 sheet.addCell(bz_1);
					 
					 //��ע_2
					 sheet.mergeCells(0, 4+size+3, 17, 4+size+3);
					 Label bz_2 = new Label(0, 4+size+3, "3�����ϸ񰴹涨ָ��ִ�У�       4����д�걨�����ͬҪ��Լ��С�", remark_cf);
					 sheet.addCell(bz_2);
					 
					 //��ע_3
					 sheet.mergeCells(0, 4+size+4, 17, 4+size+4);
					 Label bz_3 = new Label(0, 4+size+4, "ѧ��������", remarkk_cf);
					 sheet.addCell(bz_3);
					 //sheet.addCell(xscyj);
					 
				 }
				 wwb.write();
				 wwb.close();
			 }
			 return file;			 
	}
    
    /**
     * @description	�� ���ÿγ̺ͳɼ��б�ȡ10���γ����ƺͳɼ���
     * @author 		�� ������1282��
     * @date 		��2018-1-8 ����12:00:50
     * @return
     */
    public HashMap<String,String> setKcAndCj(HashMap<String,String> map){
    	//��ȡ�γ�����
    	String mchz = map.get("kchz");
    	//��ȡ�ɼ��б�
    	String cjhz = map.get("cjhz");
    	//����ж���ɼ������
    	if(StringUtils.isNotNull(mchz) && mchz.indexOf(";") != -1){
    		String[] mchzArr = mchz.split(";");
    		String[] cjhzArr = cjhz.split(";");
    		for(int i = 0;i < mchzArr.length && i < 10; i++){
    			map.put(mchzArr[i],cjhzArr[i]);
    		}
    	}
    	//���ֻ��һ���ɼ������
    	else if(StringUtils.isNotNull(mchz) && mchz.indexOf(";") == -1){
    		map.put(map.get("kchz"),map.get("cjhz"));
    	}
    	return map;   	
    }
    
    /**
     * @description	�� ��֤ѧ���������������
     * @author 		�� ������1282��
     * @date 		��2018-1-9 ����02:30:45
     * @param t
     * @return
     * @throws NoSuchMethodException 
     * @throws InvocationTargetException 
     * @throws IllegalAccessException 
     * @throws SecurityException 
     * @throws IllegalArgumentException 
     */
    public boolean yzxsglmddc(BzjlsqshModel t) throws Exception{
    	List<HashMap<String,String>> list = dao.getXsglmdList(t);
    	return list.size() > 0;
    }
    
    public Map<String,Object> getMapList(){
    	return new HashMap<String,Object>();
    }
    
    /**
     * @throws Exception 
     * 
     * @����: �������Ϣ
     * @���ߣ�����Դ[���ţ�1206]
     * @���ڣ�2018-3-19 ����11:49:21
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param model
     * @return
     * boolean �������� 
     * @throws
     */
    public boolean saveHjxx(BzjlsqshModel model) throws Exception{
    	String xh = model.getXh();
    	String sqid = model.getSqid();
    	CsszModel cssz = new CsszService().getModel();
    	String xn = cssz.getXn();
    	String xq = cssz.getXq();
    	if(StringUtils.isNotNull(sqid)){
    		BzjlsqshModel myForm = new BzjlsqshService().getModel(sqid);
    		xn = myForm.getXn();
    		xq = myForm.getXq();
    	}
    
    	List<String[]> params = new ArrayList<String[]>();
    	String[] ids = model.getIds(); ;
		for (int i = 0; i < ids.length; i++) {
			params.add(new String[]{xh,xn,xq,ids[i]});
		}
		dao.delHjxx(params);
		return dao.saveHjxx(params) ;
    	
    }
    
    /**
     * 
     * @����:TODO(������һ�仰�����������������)
     * @���ߣ�����Դ[���ţ�1206]
     * @���ڣ�2018-3-19 ����04:26:45
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @return
     * List<HashMap<String,String>> �������� 
     * @throws
     */
    public List<HashMap<String,String>> getHjxxList(String xh,String xn,String xq){
    	return dao.getHjxxList(xh, xn, xq);
    }
}
