/*
 * �������� 2006-10-18
 *
 *  Ҫ���Ĵ����ɵ��ļ���ģ�壬��ת��
 * ���� �� ��ѡ�� �� Java �� ������ʽ �� ����ģ��
 */
package xgxt.action;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.apache.struts.action.ActionForward;

import xgxt.DAO.DAO;
import xgxt.DAO.Bjlh.SynchronizeBaseDataTask;
import xgxt.base.DealString;
import xgxt.comm.search.SearchService;
import xgxt.comm.xml.XMLReader;
import xgxt.form.CommanForm;
import xsgzgl.pjpy.szgyyq.mypj.tea.PjpyTeaDAO;
import xsgzgl.pjpy.szgyyq.mypj.tea.PjpyTeaForm;

import com.zfsoft.license.LicenseOps;
import common.Globals;

/**
 * @author bat_zzj
 * 
 * �����ࣺ session��ʱ��֤ ���ַ������ �û����ʶ�� д��־
 * 
 */
public class Base {
	
	public static String currXn;
	
	public static String beforXn;
	
	public static String afterXn;

	public static String currNd;
	
	public static String currYf;
	
	public static String fwqNd;

	public static String currXq;

	public static String sydm;

	public static String xydm;
	
	public static String xxdm;
	
	public static String xxmc;

	public static String sButton; 
	
	private static String endPointUri;
	
	private static String bjlhBaseDataEndPointUri;
	
	private static String jwflag;//����汾
	
	//webservice��Զ�˷������û���������
	private static String webserviceUser;
	private static String webservicePass;
	
	public static HashMap<String, String> initProperties = new HashMap<String, String>();
	
	private static String jxjsqxn;
	private static String jxjsqnd;
	private static String jxjsqxq;
	private static String jxjsqxqmc;
	public static String dqxqmc;
	private static String pjzq;//��������
	private static String zczq;//�۲�����
	
	protected static List<HashMap<String, String>> njList = null;
	protected static List<HashMap<String, String>> njallList = null;
	private static List<HashMap<String, String>> xnndList = null;
	private static List<HashMap<String, String>> xnndList2 = null;
	private static List<HashMap<String, String>> xqList = null;
	private static List<HashMap<String, String>> yfList = null;

	
	protected static List<HashMap<String, String>> xyList = null;
	public static HashMap<String, List<HashMap<String, String>>> bjMap = new HashMap<String, List<HashMap<String,String>>>();
	protected static HashMap<String, List<HashMap<String, String>>> zyMap = new HashMap<String, List<HashMap<String,String>>>();
	protected static HashMap<String, List<HashMap<String, String>>> cwfpZyMap = new HashMap<String, List<HashMap<String,String>>>();

	protected static List<HashMap<String, String>> xyallList = null;
	protected static List<HashMap<String, String>> syallList = null;
	public static HashMap<String, List<HashMap<String, String>>> bjallMap = new HashMap<String, List<HashMap<String,String>>>();
	protected static HashMap<String, List<HashMap<String, String>>> zyallMap = new HashMap<String, List<HashMap<String,String>>>();
	

	// ====================�꼶��ѧԺ��רҵ���༶START ��ʾ���С�ѧ����׷�ӡ�=================================
	protected static List<HashMap<String, String>> njNewList = null;
	protected static List<HashMap<String, String>> xyNewList = null;
	protected static HashMap<String, List<HashMap<String, String>>> bjNewList = new HashMap<String, List<HashMap<String,String>>>();
	protected static HashMap<String, List<HashMap<String, String>>> zyNewList = new HashMap<String, List<HashMap<String,String>>>();
	// ====================�꼶��ѧԺ��רҵ���༶END ��ʾ���С�ѧ����׷�ӡ�=================================
	
	private final static String XN_KEY = "xn";//ѧ��KEY
	private final static String ND_KEY = "nd";//���KEY 
	private final static String XQ_KEY = "xq";//ѧ��KEY 
	private final static String ZCXN_KEY = "zcxn"; //�۲�ѧ��KEY
	private final static String ZCXQ_KEY = "zcxq"; //�۲�ѧ��KEY
	private final static String ZCND_KEY = "zcnd"; //�۲����KEY
	
	//ͨ�û�����
	public static String edition;//�汾��new or old
	public static String superSearch;//�߼���ѯ��yes or no
	public static String newsQuery;//�����б� new or old;
	public static String xsxxwh;//�汾��kwh or bkwh
	public static String userLogin;//��½����:xs or ls
	public static String YXPZXY_KEY ;
	public static String GYPZYQ_KEY;
	public static String userChange;//<!-- ͨ�ã�0 ��ְҵ��1 -->
	
	public static String getUserChange() {
		if (Base.isNull(userChange)) {
			userChange = XMLReader.getFlowControl("comm", "userChange");
			setUserChange(userChange);
		}

		return userChange;
	}

	public static void setUserChange(String userChange) {	
		Base.userChange = userChange;
	}

