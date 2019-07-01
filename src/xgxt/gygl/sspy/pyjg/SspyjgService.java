/**
 * @部门:学工产品(1)部
 * @日期：2018-4-28 上午09:24:52 
 */  
package xgxt.gygl.sspy.pyjg;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.UniqID;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 公寓管理管理模块
 * @类功能描述: 宿舍评优结果
 * @作者： Meng.Wei[工号:1186]
 * @时间： 2018-4-28 上午09:24:38 
 * @版本： V5.18.26
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class SspyjgService extends SuperServiceImpl<SspyjgForm,SspyjgDao>{
	private SspyjgDao dao = new SspyjgDao();
	
	/**
	 * @描述: 获取楼栋信息
	 * @作者： Meng.Wei[工号:1186]
	 * @日期： 2018-4-28 下午01:40:58
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getLdxxList() throws Exception{
		return dao.getLdxxList();
	}
	
	/**
	 * @描述: 寝室信息
	 * @作者： Meng.Wei[工号:1186]
	 * @日期： 2018-4-28 下午05:53:06
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param lddm
	 * @param ch
	 * @param qsh
	 * @return
	 * @throws Exception
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String,String> getQsxxForParam(String lddm,String ch,String qsh) throws Exception{
		return dao.getQsxxForParam(lddm,ch,qsh);
	}
	
	/**
	 * @描述: 保存
	 * @作者： Meng.Wei[工号:1186]
	 * @日期： 2018-4-28 下午03:52:12
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveFormSspyjg(SspyjgForm model,User user)throws Exception{
		
		boolean rs = true;
		
		/*生成唯一标识符*/
		String guid = UniqID.getInstance().getUniqIDHash();
		if(!this.checkIsNotRepeat(model)){
			throw new SystemException(MessageKey.SZTZ_XMSB_REPEAT);
		}
		
		/*当前操作人员用户名塞入*/
		model.setSjlrr(user.getUserName());
		
		/*获取当前操作时间塞入表中，配合操作人工号防止老师耍赖*/
		String DATE_FORMAT = "yyyy-MM-dd hh24:mi:ss";
		model.setSjlrsj(GetTime.getTimeByFormat(DATE_FORMAT));
		
		/*判断该数据是否为修改数据*/
		if(StringUtils.isNotNull(model.getGuid())){
			rs = dao.runUpdate(model);
			if(!rs){
				throw new SystemException(MessageKey.SYS_SAVE_FAIL);
			}
		}else{
			/*塞入唯一ID*/
			model.setGuid(guid);
			/*1:申请审核、2:结果增加*/
			model.setSjly("2");
			rs = dao.runInsert(model);
			if(!rs){
				throw new SystemException(MessageKey.SYS_SAVE_FAIL);
			}
		}
		return rs;
	}
	
	/**
	 * @描述: 验证唯一性:学年、学期、楼栋、寝室、评优项目
	 * @作者： Meng.Wei[工号:1186]
	 * @日期： 2018-4-28 下午04:01:50
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean checkIsNotRepeat(SspyjgForm model){
		return dao.checkIsNotRepeat(model);
	}
	
	/**
	 * @描述: 根据ID获取相关信息
	 * @作者： Meng.Wei[工号:1186]
	 * @日期： 2018-5-3 上午09:01:46
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param guid
	 * @return
	 * @throws Exception
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String,String> getInfoByGuid(String guid) throws Exception{
		return dao.getInfoByGuid(guid);
	}
}
