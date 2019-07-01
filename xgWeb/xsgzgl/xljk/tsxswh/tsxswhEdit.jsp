<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/check.js"></script>
		<script language="javascript" src="js/sxjyFunction.js"></script>

		<script type="text/javascript"
			src="/xgxt/dwr/interface/getXjydInfo.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
		<script>
		function save(){
			var xn = document.getElementById("xn").value;
			var xq = document.getElementById("xq").value;
			var xh = document.getElementById("xh").value;
			var zywt = document.getElementById("zywt").value;
			var brth = document.getElementById("brth").value;
			var lxjs = document.getElementById("lxjs").value;
			var bz = document.getElementById("bz").value;
			if(xn==""||xn == null||xq==""||xq==null||xh==""||xh==null){
				alertInfo("����*����Ŀ����Ϊ�գ���ȷ��");
				return false;
			}
			if(zywt.length>500){
				alertInfo("��Ҫ�������ݹ�����");
				return false;
			}
			if(brth.length>500){
				alertInfo("����̸�����ݹ�����");
				return false;
			}
			if(lxjs.length>500){
				alertInfo("��ϵ�������ݹ�����");
				return false;
			}
			if(bz.length>500){
				alertInfo("��ע���ݹ�����");
				return false;
			}
			confirmInfo("ȷ��������",function(data){
				if("ok"==data){
					saveUpdate(	'/xgxt/xljk_tsxswh.do?method=tsxswhEdit&doType=save', 'xn-xq-xh');
				}
			});
		}
		function chk(){
			var xn = document.getElementById("xn").value;
			var xq = document.getElementById("xq").value;
			var xh = document.getElementById("xh").value;
			if(xn!=""&&xq!=""&&xh!=""){
				document.forms[0].action='/xgxt/xljk_tsxswh.do?method=tsxswhEdit&doType=add&act=hx';
				document.forms[0].submit();
				return false;
			}
			return false;
		}
		</script>
	</head>
	<body>
		<html:form action="/xljk_tsxswh" method="post"><!--
			<div class="tab_cur" id="jd">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title }</a>
				</p>
			</div>
			--><div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>����ѧ��ά����Ϣ</span>
								<input type="hidden" id="doType" name="doType"
									value="<bean:write name="doType" scope="request"/>" />
								<html:hidden name = "rs" property="pkValue" styleId="pkValue"/>
								<input type="hidden" id="getStuInfo" name="getStuInfo"
									value="xm-xb-zymc-bjmc" />
								<input type="hidden" id="url" name="url" value="/xsgzgl/xljk/tsxswh/tsxswhEdit.jsp" />
							</th>
						</tr>
					</thead>
					<tbody >					
						<tr>
							<td align="right">
								<font color="red">*</font>ѧ�꣺
							</td>
							<td align="left">
							<logic:equal name="doType" value="add">
								<html:select name="rs" property="xn" style="width:90px"
									styleId="xn" onchange="chk()">
									<logic:notEmpty name="xnList">
										<html:options collection="xnList" property="xn"
											labelProperty="xn" />
									</logic:notEmpty>
								</html:select>
							</logic:equal>
							<logic:notEqual name="doType" value="add">
								<html:hidden name="rs" property="xn" styleId="xn"/>
								<html:select name="rs" property="xn" style="width:90px"
									styleId="xn" disabled="true">
									<logic:notEmpty name="xnList">
										<html:options collection="xnList" property="xn"
											labelProperty="xn" />
									</logic:notEmpty>
								</html:select>
							</logic:notEqual>
							</td>
							<td align="right">
								<font color="red">*</font>ѧ�ڣ�
							</td>
							<td align="left">
								<logic:equal name="doType" value="add">
									<html:select name="rs" property="xq" style="width:90px"
									styleId="xq" onchange="chk()">
									<logic:notEmpty name="xqList">
										<html:options collection="xqList" property="xqdm" 
											labelProperty="xqmc" />
									</logic:notEmpty>
									</html:select>
								</logic:equal>
								<logic:notEqual name="doType" value="add">
									<html:hidden name="rs" property="xq" styleId="xq"/>
									<html:select name="rs" property="xq" style="width:90px"
									styleId="xq" disabled="true">
									<logic:notEmpty name="xqList">
										<html:options collection="xqList" property="xqdm"
											labelProperty="xqmc" />
									</logic:notEmpty>
									</html:select>
								</logic:notEqual>
							</td>
						</tr>
						<tr>
							<td align="right">
								<font color="red">*</font>ѧ�ţ�
							</td>
							<td align="left">
								<logic:equal name="doType" value="add">
									<html:text name='rs' property="xh"
									styleId="xh" onkeypress="autoFillStuInfo(event.keyCode,this)" readonly="true" />
									<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
									class="btn_01" id="buttonFindStu" >
									ѡ��
									</button>
								</logic:equal>
								<logic:notEqual name="doType" value="add">
									<html:text name="rs" property="xh" styleId="xh" readonly="true"/>
								</logic:notEqual>
							</td>
							<td align="right">
								�걨ʱ�䣺
							</td>
							<td align="left">
								<html:text name="rs" property="sbsj" styleId="sbsj"
									onblur="dateFormatChg(this)" style="cursor:hand;"
									onclick="return showCalendar('sbsj','y-mm-dd');" />
							</td>
						</tr>
						<tr align='left'>
							<td align="right">
								������
							</td>
							<td align="left">
								<html:text name='rs' property="xm" styleId="xm" readonly="true"/>
							</td>
							<td align="right">
								�Ա�
							</td>
							<td align="left">
								<html:text name='rs' property="xb" styleId="xb" readonly="true"/>
							</td>
						</tr>
						<tr align='left'>
							<td align="right">
								רҵ��
							</td>
							<td align="left">
								<html:text name='rs' property="zymc" styleId="zy" readonly="true"/>
							</td>
							<td align="right">
								�༶��
							</td>
							<td align="left">
								<html:text name='rs' property="bjmc" styleId="bj" readonly="true"/>
							</td>
						</tr>
						<tr align="left">
							<td align="right">
								��Ҫ���⣺<br/><font color="blue">(��500��)</font>
							</td>
							<td colspan="3">
								<html:textarea name='rs' property='zywt' style="width:650px"
									rows='5' />
							</td>
						</tr>
						<tr align="left">
							<td align="right">
								����̸����<br/><font color="blue">(��500��)</font>
							</td>
							<td colspan="3">
								<html:textarea name='rs' property='brth' style="width:650px"
									rows='5' />
							</td>
						</tr>
						<tr align="left">
							<td align="right">
								��ϵ������<br/><font color="blue">(��500��)</font>
							</td>
							<td colspan="3">
								<html:textarea name='rs' property='lxjs' style="width:650px" 
									rows='5' />
							</td>
						</tr>
						<tr align="left">
							<td align="right">
								��ע��<br/><font color="blue">(��500��)</font>
							</td>
							<td colspan="3">
								<html:textarea name='rs' property='bz' style="width:650px"
									rows='5' />
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"<span class="red">*</span>"Ϊ������
								</div>
								<div class="btn">
									<logic:notEqual value="view" name="doType">
											<button type="button" name="����" onclick="save();return false;" id="buttonSave">
												�� ��
											</button>
									</logic:notEqual>
									<button type="button" name="�ر�" onclick="Close();return false;"; id="buttonClose">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
			<logic:present name="message">
				<script defer>
	alertInfo('${message}',
			function() {
				if (window.dialogArguments) {
					window.close();
					window.dialogArguments.document.getElementById('search_go')
							.click();
				}
			});
</script>
			</logic:present>
			<logic:present name="error">
				<script defer>
	alert('${error}');
</script>
			</logic:present>

		</html:form>

	</body>
</html>
