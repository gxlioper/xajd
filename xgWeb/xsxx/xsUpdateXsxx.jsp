<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- ͷ�ļ� -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/xsxx/xsxxzgkdFunction.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/interface/getStuDetails.js"></script>
	<script language="javascript" src="js/check.js"></script>
	<script type="text/javascript" src="js/xsxx/xsxxplczFunction.js"></script>
	<script>
		function saveXsxx(){
			var flag = true;	
			setVal('jg',val('jgshen')+"/"+val('jgshi')+"/"+val('jgxian'));
			setVal('syd',val('syshen')+"/"+val('syshi')+"/"+val('syxian'));
			setVal('hkszd',val('hkshen')+"/"+val('hkshi')+"/"+val('hkxian'));
			
			var zdList = val('zdList');
			var zdColumn = zdList.split("!!");			
			for(var i=0; i<zdColumn.length-1; i++){	
				if("jg" == zdColumn[i]){
					if(val('jgV') != val('jgshen')+val('jgshi')+val('jgxian')){
						setVal('xgzd',val('xgzd')+"@!!@jg");
					}
				}else if("syd" == zdColumn[i]){
					if(val('sydV') != val('syshen')+val('syshi')+val('syxian')){
						setVal('xgzd',val('xgzd')+"@!!@syd");
					}
				}else if("hkszd" == zdColumn[i]){
					if(val('hkszdV') != val('hkshen')+val('hkshi')+val('hkxian')){
						setVal('xgzd',val('xgzd')+"@!!@hkszd");
					}
				}else if("xb" == zdColumn[i]){
					if(val(zdColumn[i]+"VV") != radioValue(zdColumn[i])){
						setVal('xgzd',val('xgzd')+"@!!@"+zdColumn[i]);
					}
				}else{
					if(val(zdColumn[i]+"V") != val(zdColumn[i])){
						setVal('xgzd',val('xgzd')+"@!!@"+zdColumn[i]);
					}
				}
			}
			
			
			
			//  ----------�ж��Ա��Ƿ������֤һ�� begin-----------
			var checkXb=$("xbVV").value;
			
			if($("xbn") && !$("xbn").disabled && $("xbn").checked){
				checkXb=$("xbn").value;
			}else if($("xbv") && $("xbv").disabled==false && $("xbv").checked){
				checkXb=$("xbv").value;
			}
			//  ----------�ж��Ա��Ƿ������֤һ�� end-----------
			
			//  ----------�жϳ��������Ƿ������֤һ�� begin-----------
			var checkCsrq=$("csrqV").value;
			if($("csrq") && !$("csrq").disabled){
				checkCsrq=$("csrq").value;
			}
			//  ----------�жϳ��������Ƿ������֤һ�� begin-----------
			
			// -----------���֤ begin-----
			var checkSfzh=$("sfzhV").value;
			if($("sfzh") && !$("sfzh").disabled){
				checkSfzh=$("sfzh").value;
			}
			//------------���֤ end-----
			
			var xxdm = $("xxdm").value;
			if(xxdm=="10653"){//�ɶ�����ѧԺ
				var sjhm = ($("sjhm")&&$("sjhm")!=null)?$("sjhm").value:"";
				var lxdh1 = ($("lxdh1")&&$("lxdh1")!=null)?$("lxdh1").value:"";
				var jtszd = ($("jtszd")&&$("jtszd")!=null)?$("jtszd").value:"";
				if(sjhm==""){
				     alert("�ֻ����벻��Ϊ�գ�");
				     return false;
				}
				if(lxdh1==""){
				     alert("��ͥ�绰����Ϊ�գ�");
				     return false;
				}
				if(jtszd==""){
				     alert("��ͥ��ַ����Ϊ�գ�");
				     return false;
				}
			}
			
			if(checkSfzh!="" && checkSfzh.length==18){
				var csrqStr=checkSfzh.substring(6,14);
				var xbStr=eval(checkSfzh.substring(16,17))%2==0?'Ů':'��' ;
				
				if(checkCsrq!="" && checkCsrq!=csrqStr){
					if(!confirm("����ĳ������������֤��Ϣ����,�Ƿ������")){
						return false;
					}
				}
				if(checkXb!="" && checkXb!=xbStr){
					if(!confirm("ѡ����Ա������֤��Ϣ����,�Ƿ������")){
						return false;
					}
				}
			}else if(checkSfzh.length==15){
				var csrqStr=checkSfzh.substring(6,12);
				var xbStr=eval(checkSfzh.substring(14,15))/2==0?'Ů':'��' ;
				
				if(checkCsrq!="" && checkCsrq.substring(2,8)!=csrqStr){
					if(!confirm("����ĳ������������֤��Ϣ����,�Ƿ������")){
						return false;
					}
				}
				
				
				if(checkXb!="" && checkXb!=xbStr){
					if(!confirm("ѡ����Ա������֤��Ϣ����,�Ƿ������")){
						return false;
					}
				}
			}
			
			
			
			if(val('xgzd') == null || val('xgzd') == ''){
				alert('δ����Ϣ�����޸ģ�');
				return false;
			}else{
	        	refreshForm('xsxxgl.do?method=saveStuinfoModi&xgzd=' + val('xgzd'));
	          	$("saveButton").disabled=true;
          	}
    	}
    	
    	function showColumns(){
			var zdList = val('zdList');
			var zdColumn = zdList.split("!!");			
			for(var i=0; i<zdColumn.length-1; i++){	
				if(zdColumn[i] == "jg"){
					if(document.getElementById("jgshen")){
						document.getElementById("jgshen").disabled=false;
					}
					if(document.getElementById("jgshi")){
						document.getElementById("jgshi").disabled=false;
					}
					if(document.getElementById("jgxian")){
						document.getElementById("jgxian").disabled=false;
					}
				}
				if(zdColumn[i] == "syd"){
					if(document.getElementById("syshen")){
						document.getElementById("syshen").disabled=false;
					}
					if(document.getElementById("syshi")){
						document.getElementById("syshi").disabled=false;
					}
					if(document.getElementById("syxian")){
						document.getElementById("syxian").disabled=false;
					}
				}
				if(zdColumn[i] == "hkszd"){
					if(document.getElementById("hkshen")){
						document.getElementById("hkshen").disabled=false;
					}
					if(document.getElementById("hkshi")){
						document.getElementById("hkshi").disabled=false;
					}
					if(document.getElementById("hkxian")){
						document.getElementById("hkxian").disabled=false;
					}
				}
				if(document.getElementById(zdColumn[i])){				
					document.getElementById(zdColumn[i]).disabled=false;
				}
						
				if(zdColumn[i]=="xb"){
					if(document.getElementById('xbn')){
						document.getElementById('xbn').disabled=false;
					}
					if(document.getElementById('xbv')){
						document.getElementById('xbv').disabled=false;
					}
				}
				/*if(zdColumn[i]=='xydm'){
					if(document.getElementById("xy")){
						document.getElementById("xy").disabled=true;
					}			
				}
				if(zdColumn[i]=='zydm'){
					if(document.getElementById("zy")){
						document.getElementById("zy").disabled=true;
					}
				}
				if(zdColumn[i]=='bjdm'){
					if(document.getElementById("bj")){
						document.getElementById("bj").disabled=true;
					}
				}			
				*/
			}
			var ele = ['jt1','jt2','jt3'];
			for(var i=0; i<ele.length; i++){
				if(document.getElementById(ele[i])){
					if(document.getElementById(ele[i])){
						document.getElementById(ele[i]).style.display="none";
					}
				}
			}
			
			//�Ѿ��޸Ĺ����ֶα�Ϊ��ɫ
			var yxgzd = val('yxgzd');
			var yxgzdArr = yxgzd.split('@!!@');
			for(var i=0; i<yxgzdArr.length; i++){
				if(yxgzdArr[i] != null && yxgzdArr[i] != ''){
					document.getElementById("f_"+yxgzdArr[i]).className = "red";
				}
			}
		}
	</script>
