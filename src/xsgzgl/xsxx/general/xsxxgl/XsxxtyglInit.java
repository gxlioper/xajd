package xsgzgl.xsxx.general.xsxxgl;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

/**
 * 学生信息 - 通用管理init
 * lt
 * 2013-1-7
 */
public class XsxxtyglInit {
	
	/**
	 * 学生信息查询
	 * 
	 * @param request
	 * @author lt
	 * 
	 */
	public void initZxsSearch(RequestForm rForm, User user,
			HttpServletRequest request) {

		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 访问路径
		String path = "xsxx_tygl_cxzxs.do";
		// 其他字段
		String[] qtzd = new String[] {};
		// 其他字段值
		String[] qtzdz = new String[] {};

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
		rForm.setPath(path);
	}
	
	/**
	 * 保存在校生信息
	 * @param myForm
	 * @param request
	 * @return
	 */
	public String bcZxsxx(XsxxtyglForm myForm, HttpServletRequest request, User user,String sfxgFlag) throws Exception{
		XsxxtyglService service = new XsxxtyglService();
		
		HashMap<String, String> valueMap = service.getValueMap(request, service.getColumnByTable("xsxxb"));
		HashMap<String, String> xsxxMap = service.getZxsxxByXhCk(myForm);
		String res="";
		String jgshen = request.getParameter("jgshen");
		String jgshi = request.getParameter("jgshi");
		String jgxian = request.getParameter("jgxian");	
		String hkshen = request.getParameter("hkshen");
		String hkshi = request.getParameter("hkshi");
		String hkxian = request.getParameter("hkxian");
		String syds   = request.getParameter("syds");
		String sydshi   = request.getParameter("sydshi");
		String sydx   = request.getParameter("sydx");
		if(null==myForm.getSfzx() && null==valueMap.get("sfzx")){
			myForm.setSfzx(xsxxMap.get("sfzx"));
			valueMap.put("sfzx", xsxxMap.get("sfzx"));
		}
		//性别的处理
		valueMap.put("xb", valueMap.get("xb").equals("男")?"1":"2");
		//籍贯的处理
		if (StringUtils.isNotNull(jgshen) && StringUtils.isNotNull(jgshi) && StringUtils.isNotNull(jgxian) ) {
			valueMap.put("jg", jgxian);
		} else if (StringUtils.isNotNull(jgshen) && StringUtils.isNotNull(jgshi)) {
			valueMap.put("jg", jgshi);
			valueMap.put("jgx", "");
		} else if (StringUtils.isNotNull(jgxian)) {
			valueMap.put("jg", jgxian);
		}else if (StringUtils.isNotNull(jgshen)) {
			valueMap.put("jg", jgshen);
			valueMap.put("jgshi", "");
			valueMap.put("jgx", "");
		} else {
			valueMap.put("jg", "");
			valueMap.put("jgs", "");
			valueMap.put("jgshi", "");
			valueMap.put("jgx", "");
		}
		//户口所在地的处理
		if (StringUtils.isNotNull(hkshen) && StringUtils.isNotNull(hkshi) && StringUtils.isNotNull(hkxian) ) {
			valueMap.put("hkszd", hkxian);
		} else if (StringUtils.isNotNull(hkshen) && StringUtils.isNotNull(hkshi)) {
			valueMap.put("hkszd", hkshi);
			valueMap.put("hkxian", "");
		} else if (StringUtils.isNotNull(hkxian)) {
			valueMap.put("hkszd", hkxian);
		}else if (StringUtils.isNotNull(hkshen)) {
			valueMap.put("hkszd", hkshen);
			valueMap.put("hkshi", "");
			valueMap.put("hkxian", "");
		} else {
			valueMap.put("hkszd", "");
			valueMap.put("hkshen", "");
			valueMap.put("hkshi", "");
			valueMap.put("hkxian", "");
		}
		//生源地的处理
		if (StringUtils.isNotNull(syds) && StringUtils.isNotNull(sydshi) && StringUtils.isNotNull(sydx) ) {
			valueMap.put("syd", sydx);
		} else if (StringUtils.isNotNull(syds) && StringUtils.isNotNull(sydshi)) {
			valueMap.put("syd", sydshi);
			valueMap.put("sydx", "");
		} else if (StringUtils.isNotNull(sydx)) {
			valueMap.put("syd", sydx);
		}else if (StringUtils.isNotNull(syds)) {
			valueMap.put("syd", syds);
			valueMap.put("sydshi", "");
			valueMap.put("sydx", "");
		} else {
			valueMap.put("syd", "");
			valueMap.put("syds", "");
			valueMap.put("sydshi", "");
			valueMap.put("sydx", "");
		}
		
		boolean flag = true;
		if (xsxxMap != null && !StringUtils.isNull(xsxxMap.get("xh"))) {//修改
			myForm.setValueMap(valueMap);
			
			//学生用户的信息保存
			if ("stu".equalsIgnoreCase(user.getUserType())) {
			//	if("true".equals(sfxgFlag)){
					HashMap<String, String> csszMap = service.getCsszjg();
					//有审核流的保存处理
					if (StringUtils.isNotNull(csszMap.get("shlid")) && !"wxsh".equalsIgnoreCase(csszMap.get("shlid"))) {
						valueMap = service.getValueMap(request, service.getColumnByTable("xg_xsxx_xxxgzdxgb"));
						
						//籍贯的处理
						if (StringUtils.isNotNull(jgshen) && StringUtils.isNotNull(jgshi) && StringUtils.isNotNull(jgxian) ) {
							valueMap.put("jg", jgxian);
						} else if (StringUtils.isNotNull(jgshen) && StringUtils.isNotNull(jgshi)) {
							valueMap.put("jg", jgshi);
							valueMap.put("jgx", "");
						} else if (StringUtils.isNotNull(jgxian)) {
							valueMap.put("jg", jgxian);
						}else if (StringUtils.isNotNull(jgshen)) {
							valueMap.put("jg", jgshen);
							valueMap.put("jgshi", "");
							valueMap.put("jgx", "");
						} else {
							valueMap.put("jg", "");
							valueMap.put("jgs", "");
							valueMap.put("jgshi", "");
							valueMap.put("jgx", "");
						}
						//户口所在地的处理
						if (StringUtils.isNotNull(hkshen) && StringUtils.isNotNull(hkshi) && StringUtils.isNotNull(hkxian) ) {
							valueMap.put("hkszd", hkxian);
						} else if (StringUtils.isNotNull(hkshen) && StringUtils.isNotNull(hkshi)) {
							valueMap.put("hkszd", hkshi);
							valueMap.put("hkxian", "");
						} else if (StringUtils.isNotNull(hkxian)) {
							valueMap.put("hkszd", hkxian);
						}else if (StringUtils.isNotNull(hkshen)) {
							valueMap.put("hkszd", hkshen);
							valueMap.put("hkshi", "");
							valueMap.put("hkxian", "");
						} else {
							valueMap.put("hkszd", "");
							valueMap.put("hkshen", "");
							valueMap.put("hkshi", "");
							valueMap.put("hkxian", "");
						}
						//生源地的处理
						if (StringUtils.isNotNull(syds) && StringUtils.isNotNull(sydshi) && StringUtils.isNotNull(sydx) ) {
							valueMap.put("syd", sydx);
						} else if (StringUtils.isNotNull(syds) && StringUtils.isNotNull(sydshi)) {
							valueMap.put("syd", sydshi);
							valueMap.put("sydx", "");
						} else if (StringUtils.isNotNull(sydx)) {
							valueMap.put("syd", sydx);
						}else if (StringUtils.isNotNull(syds)) {
							valueMap.put("syd", syds);
							valueMap.put("sydshi", "");
							valueMap.put("sydx", "");
						} else {
							valueMap.put("syd", "");
							valueMap.put("syds", "");
							valueMap.put("sydshi", "");
							valueMap.put("sydx", "");
						}
						
						HashMap<String, String> sqxxMap = service.getStuSqztMap(myForm.getXh()); 
						//如果有申请过，但没审核通过而且不处于审核状态中，则要进行修改
						if (StringUtils.isNotNull(sqxxMap.get("sqid"))) {
							flag = service.updateStusqxx(sqxxMap.get("sqid"), valueMap, xsxxMap,sqxxMap,sfxgFlag);
							if(flag){
								res=sqxxMap.get("sqid");
							}
						} else if("true".equals(sfxgFlag)){//要进行增加的处理
							 res = service.insertStusqxx(valueMap, xsxxMap, csszMap.get("shlid"),user.getUserType());
							if(!"".equals(res)){
								flag=true;
							}
						}else if("false".equals(sfxgFlag)){//要进行增加的处理
							 res = user.getUserName();
								
							}
					} else if("true".equals(sfxgFlag)){//无需审核的直接修改正式表
						flag = service.updateInfo(myForm, "xsxxb");
						//修改成功后更新xsfzxxb
						if (flag) {
							valueMap = service.getValueMap(request, service.getColumnByTable("xsfzxxb"));
							myForm.setValueMap(valueMap);
							//VALUEMAP中默认字段是学号，所以必须修改过除学号以外的字段才能更新xsfzxxb
							if (valueMap != null && valueMap.size() > 1) {
								flag = service.updateInfo(myForm, "xsfzxxb");
							}
						}
						res = user.getUserName();
					}
					else if("false".equals(sfxgFlag)){//无需审核的直接修改正式表
						res = user.getUserName();
					}
			//	}
			} else {//老师用户的修改保存
				flag = service.updateInfo(myForm, "xsxxb");
				//修改成功后更新xsfzxxb
				if (flag) {
					valueMap = service.getValueMap(request, service.getColumnByTable("xsfzxxb"));
					myForm.setValueMap(valueMap);
					//VALUEMAP中默认字段是学号，所以必须修改过除学号以外的字段才能更新xsfzxxb
					if (valueMap != null && valueMap.size() > 1) {
						flag = service.updateInfo(myForm, "xsfzxxb");
					}
					res=myForm.getValueMap().get("xh");
				}
			}
		} else {//新增加
			valueMap.put("sfzx", "在校");
			flag = service.saveInfo(valueMap);
			//增加成功后往xsmmb,xsfzxxb,xszpb表中增加数据
			if (flag) {
				flag = service.saveXsqtxx(myForm, user);
			}
			res=myForm.getXh();
		}
		return res;
	}
	
	/**
	 * 保存学生申请修改信息
	 * @param valueMap
	 * @param xsxxMap
	 * @return
	 */
	public boolean bcXssqxgxx(HashMap<String, String> valueMap, HashMap<String, String> xsxxMap) {
		boolean result = false;
		
		return result;
		
	}
	
	/**
	 * 学生信息查询
	 * 
	 * @param request
	 * @author lt
	 * 
	 */
	public void initFzxsSearch(RequestForm rForm, User user,
			HttpServletRequest request) {

		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 访问路径
		String path = "xsxx_tygl_cxfzxs.do";
		// 其他字段
		String[] qtzd = new String[] {};
		// 其他字段值
		String[] qtzdz = new String[] {};

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
		rForm.setPath(path);
	}
}
