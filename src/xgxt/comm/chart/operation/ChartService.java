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
	 * ��ʱ�ģ�������
	 * 
	 * @return JFreeChart
	 */
	protected JFreeChart getStuChartByXb(String man, String woman) {
		Base.YXPZXY_KEY = message.getMessage("lable.xb");
		String title = Base.YXPZXY_KEY+"��Ů����ͼ";
		List<HashMap<String, String>> data = new ArrayList<HashMap<String, String>>();

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("key", "��");
		map.put("value", StringUtils.isNull(man) ? "0" : man);
		data.add(map);

		map = new HashMap<String, String>();
		map.put("key", "Ů");
		map.put("value", StringUtils.isNull(woman) ? "0" : woman);
		data.add(map);

		return dci.getPieChart(data, title);
	}

	/**
	 * ��ʱ�ģ�������
	 * 
	 * @return JFreeChart
	 */
	protected JFreeChart getStuChartByXymcAndXb() {
		String title = "��Ů����ͼ";
		String rowLable = "�Ա�";
		String colLable = "ѧԺ";
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
	 * ��״ͼ����ͳ��(ѧ����Ϣͨ��)
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

		//��ѯͳ�Ƶ����ݺͶ�Ӧ�ı���
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
			throw new Exception("��Ҫͳ�Ƶ���𲻴���");
		}

		
		//ѡ���ͼ����״ͼ
		if ("category".equalsIgnoreCase(tjlb)) {
			return dci.getCategoryChart(data, title, lable);
		} else {
			return dci.getPieChart(data, title);
		}
		
	}

	/**
	 * ͳ����ֵ����Ϊ�յ����
	 * 
	 * @return HashMap<String, String>
	 */
	private HashMap<String, String> getNotNullMap() {
		Base.YXPZXY_KEY = message.getMessage("lable.xb");
		HashMap<String, String> notNullMap = new HashMap<String, String>();

		notNullMap.put("nj",   "�꼶�����ֲ�ͼ");
		notNullMap.put("xymc", Base.YXPZXY_KEY+"�����ֲ�ͼ");
		notNullMap.put("zymc", "רҵ�����ֲ�ͼ");
		return notNullMap;
	}

	/**
	 * ͳ����ֵ����Ϊ�յ����
	 * 
	 * @return HashMap<String, String>
	 */
	private HashMap<String, String> getAllowNullMap() {
		HashMap<String, String> allowNullMap = new HashMap<String, String>();

		allowNullMap.put("xb", "��Ů�����ֲ�ͼ");
		allowNullMap.put("zzmmmc", "������ò�����ֲ�ͼ");
		allowNullMap.put("xjztm", "ѧ��״̬�����ֲ�ͼ");
		allowNullMap.put("xlmc", "ѧ�������ֲ�ͼ");
		allowNullMap.put("xwmc", "ѧλ�����ֲ�ͼ");
		allowNullMap.put("mzmc", "���������ֲ�ͼ");
		return allowNullMap;
	}
}
