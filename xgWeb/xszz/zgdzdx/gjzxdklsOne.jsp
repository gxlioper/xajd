<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	
	<script language="javascript">
	function yz(){
		
		document.forms[0].action = "/xgxt/zgdzdx_xszz.do?method=gjzxdklsOneSave";
		document.forms[0].submit();
	}
</script>
</head>
	<body onload="checkWinType();document.all('buttonClose').focus()">
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script language="javascript" src="js/String.js"></script>
		<script language="javascript">
		</script>
		<html:form action="/zgdzdx_xszz.do?method=gjzxdklsOne" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>��ѧ���� - ��� - ������ѧ������ʷ������ϸ��Ϣ</a>
				</p>
			</div>
	
			<input type="hidden" name="pkVal" value="<bean:write name="pkVal"/>" />
			<input type="hidden" id="userType" name="userType"
				value="<bean:write name="userType" />" />
			<logic:present name="ok">
				<logic:match value="ok" name="ok">
					<script language="javascript">
	         	alert("����ɹ���");
	         	</script>
				</logic:match>
				<logic:match value="no" name="ok">
					<script language="javascript">
	         	alert("����ʧ�ܣ�");
	         	</script>
				</logic:match>
			</logic:present>
			<div class="tab">
			  <table width="100%"  border="0" class="formlist">
				<thead>
			    	<tr>
			        	<th colspan="4"><span>������ѧ������ʷ������ϸ��Ϣ</span></th>
			        </tr>
			    </thead>
			     <tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
			          <div class="btn">
			         <logic:equal name="userType" value="admin">
			          		<button type="button" name="�ύ"  onClick="yz();">�� ��</button>
			         </logic:equal>
			            <button type="button" name="�ر�" onclick="Close();window.dialogArguments.document.getElementById('search_go').click();" 
					id="buttonClose">�� ��</button>
			          </div></td>
			      </tr>
			    </tfoot>
			    
			    <tbody>
				<tr>
					<th align="center" width="16%">
						ѧ�ţ�
					</th>
					<td align="left" width="34%">
						<input type="text" readonly="readonly" id="xh" name="xh"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="xh"/>">
					</td>
					<th width="16%">
							������
					</th>
					<td width="34%">
						<input type="text" maxlength="50" id="xm" name="xm"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="xm"/>">
					</td>
				</tr>
				<tr>
					<th>
							�Ա�
					</th>
					<td>
						<input type="text" maxlength="10" id="xb" name="xb"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="xb"/>">
					</td>
					<th>
							�������ڣ�
					</th>
					<td>
						<input type="text" maxlength="10" id="csrq" name="csrq"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="csrq"/>">
					</td>
				</tr>
				<tr>
					<th>
							���֤�ţ�
					</th>
					<td>
						<input type="text" id="sfzh" name="sfzh" maxlength="20"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="sfzh"/>">
					</td>
					<th>
							����绰��
					</th>
					<td>
						<input type="text" id="ssdh" name="ssdh" maxlength="20"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="ssdh"/>">
					</td>
				</tr>
				<tr>
					<th>
							�꼶��
					</th>
					<td>
						<input type="text" id="nj" maxlength="4" name="nj"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="nj"/>">
					</td>
					<th>
							ѧ�ƣ�
					</th>
					<td>
						<input type="text" id="xz" name="xz" maxlength="4"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="xz"/>">
					</td>
				</tr>
				<tr>
					<th>
							<bean:message key="lable.xsgzyxpzxy" />���룺
					</th>
					<td>
						<input type="text" id="xydm" maxlength="10" name="xydm"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="xydm"/>">
					</td>
					<th>
							<bean:message key="lable.xsgzyxpzxy" />���ƣ�
					</th>
					<td>
						<input type="text" id="xymc" name="xymc" maxlength="40"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="xymc"/>">
					</td>
				</tr>
				<tr>
					<th>
							רҵ���룺
					</th>
					<td>
						<input type="text" id="zydm" maxlength="10" name="zydm"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="zydm"/>">
					</td>
					<th>
							רҵ���ƣ�
					</th>
					<td>
						<input type="text" id="zymc" maxlength="40" name="zymc"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="zymc"/>">
					</td>
				</tr>
				<tr>
					<th>
							�༶���룺
					</th>
					<td>
						<input type="text" id="bjdm" maxlength="20" name="bjdm"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="bjdm"/>">
					</td>
					<th>
							������ţ�
					</th>
					<td>
						<input type="text" id="bjmc" name="bjmc" maxlength="40"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="bjmc"/>">
					</td>
				</tr>
				<tr>
					<th>
							ѧ����
					</th>
					<td>
						<input type="text" id="xl" name="xl" maxlength="20"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="xl"/>">
					</td>
					<th>
							�Ƿ��ҵ��
					</th>
					<td>
						<input type="text" id="sfby" name="sfby" maxlength="10"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="sfby"/>">
					</td>
				</tr>
				<tr>
					<th>
							��ѧ���ڣ�
					</th>
					<td>
						<input type="text" id="rxny" name="rxny" maxlength="20"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="rxny"/>">
					</td>
					<th>
							��ҵ���ڣ�
					</th>
					<td>
						<input type="text" id="byny" name="byny" maxlength="20"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="byny"/>">
					</td>
				</tr>
				<tr>
					<th>
							���壺
					</th>
					<td>
						<input type="text" id="mzmc" name="mzmc" maxlength="50"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="mzmc"/>">
					</td>
					<th>
							������ò��
					</th>
					<td>
						<input type="text" id="zzmmmc" name="zzmmmc" maxlength="50"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="zzmmmc"/>">
					</td>
				</tr>
				<tr>
					<th>
							���򾭼�״����
					</th>
					<td>
						<input type="text" id="qyjjzk" name="qyjjzk" maxlength="50"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="qyjjzk"/>">
					</td>
					<th>
							�Ƿ�Ϊ�ſ����е��ؾ���
					</th>
					<td>
						<input type="text" id="sfwfkyhddjm" name="sfwfkyhddjm" maxlength="10"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="sfwfkyhddjm"/>">
					</td>
				</tr>
				<tr>
					<th>
							��ͥ�¾����룺
					</th>
					<td>
						<input type="text" id="jtysr" name="jtysr" maxlength="6"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtysr"/>">
					</td>
					<th>
							��ͥ��ϸ��ַ��
					</th>
					<td>
						<input type="text" id="jtxxzz" name="jtxxzz" maxlength="200"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtxxzz"/>">
					</td>
				</tr>
				<tr>
					<th>
							��ͥ�ʱࣺ
					</th>
					<td>
						<input type="text" id="jtyb" maxlength="6" name="jtyb"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtyb"/>">
					</td>
					<th>
							��ͥ�绰��
					</th>
					<td>
						<input type="text" id="jtdh" name="jtdh" maxlength="20"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtdh"/>">
					</td>
				</tr>
				<tr>
					<th>
							����������
					</th>
					<td>
						<input type="text" id="fqxm" maxlength="40" name="fqxm"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="fqxm"/>">
					</td>
					<th>
							ĸ��������
					</th>
					<td>
						<input type="text" id="mqxm" name="mqxm" maxlength="40"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="mqxm"/>">
					</td>
				</tr>
				<tr>
					<th>
							����ְҵ��
					</th>
					<td>
						<input type="text" id="fqzy" maxlength="40" name="fqzy"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="fqzy"/>">
					</td>
					<th>
							ĸ��ְҵ��
					</th>
					<td>
						<input type="text" id="mqzy" name="mqzy" maxlength="40"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="mqzy"/>">
					</td>
				</tr>
				<tr>
					<th>
							�������֤�ţ�
					</th>
					<td>
						<input type="text" id="fqsfzh" maxlength="18" name="fqsfzh"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="fqsfzh"/>">
					</td>
					<th>
							ĸ�����֤�ţ�
					</th>
					<td>
						<input type="text" id="mqsfzh" name="mqsfzh" maxlength="18"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="mqsfzh"/>">
					</td>
				</tr>
				<tr>
					<th>
							����״����
					</th>
					<td>
						<input type="text" id="hyzk" name="hyzk" maxlength="10"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="hyzk"/>">
					</td>
					<th>
							��ż������
					</th>
					<td>
						<input type="text" id="pomc" name="pomc" maxlength="40"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="pomc"/>">
					</td>
				</tr>
				<tr>
					<th>
							��ż��ϵ�绰��
					</th>
					<td>
						<input type="text" id="polxdh" maxlength="20" name="polxdh"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="polxdh"/>">
					</td>
					<th>
							������λ��
					</th>
					<td>
						<input type="text" id="gzdw" name="gzdw" maxlength="50"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="gzdw"/>">
					</td>
				</tr>
				<tr>
					<th>
							��λ�绰��
					</th>
					<td>
						<input type="text" id="dwdh" maxlength="20" name="dwdh"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="dwdh"/>">
					</td>
					<th>
							�����������룺
					</th>
					<td>
						<input type="text" id="gzhysr" name="gzhysr" maxlength="10"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="gzhysr"/>">
					</td>
				</tr>
				<tr>
					<th>
							��λ��ַ��
					</th>
					<td>
						<input type="text" id="dwdz" maxlength="200" name="dwdz"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="dwdz"/>">
					</td>
					<th>
							��λ�ʱࣺ
					</th>
					<td>
						<input type="text" id="dwyb" name="dwyb" maxlength="6"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="dwyb"/>">
					</td>
				</tr>
				<tr>
					<th>
							EMAIL��QQ��
					</th>
					<td>
						<input type="text" id="emailqq" name="emailqq" maxlength="100"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="emailqq"/>">
					</td>
					<th>
							�ƶ��绰��
					</th>
					<td>
						<input type="text" id="yddh" maxlength="20" name="yddh"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="yddh"/>">
					</td>
				</tr>
				<tr>
					<th>
							��ϵ��������
					</th>
					<td>
						<input type="text" id="lxrxm" maxlength="40" name="lxrxm"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="lxrxm"/>">
					</td>
					<th>
							��ϵ�˳������ڣ�
					</th>
					<td>
						<input type="text" id="lxrcsrq" maxlength="20" name="lxrcsrq"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="lxrcsrq"/>">
					</td>
				</tr>
				<tr>
					<th>
							��ϵ���Ա�
					</th>
					<td>
						<input type="text" id="lxrxb" maxlength="10" name="lxrxb"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="lxrxb"/>">
					</td>
					<th>
							��ϵ����ϵ�绰��
					</th>
					<td>
						<input type="text" id="lxrlxdh" maxlength="20" name="lxrlxdh"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="lxrlxdh"/>">
					</td>
				</tr>
				<tr>
					<th>
							��ϵ�������˹�ϵ��
					</th>
					<td>
						<input type="text" id="lxrgx" name="lxrgx" maxlength="40"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="lxrgx"/>">
					</td>
					<th>
							��ϵ�˵�λ/��ַ��
					</th>
					<td>
						<input type="text" id="lxrdwdz" name="lxrdwdz" maxlength="200"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="lxrdwdz"/>">
					</td>
				</tr>
				<tr>
					<th>
							��ע��
					</th>
					<td colspan="3">
						<input type="text" id="bz" name="bz" maxlength="200"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="bz"/>">
					</td>
				</tr>
				<tr>
					<th>
							��ͬ��ţ�
					</th>
					<td>
						<input type="text" id="htbh" name="htbh" maxlength="20"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="htbh"/>">
					</td>
					<th>
							�����
					</th>
					<td>
						<input type="text" id="sqdkje" maxlength="20" name="sqdkje"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="sqdkje"/>">
					</td>
				</tr>
				<tr>
					<th>
							��������(��)��
					</th>
					<td>
						<input type="text" id="dkqxy" maxlength="10" name="dkqxy"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="dkqxy"/>">
					</td>
					<th>
							�������ޣ�
					</th>
					<td>
						<input type="text" id="dkqx" name="dkqx" maxlength="20"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="dkqx"/>">
					</td>
				</tr>
				<tr>
					<th>
							�������ʣ�
					</th>
					<td>
						<input type="text" id="dkll" name="dkll" maxlength="20"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="dkll"/>">
					</td>
					<th>
							�ͻ��ţ�
					</th>
					<td>
						<input type="text" id="khh" name="khh" maxlength="50"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="khh"/>">
					</td>
				</tr>
				<tr>
					<th rowspan="2">
							��һѧ��
					</th>
					<th>
							�ſ���
					</th>
					<th>
							�Ƿ�ſ�
					</th>
					<th>
							���ʱ��
					</th>
				</tr>
				<tr>
					<td>
						<div align="center">
							<input type="text" id="fk_xn1_je" name="fk_xn1_je" maxlength="20"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="fk_xn1_je"/>">
						</div>
					</td>
					<td>
						<div>
							<input type="text" id="fk_xn1_sffk" name="fk_xn1_sffk" maxlength="10"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="fk_xn1_sffk"/>">
						</div>
					</td>
					<td>
						<div>
							<input type="text" id="fk_xn1_tksj" name="fk_xn1_tksj" maxlength="20"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="fk_xn1_tksj"/>">
						</div>
					</td>
				</tr>
				<tr>
					<th rowspan="2">
							�ڶ�ѧ��
					</th>
					<th>
							�ſ���
					</th>
					<th>
							�Ƿ�ſ�
					</th>
					<th>
							���ʱ��
					</th>
				</tr>
				<tr>
					<td>
							<input type="text" id="fk_xn2_je" name="fk_xn2_je" maxlength="20"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="fk_xn2_je"/>">
					</td>
					<td>
						<div>
							<input type="text" id="fk_xn2_sffk" name="fk_xn2_sffk" maxlength="10"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="fk_xn2_sffk"/>">
						</div>
					</td>
					<td>
						<div>
							<input type="text" id="fk_xn2_tksj" name="fk_xn2_tksj" maxlength="20"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="fk_xn2_tksj"/>">
						</div>
					</td>
				</tr>
				<tr>
					<th rowspan="2">
							����ѧ��
					</th>
					<th>
							�ſ���
					</th>
					<th>
							�Ƿ�ſ�
					</th>
					<th>
							���ʱ��
					</th>
				</tr>
				<tr>
					<td>
							<input type="text" id="fk_xn3_je" name="fk_xn3_je" maxlength="20"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="fk_xn3_je"/>">
					</td>
					<td>
							<input type="text" id="fk_xn3_sffk" name="fk_xn3_sffk" maxlength="10"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="fk_xn3_sffk"/>">
					</td>
					<td>
							<input type="text" id="fk_xn3_tksj" name="fk_xn3_tksj" maxlength="20"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="fk_xn3_tksj"/>">
					</td>
				</tr>
				<tr>
					<th rowspan="2">
							����ѧ��
					</th>
					<th>
							�ſ���
					</th>
					<th>
							�Ƿ�ſ�
					</th>
					<th>
							���ʱ��
					</th>
				</tr>
				<tr>
					<td>
							<input type="text" id="fk_xn4_je" name="fk_xn4_je" maxlength="20"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="fk_xn4_je"/>">
					</td>
					<td>
							<input type="text" id="fk_xn4_sffk" name="fk_xn4_sffk" maxlength="10"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="fk_xn4_sffk"/>">
					</td>
					<td>
							<input type="text" id="fk_xn4_tksj" name="fk_xn4_tksj" maxlength="20"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="fk_xn4_tksj"/>">
					</td>
				</tr>
				<tr>
					<th rowspan="2">
							����ѧ��
					</th>
					<th>
							�ſ���
					</th>
					<th>
							�Ƿ�ſ�
					</th>
					<th>
							���ʱ��
					</th>
				</tr>
				<tr>
					<td>
							<input type="text" id="fk_xn5_je" name="fk_xn5_je" maxlength="20"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="fk_xn5_je"/>">
					</td>
					<td>
							<input type="text" id="fk_xn5_sffk" name="fk_xn5_sffk" maxlength="10"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="fk_xn5_sffk"/>">
					</td>
					<td>
							<input type="text" id="fk_xn5_tksj" name="fk_xn5_tksj" maxlength="20"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="fk_xn5_tksj"/>">
					</td>
				</tr>
				<tr>
					<th>
							�ſ��ܽ��
					</th>
					<td>
						<input type="text" id="fkzje" name="fkzje" maxlength="20"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="fkzje"/>">
					</td>
					<th>
							�������
					</th>
					<td>
						<input type="text" id="dkye" name="dkye" maxlength="20"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="dkye"/>">
					</td>
				</tr>
				<tr>
					<th>
							��ѧ��������ʱ��
					</th>
					<td>
						<input type="text" id="sqsj" name="sqsj" maxlength="20"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="sqsj"/>">
					</td>
					<th>
							����������������ϵʱ�䣺
					</th>
					<td>
						<input type="text" id="jkrzhyyhlxsj" name="jkrzhyyhlxsj" maxlength="20"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jkrzhyyhlxsj"/>">
					</td>
				</tr>
				<tr>
					<th>
							����˻��������
					</th>
					<td colspan="3">
						<input type="text" id="jkrhkqk" name="jkrhkqk" maxlength="200"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jkrhkqk"/>">
					</td>
				</tr>
				<tr>
					<th>
							�Ƿ�ǩ������Э�飺
					</th>
					<td>
						<input type="text" id="sfqdhkxy" name="sfqdhkxy" maxlength="10"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="sfqdhkxy"/>">
					</td>
					<th>
							����Э��ǩ��ʱ�䣺
					</th>
					<td>
						<input type="text" id="hkxyqssj" name="hkxyqssj" maxlength="10"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="hkxyqssj"/>">
					</td>
				</tr>
				<tr>
					<th>
							������ۺţ�
					</th>
					<td>
						<input type="text" id="hkczh" name="hkczh" maxlength="20"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="hkczh"/>">
					</td>
					<th>
							�����ʻ���
					</th>
					<td>
						<input type="text" id="hkzhye" name="hkzhye" maxlength="20"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="hkzhye"/>">
					</td>
				</tr>
				<tr>
					<th>
							������ֹʱ�䣺
					</th>
					<td>
						<input type="text" id="hkqzsj" name="hkqzsj" maxlength="40"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="hkqzsj"/>">
					</td>
					<th>
							���ʽ��
					</th>
					<td>
						<input type="text" id="hkfs" name="hkfs" maxlength="20"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="hkfs"/>">
					</td>
				</tr>
				<tr>
					<th>
							չ��ʱ�䣺
					</th>
					<td>
						<input type="text" id="zqsj" name="zqsj" maxlength="20"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="zqsj"/>">
					</td>
					<th>
							չ�����ޣ�
					</th>
					<td>
						<input type="text" id="zqqx" name="zqqx" maxlength="20"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="zqqx"/>">
					</td>
				</tr>
				<tr>
					<th>
							չ�����ʣ�
					</th>
					<td>
						<input type="text" id="zqlv" name="zqlv" maxlength="20"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="zqlv"/>">
					</td>
					<th>
							չ�ڽ�
					</th>
					<td>
						<input type="text" id="zqje" name="zqje" maxlength="20"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="zqje"/>">
					</td>
				</tr>
				<tr>
					<th>
							չ�����ڵ�λ��
					</th>
					<td colspan="3">
						<input type="text" id="zqszdw" name="zqszdw" maxlength="50"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="zqszdw"/>">
					</td>
				</tr>
				<tr>
					<th>
							�������Ƿ��Ϣ�ܶ
					</th>
					<td>
						<input type="text" id="jkrtqlxze" name="jkrtqlxze" maxlength="20"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jkrtqlxze"/>">
					</td>
					<th>
							�������Ƿ��Ϣʱ�䣺
					</th>
					<td>
						<input type="text" id="jkrtqlxsj" name="jkrtqlxsj" maxlength="20"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jkrtqlxsj"/>">
					</td>
				</tr>
				<tr>
					<th>
							�������Ƿ�����ܶ
					</th>
					<td>
						<input type="text" id="jkrtqbjze" name="jkrtqbjze" maxlength="20"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jkrtqbjze"/>">
					</td>
					<th>
							�������Ƿ����ʱ�䣺
					</th>
					<td>
						<input type="text" id="jkrtqbjsj" name="jkrtqbjsj" maxlength="20"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jkrtqbjsj"/>">
					</td>
				</tr>
				<tr>
					<th>
							�Ƿ���ǰ���
					</th>
					<td>
						<input type="text" id="sftqhk" name="sftqhk" maxlength="10"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="sftqhk"/>">
					</td>
					<th>
							��ǰ�����
					</th>
					<td>
						<input type="text" id="tqhkje" name="tqhkje" maxlength="10"
							style="width:100%;heigh:100%" 
							value="<bean:write name="rs" property="tqhkje"/>">
					</td>
				</tr>
				<tr>
					<th>
							���ѷ�ʽ��
					</th>
					<td>
						<input type="text" id="txfs" name="txfs" maxlength="50"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="txfs"/>">
					</td>
					<th>
							�����Ѵ�����
					</th>
					<td>
						<input type="text" id="ytxcs" name="ytxcs" maxlength="20"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="ytxcs"/>">
					</td>
				</tr>
				<tr>
					<th>
							���һ������ʱ�䣺
					</th>
					<td>
						<input type="text" id="zjyctxsj" name="zjyctxsj" maxlength="10"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="zjyctxsj"/>">
					</td>
					<th>
							�Ƿ���д����ȷ���飺
					</th>
					<td>
						<input type="text" id="sftxzlqrs" name="sftxzlqrs" maxlength="20"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="sftxzlqrs"/>">
					</td>
				</tr>
				</tbody>
			</table>
			</div>
		</html:form>
	</body>
</html>
