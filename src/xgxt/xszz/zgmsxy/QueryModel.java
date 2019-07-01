
package xgxt.xszz.zgmsxy;

import java.io.Serializable;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 中国美术学院查询MODEL</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 周觅</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-12-16</p>
 */
public class QueryModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String bjdm;//班级代码
	private String xydm;//学院代码
	private String zydm;//专业代码
	private String xh;//学号
	private String xm;//姓名
	private String xn;//学年
	private String nd;//年度
	private String nj;//年级
	private String mzpyjg;//推荐档次
	private String xysh;//学院审核
	private String xxsh;//学校审核
	private String sffk;//是否放款
	private String sfzh;//身份证号
	private String go;//查询标识
	private String isQuery;//功能标识
	
	
	private String lxdh;          
	private String jtzz;          
	private String yzbm;          
	private String fqxm;          
	private String fqgzdw;        
	private String fqysr;         
	private String fqdh;          
	private String mqxm;          
	private String mqgzdw;        
	private String mqysr;         
	private String mqdh;          
	private String brjyqxhdw;
	private String jtgddh;        
	private String brdzyxjdzlxfs ;
	private String dqgzdwjdz;     
	private String dqgzdwyb;      
	private String dqgzdwdh;      
	private String lxfsbgqk; 
	
	private String yxsh;//院系审核
	
	public String getBrdzyxjdzlxfs() {
		return brdzyxjdzlxfs;
	}
	public void setBrdzyxjdzlxfs(String brdzyxjdzlxfs) {
		this.brdzyxjdzlxfs = brdzyxjdzlxfs;
	}
	public String getBrjyqxhdw() {
		return brjyqxhdw;
	}
	public void setBrjyqxhdw(String brjyqxhdw) {
		this.brjyqxhdw = brjyqxhdw;
	}
	public String getDqgzdwdh() {
		return dqgzdwdh;
	}
	public void setDqgzdwdh(String dqgzdwdh) {
		this.dqgzdwdh = dqgzdwdh;
	}
	public String getDqgzdwjdz() {
		return dqgzdwjdz;
	}
	public void setDqgzdwjdz(String dqgzdwjdz) {
		this.dqgzdwjdz = dqgzdwjdz;
	}
	public String getDqgzdwyb() {
		return dqgzdwyb;
	}
	public void setDqgzdwyb(String dqgzdwyb) {
		this.dqgzdwyb = dqgzdwyb;
	}
	public String getFqdh() {
		return fqdh;
	}
	public void setFqdh(String fqdh) {
		this.fqdh = fqdh;
	}
	public String getFqgzdw() {
		return fqgzdw;
	}
	public void setFqgzdw(String fqgzdw) {
		this.fqgzdw = fqgzdw;
	}
	public String getFqxm() {
		return fqxm;
	}
	public void setFqxm(String fqxm) {
		this.fqxm = fqxm;
	}
	public String getFqysr() {
		return fqysr;
	}
	public void setFqysr(String fqysr) {
		this.fqysr = fqysr;
	}
	public String getJtgddh() {
		return jtgddh;
	}
	public void setJtgddh(String jtgddh) {
		this.jtgddh = jtgddh;
	}
	public String getJtzz() {
		return jtzz;
	}
	public void setJtzz(String jtzz) {
		this.jtzz = jtzz;
	}
	public String getLxdh() {
		return lxdh;
	}
	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}
	public String getLxfsbgqk() {
		return lxfsbgqk;
	}
	public void setLxfsbgqk(String lxfsbgqk) {
		this.lxfsbgqk = lxfsbgqk;
	}
	public String getMqdh() {
		return mqdh;
	}
	public void setMqdh(String mqdh) {
		this.mqdh = mqdh;
	}
	public String getMqgzdw() {
		return mqgzdw;
	}
	public void setMqgzdw(String mqgzdw) {
		this.mqgzdw = mqgzdw;
	}
	public String getMqxm() {
		return mqxm;
	}
	public void setMqxm(String mqxm) {
		this.mqxm = mqxm;
	}
	public String getMqysr() {
		return mqysr;
	}
	public void setMqysr(String mqysr) {
		this.mqysr = mqysr;
	}
	public String getYzbm() {
		return yzbm;
	}
	public void setYzbm(String yzbm) {
		this.yzbm = yzbm;
	}
	
	public String getSfzh() {
		return sfzh;
	}
	public void setSfzh(String sfzh) {
		this.sfzh = sfzh;
	}
	public String getGo() {
		return go;
	}
	public void setGo(String go) {
		this.go = go;
	}
	public String getBjdm() {
		return bjdm;
	}
	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
	}
	public String getNj() {
		return nj;
	}
	public void setNj(String nj) {
		this.nj = nj;
	}
	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh = xh;
	}
	public String getXn() {
		return xn;
	}
	public void setXn(String xn) {
		this.xn = xn;
	}
	public String getXydm() {
		return xydm;
	}
	public void setXydm(String xydm) {
		this.xydm = xydm;
	}
	public String getZydm() {
		return zydm;
	}
	public void setZydm(String zydm) {
		this.zydm = zydm;
	}
	public String getNd() {
		return nd;
	}
	public void setNd(String nd) {
		this.nd = nd;
	}
	public String getIsQuery() {
		return isQuery;
	}
	public void setIsQuery(String isQuery) {
		this.isQuery = isQuery;
	}
	public String getXxsh() {
		return xxsh;
	}
	public void setXxsh(String xxsh) {
		this.xxsh = xxsh;
	}
	public String getXysh() {
		return xysh;
	}
	public void setXysh(String xysh) {
		this.xysh = xysh;
	}
	public String getMzpyjg() {
		return mzpyjg;
	}
	public void setMzpyjg(String mzpyjg) {
		this.mzpyjg = mzpyjg;
	}
	public String getSffk() {
		return sffk;
	}
	public void setSffk(String sffk) {
		this.sffk = sffk;
	}
	public String getXm() {
		return xm;
	}
	public void setXm(String xm) {
		this.xm = xm;
	}
	public String getYxsh() {
		return yxsh;
	}
	public void setYxsh(String yxsh) {
		this.yxsh = yxsh;
	}
	
}
