<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini" %>
<script type="text/javascript">
	function dispType(tp) {
		var xxdm = document.getElementById('xxdm').value;
		var type = document.getElementById(tp).value;
		if (type == '' || type == null || type == ' ') {
			document.getElementById('xmdm').selectedIndex = 0;
			if ('12764'!=xxdm && '12789' != xxdm) {
				document.getElementById('zdcz').selectedIndex = 0;
				document.getElementById('zdcz').disabled = true;
			}
			document.getElementById('tjdm').selectedIndex = 0;
			document.getElementById('tjdm').disabled = true;
			document.getElementById('zdbj').selectedIndex = 0;
			document.getElementById('xmdm').disabled = true;
			document.getElementById('zdbj').disabled = true;
		} else {
			document.getElementById('xmdm').disabled = false;
			if ('12764'!=xxdm && '12789' != xxdm) {
				document.getElementById('zdcz').disabled = false;
			}
			document.getElementById('tjdm').disabled = false;
			document.getElementById('zdbj').disabled = false;
		}
	}
	function dispZdbj(){
		var type = document.getElementById('zdcz').value;
		if (type == 'pm') {
			document.getElementById('tmp').style.display="inline";
			
		} else {
			document.getElementById('tmp').style.display="none";
			
		}
		//ahjgPjpyTjsz.getZdczbjList(type,function (data){
			//if (data != null && typeof data == 'object') {
				//var objId = "zdbj";
				//if($(objId) && $(objId).tagName.toLowerCase() == "select"){
				//	DWRUtil.removeAllOptions(objId);			
				//	DWRUtil.addOptions(objId,data,"zjbjdm","zjbjmc");							
				//}
			//}else{
			//	showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
			//}
		//});
	}
	function tjsave(){
		var xxdm = document.getElementById('xxdm').value;
		var tjValue=$("tjdm").value;
		if ('12764'==xxdm) {
			if (document.getElementById('cslx').value==''
			|| document.getElementById('xmdm').value=='' || document.getElementById('tjdm').value==''
			|| document.getElementById('zdbj').value==''
			|| document.getElementById('zdval').value=='') {
				return true;
			} else {
				return false;
			}
		} else if ('12789'==xxdm) {
			if (document.getElementById('cslx').value==''
			|| document.getElementById('xmdm').value=='' || document.getElementById('tjdm').value==''
			|| document.getElementById('zdbj').value=='' || document.getElementById('zdval').value=='') {
				return true;
			} else {
				return false;
			}
		}else if ('10878'==xxdm) {
			if(tjValue == 'bjgkm'){
				if (document.getElementById('cslx').value==''|| document.getElementById('xmdm').value==''){
					return true;
				}else{
					return false;
				}
			}else{
				if (document.getElementById('cslx').value==''
				|| document.getElementById('xmdm').value=='' || document.getElementById('tjdm').value==''
				|| document.getElementById('zdcz').value=='' || document.getElementById('zdbj').value==''
				|| document.getElementById('zdval').value=='') {
					return true;
				} else {
					return false;
				}
			}
		}else {
			if (document.getElementById('cslx').value==''
			|| document.getElementById('xmdm').value=='' || document.getElementById('tjdm').value==''
			|| document.getElementById('zdcz').value=='' || document.getElementById('zdbj').value==''
			|| document.getElementById('zdval').value=='') {
				return true;
			} else {
				return false;
			}
		}
		
	}
	function dgDel() {
		if (curr_row==null || curr_row==''){
		 	alert('��ѡ��Ҫ�������У��������ɣ�');
		 	return;
		 } else {
		 	url = 'tjszdgDel.do?pkValue='+curr_row.cells[0].getElementsByTagName("input")[0].value; 
		 	if (confirm('ȷ��Ҫɾ����ѡ���������?')) {
		 		refreshForm(url);
		 		return;
		 	} else {
		 		return;
		 	}
		 }
	}
	function zhszcpcjpmZdTj(){
	    if(!$("tjdm")){
	      return false;
	    }
	    var tjValue = "";
	    var num = "1";	    
	    tjValue=$("tjdm").value;
	    num   = $("tjdm").length;
	    for(i=0;i<num;i++){		
	        document.forms[0].zdcz.options[i]=null;
	    }
		if (tjValue == 'zhszcpcjpm') {
             document.forms[0].zdcz.options[0] = new Option("����","pm");
		} else {
            document.forms[0].zdcz.options[0] = new Option("���ֵ","max");					
	    }
	   	var xxdm = $('xxdm')?document.getElementById('xxdm').value:"";
		if ('12764'!=xxdm) {
	        dispZdbj();		
	    }
	    if ('10878'==xxdm) {
			if(tjValue == 'bjgkm'){
				$("zdbj").disabled=true;
				$("zdbj").value="=";
				$("zdcz").disabled=true;
				$("zdval").style.display="none";
				$("zdval").value=$("yesorno").value;
				$("fontid").style.display="none";
				$("yesorno").style.display="";
			}else if(tjValue == 'wjcf'){
				$("zdbj").disabled=true;
				$("zdbj").value="=";
				$("zdcz").disabled=true;
				$("zdval").style.display="none";
				$("zdval").value=$("yesorno").value;
				$("fontid").style.display="none";
				$("yesorno").style.display="";
			}else{
				$("zdbj").disabled=false;
				$("zdcz").disabled=false;
				$("zdval").style.display="";
				$("fontid").style.display="";
				$("yesorno").style.display="none";
				$("zdval").value="";
			}		
	    }
	}	
	function chYesOrNo(value){
		$("zdval").value=value;
	}
