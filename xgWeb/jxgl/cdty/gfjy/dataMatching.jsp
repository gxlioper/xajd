<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- ͷ�ļ� -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript">
	function submitList(){
		var i;
		var dzyDdTab = document.getElementById('dzyDdTab').value;
	    if (document.forms[0].mappingList.options.length <= 0) {
		    alert("��Ӧ�ֶβ���Ϊ��!");
		    return false;
	    }
	
		var showConfirmText;
		var showRunningText;
	
		var flag = DWRUtil.byId("flag").value;
		if(flag == '1'){//�������ݼ��
			showConfirmText = "ȷ���������ݼ����";
			showRunningText = "������ڽ��У����Ժ�......";
			
		} else if(flag == '2'){//ֱ�ӵ�������
			showConfirmText = "ȷ���������ݼ����";
			showRunningText = "���ڵ������ݣ����Ժ�......";
			
		}   
	    if (confirm(showConfirmText)) {
	    	for (i = 0; i < document.forms[0].mappingList.options.length; i++) {
	    		document.forms[0].mappingItems.value = document.forms[0].mappingItems.value + "!!SplitOne!!";
	    		document.forms[0].mappingItems.value = document.forms[0].mappingItems.value + document.forms[0].mappingList.options[i].value;
	    	}
	    	document.forms[0].mappingItems.value = DWRUtil.byId("mappingItems").value.substr("!!SplitOne!!".length);
			var dd_html = "";
			dd_html += "<table width='200' class='formlist'><tr><td height='60' align='center'>"+showRunningText+"<br><br>";
			dd_html += "<img src='images/loading.gif' width='180' height='20' border='0'>";
			dd_html += "</td></tr></table>";
			for (i = 1; i < document.getElementsByTagName("table").length; i++) {
				///document.getElementsByTagName("table")[i].style.display = "none";
			}
			showDiv(dd_html, 300, 120);
			if (''== dzyDdTab) {
				document.forms[0].action = 'cdtyGfjy.do?method=checkAndImport';
			} else {
				document.forms[0].action = 'cdtyGfjy.do?method=checkAndImportZdy';
			}
						
			document.forms[0].submit();
			return true;
		}
	    
		return false;
	}
	</script>
