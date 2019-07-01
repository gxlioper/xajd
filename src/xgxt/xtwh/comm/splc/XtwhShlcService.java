package xgxt.xtwh.comm.splc;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommDAO;
import xgxt.comm.CommService;
import xgxt.form.SaveForm;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.CommonUpdata;
import xgxt.utils.Pages;
import xgxt.utils.String.StringUtils;
import xgxt.utils.db.GetSysData;

public class XtwhShlcService extends CommService{
	
	XtwhShlcDAO dao=new XtwhShlcDAO();
	
	//-------------------------------���ǽ���Ҫ�и��� �ķ��� ��ʦд���� begin----------------------------------
	/**
	 * ��ȡ�������̽����
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getSplcList(XtwhShlcForm model) throws Exception{

		return dao.getSplcList(model);
	}
	
	/**
	 * ��ȡ�������̽����
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String,String>> getTopTr(XtwhShlcForm model) throws Exception{

		DAO dao=DAO.getInstance();
		String[]en={"lcid","lcmc","mkmc","spnr","sycs"};
		String[]cn={"���̱��","�������","ģ������","��������","�Ƿ�ʹ��"};
		
		return dao.arrayToList(en, cn);
	}
	
	/**
	 * ����������̼���������λ
	 * @param model
	 * @return
	 */
	public List<HashMap<String, String>> getSpgwByShl(XtwhShlcForm model) {
		
		return dao.getSpgwByShl(model);
	}
	
	/**
	 * �����λ
	 * @param model
	 * @return
	 */
	public List<HashMap<String, String>> getTsgwByShl(XtwhShlcForm model) {

		return dao.getTsgwByShl(model);
	}
	
	/**
	 * ��ȡ�û���
	 * @param model
	 * @return
	 */
	public List<HashMap<String, String>> getYhzInfo() {
		XtwhShlcForm model = new XtwhShlcForm();
		return dao.getYhzInfo(model);
	}
	
	/**
	 * ��ȡ��ѡ�û�
	 * 
	 * @param model
	 * @return
	 */
	public List<HashMap<String, String>> getKxyhInfo(XtwhShlcForm model) {
		

		Pages pages=new Pages();
		List<HashMap<String,String>>rs=new ArrayList<HashMap<String,String>>();
		// ========== ���Ի� �û���Ȩ begin ============
		List<HashMap<String, String>> rsList = null;
		String yhszlx = model.getYhszlx();
		if("rcxwwh".equals(yhszlx)){
			// �ճ�����NEW-�ճ���Ϊά��NEW-�ճ���Ϊ����ά��-�ճ���Ϊ���
			rsList = dao.getKxyhInfoRcxwwh(model);
		}else{
			// ϵͳά��-��������ά��-��������
			rsList = dao.getKxyhInfo(model);
		}
		// ========== ���Ի� �û���Ȩ end ============
		HashMap<String,String>hashMap=new HashMap<String,String>();
		int pageInt = 1;
		int minR=0;
		
		if (!Base.isNull(model.getPage())) {
			pageInt = Integer.parseInt(model.getPage());
			minR=(pageInt-1)*10+minR;
		}
		
		pages.setPageSize(10);
		pages.setStart(minR);
		
		hashMap.put("count",String.valueOf(rsList.size()));
		rs.add(hashMap);
		rs.addAll(getResultList(rsList, pages));
		return rs;
	}

	/**
	 * ��ȡ��ѡ�û�
	 * 
	 * @param model
	 * @return
	 */
	public List<HashMap<String, String>> getYxyhInfo(XtwhShlcForm model) {
		Pages pages=new Pages();
		List<HashMap<String,String>>rs=new ArrayList<HashMap<String,String>>();
		// ========== ���Ի� �û���Ȩ begin ============
		List<HashMap<String, String>> rsList = null;
		String yhszlx = model.getYhszlx();
		if("rcxwwh".equals(yhszlx)){
			// �ճ�����NEW-�ճ���Ϊά��NEW-�ճ���Ϊ����ά��-�ճ���Ϊ���
			rsList = dao.getYxyhInfoRcxwwh(model);
		}else{
			// ϵͳά��-��������ά��-��������
			rsList = dao.getYxyhInfo(model);
		}
		// ========== ���Ի� �û���Ȩ end ============
		HashMap<String,String>hashMap=new HashMap<String,String>();
		int pageInt = 1;
		int minR=0;
		
		if (!Base.isNull(model.getPage())) {
			pageInt = Integer.parseInt(model.getPage());
			minR=(pageInt-1)*10+minR;
		}
		
		pages.setPageSize(10);
		pages.setStart(minR);
		
		hashMap.put("count",String.valueOf(rsList.size()));
		rs.add(hashMap);
		rs.addAll(getResultList(rsList, pages));
		return rs;
	}
	
