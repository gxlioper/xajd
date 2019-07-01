<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
		<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
		%>
		<title><bean:message key="lable.title" />
		</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control" http-equiv="no-cache" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="正方软件 zfsoft" />
		<link rel="icon" href="images/icon.ico" type="image/x-icon" />
		<link id="csss" />
		<base target="_self">
		<script language="javascript" src="js/lrh_new_js.js"></script>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript">
		function xljk_xlcs_zjst(){
			showTopWin('/xgxt/xljk_xlcs_tkwh.do?act=tkwh&doType=st_add',550,450);
		}
		
		function stb_st_view(){
			var stlsh=curr_row.cells[1].innerText;
			showTopWin('/xgxt/xljk_xlcs_tkwh.do?act=tkwh&doType=st_view&stlsh='+stlsh,550,450);
		}
		
		function sjst_add_tosj(){
			var flag = false;
			var m = 0;
			var array = document.getElementById('rsTable').getElementsByTagName('tr');
			var data = new Array();
			var data2 = new Array();
			for(var i=1;i<array.length;i++){
				if(array[i].cells[0].getElementsByTagName('input')[0].checked == true){
					flag = true;
					data[m] = array[i].cells[4].innerText.replace(/^\s+/g,"").replace(/\s*$/g,"");
					data2[m] = array[i].cells[1].innerText.replace(/^\s+/g,"").replace(/\s*$/g,"");
					m++;
				}		
			}
			if(flag == false){
				alert("请先选择要增加的试题");
				return;
			}
			window.dialogArguments.lrh_createStList(data,data2);
		}
	</script>
	</head>
	<body>
		<html:form action="/xljk_xlcs_tkwh.do?act=tkwh&doType=sjst_st_search&search=true" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>${tips }</a>
				</p>
			</div>
			
			<input type="hidden" id="tableName" name="tableName"
				value="<bean:write name="tableName" scope="request"/>" />
			<input type="hidden" id="act" name="act"
				value="<bean:write name="act" scope="request"/>" />
			<input type="hidden" id="realTable" name="realTable"
				value="<bean:write name="realTable" scope="request"/>" />
			<input type="hidden" id="pk" name="pk"
				value="<bean:write name="pk" scope="request"/>" />
				
			<div class="toolbox">
				<!-- 按钮 -->
					<div class="buttonbox">
						<ul>
						<li>
						<a class="btn_zj" onclick="sjst_add_tosj()">
							增加试题到试卷
						</a>
							</li>
						</ul>
					</div>
				<div class="searchtab">
					<table width="100%" border="0">
						<tfoot>
							<tr>
								<td colspan="12">
									<div class="btn">
										<button class="" style="" id="search_go"
											onclick="allNotEmpThenGo('/xgxt/xljk_xlcs_tkwh.do?act=tkwh&doType=sjst_st_search&search=true')">
											查询
										</button>
										<input type="hidden" name="go" value="a" />
										&nbsp;&nbsp;&nbsp;&nbsp;
										<button class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
											重置
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
						<tbody>
							<tr align="left">
								<th align="left">
								类型
								</th>
								<td >
									<html:select property="stlxdm" style="width:150px">
									<html:option value=""></html:option>
									<html:options collection="stlxList" property="STLXDM"
										labelProperty="STLXMC" />
								</html:select>
								</td>
								<th>难度级别</th>
								<td><html:select property="stndjbdm" style="width:150px">
									<html:option value=""></html:option>
									<html:options collection="stndjbList" property="STNDJBDM"
										labelProperty="STNDJBMC" />
								</html:select></td>
								<th>所属类别</th>
								<td><html:select property="sslxdm" style="width:150px">
									<html:option value=""></html:option>
									<html:options collection="stsslbList" property="SSLXDM"
										labelProperty="SSLXMC" />
								</html:select></td>
								</tr>
								<tr>
								<th>试题内容</th>
								<td><html:text property="stnr" style="width:200px"></html:text></td>
								<th>&nbsp;</th>
								<td>&nbsp;</td>
								<th>&nbsp;</th>
								<td>&nbsp;</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>	
				
			<div class="formbox">
				<h3 class="datetitle_01">
					<span> 查询结果&nbsp;&nbsp; <logic:empty name="rs">
							<font color="red">未找到任何记录！</font>
						</logic:empty> <logic:notEmpty name="rs">
			 		当前试题数：
						<bean:write name="rsNum" />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<font color="blue">提示：双击一行可以查看详细信息；单击表头可以排序</font>
						</logic:notEmpty> </span>
				</h3>

				<logic:notEmpty name="rs">

						<table summary="" class="dateline tablenowrap" align="center"
							width="100%" id="rsTable">
							<thead>
								<tr align="center" style="cursor:hand">
								<td nowrap>
									<input type="checkbox" id="cb" name="cb" onclick="selectAll()" />
								</td>
								<logic:iterate id="tit" name="topTr" offset="1">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)" nowrap>
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
							</thead>
							<tbody>
								<logic:iterate name="rs" id="s" offset="0">
							<tr onclick="rowOnClick(this)" style="cursor:hand"
								ondblclick="stb_st_view()" align="center">
								<td align="right">
									<input type="checkbox" id="pk" name="pk"
										value="<bean:write name="s" property="stlsh"/>" />
								</td>
								<td>
									<bean:write name="s" property="stlsh" />
								</td>
								<td>
									<bean:write name="s" property="stlxmc" />
								</td>
								<td>
									<bean:write name="s" property="stndjbmc" />
								</td>
								<td nowrap>
									<bean:write name="s" property="stnr" />
								</td>
								<td>
									<bean:write name="s" property="sslxmc" />
								</td>
								<td>
									<bean:write name="s" property="stjffs" />
								</td>
								<td>
									<bean:write name="s" property="stfz" />
								</td>
								<td>
									<bean:write name="s" property="stda" />
								</td>
								<td>
									<bean:write name="s" property="stksbj" />
								</td>
							</tr>
						</logic:iterate>
							</tbody>
						</table>
						<!--分页显示-->
					<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=xilk_xlcsTkwh_form"></jsp:include>
					<!--分页显示-->
						</logic:notEmpty>
					
			</div>		
		</html:form>
	</body>
</html>
