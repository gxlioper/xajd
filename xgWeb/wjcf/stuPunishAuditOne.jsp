<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<script type="text/javascript" src="/xgxt/dwr/interface/chgJxjlist.js"></script>
		<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
		<script type="text/javascript"
			src="/xgxt/dwr/interface/commWjcfDAO.js"></script>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript">
	function ckinpdata(obj){
		obj.value = obj.value.replace(/[^(\d|\.)]/g,'');
		var inputStr = obj.value;
		
		if(!(inputStr.match(/\d+/g) || inputStr.match(/\d+\.?\d{0,1}/g)) || inputStr > 100){
			alert('���ݸ�ʽ����ȷ���۷��ֶ�ֻ������λ���֣�');
			obj.value = '';
			return false;
		}
		return true;
	}
	function getRqVal(name)
			{
			var rq=document.getElementById(name).value;
			if (rq!="")
			{
				rqs=rq.split("-");
				rq="";
				for (var i=0;i<rqs.length;i++)
				{
					rq+=rqs[i];
				}
				document.getElementById(name).value=rq;
			}
			}
	function chknotnull1(){
		var xxdm = '';
		var userType = '';
		if ($('xxdm')) {
			xxdm = document.getElementById('xxdm').value;
		}
		if ($('userType')) {
			userType = document.getElementById('userType').value;
		}
		
		if ('11049'==xxdm) {
			if ('xy'==userType) {
				if (document.getElementById('cflb').value==null || document.getElementById('cflb').value==''
				||document.getElementById('cfyy').value==null|| document.getElementById('cfyy').value=='' ) {
					alert('��*������Ϊ�����');
					return false;
				}
			} else {
				if (document.getElementById('cflb').value==null || document.getElementById('cflb').value==''
				||document.getElementById('cfyy').value==null|| document.getElementById('cfyy').value=='') {
					alert('��*������Ϊ�����');
					return false;
				}
			}
		} else {
			if (document.getElementById('cflb').value==null || document.getElementById('cflb').value==''
				||document.getElementById('cfyy').value==null|| document.getElementById('cfyy').value=='' 
				) {
					alert('��*������Ϊ�����');
					return false;
				}
		}
		return true;
	}
	function chkcfwh() {
		var xh = document.getElementById('xh').value;
		var wh="";
		var sj="";
		if ($('cfwh')) {
			wh = document.getElementById('cfwh').value;
		}
		if ($('cfsj')) {
			sj = document.getElementById('cfsj').value;
		}
		var xxdm = document.getElementById('xxdm').value;
		var oldwh;
		if ($('wh')) {
			oldwh = document.getElementById('wh').value;
		}
		if (sj==null || sj=='' ||wh==null||wh=='') {
			alert("����ʱ��ʹ����ĺű�����д��");
			return false;
		}
		var xxclyj = document.getElementById("xxclyj").value;
		if (xxclyj.length>500) {
			alert("�����������������500������!");
			return false;
		}
		if (chknotnull1()) {
			if ('13022'==xxdm) {
				chgJxjlist.chkStucfwh(xh,wh,function(data){
				if (data) {
					if (wh==oldwh) {
						refreshForm('/xgxt/stuPunishAuditOne.do?act=save&cflb='+$('cflb').value + '&cfyy='+$('cfyy').value);BatAlert.showTips('���ڲ�������ȴ�...');
					} else {
						alert('�����ڸô����ĺ����Ѵ��ڴ�����Ϣ,���޸Ĵ����ĺ�!');
					}
					return;
				} else {
					refreshForm('/xgxt/stuPunishAuditOne.do?act=save&cflb='+$('cflb').value + '&cfyy='+$('cfyy').value);BatAlert.showTips('���ڲ�������ȴ�...');
				}
			});	
			} else {
				//����ǰ���жϸ���ͬһ�����ĺţ�����ʱ���Ƿ�������
				var hidd_cfsj = $('hidd_cfsj').value;
				var hidd_cfwh = $('hidd_cfwh').value;
				commWjcfDAO.checkStuWjcfIsExists(xh,sj,wh,function(data) {
					if (!data && ((hidd_cfsj != sj) || (hidd_cfwh !=wh)) && (sj != null && wh != null)) {
						alert("�����ڴ���ʱ��Ϊ:'" + sj + "',�����ĺ�Ϊ:'" + wh+"'���ļ������ܹ�һ�δ��֣�\nͬһѧ��ͬһ����ʱ�����ĺ����治���ж��δ��֣����������ʱ����ĺš�");
						return false;
					} else {
						refreshForm('/xgxt/stuPunishAuditOne.do?act=save&cflb='+$('cflb').value + '&cfyy='+$('cfyy').value);BatAlert.showTips('���ڲ�������ȴ�...');	
					}
				});
			}
		} else return;
	}
	function chkShzt() {
		var yesNo = document.getElementById('yesNo').value;
		var cfnx = document.getElementById('cfnx').value;
		if (yesNo != 'ͨ��' && cfnx != '') {
			alert("������δ���ͨ��,������д��������!");
			document.getElementById('cfnx').value = "";
			return false;
		}
	}
	function disLxck() {
		if ($('dis') && $('dis').value=='no') {
			if($('lxcksj')){
				$('lxcksj').disabled = true;
			}
			if($('cfsj')){
				$('cfsj').readonly = true;
			}
			if($('cfwh')){
				$('cfwh').readonly = true;
			}
		}
	}
