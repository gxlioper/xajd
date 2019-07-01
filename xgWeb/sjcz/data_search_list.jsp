<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
			<div id="toolTipLayer"
						style="position: absolute; visibility: hidden" >
					<center>
						<div class="buttontool" id="btn"
							style="position: absolute; left: 1%; top: 100px">
							<logic:equal value="wjcfb" name="realTable">
								<!-- 厦门理工不用显示留校察看功能 -->
								<logic:notEqual value="11062" name="xxdm">
									<!-- 浙江理工留校察看提醒 -->
									<logic:equal value="10338" name="xxdm">
										<button type="button" class="button2" id="btn_lxck"
											onclick="showTopWin('wjcf_zjlg_lxckcx.do',750,600)"
											style="width:90px">
											留校察看提醒
										</button>&nbsp;
									</logic:equal>
									<!-- 其它学校留校察看提醒 -->
									<!-- 浙江理工,广州大学不显示留校察看提醒 -->
									<logic:notEqual value="10338" name="xxdm">
										<logic:notEqual value="11078" name="xxdm">
											<logic:notEqual value="12645" name="xxdm">
											<button type="button" class="button2"
												onclick="showTopWin('wjcflxcktj.do',740,600)"
												style="width:90px">
												留校察看提醒
											</button>&nbsp;
											</logic:notEqual>
										</logic:notEqual>
									</logic:notEqual>
								</logic:notEqual>
							</logic:equal>
							
							<logic:equal value="yes" name="writeAble" scope="request">
								<!-- 盐城职业begin -->
								<logic:equal value="zhszcp" name="realTable">
									<logic:notEqual value="13108" name="xxdm">
										<button type="button" class="button2" id="btn_add" style="width:80px"
											onclick="showTopWin('pjpy_zjcm_zhszcpblsz.do',700,230)">
											设置比例
										</button>
									</logic:notEqual>
									&nbsp;
									<logic:equal value="13108" name="xxdm">
										<button type="button" class="button2" id="btn_add" style="width:120px"
											onclick="AutoAccountCj('/xgxt/AutoAccount.do?type=drjs')">
											导入后重新计算
										</button>
									</logic:equal>
									&nbsp;
								</logic:equal>
								<!-- 盐城职业end -->
								<logic:equal value="wjcfb" name="realTable">
									<button type="button" class="button2" onclick="wjsjzy('wjcf_shgc_lssjzy.do')"
										style="width:100px">
										毕业生数据转移
									</button>
								</logic:equal>
								<%--武汉理工大学华夏<bean:message key="lable.xsgzyxpzxy" />--%>
								<logic:equal value="1049701" name="xxdm">
									<button type="button" class="button2" onclick="kftj('wjcfkftj.do')"
										style="width:60px">
										扣分统计
									</button>
								</logic:equal>
								<logic:present name="showzdjs">
									<button type="button" class="button2" onclick="jsxxzcCount()"
										style="width: 80px">
										自动计算
									</button>
								</logic:present>
								&nbsp;
								<logic:notPresent name="showzdjs">
									<logic:equal name="act" value="party">
										<%--begin浙江传媒<bean:message key="lable.xsgzyxpzxy" />--%>
										<logic:equal name="xxdm" value="11647">
											<button type="button" class="button2"
												onclick="if(curr_row == null){alert('请选择要接转的学生!');return false;}else{showTopWin('/xgxt/dtjs_zjcm.do?method=zzgx&rdsj='+curr_row.cells[7].innerText+'&pk='+curr_row.cells[3].innerText,600,480)}"
												style="width: 90px">
												组织关系接转
											</button>
										</logic:equal>
										<%--end浙江传媒<bean:message key="lable.xsgzyxpzxy" />--%>
										<button type="button" class="button2"
											onclick="showTips('处理数据中，请等待......');refreshForm('/xgxt/party_stuinfo.do');"
											style="width: 90px">
											更新到学生库
										</button>
										&nbsp;
									</logic:equal>
									<logic:equal name="act" value="prepare">
										<%--begin浙江传媒<bean:message key="lable.xsgzyxpzxy" />--%>
										<logic:equal name="xxdm" value="11647">
											<button type="button" class="button2"
												onclick="showTopWin('/xgxt/dtjs_zjcm.do?method=zzYb&go=go',800,600);"
												style="width: 90px">
												党员转正
											</button>
										</logic:equal>
										<%--end浙江传媒<bean:message key="lable.xsgzyxpzxy" />--%>
										<button type="button" class="button2"
											onclick="showTips('处理数据中，请等待......');refreshForm('/xgxt/party_stuinfo.do');"
											style="width: 90px">
											更新到学生库
										</button>
										&nbsp;
									</logic:equal>
									<%--begin浙江传媒<bean:message key="lable.xsgzyxpzxy" />--%>
									<logic:equal name="act" value="active">
										<logic:equal name="xxdm" value="11647">
											<button type="button" class="button2"
												onclick="showTopWin('/xgxt/dtjs_zjcm.do?method=fzdxAll&go=go',800,600);"
												style="width:90px">
												发展对象设置
											</button>
										</logic:equal>
									</logic:equal>
									<%--end浙江传媒<bean:message key="lable.xsgzyxpzxy" />--%>
									<logic:notEqual name="realTable" value="bks_xsszjbxx">
										<logic:equal value="12872" name="xxdm">
											<logic:equal value="view_xsrychb" name="tableName">
												<button type="button" class="button2"
													onclick="refreshForm('credit_apply.do')"
													style="width: 80px">
													增 加
												</button>
											</logic:equal>
											<logic:notEqual value="view_xsrychb" name="tableName">
											<logic:notEqual value="xy" name="userType">
												<button type="button" class="button2" onclick="viewMore('add')"
													style="width: 80px">
													增 加
												</button>
												</logic:notEqual>
											</logic:notEqual>
										</logic:equal>
										<logic:notEqual value="12872" name="xxdm">
											<button type="button" class="button2" onclick="viewMore('add')"
												style="width: 80px">
												增 加
											</button>
										</logic:notEqual>
									</logic:notEqual>
									&nbsp;
									<logic:present name="xsjxjb">
										<logic:equal value="12872" name="xxdm">
											<logic:notEqual value="xy" name="userType">
											<button type="button" class="button2" onclick="hzyjxjmodi()"
												style="width: 80px">
												修 改
											</button>
											</logic:notEqual>
										</logic:equal>
										<logic:notEqual value="12872" name="xxdm">
											<button type="button" class="button2" onclick="viewMore2('modi')"
												style="width: 80px">
												修 改
											</button>
										</logic:notEqual>
									</logic:present>
									<logic:notPresent name="xsjxjb">
										<logic:equal value="12872" name="xxdm">
											<logic:equal value="xsrychb" name="realTable">
												<button type="button" class="button2" onclick="hzzyrychmodi()"
													style="width: 80px">
													修 改
												</button>
											</logic:equal>
											<logic:notEqual value="xsrychb" name="realTable">
												
												<button type="button" class="button2" onclick="viewMore('modi')"
													style="width: 80px">
													修 改
												</button>
											</logic:notEqual>
										</logic:equal>
										<logic:notEqual value="12872" name="xxdm">
											<logic:notEqual name="xxdm" value="10827" scope="session">
												<button type="button" class="button2"
													onclick="<logic:equal value="10827" name="xxdm"><logic:equal value="view_xsrychb" name="tableName">updaterychxx('modi')</logic:equal><logic:notEqual value="view_xsrychb" name="tableName">viewMore('modi')</logic:notEqual></logic:equal><logic:notEqual value="10827" name="xxdm">viewMore('modi')</logic:notEqual>"
													style="width: 80px">
													修 改
												</button>
											</logic:notEqual>
											<logic:equal value="10827" name="xxdm">
												<button type="button" class="button2" onclick="viewMore('modi')"
													style="width: 80px">
													修 改
												</button>
											</logic:equal>
										</logic:notEqual>
									</logic:notPresent>
									&nbsp;
									<logic:notEqual name="realTable" value="bks_xsszjbxx">
										<logic:equal value="12872" name="xxdm">
										<logic:equal value="xsjxjb" name="realTable">
											<logic:notEqual value="xy" name="userType">
												<button type="button" class="button2" onclick="viewMore('del')"
											style="width: 80px">
											删 除
										</button>
											</logic:notEqual>
										</logic:equal>
										<logic:notEqual value="xsjxjb" name="realTable">
										
										<logic:equal value="xsrychb" name="realTable">
											<logic:notEqual value="xy" name="userType">
												<button type="button" class="button2" onclick="viewMore('del')"
											style="width: 80px">
											删 除
										</button>
											</logic:notEqual>
										</logic:equal>
										<logic:notEqual value="xsrychb" name="realTable">
										<button type="button" class="button2" onclick="viewMore('del')"
											style="width: 80px">
											删 除
										</button>
										</logic:notEqual> 
										
										</logic:notEqual>
										</logic:equal>
										<logic:notEqual value="12872" name="xxdm">
										<button type="button" class="button2" onclick="viewMore('del')"
											style="width: 80px">
											删 除
										</button>
										</logic:notEqual>
										
									</logic:notEqual>
									&nbsp;						
							</logic:notPresent>
								<logic:notEqual value="no" name="xydel">
									<%--									<button type="button" class="button2" onclick="Alldel()" style="width: 80px">--%>
									<%--										全部删除--%>
									<%--									</button>--%>

								</logic:notEqual>
							</logic:equal>
							<logic:notEqual value="view_xslxfszsxx" name="tableName">
								<logic:equal value="yes" name="writeAble" scope="request">
									<%--武汉理工大学--%>
									<logic:equal value="10497" name="xxdm">
										<button type="button" class="button2" onclick="impAndChkData()"
											style="width: 80px">
											<!-- impAndChkData(); -->
											导入数据
										</button>
									</logic:equal>
									<%--end武汉理工大学--%>

									<logic:notEqual value="10497" name="xxdm">
										<logic:equal value="12872" name="xxdm">
											<logic:notEqual value="xy" name="userType">
												<button type="button" class="button2" onclick="impAndChkData()"
													style="width: 80px">
													导入数据
												</button>
											</logic:notEqual>
										</logic:equal>
										<logic:notEqual value="12872" name="xxdm">
											<!-- 非投保信息维护 -->
											<logic:notEqual name="tableName" value="view_xsbxxx">
											<button type="button" class="button2" onclick="impAndChkData()"
												style="width: 80px">
												导入数据
											</button>
											</logic:notEqual>
											<!-- 投保信息维护 -->
											<logic:equal name="tableName" value="view_xsbxxx">
												<logic:notEqual name="act" value="insureInfo">
													<button type="button" class="button2" onclick="impAndChkData()"
														style="width: 80px">
														导入数据
													</button>
												</logic:notEqual>
											</logic:equal>
										</logic:notEqual>
										<!-- "impAndChkData();" -->

									</logic:notEqual>									
								
									<logic:equal value="12872" name="xxdm">
										<logic:equal value="xsjxjb" name="realTable">
											<logic:notEqual value="xy" name="userType">
												&nbsp;
									0
											</logic:notEqual>
										</logic:equal>
										<logic:notEqual value="xsjxjb" name="realTable">
										&nbsp;
									<button type="button" class="button2" onclick="dataExport()"
										style="width: 80px">
										导出数据
									</button>
										</logic:notEqual>
										</logic:equal>
										<logic:notEqual value="12872" name="xxdm">
										&nbsp;
											<!-- 非投保信息维护 -->
											<logic:notEqual name="tableName" value="view_xsbxxx">
												<button type="button" class="button2" onclick="dataExport()"
													style="width: 80px">
													导出数据
												</button>
											</logic:notEqual>
											<!-- 投保信息维护 -->
											<logic:equal name="tableName" value="view_xsbxxx">
												<logic:notEqual name="act" value="insureInfo">
													<button type="button" class="button2" onclick="dataExport()"
													style="width: 80px">
													导出数据
													</button>
												</logic:notEqual>
											</logic:equal>
										</logic:notEqual>
								
									
									<!-- 上海工程begin -->
									<logic:equal value="10856" name="xxdm">
										<logic:equal value="wjcfb" name="realTable">
												&nbsp;
												<button type="button" class="button2" onclick="bgdgl()"
												title="进入行政违纪处分不归档增加页面">
												处分不归档管理
											</button>
										</logic:equal>
									</logic:equal>
									<!-- 上海工程end -->
								</logic:equal>
								<!-- 读写权 -->
								<!-- 江苏信息 综合素质测评打印 begin -->
								<logic:present name="showjsxx">
									<logic:equal value="zhszcp" name="realTable">
								&nbsp;
								<button type="button" class="button2" onclick="jsxxzcPrint()"
											style="width: 80px">
											打印
										</button>
									</logic:equal>
								</logic:present>
								<!-- end -->
								<logic:present name="isCSMZ">
									<logic:equal value="yes" name="isCSMZ">
									&nbsp;
							
										
									</logic:equal>
								</logic:present>

								<logic:present name="showhzy">
									<logic:equal value="view_xsrychb" name="tableName">
										&nbsp;
									<button type="button" id="btn_print" class="button2" style=""
											onclick="hzyrychprint();">
											报表打印
										</button>
									</logic:equal>
									<logic:equal value="view_xsjxjb" name="tableName">
									<logic:equal value="yes" name="writeAble" scope="request">
										<logic:equal value="xy" name="userType">
											<logic:equal value="view_xsrychb" name="tableName">
												<button type="button" class="button2"
													onclick="refreshForm('credit_apply.do')"
													style="width: 80px">
													增 加
												</button>
											</logic:equal>
											<logic:notEqual value="view_xsrychb" name="tableName">
												<button type="button" class="button2" onclick="viewMore('add')"
													style="width: 80px">
													增 加
												</button>
											</logic:notEqual>									
											&nbsp;
											<button type="button" class="button2" onclick="hzyjxjmodi()"
												style="width: 80px">
												修 改
										    </button>
