<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<script language="javascript" src="/xgxt/pjpy/xibeierminyuan/js/xbemyJs.js"></script>
<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<script type="text/javascript" src="pjpy/hbsf/hbsfjs.js"></script>
<script type="text/javascript" src="pjpy/pjpyjs/pjpy.js"></script>
<script type='text/javascript' src='/xgxt/dwr/interface/gzdxpjpy.js'></script>

<script type="text/javascript">
<!--
	function mdsb() {
		if (checkNotNull('dm-xy')) {
			var jyrs = 0;
			var sbrs = 0;
			var xn = document.getElementById('xn').value;
			var xydm = document.getElementById('xy').value;
			var lb = document.getElementById('lb').value;
			var dm = document.getElementById('dm').value;
			dwr.engine.setAsync(false);
				gzdxpjpy.getXyxzrs(xn, xydm, lb, dm, function(data){
					jyrs = data[0];
					sbrs = data[1]
				});
			dwr.engine.setAsync(true); 
		
			var cbv = document.getElementsByName("cbv");
			var cjtj = 0;
			var wjtj = 0;
			
			var checkLen = 0;
	
			//��ȡѡ�еļ�¼����
			if (cbv != null) {
				for (var i=0;i<cbv.length;i++) {
					if (cbv[i].checked) {
						checkLen++;
					}
				}
			}
			
			if (checkLen<=0) {
				alert("��ѡ��Ҫ�걨������!,�������׵ĸ�ѡ�򼴿�.");
				return false;
			}
			//��ȡδѡ�У������ͨ���ļ�¼����
			var trobj = document.getElementById("tobj");
			for (var j=0;j<trobj.rows.length;j++) {
				var chk = trobj.rows[j].cells[0].getElementsByTagName("input")[0].checked;
				//var xxsh = replaceBlankSpace(trobj.rows[j].cells[13].innerText);
				//alert(xxsh);
				var cj = replaceBlankSpace(trobj.rows[j].cells[9].innerText);
				var wj = replaceBlankSpace(trobj.rows[j].cells[11].innerText);
				//if (!chk && "ͨ��" == xxsh) {
				//	checkLen++;
				//}
				//�걨�������Ƿ��гɼ��ҿƺʹ��ֵ�����
				if (chk) {
					if (cj == '��') {
						cjtj++;
					}
					if (wj == '��') {
						wjtj++;
					}
				}
			}
			if (jyrs == 0) {
				alert("����Ա��δ���ñ�<bean:message key="lable.xsgzyxpzxy" />�ý�ѧ��Ļ�����,���ܽ����걨!");
				return false;
			}
			if (jyrs != 0 && (parseInt(checkLen) + parseInt(sbrs)) > parseInt(jyrs)) {
				alert("��ǰ�걨�Ļ������ѳ����õĻ�����,\n�����걨������" + jyrs + "�ˣ���ǰ�걨������" + checkLen + "�ˣ����걨������" + sbrs + "��.");
				return false;
			} else if (cjtj > 0) {
				alert("��ǰ�걨�Ļ��������в���ѧ���ɼ������񣬲������걨����!");
				return false;
			} else if (wjtj > 0) {
				alert("��ǰ�걨�Ļ��������в���ѧ���ܹ������Ҵ��ֵȼ��ѳ���ѧУ���õĴ������Ƽ��𣬲������걨����!");
				return false;				
			}
			if (confirm("ȷ��Ҫ�걨��ѡ���������")) {
					refreshForm("pjpy_gzdx_sbhjmd.do");
			} 
			
		} else {
			alert("��ѡ��Ҫ�걨��<bean:message key="lable.xsgzyxpzxy" />�ͽ���!");
			return false;
		}
	}
	function viewOne() {
		var lb = document.getElementById('lb').value;
		//var cj = curr_row.cells[9].innerText;
		//var wj = curr_row.cells[11].innerText;
		modiAndDel('pjpy_gzdx_viewHjsbxx.do?lb=' + lb +'&pkValue=','modi',650,500)
	}
	
	function searchBd() {
	
		if($('dm').value == ''){
			alert('��ȷ���걨���');
			return false;
		}
		if($('bjdm').value == ''){
			alert('��Ҫȷ����༶��������ѡ��༶��');
			return false;
		}
		refreshForm('pjpy_gzdx_xysbHjdata.do?operType=query')
	}
