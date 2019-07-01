<%@ page language="java" contentType="text/html; charset=GBK"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean"
	prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic"
	prefix="logic"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
	<head>
		<title><bean:message key="lable.title" /></title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control" http-equiv="no-cache" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="������� zfsoft" />
	</head>
	<%
	response.setHeader("Pragma","No-cache");
	response.setHeader("Cache-Control","no-cache");
	response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
	<script language="javascript" src="/xgxt/js/AjaxFunction.js"></script>
	<script language="javascript" src="/xgxt/js/xgutil.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/qgzxgzkh.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/xszzCommonN05DWR.js'></script>
	<script language="javascript">
		var Rows=new Array();	//����ѡ�е��ж���
		var ShiftStartRow="";		//Shift��ѡʱ�洢��ʼ�ж���
		var cur_bgc = "#ffdead";//ѡ���б������ַ�����
		
		function rowMoreClick(objTr) {
		
		if (curr_row != null && curr_row.tagName.toLowerCase() == "tr") {
			curr_row.style.backgroundColor = obj_bgc;
		}
		curr_row = objTr;
		obj_bgc = curr_row.style.backgroundColor;
		curr_row.style.backgroundColor = cur_bgc;
			
		iRow=window.event.srcElement;
		do{
			iRow=iRow.parentElement;
		}while(iRow.tagName!='TR')

		//Ctrl��ѡ
		if(event.ctrlKey){
			var j=-1;
			for(i=0;i<Rows.length;i++){
				if(iRow==Rows[i]){
					j=i;
					break;
				}
			}
			if(j!=-1){
				for(i=j;i<Rows.length-1;i++){
					Rows[i]=Rows[i+1];
				}
				Rows.length=Rows.length-1;
				iRow.style.backgroundColor = "#FFFFFF";
			}else{
				Rows[Rows.length]=iRow;
			}
	//		ShiftStartRow=iRow;
		}
		else{	
			if (Rows.length!=0){
				for (i=0; i<Rows.length; i++){	
					if (Rows[i].tagName.toLowerCase() == "tr") {
						Rows[i].style.backgroundColor = "#FFFFFF";
		    		}
				}
			}
			Rows.length=1;
			Rows[0]=iRow;
			
	//		ShiftStartRow=iRow;
		}
		changeColor(iRow);
	}

	//ѡ���б�ɫ
	function changeColor(E){
		
	/*	for(var i=1;i<E.parentElement.rows.length;i++){
			E.parentElement.rows(i).style.backgroundColor=cur_bgc;
		}
	*/
		for(i=0;i<Rows.length;i++){
			Rows[i].style.backgroundColor=cur_bgc;	
		}
	}

	function selectAll(){
		var checkBoxArr = document.getElementsByName("pk");
		var selall = document.getElementById('cb');
		if(selall.checked==true){
			for(var i=0;i<checkBoxArr.length;i++){
				if (!checkBoxArr[i].disabled) {
					checkBoxArr[i].checked = true;
				}
			}
		} else {
			for(var i=0;i<checkBoxArr.length;i++){
				checkBoxArr[i].checked = false;
			}
		}
	}
	
	function del(url){
		var RowsStr= false;
		
		for (i=0; i<document.getElementsByName("pk").length; i++){
	    	if(document.getElementsByName("pk")[i].checked){
	    		RowsStr = true;
	    	}
		}
		if (!RowsStr){
			alert("��ѡ��Ҫ����ɾ���ļ�¼��");
			return false;
		}
		document.forms[0].action=url;
	    document.forms[0].submit();
	}
	
	function tg(url){
		var RowsStr= false;
		
		for (i=0; i<document.getElementsByName("pk").length; i++){
	    	if(document.getElementsByName("pk")[i].checked){
	    		RowsStr = true;
	    	}
		}
		if (!RowsStr){
			alert("��ѡ��Ҫ�����޸�Ϊͨ���ļ�¼��");
			return false;
		}
		document.forms[0].action=url;
	    document.forms[0].submit();
	}
	
	function btg(url){
		var RowsStr= false;
		
		for (i=0; i<document.getElementsByName("pk").length; i++){
	    	if(document.getElementsByName("pk")[i].checked){
	    		RowsStr = true;
	    	}
		}
		if (!RowsStr){
			alert("��ѡ��Ҫ���������ļ�¼��");
			return false;
		}
		document.forms[0].action=url;
	    document.forms[0].submit();
	}
	
	
	
	function add(url){
		return showTopWin(url,750,550);
	}
	
	function modi(url){
		if((curr_row == null) || (curr_row == "")){
			alert("��ѡ��Ҫ�޸ĵļ�¼��");
			return false;
		}
		url += "&pkVal=";
		url += curr_row.getElementsByTagName("input")[0].value;
		return showTopWin(url,750,550);
	}
	
	function dataExport2() {
		document.forms[0].action = "/xgxt/n05_xszz.do?method=wszxj1Exp";
		document.forms[0].target = "_blank";
		document.forms[0].submit();
		document.forms[0].target = "_self";
	}
	
	function chkAssisOne(url, act) {
		if (curr_row == null) {
			return false;
		} else {
			url += "&xmdm=";
			url += val('xmdm');
			url += "&pkVal=";
			url += curr_row.getElementsByTagName("input")[0].value;	
			url += "&operFlag=";
			url += 	curr_row.getElementsByTagName("input")[0].disabled;		
			url += "&shjg=";
			url += curr_row.cells[7].innerText;
			showTopWin(url, 750, 550);
			return true;
		}
	}	
	
	function tjjg(){
		url = "n05_xszz.do?method=wszxj1shDeptModel&go=tjjg";
		//�ж��Ƿ��Ѿ��ύ
		var pk = "bm||dm||zjz||tjxm||tjzt";
		var pkV = 'xy'+val('userDep') + val('xn') + val('xmdm') +"wszxj1" + "1";
		qgzxgzkh.checkExists('xszz_com_bmshtjb',pk,pkV,function(data){
			if(data){
				alert('�Ѿ��ύ����ʱ�����ٴ��ύ��');
				return false;
			}else{
				pk = "xn||zxjdm||xydm";
				pkV = val('xn')+val('xmdm')+val('userDep');				
				xszzCommonN05DWR.jlsfqbsh('wszxj1','xn||zxjdm',pk,pkV,val('shjb'),function(data){
					if(data){
						alert('��������δ���,��ʱ�����ύ�����');
						return false;
					}else{
						if(confirm('��ȷ��Ҫ�ύ��˽�����ύ��˽���󽫲����ٽ�����˲�����')){
							refreshForm(url);
						}
					}
				});				
			}
		});			
	}
	</script>
	<body onload="xyDisabled('xy');">
		<html:form action="/n05_xszz.do?method=wszxj1shdm" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã�ѧ������ - ��� - <bean:write name="xmmc"/>
				</div>
			</div>
			<input type="hidden" id="shjb" name="shjb" 
				value="${shjb}" />
			<input type="hidden" id="userDep" name="userDep" 
				value="<bean:write name="userDep" scope="session"/>" />
			<input type="hidden" id="userType" name="userType" 
				value="<bean:write name="userType" scope="request"/>" />
			<input type="hidden" id="tableName" name="tableName"
				value="<bean:write name="tableName" scope="request"/>" />
			<input type="hidden" id="realTable" name="realTable"
				value="<bean:write name="realTable" scope="request"/>" />
			<input type="hidden" id="pk" name="pk"
				value="<bean:write name="pk" scope="request"/>" />
			<input type="hidden" id="isQuery" name="isQuery"
				value="<bean:write name="isQuery" scope="request"/>" />
			<input type="hidden" name="xyV" value=""/>
			<input type="hidden" name="zyV" value=""/>
			<input type="hidden" name="bjV" value=""/>
			<input type="hidden" id="cbVal" name="cbVal" value="" />
			<input type="hidden" id="xmdm" name="xmdm"
				value="<bean:write name="xmdm" />">
			<fieldset>
				<legend>
					����ѡ��
				</legend>
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
							<td align="left">
								ѧ��
								<html:select property="xn" 
									styleId="xn" disabled="true">
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select>
								&nbsp;&nbsp;�꼶
								<html:select property="nj" onchange="initBjList()">
									<html:option value=""></html:option>
									<html:options collection="njList" property="nj"
										labelProperty="nj" />
								</html:select>
								&nbsp;&nbsp;<bean:message key="lable.xsgzyxpzxy" />
								<html:select property="xydm" onchange="initZyList();initBjList()" style="width:180px" styleId="xy">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
								<!--<bean:message key="lable.xsgzyxpzxy" />�û�-->
								<logic:equal value="xy" name="userType" scope="session">
								&nbsp;&nbsp;רҵ
								<html:select property="zydm" onchange="initBjList()" style="width:180px" styleId="zy">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
								</html:select>
								&nbsp;&nbsp;�༶
								<html:select property="bjdm" style="width:180px" styleId="bj">
									<html:option value=""></html:option>
									<html:options collection="bjList" property="bjdm"
										labelProperty="bjmc" />
								</html:select>
								</logic:equal>
							</td>
							<td width="10" rowspan="" align="center" valign="middle">
								<input type="hidden" name="go" value="a" />
								<button class="button2" style="height:40px" id="search_go"
									onclick="allNotEmpThenGo('/xgxt/n05_xszz.do?method=wszxj1shDeptModel&go=go')">
									��ѯ
								</button>
							</td>
						</tr>
						<!--<bean:message key="lable.xsgzyxpzxy" />�ύ��Ϣ-->
						<logic:equal value="xy" name="userType" scope="session">
						<tr>
							<td colspan="2">
								<font color="red"><b>�ύ�����<bean:write name="tjjg"/></b></font>
							</td>
						</tr>
						</logic:equal>
						<!--end<bean:message key="lable.xsgzyxpzxy" />�ύ��Ϣ-->						
					</thead>
				</table>
			</fieldset>
			<div id="result">
				<div class="searchcontent">
					<logic:empty name="rs">
						<p align="center">
							δ�ҵ��κμ�¼��
						</p>
					</logic:empty>
					<logic:notEmpty name="rs">
						<fieldset>
							<legend>
								��¼����
								${rsNum}
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<font color="blue">��ʾ��˫��һ�п��Բ鿴��ϸ��Ϣ��������ͷ��������</font>
							</legend>
							<table width="99%" id="rsTable" class="tbstyle">
								<thead>
									<tr align="center" style="cursor:hand">
									    <td nowrap>
											<input type="checkbox" id="cb" name="cb" onclick="selectAll()" />
										</td>
										<logic:iterate id="tit" name="topTr" offset="3" scope="request">
											<td id="${tit.en}"
												onclick="tableSort(this)" nowrap>
												${tit.cn}
											</td>
										</logic:iterate>
										<td>
											����
										<td>
									</tr>
								</thead>
								<logic:iterate name="rs" id="s">
									<tr onclick="rowOnClick(this)"
										ondblclick="chkAssisOne('/xgxt/n05_xszz.do?method=wszxj1shdmOne')"
										style="cursor:hand;background-color:
    <logic:iterate id="v" name="s" offset="1" length="1">
    <bean:write name="v"/>
    </logic:iterate>
     ">
										<td align=center><input type="checkbox" id="pk" name="pk" <logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate> 
										value="<logic:iterate id="v" name="s" offset="2" length="1"><bean:write name="v"/></logic:iterate>" />
										<logic:iterate id="v" name="s" offset="2" length="1">
											<input type="hidden" value="<bean:write name="v"/>" />
										</logic:iterate>
									    </td>
										<logic:iterate id="v" name="s" offset="3">
											<td align=center>
												<bean:write name="v" />
											</td>
										</logic:iterate>
										<td>
											<a href="#" onclick="rowOnClick(findParents(this,'TR'));chkAssisOne('/xgxt/n05_xszz.do?method=wszxj1shdmOne&doType=view')"><font color="blue">�鿴��ϸ</font></a>
										</td>
									</tr>
								</logic:iterate>
							</table>
							<TABLE width="99%" id="Table" class="tbstyle">
								<TR>
									<TD height=3></TD>
								</TR>
								<TR>
									<TD>
										<jsp:include flush="true"
											page="/sjcz/turnpage.jsp?form=xszzCommonN05ActionForm"></jsp:include>
									</TD>
								</TR>
								<TR>
									<TD height="3"></TD>
								</TR>
							</TABLE>
						</fieldset>
					</logic:notEmpty>
					<logic:equal value="yes" name="writeAble" scope="request">
	                	<div class="buttontool" align="center" id="btn" style="position:absolute;width:95%;top:100px">
							<button class="button2" onclick="tg('/xgxt/n05_xszz.do?method=wszxj1sh&go=tg');"
								style="width:80px">
								ͨ ��
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button class="button2" onclick="btg('/xgxt/n05_xszz.do?method=wszxj1sh&go=btg');"
								style="width:80px">
								��ͨ��
							</button>

							<!--ѧУ�û�-->
							<logic:notEqual value="xy" name="userType" scope="session">
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button class="button2" onclick="btg('/xgxt/n05_xszz.do?method=wszxj1sh&go=th');"
								style="width:80px">
								�˻�����
							</button>
							</logic:notEqual>
							<!--endѧУ�û�-->

							<!--<bean:message key="lable.xsgzyxpzxy" />�û�-->
							<logic:equal value="xy" name="userType" scope="session">
							<!--�������-->
							<logic:equal value="3" name="shjb">
								&nbsp;&nbsp;&nbsp;&nbsp;
								<button class="button2" onclick="btg('/xgxt/n05_xszz.do?method=wszxj1sh&go=th');"
									style="width:80px">
									�˻�����
								</button>
							</logic:equal>
							<!--end�������-->
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button class="button2" onclick="tjjg()" style="width:80px">
								�ύ���
							</button>
							</logic:equal>
							<!--end<bean:message key="lable.xsgzyxpzxy" />�û�-->

							&nbsp;&nbsp;&nbsp;&nbsp;
							<button class="button2" onclick="dataExport2()" style="width:80px">
								��������
							</button>
						</div>
						</logic:equal>
					<div id="tmpdiv"></div>
					</div>
				</div>
		</html:form>
		 <script type="text/javascript" src="js/bottomButton.js"></script> 
		 <logic:notEmpty name="result">
		 	<logic:equal value="yes" name="result">
		 		<script>
		 			document.getElementById('search_go').click();
		 		</script>
		 	</logic:equal>
		 </logic:notEmpty>
	</body>
</html>