</script>
<script type='text/javascript'
			src='/xgxt/dwr/interface/ahjgPjpyTjsz.js'></script>
<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<body onload="xyDisabled('xy');dispType('cslx');zhszcpcjpmZdTj()">

	<html:form action="/pjpyahjgwh" method="post">
		<div class="title">
       		<div class="title_img" id="title_m">
         		<bean:message bundle="pjpyahjg" key="pjpy_ahjg_tjsz" />
       		</div>
    	</div>
    	<fieldset>
				<legend>
					����ѡ��
				</legend>
				<input type="hidden" name="userType" id="userType" value="${userType }"/>
				<input type="hidden" name="xxdm" id="xxdm" value="${xxdm }"/>
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
							<td align="left" nowrap="nowrap">
								�������ͣ�
									<html:select property="cslx" styleId="cslx" styleClass="select"
									onchange="refreshForm('pjpy_anjg_tjsz.do');dispType('cslx');" style="width:120px">
										<html:option value=""></html:option>
										<logic:equal value="12764" name="xxdm">
										<html:option value="jxj">��ѧ��</html:option>
										</logic:equal>
										<logic:notEqual value="12764" name="xxdm">
										<html:option value="jxj">��ѧ��</html:option>
										<html:option value="rych">�����ƺ�</html:option>
										</logic:notEqual>
									</html:select>
								&nbsp;&nbsp;
								����������Ŀ��								
									<html:select property="xmdm" styleId="xmdm" styleClass="select" style="width:200px"
									onchange="refreshForm('pjpy_anjg_tjsz.do');">
										<html:option value=""></html:option>
										<logic:equal value="1" name="jxj">
											<html:options collection="jxjList" property="xmdm" labelProperty="xmmc"/>
										</logic:equal>
										<logic:equal value="2" name="rych">
											<html:options collection="rychList" property="xmdm" labelProperty="xmmc"/>
										</logic:equal>
									</html:select>
							</td>
						</tr>
						<tr>
							<!-- �㽭����ְҵ begin -->
							<logic:equal value="12789" name="xxdm">
								<td align="left" nowrap="nowrap">
								������
									<html:select property="tjdm" styleId="tjdm" styleClass="select" style="width:150px" onchange="refreshForm('pjpy_anjg_tjsz.do');">
										<html:option value=""></html:option>
										<html:options collection="tjList" property="tjdm" labelProperty="tjmc"/>
									</html:select>
								--&gt;
									<html:select property="zdbj" styleId="zdbj" styleClass="select" style="width:90px">
										<html:options collection="zdbjList" property="zjbjdm" labelProperty="zjbjmc"/>
									</html:select>
								--&gt;
									<html:text property="zdval" styleId="zdval" styleClass="inputtext"
									onblur="ckinpdata(this)" style="width:90px"></html:text>
									<logic:equal value="pm" name="fsfs">
									<font color="red">(%,������0~100֮�������)</font>
									</logic:equal>
									<logic:notEqual value="pm" name="fsfs">
									<font color="red">(��,������0~100֮�������)</font>										
									</logic:notEqual>

								&nbsp;
								<button type="button" class="button2" id="btn_add"
									onclick="if (tjsave()) {alert('�����������Ϊ�գ�');return;} else refreshForm('ahjg_jxjpdtjadd.do?zdmc='+document.forms[0].tjdm.options[document.forms[0].tjdm.selectedIndex].text+'&czmc='+document.forms[0].zdbj.options[document.forms[0].zdbj.selectedIndex].text);document.getElementById('btn_add').disabled=true">
									�������
								</button>
							</td>
							</logic:equal>
							<!-- �㽭����ְҵ end -->
							<!-- ����ѧУ begin -->
							<logic:notEqual value="12789" name="xxdm">
								<td align="left" nowrap="nowrap">
								������
									<html:select property="tjdm" styleId="tjdm" styleClass="select" style="width:150px" onchange="zhszcpcjpmZdTj()"> 
										<html:option value=""></html:option>
										<html:options collection="tjList" property="tjdm" labelProperty="tjmc"/>
									</html:select>
								--&gt;
								<logic:notEqual value="12764" name="xxdm">
									<html:select property="zdcz" styleId="zdcz" styleClass="select"
									style="width:90px">										
										<html:options collection="zdczList" property="zdczdm" labelProperty="zdczmc"/>
									</html:select>
								--&gt;
								</logic:notEqual>
									<html:select property="zdbj" styleId="zdbj" styleClass="select" style="width:90px">
										
										<html:options collection="zdbjList" property="zjbjdm" labelProperty="zjbjmc"/>
									</html:select>
								--&gt;
									<html:text property="zdval" styleId="zdval" styleClass="inputtext"
									onblur="ckinpdata(this)" style="width:90px"></html:text>
									<font id="fontid" color="red"><div id="tmp" style="display: none;">%</div>(��,������0~100֮�������)</font>
									<logic:equal name="xxdm" value="10878">
									<select id="yesorno" style="display: none;" onchange="chYesOrNo(this.value)">
										<option value="no">��</option>
									</select>
									</logic:equal>
								&nbsp;
								<button type="button" class="button2" id="btn_add"
									onclick="if (tjsave()) {alert('�����������Ϊ�գ�');return;} else refreshForm('ahjg_jxjpdtjadd.do?zdmc='+document.forms[0].tjdm.options[document.forms[0].tjdm.selectedIndex].text+'&czmc='+document.forms[0].zdbj.options[document.forms[0].zdbj.selectedIndex].text);document.getElementById('btn_add').disabled=true">
									�������
								</button>
							</td>
							</logic:notEqual>
							<!-- ����ѧУ end -->
						</tr>
					</thead>
					</table>
		</fieldset>
		<br/>
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
								<font color="blue">��ʾ��˫��һ�п��Բ鿴��ϸ��Ϣ;������ͷ���Խ�������;</font>
							</legend>
							<table width="99%" id="rsTable" class="tbstyle">
								<thead>
									<tr align="center" style="cursor:hand">
										<logic:iterate id="tit" name="topTr" offset="1" scope="request">
											<td id="${tit.en}"
												onclick="tableSort(this)" nowrap>
												${tit.cn}
											</td>
										</logic:iterate>
									</tr>
								</thead>
								<logic:iterate name="rs" id="s">
									<tr onclick="rowOnClick(this)"
										style="cursor:hand;">
										<td align="center"><input type="hidden" value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>"/>
									    	<logic:iterate id="v" name="s" offset="1" length="1">
												<bean:write name="v" />
										</logic:iterate>
									    </td>
										<logic:iterate id="v" name="s" offset="2">
											<td align=center>
												<bean:write name="v" />
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
							</table>
						</fieldset>
					</logic:notEmpty>
					<logic:equal value="yes" name="writeAble" scope="request">
	                	<div class="buttontool" align="center" id="btn" style="position:absolute;width:95%;top:100px">
							<button type="button" class="button2" id="btn_add" style="width:80px"
								onclick="dgDel();">
								ɾ��
							</button>
							&nbsp;&nbsp;
							<button type="button" class="button2" id="btn_modi" style="width:80px"
							onclick="if (confirm('ȷ��Ҫɾ�����е�����������?�ò���������ת��')) {refreshForm('tjszplDel.do');return;} else return;" >
								�������
							</button>
						</div>
					</logic:equal>
					<div id="tmpdiv"></div>
				</div>
			</div>
	</html:form>
	<script type="text/javascript" src="js/bottomButton.js"></script>
	 <logic:present name="inserted">
	 	<logic:equal value="yes" name="inserted">
	 		<script>
	 			alert('�����ɹ���');
	 		</script>
	 	</logic:equal>
	 	<logic:equal value="no" name="inserted">
	 		<script>
	 			alert('����ʧ�ܣ�');
	 		</script>
	 	</logic:equal>
	 </logic:present> 
</body>