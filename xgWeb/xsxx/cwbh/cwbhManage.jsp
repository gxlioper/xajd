<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/qtsjFunction.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/commit.js"></script>
		<script type="text/javascript">			
			function modi(url,h,w){
				if(curr_row != null){
					showTopWin(url + '&xh='+curr_row.getElementsByTagName('input')[0].value,h,w);
					return true;
				}else{
					alert('请选择要操作的数据行！');
					return false;
				}
			}
			
			function choiceDisabled(){
				var userStatus = $('userStatus').value;
				if('stu' == userStatus){
					$('xh').disabled = true;
				}else if('xy' == userStatus){
					$('xy').disabled = true;
				}				
			}
		</script>
	</head>
	<body onload="choiceDisabled();">
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>${title } </a>
			</p>
		</div>
		<html:form action="/stuGxh" method="post">
			<input type="hidden" name="xyV" id="xyV" value="" />
			<input type="hidden" name="zyV" id="zyV" value="" />
			<input type="hidden" name="bjV" id="bjV" value="" />
			<input type="hidden" name="tableName" value=""/>
			<input type="hidden" name="realTable" value="${realTable }"/>
			<input type="hidden" id="userStatus" value="${user.userStatus }"/>
			
			<logic:equal name="user" property="userStatus" value="stu">
				<input type="hidden" name="xh" value="${userName }"/>
			</logic:equal>
			
			<logic:equal name="user" property="userStatus" value="xy">
				<input type="hidden" name="xydm" value="${userDep }"/>
			</logic:equal>
			
			<div class="toolbox">
				<div class="buttonbox">
					<ul>
						<li><a href="#" class="btn_zj" onclick="showTopWin('stuGxh.do?method=cwbhUpdate',700,500);return false;">增加</a></li>
						<li><a href="#" class="btn_xg" onclick="modi('stuGxh.do?method=cwbhUpdate&doType=modi',700,500);return false;">修改</a></li>
						<li><a href="#" class="btn_sc" onclick="batchData('primarykey_cbv','stuGxh.do?method=cwbhManage&doType=del','');return false;">删除</a></li>
						<li><a href="#" class="btn_dr" onclick="impAndChkData();return false;">导入</a></li>
					</ul>
				</div>
			</div>

			<div class="searchtab">
				<table>
					<tbody>
						<tr>
							<th>学号</th>
							<td><html:text property="xh" styleId="xh"></html:text></td>
							<th>姓名</th>
							<td><html:text property="xm" styleId="xm"></html:text></td>
							<th>财物编号</th>
							<td><html:text property="cwbh" styleId="cwbh"></html:text></td>
						</tr>
						<tr>
							<th><bean:message key="lable.xsgzyxpzxy" /></th>
							<td><html:select property="xydm" style="width: 150px" styleId="xy" onchange="initZyList();initBjList();">
								<html:option value=""></html:option>
								<html:options collection="xyList" property="xydm"
									labelProperty="xymc" />
							</html:select></td>
							<th>专业</th>
							<td><html:select property="zydm" style="width: 150px" styleId="zy" onchange="initBjList()">
								<html:option value=""></html:option>
								<html:options collection="zyList" property="zydm"
									labelProperty="zymc" />
							</html:select></td>
		         			<th>班级</th>
		         			<td><html:select property="bjdm" style="width:150px" styleId="bj">
								<html:option value=""></html:option>
								<html:options collection="bjList" property="bjdm"
									labelProperty="bjmc" />
							</html:select></td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="6">
								<div class="btn">
									<input type="hidden" name="go" value="a" />
									<button type="button" id="search_go" onclick="allNotEmpThenGo('stuGxh.do?method=cwbhManage');">
										查 询
									</button>
									 <button type="button" class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
										重 置
							 		</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
			
			<div class="formbox">
				<h3 class="datetitle_01">
					<span>
						查询结果&nbsp;&nbsp;<font color="blue">单击表头可以排序；</font>
					</span>
				</h3>
				<table width="99%" id="rsTable" class="dateline">
					<thead>
						<tr align="center" style="cursor:hand">
							<td>
								<input type="checkbox" name="all" value="all" onclick="chec()"/>
							</td>
							<logic:iterate id="tit" name="topTr" offset="0">
								<td id="${tit }" onclick="tableSort(this)">
									${tit }
								</td>
							</logic:iterate>
						</tr>
					</thead>
					<logic:empty name="rs">
					  <%
						for(int i=0; i<11; i++){
						%>
						<tr>
							<td>
								<input type="checkbox" name="pk" value="" disabled="disabled"></input>
							</td>
							<logic:iterate id="tit" name="topTr" offset="0">
								<td>
									&nbsp;
								</td>
							</logic:iterate>	
					 	</tr>
						<%}%>
					 </logic:empty>
					<logic:notEmpty name="rs">
						<logic:iterate name="rs" id="s">
							<tr onclick="rowOnClick(this);" style="cursor:hand" 
								ondblclick="modi('yhwhManage.do?method=yhwhView',700,500);">
								<td>								
									<input type="checkbox" name="primarykey_cbv" id="pkV"
										value="<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate>" />
									
								</td>
								<logic:iterate id="v" name="s" offset="0">
									<td>${v }</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
						<%
						int rsNum = ((List)request.getAttribute("rs")).size();
						int pageSize = 11;
						if(rsNum < pageSize){
							for(int i=0; i<(pageSize-rsNum); i++){
						%>
						<tr>
							<td>
								<input type="checkbox" name="primarykey_cbv" value="" disabled="disabled"></input>
							</td>
							<logic:iterate id="tit" name="topTr" offset="0">
								<td>
									&nbsp;
								</td>
							</logic:iterate>
					 	</tr>
						<%}}%>
				</logic:notEmpty>
			</table>
			<!--分页显示-->
	   	 	<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=StudentInfoForm"></jsp:include>
			<!--分页显示-->
		</div>
		<div class="tab" style="display: none" id="tempDiv">
		</div>
		</html:form>
		<logic:present name="message">
			<input type="hidden" id="message" value="${message }"/>
			<script type="text/javascript" defer="defer">
				alert(document.getElementById('message').value);
			</script>
		</logic:present>
	</body>
</html>