
package xgxt.wjcf.wjcfutils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForward;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.form.User;
import xgxt.pjpy.PjpyTyService;
import xgxt.pjpy.czxx.jxj.JxjService;
import xgxt.pjpy.jgsdx.PjpyJgsdxService;
import xgxt.pjpy.zjkjxy.PjpyZjkjxyService;
import xgxt.utils.Fdypd;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;
import xgxt.wjcf.gdby.WjcfGdbyService;
import xgxt.xtwh.xtwhOther.XtwhOtherService;

import com.zfsoft.basic.BasicAction;
import common.GlobalsVariable;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: ��������Action ͨ�õĹ�����,���ڼ����б�,���ҳ���б����Ϣ
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: ����</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-10-31</p>
 */
public class CommonAction extends BasicAction {
	
	protected static final String QUERY = "qry";//ҳ���ѯ�����жϱ�־
	
	protected static final String DELETE = "del";//ҳ��ɾ�������жϱ�־
	
	protected static final String VIEW = "view";//ҳ�浥����ʾ��ϸ�����жϱ�־
	
	protected static final String MODIFY = "modi";//ҳ���޸Ĳ����жϱ�־

	protected static final String SAVE = "save";//ҳ�汣������жϱ�־
	
	protected static final String AUTOACCOUNT = "auto";//ҳ���Զ���������жϱ�־
	
	protected static final String SH_TG = "tg";//���ͨ��
	
	protected static final String SH_BTG = "btg";//��˲�ͨ��
	
	protected static final String SH = "sh";
	
	protected static final String EXPORT = "export";//����
	
	protected static final String SB_TG = "sb";//�ϱ�
	
	protected static final String SB_QX = "qxsb";//ȡ���ϱ�

	/**
	 * �����û�����
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String getUserType(HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		return session.getAttribute("userType") == null ? "" : session
				.getAttribute("userType").toString();
	} 
	
	/**
	 * �����û�����
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String getUserDep(HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		return session.getAttribute("userDep") == null ? "" : session
				.getAttribute("userDep").toString();
	}
	
	/**
	 * ���÷���:��REQUEST�д��ҳ����Ҫ���ص�LIST����
	 * @param request
	 * @param xydm
	 * @param zydm
	 * @param nj
	 * @param xq
	 * @throws Exception
	 */
	public void appendProperties(HttpServletRequest request, String xydm,
			String zydm, String nj) throws Exception {
		WjcfGdbyService service = new WjcfGdbyService();
		String fdyQx = request.getSession().getAttribute("fdyQx").toString();
		boolean flg = "true".equalsIgnoreCase(fdyQx) ? true : false;
		String xy = "";
		String zy = "";
		String njLocal = nj;
		xy = xy==null ? "": xydm; 
		zy = zy==null ? "": zydm;
		if (StringUtils.isNull(xy)) {
			xy = "";
		}
		if (StringUtils.isNull(zy)) {
			zy = "";
		}
		njLocal = nj==null ? "": nj;
		if (StringUtils.isNull(njLocal)) {
			njLocal = "";
		}
//		String zyKey = xy==null ? "":xy; 
//		String bjKey = xy+"!!"+zy+"!!"+njLocal;
		String userName = request.getSession().getAttribute("userName")
				.toString();
		String userType = request.getSession().getAttribute("userType") == null ? ""
				: request.getSession().getAttribute("userType").toString();
		request.setAttribute("writeAble", "yes");//�ж��û���дȨ
		request.setAttribute("xqList", Base.getXqList());//ѧ���б�
		request.setAttribute("xnList", Base.getXnndList());//ѧ���б�
		request.setAttribute("njList", Base.getNjList());//�꼶�б�
		request.setAttribute("xyList", Base.getXyList());//ѧԺ�б�
		request.setAttribute("zyList", service.getZyList(xydm));//רҵ�б�
		request.setAttribute("bjList", service.getBjList(xydm, zydm, nj));//�༶�б�
		request.setAttribute("xnList2", makeXnndListWithoutNullOption());//ѧ��û�е�һ�п�ֵ�б�
		//request.setAttribute("zyList", Base.getZyMap().get(zyKey));//רҵ�б�
		//request.setAttribute("bjList", Base.getBjMap().get(bjKey));//�༶�б�
		request.setAttribute("userType", userType);//�û�����
		if (Fdypd.isFdy(userName) && flg) {
			// ����Ա��¼
			request.setAttribute("bjList", Fdypd.getFdybjList(userName));// ���Ͱ༶�б�
			request.setAttribute("zyList", Fdypd.getFdyZyList(userName));// ����רҵ�б�
			request.setAttribute("xyList", Fdypd.getFdyXyList(userName));// ���Ͱ༶�б�
		}
	}
	
