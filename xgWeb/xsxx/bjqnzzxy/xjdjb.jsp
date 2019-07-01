<%@ page language="java" contentType="text/html; charset=GBK"%>

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
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
	<head>
		<title><bean:message key="lable.title" /></title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control" http-equiv="no-cache" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="������� zfsoft" />
		<link rel="icon" href="images/icon.ico" type="image/x-icon" />
		<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css" type="text/css" media="all" />
		<base target="_self">
		<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
		<script type="text/javascript" src="js/stuinfoFunction.js"></script>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/xgutil.js"></script>
		<style media="print">
			.noprint{
				display:none
			}
			.print{
				display:block
			}
		</style>
		<object id="WebBrowser" width=0 height=0 classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
	</head>
		<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
		%>
	<body>	
		<center>
			<html:form action="/xsxxdj" method="post">
				<p align="center">&nbsp;&nbsp;</p>
				<p align="center"><h3 >${xxmc}ѧ����Ϣ�ǼǱ� </h3></p>
				<table width="95%" height="524" cellpadding="0" cellspacing="0" class="tbstyle">
					<tr>
						<td width="5%"></td><td width="5%"></td><td width="6%"></td><td  width="5%"></td><td  width="12%"></td><td  width="6%"></td><td   width="6%"></td><td  width="15%"></td><td width="4%"></td><td width="4%"></td><td width="4%"></td><td width="3%"></td><td width="3%"></td><td width="3%"></td><td width="3%"></td><td width="12%"></td><td  width="5%"></td><td width="4%"></td>
					</tr>
				  <tr>
				    <td colspan="2"><p align="center">�༶���� </p></td>
				    <td colspan="3"><p align="center">${stuRs.bjmc} </p></td>
				    <td colspan="2"><p align="center">ѧ�� </p></td>
				    <td ><p align="center">${stuRs.xh} </p></td>
				    <td colspan="3"><p align="center">���� </p></td>
				    <td colspan="5"><p align="center">${stuRs.xm} </p></td>
				    <td colspan="2" rowspan="7">
					<p align="center"><img border="0" style="height:133px;width:100px;"
													src="<%=request.getContextPath()%>/stuPic.jsp?xh=<bean:write name="rs" property="xh" />"> </p></td>
				  </tr>
				  <tr>
				    <td colspan="2"><p align="center">�Ա� </p></td>
				    <td colspan="3"><p align="center">${stuRs.xb} </p></td>
				    <td colspan="2"><p align="center">���� </p></td>
				    <td ><p align="center">${stuRs.mzmc}</p></td>
				    <td colspan="3"><p align="center">���֤���� </p></td>
				    <td colspan="5"><p align="center">${stuRs.sfzh}</p></td>
				  </tr>
				  <tr>
				    <td colspan="2"><p align="center">�������� </p></td>
				    <td colspan="3"><p align="center">${stuRs.csrq} </p></td>
				    <td colspan="2"><p align="center">������ </p></td>
				    <td ><p align="center">${stuRs.cym} </p></td>
				    <td colspan="3"><p align="center">���� </p></td>
				    <td colspan="5"><p align="center">${stuRs.jg} </p></td>
				  </tr>
				  <tr>
				    <td colspan="2"><p align="center">������ </p></td>
				    <td colspan="3"><p align="center">${rs.csd} </p></td>
				    <td colspan="2"><p align="center">Ѫ�� </p></td>
				    <td><p align="center">${rs.xuex} </p></td>
				    <td colspan="3"><p align="center">��� </p></td>
				    <td colspan="5"><p align="center">${stuRs.sg} </p></td>
				  </tr>
				  <tr>
				    <td colspan="2" rowspan="2"><p align="center">������ò </p></td>
				    <td>��Ա</td>
				    <td colspan="2">
					<p>
					<logic:equal value="��" name="rs" property="sfsty">
					��<input type="checkbox" value="��" checked="checked">
					��<input type="checkbox" value="��">
				    </logic:equal>
				    <logic:equal value="��" name="rs" property="sfsty">
					��<input type="checkbox" value="��">
					��<input type="checkbox" value="��" checked="checked">
				    </logic:equal>
					<logic:empty name="rs" property="sfsty">
				            �� �� ��� 
                   </logic:empty> </p></td>
				    <td colspan="2"><p align="center">����ʱ�� </p></td>
				    <td><p align="center">${rs.jrgqtsj} </p></td>
				    <td colspan="3" rowspan="2"><p align="center">����״�� </p></td>
				    <td colspan="4"><p>��ѧǰ </p></td>
				    <td><p align="center">${rs.rxqjkzk} </p></td>
				  </tr>
				  <tr>
				    <td colspan="5"><p align="center">���ż�תԤʱ�� </p></td>
				    <td ><p align="center">${rs.tytysj} ${rs.zybdysj} </p></td>
				    <td colspan="4"><p>��ѧ�� </p></td>
				    <td><p align="center">${rs.rxhjkzk } </p></td>
				  </tr>
				  <tr>
				    <td colspan="2"><p align="center">�ֻ����� </p></td>
				    <td colspan="3"><p align="center">${stuRs.sjhm}</p></td>
				    <td colspan="2"><p align="center">������ʷ </p></td>
				    <td colspan="9"><p align="center">${rs.ywbs} </p></td>
				  </tr>
				  <tr>
				    <td colspan="2"><p align="center">�ֻ��̺� </p></td>
				    <td colspan="3"><p align="center">${rs.sjdh} </p></td>
				    <td colspan="2"><p align="center">QQ ���� </p></td>
				    <td ><p align="center">${rs.qqhm} </p></td>
				    <td colspan="4"><p align="center">�ڽ����� </p></td>
				    <td colspan="4"><p align="center">${rs.zjxy}</p></td>
				    <td><p>e-mail </p></td>
				    <td><p>${stuRs.dzyx} </p></td>
				  </tr>
				  <tr>
				    <td colspan="2"><p align="center">��ͥ��ַ </p></td>
				    <td colspan="6"><p align="center">${jtxxRs.jtdz} </p></td>
				    <td colspan="4"><p align="center">ѧ�����˽��п��� </p></td>
				    <td colspan="6"><p align="center">${stuRs.yhkh}</p></td>
				  </tr>
				  <tr>
				    <td colspan="2"><p align="center">ͨѶ��ַ </p></td>
				    <td colspan="6"><p align="center">${rs.txdz} </p></td>
				    <td colspan="4"><p align="center">��ͥ�绰���������� </p></td>
				    <td colspan="6"><p align="center">${jtxxRs.lxdh1};${jtxxRs.jtyb} </p></td>
				  </tr>
				  <tr>
				    <td colspan="2"><p align="center">���Һ� </p></td>
				    <td colspan="6"><p align="center">${stuRs.ssbh} </p></td>
				    <td colspan="4"><p align="center">���ҵ绰 </p></td>
				    <td colspan="6"><p align="center">${stuRs.qsdh} </p></td>
				  </tr>
				  <tr>
				    <td colspan="2"><p align="center">ʵϰҽԺ </p></td>
				    <td colspan="6"><p align="center">${rs.sxyy} </p></td>
				    <td colspan="4"><p align="center">ʵϰס�޵�ַ </p></td>
				    <td colspan="6"><p align="center">${rs.sxzsdz} </p></td>
				  </tr>
				  <tr>
				    <td colspan="2"><p align="center">�س� </p></td>
				    <td colspan="16"><p align="center">${stuRs.tc} </p></td>
				  </tr>
				  <tr>
				    <td colspan="2"><p align="center">רҵ����֤�� </p></td>
				    <td colspan="7"><p align="center">${rs.zyjnzs}</p></td>
				    <td colspan="2"><p align="center">����Ǩ�� </p></td>
				    <td colspan="7">
					<p>
					<logic:equal value="��" name="rs" property="hksfqr">
					��<input type="checkbox" value="��" checked="checked">
					��<input type="checkbox" value="��">
				    </logic:equal>
				    <logic:equal value="��" name="rs" property="hksfqr">
					��<input type="checkbox" value="��">
					��<input type="checkbox" value="��" checked="checked">
				    </logic:equal>
					<logic:empty name="rs" property="hksfqr">
				            �� �� ��� 
                   </logic:empty>
                   </p></td>
				  </tr>
				  <tr>
				    <td colspan="2"><p align="center">������� </p></td>
				    <td colspan="16"><p align="center">${rs.jcqk} </p></td>
				  </tr>
				  <tr>
				    <td rowspan="6"><p align="center">��<br>ͥ<br>��<br>Ҫ<br>��<br>Ա </p></td>
				    <td colspan="3"><p align="center">���� </p></td>
				    <td colspan="2"><p align="center">�ƺ� </p></td>
				    <td colspan="8"><p align="center">������λ </p></td>
				    <td colspan="4"><p align="center">��ϵ�绰 </p></td>
				  </tr>
				  <tr height="30px">
				    <td colspan="3"><p align="center">${jtxxRs.jtcy1_xm}</p></td>
				    <td colspan="2"><p align="center">${jtxxRs.jtcy1_gx}</p></td>
				    <td colspan="8"><p align="center">${jtxxRs.jtcy1_gzdz} </p></td>
				    <td colspan="4"><p align="center">${jtxxRs.jtcy1_lxdh1}</p></td>
				  </tr>
				  <tr height="30px">
				    <td colspan="3"><p align="center">${jtxxRs.jtcy2_xm}</p></td>
				    <td colspan="2"><p align="center">${jtxxRs.jtcy2_gx}</p></td>
				    <td colspan="8"><p align="center">${jtxxRs.jtcy2_gzdz}</p></td>
				    <td colspan="4"><p align="center">${jtxxRs.jtcy2_lxdh1}</p></td>
				  </tr>
				  <tr height="30px">
				    <td colspan="3"><p align="center">${jtxxRs.jtcy3_xm}</p></td>
				    <td colspan="2"><p align="center">${jtxxRs.jtcy3_gx}</p></td>
				    <td colspan="8"><p align="center">${jtxxRs.jtcy3_gzdz}</p></td>
				    <td colspan="4"><p align="center">${jtxxRs.jtcy3_lxdh1}</p></td>
				  </tr>	
				  <tr height="30px">
				    <td colspan="3"><p align="center"></p></td>
				    <td colspan="2"><p align="center"></p></td>
				    <td colspan="8"><p align="center"></p></td>
				    <td colspan="4"><p align="center"></p></td>
				  </tr>	
				   <tr height="30px">
				    <td colspan="3"><p align="center"></p></td>
				    <td colspan="2"><p align="center"></p></td>
				    <td colspan="8"><p align="center"></p></td>
				    <td colspan="4"><p align="center"></p></td>
				  </tr>				  
				  <tr>
				    <td rowspan="${xxjlSize+1}"><p align="center">��<br>Ҫ<br>ѧ<br>ϰ<br>��<br>��</p></td>
				    <td colspan="3"><p align="center">��ʼʱ�� </p></td>
				    <td colspan="2"><p align="center">����ʱ�� </p></td>
				    <td colspan="3"><p align="center">����ѧУ </p></td>
				    <td colspan="5"><p align="center">����ְ�� </p></td>
				    <td colspan="4"><p align="center">֤���� </p></td>
				  </tr>
				<logic:iterate id="v" name="xxjl">
				  <tr>
				    <td colspan="3"><p align="center">${v.kssj} </p></td>
				    <td colspan="2"><p align="center">${v.jssj} </p></td>
				    <td colspan="3"><p align="center">${v.szdw} </p></td>
				    <td colspan="5"><p align="center">${v.drzw}</p></td>
				    <td colspan="4"><p align="center">${v.zmr}</p></td>
				  </tr>
				</logic:iterate>
				</table>
				<p align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;���ҽ��� </p>
				<p align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${rs.zwjs} </p>
				<p align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;����������������Ϣ��ʵ��Ч���ش������� </p>
				<p align="right">����ǩ����<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u></p>
				<p align="right">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
							</html:form>
				</center>
		<div class="noprint" align="center">
			<input type='button' class='button2' value='ҳ������'
				onclick="WebBrowser.ExecWB(8,1);return false;">
			<input type='button' class='button2' value='��ӡԤ��'
				onclick="WebBrowser.ExecWB(7,1);return false;">
			<input type='button' class='button2' value='ֱ�Ӵ�ӡ'
				onclick="WebBrowser.ExecWB(6,6);return false;">
		</div>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>