	/**
	 * ���������Ż��������д����Ĺ���(��У��)
	 */
/*	public static String xsxxb="( select a.xh,a.xm,(case a.xb when '1' then '��' when '2' then 'Ů' else a.xb end) xb, " +
								"a.nj,a.xydm,a.zydm,a.bjdm from (select xh,xm,xbm xb,to_char(nj) nj, bmdm xydm,zydm,bjdm from bks_xsjbxx a " +
								"where not exists (select 1 from xsxxb b where a.xh = b.xh) "+
							   "union all "+
							   "select a.xh,a.xm,a.xb,a.nj,a.xydm,a.zydm,a.bjdm from xsxxb a " +
							   "where (sfyby = '��' or sfyby is null) and (sfzx = '��У' or sfzx is null)) a )";*/
	public static String xsxxb=" ( select a.xh,a.xm,(case a.xb when '1' then '��' when '2' then 'Ů' else a.xb end) xb,a.nj,a.xydm,a.zydm,a.bjdm," +
			"(select c.qxmc from dmk_qx c where c.qxdm = substr(a.syd, 0, 2) || '0000') || (select d.qxmc from dmk_qx d where d.qxdm = substr(a.syd, 0, 4) || '00' " +
			"and a.syd <> substr(a.syd, 0, 2) || '0000') sydqmc from view_xsbfxx a ) ";
	
	
	/**
	 * ���������Ż��������д����Ĺ���
	 */
//	public static String xsxxbAll="( select a.xh,a.xm,(case a.xb when '1' then '��' when '2' then 'Ů' else a.xb end) xb, " +
//								"a.nj,a.xydm,a.zydm,a.bjdm from (select xh,xm,xbm xb,to_char(nj) nj, bmdm xydm,zydm,bjdm from bks_xsjbxx a " +
//								"where not exists (select 1 from xsxxb b where a.xh = b.xh) "+
//							   "union all "+
//							   "select a.xh,a.xm,a.xb,a.nj,a.xydm,a.zydm,a.bjdm from xsxxb a) a )";
	//ʹ���°汾ѧ����Ϣ��BKS_XSJBXX����ʹ��
	public static String xsxxbAll="( select a.xh,a.xm,a.xb,a.nj,a.xydm,a.zydm,a.bjdm,a.yhkh from view_xsxxb a )";
	
	/**
	 * ���������Ż��������д����Ĺ���(��ְͨҵ��ѧ-���Ի�)-������һ����ѧ��ʽ
	 */
	public static String xsxxb_ntzydx="( select a.xh,a.xm,(case a.xb when '1' then '��' when '2' then 'Ů' else a.xb end) xb, " +
								"a.nj,a.xydm,a.zydm,a.bjdm,a.rxfs rxfsdm from (" +
								"select xh,xm,xbm xb,to_char(nj) nj, bmdm xydm,zydm,bjdm,rxfs from bks_xsjbxx a " +
								"where not exists (select 1 from xsxxb b where a.xh = b.xh) "+
							   "union all "+
							   "select a.xh,a.xm,a.xb,a.nj,a.xydm,a.zydm,a.bjdm,rxfs from xsxxb a " +
							   "where (sfyby = '��' or sfyby is null) and (sfzx = '��У' or sfzx is null)) a )";
	
	private static boolean isHistory = false;