</head>
	<body onload="">
		<html:form action="/impAndChkData.do" method="post">
			<input type="hidden" name="mappingItems" id="mappingItems" value="" />
			<input type="hidden" name="filepath" id="filepath" value="${ filepath }" />
			<input type="hidden" name="realTable" id="realTable" value="${ realTable }" />
			<input type="hidden" name="tableName" id="tableName" value="${ tabName }" />
			<input type="hidden" name="drnj" id="drnj" value="${ drnj }" />
			<input type="hidden" id="dzyDdTab" name="dzyDdTab" value="${ dzyDdTab }" />
			<input type="hidden" name="drms" id="drms" value="${ drms }" />
			<input type="hidden" name="flag" id="flag" value="${ flag }" />
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>��Ϣ�˶� - �ֶ�ƥ��</a>
				</p>
			</div>
			<div class="tab">
		  	<table width="100%" border="0" class="formlist">
				<thead>
					<tr>
						<logic:equal name="drms" value="0">
							<th colspan="5"><span>�ֶ�ƥ��</span></th>
						</logic:equal>
						<logic:notEqual name="drms" value="0">
							<th colspan="4"><span>�ֶ�ƥ��</span></th>
						</logic:notEqual>
					</tr>
					<tr>
						<logic:equal name="drms" value="0">
						<td colspan="2">
							Դ�����б�
						</td>
						</logic:equal>
						<logic:notEqual name="drms" value="0">
						<td>
							Դ�����б�
						</td>
						</logic:notEqual>
						<td>
							Ŀ����ֶ��б�
						</td>
						<td>
							��Ӧ�ֶ��б�
						</td>
					</tr>
				</thead>
				<tbdoy>
					<tr>
						<logic:present name="drms">
						<logic:equal value="0" name="drms">
						<%--��������--%>
						<td>
							<table border="0" align="top" class="formlist">
								<tr>
									<th>������</th>
								</tr>							
								<logic:iterate id="zd" name="pkList">
									<tr>
										<th>
											<span class="red"><bean:write name="zd"/></span>
										</th>
									</tr>
								</logic:iterate>
							</table>
						</td>
						</logic:equal>
						</logic:present>
						
						<td>
							<html:select property="excelList" style="width:200px;" size="18"
								styleId="excelList">
								<html:options collection="excelListCollection" property="col" labelProperty="col" />								
							</html:select>
						</td>
						<td>
							<html:select property="oracleItem" style="width:200px;" size="18"
								styleId="oracleList"
								ondblclick="if(document.forms[0].excelList.selectedIndex>=0) addOneItemList();">
								<html:options collection="orcleItemList"
									labelProperty="comments" property="column_name" />
							</html:select>
						</td>
						<td rowspan="2">
							<select name="mappingItem" style="width:300px;cursor:hand;"
								size="20" id="mappingList" ondblclick="deleteItemList()"></select>
						</td>
					</tr>
					<tr>

					<logic:equal value="0" name="drms">
						<td colspan="3">
							<div class="btn">
								<button type="button" onclick="addOneItemList()" name="button1">
					          		 + 
					          	</button>
								<button type="button" onclick="deleteItemList()" name="button2">
					          		 - 
					          	</button>
								<button type="button" onclick="defaultAllItemList()" name="button3">
					          		Ĭ ��
					          	</button>
							</div>
						</td>
					</logic:equal>
					
					<logic:notEqual value="0" name="drms">
						<td colspan="2">
							<div class="btn">
								<button type="button" onclick="addOneItemList()" name="button1">
					          		 + 
					          	</button>
								<button type="button" onclick="deleteItemList()" name="button2">
					          		 - 
					          	</button>
								<button type="button" onclick="defaultAllItemList()" name="button3">
					          		Ĭ ��
					          	</button>
							</div>
						</td>
					</logic:notEqual>	
					</tr>

					<tr>
					<logic:equal value="0" name="drms">
					<td colspan="4">
						<div align="center">
							<button type="button" onclick="submitList();return false;" name="button1">
				          		ȷ ��
				          	</button>
							<button type="button" onclick="clearList();return false;" name="button2">
				          		�� ��
				          	</button>
							<button type="button" onclick="Close();return false;" name="button2">
				          		�� ��
				          	</button>
						</div>
					</td>
					</logic:equal>

					<logic:notEqual value="0" name="drms">
						<td colspan="3">
							<div align="center">
								<button type="button" onclick="submitList();return false;" name="button1">
					          		ȷ ��
					          	</button>
								<button type="button" onclick="clearList();return false;" name="button2">
					          		�� ��
					          	</button>
								<button type="button" onclick="Close();return false;" name="button2">
					          		�� ��
					          	</button>
							</div>
						</td>
					</logic:notEqual>								
					</tr>
				</tbdoy>
			</table>
			<table width="100%" align="center" class="formlist">				
				<thead>
					<tr align="center">							
						<th colspan="4">
							<span>excel�����Ҫ�����ݿ�����ƥ�����Ϣ</span>
						</th>
					</tr>						
				</thead>
				<tbody>
				<tr>
					<th>
						<input type="checkbox" name="yzsjzd" value="xm"/>
					</th>
					<th>
						����
					</th>
					<th>
						<input type="checkbox" name="yzsjzd" value="sfzh"/>
					</th>
					<th>
						���֤��
					</th>
				</tr>
				</tbody>
			</table>
		</div>
		<div id="tmpdiv"></div>
		</html:form>
	</body>
	<logic:equal name="checkResult" value="true">
	    <script type="text/javascript">
	        alert("лл����Ŭ������������û���κ����⣡��ֱ�ӵ������ݣ�");
	    </script>
	</logic:equal>
	
	<logic:equal name="importResult" value="true">
	    <script type="text/javascript">
	        alert("���ݵ���ɹ���");
	        if(opener.document.getElementById("importResult")){
				opener.document.getElementById("importResult").value = "yes";
			}
			if(opener.document.getElementById("search_go")){
				opener.document.getElementById("search_go").click();
	      		window.close();
			}
	    </script>
	</logic:equal>
</html>
