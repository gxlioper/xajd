package xgxt.comm.sjjc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;
import xgxt.comm.xml.XMLReader;

public class DataDetectService {

	DataDetectDAO dao = new DataDetectDAO();

	/**
	 * 根据学校代码、模块类型 获取异常数据检测基本信息
	 * 
	 * @param myForm
	 * @return
	 */
	public List<HashMap<String, Object>> getSjjcByXxMk(DataDetectForm myForm) {
		// 检测模块信息(根据jcmkdm,jcmkmc 排列组合)
		List<HashMap<String, String>> jcmkList = dao.getJcmkList(myForm);
		// 数据检测详细信息
		List<HashMap<String, String>> sjjcList = dao.getSjjcByXxMk(myForm);
		// 检测信息列表
		List<HashMap<String, Object>> jcxxList = new ArrayList<HashMap<String, Object>>();

		// 合并行记数
		int n = 0;
		// ======================构建页面显示信息=================================
		for (int i = 0; i < jcmkList.size(); i++) {
			HashMap<String, String> jcmkMap = jcmkList.get(i);
			HashMap<String, Object> jcxxMap = new HashMap<String, Object>();
			List<HashMap<String, String>> jclxList = new ArrayList<HashMap<String, String>>();
			for (int j = 0; j < sjjcList.size(); j++) {
				HashMap<String, String> sjjcMap = sjjcList.get(j);

				if (jcmkMap.get("jcmkdm").equalsIgnoreCase(
						sjjcMap.get("jcmkdm"))
						&& !"yes".equalsIgnoreCase(sjjcMap.get("sfpd"))) {

					jclxList.add(sjjcMap);
					n++;
					sjjcMap.put("sfpd", "yes");
				}
			}
			jcxxMap.put("length", n);
			jcxxMap.putAll(jcmkMap);
			jcxxMap.put("jclx", jclxList);
			jcxxList.add(jcxxMap);
			n = 0;
		}
		// ======================构建页面显示信息end=================================

		return jcxxList;
	}

	/**
	 * 数据检测(检测异常数据数量)
	 * 
	 * @param inputV(dwr获取)
	 * @return int
	 * @throws Exception
	 */
	public int dataDetect(String[] inputV) throws Exception {

		DataDetectForm myForm = new DataDetectForm();
		// 模块类型
		String mklx = inputV[0];
		// 检测模块
		String jcmk = inputV[1];
		// 异常类型
		String yclx = inputV[2];
		// 采用检测方法(如果是空则不采用检测方法检测)
		String methodValue = inputV[3];
		// 相关表名
		String tableName = inputV[4];
		// 字段
		String zd = inputV[5];
		// 条件
		String query = inputV[6];

		// ===============如果判断类型2为空，那么只检测判断类型1==================
		// 判断类型1
		String pdlxone = inputV[7];
		// 判断类型2
		String pdlxtwo = inputV[8];
		// 判断值1
		String pdzone = inputV[9];
		// 判断值2
		String pdztwo = inputV[10];
		// ===============如果判断类型2为空，那么只检测判断类型1 end==================
		myForm.setTableName(tableName);
		myForm.setZd(zd);
		myForm.setQuery(query);
		myForm.setPdlxone(pdlxone);
		myForm.setPdlxtwo(pdlxtwo);
		myForm.setPdzone(pdzone);
		myForm.setPdztwo(pdztwo);

		int count = 0;

		// 判断是否采用方法进行判断
		if (Base.isNull(methodValue)) {
			// 从XML配置文件中获取检测SQL
			String sql = XMLReader.getDataDetect(mklx, jcmk, yclx);
			myForm.setSql(sql);
			// 统计异常数据的数量
			count = dao.getCounts(myForm);
		} else {
			try {
				// 通过反射获取数据库中配置的检测方法进行检测
				DataDetectMethod.class.getMethod(methodValue,
						new Class[] { DataDetectForm.class }).invoke(null,
						new Object[] { (Object) myForm });
				// 统计异常数据的数量
				count = Integer.parseInt(myForm.getCount());
			} catch (Exception e) {
				// TODO 自动生成 catch 块
				e.printStackTrace();
			}
		}
		return count;

	}

	/**
	 * 获取异常数据类型代码获取 异常数据
	 * 
	 * @param myForm
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getJcsjList(DataDetectForm myForm) throws Exception {

		// 根据异常类型代码获取检测异常类型的相关设置
		HashMap<String, String> jcMap = dao.getJcxx(myForm);
		// 返回异常信息结果集
		List<String[]> rs = new ArrayList<String[]>();

		// =================获取异常类型检测设置========================

		// 检测方法
		String methodValue = jcMap.get("method");
		// 模块类型
		String mklx = jcMap.get("gnmklx");
		// 检测二级模块
		String jcmk = jcMap.get("jcmkdm");
		// 异常类型代码
		String yclx = jcMap.get("yclxdm");
		// 异常类型名称
		myForm.setYclxmc(jcMap.get("yclxmc"));
		// 表名
		myForm.setTableName(jcMap.get("tablename"));
		// 检测字段
		myForm.setZd(jcMap.get("zd"));
		// 检测条件
		myForm.setQuery(jcMap.get("query"));
		// 判断类型1
		myForm.setPdlxone(jcMap.get("pdlxone"));
		// 判断类型2
		myForm.setPdlxtwo(jcMap.get("pdlxtwo"));
		// 判断值1
		myForm.setPdzone(jcMap.get("pdzone"));
		// 判断值2
		myForm.setPdztwo(jcMap.get("pdztwo"));
		// 需显示异常信息字段
		myForm.setXszd(jcMap.get("xszd"));
		// 异常数据检测表主键
		myForm.setPk(jcMap.get("pk"));

		// ===============判断检测方法是否为空==========================
		if (Base.isNull(methodValue)) {
			String sql = XMLReader.getDataDetect(mklx, jcmk, yclx);
			myForm.setSql(sql);
			dao.getYcsjList(myForm);
			rs = myForm.getRs();
		} else {
			try {
				DataDetectMethod.class.getMethod(methodValue,
						new Class[] { DataDetectForm.class }).invoke(null,
						new Object[] { (Object) myForm });
				dao.getYcsjxxList(myForm);
				rs = myForm.getRs();

			} catch (Exception e) {
				// TODO 自动生成 catch 块
				e.printStackTrace();
			}
		}

		return rs;

	}

	/**
	 * 删除异常数据(根据传入主键和主键值删除)
	 * 
	 * @param myForm
	 * @return boolean
	 * @throws Exception
	 */
	public boolean delData(DataDetectForm myForm) throws Exception {
		return dao.delData(myForm);
	}
}
