package xsgzgl.qgzx.cssz;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.xszz.sqsh.XszzSqshForm;
import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.form.User;

/**
 * 勤工助学-基础设置-参数设置
 * @author yeyipin
 * @since 2012.7.16
 */
public class QgzxCsszDAO extends SuperDAOImpl<QgzxCsszForm> {
//	DAO dao = DAO.getInstance();
	/**
	 * 获得参数信息Map
	 * @return
	 */
	public HashMap<String, String> getCssz() {
		String sql = "select * from xg_qgzx_csszb where rownum = 1 ";
		return dao.getMapNotOut(sql, new String[]{});
	}
	//是否开放申请,画面按钮显示控制
	public HashMap<String,String> sfkfsq(){
		StringBuilder sql = new StringBuilder();
		sql.append("select ");
		sql.append(" case when sfkfgwsq = 'on' and (to_char(sysdate,'YYYYMMDD') between gwsqkssj and gwsqjssj) then '1' else '0' end sfkfxndwgwsq");
		sql.append(" ,case when sfkfxwgwsq = 'on' and (to_char(sysdate,'YYYYMMDD') between xwgwsqkssj and xwgwsqjssj) then '1' else '0' end sfkfxwgwsq");
		sql.append(" ,case when sfkfxsgwsq = 'on' and (to_char(sysdate,'YYYYMMDD') between xsgwsqkssj and xsgwsqjssj) then '1' else '0' end sfkfxsgwsq");
		sql.append(" ,case when gzsqkg = 'on' and (to_char(sysdate,'YYYYMMDD') between gzsqkssj and gzsqjssj) then '1' else '0' end sfkfgzsq");
		sql.append(" from xg_qgzx_csszb where rownum = 1");
		return dao.getMapNotOut(sql.toString(), new String[]{});
	}
	public HashMap<String,String> getSplc(){
		StringBuilder sql = new StringBuilder();
		sql.append("select * from XG_QGZX_CSSZB");
		/*修改，重用字段，所以备注，后期修改区分*/
		// 校内岗位发布审批流程：yrdwsplc
		// 校外岗位发布审批流程：xwgwsplc
		// 校内岗位申请审批流程：xsgwsqsplc
		// 校外岗位申请审批流程：xwxsgwsqsplc
		// 工作时长维护审批流程：gzscwhsplc
		// 学生离职审批流程: xslzsplc
		// 工资申报审批流程:gzsqsplc
		String[] out = {"yrdwsplc","xwgwsplc","xsgwsqsplc","xwxsgwsqsplc","gzscwhsplc","xslzsplc","gzsqsplc"};
		return dao.getMap(sql.toString(),new String[]{},out);
	}
	/**
	 * 保存参数设置信息
	 * @param model
	 * @return
	 * @throws Exception
	 */
//	public boolean saveCssz(QgzxCsszForm model) throws Exception {
//		boolean flag = false;
//		String sql = "delete from xg_qgzx_csszb";
//		flag = dao.runUpdate(sql, new String[]{});
//		if(flag){
//			String cjbz = model.getCjbz();
//			String gdgcjbz = model.getGdgcjbz();
//			String xsgws = model.getXsgws();
//			String sxsz = model.getSxsz();
//			String kssj = model.getKssj();
//			String jssj = model.getJssj();
//			String ksyf = model.getKsyf();
//			String jsyf = model.getJsyf();
//			String sxzd = model.getSxzd();
//			String sfyxffje = model.getSfyxffje();
//			String sfjfhb = model.getSfjfhb();
//			String sfkfgwsq = model.getSfkfgwsq();
//			String gwsqkssj = model.getGwsqkssj();
//			String gwsqjssj = model.getGwsqjssj();
//			//增加学生岗位申请字段
//			String xsxsgws = model.getXsxsgws();
//			String xsgwsqjssj = model.getXsgwsqjssj();
//			String xsgwsqkssj = model.getXsgwsqkssj();
//			String sgwsqsplc = model.getXsgwsqsplc();
//			String sfkfxsgwsq = model.getSfkfxsgwsq();
//			String kcxs = model.getKcxs();
//			//岗位酬金
//			 String sfsdgwcjsx=model.getSfsdgwcjsx();
//			 String gwzgcjsx=model.getGwzgcjsx();
//			 String sfkgggwcjsx=model.getSfkgggwcjsx();
//			 String dsfxy=model.getDsfxy();
//			 String sfxyzgsc = model.getSfxyzgsc();
//
//			 String yrdwgwsqxn=model.getYrdwgwsqxn();
//			 String yrdwsplc = model.getYrdwsplc();
//
//			 String xsyjgwsqsplc = model.getXsyjgwsqsplc();//学生院级岗位申请审批流程
//			 String yjqxfw = model.getYjqxfw();
//			 String yjrskzjb = model.getYjrskzjb();
//
//			if("10125".equals(Base.xxdm)) {
//				String sqfs = model.getSqfs();
//				sql = "insert into xg_qgzx_csszb(cjbz,xsgws,sxsz,kssj,jssj,ksyf,jsyf,sxzd,sfyxffje,sfjfhb,sfkfgwsq,gwsqkssj,gwsqjssj,xsxsgws,xsgwsqkssj,xsgwsqjssj,xsgwsqsplc,sfkfxsgwsq,rskzjb,qxfw,sfsdgwcjsx,gwzgcjsx,sfkgggwcjsx,dsfxy,sfxyzgsc,kcxs,sqfs,yrdwsplc,xsyjgwsqsplc,yjqxfw,yjrskzjb) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
//				String[] input = {cjbz,xsgws,sxsz,kssj,jssj,ksyf,jsyf,sxzd,sfyxffje,sfjfhb,sfkfgwsq,gwsqkssj,gwsqjssj,xsxsgws,xsgwsqkssj,xsgwsqjssj,sgwsqsplc,sfkfxsgwsq,model.getRskzjb(),model.getQxfw(),sfsdgwcjsx,gwzgcjsx,sfkgggwcjsx,dsfxy,sfxyzgsc,kcxs,sqfs,yrdwsplc,xsyjgwsqsplc,yjqxfw,yjrskzjb};
//				flag = dao.runUpdate(sql, input);
//			}else if("10351".equals(Base.xxdm)){
//				 //温州大学个性化字段
//				 String pkscjbz = model.getPkscjbz();
//				 String pkscjzgsx = model.getPkscjzgsx();
//				 sql = "insert into xg_qgzx_csszb(cjbz,xsgws,sxsz,kssj,jssj,ksyf,jsyf,sxzd,sfyxffje,sfjfhb,sfkfgwsq,gwsqkssj,gwsqjssj,xsxsgws,xsgwsqkssj,xsgwsqjssj,xsgwsqsplc,sfkfxsgwsq,rskzjb,qxfw,sfsdgwcjsx,gwzgcjsx,sfkgggwcjsx,dsfxy,sfxyzgsc,kcxs,pkscjbz,pkscjzgsx,yrdwsplc,xsyjgwsqsplc,yjqxfw,yjrskzjb) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
//				 String[] input = {cjbz,xsgws,sxsz,kssj,jssj,ksyf,jsyf,sxzd,sfyxffje,sfjfhb,sfkfgwsq,gwsqkssj,gwsqjssj,xsxsgws,xsgwsqkssj,xsgwsqjssj,sgwsqsplc,sfkfxsgwsq,model.getRskzjb(),model.getQxfw(),sfsdgwcjsx,gwzgcjsx,sfkgggwcjsx,dsfxy,sfxyzgsc,kcxs,pkscjbz,pkscjzgsx,yrdwsplc,xsyjgwsqsplc,yjqxfw,yjrskzjb};
//				 flag = dao.runUpdate(sql, input);
//			}else if ("12309".equals(Base.xxdm)) {//默认【  用人单位申请岗位时可否更改岗位酬金上限】yes
//				sql = "insert into xg_qgzx_csszb(xsgws,kssj,jssj,ksyf,jsyf,sfyxffje,sfjfhb,sfkfgwsq,gwsqkssj,gwsqjssj,xsxsgws,xsgwsqkssj,xsgwsqjssj,xsgwsqsplc,sfkfxsgwsq,rskzjb,qxfw,sfkgggwcjsx,sfxyzgsc,kcxs,sfsdgwcjsx,yrdwgwsqxn,yrdwsplc,xsyjgwsqsplc,yjqxfw,yjrskzjb) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
//				String[] input = {xsgws,kssj,jssj,ksyf,jsyf,sfyxffje,sfjfhb,sfkfgwsq,gwsqkssj,gwsqjssj,xsxsgws,xsgwsqkssj,xsgwsqjssj,sgwsqsplc,sfkfxsgwsq,model.getRskzjb(),model.getQxfw(),"yes",sfxyzgsc,kcxs,"yes",yrdwgwsqxn,yrdwsplc,xsyjgwsqsplc,yjqxfw,yjrskzjb};
//				flag = dao.runUpdate(sql, input);
//			}else if("10704".equals(Base.xxdm)){//西安科技大学个性化
//				sql = "insert into xg_qgzx_csszb(cjbz,xsgws,sxsz,kssj,jssj,ksyf,jsyf,sxzd,sfyxffje,sfjfhb,sfkfgwsq,gwsqkssj,gwsqjssj,xsxsgws,xsgwsqkssj,xsgwsqjssj,xsgwsqsplc,sfkfxsgwsq,rskzjb,qxfw,sfsdgwcjsx,gwzgcjsx,sfkgggwcjsx,dsfxy,sfxyzgsc,kcxs,yrdwgwsqxn,yrdwsplc,gdgcjbz,xsyjgwsqsplc,yjqxfw,yjrskzjb) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
//				String[] input = {cjbz,xsgws,sxsz,kssj,jssj,ksyf,jsyf,sxzd,sfyxffje,sfjfhb,sfkfgwsq,gwsqkssj,gwsqjssj,xsxsgws,xsgwsqkssj,xsgwsqjssj,sgwsqsplc,sfkfxsgwsq,model.getRskzjb(),model.getQxfw(),sfsdgwcjsx,gwzgcjsx,sfkgggwcjsx,dsfxy,sfxyzgsc,kcxs,yrdwgwsqxn,yrdwsplc,gdgcjbz,xsyjgwsqsplc,yjqxfw,yjrskzjb};
//				flag = dao.runUpdate(sql, input);
//			}else {
//				sql = "insert into xg_qgzx_csszb(cjbz,xsgws,sxsz,kssj,jssj,ksyf,jsyf,sxzd,sfyxffje,sfjfhb,sfkfgwsq,gwsqkssj,gwsqjssj,xsxsgws,xsgwsqkssj,xsgwsqjssj,xsgwsqsplc,sfkfxsgwsq,rskzjb,qxfw,sfsdgwcjsx,gwzgcjsx,sfkgggwcjsx,dsfxy,sfxyzgsc,kcxs,yrdwgwsqxn,yrdwsplc,xsyjgwsqsplc,yjqxfw,yjrskzjb) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
//				String[] input = {cjbz,xsgws,sxsz,kssj,jssj,ksyf,jsyf,sxzd,sfyxffje,sfjfhb,sfkfgwsq,gwsqkssj,gwsqjssj,xsxsgws,xsgwsqkssj,xsgwsqjssj,sgwsqsplc,sfkfxsgwsq,model.getRskzjb(),model.getQxfw(),sfsdgwcjsx,gwzgcjsx,sfkgggwcjsx,dsfxy,sfxyzgsc,kcxs,yrdwgwsqxn,yrdwsplc,xsyjgwsqsplc,yjqxfw,yjrskzjb};
//				flag = dao.runUpdate(sql, input);
//			}
//		}
//		return flag;
//	}
	
