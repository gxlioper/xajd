package xgxt.szdw.whlgdx;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.DAO.DAO;
import xgxt.DAO.SxjyDAO;
import xgxt.action.Base;
import xgxt.form.CommanForm;
import xgxt.form.FdyglForm;
import xgxt.utils.String.StringUtils;

public class Whlgdx_InfoPubAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
	HttpServletRequest request, HttpServletResponse response) throws Exception {
		Base.chkSessionTimeOut(request.getSession());  //���session�Ƿ����
		//FdyglForm fdyglForm = (FdyglForm)form;
		ActionForward myActFwd = null;
		String myAct = mapping.getParameter();
		try{
			if ("fdyxxOne_whlg".equalsIgnoreCase(myAct)) {
				myActFwd = fdyxxOne_Whlgdx(mapping, form, request, response);
			}
		}catch (Exception e) {
			e.printStackTrace();
			CommanForm commanForm = new CommanForm();
			commanForm.setErrMsg("���������жϣ������µ�½��");
			return new ActionForward("/login.jsp", false);
		}
		return myActFwd;
	}
	//����人����ѧ
	private ActionForward fdyxxOne_Whlgdx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		FdyglForm fdyglForm = (FdyglForm)form;
		DAO dao = DAO.getInstance();
		String act = request.getParameter("act");
		HashMap<String, String> map = new HashMap<String, String>();	
		SxjyDAO sxjyDao = new SxjyDAO();
		String sql = "";
		map.put("stuExists", "yes");
		String userDep = request.getSession().getAttribute("userDep").toString(); //�����
		String userType = dao.getUserType(userDep);
		String type = request.getParameter("type");
		type = StringUtils.isNull(type) ? "" : type;
		//�ж����
		HttpSession session = request.getSession();
		if(session.getAttribute("isFdy").toString().equalsIgnoreCase("true")){
			request.setAttribute("userJb", "fdy");
		}else if(session.getAttribute("userType").equals("xy")){
			request.setAttribute("userJb", "xy");
		}else if(session.getAttribute("userType").equals("xx") 
			|| session.getAttribute("userType").equals("admin")){
			request.setAttribute("userJb", "xx");
		}else{
			request.setAttribute("userJb", "stu");
		}
		
		String userName = request.getSession().getAttribute("userName").toString();
		String[] colList = { "zgh", "xm", "xb", "lxdh","bmdm", "zw", "zyzz", "zzmm", 
				"xl", "csrq", "mz", "zc","lxgzsj", "xsgzyjfx", "jtzz", "yddh", "dzyx", "fblw",
				"kyjl", "jg", "grhjqk", "gzjl", "bz", "bkbyyx", "bksxzy","ssbyyx", "ssbyzy","bsbyyx",
				"bsbyzy","yzbm","xw","zhxwssxk","csgz","zwrzsj","sjdw","jrgz","sfzh","cjgzrq","szgzsj",
				"sfbl","jb","djsj","pxqk"};
		if ((act != null) && !act.equalsIgnoreCase("save")) {
			String pk = "";
			if(type.equalsIgnoreCase("one")){
				pk = userName;
				//writeAble=CheckPower.checkUsrPower(session.getAttribute("userName").toString(), "fdyxxOne.do?act=view&type=one")?"yes":"no";
				request.setAttribute("ddxg", "yes");
			}else{
				pk = request.getParameter("pk");
				pk = StringUtils.isNull(pk) ? "" : pk;
			}
			sql = "select * from fdyxxb where zgh=?";
			String[] colV = dao.getOneRs(sql, new String[] { pk }, colList);
			if (colV == null) {
				if(type.equalsIgnoreCase("one")){
					sql = "select yhm from yhb where zdm = '7444' and yhm = ?";   //˧ѡ����Ա�������Ϊ7444
					String[] sffdy = dao.getOneRs(sql, new String[] { pk }, new String[] {"yhm"});
					if(null == sffdy){
						request.setAttribute("ffdy", "yes");
					}
				}
				colV = new String[colList.length];
			}
			for (int i = 0; i < colList.length; i++) {
				map.put(colList[i], StringUtils.isNull(colV[i]) ? "" : colV[i]);
			}
			if ("xy".equalsIgnoreCase(userType)) {
				map.put("bmdm", userDep);
			}
			if(type.equalsIgnoreCase("one")){
				map.put("zgh",userName);
			}
			String[] bjList = null;
			bjList = sxjyDao.getFdyBjList(pk);
			String[] zyList = sxjyDao.getZyBjList(pk);
			request.setAttribute("fdybjList", bjList);//���ظ���Ա�����İ༶�б�
			map.put("sdxsrs", sxjyDao.getFdyBjRsList(pk));//���ظ���Ա����ѧ����������
			request.setAttribute("fdyzyList", zyList);
			request.setAttribute("xbList", dao.getSexList());
			request.setAttribute("doType", act);
			request.setAttribute("pkValue", pk);
		} else if ((act != null) && act.equalsIgnoreCase("save")) {
			String ddxg = request.getParameter("ddxg");
			ddxg = StringUtils.isNull(ddxg) ? "" : ddxg;
			if(ddxg.equalsIgnoreCase("yes")){
				request.setAttribute("ddxg", "yes");
			}
			fdyglForm.deal_Whlgdx_GBK();
			if ("xy".equalsIgnoreCase(userType)) {
				fdyglForm.setBmdm(userDep);
			}
			Vector<HashMap<String, Object>> sqlVal = new Vector<HashMap<String, Object>>();
			HashMap<String, Object> sqls = new HashMap<String, Object>();
			sqls.put("sqlTxt", "delete fdyxxb where zgh=?");
			sqls.put("sqlVal", new String[] {fdyglForm.getPkValue()});
			sqlVal.add(sqls);
			sqls = new HashMap<String, Object>();
			sqls.put("sqlTxt",
			     "insert into fdyxxb(zgh,xm,xb,lxdh,bmdm,zw,zyzz,zzmm,xl,csrq,mz,zc,lxgzsj,xsgzyjfx," +
			     "jtzz,yddh,dzyx,fblw,kyjl,jg,grhjqk,gzjl,bz,bkbyyx,bksxzy,ssbyyx,ssbyzy,bsbyyx," +
			     "bsbyzy,yzbm,xw,zhxwssxk,csgz,zwrzsj,sjdw,jrgz,sfzh,cjgzrq,szgzsj,sfbl,jb,djsj,pxqk)" 
			     +"values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			sqls.put("sqlVal",getProperties(colList,fdyglForm));
			sqlVal.add(sqls);
			boolean res = dao.runUpdate(sqlVal);
			if (res) {
				request.setAttribute("added", "ok");
			} else {
				request.setAttribute("added", "no");
			}
		}
		if("xy".equalsIgnoreCase(userType)){
			request.setAttribute("writeAble", "no"); //�޷��޸�
		}else if("xx".equalsIgnoreCase(userType)||"admin".equalsIgnoreCase(userType)){
			request.setAttribute("writeAble", "yes");
		}
		request.setAttribute("rs", map);
		sql = "select zwdm,zwmc from zwb";  //ְ���		
		List zwList = dao.getList(sql, new String[] {}, new String[] { "zwdm","zwmc" });
		request.setAttribute("bmList", dao.getXyList());
		request.setAttribute("zwList", zwList);                       //ְ���б�
		request.setAttribute("JsZzmmList", sxjyDao.getJsZzmmList());  //������ò
		request.setAttribute("xlList", sxjyDao.getXlListFor_Whlg());  //ѧ���б�
		request.setAttribute("sfList", sxjyDao.getSfList());          //ʡ���б�
		request.setAttribute("mzList", dao.getMzList());              //�����б�
		request.setAttribute("xwList", sxjyDao.getXwListFor_Whlg());  //ѧλ�б�
		request.setAttribute("xkList", sxjyDao.getXkListFor_Whlg());  //ѧ���б�
		request.setAttribute("gzList", sxjyDao.getGzListFor_Whlg());  //�����б�
		request.setAttribute("zcList", sxjyDao.getZcListFor_Whlg());  //ְ���б�
		request.setAttribute("jbList", sxjyDao.getJbListFor_Whlg());  //�����б�
		request.setAttribute("sfblList", sxjyDao.getSfBlListFor_Whlg());//�Ƿ�B���б�
		request.setAttribute("act", act);
		request.setAttribute("userType", userType);
		return mapping.findForward("fdyxxOne_whgl");
	}
	
	/**for get property*/
	private static String[] getProperties(String [] colList,Object model) throws IllegalArgumentException,
	SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
	String[] resultPro = new String[colList.length];
	Class myClass = model.getClass();
	for(int i = 0 ;i<colList.length;i++){
		String methodName = "get"+colList[i].substring(0, 1).toUpperCase()+colList[i].substring(1);
		resultPro[i] = 
			(String)myClass.getMethod(methodName,(Class[]) null).invoke(model,(Object[]) null); //�����޶�ΪString����
	}
	return resultPro;
	}	

}
