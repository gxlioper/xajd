<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script type="text/javascript">
		function getOkStudent(){
			//检验是否为空
			//先判断表的行数
			var Table1 = document.getElementById("tableadddel");			
			var tableRowLength = Table1.rows.length;
<%--			var condi = document.getElementById("condi").value;--%>
<%--			var condiAr = new Array();--%>
<%--			condiAr = condi.split('-');--%>
<%--			for(i=1;i<parseInt(tableRowLength) + 1;i++){--%>
<%--				if(i == 1){--%>
<%--					//表示为第一行--%>
<%--					num = 0;--%>
<%--					for(j=0;j<condiAr.length;j++){--%>
<%--						if(!document.getElementById(condiAr[j]).value == ""){--%>
<%--							num ++;--%>
<%--						}--%>
<%--					}--%>
<%--					if(num == 1 || num == 2){--%>
<%--						alert('必须将第一行的所有的选项选上或全不选!');--%>
<%--						return;--%>
<%--					}--%>
<%--				}else {--%>
<%--					num = 0;--%>
<%--					for(k=0;k<condiAr.length;k++){--%>
<%--						//alert("condiAr[k] + i=" + condiAr[k] + i)--%>
<%--						if(!document.getElementById(condiAr[k] + i).value == ""){--%>
<%--							num ++;--%>
<%--						}--%>
<%--					}--%>
<%--					if(num == 1 || num == 2){--%>
<%--						alert("必须将第" + i + "行的所有的选项选上或全不选!");--%>
<%--						return;--%>
<%--					}--%>
<%--				}--%>
<%--			}--%>
			//将表的行数放到隐藏域中
			document.getElementById("rowIndex").value =  tableRowLength;
			refreshForm('/xgxt/xljk_xlcs_pcjgcx.do?act=pcjgcx&doType=cjb_search_xjlk')
			//allNotEmpThenGo('/xgxt/xljk_xlcs_pcjgcx.do');
		}
		function moreStuInfo(){
			var val = curr_row.cells[0].innerText;
			var url = '/xgxt/xljk_xlcs_pcjgcx.do?xh=';
			url += val + '&doType=stuMoreInfo' + '&act=pcjgcx';
			showTopWin(url, 500, 350);	
		}
		function addCondi(){
			var Table1 = document.getElementById("tableadddel");			
			var tableRowLength = Table1.rows.length + 1;
			var new_row=Table1.insertRow(Table1.rows.length); 
		    new_row.setAttribute("id", "row"+tableRowLength);  
		    var new_col=new_row.insertCell(0); 
		    new_col.innerHTML="因子&nbsp;&nbsp;&nbsp;"
							+ "<select style='width:150px' name='yz" + tableRowLength + "'"  + " id='yz" + tableRowLength + "'>"
								+ "<option value=''></option>"
								+ "<option value='qthyz'>躯体化因子</option>"
								+ "<option value='qpzzyz'>强迫症状因子</option>"
								+ "<option value='rjgxmgyz'>人际关系敏感因子</option>"
								+ "<option value='yyyz'>抑郁因子</option>"
								+ "<option value='jlyz'>焦虑因子</option>"
								+ "<option value='ddyz'>敌对因子</option>"
								+ "<option value='kbyz'>恐怖因子</option>"
								+ "<option value='pzyz'>偏执因子</option>"
								+ "<option value='jsbxyz'>精神病性因子</option>"
								+ "<option value='fjyz'>附加因子</option>"
							+ "</select>"; 
		    var new_col=new_row.insertCell(1); 
		    new_col.innerHTML="查询条件&nbsp;&nbsp;&nbsp;<select name='tj" + tableRowLength + "' style='width:150px'" +  " id='tj" + tableRowLength + "'>"
								+ "<option value=''></option>"
								+ "<option value='1'>等于</option>"
								+ "<option value='2'>大于</option>"
								+ "<option value='3'>小于</option>"
								+ "<option value='4'>大于等于</option>"
								+ "<option value='5'>小于等于</option>"
							+ "</select>"; 
		    var new_col=new_row.insertCell(2); 
		    new_col.innerHTML="因子得分&nbsp;&nbsp;&nbsp;"
							+ "<select name='yzdf" + tableRowLength + "' style='width:150px' id='yzdf" + tableRowLength + "'>"
								+ "<option value=''></option>"
								+ "<option value='0'>0</option>"
								+ "<option value='1'>1</option>"
								+ "<option value='2'>2</option>"
								+ "<option value='3'>3</option>"
								+ "<option value='4'>4</option>"
							+ "</select>"; 
			var new_col=new_row.insertCell(3);
			new_row.style.backgroundColor = 'D0E0EE';				
							
		}
		function delCondi(){
			var Table1 = document.getElementById("tableadddel");
			if (Table1.rows.length > 1){ 
	            Table1.deleteRow(Table1.rows.length-1); 
	        }
		}
	</script>