	/**
	 * ��ȡ��ѡ�û�(����)(ϵͳά��-��������ά��-��������)
	 * 
	 * @param model
	 * @return
	 */
	public List<HashMap<String, String>> loadAllYxyhInfo(XtwhShlcForm model) {
		return dao.getYxyhInfo(model);
	}
	/**
	 * ��ȡ��ѡ�û�(����)(�ճ�����NEW-�ճ���Ϊά��NEW-�ճ���Ϊ����ά��-�ճ���Ϊ���)
	 * 
	 * @param model
	 * @return
	 */
	public List<HashMap<String, String>> loadAllYxyhInfoRcxwwh(XtwhShlcForm model) {
		return dao.getYxyhInfoRcxwwh(model);
	}
	
	/**
	 * ��ȡ��ѡ�û�(ɾ����)
	 * @param model
	 * @return
	 */
	public List<HashMap<String, String>> getYxyhByDel(XtwhShlcForm model) {
		Pages pages=new Pages();
		List<HashMap<String,String>>rs=new ArrayList<HashMap<String,String>>();
		List<HashMap<String,String>>rsList=dao.getYxyhByDel(model);
		HashMap<String,String>hashMap=new HashMap<String,String>();
		int pageInt = 1;
		int minR=0;
		
		if (!Base.isNull(model.getPage())) {
			pageInt = Integer.parseInt(model.getPage());
			minR=(pageInt-1)*10+minR;
		}
		
		pages.setPageSize(10);
		pages.setStart(minR);
		
		hashMap.put("count",String.valueOf(rsList.size()));
		rs.add(hashMap);
		rs.addAll(getResultList(rsList, pages));
		return rs;
	}
	
	/**
	 * ��ȡ��ѡ�û�(ɾ����)
	 * @param model
	 * @return
	 */
	public List<HashMap<String, String>> getKxyhByDel(XtwhShlcForm model) {
		Pages pages=new Pages();
		List<HashMap<String,String>>rs=new ArrayList<HashMap<String,String>>();
		List<HashMap<String,String>>rsList=dao.getKxyhByDel(model);
		HashMap<String,String>hashMap=new HashMap<String,String>();
		int pageInt = 1;
		int minR=0;
		
		if (!Base.isNull(model.getPage())) {
			pageInt = Integer.parseInt(model.getPage());
			minR=(pageInt-1)*10+minR;
		}
		
		pages.setPageSize(10);
		pages.setStart(minR);
		
		hashMap.put("count",String.valueOf(rsList.size()));
		rs.add(hashMap);
		rs.addAll(getResultList(rsList, pages));
		return rs;
	}
	
