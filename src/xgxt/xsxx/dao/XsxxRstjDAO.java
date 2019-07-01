package xgxt.xsxx.dao;

import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.pjpy.ahjg.CommanDAO;

/**
 * 
 * 
 * 类名称：XsxxRstjDAO 
 * 类描述： 学生信息人数统计DAO 
 * 创建人：yijd 
 * 创建时间：2012-7-05 上午09:20:00 
 * 修改备注：
 * 
 * @version
 * 
 */
public class XsxxRstjDAO extends CommanDAO {
	DAO dao = DAO.getInstance();
	
	/**
	 * 学生信息人数   校区学院年级
	 * @param nj  年级列表
	 * @param xq  校区列表
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> cxRstjXqxynj(String[] nj, String[] xq)
			throws Exception {
		if(nj==null || xq==null){
			return null;
		}
		StringBuffer sql = new StringBuffer();
		StringBuffer whereInfo = new StringBuffer();
		StringBuffer showInfo = new StringBuffer();
		String[] show = new String[(nj.length*xq.length)+xq.length];
		String[] inputValue=new String[(xq.length*(nj.length*2))+xq.length];
		for (int i = 0; i < xq.length; i++) {
			showInfo.append(" nvl(sum(a.xq_"+(i+1)+"), 0) xq_"+(i+1)+", ");
			whereInfo.append(" nvl((case when c.xqmc = ? then  count(1)  end), 0) xq_"+(i+1)+", ");
			show[i*nj.length+i]="xq_"+(i+1);
			inputValue[i*nj.length*2+(i*1)]=xq[i];
			for (int j = 0; j < nj.length; j++) {
				if((i + 1) == xq.length && (j + 1) == nj.length){
					showInfo.append(" nvl(sum(a.xq_"+(i+1)+"_"+(j+1)+"), 0) xq_"+(i+1)+"_"+(j+1)+" ");
					whereInfo.append(" nvl((case when c.xqmc = ? and a.nj = ? then  count(1)  end), 0) xq_"+(i+1)+"_"+(j+1)+" ");
				}else{
					showInfo.append(" nvl(sum(a.xq_"+(i+1)+"_"+(j+1)+"), 0) xq_"+(i+1)+"_"+(j+1)+", ");
					whereInfo.append(" nvl((case when c.xqmc = ? and a.nj = ? then  count(1)  end), 0) xq_"+(i+1)+"_"+(j+1)+", ");
				}
				show[(i*nj.length)+i+j+1]="xq_"+(i+1)+"_"+(j+1);
				inputValue[i*nj.length*2+(i*1)+(2*j)+1]=xq[i];
				inputValue[i*nj.length*2+(i*1)+(2*j)+2]=nj[j];
			}
		}
		sql.append(" select (case  when a.xymc is null then  '总计' "
				+ " else  a.xymc  end) xymc, "
				+ showInfo.toString() 
				+ " from (select (case when  xymc is null then '未填写' else xymc end) xymc,  a.nj,  " 
				+ whereInfo.toString()
				+ " from (select  a.xydm,a.xh,a.nj,a.yxdm,b.bmmc xymc from(  " 
				+ " select a.xydm,a.xh,a.nj,a.yxdm from xsxxb a where (sfyby='否' or sfyby is null )and (sfzx='在校' or sfzx is null)  " 
				+ " union  " 
				+ " select a.bmdm,a.xh,to_char(a.nj)nj,'' yxdm from bks_xsjbxx a where not  exists(select 1 from xsxxb b where a.xh=b.xh ))a  " 
				+ "  left join zxbz_xxbmdm b on a.xydm=b.bmdm  " 
				+ " ) a  " 
				+ " left join xsxxb b  " 
				+ " on a.xh = b.xh  " 
				+ " left join dm_zju_xq c  " 
				+ " on b.yxdm = c.dm  " 
				+ " group by a.xymc, a.nj, c.xqmc  " 
				+ " order by a.xymc, a.nj) a  " 
				+ " group by rollup(a.xymc) ");

		String[] outputValue = new String[] { "xymc" };
		outputValue = arrayAppendArray(outputValue, show);
		return dao.getList(sql.toString(), inputValue, outputValue);
	}
	
	/**
	 * 学生信息人数 学院专业年级，
	 * 
	 * @param nj
	 *            年级列表
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> cxRstjXyzynj(String[] nj)
			throws Exception {
		StringBuffer sql = new StringBuffer();
		StringBuffer whereNj = new StringBuffer();
		StringBuffer showNj = new StringBuffer();
		String[] show = new String[nj.length];
		for (int i = 0; i < nj.length; i++) {
			if ((i + 1) == nj.length) {
				// 最后一个不要 ,道号了
				showNj.append(" sum(nj_" + (i + 1) + ") nj_" + (i + 1) + " ");
				whereNj.append(" case when nj = ? then rs else 0 end as nj_"
						+ (i + 1) + " ");
			} else {
				showNj.append(" sum(nj_" + (i + 1) + ") nj_" + (i + 1) + ", ");
				whereNj.append(" case when nj = ? then rs else 0 end as nj_"
						+ (i + 1) + ", ");
			}
			show[i] = "nj_" + (i + 1);
		}
		sql.append(" select a.*,rownum r  from( "
						+ " select (case when xymc is null then '总计' else xymc end) xymc, "
						+ " (case when xymc is null then '总计' when tjzy is null then '合计' else tjzy end) tjzy, "
						+ " (case when xymc is null then '总计' when tjzy is null then '合计' when zymc is null then '小计' else zymc end) zymc, "
						+ " sum(rs) rs, "
						+ showNj.toString()
						+ " from (select " 
						+ " (case when  xymc is null then '未填写' else xymc end) xymc, " 
						+ " tjzy, " 
						+ " (case when zymc is null then '未填写' else zymc end) zymc, " 
						+ " rs, "
						+ whereNj.toString()
						+ " from (select xymc,'专业' tjzy, zymc, nj, count(1) rs "
						+ " from view_xsjbxx " 
//						+ " where xydm <> 'null' "
//						+ " and zydm <> 'null' " + " and zymc <> 'null' "
//						+ " and xymc <> 'null' " + " and xydm is not null "
//						+ " and zydm is not null " + " and xymc is not null "
//						+ " and zymc is not null "
						+ " group by xymc, zymc, nj "
						+ " order by xymc,zymc)) "
						+ " group by  rollup(xymc,tjzy,zymc) "
						+ " ) a order by r desc ");

		String[] outputValue = new String[] { "r", "xymc", "tjzy", "zymc", "rs" };
		outputValue = arrayAppendArray(outputValue, show);
		return dao.getList(sql.toString(), nj, outputValue);
	}

	/**
	 * 学生信息人数 校区学院年级性别，
	 * 
	 * @param nj
	 *            年级列表
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> cxRstjXqxynjxb(String[] nj)
			throws Exception {
		StringBuffer sql = new StringBuffer();
		StringBuffer whereNj = new StringBuffer();
		StringBuffer showNj = new StringBuffer();
		String[] shownj = new String[nj.length];
		String[] shownan = new String[nj.length];
		String[] shownv = new String[nj.length];
		String[] inputValue=new String[]{};
		for (int i = 0; i < nj.length; i++) {
			if ((i + 1) == nj.length) {
				// 最后一个不要 ,道号了
				showNj.append(" sum(nj_" + (i + 1) + ") nj_" + (i + 1) + ", ");
				showNj.append(" sum(nj_" + (i + 1) + "_nan) nj_" + (i + 1) + "_nan, ");
				showNj.append(" sum(nj_" + (i + 1) + "_nv) nj_" + (i + 1) + "_nv ");
				whereNj.append(" (case when nj = ? then rs else 0 end) as nj_"
						+ (i + 1) + ", ");
				whereNj.append(" (case when nj = ?  and xb = '男' then rs else 0 end) as nj_"
						+ (i + 1) + "_nan, ");
				whereNj.append(" (case when nj = ?  and xb = '女' then rs else 0 end) as nj_"
						+ (i + 1) + "_nv ");
			} else {
				showNj.append(" sum(nj_" + (i + 1) + ") nj_" + (i + 1) + ", ");
				showNj.append(" sum(nj_" + (i + 1) + "_nan) nj_" + (i + 1) + "_nan, ");
				showNj.append(" sum(nj_" + (i + 1) + "_nv) nj_" + (i + 1) + "_nv, ");
				whereNj.append(" (case when nj = ? then rs else 0 end) as nj_"
						+ (i + 1) + ", ");
				whereNj.append(" (case when nj = ?  and xb = '男' then rs else 0 end) as nj_"
						+ (i + 1) + "_nan, ");
				whereNj.append(" (case when nj = ?  and xb = '女' then rs else 0 end) as nj_"
						+ (i + 1) + "_nv, ");
			}
			shownj[i]="nj_" + (i + 1);
			shownan[i]="nj_" + (i + 1)+"_nan";
			shownv[i]="nj_" + (i + 1)+"_nv";
			inputValue=arrayAppendArray(inputValue, new String[]{nj[i],nj[i],nj[i]});
		}
		sql.append(" select a.*, rownum r from (select " 
				+ " (case when xqmc is null then '总计' when tjxy is null then xqmc else xqmc end) xqmc, "
				+ " (case when xqmc is null then '总计' when tjxy is null then '合计' else tjxy end) tjxy, "
		        + " (case when xqmc is null then '总计' when tjxy is null then '合计' when xymc is null then '小计' else xymc end) xymc, "
				+ " sum(rs) rs, "
				+ showNj.toString() 
				+ " from (select xqmc,tjxy, xymc, rs, " 
				+ whereNj.toString() 
				+ " from (select c.xqmc, a.xymc,'学院' tjxy, a.xb, a.nj, count(1) rs "
				+ " from view_xsjbxx a "
				+ " left join xsxxb b "
				+ " on a.xh = b.xh "
				+ " left join dm_zju_xq c "
				+ " on b.yxdm = c.dm "
				+ " group by c.xqmc, a.xymc, a.xb, a.nj "
				+ " order by a.xymc)) group by rollup(xqmc,tjxy, xymc)) a "
				+ " order by r desc ");

		String[] outputValue = new String[] { "r", "xqmc","tjxy", "xymc", "rs" };
		outputValue = arrayAppendArray(outputValue, shownj);
		outputValue = arrayAppendArray(outputValue, shownan);
		outputValue = arrayAppendArray(outputValue, shownv);
		return dao.getList(sql.toString(), inputValue, outputValue);
	}

	/**
	 * 查询学生信息年级
	 * 
	 * @return
	 * @throws Exception
	 */
	public String[] cxXsxxNj() throws Exception {
		StringBuffer sql = new StringBuffer();
		sql
				.append(" select distinct nj from view_xsjbxx where nj is not null order by nj ");
		String[] input = new String[] {};
		String output = "nj";
		return dao.getArray(sql.toString(), input, output);
	}
	
