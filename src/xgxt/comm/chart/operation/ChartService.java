package xgxt.comm.chart.operation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.util.MessageResources;
import org.jfree.chart.JFreeChart;

import xgxt.action.Base;
import xgxt.comm.chart.imp.DrawChartImp;
import xgxt.comm.chart.interfaces.DrawChartInterface;
import xgxt.utils.String.StringUtils;

public class ChartService {

	MessageResources message = MessageResources
					.getMessageResources("config.ApplicationResources");
			
	ChartDAO dao = new ChartDAO();

	DrawChartInterface dci = new DrawChartImp();
	protected Log logger = LogFactory.getLog(ChartService.class);

	/**
	 * 临时的，测试用
	 * 
	 * @return JFreeChart
	 */
	protected JFreeChart getStuChartByXb(String man, String woman) {
		Base.YXPZXY_KEY = message.getMessage("lable.xb");
		String title = Base.YXPZXY_KEY+"男女比例图";
		List<HashMap<String, String>> data = new ArrayList<HashMap<String, String>>();

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("key", "男");
		map.put("value", StringUtils.isNull(man) ? "0" : man);
		data.add(map);

		map = new HashMap<String, String>();
		map.put("key", "女");
		map.put("value", StringUtils.isNull(woman) ? "0" : woman);
		data.add(map);

		return dci.getPieChart(data, title);
	}

	/**
	 * 临时的，测试用
	 * 
	 * @return JFreeChart
	 */
	protected JFreeChart getStuChartByXymcAndXb() {
		String title = "男女比例图";
		String rowLable = "性别";
		String colLable = "学院";
		List<HashMap<String, String>> data = dao.getStudentByXyAndXb();

		String[] values = new String[data.size()];
		String[] rowKeys = new String[data.size()];
		String[] colKeys = new String[data.size()];

		for (int i = 0; i < data.size(); i++) {
			values[i] = data.get(i).get("count");
			rowKeys[i] = data.get(i).get("xb");
			colKeys[i] = data.get(i).get("xymc");
		}

		JFreeChart chart = dci.getCategoryChart(values, rowKeys, colKeys,
				title, colLable, rowLable);

		return chart;
	}

	/**
	 * 饼状图报表统计(学生信息通用)
	 * 
	 * @param model
	 * @return JFreeChart
	 * @throws Exception
	 */
	protected JFreeChart getChart(ChartForm model) throws Exception {

		String tjlb = model.getTjlb();
		String tjzd = model.getTjzd();
		String title = null;
		String lable = dao.getColumnNameCN(new String[] {tjzd}, "view_xsxxb")[0];

		HashMap<String, String> allowNullMap = getAllowNullMap();
		HashMap<String, String> notNullMap = getNotNullMap();

		List<HashMap<String, String>> data = null;

		//查询统计的数据和对应的标题
		if (StringUtils.isNotNull(tjzd)) {

			if (allowNullMap.containsKey(tjzd)) {
				title = allowNullMap.get(tjzd);
				data = dao.getTjDataAllowNull(model);
			} else if (notNullMap.containsKey(tjzd)) {
				title = notNullMap.get(tjzd);
				data = dao.getTjDataNotNull(model);
			}
			logger.info(title);
		} else {
			throw new Exception("您要统计的类别不存在");
		}

		
		//选择饼图或柱状图
		if ("category".equalsIgnoreCase(tjlb)) {
			return dci.getCategoryChart(data, title, lable);
		} else {
			return dci.getPieChart(data, title);
		}
		
	}

	/**
	 * 统计项值不能为空的类别
	 * 
	 * @return HashMap<String, String>
	 */
	private HashMap<String, String> getNotNullMap() {
		Base.YXPZXY_KEY = message.getMessage("lable.xb");
		HashMap<String, String> notNullMap = new HashMap<String, String>();

		notNullMap.put("nj",   "年级人数分布图");
		notNullMap.put("xymc", Base.YXPZXY_KEY+"人数分布图");
		notNullMap.put("zymc", "专业人数分布图");
		return notNullMap;
	}

	/**
	 * 统计项值可能为空的类别
	 * 
	 * @return HashMap<String, String>
	 */
	private HashMap<String, String> getAllowNullMap() {
		HashMap<String, String> allowNullMap = new HashMap<String, String>();

		allowNullMap.put("xb", "男女人数分布图");
		allowNullMap.put("zzmmmc", "政治面貌人数分布图");
		allowNullMap.put("xjztm", "学籍状态人数分布图");
		allowNullMap.put("xlmc", "学历人数分布图");
		allowNullMap.put("xwmc", "学位人数分布图");
		allowNullMap.put("mzmc", "民族人数分布图");
		return allowNullMap;
	}
}
