package xsgzgl.qgzx.mrgzkh.khjg;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;
import xsgzgl.qgzx.cjgl.QgzxCjglDAO;
import xsgzgl.qgzx.cjgl.QgzxCjglForm;
import xsgzgl.qgzx.cssz.QgzxCsszDAO;
import xsgzgl.qgzx.mrgzkh.khsq.GzkhKhsqDao;

import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.UniqID;

public class GzkhKhjgService extends SuperServiceImpl<GzkhKhjgForm, GzkhKhjgDao>{
	GzkhKhjgDao dao = new GzkhKhjgDao();
	
	/**
	 * 获取学生勤工岗位
	 */
	public List<HashMap<String,String>> getGwxx(GzkhKhjgForm model){
		return dao.getGwxx(model);
	}
	/**
	 * 获取学生勤工岗位所属部门
	 */
	public List<HashMap<String, String>> getYrbm(GzkhKhjgForm model){
		return dao.getYrbm(model);
	}
	/**
	 * 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-1-8 上午11:47:15
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
    public boolean Cjcl(GzkhKhjgForm model) throws Exception{
    	GzkhKhjgForm khjgForm = dao.getKhjg(model);
    	
    	boolean result=true;
    	if(null!=khjgForm){
	    		// 根据学号、学年、岗位代码填写日期删除结果表中数据
	    		dao.delKhjg(model);
    	}else{
    		khjgForm = new GzkhKhjgForm();
    	}
    	//获取学生酬金发放信息
		QgzxCjglDAO cjglDao = new QgzxCjglDAO();
		QgzxCjglForm cjglForm = cjglDao.getCjffXx(model);
		if(null!=cjglForm){
			//重新计算学生酬金信息
			Double gs=null;
//			 直接录入，保存修改时，需要减去修改前的工时
				double khjgFormGs = khjgForm.getGs() == null ? 0 : Double.parseDouble(khjgForm.getGs());
				double cjbz = khjgForm.getCjbz() == null ? 0 : Double.parseDouble(khjgForm.getCjbz());
				gs = Double.parseDouble(cjglForm.getGs())-khjgFormGs
				+Double.parseDouble(model.getGs());

			String je = String.valueOf(Double.parseDouble(cjglForm.getJe())-khjgFormGs*cjbz+Double.parseDouble(model.getGs())*Double.parseDouble(model.getCjbz()));
			cjglForm.setGs(String.valueOf(gs));
			cjglForm.setJe(je);
			result=cjglDao.runUpdate(cjglForm);
		}
		
    	else{
    		String id = UniqID.getInstance().getUniqIDHash();
    		String je = String.valueOf(Double.parseDouble(model.getGs())*Double.parseDouble(model.getCjbz()));
    		result=dao.saveCjxx(id,je, model);
    	}
    	return result;
    	
		
	}
    /**
     * 学生酬金信息重新计算
     */
    public boolean xsCjxxJs(GzkhKhjgForm model)throws Exception{
    	//获取学生酬金发放信息
		QgzxCjglDAO cjglDao = new QgzxCjglDAO();
		QgzxCjglForm cjglForm = new QgzxCjglForm();
		//审核不通过但不是一级审核的情况下撤销审核数据
		try {
			cjglForm = cjglDao.getCjffXx(model);
		} catch (NullPointerException e) {
			// TODO 自动生成 catch 块
			return true;
		}
		if(null==cjglForm){
			return true;
		}
		//重新计算学生酬金信息
		Double gs = Double.parseDouble(cjglForm.getGs())-Double.parseDouble(model.getGs());
		QgzxCsszDAO csszDao = new QgzxCsszDAO();
    	HashMap<String,String> csszMap  = csszDao.getCssz();
		String je = String.valueOf(gs*Double.parseDouble(csszMap.get("cjbz")));
		cjglForm.setGs(String.valueOf(gs));
		cjglForm.setJe(je);
		return cjglDao.runUpdate(cjglForm);
    }

	// 保存
	public boolean saveKhjg(GzkhKhjgForm model, User user) throws Exception {
		//获取勤工助学参数设置
    	QgzxCsszDAO csszDao = new QgzxCsszDAO();
    	HashMap<String,String> csszMap  = csszDao.getCssz();
		String id = model.getId();
		model.setGs(gsFormat(model.getGs()));
		if ("save".equalsIgnoreCase(model.getType())) {
			id = UniqID.getInstance().getUniqIDHash();
			model.setId(id);
			model.setCjbz(csszMap.get("cjbz"));
			model.setSqsj(GetTime.getTimeByFormat("YYYY-MM-DD hh24:mi:ss"));
			
		}else{
			model.setCjbz(dao.getModel(id).getCjbz());
		}
		String[] gwxx = model.getGwdm().split(",");
		model.setGwdm(gwxx[0]);
		model.setXn(gwxx[1]);
		model.setCzyh(user.getUserName());
		model.setCjsj(GetTime.getTimeByFormat("YYYY-MM-DD hh24:mi:ss"));
		// 先更新薪酬
		boolean flag = Cjcl(model);
		// 再保存结果表
			flag = dao.runInsert(model);
		return flag;
	}
	
	/**
	 * 判断当前岗位是否有填写记录
	 */
	public boolean checkExistForSave(GzkhKhjgForm model){
		String[] gwxx = model.getGwdm().split(",");
		model.setXn(gwxx[1]);
		return dao.checkExistForSave(model);
	}
	
	public String gsFormat(String gs){
		int lastStr = gs.indexOf(".");
		if(gs.length()-1==lastStr){
			String[] gsArr= gs.split("\\.");
			gs=gsArr[0] ;
		}
		return gs;
	}
	