</script>
	</head>
	<body onload="disLxck()">
		<logic:notPresent name="showXbemy">
			<div class="tab_cur">
				<p class="location">
					<logic:equal value="11049" name="xxdm">
						<logic:equal value="xy" name="userType">
							<em>���ĵ�ǰλ��:</em>
							<a>Υ�ʹ��� - ��� - ϵԺ���</a>
						</logic:equal>
						<logic:notEqual value="xy" name="userType">
							<em>���ĵ�ǰλ��:</em>
							<a>Υ�ʹ��� - ��� - ѧ�������</a>
						</logic:notEqual>
					</logic:equal>
					<logic:notEqual value="11049" name="xxdm">
						<em>���ĵ�ǰλ��:</em>
						<a>Υ�ʹ��� - ��� - ѧУ��� - �������</a>
					</logic:notEqual>
				</p>
			</div>


			<html:form action="/data_search" method="post">
				<input type="hidden" name="xxdm" id="xxdm" value="${xxdm }" />
				<input type="hidden" name="userType" id="userType"
					value="${userType }" />
				<input type="hidden" name="pkVal"
					value="<bean:write name="xh||xn||sbsj"/>" />
				<input type="hidden" name="hidd_cfsj" id="hidd_cfsj"
					value="${cfsj }" />
				<input type="hidden" name="hidd_cfwh" id="hidd_cfwh"
					value="${cfwh }" />


				<div class="tab">
					<table width="100%" border="0" class="formlist">
						<thead>
							<tr>
								<th colspan="4">
									<span>�������</span>
								</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<td colspan="4">
									<logic:equal value="no" name="xy_writable">
										<div class="bz">
											<font color="red">�ô�����ѧУ���������!</font>
										</div>
									</logic:equal>
									<input type="hidden" id="dis" value="${xy_writable }" />

									<div class="btn">
										<logic:notEqual value="no" name="czxx_wjcf_writable">
											<logic:notEqual value="true" name="xyNowrite">
												<button type="button" onclick="chkcfwh();" id="buttonSave">
													�� ��
												</button>
											</logic:notEqual>
										</logic:notEqual>
										<button type="button" onclick="Close();return false;" id="buttonClose">
											�� ��
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
						<tbody>

							<tr>
								<th>
									ѧ��
								</th>
								<td align="left">
									<input type="text" name="xh" id="xh" value="${XH }"
										readonly="readonly" />
								</td>
								<th>
									���
								</th>
								<td align="left">
									<bean:write name="ND" />
								</td>
							</tr>
							<tr>
								<th>
									����
								</th>
								<td align="left">
									<bean:write name="XM" />
								</td>
								<th>
									ѧ��
								</th>
								<td align="left">
									<bean:write name="XN" />
									<input type="hidden" name="xn" id="xn" value="${XN }" />
									<input type="hidden" name="sbsj" id="sbsj" value="${sbsj }" />
								</td>
							</tr>
							<tr>
								<th>
									�Ա�
								</th>
								<td align="left">
									<bean:write name="XB" />
								</td>
								<th>
									<font color="red">*</font>�������
								</th>
								<td align="left">
									<html:select property="cflb" styleId="cflb" style="width:150"
										styleClass="select" disabled="true">
										<html:options collection="cflbList" property="cflbdm"
											labelProperty="cflbmc" />
									</html:select>
									<logic:notEmpty name="fjList">									
									<logic:iterate id="s" name="fjList">

											&nbsp;&nbsp;<a href="downloadfilewj.do?wjsclj=${s.fjsclj }"
												target="_self">�����ļ�����</a>
					
									</logic:iterate>
						
					</logic:notEmpty>
								</td>
							</tr>
							<tr>
								<th>
									�꼶
								</th>
								<td align="left">
									<bean:write name="NJ" />
								</td>
								<th>
									<font color="red">*</font>����ԭ��
								</th>
								<td align="left">
									<html:select property="cfyy" styleId="cfyy" style="width:150px"
										styleClass="select" disabled="true">
										<html:options collection="cfyyList" property="cfyydm"
											labelProperty="cfyymc" />
									</html:select>

								</td>
							</tr>
							<tr>
								<th>
									<bean:message key="lable.xsgzyxpzxy" />
								</th>
								<td align="left">
									<bean:write name="XYMC" />
								</td>
								<th>
									���
								</th>
								<td align="left">
									<html:select property="yesNo" styleId="yesNo">
										<html:options collection="chkList" property="en"
											labelProperty="cn" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th>
									רҵ
								</th>
								<td align="left">
									<bean:write name="ZYMC" />
								</td>
								<logic:equal value="11049" name="xxdm">
									<td></td>
									<td></td>
								</logic:equal>
								<logic:notEqual value="11049" name="xxdm">
									<th>
										<font color="red">*</font>����ʱ��
									</th>
									<td align="left">
										<!-- ���ƶ�����У���<bean:message key="lable.xsgzyxpzxy" />�ǲ��ܽ��з��ĵ� -->
										<logic:notEqual value="no" name="xy_writable">
											<html:text property="cfsj" styleId="cfsj"
												onblur="dateFormatChg(this)" style="cursor:hand;"
												readonly="true"
												onclick="return showCalendar('cfsj','y-mm-dd');" />
										</logic:notEqual>
										<logic:equal value="no" name="xy_writable">
											<html:text property="cfsj" styleId="cfsj"
												style="cursor:hand;" readonly="true" />
										</logic:equal>
									</td>
								</logic:notEqual>
							</tr>
							<tr>
								<th>
									�༶
								</th>
								<td align="left">
									<bean:write name="BJMC" />
								</td>
								<logic:equal value="11049" name="xxdm">
									<td></td>
									<td></td>
								</logic:equal>
								<logic:notEqual value="11049" name="xxdm">
									<th>

										<logic:equal value="11078" name="xxdm">
																				<font color="red">*</font>Υ�ʹ��־������ĺ�
										</logic:equal>
										<logic:notEqual value="11078" name="xxdm">
																			<font color="red">*</font>�����ĺ�
									</logic:notEqual>
									</th>
									<td align="left">
										<!-- ���ƶ�����У���<bean:message key="lable.xsgzyxpzxy" />�ǲ��ܽ��з��ĵ� -->
										<logic:notEqual value="no" name="xy_writable">
											<html:text property="cfwh" styleId="cfwh" maxlength="30" />
											<input type="hidden" id="wh" value="${cfwh }" />
										</logic:notEqual>
										<logic:equal value="no" name="xy_writable">
											<html:text property="cfwh" styleId="cfwh" maxlength="30"
												readonly="true" />
										</logic:equal>
									</td>
								</logic:notEqual>
							</tr>

							<tr>
								<th>
									Υ��ʱ��
								</th>
								<td align="left">
									${wjsj }
								</td>
								<logic:notEqual name="xxdm" value="11078">
									<logic:equal name="userType" value="xx">
										<th>
											����ʱ��
										</th>
										<td align="left">
											<html:text property="pysj" styleId="pysj" value="${pysj}"
												onblur="dateFormatChg(this)" style="cursor:hand;"
												readonly="true"
												onclick="return showCalendar('pysj','y-mm-dd');" />
										</td>
									</logic:equal>
									<logic:equal name="userType" value="admin">
										<th>
											����ʱ��
										</th>
										<td align="left">
											<html:text property="pysj" styleId="pysj" value="${pysj}"
												onblur="dateFormatChg(this)" style="cursor:hand;"
												readonly="true"
												onclick="return showCalendar('pysj','y-mm-dd');" />
										</td>
									</logic:equal>
									<logic:notEqual name="userType" value="admin">
										<logic:notEqual name="userType" value="xx">
											<th>

											</th>
											<td align="left">

											</td>
										</logic:notEqual>
									</logic:notEqual>
								</logic:notEqual>
								<logic:equal name="xxdm" value="11078">
									<th>
										�����
									</th>
									<td align="left">
										<logic:empty name="fsjname">
									${fsjnames }
									</logic:empty>
										<logic:notEmpty name="fsjnam">
									${fsjname }
									</logic:notEmpty>
									</td>
								</logic:equal>
							</tr>

							<%--�人����ѧ����<bean:message key="lable.xsgzyxpzxy" />--%>
							<logic:equal value="1049701" name="xxdm">
								<tr>
									<th>
										�۷�
									</th>
									<td colspan="4" align="left">
										<html:text property="kf" styleId="kf"
											onkeyup="ckinpdata(this)" maxlength="10"></html:text>
									</td>
								</tr>
							</logic:equal>
