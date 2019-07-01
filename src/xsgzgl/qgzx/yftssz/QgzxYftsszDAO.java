package xsgzgl.qgzx.yftssz;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.jdbc.core.support.SqlLobValue;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.search.SearchModel;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.String.StringUtils;
import xsgzgl.qgzx.glygl.QgzxGlyglService;
import xsgzgl.qgzx.gwglnew.QgzxGwglForm;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.wjcf.cfsh.CfshForm;
import com.zfsoft.xgxt.xlzx.zxzxjl.ZxzxjlxxModel;

import common.Globals;
import common.XszzXmdm;
import freemarker.template.SimpleDate;
/**
 * 勤工助学-勤工岗位管理-岗位信息管理
 */
public class QgzxYftsszDAO extends SuperDAOImpl<QgzxYftsszForm> {
	
	DAO dao = DAO.getInstance();
	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(QgzxYftsszForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(QgzxYftsszForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		// TODO 自动生成方法存根
		
	}
	
	/**
	 * 查询
	 */
	public String[]  getYftsszArr(QgzxYftsszForm t, User user)
		throws Exception {
		String sql = "select yf,ts from (select * from xg_qgzx_sxgwmyycqts where xn <= ? order by xn desc) where rownum <= 12 order by yf ";
		return dao.getArray(sql, new String[] {t.getXn()},"ts");
	}
	
	/**
	 * 批量修改月份信息
	 * @param pkValue
	 * @return
	 * @throws Exception 
	 */
	public boolean xgYftssz(List<String[]> params,QgzxYftsszForm t) throws Exception {
		String sql1 = "delete from xg_qgzx_sxgwmyycqts where xn=? ";
		int result1 = dao.runDelete(sql1, new String[] {t.getXn()});
		String sql2 = "insert into xg_qgzx_sxgwmyycqts(yf,ts,xn) values(?,?,?)";
		int[] result2 = dao.runBatch(sql2, params);
		return dao.checkBatchResult(result2);
	}
}
