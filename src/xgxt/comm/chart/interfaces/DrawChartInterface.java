package xgxt.comm.chart.interfaces;

import java.util.HashMap;
import java.util.List;

import org.jfree.chart.JFreeChart;




/**
 * <p>
 * 	��ǰ�ӿڶ��������ַ���������ͬ��jfreeChart
 * </p>
 * <p>Author: �����</p>
 * <p>Version: 1.0</p>
 */
public interface DrawChartInterface {

	/**
	 * 3D��״ͼ��˫key��
	 * @param values ����
	 * @param rowKeys X��key
	 * @param colKeys Y��key
	 * @param title ����
	 * @param rowLable ������
	 * @param colLable ������
	 * @return JFreeChart
	 */
	public abstract JFreeChart getCategoryChart(String[] values,
			String[] rowKeys, String[] colKeys, String title, String rowLable,
			String colLable);

	
	/**
	 * ��״ͼ����Դ����һKEY��
	 * @param data
	 * @param title
	 * @param lable
	 * @return JFreeChart
	 */
	public abstract JFreeChart getCategoryChart(List<HashMap<String, String>> data,
			String title, String lable);
	
	
	/**
	 * 3D��״ͼ
	 * 
	 * @param data
	 *            ���ݼ�
	 * @param title
	 *            ����
	 * @return JFreeChart
	 */
	public abstract JFreeChart getPieChart(List<HashMap<String, String>> data,
			String title);



}