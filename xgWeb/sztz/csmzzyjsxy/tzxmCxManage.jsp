<%@ page language="java" contentType="text/html; charset=GBK"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
	<head>
	<%@ include file="/syscommon/head.ini"%>
		<title><bean:message key="lable.title" />
		</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control" http-equiv="no-cache" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="������� zfsoft" />
	</head>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />

	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<body onload="bjDisabled('nj-xy-zy-bj');myxyDisabled('xy');removeXnXq();">
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/sztzFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getSztzData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script language="javascript" src="js/AjaxFunction.js"></script>
		
    <html:form action="/csmz_sztz.do?method=data_search" method="post">
			<input type="hidden" name="zyV" id="zyV" />
			<input type="hidden" name="bjV" id="bjV" />
			<input type="hidden" id="tableName" name="tableName"
				value="<bean:write name="tableName" scope="request"/>" />
			<input type="hidden" id="act" name="act"
				value="<bean:write name="act" scope="request"/>" />
			<input type="hidden" id="pk" name="pk"
				value="<bean:write name="pk" scope="request"/>" />
			<input type="hidden" id="userType" name="userType"
				value="<bean:write name="userType" scope="request"/>" />
			<input type="hidden" id="realTable" name="realTable"
				value="<bean:write name="realTable" scope="request"/>" />
			<input type="hidden" id="xxdm" name="xxdm"
				value="<bean:write name="xxdm" scope="request"/>" />
		     <input type="hidden" name="userName" id="userName" 
		        value="<bean:write name="userName" scope="session"/>">	
		     <input type="hidden" id="delPk" name="delPk" value="" />   	
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${tips }</a>
				</p>
			</div>
			<div class="toolbox">
				 <!-- ��ť -->
				 <div class="buttonbox">
				 	<logic:equal value="yes" name="writeAble" scope="request">
				 		 <ul>
						  <logic:equal value="xx" name="userType"><%-- ѧУ�û��ſɼ�--%>
							<li> <a href="#" onclick="showTopWin('/xgxt/csmz_sztz.do?method=tzxmAdd&doType=add','700','500')" class="btn_zj"> ���� </a> </li>
				   			<li> <a href="#" onclick="modiXm()" class="btn_xg"> �޸� </a> </li>
							<li> <a href="#" onclick="MyMoreDo('del')" class="btn_sc"> ɾ�� </a> </li>
                          </logic:equal>						
						<li> <a href="#" onclick="dataExport()" class="btn_dc"> ���� </a> </li>
						<logic:equal value="12104" name="xxdm">
							<!-- ����ְҵ����<bean:message key="lable.xsgzyxpzxy" />ѧУ���� -->
						    <li> <a href="#" onclick="sqbPrint()" class="btn_dy"> ��ӡ </a> </li>
						</logic:equal>
				    	</ul>
				    </logic:equal>
				 </div>
			

				<div class="searchtab">
					<table width="100%" border="0">
							 <tfoot>
						        <tr>
						          <td colspan="6">
						            <div class="btn">
						              <input type="hidden" name="go" value="a" />
						              <button class="btn_cx" id="search_go" 
						              	onclick="document.forms[0].go.value='go';refreshForm('/xgxt/csmz_sztz.do?method=data_search&act='+$('act').value)">
						              	�� ѯ
						              </button>
						              &nbsp;&nbsp;&nbsp;&nbsp;
						              <button class="btn_cz" id="btn_cz"  	onclick="searchReset();return false;">
						              	�� ��
						              </button>
						            </div>
						          </td>
						        </tr>
						      </tfoot>
							
		      				<tbody>
								<tr>
									<th align="left">
										ѧ��
									</th>
									<td>
										<html:select property="xn" style="width:100px" styleId="xn">
										<html:options collection="xnList" property="xn"
												labelProperty="xn" />
										</html:select>
									</td>
									<th>
										ѧ��
									</th>
									<td>
										<html:select property="xq" style="width:90px" styleId="xq">
											<html:option value=""></html:option>
											<html:options collection="xqList" property="xqdm"
												labelProperty="xqmc" />
										</html:select>
									</td>
									<th>
										������Ŀ
									</th>
									<td>
										<html:select property="kmdm" style="width:150px"
											styleId="kmdm">
											<html:option value=""></html:option>
											<html:options collection="kmList" property="kmdm"
												labelProperty="kmm" />
										</html:select>
									</td>	
								</tr>
								<tr>
									<th>	
										��Ŀ����
									</th>
									<td>
										<html:text property="xmmc" styleId="xmmc"></html:text>
									</td>
									<th>										
										����
									</th>
									<td>
										<html:select property="xydm" styleId="xy" 
											style="background-color:#FFFFFF;width=150px">
											<option value=""></option>
											<html:options collection="bmList" property="bmdm"
												labelProperty="bmmc"></html:options>
										</html:select>
									</td>
									<th>��Ŀ����</th>
									<td>
										<html:select property="xmjb" styleId="xmjb"
											style="background-color:#FFFFFF;">											
											<html:options collection="xmjbList" property="en"
												labelProperty="cn"></html:options>
										</html:select>
									</td>
								</tr>
						</tbody>
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
			 		 <logic:notEmpty name="rs">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;						
							<font color="blue">��ʾ��˫��һ�п��Բ鿴��Ŀ��ϸ��Ϣ��ѧ���걨����Ŀ�ɹ������������ͷ��������;</font>	
			 		 </logic:notEmpty>
			    </span>
			    </h3>
				<logic:notEmpty name="rs">
						  <table summary="" class="dateline" align="" width="100%">		<thead>
									<tr align="center" style="cursor:hand">