	//public static boolean initalDoneFlag = true; 
	static {//��ʼ��
		//��ʼ����������
		DAO dao = DAO.getInstance();
		
		String sql;
		sql = "select dqxn,dqnd,dqxq,(select xqmc from xqdzb b where a.dqxq=b.xqdm) dqxqmc,nvl(sydm,' ') sydm,nvl(xydm,' ') xydm,jxjsqxn,jxjsqnd,jxjsqxq,(select xqmc from xqdzb b where a.jxjsqxq=b.xqdm) jxjsqxqmc,xxdm,xxmc,jwflag from xtszb a where rownum=1";
		String[] resultArr = dao.getOneRs(sql, new String[]{}, new String[]{"dqxn","dqnd","dqxq","sydm","xydm","jxjsqxn","jxjsqnd","jxjsqxq","xxdm","xxmc", "dqxqmc", "jxjsqxqmc", "jwflag"});
		Base.currXn = resultArr[0];
		String sT = Base.currXn.substring(0,4);
		Base.beforXn = String.valueOf(Integer.parseInt(sT)-1) + "-" + sT;
		String eT = Base.currXn.substring(5, 9);
		Base.afterXn = eT + "-" + String.valueOf(Integer.parseInt(eT)+1);
		Base.currNd = resultArr[1];
		// ========== ��ǰ�·� < ============
		Calendar calendar = Calendar.getInstance();
		String currYf = String.valueOf(calendar.get(Calendar.MONTH) + 1);
		if(currYf.length() < 2){
			currYf = "0" + currYf;
		}
		Base.currYf = currYf;
		// ========== ��ǰ�·� > ============
		Base.currXq = resultArr[2];
		Base.sydm = resultArr[3];
		Base.xydm = resultArr[4];
		Base.xxdm = resultArr[8];
		Base.xxmc = resultArr[9];
		//ѧУ�������Ƹ�Ϊ����Ȩ�ļ���ȡ
		/*try {
			//�ǿ�����ģʽ����ʽ�棬ѧУ�������Ƹ�Ϊȡlicenes����
			if(LicenseOps.getInstance().isNativeLibLoadSuccess()
					&& LicenseOps.getInstance().isLicenseOpsEnabled()&&!"1".equals(LicenseOps.getInstance().getDevMode())){
				Base.xxmc = new String(new Base64().decode(LicenseOps.getInstance().getAuthUser()),"utf-8");
				Base.xxdm =	LicenseOps.getInstance().getAuthUserCode();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		Base.dqxqmc = resultArr[10];
		Base.jxjsqxqmc = resultArr[11];
		Base.jwflag = resultArr[12];
//		long t3 = System.currentTimeMillis();
//		System.out.println("��ѯѧ��ѧ�ڣ�"+(t3-t2));
		Base.setJxjsqxn(resultArr[5]);
		Base.setJxjsqnd(resultArr[6]);
		Base.setJxjsqxq(resultArr[7]);
		Base.setXnndList(dao.getXnndList());
		Base.setXnndList2(dao.getXnndList2());
		Base.setNjList(dao.getNjList());
		Base.setNjallList(dao.getNjallList());
		Base.setXyList(dao.getXyList());
		Base.setXyallList(dao.getXyallList());
		Base.setSyallList(dao.getSyallList());
		Base.setXqList(dao.getXqList());
		Base.setYfList(dao.getYfList());
		
		
		Base.fwqNd = dao.getOneRs("select to_char(sysdate,'yyyy') nd from dual", new String[]{}, "nd");
		
		Timer timer = new Timer();
		Timer nextTime = new Timer();
		TTask tTask = new TTask(true);
		if(Globals.XXDM_ZGDZDX.equalsIgnoreCase(xxdm)){
			//�й����ʴ�ѧ
			QgzxZgdzdxTask qzTask = new QgzxZgdzdxTask();
			timer.schedule(tTask, new Date());
			nextTime.schedule(qzTask, new Date());
		}
		
		//��ʼ���������ڼ��۲����ڿ�ʼ
//		HashMap<String, String> pjzqMap = dao.getMapNotOut("select xn,nd,xq,zcxn,zcnd,zcxq from pjpy_pjzqb", new String[]{});
//		Base.setPjzq(queryPjzq(pjzqMap, XN_KEY, XQ_KEY, ND_KEY));
//		Base.setZczq(queryPjzq(pjzqMap, ZCXN_KEY, ZCXN_KEY, ZCND_KEY));
		//��ʼ���������ڼ��۲����ڽ���
		
//		long t2 = System.currentTimeMillis();
		
		
//		ͬ�����ݲ���
		Timer synchronizeTimer = new Timer();
		//�Ǳ������ϴ�ѧʱ�Ž���ͬ��������
		if(Globals.XXDM_BJLHDX.equalsIgnoreCase(Base.xxdm)){
			SynchronizeBaseDataTask synchronizeTask = new SynchronizeBaseDataTask();
			synchronizeTimer.schedule(synchronizeTask, new Date());
		}		
		
//		long t4 = System.currentTimeMillis();
//		System.out.println("�趨ѧ����ȣ�"+(t4-t3));
		String xydm;
//		String zydm;
		//��ʼ��zyMap
		
		/*
		 *  xy = xy==null ? "":xy;
			zy = zy==null ? "":zy;
		    nj = nj==null ? "":nj;
		    String bjKey = xy+"!!"+zy+"!!"+nj;
		 *���Ҫѡ��ȫ����רҵ�����ǵ���ѡѧԺ��ʱ��map�ж�Ӧ��keyֵΪ"!!";
		 *�����Ӧ��keyֵΪ��Ӧ��ѧԺ���� 
		 */
		List<HashMap<String, String>> nullZyList = dao.getZyList("");
		(Base.zyMap).put("", nullZyList);
		for(HashMap<String, String> xy : Base.xyList){
			xydm = xy.get("xydm");
			List<HashMap<String, String>> subZyList = dao.getZyList(xydm);
			(Base.zyMap).put(xydm, subZyList);
		}
		
		List<HashMap<String, String>> allZyList = dao.getZyallList("");
		(Base.zyallMap).put("", allZyList);
		for(HashMap<String, String> xy : Base.xyallList){
			xydm = xy.get("xydm");
			List<HashMap<String, String>> subZyList = dao.getZyallList(xydm);
			(Base.zyallMap).put(xydm, subZyList);
		}
		
//		long t5 = System.currentTimeMillis();
//		System.out.println("�趨רҵ��"+(t5-t4));
//		��ʼ��bjMap
		new Thread(new Base.initialBj()).start();
//		long t6 = System.currentTimeMillis();
//		System.out.println("�趨�༶��"+(t6-t5));
		tTask.setFlag(false);
		timer.cancel();
		nextTime.cancel();
//		
//		SearchService search = new SearchService();
//		// ѧԺ
//		List<HashMap<String, Object>> xyTjList = search.getNewXyPxList("","","");
//		// רҵ
//		List<HashMap<String, Object>> zyTjList = search.getNewZyPxList(null,"","","");
//		// �༶
//		List<HashMap<String, Object>> bjTjList = search.getNewBjPxList(null,"","","");
//		
//		SearchService.setXyTjList(xyTjList);
//		SearchService.setZyTjList(zyTjList);
//		SearchService.setBjTjList(bjTjList);
		
		//���ݹ�ҵ԰���ۺϲ������ڼ����������
		if(Globals.XXDM_SZGYYQZYJSXY.equals(Base.xxdm)){
			new Thread(new SzgyyqJsZhcpFspm()).start();
		}
	}//��ɳ�ʼ��
	public static String getJsxmForzgh(String zgh){
		String sql = "select xm from fdyxxb where zgh=?";
		return DAO.getInstance().getOneRs(sql, new String[]{zgh},"xm");
	}
	/**
	 * 
	 * @����: ����ѧ�ڴ����ȡѧ������
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-1-24 ����02:49:30
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xqdm
	 * @return
	 * String �������� 
	 */
	public static String getXqmcForXqdm(String xqdm){
		String sql = "select xqdm,xqmc from xqdzb where xqdm=?";
		return DAO.getInstance().getOneRs(sql, new String[]{xqdm},"xqmc");
	}
	public static List<HashMap<String, String>> getNjList() {
		return njList;
	}

	public static void setNjList(List<HashMap<String, String>> lNjList) {
		Base.njList = lNjList;
	}

	public static List<HashMap<String, String>> getNjallList() {
		return njallList;
	}

	public static void setNjallList(List<HashMap<String, String>> NjallList) {
		Base.njallList = NjallList;
	}
	
	public static List<HashMap<String, String>> getXnndList() {
		return xnndList;
	}
	public static void setXnndList(List<HashMap<String, String>> lXnndList) {
		Base.xnndList = lXnndList;
	}
	
	public static List<HashMap<String, String>> getXnndList2() {
		return xnndList2;
	}
	public static void setXnndList2(List<HashMap<String, String>> lXnndList2) {
		Base.xnndList2 = lXnndList2;
	}
	
	public static String getJxjsqnd() {
		return jxjsqnd;
	}

	public static void setJxjsqnd(String sJxjsqnd) {
		jxjsqnd = sJxjsqnd;
	}

	public static String getJxjsqxn() {
		return jxjsqxn;
	}

	public static void setJxjsqxn(String sJxjsqxn) {
		jxjsqxn = sJxjsqxn;
	}

	public static String getJxjsqxq() {
		return jxjsqxq;
	}

