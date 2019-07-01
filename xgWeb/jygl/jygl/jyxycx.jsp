<%@ page language="java" contentType="text/html; charset=GBK"%>
<jsp:directive.page import="java.util.List" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="pjpy/xibeierminyuan/js/xbemyJs.js"></script>
		<script type="text/javascript" src="pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
		<script type="text/javascript" src="pjpy/czxx/czxx.js"></script>
		<script type="text/javascript" src="js/String.js"></script>
		<script type="text/javascript" src="js/jygl/jygl.js"></script>
	</head>
	<!-- 头文件 -->
	<body onload="xyDisabled('xy');hiddenTr($('moreTerm'));purviewControl();">

		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/jygl" method="post">
			<input type="hidden" name="isFdy" id="isFdy" value="${isFdy }" />
			<input type="hidden" id="userType" name="userType"
				value="${userType }" />
			<input type="hidden" id="userName" name="userName"
				value="${userName }" />
			<input type="hidden" id="tableName" name="tableName"
				value="${ tableName}" />
			<input type="hidden" id="realTable" name="realTable"
				value="${realTable }" />
			<input type="hidden" name="message" id="message" value="${message }" />
			<input type="hidden" name="xyV" value="" />
			<input type="hidden" name="zyV" value="" />
			<input type="hidden" name="bjV" value="" />
			<input type="hidden" id="cbVal" name="cbVal" value="" />

			<input type="hidden" name="isComm" value="true"/> 
			<!-- 大师的权限用的 -->
			<logic:present name="purview">
				<input type="hidden" id="purview" name="purview" value="${ purview }" /> 
				<input type="hidden" id=" operateBound " name="operateBound" value="${ operateBound }" />
			</logic:present>

			<div class="toolbox">
				<!-- 按钮 -->
				<logic:equal value="yes" name="writeAble">
					<div class="buttonbox">
						<ul>
							<li>
								<a href="#" 
									id="btn_zj"
									onclick="document.location='/xgxt/jygl_jyxywh.do'"
									class="btn_zj"> 增加 </a>
							</li>
							<li>
								<a href="#"
									id="btn_xg"
									onclick="showUpdateWindow('primarykey_cbv','/xgxt/jygl.do?method=jyxyShow&doType=update','900','600')"
									class="btn_xg"> 修改 </a>
							</li>
							<li>
								<a href="#"
									id="btn_sc"
									onclick="deletedata('/xgxt/jygl.do?method=jyxycx&doType=del');"
									class="btn_sc"> 删除 </a>
							</li>
							<li>
								<a href="#" onclick="impAndChkData()" class="btn_dr" id="btn_dr"> 导入 </a>
							</li>
							
							<li>
								<a href="#" class="btn_dc" onclick="configureExportData();return false;" id="btn_dc">导出数据</a>
							</li>
							<li>
								<a href="#" class="btn_qx" onclick="choiceFields();return false;" id="btn_qx">导出字段确认</a>
							</li>
							
							<!--<li>
								<a href="#"
									onclick="expData('/xgxt/jygl.do?method=jyxycx&doType=expData')"
									class="btn_dc"> 导出 </a>
							</li>
						--></ul>
					</div>
				</logic:equal>
				<div class="searchtab">
					<table width="100%" border="0" id="searchTab">
						<tfoot>
							<tr>
								<td colspan="6">
									<div class="bz">
										<label>
											<html:checkbox onclick="hiddenTr(this)" property="moreTerm"
												styleId="moreTerm" />
											<strong>更多条件</strong>
										</label>
									</div>
									<div class="btn">
										<button class="btn_cx" id="search_go"
											onclick="allNotEmpThenGo('/xgxt/jygl.do?method=jyxycx&doType=query')">
											查 询
										</button>
										<button class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
											重 置
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
						<tbody>
							<tr>
								<th>
									年级
								</th>
								<td>
									<html:select property="nj" style="width:180px"
										onchange="initZyList();initBjList()" styleId="rxnf">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select>
								</td>
								<th>
									学号
								</th>
								<td>
									<html:text property="xh" maxlength="20"
										style="width:175px"></html:text>
								</td>
								<th>
									姓名
								</th>
								<td>
									<html:text property="xm" maxlength="20"
										style="width:175px"></html:text>
								</td>
							</tr>
							<tr>
								<th>
									<bean:message key="lable.xsgzyxpzxy" />
								</th>
								<td>
									<html:select property="xydm"
										onchange="initZyList();initBjList()" styleId="xy"
										style="width:180px">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
								</td>
								<th>
									专业
								</th>
								<td>
									<html:select property="zydm"
										onchange="initBjList()" styleId="zy" style="width:180px">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select>
								</td>
								<th>
									班级
								</th>
								<td>
									<html:select property="bjdm" styleId="bj"
										style="width:180px">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm"
											labelProperty="bjmc" />
									</html:select>
								</td>
							</tr>
							<tr style="display:none">
								<th>
									就业标志
								</th>
								<td>
									<html:select property="jybz" style="width:180px">
										<html:options collection="jybzList" property="dm"
											labelProperty="mc" />
									</html:select>
								</td>
								<th>
									单位性质
								</th>
								<td>
									<html:select property="dwxz" style="width:180px">
										<html:options collection="dwxzList" property="dm"
											labelProperty="mc" />
									</html:select>
								</td>
								<th>
									是否对口
								</th>
								<td>
									<html:select property="sfdk" style="width:180px">
										<html:option value="">----请选择----</html:option>
										<html:options collection="isNot" property="en"
											labelProperty="cn" />
									</html:select>
								</td>
							</tr>
							<tr style="display:none">
								<th>
									就业行业
								</th>
								<td>
									<logic:equal value="10388" name="xxdm" scope="session">
										<html:select property="hyfldm" style="width:180px">
											<html:options collection="hyList" property="dm"
												labelProperty="mc" />
										</html:select>
									</logic:equal>
									<logic:notEqual value="10388" name="xxdm" scope="session">
										<html:select property="jyhy" style="width:180px">
											<html:options collection="hyList" property="dm"
												labelProperty="mc" />
										</html:select>
									</logic:notEqual>
								</td>


								<th>
									<logic:equal value="10388" name="xxdm" scope="session">
											辅导员审核
									</logic:equal>
									<logic:notEqual value="10388" name="xxdm" scope="session">
										<bean:message key="lable.xsgzyxpzxy" />审核
									</logic:notEqual>
								</th>
								<td>
									<html:select property="xysh" style="width:180px">
										<html:option value="">----请选择----</html:option>
										<html:options collection="shztList" property="en"
											labelProperty="cn" />
									</html:select>
								</td>

								<th>
									学校审核
								</th>
								<td>
									<html:select property="xxsh" style="width:180px">
										<html:option value="">----请选择----</html:option>
										<html:options collection="shztList" property="en"
											labelProperty="cn" />
									</html:select>
								</td>
							</tr>
							
							<tr style="display:none">
								<th><bean:message key="lable.xsgzyxpzxy" />审核人</th>
								<td><html:text property="xyshr" style="width:175px"></html:text></td>
								<th>学校审核人</th>
								<td><html:text property="xxshr" style="width:175px"></html:text></td>
								
								<logic:notEqual value="10388" name="xxdm" scope="session">
									<th>
										届
									</th>
									<td>
										<html:select property="je" styleId="je"
											style="width:180px">
											<html:option value=""></html:option>
											<html:options collection="nfList" property="en"
												labelProperty="cn" />
										</html:select>
									</td>
								</logic:notEqual>
								<logic:equal value="10388" name="xxdm" scope="session">
									<th>
										毕业年份
									</th>
									<td>
										<html:select property="bynf" styleId="bynf"
											style="width:180px">
											<html:option value=""></html:option>
											<html:option value="未毕业">未毕业</html:option>
											<html:options collection="nfList" property="en"
												labelProperty="cn" />
										</html:select>
									</td>
								</logic:equal>
							</tr>
						</tbody>
					</table>
				</div>

				<div class="formbox">
					<h3 class="datetitle_01">
						<span> 查询结果&nbsp;&nbsp; <logic:notEmpty name="rs">
								<font color="blue">提示：单击表头可以排序</font>
							</logic:notEmpty> </span>
					</h3>

					<div class="con_overlfow">
						<table summary="" class="dateline tablenowrap" align=""
							width="100%">
							<thead>
								<tr>
									<td>
										<input type="checkbox" disabled="true" />
									</td>
									<logic:iterate id="tit" name="topTr" offset="2" scope="request">
										<td onclick="tableSort(this)">
											${tit}
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<tbody>
								<logic:notEmpty name="rs">
									<logic:iterate name="rs" id="s">
										<tr>
											<td>
												<input type="checkbox" id="cbv" name="primarykey_cbv"
													<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>
													value="<logic:iterate id="v" name="s" offset="1" length="1"><bean:write name="v"/></logic:iterate>" />
											</td>
											<td>
												<a
													href="javascript:showTopWin('/xgxt/jygl.do?method=jyxyShow&doType=view&pkValue=<logic:iterate id="v" name="s" offset="1" length="1">${v }</logic:iterate>','900','600');"
													class="pointer" style="color:#0A63BF"> <logic:iterate
														id="v" name="s" offset="2" length="1">${v }</logic:iterate>
												</a>
											</td>

											<logic:iterate id="v" name="s" offset="3">
												<td>
													${v }
												</td>
											</logic:iterate>
										</tr>
									</logic:iterate>

									<%
												for (int i = 0; i < (Integer) request
												.getAttribute("maxNum")
												- ((List) request.getAttribute("rs")).size(); i++) {
									%>
									<tr>
										<td>
											<input type="checkbox" disabled="disabled" />
										</td>
										<logic:iterate id="t" name="topTr" offset="2" scope="request">
											<td>
												&nbsp;
											</td>
										</logic:iterate>
									</tr>
									<%
									}
									%>
								</logic:notEmpty>

								<logic:empty name="rs">
									<%
												for (int i = 0; i < (Integer) request
												.getAttribute("maxNum"); i++) {
									%>
									<tr>
										<td>
											<input type="checkbox" disabled="disabled" />
										</td>
										<logic:iterate id="t" name="topTr" offset="2" scope="request">
											<td>
												&nbsp;
											</td>
										</logic:iterate>
									</tr>
									<%
									}
									%>
								</logic:empty>
							</tbody>
						</table>
					</div>
					<!--分页显示-->
					<jsp:include flush="true"
						page="/sjcz/turnpage.jsp?form=jyglActionForm"></jsp:include>
					<!--分页显示-->
				</div>
		</html:form>
		<logic:present name="result">
			<logic:equal value="true" name="result">
				<script language="javascript">
	         	alert("操作成功！");
	         	document.getElementById('search_go').click();
	         	</script>
			</logic:equal>
			<logic:equal value="false" name="result">
				<script>
	       	 	 alert("" + $('message').value);
				document.getElementById('search_go').click();
	         	</script>
			</logic:equal>
		</logic:present>
		 <%@ include file="/comm/other/tsxx.jsp"%>
	</body>
</html>
