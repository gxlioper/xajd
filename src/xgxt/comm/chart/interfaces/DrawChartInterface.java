package xgxt.comm.chart.interfaces;

import java.util.HashMap;
import java.util.List;

import org.jfree.chart.JFreeChart;




/**
 * <p>
 * 	当前接口定义了三种方法产生不同的jfreeChart
 * </p>
 * <p>Author: 屈朋辉</p>
 * <p>Version: 1.0</p>
 */
public interface DrawChartInterface {

	/**
	 * 3D柱状图（双key）
	 * @param values 数据
	 * @param rowKeys X轴key
	 * @param colKeys Y轴key
	 * @param title 标题
	 * @param rowLable 行名称
	 * @param colLable 列名称
	 * @return JFreeChart
	 */
	public abstract JFreeChart getCategoryChart(String[] values,
			String[] rowKeys, String[] colKeys, String title, String rowLable,
			String colLable);

	
	/**
	 * 柱状图数据源（单一KEY）
	 * @param data
	 * @param title
	 * @param lable
	 * @return JFreeChart
	 */
	public abstract JFreeChart getCategoryChart(List<HashMap<String, String>> data,
			String title, String lable);
	
	
	/**
	 * 3D饼状图
	 * 
	 * @param data
	 *            数据集
	 * @param title
	 *            标题
	 * @return JFreeChart
	 */
	public abstract JFreeChart getPieChart(List<HashMap<String, String>> data,
			String title);



}