	public static void setJxjsqxq(String sJxjsqxq) {
		jxjsqxq = sJxjsqxq;
	}
	
	/** session��ʱ��֤ */
	public static int chkTimeOut(HttpSession session) throws Exception {
		Enumeration sessionName = session.getAttributeNames();
		int i = 0;
		try {
			while (sessionName.hasMoreElements()) {
				sessionName.nextElement();
				i += 1;
			}
		} catch (Exception e) {
			i = 0;
			e.printStackTrace();
		}
		return i;
	}

	public static ActionForward chkSessionTimeOut(HttpSession session) throws Exception{
		CommanForm tempForm = new CommanForm();
		int i = chkTimeOut(session);
		if(i < 2){
			tempForm.setErrMsg("��½��ʱ�������µ�½��");
			return new ActionForward("/login.jsp", false);
		}
		return null;
	}

	/** ���ַ������ */
	public static boolean isNull(String str) {
		return ((str == null||"null".equalsIgnoreCase(str)) || str.equalsIgnoreCase(""));
	}

	/** �û����ʶ��-1��Ȩ�� 0ֻ��Ȩ�� 1��дȨ�� */
	public static int chkUPower(String uName, String modID, boolean isStu) {
		String sql = "";
		int power = -1;
		DAO daoBase = DAO.getInstance();
		if (!isStu) {
			sql = "select nvl(dxq,'0') dxq from view_yhqx where yhm=? and dyym=?";
		} else {
			sql = "select nvl(dxq,'0') dxq from view_yhzqx where zdm=? and dyym=?";
			//uName = "0002";
			uName = "6727";//ln update-time2009.1.9
		}
		//BEGIN ChenHuamao E-MAIL:chhuma@mail.china.com
		//�޸�java.lang.NullPointerException
		String[] ress = daoBase.getOneRs(sql, new String[] { uName, modID }, new String[]{"dxq"});
		String res = "";
		if (ress == null) {
			return -1;
		} else {
			res = ress[0];
		}
		//END

		if (!Base.isNull(res) && res.equals("0")) {
			// ֻ��
			power = 0;
		} else if (!Base.isNull(res) && res.equals("1")) {
			// ��д
			power = 1;
		}
		return power;
	}

	/** д��־ */
	public static void log(HttpServletRequest request, String msg,
			String userName) {
		DAO dao = new DAO(request.getRemoteAddr());
		try {
			dao.writeLog("", null, null, msg, request);
		} catch (Exception e) {

		}
	}

	/**ת�����ַ���Ϊָ�����ַ�����chgTagΪ1ʱת��*/
	public static String chgNull(String inStr, String toStr, int chgTag){
		if(chgTag == 1){
			inStr = DealString.toGBK(inStr);
		}	
		if(isNull(inStr)){
			inStr = toStr;
		}
		return inStr;
	}


	/**
	 * @param request ��ǰ
	 * @return �û���дȨ
	 */
	public static String getWriteAble(HttpServletRequest request){
		HttpSession session = request.getSession();						//�õ�session
		String userType = "";
		boolean isStu = true;
		String sUName = "";
		String sPath = "";
		String qStr = "";
		String power = "";
		userType = session.getAttribute("userType") == null ? "" : session.getAttribute("userType").toString();   //�õ��û�����
		isStu = (userType.equalsIgnoreCase("stu"));				 //�ж��Ƿ���ѧ���û���¼
		sUName = session.getAttribute("userName") == null ? "" : session.getAttribute("userName").toString();	//�õ���¼�û���
		sPath = request.getServletPath().replace("/", "");
		qStr = request.getQueryString();		
		if("".equalsIgnoreCase(qStr) || qStr == null){
			power = sPath;
		}else{
			power = sPath + "?" + qStr;
		}
		String writeAble = (chkUPower(sUName, power, isStu) == 1) ? "yes" : "no";
		return writeAble;
	}

	public static List<HashMap<String, String>> getXyList() {
		return xyList;
	}

	public static void setXyList(List<HashMap<String, String>> lXyList) {
		Base.xyList = lXyList;
	}

	public static List<HashMap<String, String>> getXyallList() {
		return xyallList;
	}

	public static void setXyallList(List<HashMap<String, String>> XyallList) {
		Base.xyallList = XyallList;
	}
	public static List<HashMap<String, String>> getSyallList() {
		return syallList;
	}

	public static void setSyallList(List<HashMap<String, String>> syallList) {
		Base.syallList = syallList;
	}
	public static List<HashMap<String, String>> getXqList() {
		return xqList;
	}

	public static void setXqList(List<HashMap<String, String>> xqList) {
		Base.xqList = xqList;
	}
	
	public static List<HashMap<String, String>> getYfList() {
		return yfList;
	}

	public static void setYfList(List<HashMap<String, String>> yfList) {
		Base.yfList = yfList;
	}

	/**
	 * ��������������map֮ǰ�����������µ�keyֵ
	 * String zykey���Լ���������� = xy==null ? "":xy;
	 * */
	public static HashMap<String, List<HashMap<String, String>>> getZyMap() {
		return zyMap;
	}

	public static void setZyMap(HashMap<String, List<HashMap<String, String>>> zyMap) {
		Base.zyMap = zyMap;
	}

	/**
	 * @return the cwfpZyMap
	 */
	public static HashMap<String, List<HashMap<String, String>>> getCwfpZyMap() {
		return cwfpZyMap;
	}

	/**
	 * @param cwfpZyMapҪ���õ� cwfpZyMap
	 */
	public static void setCwfpZyMap(HashMap<String, List<HashMap<String, String>>> cwfpZyMap) {
		Base.cwfpZyMap = cwfpZyMap;
	}

	public static HashMap<String, List<HashMap<String, String>>> getZyallMap() {
		return zyallMap;
	}

	public static void setZyallMap(HashMap<String, List<HashMap<String, String>>> zyallMap) {
		Base.zyallMap = zyallMap;
	}
	
