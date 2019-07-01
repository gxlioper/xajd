/**
 * ������2007-8-29����10:13:03
 * Ҫ���Ĵ����ɵ��ļ���ģ�壬��ת��
 * ���� �� ��ѡ�� �� Java �� ������ʽ �� ����ģ��
 */
package xgxt.sztz.action;

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
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.sztz.form.SztzForm;
import xgxt.utils.CommonQueryDAO;

import common.Globals;

/**
 * @author lp
 * Ҫ���Ĵ����ɵ��ļ���ģ�壬��ת��
 * ���� �� ��ѡ�� �� Java �� ������ʽ �� ����ģ��
 */
public class MakePdf extends Action{
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		SztzForm chkUser = (SztzForm) form;
		DAO dao = DAO.getInstance();
		try {
			int i = Base.chkTimeOut(request.getSession());
			if (i <= 2) {
				chkUser.setErrMsg("��½��ʱ�������µ�½��");
				return new ActionForward("/login.jsp", false);
			}

			ActionForward myActFwd = null;
			String act = mapping.getParameter();
			if (act.equalsIgnoreCase("sztzprint")) {
				if(dao.getXxdm().equalsIgnoreCase(Globals.XXDM_CSMZZYJSXY)){//��ɳ����ְҵ����ѧԺ
					myActFwd = csmz_sztzPrint(mapping, form, request, response);
				}else{
					myActFwd = sztzPrint(mapping, form, request, response);
				}
			}else if(act.equalsIgnoreCase("xcxy_sztzprint")){//����ѧԺ֤���ӡ��ѯ
				myActFwd = xcxy_sztzprint(mapping,form,request,response);
			}else if(act.equalsIgnoreCase("xcxy_sztzprintOne")){//����ѧԺ֤���ӡ
				myActFwd = xcxy_sztzprintOne(mapping,form,request,response);
			}else if(act.equalsIgnoreCase("csmz_sztzprintOne")){//��ɳ����ְҵ����ѧԺ֤���ӡ
				myActFwd = csmz_sztzprintOne(mapping,form,request,response);
			}
			return myActFwd;
		} catch (Exception e) {
			e.printStackTrace();
			//chkUser.setErrMsg("���������Թ��ϣ�");
			return new ActionForward("/errMsg.do", false);
		}
	}
	private ActionForward sztzPrint(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		// ֤���ӡ
		DAO dao = DAO.getInstance();
		SztzForm sztzForm = (SztzForm) form;
		HttpSession session = request.getSession();
		Vector<Object> rs = new Vector<Object>();
		HashMap<String, String> map = new HashMap<String, String>();
		String[] colList = null;
		String[] colListCN = null;
		String xxdm = dao.getXxdm();
		String sql = "";// sql���
		String querry = "";
		String rsNum = "0";// ���صļ�¼��
		String writeAble = "yes";// дȨ��
		String userDep = session.getAttribute("userDep").toString();
		String userType = dao.getUserType(userDep);
		String bmdm = sztzForm.getXydm();
		String zydm = sztzForm.getZydm();
		String bjdm = sztzForm.getBjdm();
		String xh = sztzForm.getXh();
		String nj = sztzForm.getNj();
		String hjjb=sztzForm.getHjjb();
		String xn = sztzForm.getXn();
		String kmdm = sztzForm.getKmdm();
		if (userType.equalsIgnoreCase("xy")) {
			bmdm = userDep;
		}
		if(xxdm.equalsIgnoreCase(Globals.XXDM_XCXY)){//����ѧԺ
			return new ActionForward("/xcxy_sztzprint.do",false);
		}
        String tips = "������չ - ��չ�ɹ���֤ - ֤���ӡ";
		if(Globals.XXDM_LZZYJSXY.equalsIgnoreCase(xxdm)){
			tips = "�ڶ����û - ���֤ - ֤���ӡ";
		}
		request.setAttribute("tips",tips);
		sql = "select dqxn,dqnd,replace(dqxn||'-'||dqnd,'200','') sj from xtszb where rownum=1";
		colList = dao.getOneRs(sql, new String[] {}, new String[] { "dqxn",
				"dqnd", "sj" });						
		xn =colList[0];
		String nd = colList[1];
		sztzForm.setXn(xn);
		sztzForm.setNd(nd);
		String sj = colList[2];
		request.setAttribute("sj", sj);

		if ((hjjb == null) || hjjb.equalsIgnoreCase("")) {
			hjjb="";
		} else {
			map.put("hjjb", hjjb);
		}

		map.put("xn", sztzForm.getXn());
		sql = "select kmdm,kmm from sztz_kmdmb";
		List kmList = dao.getList(sql, new String[] {}, new String[] {
				"kmdm", "kmm" });
		request.setAttribute("kmList", kmList);// ����������չ��Ŀ�б�

		querry = getQuery(xn,kmdm,nj,bmdm,zydm,bjdm,xh);//��ѯ����

		if (userType.equalsIgnoreCase("xy")) {
			sztzForm.setXydm(userDep);
		}

		if(xxdm.equalsIgnoreCase(Globals.XXDM_JSXX)){//������Ϣְҵ����ѧԺ
			colList = new String[] { "�к�", "xh", "xm", "zymc", "bjmc","xmmc", "kmm","jbmc","xf" };
			sql = "select rownum �к�,rownum r,c.* from (select  * from view_sztzcj "+ querry
			+ "order by xh) c";
			colListCN = dao.getColumnNameCN(colList, "view_sztzcj");				
		}else {
			colList = new String[] { "�к�", "xh", "xm", "zymc", "bjmc","xmmc", "kmmc","jxm","xf" };
			sql = "select rownum �к�,rownum r,c.* from (select  * from view_tzcgcjxx "+ querry
			+ "order by xh) c";
			colListCN = dao.getColumnNameCN(colList, "view_tzcgcjxx");
		}

		List topTr = dao.arrayToList(colList, colListCN);
		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			rs.addAll(CommonQueryDAO.commonQuery(sql, "", new String[] {}, colList, sztzForm));
			if (rs == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(rs.size());
			}
		}
		getXyZyBjxx(request,dao);//���������б�
		request.setAttribute("writeAble", writeAble);// ���Ͷ�дȨ��			
		request.setAttribute("rs", rs);// �������ݼ�
		request.setAttribute("rsa", map);			
		request.setAttribute("topTr", topTr);// ���ͱ�ͷ
		request.setAttribute("rsNum", rsNum);// ���ͼ�¼��
		request.setAttribute("userType", userType);
		request.setAttribute("xxdm", xxdm);			
		return mapping.findForward("success");
	}
	private ActionForward xcxy_sztzprint(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		DAO dao = DAO.getInstance();
		SztzForm dataSearchForm = (SztzForm) form;
		Vector<Object> rs = new Vector<Object>();
		String querry = "";
		String sql = "";
		String rsNum = "0";// ���صļ�¼��
		String nj = dataSearchForm.getNj();
		String xy = dataSearchForm.getXydm();
		String zy = dataSearchForm.getZydm();
		String bj = dataSearchForm.getBjdm();
		String xh = dataSearchForm.getXh().trim();
		String userSpecType = request.getSession().getAttribute("userType").toString();
		String userDep = request.getSession().getAttribute("userDep").toString();	
		if(userSpecType.equalsIgnoreCase("xy")){
			xy = userDep;
			dataSearchForm.setXydm(xy);
		}
		querry = getQuery("","",nj,xy,zy,bj,xh);//��ѯ����

//		sql = "select * from (select * from(select a.*,rownum r from( select distinct xh,xm,xb,nj,xydm,xymc,bjdm,bjmc,zydm,zymc from view_sztzcj )a " + querry + ") where rownum<="
//		+ (dataSearchForm.getPages().getStart() + dataSearchForm.getPages().getPageSize()) 
//		+ ") where r>" + dataSearchForm.getPages().getStart();
		sql = "select a.*,rownum r from( select distinct xh,xm,xb,nj,xydm,xymc,bjdm,bjmc,zydm,zymc from view_sztzcj )a " + querry;
		String[] colList = new String[] { "xh", "xm", "xb", "nj", "xymc","bjmc"};		
		String[] colListCN = dao.getColumnNameCN(colList, "view_xsjbxx");
		List topTr = dao.arrayToList(colList, colListCN);
		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
			if (rs == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(rs.size());
			}
		}
		//��ҳ
