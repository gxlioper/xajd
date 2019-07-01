<%@ page language="java" contentType="text/html; charset=GBK"%>
<jsp:directive.page import="java.util.*" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/commit.js"></script>
	</head>
	<body onload="xyDisabled('xy');">

		<html:form action="/stu_info_query" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
			</div>
			<input type="hidden" id="isFdy"  name="isFdy"  value="${isFdy }" />
			<input type="hidden" id="isBzr"  name="isBzr"  value="${isBzr }" />
			<input type="hidden" id="userName"  name="userName"  value="${userName }" />
			<input type="hidden" id="userType"  name="userType"  value="${userType }" />
			<input type="hidden" id="tableName" name="tableName" value="view_bks_xjydxx" />
			<input type="hidden" id="realTable" name="realTable" value="bks_xjydxx" />

			<input type="hidden" name="xyV" value=""/>
			<input type="hidden" name="zyV" value=""/>
			<input type="hidden" name="bjV" value=""/>
		
			<div class="toolbox">
				<div class="searchtab">
					<table width="100%" border="0" id="searchTab">
						
						<tbody>
							<tr>
								<th>学年</th>
								<td>
									<html:select property="xn" styleId="xn" style="width:180px" value="${xn }">
										<html:options collection="xnList" property="xn" 
											labelProperty="xn" />
									</html:select>
								</td>
								<th>年级</th>
								<td>
									<html:select property="nj" styleId="nj" style="width:180px" onchange="initZyList();initBj();">>
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select>
								</td>
								<th>
									是否缴清
								</th>
								<td>
									<html:select property="sfjq" styleId="sfjq" style="width:180px" >
										<html:option value=""></html:option>
										<html:option value="已缴清">已缴清</html:option>
										<html:option value="未缴清">未缴清</html:option>
									</html:select>
								</td>
							</tr>
							<tr>
								<th><bean:message key="lable.xsgzyxpzxy" /></th>
								<td>
									<html:select property="xy"
										onchange="initZyList();initBjList();" styleId="xy"
										style="width:180px">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
								</td>
								<th>专业名称</th>
								<td>
									<html:select property="zy"
										onchange="initBjList();" styleId="zy" style="width:180px">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select>
								</td>
								<th>班级名称</th>
								<td>
									<html:select property="bj" styleId="bj"
										style="width:180px">
										<html:option value=""></html:option>
										<logic:present name="bjList">
											<html:options collection="bjList" property="bjdm"
												labelProperty="bjmc" />
										</logic:present>
									</html:select>
								</td>
								</tr>
								<tr>
								<th>
									学号
								</th>
								<td>
									<logic:equal value="stu" name="userType">
										<html:text property="xh" style="width:180px" value="${userName }" readonly="true"></html:text>
									</logic:equal>
									<logic:notEqual value="stu" name="userType">
										<html:text property="xh" maxlength="20" style="width:170px"></html:text>
									</logic:notEqual>
								</td>
								<th>姓名</th>
								<td>
									<html:text property="xm" maxlength="20" style="width:170px"></html:text>
								</td>
								
								<th></th>
								<td>
									<div class="btn">
										<button type="button" class="btn_cx" id="search_go"
											onclick="allNotEmpThenGo('stu_info_query.do?method=zsfcx&doType=query')">
											查 询
										</button>
										<button type="button" onclick="searchReset();return false;">
											重 置
										</button>
									</div>
								</td>
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
								<td>
									<input type="checkbox" disabled="disabled" />
								</td>
								<logic:iterate id="tit" name="topTr" offset="1">
										<td id="${tit.en}" onclick="tableSort(this)">
											${tit.cn}
										</td>
									</logic:iterate>
							</tr>
						</thead>
						<tbody>
							<logic:notEmpty name="rs">
								<logic:iterate name="rs" id="s">
									<tr>
										<td>
											<input type="checkbox" id="cbv" name="cbv"
												alt="<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate>"
												value="<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate>"/>
											</td>
										<logic:iterate id="v" name="s" offset="1">
											<td>
												${v}
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>

								<%
											for (int i = 0; i < (Integer) request.getAttribute("maxNum")
											- ((List) request.getAttribute("rs")).size(); i++) {
								%>
								<tr>
									<td>
										<input type="checkbox" disabled="disabled" />
									</td>
									<logic:iterate id="t" name="topTr" offset="1" scope="request">
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
									<logic:iterate id="t" name="topTr" offset="1" scope="request">
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
					page="/sjcz/turnpage.jsp?form=commanForm"></jsp:include>
				<!--分页显示-->
			</div>
			
		</html:form>
		<logic:present name="message">
			<script language="javascript">
         		alert("${message}");
         	</script>
		</logic:present>
	</body>
</html>
