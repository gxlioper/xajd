<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
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
	<link id="csss"  />
	<base target="_self">
	<script language="javascript">
		function sjst_view(){
			var sjst_xnid=curr_row.cells[0].getElementsByTagName("input")[0].value;
			showTopWin('/xgxt/xljk_xlcs_tkwh.do?act=tkwh&doType=sjst_view&sjst_xnid='+sjst_xnid,550,450);
		}
		
		function sjst_zj_wh(){
			var sjlsh = document.forms[0].sjlsh.value;
			var yxstlbListIndex = document.forms[0].sjlsh.selectedIndex;
			var sjm = document.forms[0].sjlsh.options[yxstlbListIndex].text.replace(/^\s+/g,"").replace(/\s*$/g,"");
			showTopWin('/xgxt/xljk_xlcs_tkwh.do?act=tkwh&doType=sjst_zj_wh&sjlsh='+sjlsh+'&sjm='+sjm,400,550);
		}
	</script>
	<body>
		<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
		%>
		<script language="javascript" src="js/function.js"></script>
		<html:form action="/xljk_xlcs_tkwh" method="post">
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
						<a class="btn_sz"
						onclick="if(document.forms[0].sjlsh.value!='') sjst_zj_wh();else {alert('请先选择试卷');return false;}">
						维护
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
											onclick="allNotEmpThenGo('/xgxt/xljk_xlcs_tkwh.do?act=tkwh&doType=sjst_search')">
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
									<html:select property="sjlsh" style="width:150px"
									styleId="sjlsh">
									<html:option value=""></html:option>
									<html:options collection="sjList" property="SJLSH"
										labelProperty="SJM" />
								</html:select>
								</td>
								<th>试题类型</th>
								<td><html:select property="stlxdm" style="width:150px">
									<html:option value=""></html:option>
									<html:options collection="stlxList" property="STLXDM"
										labelProperty="STLXMC" />
								</html:select></td>
								<th>&nbsp;</th>
								<td>&nbsp;</td>
								</tr>
								<tr>
								
								<th>试题所属类别</th>
								<td><html:select property="sslxdm" style="width:150px">
									<html:option value=""></html:option>
									<html:options collection="stsslbList" property="SSLXDM"
										labelProperty="SSLXMC" />
								</html:select></td>
								<th>试题难度级别</th>
								<td><html:select property="stndjbdm" style="width:150px">
									<html:option value=""></html:option>
									<html:options collection="stndjbList" property="STNDJBDM"
										labelProperty="STNDJBMC" />
								</html:select></td>
								
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
			 		当前记录数：
						<bean:write name="rsNum" />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<font color="blue">提示：单击表头可以排序</font>
						</logic:notEmpty> </span>
				</h3>

				<logic:notEmpty name="rs">

						<table summary="" class="dateline tablenowrap" align="center"
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
							<tr onclick="rowOnClick(this)" ondblclick="sjst_view()"
								style="cursor:hand" align="center">
								<td>
									<input type="hidden" id="xn_id" name="xn_id"
										value="<bean:write name="s" property="xn_id"/>" />
									<bean:write name="s" property="sjlsh" />
								</td>
								<td nowrap>
									<bean:write name="s" property="sjm" />
								</td>
								<td>
									<bean:write name="s" property="stxh" />
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
									<bean:write name="s" property="stfz" />
								</td>
								<td>
									<bean:write name="s" property="stda" />
								</td>
								<td>
									<bean:write name="s" property="stlsh" />
								</td>
								<td>
									<bean:write name="s" property="sslxmc" />
								</td>
							</tr>
						</logic:iterate>
							</tbody>
						</table>
						</logic:notEmpty>
					<!--分页显示-->
					<!--分页显示-->
			</div>	

		</html:form>
	</body>
</html>
