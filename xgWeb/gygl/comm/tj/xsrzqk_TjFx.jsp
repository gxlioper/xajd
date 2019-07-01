<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- author:lyl -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script language="javascript" src="js/AjaxFunction.js"></script>
		<script type="text/javascript">
			function dataExport(){
			document.forms[0].action = "/xgxt/comm_gygl.do?method=wxsyzy_xsrzqk_Tj";
			document.forms[0].target = "_blank";
			document.forms[0].submit();
			document.forms[0].target = "_self";
		    }
		</script>
	</head>
	<body>
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>公寓管理 - 统计分析 - 学生入住情况统计分析</a>
			</p>
		</div>
		<!-- 标题 end-->
		<html:form action="/comm_gygl" method="post">
			<input type="hidden" name="zyV" id="zyV" />
					<input type="hidden" name="bjV" id="bjV" />
							<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<li>
							<a href="#" 
								onclick="expTab('rsTable','','')"
								class="btn_up">打印统计表</a>
						</li>
						<li>
							<a href="#" 
								onclick="dataExport()"
								class="btn_dc">住宿情况导出</a>
						</li>
					</ul>
				</div>
				<!-- 按钮 end-->	
				<!-- 过滤条件 -->
				<div class="searchtab">
					<table width="100%" border="0">
						<tfoot>
							<tr>
								<td colspan="10">
									<div class="btn">
										<input type="hidden" name="go" value="a" />
										<button class="btn_cx" id="search_go"
											onclick="document.forms[0].go.value='go';refreshForm('/xgxt/stuHouseInfoTjFx.do');this.disabled=true">
											统计
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
						<tbody>
							<!-- 第一行 -->
							<tr>
								<th>
									年级
								</th>
								<td>
									<html:select property="nj"  onchange="initZyList();initBjList()">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select>											
								</td>
								<th>
									<bean:message key="lable.xsgzyxpzxy" />
								</th>
								<td>
									<html:select property="xydm"  styleId="xy"
										onchange="initZyList();initBjList()">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
								</td>
							</tr>
							<!-- 第二行 -->
							<tr>
								<th>
									专业
								</th>
								<td>
									<html:select property="zydm"  styleId="zy"
										onchange="initBjList()">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select>										
								</td>
								<th>
									班级
								</th>
								<td>
									<html:select property="bjdm" styleId="bj">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm"
											labelProperty="bjmc" />
									</html:select>	
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				<!-- 过滤条件 end-->
								<!-- 查询结果-->
				<div class="formbox">
					<h3 class="datetitle_01">
						<span> 查询结果&nbsp;&nbsp; 
							<logic:empty name="rs">
								<font color="red">未找到任何记录！</font>
							</logic:empty>
						</span>
					</h3>
					<logic:notEmpty name="rs">
						<table summary="" class="dateline" align="" width="100%">
						<tr>
							<td align="center">
								<B>
								
								<font size="5">
								
								<script type="text/javascript">
								if($("nj").value!=""){
								   document.write($("nj").value+"年级");
								}
								if($("xydm").value!=""){
								   document.write(document.forms[0].xydm.options[document.forms[0].xydm.selectedIndex].text);
								}
								if($("zydm").value!=""){
								   document.write(document.forms[0].zydm.options[document.forms[0].zydm.selectedIndex].text+"专业");
								}
								if($("bjdm").value!=""){
								   document.write(document.forms[0].bjdm.options[document.forms[0].bjdm.selectedIndex].text+"专业");
								}
								</script>
								<br/>
								学生入住情况统计				
								 </font>
								 </B>
								<div align="right">

								</div>
							</td>
						</tr>
						<tr>
							<td align="center">
								<table width="100%" class="tbstyle" id="rsTable">
									<thead>
										<tr>
											<td  width="15%" align="center">
												<bean:message key="lable.xsgzyxpzxy" />
											</td>
											<td  width="10%" align="center">
												年级
											</td>
											<td  width="20%" align="center">
												专业
											</td>
											<td  width="15%" align="center">
												班级
											</td>
											<td width="5%" align="center">
												总人数
											</td>
											<td width="6%" align="center">
												入住人数
											</td>
											<td width="6%" align="center"><!-- lyl -->
												外住人数
											</td>
											<td width="7%" align="center">
												其他未入住人数
											</td>
											<td width="15%" align="center">
												未入住比例
											</td>
											<td width="15%" align="center">
												入住比例
											</td>
										</tr>
									</thead>
									<logic:iterate name="rs" id="s">
										<tr>
											<td  align="center" rowspan="<bean:write name="s" property="bjcout"/>">
												<bean:write name="s" property="xymc" />
												</td>
												<logic:notEqual value="0" name="s" property="bjcout">
													<logic:iterate id="n" name="s" property="coutinfo">		
																									
														<td >
															<bean:write name="n" property="nj" />
														</td>
														<td >
															<bean:write name="n" property="zymc" />
														</td>
														<td >
															<bean:write name="n" property="bjmc" />
														</td>
														<td >
															<bean:write name="n" property="zcout" />
														</td>
														<td >
															<bean:write name="n" property="yzcout" />
														</td>
														<td >
															<bean:write name="n" property="waizcout" /><!-- lyl -->
														</td>
														<td >
															<bean:write name="n" property="wzcout" />
														</td>
														<td >
															<img src="fdygl/fdygzdc/total.jpg" width="<bean:write name="n" property="wzbl"/>px" height="10px"/>
															<bean:write name="n" property="wzbl" />%
														</td>
														<td >
															<img src="fdygl/fdygzdc/total.jpg" width="<bean:write name="n" property="rzbl"/>px" height="10px"/>
															<bean:write name="n" property="rzbl" />%
														</td>
														<%
														out.print("</tr>");
														%>
													</logic:iterate>
												</logic:notEqual>
										<logic:equal value="0" name="s" property="bjcout">
											<td >
											</td>
											<td>
											</td>
											<td>
											</td>
											<td>
											</td>
											<td>
											</td>
											<td>
											</td>
											<td>
											</td>
											<td>
											</td>
											<td>
											</td>
											<td>
											</td>
										</logic:equal>
											</tr>	
											<tr style="height:30 px" onmouseover=this.style.backgroundColor="#D7E6F0" onmouseout=this.style.backgroundColor="#FFFFFF">
											<td align="center" colspan="4">
											合计
											</td>
											<td>
												<bean:write name="s" property="xyzcout" />
											</td>
											<td>
												<bean:write name="s" property="xyyzcout" />
											</td>
											<td>
											<bean:write name="s" property="xywaizcout" />
											</td>
											<td>
												<bean:write name="s" property="xywzcout" />
											</td>
											<td align="center">
												<img src="fdygl/fdygzdc/total.jpg" width="<bean:write name="s" property="zwzbl"/>px" height="10px"/>
												<bean:write name="s" property="zwzbl" />%
											</td>
											<td align="center">
												<img src="fdygl/fdygzdc/total.jpg" width="<bean:write name="s" property="zrzbl"/>px" height="10px"/>
												<bean:write name="s" property="zrzbl" />%
											</td>
											</tr>
									</logic:iterate>									
								</table>								
							</td>
						</tr>			
					</table>	
					</logic:notEmpty>
				</div>
				<!-- 查询结果 end-->
			</div>	
		</html:form>
	</body>
</html>
