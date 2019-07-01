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
		<title><bean:message key="lable.title" />
		</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control"
			http-equiv="no-cache, must-revalidate" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="������� zfsoft" />
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script type='text/javascript'src='/xgxt/dwr/interface/getOtherData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/checkXsInfo.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	<script language="javascript" src="js/AjaxFunction.js"></script>
	<script language="javascript" src="js/xsgyglFunction.js"></script>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<script type="text/javascript">
			function zsjlAdd(){
			 var url = "/xgxt/zjcmxy_Gygl.do?method=zsjlAdd&doType=add";
			 //var tableName = document.getElementById("tableName").value;
			 //showOpenWindow(url, 650, 400);
			 //url += "&tableName="+tableName;
			 showTopWin(url, 680, 420);
			 //showOpenWindow(url, 680, 420);
		}
		function update(){
		  if(curr_row==null){
		       alert('��ѡ��Ҫ�����ļ�¼!\n(����һ�м���)');
		       return false;
		    } 
		   var tableName = document.getElementById("tableName").value;
		  var url = "/xgxt/zjcmxy_Gygl.do?method=zsjlAdd&doType=update&pk=";
		  var pk = curr_row.cells[0].getElementsByTagName("input")[0].value;
		  url += pk;
		  //url += "&tableName="+tableName;
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
		function zsjldel(doType){
		var url = "/xgxt/zjcmxy_Gygl.do?method=zsjldel&go=go&pkValue=";
		var pkValue = "";
			
		if (doType == "del") {
		   if (curr_row == null) {
			alert("��ѡ��Ҫɾ�������ݣ�\n��������Ӧ���У�");
			return false;
		    } else {
		    pkValue = curr_row.getElementsByTagName("input")[0].value;
		    checkXsInfo.checkXsInfo("","","","",pkValue,function initTjList(data){
					       if (data != "") {
								alert("�޷�ɾ���ϼ������Ѵ���ļ�¼");
								return ;
							}else{
								//showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
								//zsjlSave('xh-xn-xq-lddm-qsh-fs-wjsj-wjlbdm-wjsy');
								if (confirm("ȷ��Ҫɾ������������")) {
									url += pkValue;
									refreshForm(url);
									return true;
								} else {
									return false;
			}
							}	
					    });
		  }
		   return;
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
	 function expDatazjcm() {
			document.forms[0].action = "/xgxt/zjcmxy_Gygl.do?method=expData";
			document.forms[0].target = "_blank";
			document.forms[0].submit();
			document.forms[0].target = "_self";
		}
		function disabtext(){
			var userType = document.getElementById("userType").value;
			if(userType == "xy"){
				document.getElementById("xydm").disabled = true;
			}
		}
	</script>
	<body onload="bjLimit('bj');disabtext();">

		<html:form action="/zjcmxy_Gygl" method="post">
			<input type="hidden" id="tableName" name="tableName"
				value="<bean:write name="tableName" scope="request"/>" />
			<input type="hidden" id="act" name="act"
				value="<bean:write name="act" scope="request"/>" />
			<input type="hidden" id="realTable" name="realTable"
				value="<bean:write name="realTable" scope="request"/>" />
			<input type="hidden" id="dxq" name="dxq"
				value="<bean:write name="writeAble" scope="request"/>" />
			<input type="hidden" id="userType" name="userType"
				value="<bean:write name="userType" scope="session"/>" />
			<input type="hidden" name="lcV" id="lcV" value="" />
			<input type="hidden" name="qshV" id="qshV" />
			<input type="hidden" name="zyV" id="zyV" />
			<input type="hidden" name="bjV" id="bjV" />
			<input type="hidden" name="isFdy " id="isFdy" value="<bean:write name="fdyQx" scope="session"/>" />	
		     <div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã�
					��Ԣ���� - ס�޼��ɹ��� - ¼��
				</div>
			</div>

			<fieldset>
				<legend>
					��ѯ����
				</legend>
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
							<td align="left" nowrap>
								�꼶��
									<html:select property="nj"  onchange="initZyList();initBjList()">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select>
								&nbsp;&nbsp;ѧ�꣺
								<html:select property="xn" styleId="xn">
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select>
								&nbsp;&nbsp;ѧ�ڣ�
								<html:select property="xq"  styleId="xq">
									<html:option value=""></html:option>
									<html:options collection="xqList" property="xqdm"
										labelProperty="xqmc" />
								</html:select>								
								<bean:message key="lable.xsgzyxpzxy" />��
								<html:select property="xydm"  styleId="xy"
									onchange="initZyList();initBjList()">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
								
							</td>
							<td width="10" rowspan="4" align="center" valign="middle">
								<input type="hidden" name="go" value="a" />
								<button class="button2" style="height:40px" id="search_go"
									onclick="allNotEmpThenGo('/xgxt/zjcmxy_Gygl.do?method=zsjlInput&go=go');this.disabled=true">
									��ѯ
								</button>
							</td>
						</tr>
						<tr>
							<td align="left" nowrap>
								
								&nbsp;&nbsp;רҵ��
								<html:select property="zydm"  styleId="zy"
									onchange="initBjList()">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
								</html:select>
								&nbsp;&nbsp;
								�༶��
								<html:select property="bjdm"  styleId="bj">
									<html:option value=""></html:option>
									<html:options collection="bjList" property="bjdm"
										labelProperty="bjmc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<td align="left" nowrap>
<%--								<logic:present name="showhzy">--%>
								    ¥������
									<html:select property="lddm"  styleId="lddm"
										onchange="getLcList()">

										<html:options collection="ldList" property="lddm"
											labelProperty="ldmc" />
									</html:select>
										&nbsp;&nbsp;¥�㣺
										<html:select property="lc"  styleId="lc"
										onchange="getQshList2()">
										<html:options collection="lcList" property="dm"
											labelProperty="mc" />
									</html:select>
										&nbsp;&nbsp;���Һţ�
										<html:select property="qsh"  styleId="qsh">
										
										<html:options collection="qshList" property="dm"
											labelProperty="mc" />
									</html:select>
							</td>
						</tr>
						<tr>
							<td align="left" nowrap>
								�������
								<html:select property="wjlbdm" styleId="wjlbdm">
									<html:option value=""></html:option>
									<html:options collection="wjlbList" property="wjlbdm"
										labelProperty="wjlbmc" />
								</html:select>
								&nbsp;&nbsp;ѧ�ţ�
								<html:text property="xh" style="width: 90px" styleId="xh" />
								 &nbsp;&nbsp;������				
							    <html:text property="xm" styleId="xm" style="width:80px"></html:text>
								&nbsp;&nbsp;Υ��ʱ�䣺
								<html:text property="kssj" readonly="true"
									onblur="dateFormatChg(this)"
									onclick="return showCalendar('kssj','y-mm-dd','ag');"
									style="cursor:hand;width:80px " />
								-
								<html:text property="jssj" readonly="true"
									onblur="dateFormatChg(this)"
									onclick="return showCalendar('jssj','y-mm-dd','ag');"
									style="cursor:hand;width:80px " />
							</td>
						</tr>
					</thead>
				</table>
			</fieldset>

			<logic:empty name="rs">
				<p align="center">
					δ�ҵ��κμ�¼��
				</p>
			</logic:empty>

			<logic:notEmpty name="rs">
				<fieldset>
					<legend>
						��¼����
						<bean:write name="rsNum" />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<font color="blue">��ʾ��˫��һ�п��Բ鿴������Ϣ����������дά�������������ͷ��������</font>
					</legend>
					<table width="100%" id="rsTable" class="tbstyle">
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
						<logic:iterate name="rs" id="s">
							<tr onclick="rowMoreClick(this,'',event);" style="cursor:hand"
								ondblclick="update()">
								<td>
									<logic:iterate id="v" name="s" offset="0" length="1">
										<input type="hidden" value="<bean:write name="v"/>" />
									</logic:iterate>
									<logic:iterate id="v" name="s" offset="1" length="1">
										<bean:write name="v" />
									</logic:iterate>
								</td>
								<logic:iterate id="v" name="s" offset="2">
									<td>
										<bean:write name="v" />
									</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
					</table>
				</fieldset>
				<TABLE width="99%" id="rsTable" class="tbstyle">
					<TR>
						<TD height=3></TD>
					</TR>
					<TR>
						<TD>
							<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=zjcmxyForm"></jsp:include>
						</TD>
					</TR>
					<TR>
						<TD height=3></TD>
					</TR>
				</TABLE>
			</logic:notEmpty>
			<br>
			<br>
			<br>
			<div id="toolTipLayer" style="position:absolute; visibility: hidden" /></div>
			<center>
				<div class="buttontool" id="btn"
					style="position: absolute;left:1%;top:100px" width="100%">
					<logic:equal value="yes" name="writeAble" scope="request">
						<button class="button2" onclick="zsjlAdd();"
							style="width:80px">
							�� ��
						</button>							
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button class="button2" onclick="update();return false;"
							style="width:80px">
							�� ��
						</button>
							&nbsp;&nbsp;&nbsp;&nbsp;							
							<button class="button2" onclick="zsjldel('del')"
							style="width:80px">
							ɾ ��
						</button>							
<!--							&nbsp;&nbsp;&nbsp;&nbsp;-->
<!--							<button class="button2"-->
<!--							onclick="impAndChkData();"-->
<!--							style="width:80px">-->
<!--							��������-->
<!--						</button>-->
					</logic:equal>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="button2" onclick="expDatazjcm()" style="width:80px">
						��������
					</button>
					<logic:equal value="10822" name="xxdm">
						<!--�㶫����<bean:message key="lable.xsgzyxpzxy" />  -->
						&nbsp;&nbsp;&nbsp;&nbsp;
							<button class="button2"
							onclick="mydormDataExp('/xgxt/XsGyGlLogic.do?method=gdby_dormJlTj')"
							style="width:120px">
							������ɼ�¼��
						</button>
					</logic:equal>
				</div>
			</center>
		</html:form>

		<script language="javascript">
			document.getElementById("btn").style.pixelTop = document.body.clientHeight - 35;
			document.getElementById("btn").style.width = "96%";
			window.setInterval("initBTNTool('btn')",1);
			
			function chDate(){
				if($("gzkssj").value!=""&&$("gzjssj").value!=""){
					if($("gzkssj").value > $("gzjssj").value){
						alert("��ʼʱ����ڽ���ʱ�䣬��ȷ�ϣ���");
						return false;
					}
				}
				return true;
			}
		</script>
		<script type="text/javascript">
			function mydormDataExp(url){
				document.forms[0].action = url;
				document.forms[0].target = "_blank";
				document.forms[0].submit();
				document.forms[0].target = "_self";
			}
		</script>
		<logic:equal name="done" value="ok">
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