	/**
	 * ����������˸�λ��Ϣ(ϵͳά��-��������ά��-��������)
	 * @param form
	 * @return
	 * @throws Exception 
	 */
	public boolean saveSpgw(XtwhShlcForm form) throws Exception{
		
		XtwhShlcModel model=new XtwhShlcModel();
		
		String realTable = "xg_xtwh_spgwyh";
		String spgw=form.getSpgw();
		String[]spyh=form.getYxyhArr();
		
		String[] arrzd = new String[] { "spyh"};
		String[] onezd = new String[] { "spgw"};
		
		String pk="spgw";
		String []pkValue={spgw};
		if(spyh!=null && spyh.length>0){
			pkValue=new String[spyh.length];
			for(int i=0;i<spyh.length;i++){
				pkValue[i]=spgw;
			}
		}
		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(realTable);
		saveForm.setPk(pk);
		saveForm.setPkValue(pkValue);
		saveForm.setArrzd(arrzd);
		saveForm.setOnezd(onezd);
		
		model.setSpgw(spgw);
		model.setSpyh(spyh);

		return saveInfoToDb(saveForm, model, form.getUser());
	}
	/**
	 * ����������˸�λ��Ϣ(�ճ�����NEW-�ճ���Ϊά��NEW-�ճ���Ϊ����ά��-�ճ���Ϊ���)
	 * @param form
	 * @return
	 * @throws Exception 
	 */
	public boolean saveSpgwRcxwwh(XtwhShlcForm form) throws Exception{
		
		XtwhShlcModel model=new XtwhShlcModel();
		
		String realTable = "XG_RCSW_NEW_RCXWSQB";
		String spgw=form.getSpgw();
		String[]spyh=form.getYxyhArr();
		
		String[] arrzd = new String[] { "zgh"};
		String[] onezd = new String[] { "rcxwlbdm"};
		
		String pk="rcxwlbdm";
		String []pkValue={spgw};
		if(spyh!=null && spyh.length>0){
			pkValue=new String[spyh.length];
			for(int i=0;i<spyh.length;i++){
				pkValue[i]=spgw;
			}
		}
		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(realTable);
		saveForm.setPk(pk);
		saveForm.setPkValue(pkValue);
		saveForm.setArrzd(arrzd);
		saveForm.setOnezd(onezd);
		
		model.setSpgw(spgw);
		model.setSpyh(spyh);
		
		return saveInfoToDb(saveForm, model, form.getUser());
	}
	
	/**
	 * ɾ���������
	 * @param form
	 * @return
	 * @throws Exception 
	 */
	public boolean delShlc(XtwhShlcForm form) throws Exception {
		//ɾ����λ�������û���ע�⣺ͨ�ø�λ�µ��û��ǲ���ɾ����
		dao.deleteYhByShlc(form);
		boolean blog = dao.delShlc(form);
		if (blog) {
			blog = dao.delLcxggw(form);
		}
		if (blog) {
			blog = dao.delShbz(form);
		}
		return blog;
	}
	
	/**
	 * ��ȡģ��������Ϣ
	 * @return
	 */
	public List<HashMap<String,String>>getMklxInfo(){
		
		return dao.getMklxInfo();
	}
	
	/**
	 * ������������
	 *
	 */
	public void updateLcsz(){
		List<HashMap<String,String>>list=getMklxInfo();
		DAO dao=DAO.getInstance();
		for(int i=0;i<list.size();i++){
			HashMap<String,String>mklxMap=list.get(i);
			//��������
			if("pjpy".equalsIgnoreCase(mklxMap.get("mkdm"))){
					try {
						dao.runProcuder("{call pro_xg_xtwh_splc_pjlcsz()}",new String[]{});
					} catch (Exception e) {
						// TODO �Զ����� catch ��
						e.printStackTrace();
					}
			}else if("sztz".equalsIgnoreCase(mklxMap.get("mkdm"))){
					try {
						dao.runProcuder("{call pro_xg_xtwh_splc_sztzlcsz()}",new String[]{});
					} catch (Exception e) {
						// TODO �Զ����� catch ��
						e.printStackTrace();
					}
			}else if("rcsw".equalsIgnoreCase(mklxMap.get("mkdm"))){
				try {
					dao.runProcuder("{call pro_xg_xtwh_splc_rcswsz()}",new String[]{});
				} catch (Exception e) {
					// TODO �Զ����� catch ��
					e.printStackTrace();
				}
			}else if("xsxx".equalsIgnoreCase(mklxMap.get("mkdm"))){
				try {
					dao.runProcuder("{call pro_xg_xtwh_splc_xsxx()}",new String[]{});
				} catch (Exception e) {
					// TODO �Զ����� catch ��
					e.printStackTrace();
				}
			}
		}
	}
	
	
	//	-------------------------------���ǽ���Ҫ�и��� �ķ�����ʦд���� end----------------------------------

//	-------------------------------���ǽ���Ҫ�еı�¥�ϸ���ķ��� begin----------------------------------
	public List<HashMap<String, String>> getSsmkList() {
		String tableName="xg_xtwh_splcmkdzb";
		String[] colList = new String[]{"mkdm","mkmc"};
		return CommonQueryDAO.commonQueryforList(tableName,"", new String[]{}, colList, "");
	}
//	-------------------------------���ǽ���Ҫ�еı�¥�ϸ���ķ��� end----------------------------------