<%--									<td>--%>
<%--										<input type="checkbox" name="fyxx" value="all"--%>
<%--											onclick="chec()">--%>
<%--									</td>--%>
										<logic:iterate id="tit" name="topTr" offset="2">
											<td id="<bean:write name="tit" property="en"/>"
												onclick="tableSort(this)" >
												<bean:write name="tit" property="cn" />
											</td>
										</logic:iterate>
									</tr>
								</thead>
								<tbody>
									<logic:iterate name="rs" id="s">
										<tr onclick="rowOnClick(this);"
											style="cursor:hand;"
											ondblclick="viewXm()">
<%--													<td>--%>
<%--										<logic:iterate id="v" name="s" offset="0" length="1">--%>
<%--											<input type="checkbox" name="pkV"--%>
<%--												value="<bean:write name="v"/>">--%>
<%--										</logic:iterate>--%>
<%--								       	</td>--%>
											<td>
												<logic:iterate id="v" name="s" offset="1" length="1">
													<input type="hidden" value="<bean:write name="v"/>" />
												</logic:iterate>
												<logic:iterate id="v" name="s" offset="2" length="1">
													<bean:write name="v" />
												</logic:iterate>
											</td>
											<logic:iterate id="v" name="s" offset="3">
												<td >
													<bean:write name="v" />
												</td>
											</logic:iterate>
										</tr>
									</logic:iterate>	
								</tbody>							
							</table>
								<jsp:include flush="true"
									page="/sjcz/turnpage.jsp?form=sztzForm"></jsp:include>		
									<script type="text/javascript">
										$('choose').className="hide";
									</script>		
				</logic:notEmpty>
				</div>		
	</html:form>	
	<script type="text/javascript" src="js/bottomButton.js"></script>
	<logic:notEmpty name="result">	
		<logic:equal name="result" value="true">
			<script type="text/javascript">
				alert("�����ɹ���");
			</script>
		</logic:equal>	
		<logic:notEqual name="result" value="true">
			<script type="text/javascript">
				alert("����ʧ�ܣ�");
			</script>
		</logic:notEqual>	
	</logic:notEmpty>
    </body>
<script type="text/javascript">
function modiXm(){
       if (curr_row == null) {
		     alert("��ѡҪ�޸ĵļ�¼��\n����һ�м�¼����");
		     return false;
	   } else {
	        if(confirm("ȷ��Ҫ�޸ĸü�¼��")){
	             var url = "/xgxt/csmz_sztz.do?method=tzxmAdd&doType=modi";
	             url +="&pkValue=";
	             url +=(curr_row.cells[0].getElementsByTagName("input"))[0].value;
	            showTopWin(url,'750','500')
	        }else{	 
	            return false;           	            
	        }
       }   
}
function viewXm(){
<%--       if (curr_row == null) {--%>
<%--		     alert("��ѡҪ�޸ĵļ�¼��\n����һ�м�¼����");--%>
<%--		     return false;--%>
<%--	   } else {--%>
<%--	        if(confirm("ȷ��Ҫ�޸ĸü�¼��")){--%>
	             var url = "/xgxt/csmz_sztz.do?method=tzxmAdd&doType=modi";
	             url +="&pkValue=";
	             url +=(curr_row.cells[0].getElementsByTagName("input"))[0].value;
	             url +="&view=toview"
	            showTopWin(url,'750','500')
<%--	        }else{	 --%>
<%--	            return false;           	            --%>
<%--	        }--%>
<%--     }--%>
    
}
function chec(){
       for(i=0;i<document.getElementsByName("pkV").length;i++){
      	     document.getElementsByName("pkV")[i].checked=document.getElementsByName("fyxx")[0].checked;
       }
}
function myxyDisabled(ele) {
    var userT ="";
    if($("userType")){
       userT = $("userType").value;
    }
	if (userT == "xy"||userT =="stu") {
		var tmp = ele.split("-");
		for (i = 0; i < tmp.length; i++) {
			document.getElementById(tmp[i]).disabled = true;
		}
	}
}
function sqbPrint(){
       if (curr_row == null) {
		     alert("��ѡҪ��ӡ�Ļ��Ŀ��\n����һ�м�¼����");
		     return false;
	   } else {
	         var url = "/xgxt/csmz_sztz.do?method=sqbPrint";
	         url +="&pkValue=";
	         url +=(curr_row.cells[0].getElementsByTagName("input"))[0].value;
	         document.forms[0].action = url;
	         document.forms[0].target = "_blank";
	         document.forms[0].submit();
	         document.forms[0].target = "_self";
       }     
}
</script>
</html>	
