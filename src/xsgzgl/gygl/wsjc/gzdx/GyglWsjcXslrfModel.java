package xsgzgl.gygl.wsjc.gzdx;

/**
 * 学生录入分model，用于学生保存的分数
 * author sjf.
 * 
 */
public class GyglWsjcXslrfModel {
	private String xh;		//学号
	
	private String[] fs;	//分数
	
	private String[] xmdm;  //项目代码  
	
	private String jczq;    //检查周期
	
	private String jcsj;	//检查时间
	
	private String jczc;	//检查周次
	
	private String lrr;     //录入人
	
	private String lrsj;    //录入时间
	
	private String xn;      //学年
	
	private String xq;		//学期
	
	private String nd;		//年度
	

	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	public String getLrr() {
		return lrr;
	}

	public void setLrr(String lrr) {
		this.lrr = lrr;
	}
	
	public String getLrsj() {
		return lrsj;
	}

	public void setLrsj(String lrsj) {
		this.lrsj = lrsj;
	}

	public String[] getFs() {
		return fs;
	}

	public void setFs(String[] fs) {
		this.fs = fs;
	}

	public String[] getXmdm() {
		return xmdm;
	}

	public void setXmdm(String[] xmdm) {
		this.xmdm = xmdm;
	}

	public String getJczq() {
		return jczq;
	}

	public void setJczq(String jczq) {
		this.jczq = jczq;
	}

	public String getJcsj() {
		return jcsj;
	}

	public void setJcsj(String jcsj) {
		this.jcsj = jcsj;
	}

	public String getJczc() {
		return jczc;
	}

	public void setJczc(String jczc) {
		this.jczc = jczc;
	}

	public String getXn() {
		return xn;
	}

	public void setXn(String xn) {
		this.xn = xn;
	}

	public String getXq() {
		return xq;
	}

	public void setXq(String xq) {
		this.xq = xq;
	}

	public String getNd() {
		return nd;
	}

	public void setNd(String nd) {
		this.nd = nd;
	}
}
