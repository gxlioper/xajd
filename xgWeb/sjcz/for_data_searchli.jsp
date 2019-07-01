<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean"
		prefix="bean"%>
	<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
		prefix="html"%>
	<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic"
		prefix="logic"%>
	<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles"
		prefix="tiles"%>
	<%@ taglib uri="http://jakarta.apache.org/struts/tags-template"
		prefix="template"%>
	<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested"
		prefix="nested"%>
	<%@ taglib uri="/WEB-INF/customTag.tld" prefix="customTag"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	
	</head>							
		<logic:equal value="yes" name="writeAble">
					<div class="toolbox">
					<div class="buttonbox" id="toolTipLayer">
						<ul>
							<logic:equal value="wjcfb" name="realTable">
							<!-- 厦门理工不用显示留校察看功能 -->
							<logic:notEqual value="11062" name="xxdm">
								<!-- 浙江理工留校察看提醒 -->
								<logic:equal value="10338" name="xxdm">
									<li><a href="#" class="btn_ck" id="btn_lxck"
										onclick="showTopWin('wjcf_zjlg_lxckcx.do',750,700)">留校察看提醒</a></li>
								</logic:equal>
								<!-- 其它学校留校察看提醒 -->
								<!-- 浙江理工,广州大学不显示留校察看提醒 -->
								<logic:notEqual value="10338" name="xxdm">
									<logic:notEqual value="11078" name="xxdm">
											<logic:notEqual value="12645" name="xxdm">
											<li><a href="#" class="btn_ck" id="btn_lxck" onclick="showTopWin('wjcflxcktj.do',750,700)">留校察看提醒</a></li>
											</logic:notEqual>
										</logic:notEqual>
									</logic:notEqual>
								</logic:notEqual>
							</logic:equal>
							
							<!-- 盐城职业begin -->
							<logic:equal value="zhszcp" name="realTable">
								<logic:notEqual value="13108" name="xxdm">
									<li><a href="#" class="btn_sz" onclick="showTopWin('pjpy_zjcm_zhszcpblsz.do',700,230)">设置比例</a></li>
								</logic:notEqual>
								&nbsp;
								<logic:equal value="13108" name="xxdm">
									<li><a href="#" class="btn_zj" onclick="AutoAccountCj('/xgxt/AutoAccount.do?type=drjs')">导入后重新计算</a></li>
								</logic:equal>
								&nbsp;
							</logic:equal>
							<!-- 盐城职业end -->
							
							<logic:equal value="wjcfb" name="realTable">
								<li><a href="#" class="btn_sx" onclick="wjsjzy('wjcf_shgc_lssjzy.do')">毕业生数据转移</a></li>
								<logic:equal value="11626" name="xxdm">
								<li><a href="#" class="btn_xy" onclick="wjcfjj()">违纪处分降级</a></li>
								</logic:equal>
								<input type="hidden" name="wjcfflag" id="wjcfflag" value="${wjcfflag }"/>
								<logic:equal name="xxdm" value="10388">
									<li><a href="#" class="btn_tj" onclick="wjcfhz()">汇总统计</a></li>
								</logic:equal>
							</logic:equal>
							
							<%--武汉理工大学华夏<bean:message key="lable.xsgzyxpzxy" />--%>
							<logic:equal value="1049701" name="xxdm">
								<li><a href="#" class="btn_xg" onclick="kftj('wjcfkftj.do')">扣分统计</a></li>	
							</logic:equal>
							
							<logic:present name="showzdjs">
								<li><a href="#" class="btn_dr" onclick="jsxxzcCount()">自动计算</a></li>
							</logic:present>
							
							<logic:notPresent name="showzdjs">
							<logic:equal name="act" value="party">
								
								<%--begin浙江传媒<bean:message key="lable.xsgzyxpzxy" />--%>
								<logic:equal name="xxdm" value="11647">
									<li><a href="#" class="btn_dr" onclick="if(curr_row == null){alert('请选择要接转的学生!');return false;}else{showTopWin('/xgxt/dtjs_zjcm.do?method=zzgx&rdsj='+curr_row.cells[7].innerText+'&pk='+curr_row.cells[3].innerText,600,480)}">组织关系接转</a></li>
								</logic:equal>
								<%--end浙江传媒<bean:message key="lable.xsgzyxpzxy" />--%>
								<li><a href="#" class="btn_dr" onclick="showTips('处理数据中，请等待......');refreshForm('/xgxt/party_stuinfo.do');">更新到学生库</a></li>
							</logic:equal>
							
							<logic:equal name="act" value="prepare">
								<%--begin浙江传媒<bean:message key="lable.xsgzyxpzxy" />--%>
								<logic:equal name="xxdm" value="11647">
									<li><a href="#" class="btn_dc" onclick="showTopWin('/xgxt/dtjs_zjcm.do?method=zzYb&go=go',800,600);">党员转正</a></li>
								</logic:equal>
								<%--end浙江传媒<bean:message key="lable.xsgzyxpzxy" />--%>
								<li><a href="#" class="btn_dc" onclick="showTips('处理数据中，请等待......');refreshForm('/xgxt/party_stuinfo.do');">更新到学生库</a></li>
							</logic:equal>
							
							<%--begin浙江传媒<bean:message key="lable.xsgzyxpzxy" />--%>
							<logic:equal name="act" value="active">
								<logic:equal name="xxdm" value="11647">
									<li><a href="#" class="btn_dc" onclick="showTopWin('/xgxt/dtjs_zjcm.do?method=fzdxAll&go=go',800,600);">发展对象设置</a></li>
								</logic:equal>
							</logic:equal>
							<%--end浙江传媒<bean:message key="lable.xsgzyxpzxy" />--%>
							<logic:notEqual name="realTable" value="bks_xsszjbxx">
								<logic:equal value="12872" name="xxdm">
									<logic:equal value="view_xsrychb" name="tableName">
										<li><a href="#" class="btn_zj" onclick="refreshForm('credit_apply.do')">增加</a></li>
									</logic:equal>
									<logic:notEqual value="view_xsrychb" name="tableName">
									<logic:notEqual value="xy" name="userType">
										<li><a href="#" class="btn_zj" onclick="viewMore('add')">增加</a></li>
									</logic:notEqual>
									</logic:notEqual>
								</logic:equal>
								<logic:notEqual value="12872" name="xxdm">
									<li><a href="#" class="btn_zj" onclick="viewMore('add')">增加</a></li>
								</logic:notEqual>
							</logic:notEqual>
							
							<logic:present name="xsjxjb">
										<logic:equal value="12872" name="xxdm">
											<logic:notEqual value="xy" name="userType">
											<li><a href="#" class="btn_xg" onclick="hzyjxjmodi()">修改1</a></li>
											</logic:notEqual>
										</logic:equal>
										
										<logic:notEqual value="12872" name="xxdm">
											<li><a href="#" class="btn_xg" onclick="viewMore2('modi')">修改2</a></li>
										</logic:notEqual>
							</logic:present>
							
							<logic:notPresent name="xsjxjb">
							<logic:equal value="12872" name="xxdm">
								<logic:equal value="xsrychb" name="realTable">
									<li><a href="#" class="btn_xg" onclick="hzzyrychmodi()">修改3</a></li>
								</logic:equal>
								
								<logic:notEqual value="xsrychb" name="realTable">
									<li><a href="#" class="btn_xg" onclick="viewMore('modi')">修改4</a></li>			
								</logic:notEqual>
							</logic:equal>
							
							<logic:notEqual value="12872" name="xxdm">
								<logic:notEqual name="xxdm" value="10827" scope="session">
								<li><a href="#" class="btn_xg" onclick="
										<logic:equal value="10827" name="xxdm"><logic:equal value="view_xsrychb" name="tableName">
										updaterychxx('modi')
										</logic:equal>
										<logic:notEqual value="view_xsrychb" name="tableName">viewMore('modi')</logic:notEqual></logic:equal>
										<logic:notEqual value="10827" name="xxdm">viewMore('modi')</logic:notEqual>">修改</a></li>
								</logic:notEqual>
							
							<logic:equal value="10827" name="xxdm">
									<li><a href="#" class="btn_xg" onclick="viewMore('modi')">修改6</a></li>
							</logic:equal>
							</logic:notEqual>
							</logic:notPresent>
							
							<logic:notEqual name="realTable" value="bks_xsszjbxx">
								<logic:equal value="12872" name="xxdm">
								<logic:equal value="xsjxjb" name="realTable">
									<logic:notEqual value="xy" name="userType">
										<li><a href="#" class="btn_sc" onclick="viewMore('del')">删除</a></li>
									</logic:notEqual>
								</logic:equal>
								<logic:notEqual value="xsjxjb" name="realTable">
								<logic:equal value="xsrychb" name="realTable">
									<logic:notEqual value="xy" name="userType">
										<li><a href="#" class="btn_sc" onclick="viewMore('del')">删除</a></li>
							</logic:notEqual>
							</logic:equal>
							
							<logic:notEqual value="xsrychb" name="realTable">
										<li><a href="#" class="btn_sc" onclick="viewMore('del')">删除</a></li>
										</logic:notEqual> 
										
										</logic:notEqual>
										</logic:equal>
										<logic:notEqual value="12872" name="xxdm">
										<li><a href="#" class="btn_sc" onclick="viewMore('del')">删除</a></li>
										</logic:notEqual>
										
									</logic:notEqual>
									&nbsp;						
							</logic:notPresent>
							<logic:notEqual value="no" name="xydel">