</head>
<body onload="showColumns();loadXsxxjl();loadXsshgx();">
		<html:form action="/xsxxgl.do?method=stuModiInfo" method="post">
			<input type="hidden" id="zdList" name="zdList" value="${zdList}"/>
			<input type="hidden" name="url" id="url" value="xsxxgl.do?method=stuModiInfo"/>
			<input type="hidden" name="redirect" id="redirect" value=""/>
			<input type="hidden" name="variable" id="variable" value=""/>
			<!--�޸��ֶ�-->
			<input type="hidden" name="xgzd" id="xgzd" value=""/>
			<!--���޸��ֶ�-->
			<input type="hidden" name="yxgzd" id="yxgzd" value="${xgzd}"/>
			<input type="hidden" name="sfzcV" id="sfzcV" value="${rs.sfzc}"/>
			<input type="hidden" name="nfbyV" id="nfbyV" value="${rs.nfby}"/>
			<input type="hidden" id="xxdm" name="xxdm" value="${xxdm}" />
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>ѧ����Ϣ - ��Ϣ�޸� - �޸ĸ�����Ϣ</a>
				</p>
			</div>			
			<logic:notEqual name="userOnLine" value="student" scope="session">
				<br />
				<br />
				<br />
				<p align="center">
					��ҳ��ֻ��ѧ�����Է���
				</p>
			</logic:notEqual>
			<logic:equal value="student" name="userOnLine" scope="session">			
			<logic:equal name="sqsjFlag" value="false">
				<script>
		   			 alert("�����趨���޸�ʱ�䷶Χ��,�ݲ����Ÿù���!");
		    		 location.href="about:blank";
   			 	</script>
			</logic:equal>
			<logic:equal value="yes" name="xsxxshFlag">
				<logic:equal value="stu" name="userType">
				<logic:notEmpty name="message">
				<div class="prompt">
		          <h3><span>��ʾ��</span></h3>
		          <p>
					${message}
				  </p>
		          <a class="close" title="����" onclick="this.parentNode.style.display='none'"></a>
		      	</div>
				</logic:notEmpty>
				</logic:equal>
			</logic:equal>
			<div class="tab">
			<table class="formlist" id="rsTable" width="100%">
				<thead>
					<tr style="cursor:hand">
						<th colspan="4" onclick="document.getElementById('xsjbxx').style.display=(document.getElementById('xsjbxx').style.display==''?'none':'')">
							<span>������Ϣ</span>
						</th>
					</tr>
				</thead>
				<tbody id="xsjbxx">
				<tr>
					<th id="f_xh"><span class="red">*</span>ѧ��</th>
					<td>
						<html:text property="xh" name="rs" disabled="true" styleId="xh" maxlength="20"/>	
						<html:hidden property="xh" name="rs1" styleId="xhV"/>
					</td>
					<th id="f_xm">����</th>
					<td>
						<html:text property="xm" name="rs" disabled="true" maxlength="16" styleId="xm"/>
						<html:hidden property="xm" name="rs1" styleId="xmV"/>
					</td>
				</tr>	
				<tr>
					<th id="f_xb">�Ա�</th>
					<td>
						<html:radio property="xb" value="��" name="rs" disabled="true" styleId="xbn">��</html:radio>
						<html:radio property="xb" value="Ů" name="rs" disabled="true"  styleId="xbv">Ů</html:radio>
						<html:hidden property="xb" name="rs1" styleId="xbVV"/>
					</td>
					<th id="f_csrq">��������</th>
					<td>
						<html:text property="csrq" name="rs" disabled="true" readonly="" onclick="return showCalendar('csrq','ymmdd');" styleId="csrq"/>
						<html:hidden property="csrq" name="rs1" styleId="csrqV"/>
					</td>
					
				</tr>	
				<tr>
					<th id="f_mz">����</th>
					<td>
						<html:select property="mz" name="rs" disabled="true" styleId="mz"  style="width:160px">
							<html:option value=""></html:option>
							<html:options collection="mzList" property="mzdm" labelProperty="mzmc"/>
						</html:select>
						<html:hidden property="mz" name="rs1" styleId="mzV"/>
					</td>
					<th id="f_zzmm">������ò</th>
					<td>
						<html:select property="zzmm" name="rs" disabled="true" styleId="zzmm"  style="width:160px">
							<html:option value=""></html:option>
							<html:options collection="zzmmList" property="zzmmdm" labelProperty="zzmmmc"/>
						</html:select>
						<html:hidden property="zzmm" name="rs1" styleId="zzmmV"/>
					</td>
				</tr>
				<tr>
					<th id="f_sfzh">���֤��</th>
					<td>
						<div class="pos" style="z-index:1">
							<html:text name="rs" property="sfzh" disabled="true" styleId="sfzhgggg" maxlength="18" styleClass="text_nor" onblur="checkSfzh(this)" />
							<html:hidden property="sfzh" name="rs1" styleId="sfzhV"/>
							<div id="sfzhErrow" class="hide">
								<p>
									���֤�Ÿ�ʽ����ȷ
								</p>
							</div>
						</div>
					</td>
					<th id="f_pycc">�������</th>
					<td>
						<html:select name="rs" property="pycc" style="" onchange="" styleId="pycc" disabled="true">
							<html:options collection="xsccList" property="dm" labelProperty="mc" />
						</html:select>
						<html:hidden property="pycc" name="rs1" styleId="pyccV"/>
					</td>					
				</tr>
				<tr>
					<th id="f_jg">����</th>
					<td colspan="3">
						<input type="hidden" name="jg" value="" id=""/>
						<input type="hidden"  value="${rs1.jgshen}${rs1.jgshi}${rs1.jgxian}" id="jgV"/>
						<html:select name="rs" property="jgshen" styleId="jgshen" onchange="loadShi('jgshen','jgshi','jgxian');" disabled="true">
							<html:option value="">--��ѡ��--</html:option>
							<html:options collection="ssList" property="ssdm"
								labelProperty="ssmc" />
						</html:select> ʡ/��
						<html:select name="rs" property="jgshi" styleId="jgshi"
							onchange="loadXian('jgshi','jgxian')" disabled="true">
							<html:options collection="jgshiList" property="shidm"
								labelProperty="shimc" />
						</html:select> ��
						<html:select name="rs" property="jgxian" styleId="jgxian" disabled="true">
							<html:options collection="jgxianList" property="xiandm"
								labelProperty="xianmc" />
						</html:select> ��/��
					</td>
				</tr>
				<tr>
					<th id="f_syd">��Դ����(��Դ��)</th>
					<td colspan="3">
						<input type="hidden" name="syd" value="" id=""/>
						<input type="hidden"  value="${rs1.syshen}${rs1.syshi}${rs1.syxian}" id="sydV"/>
						<html:select name="rs" property="syshen" styleId="syshen" onchange="loadShi('syshen','syshi','syxian')" disabled="true">
							<html:option value="">--��ѡ��--</html:option>
							<html:options collection="ssList" property="ssdm"
								labelProperty="ssmc" />
						</html:select> ʡ/��
						<html:select name="rs" property="syshi" styleId="syshi"
							onchange="loadXian('syshi','syxian')" disabled="true">								
							<html:options collection="shiList" property="shidm"
								labelProperty="shimc" />
						</html:select> ��
						<html:select name="rs" property="syxian" styleId="syxian" disabled="true">								
							<html:options collection="xianList" property="xiandm"
								labelProperty="xianmc" />
						</html:select> ��/��
					</td>			
				</tr>
				</tbody>
				<thead>
					<tr style="cursor:hand">
						<th colspan="4" onclick="document.getElementById('xsxjxx').style.display=(document.getElementById('xsxjxx').style.display==''?'none':'')">
							<span>ѧ����Ϣ</span>
						</th>
					</tr>
				</thead>
				<tbody id="xsxjxx" style="display:none">
				<tr>
					<th id="f_nj">�꼶</th>
					<td>
						<html:select property="nj" name="rs" disabled="true" styleId="nj"  style="width:160px">
							<html:option value=""></html:option>
							<html:options collection="njList" property="nj" labelProperty="nj"/>
						</html:select>
						<input type="hidden"  value="${rs1.nj}" id="njV"/>
					</td>
					<th id="f_xz">ѧ��(��)</th>
					<td>
						<html:text property="xz" name="rs" disabled="true" maxlength="2" onkeyup="value=value.replace(/[^\d]/g,'')"/>
						<input type="hidden"  value="${rs1.xz}" id="xzV"/>
					</td>
				</tr>
				<tr>
					<th id="f_xydm"><span class="red">*</span><bean:message key="lable.xsgzyxpzxy" /></th>
					<td>
						<html:select property="xydm" name="rs" disabled="true" styleId="xy" style="width:160px">
							<html:option value=""></html:option>
							<html:options collection="xyList" property="xydm" labelProperty="xymc"/>
						</html:select>
						<input type="hidden"  value="${rs1.xydm}" id="xydmV"/>
					</td>
					<th id="f_sfzc">�Ƿ�ע��</th>
					<td>
						<html:select property="sfzc" name="rs" style="width:120px" styleId="sfzc" disabled="true">
							<html:option value=""></html:option>
							<html:option value="��">��</html:option>
							<html:option value="��">��</html:option>
						</html:select>
						<input type="hidden"  value="${rs1.sfzc}" id="sfzcV"/>
					</td>
				</tr>	
				<tr>
					<th id="f_zydm"><span class="red">*</span>רҵ</th>
					<td>
						<html:select property="zydm" name="rs" disabled="true" styleId="zy"  style="width:160px">
							<html:option value=""></html:option>
							<html:options collection="zyList" property="zydm" labelProperty="zymc"/>
						</html:select>
						<input type="hidden"  value="${rs1.zydm}" id="zydmV"/>
					</td>
					<th id="f_sfzd">�Ƿ��߶�</th>
					<td>
						<html:select property="sfzd" name="rs" style="width:120px" styleId="sfzd" disabled="true">
							<html:option value=""></html:option>
							<html:option value="��">��</html:option>
							<html:option value="��">��</html:option>
						</html:select>
						<input type="hidden"  value="${rs1.sfzd}" id="sfzdV"/>
					</td>
				</tr>
				<tr>
					<th id="f_bjdm"><span class="red">*</span>�༶</th>
					<td>
						<html:select property="bjdm" name="rs" disabled="true" styleId="bj"  style="width:160px">
							<html:option value=""></html:option>
							<html:options collection="bjList" property="bjdm" labelProperty="bjmc"/>
						</html:select>
						<input type="hidden"  value="${rs1.bjdm}" id="bjdmV"/>
					</td>
					<th id="f_sfzx">�Ƿ���У</th>
					<td>
						<html:select property="sfzx" name="rs" style="width:120px" disabled="true">
							<html:option value=""></html:option>
							<html:option value="��У">��У</html:option>
							<html:option value="����У">����У</html:option>
						</html:select>
						<input type="hidden"  value="${rs1.sfzx}" id="sfzxV"/>
					</td>
				</tr>
				<tr>
					<th id="f_xjztm">ѧ��״̬</th>
					<td>
						<html:select property="xjztm" name="rs" disabled="true" styleId="xjztm"  style="width:160px">
							<html:option value=""></html:option>
							<html:options collection="xjztList" property="zxdmmc" labelProperty="zxdmmc"/>
						</html:select>
						<input type="hidden"  value="${rs1.xjztm}" id="xjztmV"/>
					</td>
					<th id="f_sfbys">�Ƿ��ҵ��</th>
					<td>
						<html:select property="sfbys" name="rs" style="width:120px" disabled="true">
							<html:option value=""></html:option>
							<html:option value="��">��</html:option>
							<html:option value="��">��</html:option>
						</html:select>
						<input type="hidden"  value="${rs1.sfbys}" id="sfbysV"/>
					</td>
				</tr>
				<tr>
					<th id="f_rxrq">��ѧʱ��</th>
					<td>
						<html:text property="rxrq" name="rs" disabled="true" styleId="rxrq" maxlength="10" onclick="return showCalendar('rxrq','y-mm-dd');" />
						<input type="hidden"  value="${rs1.rxrq}" id="rxrqV"/>
					</td>
					<th id="f_sfyby">�Ƿ��ҵ</th>
					<td>
						<html:select property="sfyby" name="rs" style="width:120px" disabled="true">
							<html:option value=""></html:option>
							<html:option value="��">��</html:option>
							<html:option value="��">��</html:option>
						</html:select>
						<input type="hidden"  value="${rs1.sfyby}" id="sfybyV"/>
					</td>					
				</tr>
				<tr>					
					<th id="f_byny">��ҵʱ��</th>
					<td colspan="3">
						<html:text property="byny" name="rs" disabled="true" styleId="byny" maxlength="10"  onclick="return showCalendar('byny','y-mm-dd');"/>
						<input type="hidden"  value="${rs1.byny}" id="bynyV"/>
					</td>
				</tr>	
				</tbody>
				<thead>
					<tr style="cursor:hand">
						<th colspan="4" onclick="document.getElementById('xstxxx').style.display=(document.getElementById('xstxxx').style.display==''?'none':'')">
							<span>��ϵ��ʽ��֤��</span>
						</th>
					</tr>
				</thead>
				<tbody id="xstxxx" style="display:none">
				<tr>					
					<th id="f_lxdh">
					<!-- ��ְҵ����ѧԺ -->
					<logic:equal name="xxdm" value="12061">
					�̺�
					</logic:equal>
					<!-- end��ְҵ����ѧԺ -->
					<logic:notEqual name="xxdm" value="12061">
					�̶��绰
					</logic:notEqual>
					</th>
					<td>
						<div class="pos" style="z-index:2">
							<html:text property="lxdh" name="rs" disabled="true" styleId="lxdh" maxlength="15" onblur="checkPhoneV4(this)"/>
							<input type="hidden"  value="${rs1.lxdh}" id="lxdhV"/>
							<div id="phoneErrow" class="hide">
								<p>
									�绰��ʽ����ȷ
								</p>
							</div>
						</div>
					</td>
					<th id="f_sjhm"><logic:equal value="10653" name="xxdm"><span class="red">*</span></logic:equal>�ֻ�����</th>
					<td>
						<div class="pos" style="z-index:2">
							<html:text property="sjhm" name="rs" disabled="true" styleId="sjhm" maxlength="11" onkeyup="value=value.replace(/[^\d]/g,'')" onblur="checkPhoneError(this,'sjhmerror')"/>
							<input type="hidden"  value="${rs1.sjhm}" id="sjhmV"/>
							<div id="sjhmerror" class="hide">
								<p>
									�ֻ������ʽ����ȷ
								</p>
							</div>
						</div>						
					</td>		
				</tr>	
				<tr>
					<th id="f_qqhm">QQ����</th>
					<td>
						<html:text property="qqhm" name="rs" disabled="true" styleId="qqhm" maxlength="20" onkeyup="value=value.replace(/[^\d]/g,'') " />
						<input type="hidden"  value="${rs1.qqhm}" id="qqhmV"/>
					</td>	
					<th id="f_dzyx">��������</th>
					<td>
						<div class="pos" style="z-index:1">
							<html:text property="dzyx" name="rs" disabled="true" styleId="dzyx" maxlength="32" onblur="checkEmaile(this)"/>
							<input type="hidden"  value="${rs1.dzyx}" id="dzyxV"/>
							<div id="emaliErrow" class="hide">
								<p>
									���������ʽ����ȷ
								</p>
							</div>
						</div>						
					</td>				
									
				</tr>
				<tr>					
					<th id="f_yhdm">��������</th>
					<td>
						<html:select property="yhdm" name="rs" styleId="yhdm" disabled="true">
							<html:option value=""></html:option>
							<html:options collection="yhList" property="yhdm" labelProperty="yhmc"/>
						</html:select>
						<input type="hidden"  value="${rs1.yhdm}" id="yhdmV"/>
					</td>
					<th id="f_yhkh">���п���</th>
					<td>
						<html:text name="rs" property="yhkh" maxlength="20" onkeyup="value=value.replace(/[^\d]/g,'') " disabled="true"/>
						<input type="hidden"  value="${rs1.yhkh}" id="yhkhV"/>
					</td>
				</tr>
				<tr>
					<th id="f_kh">һ��ͨ��</th>
					<td colspan="3">
						<html:text name="rs" property="kh" maxlength="20" onkeyup="value=value.replace(/[^\d]/g,'') "
							style="ime-mode:disabled" disabled="true"/>
						<input type="hidden"  value="${rs1.kh}" id="khV"/>
					</td>					
				</tr>
				</tbody>
				<thead>
					<tr style="cursor:hand">
						<th colspan="4" onclick="document.getElementById('xsqtxx').style.display=(document.getElementById('xsqtxx').style.display==''?'none':'')">
							<span>������Ϣ</span>
						</th>
					</tr>
				</thead>
				<tbody id="xsqtxx" style="display:none">
				<tr>
					<th id="f_xmpy">����ƴ��</th>
					<td>
						<html:text property="xmpy" name="rs" disabled="true" styleId="xmpy" maxlength="32"/>
						<input type="hidden"  value="${rs1.xmpy}" id="xmpyV"/>
					</td>
					<th id="f_cym">������</th>
					<td>
						<html:text property="cym" name="rs" disabled="true" styleId="cym" maxlength="16"/>
						<input type="hidden"  value="${rs1.cym}" id="cymV"/>
					</td>
				</tr>
				<tr>
					<th id="f_sg">���(cm)</th>
					<td>
						<html:text property="sg" name="rs" disabled="true" styleId="sg" maxlength="10" onkeyup="value=value.replace(/[^\d]/g,'')"/>
						<input type="hidden"  value="${rs1.sg}" id="sgV"/> 
					</td>
					<th id="f_tz">����(kg)</th>
					<td>
						<html:text property="tz" name="rs" disabled="true" styleId="tz" maxlength="10" onkeyup="value=value.replace(/[^\d|.]/g,'')"/>
						<input type="hidden"  value="${rs1.tz}" id="tzV"/> 
					</td>
				</tr>
				<tr>					
					<th id="f_tc">�س�</th>
					<td>
						<html:text property="tc" name="rs" disabled="true" styleId="tc" maxlength="32"/>
						<input type="hidden"  value="${rs1.tc}" id="tcV"/>
					</td>
					<th id="f_kslb">�������</th>
					<td>
						<html:select property="kslb" name="rs" disabled="true">
							<html:options collection="kslbList" property="dm" labelProperty="mc"/>
						</html:select>
						<input type="hidden"  value="${rs1.kslb}" id="kslbV"/>
					</td>
				</tr>					
				<tr>
					<th id="f_rxfs">��ѧ��ʽ</th>
					<td>
						<html:select property="rxfs" name="rs" disabled="true">
							<html:options collection="rxfsList" property="dm" labelProperty="mc"/>
						</html:select>
						<input type="hidden"  value="${rs1.rxfs}" id="rxfsV"/>
					</td>
					<th id="f_pyfs">������ʽ</th>
					<td>
						<html:select property="pyfs" name="rs" disabled="true">
							<html:options collection="pyfsList" property="dm" labelProperty="mc"/>
						</html:select>
						<input type="hidden"  value="${rs1.pyfs}" id="pyfsV"/>
					</td>
				</tr>
				<tr>
					<th id="f_hkszd">�������ڵ�</th>
					<td colspan="3">
						<input type="hidden" name="hkszd" value=""/>
 						<input type="hidden"  value="${rs1.hkshen}${rs1.hkshi}${rs1.hkxian}" id="hkszdV"/>
						<html:select name="rs" property="hkshen" styleId="hkshen" onchange="loadShi('hkshen','hkshi','hkxian');" disabled="true">
							<html:option value="">--��ѡ��--</html:option>
							<html:options collection="ssList" property="ssdm"
								labelProperty="ssmc" />
						</html:select> ʡ/��
						<html:select name="rs" property="hkshi" styleId="hkshi"
							onchange="loadXian('hkshi','hkxian')" disabled="true">
							<html:options collection="hkshiList" property="shidm"
								labelProperty="shimc" />
						</html:select> ��
						<html:select name="rs" property="hkxian" styleId="hkxian" disabled="true">
							<html:options collection="hkxianList" property="xiandm"
								labelProperty="xianmc" />
						</html:select> ��/��				
					</td>
				</tr>	
				</tbody>
				<thead>
					<tr style="cursor:hand">
						<th colspan="4" onclick="document.getElementById('jtjbxx').style.display=(document.getElementById('jtjbxx').style.display==''?'none':'')">
							<span>��ͥ��Ϣ</span>
						</th>
					</tr>
				</thead>
				<tbody id="jtjbxx" style="display:none">	
				<tr><td colspan="4">
				<table class="formlist" width="100%">
				<tbody>
				<tr>					
					<th id="f_lxdh1"><logic:equal value="10653" name="xxdm"><span class="red">*</span></logic:equal>��ͥ�绰</th>
					<td>
						<html:text property="lxdh1" name="rs" disabled="true" styleId="lxdh1" maxlength="50"/>
						<input type="hidden"  value="${rs1.lxdh1}" id="lxdh1V"/>
					</td>
					<th id="f_yb">��ͥ�ʱ�</th>
					<td>
						<html:text property="yb" name="rs" disabled="true" styleId="yb" maxlength="10" onkeyup="value=value.replace(/[^\d]/g,'')"/>
						<input type="hidden"  value="${rs1.yb}" id="ybV"/>
					</td>
				</tr>
				<tr>
					<th id="f_jtszd"><logic:equal value="10653" name="xxdm"><span class="red">*</span></logic:equal>��ͥ��ַ</th>
					<td colspan="3">
						<html:text property="jtszd" name="rs" disabled="true" styleId="jtszd" maxlength="25" style="width:600px"/>
						<input type="hidden"  value="${rs1.jtszd}" id="jtszdV"/>
					</td>
				</tr>
				<tr>
					<th id="f_jjzk">��ͥ�������</th>
					<td colspan="3">
						<html:textarea name="rs" property="jjzk" cols="60" rows="4" onblur="chLeng(this,100)" styleId="jjzk" disabled="true"/>
						<input type="hidden"  value="${rs1.jjzk}" id="jjzkV"/>
					</td>
				</tr>
				</tbody>
				</table></td></tr>	
				<tr><td colspan="4">
				<table class="formlist" width="100%">		
				<thead>
					<tr>
						<td colspan="4" style="cursor:hand" align="center"
							onclick="document.getElementById('jt1').style.display=(document.getElementById('jt1').style.display==''?'none':'')">
								ѧ����ͥ��Ա��Ϣ1
						</td>
					</tr>
				</thead>
				<tbody>
				<tr id="jt1">
					<td colspan="4">					
					<table class="formlist" width="100%">	
						<tbody>					
						<tr>
							<th id="f_jtcy1_xm">����</th>
							<td>
								<html:text property="jtcy1_xm" name="rs" disabled="true" styleId="jtcy1_xm" maxlength="25"/>
								<input type="hidden"  value="${rs1.jtcy1_xm}" id="jtcy1_xmV"/>
							</td>
							<th id="f_jtcy1_gx">�뱾�˹�ϵ</th>
							<td>
								<html:select name="rs" property="jtcy1_gx" styleId="jtcy1_gx" disabled="true">
									<html:option value="" />
									<html:options collection="jtgxList" labelProperty="mc" property="mc"/>
								</html:select>	
								<input type="hidden"  value="${rs1.jtcy1_gx}" id="jtcy1_gxV"/>
							</td>
						</tr>				
						<tr>
							<th id="f_jtcy1_nl">��������</th>
							<td>
								<html:text property="jtcy1_nl" name="rs" disabled="true" styleId="jtcy1_nl" readonly="" onclick="return showCalendar('jtcy1_nl','y-mm-dd');" styleId="jtcy1_nl"/>
								<input type="hidden"  value="${rs1.jtcy1_nl}" id="jtcy1_nlV"/>
							</td>
							<th id="f_jtcy1_sfzh">���֤��</th>
							<td>
								<html:text property="jtcy1_sfzh" name="rs" disabled="true" styleId="jtcy1_sfzh" maxlength="18" onblur="if(!checkSfzh(this)){this.focus();}"/>
								<input type="hidden"  value="${rs1.jtcy1_sfzh}" id="jtcy1_sfzhV"/>
							</td>
						</tr>						
						<tr>
							<th id="f_jtcy1_mz">����</th>
							<td>
								<html:select property="jtcy1_mz" name="rs" disabled="true" styleId="jtcy1_mz">
									<html:option value=""></html:option>
									<html:options collection="mzList" property="mzdm" labelProperty="mzmc"/>
								</html:select>
								<input type="hidden"  value="${rs1.jtcy1_mz}" id="jtcy1_mzV"/>
							</td>
							<th id="f_jtcy1_zzmm">������ò</th>
							<td>
								<html:select property="jtcy1_zzmm" name="rs" disabled="true" styleId="jtcy1_zzmm">
									<html:option value=""></html:option>
									<html:options collection="zzmmList" property="zzmmdm" labelProperty="zzmmmc"/>
								</html:select>
								<input type="hidden"  value="${rs1.jtcy1_zzmm}" id="jtcy1_zzmmV"/>
							</td>
						</tr>						
						<tr>
							<th id="f_jtcy1_zy">ְҵ</th>
							<td>
								<html:text property="jtcy1_zy" name="rs" disabled="true" styleId="jtcy1_zy" maxlength="10"/>
								<input type="hidden"  value="${rs1.jtcy1_zy}" id="jtcy1_zyV"/>
							</td>
							<th id="f_jtcy1_zw">ְ��</th>
							<td>
								<html:text property="jtcy1_zw" name="rs" disabled="true" styleId="jtcy1_zw" maxlength="10"/>
								<input type="hidden"  value="${rs1.jtcy1_zw}" id="jtcy1_zwV"/>
							</td>
						</tr>
						<tr>
							<th id="f_jtcy1_lxdh1">������λ�绰</th>
							<td>
								<html:text property="jtcy1_lxdh1" name="rs" disabled="true" styleId="jtcy1_lxdh1" maxlength="25"/>
								<input type="hidden"  value="${rs1.jtcy1_lxdh1}" id="jtcy1_lxdh1V"/>
							</td>
							<th id="f_jtcy1_lxdh2">���˵绰</th>
							<td>
								<html:text property="jtcy1_lxdh2" name="rs" disabled="true" styleId="jtcy1_lxdh2" maxlength="25"/>
								<input type="hidden"  value="${rs1.jtcy1_lxdh2}" id="jtcy1_lxdh2V"/>
							</td>
						</tr>
						<tr>
							<th id="f_jtcy1_gzdz">������ַ</th>
							<td colspan="3">
								<html:text property="jtcy1_gzdz" name="rs" disabled="true" styleId="jtcy1_gzdz" maxlength="25" style="width:90%"/>
								<input type="hidden"  value="${rs1.jtcy1_gzdz}" id="jtcy1_gzdzV"/>
							</td>			
						</tr>	
						</tbody>		
					</table>
				</td>
				</tr>
				</tbody>	
				</table></td></tr>
				<tr><td colspan="4">
				<table width="100%">			
				<thead>
					<tr>
						<td colspan="4" style="cursor:hand" align="center"
							onclick="document.getElementById('jt2').style.display=(document.getElementById('jt2').style.display==''?'none':'')">
								ѧ����ͥ��Ա��Ϣ2
						</td>
					</tr>
				</thead>
			
				<tbody>
				<tr id="jt2">
					<td colspan="4">					
						<table class="formlist" width="100%">	
						<tbody>			
							<tr>
								<th id="f_jtcy2_xm">����</th>
								<td>
									<html:text property="jtcy2_xm" name="rs" disabled="true" styleId="jtcy2_xm" maxlength="25"/>
									<input type="hidden"  value="${rs1.jtcy2_xm}" id="jtcy2_xmV"/>
								</td>								
								<th id="f_jtcy2_gx">�뱾�˹�ϵ</th>
								<td>
									<html:select name="rs" property="jtcy2_gx" styleId="jtcy2_gx" disabled="true">
										<html:option value="" />
										<html:options collection="jtgxList" labelProperty="mc" property="mc"/>
									</html:select>	
									<input type="hidden"  value="${rs1.jtcy2_gx}" id="jtcy2_gxV"/>
								</td>
							</tr>
							<tr>
								<th id="f_jtcy2_nl">��������</th>
								<td>
									<html:text property="jtcy2_nl" name="rs" disabled="true" styleId="jtcy2_nl" readonly="" onclick="return showCalendar('jtcy2_nl','y-mm-dd');" styleId="jtcy2_nl"/>
									<input type="hidden"  value="${rs1.jtcy2_nl}" id="jtcy2_nlV"/>
								</td>
								<th id="f_jtcy2_sfzh">���֤��</th>
								<td>
									<html:text property="jtcy2_sfzh" name="rs" disabled="true" styleId="jtcy2_sfzh" maxlength="18"  onblur="if(!checkSfzh(this)){this.focus();}"/>
									<input type="hidden"  value="${rs1.jtcy2_sfzh}" id="jtcy2_sfzhV"/>
								</td>
							</tr>
							<tr>
								<th id="f_jtcy2_mz">����</th>
								<td>
									<html:select property="jtcy2_mz" name="rs" disabled="true" styleId="jtcy2_mz">
										<html:option value=""></html:option>
										<html:options collection="mzList" property="mzdm" labelProperty="mzmc"/>
									</html:select>
									<input type="hidden"  value="${rs1.jtcy2_mz}" id="jtcy2_mzV"/>
								</td>
								<th id="f_jtcy2_zzmm">������ò</th>
								<td>
									<html:select property="jtcy2_zzmm" name="rs" disabled="true" styleId="jtcy2_zzmm">
										<html:option value=""></html:option>
										<html:options collection="zzmmList" property="zzmmdm" labelProperty="zzmmmc"/>
									</html:select>
									<input type="hidden"  value="${rs1.jtcy2_zzmm}" id="jtcy2_zzmmV"/>
								</td>
							</tr>						
							<tr>
								<th id="f_jtcy2_zy">ְҵ</th>
								<td>
									<html:text property="jtcy2_zy" name="rs" disabled="true" styleId="jtcy2_zy" maxlength="10"/>
									<input type="hidden"  value="${rs1.jtcy2_zy}" id="jtcy2_zyV"/>
								</td>
								<th id="f_jtcy2_zw">ְ��</th>
								<td>
									<html:text property="jtcy2_zw" name="rs" disabled="true" styleId="jtcy2_zw" maxlength="10"/>
									<input type="hidden"  value="${rs1.jtcy2_zw}" id="jtcy2_zwV"/>
								</td>
							</tr>							
							<tr>
								<th id="f_jtcy2_lxdh1">������λ�绰</th>
								<td>
									<html:text property="jtcy2_lxdh1" name="rs" disabled="true" styleId="jtcy2_lxdh1" maxlength="25"/>
									<input type="hidden"  value="${rs1.jtcy2_lxdh1}" id="jtcy2_lxdh1V"/>
								</td>
								<th id="f_jtcy2_lxdh2">���˵绰</th>
								<td>
									<html:text property="jtcy2_lxdh2" name="rs" disabled="true" styleId="jtcy2_lxdh2" maxlength="25"/>
									<input type="hidden"  value="${rs1.jtcy2_lxdh2}" id="jtcy2_lxdh2V"/>
								</td>
							</tr>
							<tr>
								<th id="f_jtcy2_gzdz">������ַ</th>
								<td colspan="3">
									<html:text property="jtcy2_gzdz" name="rs" disabled="true" styleId="jtcy2_gzdz" maxlength="25" style="width:90%"/>
									<input type="hidden"  value="${rs1.jtcy2_gzdz}" id="jtcy2_gzdzV"/>
								</td>			
							</tr>
							</tbody>
						</table>
					</td>
				</tr>
				</tbody>
				</table></td></tr>
				<tr><td colspan="4">
				<table class="formlist" width="100%">
				<thead>
					<tr>
						<td colspan="4" style="cursor:hand" align="center"
							onclick="document.getElementById('jt3').style.display=(document.getElementById('jt3').style.display==''?'none':'')">
								ѧ����ͥ��Ա��Ϣ3
						</td>
					</tr>
				</thead>

				<tbody>
				<tr id="jt3">
					<td colspan="4">
						<table class="formlist" width="100%">
						<tbody>				
						<tr>
							<th id="f_jtcy3_xm">����</th>
							<td>
								<html:text property="jtcy3_xm" name="rs" disabled="true" styleId="jtcy3_xm" maxlength="25"/>
								<input type="hidden"  value="${rs1.jtcy3_xm}" id="jtcy3_xmV"/>
							</td>
							<th id="f_jtcy3_gx">�뱾�˹�ϵ</th>
							<td>
								<html:select name="rs" property="jtcy3_gx" styleId="jtcy3_gx" disabled="true">
									<html:option value="" />
									<html:options collection="jtgxList" labelProperty="mc" property="mc"/>
								</html:select>
								<input type="hidden"  value="${rs1.jtcy3_gx}" id="jtcy3_gxV"/>
							</td>
						</tr>						
						<tr>
							<th id="f_jtcy3_nl">��������</th>
							<td>
								<html:text property="jtcy3_nl" name="rs" disabled="true" styleId="jtcy3_nl" readonly="" onclick="return showCalendar('jtcy3_nl','y-mm-dd');" styleId="jtcy3_nl"/>
								<input type="hidden"  value="${rs1.jtcy3_nl}" id="jtcy3_nlV"/>
							</td>
							<th id="f_jtcy3_sfzh">���֤��</th>
							<td>
								<html:text property="jtcy3_sfzh" name="rs" disabled="true" styleId="jtcy3_sfzh" maxlength="18" onblur="if(!checkSfzh(this)){this.focus();}"/>
								<input type="hidden"  value="${rs1.jtcy3_sfzh}" id="jtcy3_sfzhV"/>
							</td>
						</tr>						
						<tr>
							<th id="f_jtcy3_mz">����</th>
							<td>
								<html:select property="jtcy3_mz" name="rs" disabled="true" styleId="jtcy3_mz">
									<html:option value=""></html:option>
									<html:options collection="mzList" property="mzdm" labelProperty="mzmc"/>
								</html:select>
								<input type="hidden"  value="${rs1.jtcy3_mz}" id="jtcy3_mzV"/>
							</td>
							<th id="f_jtcy3_zzmm">������ò</th>
							<td>
								<html:select property="jtcy3_zzmm" name="rs" disabled="true" styleId="jtcy3_zzmm">
									<html:option value=""></html:option>
									<html:options collection="zzmmList" property="zzmmdm" labelProperty="zzmmmc"/>
								</html:select>
								<input type="hidden"  value="${rs1.jtcy3_zzmm}" id="jtcy3_zzmmV"/>
							</td>
						</tr>
						<tr>
							<th id="f_jtcy3_zy">ְҵ</th>
							<td>
								<html:text property="jtcy3_zy" name="rs" disabled="true" styleId="jtcy3_zy" maxlength="10"/>
								<input type="hidden"  value="${rs1.jtcy3_zy}" id="jtcy3_zyV"/>
							</td>
							<th id="f_jtcy3_zw">ְ��</th>
							<td>
								<html:text property="jtcy3_zw" name="rs" disabled="true" styleId="jtcy3_zw" maxlength="10"/>
								<input type="hidden"  value="${rs1.jtcy3_zw}" id="jtcy3_zwV"/>
							</td>			
						</tr>
						<tr>
							<th id="f_jtcy3_lxdh1">������λ�绰</th>
							<td>
								<html:text property="jtcy3_lxdh1" name="rs" disabled="true" styleId="jtcy3_lxdh1" maxlength="25"/>
								<input type="hidden"  value="${rs1.jtcy3_lxdh1}" id="jtcy3_lxdh1V"/>
							</td>
							<th id="f_jtcy3_lxdh2">���˵绰</th>
							<td>
								<html:text property="jtcy3_lxdh2" name="rs" disabled="true" styleId="jtcy3_lxdh2" maxlength="25"/>
								<input type="hidden"  value="${rs1.jtcy3_lxdh2}" id="jtcy3_lxdh2V"/>
							</td>
						</tr>
						<tr>
							<th id="f_jtcy3_gzdz">������ַ</th>
							<td colspan="3">
								<html:text property="jtcy3_gzdz" name="rs" disabled="true" styleId="jtcy3_gzdz" maxlength="25" style="width:90%"/>
								<input type="hidden"  value="${rs1.jtcy3_gzdz}" id="jtcy3_gzdzV"/>
							</td>			
						</tr>
						</tbody>
						</table></td></tr>
					  </tbody>
					</table>
					</td>
				</tr>
				</tbody>
				<logic:equal value="yes" name="writeAble">
				<tfoot>
				 <logic:notEmpty name="errMsg">
				  <tr>
			        <td colspan="4">
						<div class="bz">
							<span class="red">��ʾ��${errMsg}</span>
						</div>			          	
			        </td>
			      </tr>
				</logic:notEmpty>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������; ��ɫ�����־���ֶ��޸Ĺ�</div>
			          	<div class="btn">							
							<button type="button" id="saveButton"
								<logic:notEmpty name="errMsg">
									disabled="disabled" 
								</logic:notEmpty>
								onclick="saveXsxx()">
								�� ��
							</button>
						</div>
			        </td>
			      </tr>
			    </tfoot>
				</logic:equal>
			</table>
			</div>
			
			<logic:equal value="true" name="result">
				<script>
					alert("�����ɹ�!");				
				</script>
			</logic:equal>
			<logic:equal value="false" name="result">
				<script>
					alert("����ʧ��!");				
				</script>
			</logic:equal>
		</logic:equal>
		</html:form>
</body>
</html>