	/**
	 *  ��ʹ������������mapǰ��Ҫ�����趨��Ӧ��keyֵ���Ա�ȡ����ͬ���б�
	 *  xy = xy==null ? "":xy;
		zy = zy==null ? "":zy;
		nj = nj==null ? "":nj;
		String bjKey ���Լ���������� = xy+"!!"+zy+"!!"+nj;
	 * */
	public static HashMap<String, List<HashMap<String, String>>> getBjMap() {
		return bjMap;
	}

	public static void setBjMap(
			HashMap<String, List<HashMap<String, String>>> bjMap) {
		Base.bjMap = bjMap;
	}
	
	public static HashMap<String, List<HashMap<String, String>>> getBjallMap() {
		return bjallMap;
	}

	public static void setBjallMap(
			HashMap<String, List<HashMap<String, String>>> bjallMap) {
		Base.bjallMap = bjallMap;
	}

	public static String getEndPointUri() {
		return endPointUri;
	}

	public static void setEndPointUri(String endPointUri) {
		Base.endPointUri = endPointUri;
	}

	public static String getBjlhBaseDataEndPointUri() {
		return bjlhBaseDataEndPointUri;
	}

	public static void setBjlhBaseDataEndPointUri(String bjlhBaseDataEndPointUri) {
		Base.bjlhBaseDataEndPointUri = bjlhBaseDataEndPointUri;
	}
	
