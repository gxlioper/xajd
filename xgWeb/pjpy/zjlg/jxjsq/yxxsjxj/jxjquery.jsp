<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
<script type="text/javascript" src="js/pjpy/pjpy_dwr.js"></script>
<script type='text/javascript' src='/xgxt/dwr/interface/getJxjRs.js'></script>
<script type='text/javascript' src='/xgxt/dwr/interface/getJxjRstg.js'></script>
<script type='text/javascript' src='/xgxt/dwr/interface/getCpzfp.js'></script>
<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
<script language="javascript">
/*
ȫ��ѡ��
*/    
  function chec(){
     for(i=0;i<document.getElementsByName("pk").length;i++){
  	    document.getElementsByName("pk")[i].checked=document.getElementsByName("qbxz")[0].checked;
     }
  }		


	function viewMoreinfojxj(doType){
		var url ="/xgxt/zjlgPjpy.do?method=jxjUpdate&act=view&pkValue=";
		var pkValue = "";
		var jxjcxzj = "";
		if(curr_row.getElementsByTagName("input")[0]){
			jxjcxzj = curr_row.getElementsByTagName("input")[1].value;
		}
		 if (jxjcxzj != ""){
		 pkValue = curr_row.getElementsByTagName("input")[0].value;
		 var xh = curr_row.getElementsByTagName("input")[2].value;
		 var xn = curr_row.getElementsByTagName("input")[3].value;
		 url += pkValue+"&jxjcxzj="+jxjcxzj;
		 url += "&xh="+xh
		 url += "&xn="+xn
		 showOpenWindow(url, 800, 650,false);
		 }else{
		
		 }
		}


	
		function bysjbxxbupdate(doType){
		//var url ="/xgxt/bysbm.do?act=update&pkValue=";
		//var pkValue ="";
		//
		// if (doType == "update"){
		//    if (curr_row == null) {
		//	alert("��ѡ��Ҫ�޸ĵ����ݣ�\n��������Ӧ���У�");
		//	return false;
		//	 } else {
		//	if (confirm("ȷ��Ҫ�޸ĸ���������")) {
		//		 pkValue = curr_row.getElementsByTagName("input")[0].value;
		 //        url += pkValue;
		//         showTopWin(url, 600, 370);
		//		return true;
		//	} else {
		//		return false;
		//	}
		 //  }
	     // }	
		var url ="/xgxt/zjlgPjpy.do?method=jxjUpdate&act=update&pkValue=";
		var pkValue = "";
		var jxjcxzj = "";
		if(curr_row == null){
			alert("��ѡ���У���");
			return false;
		}
		if(curr_row.getElementsByTagName("input")[0]){
			jxjcxzj = curr_row.getElementsByTagName("input")[1].value;
		}
		 if (jxjcxzj != ""){
		 pkValue = curr_row.getElementsByTagName("input")[0].value;
		 

		 var xh = curr_row.getElementsByTagName("input")[2].value;
		 var xn = curr_row.getElementsByTagName("input")[3].value;
		 url += pkValue+"&jxjcxzj="+jxjcxzj;
		 url += "&xh="+xh
		 url += "&xn="+xn
		 showOpenWindow(url, 800, 650,false);
		 }else{
		
		 }	
		}

		function jxjreport(){
			var url ="/xgxt/zjlgPjpy.do?method=jxjReport&pkValue=";
			var pkValue = "";
			var jxjcxzj = "";
			if(curr_row == null){
				alert("��ѡ��Ҫ��ӡ���У���");
				return false;
			}
			if(curr_row.getElementsByTagName("input")[0]){
				jxjcxzj = curr_row.getElementsByTagName("input")[1].value;
			}
			 if (jxjcxzj != ""){
			 pkValue = curr_row.getElementsByTagName("input")[0].value;
			 
			 var xh = curr_row.getElementsByTagName("input")[2].value;
			 var xn = curr_row.getElementsByTagName("input")[3].value;
			 url += pkValue+"&jxjcxzj="+jxjcxzj;
			 url += "&xh="+xh
			 url += "&xn="+xn
			 
<%--			 showOpenWindow(url, "900", "700",false);--%>
				document.forms[0].action = url;
				document.forms[0].target = "_blank";
				document.forms[0].submit();
				document.forms[0].target = "_self";
			 }else{
			
			 }	
		}
		
		
		function dataExportjxj() {
	       document.forms[0].action = "/xgxt/zjlgPjpy.do?method=jxjDataExport&act=expData";
	       document.forms[0].target = "_blank";
	       document.forms[0].submit();
	       document.forms[0].target = "_self";
        }
        
