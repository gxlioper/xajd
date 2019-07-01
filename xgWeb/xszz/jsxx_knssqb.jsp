<%@ page language="java" contentType="text/html;charset=GBK"%>
<jsp:directive.page import="java.util.ArrayList" />
<jsp:directive.page import="java.util.Iterator" />

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean"
	prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic"
	prefix="logic"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles"
	prefix="tiles"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-template"
	prefix="template"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested"
	prefix="nested"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<base target="_self">
<head>
	<title><bean:message key="lable.title" />
	</title>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<meta http-equiv="Pragma" http-equiv="no-cache" />
	<meta http-equiv="Cache-Control" http-equiv="no-cache, must-revalidate" />
	<meta http-equiv="Expires" http-equiv="0" />
	<meta name="Copyright" content="������� zfsoft" />
</head>
<%
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
%>
<link rel="icon" href="images/icon.ico" type="image/x-icon" />
<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
	type="text/css" media="all" />
<script language="javascript" src="js/function.js"></script>
<script language="javascript" src="js/xszzFunction.js"></script>
<body>
	<html:form action="zxdksq.do" method="post">
		<input type="hidden" name="tableName" id="tableName"
			value="<bean:write name="sqlb" />" />
		<p align="center" style="font-size:24px">
			<bean:write name="sqlb" />
			<br>
			��������ѧ������ͥ��������󶨱�
		</p>
		<table width="100%" border="1" class="tbstyle" id="theTable">
			<tr>
				<td colspan="9">
					<div align="center">
						ϵ
						<u>&nbsp; <bean:write name='rs' property="zymc" /> &nbsp;</u>&nbsp;
						�༶
						<u>&nbsp; <bean:write name='rs' property="bjmc" /> &nbsp;</u>&nbsp;
						����
						<u>&nbsp; <bean:write name='rs' property="xm" /> &nbsp;</u>&nbsp;
						�Ա�
						<u>&nbsp; <bean:write name='rs' property="xb" /> &nbsp;</u>&nbsp;
						��������
						<u>&nbsp; <bean:write name='rs' property="csrq" />
						</u>
					</div>
				</td>
			</tr>
			<tr>
				<td rowspan="5" scope="col" width="10%">
					<div align="center">
						ѧ�����˻������
					</div>
				</td>
				<td scope="col" width="8%">
					<div align="center">
						��ͥ��ַ
					</div>
				</td>
				<td colspan="4" scope="col">
					<div align="center">
						<bean:write name='rs' property="jtdz" />
					</div>
				</td>
				<td scope="col" width="10%">
					<div align="center">
						��ͥ�绰
					</div>
				</td>
				<td colspan="2" scope="col">
					<div align="center">
						<bean:write name='rs' property="jtdh" />
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						���֤��
					</div>
				</td>
				<td colspan="4">
					<div align="center">
						<bean:write name='rs' property="sfzh" />
					</div>
				</td>
				<td>
					<div align="center">
						<bean:message key="lable.xsgzyxpzxy" />ͳ��ѧ��
					</div>
				</td>
				<td colspan="2">
					<div align="center">
						<bean:write name='rs' property="xh" />
					</div>
				</td>
			</tr>
			<tr>
				<td width="8%">
					<div align="center">
						������ò
					</div>
				</td>
				<td width="10%">
					<div align="center">
						<bean:write name='rs' property="zzmm" />
					</div>
				</td>
				<td width="10%">
					<div align="center">
						ѧ���绰
					</div>
				</td>
				<td colspan="2">
					<div align="center">
						<bean:write name='rs' property="xsdh" />
					</div>
				</td>
				<td width="15%">
					<div align="center">
						��ѧǰ����
					</div>
				</td>
				<td colspan="2">
					<div align="center">
						<bean:write name='rs' property="rxqhk" />
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="8">
					<div align="left">
						�¶�:
						<bean:write name='rs' property="sfgr" />
						,&nbsp; �м�:
						<bean:write name='rs' property="sfcj" />
						,&nbsp; ����:
						<bean:write name='rs' property="sfdq" />
						,&nbsp; ��ʿ��Ů:
						<bean:write name='rs' property="sfjszn" />
						,&nbsp; ��������:
						<bean:write name='rs' property="sfssmz" />
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="8">
					<div align="left">
						�����뻧:
						<bean:write name='rs' property="sfwsrh" />
						,&nbsp; �ز���:
						<bean:write name='rs' property="sfzbh" />
						,&nbsp; �ͱ���:
						<bean:write name='rs' property="sfdbh" />
						,&nbsp; ��ĸ˫�¸�:
						<bean:write name='rs' property="sffmsxg" />
						,&nbsp; ��ũ��:
						<bean:write name='rs' property="sfcnh" />
						,&nbsp; ������:
						<bean:write name='rs' property="sfdsr" />
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="8">
					<div align="left">
						��ѧ�ɼ�:
						<u> <bean:write name='rs' property="rxcj" />
						</u> ��,&nbsp; ��ѧ�ڰ���������
						<u> <bean:write name='rs' property="sxqpm" />
						</u> ��,&nbsp; ���γ̳ɼ�ƽ����:
						<u> <bean:write name='rs' property="pjcj" />
						</u> ��,&nbsp; ���еǵ�:
						<u> <bean:write name='rs' property="cxdd" />
						</u> ��
					</div>
				</td>
			</tr>
			<tr>
				<td rowspan="6" scope="row" width="10%">
					<div align="center">
						��ͥ��Ա
					</div>
				</td>
				<td width="8%">
					<div align="center">
						����
					</div>
				</td>
				<td width="10%">
					<div align="center">
						��ν
					</div>
				</td>
				<td width="10%">
					<div align="center">
						����
					</div>
				</td>
				<td colspan="3">
					<div align="center">
						������λ��ְ��
					</div>
				</td>
				<td width="10%">
					<div align="center">
						������
					</div>
				</td>
				<td width="10%">
					<div align="center">
						����״��
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						<bean:write name='rs' property="jtcy1_xm" />
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name='rs' property="jtcy1_cw" />
					</div>
				</td>
				<td>
					<div align="center">
						&nbsp;
						<bean:write name='rs' property="jtcy1_nl" />
						&nbsp;
					</div>
				</td>
				<td colspan="3">
					<div align="center">
						<bean:write name='rs' property="jtcy1_gzdwjzw" />
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name='rs' property="jtcy1_nsr" />
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name='rs' property="jtcy1_jkzk" />
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						<bean:write name='rs' property="jtcy2_xm" />
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name='rs' property="jtcy2_cw" />
					</div>
				</td>
				<td>
					<div align="center">
						&nbsp;
						<bean:write name='rs' property="jtcy2_nl" />
						&nbsp;
					</div>
				</td>
				<td colspan="3">
					<div align="center">
						<bean:write name='rs' property="jtcy2_gzdwjzw" />
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name='rs' property="jtcy2_nsr" />
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name='rs' property="jtcy2_jkzk" />
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						<bean:write name='rs' property="jtcy3_xm" />
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name='rs' property="jtcy3_cw" />
					</div>
				</td>
				<td>
					<div align="center">
						&nbsp;
						<bean:write name='rs' property="jtcy3_nl" />
						&nbsp;
					</div>
				</td>
				<td colspan="3">
					<div align="center">
						<bean:write name='rs' property="jtcy3_gzdwjzw" />
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name='rs' property="jtcy3_nsr" />
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name='rs' property="jtcy3_jkzk" />
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						<bean:write name='rs' property="jtcy4_xm" />
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name='rs' property="jtcy4_cw" />
					</div>
				</td>
				<td>
					<div align="center">
						&nbsp;
						<bean:write name='rs' property="jtcy4_nl" />
						&nbsp;
					</div>
				</td>
				<td colspan="3">
					<div align="center">
						<bean:write name='rs' property="jtcy4_gzdwjzw" />
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name='rs' property="jtcy4_nsr" />
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name='rs' property="jtcy4_jkzk" />
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						<bean:write name='rs' property="jtcy5_xm" />
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name='rs' property="jtcy5_cw" />
					</div>
				</td>
				<td>
					<div align="center">
						&nbsp;
						<bean:write name='rs' property="jtcy5_nl" />
						&nbsp;
					</div>
				</td>
				<td colspan="3">
					<div align="center">
						<bean:write name='rs' property="jtcy5_gzdwjzw" />
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name='rs' property="jtcy5_nsr" />
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name='rs' property="jtcy5_jkzk" />
					</div>
				</td>
			</tr>
			<tr>
				<td scope="row">
					<div align="center">
						��ͥ��������״��(����,�����¹�,��ĸ����,Ƿծ,ʧҵ�¸�,�ͱ�)
					</div>
				</td>
				<td colspan="8">
					<bean:write name='rs' property="jtjjknqk" />
				</td>
			</tr>
			<tr>
				<td rowspan="2" scope="row">
					<div align="center">
						�й�����������Ϣ
					</div>
				</td>
				<td colspan="8">
					<div align="left">
						ѧ����ͥ���������������(����):
					</div>
					<br />
					<div align="right">
						��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						��������
					</div>
				</td>
				<td colspan="2">
					<div align="center">
						<bean:write name='rs' property="mzbm_yzbm" />
					</div>
				</td>
				<td width="10%">
					<div align="center">
						��ϵ�绰
					</div>
				</td>
				<td colspan="2">
					<div align="center">
						<bean:write name='rs' property="mzbm_lxdh" />
					</div>
				</td>
				<td>
					<div align="center">
						��ϵ��
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name='rs' property="mzbm_lxr" />
					</div>
				</td>
			</tr>
			<tr>
				<td rowspan="3" scope="row">
					<div align="center">
						�ɷ����
					</div>
				</td>
				<td colspan="8">
					<div align="left">
						ÿ�걾�˺ͼ�ͥ�ṩ����
						<u> <bean:write name='rs' property="jfqk_jttg" />
						</u> Ԫ,&nbsp; ���������ṩ
						<u> <bean:write name='rs' property="jfqk_qtqytg" />
						</u> Ԫ,&nbsp; �ϼ�
						<u> <bean:write name='rs' property="jfqk_hjtg" />
						</u> Ԫ
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="8">
					<div align="left">
						ÿ��Ӧ�ɸ��ַ���
						<u> <bean:write name='rs' property="jfqk_yjfy" />
						</u> Ԫ,&nbsp; û�������
						<u> <bean:write name='rs' property="jfqk_mysffy" />
						</u> Ԫ,&nbsp; ȫ��ϼƷ���:
						<u> <bean:write name='rs' property="jfqk_qnhjfy" />
						</u> Ԫ
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="8">
					<div align="left">
						��ѧ���ͥ�ṩ���ò�����:
						<u> <bean:write name='rs' property="jfqk_bxnjttgfybzs" />
						</u> Ԫ,&nbsp; �ۼ�Ƿ��:
						<u> <bean:write name='rs' property="jfqk_ljqf" />
						</u> Ԫ,&nbsp; ���޻��ɼƻ�:
						<u> <bean:write name='rs' property="jfqk_ywhjjh" />
						</u>
					</div>
				</td>
			</tr>
			<tr>
				<td rowspan="5" scope="row">
					<div align="center">
						�������
					</div>
				</td>
				<td colspan="8">
					<div align="left">
						У���ڹ���ѧ:
						<u> <bean:write name='rs' property="zzqk_sfsqxnqgzx" />
						</u> ,&nbsp; ��λ:
						<u> <bean:write name='rs' property="zzqk_xnqgzxyapgw" />
						</u> ��&nbsp; У���ڹ���ѧ:
						<u> <bean:write name='rs' property="zzqk_sfsqxwqgzx" />
						</u>
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="8">
					<div align="left">
						��ѧ��ũ����ѧ����:
						<u> <bean:write name='rs' property="zzqk_sfsbnczxdk" />
						</u> ,&nbsp; �����ѻ�ũ����ѧ�����ۼ�
						<u> <bean:write name='rs' property="zzqk_lnyhnczxdhje" />
						</u>
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="8">
					<div align="left">
						��ѧ���У��ѧ����:
						<u> <bean:write name='rs' property="zzqk_sfsbgxzxdk" />
						</u> ,&nbsp; �����ѻ��У��ѧ�����ۼ�
						<u> <bean:write name='rs' property="zzqk_lnyhgxzxdhje" />
						</u>
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="8">
					<div align="left">
						��ѧ�걣����ѧ����:
						<u> <bean:write name='rs' property="zzqk_sfsbbxzxdk" />
						</u> ,&nbsp; �����ѻ�����ѧ�����ۼ�
						<u> <bean:write name='rs' property="zzqk_lnyhbxzxdhje" />
						</u>
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="8">
					<div align="left">
						�����ѻ񽱡���ѧ����������:
					</div>
					<logic:equal name="notJzxj" value="is">
						<div align="center">
							��
						</div>
					</logic:equal>
					<logic:equal name="notJzxj" value="no">
						<%
							@SuppressWarnings("unchecked")
								ArrayList<String> list = (ArrayList<String>) request
										.getAttribute("xszzList");
								for (Iterator<String> it = list.iterator(); it
										.hasNext();) {
									String temp = it.next();
						%>
								&nbsp;&nbsp;&nbsp;&nbsp;<%=temp%>
						<br />
						<%
						}
						%>
					</logic:equal>
				</td>
			</tr>
			<tr>
				<td rowspan="2" scope="row">
					<div align="center">
						�༶������������
					</div>
				</td>
				<td colspan="8">
					<div align="left">
						������ʲôΥ�Ͷ���ѧУʲô�ȼ���������:��
						������(&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						)
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="8">
					<div align="left">
						����ѧ����ͥ���ڵ�����(�ְ�)�������ų��ߵļ�ͥ��������֤��:�� ��(Ӧ�渶��ӡ��) �� ��
					</div>
					<div align="left">
						����ˮƽ:������ ������ �����ܵ� �����༶���顢�����β�����:
					</div>
					<br />
					<div align="right">
						�϶�Ϊ:������ ��,һ������ ��,�ر����� ��
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ������ǩ��
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</div>
				</td>
			</tr>
			<tr>
				<td scope="row">
					<div align="center">
						<bean:message key="lable.xsgzyxpzxy" />���
					</div>
				</td>
				<td colspan="8">
					<bean:write name='rs' property="xyshyj" />
					<br />
					<div align="right">
						�϶�Ϊ:������ ��,һ������ ��,�ر����� ��
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ������ǩ��
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</div>
				</td>
			</tr>
			<tr>
				<td height="17" scope="row">
					<div align="center">
						ѧУ���
					</div>
				</td>
				<td colspan="8">
					<bean:write name='rs' property="xxshyj" />
					<br />
					<p align="right">
						�϶�Ϊ:������ ��,һ������ ��,�ر����� ��
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ������ǩ��
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</p>
				</td>
			</tr>
			<tr>
				<td colspan="9">
					<div align="left">
						�˱���춿�ѧ����ʵ��дһʽ����,ϵ����ѧ������һ��
					</div>
					<div align="right">
						�������:&nbsp;&nbsp;
						<bean:write name='rs' property="sqsj" />
						&nbsp;&nbsp;&nbsp;&nbsp;
					</div>
				</td>
			</tr>
		</table>
	</html:form>
	<div align=center>
		<input  value="��ӡ" name="button_print"
			onclick="expTab('theTable','������Ϣְҵ����<bean:message key="lable.xsgzyxpzxy" />��������ѧ������ͥ��������󶨱�')" />
	</div>
</body>
</html:html>