	public boolean saveSplcCssz(QgzxCsszForm model) throws Exception {
		boolean flag = false;
		String sql = "delete from XG_QGZX_SPLCSSZB";
		flag = dao.runUpdate(sql, new String[]{});
		if(flag){
			sql = "insert into XG_QGZX_SPLCSSZB(sqkg,splc,sqkssj,sqjssj) values(?,?,?,?)";
			String[] input = {model.getGzsqkg(),model.getGzsqsplc(),model.getGzsqkssj(),model.getGzsqjssj()};
			flag = dao.runUpdate(sql, input);
		}
		return flag;
	}
	
	public String getCsz(String csdm) {
		String sql = "select csz from xg_qgzx_new_cspzb where csdm = ? ";
		return dao.getOneRs(sql, new String[]{csdm}, "csz");
	}
	/**
	 * 新勤工参数设置保存
	 */
	public boolean saveZjdxCssz(QgzxCsszForm model) throws Exception {
		boolean flag = false;
		String sql = " delete from xg_qgzx_csszb ";
		flag = dao.runUpdate(sql, new String[]{});
		if(flag){
			String cjbz = model.getCjbz();
			String sxsz = model.getSxsz();
			String ksyf = model.getKsyf();
			String jsyf = model.getKsyf();
			String kssj = model.getKssj();
			String jssj = model.getJssj();
			String sxzd = model.getSxzd();
			/*是否允许超过酬金上限,2017-07-27,孟威*/
			String sfyxcgcjsx = model.getSfyxcgcjsx();
			sql = "insert into xg_qgzx_csszb(cjbz,sxsz,ksyf,jsyf,kssj,jssj,sxzd,sfyxcgcjsx) values(?,?,?,?,?,?,?,?)";
			String[] input = {cjbz,sxsz,ksyf,jsyf,kssj,jssj,sxzd,sfyxcgcjsx};
			flag = dao.runUpdate(sql, input);
		}
		return flag;
	}
	
	public HashMap<String, String> getSplcCssz() {
		String sql = "select * from XG_QGZX_SPLCSSZB where rownum = 1 ";
		return dao.getMapNotOut(sql, new String[]{});
	}
	
	public String getSqkg() {
		String sql = "select case when t.sqkg = 'on' and sysdate between to_date(nvl(t.sqkssj,'1990-01-01 00:00'),'yyyy-mm-dd hh24:mi') "+
				" and to_date(nvl(t.sqjssj,'9999-01-01 00:00'),'yyyy-mm-dd hh24:mi') + 1 then '1' else '0' end sqkg "+
				" from XG_QGZX_SPLCSSZB t where 1=1 ";
		return dao.getOneRs(sql, new String[]{}, "sqkg");
	}

	@Override
	protected void setTableInfo() {
		super.setKey("id");
		super.setTableName("XG_QGZX_CSSZB");
		super.setClass(QgzxCsszForm.class);
	}

	@Override
	public List<HashMap<String, String>> getPageList(QgzxCsszForm qgzxCsszForm) throws Exception {
		return null;
	}

	@Override
	public List<HashMap<String, String>> getPageList(QgzxCsszForm qgzxCsszForm, User user) throws Exception {
		return null;
	}
}