<%--							<logic:equal value="yes" name="lxck">--%>
<%--								<tr>--%>
<%--									<th>--%>
<%--										��У�쿴���ʱ��--%>
<%--									</th>--%>
<%--									<td colspan="4" align="left">--%>
<%--										<html:text property="lxcksj" styleId="lxcksj"--%>
<%--											onblur="dateFormatChg(this)" style="cursor:hand;"--%>
<%--											readonly="true"--%>
<%--											onclick="return showCalendar('lxcksj','y-mm-dd');"--%>
<%--											value="${lxcksj}"></html:text>--%>
<%--									</td>--%>
<%--								</tr>--%>
<%--							</logic:equal>--%>


							<!-- �������е��� -->
							<logic:equal value="12645" name="xxdm">
								<tr>
									<th>
										��������
									</th>
									<td colspan="" align="left">


										<logic:notEqual value="no" name="xy_writable">
											<html:text property="cfnx" styleId="cfnx" maxlength="20"
												onkeyup="chkShzt()" value="${cfnx }"></html:text>
										</logic:notEqual>
										<logic:equal value="no" name="xy_writable">
											<html:text property="cfnx" styleId="cfnx" value="${cfnx }"
												maxlength="30" readonly="true" />
										</logic:equal>
									</td>
									<th>
										�������Ƿ����
									</th>
									<td>
										${sfsb }
									</td>
								</tr>
								<tr>
									<th>
										�������
									</th>
									<td colspan="4">
										<html:textarea rows="3" style="width:500px;overflow:auto"
											property="sbsy" styleId="a" readonly="true"></html:textarea>
									</td>
								</tr>
							</logic:equal>
