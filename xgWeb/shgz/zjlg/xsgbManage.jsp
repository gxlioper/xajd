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
			 var url = "/xgxt/xsgbxx.do?method=xsgbxxwhAdd&doType=add";
			 var tableName = document.getElementById("tableName").value;
			 //showOpenWindow(url, 650, 400);
			 //url += "&tableName="+tableName;
			 showTopWin(url, 800, 600);
		}
		function update(){
		  if(curr_row==null){
		       alert('��ѡ��Ҫ�����ļ�¼!\n(����һ�м���)');
		       return false;
		    } 
		   var tableName = document.getElementById("tableName").value;
		  var url = "/xgxt/xsgbxx.do?method=xsgbxxwhAdd&doType=update&pk=";
		  var pk = curr_row.cells[0].getElementsByTagName("input")[0].value;
		  url += pk;
		  url += "&tableName="+tableName;
		  //showOpenWindow(url, 650, 400);
		  showTopWin(url, 800, 600);
		}
		function viewxljk(){
			  if(curr_row==null){
			       alert('��ѡ��Ҫ�����ļ�¼!\n(����һ�м���)');
			       return false;
			    } 
			  var tableName = document.getElementById("tableName").value;
			  var url = "/xgxt/xsgbxx.do?method=xlzxsAdd&titleType=view&pk=";
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
	 function shgzsczp(){
		 if (curr_row == null) {
			alert("��ѡ��Ҫ�ϴ���Ƭ��ѧ����\n��������Ӧ���У�");
			return false;
		 }else {
			 var pk = curr_row.cells[0].getElementsByTagName("input")[1].value;
			 var url =  "/xgxt/xsgbxx.do?method=sczp";
			 url += "&pk="+pk;
			 showTopWin(url, 500, 200);
			
		 }
	}
		</script>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/jsFunction.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script language="javascript" src="js/AjaxFunction.js"></script>
		<script type='text/javascript'
			src='/xgxt/dwr/interface/GetListData.js'></script>

		<html:form action="/xsgbxx.do" method="post">
			<input type="hidden" name="zyV" id="zyV" />
			<input type="hidden" name="bjV" id="bjV" />
			<input type="hidden" id="username" name="username"
				value="<bean:write name="userType" scope="session"/>" />
			<input type="hidden" id="tableName" name="tableName"
				value="zjlg_xsgbxxb" />
			<input type="hidden" id="realTable" name="realTable"
				value="zjlg_xsgbxxb" />
			<input type="hidden" id="pkVStr" name="pkVStr" value="" />
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ�ã�</em><a>��Ṥ�� - ѧ���ɲ� - ѧ���ɲ���Ϣ</a>
				</p>
			</div>
			<div class="toolbox">
				<logic:equal value="yes" name="writeAble" scope="request">
					<div class="buttonbox">
						<ul>
							<li>
								<a href="#" onclick="add();" class="btn_zj"> ���� </a>
							</li>
							<li>
								<a href="#" onclick="update();" class="btn_xg"> �޸� </a>
							</li>
							<logic:notEqual value="stu" name="userType" scope="session">
								<li>
									<a href="#" onclick="xljkdel()" class="btn_sc"> ɾ�� </a>
								</li>
								<li>
									<a href="#" onclick="shgzsczp()" class="btn_up"> �ϴ���Ƭ </a>
								</li>
								<li>
									<a href="#" onclick="impAndChkData();" class="btn_dr"> ���� </a>
								</li>
								<li>
									<a href="#" onclick="zjlgdataExport()" class="btn_dc"> ���� </a>
								</li>
							</logic:notEqual>
						</ul>
					</div>
				</logic:equal>
				<div class="searchtab">
						<table width="100%" border="0">
							<tbody>
								<tr>
									<th>
										ѧ��
									</th>
									<td>
										<html:text property="xh" style="width:85px"></html:text>
									</td>
									<th>
										����
									</th>
									<td>
										<html:text property="xm" style="width:85px"></html:text>
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
											styleClass="select" style="width:150px;" styleId="xy">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm"
												labelProperty="xymc" />
										</html:select>
									</td>
									<th>
										�༶
									</th>
									<td>
										<html:select property="bjdm" style="width:150px;"
											styleClass="select" styleId="bj">
											<html:option value=""></html:option>
											<html:options collection="bjList" property="bjdm"
												labelProperty="bjmc" />
										</html:select>
									</td>
									<td colspan="2"></td>
								</tr>
							</tbody>
							<tfoot>
							 	<tr>
							 		<td colspan="6" >
										<input type="hidden" name="go" value="a" />
										<div class="btn">
					             			 <button class="btn_cx" id="search_go" 
												onclick="refreshForm('/xgxt/xsgbxx.do?method=xsgbxxwhManage&go=go');">
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
				    <span>
				    	��ѯ���&nbsp;&nbsp;
				    	<logic:empty name="rs">
							<font color="red">δ�ҵ��κμ�¼��</font> 
				 		 </logic:empty>
				 		 <logic:notEmpty name="rs" >
				 		 		<font color="blue">��ʾ��˫��һ�п��Բ鿴��ϸ��Ϣ��������ͷ��������</font>
				 		 </logic:notEmpty>
				    </span>
				    </h3>
					<logic:notEmpty name="rs">
					<div class="con_overlfow">
						 <table summary="" id="rsTable" class="dateline" width="100%">
								<thead>
									<tr align="center" style="cursor:hand">
										<td>
											<input type="checkbox" name="pk" value="all"
												onclick="checValue();" />
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
												<input type="checkbox" name="pkV"
													value="<bean:write name="v"/>">
											</logic:iterate>
											<logic:iterate id="v" name="s" offset="1" length="1">
												<input type="hidden" value="<bean:write name="v"/>">
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
