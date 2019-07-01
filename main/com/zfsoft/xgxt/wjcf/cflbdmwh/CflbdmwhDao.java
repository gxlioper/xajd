/**
 * @部门:学工产品事业部
 * @日期：2013-10-24 上午10:55:16 
 */  
package com.zfsoft.xgxt.wjcf.cflbdmwh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.wjcf.cfsb.CfsbglForm;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 违纪管理模块
 * @类功能描述: (处分类别代码维护) 
 * @作者： 陈敏杰[工号:913]
 * @时间： 2013-10-24 上午10:55:16 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class CflbdmwhDao extends SuperDAOImpl<CflbdmwhForm> {


	@Override
	public List<HashMap<String, String>> getPageList(CflbdmwhForm model)
			throws Exception {
		
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder("select a.* from (select a.cflbdm, a.cflbmc,a.jsslqsr, replace(nvl(b.lcxx,'无需审核'),';','->') spl,  replace(replace(replace(a.sfkss, 'no', '不可申诉'), 'xs', '学生可申诉'), 'js', '教师可申诉') sfkss,sfksqjc, a.ssslgzr,");
		sql.append("  a.sfszcfqx, (case when  a.sfszcfqx='0' then '否' when  a.sfszcfqx='1' then '是' else '否' end) cfqxflag,cfqx,cjsj ");
		sql.append("from XG_WJCF_CFLBDMB a left join ( select splc, mc, lcxx  from (select splc,a.mc,  to_char(WM_CONCAT(c.mc) over(partition by splc order by xh )) lcxx, xh,row_number() over(partition by splc order by xh desc ) as ddd  from xg_xtwh_splc a, xg_xtwh_spbz b, xg_xtwh_spgw c  where djlx = 'wjcf' and a.id = b.splc   and b.spgw = c.id) b   where ddd = 1  ) b on a.spl = b.splc)a where 1=1  ");
		
		if (!StringUtil.isNull(model.getCflbmc())){
			params.add(model.getCflbmc());
			sql.append(" and cflbmc like '%'||?||'%'");
		}

		
		
		return getPageList(model, sql.toString(), params.toArray(new String[]{}));
	}

	@Override
	public List<HashMap<String, String>> getPageList(CflbdmwhForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}


	@Override
	protected void setTableInfo() {
		this.setKey("cflbdm");
		this.setTableName("xg_wjcf_cflbdmb");
		this.setClass(CflbdmwhForm.class);

	}

	/** 
	 * @系统名称: 学生工作管理系统
	 * @模块名称: 违纪管理模块
	 * @类功能描述: (处分类别名称是否存在) 
	 * @作者： 陈敏杰[工号:913]
	 * @时间： 2013-10-24 上午10:52:35 
	 * @版本： V1.0
	 * @修改记录: 类修改者-修改日期-修改说明  
	 */
	public boolean checkIsExist(CflbdmwhForm myForm) {
		
		String sql="select cflbmc from xg_wjcf_cflbdmb where cflbmc=?";
		String[] inputVal=null;
		if(myForm.getCflbdm()!=null&&!myForm.getCflbdm().equalsIgnoreCase("")){
			sql+=" and cflbdm<>?";
			inputVal=new String[2];
			inputVal[0]=myForm.getCflbmc();
			inputVal[1]=myForm.getCflbdm();
		}else{
			inputVal=new String[1];
			inputVal[0]=myForm.getCflbmc();
		}
		String cflbmc=dao.getOneRs(sql, inputVal, "cflbmc");
		return cflbmc!=null&&!"".equalsIgnoreCase(cflbmc);
	}
	
	/** 
	 * @系统名称: 学生工作管理系统
	 * @模块名称: 违纪管理模块
	 * @类功能描述: (根据处分类别获取处分类别信息)
	 * @作者： 王洪锦[工号:1004]
	 * @时间： 2013-11-26 下午3:44:35 
	 * @版本： V1.0
	 * @修改记录: 类修改者-修改日期-修改说明  
	 */
	public  HashMap<String, String> cflbInfoByMc(String cflbmc) {
		String sql="select sfszcfqx,cfqx,qxnsfkzz from xg_wjcf_cflbdmb where cflbmc=?";
		HashMap<String, String> cflbInfo=dao.getMapNotOut(sql, new String[]{cflbmc});
		return cflbInfo;
	}
	
	/** 
	 * @系统名称: 学生工作管理系统
	 * @模块名称: 违纪管理模块
	 * @类功能描述: (根据处分类别获取处分类别信息)
	 * @作者： 王洪锦[工号:1004]
	 * @时间： 2013-11-26 下午3:44:35 
	 * @版本： V1.0
	 * @修改记录: 类修改者-修改日期-修改说明  
	 */
	public  HashMap<String, String> cflbInfoById(String cflbdm) {
		String sql="select sfszcfqx,cfqx,qxnsfkzz from xg_wjcf_cflbdmb where cflbdm=?";
		HashMap<String, String> cflbInfo=dao.getMapNotOut(sql, new String[]{cflbdm});
		return cflbInfo;
	}
	
	/**
	 * 
	 * @描述:(处分类别代码删除)
	 * @作者：陈敏杰[工号：913]
	 * @日期：2013-10-24 下午01:33:16
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws
	 */
	@Override
	public int runDelete(String[] values) throws Exception {
		StringBuilder sql=new StringBuilder();
		sql.append("delete xg_wjcf_cflbdmb a  where (");
		for (int i = 0 , n = values.length ; i < n ; i++){
			sql.append("cflbdm=?");
			if (i != n-1){
				sql.append(" or ");
			}
		}
		sql.append(")and not exists(select 1 from xg_wjcf_wjcfsbb t where a.cflbdm=t.cflbdm)");
		return dao.runDelete(sql.toString(), values);
	}

	/** 
	 * @描述:根据处分类别代码获取处分发文权限
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年3月15日 下午2:19:47
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param cflbdm
	 * @return
	 * String 返回类型 
	 * @throws 
	 */
	public String getFwqxByLbdm(String cflbdm) {
		String sql = "SELECT CFFWQX FROM XG_WJCF_CFLBDMB WHERE CFLBDM = ?";
		return dao.getOneRs(sql, new String[]{cflbdm}, "CFFWQX");
	}
	
	/**
	 * @description	： 获取违纪处分名称
	 * @author 		： 柳俊（1282）
	 * @date 		：2017-12-1 上午11:14:52
	 * @return
	 */
	public List<HashMap<String,String>> getWjcfmcList(){
		String sql = "select cflbmc as dm,cflbmc as mc from XG_WJCF_CFLBDMB";
		return dao.getListNotOut(sql, new String[]{});
	}
	
}