	public static class initialBj implements Runnable {
		public void run() {
//			��ʼ��bjMap
			/*
			 * ��ѧԺ��רҵ���꼶Ϊ��ʱ��map�е�key����!!!!;���ֻ��ѧԺ����,����ѧԺ����Ϊ001��
			 * ��map�е�keyΪ001!!!!;�Դ����ơ�
			 */
			String xydm;
			String zydm;
			String sydm;
			DAO dao = DAO.getInstance();

			/*
			 *  xy = xy==null ? "":xy;
				zy = zy==null ? "":zy;
			    nj = nj==null ? "":nj;
			    String bjKey = xy+"!!"+zy+"!!"+nj;
			 *���Ҫѡ��ȫ����רҵ�����ǵ���ѡѧԺ��ʱ��map�ж�Ӧ��keyֵΪ"!!";
			 *�����Ӧ��keyֵΪ��Ӧ��ѧԺ���� 
			 */
			List<HashMap<String, String>> nullZyList = dao.getZyList("");
			(Base.zyMap).put("", nullZyList);
			for(HashMap<String, String> xy : Base.xyList){
				xydm = xy.get("xydm");
				List<HashMap<String, String>> subZyList = dao.getZyList(xydm);
				(Base.zyMap).put(xydm, subZyList);
			}
			
			List<HashMap<String, String>> allZyList = dao.getZyallList("");
			(Base.zyallMap).put("", allZyList);
			for(HashMap<String, String> xy : Base.xyallList){
				xydm = xy.get("xydm");
				List<HashMap<String, String>> subZyList = dao.getZyallList(xydm);
				(Base.zyallMap).put(xydm, subZyList);
			}
			
			List<HashMap<String, String>> allBjList = dao.getAllBjList();
			(Base.bjMap).put("!!!!", allBjList);
			
			//��λ����ѧԺרҵ�༶�꼶�������� modify by xiaxia
			
			List<HashMap<String, String>> cwfpZyList = dao.getAllZyNewList();
			(Base.cwfpZyMap).put("!!", cwfpZyList);

			//2018/12/02����Ա�����Ժ�༶���start
			List<HashMap<String,String>> njBjList = dao.getNjBjForSy();
			String synjkey;
			String syKey;
			//�Ż��·�ע�͵�������ѭ��
			for(HashMap<String,String> njbj : njBjList){
				synjkey = njbj.get("sydm") + "!!www" + njbj.get("nj");
				if((Base.bjallMap).containsKey(synjkey)){
					(Base.bjallMap).get(synjkey).add(njbj);
				} else {
					List<HashMap<String,String>> list = new ArrayList<HashMap<String, String>>();
					list.add(njbj);
					(Base.bjallMap).put(synjkey,list);
				}
				syKey = njbj.get("sydm") + "!!www";
				if((Base.bjallMap).containsKey(syKey)){
					(Base.bjallMap).get(syKey).add(njbj);
				} else {
					List<HashMap<String,String>> list = new ArrayList<HashMap<String, String>>();
					list.add(njbj);
					(Base.bjallMap).put(syKey,list);
				}
			}
			/*for(HashMap<String,String> sy : Base.syallList){
				sydm = sy.get("sydm");
				for(HashMap<String, String> njMap : Base.njallList){
					String nj = njMap.get("nj");
					List<HashMap<String,String>> njbjListForSy = new ArrayList<HashMap<String, String>>();
					for(HashMap<String,String> njbj : njBjList){
						if(sydm.equalsIgnoreCase(njbj.get("sydm"))
								&& nj.equalsIgnoreCase(njbj.get("nj"))){
							njbjListForSy.add(njbj);
						}
					}
					(Base.bjallMap).put(sydm+"!!www"+nj,njbjListForSy);
				}
			}*/
			(Base.bjallMap).put("!!www",njBjList);
			//2018/12/02����Ա�����Ժ�༶���end
			for (HashMap<String,String> xy : Base.xyallList) {
				//xydm!! ��Ӧ��רҵ�б�
				xydm = xy.get("xydm");
				List<HashMap<String,String>> zyList = new ArrayList<HashMap<String,String>>(); 
				for (HashMap<String,String> map : cwfpZyList){
					if (xydm.equalsIgnoreCase(map.get("xydm"))){
						zyList.add(map);
					}
				}
				(Base.cwfpZyMap).put(xydm+"!!", zyList);
				
				for(HashMap<String, String> njMap : Base.njallList){
					String nj = njMap.get("nj");
					 List<HashMap<String, String>> zyListForNj = new ArrayList<HashMap<String,String>>();                   
					 List<HashMap<String, String>> zyListForNjXy = new ArrayList<HashMap<String,String>>(); 
					 for (HashMap<String,String> map : cwfpZyList){
							if (nj.equalsIgnoreCase(map.get("nj"))){
								zyListForNj.add(map);
							}
							if (nj.equalsIgnoreCase(map.get("nj"))
									&& xydm.equalsIgnoreCase(map.get("xydm"))){
								zyListForNjXy.add(map);
							}
						}
					 (Base.cwfpZyMap).put("!!"+nj, zyListForNj);
					 (Base.cwfpZyMap).put(xydm+"!!"+nj, zyListForNjXy);
				}
			}
			//--------2011.9.16 �Ż�����------Penghui.Qu----------------
			for (HashMap<String,String> xy : Base.xyList){
				
				//xydm!!!! ��Ӧ�İ༶�б�
				xydm = xy.get("xydm");
				List<HashMap<String,String>> bjList = new ArrayList<HashMap<String,String>>(); 
				for (HashMap<String,String> map : allBjList){
					if (xydm.equalsIgnoreCase(map.get("xydm"))){
						bjList.add(map);
					}
				}
				(Base.bjMap).put(xydm+"!!!!", bjList);
				
				
				for(HashMap<String, String> zyMap : Base.zyMap.get(xydm)){
					//xydm!!zydm!! ��Ӧ�İ༶�б�
					zydm = zyMap.get("zydm");
					List<HashMap<String, String>> subBjList = new ArrayList<HashMap<String,String>>(); 
					for (HashMap<String,String> map : allBjList){
						if (xydm.equalsIgnoreCase(map.get("xydm")) && zydm.equalsIgnoreCase(map.get("zydm"))){
							subBjList.add(map);
						}
					}
					(Base.bjMap).put(xydm+"!!"+zydm+"!!", subBjList);
					(Base.bjMap).put("!!"+zydm+"!!", subBjList);
					
					for(HashMap<String, String> njMap : Base.njList){
						
						String nj = njMap.get("nj");
						 List<HashMap<String, String>> bjListForNj = new ArrayList<HashMap<String,String>>();                   
						 List<HashMap<String, String>> bjListForNjXy = new ArrayList<HashMap<String,String>>();                   
						 List<HashMap<String, String>> bjListForNjXyZy = new ArrayList<HashMap<String,String>>(); 
						 for (HashMap<String, String> map : allBjList) {
							
							if (nj.equalsIgnoreCase(map.get("nj"))){
								bjListForNj.add(map);
							} 
							
							if (nj.equalsIgnoreCase(map.get("nj"))
									&& xydm.equalsIgnoreCase(map.get("xydm"))){
								bjListForNjXy.add(map);
							}
							
							if (xydm.equalsIgnoreCase(map.get("xydm"))
									&& zydm.equalsIgnoreCase(map.get("zydm"))
									&& nj.equalsIgnoreCase(map.get("nj"))) {
								bjListForNjXyZy.add(map);
							}
						}
						 //xydm!!zydm!!nj ��Ӧ�İ༶�б�
						(Base.bjMap).put(xydm+"!!"+zydm+"!!"+nj, bjListForNjXyZy);
						//!!zydm!!nj ��Ӧ�İ༶�б�
						(Base.bjMap).put("!!"+zydm+"!!"+nj, bjListForNjXyZy);
						//!!!!nj ��Ӧ�İ༶�б�
						(Base.bjMap).put("!!!!"+nj, bjListForNj);
						//xydm!!!!nj  ��Ӧ�İ༶�б�
						(Base.bjMap).put(xydm+"!!!!"+nj, bjListForNjXy);
					}
				}
				
			}
			
			List<HashMap<String, String>> BjallList = dao.getBjAllList();
			(Base.bjallMap).put("!!!!", BjallList);
			
			
			//--------2011.9.16 �Ż�����------Penghui.Qu----------------
			for (HashMap<String,String> xy : Base.xyallList){
				
				//xydm!!!! ��Ӧ�İ༶�б�
				xydm = xy.get("xydm");
				List<HashMap<String,String>> bjList = new ArrayList<HashMap<String,String>>(); 
				for (HashMap<String,String> map : BjallList){
					if (xydm.equalsIgnoreCase(map.get("xydm"))){
						bjList.add(map);
					}
				}
				(Base.bjallMap).put(xydm+"!!!!", bjList);
				
				
				for(HashMap<String, String> zyMap : Base.zyallMap.get(xydm)){
					//xydm!!zydm!! ��Ӧ�İ༶�б�
					zydm = zyMap.get("zydm");
					List<HashMap<String, String>> subBjList = new ArrayList<HashMap<String,String>>(); 
					for (HashMap<String,String> map : BjallList){
						if (xydm.equalsIgnoreCase(map.get("xydm")) && zydm.equalsIgnoreCase(map.get("zydm"))){
							subBjList.add(map);
						}
					}
					(Base.bjallMap).put(xydm+"!!"+zydm+"!!", subBjList);
					(Base.bjallMap).put("!!"+zydm+"!!", subBjList);
					
					for(HashMap<String, String> njMap : Base.njallList){
						
						String nj = njMap.get("nj");
						 List<HashMap<String, String>> bjListForNj = new ArrayList<HashMap<String,String>>();                   
						 List<HashMap<String, String>> bjListForNjXy = new ArrayList<HashMap<String,String>>();                   
						 List<HashMap<String, String>> bjListForNjXyZy = new ArrayList<HashMap<String,String>>(); 
						 for (HashMap<String, String> map : BjallList) {
							
							if (nj.equalsIgnoreCase(map.get("nj"))){
								bjListForNj.add(map);
							} 
							
							if (nj.equalsIgnoreCase(map.get("nj"))
									&& xydm.equalsIgnoreCase(map.get("xydm"))){
								bjListForNjXy.add(map);
							}
							
							if (xydm.equalsIgnoreCase(map.get("xydm"))
									&& zydm.equalsIgnoreCase(map.get("zydm"))
									&& nj.equalsIgnoreCase(map.get("nj"))) {
								bjListForNjXyZy.add(map);
							}
						}
						 //xydm!!zydm!!nj ��Ӧ�İ༶�б�
						(Base.bjallMap).put(xydm+"!!"+zydm+"!!"+nj, bjListForNjXyZy);
						//!!zydm!!nj ��Ӧ�İ༶�б�
						(Base.bjallMap).put("!!"+zydm+"!!"+nj, bjListForNjXyZy);
						//!!!!nj ��Ӧ�İ༶�б�
						(Base.bjallMap).put("!!!!"+nj, bjListForNj);
						//xydm!!!!nj  ��Ӧ�İ༶�б�
						(Base.bjallMap).put(xydm+"!!!!"+nj, bjListForNjXy);
					}
				}
				
			}
			
			
			Base.setXnndList(dao.getXnndList());
			Base.setXnndList2(dao.getXnndList2());
			Base.setNjList(dao.getNjList());
			Base.setNjallList(dao.getNjallList());
			Base.setXyList(dao.getXyList());
			Base.setXyallList(dao.getXyallList());
			Base.setSyallList(dao.getSyallList());
			Base.setXqList(dao.getXqList());
			Base.setYfList(dao.getYfList());
			
			// ====================�꼶��ѧԺ��רҵ���༶START ��ʾ���С�ѧ����׷�ӡ�=================================
			Base.setNjNewList(dao.getNjNewList());
			Base.setXyNewList(dao.getXyNewList());
//			Base.setZyNewList(dao.getZyNewList(""));
//			Base.setBjNewList(dao.getBjNewList());

			// ====================�꼶��ѧԺ��רҵ���༶ END ��ʾ���С�ѧ����׷�ӡ�=================================
			
			SearchService search = new SearchService();
			// ѧԺ
			List<HashMap<String, Object>> xyTjList = search.getNewXyPxList("","","","");
			// רҵ
			List<HashMap<String, Object>> zyTjList = search.getNewZyPxList(null,"","","","");
			// �༶
			List<HashMap<String, Object>> bjTjList = search.getNewBjPxList(null,"","","","");
			
			SearchService.setXyTjList(xyTjList);
			SearchService.setZyTjList(zyTjList);
			SearchService.setBjTjList(bjTjList);

			// �꼶[ȫ]
			List<HashMap<String, String>> njNewTjList = search.getNjNewTjList();
			
			// ѧԺ[ȫ]
			List<HashMap<String, Object>> xyNewTjList = search.getXyNewList("","","");
			// רҵ[ȫ]
			List<HashMap<String, Object>> zyNewTjList = search.getZyNewList(null,"","","");
			// �༶[ȫ]
			List<HashMap<String, Object>> bjNewTjList = search.getBjNewList(null,"","","");

			SearchService.setNjNewTjList(njNewTjList);
			SearchService.setXyNewTjList(xyNewTjList);
			SearchService.setZyNewTjList(zyNewTjList);
			SearchService.setBjNewTjList(bjNewTjList);
			
		/*	for(HashMap<String, String> xy : Base.xyList){
				xydm = xy.get("xydm");
				List<HashMap<String, String>> BjList = dao.getBjList(xydm,null);
				(Base.bjMap).put(xydm+"!!!!", BjList);
				for(HashMap<String, String> zy : Base.zyMap.get(xy.get("xydm"))){
					zydm = zy.get("zydm");
					List<HashMap<String, String>> subBjList = dao.getBjList(xydm,zydm);
					(Base.bjMap).put(xydm+"!!"+zydm+"!!", subBjList);
					(Base.bjMap).put("!!"+zydm+"!!", subBjList);
					for(HashMap<String, String> nj : Base.njList){
						 List<HashMap<String, String>> subsubBjList = dao.getBjList(xydm,zydm,nj.get("nj"));
						(Base.bjMap).put(xydm+"!!"+zydm+"!!"+nj.get("nj"), subsubBjList);
						(Base.bjMap).put("!!"+zydm+"!!"+nj.get("nj"), subsubBjList);
						(Base.bjMap).put("!!!!"+nj.get("nj"), dao.getBjList(null, null, nj.get("nj")));
						(Base.bjMap).put(xydm+"!!!!"+nj.get("nj"), dao.getBjList(xydm, null, nj.get("nj")));
					}
				}
			}*/
		}
		
	}
	
	
//	public static String queryPjzq(HashMap<String, String> map, String xnKey,
//			String xqKey, String ndKey) {
//		String result = "";
//		if (map != null) {
//			if ("checked".equalsIgnoreCase(map.get(xqKey))) {
//				result = "xq";
//			} else if ("checked".equalsIgnoreCase(map.get(xnKey))) {
//				result = "xn";
//			} else if ("checked".equalsIgnoreCase(map.get(ndKey))) {
//				result = "nd";
//			} else {
//				System.out.println("�������ڻ��۲����ڳ�ʼ��ʧ��.");
//			}
//		}
//		return result;
//	}

