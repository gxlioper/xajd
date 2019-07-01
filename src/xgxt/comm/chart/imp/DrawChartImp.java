package xgxt.comm.chart.imp;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import xgxt.comm.chart.interfaces.DrawChartInterface;

/**
 * <p>
 * 	当前类实现了  AbstractDrawChart
 *  提供返回 三种 形式JFreeChart(饼图、单一key柱状图、双key柱状图)
 * </p>
 * <p>
 *	具体数据处理和效果设置详见父类
 * </p>
 * <p>Author: 屈朋辉</p>
 * <p>Version: 1.0</p>
 * @see AbstractDrawChart
 * @see DrawChartInterface
 */
public class DrawChartImp extends AbstractDrawChart implements DrawChartInterface {

	protected Log logger = LogFactory.getLog(DrawChartImp.class);
	
	/*
	 * （非 Javadoc）
	 * 
	 * @see xgxt.comm.chart.interfaces.DrawChartInterface#getCategoryChart(java.lang.String[],
	 *      java.lang.String[], java.lang.String[], java.lang.String,
	 *      java.lang.String, java.lang.String)
	 */
	public JFreeChart getCategoryChart(String[] values, String[] rowKeys,
			String[] colKeys, String title, String rowLable, String colLable) {

		DefaultCategoryDataset dataset = null;
		try {
			if (null != rowKeys) {
				dataset = getCategoryDataset(values, rowKeys, colKeys);
			} else {
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		JFreeChart chart = ChartFactory.createBarChart3D(title, rowLable,
				colLable, dataset, PlotOrientation.VERTICAL, true, true, false);

		setCategoryPlot(chart);
		return chart;
	}

	/*
	 * （非 Javadoc）
	 * 
	 * @see xgxt.comm.chart.interfaces.DrawChartInterface#getCategoryChart(java.util.List,
	 *      java.lang.String，java.lang.String)
	 */
	public JFreeChart getCategoryChart(List<HashMap<String, String>> data,
			String title, String lable) {

		DefaultCategoryDataset dataset = null;
		try {
			dataset = getCategoryDataset(data);

		} catch (Exception e) {
			e.printStackTrace();
		}

		JFreeChart chart = ChartFactory.createBarChart3D(title, lable, "",
				dataset, PlotOrientation.VERTICAL, true, true, false);

		setCategoryPlot(chart);

		return chart;
	}

	/*
	 * （非 Javadoc）
	 * 
	 * @see xgxt.comm.chart.interfaces.DrawChartInterface#getPieChart(java.util.List,
	 *      java.lang.String)
	 */
	public JFreeChart getPieChart(List<HashMap<String, String>> data,
			String title) {
		
		logger.info("---getPieChart Start---");
		
		DefaultPieDataset dataset = getPieDataset(data);

		JFreeChart chart = ChartFactory.createPieChart3D(title, dataset, true,
				false, false);

		setPiePlot(chart);
		
		logger.info("---getPieChart End---");
		
		return chart;
	}

	
}
