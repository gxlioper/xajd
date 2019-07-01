<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- ͷ�ļ� -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript" src="js/jsFunction.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/interface/cqkjFunc.js"></script>
	<script language="javascript" src="js/xgutil.js"></script>
	<script language="javascript" src="js/qgzxFunction.js"></script>
	<script language="javascript">
	function expStationDis(){
		document.forms[0].target = "_blank";
		refreshForm('xsqgzx.do?method=expStation');
		document.forms[0].target = "_self";
	}
	function add(){
		var xxdm = document.getElementById("xxdm").value;
		if(xxdm == "10395"){//����ѧԺ
			refreshForm('qgzxgwfb.do');
			return true;
		}else{
			refreshForm('comm_pub.do?doType=add&tableName='+document.forms[0].tableName.value,800,600);
		}
	}
	function modi(){
		var xxdm = document.getElementById("xxdm").value;
		if(curr_row != null){
			var pkValue = curr_row.getElementsByTagName('input')[0].value;
			if(xxdm=="10497"){//�人����ѧ
				if(curr_row.cells[10].innerText.trim()=="ͨ��"){
					alert("����Ѿ�ͨ�����Ѳ����ٽ����޸ģ�");			
					return false;
				}
			}
			if(xxdm=="13275"){//�㽭��ҵ��ѧ֮��ѧԺ
				if(curr_row.cells[12].innerText.trim()=="ͨ��"){
					alert("����Ѿ�ͨ�����Ѳ����ٽ����޸ģ�");			
					return false;
				}
			}
			if(xxdm == "10395"){//����ѧԺ
				refreshForm('gwfb.do?method=gwfbModi&pkValue=' + pkValue);
				return true;
			}else{
				refreshForm('/xgxt/comm_pub.do?doType=modi&pkValue=' + pkValue);
				return true;
			}
		}else{
			alert('��ѡ��Ҫ�޸ĵ��У�');
			return false;
		}
	}
	
	function applyGw(url){
		if(curr_row == null ){
				alert("��ѡҪ����ĸ�λ��\n����һ�м�¼����");
				return false;
			} 
		var pkValue = (curr_row.cells[0].getElementsByTagName("input"))[0].value;		 
		//�жϸ�λʱ���Ƿ����
		cqkjFunc.checkGwsj(pkValue,function(data){
			if(data == true){
				var xxdm = document.getElementById("xxdm").value;
				//�㽭��ҵ��ѧ֮��ѧԺ
				if(xxdm == "13275"){
					cqkjFunc.checkGwsqsj(pkValue,function(data){
						if(data == true){
							//�㽭��ҵ��ѧ֮��ѧԺ��ѧ����λ������ת���µ�ҳ�洦����ʹ��ͨ��url
							url = "qgzx_gwsqwh.do?method=xssqUpdate";
							url += "&gwdmsbsj="+pkValue;
							refreshForm(url);
						}else{
							alert("�ø�λĿǰ���������룬���ע�ø�λ���뿪ʼʱ�������ʱ�䣡");
						}
					});
				}else{
					refreshForm(url+pkValue,600,480);
				}
			}else{
				alert("�ø�λ��δ���ͨ������ʱ���ѹ��ڣ����ڲ������룡");
				return false;
			}
		});	

	}
	
	//��ʾ��λ��ϸ��Ϣ
	function showDetails(){
		var xxdm = val('xxdm');
		var pkValue = curr_row.cells[1].getElementsByTagName('input')[0].value;
		if(xxdm == '10395'){//����ѧԺ
			url = 'gwfb.do?method=gwfbDetails&pkValue=';
			url += pkValue;
		}else{
			url = 'qgzxLogic.do?method=queryGwxxxx&pk=';
			url += pkValue;
		}
		showTopWin(url, 650, 550);
	}

	//��ʾ���������ϸ��Ϣ
	function showRsxxDetail(lx){
		var pkValue = curr_row.cells[1].getElementsByTagName('input')[0].value;
		url = "qgzxLogic.do?method=showRsxxDetail&pk=";
		url += pkValue;
		url += "&lx=";
		url += lx;
		showTopWin(url,650,550);
	}
	
	function print1(){
		var pkValue = '';
		if (curr_row != null) {
			pkValue = curr_row.cells[0].getElementsByTagName("input")[0].value;
		}
		window.open('gzdx_scgwxxbPrint.do?pkValue=' + pkValue);
	} 
	
	function print(){
		var nd = val('nd');
		window.open('qgzxGwgl.do?method=printGwhzb&nd='+nd);	
	}
	
	function change(id,disId,loadFlag){	
		var xxdm = val('xxdm');
		if(xxdm == "11078"){//���ݴ�ѧ
			if(id== ""){
				id = "gwfbr";
				disId = "qtfbr";
			}	
			
			ele("field"+id).style.display = "";
			ele("field"+disId).style.display = "none";
			ele("field"+id).disabled = false;
			ele("field"+disId).disabled = true;
			
			setVal('divId',id);
			setVal('disDivId',disId);
			
			ele(id + "l").className = "xxk_on_l";
			ele(id + "m").className = "xxk_on_m";
			ele(id + "r").className = "xxk_on_r";
			
			ele(disId + "l").className = "xxk_off_l";
			ele(disId + "m").className = "xxk_off_m";
			ele(disId + "r").className = "xxk_off_r";
			if(id == "qtfbr"){
				ele('gwfbr').disabled=true;
			}else{
				ele('gwfbr').disabled=false;
			}
			if(!loadFlag){
				refreshForm('data_search2.do?act=work');
			}					
		}
	}
	
	
	function printQggwInfo(){
		$("zzxn").value=$("qsxn").value;
		document.forms[0].target = "_blank";
		refreshForm('qgzxGxls.do?method=printQggwInfo');
		document.forms[0].target = "_self";
		closeWindown();
	}
	
	function showXnChecked(){
			tipsWindown("ϵͳ��ʾ","id:div_xn","350","200","true","","true","id");
		}
		
	</script>
