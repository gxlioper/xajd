package xgxt.pjpy.zjlg.dycj;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.MakeQuery;

public class DycjDAO {

	DAO dao = DAO.getInstance();
	
	private final StringBuilder QUERY_PSFBYFDY_SQL = new StringBuilder("select a.*,a.xh||a.xn pk,rownum r,(case when xysh='通过' then 'readonly=readonly' else '' end) dis from (select a.*,b.zwpyf")
			.append(",b.bjpyf,b.xyfjf,nvl(b.xysh,'未审核') xysh,b.lrsj,b.xh cz from (select xh,xm,a.xydm,a.zydm,a.bjdm")
			.append(",a.nj,a.bjmc,? xn,a.xb from view_xsjbxx a) a left join zjlg_dypsf b on a.xh=b.xh and a.xn=b.xn order by xydm,nj,zydm,bjdm) a");
	
	private final StringBuilder QUERY_WSFBYFDY_SQL = new StringBuilder("select a.*,a.xh||a.xn pk,rownum r,(case when xysh='通过' then 'readonly=readonly' else '' end) dis from (select a.*,")
			.append("b.qsf,b.xyfjf,b.xysh,b.lrsj,b.iszds from (select xh,xm,a.xydm,a.zydm,a.bjdm")
				.append(",a.nj,a.bjmc,? xn,a.xb from view_xsjbxx a) a left join zjlg_dywsf b on a.xh=b.xh and a.xn=b.xn order by xydm,nj,zydm,bjdm) a");
	
	private final StringBuilder QUERY_KQFBYFDY_SQL = new StringBuilder("select a.*,a.xh||a.xn pk,rownum r from (select a.*,")
			.append("b.kqf,b.lrsj from (select xh,xm,a.xydm,a.zydm,a.bjdm")
			.append(",a.nj,a.bjmc,? xn,a.xb from view_xsjbxx a) a left join zjlg_dykqf b on a.xh=b.xh and a.xn=b.xn order by xydm,nj,zydm,bjdm) a");
	//========================2010.10.14 edit by luojw============================
	private final StringBuilder QUERY_DYZF_SQL = new StringBuilder("select a.*,rownum r from (select t.* ")
			.append(",nvl(case when instr(to_char(t.psfzf),'.',1,1)=1 then '0'||to_char(t.psfzf) else to_char(t.psfzf) end, '0') pf ")
			.append(",nvl(case when instr(to_char(t.wszpf),'.',1,1)=1 then '0'||to_char(t.wszpf) else to_char(t.wszpf) end, '0') wf ")
			.append(",nvl(case when instr(to_char(t.kqzpf),'.',1,1)=1 then '0'||to_char(t.kqzpf) else to_char(t.kqzpf) end, '0') kf ")
			.append(",nvl(case when instr(to_char(t.zf),'.',1,1)=1 then '0'||to_char(t.zf) else to_char(t.zf) end, '0') dyzf ")
			.append("from (")
			.append("(select a.*,a.xh||a.xn pk,(kqzpf+wszpf+psfzf) zf from (select a.*,(case when kqfbl = '0' then nvl(kqf,0) when to_number(kqfbl)>to_number(nvl(kqf,0)) then nvl(kqf,0) else nvl(kqfbl,0) end) kqzpf,")
			.append("(case when wszf = '0' then nvl(wszf,0) when to_number(wsfbl)>to_number(wszf) then nvl(wszf,0) else to_number(nvl(wsfbl,0)) end) wszpf,")
			.append("(case when psfbl = '0' then (zpzf+bpzf+xyfjf) when to_number(psfbl)>to_number(zpzf+bpzf+xyfjf) then (zpzf+bpzf+xyfjf) else to_number(psfbl) end) psfzf from (")
			.append("select a.*,(qsf+wsxyfjf) wszf,(case when isbl='1' then (round(zwpyf*zpfbl/100,2)) else to_number(zwpyf) end) zpzf,")
			.append("(case when isbl='1' then (round(bjpyf*bjfbl/100,2)) else to_number(bjpyf) end) bpzf from (")
			.append("select a.*,(select kqf from zjlg_dykqf b where a.xh=b.xh and a.xn=b.xn) kqf,")
			.append("nvl((select case when iszds='checked' then '是' else '否' end from zjlg_dywsf b where a.xh=b.xh and a.xn=b.xn and xysh='通过'),'否') iszds,")
			.append("(select nvl(qsf,0) from zjlg_dywsf b where a.xh=b.xh and a.xn=b.xn and xysh='通过') qsf,")
			.append("(select nvl(xyfjf,0) from zjlg_dywsf b where a.xh=b.xh and a.xn=b.xn and xysh='通过') wsxyfjf,")
			.append("nvl(b.zwpyf,0) zwpyf,nvl(b.bjpyf,0) bjpyf,nvl(b.xyfjf,0) xyfjf,b.isbl,nvl(b.zpfbl,0) zpfbl,nvl(b.bjfbl,0) bjfbl,nvl(b.psfbl,0) psfbl,nvl(b.wsfbl,0) wsfbl,nvl(b.kqfbl,0) kqfbl from (")
			.append("select a.xh,a.xm,a.xb,a.nj,a.xydm,a.zydm,a.bjdm,a.xymc,a.zymc,a.bjmc,? xn from view_xsjbxx a where a.xjztm='有'")
			.append(") a left join view_zjlg_dypszf b on a.xh=b.xh and a.xn=b.xn and b.xysh='通过'")
			.append(") a) a) a ) t ) ) a ");
	