	//��������ѧ�꣬ѧ�ڣ�����б�
	public void appendXnxqndList(HttpServletRequest request) {
		request.setAttribute("xqList", Base.getXqList());//ѧ���б�
		request.setAttribute("xnList", Base.getXnndList());//ѧ��,����б� key:xn,nd
	}

	private List<HashMap<String, String>> makeXnndListWithoutNullOption() {
		List<HashMap<String, String>> result =  new ArrayList<HashMap<String,String>>();
		result.addAll(Base.getXnndList());
		if(result.size()!=0 && Base.isNull(result.get(0).get("xn"))){
			result.remove(0);
		}
		return result;
	}
	
	/**
	 * ���÷���
	 *    ����û��Ǹ���Ա,��ֻ��ȡ�����༶
	 * @param request
	 * @throws Exception
	 */
	public void appendFdybjList(HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		String userName = session.getAttribute("userName") == null ? ""
				: session.getAttribute("userName").toString();
		String isFdy = session.getAttribute("isFdy") == null ? "" : session
				.getAttribute("isFdy").toString();
		if ("true".equalsIgnoreCase(isFdy)) {
			WjcfGdbyService service = new WjcfGdbyService();
			request.setAttribute("bjList", service.getFdybjList(userName));
		}
	}
	
	/**
	 * ��ȡ������𣬴���ԭ���б�
	 * @param request
	 * @throws Exception
	 */
	public void appendCflbCfyy(HttpServletRequest request) throws Exception {
		WjcfGdbyService service = new WjcfGdbyService();
		List<HashMap<String, String>> cflbList = service.getCflbList();//��ȡ��������б�
		List<HashMap<String, String>> cfyyList = service.getCfyyList();//��ȡ����ԭ���б�
		request.setAttribute("cflbList", cflbList);
		request.setAttribute("cfyyList", cfyyList);
	}
	