	public static HashMap<String, String> getInitProperties() {
		return initProperties;
	}

	public static void setInitProperties(String initName,String initValue) {
		Base.initProperties.put(initName, initValue);
	}

	public static String getDqxqmc() {
		return dqxqmc;
	}

	public static void setDqxqmc(String dqxqmc) {
		Base.dqxqmc = dqxqmc;
	}

	public static String getJxjsqxqmc() {
		return jxjsqxqmc;
	}

	public static void setJxjsqxqmc(String jxjsqxqmc) {
		Base.jxjsqxqmc = jxjsqxqmc;
	}

	public static String getWebservicePass() {
		return webservicePass;
	}

	public static void setWebservicePass(String webservicePass) {
		Base.webservicePass = webservicePass;
	}

	public static String getWebserviceUser() {
		return webserviceUser;
	}

	public static void setWebserviceUser(String webserviceUser) {
		Base.webserviceUser = webserviceUser;
	}

	public static String getJwflag() {
		return jwflag;
	}

	public static void setJwflag(String jwflag) {
		Base.jwflag = jwflag;
	}

	public static String getPjzq() {
		return pjzq;
	}

	public static void setPjzq(String pjzq) {
		Base.pjzq = pjzq;
	}

	public static String getZczq() {
		return zczq;
	}

