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
	<script language="javascript" src="js/comm/commFunction.js"></script>
	<script language="javascript">
/*
ȫ��ѡ��
*/    
  function chec(){
	  
     for(i=0;i<document.getElementsByName("pkV").length;i++){
  	    document.getElementsByName("pkV")[i].checked=document.getElementsByName("qbxz")[0].checked;
     }
  }		


	function viewMoreinfojxj(doType){
		var url ="/xgxt/zjlgPjpy.do?method=jxjCheck&act=view&pkValue=";
		var pkValue = "";
		var jxjcxzj = "";
		if(curr_row.getElementsByTagName("input")[0]){
			jxjcxzj = curr_row.getElementsByTagName("input")[1].value;
		}
		 if (jxjcxzj != ""){
		 pkValue = curr_row.getElementsByTagName("input")[0].value;
		 url += pkValue+"&jxjcxzj="+jxjcxzj;
		 url += "&xh="+curr_row.getElementsByTagName("input")[3].value;
		 showTopWin(url, 800, 650,false);
		 }else{
		
		 }
	}
function checkType(){
   var userType = $("userType").value;
   if(userType=="xx"||userType=="admin"){
      if($("checkpass")){ $("checkpass").value="ѧУ���ͨ��";}
      if($("checknopass")){ $("checknopass").value="ѧУ��˲�ͨ��";}
      if($("shyjLable")){ $("shyjLable").innerHTML="ѧУ������";}
   }else if(userType=="xy"){
      if($("checkpass")){ $("checkpass").value="<bean:message key="lable.xsgzyxpzxy" />���ͨ��";}
      if($("checknopass")){ $("checknopass").value="<bean:message key="lable.xsgzyxpzxy" />��˲�ͨ��";}
      if($("shyjLable")){ $("shyjLable").innerHTML="<bean:message key="lable.xsgzyxpzxy" />������";}
   }
}
function batchCheck(str){
	var bmlb;
	var bmdm;
	
	var userType=$("userType").value;
	if(document.getElementById("isZds")){
	var isZds = document.getElementById("isZds").value;//��˷�ʽ
	}
	

	if(isZds=="xysh"){
		var xydm = document.getElementById("xydm").value;//��˷�ʽ
		if(xydm==""){
		alert("��ѡ��<bean:message key="lable.xsgzyxpzxy" />������");
		return false;
		}
	}
	if(isZds=="cpzsh"){
		var cpzdm = document.getElementById("cpzdm").value;//��˷�ʽ
		if(cpzdm==""){
		alert("��ѡ������飡����");
		return false;
		}
	}
	if(isZds=="bjsh"){
		var bjdm = document.getElementById("bjdm").value;//��˷�ʽ
		if(bjdm==""){
		alert("��ѡ��༶������");
		return false;
		}
	}
	
	if($("jxjdm") && $("jxjdm").value==""){
		alert("��ѡ������˵Ľ�ѧ��!");
		return false;
	}
	
	
   var userType = $("userType").value;
   var url = "/xgxt/1.do?method=plCheck&check="+str; 
   $("check").value= str;
   var RowsStr="!!";		  
   var userType = $("userType").value;		   
   xyshDone = (str=="yes")?"ͨ��":"��ͨ��";
   var pkVArray = "'";
   for (i=0; i<document.getElementsByName("pkV").length; i++){
   	  if(document.getElementsByName("pkV")[i].checked){	    		 
   		 pkVArray+=document.getElementsByName("pkV")[i].value+"','"
   		 RowsStr+=document.getElementsByName("pkV")[i].value+"!!";
   	  }	    	  
   }		   
   if (RowsStr=="!!"){
	   alert("��ѡ��Ҫ�����ļ�¼��\n(����ÿ����¼ǰ��ѡ��)");
	   return false;
   }
   document.forms[0].pkVStr.value = RowsStr;
   pkVArray=pkVArray.substring(0,pkVArray.length-2);
   if (confirm("ȷ��Ҫ\""+xyshDone+"\"��ѡ��¼��")){
	 //showMessage('showDiv',true,'#C7DEFC');    
	 viewTempDiv('������','showDiv','400','',null)      
   }
}

