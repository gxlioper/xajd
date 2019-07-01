package xgxt.comm;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.search.SearchDAO;
import xgxt.comm.xml.XMLReader;
import xgxt.form.CommanForm;
import xgxt.form.RequestForm;
import xgxt.pjpy.comm.pjpy.PjpyCommService;
import xgxt.studentInfo.dao.StuInfoDAO;
import xgxt.studentInfo.service.XsxxglService;
import xgxt.studentInfo.ynys.XsxxYnysService;
import xgxt.utils.FormModleCommon;
import xgxt.xsgygl.GyglTyDAO;
import xgxt.xsgygl.GyglTyService;

import common.Globals;
import common.GlobalsVariable;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ҳ���ʼ���б���
 * </p>
 * <p>
 * Copyright: Copyright (c) 2010
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author ΰ�����
 * @version 1.0
 */

public class CommList {

	/**
	 * ��������ҳ���ʼ���б�
	 * 
	 * @param form
	 * @param request
	 * @author luojw
	 * @throws Exception
	 */
	public void setList(CommForm model, RequestForm rForm,
			HttpServletRequest request) throws Exception {

		// ����ģ��
		String gnmk = rForm.getGnmk();

		// =====================ͨ��=============================
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);// �꼶ѧԺרҵ�༶
		FormModleCommon.setNdXnXqList(request);// ���ѧ��ѧ��
		FormModleCommon.requestSetList(new String[] { "bm", "zzmm","yjbm" }, request);// �Զ���(Ŀǰ�����Ŵ���,������ò)
		setCommOptionList(model, rForm, request);
		// =====================ͨ��OVER=============================