	public static void setZczq(String zczq) {
		Base.zczq = zczq;
	}

	public static String getSuperSearch() {

		if (Base.isNull(superSearch)) {

			superSearch = XMLReader.getFlowControl("comm", "superSearch");

			setSuperSearch(superSearch);
		}

		return superSearch;
	}

	public static void setSuperSearch(String superSearch) {
		Base.superSearch = superSearch;
	}

	public static String getEdition() {

		if (Base.isNull(edition)) {

			edition = Base.initProperties.get("edition");

			setEdition(edition);
		}

		return edition;
	}

	public static void setEdition(String edition) {
		Base.edition = edition;
	}
	
	public static String getXsxxwh() {

		if (Base.isNull(xsxxwh)) {

			xsxxwh = XMLReader.getFlowControl("xsxx", "xsxxwh");

			setXsxxwh(xsxxwh);
		}

		return xsxxwh;
	}

	public static void setXsxxwh(String xsxxwh) {
		Base.edition = edition;
	}
	
	public static void setUserLogin(String userLogin) {
		Base.userLogin = userLogin;
	}
	
	public static String getUserLogin() {
		if (Base.isNull(userLogin)) {
			
			userLogin = XMLReader.getFlowControl("login", "userLogin");

			setUserLogin(userLogin);
		}

		return userLogin;
	}
	
	public static String getNewsQuery() {
		if (Base.isNull(newsQuery)) {

			edition = Base.initProperties.get("newsQuery");

			setNewsQuery(newsQuery);
		}

		return edition;
	}

	public static void setNewsQuery(String newsQuery) {
		Base.newsQuery = newsQuery;
	}

	public static boolean isHistory() {
		return isHistory;
	}

	public static void setHistory(boolean isHistory) {
		Base.isHistory = isHistory;
	}

	public static List<HashMap<String, String>> getNjNewList() {
		return njNewList;
	}

	public static void setNjNewList(List<HashMap<String, String>> njNewList) {
		Base.njNewList = njNewList;
	}

	public static List<HashMap<String, String>> getXyNewList() {
		return xyNewList;
	}

	public static void setXyNewList(List<HashMap<String, String>> xyNewList) {
		Base.xyNewList = xyNewList;
	}

	public static HashMap<String, List<HashMap<String, String>>> getBjNewList() {
		return bjNewList;
	}

	public static void setBjNewList(
			HashMap<String, List<HashMap<String, String>>> bjNewList) {
		Base.bjNewList = bjNewList;
	}

	public static HashMap<String, List<HashMap<String, String>>> getZyNewList() {
		return zyNewList;
	}

	public static void setZyNewList(
			HashMap<String, List<HashMap<String, String>>> zyNewList) {
		Base.zyNewList = zyNewList;
	}
}

class TTask extends TimerTask{
	private boolean flag;
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public TTask(boolean f){
		this.flag = f;
	}
	public void run() {
		try{
			//System.out.println("���ڳ�ʼ�����У���ʼ��Ǯ������^_^");
			//int i=1;
			while(isFlag()){
				//System.out.print(".");//
				Thread.sleep(1000);
				//i++;
				//if((i=(i%20)) == 1) System.out.println("���еı����������������ɣ�");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}			
}

class QgzxZgdzdxTask extends TimerTask{
	DAO dao = DAO.getInstance();
	public void run() {
		try{			
			while(true){
				String sql = "select shjssj, to_char(sysdate,'YYYYMMDDHH24MISS')nowtime from gwsqsjb";
				HashMap<String, String> timeMap = new HashMap<String, String>();
				timeMap = dao.getMap(sql, new String[]{}, new String[]{"shjssj", "nowtime" });
				String shjssj = timeMap.get("shjssj");
				String nowTime = timeMap.get("nowtime");
				if(shjssj != null || "".equalsIgnoreCase(shjssj)){
					if(Double.parseDouble(shjssj)<Double.parseDouble(nowTime)){
						dao.runUpdate("delete from xsgwxxb where xyyj='δ���'",new String[]{});
					}
				}
				Thread.sleep(1000);				
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}

/**
 * ���ݹ�ҵ԰�������ۺϲ�����������
 * @author gaobb
 */
class SzgyyqJsZhcpFspm extends TimerTask{

	@Override
	public void run() {
		 try {
			 while(true){
				 Calendar lastDate = Calendar.getInstance(); 
				 
				 lastDate.add(Calendar.DATE, 1);
				 
//				 lastDate.add(Calendar.MONTH,1);//��һ����    
//				 lastDate.set(Calendar.DATE, 1);//����������Ϊ���µ�һ��     
				 lastDate.set(Calendar.AM_PM, 0);
				 lastDate.set(Calendar.HOUR, 0);
				 lastDate.set(Calendar.MINUTE, 0);
				 lastDate.set(Calendar.SECOND, 0);
				 lastDate.set(Calendar.MILLISECOND, 0);
				 
//				 
				 System.out.println("�Զ�����ʱ��:"+(lastDate.getTimeInMillis()-System.currentTimeMillis()));
				 
				 Thread.sleep(lastDate.getTimeInMillis()-System.currentTimeMillis());//���������µ�һ�ŵ��賿

				 new PjpyTeaDAO().jsfspm(new PjpyTeaForm(), null);//��ʼ�����ۺϲ�ֺ�����
			 }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
