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
<%@ include file="/syscommon/pagehead_V4.ini"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
	<head>
		<title><bean:message key="lable.title" />
		</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control" http-equiv="no-cache" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="正方软件 zfsoft" />
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/sztzFunction.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	<script language="javascript" src="js/AjaxFunction.js"></script>
	<body>
		<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
		%>
		<center>

			<html:form action="/csmz_sztz" method="post">
			   <div class="tab_cur">
					<p class="location">
						<em>您的当前位置:</em><a>素质拓展 - 统计分析 - 学生拓展成果(认证)记录汇总</a>
					</p>
				</div>
					<input type="hidden" name="zyV" id="zyV" />
					<input type="hidden" name="bjV" id="bjV" />
					<%--					<input type="hidden" id="tableName" name="tableName"--%>
					<%--						value="<bean:write name="tableName" scope="request"/>" />--%>
				<div class="toolbox">
					 <!-- 按钮 -->
					 <div class="buttonbox">
					    <ul>
						<li> <a href="#" onclick="hzckLoad()" class="btn_tj"> 个人拓展成果汇总 </a> </li>
					    </ul>
					 </div>
					<div class="searchtab">
							<table width="100%" border="0">
							      <tfoot>
							        <tr>
							          <td colspan="6">
							            <div class="btn">
							              <input type="hidden" name="go" value="a" />
							              <button class="btn_cx" id="search_go" 
							              	onclick="allNotEmpThenGo('/xgxt/csmz_sztz.do?method=tzcgrz_jlhz');">
							              	查 询
							              </button>
							              &nbsp;&nbsp;&nbsp;&nbsp;
							              <button class="btn_cz" id="btn_cz"  	onclick="searchReset();return false;">
							              	重 置
							              </button>
							            </div>
							          </td>
							        </tr>
							      </tfoot>
							<tbody>
							<div style="display: none;">
							<tr> 
								<th>
									学年
								</th>
								<td>
									<html:select property="xn" styleId = "dsxn"
										onchange="">										
										<html:options collection="xnList" property="xn"
											labelProperty="xn" />
									</html:select>
								</td>
								<th>
									学期
								</th>
								<td>
									<html:select  property="xq" styleId = "dsxq" >										
										<html:option value=""></html:option>
										<html:options collection="xqList" property="xqdm"
											labelProperty="xqmc" />
									</html:select>
								</td>
								<td>
								</td>
								<td>
								</td>
							</tr>
							</div>
							<tr>
								<th>
									年级
								</th>
								<td>
									<html:select property="nj" styleId="nj"
										style="width:90px;background-color:#FFFFFF"
										onchange="initZyList();initBjList()">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select>
								</td>
								<th>
									<bean:message key="lable.xsgzyxpzxy" />
								</th>
								<logic:equal name="userType" value="xy">
								<td>
									<html:select property="query_xydm" styleId="xy" value="${userDep}"  disabled="true"
										style="width:180px;background-color:#FFFFFF"
										onchange="initZyList();initBjList()">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
									<html:hidden property="xydm" value="${userDep}"/>
								</td>
								</logic:equal>
								<logic:notEqual name="userType" value="xy">
								<td>
									<html:select property="xydm" styleId="xy" 
										style="width:180px;background-color:#FFFFFF"
										onchange="initZyList();initBjList()">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
								</td>
								</logic:notEqual>
								
								<th>
									专业
								</th>
								<td>
									<html:select property="zydm"
										style="width:180px;background-color:#FFFFFF" styleId="zy"
										onchange="initBjList()">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th align="left" nowrap>
									班级
								</th>
								<td>
									<html:select property="bjdm"
										style="width:120px;background-color:#FFFFFF" styleId="bj">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm"
											labelProperty="bjmc" />
									</html:select>
								</td>
								<th>
									学号
								</th>
								<td>
									<html:text property="xh" />
								</td>
								<th>
									姓名
								</th>
								<td>
									<html:text property="xm" />
								</td>
							</tr>
						</tbody>
					</table>
					</div>
				</div>
				<div class="formbox">
			    <h3 class="datetitle_01">
			    <span>
			    	项目奖项&nbsp;&nbsp;
			    	<logic:empty name="rs">
						<font color="red">未找到任何记录！</font> 
			 		 </logic:empty>
			 		 <logic:notEmpty name="rs">
			 		 记录数：
							<bean:write name="rsNum" />
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<font color="blue">提示：单击一行可以选定；单击表头可以排序</font>	
			 		 </logic:notEmpty>
			    </span>
			    </h3>

				<logic:notEmpty name="rs">
						 <table summary="" class="dateline" align="" width="100%">
							<thead>
								<tr align="center" style="cursor:hand">
									<logic:iterate id="tit" name="topTr" offset="1">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)" nowrap>
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<tbody>
							<logic:iterate name="rs" id="s">
								<tr onclick="rowOnClick(this)" style="cursor:hand">
									<td>
									<logic:iterate id="v" name="s" offset="0" length="1">
									   <input type="hidden" id="pkStr" name="pkStr"
						                     value="<bean:write name="v" />" />						                     
									</logic:iterate>
									<logic:iterate id="v" name="s" offset="1" length="1">
									   <bean:write name="v" />					                     
									</logic:iterate>
									</td>
									<logic:iterate id="v" name="s" offset="2">
										<td align="left">
											<bean:write name="v" />
										</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
							</tbody>
						</table>
				</logic:notEmpty>
				</div>
			</html:form>
		</center>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
