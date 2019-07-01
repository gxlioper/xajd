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
	 * �����Ϣ��ѯ
	 * @param model
	 * @return
	 */
	public ArrayList<String[]> mbxxCx(RwglMbxxForm model) throws Exception{
		// �߼���ѯMODEL
		SearchModel searchModel = model.getSearchModel();
		// �û�����
		User user = model.getUser();
		String[] colList = new String[] { "sfzh", "xm", "xb", "zw", "csrq", "rdsj", "zzmmmc", "sftwjr" };
		// �߼���ѯ����
		String searchTj = SearchService.getSearchTj(searchModel);
		String[] inputV = SearchService.getTjInput(searchModel);
		//Ȩ�޿��� 	
		String searchTjQx = "";
		StringBuilder sql = new StringBuilder();
		sql.append("select rownum r,a.* from view_xg_rwgl_mbxxb a where 1 = 1");
		// ====================SQLƴװ end================================
		return CommonQueryDAO.commonQuery(sql.toString(), searchTj+searchTjQx, inputV, colList, model);
	}

	
	/**
	 * �����Ϣ��֤
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
	 * �����Ϣɾ��
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
	 * ���һ�������Ϣ
	 * @param model
	 * @return
	 */
	public HashMap<String, String> getMbxx(RwglMbxxForm model) {
		String sql="select * from view_xg_rwgl_mbxxb where sfzh = ? ";
		String[] inputValue = new String[]{model.getPkValue()};
		return getMapNotOut(sql, inputValue);
	}


	/**
	 * �����Ϣ�޸ı���
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
