<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript">
</script>
	<body onload="checkWinType();document.all('buttonClose').focus()">
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script language="javascript" src="js/String.js"></script>
		<script language="javascript">
		function yz(){
			 refreshForm('/xgxt/zgdzdx_xszz.do?method=jtqkdcshSave');
		}
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
		</script>
		<html:form action="/data_search" method="post">
			<div class="tab_cur">
				<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>
				������ - ��� - ��ͥ���������� - �������
				</a>
				</p>
			</div>
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
			<input type="hidden" name="pkVal" value="<bean:write name="pkVal"/>" />
			<input type="hidden" id="userType" name="userType"
				value="<bean:write name="userType" />" />
			<table width="98%" align="center" class="tbstyle">
			<div class="tab">
				<table width="100%"  border="0" class="formlist">
				<thead>
					<tr style="height:22px">
						<td colspan="12" align="center">
							��ͥ�������
						</td>
					</tr>
				</thead>

				<tr>
					<th  colspan="3">
						ѧ��
					</th>
					<td align="left" colspan="2">
						<bean:write name='rs' property="xh" />
						<input type="hidden" id="xh" name="xh"
							value="<bean:write name='rs' property="xh" />" />
					</td>
					<th width="16%">
							����
					</th>
					<td colspan="3">
						<bean:write name="rs" property="xm" />
					</td>
				</tr>
				<tr>
					<th colspan="3">
							�Ա�
					</th>
					<td colspan="2">
						<bean:write name="rs" property="xb"/>
					</td>
					<th>
							ѧ��
					</th>
					<td colspan="3">
						<bean:write name="rs" property="xn"/>
						<input type="hidden" id="xn" name="xn"
							value="<bean:write name='rs' property="xn" />" />
					</td>
				</tr>
				<tr>
					<th colspan="3">
							����
					</th>
					<td colspan="2">
						<bean:write name="rs" property="mzmc"/>
					</td>
					<th>
							������ò
					</th>
					<td colspan="3">
						<bean:write name="rs" property="zzmmmc"/>
					</td>
				</tr>
				<tr>
					<th colspan="3">
							�꼶
					</th>
					<td colspan="2">
						<bean:write name="rs" property="nj"/>
					</td>
					<th>
							<bean:message key="lable.xsgzyxpzxy" />����
					</th>
					<td colspan="3">
						<bean:write name="rs" property="xymc"/>
					</td>
				</tr>
				<tr>
					<th colspan="3">
							רҵ����
					</th>
					<td colspan="2">
						<bean:write name="rs" property="zymc"/>
					</td>
					<th>
							�༶����
					</th>
					<td colspan="3">
						<bean:write name="rs" property="bjmc"/>
					</td>
				</tr>
				<tr>
					<th colspan="3">
							���֤��
					</th>
					<td colspan="2">
						<bean:write name="rs" property="sfzh"/>
					</td>
					<th>
							��ѧǰ����
					</th>
					<td colspan="3">
						<bean:write name="rs" property="rxqhk"/>
					</td>
				</tr>
				<tr>
					<th colspan="3">
							��Դ
					</th>
					<td colspan="6">
						<bean:write name="rs" property="sy"/>
					</td>
				</tr>
				<tr>
					<th colspan="3">
							��ͥ�˿���
					</th>
					<td colspan="2">
						<bean:write name="rs" property="jtrks"/>
					</td>
					<th>
							�Ƿ�²�
					</th>
					<td colspan="3">
						<bean:write name="rs" property="sfgc"/>
					</td>
				</tr>
				<tr>
					<th colspan="3">
							�Ƿ���
					</th>
					<td colspan="2">
						<bean:write name="rs" property="sfdq"/>
					</td>
					<th>
							�Ƿ���ʿ��Ů
					</th>
					<td colspan="3">
						<bean:write name="rs" property="sflszn"/>
					</td>
				</tr>
				<tr>
					<th colspan="3">
							��ͥ��������
					</th>
					<td colspan="2">
						<bean:write name="rs" property="jt_yzbm"/>
					</td>
					<th>
							��ͥ��ϵ�绰
					</th>
					<td colspan="3">
						<bean:write name="rs" property="jt_lxdh"/>
					</td>
				</tr>
				<tr>
					<th colspan="3">
							��ͥ��ϸͨѶ��ַ
					</th>
					<td colspan="6">
						<bean:write name="rs" property="jt_xxtxdz"/>
					</td>
				</tr>
				<tr>
					<th width="4%" rowspan="6">
							��
							<br>
							ͥ
							<br>
							��
							<br>
							Ա
							<br>
							��
							<br>
							��
					</th>
					<th width="8%">
							����
					</td>
					<th width="8%">
							����
					</th>
					<th width="9%">
							��ѧ����ϵ
					</th>
					<th colspan="2">
							����(ѧϰ)��λ
					</th>
					<th width="12%">
							ְҵ
					</th>
					<th width="11%">
							������(Ԫ)
					</th>
					<th width="11%">
							����״��
					</th>
				</tr>
				<tr>
					<td width="6%">
							<bean:write name="rs" property="jtcy1_xm"/>
					</td>
					<td width="6%">
						<div align="center">
							<bean:write name="rs" property="jtcy1_nl"/>
						</div>
					</td>
					<td>
							<bean:write name="rs" property="jtcy1_gx"/>
					</td>
					<td colspan="2">
							<bean:write name="rs" property="jtcy1_gzdw"/>
					</td>
					<td>
							<bean:write name="rs" property="jtcy1_zy"/>
					</td>
					<td>
							&nbsp;<bean:write name="rs" property="jtcy1_sr"/>&nbsp;
					</td>
					<td>
							<bean:write name="rs" property="jtcy1_jkzk"/>
					</td>
				</tr>
				<tr>
					<td width="6%">
							<bean:write name="rs" property="jtcy2_xm"/>
					</td>
					<td width="6%">
							<bean:write name="rs" property="jtcy2_nl"/>
					</td>
					<td>
							<bean:write name="rs" property="jtcy2_gx"/>
					</td>
					<td colspan="2">
							<bean:write name="rs" property="jtcy2_gzdw"/>
					</td>
					<td>
							<bean:write name="rs" property="jtcy2_zy"/>
					</td>
					<td>
							<bean:write name="rs" property="jtcy2_sr"/>
					</td>
					<td>
							<bean:write name="rs" property="jtcy2_jkzk"/>
					</td>
				</tr>
				<tr>
					<td width="6%">
							<bean:write name="rs" property="jtcy3_xm"/>
					</td>
					<td width="6%">
							<bean:write name="rs" property="jtcy3_nl"/>
					</td>
					<td>
							<bean:write name="rs" property="jtcy3_gx"/>
					</td>
					<td colspan="2">
							<bean:write name="rs" property="jtcy3_gzdw"/>
					</td>
					<td>
							<bean:write name="rs" property="jtcy3_zy"/>
					</td>
					<td>
							<bean:write name="rs" property="jtcy3_sr"/>
					</td>
					<td>
							<bean:write name="rs" property="jtcy3_jkzk"/>
					</td>
				</tr>
				<tr>
					<td width="6%">
							<bean:write name="rs" property="jtcy4_xm"/>
					</td>
					<td width="6%">
							<bean:write name="rs" property="jtcy4_nl"/>
					</td>
					<td>
							<bean:write name="rs" property="jtcy4_gx"/>
					</td>
					<td colspan="2">
							<bean:write name="rs" property="jtcy4_gzdw"/>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy4_zy"/>
						</div>
					</td>
					<td>
							<bean:write name="rs" property="jtcy4_sr"/>
					</td>
					<td>
							<bean:write name="rs" property="jtcy4_jkzk"/>
					</td>
				</tr>
				<tr>
					<td width="6%">
							<bean:write name="rs" property="jtcy5_xm"/>
					</td>
					<td width="6%">
							<bean:write name="rs" property="jtcy5_nl"/>
					</td>
					<td>
							<bean:write name="rs" property="jtcy5_gx"/>
					</td>
					<td colspan="2">
							<bean:write name="rs" property="jtcy5_gzdw"/>
					</td>
					<td>
							<bean:write name="rs" property="jtcy5_zy"/>
					</td>
					<td>
							<bean:write name="rs" property="jtcy5_sr"/>
					</td>
					<td>
							<bean:write name="rs" property="jtcy5_jkzk"/>
					</td>
				</tr>
				<tr>
					<th colspan="3">
							�������֤��
					</th>
					<td colspan="2">
						<bean:write name="rs" property="fqsfzh"/>
					</td>
					<th>
							ĸ�����֤��
					</th>
					<td colspan="3">
						<bean:write name="rs" property="mqsfzh"/>
					</td>
				</tr>
				<tr>
					<th colspan="3">
						<div align="center">
							��ͥȫ������
						</div>
					</th>
					<td colspan="2">
						<bean:write name="rs" property="jtqnsr"/>
					</td>
					<th>
						<div align="center">
							�˾�������
						</div>
					</th>
					<td colspan="3">
						<bean:write name="rs" property="rjnsr"/>
					</td>
				</tr>
				<tr>
					<th colspan="9">
							��ͥƶ��ԭ��
					</th>
				</tr>
				<tr>
					<th colspan="3">
							ũ��ƶ������
					</th>
					<td colspan="2">
						<bean:write name="rs" property="sfncpkdq"/>
					</td>
					<th>
							�����¸ڼ�ͥ
					</th>
					<td colspan="3">
						<bean:write name="rs" property="sfczxgjt"/>
					</td>
				</tr>
				<tr>
					<th colspan="3">
							��ĸ�²м�
					</th>
					<td colspan="2">
						<bean:write name="rs" property="sffmxcj"/>
					</td>
					<th>
							���ش󼲲�
					</th>
					<td colspan="3">
						<bean:write name="rs" property="sfhzdjb"/>
					</td>
				</tr>
				<tr>
					<th colspan="3">
							���׼�ͥ
					</th>
					<td colspan="2">
						<bean:write name="rs" property="sfdqjt"/>
					</td>
					<th>
							�¶�
					</th>
					<td colspan="3">
						<bean:write name="rs" property="sfgr"/>
					</td>
				</tr>
				<tr>
					<th colspan="3">
							��Ȼ�ֺ�
					</th>
					<td colspan="2">
						<bean:write name="rs" property="sfzrzh"/>
					</td>
					<th>
							��ͥ�˿ڶ�
					</th>
					<td colspan="3">
						<bean:write name="rs" property="sfjtrkd"/>
					</td>
				</tr>
				<tr>
					<th colspan="3">
							����
					</th>
					<td colspan="6">
						<bean:write name="rs" property="qtnr"/>
					</td>
				</tr>
				<tr>
					<th colspan="3">
							ƶ��ԭ����ϸ˵��
					</th>
					<td colspan="6">
						<bean:write name="rs" property="pkyyxxsm"/>
					</td>
				</tr>
				<tr>
					<th colspan="3">
							����Ƿծ���
					</th>
					<td colspan="6">
						<bean:write name="rs" property="jzqzqk"/>
					</td>
				</tr>
				<tr>
					<th colspan="3">
							ѧ����ѧǰ�ѻ��������(���������)
					</th>
					<td colspan="6">
						<bean:write name="rs" property="xsrxqyhzzqk"/>
					</td>
				</tr>
				<tr>
					<th colspan="3">
							�������
					</th>
					<td colspan="6">
						<bean:write name="rs" property="qtqk"/>
					</td>
				</tr>
				<tr>
					<th colspan="3">
							����������������
					</th>
					<td colspan="2">
						<bean:write name="rs" property="mzbm_yzbm"/>
					</td>
					<th>
							����������ϵ�绰
					</th>
					<td colspan="3">
						<bean:write name="rs" property="mzbm_lxdh"/>
					</td>
				</tr>
				<tr>
					<th colspan="3">
							����������ϸͨѶ��ַ
					</th>
					<td colspan="6">
						<bean:write name="rs" property="mzbm_xxtxdz"/>
					</td>
				</tr>
				<tr>
					<th colspan="3">
							����ʱ��
					</th>
					<td colspan="2">
						<bean:write name="rs" property="sqsj" />
					</td>
					<th>
							��˽��
					</th>
					<td colspan="3">
						<html:select name="rs" property="sh">
							<html:options collection="chkList" property="en"
								labelProperty="cn" />
						</html:select>
					</td>
				</tr>
				</tbody>
				 <tfoot>
				      <tr>
				        <td colspan="12"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
				          <div class="btn">
							<button type="button"  onclick="yz()"  
								id="buttonSave">
								�� ��
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button type="button"   onclick="Close();window.dialogArguments.document.getElementById('search_go').click();"  
								id="buttonClose">
								�� ��
							</button>	           
				          </div>
				          </td>
				      </tr>
				    </tfoot>
			</table>
		</html:form>
	</body>
</html>
