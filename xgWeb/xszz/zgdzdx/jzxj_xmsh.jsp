<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type="text/javascript" src="/xgxt/dwr/interface/getOtherData.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script language="javascript" src="/xgxt/js/AjaxFunction.js"></script>
		<script language="javascript">
		</script>
		<script language="javascript">
		var Rows=new Array();	//����ѡ�е��ж���
		var ShiftStartRow="";		//Shift��ѡʱ�洢��ʼ�ж���
		var cur_bgc = "#ffdead";//ѡ���б������ַ�����
		
		function rowOver(objTr) {//
			curr_row = objTr;
		}
		
		function rowOut(objTr) {//
			curr_row = null;
		}
		
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

function del(url){
	var RowsStr="!#!";
	
	for (i=0; i<document.getElementsByName("pkCh").length; i++){
    	if(document.getElementsByName("pkCh")[i].checked){
    		RowsStr+=document.getElementsByName("pkCh")[i].value+"!#!";
    	}
	}
	document.forms[0].pkDel.value = RowsStr;
	
	if (RowsStr=="!#!"){
		alert("��ѡ��Ҫ����ɾ���ļ�¼��");
		return false;
	}
	
	if (!confirm("<bean:message key="lable.xsgzyxpzxy" />�û�����ɾ����ͨ��ѧУ��˵����ݣ�ȷ��Ҫɾ����ѡ��¼��")){
		return false;
	}
	document.forms[0].action=url;
    document.forms[0].submit();
}

function notPass(url){
	var RowsStr="!#!";
	
	for (i=0; i<document.getElementsByName("pkCh").length; i++){
    	if(document.getElementsByName("pkCh")[i].checked){
    		RowsStr+=document.getElementsByName("pkCh")[i].value+"!#!";
    	}
	}
	document.forms[0].pkDel.value = RowsStr;
	
	if (RowsStr=="!#!"){
		alert("��ѡ��Ҫ�����޸�Ϊ��ͨ���ļ�¼��");
		return false;
	}
	
	document.forms[0].action=url;
    document.forms[0].submit();
}

function pass(url){
	var RowsStr="!#!";
	
	for (i=0; i<document.getElementsByName("pkCh").length; i++){
    	if(document.getElementsByName("pkCh")[i].checked){
    		RowsStr+=document.getElementsByName("pkCh")[i].value+"!#!";
    	}
	}
	document.forms[0].pkDel.value = RowsStr;
	
	if (RowsStr=="!#!"){
		alert("��ѡ��Ҫ�����޸�Ϊͨ���ļ�¼��");
		return false;
	}
	
	var i;
	
	dwr.engine.setAsync(false);
	getOtherData.getJzxjPlshNum(RowsStr,document.getElementById("userType").value,function(data){
       if(data!=null){
       	i=data;
       }
    });
    dwr.engine.setAsync(true);
	
	var kshrs = document.getElementById('kshrs').value;
	if (Math.round(i)>Math.round(kshrs)){
		alert("�����������������ͨ��ѧ����"+i+"�ˣ��ȿ�ͨ��ʣ������"+kshrs+"�˶���"+(Math.round(i)-Math.round(kshrs))+"�ˣ�");
		return false;
	}
	document.forms[0].action=url;
	document.forms[0].submit();
}

function chec(){
      for(i=0;i<document.getElementsByName("pkCh").length;i++){
      	document.getElementsByName("pkCh")[i].checked=document.getElementsByName("qbxz")[0].checked;
      }
}

function tgzg(){
	var kshrs = document.getElementById('kshrs').value;
	var num = 0;
	
	for (i=0; i<document.getElementsByName("pkCh").length; i++){
    	if(document.getElementsByName("pkCh")[i].checked){
    		alert(document.getElementsByName("pkCh")[i].value);
    	}
	}
	
	if (kshrs == "0") {
		document.getElementById('tgBut').disabled = true;
		document.getElementById('tgBut').onclikc= function(){};
	} else {
		document.getElementById('tgBut').disabled = false;
	}
}

function chkAssisOne(url, act) {
	if (curr_row == null) {
		return false;
	} else {
		url += "&actDo=";
		url += act;
		url +="&act=";
		url += document.getElementById("act").value;
		if ('view'==act) {
			url += '&writ=view';
		}
		url += "&pkVal=";
		url += curr_row.getElementsByTagName("input")[0].value;
		url += "&xmdm=";
		url += curr_row.getElementsByTagName("input")[2].value;
		url += "&tName=";
		url += document.getElementById("realTable").value;
		if(url.search('assisChkOne')){
			url += "&xydm="+document.getElementById("xy").value;
		}
		showTopWin(url, 750, 550);
		return true;
	}
}

