<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/commit.js"></script>
	</head>

	<body onload="xyDisabled('xy');">
		<html:form action="/xxcj" method="post">
			<input type="hidden" name="isComm" value="true"/>
			<input type="hidden" name="tableName" id="tableName" value="${tableName }"/>
			<input type="hidden" name="realTable" id="realTable" value="${realTable }"/>
			
			<input type="hidden" name="isFdy"    id="isFdy"    value="${isFdy }" />
			<input type="hidden" name="userType" id="userType" value="${userType }" />
			<input type="hidden" name="userName" id="userName" value="${userName }" />
			
			<input type="hidden" name="xyV" value="" />
			<input type="hidden" name="zyV" value="" />
			<input type="hidden" name="bjV" value="" />
		
			<logic:equal value="xy" name="userType">
				<logic:equal value="false" name="isFdy">
					<input type="hidden" name="queryequals_xydm" id="userDep" value="${userDep }" />
				</logic:equal>
			</logic:equal>
		
		
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
			</div>
			<div class="toolbox">
				<!-- 按钮 -->
				<logic:equal value="yes" name="writeAble">
					<div class="buttonbox">
						<ul>
							<li>
								<a href='xxcj.do?method=xstx&doType=add' class="btn_zj"
									id="btn_zj">增加</a>
							</li>
							<li>
								<a href="#" class="btn_xg"
									onclick="showUpdateWindow('primarykey_cbv','xxcj.do?method=xxcjUpdate',800,600,true);"
									id="btn_qx">修改</a>
							</li>
							<li>
								<a href="#" class="btn_sc"
									onclick="batchData('primarykey_cbv','xxcj.do?method=xxcjDel','del');"
									id="btn_qx">删除</a>
							</li>
							
							<li>
								<a href="#" class="btn_dr"
									onclick="impAndChkData()"
									id="btn_dr">导入</a>
							</li>
							<li>
								<a href="#" class="btn_dc"
									onclick="configureExportData();return false;">导出</a>
							</li>
						</ul>
					</div>
				</logic:equal>
				<!-- 过滤条件 -->
				<div class="searchtab">
					<table width="100%" border="0" id="searchTab">
						<tfoot>
							<tr>
								<td colspan="6">
									<div class="btn">
										<button type="button" class="btn_cx" id="search_go"
											onclick="allNotEmpThenGo('xxcj.do?method=xxcjcx&doType=query')">
											查 询
										</button>
										<button type="button" class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
											重 置
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
						<tbody>
							<tr>
								<th>学号</th>
								<td>
									<logic:equal value="stu" name="userType">
										<html:text property="querylike_xh" value="${userName }" style="width:175px" readonly="true"></html:text>
									</logic:equal>
									<logic:notEqual value="stu" name="userType">
										<html:text property="querylike_xh" style="width:175px"></html:text>
									</logic:notEqual>
								</td>
								<th>姓名</th>
								<td>
									<html:text property="querylike_xm" style="width:175px"></html:text>
								</td>
								<th></th>
								<td></td>
							</tr>
							<tr>
								<th>学年</th>
								<td>
									<html:select property="queryequals_xn" style="width:180px">
										<html:options collection="xnList" property="xn" labelProperty="xn"/>
									</html:select>
								</td>
								<th>学期</th>
								<td>
									<html:select property="queryequals_xq" style="width:180px">
										<html:option value=""></html:option>
										<html:options collection="xqList" property="xqdm" labelProperty="xqmc"/>
									</html:select>
								</td>
								<th>年级</th>
								<td>
									<html:select property="queryequals_nj" styleId="nj"
										onchange="initZyList();initBjList()" style="width:180px">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th><bean:message key="lable.xsgzyxpzxy" /></th>
								<td>
									<html:select property="queryequals_xydm"
										onchange="initZyList();initBjList()" styleId="xy"
										style="width:180px">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
								</td>
								<th>专业</th>
								<td>
									<html:select property="queryequals_zydm"
										onchange="initBjList()" styleId="zy" style="width:180px">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select>
								</td>
								<th>班级</th>
								<td>
									<html:select property="queryequals_bjdm" styleId="bj"
										style="width:180px">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm"
											labelProperty="bjmc" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th><bean:message key="lable.xsgzyxpzxy" />审核</th>
								<td>
									<html:select property="queryequals_xysh" style="width:180px">
										<html:option value=""></html:option>
										<html:options collection="shjgList" property="en" labelProperty="cn"/>
									</html:select>
								</td>
								<th>学校审核</th>
								<td>
									<html:select property="queryequals_xxsh" style="width:180px">
										<html:option value=""></html:option>
										<html:options collection="shjgList" property="en" labelProperty="cn"/>
									</html:select>
								</td>
								<th></th>
								<td></td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			
			
			<div class="formbox">
				<h3 class="datetitle_01">
					<span> 查询结果&nbsp;&nbsp; 
						<logic:notEmpty name="rs">
							<font color="blue">提示：单击表头可以排序</font>
						</logic:notEmpty> 
					</span>
				</h3>
			<div class="con_overlfow">
					<table class="dateline tablenowrap" width="100%">
						<thead>
							<tr>
								<td width="17px">
									<input type="checkbox" disabled="disabled" />
								</td>
								<logic:iterate id="tit" name="topTr" offset="0" scope="request">
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
												<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate>
												value="<logic:iterate id="v" name="s" offset="1" length="1">${v }</logic:iterate>"
												/>
										</td>
										<td>
											<a
											href="javascript:showOpenWindow('xxcj.do?method=xxcjUpdate&doType=view&pkValue=<logic:iterate id="v" name="s" offset="1" length="1">${v }</logic:iterate>','900','600');"
											class="pointer" style="color:#0A63BF"> <logic:iterate
												id="v" name="s" offset="2" length="1">${v }</logic:iterate>
											</a>
										</td>
										<logic:iterate id="v" name="s" offset="3" >
											<td>
												${v }
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>

								<%
									for (int i = 0; i < (Integer) request.getAttribute("maxNum")- ((List) request.getAttribute("rs")).size(); i++) {
								%>
								<tr>
									<td>
										<input type="checkbox" disabled="disabled" />
									</td>
									<logic:iterate id="t" name="topTr" offset="0" scope="request">
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
									for (int i = 0; i < (Integer) request.getAttribute("maxNum"); i++) {
								%>
								<tr>
									<td>
										<input type="checkbox" disabled="disabled" />
									</td>
									<logic:iterate id="t" name="topTr" offset="0" scope="request">
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
					page="/sjcz/turnpage.jsp?form=xxcjForm"></jsp:include>
				<!--分页显示-->
			</div>
			
			<logic:present name="message">
				<script defer>
					alert('${message}');
				</script>
			</logic:present>
		</html:form>
	</body>
</html>
