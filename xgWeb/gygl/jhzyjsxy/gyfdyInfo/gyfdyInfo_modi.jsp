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
    <base target="_self">
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/gyglShareData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>	
	<script language="javascript" src="js/gygl/gyglFunction.js"></script>	
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
<%--	<script language="javascript" src="js/xsgyglFunction.js"></script>		--%>
	<script language="javascript" src="js/AjaxFunction.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/gyglJhzyDAO.js'></script>
	<script type="text/javascript" src="js/BatAlert.js"></script>
	<script type="text/javascript">
	var issubmit = true;
	function jyzhLcList(){
			var lddm = document.getElementById("lddm").value;
			var gyfdy = $("gyfdy").value;
			//var bool = false;
			//if(gyfdy == ""){
			//	bool = true;
			//	lddm = "";
			//}else{
			//	bool = true;
			//}
			//if(bool){		
				gyglJhzyDAO.getSsLcList(lddm,function initTjList(data){
			       if (data != null && typeof data == 'object') {
						var objId = "lc";
						if($(objId) && $(objId).tagName.toLowerCase() == "select"){
							DWRUtil.removeAllOptions(objId);			
							DWRUtil.addOptions(objId,data,"dm","mc");
						}
					}else{
						showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
					}	
			    });
			//}
	}
	//
	function jhzyQshList(userType){
		var lddm = document.getElementById("lddm").value;
		var lch = document.getElementById("lcdm").value;
		var yhm = document.getElementById("yhm").value;
		var xn = document.getElementById("xn").value;
		var xq = document.getElementById("xq").value;
		var gyfdy = $("gyfdy").value;
		var bool = false;
		if(yhm == ""){
			$("gyfdy").value = "";
		}
		//if(userType == "yhm"){
		//	bool = true;
		//}else{
		//	if(gyfdy == ""){
		//		lddm = "";
		///		alert("��ѡ��Ԣ����Ա������");
		//		$("lddm").value = "";
		//		bool = true;
		///		return false;
		//	}else{
		//		bool = true;
		//	}
		//}
		//dwr.engine.setAsync(true);
		//if(bool){
			gyglJhzyDAO.getSsmList(lddm,lch,"no","",xn,xq,function initTjList(data){
			       if (data != null && typeof data == 'object') {
						var objId = "qsh";
						if($(objId) && $(objId).tagName.toLowerCase() == "select"){
							DWRUtil.removeAllOptions(objId);			
							DWRUtil.addOptions(objId,data,"dm","mc");
						}
					}else{
						showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
					}	
			});
			//dwr.engine.setAsync(true);
			gyglJhzyDAO.getSsmList(lddm,lch,"yes",yhm,xn,xq,function initTjList(data){
			       if (data != null && typeof data == 'object') {
						var objId = "fdyqsh";
						if($(objId) && $(objId).tagName.toLowerCase() == "select"){
							DWRUtil.removeAllOptions(objId);			
							DWRUtil.addOptions(objId,data,"dm","mc");
						}
						objId = "fdyqshfb";
						if($(objId) && $(objId).tagName.toLowerCase() == "select"){
							DWRUtil.removeAllOptions(objId);			
							DWRUtil.addOptions(objId,data,"dm","mc");
						}
					}else{
						showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
					}	
			});
		//}
	}
	function addBatchColum(){
        var whfss =  document.getElementById("whfss");
        //var cpz =  document.getElementById("cpz");
        var yhfss= document.getElementById("yhfss"); 
        var yhm = document.getElementById("yhm").value; 
        var yfpLength = yhfss.length;      
        var whfssNum = 0;
        var j = 0;
        var bjdms =  Array();
	    var bjmcs =  Array();	
        if(yhm==''){
		   alert('����ѡ��Ԣ����Ա���ٽ��в�����');
		   return false;
	    }
        for(var i=0;i<whfss.options.length;i++){	
		    if(whfss.options[i].selected){
		    	bjdms[j]=whfss.options[i].value;
                bjmcs[j]=whfss.options[i].text;
                whfss.options[i]=null;
			  	whfssNum++;
			  	i--;
			  	j++;
		    }
	    }
	    if(whfssNum==0){
		   alert('�������\'�ѻ������\'�б��У�ѡ��һ���������¼��');
		   return false;
	    }
	    //alert(j);
	    for (i = 0; i < j; i++) {     	   
             yhfss.options[yfpLength++] = new Option(bjmcs[i],bjdms[i]);                                                       
        }	 
        issubmit = false;
}
	function delBatchColum(){
        var whfss =  document.getElementById("whfss");
        var yhfss= document.getElementById("yhfss"); 
        var wfpLength = whfss.length;      
        var yfpBjNum = 0;
        var j = 0;
        var bjdms =  Array();
	    var bjmcs =  Array();	
        for(var i=0;i<yhfss.options.length;i++){	
		    if(yhfss.options[i].selected){
		    	bjdms[j]=yhfss.options[i].value;
                bjmcs[j]=yhfss.options[i].text;
                yhfss.options[i]=null;
			  	yfpBjNum++;
			  	i--;
			  	j++;
		    }
	    }
	    if(yfpBjNum==0){
		   alert('�����ұ�\'�ѻ�������\'�б��У�ѡ��һ���������¼��');
		   return false;
	    }
	    for (i = 0; i < j; i++) {     	   
             whfss.options[wfpLength++] = new Option(bjmcs[i],bjdms[i]);                                                       
        }	   
        issubmit = false;
}
	function yhInfo(){
		var yhm = document.getElementById("yhm").value;
		gyglJhzyDAO.getUserInfo(yhm,function initUserList(data){
		       if (data != null && typeof data == 'object') {
					document.getElementById("zmc").value = data.zmc;
					$("bmmc").value = data.bmmc;
					$("xm").value = data.xm;
					$("dwmc").value = data.dwmc;
					$("gyfdy").value = data.yhm;
				}else{
					showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
				}	
		});
	}
	function GetRadioValue(RadioName){
	    var obj;   
	    obj=document.getElementsByName(RadioName);
	    if(obj!=null){
	        var i;
	        for(i=0;i<obj.length;i++){
	            if(obj[i].checked){
	                return obj[i].value;           
	            }
	        }
	    }
	    return null;
	}
	function allselgy(){
		 var whfss =  document.getElementById("whfss");
		 var allsel = document.getElementById("allsel");
		 var bool = false;
		 BatAlert.showTips("����ִ�У���ȴ�����");
		 if(allsel.checked){
			 for(var i=0;i<whfss.options.length;i++){	
				 whfss.options[i].selected = true;
			    }
		 }else{
			 for(var i=0;i<whfss.options.length;i++){	
				 whfss.options[i].selected = false;
			    }
		 }
		 document.getElementById("yhfssbf").style.display="none";
		 BatAlert.closeTips();
	}
	function allnoselgy(){
	     var yhfss= document.getElementById("yhfss"); 
		 var allnosel = document.getElementById("allnosel");
		 BatAlert.showTips("����ִ�У���ȴ�����");
		 if(allnosel.checked){
			 for(var i=0;i<yhfss.options.length;i++){	
		    	  yhfss.options[i].selected = true;
			    }
		 }else{
			 for(var i=0;i<yhfss.options.length;i++){	
		   	  yhfss.options[i].selected = false;
			    }
		 }
		 document.getElementById("yhfssbf").style.display="none";
		 BatAlert.closeTips();
	}
	function changeSave(obj){
		if(!issubmit){
		if(obj == "fdy" && document.getElementById("yhm").value !=""){
			if(confirm('�Ƿ�Ҫ�ύ��ǰ�ѻ���������ݣ�\n���\'ȷ��\'���������ݣ�\n���\'ȡ��\'���������ύ��')){
			dataSave();
			}
		}else{
			if(confirm('�Ƿ�Ҫ�ύ��ǰ�ѻ���������ݣ�\n���\'ȷ��\'���������ݣ�\n���\'ȡ��\'���������ύ��')){
			dataSave();
			}
		}
		}
	}
	</script>
	<body onload="">
		<html:form action="/jhzy_gygl" method="post">
		    <input type="hidden" id="pkValue" name="pkValue" value="" />
			 <input type="hidden" id="doType" name="doType" value="" />	
		 	<input type="hidden" id="isView" name="isView" value="<bean:write name="isView"/>" />			 	
		 	<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã�
					��Ԣ���� - ��Ϣά�� - ��Ԣ����Ա��Ϣ - ��Ԣ����Ա��Ϣά��
				</div>
			</div>

			<fieldset>
				<legend>
					��Ԣ����Ա��Ϣά��
				</legend>
				<table width="100%" class="tbstyle">
					<thead>
						<tr style="height:22px">
							<td colspan="4" align="center">
								<b>��д��Ԣ����Ա��Ϣ</b>
							</td>
						</tr>
					</thead>
					<tr valign="middle">
						<td align="right" width="15%">
							<font color="red">*</font>��Ԣ����Ա��
						</td>
						<td align="left">
						<logic:equal value="modify" name="isView">
						<input id="yhm" type="text" name="yhm" value="<bean:write name="rs" property="yhm"/>" readonly="readonly"/>
						<input id="gyfdy" type="hidden" name="gyfdy" value="<bean:write name="rs" property="yhm"/>" readonly="readonly"/>
						</logic:equal>
						<logic:notEqual value="modify" name="isView">
							<html:select name="rs" property="yhm" style="width:150px"
									onchange="yhInfo();jhzyQshList('yhm');" onfocus="changeSave('fdy');">
								<html:option value=""></html:option>
								<html:options collection="yhList" property="yhm"
									labelProperty="xm" />
							</html:select>
							<input id="gyfdy" type="hidden" name="gyfdy" value="<bean:write name="rs" property="yhm"/>" />
						</logic:notEqual>
						
						</td>
						<td align="right" width="15%">
							��Ԣ����Ա������
						</td>
						<td align="left">
							<html:text name="rs" property="xm" readonly="true"></html:text>
						</td>
					</tr>
	                 <tr valign="middle">
							<td align="right" width="25%">
							<font color="red">*</font>ѧ�꣺
						</td>
						<td align="left">
							<logic:equal value="modify" name="isView">
								<html:select name="rs" property="xn" style="width:100px" styleId="xn"
									disabled="true">
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select>
								<input type="hidden" name="xn" value="<bean:write name="rs" property="xn"/>"> 
							</logic:equal>
							<logic:notEqual value="modify" name="isView">
								<html:select name="rs" property="xn" style="width:100px" styleId="xn" 
								   		onchange="jhzyQshList();">
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select>
							</logic:notEqual>
						</td>
						<td align="right" width="25%">
							<font color="red">*</font>ѧ�ڣ�
						</td>
						<td align="left">
							<logic:equal value="modify" name="isView">
								<html:select name="rs" property="xq" style="width:100px" styleId="xq"
									disabled="true">
									<html:options collection="xqList" property="xqdm"
										labelProperty="xqmc" />
								</html:select>
								<input type="hidden" name="xq" value="<bean:write name="rs" property="xq"/>"> 
							</logic:equal>
							<logic:notEqual value="modify" name="isView">
								<html:select name="rs" property="xq" style="width:100px" styleId="xq" 
										onchange="jhzyQshList();">
									<html:options collection="xqList" property="xqdm"
										labelProperty="xqmc" />
								</html:select>
							</logic:notEqual>
						</td>
					</tr>
					<tr valign="middle">
						<td align="right">
							�������ţ�
						</td>
						<td align="left">
							<html:text name='rs' property="bmmc" readonly="true"></html:text>
						</td>
						<td align="right">
							������λ��
						</td>
						<td align="left">
							<html:text name='rs' property="dwmc" readonly="true"></html:text>
						</td>
					</tr>
					<tr style="height:22px">
					<td align="right" width="25%">
							�����飺
						</td>
						<td align="left">
							<html:text name='rs' property="zmc" readonly="true"></html:text>
						</td>
						<td align="right">
							����Ա�����ң�
						</td>
						<td align="left">
							<html:text name="rs" property="gzs" styleId="gzs" maxlength="25"></html:text>
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							��ϵ�绰��
						</td>
						<td align="left">
							<html:text name="rs" property="lxdh" styleId="lxdh" style="" maxlength="15"></html:text>
						</td>
							<td align="right" nowrap>
							�������䣺
						</td>
						<td align="left">
							<html:text name="rs" property="dzyx" styleId="dzyx" maxlength="50"></html:text>
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							��ע��<br>(��200��)
						</td>
						<td align="left" colspan="3">
							<html:textarea name="rs" property="bz" styleId="bz" cols="65" rows="6" ></html:textarea>
						</td>
						
					</tr>
						<tr align="right">
							<td width="20%" align=""right"" rowspan="2">
							<span id="btn1" style="display: ">
										  <table width="97%" align="center" class="tbstyle">
											<tr align="center">