function qxsave(){
	hiddenMessage(true,true); 
}
function jxjShSave(){
    var shyj = $("shyj").value;
    var num = 0;
    var jxjnumszg = 0;
	var jxjnumstg = 0;
	var isZds = document.getElementById("isZds").value;//��˷�ʽ
	var xxxysbh = document.getElementById("xxxysbh").value;
    for (i=0; i<document.getElementsByName("pkV").length; i++){
  	  if(document.getElementsByName("pkV")[i].checked){	   
  	  	  var jxjsfsh = document.getElementsByName("jxjsfsh")[i].value; 
  	  	  var cklx = document.getElementById('shlx').value;
  	  	  if(cklx != 'btg'){
  	  	  if(jxjsfsh == "ͨ��"){
				alert("��ѡ��ѧУ���û��ͨ����ѧ������");
				return false;
  	  	  }
  	  	  }		 
  		 num++;
  	  }	    	  
	   }
			
			if(document.getElementById("jxjdm")){
			var jxjdm = document.getElementById("jxjdm").value;
			}
			if(document.getElementById("xn")){
			var xn = document.getElementById("xn").value;
			}
			if(document.getElementById("cpzdm")){
			var cpzdm = document.getElementById("cpzdm").value;
			}
			if(document.getElementById("xydm")){
			var xydm = document.getElementById("xydm").value;
			}
			if(document.getElementById("bjdm")){
			var bjdm = document.getElementById("bjdm").value;
			}
			if(isZds=="xysh"){
				bmlb = "xydm";
				bmdm = xydm;
			}else if(isZds=="cpzsh"){
				bmlb = "cpzdm";
				bmdm = cpzdm;
			}else if(isZds=="bjsh"){
				bmlb = "bjdm";
				bmdm = bjdm;
			}
			
			var xydm = document.getElementById("xydm").value;
			    dwr.engine.setAsync(false);
			   	getJxjRs.getJxjrsxz(bmlb,bmdm,jxjdm,xn,xxxysbh,function jxjNum(data){
			   		if (data != null || typeof data == 'object') {
			   			jxjnumszg = data;
			   			//alert(jxjnumszg);
					}else{
						showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
					}
					});
			   	dwr.engine.setAsync(true);
                dwr.engine.setAsync(false);
			   	getJxjRstg.getJxjrsxz1(bmlb,bmdm,jxjdm,xn,xxxysbh,function jxjNums(data1){
			   		if (data1 != null || typeof data1 == 'object') {
			   			jxjnumstg = data1;
			   			//alert(jxjnumstg);
					}else{
						showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
					}
					});
			   	dwr.engine.setAsync(true);
				
			    //alert(jxjnumszg);
			   	//alert(jxjnumstg);
			   	 if(cklx != 'btg'){
			if(num>(jxjnumszg-jxjnumstg)){
				if((jxjnumszg-jxjnumstg)<=0){
					alert("�ý�ѧ�����������Ϊ"+"��"+jxjnumszg+"��,���С�0�����˿������ͨ��");
					}else{
					alert("�ý�ѧ�����������Ϊ"+"��"+jxjnumszg+"��,���С�"+(jxjnumszg-jxjnumstg)+"�����˿������ͨ��");
				}
				return false;
				}
			   	 }

    if(shyj.length>200){
        alert("������������������200���ڣ�");
        return false;
    }
    var url = "/xgxt/zjlgPjpy.do?method=jxjSqSh&go=go&shyj="+shyj; 
    refreshForm(url);
    $("kfbtnSave").disabled=true;
}
function isshfs(){
	var shfs = document.getElementById("isZds").value;
	if(shfs=="xysh"){
		document.getElementById("xydm").disabled = false;
		document.getElementById("cpzdm").disabled = true;
		document.getElementById("zydm").disabled = true;
		document.getElementById("bjdm").disabled = true;
	}else if(shfs=="cpzsh"){
		document.getElementById("xydm").disabled = false;
		document.getElementById("cpzdm").disabled = false;
		document.getElementById("zydm").disabled = true;
		document.getElementById("bjdm").disabled = true;
		var cpztt = document.getElementById("cpzdm").value;
		if(cpztt == null){
		initCpzList();
		}
	}else if(shfs=="zysh"){
		document.getElementById("xydm").disabled = false;
		document.getElementById("cpzdm").disabled = true;
		document.getElementById("zydm").disabled = false;
		document.getElementById("bjdm").disabled = true;
	}else if(shfs=="bjsh"){
		document.getElementById("xydm").disabled = false;
		document.getElementById("cpzdm").disabled = true;
		document.getElementById("zydm").disabled = false;
		document.getElementById("bjdm").disabled = false;
	}
}

