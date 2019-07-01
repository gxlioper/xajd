<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<title><bean:message key="lable.title" />
		</title>
		
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" />

	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<base target="_self" />

	<script language="javascript">
		function xljk_xlcs_zjst(){
			showTopWin('/xgxt/xljk_xlcs_tkwh.do?act=tkwh&doType=st_add',570,400);
		}
		
		function stb_st_view(){
			var stlsh=curr_row.cells[0].innerText;
			showTopWin('/xgxt/xljk_xlcs_tkwh.do?act=tkwh&doType=st_view&stlsh='+stlsh,570,430);
		}
		
		function stb_st_modi(){
			if (curr_row == null) {
				alert("请选择要修改的数据！\n（单击相应的行）");
				return false;
			}else{
				var stlsh=curr_row.cells[0].innerText;
				showTopWin('/xgxt/xljk_xlcs_tkwh.do?act=tkwh&doType=st_modi&stlsh='+stlsh,570,400);
			}
		}
		
		function stb_st_del(){
			if (curr_row == null) {
				alert("请选择要删除的数据！\n（单击相应的行）");
				return false;
			}else if (confirm("确定要删除该行数据吗？")) {
				var stlsh=curr_row.cells[0].innerText;
				url="/xgxt/xljk_xlcs_tkwh.do?act=tkwh&doType=st_del&stlsh="+stlsh;
				underDealWith();
				refreshForm(url);
			}
		}
		
		function stb_stsslb_pipei(){
			if (curr_row == null) {
				alert("请选择要匹配的数据！\n（单击相应的行）");
				return false;
			}else{
				var stlsh=curr_row.cells[0].innerText;
				showTopWin('/xgxt/xljk_xlcs_tkwh.do?act=tkwh&doType=st_stsslb_pipei&stlsh='+stlsh,570,430);
			}
		}
		
		function stb_st_xxwh(){
			if (curr_row == null) {
				alert("请先选择试题！\n（单击相应的行）");
				return false;
			}else{
				var stlsh=curr_row.cells[0].innerText;
				showTopWin('/xgxt/xljk_xlcs_tkwh.do?act=tkwh&doType=st_xxwh&stlsh='+stlsh,570,430);
			}
		}
		
		function dataExport2() {
			document.forms[0].action = "/xgxt/xljk_xlcs_tkwh.do?doType=common_exp";
			document.forms[0].target = "_blank";
			document.forms[0].submit();
			document.forms[0].target = "_self";
		}
		function dcjf(){
			document.forms[0].target = "";
		}
	</script>
	<body>
		<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
		%>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<html:form action="/xljk_xlcs_tkwh.do?act=tkwh&doType=st_search" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>${tips}</a>
				</p>
			</div>
		
			<input type="hidden" id="tableName" name="tableName"
				value="VIEW_XLJK_XLCS_ST" />
			<input type="hidden" id="act" name="act"
				value="<bean:write name="act" scope="request"/>" />
			<input type="hidden" id="realTable" name="realTable"
				value="STB" />
			<input type="hidden" id="pk" name="pk"
				value="<bean:write name="pk" scope="request"/>" />
				
			<div class="toolbox">
				<!-- 按钮 -->

				<logic:equal value="yes" name="writeAble">
					<div class="buttonbox">
						<ul>
						<li>
						<a class="btn_zj" onclick="xljk_xlcs_zjst()">
							增加试题
						</a>
						</li>
						<li>
						<a class="btn_xg" onclick="stb_st_modi()" >
							修改试题
						</a>
						</li>
						<li>
						<a class="btn_sc" onclick="stb_st_del()">
							删除试题
						</a>
						</li>
						<li>
						<a class="btn_sz" onclick="stb_stsslb_pipei()">
							试题类别维护
						</a>
						</li>
						<li>
						<a class="btn_sz" onclick="stb_st_xxwh()">
							选项维护
						</a>
						</li>
						<li>
						<a class="btn_dr" onclick="impAndChkData();">
							导入数据
						</a>
						</li>
						<li>
						<a class="btn_dc" onclick="dataExport2()" >
							导出数据
						</a>
						</li>
						</ul>
					</div>
				</logic:equal>
				<div class="searchtab">
					<table width="100%" border="0">
						<tfoot>
							<tr>
								<td colspan="6">
									<div class="btn">
										<button class="" style="" id="search_go"
											onclick="dcjf();allNotEmpThenGo('/xgxt/xljk_xlcs_tkwh.do?act=tkwh&doType=st_search');">
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
									试题类型
								</th>
								<td >
									<html:select property="stlxdm" style="width:90px">
									<html:option value=""></html:option>
									<html:options collection="stlxList" property="STLXDM"
										labelProperty="STLXMC" />
								</html:select>
								</td>
								<th>试题难度级别</th>
								<td><html:select property="stndjbdm" style="width:90px">
									<html:option value=""></html:option>
									<html:options collection="stndjbList" property="STNDJBDM"
										labelProperty="STNDJBMC" />
								</html:select></td>
								<th>试题类别(心理)</th>
								<td><html:select property="sslxdm" style="width:90px">
									<html:option value=""></html:option>
									<html:options collection="stsslbList" property="SSLXDM"
										labelProperty="SSLXMC" />
								</html:select></td>
								</tr>
								<tr>
								<th>试题内容</th>
								<td><html:text property="stnr" style="width:180px"></html:text></td>
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
					
						<table summary="" class="dateline" align="center"
							width="100%">
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
								<logic:iterate name="rs" id="s" offset="0">
								<tr onclick="rowOnClick(this)" style="cursor:hand"
									ondblclick="stb_st_view()">
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
										<bean:write name="s" property="stxsbj" />
									</td>
								</tr>
							</logic:iterate>
							</tbody>
						</table>

						</logic:notEmpty>
					<!--分页显示-->
					<!--分页显示-->
			</div>
			
				<logic:notEmpty name="message">
					<logic:equal name="message" value="del success">
						<script>
						alert("删除成功!");
						</script>
					</logic:equal>
					<logic:equal name="message" value="del fail">
						<script>
						alert("删除失败!");
						document.getElementById("tmpdiv").innerHTML = "";
						</script>
					</logic:equal>
					<logic:equal name="message" value="no">
						<script>alert("保存失败!")</script>
					</logic:equal>
				</logic:notEmpty>
			</div>
		</html:form>
		
	</body>
</html>