</head>
<body onload="change(val('divId'),val('disDivId'),true);">
	<html:form action="/data_search2" method="post">
		<input type="hidden" id="tableName" name="tableName" value="<bean:write name="tableName" scope="request"/>" />
		<input type="hidden" id="act" name="act" value="<bean:write name="act" scope="request"/>" />
		<input type="hidden" id="realTable" name="realTable" value="<bean:write name="realTable" scope="request"/>" />
		<input type="hidden" id="pk" name="pk" value="<bean:write name="pk" scope="request"/>" />
		<input type="hidden" id="xxdm" name="xxdm" value="<bean:write name="xxdm"/>" />
		<input type="hidden" id="userName" name="userName" value="${userName}" />
		<input type="hidden" id="divId" name="divId" value="${divId}" />
		<input type="hidden" id="disDivId" name="disDivId" value="${disDivId}" />
		<input type="hidden" id="shFlag" name="shFlag" value="false" />
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>${title }</a>
			</p>
		</div>
		
		<!--���ݴ�ѧ-->
		<logic:equal value="11078" name="xxdm">
		<html:hidden property="gwfbr" value="${userName}"></html:hidden>
		<div class="xxk" id="xxkDiv" style="width:97%">
			<ul id="gwfbrul">
				<li id="gwfbrl" class="xxk_on_l"></li>
				<li id="gwfbrm" onclick="change('gwfbr','qtfbr',false);" class="xxk_on_m">
					&nbsp;
					���˷�����λ
					&nbsp;
				</li>
				<li id="gwfbrr"class="xxk_on_r"></li>
			</ul>
			<ul id="qtfbrul">
				<li id="qtfbrl" class="xxk_off_l"></li>
				<li id="qtfbrm" onclick="change('qtfbr','gwfbr',false);" class="xxk_off_m">
					&nbsp;
					�����û�������λ
					&nbsp;
				</li>
				<li id="qtfbrr"class="xxk_off_r"></li>
			</ul>
		</div>		
		<fieldset id="fieldqtfbr" style="display:none">
			<legend>
				��ѯ����
			</legend>
			<table width="100%" class="tbstyle">
				<thead>
					<tr>
						<td align="left">
							��ȣ�
							<html:select property="nd" style="width:90px" onchange="loadFgwfbrGwmcxx('gwdm','xn','nd','xq')">
								<html:options collection="xnList" property="nd"
									labelProperty="nd" />
							</html:select>
							&nbsp;&nbsp;ѧ�꣺
							<html:select property="xn" style="width:120px" onchange="loadFgwfbrGwmcxx('gwdm','xn','nd','xq')">
								<html:options collection="xnList" property="xn"
									labelProperty="xn" />
							</html:select>
							&nbsp;&nbsp;ѧ�ڣ�
							<html:select property="xq" style="width:90px" onchange="loadFgwfbrGwmcxx('gwdm','xn','nd','xq')">
								<html:option value=""></html:option>
								<html:options collection="xqList" property="xqdm"
									labelProperty="xqmc" />
							</html:select>
							&nbsp;&nbsp;���˵�λ��
							<html:select property="yrdwdm" style="width:90px" onchange="loadFgwfbrGwmcxx('gwdm','xn','nd','xq')">
								<html:option value=""></html:option>
								<html:options collection="yrdwList" property="yrdwdm"
									labelProperty="yrdwmc" />
							</html:select>
							
						</td>
						<td width="10" align="center" valign="middle" rowspan="2">
							<input type="hidden" name="go" value="go" />
							<button type="button" class="button2" id="search_go" style="height:40px"
								onclick="allNotEmpThenGo('/xgxt/data_search2.do?act='+document.getElementById('act').value)">
								�� ѯ
							</button>
						</td>
					</tr>
					<logic:equal value="view_gwxx" name="tableName">
						<tr>
							<td>
								<logic:equal value="view_gwxx" name="tableName">
								��λ���ƣ�
								<html:select property="gwdm">
										<html:option value=""></html:option>
										<html:options collection="gwList" property="gwmc"
											labelProperty="gwmc" />
									</html:select>
								</logic:equal>	
							</td>
						</tr>
					</logic:equal>
				</thead>
			</table>
		</fieldset>

		<fieldset id="fieldgwfbr" style="">
			<legend>
				��ѯ����
			</legend>
			<table width="100%" class="tbstyle">
				<thead>
					<tr>
						<td align="left">
							��ȣ�
							<html:select property="nd" style="width:90px" styleId="nd">
								<html:options collection="xnList" property="nd"
									labelProperty="nd" />
							</html:select>
							&nbsp;&nbsp;ѧ�꣺
							<html:select property="xn" style="width:120px" styleId="xn">
								<html:options collection="xnList" property="xn"
									labelProperty="xn" />
							</html:select>
							&nbsp;&nbsp;ѧ�ڣ�
							<html:select property="xq" style="width:90px" styleId="xq">
								<html:option value=""></html:option>
								<html:options collection="xqList" property="xqdm"
									labelProperty="xqmc" />
							</html:select>								
						</td>
						<td width="10" align="center" valign="middle" rowspan="2">
							<input type="hidden" name="go" value="go" />
							<button type="button" class="button2" id="search_go" style="height:40px"
								onclick="allNotEmpThenGo('/xgxt/data_search2.do?act='+document.getElementById('act').value)">
								�� ѯ
							</button>
						</td>
					</tr>
					<logic:equal value="view_gwxx" name="tableName">
						<tr>
							<td>
								<logic:equal value="view_gwxx" name="tableName">
								��λ���ƣ�
								<html:select property="gwdm">
										<html:option value=""></html:option>
										<html:options collection="brfbgwList" property="gwmc"
											labelProperty="gwmc" />
									</html:select>
								</logic:equal>									
							</td>
						</tr>
					</logic:equal>
				</thead>
			</table>
		</fieldset>	
		</logic:equal>
		<!--end���ݴ�ѧ-->

		<!--�ǹ��ݴ�ѧ-->
		<logic:notEqual value="11078" name="xxdm">
			<div class="toolbox">
			  <!-- ��ť -->
			  <div class="buttonbox">
			    <ul>
					<logic:equal value="yes" name="writeAble">					
					<!-- ���ݴ�ѧ -->
					<logic:notEqual value="11078" name="xxdm">
						<li> <a href="#" onclick="applyGw('/xgxt/post_stu_apply.do?gwValue=')" class="btn_sq">��λ����</a> </li>
					</logic:notEqual>	
					<logic:notEqual value="stu" name="userType">
					<logic:notEmpty name="gwcxview">
						<li> <a href="#" onclick="viewMore('modi','yes')" class="btn_xg">�޸�</a> </li>
						<li> <a href="#" onclick="dataDel('qgzxLogic.do?method=delPost')" class="btn_sc">ɾ ��</a> </li>
					</logic:notEmpty>	
					<logic:empty name="gwcxview">	
						<li> <a href="#" onclick="add()" class="btn_zj">�� ��</a> </li>				
						<li> <a href="#" onclick="modi()" class="btn_xg">�� ��</a> </li>
						<li> <a href="#" onclick="dataDel('qgzxLogic.do?method=delPost')" class="btn_sc">ɾ ��</a> </li>
						<li> <a href="#" onclick="impAndChkData();" class="btn_dr">��������</a> </li>					
						<li> <a href="#" onclick="showExportDIV('/xgxt/expData.do');" class="btn_dc">��������</a> </li>
						<%--���Ϲ�ҵ��ѧ--%>
						<logic:equal value="11535" name="xxdm">
							<logic:equal value="view_gwxx" name="tableName">								
								<%--�ڹ���ѧ--%>
								<li> <a href="#" onclick="expStationDis()" class="btn_ck">��λ�ֲ�</a> </li>
							</logic:equal>
						</logic:equal>
						<%--end���Ϲ�ҵ��ѧ--%>
						<!--���ݴ�ѧ-->
						<logic:equal value="11078" name="xxdm">
							<li> <a href="#" onclick="print1()" class="btn_dy">��λ������ӡ</a> </li>
						</logic:equal>
						<!--end���ݴ�ѧ-->					
						<!--������һְҵ����ѧԺ-->
						<logic:equal value="13742" name="xxdm">
							<li> <a href="#" onclick="print()" class="btn_dy">��λ���ܱ��ӡ</a> </li>						
						</logic:equal>
						<!--end������һְҵ����ѧԺ-->
						<!--�㽭�Ƽ�ѧԺ-->
						<logic:equal value="11057" name="xxdm">
							<li> <a href="#" onclick="printYrqkb()" class="btn_dy">����������ӡ</a> </li>
						</logic:equal>
						
						<li> <a href="#" onclick="showXnChecked()" class="btn_dy">����������ӡ</a> </li>
						<!--end�㽭�Ƽ�ѧԺ-->	
					</logic:empty>
					</logic:notEqual>
					</logic:equal>	
			    </ul>
			  </div>
			  <!--��ѯ����-->
			  <div class="searchtab">
			    <table width="100%" border="0">
			      <tfoot>
			        <tr>
			          <td colspan="6">
			            <div class="btn">
			              <input type="hidden" name="go" value="a" />
						  <button type="button" class="btn_cx" id="search_go"
								onclick="allNotEmpThenGo('/xgxt/data_search2.do?act='+document.getElementById('act').value)">
								��ѯ
						  </button>
						  <button type="button" id="cz"
							onclick="czSearchCond('nd-xn-xq-yrdwdm-gwxzdm-jcfs-xxyj-gwdm-gwflag-sfgq');return false;">
							����
					    	</button>
			            </div>
			          </td>
			        </tr>
			      </tfoot>
				  <tbody>
			      	<tr>
			      		<th>���</th>
						<td>
							<html:select property="nd" style="width:180px" styleId="nd" onchange="loadGwmcxx('gwdm','xn','nd','xq')">
								<html:options collection="xnList" property="nd"
									labelProperty="nd" />
							</html:select>
						</td>
						<th>ѧ��</th>
						<td>
							<html:select property="xn" style="width:180px" styleId="xn" onchange="loadGwmcxx('gwdm','xn','nd','xq')">
								<html:options collection="xnList" property="xn"
									labelProperty="xn" />
							</html:select>
						</td>
						<th>ѧ��</th>
						<td>
							<html:select property="xq" style="width:180px" styleId="xq" onchange="loadGwmcxx('gwdm','xn','nd','xq')">
								<html:option value=""></html:option>
								<html:options collection="xqList" property="xqdm"
									labelProperty="xqmc" />
							</html:select>	
						</td>
					</tr>
					<tr>
						<th>���˵�λ</th>
						<td>
							<html:select property="yrdwdm" style="width:180px" styleId="yrdwdm">
									<html:option value=""></html:option>
									<html:options collection="yrdwList" property="yrdwdm"
										labelProperty="yrdwmc" />
							</html:select>
						</td>
			      		<th>��λ����</th>
						<td>
							<html:select property="gwxz" style="width:180px" styleId="gwxzdm" onchange="loadGwmcxx('gwdm','xn','nd','xq')">
								<html:option value=""></html:option>
								<html:options collection="gwxzList" property="gwxzdm"
									labelProperty="gwxzmc" />
							</html:select>
						</td>
						<th>�Ƴ귽ʽ</th>
						<td>
							<html:select property="jcfs" style="width:180px" styleId="jcfs">
								<html:option value=""></html:option>
								<html:options collection="jcfsList" property="dm"
									labelProperty="mc" />
							</html:select>
						</td>						
					</tr>
					<tr>
						<th>��˽��</th>
						<td>
							<html:select property="xxyj" style="width:180px" styleId="xxyj">
									<html:option value=""></html:option>
									<html:options collection="chkList" property="en" labelProperty="cn" />
							</html:select>
						</td>	
						<th>��λ����</th>
						<td>
							<html:text property="gwdm" style="width:180px" styleId="gwdm"></html:text>
						</td>
						<logic:notEqual name="userType" value="stu">
							<th>�Ƿ���Ч</th>
							<td>
									<html:radio property="sfgq" value="yx" styleId="sfgq">��Ч</html:radio>	&nbsp;  
								    <html:radio property="sfgq" value="gq" styleId="sfgq">��Ч</html:radio>&nbsp;
								    <html:radio property="sfgq" value="" styleId="sfgq">ȫ��</html:radio>
							</td>			      							
						 </logic:notEqual>
						 <logic:equal name="userType" value="stu">
						    	<input type="hidden" name="sfgq" value="yx"/>
					    	<th>�Ƿ���и�λ</th>
							<td>
								<html:radio property="sfkx" value="kx" styleId="sfkx">����</html:radio>	&nbsp;  
							    <html:radio property="sfkx" value="bkx" styleId="sfbkx">�ǿ���</html:radio>&nbsp;
							    <html:radio property="sfkx" value="qb" styleId="">ȫ��</html:radio>&nbsp;
							</td>		
					    </logic:equal>
					</tr>
					<!--����ѧԺ-->
					<logic:equal value="10395" name="xxdm">
					<tr>
			      		<th>��λ���</th>
						<td>
							<html:select property="gwflag" style="width:180px" styleId="gwflag">
								<html:option value=""></html:option>
								<html:option value="xngw">У�ڸ�λ</html:option>
								<html:option value="xwgw">У���λ</html:option>
							</html:select>
						</td>
						<th></th>
						<td>
							
						</td>
						<th></th>
						<td>
							
						</td>
					</tr>
					</logic:equal>
				</tbody>
			</table>
			</div>		
			</div>
		</logic:notEqual>
		<!--end�ǹ��ݴ�ѧ-->
		<div class="formbox">
			    <h3 class="datetitle_01">
			    <span>
			    	��ѯ���&nbsp;&nbsp;
			    	<logic:empty name="rs">
						<font color="red">δ�ҵ��κμ�¼��</font> 
			 		 </logic:empty>
					<logic:notEmpty name="rs">
						<font color="blue">��ʾ��˫��һ�п��Բ鿴��ϸ��Ϣ��������ͷ��������</font>
					</logic:notEmpty>
			    </span>
			    </h3>
				   
			  <logic:notEmpty name="rs">
			  <div class="con_overlfow">
			  <table class="dateline tablenowrap" width="100%" id="rsTable">
			    <thead>
			      <tr>
					<td>
						<input type="checkbox" name="all" value="all" onclick="chec()"/>
					</td>
					<logic:equal name="xxdm" value="11078">
						<logic:iterate id="tit" name="topTr" offset="2">
							<td id="<bean:write name="tit" property="en"/>"
								onclick="tableSort(this)">
								<bean:write name="tit" property="cn" />
							</td>
						</logic:iterate>
					</logic:equal>
					<logic:notEqual name="xxdm" value="11078">
						<logic:iterate id="tit" name="topTr" offset="1">
							<td id="<bean:write name="tit" property="en"/>"
								onclick="tableSort(this)">
								<bean:write name="tit" property="cn" />
							</td>
						</logic:iterate>
					</logic:notEqual>
			      </tr>
			    </thead>
			    <tbody>
					<logic:iterate name="rs" id="s">
						<!--�人��-->
						<logic:equal value="10497" name="xxdm">
							<tr onclick="rowMoreClick(this,'',event);" ondblclick="viewMore('modi','yes')" style="cursor:hand">
						</logic:equal>
						<!--end�人��-->

						<!--���Ϲ�ҵ-->
						<logic:equal value="11535" name="xxdm">
							<tr onclick="rowMoreClick(this,'',event);" ondblclick="viewMore('modi','yes')" style="cursor:hand">
						</logic:equal>
						<!--end���Ϲ�ҵ-->
						
						<!--����-->
						<logic:notEqual value="10497" name="xxdm">
						<logic:notEqual value="11535" name="xxdm">
							<tr onclick="rowMoreClick(this,'',event);" align="center" ondblclick="showDetails()" style="cursor:hand">
						</logic:notEqual>
						</logic:notEqual>
						<!--end����-->
							<td>
								<input type="checkbox" name="pkV" <logic:equal name="xxdm" value="11078">
								<logic:iterate id="v" name="s" offset="1" length="1"><bean:write name="v"/></logic:iterate>
								</logic:equal>
								value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>"/>
							</td>
							<td>
								<logic:iterate id="v" name="s" offset="0" length="1">
									<input type="hidden" value="<bean:write name="v"/>"/>
								</logic:iterate>
								<!-- ���ݴ�ѧ�ڶ�����DISABLED -->
								<logic:equal name="xxdm" value="11078">
									<logic:iterate id="v" name="s" offset="2" length="1">
										<bean:write name="v" />
									</logic:iterate>
								</logic:equal>
								<!-- �ǹ��ݴ�ѧ -->
								<logic:notEqual name="xxdm" value="11078">
									<logic:iterate id="v" name="s" offset="1" length="1">
										<bean:write name="v" />
									</logic:iterate>
								</logic:notEqual>
							</td>
							<!-- ���ݴ�ѧ�ڶ�����DISABLED -->
							<logic:equal name="xxdm" value="11078">
							<logic:iterate id="v" name="s" offset="3">
								<td>
									<bean:write name="v" />
								</td>
							</logic:iterate>
							</logic:equal>
							<logic:notEqual name="xxdm" value="11078">
								<!-- �㽭��ҵ��ѧ֮��ѧԺ -->
								<logic:equal value="13275" name="xxdm">
									<logic:iterate id="v" name="s" offset="2" length="6">
										<td>
											<bean:write name="v" />
										</td>
									</logic:iterate>
									<logic:iterate id="v" name="s" offset="8" length="1">
										<td>
											<logic:equal value="0" name="v">
												<bean:write name="v" />
											</logic:equal>
											<logic:notEqual value="0" name="v">
												<a href="javascript:showRsxxDetail('sqrs');" style="color: blue;">
													<bean:write name="v" />
												</a>
											</logic:notEqual>
										</td>
									</logic:iterate>
									<logic:iterate id="v" name="s" offset="9" length="1">
										<td>
											<logic:equal value="0" name="v">
												<bean:write name="v" />
											</logic:equal>
											<logic:notEqual value="0" name="v">
												<a href="javascript:showRsxxDetail('lyrs');" style="color: blue;">
													<bean:write name="v" />
												</a>
											</logic:notEqual>
										</td>
									</logic:iterate>
									<logic:iterate id="v" name="s" offset="10">
										<td>
											<bean:write name="v" />
										</td>
									</logic:iterate>
								</logic:equal>
								<!-- ͨ��ѧУ -->
								<logic:notEqual value="13275" name="xxdm">
									<logic:iterate id="v" name="s" offset="2">
										<td>
											<bean:write name="v" />
										</td>
									</logic:iterate>
								</logic:notEqual>
							</logic:notEqual>
						</tr>
					</logic:iterate>
			    </tbody>
			</table>
			</div>
			<!--��ҳ��ʾ-->
			   <jsp:include flush="true" page="/sjcz/turnpage.jsp?form=commanForm"></jsp:include>
			<!--��ҳ��ʾ-->			
			</logic:notEmpty>
			</div>	
			
			
		<!-- ��λͳ����ʼ������ѧ��ѡ�� -->
		<div id="div_xn" style="display:none">
				<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>�ּܷ���</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th>
									ѧ��
								</th>
								<td>
									<html:select property="qsxn" styleId="qsxn" style="width:150px"
										>
										<html:options collection="xnList" property="xn"
											labelProperty="xn" />
									</html:select>
									<html:hidden property="zzxn" styleId="zzxn"/>
								</td>
							</tr>
							
						</tbody>
						<tfoot>
							<tr>
								<td colspan="2">
									<div class="bz">
										"
										<span class="red">*</span>"Ϊ������
									</div>
									<div class="btn">
										<button type="button" name="�� ��" onclick="printQggwInfo()">
											ȷ ��
										</button>
										<button type="button" name="ȡ ��" onclick="closeWindown();return false;">
											ȡ ��
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
			<logic:present name="result">
				<logic:equal value="true" name="result">
				<script>
					alert('�����ɹ���');
				</script>			
				</logic:equal>
				<logic:notEqual value="true" name="result">
				<script>
					alert('����ʧ�ܣ�');
				</script>
				</logic:notEqual>
			</logic:present>
	</html:form>
</body>
</html>
