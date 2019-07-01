<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	
	<script language="javascript" src="js/jsFunction.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script language="javascript">
		function add(){
			var tableName = document.getElementById("tableName").value;
			 var url = "/xgxt/zjlg_xljk.do?method=xlzxsAdd&titleType=add";
			 //showOpenWindow(url, 650, 400);
			  url += "&tableName="+tableName;
			 showTopWin(url, 650, 400);
		}
		function update(){
		  if(curr_row==null){
		       alert('��ѡ��Ҫ�����ļ�¼!\n(����һ�м���)');
		       return false;
		    } 
		  var tableName = document.getElementById("tableName").value;
		  var url = "/xgxt/zjlg_xljk.do?method=xlzxsAdd&titleType=update&pk=";
		  var pk = curr_row.cells[0].getElementsByTagName("input")[0].value;
		  url += pk;
		  url += "&tableName="+tableName;
		  //showOpenWindow(url, 650, 400);
		  showTopWin(url, 650, 400);
		}
		function viewxljk(){
			  if(curr_row==null){
			       alert('��ѡ��Ҫ�����ļ�¼!\n(����һ�м���)');
			       return false;
			    } 
			  var tableName = document.getElementById("tableName").value;
			  var url = "/xgxt/zjlg_xljk.do?method=xlzxsAdd&titleType=view&pk=";
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
	           var url = "/xgxt/zjlg_xljk.do?method=xlzxsDel&go=go"; 
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
			document.forms[0].action = "/xgxt/zjlg_xljk.do?method=expData";
			document.forms[0].target = "_blank";
			document.forms[0].submit();
			document.forms[0].target = "_self";
		}
	</script>
	</head>
	
	<body onload="">
	
		<html:form action="/zjlg_xljk.do" method="post">
			<input type="hidden" name="zyV" id="zyV" />
			<input type="hidden" name="bjV" id="bjV" />
			<input type="hidden" id="tableName" name="tableName" value="zjlg_xlzxxszl" />
			<input type="hidden" id="realTable" name="realTable" value="zjlg_xlzxxszl" />
			<input type="hidden" id="pkVStr" name="pkVStr"  value="" />
			
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a><bean:write name="titleName"/></a>
				</p>
			</div>
			<logic:equal value="yes" name="writeAble">
				<div class="toolbox">
				<div class="buttonbox">	
					<ul>
						<li><a href="#" class="btn_zj" onclick="add();">����</a></li>
						<li><a href="#" class="btn_xg" onclick="update();">�޸�</a></li>
						<logic:notEqual value="stu" name="userType" scope="session">
						<li><a href="#" class="btn_sc" onclick="xljkdel()">ɾ��</a></li>
						<li><a href="#" class="btn_dr" onclick="impAndChkData()">����</a></li>
						<li><a href="#" class="btn_dc" onclick="zjlgdataExport()">����</a></li>
						</logic:notEqual>								
					</ul>
				</div>
				</div>
			</logic:equal>
			<div class="searchtab">
				<table width="100%" class="tbstyle">
					<tbody>
						<tr>
							<th>����</th>
							<td><logic:equal value="stu" name="userType" scope="session">
								<html:text property="xm" style="width:85px" readonly="true"></html:text>
								</logic:equal>
								<logic:notEqual value="stu" name="userType" scope="session">
								<html:text property="xm" maxlength="30" style="width:85px" ></html:text>
								</logic:notEqual></td>
							<th>�Ա�</th>
							<td><html:select property="xb">
									<html:option value=""></html:option>
									<html:option value="��">��</html:option>
									<html:option value="Ů">Ů</html:option>
								</html:select></td>
							<th>����</th> 
							<td><html:text property="nl" style="width:85px"  onkeyup="value=value.replace(/[^\d]/g,'') "></html:text>
							</td>
						</tr>
						<tr>
							<th><bean:message key="lable.xsgzyxpzxy" /></th> 
							<td><logic:notEqual value="stu" name="userType" scope="session">
								<logic:equal value="xy" name="userType" scope="session">
								<html:select property="xy" onchange="initBjList();initZyList();"
								styleClass="select" style="width:150px;display: none" styleId="xy">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
								<html:text property="xymc" disabled="true"></html:text>
								</logic:equal>
								<logic:notEqual value="xy" name="userType" scope="session">
								<html:select property="xy" onchange="initBjList();initZyList();"
								styleClass="select" style="width:150px" styleId="xy">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
								</logic:notEqual>
								</logic:notEqual>
								<logic:equal value="stu" name="userType" scope="session">
								<html:select property="xy" onchange="" 
									styleClass="select" style="width:150px;display: none" styleId="xy">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
								<html:text property="xymc" disabled="true"></html:text>
								</logic:equal></td>
							<th>רҵ</th>
							<td><html:select property="zydm" style="width:150px" styleId="zy"
									onchange="initBjList();">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
								</html:select></td>
							<th>�༶</th>
							<td><logic:equal value="stu" name="userType" scope="session"> 
								<html:select property="bjdm" style="width:150px;display: none" 
								styleClass="select" styleId="bj">
									<html:option value=""></html:option>
									<html:options collection="bjList" property="bjdm"
										labelProperty="bjmc" />
								</html:select>
								<html:text property="bjmc" disabled="true"></html:text>
								</logic:equal>
									<logic:notEqual value="stu" name="userType" scope="session">
								<html:select property="bjdm" style="width:150px" 
								styleClass="select" styleId="bj">
									<html:option value=""></html:option>
									<html:options collection="bjList" property="bjdm"
										labelProperty="bjmc" />
								</html:select>
								</logic:notEqual>
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="6">
							<div class="btn">
							<button id="search_go" onclick="refreshForm('/xgxt/zjlg_xljk.do?method=xlzxsManage&go=go');">
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
			<div class="formbox">
				<logic:empty name="rs">
				    <h3 class="datetitle_01">
				    <span>
				    	��ѯ���&nbsp;&nbsp;
							<font color="red">δ�ҵ��κμ�¼��</font> 
				    </span>
				    </h3>
				 </logic:empty>
				<logic:notEmpty name="rs">
					<h3 class="datetitle_01">
						<span>
							��ѯ���&nbsp;&nbsp;<font color="blue">˫��һ�п��Բ鿴��ϸ��Ϣ��������ͷ��������</font> 
						</span>
					</h3>
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
								ondblclick="viewxljk()">
								<td align="center">
									<logic:iterate id="v" name="s" offset="0" length="1">
										<input type="checkbox" name="pkV" value="<bean:write name="v"/>" />
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
						<!--��ҳ��ʾ-->
					     <jsp:include flush="true" page="/sjcz/turnpage.jsp?form=zjlg_xljkForm"></jsp:include>
						<!--��ҳ��ʾ-->
						<br />
					</logic:notEqual>
					</logic:notEmpty>
					</div>
					<br />
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