function jxjshquery(){
	var jxjdm = document.getElementById("jxjdm").value;
	var nj = document.getElementById("nj").value;
	var cpzdm;
	var xydm;
	var bjdm;
	if(document.getElementById("cpzdm")){
		cpzdm = document.getElementById("cpzdm").value;
	}
	if(document.getElementById("xydm")){
		xydm = document.getElementById("xydm").value;
	}
	if(document.getElementById("bjdm")){
		bjdm = document.getElementById("bjdm").value;
	}
	var isZds = document.getElementById("isZds").value;//��˷�ʽ
	if(jxjdm==""){
	alert("��ѡ��ѧ��");
	return false;
	}
	//if(xn==""){
	//	alert("��ѡ��ѧ��");
	//	return false;
	//}

	if(isZds=="xysh"){
		if(xydm == ""){
			alert("������˷�ʽ��<bean:message key="lable.xsgzyxpzxy" />����ѡ��<bean:message key="lable.xsgzyxpzxy" />����");
			return false;
		}
		if(nj==""){
			alert("������˷�ʽ��<bean:message key="lable.xsgzyxpzxy" />����ѡ���꼶����");
			return false;
		}
	}else if(isZds=="cpzsh"){
		if(cpzdm == ""){
			alert("������˷�ʽ�ǲ����飬��ѡ������飡��");
			return false;
		}
	}else if(isZds=="bjsh"){
		if(bjdm == ""){
			alert("������˷�ʽ�ǰ༶����ѡ��༶����");
			return false;
		}
	}

		refreshForm('zjlgPjpy.do?method=jxjshQuery&go=go')
}
function checkshlx(type){
	if(type=='no'){
		document.getElementById('shlx').value='btg';
	}else{
		document.getElementById('shlx').value='';
	}
}

function checkback(){
     
      var RowsStr="!!";
      var pkVArray = "'";
		   for (i=0; i<document.getElementsByName("pkV").length; i++){
	    	  if(document.getElementsByName("pkV")[i].checked){	    		 
	    		 pkVArray+=document.getElementsByName("pkV")[i].value+"','"
	    		 RowsStr+=document.getElementsByName("pkV")[i].value+"!!";
	    		 var jxjsfsh = document.getElementsByName("jxjsfsh")[i].value; 
	    		 if(jxjsfsh == "ͨ��"){
				     alert("��ѡ��ѧУ���û��ͨ����ѧ������");
				  return false;
  	  	         }
	    	  }	    	  
		   }		   
		   if (RowsStr=="!!"){
			   alert("��ѡ��Ҫ�����ļ�¼��\n(����ÿ����¼ǰ��ѡ��)");
			   return false;
		   }
		   if (confirm("ȷ��Ҫ�˻���ѡ��¼��")){
			 //showMessage('showDiv',true,'#C7DEFC');    
			 viewTempDiv('�˻�����','showDivBack','400','',null)      
		   }
		   document.forms[0].pkVStr.value = RowsStr;
		   pkVArray=pkVArray.substring(0,pkVArray.length-2);
		  
		   
}
function jxjBackSave(){
         var thly = $("thly").value;
         if(thly.length>200){
                alert("������������������200���ڣ�");
                return false;
          }
          var url = "/xgxt/zjlgPjpy.do?method=audit&go=go&workFlowName=jxjlc&tableName=xsjxjb&status=th&thly="+thly; 
          refreshForm(url);
          $("kfbtnSave1").disabled=true; 
}
function checkview(){
      var RowsStr="!!";
      var pkR = "'";
      var num=0;
		   for (i=0; i<document.getElementsByName("pkV").length; i++){
	    	  if(document.getElementsByName("pkV")[i].checked){	    		 
	    		 pkR=document.getElementsByName("pkV")[i].value;
	    		 num++;
	    	  }	    	  
		   }		   
		   if(num==0){
		      alert("��ѡ��һ��Ҫ�����ļ�¼��\n(����ÿ����¼ǰ��ѡ��)");
		      return false;
		   }
		   if (num>1){
			   alert("��ѡ��һ��Ҫ�����ļ�¼��\n(����ÿ����¼ǰ��ѡ��)");
			   return false;
		   }
		   var url = "/xgxt/zjlgPjpy.do?method=recordView&tableName=xsjxjb&pkR="+pkR; 
           showTopWin(url,750,550);
}