<%--											&nbsp;--%>
<%--											<button type="button" class="button2" onclick="viewMore('del')"--%>
<%--												style="width: 80px">--%>
<%--												删 除--%>
<%--											</button>--%>
<%--								&nbsp;										--%>
											<button type="button" class="button2" onclick="dataExport()"
												style="width: 80px">
												导出数据
											</button>
										</logic:equal>
										
									</logic:equal>
									
											&nbsp;
											<button type="button" class="button2" onclick="hzyprint()"
											style="width: 90px" id="buttonQuery">
											打印报表
										</button>
									</logic:equal>
								</logic:present>
								<logic:equal value="10878" name="xxdm">
									<logic:equal value="view_zhszcp" name="tableName">
										<button type="button" class="button2" onclick="impAndChkDataByahjg()"
											style="width: 110px">
											评分数据导入
										</button>
									&nbsp;
									</logic:equal>
								</logic:equal>
								<logic:notPresent name="showjsxx">
									<logic:notEqual value="12872" name="xxdm">
										<%--浙江大学宁波理工<bean:message key="lable.xsgzyxpzxy" />--%>
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
										<logic:notEqual value="13022" name="xxdm">
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
										<button type="button" class="button2" onclick="wjcfDataExport('wjcf_gzdx_wjcfhzb.do')" style="width:70px">处分汇总</button>&nbsp;<button type="button" class="button2" onclick="wjcfDataExport('wjcf_gzdx_wjcfhzbByjwc.do')" style="width:95px">教务处处分汇总</button>
									</logic:equal>
									<!-- 浙江传媒 <bean:message key="lable.xsgzyxpzxy" />处分表汇总 -->
									<logic:equal value="11647" name="xxdm">
										<button type="button" class="button2" onclick="if (confirm('将按学年,学期,<bean:message key="lable.xsgzyxpzxy" />进行处分数据的统计,确认要输出吗?')) {wjcfDataExport('wjcf_zjcm_cfhzb.do?xydm='+document.getElementById('xydm').value)}" style="width:100px">学生处分汇总表</button>
									</logic:equal>
								</logic:equal>
							
							<!-- END -->
						</div>
					</center>
				</div>
			</logic:notEqual>