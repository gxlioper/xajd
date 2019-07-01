package xsgzgl.gygl.wsjc.fscx;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��Ԣ����-������ѯ
 * @�๦������: �������㹤����
 * @���ߣ� �׽���[781]
 * @ʱ�䣺 2013-10-21 ����11:01:50 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class FscxFsjsUtil {
	
	/**
	1��	A��������������������85�ּ�����
	2��	A�����ҳ������ۼ�����85�ּ�����
	3��	C�������������ۼ����ܵ���60������
	4��	C�����ҳ������������ܸ���60�ּ�����
	5��	��A��C���������ΪB������
	6��	����0�ֺ󣬵ȼ�����C
	7��	����1��59��������������ȼ���ΪB���ۼ����α�C
	8��	ѧ��������鳷�������������֮ǰ����������ָ����ʦ������г���A�������ң����ҵ��ܵ�����������Ϊ59�֣�������ָ����ʦ�޸Ĳ�¼�뵽ϵͳ��


	
	/****************************��ͨ��set*������������*************************/
	private int N = 3;// A�����ּ���
	private int M = 2;// B�����ּ���
	private int L = 2;// C�����ּ���

	private int num1 = 85;//��������num1
	private int num2 = 60;//��������num2
	
	private int firstVerdictNum = 2;// �״εȼ��ж���  �ڼ���
	
	private int neglectNum = 0;//���Լ������
	/*****************************************************************/
	
	/****************************������������*************************/
	private int markerA = 0;// A�����ֱ��
	private int markerB = 0;// B�����ֱ��
	private int markerC = 0;// C�����ֱ��
	
	private int repealA_B = 0;//����A ��B
	private int repealC_B = 0;//����C ��B
	
	//private int markerAClueNum = 0;// ����A�������Ѵ���

	private int markerANum = 0;// ����A����
	
	private String currLevel = "";// ��ǰ����
	private List<String[]> dataList = new ArrayList<String[]>();// ����Դ
	/*****************************************************************/
	
	/**
	 * ����
	 * 
	 * @return List<String[]>
	 */
	public List<String[]> grade() {
		// ���ֽ���б�
		List<String[]> gradeResultList = new ArrayList<String[]>();
		// ���ֽ��
		String[] gradeResult = null;
		// ������ֵ
		int gradeValue = 0;
		// ��ʼ�ж�����
		for (int i = 0; i < dataList.size(); i++) {
			// ��¼���ֽ��
			gradeResult = new String[dataList.get(i).length];
			for (int j = 0; j < dataList.get(i).length; j++) {
				//���Բ��ж�
				if(j < neglectNum){
					//������������
					gradeResult[j] = String.valueOf(dataList.get(i)[j]);
					continue;
				}
				gradeValue = Integer.valueOf(dataList.get(i)[j]);
				
				// ��ʼ����
				verdict(gradeValue);

				// �״��ж���¼�ȼ�
				if (j >= firstVerdictNum) {
					//System.out.println("aaa");
					// ��¼���ֽ��
					gradeResult[j] = currLevel;
				}else{
					//��յ�ǰ�ȼ�
					currLevel="";
					// �״��ж���ǰ���������
					gradeResult[j] = "";
				}
			}

			// ��������
			gradeResultList.add(gradeResult);

			// ��ձ��
			cleanMarker();
		}
		return gradeResultList;
	}

	/**
	 * ��������
	 * 
	 * @param gradeValue
	 */
	private void verdict(int gradeValue) {
		//System.out.println("markerA:"+markerA+" markerB:"+markerB+" markerC:"+markerC+" repealA_B:"+repealA_B+" repealC_B:"+repealC_B+" currLevel:"+currLevel);
		// ��ʼ����
		if (verdictA(gradeValue)) {
			// ��֤������A
			currLevel = "A";
		} else if (verdictC(gradeValue)) {
			// ��֤������C
			currLevel = "C";
		} else if (verdictB(gradeValue)) {
			// ��֤������B
			currLevel = "B";
		} else {
			
		}
		
	}

	/**
	 * ��ձ��
	 */
	private void cleanMarker() {
		//��ձ��
		markerA = 0;
		markerB = 0;
		markerC = 0;
		repealA_B = 0;
		repealC_B = 0;
		// ��յȼ�
		currLevel = "";
		// �����A����
		markerANum=0;
	}

	/**
	 * �ж�A��
	 * 
	 * @param num
	 * @return
	 */
	private boolean verdictA(int num) {
		// �ж�A����������
		if (num >= num1) {
			// A��������1
			markerA++;
		}else{
			// ���markerA������
			markerA = 0;
		}

		// �ж��������Ƿ���Ч
		if (markerA == N) {
			//�����C�ı��
			markerC = 0;
			//����A  ��  ����C ��¼���
			repealA_B = 0;
			repealC_B = 0;
			return true;
		}

		return false;
	}

	/**
	 * �ж�B��
	 * 
	 * @param num
	 * @return
	 */
	private boolean verdictB(int num) {
		if("A".equals(currLevel)){
			//��ǰ�ȼ���A������A
			if(num2 <= num && num < num1){
				//����A ,A --> B ��¼һ��
				repealA_B++;
			}else if(0 < num && num < num2){
				//����A ,A --> B  ��¼ȡ��    ֱ�ӳ���A����B
				return true;
			}
			//���㳷��A --> B ʱ
			if(repealA_B == 3){
				//��ճ���A�Ĵ���
				repealA_B=0;
				return true;
			}
		}else if("C".equals(currLevel)){
			//��ǰ�ȼ���C  ����C
			if( num >= num2){
				repealC_B++;
			}else{
				repealC_B=0;
			}
			
			//���㳷��C --> B ʱ 
			if(repealC_B == 2){
				//��ճ���C�Ĵ���
				repealC_B=0;
				return true;
			}
		}else if("".equals(currLevel)){
			//��ǰ��δ����
			return true;
			
		}
		return false;
	}

	/**
	 * �ж�C��
	 * 
	 * @param num
	 * @return
	 */
	private boolean verdictC(int num) {
		//����0��ֱ����C
		if(num == 0){
			return true;
		}
		
		//1~59��
		if(num < num2){
			// C��������1
			markerC++;
			
		}
		
		// �ж��������Ƿ���Ч
		if(num < num2 && markerC == L){
			//��ճ���C�Ĵ�����
			repealC_B = 0;
			return true;
		}
		return false;
	}

	/**
	 * @param dataList
	 *            ��������Դ
	 */
	public FscxFsjsUtil(List<String[]> dataList) {
		super();
		this.dataList = dataList;
	}

	/**
	 * @param n
	 *            A�����ּ���
	 * @param m
	 *            B�����ּ���
	 * @param l
	 *            C�����ּ���
	 * @param dataList
	 *            ��������Դ
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

		System.out.println("---------------��������-----------------");
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
		System.out.println("--------------���ֽ��--------------");
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