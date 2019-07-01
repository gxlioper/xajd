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
		<title><bean:message key="lable.title" /></title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control" http-equiv="no-cache" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="正方软件 zfsoft" />
		<link id="csss" rel="stylesheet" rev="stylesheet"
			href="style/main.css" type="text/css" media="all" />
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript">
	function xljk_tkwh_view(){
		var sjlsh = curr_row.cells[0].innerText;
		showTopWin('/xgxt/xljk_xlcs_tkwh.do?act=tkwh&doType=View&sjlsh='+sjlsh,500,400);
	}
	
	function xljk_paper_add(){
		showTopWin('/xgxt/xljk_xlcs_tkwh.do?act=tkwh&doType=Insert',500,400);
	}
	
	function xljk_paper_modi(){
		if (curr_row == null) {
			alert("请选择要修改的数据！\n（单击相应的行）");
			return false;
		}else{
			pkValue =curr_row.cells[0].innerText;
			url="/xgxt/xljk_xlcs_tkwh.do?act=tkwh&doType=Modi&sjlsh="+pkValue;
			showTopWin(url,500,400);
		}
	}
	
	function xljk_paper_del(){
		if (curr_row == null) {
			alert("请选择要删除的数据！\n（单击相应的行）");
			return false;
		}else if (confirm("确定要删除该行数据吗？")) {
			pkValue =curr_row.cells[0].innerText;
			url="/xgxt/xljk_xlcs_tkwh.do?act=tkwh&doType=Del&sjlsh="+pkValue;
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
					<em>您的当前位置:</em><a>${tips}</a>
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
			<input type="hidden" id="buttonxsbj" name="buttonxsbj" value="" />

			<div class="toolbox">
				<!-- 按钮 -->

				<logic:equal value="yes" name="writeAble">
					<div class="buttonbox">
						<ul>
							<li>
								<a href="#" class="btn_zj" onclick="xljk_paper_add()"> 增加试卷 </a>
							</li>
							<li>
								<a href="#" class="btn_xg" onclick="xljk_paper_modi()"> 修改试卷 </a>
							</li>
							<li>
								<a href="#" class="btn_sc" onclick="xljk_paper_del()"> 删除试卷 </a>
							</li>
							<li>
								<a href="#" class="btn_sz"
									onclick="showTopWin('/xgxt/xljk_xlcs_tkwh.do?act=tkwh&doType=st_index',820,550)"> 题库维护 </a>
							</li>
							<li>
								<a href="#" class="btn_sz"
									onclick="showTopWin('/xgxt/xljk_xlcs_tkwh.do?act=tkwh&doType=zjwh',730,550)"> 组卷维护 </a>
							</li>
						</ul>
					</div>
				</logic:equal>


				<div class="searchtab">
					<table width="100%" border="0">
						<tfoot>
							<tr>
								<td colspan="12">
									<div class="btn">
										<button class="" style="" id="search_go"
											onclick="allNotEmpThenGo('/xgxt/xljk_xlcs_tkwh.do?act=tkwh&doType=search')">
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
									试卷名
								</th>
								<td >
									<html:select property="sjlsh" style="width:200px">
										<html:option value=""></html:option>
										<html:options collection="sjList" property="SJLSH"
											labelProperty="SJM" />
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
			 		当前试卷数：
						<bean:write name="rsNum" />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<font color="blue">提示：双击一行可以查看详细信息；单击表头可以排序</font>
						</logic:notEmpty> </span>
				</h3>

				<logic:notEmpty name="rs">
	
						<table summary="" class="dateline" align="center"
							width="100%">
							<thead>
								<tr align="" style="cursor:hand">
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
										ondblclick="xljk_tkwh_view()">
										<td>
											<bean:write name="s" property="sjlsh" />
										</td>
										<td>
											<bean:write name="s" property="sjm" />
										</td>
										<td>
											<bean:write name="s" property="sjxd" />
										</td>
										<td>
											<bean:write name="s" property="sjxsbj" />
										</td>
										<td>
											<bean:write name="s" property="jrsj" />
										</td>
									</tr>
								</logic:iterate>
							</tbody>
						</table>
					</div>
					<!--分页显示-->

					<!--分页显示-->
				</logic:notEmpty>
			</div>
			<logic:notEmpty name="message">
				<logic:equal name="message" value="del success">
					<script>
						alert("删除成功!");
					</script>
				</logic:equal>
				<logic:equal name="message" value="no">
					<script>
					alert("删除失败!");
					document.getElementById("tmpdiv").innerHTML = "";
					</script>
				</logic:equal>
			</logic:notEmpty>
		</html:form>

	</body>
</html>