<%--									<button type="button" class="button2" onclick="Alldel()" style="width: 80px">--%>
<%--										全部删除--%>
<%--									</button>--%>
							</logic:notEqual>
							
							<logic:notEqual value="view_xslxfszsxx" name="tableName">
								<logic:equal value="yes" name="writeAble" scope="request">
									<%--武汉理工大学--%>
									<logic:equal value="10497" name="xxdm">
										<li><a href="#" class="btn_dr" onclick="impAndChkData()">导入数据</a></li>
									</logic:equal>
									<%--end武汉理工大学--%>
									<logic:notEqual value="10497" name="xxdm">
										<logic:equal value="12872" name="xxdm">
											<logic:notEqual value="xy" name="userType">
												<li><a href="#" class="btn_dr" onclick="impAndChkData()">导入数据</a></li>
											</logic:notEqual>
										</logic:equal>
										
										<logic:notEqual value="12872" name="xxdm">
											<!-- 非投保信息维护 -->
											<logic:notEqual name="tableName" value="view_xsbxxx">
											<li><a href="#" class="btn_dr" onclick="impAndChkData()">导入</a></li>
											</logic:notEqual>
											<!-- 投保信息维护 -->
											<logic:equal name="tableName" value="view_xsbxxx">
												<logic:notEqual name="act" value="insureInfo">
													<li><a href="#" class="btn_dr" onclick="impAndChkData()">导入数据</a></li>
												</logic:notEqual>
											</logic:equal>
										</logic:notEqual>
										<!-- "impAndChkData();" -->
									</logic:notEqual>
									
									<logic:equal value="12872" name="xxdm">
										<logic:equal value="xsjxjb" name="realTable">
											<logic:notEqual value="xy" name="userType">
												&nbsp;
											</logic:notEqual>
										</logic:equal>
										<logic:notEqual value="xsjxjb" name="realTable">
										&nbsp;
										<li><a href="#" class="btn_dc" onclick="dataExport()">导出数据</a></li>
										</logic:notEqual>
									</logic:equal>	
									<logic:notEqual value="12872" name="xxdm">
										&nbsp;
											<!-- 非投保信息维护 -->
											<logic:notEqual name="tableName" value="view_xsbxxx">
												<li><a href="#" class="btn_dc" onclick="dataExport()">导出</a></li>
												<logic:equal value="11641" name="xxdm">
													<logic:equal name="realTable" value="xsjxjb">
													<li><a href="#" class="btn_tj" onclick="hhgxyTj()"> 统计 </a></li>
													</logic:equal>
												</logic:equal>
											</logic:notEqual>
											<logic:equal name="xxdm" value="1024701">
												<li><a href="#" onclick="bbdcExport();return false;" class="btn_dc">报表导出</a></li>
											</logic:equal>
											<!-- 投保信息维护 -->
											<logic:equal name="tableName" value="view_xsbxxx">
												<logic:notEqual name="act" value="insureInfo">
													<li><a href="#" class="btn_dc" onclick="dataExport()">导出数据</a></li>
												</logic:notEqual>
											</logic:equal>
										</logic:notEqual>
										
										<!-- 上海工程begin -->
										<logic:equal value="10856" name="xxdm">
											<logic:equal value="wjcfb" name="realTable">
												<li><a href="#" class="btn_dr" onclick="bgdgl()" title="进入行政违纪处分不归档增加页面">处分不归档管理</a></li>	
											</logic:equal>
										</logic:equal>
											<!-- 上海工程end -->
										</logic:equal>
										<!-- 读写权 -->
										<!-- 江苏信息 综合素质测评打印 begin -->
										<logic:present name="showjsxx">
										<logic:equal value="zhszcp" name="realTable">
										<li><a href="#" class="btn_dc" onclick="jsxxzcPrint()">导出</a></li>
										</logic:equal>
										</logic:present>
										<!-- end -->
										<logic:present name="isCSMZ">
											<logic:equal value="yes" name="isCSMZ">
											</logic:equal>
										</logic:present>
										
									<logic:present name="showhzy">
									<logic:equal value="view_xsrychb" name="tableName">
										<li><a href="#" class="btn_dy" onclick="hzyrychprint();">报表打印</a></li>
									</logic:equal>
									
									<logic:equal value="view_xsjxjb" name="tableName">
									<logic:equal value="yes" name="writeAble" scope="request">
										<logic:equal value="xy" name="userType">
											<logic:equal value="view_xsrychb" name="tableName">
												<li><a href="#" class="btn_dy" onclick="refreshForm('credit_apply.do')">增加</a></li>
											</logic:equal>
											<logic:notEqual value="view_xsrychb" name="tableName">
												<li><a href="#" class="btn_dy" onclick="viewMore('add')">增加</a></li>
											</logic:notEqual>
											<li><a href="#" class="btn_dy" onclick="hzyjxjmodi()">修改</a></li>