//		dataSearchForm.getPages().setMaxRecord(Integer.parseInt(dao.getOneRs("select count(distinct xh ) count from view_sztzcj " + querry, new String[]{}, "count")));		
		getXyZyBjxx(request,dao);//���������б�
		request.setAttribute("rs", rs);// �������ݼ�
		request.setAttribute("topTr", topTr);// ���ͱ�ͷ
		request.setAttribute("rsNum", rsNum);// ���ͼ�¼��
		return mapping.findForward("success");
	}
	/**����ѧԺ������չ֤�鵥����ӡ*/
	private ActionForward xcxy_sztzprintOne(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		DAO dao = DAO.getInstance();
		String xh = request.getParameter("xh");
		String fzrq = request.getParameter("fzrq");//��֤����
		String mb = request.getParameter("mb");//ģ��
		String sql = "";
		String forward = "";//��ת��־		
		HashMap<String, String> rsMap = new HashMap<String, String>();
		HashMap<String, String> csMap = new HashMap<String, String>();
		HashMap<String, String> rxMap = new HashMap<String, String>();
		HashMap<String, String> fzMap = new HashMap<String, String>();
		HashMap<String, String> zsbhMap = new HashMap<String, String>();

		String [] colList = {"xh","xm","xb","xymc","zymc","mzmc","lydq","zzmmmc","nj"};
		//ѧ��������Ϣ
		sql = "select xh,xm,xb,xymc,zymc,mzmc,jg lydq,zzmmmc,nj from view_xsxxb where xh=?"; 
		rsMap = dao.getMap(sql, new String[]{xh}, colList);
		request.setAttribute("rsMap", rsMap);

		zsbhMap.put("zsbh", "5110628"+xh);//֤����
		request.setAttribute("bhMap", zsbhMap);

		if(!Base.isNull(mb)){
			if(mb.equalsIgnoreCase("0")){//֤���һҳ��ѧ����ػ�����Ϣ
				//ѧ����������
				sql = " select csrq from view_xsxxb where xh=? ";
				String csrq=dao.getOneRs(sql, new String[] {xh}, "csrq");	
				if(csrq == null || "".equalsIgnoreCase(csrq)){
					csMap.put("csNYR", "-��-��-��");
				}else if(csrq.indexOf("-")<0 && !csrq.equalsIgnoreCase("")){
					csMap.put("csNYR", csrq.substring(0,4)+"��"+csrq.substring(4,6)+"��");					
				}else{
					csMap.put("csNYR", csrq.substring(0,4)+"��"+csrq.substring(csrq.indexOf("-")+1,csrq.lastIndexOf("-"))+"��"+csrq.substring(csrq.lastIndexOf("-")+1,csrq.length())+"��");
				}
				request.setAttribute("csMap", csMap);

				//ѧ����ѧ����
				sql = "select rxrq rxny from view_xsxxb where xh=?";
				String rxny=dao.getOneRs(sql, new String[] {xh}, "rxny");	
				
				if(rxny == null || "".equalsIgnoreCase(rxny)){			
					rxMap.put("rxNYR", "-��-��-��");							
				}else{
					if(rxny.length()==8){
						rxMap.put("rxNYR",rxny.substring(0,4)+"��"+rxny.substring(4,6)+"��"+rxny.substring(6,8)+"��");			
					}else{
						rxMap.put("rxNYR",rxny.substring(0,4)+"��"+rxny.substring(5,7)+"��"+rxny.substring(8,10)+"��");			
					}
//					if(rxny.indexOf("-")<0){
//						rxMap.put("rxNYR",csrq.substring(0,4)+"��"+csrq.substring(4,6)+"��"+csrq.substring(6,8)+"��");					
//					}else{
//						rxMap.put("rxNYR",rxny.substring(0,4)+"��"+rxny.substring(rxny.indexOf("-")+1,rxny.lastIndexOf("-"))+"��"+rxny.substring(rxny.lastIndexOf("-")+1,rxny.length())+"��");			
//					}					
				}
				
				//��֤����
				sql = "select (substr(nvl('" + fzrq + "','0000-00-00'),1,4)||'��'||substr(nvl('" + fzrq + "','0000-00-00'),6,2)||'��'||substr(nvl('" + fzrq + "','0000-00-00'),9,2)||'��')fzNYR from dual";
				fzMap = dao.getMap(sql, new String[]{}, new String[]{"fzNYR"});			
				request.setAttribute("fzMap", fzMap);
				request.setAttribute("rxMap", rxMap);
				forward = "successO";								
			}else if(mb.equalsIgnoreCase("1")){//֤��ڶ�ҳ��֤������Ϣ
				//��֤����
				sql = "select substr(nvl('" + fzrq + "','0000-00-00'),1,4) fzN,substr(nvl('" + fzrq + "','0000-00-00'),6,2) fzY,substr(nvl('" + fzrq + "','0000-00-00'),9,2) fzR from dual";
				fzMap = dao.getMap(sql, new String[]{}, new String[]{"fzN","fzY","fzR"});
				request.setAttribute("fzMap", fzMap);
				forward = "successT";
			}else if(mb.equalsIgnoreCase("2")){//֤�����ҳ�������������
				SztzForm dataSearchForm = (SztzForm) form;
				dataSearchForm.getPages().setPageSize(15);				 
				sql = "select a.* from ( select (case when (select b.kmm from (select b.kmm ,rownum ro from" +
				" (select kmm from view_sztzcj where xh ='"+xh+"' order by kmm,xn)b)b where b.ro=a.r-1  )=a.kmm then null else a.kmm end)kmm,"
				+ "a.xn,(case when (select b.xmmc from (select b.xmmc, rownum ro from (select xmmc from view_sztzcj where xh = '"+xh+"' order " +
				"by kmm,xmmc,xn) b) b where b.ro = a.r - 1) = a.xmmc then null else a.xmmc end) xmmc,(case when a.cg is null then a.jbmc else" +
				" a.cg end )jbmc ,(select xqmc from xqdzb where xqdm = a.xq )xqmc,a.r from "
				+"(select (select cg from sztz_xfsbb a where a.xh||a.nd||a.xn||a.xq||a.tzxmdm=xh||nd||xn||xq||xmdm and rownum=1 )cg,"
				+" a.kmm,a.xn,a.xmmc,a.jbmc , a.xq,rownum r from (select xmdm,kmm,xn,xmmc,jbmc,xq from view_sztzcj where xh ='"+xh+"' order by kmm,xmmc,xn,xq) a)a where rownum<="
				+ (dataSearchForm.getPages().getStart() + dataSearchForm.getPages().getPageSize()) 
				+ ")a where r>" + dataSearchForm.getPages().getStart();

				//��ҳ
				dataSearchForm.getPages().setMaxRecord(Integer.parseInt(dao.getOneRs("select count(xh ) cout from view_sztzcj where xh=? ", new String[]{xh}, "cout")));		
				
				List<HashMap<String,String>> rs = dao.getList(sql,  new String[] {}, new String[]{"kmm","xn","xqmc","xmmc","jbmc"});
				

				request.setAttribute("rs",rs);//�������ݼ�
				forward = "successTh";
			}

		}
		request.setAttribute("xh", xh);
		request.setAttribute("fzrq",fzrq);//��֤����
		request.setAttribute("mb",mb);//����ģ���

		return mapping.findForward(forward);
	}
	/**
	 * 
	 * @param request
	 * @param dao
	 * ����ѧ��ѧ�����ѧԺרҵ�༶��Ϣ
	 */
	private static void getXyZyBjxx(HttpServletRequest request, DAO dao) {
		String nj = request.getParameter("nj");
		String xydm = request.getParameter("xydm");
		String zydm = request.getParameter("zydm");		
		xydm = xydm==null ? "":xydm;
		zydm = zydm==null ? "":zydm;
		nj = nj==null ? "":nj;
		String bjKey = xydm+"!!"+zydm+"!!"+nj;
		request.setAttribute("njList", Base.getNjList());// �����꼶�б�
		request.setAttribute("xnList", Base.getXnndList());// ����ѧ���б�
		request.setAttribute("xqList", Base.getXqList());// ����ѧ���б�
		request.setAttribute("xyList", Base.getXyList());// ����ѧԺ�б�
		request.setAttribute("zyList", (Base.getZyMap()).get(xydm));// ����רҵ�б�
		request.setAttribute("bjList", (Base.getBjMap()).get(bjKey));// ���Ͱ༶�б�	
	}
	/**
	 * @param xn
	 * @param kmdm
	 * @param nj
	 * @param bmdm
	 * @param zydm
	 * @param bjdm
	 * @param xh
	 * @return ��ѯ�����ַ���
	 */
	public static String getQuery(String xn,String kmdm,String nj,String bmdm,String zydm,String bjdm,String xh){
		StringBuffer querry = new StringBuffer();
		querry.append(" where 1=1 ");// sql����
		querry.append((Base.isNull(xn))?" and 1=1 ":"and xn = '" + xn + "' ");
		querry.append((Base.isNull(kmdm))?" and 1=1 ":" and kmdm = '"+kmdm+"' ");
		querry.append((Base.isNull(nj))?" and 1=1 ":" and nj = '"+nj+"' ");
		querry.append((Base.isNull(bmdm))?" and 1=1 ":" and xydm = '"+bmdm+"' ");
		querry.append((Base.isNull(zydm))?" and 1=1 ":" and zydm = '"+zydm+"' ");
		querry.append((Base.isNull(bjdm)?" and 1=1 ":" and bjdm = '"+bjdm+"' "));
		querry.append((Base.isNull(xh))?" and 1=1 ":" and xh = '"+xh+"' ");
		return querry.toString();
	}
	/**��ɳ����������չ֤���ӡ��ѯ*/
	public ActionForward  csmz_sztzPrint (ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		DAO dao = DAO.getInstance();
		SztzForm dataSearchForm = (SztzForm) form;
		Vector<Object> rs = new Vector<Object>();
		String querry = "";
		StringBuffer sql = new StringBuffer();
		String rsNum = "0";// ���صļ�¼��
		String nj = dataSearchForm.getNj();
		String xy = dataSearchForm.getXydm();
		String zy = dataSearchForm.getZydm();
		String bj = dataSearchForm.getBjdm();
		String xh = DealString.toGBK(dataSearchForm.getXh().trim());
		dataSearchForm.setXh(xh);
		String xn = dataSearchForm.getXn();
		String userSpecType = request.getSession().getAttribute("userType").toString();
		String userDep = request.getSession().getAttribute("userDep").toString();	
		if(userSpecType.equalsIgnoreCase("xy")){
			xy = userDep;
			dataSearchForm.setXydm(xy);
		}
		if(xn==null){
			xn = Base.currXn;
			dataSearchForm.setXn(Base.currXn);
		}
		querry = getQuery(xn,"",nj,xy,zy,bj,xh);//��ѯ����
//		querry +=" and fdysh='ͨ��' and xysh='ͨ��' and xxsh='ͨ��' ";
		querry +=" and sfdy='��' ";
		//sql = " select distinct xh,xm,xb,nj,xymc,zymc,bjmc,(case when zxf<1 then '0'||to_char(ltrim(trim (zxf),'0')) else trim(zxf) end )zxf from view_tzcgrzxx  "+querry ;
		
		sql.append(" select distinct xh,xm,xb,nj,xymc,zymc,bjmc,(case when zxf<1 then '0'||to_char(ltrim(trim (zxf),'0')) else trim(zxf) end )zxf from (");	
		sql.append(" select distinct xh,xm,xb,nj,xydm,xymc,zydm,zymc,bjdm,bjmc,(select sum(xf) zxf from  view_tzcgcjxx b "+querry+" and a.xh=b.xh )zxf  ");
		sql.append(" from view_tzcgcjxx a "+querry+" )");
		
		String[] colList = new String[]{"xh","xm","xb","nj","xymc","zymc","bjmc","zxf"};
		String[] colListCN = new String[]{"ѧ��","����","�Ա�","�꼶",Base.YXPZXY_KEY,"רҵ","�༶","�����ܷ�"};
		List topTr = dao.arrayToList(colList, colListCN);
		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			rs.addAll(dao.rsToVator(sql.toString(), new String[] {}, colList));
			if (rs == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(rs.size());
			}
		}
		getXyZyBjxx(request,dao);//���������б�
		request.setAttribute("rs", rs);// �������ݼ�
		request.setAttribute("topTr", topTr);// ���ͱ�ͷ
		request.setAttribute("rsNum", rsNum);// ���ͼ�¼��
		return new ActionForward("/sztz/print/csmz/csmzprint.jsp",false);
	}
	/**��ɳ����������չ֤�鵥����ӡ*/
	public ActionForward csmz_sztzprintOne(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		DAO dao = DAO.getInstance();
		String xh = request.getParameter("xh");		
		String fzrq = request.getParameter("fzrq");//��֤����
		String mb = request.getParameter("mb");//ģ��
		String xn = request.getParameter("xn");//ѧ��
		String xqRz = request.getParameter("xqRz");
		xqRz = Base.isNull(xqRz)?"one":xqRz;
		String sql = "";
		String forward = "";//��ת��־
		HashMap<String, String> rsMap = new HashMap<String, String>();
		String [] colList = {"xh","xm","xb","xymc","zymc","mzmc","lydq","zzmmmc","nj"};
		//ѧ��������Ϣ
		sql = "select xh,xm,xb,xymc,zymc,mzmc,jg lydq,zzmmmc,nj from view_xsxxb where xh=?";
		rsMap = dao.getMap(sql, new String[]{xh}, colList);
		rsMap.put("xxmc",StandardOperation.getXxmc());//ѧУ����
		if(!Base.isNull(mb)){
			if(mb.equalsIgnoreCase("0")){//֤���һҳ��ѧ����ػ�����Ϣ
				//ѧ����������
				sql = "select a.csrq csrq from view_xsxxb a where a.xh=?";
				String csrq=dao.getOneRs(sql, new String[] {xh}, "csrq");	
//				if(csrq == null || "".equalsIgnoreCase(csrq)){
//					rsMap.put("csNYR", "-��-��-��");
//				}else if(csrq.indexOf("-")<0 && !csrq.equalsIgnoreCase("")){
//					rsMap.put("csNYR", csrq.substring(0,4)+"��"+csrq.substring(4,6)+"��");					
//				}else{
//					rsMap.put("csNYR", csrq.substring(0,4)+"��"+csrq.substring(csrq.indexOf("-")+1,csrq.lastIndexOf("-"))+"��"+csrq.substring(csrq.lastIndexOf("-")+1,csrq.length())+"��");
//				}
				rsMap.put("csNYR",Base.isNull(csrq)?"":csrq);
				//ѧ����ѧ����
				sql = "select nvl(a.rxrq,(select c.rxny from bks_xsjbxx c where c.xh=a.xh) )rxny from xsxxb a,view_xsjbxx b where a.xh=b.xh and a.xh=?";
				String rxny=dao.getOneRs(sql, new String[] {xh}, "rxny");	
//				if(rxny == null || "".equalsIgnoreCase(rxny)){			
//					rsMap.put("rxNYR", "-��-��-��");							
//				}else if(rxny.indexOf("-")<0){					
//					rsMap.put("rxNYR",csrq.substring(0,4)+"��"+csrq.substring(4,6)+"��"+csrq.substring(6,8)+"��");					
//				}else{
//					rsMap.put("rxNYR",rxny.substring(0,4)+"��"+rxny.substring(rxny.indexOf("-")+1,rxny.lastIndexOf("-"))+"��"+rxny.substring(rxny.lastIndexOf("-")+1,rxny.length())+"��");			
//				}
				rsMap.put("rxNYR",Base.isNull(rxny)?"":rxny);
				//��֤����
   		        if(Base.isNull(fzrq)){
					fzrq = dao.getOneRs("select to_char(sysdate,'yyyy-mm-dd')fzrq from dual", new String[]{}, "fzrq");
				}
				sql = "select substr(nvl('" + fzrq + "','0000-00-00'),1,4)fzN, substr(nvl('" + fzrq + "','0000-00-00'),6,2)fzY,substr(nvl('" + fzrq + "','0000-00-00'),9,2)fzR from dual ";
				String[] fzNyr = dao.getOneRs(sql, new String[]{}, new String[]{"fzN","fzY","fzR"});
				rsMap.put("fzN", fzNyr[0]);
				rsMap.put("fzY",fzNyr[1]);
				rsMap.put("fzR",fzNyr[2]);
				rsMap.put("zsbh","43-"+"10827"+"-"+xh);
				forward = "successO";
			}else if(mb.equalsIgnoreCase("1")){//֤��ڶ�ҳ����ѡ������
				Vector<Object> rs1 = new Vector<Object>();
				Vector<Object> rs2 = new Vector<Object>();
				String sql1 = "" ;
				String sql2 = "";								
				String sqlCon = "";
//				SztzForm dataSearchForm = (SztzForm) form;
				sqlCon = "select ((substr(nvl(zbsj,'00000000'),1,4))||'��'||(substr(nvl(zbsj,'00000000'),5,2))" 
					+"||'��'||'  '||xmmc||'   '||cyjs||'  '||jxm||'  '||xmjb)jxnr from view_tzcgcjxx ";
				sql1 =  sqlCon+" where kmmc like '%˼������%'  and xn='"+xn+"' and xh='"+xh+"'   ";//��һѧ��
				sql2 = sqlCon+" where kmmc like '%���ʵ��%'  and xn='"+xn+"'and xh='"+xh+"'   ";
				if(xqRz!=null&&xqRz.equalsIgnoreCase("tow")){//�ڶ�ѧ��
					sql1 = sqlCon+"  where kmmc like '%�Ƽ�ѧ��%' and xn='"+xn+"' and xh='"+xh+"' ";
					sql2 = sqlCon+"  where kmmc like '%�Ļ�����%' and xn='"+xn+"' and xh='"+xh+"' ";
				}else if(xqRz!=null&&xqRz.equalsIgnoreCase("three")){//����ѧ��
					sql1 = sqlCon+" where kmmc like '%���Ż%' and xn='"+xn+"' and xh='"+xh+"'  ";
					sql2 = sqlCon+"  where kmmc like '%������ѵ%' and xn='"+xn+"' and xh='"+xh+"' ";
				}
				sql1+=" and sfdy='��'";
				sql2+=" and sfdy='��'";
				rs1.addAll(dao.rsToVator(sql1, new String[] {}, new String[]{"jxnr"}));
				rs2.addAll(dao.rsToVator(sql2, new String[] {}, new String[]{"jxnr"}));
				//����8�����¼����ʼ���á���������䣬���һ������			
				String[] tmp = new String[]{"����  ����  ����  ���� ����  "};
				int rs1Len = rs1.size();
				int rs2Len = rs2.size();
				if(rs1Len<8){//��¼����8����ʼ���á���������䣬���ÿո����һ������		
					for(int i=rs1Len;i<8;i++){
						if(i==rs1Len){
							rs1.add(i, tmp);
						}else{
							rs1.add(i, new String[]{""});
						}						
					}					 
				}
				if(rs2Len<8){//��¼����8����ʼ���á���������䣬���ÿո����һ������		
					for(int i=rs2Len;i<8;i++){
						if(i==rs2Len){
							rs2.add(i, tmp);
						}else{
							rs2.add(i,new String[]{"  "});
						}
					}					 
				}
				//����8����	��������������
				request.setAttribute("rs1",rs1);
				request.setAttribute("rs2",rs2);
				forward = "successT";
			}else if(mb.equalsIgnoreCase("2")){//֤�����ҳ����֤��������
				//��֤����
   		        if(Base.isNull(fzrq)){
					fzrq = dao.getOneRs("select to_char(sysdate,'yyyy-mm-dd')fzrq from dual", new String[]{}, "fzrq");
				}
				sql = "select substr(nvl('" + fzrq + "','0000-00-00'),1,4)fzN, substr(nvl('" + fzrq + "','0000-00-00'),6,2)fzY,substr(nvl('" + fzrq + "','0000-00-00'),9,2)fzR from dual ";
				String[] fzNyr = dao.getOneRs(sql, new String[]{}, new String[]{"fzN","fzY","fzR"});
				rsMap.put("fzN", fzNyr[0]);
				rsMap.put("fzY",fzNyr[1]);
				rsMap.put("fzR",fzNyr[2]);
				forward = "successTH";
			}

		}
		request.setAttribute("xqRz",xqRz);
		request.setAttribute("xn",xn);
		request.setAttribute("fzrq",fzrq);
		request.setAttribute("mb",mb);
		request.setAttribute("xh", xh);
		request.setAttribute("rsMap", rsMap);			
		return mapping.findForward(forward);
	}
}
