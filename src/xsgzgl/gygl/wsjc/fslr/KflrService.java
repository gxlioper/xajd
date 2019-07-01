/**
 * @部门:学工产品事业部
 * @日期：2017-6-1 下午04:12:05 
 */  
package xsgzgl.gygl.wsjc.fslr;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.transaction.TransactionControl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 柳俊[工号:1282]
 * @时间： 2017-6-1 下午04:12:05 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class KflrService extends SuperServiceImpl<KflrForm, KflrDao>{
	private KflrDao dao = new KflrDao();
	
	/** 
	 * @描述:获取单个寝室扣分明细(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-6-1 下午04:13:52
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String,String>> getKfmx(KflrForm t){
		return dao.getKfmx(t);
	}
	
	/**
	 * @throws Exception 
	 * @throws SQLException  
	 * @描述:批量插入(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-6-1 下午05:12:02
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean plInsert(KflrForm t) throws Exception{
		boolean result = true;		
		if(null != t.getDelArr() && t.getDelArr().length>0){			
			result = dao.plSc(t);		
		}if(null != t.getKfArr() && t.getKfArr().length>0){
			result = dao.plInsert(t);
		}
		dao.scFslr(t);//删除寝室分数表
		return result;		
	}
	
	/**
	 * @throws UnsupportedEncodingException 
	 * @throws SQLException  
	 * @描述:批量录入扣分依据(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-6-5 下午02:05:53
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean kflrUpdateKfyj(String jcrcid,String kfyjStr) throws SQLException, UnsupportedEncodingException{
		String[] str = kfyjStr.split("!!");
		String[] target = new String[str.length];
		List<String[]> dels = new ArrayList<String[]>();
		for(int i = 0;i<str.length;i++){
			StringBuilder sq = new StringBuilder();
			String[] strr1 = str[i].split("!@");
			if(strr1[1].split("_").length<2){
				String del = jcrcid + "_" + strr1[0] + "_" + ((strr1[1]).split("_"))[0];
				dels.add(del.split("_"));
				continue;
			}
			sq.append(((strr1[1]).split("_"))[1].replaceAll("，", ","));//设置扣分依据
			sq.append("_");
			sq.append(jcrcid);//设置检查日程id
			sq.append("_");
			sq.append(strr1[0]);//设置楼栋代码
			sq.append("_");
			sq.append(((strr1[1]).split("_"))[0]);//设置寝室号
			target[i] = sq.toString();
		}
		if(dels.size()>1){
			dao.plScFskf(dels);
		}
		return dao.plUpdateKfyj(target);
	}
	
	/** 
	 * @描述:判断是否为保存分数(这里用一句话描述这个方法的作用)
	 * @作者：柳俊 [工号：1282]
	 * @日期：2017-6-6 上午10:17:32
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean isBcfs(KflrForm t){
		return dao.isBcfs(t);
	}
	
	/**
	 * @throws SQLException  
	 * @描述:删除扣分明细(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-6-8 上午10:01:58
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param jcrcid
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	@TransactionControl
	public boolean plscJcrc(String[] jcrcid) throws SQLException{
		boolean result;
		result = dao.scKfmx(jcrcid);
		if(result){
			dao.plScJcrc(jcrcid);
		}
		return result;
		
	}
	
}
