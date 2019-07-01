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
	<body onload="xyDisabled('xy');">
		<script language="javascript" src="js/function.js"></script>
		 <html:form action="/csmz_sztz.do?method=tzxm_tj" method="post">
			<input type="hidden" name="userType" id="userType" 
		        value="<bean:write name="userType" scope="request"/>">			
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>${tips }</a>
				</p>
			</div>
			<div class="toolbox">
				 <!-- 按钮 -->
				 <div class="buttonbox">
				    <ul>
					<li> <a href="#" onclick="if($('xn').value==''||$('xq').value==''){alert('学年、学期不能为空!');return false}else{showTopWin('/xgxt/csmz_sztz.do?method=tzxm_tj&print=do&xydm='+$('xydm').value+'&xn='+$('xn').value+'&xq='+$('xq').value,'800','600')}" class="btn_dy"> 报表打印 </a> </li>
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
				              	onclick="if($('xn').value==''||$('xq').value==''){alert('学年、学期不能为空!');return false}else if($('xy').value=='' ){alert('部门不能为空！');return false;}else{$('go').value='go';refreshForm('/xgxt/csmz_sztz.do?method=tzxm_tj')}">
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
								<tr>
									<th align="left">
										学年
									</th>
									<td>
										<html:select property="xn" style="width:100px" styleId="xn">
										<html:options collection="xnList" property="xn"
												labelProperty="xn" />
										</html:select>
									</td>
									<th>
										学期
									</th>
									<td>
										<html:select property="xq" style="width:90px" styleId="xq">
											<html:option value=""></html:option>
											<html:options collection="xqList" property="xqdm"
												labelProperty="xqmc" />
										</html:select>
									</td>
									<th>
										部门
									</th>
									<td>
									   <html:select property="xydm" styleId="xydm" styleId="xy"
										style="background-color:#FFFFFF;">										
										<html:options collection="bmList" property="bmdm"
											labelProperty="bmmc"></html:options>
									   </html:select>
									 </td>
								</tr>	
								<tr>	
									<th>
										项目级别
									</th>
									<td>
										<html:select property="xmjb" styleId="xmjb"
											style="background-color:#FFFFFF;">
											<html:options collection="xmjbList" property="en"
												labelProperty="cn"></html:options>
										</html:select>
									</td>	
									<td>
									</td>
									<td>
									</td>
									<td>
									</td>
									<td>
									</td>
								</tr>																										
						</tbody>
					</table>
					</div>
				</div>
				<h3 class="datetitle_01">
			    <span>
			    	查询结果&nbsp;&nbsp;
			    	<logic:empty name="rs">
						<font color="red">未找到任何记录！</font> 
			 		 </logic:empty>
			 		 <logic:notEmpty name="rs">
			 			 <font color="blue">提示：单击表头可以排序;</font>
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
										<tr onclick="rowOnClick(this);"
											style="cursor:hand;">
											<td>
												<logic:iterate id="v" name="s" offset="0" length="1">
													<input type="hidden" value="<bean:write name="v"/>" />
												</logic:iterate>
												<logic:iterate id="v" name="s" offset="1" length="1">
													<bean:write name="v" />
												</logic:iterate>
											</td>
											<logic:iterate id="v" name="s" offset="2">
												<td nowrap>
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
	<script type="text/javascript" src="js/bottomButton.js"></script>		
    </body>
</html>	
