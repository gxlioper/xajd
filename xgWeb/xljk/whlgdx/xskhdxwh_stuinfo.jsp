<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/jsFunction.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
	<script language="javascript" src="js/AjaxFunction.js"></script>
	<script type="text/javascript">
		function xljk_stu_send(){
			if(curr_row != null){
				var xh=curr_row.cells[0].innerText.replace(/^\s+/g,"").replace(/\s*$/g,"");
				//window.dialogArguments.document.forms[0].doType.value = "per_stu";
				window.dialogArguments.document.forms[0].xh.value = xh; 
				//window.dialogArguments.document.forms[0].submit();
				window.dialogArguments.document.getElementById("getStuInfo").click();
				Close();
			}
		}	
	</script>
	</head>
	<body onload="initPage();initBjList()">
		<center>
			<html:form action="/xljk_whlgdx_xskhdxwh" method="post">
				<div class="tab_cur">
					<p class="location">
						<em>您的当前位置:</em><a>学生信息 - 信息学查询 - 个人信息</a>
					</p>
				</div>
				<input type="hidden" name="njV" id="njV" />
				<input type="hidden" name="xyV" id="xyV" />
				<input type="hidden" name="zyV" id="zyV" />
				<input type="hidden" name="bjV" id="bjV" />
				<div class="searchtab">
					<table width="100%" class="" border="0">
						<tbody>
							<tr>
								<th>年级</th>
								<td><html:select property="nj" style="width:150px">
									</html:select></td>
								<th>学号</th>
								<td><input type="text" name="xh" id="xh" style="width:150px" /></td>
								<th>姓名</th>
								<td><input type="text" name="xm" id="xm" style="width:150px" />
								</td>
							</tr>
							<tr>
								<th><bean:message key="lable.xsgzyxpzxy" /></th>
								<td><html:select property="xydm" style="width:150px" styleId="xy"
										onchange="initZyList();initBjList()">
									</html:select></td>
								<th>专业</th>
								<td><html:select property="zydm" style="width:150px" styleId="zy"
										onchange="initBjList()">
									</html:select>
								</td>
								<th>班级</th>
								<td><html:select property="bjdm" style="width:150px" styleId="bj">
									</html:select></td>
								
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="6">
								<div class="btn">
								<button name="go" id="search_go" onclick="allNotEmpThenGo('xljk_whlgdx_xskhdxwh.do?doType=stu_info&action=stu_info')">
									查询
								</button>
								 <button class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
									重置
								 </button>
								</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
				<div class="formbox">
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
								查询结果&nbsp;&nbsp;<font color="blue">双击一行可以查看详细信息；单击表头可以排序</font> 
							</span>
						</h3>
						<table width="100%" id="rsTable" class="dateline">
							<thead>
								<tr align="center" style="cursor:hand">
									<logic:iterate id="tit" name="topTr">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)">
											<bean:write name="tit" property="cn" />
											<br/>
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<logic:iterate name="rs" id="s">
								<tr onclick="rowOnClick(this)" style="cursor:hand"
									ondblclick="xljk_stu_send()">
									<td>
										<bean:write name="s" property="xh"/>
									</td>
									<td>
										<bean:write name="s" property="xm"/>
									</td>
									<td>
										<bean:write name="s" property="xb"/>
									</td>
									<td>
										<bean:write name="s" property="nj"/>
									</td>
									<td>
										<bean:write name="s" property="xz"/>
									</td>
									<td>
										<bean:write name="s" property="bjmc"/>
									</td>
								</tr>
							</logic:iterate>
						</table>
				 
				 	<!--分页显示-->
				     <jsp:include flush="true" page="/sjcz/turnpage.jsp?form=xljk_XskhdxwhForm"></jsp:include>
					<!--分页显示-->
				</logic:notEmpty>
				</div>
			</html:form>
		</center>
	</body>
	<logic:present name="closeWindow">
		<script>
			Close();
		</script>
	</logic:present>
</html>