		// =====================��������ģ��=============================
		if ("xtwh".equalsIgnoreCase(gnmk)) {// ϵͳά��
			setXtwhOptionList(model, rForm, request);
		} else if ("xszz".equalsIgnoreCase(gnmk)) {// ѧ������
			setXszzOptionList(model, rForm, request);
		} else if ("gygl".equalsIgnoreCase(gnmk)) {// ��Ԣ����
			setGyglOptionList(model, rForm, request);
		} else if ("pjpy".equalsIgnoreCase(gnmk)) {// ��������
			setPjpyOptionList(model, rForm, request);
		} else if ("xsxx".equalsIgnoreCase(gnmk)) {// ѧ����Ϣ
			setXsxxOptionList(model, rForm, request);
		} else if ("jxgl".equalsIgnoreCase(gnmk)) {// ��ѵ����
			setJxglOptionList(model, rForm, request);
		}
		// =====================��������ģ��OVER=============================
	}
	
	/**
	 * ��������ҳ���ʼ���б�
	 * 
	 * @param form
	 * @param request
	 * @author 
	 * @throws Exception
	 */
	public void setAllList(CommForm model, RequestForm rForm,
			HttpServletRequest request) throws Exception {

		// ����ģ��
		String gnmk = rForm.getGnmk();

		// =====================ͨ��=============================
		FormModleCommon.setAllNjXyZyBjListForFdyBzr(request);// �꼶ѧԺרҵ�༶
		FormModleCommon.setNdXnXqList(request);// ���ѧ��ѧ��
		FormModleCommon.requestSetList(new String[] { "bm", "zzmm" }, request);// �Զ���(Ŀǰ�����Ŵ���,������ò)
		setCommOptionList(model, rForm, request);
		// =====================ͨ��OVER=============================

		// =====================��������ģ��=============================
		if ("xtwh".equalsIgnoreCase(gnmk)) {// ϵͳά��
			setXtwhOptionList(model, rForm, request);
		} else if ("xszz".equalsIgnoreCase(gnmk)) {// ѧ������
			setXszzOptionList(model, rForm, request);
		} else if ("gygl".equalsIgnoreCase(gnmk)) {// ��Ԣ����
			setGyglOptionList(model, rForm, request);
		} else if ("pjpy".equalsIgnoreCase(gnmk)) {// ��������
			setPjpyOptionList(model, rForm, request);
		} else if ("xsxx".equalsIgnoreCase(gnmk)) {// ѧ����Ϣ
			setXsxxOptionList(model, rForm, request);
		} else if ("jxgl".equalsIgnoreCase(gnmk)) {// ��ѵ����
			setJxglOptionList(model, rForm, request);
		}
		// =====================��������ģ��OVER=============================
	}

	/**
	 * ҳ�������б��ʼ����ͨ�ã�
	 * 
	 * @author luojw
	 */
	private void setCommOptionList(CommForm model, RequestForm rForm,
			HttpServletRequest request) {

		// �Ա�
		List<HashMap<String, String>> xbList = getCommSelectList("xblx");
		request.setAttribute("xbList", xbList);

		// �Ƿ�
		List<HashMap<String, String>> sfList = getCommSelectList("sflx");
		request.setAttribute("sfList", sfList);
	}

	/**
	 * ҳ�������б��ʼ����ϵͳά����
	 * 
	 * @author luojw
	 */
	private void setXtwhOptionList(CommForm model, RequestForm rForm,
			HttpServletRequest request) {

		DAO dao = DAO.getInstance();

		// ����ģ��
		String gnmk = "xtwh";
		// �˵�
		String menu = rForm.getMenu();

		if ("xzzq".equalsIgnoreCase(menu)) {// ����ר��

			// �ļ������б�
			List<HashMap<String, String>> filelxList = dao.getWhList(
					"xg_xtwh_szzqlxb", "dm", "mc", "", "", "");
			request.setAttribute("filelxList", filelxList);

			// ���������б�
			List<HashMap<String, String>> sslxList = dao.getWhList(
					"xg_xtwh_szzqssb", "dm", "mc", "", "", "");
			request.setAttribute("sslxList", sslxList);

			// ���ض����б�
			List<HashMap<String, String>> xzdzList = getSelectList(gnmk, "xzdx");
			request.setAttribute("xzdzList", xzdzList);

		} else if ("sydc".equalsIgnoreCase(menu)) {// ��ҳ����

			// �Ƿ�����
			List<HashMap<String, String>> sfqyList = getCommSelectList("sflx");
			request.setAttribute("sfqyList", sfqyList);
		} else if("qxgl".equalsIgnoreCase(menu)){
			List<HashMap<String, String>> yhzList 
				= dao.getList("select distinct zdm dm,zmc mc from yhzb where zdm<>'6727'",
						new String[] {}, new String[] { "dm", "mc" });
			request.setAttribute("yhzList", yhzList);
			
			List<HashMap<String, String>> dwList 
			= dao.getList("select distinct dwdm dm,dwmc mc from bks_dwdmb order by dwdm ",
					new String[] {}, new String[] { "dm", "mc" });
			request.setAttribute("dwList", dwList);
		}
	}

	/**
	 * ҳ�������б��ʼ����ѧ��������
	 * 
	 * @author luojw
	 */
	private void setXszzOptionList(CommForm model, RequestForm rForm,
			HttpServletRequest request) {

		DAO dao = DAO.getInstance();

		// ����ģ��
		String gnmk = "xszz";
		// �˵�
		String menu = rForm.getMenu();

		if ("xmtj".equalsIgnoreCase(menu)) {// ����ͳ��
			// ��Ŀ����б�
			List<HashMap<String, String>> xmlbList = getSelectList(gnmk, "xmlb");
			request.setAttribute("xmlbList", xmlbList);

			// ������Ŀ�б�
			List<HashMap<String, String>> xmList = dao.getWhList("xszz_zzxmb",
					"xmdm", "xmmc", "", "", "");
			request.setAttribute("xmList", xmList);
		} else if ("zdsz".equalsIgnoreCase(menu)) {// �ֶ�����

			// ������Ŀ�б�
			List<HashMap<String, String>> xmList = dao.getWhList("xszz_zzxmb",
					"xmdm", "xmmc", "", "mrxm", "��");

			HashMap<String, String> map = new HashMap<String, String>();
			map.put("dm", "ws");
			map.put("mc", "����");

			xmList.add(map);

			request.setAttribute("xmList", xmList);
		}
	}

	/**
	 * ҳ�������б��ʼ������Ԣ����
	 * 
	 * @author luojw
	 */
	private void setGyglOptionList(CommForm model, RequestForm rForm,
			HttpServletRequest request) {

		GyglTyService service = new GyglTyService();
		GyglTyDAO gyDao = new GyglTyDAO();
		DAO dao = DAO.getInstance();

		// ѧУ����
		String xxdm = Base.xxdm;
		// ����ģ��
		String gnmk = "gygl";
		// �˵�
		String menu = rForm.getMenu();
		// =================�ж��Ƿ�Ԣ����Ա==========================
		// �û���
		String userName = rForm.getUserName();
		// ¥������
		String lddm = model.getLddm();
		lddm = Base.isNull(lddm) ? model.getQueryequals_lddm() : lddm;
		// ����
		String cs = model.getCs();
		cs = Base.isNull(cs) ? model.getQueryequals_cs() : cs;

		// ====================ͨ��==========================
		// У��
		List<HashMap<String, String>> xqdmList = dao.getWhList("dm_zju_xq",
				"dm", "xqmc", "", "", "");
		request.setAttribute("xqdmList", xqdmList);

		// ԰��
		List<HashMap<String, String>> yqList = dao.getWhList("yqdmb", "yqdm",
				"yqmc", "", "", "");
		request.setAttribute("yqList", yqList);

		// ¥��
		if (Globals.XXDM_ZGDZDX.equalsIgnoreCase(xxdm)) {// �й��ش�
			List<HashMap<String, String>> ldList = dao.getWhList(
					"view_bjlh_ldxx", "lddm", "yqmc||'/'||ldmc", "", "", "");
			request.setAttribute("ldList", ldList);
		} else {
			List<HashMap<String, String>> ldList = dao.getWhList("sslddmb",
					"lddm", "ldmc", "", "", "");
			request.setAttribute("ldList", ldList);
		}

		// �Ƿ�Ԣ����Ա
		if (service.isGyfdy(userName)) {
			service.initGyglList(userName, request);
		}

		// ����
		List<HashMap<String, String>> csList = gyDao.getCsList(lddm);
		request.setAttribute("csList", csList);

		// ����
		List<HashMap<String, String>> qsList = gyDao.getQsList(lddm, cs, "");
		request.setAttribute("qsList", qsList);

		// �ܴ�
		List<HashMap<String, String>> zcList = CommService.getZcList(
				"gygl_wsjc_csszb", "zgzc");
		request.setAttribute("zcList", zcList);
		// ====================ͨ��OVER==========================

		// ====================�°汾 ͨ��==========================
		String edition = XMLReader.getFlowControl("gygl", "edition");

		if ("new".equalsIgnoreCase(edition)) {
			String sql = "select lddm,ldmc from sslddmb order by lddm";
			List<HashMap<String, String>> ldList = dao.getList(sql,
					new String[] {}, new String[] { "lddm", "ldmc" });
			request.setAttribute("ldList", ldList);

			if ("qszdfp".equalsIgnoreCase(menu)) {// �����Զ�����

				SearchDAO searchDAO = new SearchDAO();

				List<HashMap<String, String>> ldQsList = searchDAO
						.getLdForWfpQssList(null, null, null, null, null);

				request.setAttribute("ldQsList", ldQsList);
			} else if ("qsfp".equalsIgnoreCase(menu)) {// ���ҷ���
//				SearchDAO searchDAO = new SearchDAO();
//
//				List<HashMap<String, String>> ldQsList = searchDAO
//						.getLdForWfpQssList(null, null, rForm.getGyglyQx(), userName, null);
//
//				request.setAttribute("ldQsList", ldQsList);
			}
		}
		// ====================�°汾 ͨ�� end==========================
		
		if ("wsjc_cssz".equalsIgnoreCase(menu)) {// �������_��������

			// �����ȼ�
			List<HashMap<String, String>> gldjTopList = getSelectList(gnmk,
					"gldjTop");
			request.setAttribute("gldjTopList", gldjTopList);

			// ��������
			List<HashMap<String, String>> glfsTopList = getSelectList(gnmk,
					"glfsTop");
			request.setAttribute("glfsTopList", glfsTopList);

			// ����������
			List<HashMap<String, String>> bglfsTopList = getSelectList(gnmk,
					"bglfsTop");
			request.setAttribute("bglfsTopList", bglfsTopList);
		} else if ("wsjc_fs".equalsIgnoreCase(menu)) {// �������_����

			// ��鲿��
			List<HashMap<String, String>> jcbmList = dao.getWhList("gywsjcbmb",
					"bmdm", "bmmc", "", "", "");
			request.setAttribute("jcbmList", jcbmList);

			// ͳ�Ʒ�ʽ
			List<HashMap<String, String>> tjfsList = getSelectList(gnmk,
					"wsf_tjfs");
			request.setAttribute("tjfsList", tjfsList);

			// ͳ�Ʒ�Χ
			List<HashMap<String, String>> tjfwList = getSelectList(gnmk,
					"wsf_tjfw");
			request.setAttribute("tjfwList", tjfwList);
		}

	}

	/**
	 * ҳ�������б��ʼ�����������ţ�
	 * 
	 * @author luojw
	 */
	private void setPjpyOptionList(CommForm model, RequestForm rForm,
			HttpServletRequest request) {

		DAO dao = DAO.getInstance();

		// ѧУ����
		String xxdm = Base.xxdm;
		// ����ģ��
		String gnmk = "pjpy";
		// �˵�
		String menu = rForm.getMenu();
		// ����sql
		String sql = "";

		PjpyCommService pjpyService = new PjpyCommService();

		// ��Ŀ�����б�
		List<HashMap<String, String>> pjpyXmxzList = pjpyService.getXmxzList();
		request.setAttribute("xmxzList", pjpyXmxzList);

		// ��Ŀ��Χ�б�
		List<HashMap<String, String>> pjpyXmfwList = pjpyService.getXmfwList();
		request.setAttribute("xmfwList", pjpyXmfwList);

		// ��Ŀ�����б�
		List<HashMap<String, String>> pjpyXmlxList = getSelectList(gnmk, "xmlx");
		request.setAttribute("xmlxList", pjpyXmlxList);
		
		if ("pjjbsz".equalsIgnoreCase(menu)) {// ������������

			// �������䷽ʽ
			List<HashMap<String, String>> rsfpfsList = getSelectList(gnmk,
					"rsfpfs");
			request.setAttribute("rsfpfsList", rsfpfsList);
			request.setAttribute("fpfsNum", rsfpfsList.size());

			sql = "select tjdm,tjms,tjmc,sfqy from xg_pjpy_pjtjkb order by tjdm";
			// ����������
			List<HashMap<String, String>> tjkList = dao.getList(sql,
					new String[] {}, new String[] { "tjdm", "tjms", "tjmc",
							"sfqy" });
			if (tjkList != null && tjkList.size() > 0) {

				int tjkNum = tjkList.size();
				int nullNum = 4 - tjkNum % 4;
				for (int i = 0; i < nullNum; i++) {
					HashMap<String, String> map = new HashMap<String, String>();
					tjkList.add(map);
				}

				int rowNum = tjkList.size() / 4;
				if (rowNum < 5) {
					for (int i = 0; i < (5 - rowNum) * 4; i++) {
						HashMap<String, String> map = new HashMap<String, String>();
						tjkList.add(map);
					}
				}
				request.setAttribute("tjkNum", tjkNum);
				request.setAttribute("tjkList", tjkList);
			}
		} else if ("pjlcsz".equalsIgnoreCase(menu)) {// ������������

		}
	}

	/**
	 * ҳ�������б��ʼ����ѧ����Ϣ��
	 * 
	 * @author luojw
	 */
	private void setXsxxOptionList(CommForm model, RequestForm rForm,
			HttpServletRequest request) {

		DAO dao = DAO.getInstance();

		// ѧУ����
		String xxdm = Base.xxdm;
		// ����ģ��
		String gnmk = "xsxx";
		// �˵�
		String menu = rForm.getMenu();

		if ("jcsjsz".equalsIgnoreCase(menu)) {// ����Դ_������������

			// ����Դ�б�
			List<HashMap<String, String>> jbszSjyList = getSelectList(gnmk,
					"jbsz_sjy");
			request.setAttribute("jbszSjyList", jbszSjyList);

			// ¼�������б�
			List<HashMap<String, String>> jbszLrxzList = getSelectList(gnmk,
					"jbsz_lrxz");
			request.setAttribute("jbszLrxzList", jbszLrxzList);

			// Ϊ�������б�
			List<HashMap<String, String>> jbszWkxzList = getSelectList(gnmk,
					"jbsz_wkxz");
			request.setAttribute("jbszWkxzList", jbszWkxzList);

			// ¼����ʽ�б�
			List<HashMap<String, String>> jbszLrxsList = getSelectList(gnmk,
					"jbsz_lrxs");
			request.setAttribute("jbszLrxsList", jbszLrxsList);
		} else if ("zxsxx".equalsIgnoreCase(menu)) {//��У����Ϣ
			try {
				FormModleCommon.requestSetList(new String[] { "mz" }, request);
				CommanForm dataSearchForm =new CommanForm();
				XsxxYnysService ynysService = new XsxxYnysService();
				XsxxglService service = new XsxxglService();
				StuInfoDAO stuInfoDao = new StuInfoDAO();
				HashMap<String, String> map = request.getAttribute("rs") != null ? (HashMap<String, String>)
						request.getAttribute("rs") : new HashMap<String, String>();
				request.setAttribute("ssList", ynysService.getSsList());//ʡ�б�
		        request.setAttribute("jgshiList", ynysService.getShiList(map.get("jgshen") == null ? "9999999" : map.get("jgshen")).get("shiList"));//���б�
				request.setAttribute("jgxianList",  ynysService.getShiListNew(map.get("jgshi") == null ? ( map.get("jgshen") != null ? map.get("jgshen").substring(0, 2) :"9999999"): map.get("jgshi")).get("xianList"));//���б�
				
				request.setAttribute("sydshiList", ynysService.getShiList(map.get("syds") == null ? "9999999" : map.get("syds")).get("shiList"));//���б�
				request.setAttribute("sydxianList",  ynysService.getShiListNew(map.get("sydshi") == null ? (map.get("syds") !=null ? map.get("syds").substring(0, 2) :"9999999"): map.get("sydshi")).get("xianList"));//���б�
					
				request.setAttribute("hkshiList", ynysService.getShiList(map.get("hkshen") == null ? "9999999" : map.get("hkshen")).get("shiList"));//���б�
				request.setAttribute("hkxianList",  ynysService.getShiListNew(map.get("hkshi") == null ? ( map.get("hkshen")!=null?map.get("hkshen").substring(0, 2) :"9999999"): map.get("hkshi")).get("xianList"));//���б�
				
				request.setAttribute("xjztList", stuInfoDao.getXjztList());//ѧ��״̬
				request.setAttribute("pyccList", service.getList(GlobalsVariable.XTWH_PYCC_LIST));//�������
				request.setAttribute("kslbList", service.getList(GlobalsVariable.XTWH_KSLB_LIST));//�������
				request.setAttribute("rxfsList", service.getList(GlobalsVariable.XTWH_RXFS_LIST));//��ѧ��ʽ
				request.setAttribute("pyfsList", service.getList(GlobalsVariable.XTWH_PYFS_LIST));//������ʽ
				request.setAttribute("jtgxList", service.getJtgxList());//��ͥ��ϵ 
				request.setAttribute("yhmcList", service.getList(GlobalsVariable.XTWH_YH_LIST));//�����б�
				
			} catch (Exception e) {
				// TODO �Զ����� catch ��
				e.printStackTrace();
			}// �Զ���(Ŀǰ�����Ŵ���,������ò)
		}
	}

	/**
	 * ҳ�������б��ʼ������ѵ����
	 * 
	 * @author luojw
	 */
	private void setJxglOptionList(CommForm model, RequestForm rForm,
			HttpServletRequest request) {

		DAO dao = DAO.getInstance();

		// ѧУ����
		String xxdm = Base.xxdm;
		// ����ģ��
		String gnmk = "jxgl";
		// �˵�
		String menu = rForm.getMenu();

		if ("jxbz".equalsIgnoreCase(menu)) {// ��ѵ����

			// ��ѵ���Ƶȼ��б�
			//List<HashMap<String, String>> jxbzdjList = dao.getWhList("jxjzdj",
			//		"jzdm", "jzmc", "", "", "");
			//request.setAttribute("jxbzdjList", jxbzdjList);
		}
	}
	
	/**
	 * �������ά��������ֵ
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getSelectList(String gnmk, String lx) {

		DAO dao = DAO.getInstance();

		String[] dm = null;
		String[] mc = null;

		if (Base.isNull(gnmk)) {// ����
			return getCommSelectList(lx);
		} else if ("xszz".equalsIgnoreCase(gnmk)) {// ѧ������
			return getXszzSelectList(lx);
		} else if ("gygl".equalsIgnoreCase(gnmk)) {// ��Ԣ����
			return getGyglSelectList(lx);
		} else if ("pjpy".equalsIgnoreCase(gnmk)) {// ��������
			return getPjpySelectList(lx);
		} else if ("xtwh".equalsIgnoreCase(gnmk)) {// ϵͳά��
			return getXtwhSelectList(lx);
		} else if ("xsxx".equalsIgnoreCase(gnmk)) {// ѧ����Ϣ
			return getXsxxSelectList(lx);
		}

		return dao.arrayToList(dm, mc);
	}

	/**
	 * �������ά��������ֵ�����ã�
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getCommSelectList(String lx) {

		DAO dao = DAO.getInstance();

		String[] dm = null;
		String[] mc = null;

		if ("xblx".equalsIgnoreCase(lx)) {
			dm = new String[] { "��", "Ů" };
			mc = new String[] { "��", "Ů" };
		} else if ("sflx".equalsIgnoreCase(lx)) {
			dm = new String[] { "��", "��" };
			mc = new String[] { "��", "��" };
		}

		return dao.arrayToList(dm, mc);
	}

	/**
	 * �������ά��������ֵ��ѧ��������
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getXszzSelectList(String lx) {

		DAO dao = DAO.getInstance();

		String[] dm = null;
		String[] mc = null;

		if ("xmlb".equalsIgnoreCase(lx)) {
			dm = new String[] { "��ѧ��", "��ѧ��", "���Ѳ���", "����" };
			mc = new String[] { "��ѧ��", "��ѧ��", "���Ѳ���", "����" };
		}

		return dao.arrayToList(dm, mc);
	}

	/**
	 * �������ά��������ֵ����Ԣ����
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getGyglSelectList(String lx) {

		DAO dao = DAO.getInstance();

		String[] dm = null;
		String[] mc = null;

		if ("gldjTop".equalsIgnoreCase(lx)) {// �������_��������_���������ȼ�top
			dm = new String[] { "", "����������", "����������", "��Ӧ�ȼ�" };
			mc = new String[] { "", "����������", "����������", "��Ӧ�ȼ�" };
		} else if ("glfsTop".equalsIgnoreCase(lx)) {// �������_��������_�ȼ���������top
			dm = new String[] { "", "�����ֵȼ�", "��Ӧ����" };
			mc = new String[] { "", "�����ֵȼ�", "��Ӧ����" };
		} else if ("bglfsTop".equalsIgnoreCase(lx)) {// �������_��������_�ȼ�����������top
			dm = new String[] { "", "�����ֵȼ�" };
			mc = new String[] { "", "�����ֵȼ�" };
		} else if ("wsf_tjfs".equalsIgnoreCase(lx)) {// �������_������ͳ�Ʒ�ʽ
			dm = new String[] { "qs", "xs" };
			mc = new String[] { "����", "ѧ��" };
		} else if ("wsf_tjfw".equalsIgnoreCase(lx)) {// �������_������ͳ�Ʒ�Χ
			dm = new String[] { "nj", "xy", "zy", "bj" };
			mc = new String[] { "�꼶", Base.YXPZXY_KEY, "רҵ", "�༶" };
		}

		return dao.arrayToList(dm, mc);
	}

	/**
	 * �������ά��������ֵ���������ţ�
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getPjpySelectList(String lx) {

		DAO dao = DAO.getInstance();

		String[] dm = null;
		String[] mc = null;

		if ("rsfpfs".equalsIgnoreCase(lx)) {// ��������_��������_�������䷽ʽ
//			dm = new String[] { "xx", "xxxy", "xxfpxy" };
//			mc = new String[] { "��ѧУ����", "ѧУ,ѧԺ��������", "ѧУ����ѧԺ��ѧԺ��������רҵ�༶" };
			
			dm = new String[] { "xx" };
			mc = new String[] { "��ѧУ����" };
		} else if ("xmlx".equalsIgnoreCase(lx)) {// ��������_��Ŀ����
			dm = new String[] { "01", "02" };
			mc = new String[] { "��ѧ��", "�����ƺ�" };
		}

		return dao.arrayToList(dm, mc);
	}

	/**
	 * �������ά��������ֵ��ϵͳά����
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getXtwhSelectList(String lx) {

		DAO dao = DAO.getInstance();

		String[] dm = null;
		String[] mc = null;

		if ("xzdx".equalsIgnoreCase(lx)) {// ��ҳ����_���ض���
			dm = new String[] { "ȫ��", "��ʦ", "ѧ��" };
			mc = new String[] { "ȫ��", "��ʦ", "ѧ��" };
		}

		return dao.arrayToList(dm, mc);
	}

	/**
	 * �������ά��������ֵ��ѧ����Ϣ��
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getXsxxSelectList(String lx) {

		DAO dao = DAO.getInstance();

		String[] dm = null;
		String[] mc = null;

		if ("jbsz_sjy".equalsIgnoreCase(lx)) {// ��������_����Դ
			dm = new String[] { "ѧ��", "�ӿ�" };
			mc = new String[] { "ѧ��", "�ӿ�" };
		} else if ("jbsz_lrxz".equalsIgnoreCase(lx)) {// ��������_¼������
			dm = new String[] { "������", "��������", "��������(�ɴ�С��)", "Ӣ��������", "��������" };
			mc = new String[] { "������", "��������", "��������(�ɴ�С��)", "Ӣ��������", "��������" };
		} else if ("jbsz_wkxz".equalsIgnoreCase(lx)) {// ��������_Ϊ������
			dm = new String[] { "����Ϊ��", "����Ϊ��" };
			mc = new String[] { "����Ϊ��", "����Ϊ��" };
		} else if ("jbsz_wkxz".equalsIgnoreCase(lx)) {// ��������_Ϊ������
			dm = new String[] { "����Ϊ��", "����Ϊ��" };
			mc = new String[] { "����Ϊ��", "����Ϊ��" };
		} else if ("jbsz_lrxs".equalsIgnoreCase(lx)) {// ��������_¼����ʽ
			dm = new String[] { "�����","ʱ���ʽ", "�ı���", "�����б�", "��ѡ��" };
			mc = new String[] { "�����","ʱ���ʽ", "�ı���", "�����б�", "��ѡ��" };
		}

		return dao.arrayToList(dm, mc);
	}
}