	/**
	 * 
	 * @描述: 考核结果增加酬金工时上限判断(增加)
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-1-20 上午11:25:21
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param xn
	 * @param gs
	 * @param gzrq
	 * @param cjbz
	 * @param sqid
	 * @param gwdm
	 * @return
	 * @throws Exception
	 * String 返回类型 
	 * @throws
	 */
	public String checkZjeAndGsJG(String xh, String xn, String gs, String gzrq, String cjbz, String sqid, String gwdm) throws Exception {

		QgzxCsszDAO csszDao = new QgzxCsszDAO();
		GzkhKhsqDao gzkhKhsqDAO = new GzkhKhsqDao();
		HashMap<String, String> gwxxMap = dao.getGwxx(gwdm);
		HashMap<String, String> csszMap = csszDao.getCssz();
		if (null == cjbz) {
			cjbz = csszMap.get("cjbz");
		}
		HashMap<String, String> xsCjxx = gzkhKhsqDAO.getCjffXxOfStu(xh, xn, gzrq);
		String cjgs = xsCjxx.get("gs") == null ? "0" : xsCjxx.get("gs");
		String je = xsCjxx.get("je") == null ? "0" : xsCjxx.get("je");
		String ffje = gzkhKhsqDAO.getCjByGwdm(xh, xn, gwdm, gzrq);
		if ("gs".equals(csszMap.get("sxzd")) && (Double.parseDouble(cjgs) + Double.parseDouble(gs) > Double.parseDouble(csszMap.get("sxsz")))) {
			return MessageUtil.getText(MessageKey.QGZX_MRGZKH_GSOUT, new String[] { xh,gzrq.substring(0, 7), csszMap.get("sxsz") });
		}
		if ("yes".equals(csszMap.get("sfsdgwcjsx"))&&StringUtils.isNotNull(gwxxMap.get("gwcjsx"))
				&& (Double.parseDouble(ffje) + Double.parseDouble(gs) * Double.parseDouble(cjbz) > Double.parseDouble(gwxxMap.get("gwcjsx")))) {
			return MessageUtil.getText(MessageKey.QGZX_MRGZKH_GWJESXOUT, new String[] { gzrq.substring(0, 7), gwxxMap.get("gwmc"), gwxxMap.get("gwcjsx") });
		}
		if ("je".equals(csszMap.get("sxzd"))
				&& (Double.parseDouble(je) + Double.parseDouble(gs) * Double.parseDouble(cjbz) > Double.parseDouble(csszMap.get("sxsz")))) {
			return MessageUtil.getText(MessageKey.QGZX_MRGZKH_JEOUT, new String[] { xh,gzrq.substring(0, 7), csszMap.get("sxsz") });
		}
		return "true";
	}
	
	/**
	 * 
	 * @描述: 考核结果增加酬金工时上限判断(修改)
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-1-20 下午01:48:21
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param xn
	 * @param gs
	 * @param gzrq
	 * @param cjbz
	 * @param id
	 * @param gwdm
	 * @return
	 * @throws Exception
	 * String 返回类型 
	 * @throws
	 */
	public String checkZjeAndGsJGUp(String xh, String xn, String gs, String gzrq, String cjbz, String id, String gwdm) throws Exception {

		QgzxCsszDAO csszDao = new QgzxCsszDAO();
		GzkhKhsqDao gzkhKhsqDAO = new GzkhKhsqDao();
		HashMap<String, String> gwxxMap = dao.getGwxx(gwdm);
		HashMap<String, String> csszMap = csszDao.getCssz();
		HashMap<String, String> updateMap = dao.checkExistForUpdate(id);
		if (null == cjbz) {
			cjbz = csszMap.get("cjbz");
		}
		HashMap<String, String> xsCjxx = gzkhKhsqDAO.getCjffXxOfStu(xh, xn, gzrq);
		String cjgs = xsCjxx.get("gs") == null ? "0" : xsCjxx.get("gs");
		String je = xsCjxx.get("je") == null ? "0" : xsCjxx.get("je");
		String ffje = gzkhKhsqDAO.getCjByGwdm(xh, xn, gwdm, gzrq);
		if ("gs".equals(csszMap.get("sxzd")) && (Double.parseDouble(cjgs) + Double.parseDouble(gs) - Double.parseDouble(updateMap.get("gs")) > Double.parseDouble(csszMap.get("sxsz")))) {
			return MessageUtil.getText(MessageKey.QGZX_MRGZKH_GSOUT, new String[] { xh,gzrq.substring(0, 7), csszMap.get("sxsz") });
		}
		if ("yes".equals(csszMap.get("sfsdgwcjsx"))&&StringUtils.isNotNull(gwxxMap.get("gwcjsx"))
				&& (Double.parseDouble(ffje) + Double.parseDouble(gs) * Double.parseDouble(cjbz) - Double.parseDouble(updateMap.get("je")) > Double.parseDouble(gwxxMap.get("gwcjsx")))) {
			return MessageUtil.getText(MessageKey.QGZX_MRGZKH_GWJESXOUT, new String[] { gzrq.substring(0, 7), gwxxMap.get("gwmc"), gwxxMap.get("gwcjsx") });
		}
		if ("je".equals(csszMap.get("sxzd"))
				&& (Double.parseDouble(je) + Double.parseDouble(gs) * Double.parseDouble(cjbz) - Double.parseDouble(updateMap.get("je")) > Double.parseDouble(csszMap.get("sxsz")))) {
			return MessageUtil.getText(MessageKey.QGZX_MRGZKH_JEOUT, new String[] { xh,gzrq.substring(0, 7), csszMap.get("sxsz") });
		}
		return "true";
	}
}
