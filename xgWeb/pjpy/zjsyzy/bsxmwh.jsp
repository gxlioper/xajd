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
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
	<head>
		<title><bean:message key="lable.title" /></title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control" http-equiv="no-cache" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="正方软件 zfsoft" />
		<script type="text/javascript">
		</script>
	</head>
	<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<body>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/String.js"></script>
		<script language="javascript" src="js/AjaxFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script type="text/javascript">
			function modidata(url,w,h){
				if(curr_row == null){
					alert("请选择一行要修改的记录！");
					return false;
				}
				var pkValue = curr_row.cells[1].innerText.trim();
				if(pkValue=='0002' || pkValue=='0001'){					
					alert('代码为0001和0002的项目为默认的项目，不能进行修改操作！');
					return false;
				}
				
				showTopWin(url+pkValue,w,h);				
			}
			
			function del(url){
				var RowsStr="!!";		
				for (i=0; i<document.getElementsByName("cbv").length; i++){
			    	if(document.getElementsByName("cbv")[i].checked){
			    		RowsStr+=document.getElementsByName("cbv")[i].value+"!!";
			    	}
				}
				
				if (RowsStr=="!!"){
					alert("请选择要批量设置的记录！");
					return false;
				}
				
				if (!confirm("确定要删除所选记录？")){
					return false;
				}
				refreshForm(url);
			}
		</script>
		<html:form action="/pjpy_zjsyzy.do" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前位置：评奖评优 - 参数设置 - 比赛项目维护
				</div>
			</div>
			<input type="hidden" id="userType" name="userType" value="${userType }"/>
			<input type="hidden" name="realTable" id="realTable" value="${realTable}"/>
			<input type="hidden" name="tableName" id="tableName" value="${tableName}"/>
			<input type="hidden" name="mes" id="mes" value="${mes}"/>
			<input type="hidden" name="xyV" value=""/>
			<input type="hidden" name="zyV" value=""/>
			<input type="hidden" name="bjV" value=""/>
			<fieldset>
				<legend>
					查询条件
				</legend>
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
							<td align="left">
								 所属项目：
								<html:select property="ssjfxm" styleId="ssjfxm">
								<html:option value=""></html:option>
								<html:options collection="ssjfxmList" property="en" labelProperty="cn"/>
								</html:select>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								操作类型：
								<html:select property="czlx" styleId="czlx">
								<html:option value=""></html:option>
								<html:options collection="czlxList" property="en" labelProperty="cn"/>
								</html:select>
							</td>
							<td width="10" align="center" valign="middle">
								<input type="hidden" name="go" value="a" />
								<button type="button" class="button2" id="search_go"
									onclick="allNotEmpThenGo('pjpy_zjsyzy.do?method=bsxmwh');">
									查询
								</button>
							</td>
						</tr>
					</thead>
				</table>
			</fieldset>
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					未找到任何记录！
				</p>
			</logic:empty>
			<logic:notEmpty name="rs">
				<fieldset>
					<legend>
						记录数：
						<bean:write name="rsNum" />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<font color="blue">提示：单击表头可以进行排序；双击一行可以查看详细信息；</font>
					</legend>
					<table width="100%" id="rsTable" class="tbstyle">
						<thead>
							<tr align="center" style="cursor:hand">
								<td nowrap>
									<input type="checkbox" id="cb" name="cb" onclick="selectAll()" />
								</td>
								<logic:iterate id="tit" name="topTr" offset="0">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)" nowrap>
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<logic:iterate name="rs" id="s">
							<tr onclick="rowOnClick(this)"
								style="cursor:hand;" align="center" bgcolor="" ondblclick="">
								<td>
									<input type="hidden" value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>"/>
									<input type="checkbox" id="pk" name="cbv" value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>" />
								</td>							
								<logic:iterate id="v" name="s" offset="0">
									<td>
										<bean:write name="v" />
									</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
					</table>
				</fieldset>
			</logic:notEmpty>
			<div id="toolTipLayer" style="position:absolute; visibility: hidden" /></div>
				<logic:equal value="yes" name="writeAble" scope="request">
					<center>
						<div class="buttontool" id="btn"
							style="position: absolute;left:1%;top:100px" width="100%">
							<button type="button" class="button2" id="btn_add"
								onclick="showTopWin('pjpy_zjsyzy.do?method=showAddBsxm','400','300')"
								style="width:80px">
								增 加
							</button>
							&nbsp;&nbsp;&nbsp;
							<button type="button" class="button2" id="btn_edit"
								onclick="modidata('pjpy_zjsyzy.do?method=showBsxmInfo&pkValue=','500','400')"
								style="width:80px">
								修 改
							</button>
							<logic:notEqual value="xy" name="userType">
							&nbsp;&nbsp;&nbsp;
							<button type="button" class="button2" id="btn_delete"
								onclick="del('pjpy_zjsyzy.do?method=delBsxm')"
								style="width:80px">
								删 除
							</button>
							</logic:notEqual>
							<logic:equal value="xy" name="userType">
							&nbsp;&nbsp;&nbsp;
							<button type="button" class="button2" id="btn_delete"
								onclick="if(confirm('初始化将清除设置所有项目对应的分数,确定初始化吗？')){refreshForm('pjpy_zjsyzy.do?method=initMark')}"
								style="width:80px">
								初始化
							</button>
							</logic:equal>
							&nbsp;&nbsp;&nbsp;
							<button type="button" class="button2" id="btn_impdata"
								onclick="impAndChkData()"
								style="width:80px">
								导入数据
							</button>
							&nbsp;&nbsp;&nbsp;
							<button type="button" class="button2" id="btn_expdata"
								onclick="dataExport('/xgxt/expData.do?tableName=bsxmb')"
								style="width:80px">
								导出数据
							</button>		
							</div>					
					</center>
				</logic:equal>
			<div id="tmpdiv"></div>
		</html:form>
		<script language="javascript">
			document.getElementById("btn").style.pixelTop = document.body.clientHeight - 35;
			document.getElementById("btn").style.width = "96%";
			window.setInterval("initBTNTool('btn')",1);
		</script>
		<logic:present name="result">
			<logic:equal value="true" name="result">
			<script>
					alert('操作成功!');
					document.getElementById('search_go').click();
				</script>	
			</logic:equal>
			<logic:equal value="false" name="result">
			<script>
				alert(document.getElementById('mes').value);				
			</script>
		</logic:equal>	
		</logic:present>		
	</body>
</html>
