<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	</head>
	<body onload="checkuser();">
		<script language="javascript">
		function add(){
			 var url = "/xgxt/xsgbxx.do?method=pdyjAdd&doType=add";
			 var tableName = document.getElementById("tableName").value;
			 //showOpenWindow(url, 650, 400);
			 //url += "&tableName="+tableName;
			 alert();
			 showTopWin(url, 850, 500);
		}
		function update(){
		  if(curr_row==null){
		       alert('��ѡ��Ҫ�����ļ�¼!\n(����һ�м���)');
		       return false;
		    } 
		   var tableName = document.getElementById("tableName").value;
		   var userlx = document.getElementById("userlx").value;
		  var url = "/xgxt/xsgbxx.do?method=pdyjAdd&doType=update&pk=";
		  var pk = curr_row.cells[0].getElementsByTagName("input")[0].value;
		  url += pk;
		  url += "&tableName="+tableName;
		  url += "&userlx="+userlx;
		  //showOpenWindow(url, 650, 400);
		  showTopWin(url, 850, 500);
		}
		function viewxljk(){
			  if(curr_row==null){
			       alert('��ѡ��Ҫ�����ļ�¼!\n(����һ�м���)');
			       return false;
			    } 
			  var tableName = document.getElementById("tableName").value;
			  var url = "/xgxt/xsgbxx.do?method=pdyjAdd&titleType=view&pk=";
			  var pk = curr_row.cells[0].getElementsByTagName("input")[0].value;
			  url += pk;
			  url += "&tableName="+tableName;
			  //showOpenWindow(url, 650, 400);
			  showTopWin(url, 650, 400);
			}
		//ȫ��ѡ��
		 function checValue(){
		     for(i=0;i<document.getElementsByName("pkV").length;i++){
		  	    document.getElementsByName("pkV")[i].checked=document.getElementsByName("pk")[0].checked;
		     }
		  }	
		 function xljkdel(){          
	           var url = "/xgxt/xsgbxx.do?method=xlzxsDel&go=go"; 
			   var RowsStr="";		  		 
			   var pkVArray = "'";
			   for (i=0; i<document.getElementsByName("pkV").length; i++){
		    	  if(document.getElementsByName("pkV")[i].checked){	    		 
		    		 pkVArray+=document.getElementsByName("pkV")[i].value+"','"
		    		 RowsStr+=document.getElementsByName("pkV")[i].value+"!!";
		    	  }	    	  
			   }		   
			   if (RowsStr==""){
				   alert("��ѡ��Ҫ�����ļ�¼��\n(����ÿ����¼ǰ��ѡ��)");
				   return false;
			   }
			   document.forms[0].pkVStr.value = RowsStr;
			   pkVArray=pkVArray.substring(0,pkVArray.length-2);		   
			   //if (confirm("ȷ��Ҫɾ����ѡ��¼��\n\n�¼����Ž��޷�ɾ���ϼ���������˵ļ�¼")){
			   if (confirm("ȷ��Ҫɾ����ѡ��¼��")){
				     refreshForm(url);
			   }         		                  
	 }
	 function zjlgdataExport() {
			document.forms[0].action = "/xgxt/xsgbxx.do?method=expData";
			document.forms[0].target = "_blank";
			document.forms[0].submit();
			document.forms[0].target = "_self";
		}
	 function checkuser(){
		 var username = document.getElementById("username").value;
		 if(username == "stu"){
			document.getElementById("xh").disabled = true;
		 }
		 if(username == "xy"){
				document.getElementById("xydm").disabled = true;
			 }
	 }
		</script>
		<script language="javascript" src="js/jsFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>

		<html:form action="/xsgbxx.do" method="post">
			<input type="hidden" name="zyV" id="zyV" />
			<input type="hidden" name="bjV" id="bjV" />
			<input type="hidden" id="userlx" name="userlx" value="<bean:write name="userlx"/>" />
			<input type="hidden" id="username" name="username" value="<bean:write name="userType" scope="session"/>" />
			<input type="hidden" id="tableName" name="tableName" value="view_xsgzgb" />
			<input type="hidden" id="realTable" name="realTable" value="view_xsgzgb" />
			<input type="hidden" id="pkVStr" name="pkVStr"  value="" />
			<div class="tab_cur" id="jd">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>��Ṥ��  - ѧ���ɲ�  - �������</a>
				</p>
			</div>
			<div class="toolbox">
				<logic:equal value="yes" name="writeAble" scope="request">
					<div class="buttonbox">
						<ul>
							<li>
								<a href="#" onclick="update();" class="btn_xg">����</a>
							</li>
							<li>
								<a href="#" onclick="zjlgdataExport()" class="btn_dc">���� </a>
							</li>
						</ul>
					</div>
					</logic:equal>
					<div class="searchtab">
					<table width="100%" border="0">
							<thead>
								<tr>
									<th>
										ѧ��
									</th>
									<td>
										<html:text property="xh"></html:text>
									</td>
									<th>	
										����
									</th>
									<td>
										<html:text property="xm"></html:text>
									</td>
									<th>	
										�Ա�
									</th>
									<td>
										<html:select property="xb">
											<html:option value=""></html:option>
											<html:option value="��">��</html:option>
											<html:option value="Ů">Ů</html:option>
										</html:select>
									</td>
								</tr>
								<tr>
									<th>
										<bean:message key="lable.xsgzyxpzxy" />
									</th>
									<td> 
										<html:select property="xydm" onchange="initBjList();"
											styleClass="select" style="width:150px;display: " styleId="xy">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm"
												labelProperty="xymc" />
										</html:select>
								  	</td>	
								  	<th>
								  		�༶
								  	</th>
								  	<td> 
										<html:select property="bjdm" style="width:150px;display: " 
											styleClass="select" styleId="bj">
											<html:option value=""></html:option>
											<html:options collection="bjList" property="bjdm"
												labelProperty="bjmc" />
										</html:select>
									</td>	
									<td colspan="2">
										
									</td>
								</tr>
							</thead>
							<tfoot>
							<tr>
								<td colspan="6">
									<input type="hidden" name="go" value="a" />
									<div class="btn">
										<button class="btn_cx" id="search_go"
											onclick="refreshForm('/xgxt/xsgbxx.do?method=pdyjManage&go=go');">
											��ѯ
										</button>
										<button class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
											����
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
						</table>
					</div>
					</div>
					<div class="formbox">
					<h3 class="datetitle_01">
						<span> ��ѯ���&nbsp;&nbsp; <logic:empty name="rs">
								<font color="red">δ�ҵ��κμ�¼��</font>
							</logic:empty> <logic:notEmpty name="rs">
								<font color="blue">��ʾ��˫��һ�п��Բ鿴��ϸ��Ϣ��������ͷ��������</font>
							</logic:notEmpty> </span>
					</h3>
					<logic:notEmpty name="rs">
					<div class="con_overlfow">
						<table summary="" id="rsTable" class="dateline" width="100%">
						<thead>
							<tr align="center" style="cursor:hand">
								<td>
									<input type="checkbox" name="pk" value="all" onclick="checValue();"/>
								</td>
								<logic:iterate id="tit" name="topTr" offset="1" length="12">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)" nowrap="nowrap">
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<tbody>
						<logic:iterate name="rs" id="s">
							<tr onclick="rowOnClick(this)" style="cursor:hand"
								ondblclick="update()">
								<td align="center">
									<logic:iterate id="v" name="s" offset="0" length="1">
										<input type="checkbox" name="pkV" value="<bean:write name="v"/>"/>
									</logic:iterate>
								</td>
								<logic:iterate id="v" name="s" offset="1">
									<td align="center">
										<bean:write name="v" />
									</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
						</tbody>
					</table>
					</div>
							<logic:notEqual value="12872" name="xxdm">
											<jsp:include flush="true"
												page="/sjcz/turnpage.jsp?form=xsgbxxForm"></jsp:include>
							</logic:notEqual>
					</logic:notEmpty>
					</div>
					

			<div id="tmpdiv"></div>
		</html:form>
		<logic:equal name="done" value="yes">
			<script language="javascript">
      				alert("�����ɹ���");
	  		</script>
		</logic:equal>
		<logic:equal name="done" value="no">
			<script language="javascript">
	  				alert("����ʧ��! ");
	  		</script>
		</logic:equal>
	</body>
</html>
