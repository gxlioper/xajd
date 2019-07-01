
package xgxt.pjpy.xnmz;

import java.util.*;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.utils.String.StringUtils;

public class PjpyXnmzDAO {

	DAO dao = DAO.getInstance();
	ArrayList<String> values = new ArrayList<String>();
	/**
	 * 获取学生密码
	 * @return
	 * @throws Exception
	 */
	public String getStuMm() throws Exception {
		return dao.getOneRs("select xsjdcx from view_stumm", new String[]{}, "xsjdcx");
	}
	
	/**
	 * 获取学生学号
	 * @return
	 * @throws Exception
	 */
	public String[] getStuxh() throws Exception {
		return dao.getArray("select xh from view_xsjbxx", new String[]{}, "xh");
	}
	
	/**
	 * 插入学生成绩绩点信息
	 * @param stuList
	 * @return
	 * @throws Exception
	 */
	public String[] insertStucjjd(String[] stuList) throws Exception {
		String[] sqlList = new String[stuList.length];
		int k=0;
		for (String i :stuList) {
			StringBuffer inserSql = new StringBuffer();
			String[] ar = i.replace("/", "").replace("$", "@@").split("@@");
			inserSql.append(" insert into xscjjdb (XN,XQ,KCDM,KCXZ,XKKH,KCMC,XF,JD,XH,CJ) values (");
			for (String j : ar) {
				inserSql.append("'");
				inserSql.append(j);
				inserSql.append("',");
			}
			String sql = StringUtils.isNull(inserSql.toString()) ? "" : inserSql.toString().substring(0, inserSql.toString().length()-1); 
			sql += ")";
			sqlList[k] = sql;
			k++;
		}
		return sqlList;
	}
	