function goSearch(){
	refreshForm('zjlgPjpy.do?method=jxjshQuery&go=go');
}
</script>
</head>

<body onload="xyDisabled('xy');checkType();isshfs();">
	<html:form action="/zjlgPjpy" method="post">
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>�������� - ��� - ��ѧ�����</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv();">ʹ�ð���</a>
			</p>
		</div>			
		<!-- ���� end-->
		<!-- ��ʾ��Ϣ START-->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>��ʾ��</span>
			</h3>
			<p>
				<font color="red"><bean:message key="lable.xb" />���ʱ������ѡ��<bean:message key="lable.xb" />������˵Ľ�ѧ����Ŀ�����������ʱ��ѡ������������˵Ľ�ѧ����Ŀ�������޷���ˡ�</font><br/>
				<logic:notEmpty name="jxjSqrs" >
					<font color="blue"><bean:message key="lable.xb" />һ����������${jxjSqrs.xyjxjrs },������${jxjSqrs.xysqrs }�ˣ���������${jxjSqrs.xyksqrs }�ˡ�
					<logic:notEmpty name="jxjSqrs" property="cpzjxjrs">
					<br/>
					������һ����������${jxjSqrs.cpzjxjrs },������${jxjSqrs.cpzsqrs }�ˣ���������${jxjSqrs.cpzksqrs }�ˡ�<br/>
					</logic:notEmpty></font>
				</logic:notEmpty>
				
			</p>
			<a class="close" title="����"
				onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- ��ʾ��Ϣ end-->
		<logic:equal value="yes" name="writeAble">
			<div class="toolbox">
			<div class="buttonbox">	
				<ul>
					<li><a href="#" class="btn_shtg" id="checkpass" onclick="batchCheck('yes');checkshlx('yes');">ͨ ��</a></li>
					<li><a href="#" class="btn_shbtg" id="checknopass" onclick="batchCheck('no');checkshlx('no');">��ͨ��</a></li>
					<logic:equal value="xx" name="userType">
					<li><a href="#" class="btn_fh" id="checkback" onclick="checkback();">�˻�</a></li>
					</logic:equal>
					<li><a href="#" class="btn_sx" id="checkview" onclick="checkview();">����׷��</a></li>
				</ul>
			</div>
			</div>
		</logic:equal>
		<input type="hidden" id="zyV" name="zyV" value="" />
		<input type="hidden" id="bjV" name="bjV" value="" />
		<input type="hidden" id="pt" name="pt" />
		<input type="hidden" id="shlx" name="xhlx" />
		<input type="hidden" id="userType" name="userType" value="${userType }" />
		<input type="hidden" id="xxxysbh" name="xxxysbh" value="<bean:write name="userType" scope="session"/>" />
		<input type="hidden" id="tableName" name="tableName" value="" />
		<input type="hidden" id="realTable" name="realTable" value="xsjxjb" />
		<input type="hidden" style="" id="failbg" name="failbg" value="${errorStr }" />
		<!-- ������Ϣ���� -->
		<input type="hidden" name="pkVStr" value="" />
		<input type="hidden" name="check" value="" />
		<div id="showDiv" style="display: none" align="center">
			<fieldset style="width: 100%; height: 100%">
				<legend>
					��������д<font color="red">(200����)</font>
				</legend>
				<table class='formlist'>
					<tr>
						<td align='left'>
							<html:textarea property="shyj" rows="12" cols="50"></html:textarea>
						</td>
					</tr>
					<tfoot>
						<tr>
							<td colspan='2'>
								<button type="button" class='button2' id="kfbtnSave" onclick='jxjShSave()'>
									�ύ
								</button>
								<button type="button" class='button2' id="qxsave" onclick='hiddenMessage(true,true);refreshForm("zjlgPjpy.do?method=jxjshQuery&go=go")'>
									�ر�
								</button>
							</td>
						</tr>
					</tfoot>
				</table>
			</fieldset>
		</div>
      <div id="showDivBack" style="display: none" align="center">
			<fieldset style="width: 100%; height: 100%">
				<legend>
					�˻�������д<font color="red">(200����)</font>
				</legend>
				<table class='formlist'>
					<tr>
						<td align='left'>
							<html:textarea property="thly" rows="12" cols="50"></html:textarea>
						</td>
					</tr>
					<tfoot>
						<tr>
							<td colspan='2'>
								<button type="button" class='button2' id="kfbtnSave1" onclick='jxjBackSave()'>
									�ύ
								</button>
								<button type="button" class='button2' id="qxsave1" onclick='hiddenMessage(true,true);refreshForm("zjlgPjpy.do?method=jxjshQuery&go=go")'>
									�ر�
								</button>
							</td>
						</tr>
					</tfoot>
				</table>
			</fieldset>
		</div>
		<div id="showDivView" style="display: none" align="center">
			<fieldset style="width: 100%; height: 100%">
				<legend>
					����׷��
				</legend>
				<logic:notEmpty name="rs1">
				<table width="100%" id="rsTable" class="dateline">
						<thead>
							<tr align="center" style="cursor:hand">
								<logic:iterate id="tit" name="topTr" >
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)">
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<logic:iterate name="rs1" id="s">
							<tr onclick="rowOnClick(this);" style="cursor:hand;">
								<logic:iterate id="v" name="s">
									<td align="center">
										<bean:write name="v" />
									</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
					<tfoot>
						<tr>
							<td colspan='2'>
								<button type="button" class='button2' id="qxsave1" onclick='hiddenMessage(true,true);refreshForm("zjlgPjpy.do?method=jxjshQuery&go=go")'>
									�ر�
								</button>
							</td>
						</tr>
					</tfoot>
				</table>
				</logic:notEmpty>
			</fieldset>
		</div>
		<div class="searchtab">		
			<table width="100%" class="tbstyle">
				<tbody>
					<tr>
						<th>�꼶</th>
						<td><html:select property="nj" styleId="nj" styleClass="select"
								style="width:90px">
								<html:option value=""></html:option>
								<html:options collection="njList" property="nj"
									labelProperty="nj" />
							</html:select></td>
						<th>ѧ��</th>
						<td><input name="xndt" value="<bean:write name="xnval"/>"
								disabled="disabled" />
							<html:hidden property="xn"></html:hidden>
						</td>
						<th>��ѧ��</th>
						<td><html:select property="jxjdm" styleClass="select" styleId="jxjdm"
								onchange="goSearch()">
								<html:option value=""></html:option>
								<html:options collection="jzxjxmList" property="jxjdm"
									labelProperty="jxjmc" />
							</html:select></td>
						</tr>
						<tr>
							<th>ѧ��</th>
							<td><html:text property="xh" styleId="xh"></html:text>
							</td>
							<th>����</th>
							<td><html:text property="xm" styleId="xm"></html:text></td>
							<th>��˷�ʽ</th>
							<td><html:select property="isZds" onchange="isshfs();goSearch()">
								<html:option value="xysh"><bean:message key="lable.xsgzyxpzxy" /></html:option>
								<html:option value="cpzsh">������</html:option>
								<html:option value="bjsh">�༶</html:option>
							</html:select></td>
						</tr>
						<tr>
							<th>���״̬</th>
							<td><html:select property="yesNo" onchange="">
								<html:option value=""></html:option>
								<html:option value="δ���">δ���</html:option>
								<html:option value="ͨ��">ͨ��</html:option>
								<html:option value="��ͨ��">��ͨ��</html:option>
							</html:select>
							</td>
							<th>������</th>
							<td><html:select property="cpzdm"
								onchange="initZyList();initBjList();goSearch()" styleClass="select"
								style="width:150px" styleId="cpz">
								<html:option value=""></html:option>
								<html:options collection="cpzList" property="cpzdm"
									labelProperty="cpzmc" />
							</html:select>
							<input type="hidden" name="cpzV" value="" /></td>
							<th></th>
							<td></td>
					</tr>
					<tr>
						<th><bean:message key="lable.xsgzyxpzxy" /></th>
						<td><logic:equal name="userType" value="xy">
							<input id="shbmmcfg" name="shbmmcfg" value="${shbmmc}" disabled="disabled"/>
							<html:select property="xydm"
								onchange="initZyList();initBjList();initCpzList();"
								styleClass="select" style="width:180px;display: none" styleId="xy">
								<html:option value=""></html:option>
								<html:options collection="xyList" property="xydm"
									labelProperty="xymc" />
							</html:select>
							</logic:equal>
							<logic:notEqual name="userType" value="xy">
								<html:select property="xydm"
								onchange="initZyList();initBjList();initCpzList();goSearch()"
								styleClass="select" style="width:150px" styleId="xy">
								<html:option value=""></html:option>
								<html:options collection="xyList" property="xydm"
									labelProperty="xymc" />
							</html:select>
							</logic:notEqual></td>
							<th>רҵ</th>
							<td><html:select property="zydm" onchange="initBjList()"
								style="width:150px" styleClass="select" styleId="zy"
								disabled="true">
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
				</tbody>
				<tfoot>
					<tr>
						<td colspan="6">
						<div class="btn">
							<input type="hidden" name="act" value="qry" />
							<button type="button" id="search_go" onclick="refreshForm('zjlgPjpy.do?method=jxjshQuery&go=go')">
								�� ѯ
							</button>
						 <button type="button" class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
							�� ��
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
						��ѯ���&nbsp;&nbsp;��¼����
							<bean:write name="rsNum" />
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<%--							<font color="blue">��ʾ���ý�ѧ��һ����������<bean:write name="jxjszrs"/>�ˣ�--%>
<%--													�Ѿ�����<bean:write name="jxjysqrs"/>�ˣ�--%>
<%--													��������<bean:write name="jxjhxsyrs"/>��</font>--%>
					
					</span>
				</h3>
				<table width="100%" id="rsTable" class="dateline">
							<thead>
								<tr align="center" style="cursor: hand">
									<td>
										<input type="checkbox" name="qbxz" value="all"
											onclick="chec('qbxz')" />
									</td>
									<logic:iterate id="tit" name="topTr" offset="1" length="12">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)" nowrap="nowrap">
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<logic:iterate name="rs" id="s">
								<tr onclick="rowOnClick(this)" style="cursor: hand"
									ondblclick="viewMoreinfojxj('view')">

									<td align="center">
										<logic:iterate id="v" name="s" offset="0" length="1">
											<input type="checkbox" name="pkV" value="<bean:write name="v"/>" />
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="1" length="1">
											<input type="hidden" id="jxjcxzj"
												value="<bean:write name="v"/>" />
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="8" length="1">
											<input type="hidden" id="jxjsfsh" name="jxjsfsh"
												value="<bean:write name="v"/>" />
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="2" length="1">
											<input type="hidden" id="rtr" name="reter"
												value="<bean:write name="v"/>" />
										</logic:iterate>
									</td>
									<logic:iterate id="v" name="s" offset="2" length="12">
										<td align="center">
											<bean:write name="v" />
										</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
						</table>
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
	var tt = document.getElementById('failInfo').value();
	alert(tt);
	document.getElementById('search_go').onclick();
</script>
		</logic:equal>
	</logic:present>
	<logic:notEmpty name="errorStr">
		<script>
	var tt = document.getElementById('failbg').value;
	var gnmk = tt.replace(/\;/g, ";\n");
	alert(gnmk);
	//document.getElementById('search_go').onclick();
</script>
	</logic:notEmpty>

<logic:equal name="zycjbf" value="jxjjd1">
			<script>
			        alert("��־���ѧ�������ѧ����ѧ���ܼ��");			    
			        </script>
			</logic:equal>
			
			<logic:equal name="zycjbf" value="jxjjd2">
			<script>
			        alert("����ѧ����ѧ���ܼ��");			    
			        </script>
			</logic:equal>
</body>
</html>
