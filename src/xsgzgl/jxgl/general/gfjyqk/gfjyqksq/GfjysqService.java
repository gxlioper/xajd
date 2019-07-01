/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package xsgzgl.jxgl.general.gfjyqk.gfjyqksq;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;

public class GfjysqService extends SuperServiceImpl<GfjysqForm, GfjysqDao>{
	private ShlcInterface shlc = new CommShlcImpl();
	public static final String SUBMIT = "submit";
	public static final String SAVE = "save";
	public static String _BCZSCID="-1";
	
	public boolean isExist(GfjysqForm model) {
		boolean flag = false;
		if("save".equalsIgnoreCase(model.getType())) {
			 flag =  dao.isExist(model);
		}else{
			 flag = dao.isExistforUpdate(model);
		}
		return flag;
	}
/**
 * @description	： 保存草稿或者提交
 * @author 		： CP（1352）
 * @date 		：2018-1-3 上午11:37:033
 * 
 * @param model
 * @return
 * @throws Exception 
 */
	public boolean saveGfjyqk(GfjysqForm model) throws Exception {
		if(model.getType().equals(SUBMIT)){
			model.setShzt(Constants.YW_SHZ);//审核中
		}else{
			model.setShzt(Constants.YW_WTJ);//未提交
		}
		// 获取审批流程
		String splc = dao.getShlcID();
		model.setSplc(splc);
		boolean insertResult = super.runInsert(model);
		if( SAVE.equalsIgnoreCase(model.getType())){
			return insertResult;
		}
		boolean result = false;
		if (insertResult && SUBMIT.equalsIgnoreCase(model.getType())) {
			//保存审核流程
			result = shlc.runSubmit(model.getSqid(),splc,model.getXh(),"gfjy_gfjyqk_sh.do","gfjy_gfjyqk_sq.do");
		}
		return result;
	}
	
	
	
public boolean updateGfjyqk(GfjysqForm model) throws Exception {
	if(model.getType().equals(SUBMIT)&&!Constants.YW_YTH.equals(model.getShzt())){
		// 获取审批流程
		model.setSplc(dao.getShlcID());
	}
	
	if(model.getType().equals(SUBMIT)){
		model.setShzt(Constants.YW_SHZ);//审核中
	}else{
		model.setShzt(Constants.YW_WTJ);//未提交
	}
	//修改国防情况分类的时候情况其他分类相关字段
	if ("1".equals(model.getGfqkfl())) {
		model.setCjrwsj("");model.setRwpzdw("");model.setTwfxsj("");model.setTwpzdw("");
		model.setPjpydw("");model.setPjpysj("");model.setJcdw("");model.setJcsj("");
		model.setCjhddd("");model.setCjhdsj("");
	}else if ("2".equals(model.getGfqkfl())) {
		model.setBydjsj("");model.setBydjdd("");model.setTwfxsj("");model.setTwpzdw("");
		model.setPjpydw("");model.setPjpysj("");model.setJcdw("");model.setJcsj("");
		model.setCjhddd("");model.setCjhdsj("");
	}else if ("3".equals(model.getGfqkfl())) {
		model.setBydjsj("");model.setBydjdd("");model.setCjrwsj("");model.setRwpzdw("");
		model.setPjpydw("");model.setPjpysj("");model.setJcdw("");model.setJcsj("");
		model.setCjhddd("");model.setCjhdsj("");
	}else if ("4".equals(model.getGfqkfl())) {
		model.setBydjsj("");model.setBydjdd("");model.setCjrwsj("");model.setRwpzdw("");
		model.setTwfxsj("");model.setTwpzdw("");model.setJcdw("");model.setJcsj("");
		model.setCjhddd("");model.setCjhdsj("");
	}else if ("5".equals(model.getGfqkfl())) {
		model.setBydjsj("");model.setBydjdd("");model.setCjrwsj("");model.setRwpzdw("");
		model.setTwfxsj("");model.setTwpzdw("");model.setPjpydw("");model.setPjpysj("");
		model.setCjhddd("");model.setCjhdsj("");
	}else if ("6".equals(model.getGfqkfl())) {
		model.setBydjsj("");model.setBydjdd("");model.setCjrwsj("");model.setRwpzdw("");
		model.setTwfxsj("");model.setTwpzdw("");model.setPjpydw("");model.setPjpysj("");
		model.setJcdw("");model.setJcsj("");
	}
	boolean insertResult = super.runUpdate(model);
	boolean result = false;
	if (insertResult && SUBMIT.equalsIgnoreCase(model.getType())) {
		//保存审核流程
		result = shlc.runSubmit(model.getSqid(), model.getSplc(),model.getXh(),"gfjy_gfjyqk_sh.do","gfjy_gfjyqk_sq.do");
		return result;
	}else{
		return insertResult;
	}
	
}
public String[] deleteGfjyqk(String[] ids) throws Exception {
	List<String> delId=new ArrayList<String>();//可删除的id集合
	StringBuffer noDel = new StringBuffer();
	boolean isHaveNoId = false;
	if(null==ids||ids.length<=0){
		return null;
	}
	for(String str:ids){
		if(dao.isCanDel(str)){
			delId.add(str);//记录删除id
		}else{
			HashMap<String, String> hm=dao.getGfjyqk(str);
			noDel.append(hm.get("xh"));
			noDel.append("&nbsp;");
			noDel.append(hm.get("xm"));
			noDel.append(",</br>");
			isHaveNoId=true;
		}
	}
	int i=delId.size()>0?gfjyqkDelete(delId.toArray(new String[]{})):0;
	String str=noDel.toString();
	//去除最后多余逗号
	str=isHaveNoId?str:_BCZSCID;
	return new String[]{String.valueOf(i),str};
}
private int gfjyqkDelete(String[] ids) throws Exception {
	for(String id:ids){
		shlc.deleteShjl(id);
	}
	return runDelete(ids);
}
public boolean submitGfjyqk(GfjysqForm model) throws Exception {
	if(!Constants.YW_YTH.equalsIgnoreCase(model.getShzt())){
		// 获取审批流程
		model.setSplc(dao.getShlcID());
	}
	model.setShzt(Constants.YW_SHZ);
	boolean resultHcccqjtx = dao.updateGfjyqk(model);
	boolean result = false;
	if(resultHcccqjtx){
		//保存审核流程
		result = shlc.runSubmit(model.getSqid(), model.getSplc(),model.getXh(),"gfjy_gfjyqk_sh.do","gfjy_gfjyqk_sq.do");
	}
	return result;
}
public boolean cancelGfjyqk(String ywid, String lcid) throws Exception {
	return shlc.firstStepCancle(ywid,lcid);
	}
public boolean updateCxGfjyqk(GfjysqForm model) throws Exception {
	return dao.updateGfjyqk(model);
}
public HashMap<String, String> getGfjyqkInfo(GfjysqForm model) {
	return dao.getGfjyqkInfo(model);
}

	
	
}