	/**
	 * ��ѯ�б�
	 * @param request
	 * @param topList
	 * @param resList
	 * @throws Exception
	 */
	public void appendResult(HttpServletRequest request,
			List<HashMap<String, String>> topList, List<String[]> resList)
			throws Exception {
		request.setAttribute("topTr", topList);// ��ͷ
		request.setAttribute("rs", resList);// ���
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);// ��¼��
	}
	
	/**
	 * ��������
	 * @param request
	 * @param pkValue
	 * @throws Exception
	 */
	public void appendPkValue(HttpServletRequest request, String pkValue)
			throws Exception {
		request.setAttribute("pkValue", pkValue);
	}
	
	/**
	 * ���ʱ��ʾ��ϸ��Ϣ
	 * @param request
	 * @param resMap
	 * @throws Exception
	 */
	public void appendViewMap(HttpServletRequest request,
			HashMap<String, String> resMap) throws Exception {
		request.setAttribute("rs", resMap);
	}
	
	/**
	 * ��ȡ����б�:ͨ��,��ͨ��,δ���
	 * @param request
	 * @throws Exception
	 */
	public void appendChkList(HttpServletRequest request) throws Exception {
		WjcfGdbyService service = new WjcfGdbyService();
		List<HashMap<String, String>> chkList = service.getChkList(3);
		request.setAttribute("chkList", chkList);
	}
	
	/**
	 * ��ȡ��ѧ���б�
	 * @param request
	 * @throws Exception
	 */
	public void appendJxjList(HttpServletRequest request) throws Exception {
		PjpyJgsdxService service = new PjpyJgsdxService();
		List<HashMap<String, String>> jxjList = service.getJxjList();
		request.setAttribute("jxjList", jxjList);
	} 
	
	/**
	 * ��ȡ�����ƺ��б�
	 * @param request
	 * @throws Exception
	 */
	public void appendRychList(HttpServletRequest request) throws Exception {
		PjpyJgsdxService service = new PjpyJgsdxService();
		List<HashMap<String, String>> rychList = service.getRychList();
		request.setAttribute("rychList", rychList);
	}
	
	/**
	 * ��ѯ���
	 * @param request
	 * @param titList
	 * @param rsList
	 * @throws Exception
	 */
	public void appendQryResult(HttpServletRequest request,
			List<HashMap<String, String>> titList, List<String[]> rsList){
		request.setAttribute("topTr", titList);//��ͷ
		request.setAttribute("rs", rsList);//���
		request.setAttribute("rsNum", rsList==null ? 0 : rsList.size());//��¼��
	}
	
	/**
	 * �����������ͼ��
	 * @param request
	 * @param tableName
	 * @param realTable
	 * @throws Exception
	 */
	public void appendTableXx(HttpServletRequest request, String tableName,
			String realTable) {
		request.setAttribute("tableName", tableName);
		request.setAttribute("realTable", realTable);
	}
	
	public HashMap<String, String> getStuInfo(String xh) throws Exception {
		PjpyJgsdxService service = new PjpyJgsdxService();
		return service.getStuInfo(xh);
	}
	
	/**
	 * ���ؽ�ѧ������ѧ�꣬ѧ�ڣ����
	 * @param request
	 */
	public void appendPjxnxqnd(HttpServletRequest request) {
		request.setAttribute("xn", Base.getJxjsqxn());
		request.setAttribute("nd", Base.getJxjsqnd());
		request.setAttribute("xqmc", Base.getJxjsqxqmc());
	}
	
	/**
	 * ���ؾ�ѵ��������б�
	 * @param request
	 */
	public void appendJxjxdmList(HttpServletRequest request) throws Exception{
		WjcfGdbyService service = new WjcfGdbyService();
		request.setAttribute("jxjxdmList", service.getJxjxdmList());
	}
	
	public void commonRequestSet(HttpServletRequest request, String tableName,
			String realTable, ArrayList<String[]> rs, List topTr) {
		// Request��ֵ��ͨ�÷��� ������title�����ݿ���ȡ
		String writeAble  = request.getParameter("writeAble");
		String title      = DealString.toGBK(request.getParameter("title"));
		if(Base.isNull(writeAble)) {
			String [] message = getWriteAbleAndTitle(request);
			writeAble = message[0];
			title     = message[1];
		}
		
		if(rs!=null){
			request.setAttribute("rsNum", rs.size());
		}
		request.setAttribute("rs", rs);
		request.setAttribute("topTr", topTr);
		request.setAttribute("title", title);
		request.setAttribute("writeAble", writeAble);
		request.setAttribute("tableName", tableName);
		request.setAttribute("realTable", realTable);
	}
	
	public String [] getWriteAbleAndTitle(HttpServletRequest request){
		//����request,����ҳ����⣨�ɹ���ģ���������ɣ��Ͷ�дȨ�����ص������һ��ֵ�Ƕ�дȨ���ڶ����Ǳ���
		//ʹ��ʱҳ����Ҫ����nameΪtitle��������
		HttpSession session = request.getSession();						//�õ�session
		String userType = "";
		boolean isStu = true;
		String sUName = "";
		String sPath = "";
		String qStr = "";
		String power = "";
		String writeAble = "";
		userType = session.getAttribute("userType").toString();   //�õ��û�����
		isStu = (userType.equalsIgnoreCase("stu"));				 //�ж��Ƿ���ѧ���û���¼
		sUName = session.getAttribute("userName").toString();	//�õ���¼�û���
		if(request.getAttribute("path")!=null){
			power = (String)request.getAttribute("path");
		}else{
			sPath = request.getServletPath().replace("/", "");
			qStr = request.getQueryString();		
			if("".equalsIgnoreCase(qStr) || qStr == null){
				power = sPath;
			}else{
				power = sPath + "?" + qStr;
			}
		}
		String messTemp[] = chkUPower(sUName, power, isStu);
		if (messTemp == null 
				||(!Base.isNull(messTemp[0]) && messTemp[0].equals("0"))) {
			// ֻ��
			writeAble = "no";
		} else if (!Base.isNull(messTemp[0]) && messTemp[0].equals("1")) {
			// ��д
			writeAble = "yes";
		}
		return new String [] {writeAble,messTemp != null && messTemp.length>0 ? messTemp[1] : null};
	}
	
	public String [] chkUPower(String uName, String modID, boolean isStu) {
		String sql = "";
		DAO daoBase = DAO.getInstance();
		if (!isStu) {
			sql = "select nvl(dxq,'0') dxq,(select gnmkmc from gnmkdmb where gnmkdm = " +
					"substr(a.gnmkdm,0,3))||'-'||(select gnmkmc from gnmkdmb where gnmkdm = " +
					"substr(a.gnmkdm,0,5))||'-'||gnmkmc title from view_yhqx a where yhm=? and dyym=?";
		} else {
			sql = "select nvl(dxq,'0') dxq,(select gnmkmc from gnmkdmb where gnmkdm = " +
					"substr(a.gnmkdm,0,3))||'-'||(select gnmkmc from gnmkdmb where gnmkdm = " +
					"substr(a.gnmkdm,0,5))||'-'||gnmkmc title from view_yhzqx a where zdm=? and dyym=?";
			uName = "6727";
		}

		String[] ress = daoBase.getOneRs(sql, new String[] { uName, modID }, new String[]{"dxq","title"});
		return ress;
	}
	
	public void appendOperResult(HttpServletRequest request, boolean result) {
		String res = result ? "yes" : "no";
		request.setAttribute("inserted", res);
	}
	
	public void appendOperQx(HttpServletRequest request, String path) {
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
	}
	
	/**
	 * ��ѯѧ��������Ϣ
	 * @param request
	 * @param paramMap
	 */
	public void setStuWjcfxx(HttpServletRequest request, HashMap<String, String> paramMap) {
		JxjService service = new JxjService();
		request.setAttribute("cfList", service.queryStuWjcfxx(paramMap));
	}
	
	/**
	 * ��ѯѧ���ɼ���Ϣ
	 * @param request
	 * @param paramMap
	 */
	public void setStuKccjxx(HttpServletRequest request, HashMap<String, String> paramMap) {
		JxjService service = new JxjService();
		request.setAttribute("cjList", service.queryStuKccjxx(paramMap));
	}
	
	/**
	 * ��ȡSESSION����
	 * @param request
	 * @return
	 */
	public HttpSession getSession(HttpServletRequest request) {
		return request.getSession(); 
	}
	
	/**
	 * ����SESSION�����ĳ���������ƣ���������ֵ
	 * @param attrName
	 * @return
	 */
	public String getSessionAttributeValue(HttpServletRequest request,
			String attrName) {
		HttpSession sessiones = getSession(request);
		return sessiones == null ? ""
				: (sessiones.getAttribute(attrName) != null ? sessiones
						.getAttribute(attrName).toString() : "");
	}
	
	/**
	 * ��ȡ��˽���ַ���
	 * @param request
	 * @return
	 */
	public String getShjgString(HttpServletRequest request) {
		String jg = request.getParameter("jg");
		return SH_TG.equalsIgnoreCase(jg) ? "ͨ��"
				: (SH_BTG.equalsIgnoreCase(jg) ? "��ͨ��" : "δ���");
	}
	
	public void appendOperResultMes(HttpServletRequest request, boolean result) {
		request.setAttribute("result", result);
	}
	
	/**
	 * ���ؿ��ƣ��ر�ת����ʾ��Ϣҳ��
	 * @param request
	 * @param gndm
	 * */	
	public ActionForward controlOnOffToPage(HttpServletRequest request,String gndm){
		User user = getUser(request);
		XtwhOtherService xtwhService = new XtwhOtherService();
		PjpyTyService tyService = new PjpyZjkjxyService();
		
		if(!GlobalsVariable.XTDM_ADMIN.equalsIgnoreCase(user.getUserType())
				&&!xtwhService.gnkgFlag(gndm)
				&& tyService.checkKgflag()){
			String msg = "�ù�����ʱ�����Ų�����";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}else{
			return null;
		}
	}
}
