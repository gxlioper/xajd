
package xgxt.pjpy.ynys;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import common.Globals;

import xgxt.daoActionLogic.StandardOperation;
import xgxt.utils.String.StringUtils;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 云南艺术评奖评优先进班级SERVICE
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 李涛</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-10-20</p>
 */
public class XjbjService extends PjpyYnysService {

	XjbjDAO dao = null;//先进班级DAO
	
	/**
	 * 保存先进班级信息
	 * 
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean saveYnys_xjbjb(XjbjModel model, HttpServletRequest request)
			throws Exception {
		dao = new XjbjDAO();
		return dao.saveYnys_xjbjb(model, request);
	}
	
	/**
	 * 先进班级打印
	 * @param xn
	 * @param bjdm
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> printXjbj(String pkValue) throws Exception {
		dao = new XjbjDAO();
		return dao.printXjbj(pkValue);
		/*HashMap<String, String> rsMap = dao.printXjbj(bjdm); //获取打印数据
		WritableSheet ws = wwb.getSheet(0);//写入到第一个sheet
		String tbrq = rsMap.get("tbrq");
		if (!StringUtils.isNull(tbrq) && tbrq.length() == 8) {
			tbrq = "填表日期:  " + tbrq.substring(0, 4) + "年"
					+ tbrq.subSequence(4, 6) + "月" + tbrq.subSequence(6, 8)
					+ "日";
		}
		//WritableCellFormat tStyle = new WritableCellFormat();
 	    WritableCellFormat wcfStyle = new WritableCellFormat();
 	    WritableCellFormat tStyle = new WritableCellFormat();
	    Alignment alignMent = Alignment.CENTRE;
	    wcfStyle.setAlignment(alignMent);//设置输出数据居中
	   // tStyle.setAlignment(Alignment.LEFT);
	    wcfStyle.setBorder(Border.ALL, BorderLineStyle.THIN);//设置边框及边框线条
	    WritableFont wf = new WritableFont(WritableFont.ARIAL);//设置字体大
		wf.setPointSize(13);
		wcfStyle.setFont(wf);
		tStyle.setFont(wf);
		tStyle.setAlignment(alignMent);
	    //tStyle.setBorder(Border.ALL, BorderLineStyle.THIN);
	    if (!StringUtils.isNull(bjdm) && !StringUtils.isNull(rsMap.get("bjmc")) ) {
	    	ws.addCell(new Label(5, 2, tbrq, tStyle));//输出日期
		    ws.addCell(new Label(1, 3, rsMap.get("xymc"), wcfStyle));
		    ws.addCell(new Label(4, 3, rsMap.get("nj"), wcfStyle));
		    ws.addCell(new Label(7, 3, rsMap.get("bjmc"), wcfStyle));
		    ws.addCell(new Label(1, 4, rsMap.get("zymc"), wcfStyle));
		    ws.addCell(new Label(5, 4, rsMap.get("bjrs"), wcfStyle));
		    ws.addCell(new Label(7, 4, rsMap.get("nj"), wcfStyle));
		    ws.addCell(new Label(1, 5, rsMap.get("bjdbqk"), wcfStyle));
		    ws.addCell(new Label(1, 6, rsMap.get("bzryj"), wcfStyle));
	    }
	    ExcelMethods.submitWritableWorkbookOperations(wwb);//输出到客户端
*/	}
	
	/**
	 * 审核项目列表
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getShxmList() throws Exception {
		dao = new XjbjDAO();
		return dao.getShxmList();
	}
	
	/**
	 * 先进班级表头
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getXjbjTitle(String userType, String realTable) throws Exception {
		dao = new XjbjDAO();
		String[] enList = null;
		String[] cnList = null;
		String xxdm = StandardOperation.getXxdm();
		if (StringUtils.isEqual("xy", userType)) {//学院
			if (StringUtils.isEqual("ynys_xjbjb", realTable)) {//先进班级
				enList = new String[] {"pk", "rownum", "xn", "nj", "xymc", "zymc",
						"bjmc", "bjch", "bzr", "yxsh" };
				cnList = new String[] {"pk", "行号", "学年", "年级", "学院名称", "专业名称",
						"班级名称", "班级荣誉", "班主任", "院系审核" };
				if (Globals.XXDM_YCWSZYJSXY.equalsIgnoreCase(xxdm)) {
					enList = new String[] {"pk", "rownum", "xn", "nj", "xymc", "zymc",
							"bjmc", "bjch", "bzr","jtch", "yxsh" };
					cnList = new String[] {"pk", "行号", "学年", "年级", "学院名称", "专业名称",
							"班级名称", "班级荣誉","集体称号", "班主任", "院系审核" };
				}
			} else if (StringUtils.isEqual("ynys_yxbysb", realTable)) {//优秀毕业生
				enList = new String[] {"pk", "rownum", "xh", "xm", "xn", "nj", "xymc", "zymc",
						"bjmc", "grch", "yxsh" };
				cnList = new String[] {"pk", "行号","学号", "姓名", "学年", "年级", "学院名称", "专业名称",
						"班级名称", "个人荣誉", "院系审核" };
			}
		} else {//学校
			if (StringUtils.isEqual("ynys_xjbjb", realTable)) {//先进班级
				enList = new String[] {"pk", "rownum", "xn", "nj", "xymc", "zymc",
						"bjmc", "bjch", "bzr", "xxsh" };
				cnList = new String[] {"pk", "行号", "学年", "年级", "学院名称", "专业名称",
						"班级名称", "班级荣誉", "班主任", "学校审核" };
				if (Globals.XXDM_YCWSZYJSXY.equalsIgnoreCase(xxdm)) {
					enList = new String[] {"pk", "rownum", "xn", "nj", "xymc", "zymc",
							"bjmc", "bjch", "bzr", "","xxsh" };
					cnList = new String[] {"pk", "行号", "学年", "年级", "学院名称", "专业名称",
							"班级名称", "班级荣誉","集体称号", "班主任", "学校审核" };
				}
			} else if (StringUtils.isEqual("ynys_yxbysb", realTable)) {//优秀毕业生
				enList = new String[] {"pk", "rownum", "xh", "xm", "xn", "nj", "xymc", "zymc",
						"bjmc", "grch", "xxsh" };
				cnList = new String[] {"pk", "行号","学号", "姓名",  "学年", "年级", "学院名称", "专业名称",
						"班级名称", "个人荣誉", "学校审核" };
			}
			
		}
		return dao.getQryTitle(enList, cnList);
	}
	
	/**
	 * 先进班级查询结果
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getXjbjList(XjbjModel model, String userType, String realTable)
			throws Exception {
		dao = new XjbjDAO();
		if (StringUtils.isEqual("xy", userType)) {//学院
			if (StringUtils.isEqual("ynys_xjbjb", realTable)) {//先进班级
				return dao.getXjbjListByyx(model);
			} else if (StringUtils.isEqual("ynys_yxbysb", realTable)) {//优秀毕业生
				return dao.getYxbysByxy(model);
			}
			return null;
		} else {//学校
			if (StringUtils.isEqual("ynys_xjbjb", realTable)) {//先进班级
				return dao.getXjbjListByxx(model);
			} else if (StringUtils.isEqual("ynys_yxbysb", realTable)) {//优秀毕业生
				return dao.getYxbysByxx(model);
			}
			return null;
		}
	}
	
	/**
	 * 先进班级审核结果
	 * @param keys
	 * @param userType
	 * @param sJg
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String xjbjShResult(String[] keys, String userType, String sJg,
			HttpServletRequest request, String realTable) throws Exception {
		dao = new XjbjDAO();
		if (StringUtils.isEqual("xy", userType)) {//院系审核
			if (StringUtils.isEqual("ynys_xjbjb", realTable)) {
				return dao.yxshXjbjResult(keys, sJg, request);
			} else if (StringUtils.isEqual("ynys_yxbysb", realTable)) {
				return dao.yxshYxbysResult(keys, sJg, request);
			}
			return "无审核项目!";
		} else if (StringUtils.isEqual("xx", userType)
				|| StringUtils.isEqual("admin", userType)) {// 学校审核
			if (StringUtils.isEqual("ynys_xjbjb", realTable)) {
				return dao.xxshXjbjResult(keys, sJg, request);
			} else if (StringUtils.isEqual("ynys_yxbysb", realTable)) {
				return dao.xxshYxbysResult(keys, sJg, request);
			}
			return "无审核项目!";
		} else {//其它用户直接返回
			return "";
		}
	}
	
	/**
	 * 显示先进班级单个审核信息
	 * @param pkValue
	 * @param userType
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> viewXjbjshOne(String pkValue, String userType, String realTable)
			throws Exception {
		dao = new XjbjDAO();
		if (!StringUtils.isNull(realTable)) {
			if (StringUtils.isEqual("ynys_xjbjb", realTable)) {
				return dao.viewXjbjshOne(pkValue, userType);
			} else if (StringUtils.isEqual("ynys_yxbysb", realTable)) {
				return dao.viewYxbysOne(pkValue, userType);
			}
		}
		return null;
	}
	
	/**
	 * 审核列表
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getShList() throws Exception {
		dao = new XjbjDAO();
		return dao.getShList();
	}
	
	/**
	 * 学校单个审核先进班级结果
	 * @param pkValue
	 * @param yesNo
	 * @param yj
	 * @param xyyj
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean saveXjbjshone(String pkValue, String userType, String yesNo,
			String yj, String xyyj, HttpServletRequest request)
			throws Exception {
		dao = new XjbjDAO();
		if (StringUtils.isEqual("xy", userType)) {//学院审核
			return dao.saveXjbjshone(pkValue, yesNo, yj, request);
		} else {//其它审核
			return dao.saveXjbjshone(pkValue, yesNo, yj, xyyj, request);
		}
	}
	
	/**
	 * 优秀毕业生单个审核结果
	 * @param pkValue
	 * @param userType
	 * @param yesNo
	 * @param yj
	 * @param xyyj
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean saveYxbysshone(String pkValue, String userType,
			String yesNo, String yj, String jytyj, HttpServletRequest request)
			throws Exception {
		dao = new XjbjDAO();
		if (StringUtils.isEqual("xy", userType)) {// 学院审核
			return dao.saveYxbysshone(pkValue, yesNo, yj, request);
		} else {// 其它审核
			return dao.saveYxbysshone(pkValue, yesNo, yj, jytyj, request);
		}
	}
	
	/**
	 * 先进班级查询表头
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getXjbjQryTitle(String xxdm) throws Exception {
		dao = new XjbjDAO();
		String[] enList = new String[] { "pk", "rownum", "xn", "nj", "xymc",
				"zymc", "bjmc", "bjch", "bzr", "yxsh", "xxsh" };
		String[] cnList = new String[] { "pk", "行号", "学年", "年级", "学院名称",
				"专业名称", "班级名称", "班级荣誉", "班主任", "院系审核", "学生处审核" };
		if (Globals.XXDM_NBZYJSXY.equalsIgnoreCase(xxdm)) {
			cnList = new String[] { "pk", "行号", "学年", "年级", "学院名称",
					"专业名称", "班级名称", "班级荣誉", "班主任", "院团委审核", "学生处审核" };
		} 
		if (Globals.XXDM_YCWSZYJSXY.equalsIgnoreCase(xxdm)) {
			enList = new String[] { "pk", "rownum", "xn", "nj", "xymc",
					"zymc", "bjmc", "bjch", "bzr","", "yxsh", "xxsh" };
			cnList = new String[] { "pk", "行号", "学年", "年级", "学院名称",
					"专业名称", "班级名称", "班级荣誉","集体称号", "班主任", "院系审核", "学生处审核" };
		}
		return dao.getQryTitle(enList, cnList);
	}
	
	/**
	 * 先进班级查询结果
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getXjbjQryResult(XjbjModel model, String xxdm) throws Exception {
		dao = new XjbjDAO();
		if (Globals.XXDM_NBZYJSXY.equalsIgnoreCase(xxdm)) {
			return dao.nbzyXjbjQryResult1(model);
		} else if (Globals.XXDM_YNYS.equalsIgnoreCase(xxdm)) {
			return dao.getXjbjQryResult(model); 
		} else {
			return dao.nbzyXjbjQryResult(model);
		}
		
	}
	
	/**
	 * 显示先进班修改信息
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> viewXjbjxx(String pkValue) throws Exception {
		dao = new XjbjDAO();
		return dao.viewXjbjxx(pkValue);
	}
	
	public HashMap<String, String> nbzyXjbjxx(String pkValue) throws Exception {
		dao = new XjbjDAO();
		return dao.nbzyXjbjxx(pkValue);
	}
	
	public HashMap<String, String> zjjdXjbjxx(String pkValue) throws Exception {
		dao = new XjbjDAO();
		return dao.zjjdXjbjxx(pkValue);
	}
	
	/**
	 * 保存修改信息
	 * @param pkValue
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean saveXjbjxx(String pkValue, XjbjModel model,
			HttpServletRequest request) throws Exception {
		dao = new XjbjDAO();
		return dao.saveXjbjxx(pkValue, model, request);
	}
	
	/**
	 * 删除先进班级信息
	 * @param keys
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String delXjbjxx(String[] keys, HttpServletRequest request) throws Exception {
		dao = new XjbjDAO();
		return dao.delXjbjxx(keys, request);
	}
}
