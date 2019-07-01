<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead_V4.ini"%>
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
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
	<head>
		<title><bean:message key="lable.title" /></title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control" http-equiv="no-cache" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="正方软件 zfsoft" />
		<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">    
	<script language="javascript" src="js/function.js"></script>
		<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
        %>
	</head>
	<script type="text/javascript">
			function wjcfjd(){
				if (curr_row==null || curr_row=='') {
					alert('请选择要操作的数据行！');
					return;
				} else {
					var url = 'wjcf_nblg_cfjdUpdate.do?pkValue=';
					url += curr_row.cells[0].getElementsByTagName("input")[0].value;
					showTopWin(url, 700, 620);
				}
			}
		</script>

	<body onload="xyDisabled('xy')">
		<center>			
			<html:form action="/wjcfnblgwh.do">
			    <input type="hidden" name="zyV" id="zyV" />
				<input type="hidden" name="bjV" id="bjV" />
			    <input type="hidden" id="tableName" name="tableName" 
					       value="${tableName }" />
				<input type="hidden" id="realTable" name="realTable" 
					       value="${realTable }" />
				
				<input type="hidden" name="userType" id="userType" value="${userType }"/>	       
					<div class="tab_cur">
					<p class="location">
						<em>当前所在位置：</em><a>违纪处分 - 申诉申请审核 - 委员会决定</a>
					</p>
		           </div>
		           <logic:equal value="yes" name="writeAble">
					<div class="toolbox">
					<div class="buttonbox">	
						<input type="hidden" name="ts" id="ts"/>
						<ul>
							<li><a href="#" class="btn_dy" onclick="bbprint()">打印/预览</a></li>
						</ul>
					</div>
					</div>
		         </logic:equal>
				
				<div class="searchtab">
				<table width="100%" class="" border="0">
					<tbody>
					<tr>
					<th align="left">年级</th>
						<td><html:select property="nj" style="width:90px;background-color:#FFFFFF"
									    onchange="initZyList();initBjList()">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select>
						</td>
						<th align="left">学年</th>
						<td>
							<html:select property="xn" styleId="xn" style="width:100px">
										<html:options collection="xnList" property="xn" labelProperty="xn"/>
									</html:select>
						</td>
						<th align="left">学号</th>
						<td><html:text property="xh" style="width:120px;"/>
						</td>
					</tr>
					<tr>
						<th align="left">姓名</th>
							<td><html:text property="xm" style="width:120px;"/>
						    </td>	
						    <th align="left"><bean:message key="lable.xsgzyxpzxy" /></th>
							<td><html:select property="xydm" styleId="xy" style="width:200px;background-color:#FFFFFF"
										onchange="initZyList();initBjList()">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
							</td>
							<th align="left">专业</th>
							<td><html:select property="zydm" style="width:200px;background-color:#FFFFFF" styleId="zy"
										onchange="initBjList()">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select>
						    </td>
					</tr>
					<tr>
					<th align="left">班级</th>
							<td><html:select property="bjdm" style="width:200px;background-color:#FFFFFF" styleId="bj">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm"
											labelProperty="bjmc" />
									</html:select>
						    </td>
					</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="8">
							<div class="btn">
							<input type="hidden" name="act" value="query" />
							<button type="button" id="search_go" 
							onclick="refreshForm('wjcf_nblg_wyhjdwh.do');">
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
			
				
				
	<div class="formbox" >
				<logic:empty name="rs">
				    <h3 class="datetitle_01">
				    <span>
				    	查询结果&nbsp;&nbsp;
							<font color="red">未找到任何记录！</font> 
				    </span>
				    </h3>
				 </logic:empty>
				<logic:notEmpty name="rs">
				<h3 class="datetitle_01">
						<span>
							查询结果&nbsp;&nbsp;<font color="blue">双击一行可以改变状态；单击表头可以排序</font> 
						</span>
					</h3>
					<div class="con_overlfow">
						<table width="100%" id="rsTable" class="dateline">
							<thead>
								<tr align="center" style="cursor:hand">							
									<logic:iterate id="tit" name="topTr" offset="2">
										<td id="<bean:write name="tit" property="en" />"
											onclick="tableSort(this)" nowrap>
											<bean:write name="tit" property="cn" />
										<br></td>
									</logic:iterate>
								</tr>
							</thead>
							<logic:iterate name="rs" id="s">
								<tr onclick="rowOnClick(this)" 
                                    style="cursor:hand;background-color:
                          <logic:iterate id="v" name="s" offset="1" length="1">
                           <bean:write name="v"/>
                            </logic:iterate>
                             " ondblclick="wjcfjd()">
									<td nowrap>
									<logic:iterate id="v" name="s" offset="0" length="1">
										<input type="hidden" value="<bean:write name="v"/>" />
									</logic:iterate>
									<logic:iterate id="v" name="s" offset="2" length="1">
										<bean:write name="v"/>
									</logic:iterate>
								    </td>
								    <logic:iterate id="v" name="s" offset="3">
									<td nowrap>
										<bean:write name="v" />
									</td>
								   </logic:iterate>
								</tr>
							</logic:iterate>
						</table>
						</div>		
					    </logic:notEmpty>
					    </div>
					<script type="text/javascript" src="js/bottomButton.js"></script>	
			<script>
				function bbprint() {
				var pk;
				if (curr_row==null||curr_row=='') {
					pk=''
				} else {
				pk = curr_row.cells[0].getElementsByTagName("input")[0].value;
					}
				var url = 'wjcf_nblg_cxprint.do?pkValue=';
				url += pk;
				window.open(url);
			
		}
			</script>				    					   		 			
			</html:form>
		</center>		
		
	</body>
</html>
