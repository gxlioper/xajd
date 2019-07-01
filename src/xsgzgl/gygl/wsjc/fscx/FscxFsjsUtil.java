package xsgzgl.gygl.wsjc.fscx;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称: 公寓管理-分数查询
 * @类功能描述: 分数计算工具类
 * @作者： 易江东[781]
 * @时间： 2013-10-21 上午11:01:50 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class FscxFsjsUtil {
	
	/**
	1、	A级寝室评定：连续三周85分及以上
	2、	A级寝室撤销：累计三周85分及以下
	3、	C级寝室评定：累计两周低于60分以下
	4、	C级寝室撤销：连续两周高于60分及以上
	5、	除A级C级外其余均为B级寝室
	6、	输入0分后，等级即变C
	7、	输入1至59分中任意分数，等级变为B，累计两次变C
	8、	学生社区检查撤销结果，于周五之前反馈给生活指导老师。检查中撤销A级的寝室，寝室当周的卫生分数改为59分，由生活指导老师修改并录入到系统。


	
	/****************************可通过set*方法配置数据*************************/
	private int N = 3;// A级评分计数
	private int M = 2;// B级评分计数
	private int L = 2;// C级评分计数

	private int num1 = 85;//评定分数num1
	private int num2 = 60;//评定分数num2
	
	private int firstVerdictNum = 2;// 首次等级判定数  第几周
	
	private int neglectNum = 0;//忽略计算个数
	/*****************************************************************/
	
	/****************************不可设置属性*************************/
	private int markerA = 0;// A级评分标记
	private int markerB = 0;// B级评分标记
	private int markerC = 0;// C级评分标记
	
	private int repealA_B = 0;//撤销A 评B
	private int repealC_B = 0;//撤销C 评B
	
	//private int markerAClueNum = 0;// 评级A降级提醒次数

	private int markerANum = 0;// 评级A次数
	
	private String currLevel = "";// 当前级别
	private List<String[]> dataList = new ArrayList<String[]>();// 数据源
	/*****************************************************************/
	
	/**
	 * 评分
	 * 
	 * @return List<String[]>
	 */
	public List<String[]> grade() {
		// 评分结果列表
		List<String[]> gradeResultList = new ArrayList<String[]>();
		// 评分结果
		String[] gradeResult = null;
		// 评分数值
		int gradeValue = 0;
		// 开始判定数据
		for (int i = 0; i < dataList.size(); i++) {
			// 记录评分结果
			gradeResult = new String[dataList.get(i).length];
			for (int j = 0; j < dataList.get(i).length; j++) {
				//忽略不判定
				if(j < neglectNum){
					//保留忽略数据
					gradeResult[j] = String.valueOf(dataList.get(i)[j]);
					continue;
				}
				gradeValue = Integer.valueOf(dataList.get(i)[j]);
				
				// 开始评分
				verdict(gradeValue);

				// 首次判定记录等级
				if (j >= firstVerdictNum) {
					//System.out.println("aaa");
					// 记录评分结果
					gradeResult[j] = currLevel;
				}else{
					//清空当前等级
					currLevel="";
					// 首次判定以前给予空数据
					gradeResult[j] = "";
				}
			}

			// 加入结果集
			gradeResultList.add(gradeResult);

			// 清空标记
			cleanMarker();
		}
		return gradeResultList;
	}

	/**
	 * 评定数据
	 * 
	 * @param gradeValue
	 */
	private void verdict(int gradeValue) {
		//System.out.println("markerA:"+markerA+" markerB:"+markerB+" markerC:"+markerC+" repealA_B:"+repealA_B+" repealC_B:"+repealC_B+" currLevel:"+currLevel);
		// 开始评分
		if (verdictA(gradeValue)) {
			// 验证结果标记A
			currLevel = "A";
		} else if (verdictC(gradeValue)) {
			// 验证结果标记C
			currLevel = "C";
		} else if (verdictB(gradeValue)) {
			// 验证结果标记B
			currLevel = "B";
		} else {
			
		}
		
	}

	/**
	 * 清空标记
	 */
	private void cleanMarker() {
		//清空标记
		markerA = 0;
		markerB = 0;
		markerC = 0;
		repealA_B = 0;
		repealC_B = 0;
		// 清空等级
		currLevel = "";
		// 清空评A次数
		markerANum=0;
	}

	/**
	 * 判定A级
	 * 
	 * @param num
	 * @return
	 */
	private boolean verdictA(int num) {
		// 判断A级分数条件
		if (num >= num1) {
			// A级计数加1
			markerA++;
		}else{
			// 清空markerA计数器
			markerA = 0;
		}

		// 判定计数器是否起效
		if (markerA == N) {
			//清空评C的标记
			markerC = 0;
			//撤销A  和  撤销C 记录清空
			repealA_B = 0;
			repealC_B = 0;
			return true;
		}

		return false;
	}

	/**
	 * 判定B级
	 * 
	 * @param num
	 * @return
	 */
	private boolean verdictB(int num) {
		if("A".equals(currLevel)){
			//当前等级是A，撤销A
			if(num2 <= num && num < num1){
				//撤销A ,A --> B 记录一次
				repealA_B++;
			}else if(0 < num && num < num2){
				//撤销A ,A --> B  记录取消    直接撤销A返回B
				return true;
			}
			//满足撤销A --> B 时
			if(repealA_B == 3){
				//清空撤销A的次数
				repealA_B=0;
				return true;
			}
		}else if("C".equals(currLevel)){
			//当前等级是C  撤销C
			if( num >= num2){
				repealC_B++;
			}else{
				repealC_B=0;
			}
			
			//满足撤销C --> B 时 
			if(repealC_B == 2){
				//清空撤销C的次数
				repealC_B=0;
				return true;
			}
		}else if("".equals(currLevel)){
			//当前还未评分
			return true;
			
		}
		return false;
	}

	/**
	 * 判定C级
	 * 
	 * @param num
	 * @return
	 */
	private boolean verdictC(int num) {
		//若得0分直接评C
		if(num == 0){
			return true;
		}
		
		//1~59分
		if(num < num2){
			// C级计数加1
			markerC++;
			
		}
		
		// 判定计数器是否起效
		if(num < num2 && markerC == L){
			//清空撤销C的次数。
			repealC_B = 0;
			return true;
		}
		return false;
	}

	/**
	 * @param dataList
	 *            评分数据源
	 */
	public FscxFsjsUtil(List<String[]> dataList) {
		super();
		this.dataList = dataList;
	}

	/**
	 * @param n
	 *            A级评分计数
	 * @param m
	 *            B级评分计数
	 * @param l
	 *            C级评分计数
	 * @param dataList
	 *            评分数据源
	 */
	public FscxFsjsUtil(int n, int m, int l, List<String[]> dataList) {
		super();
		N = n;
		M = m;
		L = l;
		this.dataList = dataList;
	}

	public static void main(String[] args) {
		List<String[]> dataList = new ArrayList<String[]>();
		Random num = new Random(1L);
		String[] str = null;
		/**for (int i = 0; i < 10; i++) {
			str = new String[10];
			for (int j = 0; j < 10; j++) {
				//str[j] = String.valueOf(num.nextInt(100));
				str[j] = String.valueOf(String.valueOf((int)Math.floor(Math.random() * 10))+String.valueOf((int)Math.floor(Math.random() * 10)));
				
			}
			dataList.add(str);
		}
		**/
		str =new String[]{"90","85","90","80","0","85","85","85","25","85","80","90"};
		dataList.add(str);

		System.out.println("---------------评分数据-----------------");
		for (int i = 0; i < dataList.size(); i++) {
			System.out.print((i+1) + "\t");
			for (int j = 0; j < dataList.get(i).length; j++) {
				System.out.print(dataList.get(i)[j] + "\t");
			}
			System.out.println();
		}

		FscxFsjsUtil test = new FscxFsjsUtil(dataList);
		//test.setFirstVerdictNum(2);
		List<String[]> resultList = test.grade();
		System.out.println("--------------评分结果--------------");
		for (int i = 0; i < resultList.size(); i++) {
			System.out.print((i+1) + "\t");
			for (int j = 0; j < resultList.get(i).length; j++) {
				System.out.print(resultList.get(i)[j] + "\t");
			}
			System.out.println();
		}
	}

	private void printMarker() {
		System.out.println("markerA:" + markerA + "   markerB:" + markerB
				+ "   markerC" + markerC);
	}

	public int getN() {
		return N;
	}

	public void setN(int n) {
		N = n;
	}

	public int getM() {
		return M;
	}

	public void setM(int m) {
		M = m;
	}

	public int getL() {
		return L;
	}

	public void setL(int l) {
		L = l;
	}

	public int getNum1() {
		return num1;
	}

	public void setNum1(int num1) {
		this.num1 = num1;
	}

	public int getFirstVerdictNum() {
		return firstVerdictNum;
	}

	public void setFirstVerdictNum(int firstVerdictNum) {
		this.firstVerdictNum = firstVerdictNum;
	}

	public int getNeglectNum() {
		return neglectNum;
	}

	public void setNeglectNum(int neglectNum) {
		this.neglectNum = neglectNum;
	}
}