	/**
	 * 学生成绩绩点表头
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> stucjjdTitle() throws Exception {
		String[] enList = new String[]{ "rownum", "xm", "xn", "xq", "bjmc","kcmc", "cj", "xf", "jd"};
		String[] cnList = new String[]{ "行号", "姓名", "学年", "学期", "班级","课程", "成绩", "学分", "绩点"};
		return dao.arrayToList(enList, cnList);
	}
	
	public List<HashMap<String, String>> stucjjdTitle1() throws Exception {
		String[] enList = new String[]{ "rownum", "xh", "xm", "bjmc", "zxf"};
		String[] cnList = new String[]{ "行号", "学号", "姓名", "班级", "总学分"};
		return dao.arrayToList(enList, cnList);
	}
	
	public List<HashMap<String, String>> stucjjdTitle2() throws Exception {
		String[] enList = new String[]{ "rownum", "xh", "xm","xn", "bjmc", "pjjd"};
		String[] cnList = new String[]{ "行号", "学号", "姓名", "学年","班级", "平均绩点"};
		return dao.arrayToList(enList, cnList);
	}
	
	public StringBuffer getWhereSql(CjjdModel model) throws Exception {
		StringBuffer whereSql = new StringBuffer();
		if (!StringUtils.isNull(model.getXh())) {
			whereSql.append(" and xh = ?");
			values.add(model.getXh());
		}
		if (!StringUtils.isNull(model.getXm())) {
			whereSql.append(" and xm like ?");
			values.add("%" + DealString.toGBK(model.getXm()) + "%");
		}
		if (!StringUtils.isNull(model.getXn())) {
			whereSql.append(" and xn = ?");
			values.add(model.getXn());
		}
		if (!StringUtils.isNull(model.getXq())) {
			whereSql.append(" and xq = ?");
			values.add(model.getXq());
		}
		if (!StringUtils.isNull(model.getXydm())) {
			whereSql.append(" and xydm = ?");
			values.add(model.getXydm());
		}
		if (!StringUtils.isNull(model.getZydm())) {
			whereSql.append(" and zydm = ?");
			values.add(model.getZydm());
		}
		if (!StringUtils.isNull(model.getBjdm())) {
			whereSql.append(" and bjdm = ?");
			values.add(model.getBjdm());
		}
		if (!StringUtils.isNull(model.getNj())) {
			whereSql.append(" and nj = ?");
			values.add(model.getNj());
		}
		return whereSql;
	}
	
	/**
	 * 学生成绩查询结果
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> stucjjdQry(CjjdModel model) throws Exception {
		StringBuffer whereSql = getWhereSql(model);
		String sql = "select rownum,xm,xn,(select b.xqmc from xqdzb b where b.xqdm=view_xscjjdb.xq) xq,bjmc,kcmc,cj,xf,jd from view_xscjjdb where 1=1 ";
		String[] opList = new String[]{ "rownum", "xm", "xn", "xq","bjmc","kcmc", "cj", "xf", "jd"};
		return dao.rsToVator(sql + whereSql.toString(), values != null ? values.toArray(new String[0]) : new String[]{}, opList);
	}
	
	/**
	 * 学生成绩查询结果总学分
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> stucjjdQry1(CjjdModel model) throws Exception {
		StringBuffer whereSql = getWhereSql(model);
		String xn = model.getXn();
		String tmpSql = "";
		if (!StringUtils.isNull(xn)) {
			tmpSql = "and substr('"+xn+"',0,4)>=substr(xn,0,4) ";
		}
		String sql = "select rownum,xh,xm,bjmc,zxf from (select xh,xm,bjmc,trunc(sum(to_number(xf)),3) zxf from view_xscjjdb where 1=1 "+tmpSql;
		String[] opList = new String[]{ "rownum", "xh", "xm", "bjmc", "zxf"};
		return dao.rsToVator(sql + whereSql.toString() + "group by xh,xm,bjmc)", values != null ? values.toArray(new String[0]) : new String[]{}, opList);
	}
	
	/**
	 * 学生成绩查询结果平均绩点
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> stucjjdQry2(CjjdModel model) throws Exception {
		StringBuffer whereSql = getWhereSql(model);
		String sql = "select rownum,xh,xn,xm,bjmc,pjjd from (select xh,xm,xn,bjmc,trunc(avg(to_number(jd)),3) pjjd from view_xscjjdb where 1=1 ";
		String[] opList = new String[]{ "rownum", "xh", "xm", "xn","bjmc", "pjjd"};
		return dao.rsToVator(sql + whereSql.toString() +"group by xh,xm,bjmc,xn)", values != null ? values.toArray(new String[0]) : new String[]{}, opList);
	}
	
	public void xscjjdDel() throws Exception {
		dao.runUpdate("delete from xscjjdb", new String[]{});
	}
	
	/**
	 * 成绩表头
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> cjTitle() throws Exception {
		String[] enList = new String[]{"rownum", "xh", "xm", "xn", "xq", "kcmc", "cj"};
		String[] cnList = new String[]{"行号", "学号", "姓名", "学年", "学期", "课程", "成绩"};
		return dao.arrayToList(enList, cnList);
	}
	
	/**
	 * 成绩结果
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> cjResult(CjjdModel model) throws Exception {
		StringBuffer whereSql = getWhereSql(model);
		String sql = "select rownum,xh,xm,xn,xq,kcmc,cj from view_cjb where 1=1 ";
		String[] opList = new String[]{"rownum", "xh", "xm", "xn", "xq", "kcmc", "cj"};
		dao = DAO.getInstance();
		return dao.rsToVator(sql + whereSql.toString(), values != null ? values.toArray(new String[0]) : new String[]{}, opList);
		
	}
	
	public List<String[]> shcbCjjdResult(CjjdModel model) throws Exception {
		StringBuffer whereSql = getWhereSql(model);
		String sql = "select rownum,xh,xm,nj,xn,xq,bjmc,pjxfjd,mc1 from view_xsxqpmjgb where 1=1 ";
		String[] opList = new String[]{"rownum", "xh", "xm","nj", "xn", "xq", "bjmc","pjxfjd", "mc1"};
		return dao.rsToVator(sql + whereSql.toString(), values != null ? values
				.toArray(new String[0]) : new String[] {}, opList);
	}
	
	public List<HashMap<String, String>> shcbcjjdTitle() throws Exception {
		String[] enList = new String[]{"rownum", "xh", "xm","nj", "xn", "xq", "bjmc","jd", "zypm"};
		String[] cnList = new String[]{"行号", "学号", "姓名", "年级","学年", "学期","班级", "平均绩点", "专业排名"};
		return dao.arrayToList(enList, cnList);
	}
	
	public List<HashMap<String, String>> zhszcpTitle() throws Exception {
		String[] enList = new String[]{"rownum", "xh", "xm", "xn", "xq", "bjmc","mc", "jyfs"};
		String[] cnList = new String[]{"行号", "学号", "姓名", "学年", "学期", "班级","名称", "素质分"};
		return dao.arrayToList(enList, cnList);
	}
	
	public List<HashMap<String, String>> zhszcpTitle1() throws Exception {
		String[] enList = new String[]{"rownum", "xh", "xm", "xn", "bjmc","pjf"};
		String[] cnList = new String[]{"行号", "学号", "姓名", "学年", "班级","素质平均分"};
		return dao.arrayToList(enList, cnList);
	}
	
	public List<HashMap<String, String>> zhszcpTitle2() throws Exception {
		String[] enList = new String[]{"rownum", "xh", "xm", "xn", "bjmc", "zf"};
		String[] cnList = new String[]{"行号", "学号", "姓名", "学年", "班级","素质总分"};
		return dao.arrayToList(enList, cnList);
	}
	
	public List<String[]> zhszcpResult(CjjdModel model) throws Exception {
		StringBuffer whereSql = getWhereSql(model);
		String sql = "select rownum,xh,xm,xn,xq,bjmc,mc,jyfs from view_sztz_bjjyxx where 1=1 ";
		String[] opList = new String[]{"rownum", "xh", "xm", "xn", "xq", "bjmc","mc", "jyfs"};
		return dao.rsToVator(sql + whereSql.toString(), values != null ? values
				.toArray(new String[0]) : new String[] {}, opList);
	}
	
	public List<String[]> zhszcpResult1(CjjdModel model) throws Exception {
		StringBuffer whereSql = getWhereSql(model);
		String sql = "select rownum,xh,xm,xn,bjmc,pjf from (select xh,xm,xn,bjmc,trunc(avg(jyfs),3) pjf from view_sztz_bjjyxx where 1=1 ";
		String[] opList = new String[]{"rownum", "xh", "xm", "xn", "bjmc","pjf"};
		return dao.rsToVator(sql + whereSql.toString() + " group by xh,xm,xn,bjmc)", values != null ? values
				.toArray(new String[0]) : new String[] {}, opList);
	}
	
	public List<String[]> zhszcpResult2(CjjdModel model) throws Exception {
		StringBuffer whereSql = getWhereSql(model);
		String sql = "select rownum,xh,xm,xn,bjmc,zf from (select xh,xm,xn,bjmc,sum(jyfs) zf from view_sztz_bjjyxx where 1=1 ";
		String[] opList = new String[]{"rownum", "xh", "xm", "xn", "bjmc", "zf"};
		return dao.rsToVator(sql + whereSql.toString() + " group by xh,xm,xn,bjmc)", values != null ? values
				.toArray(new String[0]) : new String[] {}, opList);
	}
	
	public List<HashMap<String, String>> xfTitle() throws Exception {
		String[] enList = new String[]{"pk","rownum", "xn","xymc","nj", "zymc", "xf"};
		String[] cnList = new String[]{"pk","行号", "学年", "学院","年级","专业","毕业最低学分"};
		return dao.arrayToList(enList, cnList);
	}
	
	public List<String[]> xfResult(CjjdModel model) throws Exception {
		StringBuffer whereSql = getWhereSql(model);
		String sql = "select xn||nj||zydm pk,rownum,xn,xydm,(select distinct b.xymc from view_njxyzybj b where b.xydm=pjpy_xyjdszb.xydm) xymc,nj,(select distinct b.zymc from view_njxyzybj b where b.zydm=pjpy_xyjdszb.zydm) zymc,jd from pjpy_xyjdszb where 1=1";
		String[] opList = new String[]{"pk","rownum", "xn","xymc","nj", "zymc", "jd"};
		return dao.rsToVator(sql + whereSql.toString(), values != null ? values
				.toArray(new String[0]) : new String[] {}, opList);
	}
	
	public boolean xfsave(CjjdModel model, HttpServletRequest request) throws Exception {
		return StandardOperation.insert("pjpy_xyjdszb", new String[]{"xn", "xydm", "nj", "zydm", "jd"}, new String[]{model.getXn(),model.getXydm(),model.getNj(),model.getZydm(),model.getJd()}, request);
	}
	
	public HashMap<String, String> xfview(String pkValue) throws Exception {
		return dao.getMapNotOut("select xn,xydm,zydm,xymc,zymc,nj,jd from view_pjpy_xyjdszb where xn||nj||zydm = ?", new String[]{pkValue});
	}
	
	public boolean xfUpdate(String pkValue, CjjdModel model, HttpServletRequest request) throws Exception {
		dao.runUpdate("delete from pjpy_xyjdszb where xn||nj||zydm = ?", new String[]{pkValue});
		return StandardOperation.insert("pjpy_xyjdszb", new String[] { "xn",
				"xydm", "nj", "zydm", "jd" },
				new String[] { model.getXn(), model.getXydm(), model.getNj(),
						model.getZydm(), model.getJd() }, request);
	}
	
	public String xfDel(String[] keys, HttpServletRequest request) throws Exception {
		String del = "";//删除未成功记录数
		for (int i = 0; i < keys.length; i++) {
			String whichpk = keys[i];// 得到主键
			boolean bFlag = StandardOperation.delete("pjpy_xyjdszb", "xn||nj||zydm", whichpk, request);
			if (!bFlag){//删除不成功
				del += (i+1);
				del += ",";
			}
		}
		return StringUtils.isNull(del) ? "" : del.substring(0, del.length() - 1);
	}
	
	/**
	 * 将两个数组合并为一个数组
	 * @param array1
	 * @param array2
	 * @return array2
	 */
	public String[] unionArray(String [] array1,String [] array2){
		if(array1 != null){
			if(array2 !=null){
				String array[] = new String[array1.length+array2.length];
				copyArray(array2,array);
				for(int i=0;i<array1.length;i++){
					array[array2.length+i] = array1[i];
				}
				return array;
			}else{
				return array1;
			}
		}else{
			return array2;
		}
	}
	
	public String[] copyArray(String [] fromArray,String [] toArray2){
		if(fromArray != null && toArray2 != null){
			int min = fromArray.length <= toArray2.length?fromArray.length:toArray2.length;
			for(int i=0;i<min;i++){
				toArray2[i] = fromArray[i];
			}
			return toArray2;
		}else{
			if(toArray2 == null){
				return fromArray;
			}else{
				return null;
			}
		}	
	}
}