	/**
	 * 查询学生信息培养层次
	 * @return
	 * @throws Exception 
	 */
	public String[] cxXsxxCC() throws Exception {
		StringBuffer sql=new StringBuffer();
		sql.append("  select distinct case when pycc is null then '未填写' else pycc end pycc from view_xsxxb order by pycc ");
		String[] input = new String[] {};
		String output = "pycc";
		return dao.getArray(sql.toString(), input, output);
	}
	
	/**
	 * 查询学生信息培养层次名称
	 * @return
	 * @throws Exception 
	 */
	public String[] cxXsxxCCmc() throws Exception {
		StringBuffer sql=new StringBuffer();
		sql.append(" select case when pyccmc is null then '未填写' else pyccmc end pycc from (select distinct pycc from xsxxb order by pycc) a left join xg_xsxx_pyccdmb b on a.pycc=b.pyccdm order by a.pycc ");
		String[] input = new String[] {};
		String output = "pycc";
		return dao.getArray(sql.toString(), input, output);
	}
	
	/**
	 * 查询学生信息性别
	 * @return
	 * @throws Exception 
	 */
	public String[] cxXsxxXb() throws Exception {
		StringBuffer sql=new StringBuffer();
		sql.append(" select distinct case when xb is null then '未填写' else xb end xb from view_xsxxb order by xb ");
		String[] input = new String[] {};
		String output = "xb";
		return dao.getArray(sql.toString(), input, output);
	}
	/**
	 * 查询学生信息民族
	 * @return
	 * @throws Exception 
	 */
	public String[] cxXsxxMz() throws Exception {
		StringBuffer sql=new StringBuffer();
		sql.append(" select distinct case when mz is null then '未填写' else mz end mz from view_xsjbxx order by mz ");
		String[] input = new String[] {};
		String output = "mz";
		return dao.getArray(sql.toString(), input, output);
	}
	
