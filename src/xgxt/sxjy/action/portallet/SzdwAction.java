
package xgxt.sxjy.action.portallet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import xgxt.DAO.DAO;
import xgxt.DAO.SxjyDAO;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.utils.CheckPower;

import common.Globals;

public class SzdwAction extends DispatchAction{
	SxjyDAO dao = new SxjyDAO();
	String[] colList = null;
	String[] colListCN = null;
	String sql =null;
	String writeAble = null;
	
	public ActionForward jscx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		ArrayList<String[]> rs = new ArrayList<String[]>();
		SxjyDAO dao = new SxjyDAO();
		StringBuffer query = new StringBuffer();
		SzdwForm szdwForm = (SzdwForm) form;
		String tableName = "view_fdyxx";
		String zgh = DealString.toGBK(szdwForm.getZgh());
		String jsm = DealString.toGBK(szdwForm.getJsm());
		query.append(" where 1=1 ");
		if(zgh != null && !("".equalsIgnoreCase(zgh.trim()))){
			query.append(" and zgh like '");
			query.append(zgh);
			query.append("' ");
		}
		if(jsm != null && !("".equalsIgnoreCase(jsm.trim()))){
			query.append(" and jsm like '");
			query.append(jsm);
			query.append("' ");
		}
		colList = new String[] { "zgh","xm","bmmc","zwmc"};
		colListCN = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);//表头 
		if ((request.getParameter("go") != null)&& request.getParameter("go").equalsIgnoreCase("go")) {	
			rs = dao.sxjyQuery(tableName,query.toString(),new String []{},colList,"");
		}
		request.setAttribute("rs", rs);// 发送数据集
		request.setAttribute("njList", Base.getNjList());
		request.setAttribute("topTr", topTr);// 发送表头
		request.setAttribute("rsNum", rs.size());
		return mapping.findForward("success");
}
	public ActionForward fdyxxOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String act = request.getParameter("act");
		HashMap<String, String> map = new HashMap<String, String>();
		DAO dao = DAO.getInstance();
		SxjyDAO sxjyDao = new SxjyDAO();
		String xxdm = dao.getXxdm();
		String sql = "";
		map.put("stuExists", "yes");
		String userDep = request.getSession().getAttribute("userDep").toString();
		String userType = dao.getUserType(userDep);
		String type = request.getParameter("type");
		type = isNull(type) ? "" : type;
		String writeAble = "yes";
		HttpSession session = request.getSession();
		String userName = request.getSession().getAttribute("userName").toString();
		if(xxdm.equalsIgnoreCase(Globals.XXDM_ZZSF)){
			request.setAttribute("isZZSF", "yes");
		}
		if ((act != null) && !act.equalsIgnoreCase("save")) {
			String pk = "";
			if(type.equalsIgnoreCase("one")){
				pk = userName;
				writeAble=CheckPower.checkUsrPower(session.getAttribute("userName").toString(), "fdyxxOne.do?act=view&type=one")?"yes":"no";
				request.setAttribute("ddxg", "yes");
			}else{
			pk = request.getParameter("pk");
			pk = isNull(pk) ? "" : pk;
			}
			sql = "select zgh, xm, xb, lxdh,bmdm, zw, zyzz,zzmm,xl,csrq,mz,zc,lxgzsj,xsgzyjfx,jtzz,yddh,dzyx,fblw,kyjl,jg,grhjqk,gzjl,bz,byyx,sxzy,yzbm,xw,rwsj,gzfg from fdyxxb where zgh=?";
			String[] colList = new String[] { "zgh", "xm", "xb", "lxdh",
					"bmdm", "zw", "zyzz", "zzmm", "xl", "csrq", "mz", "zc",
					"lxgzsj", "xsgzyjfx", "jtzz", "yddh", "dzyx", "fblw",
					"kyjl", "jg", "grhjqk", "gzjl", "bz", "byyx", "sxzy","yzbm","xw","rwsj","gzfg" };
			String[] colV = dao.getOneRs(sql, new String[] { pk }, colList);
			if (colV == null) {
				if(type.equalsIgnoreCase("one")){
					sql = "select yhm from yhb where zdm = '1082' and yhm = ?";
					String[] sffdy = dao.getOneRs(sql, new String[] { pk }, new String[] {"yhm"});
					if(null == sffdy){
						request.setAttribute("ffdy", "yes");
					}
				}
				colV = new String[colList.length];
			}
			for (int i = 0; i < colList.length; i++) {
				map.put(colList[i], isNull(colV[i]) ? "" : colV[i]);
			}
			if ("xy".equalsIgnoreCase(userType)) {
				map.put("bmdm", userDep);
			}
			if(type.equalsIgnoreCase("one")){
				map.put("zgh",userName);
			}
			String[] bjList = sxjyDao.getFdyBjList(pk);
			String[] zyList = sxjyDao.getZyBjList(pk);
			request.setAttribute("fdybjList", bjList);
			request.setAttribute("fdyzyList", zyList);
			request.setAttribute("xbList", dao.getSexList());
			request.setAttribute("doType", act);
			request.setAttribute("pkValue", pk);
		} else if ((act != null) && act.equalsIgnoreCase("save")) {
			String ddxg = request.getParameter("ddxg");
			ddxg = isNull(ddxg) ? "" : ddxg;
			if(ddxg.equalsIgnoreCase("yes")){
				request.setAttribute("ddxg", "yes");
			}
			String pkValue =request.getParameter("pkValue");
			String zgh = request.getParameter("zgh");
			String xm = DealString.toGBK(request.getParameter("xm"));
			String xb = request.getParameter("xb");
			String lxdh = DealString.toGBK(request.getParameter("lxdh"));
			String szdw = request.getParameter("bmdm");
			String zw = DealString.toGBK(request.getParameter("zw"));
			String zzmm = DealString.toGBK(request.getParameter("zzmm"));
			String xl = DealString.toGBK(request.getParameter("xl"));
			String csrq = DealString.toGBK(request.getParameter("csrq"));
			String grhjqk = DealString.toGBK(request.getParameter("grhjqk"));
			String gzjl = DealString.toGBK(request.getParameter("gzjl"));
			String bz = DealString.toGBK(request.getParameter("bz"));
			String zyzz = DealString.toGBK(request.getParameter("zyzz"));
			String mz = DealString.toGBK(request.getParameter("mz"));
			String zc = DealString.toGBK(request.getParameter("zc"));
			String lxgzsj = DealString.toGBK(request.getParameter("lxgzsj"));
			String xsgzyjfx = DealString.toGBK(request.getParameter("xsgzyjfx"));
			String jtzz = DealString.toGBK(request.getParameter("jtzz"));
			String yddh = DealString.toGBK(request.getParameter("yddh"));
			String dzyx = DealString.toGBK(request.getParameter("dzyx"));
			String fblw = DealString.toGBK(request.getParameter("fblw"));
			String kyjl = DealString.toGBK(request.getParameter("kyjl"));
			String jg = DealString.toGBK(request.getParameter("jg"));
			String byyx = DealString.toGBK(request.getParameter("byyx"));
			String sxzy = DealString.toGBK(request.getParameter("sxzy"));
			String yzbm = DealString.toGBK(request.getParameter("yzbm"));
			String xw = DealString.toGBK(request.getParameter("xw"));
			String rwsj = DealString.toGBK(request.getParameter("rwsj"));
			String gzfg = DealString.toGBK(request.getParameter("gzfg"));
			if ("xy".equalsIgnoreCase(userType)) {
				szdw = userDep;
			}
			Vector<HashMap<String, Object>> sqlVal = new Vector<HashMap<String, Object>>();
			HashMap<String, Object> sqls = new HashMap<String, Object>();
			sqls.put("sqlTxt", "delete fdyxxb where zgh=?");
			sqls.put("sqlVal", new String[] { pkValue });
			sqlVal.add(sqls);
			sqls = new HashMap<String, Object>();
			sqls.put("sqlTxt",
			"insert into fdyxxb(zgh,xm,xb,lxdh,bmdm,zw,zyzz,zzmm,xl,csrq,mz,zc,lxgzsj,xsgzyjfx,jtzz,yddh,dzyx,fblw,kyjl,jg,grhjqk,gzjl,bz,byyx,sxzy,yzbm,xw,rwsj,gzfg) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			sqls.put("sqlVal",new String[] { zgh, xm, xb, lxdh, szdw, zw, zyzz,zzmm,xl,csrq,mz,zc,lxgzsj,xsgzyjfx,jtzz,yddh,dzyx,fblw,kyjl,jg,grhjqk,gzjl,bz,byyx,sxzy,yzbm,xw,rwsj,gzfg });
			sqlVal.add(sqls);
			boolean res = dao.runUpdate(sqlVal);
			if (res) {
				request.setAttribute("added", "ok");
			} else {
				request.setAttribute("added", "no");
			}
		}
		if("xy".equalsIgnoreCase(userType))
		{
			request.setAttribute("writeAble", "no");
		}
		else if("xx".equalsIgnoreCase(userType)||"admin".equalsIgnoreCase(userType))
		{
			request.setAttribute("writeAble", "yes");
		}
		request.setAttribute("rs", map);
		sql = "select zwdm,zwmc from zwb";		
		List zwList = dao.getList(sql, new String[] {}, new String[] { "zwdm","zwmc" });
		if(xxdm.equalsIgnoreCase(Globals.XXDM_ZZSF)){
			sql = "select distinct bmdm xydm,bmmc xymc from ZXBZ_XXBMDM ";			
			List<HashMap<String, String>> partList = dao.getList(sql, new String[]{},new String[]{"xydm", "xymc"});
			
			request.setAttribute("bmList", partList);
		}else{
			request.setAttribute("bmList", dao.getXyList());
		}
		if(writeAble.equalsIgnoreCase("yes")){
		request.setAttribute("writeAble2", writeAble);
		}
		request.setAttribute("zwList", zwList);
		request.setAttribute("JsZzmmList", sxjyDao.getJsZzmmList());
		request.setAttribute("JsXlList", sxjyDao.getJsXlList());
		request.setAttribute("sfList", sxjyDao.getSfList());
		request.setAttribute("mzList", dao.getMzList());
		request.setAttribute("userType", userType);
		return mapping.findForward("oneDis");
	}
	
	private boolean isNull(String str) {
		return ((str == null) || str.equalsIgnoreCase(""));
	}
}