	public List<HashMap<String, String>> getGdgwList() {
		String tableName="xg_xtwh_spgw";
		String[] colList = new String[]{"id","mc"};
		String query = " where gwlx='1'";
		return CommonQueryDAO.commonQueryforList(tableName,query, new String[]{}, colList, "");
	}

	public Boolean savelcxx(XtwhShlcModel model, HttpServletRequest request) {
		String tableName = "xg_xtwh_splc";
		String [] colList = new String []{"mc","djlx","ms","id"};
		CommDAO dao =new CommDAO();
		Boolean update = false;
		String lcid = model.getId();
		try {
			update = CommonUpdata.commonUpdata(colList, model, tableName, "mc", "", request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(update){
			String sum = request.getParameter("jscmdm");
			ArrayList<String> sqlArr = new ArrayList<String>();
			String[] zdylclist = new String[Integer.parseInt(sum)]; 
			String sql="";
			for(int i=1;i<=Integer.parseInt(sum);i++){
				String lcgw = request.getParameter("lcgw"+i);
				String gwz = request.getParameter("spgwzdm"+i);
				String spgwdm = request.getParameter("lcgwdm"+i);
				if(lcgw!=null){
					String sid = GetSysData.getGuid();
					sql = "insert into xg_xtwh_spgw (id,mc,gwz) values ('"+sid+"','"+lcgw+"','"+gwz+"')";
					zdylclist[i-1]=sid;
				}else{
					sql = "update xg_xtwh_spgw set gwz= '"+gwz+"' where id='"+spgwdm+"'";
				}
				sqlArr.add(sql);
				
			}
			
			try {
				update = dao.saveArrDate(sqlArr.toArray(new String[sqlArr.size()]));
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			if(update){
				String [] lcgwSql = new String[zdylclist.length]; 
				for(int i = 0;i<zdylclist.length;i++){
					if(zdylclist[i]!=null){
						lcgwSql[i] =  "insert into xg_xtwh_spbz (splc,xh,spgw) values ('"+lcid+"','"+(i+1)+"','"+zdylclist[i]+"')";
					}else{
						String tmp =  "lcgwdm"+(i+1);
						String yygwid = request.getParameter("lcgwdm"+(i+1));
						lcgwSql[i] =  "insert into xg_xtwh_spbz (splc,xh,spgw) values ('"+lcid+"','"+(i+1)+"','"+yygwid+"')";
					}
				}
				try {
					update = dao.saveArrDate(lcgwSql);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		}
		return update;
	}

	public HashMap<String, String> getLcxx(String lcid) {
		// TODO �Զ����ɷ������
		String tableName = "xg_xtwh_splc";
		String [] colList = new String[]{"id","mc","ms","djlx"};
		return CommonQueryDAO.commonQueryOne(tableName, colList, "id", lcid);
	}
	
	public List<String[]> getLcgwxx(String lcid) {
		XtwhShlcDAO dao = new XtwhShlcDAO();
		return dao.getLcgw(lcid);
	}

	public String[] getGwxx(String gwlx, String gwdm,String lcid) {
		// ��ȡ��λ���Ƽ�������������
		XtwhShlcDAO dao = new XtwhShlcDAO();
		String[] gwxx = new String[]{};
		if(gwlx!=null&&gwlx.equalsIgnoreCase("ty")){
			gwxx = dao.getTygwxx(gwdm);
		}else{
			gwxx = dao.getGwxx(gwdm,lcid);
		}
		return gwxx;
	}
	

	// -------------------------------ΰ���luo  begin----------------------------------
	/**
	 * ������������б�
	 * 
	 * @author luojw
	 */
	public static List<HashMap<String, String>> getSplcList(String gnmk) {

		DAO dao = DAO.getInstance();

		List<HashMap<String, String>> list = dao.getWhList("xg_xtwh_splc",
				"id", "mc", "", "djlx", gnmk, false);

		return list;
	}
	
	/**
	 * ���������λ�б�
	 * 
	 * @author luojw
	 */
	public static List<HashMap<String, String>> getSpgwList(String lcid,String userName) {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		sql.append("select b.spgw gwid, c.mc gwmc, b.xh lv,d.maxlv ");
		sql.append("from xg_xtwh_splc a, xg_xtwh_spbz b, xg_xtwh_spgw c, ");
		sql.append("(select max(xh) maxlv, splc from xg_xtwh_spbz group by splc) d ");
		sql.append("where a.id = b.splc ");
		sql.append("and d.splc = b.splc ");
		sql.append("and b.spgw = c.id ");
		sql.append("and a.id = ? ");
		
		if (!Base.isNull(userName)) {
			sql.append("and exists(select 1 from xg_xtwh_spgwyh d ");
			sql.append("where c.id = d.spgw and spyh = '" + userName + "') ");
		}
		
		sql.append("order by to_number(lv) ");
		
		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] { lcid }, new String[] { "gwid", "gwmc", "lv",
						"maxlv" });

		return list;
	}
	
	/**
	 * ����ģ�����ͻ�ȡ���������Ϣ
	 * @param djlx
	 * @return splc,lcxx
	 */
	public List<HashMap<String,String>> getShlcByDjlx(String djlx){
		List<HashMap<String,String>> listmap = new ArrayList<HashMap<String,String>>(); 
		List<HashMap<String,String>> list =  dao.getShlcByDjlx(djlx);
//		HashMap<String,String> map = new HashMap<String,String>();
//		map.put("sqlc", "wxsh");
//		map.put("lcxx", "�������");
//		listmap.add(map);
		if(null!=list&&list.size()>0){
			for(int i=0;i<list.size();i++){
				listmap.add(list.get(i));
			}
		}
		return listmap;
	}
	
	/**
	 * ����ͨ�ø�λDiv
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	public void createTygwDiv(XtwhShlcForm model, User user,
			HttpServletResponse response) throws Exception {

		List<HashMap<String, String>> list = getTsgwByShl(model);

		response.setContentType("text/html;charset=gbk");

		StringBuilder html = new StringBuilder();
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				html.append("<input type='radio' name='spgw' ");
				if(i==0){
					html.append("checked=\"checked\" ");
				}
				html.append("id=\"spgw" + i + list.get(i).get("gwlx") + "\" ");
				html.append("value=\"" + list.get(i).get("spgw") + "\" ");
				html.append("/> ");
				html.append(list.get(i).get("mc"));
				html.append("<br/>");
			}
		}
		response.getWriter().print(html.toString());
	}
	/**
	 * 
	 * @����:���ش�����divHtml
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-7-10 ����02:25:20
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @param response
	 * @return
	 * @throws Exception
	 * String �������� 
	 * @throws
	 */
	public String createTygwDivStr(XtwhShlcForm model, User user,
			HttpServletResponse response) throws Exception {

		List<HashMap<String, String>> list = getTsgwByShl(model);

		response.setContentType("text/html;charset=gbk");

		StringBuilder html = new StringBuilder();
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				html.append("<input type='radio' name='spgw' ");
				if(i==0){
					html.append("checked=\"checked\" ");
				}
				html.append("id=\"spgw" + i + list.get(i).get("gwlx") + "\" ");
				html.append("value=\"" + list.get(i).get("spgw") + "\" ");
				html.append("/> ");
				html.append(list.get(i).get("mc"));
				html.append("<br/>");
			}
		}
		return html.toString();
	}
	/**
	 * ���������λDiv
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	public void createTsgwDiv(XtwhShlcForm model, User user,
			HttpServletResponse response) throws Exception {

		List<HashMap<String, String>> list = getSpgwByShl(model);
		
		response.setContentType("text/html;charset=gbk");

		StringBuilder html = new StringBuilder();
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				html.append("<input type='radio' name='spgw' ");
				if (i == 0) {
					html.append("checked=\"checked\" ");
				}
				html.append("id=\"spgw" + i + list.get(i).get("gwlx") + "\" ");
				html.append("value=\"" + list.get(i).get("spgw") + "\" ");
				html.append("/> ");
				html.append(list.get(i).get("mc"));
				html.append("<br/>");
			}
		}
		response.getWriter().print(html.toString());
	}
	/**
	 * 
	 * @����:���ش�����divHtml
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-7-10 ����02:25:20
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @param response
	 * @return
	 * @throws Exception
	 * String �������� 
	 * @throws
	 */
	public String createTsgwDivStr(XtwhShlcForm model, User user,
			HttpServletResponse response) throws Exception {

		List<HashMap<String, String>> list = getSpgwByShl(model);
		StringBuilder html = new StringBuilder();
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				html.append("<input type='radio' name='spgw' ");
				if (i == 0) {
					html.append("checked=\"checked\" ");
				}
				html.append("id=\"spgw" + i + list.get(i).get("gwlx") + "\" ");
				html.append("value=\"" + list.get(i).get("spgw") + "\" ");
				html.append("/> ");
				html.append(list.get(i).get("mc"));
				html.append("<br/>");
			}
		}
		return html.toString();
	}
	// -------------------------------ΰ���luo end----------------------------------
	
	/**
	 * ͨ�û�ȡ��˼����б�
	 * @param splcid
	 * 
	 * @return List<HashMap<String, String>>
	 *              HashMap.put(spgwdm) ����
	 *              HashMap.put(spgwmc) ����     
	 */
	public List<HashMap<String, String>> getSpjbListByGnmk(String splcid) {
		
		List<HashMap<String,String>> spgwList = dao.getSpjbListByGnmk(splcid);
		
//		HashMap<String,String> sqMap = new HashMap<String, String>();
//		sqMap.put("spgwdm", "sq");
//		sqMap.put("spgwmc", "����");
//		
//		spgwList.add(0, sqMap);
		
		return spgwList;
	}

	/**
	 * @throws Exception  
	 * @����:�޸���������
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-1-2 ����07:27:28
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param myForm
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean updateShlc(XtwhShlcForm myForm,HttpServletRequest request,List<String[]> lcgwrs) throws Exception {
		
		boolean blog = dao.updateShlc(myForm);
		String sql ="";
		ArrayList<String> sqlArr = new ArrayList<String>();
		for(int i=0;i<lcgwrs.size();i++){
			String gwz = request.getParameter("spgwzdm"+i);
				sql = "update xg_xtwh_spgw set gwz= '"+gwz+"' where id='"+lcgwrs.get(i)[0]+"'";
			sqlArr.add(sql);
			
		}
		
		try {
			blog = dao.saveArrDate(sqlArr.toArray(new String[sqlArr.size()]));
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return blog;
	}
	
	/**
	 * @throws SQLException  
	 * @����:�������ݷ�Χ�����λ�û�(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-3-1 ����09:05:18
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean insertGwyhBySjfw(String splcid) throws SQLException {
		List<HashMap<String,String>> mapList = getSpgwAndGwz(splcid); 
		List<String[]> yhms = new ArrayList<String[]>();
		List<String> spgwList = new ArrayList<String>();
		for(int i = 0;i<mapList.size();i++) {
			HashMap<String,String> map = mapList.get(i);
			if(null == map.get("gwz") || "".equalsIgnoreCase(map.get("gwz"))){//�����ǰ��˸�λû��û���趨��λ��
				continue;
			}
			spgwList.add(map.get("spgw"));
			List<String> list = dao.getGwyhForInsert(map.get("spgw"), map.get("gwz"));//��ȡÿ��������λ�ܲ�����û���
			String[] yhmStrs = new String[list.size()];
			for(int j = 0;j<list.size();j++){
				yhmStrs[j] = list.get(j);//ת����ÿ��������λ�ܲ�����û�����
			}
			yhms.add(yhmStrs);
		}
		if(yhms.size()>0){
			String[] spgwidArr = new String[spgwList.size()];
			for(int p = 0;p<spgwList.size();p++){
				spgwidArr[p] = spgwList.get(p);
			}			
			return dao.insertGwyh(spgwidArr, yhms);
		}else{//���û��һ��������λ�и�λ���޶��������
			return true;
		}
	}
	
		/**
		 * @throws Exception  
		 * @����:�޸ĸ�λ���û�(������һ�仰�����������������)
		 * @���ߣ�����[���ţ�1282]
		 * @���ڣ�2017-3-7 ����03:47:57
		 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
		 * @return
		 * boolean �������� 
		 * @throws 
		 */
		public boolean updateGwzyh(List<HashMap<String,String>> oldList,List<HashMap<String,String>> newList,String splcId) throws Exception{
			List<String> gwidList = new ArrayList<String>();
			List<String> gwzList = new ArrayList<String>();
			for(int i = 0;i<oldList.size();i++){
				String oldSpgwz = oldList.get(i).get("gwz");//ȡ���ɵ�������λ��Ӧ��������λ��
				String newSpgwz = newList.get(i).get("gwz");//ȡ���µ�������λ��Ӧ��������λ��
				if(StringUtils.isNull(newSpgwz)){
					continue;
				}else if(StringUtils.isNotNull(oldSpgwz) && oldSpgwz.equalsIgnoreCase(newSpgwz)){
					continue;
				}else{
					String newSpgwid = newList.get(i).get("spgw");
					gwidList.add(newSpgwid);
					gwzList.add(newSpgwz);
				}
			}
			boolean result = true;
			if(gwidList.size()>0){
				result = dao.deleteYhBySpgw(gwidList.toArray(new String[]{}));
				if(result){
					if(gwidList.size()>0 && gwzList.size()>0){
						List<String[]> yhms = new ArrayList<String[]>();
						for(int p = 0;p<gwidList.size();p++){
							List<String> list = dao.getGwyhForInsert(gwidList.get(p), gwzList.get(p));//��ȡÿ��������λ�ܲ�����û���
							String[] yhmStrs = new String[list.size()];
							for(int j = 0;j<list.size();j++){
								yhmStrs[j] = list.get(j);//ת����ÿ��������λ�ܲ�����û�����
							}
							yhms.add(yhmStrs);
						}
						if(yhms.size()>0){			
							return dao.insertGwyh(gwidList.toArray(new String[]{}), yhms);
						}else{//���û��һ��������λ�и�λ���޶��������
							return true;
						}
						
					}
				}
			}
			return result;			
		}
	
	/** 
	 * @����:��������id��ȡ������λ����(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-3-7 ����04:21:35
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param splcid
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String,String>> getSpgwAndGwz(String splcId){
		return dao.getSpgwAndGwz(splcId);
		
	}
	
	/**
	 * @throws Exception 
	 * @throws SQLException  
	 * @����:��λ��ʼ��(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-3-9 ����03:50:33
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean gwCsh(String lcid) throws Exception{
		List<HashMap<String,String>> list = getSpgwAndGwz(lcid);
		List<String> spgws = new ArrayList<String>();
		for(HashMap<String,String> map : list){
			if(StringUtils.isNull(map.get("gwz"))){
				continue;				
			}else{
				spgws.add(map.get("spgw"));
			}
		}
		if(spgws.size()>0){		
			dao.deleteYhBySpgw(spgws.toArray(new String[]{}));
		}
		List<String[]> yhms = new ArrayList<String[]>();		
		if(list.size()>0){
			for(HashMap<String,String> map : list){
				if(StringUtils.isNull(map.get("gwz"))){//��λ��û���޶�����ʼ��
					continue;
				}
				List<String> lists = dao.getGwyhForInsert(map.get("spgw"), map.get("gwz"));//��ȡÿ��������λ�ܲ�����û���
				String yhmStrs[] = lists.toArray(new String[]{});
				yhms.add(yhmStrs);
			}
			return dao.insertGwyh(spgws.toArray(new String[]{}), yhms);				
		}else{
			return true;			
		}
		
	}
}
