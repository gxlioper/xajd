package xsgzgl.xtwh.general.mobilemessage;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

public class MobileMessageDao extends SuperDAOImpl<MobileMessageForm> {
	private static final String  ALLTEAANDSTU="全校师生";//全校师生
	private static final String  ALLTEA="全校教师";//全校教师
	private static final String  ALLSTU="全校学生";//全校学生
	private static final String DATE_FORMAT = "yyyy-MM-dd hh24:mi:ss";
	
	@Override
	public List<HashMap<String, String>> getPageList(MobileMessageForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}
	@Override
	public List<HashMap<String, String>> getPageList(MobileMessageForm t,
			User user) throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		
		StringBuilder sql = new StringBuilder();
		sql.append("select a.*,b.xm,case when length(a.fsnr)>50 then substr(a.fsnr,0,50)||'...'  else a.fsnr end  fsnrformat ");
		sql.append(" from  xg_xtwh_dxfsjlb a left join yhb b on a.fsr=b.yhm where a.fsr='"+user.getUserName()+"'");
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}
	@Override
	public MobileMessageForm getModel(String id) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select a.*,b.xm ");
		sql.append(" from  xg_xtwh_dxfsjlb a left join yhb b on a.fsr=b.yhm where a.id=?" );
		return getModel(sql.toString(), new String[]{id});
		
	}
	/**
	 * 
	 * @描述:获取收信人列表
	 * @作者：xiaxia[工号：1104]
	 * @日期：2016-8-1 下午03:17:43
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getSxrList(MobileMessageForm t){
		StringBuffer sql = new StringBuffer();
		if(ALLTEAANDSTU.equals(t.getSxr())){
			sql.append("select xm,lxdh from fdyxxb where lxdh is not null union all select xm, sjhm lxdh from view_xsjbxx where sjhm is not null");
		}else if(ALLTEA.equals(t.getSxr())){
			sql.append("select xm,lxdh from fdyxxb where lxdh is not null");
		}else if(ALLSTU.equals(t.getSxr())){
			sql.append("select xm, sjhm lxdh from view_xsjbxx where sjhm is not null");
		}else{
			return null;
		}
		
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	@Override
	protected void setTableInfo() {
		super.setClass(MobileMessageForm.class);
		super.setKey("id");
		super.setTableName("xg_xtwh_dxfsjlb");
	}
	
}