<script type="text/javascript">
	  function hzckLoad() {
	   if(curr_row == null ){
			alert("请选要汇总的学生\n单击一行记录即可！");
			return false;
		}
		var dd_html="";
		dd_html += "<table width='300' class='formlist'>";
		dd_html += "<thead>";
		dd_html += "<tr height='30'>";
		dd_html += "<td colspan='2'><span>";
		dd_html += "选择学年学期";
		dd_html += "</span></td>";
		dd_html += "</tr>";
		dd_html += "</thead>";
		dd_html += "<tbody>";

		dd_html += "<tr height='30'>";
		dd_html += "<td align='right'>";
		dd_html += "学年";
		dd_html += "</td>";
		dd_html += "<td align='left'>";
		dd_html += "<select name='xzxn' id ='xzxn' style='width:150px'>" 
		dd_html += $('dsxn').innerHTML;
		dd_html += "</select>";
		dd_html += "</td>";
		dd_html += "</tr>";
		dd_html += "<td align='right'>";
		dd_html += "学年";
		dd_html += "</td>";
		dd_html += "<td align='left'>";
		dd_html += "<select name='xzxq' id ='xzxq' style='width:150px'>" 
		dd_html += $('dsxq').innerHTML;
		dd_html += "</select>";
		dd_html += "</td>";
		dd_html += "</tr>";
		dd_html += "<tr bgcolor='EEF4F9'>";
		dd_html += "<td colspan='2' align='right'>";
		dd_html += "<button class='' onclick='tzcgrzJlhz();closeStuDiv()'>确定</button>";
		dd_html += "&nbsp;&nbsp;&nbsp;&nbsp;";
		dd_html += "<button class='' onclick='closeStuDiv()'>关闭</button>";
		dd_html += "</td>";
		dd_html += "</tr>";
		dd_html += "<tbody>";
		dd_html += "</table>";
		showTempDivForJs("拓展成果",dd_html,300,200);
}

	function closeStuDiv(){
		$('tempDiv').style.display='none';
		i = document.getElementsByTagName("select").length;
	
		for (j = 0; j < i; j++) {
			document.getElementsByTagName("select")[j].style.visibility = "";
			document.getElementsByTagName("select")[j].style.display = "";
		}
	}
	
	  function tzcgrzJlhz(){
	  var url="/xgxt/csmz_sztz.do?method=cgjlhzb";
	  if(curr_row == null ){
			alert("请选要汇总的学生\n单击一行记录即可！");
			return false;
		} else {
<%--	   url+="&xh=";--%>
<%--	   url+=curr_row.cells[0].innerText;--%>
<%--	   url+="&xm=";--%>
<%--	   url+=curr_row.cells[1].innerText;--%>
<%--	   url+="&xb=";--%>
<%--	   url+=curr_row.cells[2].innerText;--%>
<%--	   url+="&nj=";--%>
<%--	   url+=curr_row.cells[3].innerText;--%>
<%--	   url+="&xy=";--%>
<%--	   url+=curr_row.cells[4].innerText;--%>
<%--	   url+="&bj=";--%>
<%--	   url+=curr_row.cells[6].innerText;--%>
<%--	   url+="&xn=";--%>
<%--	   url+=$("xn").value;--%>
<%--	   url+="&xq=";--%>
<%--	   url+=$("xq").value;--%>
	   url +="&pkValue=";
	   url +=(curr_row.cells[0].getElementsByTagName("input"))[0].value;
	   url +="&xzxn=";
	   url +=$("xzxn").value;
	   url +="&xzxq=";
	   url +=$("xzxq").value;
	   document.forms[0].action = url;
	   document.forms[0].target = "_blank";
	   document.forms[0].submit();
	   document.forms[0].target = "_self";		
		}
	}
</script>

</html>