//-->
</script>
<body onload="xyDisabled('xy');">
	<html:form action="/gzdxPjpy.do" method="post">
		<input type="hidden" name="zyV" id="zyV" value="" />
		<input type="hidden" name="bjV" id="bjV" value="" />
	
		<input type="hidden" name="pt" id="pt"/>
		<input type="hidden" name="userType" id="userType"
			value="${userType }" />
		<div class="title">
			<div class="title_img" id="title_m">
				��ǰλ��: �������� - ����Ϣά�� - �걨������
			</div>
		</div>
		<fieldset>
			<legend>
				����ѡ��
			</legend>
			<table width="100%" class="tbstyle">
				<thead>
					<tr>
						<td align="" nowrap="nowrap">
							ѧ�꣺
							<html:select property="xn" styleId="xn" style="width:90px"
								styleClass="select" disabled="true">
								<html:options collection="xnList" property="xn"
									labelProperty="xn" />
							</html:select>
							&nbsp;&nbsp;
							ѧ�ţ�
							<html:text property="xh" styleId="xh" styleClass="inputtext"
								style="width:100px">
							</html:text>
							&nbsp;&nbsp;�걨�������
							<html:select property="lb" styleId="lb" style="width:100px" onchange="refreshForm('pjpy_gzdx_xysbHjdata.do')">
								<html:option value="jxj">��ѧ��</html:option>
								<html:option value="rych">�����ƺ�</html:option>
							</html:select>
							&nbsp;&nbsp;�걨���
							<html:select property="dm" styleId="dm" >
								<html:option value=""></html:option>
								<html:options collection="dmList" property="dm" labelProperty="mc"/>
							</html:select>
						</td>
						<td width="10" rowspan="2" align="center" valign="middle">
							<button class="button2" style="height:40px" id="search_go"
								onclick="searchBd()">
								��ѯ
							</button>
						</td>
					</tr>
					<tr>
						<td align="left" nowrap>
							�꼶��
							<html:select property="nj" styleId="nj" style="width:80px"
								styleClass="select" onchange="initZyList();initBjList()">
								<html:option value=""></html:option>
								<html:options collection="njList" property="nj"
									labelProperty="nj" />
							</html:select>
							&nbsp;&nbsp;
							<bean:message key="lable.xsgzyxpzxy" />��
							<html:select property="xydm" style="width:175px"
								styleClass="select" styleId="xy"
								onchange="initZyList();initBjList()">
								<html:option value=""></html:option>
								<html:options collection="xyList" property="xydm"
									labelProperty="xymc" />
							</html:select>
							&nbsp;&nbsp; רҵ��
							<html:select property="zydm" style="width:170px" styleId="zy"
								styleClass="select" onchange="initBjList()">
								<html:option value=""></html:option>
								<html:options collection="zyList" property="zydm"
									labelProperty="zymc" />
							</html:select>
							&nbsp;&nbsp; �༶��
							<logic:notEmpty name="bjList">
							<html:select property="bjdm" style="width:180px" styleId="bj"
								styleClass="select">
								<html:option value=""></html:option>
								<html:options collection="bjList" property="bjdm"
									labelProperty="bjmc" />
							</html:select>
							</logic:notEmpty>
						</td>
					</tr>
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
						${rsNum}
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
						<tbody id="tobj">
						<logic:iterate name="rs" id="s">
							<tr onclick="rowOnClick(this)" style="cursor:hand;" 
								align="center" ondblclick="viewOne()">
								<td>
									<input type="checkbox" id="cbv" name="cbv"
										value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>"/>								
								</td>
								<logic:iterate id="v" name="s" offset="3">
									<td>
										<bean:write name="v" />
									</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
						</tbody>
					</table>
					<TABLE width="99%" id="rsTable1" class="tbstyle">
											<TR>
												<TD height=3></TD>
											</TR>
											<TR>
												<TD>
													<jsp:include flush="true"
														page="/sjcz/turnpage.jsp?form=pjpyGzdxActionForm"></jsp:include>
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
							<button class="button2" id="btn_add"
								onclick="mdsb()"
								style="width:80px">
								�����걨
							</button>
							&nbsp;&nbsp;
							<!-- 
							<button class="button2" id="btn_del"
								onclick="deldata('pjpy_gzdx_deleteHjxx.do')"
								style="width:80px">
								ȡ���걨
							</button>
							 -->
						</div>
					</center>
				</logic:equal>
			<div id="tmpdiv"></div>
	</html:form>
		<logic:equal value="yes" name="writeAble" scope="request">
		<script language="javascript">
			document.getElementById("btn").style.pixelTop = document.body.clientHeight - 35;
			document.getElementById("btn").style.width = "96%";
			window.setInterval("initBTNTool('btn')",1);
		</script>
		</logic:equal>
		<logic:present name="result">
			<logic:equal value="true" name="result">
				<script>
					alert('�����ɹ�!');
					document.getElementById('search_go').onclick();
				</script>
			</logic:equal>
			<logic:equal value="false" name="result">
				<script>
					alert('����ʧ��!');
					document.getElementById('search_go').onclick();
				</script>
			</logic:equal>
		</logic:present>
</body>