<%--											&nbsp;--%>
<%--											<button type="button" class="button2" onclick="viewMore('del')"--%>
<%--												style="width: 80px">--%>
<%--												删 除--%>
<%--											</button>--%>
<%--								&nbsp;	
								--%>
									<li><a href="#" class="btn_dy" onclick="dataExport()">导出数据</a></li>
									</logic:equal>
									</logic:equal>
										<li><a href="#" class="btn_dy" onclick="hzyprint()" id="buttonQuery">打印报表</a></li>
									</logic:equal>
								</logic:present>
								
								<logic:equal value="10878" name="xxdm">
									<logic:equal value="view_zhszcp" name="tableName">
										<li><a href="#" class="btn_dy" onclick="impAndChkDataByahjg()">评分数据导入</a></li>
									</logic:equal>
								</logic:equal>
								<logic:notPresent name="showjsxx">
									<logic:notEqual value="12872" name="xxdm">
										<%--浙江大学宁波理工<bean:message key="lable.xsgzyxpzxy" />--%><%--
										  <logic:equal value="13022" name="xxdm">
											<logic:equal value="view_zhszcp" name="tableName">
												<a href="xlsDown/nblgddcj.xls" target="_blank">道德积分</a>

												<a href="xlsDown/nblgddjfb.xls" target="_blank">行为积分</a>

												<a href="xlsDown/nblgshqb.xls" target="_blank">生活区积分</a>
											</logic:equal>
											<logic:notEqual value="view_zhszcp" name="tableName">
												<!--  <a
													href="xlsDown/<bean:write name="realTable" scope="request"/>.xls"
													target="_blank">模板下载</a>-->
											</logic:notEqual>
										</logic:equal>
										--%><logic:notEqual value="13022" name="xxdm">
											<%--上海出版印刷高等专科学校--%>
											<logic:equal value="11733" name="xxdm">
												<%--评奖评优--%>
												<logic:equal value="view_xsjxjb" name="tableName">
													<a href="xlsDown/shcbysgdzkxx_xsjxjb.xls" target="_blank">模板下载</a>
												</logic:equal>
												<%--非评奖评优--%>
												<logic:notEqual value="view_xsjxjb" name="tableName">
													<a
														href="xlsDown/<bean:write name="realTable" scope="request"/>.xls"
														target="_blank">模板下载</a>
												</logic:notEqual>
											</logic:equal>
											<%--非上海出版印刷高等专科学校--%>
											<logic:notEqual value="11733" name="xxdm">
												<logic:notEqual value="11417" name="xxdm">
													<%--													<a--%>
													<%--														href="xlsDown/<bean:write name="realTable" scope="request"/>.xls"--%>
													<%--														target="_blank">模板下载</a>--%>
												</logic:notEqual>
											</logic:notEqual>
										</logic:notEqual>
									</logic:notEqual>
									<%--杭州职业技术<bean:message key="lable.xsgzyxpzxy" />--%>
									<logic:equal value="12872" name="xxdm">
										<logic:equal value="yes" name="writeAble">
											<logic:equal value="view_xsjxjb" name="tableName">
												<a href="xlsDown/hzyjxjsqb.xls" target="_blank">模板下载</a>
											</logic:equal>
											<logic:notEqual value="view_xsjxjb" name="tableName">
												<%--												<a--%>
												<%--													href="xlsDown/<bean:write name="realTable" scope="request"/>.xls"--%>
												<%--													target="_blank">模板下载</a>--%>
											</logic:notEqual>
										</logic:equal>
									</logic:equal>
									<%--end杭州职业技术<bean:message key="lable.xsgzyxpzxy" />--%>
								</logic:notPresent>
							</logic:notEqual>	
							<%--安徽建筑工业<bean:message key="lable.xsgzyxpzxy" />--%>
							<logic:equal value="10878" name="xxdm">
								<logic:equal value="view_zhszcp" name="tableName">
									<a href="xlsDown/ahjg_sjdxspfb.xls" target="_blank">评分模板</a>
								</logic:equal>
							</logic:equal>
							<%--end安徽建筑工业<bean:message key="lable.xsgzyxpzxy" />--%>

								<logic:equal value="wjcfb" name="realTable">
									<!-- 广州大学违纪汇总表 -->
									<logic:equal value="11078" name="xxdm">
										<li><a href="#" class="btn_dr" onclick="wjcfDataExport('wjcf_gzdx_wjcfhzb.do')">处分汇总</a></li>
										<li><a href="#" class="btn_dr" onclick="wjcfDataExport('wjcf_gzdx_wjcfhzbByjwc.do')">教务处处分汇总</a></li>
									</logic:equal>
									<!-- 浙江传媒 <bean:message key="lable.xsgzyxpzxy" />处分表汇总 -->
									<logic:equal value="11647" name="xxdm">
										<li><a href="#" class="btn_dr" onclick="if (confirm('将按学年,学期,<bean:message key="lable.xsgzyxpzxy" />进行处分数据的统计,确认要输出吗?')) {wjcfDataExport('wjcf_zjcm_cfhzb.do?xydm='+document.getElementById('xydm').value)}">学生处分汇总表</a></li>
									</logic:equal>
								</logic:equal>				
										
						</ul>
					 </div>
					</div>
		</logic:equal>
</html>