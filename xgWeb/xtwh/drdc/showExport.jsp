<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/syscommon/pagehead_V4.ini"%>
<script language="javascript" src="js/stuinfoFunction.js"></script>
<script language="javascript" src="js/xgutil.js"></script>
<script>
	function submitList(){
		var i;
		document.forms[0].mappingItems.value = "";//清空mappingItems中的值
		if (document.forms[0].mappingList.options.length <= 0) {
			alert("导出字段不能为空!");
			return false;
		}
		for (i = 0; i < document.forms[0].mappingList.options.length; i++) {
			document.forms[0].mappingItems.value = document.forms[0].mappingItems.value + "!!SplitOne!!";
			document.forms[0].mappingItems.value = document.forms[0].mappingItems.value + document.forms[0].mappingList.options[i].value;
		}
		document.forms[0].mappingItems.value = DWRUtil.byId("mappingItems").value.substr("!!SplitOne!!".length);//剪切掉字符串前面的"!!SplitOne"
		
		var showConfirmText = "您确定导出所选的字段吗？";
		var showRunningText;
		
	    if (confirm(showConfirmText)) {
	    	var Items = document.getElementById("mappingItems").value;
			wjcfDataExport("xtwhOther.do?method=exportData"+"&mappingItems=" + Items );
		}
	    
		return false;
	}
</script>
</head>
<!-- 头文件 -->
	<body>		
		<html:form action="/xtwhOther.do" method="post">
			<input type="hidden" name="tableName" id="tableName" value="${tableName}"/>
			<input type="hidden" name="viewName" id="viewName" value="${viewName}"/>
			<input type="hidden" name="value" id="value" value="${value}"/>
			<input type="hidden" name="sql" id="sql" value="${sql}"/>
			<input type="hidden" name="mappingItems" id="mappingItems" value=""/>

			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>数据导出</a>
				</p>
			</div>
			<div class="tab">				
				<table width="100%" border="0" class="formlist" id="rsTable">
					<thead>
						<tr>
							<th><span>数据库表字段</span></th>					
							<th></th>
							<th><span>需导出的字段</span></th>
						</tr>
					</thead>
					<tbody>
					<tr>
						<td align="right">
							<html:select property="xmdm" style="width:300px;" size="18"
									styleId="excelList" ondblclick="addOneItemList()">
									<html:options collection="list" property="en" labelProperty="cn" />								
							</html:select>
						</td>
						<td align="center">
							<div class="btn">
							<button type="button" name="button1"
									onclick="defaultAllItemList()"
									style="width:80px">
									 >> 
							</button>
							<br/><br/><br/>

							<button type="button" name="button2"
									onclick="addOneItemList()"
									style="width:80px">
									 > 
							</button>							
							<br/><br/><br/>

							<button type="button" name="button3"
									onclick="deleteItemList()"
									style="width:80px">
									 < 
							</button>
							<br/><br/><br/>

							<button type="button" name="button4"
									onclick="clearList()"
									style="width:80px">
									  << 
							</button>
							</div>
						</td>
						<td>
							<html:select property="xmkg" style="width:300px;" size="18"
									styleId="mappingList" ondblclick="deleteItemList()">		
							</html:select>
						</td>
					</tr>
					</tbody>
					<tfoot>
				      <tr>
				        <td colspan="4">
				          <div class="btn">
				            <button type="button" id="btn_add"
									onclick="submitList()"
									style="width:80px">
									数据输出
							</button>							
				          </div>
				        </td>
				      </tr>
				    </tfoot>
				</table>
		</html:form>
	</body>
</html>
