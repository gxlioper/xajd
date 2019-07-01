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
		<title><bean:message key="lable.title" /></title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control" http-equiv="no-cache" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="正方软件 zfsoft" />
	</head>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	<script language="javascript" src="js/AjaxFunction.js"></script>
	<script language="javascript" src="js/dtjs/dtjsFunction.js"></script>
	<script language="javascript">
	
	</script>
	<body onload="xyDisabled('xy');">
		<html:form action="/gzdxJxgl" method="post">
			<%@ include file="/jxgl/hiddenValue.jsp"%>
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
			</div>

			<logic:notEmpty name="msg">
				<div align="center">
					<FONT color="red" size="6"><bean:write name="msg" /></FONT>
				</div>
			</logic:notEmpty>
			<logic:empty name="msg">
				<div class="toolbox">
					 <!-- 按钮 -->
					 <logic:equal value="yes" name="writeAble">
					 <div class="buttonbox">
					 	<ul>
						<li> <a href="#" onclick="wjcfDataExport('gzdxJxgl.do?method=cjckManage&doType=exp')" class="btn_dc"> 导出 </a> </li>
					    </ul>
					 </div>
					</logic:equal>
					<div class="searchtab">
					<table width="100%" border="0">
					      <tfoot>
					        <tr>
					          <td colspan="6">
					            <div class="btn">	
					            	<input type="hidden" name="go" value="a" />
					              <button type="button" class="btn_cx" id="search_go" 
					              	onclick="allNotEmpThenGo('/xgxt/gzdxJxgl.do?method=cjckManage');">
					              	查 询
					              </button>
					              &nbsp;&nbsp;&nbsp;&nbsp;
					              <button type="button" class="btn_cz" id="btn_cz"  	onclick="searchReset();return false;">
					              	重 置
					              </button>
					            </div>
					          </td>
					        </tr>
					      </tfoot>
							<tbody>
								<tr>
									<th>年级：</th>
										<td>
										<html:select property="queryequals_nj" style="" onchange="initZyList();initBjList()">
											<html:option value=""></html:option>
											<html:options collection="njList" property="nj" labelProperty="nj" />
										</html:select>
										</td>
										<th>学年：</th>
										<td>
										<html:select property="queryequals_xn" style="" onchange="">
											<html:option value=""></html:option>
											<html:options collection="xnList" property="xn" labelProperty="xn" />
										</html:select>
										</td>
										<th>
										学号：
										</th>
										<td>
										<html:text property="querylike_xh" style="" maxlength="20"/>
										</td>
								</tr>
								<tr>
										<th>
										<bean:message key="lable.xsgzyxpzxy" />：
										</th>
										<td>
										<html:select property="queryequals_xydm" style="width:150px" styleId="xy" onchange="initZyList();initBjList();">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm"
												labelProperty="xymc" />
										</html:select>
										</td>
										<th>
										专业：
										</th>
										<td>
										<html:select property="queryequals_zydm"  style="width:150px"  styleId="zy" onchange="initBjList()">
											<html:option value=""></html:option>
											<html:options collection="zyList" property="zydm"
												labelProperty="zymc" />
										</html:select>
										</td>
										<th>
										班级：
										</th>
										<td>
										<html:select property="queryequals_bjdm"  style="width:150px"  styleId="bj">
											<html:option value=""></html:option>
											<html:options collection="bjList" property="bjdm"
												labelProperty="bjmc" />
										</html:select>
									</td>
								</tr>
								<tr>
									<th>
									姓名：
									</th>
									<td>
										<html:text property="querylike_xm" style="" maxlength="20"/>
									</td>
									<th>
										性别：
									</th>
									<td>
										<html:select property="queryequals_xb" style="" onchange="">
											<html:option value=""></html:option>
											<html:options collection="xbList" property="en" labelProperty="cn" />
										</html:select>
									</td>
									<logic:equal name="xxdm" value="11355">
									<th>
									身份证号：
									</th>
									<td>
									<html:text property="querylike_sfzh" style="" maxlength="20"/>
									</td>
									</logic:equal>
									<logic:notEqual name="xxdm" value="11355">
									<td>
									</td>
									<td>
									</td>
									</logic:notEqual>
								</tr>
							</tbody>
						</table>
						</div>
					</div>
					<div class="formbox">
			  	  <h3 class="datetitle_01">
				    <span>
				    	查询结果&nbsp;&nbsp;
				    	<logic:empty name="rs">
							<font color="red">未找到任何记录！</font> 
				 		 </logic:empty>
				 		 <logic:notEmpty name="rs">
				 		 	记录数：
								<bean:write name="rsNum" />
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<font color="blue">提示：单击表头可以排序.</font>
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
								<logic:iterate name="rs" id="s" indexId="index">
									<tr onclick="rowOnClick(this);" style="cursor:hand" ondblclick="">
										<logic:iterate id="v" name="s" offset="1" length="1">
											<td align="left">
												<bean:write name="v" />
												<input type="hidden" id="cbv" name="primarykey_xh" style=""
													value="<logic:iterate id="v" name="s" offset="2" length="1"><bean:write name="v"/></logic:iterate>"/>
											</td>
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="2">
											<td align="left">
												<bean:write name="v" />
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
								</tbody>
							</table>
							<jsp:include flush="true"
								page="/sjcz/turnpage.jsp?form=jxglTyForm"></jsp:include>
							<script type="text/javascript">
								$('choose').className="hide";
							</script>
					</logic:notEmpty>
					</div>
					<div id="tmpdiv1"></div>
			</logic:empty>
		</html:form>
			<logic:notEmpty name="result">
				<logic:equal name="result" value="true">
				<script>
					if($("message") && $("message").value != ""){
						alert($("message").value);
						$("message").value = "";
						$("doType").value = "";
					}
				</script>
				</logic:equal>
			</logic:notEmpty>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>
