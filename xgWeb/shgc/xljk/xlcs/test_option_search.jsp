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
		<base target="_self">
		<title><bean:message key="lable.title" /></title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control" http-equiv="no-cache" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="正方软件 zfsoft" />
		<link rel="icon" href="images/icon.ico" type="image/x-icon" />
		<link id="csss" />
		<link id="csssDate" rel="stylesheet" rev="stylesheet"
			href="js/calendar.css" type="text/css" media="all" />
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript">
		function st_xx_search(){
			document.all['xx_search_flag'].value='yes';
			refreshForm('/xgxt/xljk_xlcs_tkwh.do?act=tkwh&doType=st_xxwh');
		}
		
		function st_xx_view(){
			var xxlsh=curr_row.cells[0].innerText;
			showTopWin('/xgxt/xljk_xlcs_tkwh.do?act=tkwh&doType=st_xx_view&xxlsh='+xxlsh,480,300);
		}
		
		function st_xx_add(){
			var stlsh=document.forms[0].stlsh.value;
			showTopWin('/xgxt/xljk_xlcs_tkwh.do?act=tkwh&doType=st_xx_add&stlsh='+stlsh,480,300);
		}
		
		function st_xx_modi(){
			if (curr_row == null) {
				alert("请选择要修改的数据！\n（单击相应的行）");
				return false;
			}else{
				var xxlsh=curr_row.cells[0].innerText;
				showTopWin('/xgxt/xljk_xlcs_tkwh.do?act=tkwh&doType=st_xx_modi&xxlsh='+xxlsh,480,300);
			}
		}
		
		function st_xx_del(){
			if (curr_row == null) {
				alert("请选择要修改的数据！\n（单击相应的行）");
				return false;
			}else if (confirm("确定要删除该行数据吗？")) {
				var xxlsh=curr_row.cells[0].innerText;
				url="/xgxt/xljk_xlcs_tkwh.do?act=tkwh&doType=st_xx_del&xxlsh="+xxlsh;
				underDealWith();
				refreshForm(url);
			}
		}
	</script>
	</head>
	<body>
		<html:form action="/xljk_xlcs_tkwh" method="post">
		<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>心理健康 - 心理测试 - 题库维护 - 选项维护</a>
				</p>
			</div>
			<div id="main" style="heigth:100px;">
				<input type="hidden" id="xx_search_flag" name="xx_search_flag"
					value="no" />
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
						<a class="btn_zj" id="buttonSave"
								onclick="if(document.forms[0].stlsh.value!='')st_xx_add();else {alert('请先选择试题');return false;}">
								增加选项
							</a>
							</li>
							<li>
							<a class="btn_xg" onclick="st_xx_modi()"  id="buttonUpdate">
								修改选项
							</a>
							</li>
							<li>
							<a class="btn_sc" onclick="st_xx_del()"  id="buttonDelete">
								删除选项
							</a>
							</li>
						<li>
						</ul>
					</div>
				<div class="searchtab">
					<table width="100%" border="0">
						<tfoot>
							<tr>
								<td colspan="12">
									<div class="btn">
										<button class="" style="" id="search_go"
											onclick="st_xx_search()">
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
									试题流水号
								</th>
								<td >
									<html:select property="stlsh" style="width:150px"
										styleId="stlsh">
										<html:option value=""></html:option>
										<html:options collection="stlshList" property="STLSH"
											labelProperty="STLSH" />
									</html:select>
					&nbsp;&nbsp;&nbsp;&nbsp;
								</td>
								<th>&nbsp;&nbsp;&nbsp;</th>
								<td>&nbsp;&nbsp;&nbsp;</td>
								<th>&nbsp;&nbsp;&nbsp;</th>
								<td>&nbsp;&nbsp;&nbsp;</td>
								<th>&nbsp;&nbsp;&nbsp;</th>
								<td>&nbsp;&nbsp;&nbsp;</td>
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
			 		当前选项数：
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
								<logic:iterate name="rs" id="s">
									<tr onclick="rowOnClick(this)" style="cursor:hand"
										ondblclick="st_xx_view()">
										<td>
											<bean:write name="s" property="XXLSH" />
										</td>
										<td>
											<bean:write name="s" property="STLSH" />
										</td>
										<td>
											<bean:write name="s" property="XXXH" />
										</td>
										<td>
											<bean:write name="s" property="XXNR" />
										</td>
										<td>
											<bean:write name="s" property="XXFZ" />
										</td>
										<td>
											<bean:write name="s" property="XXXSBJ" />
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
								document.getElementById("search_go").click();
								alert("删除成功!");
							</script>
						</logic:equal>
						<logic:equal name="message" value="del fail">
							<script>
								alert("删除失败!");
							</script>
						</logic:equal>
					</logic:notEmpty>
				</div>
			</div>
		</html:form>

	</body>
</html>
