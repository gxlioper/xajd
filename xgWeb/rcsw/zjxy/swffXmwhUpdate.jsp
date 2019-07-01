<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type='text/javascript' src='/xgxt/dwr/interface/getRcswDAO.js'></script>
		<script type="text/javascript" src="js/rcsw/rcswFunction.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">	
	
	//������Ŀά��
	function saveXmwh(){
		if($("rqnum")){
			var rqnum = $("rqnum").value;
			var flag = false;
			
			if( rqnum != ""){
				for(var i=0;i<rqnum;i++){
					var id = "ffrq"+ i;
					if($(id)){
						if($(id).checked==true){
							flag = true;
						}
					}
				}
			}
		}
		if(flag){
			saveUpdate('/xgxt/zjxyRcsw.do?method=swffXmwhUpdate&doType=save','xn-xq-nd-xmlx-ffsj');
		}else{
			alert("������ѡ��һ��������Ⱥ����ȷ��!!")
			return false;
		}
	}
	
	//������Ŀά�� ֮��ѧԺ
	function saveXm(){
		if($("xmmc").value==""){
			alert("��Ŀ���Ʋ���Ϊ��!");
			return false;
		}
		if($("xn").value==""){
			alert("ѧ�겻��Ϊ��!");
			return false;
		}
		if($("xmlx").value==""){
			alert("��Ŀ���Ͳ���Ϊ��!");
			return false;
		}
		if($("xq").value==""){
			alert("ѧ�ڲ���Ϊ��!");
			return false;
		}
		if($("ffsj").value==""){
			alert("���ſ�ʼʱ�䲻��Ϊ��!");
			return false;
		}
		if($("nd").value==""){
			alert("��Ȳ���Ϊ��!");
			return false;
		}
		if($("ffjssj").value==""){
			alert("���Ž���ʱ�䲻��Ϊ��!");
			return false;
		}
		if($("ffdd").value==""){
			alert("���ŵص㲻��Ϊ��!");
			return false;
		}
		if(checkSjTj("ffsj","����ʼʱ��","ffjssj","�������ʱ��")){
			saveUpdate('/xgxt/zjxyRcsw.do?method=swffXmwhUpdate&doType=save','xn-xq-nd-xmlx-ffsj');
		}else {
			return false;
		}
		
	}
	
	//������Ŀά�� ֮��ѧԺ
	function updateXm(){
		if($("xmmc").value==""){
			alert("��Ŀ���Ʋ���Ϊ��!");
			return false;
		}
		if($("xn").value==""){
			alert("ѧ�겻��Ϊ��!");
			return false;
		}
		if($("xmlx").value==""){
			alert("��Ŀ���Ͳ���Ϊ��!");
			return false;
		}
		if($("xq").value==""){
			alert("ѧ�ڲ���Ϊ��!");
			return false;
		}
		if($("ffsj").value==""){
			alert("���ſ�ʼʱ�䲻��Ϊ��!");
			return false;
		}
		if($("nd").value==""){
			alert("��Ȳ���Ϊ��!");
			return false;
		}
		if($("ffjssj").value==""){
			alert("���Ž���ʱ�䲻��Ϊ��!");
			return false;
		}
		if($("ffdd").value==""){
			alert("���ŵص㲻��Ϊ��!");
			return false;
		}
		if(checkSjTj("ffsj","����ʼʱ��","ffjssj","�������ʱ��")){
			saveUpdate('/xgxt/zjxyRcsw.do?method=swffXmwhUpdate&doType=modi','xn-xq-nd-xmlx-ffsj');
		}else {
			return false;
		}
		
	}
	
	//���÷�����Ⱥ
	function setFfrq(){
		var yffrq = $("yffrq").value;
		var rqnum = $("rqnum").value;
		if(yffrq != ""){
			var ffrq = yffrq.split("!!@@!!");
			for(var i=0;i<rqnum;i++){
				var id = "ffrq"+ i;
				if($(id)){
					for(var j =0;j<ffrq.length;j++){
						if($(id).value == ffrq[j]){
							$(id).checked = true;
						}
					}
				}
			}
		}
	}
	
	function sendXx(pk){
		
		var url = "/xgxt/zjxyRcsw.do?method=swffXmffManage&doType=view&goType=xmwh";
		url+="&cs=ff";
		url+="&xmPkValue="+$("xmPkValue").value;
		url+="&pkValue="+pk;
		refreshForm(url);
		hiddenNr();
	}
	
	function loadJzxx(){
		//���ݼ�����ʾ��Ϣ
		$("p_tsxx").innerHTML="<B>���ݼ�����,���Ժ�...</B>";
		$("div_tsxx").style.display="none";
	}
	
	//��һ������
	function nextCz(){
		var next = $("next_cz").value;
		if(next == "gb"){//�ر�
			hiddenMessage(true,true);
			dialogArgumentsQueryChick();
			Close();
		}else if(next == "sd"){//�ֶ�����
			sendXx($("pkValues").value);
		}
	}
	</script>
	</head>

	<body onload="setFfrq();loadJzxx()">
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>${title }</a>
			</p>
		</div>


		<html:form action="/zjxyRcsw">
			<%@ include file="/rcsw/hiddenValue.jsp"%>
			<input type="hidden" name="pkValues" id="pkValues" value="${rs.pk}"/>
			<input type="hidden" name="xmPkValue" id="xmPkValue" value="${xmPkValue}"/>
			<input type="hidden" name="rqnum" id="rqnum" value="${rqnum }" />
			<input type="hidden" name="yffrq" id="yffrq" value="${rs.ffrq }" />
			<!--  ��ʱд��,ֻ����ѧ�� -->
			<html:hidden property="save_ffrq" styleId="ffrq" value="�༶" />
			
			<div class="tab" id="div_main_box">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>���������Ŀά��</span>
							</th>
						</tr>
					</thead>
					
					<tbody>
						<tr>
							<th>
								<font color="red">*</font>��Ŀ����
							</th>
							<td colspan="3">
								<logic:equal name="doType" value="add">
									<html:text   property="save_xmmc" styleId="xmmc" style="width:90%" maxlength="30"></html:text>
								</logic:equal>
								<logic:notEqual name="doType" value="add">
									<html:text property="save_xmmc" styleId="xmmc" style="width:90%" maxlength="30" value="${rs.xmmc }" ></html:text>
								</logic:notEqual>
							</td>
						</tr>
						<tr>	
							<th>
								<font color="red">*</font>ѧ��
							</th>
							<td width="34%">
								<logic:equal name="doType" value="add">
									<html:select property="save_xn" style="" styleId="xn"
										onchange="">
										<html:options collection="xnList" property="xn"
											labelProperty="xn" />
									</html:select>
								</logic:equal>
								<logic:notEqual name="doType" value="add">
									<html:hidden  property="save_xn" styleId="xn" value="${rs.xn }"/>
									<html:select name="rs" property="xn" style="" styleId="xn"
										disabled="true">
										<html:options collection="xnList" property="xn"
											labelProperty="xn" />
									</html:select>
								</logic:notEqual>
							</td>
							<th width="16%">
								<font color="red">*</font>��Ŀ����
							</th>
							<td width="34%">
								<logic:equal name="doType" value="add">
									<html:select name="rs" property="save_xmlx" style="" styleId="xmlx">
										<html:options collection="xmlxList" property="dm"
											labelProperty="mc" />
									</html:select>
								</logic:equal>
								<logic:notEqual name="doType" value="add">
									<html:hidden  property="save_xmlx" styleId="xmlx" value="${rs.xmlx }"/>
									<html:select name="rs" property="xmlx" style="" styleId="xmlx"
										disabled="true">
										<html:options collection="xmlxList" property="dm"
											labelProperty="mc" />
									</html:select>
								</logic:notEqual>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>ѧ��
							</th>
							<td>
								<logic:equal name="doType" value="add">
									<html:select  property="save_xq" style="" styleId="xq"
										onchange="">
										<html:option value=""></html:option>
										<html:options collection="xqList" property="xqdm"
											labelProperty="xqmc" />
									</html:select>
								</logic:equal>
								<logic:notEqual name="doType" value="add">
									<html:hidden  property="save_xq" styleId="xq" value="${rs.xq }"/>
									<html:select name="rs" property="xq" style="" styleId="xq"
										disabled="true">
										<html:option value=""></html:option>
										<html:options collection="xqList" property="xqdm"
											labelProperty="xqmc" />
									</html:select>
								</logic:notEqual>
							</td>
							<th>
								<font color="red">*</font>����ʼʱ��
							</th>
							<td>
								<logic:equal name="doType" value="add">
									<html:text property="save_ffsj" styleId="ffsj" 
										onclick="return showCalendar('ffsj','y-mm-dd');" 
										onblur="dateFormatChg(this)" readonly="true" />
								</logic:equal>
								<logic:notEqual name="doType" value="add">
									<html:text name="rs" property="ffsj" disabled="true" />
									<html:hidden  property="save_ffsj" styleId="ffsj" value="${rs.ffsj }"/>
								</logic:notEqual>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>���
							</th>
							<td>
								<logic:equal name="doType" value="add">
									<html:select  property="save_nd" style="" styleId="nd">
										<html:options collection="ndList" property="nd"
											labelProperty="nd" />
									</html:select>
								</logic:equal>
								<logic:notEqual name="doType" value="add">
									<html:hidden  property="save_nd" styleId="nd" value="${rs.nd }"/>
									<html:select name="rs" property="nd" style="" styleId="nd"
										disabled="true">
										<html:options collection="ndList" property="nd"
											labelProperty="nd" />
									</html:select>
								</logic:notEqual>
							</td>
							<th >
								<font color="red">*</font>�������ʱ��
							</th>
							<td colspan="3">
								<logic:equal name="doType" value="add">
									 <html:text property="save_ffjssj" styleId="ffjssj" 
										onclick="return showCalendar('ffjssj','y-mm-dd');" 
										onblur="dateFormatChg(this)" readonly="true" />
								</logic:equal>
								<logic:notEqual name="doType" value="add">
									<html:text name="rs" property="ffjssj" disabled="true" />
									<html:hidden  property="save_ffjssj" styleId="ffjssj" value="${rs.ffjssj }"/>
								</logic:notEqual>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>���ŵص�
							</th>
							<td colspan="3">
								<logic:equal name="doType" value="add">
									<html:text name="rs" property="save_ffdd" styleId="ffdd" style="width:350px" maxlength="50"></html:text>
								</logic:equal>
								<logic:notEqual name="doType" value="add">
									<html:text   property="save_ffdd" styleId="ffdd" style="width:350px" maxlength="50" value="${rs.ffdd}"></html:text>
								</logic:notEqual>
							</td>
						</tr>
						<logic:equal name="doType" value="update">
						<tr>
							<th>
								������Աȷ��
							</th>
							<td colspan="3">
								<a href="#" onclick="sendXx('${rs.pk}');return false;">
										<font color="blue"><U>������Ա</U></font>
								</a>
							</td>
						</tr>
						</logic:equal>
						<logic:equal name="doType" value="view">
						<tr>
							<th>
								������Աȷ��
							</th>
							<td colspan="3">
								<a href="#" onclick="sendXx('${rs.pk}');return false;">
										<font color="blue"><U>������Ա</U></font>
								</a>
							</td>
						</tr>
						</logic:equal>
						<logic:notEqual name="xxdm" value="13275">
						<tr>
							<th>
								<font color="red">*</font>������Ⱥ
							</th>
							<td colspan="3">
								<logic:empty name="ffrqList">
							δ������Ⱥ
							</logic:empty>
									<logic:notEmpty name="ffrqList">
										<logic:iterate name="ffrqList" id="rq" indexId="index">
											<logic:notEqual name="index" value="0">
												<input type="checkbox" id="ffrq${index }" name="ffrq"
													value="${rq.dm }" />${rq.mc }
										&nbsp;&nbsp;&nbsp;&nbsp;
										<%
										if (index % 4 == 0) {
										%>
												<br />
												<%
												}
												%>
											</logic:notEqual>
										</logic:iterate>
									</logic:notEmpty>
							</td>
						</tr>
						</logic:notEqual>
						<tr>
							<th>
								��ע
								<br />
								<font color="red">(��500��)</font>
							</th>
							<td colspan="3">
								<html:textarea property="save_bz" style="word-break:break-all;width:98%"
									rows="5" onblur="chLeng(this,500)" value="${rs.bz}"/>
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<logic:notEqual name="doType" value="view">
										<logic:equal name="xxdm" value="13275">
											<logic:equal name="doType" value="update">
												<button type="button" id="buttonSave" onclick="updateXm()"
													style="width: 80px">
												�� ��
												</button>
											</logic:equal>
											<logic:equal name="doType" value="add">
												<button type="button" id="buttonSave" onclick="saveXm()"
													style="width: 80px">
													�� ��
												</button>
											</logic:equal>
										</logic:equal>
										<logic:notEqual name="xxdm" value="13275">
										<button type="button" id="buttonSave" onclick="saveXmwh()"
											style="width: 80px">
											�� ��
										</button>
										</logic:notEqual>
									</logic:notEqual>
									<button type="button" id="buttonClose" onclick="Close();dialogArgumentsQueryChick();" style="width: 80px">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
			<logic:equal name="doType" value="save">
			<logic:equal name="result" value="true">
			<logic:present name="message">
				<!-- ���������ʾ�� -->
				<div id="tsxxDiv" style="display: none">
					<div class="tab">
						<table class="formlist">
							<thead>
								<tr>
									<th>
										<span>
											���������Ŀ���ӳɹ�,��ѡ����һ��������
										</span>
									</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>
										<input type="hidden"  name="next" id="next_cz" value="gb"/>
										<input type="radio" name="next" id="next_sd" value="sd" onclick="$('next_cz').value = this.value"/>
										ǰ��"���������Աȷ��"ģ��
										</br>
										<input type="radio" name="next" id="next_gb" value="gb" checked="true" onclick="$('next_cz').value = this.value"/>
										�رձ�����
									</td>
								</tr>
							</tbody>
							<tfoot>
							<tr>
								<td>
									<div class="btn">
										<!-- ȷ�� -->
										<button type="button" onclick="nextCz()">
											<bean:message key="lable.btn_qd_space" />
										</button>
									</div>
								</td>
							</tr>
							</tfoot>
						</table>
					</div>
				</div>
				<script defer="defer">
					function cz(){
						viewTempDiv("��ѡ����һ������","tsxxDiv",350,200);
						
					}
					setTimeout("cz()",100);
				</script>
			</logic:present>
			</logic:equal>
			</logic:equal>
			<logic:notEqual name="doType" value="save">
				<logic:present name="message">
					<script defer="defer">
						alert('${message}'+"!");
						dialogArgumentsQueryChick();
						Close();
					</script>
				</logic:present>
			</logic:notEqual>
			<%@ include file="/comm/other/loading.jsp"%>
			
		</html:form>
	</body>
</html>