</head>
<body>
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a><bean:write name="tips" scope="request" /></a>
				</p>
			</div>
	<html:form action="/xljk_xlcs_pcjgcx" method="post">
		<input type="hidden" name="search" id="search" value="1"/>
		<input type="hidden" name="condi" value="yz-tj-yzdf" id="condi"/>
		<input type="hidden" id="tableName" name="tableName"
				value="<bean:write name="tableName"/>" />
		<input type="hidden" id="realTable" name="realTable"
				value="<bean:write name="realTable"/>" />
		<logic:present name="sql">		
			<input type="hidden" id="sql" name="sql"
					value="<bean:write name="sql"/>" />	
			</logic:present>
		<logic:present name="yz">				
			<input type="hidden" id="yzB" name="yzB"
					value="<bean:write name="yz"/>" />
		</logic:present>
		<logic:present name="tj">				
			<input type="hidden" id="tjB" name="tjB"
					value="<bean:write name="tj"/>" />
		</logic:present>	
		<logic:present name="yzdf">				
			<input type="hidden" id="yzdfB" name="yzdfB"
					value="<bean:write name="yzdf"/>" />
		</logic:present>
		<input type="hidden" id="rowIndex" name="rowIndex"
					value="1" />								

		<div class="toolbox">
			<!-- 过滤条件 -->
					<div class="buttonbox">
						<ul>
							<li>
								<a href="#"
									onclick="dataExport()"
									class="btn_dc"> 导出 </a>
							</li>
					</div>
				<div class="searchtab">
					<table width="100%" border="0" id="tableadddel">
						<tfoot>
							<tr>
								<td colspan="6">
									<div class="btn">
										<input type="hidden" name="go" value="a" />
										<button class="btn_cx" id="search_go"
											onclick="getOkStudent();">
											查 询
										</button>
										<button class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
											重 置
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
						<tbody>
							<tr>
								<th>
									因子
								</th>
								<td>
									<select style="width:150px" name="yz" id="yz">
										<option value=""></option>
										<option value="qthyz">躯体化因子</option>
										<option value="qpzzyz">强迫症状因子</option>
										<option value="rjgxmgyz">人际关系敏感因子</option>
										<option value="yyyz">抑郁因子</option>
										<option value="jlyz">焦虑因子</option>
										<option value="ddyz">敌对因子</option>
										<option value="kbyz">恐怖因子</option>
										<option value="pzyz">偏执因子</option>
										<option value="jsbxyz">精神病性因子</option>
										<option value="fjyz">附加因子</option>
									</select>
								</td>
								<th>
									查询条件
								</th>
								<td>
									<select name="tj" style="width:150px" id="tj">
										<option value=""></option>
										<option value="1">等于</option>
										<option value="2">大于</option>
										<option value="3">小于</option>
										<option value="4">大于等于</option>
										<option value="5">小于等于</option>
									</select>
								</td>
								<th>
									因子得分
								</th>
								<td>
									<select name="yzdf" style="width:150px" id="yzdf">
										<option value=""></option>
										<option value="0">0</option>
										<option value="1">1</option>
										<option value="2">2</option>
										<option value="3">3</option>
										<option value="4">4</option>
									</select>	
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			
			<div class="formbox">
				<h3 class="datetitle_01">
					<span> 查询结果&nbsp;&nbsp; <logic:empty name="rs">
							<font color="red">未找到任何记录！</font>
						</logic:empty>
						<logic:notEmpty name="rs">
							<font color="blue">记录数：
							<bean:write name="rsNum" /> &nbsp;&nbsp;&nbsp;提示：双击一行可以查看详细信息；单击表头可以排序</font>
						</logic:notEmpty>	
					 </span>
				</h3>

				<logic:notEmpty name="rs">
				<div class="con_overlfow">
					<table summary="" class="dateline tablenowrap" align="" width="100%">
						<thead>
							<tr align="center" style="cursor:hand">
								<logic:iterate id="tit" name="topTr" offset="0">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)" nowrap>
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<tbody>
							<logic:iterate name="rs" id="s">
							<tr onclick="rowOnClick(this)" ondblclick="moreStuInfo()"
							style="cursor:hand">
								<td >
									<input type="hidden" id="XN_ID" name="XN_ID"
										value="<bean:write name="s" property="XN_ID"/>" />
									<bean:write name="s" property="xh"/>
								</td>
								<td>
									<bean:write name="s" property="xb"/>
								</td>
								<td>
									<bean:write name="s" property="nl"/>
								</td>
								<td>
									<bean:write name="s" property="zf"/>
								</td>
								<td>
									<bean:write name="s" property="zjf"/>
								</td>
								<td>
									<bean:write name="s" property="yxzztksp"/>
								</td>
							</tr>
						</logic:iterate>
						</tbody>
					</table>
					</div>
				</logic:notEmpty>
			</div>
	</html:form>
	<div id="toolTipLayer" style="position:absolute; visibility: hidden" /></div>
</body>
</html>