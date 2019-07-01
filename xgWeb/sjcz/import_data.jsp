<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/jsFunction.js"></script>
	</head>
	<body
		onload="title_m.innerHTML = window.dialogArguments.document.all('title_m').innerHTML;">
		<html:form action="/saveUpData" method="post">
			<div class="tab_cur">
				<div class="title_img" id="title_m"></div>
			</div>
			<html:hidden property="itemStr" styleId="itemList" />
			<input type="hidden" name="mappingItems" value="" />
			<input type="hidden" name="filepath"
				value="<%=request.getAttribute("filepath")%>" />
			<input type="hidden" name="tabName"
				value="<%=request.getAttribute("tabName")%>" />
			<html:hidden property="drms"/>			
			<div class="tab">
			  <table width="100%"  border="0" class="formlist">
			    <thead>
			    	<tr>
			        	<th colspan="4"><span>字段维护</span></th>
			        </tr>
			    </thead>

					<tbody>
						<tr align="center">
							<th>
								源数据列表
							</th>
							<th>
								目标表字段列表
							</th>
							<th>
								对应字段列表
							</th>
						</tr>
					<tr align="center">
						<td>
							<select name="excelItem" style="width:160px;" size="18" onmouseover=""
								id="excelList"></select>
						</td>
						<td>
							<html:select property="oracleItem" style="width:160px;" size="18"
								styleId="oracleList"
								ondblclick="if(document.forms[0].excelList.selectedIndex>=0) addItemList();">
								<html:options collection="orcleItemList"
									labelProperty="comments" property="column_name" />
							</html:select>
						</td>
						<td rowspan="2">
							<select name="mappingItem" style="width:200px;cursor:hand;"
								size="20" id="mappingList" ondblclick="deleteItemList()"></select>
						</td>
					</tr>
					<tr>
						<td colspan="2" align="center">
							<button type="button"   name="button1"
								style="width:70px" value=" + " onclick="addItemList()" >+</button>
							<button type="button"   name="button2"
								style="width:70px" value=" - " onclick="deleteItemList()" >-</button>
							<button type="button"  name="button3" style="width:70px"
								 value="默 认" onclick="defaultItemList()" >默 认</button>
							<br/>
							<input type="radio" id="defType" name="defType" value="1" checked="true" onclick="selRadioIndex('defType','1')">按顺序
							<input type="radio" id="defType" name="defType" onclick="selRadioIndex('defType','2')">按字段名
						</td>
					</tr>
					<tr>
						<td colspan="3" align="center">
						<div class="btn">
							<button type="button"   name="button1"
								 value="确 定" onclick="subList();" >确 定</button>
							<button type="button"  name="button2" type="reset"
								 value="重 置" onclick="clearList()" >重 置</button>
							<button type="button"  name="button2"
								 value="关 闭" onclick="Close();return false;" >关 闭</button>
						</div>
						</td>
					</tr>
					</tbody>
				</table>
			</div>
			<div id="tmpdiv"></div>
		</html:form>
		<script language="javascript">
var powerChanged = false;
var i = 1;
var j = 1;
var dpd = new Array();
var txt = document.forms[0].itemList.value;
var SplitSignOne = "!!SplitSignOne!!";
var SplitSignTwo = "!!SplitSignTwo!!";
var initStrToSplit = txt.split(SplitSignOne);

document.forms[0].excelList.options.length = 0;
for(i = 1;i<initStrToSplit.length;i++){
	dpd[i] = initStrToSplit[i].split(SplitSignTwo);
    document.forms[0].excelList.options[document.forms[0].excelList.options.length] = new Option(dpd[i][2],dpd[i][1]);
	
}
</script>
	</body>
</html>
