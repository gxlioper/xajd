package xgxt.dtjs.zgdzdx.xxwh;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.dtjs.zgdzdx.ZgdzdxDtjsDAO;
import xgxt.dtjs.zgdzdx.ZgdzdxDtjsForm;
import xgxt.dtjs.zgdzdx.ZgdzdxDtjsUnit;
import xgxt.szdw.utils.ModelToStrings;
import xgxt.utils.String.StringUtils;

public class XxwhDAO extends ZgdzdxDtjsDAO {
	DAO dao = DAO.getInstance();
    //思想教育
	public ArrayList<String[]> getSxjyhdList(XxwhModel myModel, String tableName) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		//获得思想教育活动列表
		String[] colList   = new String[]{"pk","xh","xymc","fssj","hddd","hdzt"};
		String[] inPutList = new String[]{};
		StringBuffer query = ZgdzdxDtjsUnit.makeQuery(new String[]{"xydm"},myModel);
		String sql = "";
		return commonQuery(tableName, query.toString(), inPutList, colList,
				sql, myModel);
	}

	public List<HashMap<String, String>> getSxjyhdTopTr(String tableName) {
		// 获得思想教育活动列表表头
		String[] colList = new String[] { "pk", "xh", "xymc", "fssj", "hddd",
				"hdzt" };
		String[] colListCN = dao.getColumnNameCN(colList, tableName);
		List<HashMap<String, String>> topTr = dao.arrayToList(colList,
				colListCN);// 表头
		return topTr;
	}

	public HashMap<String, String> getSxjyhdOne(String pk) {
		String tableName = "view_dtjsZgdzdxZtjyhd";
		String[] colList = new String[] { "bz", "fssj", "hddd", "hdnr", "hdxg",
				"hdzt", "pk", "xh", "xydm", "xymc" };
		return commonQueryOne(tableName, colList, "pk", pk);
	}

	public boolean SxjyhdUpdate(String pk, XxwhModel myModel,
		HttpServletRequest request) throws Exception {
		String tableName = "dtjsZgdzdxZtjyhdb";
		String pkComment = "xh||xydm";
		String[] colList = new String[] { "bz", "fssj", "hddd", "hdnr", "hdxg",
				"hdzt", "xh", "xydm" };
		String[] inputList = ModelToStrings.modelToStrings(colList, myModel);
		boolean updata = true;
		if (pk.equalsIgnoreCase("")) {
			updata = StandardOperation.insert(tableName, colList, inputList,
					request);
		} else {
			updata = StandardOperation.update(tableName, colList, inputList,
					pkComment, pk, request);
		}
		return updata;
	}

	public boolean SxjyhdDelete(String pk, HttpServletRequest request)
			throws Exception {
		String tableName = "dtjsZgdzdxZtjyhdb";
		String pkComment = "xh||xydm";
		boolean updata = StandardOperation.delete(tableName, pkComment, pk,
				request);
		return updata;
	}
	
	//发表论文
	public ArrayList<String[]> getFblwList(XxwhModel myModel, String tableName) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		String[] colList   = new String[]{"pk","bmmc","xm","fbqkmc","lwlbmc","fbsj","lwtm"};
		ArrayList<String> list = new ArrayList<String>();
		StringBuffer query = ZgdzdxDtjsUnit.makeQuery(new String[]{"bmdm","lwlbdm"},myModel);
		if(myModel.getLwtm()!=null&&!myModel.getLwtm().equalsIgnoreCase("")){
			query.append(" and lwtm like '%'||?||'%'");
			list.add(DealString.toString(myModel.getLwtm()));
		}
		if(myModel.getXm()!=null&&!myModel.getXm().equalsIgnoreCase("")){
			query.append(" and xm like '%'||?||'%'");
			list.add(DealString.toString(myModel.getXm()));
		}
		String sql = "";
		return commonQuery(tableName,query.toString(),list.toArray(new String[]{}),colList,sql,myModel);
	}

	public List<HashMap<String, String>> getFblwTopTr(String tableName) {
		String[] colList   = new String[]{"pk","bmmc","xm","fbqkmc","lwlbmc","fbsj","lwtm"};
		String[] colListCN = dao.getColumnNameCN(colList, tableName);
		List<HashMap<String, String>> topTr = dao.arrayToList(colList, colListCN);// 表头
		return topTr;
	}

	public boolean fblwUpdate(String pk, XxwhModel myModel, HttpServletRequest request) throws Exception {
		String tableName = "dtjsZgdzdxLwxxb";
		String pkComment = "xh";
		String[] colList = new String[]{};
		if(myModel.getXzlj().equalsIgnoreCase("")){
			colList = new String[] {"fbqkmc","fbsj","lwlbdm","lwtm","zgh","zy"};
		}
		colList = new String[] {"fbqkmc","fbsj","lwlbdm","lwtm","xzlj","zgh","zy"};
		String[] inputList = ModelToStrings.modelToStrings2(colList, myModel);
		boolean updata= true;
		if(pk.equalsIgnoreCase("")){
			updata = StandardOperation.insert(tableName, colList, inputList,
					request);
		}else{
			updata = StandardOperation.update(tableName, colList, inputList, pkComment, pk, request);
		}
		return updata;
	}

	public boolean fblwDelete(String pk, HttpServletRequest request) throws Exception {
		String tableName = "dtjsZgdzdxLwxxb";
		String pkComment = "xh";
		boolean updata = StandardOperation.delete(tableName, pkComment, pk, request);
		return updata;
	}

	public HashMap<String, String> getFblwOne(String pk) {
		String tableName = "view_dtjsZgdzdxLwxx";
		String[] colList   = new String[]{"bmdm","bmmc","fbqkmc","fbsj","lwlbdm","lwlbmc","lwtm","pk","xh","xm","xzlj","zgh","zy"};
		return commonQueryOne(tableName,colList,"pk",pk);
	}

	public ArrayList<String[]> getKyxmList(XxwhModel myModel, String tableName) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		String[] colList   = new String[]{"pk","bmmc","xm","xmmc","xmjbmc","jssj","kssj","kyjf"};
		ArrayList<String> list = new ArrayList<String>();
		StringBuffer query = ZgdzdxDtjsUnit.makeQuery(new String[]{"bmdm","xmjbdm"},myModel);
		if(myModel.getXmmc()!=null&&!myModel.getXmmc().equalsIgnoreCase("")){
			query.append(" and xmmc like '%'||?||'%'");
			list.add(DealString.toString(myModel.getXmmc()));
		}
		if(myModel.getXm()!=null&&!myModel.getXm().equalsIgnoreCase("")){
			query.append(" and xm like '%'||?||'%'");
			list.add(DealString.toString(myModel.getXm()));
		}
		String sql = "";
		return commonQuery(tableName,query.toString(),list.toArray(new String[]{}),colList,sql,myModel);
	}

	public List<HashMap<String, String>> getKyxmTopTr(String tableName) {
		String[] colList   = new String[]{"pk","bmmc","xm","xmmc","xmjbmc","jssj","kssj","kyjf"};
		String[] colListCN = dao.getColumnNameCN(colList, tableName);
		List<HashMap<String, String>> topTr = dao.arrayToList(colList, colListCN);// 表头
		return topTr;
	}

	public HashMap<String, String> getKyxmOne(String pk) {
		String tableName = "view_dtjsZgdzdxKyxm";
		String[] colList   = new String[]{"bmdm","bmmc","cyr","fzr","jssj","kssj","kyjf","pk","xh","xm","xmjbdm","xmjbmc","xmjs","xmly","xmmc","zgh"};
		return commonQueryOne(tableName,colList,"pk",pk);
	}

	public boolean kyxmUpdate(String pk, XxwhModel myModel, HttpServletRequest request) throws Exception {
		String tableName = "dtjsZgdzdxKyxmb";
		String pkComment = "xh";
		String[] colList = new String[] {"cyr","fzr","jssj","kssj","kyjf","xmjbdm","xmjs","xmly","xmmc","zgh"};
		String[] inputList = ModelToStrings.modelToStrings(colList, myModel);
		boolean updata= true;
		if(pk.equalsIgnoreCase("")){
			updata = StandardOperation.insert(tableName, colList, inputList,
					request);
		}else{
			updata = StandardOperation.update(tableName, colList, inputList, pkComment, pk, request);
		}
		return updata;
	}

	public boolean kyxmDelete(String pk, HttpServletRequest request) throws Exception {
		String tableName = "dtjsZgdzdxKyxmb";
		String pkComment = "xh";
		boolean updata = StandardOperation.delete(tableName, pkComment, pk, request);
		return updata;
	}

	public ArrayList<String[]> getFdyzzList(XxwhModel myModel, String tableName) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		String[] colList   = new String[]{"pk","bmmc","xm","zzmc","cbdw"};
		ArrayList<String> list = new ArrayList<String>();
		StringBuffer query = ZgdzdxDtjsUnit.makeQuery(new String[]{"bmdm"},myModel);
		if(myModel.getZzmc()!=null&&!myModel.getZzmc().equalsIgnoreCase("")){
			query.append(" and zzmc like '%'||?||'%'");
			list.add(DealString.toString(myModel.getZzmc()));
		}
		if(myModel.getXm()!=null&&!myModel.getXm().equalsIgnoreCase("")){
			query.append(" and xm like '%'||?||'%'");
			list.add(DealString.toString(myModel.getXm()));
		}
		String sql = "";
		return commonQuery(tableName,query.toString(),list.toArray(new String[]{}),colList,sql,myModel);
	}

	public List<HashMap<String, String>> getFdyzzTopTr(String tableName) {
		String[] colList   = new String[]{"pk","bmmc","xm","zzmc","cbdw"};
		String[] colListCN = dao.getColumnNameCN(colList, tableName);
		List<HashMap<String, String>> topTr = dao.arrayToList(colList, colListCN);// 表头
		return topTr;
	}

	public HashMap<String, String> getFdyzzOne(String pk) {
		String tableName = "view_dtjsZgdzdxfdyzz";
		String[] colList   = new String[]{"bmdm","bmmc","bz","cbdw","pk","xh","xm","zgh","zzjs","zzmc"};
		return commonQueryOne(tableName,colList,"pk",pk);
	}

	public boolean fdyzzUpdate(String pk, XxwhModel myModel, HttpServletRequest request) throws Exception {
		String tableName = "dtjsZgdzdxfdyzzb";
		String pkComment = "xh";
		String[] colList = new String[] {"bz","cbdw","zgh","zzjs","zzmc"};
		String[] inputList = ModelToStrings.modelToStrings(colList, myModel);
		boolean updata= true;
		if(pk.equalsIgnoreCase("")){
			updata = StandardOperation.insert(tableName, colList, inputList,
					request);
		}else{
			updata = StandardOperation.update(tableName, colList, inputList, pkComment, pk, request);
		}
		return updata;
	}

	public boolean fdyzzDelete(String pk, HttpServletRequest request) throws Exception {
		String tableName = "dtjsZgdzdxfdyzzb";
		String pkComment = "xh";
		boolean updata = StandardOperation.delete(tableName, pkComment, pk, request);
		return updata;
	}

	public HashMap<String, String> getYjzlOne(String pk) {
		String tableName = "dtjsZgdzdxYjzlb";
		String[] colList   = new String[]{"xh","sm","cbdw","cbsj","jg","bz"};
		return commonQueryOne(tableName,colList,"xh",pk);
	}

	public ArrayList<String[]> getYjzlList(XxwhModel myModel, String tableName) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		String[] colList   = new String[]{"xh","sm","cbdw","cbsj","jg"};
		ArrayList<String> list = new ArrayList<String>();
		StringBuffer query = new StringBuffer(" where 1=1 ");
		if(myModel.getSm()!=null&&!myModel.getSm().equalsIgnoreCase("")){
			query.append(" and sm like '%'||?||'%'");
			list.add(DealString.toString(myModel.getSm()));
		}
		String sql = "";
		return commonQuery(tableName,query.toString(),list.toArray(new String[]{}),colList,sql,myModel);
	}

	public List<HashMap<String, String>> getYjzlTopTr(String tableName) {
		String[] colList   = new String[]{"xh","sm","cbdw","cbsj","jg"};
		String[] colListCN = dao.getColumnNameCN(colList, tableName);
		List<HashMap<String, String>> topTr = dao.arrayToList(colList, colListCN);// 表头
		return topTr;
	}

	public boolean yjzlUpdate(String pk, XxwhModel myModel, HttpServletRequest request) throws Exception {
		String tableName = "dtjsZgdzdxYjzlb";
		String pkComment = "xh";
		String[] colList = new String[] {"bz","cbdw","cbsj","jg","sm"};
		String[] inputList = ModelToStrings.modelToStrings(colList, myModel);
		boolean updata= true;
		if(pk.equalsIgnoreCase("")){
			updata = StandardOperation.insert(tableName, colList, inputList,
					request);
		}else{
			updata = StandardOperation.update(tableName, colList, inputList, pkComment, pk, request);
		}
		return updata;
	}

	public boolean yjzlDelete(String pk, HttpServletRequest request) throws Exception {
		String tableName = "dtjsZgdzdxYjzlb";
		String pkComment = "xh";
		boolean updata = StandardOperation.delete(tableName, pkComment, pk, request);
		return updata;
	}

	public List<HashMap<String, String>> getBmList() {
		// TODO 自动生成方法存根
		return dao.getBmList();
	}

	public List<HashMap<String, String>> getFblwlbList() {
		String sql = "select lwlbdm,lwlbmc from dtjsZgdzdxLwlbb";
		return dao.getList(sql, new String[] {}, new String[] { "lwlbdm","lwlbmc" });
	}
	
	public List<HashMap<String, String>> getXmjbList() {
		String sql = "select xmjbdm,xmjbmc from dtjsZgdzdxkyxmjbb";
		return dao.getList(sql, new String[] {}, new String[] { "xmjbdm","xmjbmc" });
	}
	
	/**
	 * 党员活动室保存.
	 * 
	 * @param pk
	 *            the pk
	 * @param request
	 *            the request
	 * 
	 * @return true, if successful
	 * 
	 * @throws Exception
	 *             the exception
	 */
	public boolean dyhdsgl_save(String pk, DyhdsglModel myModel,
			HttpServletRequest request, String czlx, String xxk)
			throws Exception {

		String tableName = null;
		String[] colList = null;
		if("cdsbsy".equals(xxk)){
			//场地设备使用申请
			tableName = "zgdx_cdsbsy";
			colList = new String[] {"xh","sydw","sqrxm","sqrbj","hdnr","sysj","rs","sfsp","bz","xxsh"};
		}else if("zcgl".equals(xxk)){
			//资产管理
			tableName = "zgdx_zcgl";
			colList = new String[] {"xh","sbmc","sbxh","grsj","bxsj","jg","gmjsr","sbfzr","xxsh"};
		}else if("xxzl".equals(xxk)){
			//学习资料
			tableName = "zgdx_xxzl";
			colList = new String[] {"xh","sqkm","cbs","jg","cbsj","kfjy","bz","xxsh"};
		}else if("zljy".equals(xxk)){
			//资料借阅
			tableName = "zgdx_zkjy";
			colList = new String[] {"xh","sqkmc","jyr","jyrbj","jysj","ghsj","lxdh","bz","xxsh"};
		}

		String[] inputList = ModelToStrings.modelToStrings(colList, myModel);
		boolean updata = false;
		if (StringUtils.isNull(pk)) {
			updata = StandardOperation.insert(tableName, colList, inputList,request);
		} else {
			updata = StandardOperation.update(tableName, colList, inputList,"id", pk, request);
		}
		return updata;
	}
	/**
	 * 党员活动室保存.
	 * 
	 * @param pk
	 *            the pk
	 * @param request
	 *            the request
	 * 
	 * @return true, if successful
	 * 
	 * @throws Exception
	 *             the exception
	 */
	public boolean dyhdsgl_sh(String pk, DyhdsglModel myModel,
			HttpServletRequest request, String czlx, String xxk)
			throws Exception {

		String tableName = null;
		String[] colList = new String[] {"xxsh","shsj","shyj"};;
		if("cdsbsy".equals(xxk)){
			//场地设备使用申请
			tableName = "zgdx_cdsbsy";
		}else if("zcgl".equals(xxk)){
			//资产管理
			tableName = "zgdx_zcgl";
		}else if("xxzl".equals(xxk)){
			//学习资料
			tableName = "zgdx_xxzl";
		}else if("zljy".equals(xxk)){
			//资料借阅
			tableName = "zgdx_zkjy";
		}
		String shsj = (dao.getOneRs("select to_char(sysdate,'yyyymmddhh24miss') sdate from dual",
						new String[] {}, new String[] { "sdate" }))[0];
		myModel.setShsj(shsj);
		String[] inputList = ModelToStrings.modelToStrings(colList, myModel);
		boolean updata = false;
		if (!StringUtils.isNull(pk)) {
			updata = StandardOperation.update(tableName, colList, inputList,"id", pk, request);
		}
		return updata;
	}
	/**
	 * 党员活动室保存.
	 * 
	 * @param pk
	 *            the pk
	 * @param request
	 *            the request
	 * 
	 * @return true, if successful
	 * 
	 * @throws Exception
	 *             the exception
	 */
	public ArrayList<String[]> dyhdsgl_query(String xxk, DyhdsglModel myModel,ZgdzdxDtjsForm myForm)
			throws Exception {

		String realTable = "";
		String[] colList = null;
		ArrayList<String[]>  rs = new ArrayList<String[]>();
		String xydw = myModel.getXydm();
		String sqrxm = myModel.getSqrxm();
		String sysj = myModel.getSysj();
		String xxsh = myModel.getXxsh();
		String sbmc = myModel.getSbmc();
		String sbxh = myModel.getSbxh();
		String sbfzr = myModel.getSbfzr();
		String sqkm = myModel.getSqkm();
		String cbs = myModel.getCbs();
		String cbsj = myModel.getCbsj();
		String sqkmc = myModel.getSqkmc();
		String jyr = myModel.getJyr();
		String jysj = myModel.getJysj();
		String ghsj = myModel.getGhsj();
		String lxdh = myModel.getLxdh();
		String sydw = myModel.getSydw();
		StringBuffer query = new StringBuffer();
		
		if ("cdsbsy".equalsIgnoreCase(xxk)) {
			// 场地设备使用申请
			realTable = "zgdx_cdsbsy";
			query.append(Base.isNull(xydw)?"":" and xydw like '%"+xydw+"%' ");
			query.append(Base.isNull(sydw)?"":" and sydw like '%"+sydw+"%' ");
			query.append(Base.isNull(sqrxm)?"":" and sqrxm like '%"+sqrxm+"%' ");
			query.append(Base.isNull(xxsh)?"":" and xxsh like '%"+xxsh+"%' ");
			query.append(Base.isNull(sysj)?"":" and sysj like '%"+sysj+"%' ");
		} else if ("zcgl".equalsIgnoreCase(xxk)) {
			// 资产管理
			realTable = "zgdx_zcgl";
			query.append(Base.isNull(sbmc)?"":" and sbmc like '%"+sbmc+"%' ");
			query.append(Base.isNull(sbxh)?"":" and sbxh like '%"+sbxh+"%' ");
			query.append(Base.isNull(sbfzr)?"":" and sbfzr like '%"+sbfzr+"%' ");
			query.append(Base.isNull(xxsh)?"":" and xxsh like '%"+xxsh+"%' ");
		} else if ("xxzl".equalsIgnoreCase(xxk)) {
			// 学习资料
			realTable = "zgdx_xxzl";
			query.append(Base.isNull(sqkm)?"":" and sqkm like '%"+sqkm+"%' ");
			query.append(Base.isNull(cbs)?"":" and cbs like '%"+cbs+"%' ");
			query.append(Base.isNull(cbsj)?"":" and cbsj like '%"+cbsj+"%' ");
			query.append(Base.isNull(xxsh)?"":" and xxsh like '%"+xxsh+"%' ");
		} else if ("zljy".equalsIgnoreCase(xxk)) {
			// 资料借阅
			realTable = "zgdx_zkjy";
			query.append(Base.isNull(sqkmc)?"":" and sqkmc like '%"+sqkmc+"%' ");
			query.append(Base.isNull(jyr)?"":" and jyr like '%"+jyr+"%' ");
			query.append(Base.isNull(jysj)?"":" and jysj like '%"+jysj+"%' ");
			query.append(Base.isNull(lxdh)?"":" and lxdh like '%"+lxdh+"%' ");
			query.append(Base.isNull(ghsj)?"":" and ghsj like '%"+ghsj+"%' ");
			query.append(Base.isNull(xxsh)?"":" and xxsh like '%"+xxsh+"%' ");
		}
		if(StringUtils.isNotNull(realTable)){
			//场地设备使用申请
			if("zgdx_zkjy".equals(realTable)){
				colList = new String[]{"R", "ID", "XH", "SQKMC", "JYR", "JYRBJ", "JYSJ", "GHSJ", "LXDH"}; 
			}else{
			colList = dao.getColumnName("select 'r' r,a.* from "+realTable+" a");
			}
			String sql = "select * from (select rownum r,a.* from "+realTable+" a) a where r<="
							+ (myForm.getPages().getStart() + myForm.getPages().getPageSize())
							+ " and r> "
							+ myForm.getPages().getStart()
							+query;
//			System.out.println(sql);
			rs = dao.rsToVator(sql, new String[]{}, colList);
		}
//		}else if("cdsbsy".equals(xxk)){
			//资产管理
//			tableName = "zgdx_zcgl";
//			colList = new String[] {"xh","sbmc","sbxh","grsj","bxsj","jg","gmjsr","sbfzr","xxsh"};
//		}else if("cdsbsy".equals(xxk)){
			//学习资料
//			tableName = "zgdx_xxzl";
//			colList = new String[] {"xh","sqkm","cbs","jg","cbsj","kfjy","bz","xxsh"};
//		}else{
			//资料借阅
//			tableName = "zgdx_zkjy";
//			colList = new String[] {"xh","sqkmc","jyr","jyrbj","jysj","ghsj","lxdh","bz","xxsh"};
//		}
		return rs;
	}
	/**
	 * 英文列明和中文列明合并.
	 * 
	 * @param pk
	 *            the pk
	 * @param request
	 *            the request
	 * 
	 * @return true, if successful
	 * 
	 * @throws Exception
	 *             the exception
	 */
	public List<HashMap<String, String>> get_dyhdsglTopTr(String tableName) {
		// 获得党员活动室管理列表表头
		String[] colList;
		if("zgdx_zkjy".equals(tableName)){
			colList = new String[]{"ID", "XH", "SQKMC", "JYR", "JYRBJ", "JYSJ", "GHSJ", "LXDH", "BZ"};
		}else{
		    colList = dao.getColumnName("select * from "+tableName);
		}
		String[] colListCN = dao.getColumnNameCN(colList, tableName);
		List<HashMap<String, String>> topTr = dao.arrayToList(colList,
				colListCN);// 表头
		return topTr;
	}
	
	public int get_count(DyhdsglModel model,String table) {
		//获得分页总数
		String count;
		String sql = "select count(id) count from "+table;
		String[] countNum = dao.getOneRs(sql, new String[]{}, new String[]{"count"});
		count = StringUtils.isArrayNotNull(countNum)?countNum[0]:"0";
		return Integer.parseInt(count);
	}
	public HashMap<String, String> get_viewRs(String pk,String table,String xxk) {
		String[] colList = null;
		if ("cdsbsy".equalsIgnoreCase(xxk)) {
			// 场地设备使用申请
			colList = new String[]{"id","xh","sydw","sqrxm","sqrbj","hdnr","sysj","rs","sfsp","bz","xxsh","shsj","shyj"};
			table = "zgdx_cdsbsy ";
		} else if ("zcgl".equalsIgnoreCase(xxk)) {
			// 资产管理
			colList = new String[]{"id","xh","sbmc","sbxh","grsj","bxsj","jg","gmjsr","sbfzr","xxsh","shsj","shyj"};
			table = "zgdx_zcgl ";
		} else if ("xxzl".equalsIgnoreCase(xxk)) {
			// 学习资料
			colList = new String[]{"id","xh","sqkm","cbs","jg","cbsj","kfjy","bz","xxsh","shsj","shyj"};
			table = "zgdx_xxzl ";
		} else if ("zljy".equalsIgnoreCase(xxk)) {
			// 资料借阅
			colList = new String[]{"id","xh","sqkmc","jyr","jyrbj","jysj","ghsj","lxdh","bz","xxsh","shsj","shyj"};
			table = "zgdx_zkjy ";
		}
		String sql = "select * from "+table+" where id=?";
		HashMap<String, String> map = dao.getMap(sql, new String[]{pk}, colList);
		if(map!=null){
			if(map.get("shsj")!=null){
				String shsj = map.get("shsj");
				String sj = map.get("shsj").toString();
				String sjyear = sj.substring(0, 4);
				String sjmon = sj.substring(4, 6);
				String sjday = sj.substring(6, 8);
				String sjhour = sj.substring(8, 10);
				shsj = sjyear + "年" + sjmon + "月" + sjday + "日" + sjhour
						+ "时";
				map.put("shsj", shsj);
			}
		}
		return map;
	}
	public String get_del(String pks,String table,HttpServletRequest request) throws SQLException {
		StringBuffer sb = new StringBuffer();
		String[] keys = pks.split("!!#!!");
		String[] pksql = new String[] {};
		String sql = "";
		for (int i = 0; i < keys.length; i++) {
			String pk = DealString.toGBK(keys[i]);// 得到主键
			sql = "delete from "+table+" where id = '" + pk + "'";
			// 把主键组合成sql语句
			sb.append(sql);
			sb.append("!!#!!");
		}
		// sql语句拆分成数组
		pksql = sb.toString().split("!!#!!");
		int[] judge = dao.runBatch(pksql);
		String whichpk = "";
		// 检查哪一条删除失败
		for (int i = 0; i < judge.length; i++) {
			if (judge[i] < 0) {
				whichpk = whichpk + " 第" + String.valueOf(i) + "条数据删除失败;\n";
			}
		}
		return whichpk;
	}

	public ArrayList<String[]> getDyjhList(XxwhModel myModel, String tableName) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		//调研计划
		String[] colList = new String[] { "xh","dytm","cyr","dydd","dysj" };
		String[] inPutList = new String[]{};
		StringBuffer query = ZgdzdxDtjsUnit.makeQuery(new String[]{"dytm","dysj"},myModel);
		String sql = "";
		return commonQuery(tableName, query.toString(), inPutList, colList,
				sql, myModel);
	}

	public List<HashMap<String, String>> getDyjhTopTr(String tableName) {
		//获得思想教育活动列表表头
		String[] colList = new String[] { "xh","dytm","cyr","dydd","dysj" };
		String[] colListCN = dao.getColumnNameCN(colList, tableName);
		List<HashMap<String, String>> topTr = dao.arrayToList(colList,
				colListCN);// 表头
		return topTr;
	}

	public HashMap<String, String> getDyjhOne(String pk) {
		String tableName = "dtjsZgdzdxDyjhb";
		String[] colList = new String[] { "cyr","dydd","dynr","dysj","dytg","dytm","xh","bz" };
		return commonQueryOne(tableName, colList, "xh", pk);
	}

	public boolean dyjhUpdate(String pk, XxwhModel myModel, HttpServletRequest request)throws Exception {
			String tableName = "dtjsZgdzdxDyjhb";
			String pkComment = "xh";
			String[] colList = new String[] { "cyr","dydd","dynr","dysj","dytg","dytm","bz"  };
			String[] inputList = ModelToStrings.modelToStrings(colList, myModel);
			boolean updata = true;
			if (pk.equalsIgnoreCase("")) {
				updata = StandardOperation.insert(tableName, colList, inputList,
						request);
			} else {
				updata = StandardOperation.update(tableName, colList, inputList,
						pkComment, pk, request);
			}
		return updata;
	}

	public boolean dyjhDelete(String pk, HttpServletRequest request) throws Exception {
		String tableName = "dtjsZgdzdxDyjhb";
		String pkComment = "xh";
		boolean updata = StandardOperation.delete(tableName, pkComment, pk,
				request);
		return updata;
	}

	public ArrayList<String[]> getXgxxList(XxwhModel myModel, String tableName) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		//学工信息
		String[] colList = new String[] { "xh","xm","tjyhm","dw","bmmc","bt","qj","tjsj","lbmc","lbfz"};
		String[] inPutList = new String[]{};
		StringBuffer query = new StringBuffer( " where 1=1 ");
		String kssj = myModel.getKssj();
		String jssj = myModel.getJssj();
		String dw = DealString.toGBK(myModel.getDw()); 
		if(kssj != null && !("".equalsIgnoreCase(kssj.trim()))){
				query.append(" and tjsj");
				query.append(" >='");
				query.append(kssj);
				query.append("' ");
		}
		if(jssj != null && !("".equalsIgnoreCase(jssj.trim()))){
				query.append(" and tjsj");
				query.append(" <='");
				query.append(jssj);
				query.append("' ");
		}
		if(dw != null && !("".equalsIgnoreCase(dw.trim()))){
			query.append(" and dw like '%'||?||'%' ");
			inPutList = new String[]{dw};	
		}
		String sql = "";
		return commonQuery(tableName, query.toString(), inPutList, colList,
				sql, myModel);
	}

	public List<HashMap<String, String>> getXgxxTopTr(String tableName) {
		String[] colList = new String[] { "xh","xm","tjyhm","dw","bmmc","bt","qj","tjsj","lbmc","lbfz"};
		String[] colListCN = dao.getColumnNameCN(colList, tableName);
		List<HashMap<String, String>> topTr = dao.arrayToList(colList,
				colListCN);// 表头
		return topTr;
	}

	public HashMap<String, String> getXgxxOne(String pk, HttpServletRequest request) {
		String tableName = "view_dtjsDdxgxx";
		String[] colList = new String[] { "bt","dw","lbdm","lbfz","lbmc","nr","qj","tjsj","tjyhm","xh","xm","zt" };
		HashMap<String, String> rs = commonQueryOne(tableName, colList, "xh", pk);
		if(pk==null||pk.equalsIgnoreCase("")){
			HttpSession session = request.getSession();
			String userName = (String)session.getAttribute("userName");
			String userNameReal = (String)session.getAttribute("userNameReal");
			String userDep = (String)session.getAttribute("userDep");
			String bmmc = (String)session.getAttribute("bmmc");
			rs.put("tjyhm", userName);
			rs.put("xm",userNameReal);
			rs.put("bmdm", userDep);
			rs.put("bmmc", bmmc);
		}
		return rs;
	}

	public boolean xgxxUpdate(String pk, XxwhModel myModel, HttpServletRequest request) throws Exception {
		String tableName = "dtjsDdxgxxb";
		String pkComment = "xh";
		String[] colList = new String[] { "bt","dw","lbdm","nr","qj","tjyhm","zt" };
		String[] inputList = ModelToStrings.modelToStrings(colList, myModel);
		boolean updata = true;
		if (pk.equalsIgnoreCase("")) {
			updata = StandardOperation.insert(tableName, colList, inputList,
					request);
		} else {
			updata = StandardOperation.update(tableName, colList, inputList,
					pkComment, pk, request);
		}
		return updata;
	}

	public boolean xgxxDelete(String pk, HttpServletRequest request) throws Exception {
		String tableName = "dtjsDdxgxxb";
		String pkComment = "xh";
		boolean updata = StandardOperation.delete(tableName, pkComment, pk,
				request);
		return updata;
	}

	public List<HashMap<String, String>> getXgxxlbList() {
			String sql = "select lbdm,lbmc from dtjsDdxgxxjbb";
			return dao.getList(sql, new String[] {}, new String[] { "lbdm","lbmc" });
	}

	public ArrayList<String[]> getXgxxTjList(XxwhModel myModel) {
		//学工信息统计
		String [] inPutList = new String []{};
		StringBuffer query = new StringBuffer( " where 1=1 ");
		String kssj = myModel.getKssj();
		String jssj = myModel.getJssj();
		String dw = DealString.toGBK(myModel.getDw()); 
		String fz = DealString.toGBK(myModel.getFz()); 
		if(kssj != null && !("".equalsIgnoreCase(kssj.trim()))){
				query.append(" and tjsj");
				query.append(" >='");
				query.append(kssj);
				query.append("' ");
		}
		if(jssj != null && !("".equalsIgnoreCase(jssj.trim()))){
				query.append(" and tjsj");
				query.append(" <='");
				query.append(jssj);
				query.append("' ");
		}
		if(dw != null && !("".equalsIgnoreCase(dw.trim()))){
			query.append(" and dw like '%'||?||'%' ");
			inPutList = new String[]{dw};	
		}
		StringBuffer haveQuery = new StringBuffer( "");
		if(fz != null && !("".equalsIgnoreCase(fz.trim()))){
			haveQuery.append(" having sum(lbfz)");
			haveQuery.append(" >=");
			haveQuery.append(fz);
			haveQuery.append(" ");
		}
		String sql = "select bmmc,xm,sum(lbfz) from view_dtjsDdxgxx"+query+"group by xm,bmmc "+haveQuery;
		return dao.rsToVator(sql, inPutList, new String []{"bmmc","xm","sum(lbfz)"});
	}

}
