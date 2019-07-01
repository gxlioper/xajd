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
		document.forms[0].mappingItems.value = "";//���mappingItems�е�ֵ
		if (document.forms[0].mappingList.options.length <= 0) {
			alert("�����ֶβ���Ϊ��!");
			return false;
		}
		for (i = 0; i < document.forms[0].mappingList.options.length; i++) {
			document.forms[0].mappingItems.value = document.forms[0].mappingItems.value + "!!SplitOne!!";
			document.forms[0].mappingItems.value = document.forms[0].mappingItems.value + document.forms[0].mappingList.options[i].value;
		}
		document.forms[0].mappingItems.value = DWRUtil.byId("mappingItems").value.substr("!!SplitOne!!".length);//���е��ַ���ǰ���"!!SplitOne"
		
		var showConfirmText = "��ȷ��������ѡ���ֶ���";
		var showRunningText;
		
	    if (confirm(showConfirmText)) {
	    	var Items = document.getElementById("mappingItems").value;
			wjcfDataExport("xtwhOther.do?method=exportData"+"&mappingItems=" + Items );
		}
	    
		return false;
	}
</script>
</head>
<!-- ͷ�ļ� -->
	<body>		
		<html:form action="/xtwhOther.do" method="post">
			<input type="hidden" name="tableName" id="tableName" value="${tableName}"/>
			<input type="hidden" name="viewName" id="viewName" value="${viewName}"/>
			<input type="hidden" name="value" id="value" value="${value}"/>
			<input type="hidden" name="sql" id="sql" value="${sql}"/>
			<input type="hidden" name="mappingItems" id="mappingItems" value=""/>

			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>���ݵ���</a>
				</p>
			</div>
			<div class="tab">				
				<table width="100%" border="0" class="formlist" id="rsTable">
					<thead>
						<tr>
							<th><span>���ݿ���ֶ�</span></th>					
							<th></th>
							<th><span>�赼�����ֶ�</span></th>
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
									�������
							</button>							
				          </div>
				        </td>
				      </tr>
				    </tfoot>
				</table>
		</html:form>
	</body>
</html>