<%--													˵����--%>
<%--													1:��¥�����֣�����ʱ��ɾ�����û��ڡ��ѻ����������������¥��������--%>
<%--													������Ϣ��ֻ�����ڡ��ѻ�����������ڵ�������Ϣ<br>--%>
<%--													2:��¥�㻮�֣�����ʱ��ɾ�����û��ڡ��ѻ����������������¥����¥�������--%>
<%--													������Ϣ��ֻ�����ڡ��ѻ�����������ڵ�������Ϣ<br>--%>
<%--													3:�����ҺŻ��֣�����ʱ��ɾ�����û��ڡ��ѻ����������������¥������--%>
<%--													������Ϣ��ֻ�����ڡ��ѻ�����������ڵ�������Ϣ--%>
												</tr>
										   </table>
									</span>
									<span id="btn1" style="display: none">
										  <table width="97%" align="center" class="tbstyle">
											<tr align="center">
													<td>
														��������
														<br>
														<br>
														<html:radio property="hflx" value="ld">��¥������</html:radio><br>
														<br>
														<html:radio property="hflx" value="lc">��¥�㻮��</html:radio><br>
														<br>
														<html:radio property="hflx" value="qsh">�����ҺŻ���</html:radio><br>
													</td>
												</tr>
										   </table>
									</span>
									<span id="btn1">
										  <table width="97%" align="center" class="tbstyle">
											<tr align="center">
													<td>
											 ¥����<br>
												<html:select property="lddm"  styleId="lddm"
													onchange="jyzhLcList();jhzyQshList('');" onfocus="changeSave();">
													<html:options collection="ldList" property="dm"
														labelProperty="mc" />
												</html:select><br>
													&nbsp;&nbsp;¥��<br>
													<html:select property="lcdm"  styleId="lc"
													onchange="jhzyQshList('')" onfocus="changeSave();">
													<html:options collection="lcList" property="dm"
														labelProperty="mc" />
												</html:select><br>
			<%--										&nbsp;&nbsp;���Һţ�<br>--%>
			<%--										<html:select property="ssbh"  styleId="qsh">--%>
			<%--										--%>
			<%--										<html:options collection="qshList" property="dm"--%>
			<%--											labelProperty="mc" />--%>
			<%--								</html:select> 							--%>
																</td>
												</tr>
										   </table>
									</span>
									
							</td>
						</tr>

						<tr align="center">
							<td rowspan="2" valign="top" colspan="3">
									<table width="97%" align="center" class="tbstyle">
									<tr align="center" bgcolor="#D0E0EE">
										<td align="center">
											δ��������
										</td>
										<td>
										</td>
										<td align="center">
											�ѻ������
										</td>
									</tr>
									<tr align="center">

										<td align="center" style="width:40%;" align="left">
											<font color="red" style="font-size:10px;">��ʾ����סCtrl��Shift��(���������������ƶ�)���ж�ѡ</font>
											<br>
											
											¥������/¥��/���Һ�<input id="allsel" name="allsel" type="checkbox" onclick="allselgy()"/>(ȫѡ)
											<html:select property="whfss" style="width:100%; "
												size="27" styleId="qsh" multiple="multiple">
												<html:options collection="ssxxList" property="dm"
													labelProperty="mc" />
											</html:select>
										</td>

										<td>
											<span id="btn1">
												<table width="97%" align="center" class="tbstyle">
												<tr align="center">
														<td>
															����
															<br>
															<button class="button2" onclick="addBatchColum();"
																style="width:50px;height: 20px" title="���Ữ��">
																&rarr;
															</button>
															<br>
															�ͷ�
															<br>
															<button class="button2" onclick="delBatchColum()"
																style="width:50px;height: 20px" title="�����ͷ�">
																&larr;
															</button>
															<br>
															<br>
														</td>
													</tr>
												</table> <br />