//��ѯ��ʾ
function dataQryChk(url) {
	if (qryChk()) {
		if (confirm("��û��ѡ���κ��������˴β���������ȫ�����ݣ����ܻ�ķ��൱����ʱ�䡣ȷ��Ҫ������")) {
			refreshForm(url);
			if ($('search_go')) {
				document.getElementById('search_go').disabled=true;
			}
			return;
		} else {
			return;
		}
	} else {
		refreshForm(url);
		if ($('search_go')) {
				document.getElementById('search_go').disabled=true;
			}
		return;
	}
}

//���ݲ�ѯʱ����ʾ
function qryChk() {
	var sel = document.getElementsByTagName("select");
	for (i = 0; i < sel.length; i++) {
		if (sel[i].value != null && sel[i].value != '') {
			return false;
		}
	}
	if (document.getElementById('xh').value != '' && document.getElementById('xh').value != null) {
		return false;
	}
	return true;
}

//�޸ĺ�ɾ��
function modiAndDel(url,type,w,h) {
	if (curr_row==null || curr_row=='') {
		alert('��ѡ��Ҫ�����������У�');
		return;
	} else {
		if (type=='modi') {
			var realTable;
			if ($('realTable')) {
				realTable = document.getElementById('realTable').value;
			}
			url += curr_row.cells[0].getElementsByTagName("input")[0].value;
			url += '&realTable=';
			url += realTable;
			url += '&xh=';
			url += curr_row.cells[2].innerText;
			url += '&xn=';
			url += curr_row.cells[4].innerText;
			showTopWin(url,w,h);
		} else {
			if (confirm('ȷ��Ҫɾ����ѡ���������')) {
				refreshForm(url);
			} else {
				return;
			}
		}
	}
}

/*
����ɾ��
*/
  function del(url){
    var RowsStr="!!#!!";

    for (i=0; i<document.getElementsByName("pk").length; i++){
	   if(document.getElementsByName("pk")[i].checked){
		RowsStr+=document.getElementsByName("pk")[i].value+"!!#!!";
	   }
    }
    document.forms[0].pkstring.value = RowsStr;
       if (RowsStr=="!!#!!"){
         alert("��ѡ��Ҫ����ɾ���ļ�¼��");
	   return false;
	}

    if (!confirm("ȷ��Ҫɾ����ѡ��¼��")){
	   return false;
    }
    document.forms[0].action=url;
    document.forms[0].submit();
 }
	function pjpyjxjprint(){
		var jxjdm = document.getElementById("jxjdm").value;
		if(jxjdm == ""){
			alert("��ѡ��ѧ������");
			return false;
		}
		var xh = document.getElementById("xh").value;
		window.open('/xgxt/zjlgPjpy.do?method=jxjReport&pkValue='+xh+'&jxjdm='+jxjdm)
	}
	function isshfs(){
		if ($('yesNo')) {
			var shfs = document.getElementById("yesNo").value;
			if(shfs=="azxy"){
				document.getElementById("xydm").disabled = false;
				document.getElementById("cpzdm").disabled = true;
				document.getElementById("zydm").disabled = true;
				document.getElementById("bjdm").disabled = true;
			}else if(shfs=="azcpz"){
				document.getElementById("xydm").disabled = false;
				document.getElementById("cpzdm").disabled = false;
				document.getElementById("zydm").disabled = true;
				document.getElementById("bjdm").disabled = true;
				//initCpzList();
			}else if(shfs=="azbj"){
				document.getElementById("xydm").disabled = false;
				document.getElementById("cpzdm").disabled = true;
				document.getElementById("zydm").disabled = false;
				document.getElementById("bjdm").disabled = false;
			}	
		}
	}