<%--							<tr>--%>
<%--								<th>--%>
<%--									ѧ��ǩ��--%>
<%--								</th>--%>
<%--								<td align="left">--%>
<%--									${sfqs}--%>
<%--								</td>--%>
<%--								<logic:equal name="xxdm" value="11654">--%>
<%--								<th>--%>
<%--									�Ƿ��֪ѧ��--%>
<%--								</th>--%>
<%--								</logic:equal>--%>
<%--								<logic:notEqual name="xxdm" value="11654">--%>
<%--								<th>--%>
<%--									ѧ������ȷ��--%>
<%--								</th>--%>
<%--								</logic:notEqual>--%>
<%--								<td align="left">--%>
<%--									${xsqr}--%>
<%--								</td>--%>
<%--							</tr>--%>
<%--							<tr>--%>
<%--								<th>--%>
<%--									ȷ��ʱ��--%>
<%--								</th>--%>
<%--								<td align="left">--%>
<%--									${qrsj }--%>
<%--								</td>--%>
<%--								<th>--%>
<%--								</th>--%>
<%--								<td>--%>
<%--								</td>--%>
<%--							</tr>--%>

							<tr>
								<th>
									Υ����ʵ
								</th>
								<td colspan="4" align="left">
									<html:textarea rows="6" style="width:500px;overflow:auto"
										property="bz" styleId="a" readonly="true"></html:textarea>
								</td>
							</tr>
							<logic:equal value="11049" name="xxdm">
								<tr>
									<th>
										ϵԺ���
									</th>
									<td colspan="4" align="left">
										<logic:equal value="xy" name="userType">
											<html:textarea rows="6" style="width:500px" property="xyclyj"
												styleId="a"></html:textarea>
										</logic:equal>
										<logic:notEqual value="xy" name="userType">
											<html:textarea rows="6" style="width:500px" property="xyclyj"
												styleId="a" readonly="true"></html:textarea>
										</logic:notEqual>
									</td>
								</tr>
								<logic:notEqual value="xy" name="userType">
									<tr>
										<th>
											������
										</th>
										<td colspan="4" align="left">
											<html:textarea rows="5" style="width:500px" property="XXCLYJ"
												styleId="a" />
										</td>
									</tr>
								</logic:notEqual>
							</logic:equal>
							<logic:notEqual value="11049" name="xxdm">
								<logic:notEqual value="xy" name="userType">
	
								</logic:notEqual>
								<tr>
									<th>
										������
										<br />
										<font color="red">(500������)</font>
									</th>
									<td colspan="4" align="left">
										<html:textarea rows="5" style="width:500px" property="XXCLYJ"
											onkeyup="checkLen(this,500)" styleId="xxclyj" />
									</td>
								</tr>
							</logic:notEqual>
					</table>
				</div>
			</html:form>
		</logic:notPresent>
		<logic:equal value="showXbemy" name="showXbemy">
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>Υ�ʹ��� - ��� - ѧУ��� - �������</a>
				</p>
			</div>
			</div>
			<input type="hidden" name="pkVal"
				value="<bean:write name="xh||cfrq||cfwh"/>" />


			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="5">
								<span>�������</span>
							</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<td colspan="5">
								<div class="btn">
									<button type="button"
										onclick="refreshForm('/xgxt/stuPunishAuditOne.do?act=save"
										);"
											id="buttonSave">
										�� ��
									</button>
									<button type="button" onclick="Close();return false;" id="buttonClose">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>

					<tbody>
						<tr>
							<th>
								ѧ��
							</th>
							<td align="left">
								<bean:write name="XH" />
							</td>
							<th>
								���
							</th>
							<td align="left">
								<bean:write name="ND" />
							</td>
							<td rowspan="5" align="center" width="15%">
								<img align="center" border="0" style="height:133px;width:100px;"
									src="/xgxt/pictures/<bean:write name="XH" />.jpg" id="pic" />
							</td>
						</tr>
						<tr>
							<th>
								����
							</th>
							<td align="left">
								<bean:write name="XM" />
							</td>
							<th>
								ѧ��
							</th>
							<td align="left">
								<bean:write name="XN" />
							</td>
						</tr>
						<tr>
							<th>
								�Ա�
							</th>
							<td align="left">
								<bean:write name="XB" />
							</td>
							<th>
								�������
							</th>
							<td align="left">
								<bean:write name="cflbmc" />
							</td>
						</tr>
						<tr>
							<th>
								�꼶
							</th>
							<td align="left">
								<bean:write name="NJ" />
							</td>
							<th>
								����ԭ��
							</td>
							<td align="left">
								<bean:write name="cfyymc" />
							</td>
						</tr>
						<tr>
							<th>
								<bean:message key="lable.xsgzyxpzxy" />

							</th>
							<td align="left">
								<bean:write name="XYMC" />
							</td>
							<th>
								���
							</th>
							<td align="left">
								<html:select property="yesNo">
									<html:options collection="chkList" property="en"
										labelProperty="cn" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								רҵ
							</th>
							<td align="left">
								<bean:write name="ZYMC" />
							</td>
							<th>
								Υ��ʱ��
							</th>
							<td align="left" colspan="2">
								<bean:write name="wjsj" />
							</td>
						</tr>
						<tr>
							<th>
								�༶
							</th>
							<td align="left">
								<bean:write name="BJMC" />
							</td>
							<th>
								���ʱ��
							</th>
							<td align="left" colspan="2">
								<input type="text" name="shsj" id="shsj"
									value="<bean:write name="shsj"/>"
									onclick="return showCalendar('shsj','y-mm-dd');"
									onblur="getRqVal('shsj')" />
							</td>
						</tr>
						<tr>
							<th>
								Υ����ʵ
							</th>
							<td colspan="4" align="left">
								<html:textarea rows="5" style="width:98%" property="bz"
									styleId="a"></html:textarea>
							</td>
						</tr>
						<tr>
							<th>
								������
							</th>
							<td colspan="4" align="left">
								<html:textarea rows="5" style="width:98%" property="XXCLYJ"
									styleId="a" />
							</td>
						</tr>
					</tbody>
				</table>
		</logic:equal>
		<logic:equal value="view" name="result">
			<script>
				alert("�����ɹ�");
				if(window.dialogArguments){
			 		window.dialogArguments.document.getElementById('search_go').onclick();
			 	}
				Close();
			</script>
		</logic:equal>
		<logic:equal value="noview" name="result">
			<script>
				alert("�ĺ��Ѵ��ڣ�����ʧ��");
				if(window.dialogArguments){
			 		window.dialogArguments.document.getElementById('search_go').onclick();
			 	}
				Close();
			</script>
		</logic:equal>
	</body>
</html>