<%--												<table width="97%" align="center" class="tbstyle">--%>
<%--													<tr align="center">--%>
<%--														<td>--%>
<%--															�� ¥ ��--%>
<%--															<br>--%>
<%--															<br>--%>
<%--															<button class="button2" onclick="addBatchColum()"--%>
<%--																style="width:50px;height: 20px" title="���Ữ��">--%>
<%--																&rarr;--%>
<%--															</button>--%>
<%--															<br>--%>
<%--															<br>--%>
<%--															<button class="button2" onclick="delBatchColum()"--%>
<%--																style="width:50px;height: 20px" title="�����ͷ�">--%>
<%--																&larr;--%>
<%--															</button>--%>
<%--															<br>--%>
<%--															<br>--%>
<%--														</td>--%>
<%--													</tr>--%>
<%--												</table> <br />--%>
<%--												<table width="97%" align="center" class="tbstyle">--%>
<%--												<tr align="center">--%>
<%--														<td>--%>
<%--															�� ¥ ��--%>
<%--															<br>--%>
<%--															<br>--%>
<%--															<button class="button2" onclick="addBatchColum()"--%>
<%--																style="width:50px;height: 20px" title="���Ữ��">--%>
<%--																&rarr;--%>
<%--															</button>--%>
<%--															<br>--%>
<%--															<br>--%>
<%--															<button class="button2"--%>
<%--																onclick="delBatchColum();"--%>
<%--																style="width:50px;height: 20px" title="�����ͷ�">--%>
<%--																&larr;--%>
<%--															</button>--%>
<%--															<br>--%>
<%--															<br>--%>
<%--														</td>--%>
<%--													</tr>--%>
<%--												</table> --%>
												</span>
										</td>
										<td align="center" align="left">
											<font color="red" style="font-size:10px;">��ʾ����סCtrl����Shift��(���������������ƶ�)���ж�ѡ</font>
											<br>
											¥������/¥��/���ұ��<input id="allnosel" name="allnosel" type="checkbox" onclick="allnoselgy();"/>(ȫѡ)
											<html:select property="yhfss" style="width:100%;" size="27"
												styleId="fdyqsh" multiple="multiple">
												<html:options collection="extssxxList" property="dm"
													labelProperty="mc" />
											</html:select>	
											<div style="display: none">
											<html:select property="yhfssbf" style="width:100%;display: none" size="27"
												styleId="fdyqshfb" multiple="multiple">
												<html:options collection="extssxxList" property="dm"
													labelProperty="mc" />
											</html:select>
											</div>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</fieldset>	
				<div class="buttontool" id="btn" align="center">
					<button class="button2"
						onclick="dataSave()"
						style="width:80px" id="buttonSave">
						����
					</button>
					&nbsp;&nbsp;
					<button class="button2" onclick="Close();return false;" style="width:80px"
						id="buttonClose">
						�ر�
					</button>
				</div>
		</html:form>
		<script language="javascript">