</script>
</head>
<body onload="isshfs();">
	<html:form action="/zjlgPjpy" method="post">
    	<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>�������� - ��ѧ������ - ��������ѯ</a>
				</p>
			</div>
			
			<logic:notEqual value="stu" name="userType" scope="session">
			<div class="toolbox">
			<div class="buttonbox">	
				<ul>
					<li><a href="#" class="btn_zj" id="btn" onclick="refreshForm('zjlgPjpy.do?method=yxxsjxjsq');">����</a></li>
					<li><a href="#" class="btn_xg" id="btn_modi" onclick="bysjbxxbupdate();">�޸�</a></li>
					<li><a href="#" class="btn_sc" onclick="del('zjlgPjpy.do?method=jxjQuery&jxjdel=jxjdel')">ɾ��</a></li>
					<li><a href="#" class="btn_dr" onclick="impAndChkData()">����</a></li>
					<li><a href="#" class="btn_dc" onclick="dataExportjxj()">����</a></li>	
					<li><a href="#" class="btn_dy" onclick="jxjreport();">��ӡ</a></li>									
				</ul>
			</div>
			</div>
			</logic:notEqual>
    	
    	<input type="hidden" id="zyV" name="zyV" value=""/>
    	<input type="hidden" id="bjV" name="bjV" value=""/>
    	<input type="hidden" id="pt" name="pt" />
    	<input type="hidden" id="userType" name="userType" value="${userType }"/>
    	<input type="hidden" id="tableName" name="tableName" value="xsjxjb"/>
    	<input type="hidden" id="realTable" name="realTable" value="xsjxjb"/>
    	<input type="hidden" id="failInfo" name="failInfo" value="${whichpk }"/><!-- ����ɾ����Ϣ��ʾ -->
    	<input type="hidden" name="pkstring" value="" />
			<div class="searchtab">
				<table width="100%" class="" border="0">
					<tbody>
						<tr>
						<th align="left">�꼶</th>
						<td><html:select property="nj" styleId="nj" styleClass="select" style="width:90px">
								<html:option value=""></html:option>
								<html:options collection="njList" property="nj" labelProperty="nj"/>
							</html:select>
						</td>
						<th>ѧ��</th>
						<td><html:select name="xnmap" property="xn" style="width:90px;display:none" styleClass="select"
								styleId="xn">
								<html:options collection="xnList" property="xn"
									labelProperty="xn" />
							</html:select>
							<input id="xnxdt" name="xnxdt" value="<bean:write name="xnmap" property="xn"/>" disabled="disabled"/>
						</td>	
						<th>��ѧ��</th>
						<td><html:select property="jxjdm" styleClass="select"
								styleId="jxjdm" ><html:option value=""></html:option>
								<html:options collection="jzxjxmList" property="jxjdm"
									labelProperty="jxjmc" />
							</html:select>
						</td>
						</tr>
						<logic:notEqual value="stu" name="userType" scope="session">
							<tr>
							<th>ѧ��</th>
							<td><html:text property="xh" styleId="xh" styleClass="inputtext"
							 style="width:100px"></html:text></td>
							<th>����</th>
							<td><html:text property="xm" styleId="xm" styleClass="inputtext" style="width:100px"></html:text></td>
							<th>��ʾ��ʽ</th>
							<td><html:select property="yesNo" onchange="isshfs();" styleId="yesNo">
									<html:option value="azxy"><bean:message key="lable.xsgzyxpzxy" /></html:option>
									<html:option value="azcpz">������</html:option>
									<html:option value="azbj">�༶</html:option>
								</html:select>
							</td>
							</tr>
						</logic:notEqual>
						<logic:equal value="stu" name="userType" scope="session">
							<tr><th>ѧ��</th>
							<td><input id="xhdt" name="xhdt" value="<bean:write name="userName" scope="session"/>" disabled="disabled"/>
							<input style="display: none" id="xh" name="xh" value="<bean:write name="userName" scope="session"/>" readonly="readonly"/></td>
								<th>����</th>
								<td><input id="xmdt" name="xmdt" value="<bean:write name="userNameReal" scope="session"/>" disabled="disabled"/>
								<input style="display: none" id="xm" name="xm" value="<bean:write name="userNameReal" scope="session"/>" readonly="readonly"/></td>
							</tr>
						</logic:equal>
			
						<logic:equal value="xx" name="whotype">
						<tr>
							<th><bean:message key="lable.xsgzyxpzxy" /></th>
							<td><html:select property="xydm" onchange="initZyList();initBjList();initCpzList();"
								styleClass="select" style="width:180px" styleId="xy">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select></td>
								<th>רҵ</th>
								<td><html:select property="zydm" onchange="initBjList();" style="width:150px" 
								styleClass="select" styleId="zy">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
								</html:select></td>
								<th>�༶</th>
								<td><html:select property="bjdm" style="width:150px" 
								styleClass="select" styleId="bj">
									<html:option value=""></html:option>
									<html:options collection="bjList" property="bjdm"
										labelProperty="bjmc" />
								</html:select></td>
								</tr>
								<tr>
								<th><input type="hidden" name="xyV" value=""/>������</th>
								<td><html:select property="cpzdm" style="width:150px" styleId="cpz"
									onchange="initjxjcpzBjList();">
									<html:option value=""></html:option>
									<html:options collection="cpzList" property="cpzdm"
										labelProperty="cpzmc" />
									</html:select>
								<input type="hidden" name="cpzV" value=""/>
								<input type="hidden" name="zyV" value=""/>
								<input type="hidden" name="bjV" value="" />
								<input type="hidden" name="cpzV" value="" />
							</td>
							<th></th>
							<td></td>
							<th></th>
							<td></td>
							</tr>
							</logic:equal>
							<logic:notEqual value="xx" name="whotype">
							<logic:equal value="xy" name="whotype">
							<tr>
								<th><bean:message key="lable.xsgzyxpzxy" /></th>
								<td><html:select property="xydm" onchange="initZyList();initBjList();initCpzList();"
								styleClass="select" style="width:180px;display:none" styleId="xy">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
								<input id="xybmmc" name="xybmmc" disabled="disabled" value="<bean:write name="xybmmc"/>"/>
									<input type="hidden" name="xyV" value=""/>
								</td>
								<th>רҵ</th>
								<td><html:select property="zydm" onchange="initBjList();" style="width:180px" 
								styleClass="select" styleId="zy">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
								</html:select></td>
								<th>�༶</th>
								<td><html:select property="bjdm" style="width:180px" 
								styleClass="select" styleId="bj">
									<html:option value=""></html:option>
									<html:options collection="bjList" property="bjdm"
										labelProperty="bjmc" />
								</html:select>
								</td>
								</tr>
								<tr>
								<th>������</th>
								<td><html:select property="cpzdm" style="width:150px" styleId="cpz"
									onchange="initjxjcpzBjList();">
									<html:option value=""></html:option>
									<html:options collection="cpzList" property="cpzdm"
										labelProperty="cpzmc" />
									</html:select>
								<input type="hidden" name="cpzV" value=""/>
								<input type="hidden" name="zyV" value=""/>
							
								<input type="hidden" name="bjV" value="" />
								<input type="hidden" name="cpzV" value="" />
								</td>
								<th></th>
								<td></td>
								<th></th>
								<td></td>
								</tr>
								</logic:equal>
							<logic:equal value="stu" name="whotype">
								<html:select property="xydm" onchange="initZyList();initBjList()"
								styleClass="select" style="width:180px;display: none" styleId="xy">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
								<html:select property="zydm" onchange="initBjList()" style="width:180px;display: none" 
								styleClass="select" styleId="zy">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
								</html:select>
								<html:select property="bjdm" style="width:180px;display: none" 
								styleClass="select" styleId="bj">
									<html:option value=""></html:option>
									<html:options collection="bjList" property="bjdm"
										labelProperty="bjmc" />
								</html:select>
								</logic:equal>
							</logic:notEqual>
					
					</tbody>
					
				<tfoot>
					<tr>
						<td colspan="6">
						<div class="btn">
							<input type="hidden" name="act" value="qry" />
								<button type="button" class="button2" id="search_go"
									onclick="refreshForm('zjlgPjpy.do?method=jxjQuery&go=go');">
									��ѯ
								</button>
								 <button type="button" class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
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
					<table width="100%" id="rsTable" class="dateline">
						<thead>
							<tr align="center" style="cursor:hand">
								<td>
									<input type="checkbox" name="qbxz" value="all"
										onclick="chec('qbxz')"/>
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
								ondblclick="viewMoreinfojxj('view')">
								<logic:iterate id="v" name="s" offset="0" length="1">
									<input type="hidden" value="<bean:write name="v"/>" />
								</logic:iterate>
								<logic:iterate id="v" name="s" offset="1" length="1">
									<input type="hidden" id="jxjcxzj" value="<bean:write name="v"/>" />
								</logic:iterate>
									<logic:iterate id="v" name="s" offset="3" length="1">
									<input type="hidden" id="xh" value="<bean:write name="v"/>" />
								</logic:iterate>
								<logic:iterate id="v" name="s" offset="2" length="1">
									<input type="hidden" id="xn" value="<bean:write name="v"/>" />
								</logic:iterate>
								<td align="center">
									<logic:iterate id="v" name="s" offset="0" length="1">
										<input type="checkbox" name="pk"
											value="<bean:write name="v"/>"/>
									</logic:iterate>
								</td>
								<logic:iterate id="v" name="s" offset="2" length="12">
									<td align="center">
										<bean:write name="v" />
									</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
						</tbody>
					</table>
				<!--��ҳ��ʾ-->
			     <jsp:include flush="true" page="/sjcz/turnpage.jsp?form=zjlgPjpyForm"></jsp:include>
				<!--��ҳ��ʾ-->

			</logic:notEmpty>
			
				<div id="tmpdiv"></div>
			</div>
	</html:form>
	 <script type="text/javascript" src="js/bottomButton.js"></script>
	 <logic:present name="deleted">
	 	<logic:equal value="yes" name="deleted">
	 		<script>
	 			alert('�����ɹ���');
	 			document.getElementById('search_go').onclick();
	 		</script>
	 	</logic:equal>
	 	<logic:equal value="no" name="deleted">
	 		<script>
	 			alert('' + document.getElementById('failInfo').value());
	 			document.getElementById('search_go').onclick();
	 		</script>
	 	</logic:equal>
	 </logic:present> 
</body>
</html>