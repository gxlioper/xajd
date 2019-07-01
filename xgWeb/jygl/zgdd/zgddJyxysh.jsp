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
		<script type="text/javascript" src="js/jygl/jygl.js"></script>
		<script type="text/javascript">
			function plsh(){
				viewTempDiv("就业协议审核","shdiv",380,200);
			}
		</script>
	</head>
	<body onload="xyDisabled('xy');hiddenTr($('moreTerm'))">

		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>${title }</a>
			</p>
		</div>


		<html:form action="/jygl" method="post">
			<input type="hidden" id="userName" name="userName" value="${userName }" />
			<input type="hidden" id="userType" name="userType" value="${userType }" />
			<input type="hidden" id="tableName" name="tableName" value="${ tableName}" />
			<input type="hidden" id="realTable" name="realTable" value="${realTable }" />
			<input type="hidden" name="message" id="message" value="${message }" />
			<input type="hidden" name="xyV" value="" />
			<input type="hidden" name="zyV" value="" />
			<input type="hidden" name="bjV" value="" />
			<input type="hidden" id="cbVal" name="cbVal" value="" />
			<input type="hidden" name="isFdy" value="${isFdy }" />

			<div class="toolbox">
				<!-- 按钮 -->
				<logic:equal value="yes" name="writeAble">
					<div class="buttonbox">
						<ul>
							<li>
								<a href="#"
									onclick="showAuditingWindow('primarykey_cbv','/xgxt/jygl.do?method=zgddJyxyShow&doType=sh',900,600);"
									class="btn_sh">审核 </a>
							</li>
							<li>
								<a href="#"
									onclick="if(isChecked('primarykey_cbv') && confirm('您确定要取消审核所勾选的记录吗？')){refreshForm('/xgxt/jygl.do?method=zgddJyxysh&doType=qxsh')}"
									class="btn_qxsh">取消审核 </a>
							</li>
							<!-- 
							<li>
								<a href="#"
									onclick="if($('nj').value==''){if (confirm('请选择入学年级,若不选默认为当前年度'))expData('/xgxt/jygl.do?method=jyltj')}else expData('/xgxt/jygl.do?method=jyltj')"
									class="btn_tj">就业率统计</a>
							</li>
							 -->
						</ul>
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
											onclick="allNotEmpThenGo('/xgxt/jygl.do?method=zgddJyxysh&doType=query')">
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
									<html:select property="queryequals_nj" style="width:180px"
										onchange="initZyList();initBjList()" styleId="nj">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select>
								</td>
								<th>
									学号
								</th>
								<td>
									<html:text property="querylike_xh" maxlength="20"
										style="width:175px"></html:text>
								</td>
								<th>
									姓名
								</th>
								<td>
									<html:text property="querylike_xm" maxlength="20"
										style="width:175px"></html:text>
								</td>
							</tr>
							<tr>
								<th>
									<bean:message key="lable.xsgzyxpzxy" />
								</th>
								<td>
									<html:select property="queryequals_xydm"
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
									<html:select property="queryequals_zydm"
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
									<html:select property="queryequals_bjdm" styleId="bj"
										style="width:180px">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm"
											labelProperty="bjmc" />
									</html:select>
								</td>
							</tr>
							<tr style="display:none">
								<th>
									就业情况
								</th>
								<td>
									<html:select property="queryequals_jybz" style="width:180px">
										<html:options collection="jyqkList" property="dm"
											labelProperty="mc" />
									</html:select>
								</td>
								<th>
									单位性质
								</th>
								<td>
									<html:select property="queryequals_dwxz" style="width:180px">
										<html:options collection="dwxzList" property="dm"
											labelProperty="mc" />
									</html:select>
								</td>
								<th>
									专业是
									<br />
									否对口
								</th>
								<td>
									<html:select property="queryequals_sfdk" style="width:180px">
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
									<html:select property="queryequals_jyhy" style="width:180px">
										<html:options collection="hyList" property="dm"
											labelProperty="mc" />
									</html:select>
								</td>
								<th>
									<bean:message key="lable.xsgzyxpzxy" />审核
								</th>
								<td>
									<html:select property="queryequals_xysh" style="width:180px">
										<html:option value="">----请选择----</html:option>
										<html:options collection="shztList" property="en"
											labelProperty="cn" />
									</html:select>
								</td>
								<th>
									学校审核
								</th>
								<td>
									<html:select property="queryequals_xxsh" style="width:180px">
										<html:option value="">----请选择----</html:option>
										<html:options collection="shztList" property="en"
											labelProperty="cn" />
									</html:select>
								</td>
							</tr>
							<tr style="display:none">
								<th><bean:message key="lable.xsgzyxpzxy" />审核人</th>
								<td><html:text property="querylike_xyshr" style="width:175px"></html:text></td>
								<th>学校审核人</th>
								<td><html:text property="querylike_xxshr" style="width:175px"></html:text></td>
								<th></th>
								<td></td>
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
								<tr align="center" style="cursor:hand">
									<td>
										<input type="checkbox" name="cb" disabled="disabled" />
									</td>
									<logic:iterate id="tit" name="topTr" offset="3" scope="request">
										<td onclick="tableSort(this)" nowrap>
											${tit}
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<tbody>
								<logic:notEmpty name="rs">
									<logic:iterate name="rs" id="s">
										<tr onclick="rowOnClick(this)"
											style="cursor:hand;">
											<td>
												<input type="checkbox" id="cbv" name="primarykey_cbv"
													<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate>
													alt="<logic:iterate id="v" name="s" offset="1" length="1">${v }</logic:iterate>"
													value="<logic:iterate id="v" name="s" offset="2" length="1">${v }</logic:iterate>" />

											</td>

											<td>
												<a
													href="javascript:showTopWin('/xgxt/jygl.do?method=zgddJyxyShow&doType=view&pkValue=<logic:iterate id="v" name="s" offset="2" length="1">${v }</logic:iterate>','900','600');"
													class="pointer" style="color:#0A63BF"> <logic:iterate
														id="v" name="s" offset="3" length="1">${v }</logic:iterate>
												</a>
											</td>

											<logic:iterate id="v" name="s" offset="4">
												<td>
													<bean:write name="v" />
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
										<logic:iterate id="t" name="topTr" offset="3" scope="request">
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
										<logic:iterate id="t" name="topTr" offset="3" scope="request">
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

				<div class="open_win01" style="display:none;" id="shdiv">
					<table width='80%' class='formlist'>
						<tbody>
							<tr>
								<th>
									审核意见
									<br />
									<font color="red"><限500字>
									</font>
								</th>
								<td colspan="3">
									<html:textarea property="save_shyj" onblur="checkLen(this,500)"
										style="width:90%" rows="5"></html:textarea>
								</td>
							</tr>
							<tr>
								<th>
									审核人
								</th>
								<td>
									${userNameReal }
								</td>
								<th>
									审核时间
								</th>
								<td>
									${now }
								</td>
							</tr>
						<tbody>
						<tfoot>
							<tr>
								<td colspan='4'>
									<div class='btn'>
										<button
											onclick="if (confirm('您确定要审核所勾选的记录吗？')){refreshForm('/xgxt/jygl.do?method=zgddJyxysh&shzt=通过&doType=sh')}">
											通过
										</button>
										<button
											onclick="if (confirm('您确定要审核所勾选的记录吗？')){refreshForm('/xgxt/jygl.do?method=zgddJyxysh&shzt=不通过&doType=sh')}">
											不通过
										</button>
										<button
											onclick="if (confirm('您确定要退回所勾选的记录吗？')){refreshForm('/xgxt/jygl.do?method=zgddJyxysh&shzt=退回&doType=sh')}">
											退回
										</button>
										<button onclick="hiddenMessage(true,true);return false;">
											关闭
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
		</html:form>
		<logic:present name="message">
			<script defer="defer">
	         	alert("${message}");
	         </script>
		</logic:present>
	</body>
</html>