	private final StringBuilder QUERY_ZJLGDYZF_SQL = 
		//new StringBuilder("select a.* ,rownum r from (select a.*,a.xh||a.xn pk,nvl(b.zpf,0) dyzf  from (select a.xh,a.xm,a.xb,a.nj,a.xydm,a.zydm,a.bjdm,a.xymc,a.zymc,a.bjmc,? xn from view_xsjbxx a) a left join zjlg_zpf b on a.xh=b.xh and a.xn=b.xn) a");
	 new StringBuilder("select a.*,rownum r from (select t.* ")
		.append(",nvl(case when instr(to_char(t.psfzf),'.',1,1)=1 then '0'||to_char(t.psfzf) else to_char(t.psfzf) end, '0') pf ")
		.append(",nvl(case when instr(to_char(t.wszpf),'.',1,1)=1 then '0'||to_char(t.wszpf) else to_char(t.wszpf) end, '0') wf ")
		.append(",nvl(case when instr(to_char(t.kqzpf),'.',1,1)=1 then '0'||to_char(t.kqzpf) else to_char(t.kqzpf) end, '0') kf ")
		.append(",nvl(case when instr(to_char(t.zf),'.',1,1)=1 then '0'||to_char(t.zf) else to_char(t.zf) end, '0') dyzf ")
		.append("from (")
		.append("(select a.*,a.xh||a.xn pk,(kqzpf+wszpf+psfzf) zf from (select a.*,(case when kqfbl = '0' then nvl(kqf,0) when to_number(kqfbl)>to_number(nvl(kqf,0)) then nvl(kqf,0) else nvl(kqfbl,0) end) kqzpf,")
		.append("(case when wszf = '0' then nvl(wszf,0) when to_number(wsfbl)>to_number(wszf) then nvl(wszf,0) else to_number(nvl(wsfbl,0)) end) wszpf,")
		.append("(case when psfbl = '0' then (zpzf+bpzf+xyfjf) when to_number(psfbl)>to_number(zpzf+bpzf+xyfjf) then (zpzf+bpzf+xyfjf) else to_number(psfbl) end) psfzf from (")
		.append("select a.*,nvl(x.zpf,0) zpf,(qsf+wsxyfjf) wszf,(case when isbl='1' then (round(zwpyf*zpfbl/100,2)) else to_number(zwpyf) end) zpzf,")
		.append("(case when isbl='1' then (round(bjpyf*bjfbl/100,2)) else to_number(bjpyf) end) bpzf from (")
		.append("select a.*,(select kqf from zjlg_dykqf b where a.xh=b.xh and a.xn=b.xn) kqf,")
		.append("nvl((select case when iszds='checked' then '是' else '否' end from zjlg_dywsf b where a.xh=b.xh and a.xn=b.xn and xysh='通过'),'否') iszds,")
		.append("(select nvl(qsf,0) from zjlg_dywsf b where a.xh=b.xh and a.xn=b.xn and xysh='通过') qsf,")
		.append("(select nvl(xyfjf,0) from zjlg_dywsf b where a.xh=b.xh and a.xn=b.xn and xysh='通过') wsxyfjf,")
		.append("nvl(b.zwpyf,0) zwpyf,nvl(b.bjpyf,0) bjpyf,nvl(b.xyfjf,0) xyfjf,b.isbl,nvl(b.zpfbl,0) zpfbl,nvl(b.bjfbl,0) bjfbl,nvl(b.psfbl,0) psfbl,nvl(b.wsfbl,0) wsfbl,nvl(b.kqfbl,0) kqfbl from (")
		.append("select a.xh,a.xm,a.xb,a.nj,a.xydm,a.zydm,a.bjdm,a.xymc,a.zymc,a.bjmc,? xn from view_xsjbxx a where a.xjztm='有'")
		.append(") a left join view_zjlg_dypszf b on a.xh=b.xh and a.xn=b.xn and b.xysh='通过'")
		.append(") a left join zjlg_zpf x on a.xn=x.xn and a.xh=x.xh) a) a ) t)  order by t.xh,t.zpf ) a");
	public final StringBuilder QUERY_ZHSZCP_SQL = new StringBuilder("select zfpm,xh,zymc||bjmc mc,xm,dycpf,dypm,zycpf,zypm,ms,zhcpf from (")
			.append("select xh,xm,zymc,bjmc,zycpf,tycpf,dycpf,zhcpf,xn,cpzdm,xydm,zydm,bjdm,xb,nj,")
			.append("(case when dycpf <>'0' and cpzdm is not null then to_char(dense_rank() over (partition by xn,cpzdm order by to_number(dycpf) desc nulls last)) else '' end) dypm,")
			.append("(case when zycpf <>'0' and cpzdm is not null then to_char(dense_rank() over (partition by xn,cpzdm order by to_number(zycpf) desc nulls last)) else '' end) zypm,")
			.append("(case when zhcpf <>'0' and cpzdm is not null then to_char(dense_rank() over (partition by xn,cpzdm order by to_number(zhcpf) desc nulls last)) else '' end) zfpm,")
			.append("(select count(*) from cjb b where a.xh=b.xh and a.xn=b.xn and b.cj<60 and b.kcxz not like '%辅修二专业%' ) ms from view_zjlg_zhszcpf a")
			.append(") ");
	
	
	/**
	 * 平时分查询结果
	 * 
	 * @param model
	 * @param whereSql
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryPsfResult(DycjModel model, String whereSql)
			throws Exception {
		//==================2010.10.14 edit by luojw===============================
		MakeQuery aMakeQuery = new MakeQuery();
		aMakeQuery.makeQuery(
				new String[] { "xn", "nj", "xydm", "zydm", "bjdm" },
				aMakeQuery.baseListQueryArr, model);

		List<String> list = new ArrayList<String>();
		list.add(model.getXn());
		if (aMakeQuery.getInputList() != null
				&& aMakeQuery.getInputList().length > 0) {
			list.addAll(Arrays.asList(aMakeQuery.getInputList()));
		}

		return CommonQueryDAO.commonQuery(getQUERY_PSFBYFDY_SQL(), aMakeQuery
				.getQueryString()
				+ whereSql, list.toArray(new String[0]), new String[] { "pk","dis",
				"r", "xh", "xm", "bjmc", "xn", "zwpyf", "bjpyf", "xyfjf",
				"xysh","cz" }, model);
	}
	
	/**
	 * 卫生分查询结果
	 * @param model
	 * @param whereSql
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryWsfResult(DycjModel model, String whereSql)
			throws Exception {
		MakeQuery aMakeQuery = new MakeQuery();
		aMakeQuery.makeQuery(
				new String[] { "xn", "nj", "xydm", "zydm", "bjdm" },
				aMakeQuery.baseListQueryArr, model);
		
		List<String> list = new ArrayList<String>();
		list.add(model.getXn());
		if (aMakeQuery.getInputList() != null
				&& aMakeQuery.getInputList().length > 0) {
			list.addAll(Arrays.asList(aMakeQuery.getInputList()));
		}
		
		return CommonQueryDAO.commonQuery(getQUERY_WSFBYFDY_SQL(), aMakeQuery
				.getQueryString()
				+ whereSql, list.toArray(new String[0]), new String[] { "pk","dis",
				"r", "xh", "xm", "bjmc", "xn", "qsf","xyfjf",
				"xysh" }, model);
	}

	/**
	 * 考勤分查询结果
	 * @param model
	 * @param whereSql
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryKqfResult(DycjModel model, String whereSql)
			throws Exception {
		MakeQuery aMakeQuery = new MakeQuery();
		aMakeQuery.makeQuery(
				new String[] { "xn", "nj", "xydm", "zydm", "bjdm" },
				aMakeQuery.baseListQueryArr, model);
		
		List<String> list = new ArrayList<String>();
		list.add(model.getXn());
		if (aMakeQuery.getInputList() != null
				&& aMakeQuery.getInputList().length > 0) {
			list.addAll(Arrays.asList(aMakeQuery.getInputList()));
		}
		
		return CommonQueryDAO.commonQuery(getQUERY_KQFBYFDY_SQL(), aMakeQuery
				.getQueryString()
				+ whereSql, list.toArray(new String[0]), new String[] { "pk",
				"r", "xh", "xm", "bjmc", "xn", "kqf" }, model);
	}
	
	/**
	 * 保存平时分比例设置信息
	 * @param userName
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean savePsfblxx(String userName, DycjModel model)
			throws Exception {
		boolean result = dao
				.runUpdate(
						"delete from zjlg_dypsf_sz a where exists (select 1 from fdybjb b where a.bzrzgh=b.bjdm and b.zgh=?)",
						new String[] { userName });
		if (result) {
			result = dao
					.runUpdate(
							"insert into zjlg_dypsf_sz(bzrzgh,isbl,zpfbl,bjfbl) select bjdm,?,?,? from fdybjb where zgh=?",
							new String[] { model.getSave_isbl(),
									model.getSave_zpfbl(),
									model.getSave_bjfbl(), userName });
		}
		return result;
	}

	
	/**
	 * 考勤分查询结果
	 * @param model
	 * @param whereSql
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryDyfResult(DycjModel model, String whereSql)
			throws Exception {
		MakeQuery aMakeQuery = new MakeQuery();
		aMakeQuery.makeQuery(
				new String[] { "xn", "nj", "xydm", "zydm", "bjdm" },
				aMakeQuery.baseListQueryArr, model);
		
		List<String> list = new ArrayList<String>();
		list.add(model.getXn());
		if (aMakeQuery.getInputList() != null
				&& aMakeQuery.getInputList().length > 0) {
			list.addAll(Arrays.asList(aMakeQuery.getInputList()));
		}
		
		return CommonQueryDAO.commonQuery(getQUERY_DYZF_SQL(), aMakeQuery
				.getQueryString()
				+ whereSql, list.toArray(new String[0]), new String[] { "pk",
				"r", "xh", "xm", "bjmc", "xn", "pf", "wf", "iszds", "kf", "dyzf" }, model);
	}
	/**
	 * 考勤分查询结果
	 * @param model
	 * @param whereSql
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryZjlgDyfResult(DycjModel model, String whereSql)
			throws Exception {
		MakeQuery aMakeQuery = new MakeQuery();
		aMakeQuery.makeQuery(
				new String[] { "xn", "nj", "xydm", "zydm", "bjdm" },
				aMakeQuery.baseListQueryArr, model);
		
		List<String> list = new ArrayList<String>();
		list.add(model.getXn());
		if (aMakeQuery.getInputList() != null
				&& aMakeQuery.getInputList().length > 0) {
			list.addAll(Arrays.asList(aMakeQuery.getInputList()));
		}
		
		return CommonQueryDAO.commonQuery(getQUERY_ZJLGDYZF_SQL(), aMakeQuery
				.getQueryString()
				+ whereSql, list.toArray(new String[0]), new String[] { "pk",
				"r", "xh", "xm", "bjmc", "xn","pf", "wf", "iszds", "kf", "zpf" }, model);
	}
	
	/**
	 * 自动计算综合总分
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean countZhszcpf(DycjModel model) throws Exception {
		return dao.runProcuder("{call PROC_PJPY_ZJLG_ZHSZCP(?,?,?,?,?)}", new String[]{model.getQueryequals_xn(),model.getQueryequals_nj(),model.getQueryequals_xydm(),model.getQueryequals_zydm(),model.getQueryequals_bjdm()});
	}
	
	/**
	 * 导出综合排名信息
	 * @param model
	 * @param whereSql
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryZhszcppmxx(DycjModel model, String whereSql) throws Exception{
		MakeQuery aMakeQuery = new MakeQuery();
		aMakeQuery.makeQuery(
				new String[] { "xn", "nj", "xydm", "zydm", "bjdm", "cpzdm" },
				aMakeQuery.baseListQueryArr, model);
		
		return CommonQueryDAO.commonQueryNotFy(getQUERY_ZHSZCP_SQL(), aMakeQuery
				.getQueryString()
				+ whereSql + " order by xn,xydm,cpzdm,to_number(zfpm)", aMakeQuery.getInputList(), new String[] { "zfpm",
				"xh", "mc", "xm", "dycpf", "dypm", "zycpf", "zypm", "ms",
				"zhcpf" }, model);
	}
	
	public String getQUERY_PSFBYFDY_SQL() {
		return QUERY_PSFBYFDY_SQL.toString();
	}

	public String getQUERY_WSFBYFDY_SQL() {
		return QUERY_WSFBYFDY_SQL.toString();
	}

	public String getQUERY_KQFBYFDY_SQL() {
		return QUERY_KQFBYFDY_SQL.toString();
	}

	public String getQUERY_DYZF_SQL() {
		return QUERY_DYZF_SQL.toString();
	}

	public String getQUERY_ZHSZCP_SQL() {
		return QUERY_ZHSZCP_SQL.toString();
	}

	public String getQUERY_ZJLGDYZF_SQL() {
		return QUERY_ZJLGDYZF_SQL.toString();
	}
	
	
}
