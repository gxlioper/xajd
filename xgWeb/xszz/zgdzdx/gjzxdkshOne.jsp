<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<base target="_self"/>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />

	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
</head>
	<body onload="checkWinType();document.all('buttonClose').focus()">
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script language="javascript" src="js/String.js"></script>
		<script language="javascript">
		function yz(){
			var htbh = document.getElementById('htbh').value;
			var shjg = document.getElementById('shjg').value;
			var hthList = document.getElementById('hthList').value;
			var havehtbhList = document.getElementById('havehtbhList').value;
			var b = false;
			list = hthList.split("!!OneSpile!!");
			
			list2 = havehtbhList.split("!!OneSpile!!");
			
			if('ͨ��'==shjg && ''== htbh){
				alert('����д��ͬ��ţ�')
				return false;
			}
			
			if (htbh != null && htbh != ""){
				for(i=0; i < list.length && !b; i++){
					if (Math.round(list[i]) == Math.round(htbh)){
						b = true;
					}
				}
			
				if(!b){
					alert("��ͬ��Ų���<bean:message key="lable.xsgzyxpzxy" />�ɷ����ŷ�Χ�ڣ���������д��ͬ���!");
					return false;
				}
				for(j=0; j < list2.length; j++){
					if (Math.round(list2[j]) == Math.round(htbh)){
						alert("�ú�ͬ����ѷ��䣬��������д��ͬ���!");
						return false;
					}
				}
			}
			
			refreshForm('/xgxt/zgdzdx_xszz.do?method=gjzxdkshSave');
		}
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
		function toPrintOut(){//�����Ӧ�Ĵ�ӡҳ��
			var pkVal = document.getElementById('pkVal').value;
			showOpenWindow("/xgxt/zgdzdx_xszz.do?method=gjzxdksqb&pkVal="+pkVal);
		}
		
		function setHdbh(text){
			if ('ͨ��'==text) {
				if ($('kyhdbh').options.length>0){
					$('htbh').value = $('kyhdbh').options[1].value;
				} else {
					alert('��ͬ��Ų���!');
				}
				
			} else {
				$('htbh').value = '';
			}
		}
		
		</script>
		<html:form action="/zgdzdx_xszz.do?method=gjzxdkshSave" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>��ѧ���� - ��� - ������ѧ������� - �������</a>
				</p>
			</div>
			
			<logic:present name="ok">
				<logic:match value="ok" name="ok">
					<script language="javascript">
	         	alert("����ɹ���");
	         	close();
	         	dialogArgumentsQueryChick();
	         	</script>
				</logic:match>
				<logic:match value="no" name="ok">
					<script language="javascript">
	         	alert("����ʧ�ܣ�");
	         	</script>
				</logic:match>
			</logic:present>
			<input type="hidden" name="pkVal" value="<bean:write name="pkVal"/>" />
			<input type="hidden" id="userType" name="userType"
				value="<bean:write name="userType" />" />
			<input type="hidden" id="hthList" name="hthList"
				value="<bean:write name="rs" property="hthList" />">
			<input type="hidden" id="havehtbhList" name="havehtbhList"
				value="<bean:write name="rs" property="havehtbhList" />">
			<input type="hidden" id="htmc" name="htmc"
				value="<bean:write name="rs" property="htmc" />">
				
			<div class="tab">
			  <table width="100%"  border="0" class="formlist">
			    <thead>
			    	<tr>
			        	<th colspan="4"><span>�������</span></th>
			        </tr>
			    </thead>
				<tfoot>
				  <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
			            <div class="btn">
			         	 <logic:notEqual name="lsht" value="yes">
						<button type="button"  onclick="yz()" style="width:80px"
							id="buttonSave">
							�� ��
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						</logic:notEqual>
						<button type="button"  onclick="toPrintOut();" style="width:80px">
							�� ӡ
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="button"  onclick="Close();return false;" style="width:80px"
							id="buttonClose">
							�� ��
						</button>
			          	</div>
			          </td>
			      </tr>	
				</tfoot>
				<tbody>
				<tr>
					<th align="center" width="16%">
						ѧ�ţ�
					</th>
					<td align="left" width="34%">
						<bean:write name='rs' property="xh" />
						<input type="hidden" id="xh" name="xh"
							value="<bean:write name='rs' property="xh" />" />
					</td>
					<th width="16%">
							������
					</th>
					<td width="34%">
						<bean:write name="rs" property="xm" />
						<input type="hidden" id="xm" name="xm"
							value="<bean:write name='rs' property="xm" />" />
					</td>
				</tr>
				<tr>
					<th>
							�Ա�
					</th>
					<td>
						<bean:write name="rs" property="xb" />
						<input type="hidden" id="xb" name="xb"
							value="<bean:write name='rs' property="xb" />" />
					</td>
					<th>
							�������ڣ�
					</th>
					<td>
						<bean:write name="rs" property="csrq" />
						<input type="hidden" id="csrq" name="csrq"
							value="<bean:write name='rs' property="csrq" />" />
					</td>
				</tr>
				<tr>
					<th>
							���֤�ţ�
					</th>
					<td>
						<bean:write name="rs" property="sfzh"/>
						<input type="hidden" id="sfzh" name="sfzh"
							value="<bean:write name='rs' property="sfzh" />" />
					</td>
					<th>
							����绰��
					</th>
					<td>
						<bean:write name="rs" property="ssdh"/>
						<input type="hidden" id="ssdh" name="ssdh"
							value="<bean:write name='rs' property="ssdh" />" />
					</td>
				</tr>
				<tr>
					<th>
							ѧ�ƣ�
					</th>
					<td>
						<bean:write name="rs" property="xz"/>
						<input type="hidden" id="xz" name="xz"
							value="<bean:write name='rs' property="xz" />" />
					</td>
					<th>
							ѧ����
					</th>
					<td>
						<bean:write name="rs" property="xl"/>
						<input type="hidden" id="xl" name="xl"
							value="<bean:write name='rs' property="xl" />" />
					</td>
				</tr>
				<tr>
					<th>
							�꼶��
					</th>
					<td>
						<bean:write name="rs" property="nj"/>
						<input type="hidden" id="nj" name="nj"
							value="<bean:write name='rs' property="nj" />" />
					</td>
					<th>
							<bean:message key="lable.xsgzyxpzxy" />���ƣ�
					</th>
					<td>
						<bean:write name="rs" property="xymc"/>
						<input type="hidden" id="xymc" name="xymc"
							value="<bean:write name='rs' property="xymc" />" />
					</td>
				</tr>
				<tr>
					<th>
							רҵ���ƣ�
					</th>
					<td>
						<bean:write name="rs" property="zymc"/>
						<input type="hidden" id="zymc" name="zymc"
							value="<bean:write name='rs' property="zymc" />" />
					</td>
					<th>
							������ţ�
					</th>
					<td>
						<bean:write name="rs" property="bjmc"/>
						<input type="hidden" id="bjmc" name="bjmc"
							value="<bean:write name='rs' property="bjmc" />" />
					</td>
				</tr>
				<tr>
					<th>
							E-Mail��QQ��
					</th>
					<td>
						<bean:write name="rs" property="emailqq"/>
					</td>
					<th>
							�����˺ţ�
					</th>
					<td>
						<bean:write name="rs" property="hkczh"/>
					</td>
				</tr>
				<tr>
					<th>
							���򾭼�״����
					</th>
					<td>
						<bean:write name="rs" property="qyjjzk"/>
					</td>
					<th>
							�Ƿ�Ϊ�ſ����е��ؾ���
					</th>
					<td>
						<bean:write name="rs" property="sfwfkyhddjm"/>
					</td>
				</tr>
				<tr>
					<th>
							��������(��)��
					</th>
					<td>
						<bean:write name="rs" property="dkqxy"/>
						<input type="hidden" id="dkqxy" name="dkqxy"
							value="<bean:write name='rs' property="dkqxy" />" />
					</td>
					<th>
							�������ޣ�
					</th>
					<td>
						<bean:write name="rs" property="dkqx"/>
						<input type="hidden" id="dkqx" name="dkqx"
							value="<bean:write name='rs' property="dkqx" />" />
					</td>
				</tr>
				<tr>
					<th>
							�����
					</th>
					<td>
						<bean:write name="rs" property="sqdkje"/>
						<input type="hidden" id="sqdkje" name="sqdkje"
							value="<bean:write name='rs' property="sqdkje" />" />
					</td>
					<th>
							��ͥ�¾����룺
					</th>
					<td>
						<bean:write name="rs" property="jtysr"/>
						<input type="hidden" id="jtysr" name="jtysr"
							value="<bean:write name='rs' property="jtysr" />" />
					</td>
				</tr>
				<tr>
					<th>
							��ͥ��ϸ��ַ��
					</th>
					<td colspan="3">
						<bean:write name="rs" property="jtxxzz"/>
						<input type="hidden" id="jtxxzz" name="jtxxzz"
							value="<bean:write name='rs' property="jtxxzz" />" />
					</td>
				</tr>
				<tr>
					<th>
							��ͥ�ʱࣺ
					</th>
					<td>
						<bean:write name="rs" property="jtyb"/>
						<input type="hidden" id="jtyb" name="jtyb"
							value="<bean:write name='rs' property="jtyb" />" />
					</td>
					<th>
							��ͥ�绰��
					</th>
					<td>
						<bean:write name="rs" property="jtdh"/>
						<input type="hidden" id="jtdh" name="jtdh"
							value="<bean:write name='rs' property="jtdh" />" />
					</td>
				</tr>
				<tr>
					<th>
							����������
					</th>
					<td>
						<bean:write name="rs" property="fqxm"/>
						<input type="hidden" id="fqxm" name="fqxm"
							value="<bean:write name='rs' property="fqxm" />" />
					</td>
					<th>
							ĸ��������
					</th>
					<td>
						<bean:write name="rs" property="mqxm"/>
						<input type="hidden" id="mqxm" name="mqxm"
							value="<bean:write name='rs' property="mqxm" />" />
					</td>
				</tr>
				<tr>
					<th>
							����ְҵ��
					</th>
					<td>
						<bean:write name="rs" property="fqzy"/>
						<input type="hidden" id="fqzy" name="fqzy"
							value="<bean:write name='rs' property="fqzy" />" />
					</td>
					<th>
							ĸ��ְҵ��
					</th>
					<td>
						<bean:write name="rs" property="mqzy"/>
						<input type="hidden" id="mqzy" name="mqzy"
							value="<bean:write name='rs' property="mqzy" />" />
					</td>
				</tr>
				<tr>
					<th>
							�������֤�ţ�
					</th>
					<td>
						<bean:write name="rs" property="fqsfzh"/>
						<input type="hidden" id="fqsfzh" name="fqsfzh"
							value="<bean:write name='rs' property="fqsfzh" />" />
					</td>
					<th>
							ĸ�����֤�ţ�
					</th>
					<td>
						<bean:write name="rs" property="mqsfzh"/>
						<input type="hidden" id="mqsfzh" name="mqsfzh"
							value="<bean:write name='rs' property="mqsfzh" />" />
					</td>
				</tr>
				<tr>
					<th>
							��ע��
					</th>
					<td colspan="3">
						<bean:write name="rs" property="bz"/>
						<input type="hidden" id="bz" name="bz"
							value="<bean:write name='rs' property="bz" />" />
					</td>
				</tr>
				<tr>
					<th>
							����ʱ�䣺
					</th>
					<td>
						<bean:write name="rs" property="sqsj" />
						<input type="hidden" id="sqsj" name="sqsj"
							value="<bean:write name='rs' property="sqsj" />" />
					</td>
					<td colspan="2">
						&nbsp;
					</td>
				</tr>
				<logic:equal name="lsht" value="yes">
					<tr>
						<th>
								��˽����
						</th>
						<td>
							<bean:write name="rs" property="shjg"/>
						</td>
						<th>
								��ͬ��ţ�
						</th>
						<td>
							<bean:write name="rs" property="htbh"/>
						</td>
					</tr>
					<tr>
						<th>
								��������
						</th>
						<td colspan="3">
							<bean:write name="rs" property="shyj"/>
						</td>
					</tr>
				</logic:equal>
				<logic:notEqual name="lsht" value="yes">
					<tr>
						<th>
								��˽����
						</th>
						<td>
							<html:select name="rs" property="shjg" styleId="shjg" onchange="setHdbh(this.value);">
								<html:options collection="chkList" property="en"
									labelProperty="cn" />
							</html:select>
						</td>
						<th>
								��ͬ��ţ�
						</th>
						<td>
							7245-<bean:write name="rs" property="nd"/>-<bean:write name="rs" property="htmc"/>-
							<input type="text" id="htbh" name="htbh" maxlength="4"
								style="width:40%;heigh:100%"
								value="<bean:write name="rs" property="htbh"/>"
								readonly="true">
								
								<html:select property="kyhdbh" styleId="kyhdbh" style="display:none">
									<html:option value=""></html:option>
									<html:options collection="htList" property="en" labelProperty="cn"/>
								</html:select>
						</td>
					</tr>
					<tr>
						<th>
								��������
						</th>
						<td colspan="3">
							<input type="text" id="shyj" name="shyj" maxlength="200"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="shyj"/>">
						</td>
					</tr>
				</logic:notEqual>
				</tbody>
			</table>
				 </div>
			
		</html:form>
	</body>
</html>