function add(url){
	return showTopWin(url,750,550);
}

function modi(url){
	if((curr_row == null) || (curr_row == "")){
		alert("��ѡ��Ҫ�޸ĵļ�¼��");
		return false;
	}
	url += "&xmdm=";
	url += curr_row.getElementsByTagName("input")[2].value;
	url += "&pkVal=";
	url += curr_row.getElementsByTagName("input")[0].value;
	return showTopWin(url,750,550);
}

function dataExport2() {
	document.forms[0].action = "/xgxt/zgdzdx_xszz.do?method=jzxj_xmshExp";
	document.forms[0].target = "_blank";
	document.forms[0].submit();
	document.forms[0].target = "_self";
}
function gshjmc() {
	if(confirm('��������ʾ���������Ứ�Ѻܳ���ʱ�䣬��ȷ��Ҫ�����𣡣�')){
		var xmdm = document.getElementById("xmdm").value;
		if(xmdm==""){
			alert("��ѡ����Ŀ����");
			return false;
		}
		document.forms[0].action = "/xgxt/zgdzdx_xszz.do?method=jzxj_xmsh&jxlc=gs&go=go";
		document.forms[0].submit();
		document.getElementById("gsmd").disabled=true;
		}else{
		return false;
		}
	
}
function hjjggg(){
	if(confirm('�������񽱽�����桷�Ứ�Ѻܳ���ʱ�䣬��ȷ��Ҫ�����𣡣�')){
		var xmdm = document.getElementById("xmdm").value;
		if(xmdm==""){
			alert("��ѡ����Ŀ����");
			return false;
		}
		document.forms[0].action = "/xgxt/zgdzdx_xszz.do?method=jzxj_xmsh&gsjg=gsjg&go=go";
		document.forms[0].submit();
		document.getElementById("hjjg").disabled=true;
		}else{
		return false;
		}
	
}
</script>
</head>
<body onload="xyDisabled('xy')">
		<html:form action="/zgdzdx_xszz.do?method=jzxj_xmsh" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${tips }</a>
				</p>
			</div>
			
			<input type="hidden" name="xyV" value=""/>
			<input type="hidden" name="zyV" value=""/>
			<input type="hidden" name="bjV" value=""/>
			<input type="hidden" id="userType" name="userType"
				value="<bean:write name="userType" scope="request"/>" />
			<input type="hidden" id="tableName" name="tableName"
				value="<bean:write name="tableName" scope="request"/>" />
			<input type="hidden" id="act" name="act"
				value="<bean:write name="act" scope="request"/>" />
			<input type="hidden" id="realTable" name="realTable"
				value="<bean:write name="realTable" scope="request"/>" />
			<input type="hidden" id="pk" name="pk"
				value="<bean:write name="pk" scope="request"/>" />
			<input type="hidden" id="isQuery" name="isQuery"
				value="<bean:write name="isQuery" scope="request"/>" />
			<input type="hidden" id="kshrs" name="kshrs" value="<bean:write name="kshrs"/>" />
			<input type="hidden" id="pkDel" name="pkDel" value="" />
			<input type="text" style="display: none;"/>
			<div class="toolbox">
			 <!-- ��ť -->
			 <div class="buttonbox">
			    <ul>
			    <logic:equal name="isQuery" value="is">
					<li> <a href="#" onclick="add('/xgxt/zgdzdx_xszz.do?method=jzxj_xmsq');" class="btn_zj"> ���� </a> </li>
			   		<li> <a href="#" onclick="modi('/xgxt/zgdzdx_xszz.do?method=jzxj_xmsq');" class="btn_xg"> �޸� </a> </li>
				</logic:equal>
				<logic:notEqual name="isQuery" value="is">
					<li> <a href="#" id="tgBut" onclick="pass('/xgxt/zgdzdx_xszz.do?method=jzxj_xmshXxxx&actDo=pass');" class="btn_shtg"> ͨ�� </a> </li>
			   		<li> <a href="#" onclick="notPass('/xgxt/zgdzdx_xszz.do?method=jzxj_xmshXxxx&actDo=notPass');" class="btn_shbtg"> ��ͨ�� </a> </li>
				</logic:notEqual>
				<li> <a href="#" onclick="del('/xgxt/zgdzdx_xszz.do?method=jzxj_xmshXxxx&actDo=del');" class="btn_sc"> ɾ�� </a> </li>
				<li> <a href="#" onclick="impAndChkData()" class="btn_dr"> ���� </a> </li>
				<li> <a href="#" onclick="dataExport2()" class="btn_dc"> ���� </a> </li>
				<logic:equal value="xx" name="gslx">
					<li> <a href="#"  onclick="gshjmc();" id="gsmd" class="btn_dc"> ��ʾ������ </a> </li>
					</logic:equal>
					<logic:equal value="admin" name="gslx">
					<li> <a href="#"  onclick="hjjggg();" id="hjjg" class="btn_dc"> �񽱽������ </a> </li>
					</logic:equal>
			    </ul>
			 </div>
			<div class="searchtab">
			  <table width="100%" border="0">
		      <tfoot>
		        <tr>
		          <td colspan="6">
		            <div class="btn">
		              <input type="hidden" name="go" value="a" />
		              <button type="button" class="btn_cx" id="search_go" 
		              	onclick="allNotEmpThenGo('/xgxt/zgdzdx_xszz.do?method=jzxj_xmsh')">
		              	�� ѯ
		              </button>
		              &nbsp;&nbsp;&nbsp;&nbsp;
		              <button type="button" class="btn_cz" id="btn_cz"  	onclick="searchReset();return false;">
		              	�� ��
		              </button>
		            </div>
		          </td>
		        </tr>
		      </tfoot>
			<tbody>
				<logic:equal name="isQuery" value="is">
							<tr>
								<th align="left">
									<bean:message key="lable.xsgzyxpzxy" />��
								</th>
								<td>
									<html:select name="rs1" property="xydm" onchange="initZyList();initBjList()" style="width:150px" styleId="xy">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
									</html:select>
								</td>
								<th>
									רҵ��
								</th>
								<td>
									<html:select name="rs1" property="zydm" onchange="initBjList()" style="width:150px" styleId="zy">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
									</html:select>
								</td>
								<th>
									�༶��
								</th>
								<td>
									<html:select name="rs1" property="bjdm" style="width:150px" styleId="bj">
									<html:option value=""></html:option>
									<html:options collection="bjList" property="bjdm"
										labelProperty="bjmc" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th>
									�꼶��
								</th>
								<td>
									<html:select name="rs1" property="nj" onchange="initBjList()">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select>
								</td>
								<th>
									ѧ�꣺
								</th>
								<td>
									<html:select name="rs1" property="xn">
										<html:option value=""></html:option>
										<html:options collection="xnList" property="xn"
											labelProperty="xn" />
									</html:select>
								</td>
								<th>
									ѧ�ڣ�
								</th>
								<td>
									<html:select name="rs1" property="xq">
										<html:option value=""></html:option>
										<html:options collection="xqList" property="xqdm"
											labelProperty="xqmc" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th>
									ѧ�ţ�
								</th>
								<td>
									<input type="text" id="xh" name="xh" maxlength="20"
										style="width:120px;heigh:100%"
										value="<bean:write name="rs1" property="xh"/>"/>
								</td>
								<th>
								��Ŀ��
								</th>
								<td>
									<html:select name="rs1" property="xmdm" style="width:180px">
										<html:option value=""></html:option>
										<html:options collection="jzxjxmList" property="xmdm"
											labelProperty="xmmc" />
									</html:select>
								</td>
								<td colspan="2">
								</td>
							</tr>
						</logic:equal>
						<logic:notEqual name="isQuery" value="is">
							<tr>
								<th align="left">
									<bean:message key="lable.xsgzyxpzxy" />��
								</th>
								<td>
									<html:select name="rs1" property="xydm" onchange="initZyList();initBjList()" style="width:180px" styleId="xy">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
									</html:select>
								</td>
								<th>
									רҵ��
								</th>
								<td>
									<html:select name="rs1" property="zydm" onchange="initBjList()" style="width:180px" styleId="zy">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
									</html:select>
								</td>
								<th>
									�༶��
								</th>
								<td>
									<html:select name="rs1" property="bjdm" style="width:180px" styleId="bj">
									<html:option value=""></html:option>
									<html:options collection="bjList" property="bjdm"
										labelProperty="bjmc" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th>
									�꼶��
								</th>
								<td>
									<html:select name="rs1" property="nj" onchange="initBjList()">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select>
								</td>
								<th>
									��Ŀ��
								</th>
								<td>
									<html:select name="rs1" property="xmdm" style="width:180px">
										<html:options collection="jzxjxmList" property="xmdm"
											labelProperty="xmmc" />
									</html:select>
								</td>
								<th>
									ѧ�ţ�
								</th>
								<td>
									<input type="text" id="xh" name="xh" maxlength="20"
										style="width:180px;heigh:100%"
										value="<bean:write name="rs1" property="xh"/>"/>
								</td>
							</tr>
						</logic:notEqual>
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
			 		 	
						<logic:equal name="isQuery" value="no">
						<font color="blue">��ʾ��˫��һ�п��Բ鿴��ϸ��Ϣ�������Ըı����״̬��������ͷ��������</font>
						<br />
						<font color="red">
							<logic:equal name="kshrs" value="#">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								ͳ�ƣ���ͨ��������<bean:write name="yshNum" />�ˣ����ͨ�����������ơ�
							</logic:equal>
							<logic:notEqual name="kshrs" value="#">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								ͳ�ƣ����ͨ��������<bean:write name="zrs" />�ˣ���ͨ��������<bean:write name="yshNum" />�ˣ���ʣ��<bean:write name="kshrs" />��
							</logic:notEqual>
						</font>
						</logic:equal>
						<logic:notEqual name="isQuery" value="no">
						<font color="blue">��ʾ��˫��һ�п��Բ鿴��ϸ��Ϣ��������ͷ��������</font>
						</logic:notEqual>
			 		 </logic:notEmpty>
			    </span>
			    </h3>
			<logic:notEmpty name="rs">
					<div class="con_overlfow">
					 <table summary="" class="dateline tablenowrap" align="" width="100%">
						<thead>
							<tr align="center" style="cursor:hand">
								<td>
									<input type="checkbox" name="qbxz" value="all"
										onclick="chec('qbxz')">
								</td>
								<logic:iterate id="tit" name="topTr" offset="3">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)">
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<tbody>
						<logic:iterate name="rs" id="s">
							<logic:equal name="isQuery" value="no">
								<tr onclick="rowOnClick(this)"
									style="cursor:hand;background-color:
								    <logic:iterate id="v" name="s" offset="0" length="1">
								    <bean:write name="v"/>
								    </logic:iterate>
								     "
									ondblclick="chkAssisOne('/xgxt/zgdzdx_xszz.do?method=jzxj_xmshXxxx','view')">
									<td align="center" >
										<logic:iterate id="v" name="s" offset="1" length="1">
											<input type="checkbox" name="pkCh"
												value="<bean:write name="v"/>"/>
										</logic:iterate>
									</td>
									<td align="center">
										<logic:iterate id="v" name="s" offset="1" length="1">
											<input type="hidden" value="<bean:write name="v"/>" />
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="2" length="1">
											<input type="hidden" value="<bean:write name="v"/>" />
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="3" length="1">
											<bean:write name="v" />
										</logic:iterate>
									</td>
									<logic:iterate id="v" name="s" offset="4">
										<td align="center">
											<bean:write name="v" />
										</td>
									</logic:iterate>
								</tr>
							</logic:equal>
							<logic:equal name="isQuery" value="is">
								<tr onclick="rowOnClick(this)"
									style="cursor:hand;background-color:
								    <logic:iterate id="v" name="s" offset="0" length="1">
								    <bean:write name="v"/>
								    </logic:iterate>
								     "
									ondblclick="chkAssisOne('/xgxt/zgdzdx_xszz.do?method=jzxj_xmsq','view')">
									<td align="center">
										<logic:iterate id="v" name="s" offset="1" length="1">
											<input type="checkbox" name="pkCh"
												value="<bean:write name="v"/>">
										</logic:iterate>
									</td>
									<td align="center">
										<logic:iterate id="v" name="s" offset="1" length="1">
											<input type="hidden" value="<bean:write name="v"/>" />
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="2" length="1">
											<input type="hidden" value="<bean:write name="v"/>" />
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="3" length="1">
											<bean:write name="v" />
										</logic:iterate>
									</td>
									<logic:iterate id="v" name="s" offset="4">
										<td align="center">
											<bean:write name="v" />
										</td>
									</logic:iterate>
								</tr>
							</logic:equal>
						</logic:iterate>
						</tbody>
					</table>
					</div>
			</logic:notEmpty>
			</div>
			<div id="tmpdiv"></div>
			<logic:equal name="fbcg" value="yes">
			<script>
			        alert("�����ɹ�����");			    
			        </script>
			</logic:equal>
			<logic:equal name="fbcg" value="no">
			<script>
			        alert("����ʧ�ܣ�û��ѧ����øý�ѧ�𣡣�");			    
			        </script>
			</logic:equal>
		</html:form>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
<script language="javascript">
	var kshrs = document.getElementById('kshrs').value;
	var isQuery = document.getElementById('isQuery').value;
	
	if (isQuery == "no"){
		if (kshrs == "0") {
			document.getElementById('tgBut').disabled = true;
		} else {
			document.getElementById('tgBut').disabled = false;
		}
	}
</script>
</html>