	/**
	 * 层次性别民族学生信息结果统计
	 * @param cc 层次
	 * @param xb 性别
	 * @param mz 民族
	 * @author yyp
	 * @return
	 */
	public List<HashMap<String, String>> ccxbmzXsxxRstj(String[] cc,
			String[] xb, String[] mz) {
		StringBuffer sql = new StringBuffer();
		StringBuffer whereCc=new StringBuffer();
		StringBuffer showCc=new StringBuffer();
		StringBuffer whereXb=new StringBuffer();
		StringBuffer showXb=new StringBuffer();
		StringBuffer whereMz=new StringBuffer();
		StringBuffer showMz=new StringBuffer();
		String[] show=new String[cc.length+xb.length+mz.length];
		//层次
		for (int i = 0; i < cc.length; i++) {
			if((i+1)==cc.length){
				//最后一个不要 逗号
				showCc.append(" sum(zd_"+(i+1)+") zd_"+(i+1)+" ");
				whereCc.append(" case when pycc = ? then rs else 0 end as zd_"+(i+1)+" ");
			}else{
				showCc.append(" sum(zd_"+(i+1)+") zd_"+(i+1)+", ");
				whereCc.append(" case when pycc = ? then rs else 0 end as zd_"+(i+1)+", ");
			}
			show[i]="zd_"+(i+1);
		}
		//性别
		for (int i = cc.length; i < cc.length+xb.length; i++) {
			if((i+1)==cc.length+xb.length){
				//最后一个不要 逗号
				showXb.append(" sum(zd_"+(i+1)+") zd_"+(i+1)+" ");
				whereXb.append(" case when xb = ? then rs else 0 end as zd_"+(i+1)+" ");
			}else{
				showXb.append(" sum(zd_"+(i+1)+") zd_"+(i+1)+", ");
				whereXb.append(" case when xb = ? then rs else 0 end as zd_"+(i+1)+", ");
			}
			show[i]="zd_"+(i+1);
		}
		//民族
		for (int i = cc.length+xb.length; i < cc.length+xb.length+mz.length; i++) {
			if((i+1)==cc.length+xb.length+mz.length){
				//最后一个不要 逗号
				showMz.append(" sum(zd_"+(i+1)+") zd_"+(i+1)+" ");
				whereMz.append(" case when mzmc = ? then rs else 0 end as zd_"+(i+1)+" ");
			}else{
				showMz.append(" sum(zd_"+(i+1)+") zd_"+(i+1)+", ");
				whereMz.append(" case when mzmc = ? then rs else 0 end as zd_"+(i+1)+", ");
			}
			show[i]="zd_"+(i+1);
		}
		sql.append(" select a.*,b.*,c.*,rownum r from ")
				.append(" (select case when sydsmc is null then '总计' else sydsmc end sydsmc,sum(rs) rscc, ")
				.append(showCc.toString())
				.append(" from (select sydsmc,rs, ")
				.append(whereCc.toString())
				.append(" from (select case when sydsmc is null then '未填写' else sydsmc end sydsmc, ")
				.append(" case when pycc is null then '未填写' else pycc end pycc,")
				.append(" count(1) rs from view_xsxxb where (sfyby = '否' or sfyby is null) and (sfzx = '在校' or sfzx is null) group by sydsmc,pycc order by sydsmc)) group by rollup(sydsmc)) a,")
				.append(" (select case when sydsmc is null then '总计' else sydsmc end sydsmc,sum(rs) rsxb, ")
				.append(showXb.toString())
				.append(" from (select sydsmc,rs, ")
				.append(whereXb.toString())
				.append(" from (select case when sydsmc is null then '未填写' else sydsmc end sydsmc,")
				.append(" case when xb is null then '未填写' else xb end xb,")
				.append(" count(1) rs from view_xsxxb where (sfyby = '否' or sfyby is null) and (sfzx = '在校' or sfzx is null) group by sydsmc,xb order by sydsmc)) group by rollup(sydsmc)) b,")
				.append(" (select case when sydsmc is null then '总计' else sydsmc end sydsmc,sum(rs) rsmz, ")
				.append(showMz.toString())
				.append(" from (select sydsmc,rs, ")
				.append(whereMz.toString())
				.append(" from (select case when sydsmc is null then '未填写' else sydsmc end sydsmc,")
				.append(" case when mzmc is null then '未填写' else mzmc end mzmc,")
				.append(" count(1) rs from view_xsxxb where (sfyby = '否' or sfyby is null) and (sfzx = '在校' or sfzx is null) group by sydsmc,mzmc order by sydsmc)) group by rollup(sydsmc)) c")
				.append(" where a.sydsmc = b.sydsmc and b.sydsmc = c.sydsmc order by r desc ");
		String[] inputValue = dao.uniteArrays(cc,xb,mz);
		String[] outputValue = new String[] {"r","sydsmc","rscc","rsxb","rsmz"};
		outputValue=dao.uniteArrays(outputValue,show);
		return dao.getList(sql.toString(), inputValue, outputValue);
	}
	
