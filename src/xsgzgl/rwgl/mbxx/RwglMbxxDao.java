package xsgzgl.rwgl.mbxx;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.comm.search.SearchModel;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;

public class RwglMbxxDao extends DAO{

	
	/**
	 * 民兵信息查询
	 * @param model
	 * @return
	 */
	public ArrayList<String[]> mbxxCx(RwglMbxxForm model) throws Exception{
		// 高级查询MODEL
		SearchModel searchModel = model.getSearchModel();
		// 用户对象
		User user = model.getUser();
		String[] colList = new String[] { "sfzh", "xm", "xb", "zw", "csrq", "rdsj", "zzmmmc", "sftwjr" };
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(searchModel);
		String[] inputV = SearchService.getTjInput(searchModel);
		//权限控制 	
		String searchTjQx = "";
		StringBuilder sql = new StringBuilder();
		sql.append("select rownum r,a.* from view_xg_rwgl_mbxxb a where 1 = 1");
		// ====================SQL拼装 end================================
		return CommonQueryDAO.commonQuery(sql.toString(), searchTj+searchTjQx, inputV, colList, model);
	}

	
	/**
	 * 民兵信息验证
	 * @param model
	 * @return
	 */
	public String mbxxYz(RwglMbxxForm model) {
		String sfzh = model.getSfzh();
		String[] inputValue = new String[]{sfzh};
		String sql = "select count(1) num from XG_RWGL_MBXXB where sfzh = ?";
		return getOneRs(sql, inputValue, "num");
	}
	


	/**
	 * 民兵信息删除
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	public boolean mbxxSc(List<String[]> params) throws SQLException {
		String sql = "delete from XG_RWGL_MBXXB where sfzh = ?";
		int[] result = runBatch(sql, params);
		return checkBatchResult(result);
	}


	/**
	 * 获得一条民兵信息
	 * @param model
	 * @return
	 */
	public HashMap<String, String> getMbxx(RwglMbxxForm model) {
		String sql="select * from view_xg_rwgl_mbxxb where sfzh = ? ";
		String[] inputValue = new String[]{model.getPkValue()};
		return getMapNotOut(sql, inputValue);
	}


	/**
	 * 民兵信息修改保存
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public boolean mbxxBc(RwglMbxxForm model) throws Exception {
		String doType = model.getDoType();
		String xm = model.getXm();
		String xb = model.getXb();
		String csrq = model.getCsrq();
		String sfzh = model.getSfzh();
		String whcd = model.getWhcd();
		String zy = model.getZy();
		String zc = model.getZc();
		String zw = model.getZw();
		String rdsj = model.getRdsj();
		String zzmmdm = model.getZzmmdm();
		String sftwjr = model.getSftwjr();
		String zygw = model.getZygw();
		String gzdw = model.getGzdw();
		String lxfs = model.getLxfs();
		String bgdh = model.getBgdh();
		String dh = model.getDh();
		String mbzw = model.getMbzw();
		String zzbx = model.getZzbx();
		String jsxl = model.getJsxl();
		String jtdz = model.getJtdz();
		String[] input = new String[]{xm,xb,csrq,sfzh,whcd,zy,zc,zw,rdsj,zzmmdm,sftwjr,zygw,gzdw,lxfs,bgdh,dh,mbzw,zzbx,jsxl,jtdz};
		String sql = "insert into XG_RWGL_MBXXB(xm,xb,csrq,sfzh,whcd,zy,zc,zw,rdsj,zzmmdm,sftwjr,zygw,gzdw,lxfs,bgdh,dh,mbzw,zzbx,jsxl,jtdz)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		if("update".equalsIgnoreCase(doType)){
			input = new String[]{xm,xb,csrq,whcd,zy,zc,zw,rdsj,zzmmdm,sftwjr,zygw,gzdw,lxfs,bgdh,dh,mbzw,zzbx,jsxl,jtdz,sfzh};
			sql = "update XG_RWGL_MBXXB set xm=?,xb=?,csrq=?,whcd=?,zy=?,zc=?,zw=?,rdsj=?,zzmmdm=?,sftwjr=?,zygw=?,gzdw=?,lxfs=?,bgdh=?,dh=?,mbzw=?,zzbx=?,jsxl=?,jtdz=? where sfzh = ?";
		}
		return runUpdate(sql, input);
	}




}
