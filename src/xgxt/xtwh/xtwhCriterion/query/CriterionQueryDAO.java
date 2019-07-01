package xgxt.xtwh.xtwhCriterion.query;

import java.util.List;

import xgxt.utils.CommonQueryDAO;
import xgxt.utils.MakeQuery;
import xgxt.xtwh.xtwhCriterion.QxwhForm;

public class CriterionQueryDAO {
	/**
	 * 角色用户查询
	 * @param model
	 * @return
	 */
	public List<String[]> getJsyhList(QxwhForm model){
		StringBuilder sql = new StringBuilder();
		
		String[] colList = new String[]{"jslxdm", "jscmdm"};
		String[] colLikeList = new String[]{"yhm", "jsmc"};
		
		MakeQuery makeQuery = new MakeQuery();
		
		try {
			makeQuery.makeQuery(colList, colLikeList, model);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String otherQuery = makeQuery.getQueryString();
		
		sql.append("select rownum r,a.yhm,(select szbm from xg_xtwh_yhb b where b.yhm=a.yhm) szbm,a.jsdm,b.jsmc,b.jslxmc,b.jscmmc ")
			.append("from xg_xtwh_yhjsb a left join xg_view_xtwh_jswh b on a.jsdm=b.jsdm ");
		
		List<String[]> list = null;
		
		String[] outputs = new String[]{"jsmc", "jslxmc", "jscmmc", "yhm", "szbm"};
		String[] inputs = makeQuery.getInputList();
		try {
			list = CommonQueryDAO.commonQuery(sql.toString(), otherQuery, inputs, outputs, model);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	/**
	 * 获取角色权限List
	 * @param model
	 * @return
	 */
	public List<String[]> getJsqxList(QxwhForm model){
		StringBuilder sql = new StringBuilder();
		
		String[] colList = new String[]{"jslxdm", "jscmdm"};
		String[] colLikeList = new String[]{"jsmc"};
		
		MakeQuery makeQuery = new MakeQuery();
		
		try {
			makeQuery.makeQuery(colList, colLikeList, model);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String otherQuery = makeQuery.getQueryString();
		
		sql.append("select rownum r,a.*, b.gnmkmc, c.jsmc, c.jslxmc, c.jscmmc ")
			.append("from (select jsdm, gnmkdm, to_char(wm_concat(btnmc)) btns ")
			.append("from (select a.*, c.btnmc ")
			.append("from xg_xtwh_jscdqxb a ")
			.append("left join (select 'view' btndm, '查询' btnmc, 'view' btnid from dual ")
			.append("union ")
			.append("select btndm, btnmc, btnid from xg_xtwh_btndmb) c on a.cdgndm=c.btnid) ")
			.append("group by jsdm, gnmkdm) a ")
			.append("left join gnmkdmb b on a.gnmkdm = b.gnmkdm ")
			.append("left join (select a.*, b.jslxmc, c.jscmmc ")
			.append("from xg_xtwh_jswhb a left join xg_xtwh_jslxdmb b on a.jslxdm = b.jslxdm ")
			.append("left join xg_xtwh_yhcmdmb c on a.jscmdm = c.jscmdm) c on a.jsdm=c.jsdm ");
		
		List<String[]> list = null;
		
		String[] outputs = new String[]{"jsmc", "jslxmc", "jscmmc", "gnmkmc", "btns"};
		String[] inputs = makeQuery.getInputList();
		try {
			list = CommonQueryDAO.commonQuery(sql.toString(), otherQuery, inputs, outputs, model);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	/**
	 * 获取用户权限List
	 * @param model
	 * @return
	 */
	public List<String[]> getYhqxList(QxwhForm model){
		StringBuilder sql = new StringBuilder();
		
		String[] colList = new String[]{"jslxdm", "jscmdm"};
		String[] colLikeList = new String[]{"yhm"};
		
		MakeQuery makeQuery = new MakeQuery();
		
		try {
			makeQuery.makeQuery(colList, colLikeList, model);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String otherQuery = makeQuery.getQueryString();
		
		sql.append("select rownum r,a.*, b.bmmc, c.gnmkmc ")
			.append("from (select a.yhm, a.gnmkdm, to_char(wm_concat(btnmc)) btns ")
			.append("from (select yhm, gnmkdm, cdid from xg_xtwh_yhcdqxb ")
			.append("union ")
			.append("select yhm, gnmkdm, cdgndm ")
			.append("from xg_xtwh_yhjsb a, xg_xtwh_jscdqxb b ")
			.append("where a.jsdm = b.jsdm) a ")
			.append("left join (select 'view' btndm, '查询' btnmc, 'view' btnid ")
			.append("from dual union ")
			.append("select btndm, btnmc, btnid from xg_xtwh_btndmb) b on a.cdid=b.btnid ")
			.append("group by yhm, gnmkdm) a left join (select a.*, b.bmmc from xg_xtwh_yhb a ")
			.append("left join zxbz_xxbmdm b on a.szbm = b.bmdm) b on a.yhm=b.yhm ")
			.append("left join gnmkdmb c on a.gnmkdm = c.gnmkdm ");
		
		List<String[]> list = null;
		
		String[] outputs = new String[]{"yhm", "bmmc", "gnmkmc", "btns"};
		String[] inputs = makeQuery.getInputList();
		try {
			list = CommonQueryDAO.commonQuery(sql.toString(), otherQuery, inputs, outputs, model);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
}