	/**
	 * 查询校区信息
	 * @return
	 * @throws Exception
	 */
	public String[] cxXq() throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" select dm xqdm,xqmc from dm_zju_xq order by xqdm ");
		String[] input = new String[] {};
		String output = "xqmc";
		return dao.getArray(sql.toString(), input, output);
	}
	
	
	/**
	 * 工具类 用于合并数组
	 * 
	 * @param beginArray
	 * @param endArray
	 * @return
	 */
	public String[] arrayAppendArray(String[] beginArray, String[] endArray) {
		if (beginArray == null || endArray == null) {
			return null;
		}
		String[] newArray = new String[(beginArray.length + endArray.length)];
		int p = 0;
		for (int i = 0; i < beginArray.length; i++) {
			newArray[p] = beginArray[i];
			p++;
		}
		for (int i = 0; i < endArray.length; i++) {
			newArray[p] = endArray[i];
			p++;
		}
		return newArray;
	}
	
	
	/**
	 * 合并数组
	 * @param arrays
	 * @return
	 * @author sjf
	 */
	public String[] uniteArrays(String[]...arrays){
		int length = 0;
		for (int i=0; i<arrays.length; i++){
			length += arrays[i].length;
		}
		
		String[] strs = new String[length];
		
		int count = 0;
		for(String[] array : arrays){
			for(int j=0; j<array.length; j++){
				strs[count++] = array[j];
			}
		}
		
		return strs; 
	}
}
