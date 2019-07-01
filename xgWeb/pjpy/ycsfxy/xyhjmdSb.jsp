<%@ page language="java" contentType="text/html; charset=GBK"%>

<%@ include file="/syscommon/pagehead.ini"%>
<body onload="xyDisabled('xy');">
	<script language="javascript"
		src="/xgxt/pjpy/xibeierminyuan/js/xbemyJs.js"></script>
	<script type="text/javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getPjpyDao.js'></script>
	<script type="text/javascript">
	//��֤���ݸ�ʽ�Ƿ�������
	function ckinpdata(obj){
         if(obj.value==""){		
		   return false;
		}
		obj.value = obj.value.replace(/[^(\d|\.)]/g,'');
		var inputStr = obj.value;		

		if(!(inputStr.match(/\d+/g) || inputStr.match(/\d+\.?\d{0,1}/g)) || inputStr > 100){
			obj.value = '';
			return false;
		}
		return true;
	}
	
	function chkIsInteger(obj){
		obj.value = obj.value.replace(/[^(\d|.]/g,'');
		return true;
	}
	
	function deleteinfo() {
		var xm = document.getElementById('lb').value;
		if (xm==null || xm=='') {
			alert("����ѡ��Ҫ�����Ľ������!");
			return false;
		}
		deldata('pjpy_ycsf_hjmdxxDelete.do?type=' + xm);
	}
	
	function update(url,act) {
		var xm = document.getElementById('lb').value;
		if (xm==null || xm=='') {
			alert("����ѡ��Ҫ�����Ľ������!");
			return false;
		}
		if (curr_row==null || curr_row=='') {
			alert('��ѡ��Ҫ�����������У�');
			return;
		}
		var pk = curr_row.cells[0].getElementsByTagName("input")[0].value;
		if (act=='view') {
			var xn = document.getElementById('xn').value;
			var xq = document.getElementById('xq').value;
			var xh = curr_row.cells[2].innerText;
			xh = xh.replace(/(^\s*)|(\s*$)/g, "");
			showTopWin(url + pk + '&lb=' + xm + '&xn=' + xn + '&xq=' + xq + '&xh=' + xh,700,550); 
		} else {
			//�ȼ�������Ƿ����
			getPjpyDao.checkDataExists(pk,xm,function (data) {
				if (data==false) {
					alert("��������δ�ϱ�,���ܽ����޸�!");
					return;
				} else {
					showTopWin(url + pk + '&lb=' + xm,700,550); 
				}
			});
		}
	}
	
	function jxjsb() {
		var checkBoxArr = document.getElementsByName("cbv");
		var flag = false;
		for(var i=0;i<checkBoxArr.length;i++){
			if(checkBoxArr[i].checked==true){
				flag = true;
			}
		}
		if (flag) {
			var array = document.getElementById('rsTable').getElementsByTagName('tr');
			var xhList = "";
			for (var i=1;i<array.length;i++) {
				var cbv = array[i].getElementsByTagName("input")[0];
				if (cbv.checked==true) {
					var xh = array[i].cells[2].innerText;
					xh = xh.replace(/(^\s*)|(\s*$)/g, "");
					xhList += xh
					xhList += "!@";
				}
			}
			var lb = document.getElementById('lb').value;
			showTopWin('pjpy_ycsf_hjmdsb.do?xhList=' + xhList + '&lb=' + lb,600,400);
		} else {
			alert("û��ѡ����Ӧ��¼����ѡ��֮���ٽ��в�������");
			return;
		}
	}
	function query(){
		var xn = document.getElementById('xn').value;
		var xq = document.getElementById('xq').value;
		if(xn == ''){
			alert('ѧ�겻��Ϊ�գ�');
			return false;
		}
		if(xq == ''){
			alert('ѧ�ڲ���Ϊ�գ�');
			return false;
		}
		refreshForm('pjpy_ycsf_xyhjmdsb.do?act=query');
		document.getElementById('search_go').disabled=true;
	}
	</script>
	<html:form action="/pjpyycsfwh.do" method="post">
		<div class="title">
			<div class="title_img" id="title_m">
				��ǰλ�ã��������� - ��� - <bean:message key="lable.xsgzyxpzxy" />�������ϱ�
			</div>
		</div>
		<input type="hidden" id="userType" name="userType"
			value="${userType }" />
		<input type="hidden" name="realTable" id="realTable"
			value="${realTable}" />
		<input type="hidden" name="tableName" id="tableName"
			value="${tableName}" />
		<input type="hidden" name="userName" id="userName" value="${userName}" />
		<input type="hidden" name="isFdy" id="isFdy" value="${fdyQx}" />
		<!-- ����������ʾ��Ϣ -->
		<input type="hidden" name="pt" id="pt"/>
		<input type="hidden" name="xyV" value="" />
		<input type="hidden" name="zyV" value="" />
		<input type="hidden" name="bjV" value="" />
		<fieldset>
			<legend>
				��ѯ����
			</legend>
			<table width="100%" class="tbstyle">
				<thead>
					<tr>
						<td align="left">
							ѧ�꣺
							<html:select property="xn" style="width:100px" styleId="xn"
								styleClass="select">
								<html:options collection="xnList" property="xn"
									labelProperty="xn" />
							</html:select>
							&nbsp;&nbsp; ѧ�ڣ�
							<html:select property="xq" styleId="xq" styleClass="xq"
								style="width:60px">
								<html:option value=""></html:option>
								<html:options collection="xqList" property="xqdm"
									labelProperty="xqmc" />
							</html:select>
							&nbsp;&nbsp; �꼶��
							<html:select property="nj" styleId="nj" style="width:80px">
								<html:option value=""></html:option>
								<html:options collection="njList" property="nj"
									labelProperty="nj" />
							</html:select>
							&nbsp;&nbsp; ѧ�ţ�
							<html:text property="xh" styleId="xh" style="width:110px"></html:text>
							&nbsp;&nbsp; ������
							<html:text property="xm" styleId="xm" style="width:110px"></html:text>
						</td>
						<td width="10" rowspan="4" align="center" valign="middle">
							<button class="button2" style="height:40px" id="search_go"
								onclick="query()">
								��ѯ
							</button>
						</td>
					</tr>
					<tr>
						<td align="left" nowrap>
							<bean:message key="lable.xsgzyxpzxy" />��
							<html:select property="xydm" style="width:175px"
								styleClass="select" styleId="xy"
								onchange="initZyList();initBjList()">
								<html:option value=""></html:option>
								<html:options collection="xyList" property="xydm"
									labelProperty="xymc" />
							</html:select>
							&nbsp;&nbsp; רҵ��
							<html:select property="zydm" style="width:175px" styleId="zy"
								styleClass="select" onchange="initBjList()">
								<html:option value=""></html:option>
								<html:options collection="zyList" property="zydm"
									labelProperty="zymc" />
							</html:select>
							&nbsp;&nbsp;�༶��
							<html:select property="bjdm" style="175px" styleId="bj"
								styleClass="select">
								<html:option value=""></html:option>
								<html:options collection="bjList" property="bjdm"
									labelProperty="bjmc" />
							</html:select>
						</td>
					</tr>
					<tr>
						<td align="left" nowrap> 
							�������
							<html:select property="lb" styleId="lb" onchange="refreshForm('pjpy_ycsf_xyhjmdsb.do')">
								<html:option value="jxj">��ѧ��</html:option>
								<html:option value="rych">�����ƺ�</html:option>
							</html:select>
							&nbsp;&nbsp; 
							���
								<logic:equal value="jxj" name="lb">
									<html:select property="dm" styleId="dm" style="width:160px">
										<html:option value=""></html:option>
										<html:options collection="jxjList" property="jxjdm" labelProperty="jxjmc"/>
									</html:select>
								</logic:equal>
								<logic:equal value="rych" name="lb">
									<html:select property="dm" styleId="dm" style="width:160px">>
										<html:option value=""></html:option>
										<html:options collection="rychList" property="rychdm" labelProperty="rychmc"/>
									</html:select>
								</logic:equal>
							&nbsp;&nbsp;
							<logic:notEqual value="yes" name="ahzyjsxy">
							�۲��ְܷ༶����ǰ(����)��
							<html:text property="pm" styleId="pm" style="width:80px" maxlength="5" onkeyup="chkIsInteger(this)"></html:text>%
							</logic:notEqual>
							
						</td>
					</tr>
					<logic:notEqual value="yes" name="ahzyjsxy">
					<tr>
						<td align="left" nowrap> 
							ѧҵ�ְܷ༶����ǰ(����)��
							<html:text property="xyzfpm" styleId="xyzfpm" style="width:40px" maxlength="5" onkeyup="chkIsInteger(this)"></html:text>%
							&nbsp;
							���ޡ�ѡ�޵��Ƴɼ������ڣ�
							<html:text property="xycj" styleId="xycj" style="width:40px" maxlength="5" onkeyup="chkIsInteger(this)"></html:text>
							&nbsp;
							Уѡ�޲����ڣ�
							<html:text property="xxxcj" styleId="xxxcj" style="width:40px" maxlength="5" onkeyup="chkIsInteger(this)"></html:text>
							&nbsp;
							�׶ο��˳ɼ������ڣ�
							<html:text property="jdcj" styleId="jdcj" style="width:40px" maxlength="5" onkeyup="chkIsInteger(this)"></html:text>
						</td>
					</tr>
					</logic:notEqual>
				</thead>
			</table>
		</fieldset>
		<logic:empty name="rs">
			<br />
			<br />
			<p align="center">
				δ�ҵ��κμ�¼��
			</p>
		</logic:empty>
		<logic:notEmpty name="rs">
			<fieldset>
				<legend>
					��¼����
					<bean:write name="rsNum" />
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<font color="blue">��ʾ��������ͷ���Խ�������˫��һ�п��Բ鿴��ϸ��Ϣ��</font>
				</legend>
				<table width="100%" id="rsTable" class="tbstyle">
					<thead>
						<tr align="center" style="cursor:hand">
							<td nowrap>
								<input type="checkbox" id="cb" name="cb" onclick="selectAll()" />
							</td>
							<logic:iterate id="tit" name="topTr" offset="3">
								<td id="<bean:write name="tit" property="en"/>"
									onclick="tableSort(this)" nowrap>
									<bean:write name="tit" property="cn" />
								</td>
							</logic:iterate>
						</tr>
					</thead>
					<logic:iterate name="rs" id="s">
						<tr onclick="rowOnClick(this)" style="cursor:hand;" align="center"
							bgcolor="<logic:iterate id="v" name="s" offset="1" length="1"><bean:write name="v"/></logic:iterate>"
							ondblclick="update('pjpy_ycsf_hjmdxxView.do?pk=','view')" >
							<td>
								<!-- ���� -->
								<input type="checkbox" id="cbv" name="cbv"
									value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>" 
									<logic:iterate id="v" name="s" offset="2" length="1"><bean:write name="v"/></logic:iterate>/>
							</td>
							<logic:iterate id="v" name="s" offset="3">
								<td>
									<bean:write name="v" />
								</td>
							</logic:iterate>
						</tr>
					</logic:iterate>
				</table>
				<!-- ��ҳ���� -->
				<TABLE width="99%" id="rsTable1" class="tbstyle">
					<TR>
						<TD height=3></TD>
					</TR>
					<TR>
						<TD>
							<jsp:include flush="true"
								page="/sjcz/turnpage.jsp?form=pjpyYcsfxyActionForm"></jsp:include>
						</TD>
					</TR>
					<TR>
						<TD height=3></TD>
					</TR>
				</TABLE>
			</fieldset>
		</logic:notEmpty>
		<div id="toolTipLayer" style="position:absolute; visibility: hidden" /></div>
		<logic:equal value="yes" name="writeAble" scope="request">
			<center>
				<div class="buttontool" id="btn"
					style="position: absolute;left:1%;top:100px" width="100%">
					<button class="button2" id="btn_sb"
						onclick="jxjsb()"
						style="width:80px">
						�����ϱ�
					</button>
					&nbsp;&nbsp;&nbsp;
					<button class="button2" id="btn_add"
						onclick="showTopWin('pjpy_ycsf_addHjmdxx.do', 700,550)"
						style="width:80px">
						����
					</button>
					&nbsp;&nbsp;&nbsp;
					<button class="button2" id="btn_modi"
						onclick="update('pjpy_ycsf_hjmdxxModi.do?pk=')"
						style="width:80px">
						�޸�
					</button>
					&nbsp;&nbsp;&nbsp;
					<button class="button2" id="btn_add"
						onclick="deleteinfo()"
						style="width:80px">
						ɾ��
					</button>
					&nbsp;&nbsp;&nbsp;
					<button class="button2" id="btn_impdata" onclick="impAndChkData()"
						style="width:80px">
						��������
					</button>
					&nbsp;&nbsp;&nbsp;
					<button class="button2" id="btn_expdata" onclick="dataExport()"
						style="width:80px">
						��������
					</button>
				</div>
			</center>
		</logic:equal>
		<div id="tmpdiv"></div>
	</html:form>
	<script language="javascript">
			document.getElementById("btn").style.pixelTop = document.body.clientHeight - 35;
			document.getElementById("btn").style.width = "96%";
			window.setInterval("initBTNTool('btn')",1);
		</script>
	<!-- ������ʾ -->
	<logic:present name="result">
		<logic:equal value="true" name="result">
			<script>
				alert('�����ɹ�!');
			</script>
		</logic:equal>
		<logic:equal value="false" name="result">
			<script>
				alert('����ʧ��!');
			</script>
		</logic:equal>
	</logic:present>
</body>