document.getElementById("btn").style.pixelTop = document.body.clientHeight - 35;
document.getElementById("btn").style.width = "100%";
window.setInterval("initBTNTool('btn')",1);
</script>
<logic:equal value="ok" name="done">
<script language="javascript">
alert("�����ɹ���");
//Close();
//window.dialogArguments.document.getElementById('search_go').click();   
</script>
</logic:equal>
<logic:equal value="no" name="done">
<script language="javascript" >
  alert("����ʧ�ܣ�");
//Close();
//window.dialogArguments.document.getElementById('search_go').click();   
</script>
</logic:equal>
</body>

<script type="text/javascript">
 function isEmail(sEmail){	      
 	      var p = /^[_\.0-9a-z-]+@([0-9a-z][0-9a-z-]+\.){1,4}[a-z]{2,3}$/i; 
 	      return p.test(sEmail);
 }
 function IsmustFill(mustFill){
		var eles = mustFill.split("-");
		for (i = 0; i < eles.length; i++) {
			if (document.getElementById(eles[i]).value == "") {
				alert("�뽫��\"*\"�ŵ���Ŀ����������");
				return false;
			}
		}
		return true;
	}
 function dataSave(){
    var dzyx = document.getElementById('dzyx').value;
    var xn = document.getElementById('xn').value;
    var yhm ;
    if(document.getElementById('yhm')){
    yhm = document.getElementById('yhm').value;
    }
    //var hflx = GetRadioValue("hflx");
    if(yhm==""){
		alert("��ѡ��Ԣ����Ա!");
		return false;
	}
    if(xn==""){
		alert("��ѡ��ѧ��!");
		return false;
	}
    //if(hflx=="" || hflx == null){
	//	alert("��ѡ�񻮷����� !");
	//	return false;
	//}
	if(!isEmail(dzyx) && dzyx!=""){
		alert("��������ȷ�ĵ�������!");
		return false;
	}
	if($("bz").value.length>200){
	   alert("��ע�������ܴ���200�֣�");
	   return false;
	}
      var yhfss= document.getElementById("yhfss"); 
      //if(yhfss.options.length<1){
    	//  alert("�ѻ������Ϊ�գ�");
   	   //	return false;
      //}
      BatAlert.showTips("����ִ�У���ȴ�����");
      
	      for(var i=0;i<yhfss.options.length;i++){	
	    	  yhfss.options[i].selected = true;
		    }
	
	    if(document.getElementById("yhfssbf")){
		  var yhfssbf= document.getElementById("yhfssbf"); 
	      for(var i=0;i<yhfssbf.options.length;i++){	
	    	  yhfssbf.options[i].selected = true;
		    }
		  }
      BatAlert.closeTips();
      if(yhfss.length==0){
     	 refreshForm('/xgxt/jhzy_gygl.do?method=gyfdyOperation&doType=save');
      }else if(IsmustFill('xn-xq')){
        refreshForm('/xgxt/jhzy_gygl.do?method=gyfdyOperation&doType=save');
    }
    issubmit = true;
 }
 
 </script>
</html>
		

		
