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
<html>
	<head>
		<title><bean:message key="lable.title" />
		</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta name="Copyright" content="������� zfsoft" />
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<link rel="icon" href="images/icon.ico" type="image/x-icon" />
		<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css" type="text/css" media="all" />	
		
		<object id="WebBrowser" width=0 height=0 classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
		<link id="csssDate" rel="stylesheet" rev="stylesheet" href="js/calendar.css" type="text/css" media="all" />
		<style media='print'>
			.noPrin{display:none;}
		</style>
		<script language="javascript" src="js/commanFunction.js"></script>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/xszzFunction.js"></script>
		<script language="javascript" src="js/String.js"></script>
		<script language="javascript" src="js/calendar.js"></script>
		<script language="javascript" src="js/calendar-zh.js"></script>
		<script language="javascript" src="js/calendar-setup.js"></script>
		<script type="text/javascript">
			function choicesqzk(name,value){
				var sqzkbox = document.getElementsByName(name);
				var sqzk = document.getElementById(value).value;
				switch(sqzk){
					case '������ɹ�':sqzkbox[0].checked='checked';break;
					case '����������':sqzkbox[1].checked='checked';break;
				}
			}
		</script>
	</head>
	
	<body>
		<div align="center">
			<p>
				<b><span style='font-size:18.0pt;line-height:150%;font-family:����'>������һְҵ����ѧԺ</span>
				</b>
			</p>
			<p style='line-height:150%'>
				<b><u><span style='font-size:18.0pt;line-height:150%;font-family:����'>${rs.xn}</span> </u> </b><b><span style='font-size:18.0pt;line-height:
150%;font-family:����'>ѧ�������ѧ������˱�</span>
				</b>
			</p>
			<table class="tbstyle" style="width: 90%">
				<tr>
					<td width="10%" align=center>
						�� ��
					</td>
					<td width="15%" align=center>
						${rs.xm }
					</td>
					<td width="8%" colspan=3 align=center>
						�Ա�
					</td>
					<td width="8%" colspan=2 align=center>
						${rs.xb }
					</td>
					<td width="12%" align=center>
						��������
					</td>
					<td width="12%" align=center>
						${rs.csrq }
					</td>
					<td width="14%" colspan=3 align=center>
						�꼶��רҵ���༶
					</td>
					<td width="19%" align=center>
						${rs.bjmc }
					</td>
				</tr>
				
				<tr>
					<td align=center align=center>
						ѧ��
					</td>
					<td align=center align=center>
						${rs.xh }
					</td>
					<td colspan=3 align=center>
						ѧ��
					</td>
					<td colspan=2 align=center>
						${rs.xz }
					</td>
					<td align=center>
						��ҵʱ��
					</td>
					<td align=center>
						${rs.byny }
					</td>
					<td colspan=3 align=center>
						���Һš����ҵ绰
					</td>
					<td align=center>
						${rs.ssbh}&nbsp;&nbsp;${rs.qsdh }
					</td>
				</tr>
				<tr>
					<td align=center>
						��ͥ��ַ
					</td>
					<td colspan=8 align=left>
						&nbsp;&nbsp;${rs.jtszd }
					</td>
					<td colspan=3 align=center>
						����ϵ��ͥ�绰
					</td>
					<td align=center>
						${rs.lxdh1 }
					</td>
				</tr>
				<tr>
					<td align=center>						
						��&nbsp;&nbsp;&nbsp;��
					</td>
					<td align=center>
						${rs.jtyb }
					</td>
					<td colspan=4 align=center>
						���֤��
					</td>
					<td colspan=3 align=center>
						${rs.sfzh }
					</td>
					<td colspan=3 align=center>
						���˵绰
					</td>
					<td align=center>
						${rs.lxdh }
					</td>
				</tr>
				<tr>
					<td align=center>
						��������
					</td>
					<td align=center>
						${rs.fqxm }
					</td>
					<td colspan=4 align=center>
						��λ
					</td>
					<td colspan=5 align=center>
						${rs.fqdw }
					</td>
					<td align=center width="9%">
						�����ֻ�
					</td>
					<td align=center>
						${rs.fqsj }
					</td>
				</tr>
				<tr>
					<td align=center>
						ĸ������
					</td>
					<td align=center>
						${rs.mqxm }
					</td>
					<td colspan=4 align=center>
						��λ
					</td>
					<td colspan=5 align=center>
						${rs.mqdw }
					</td>
					<td align=center>
						ĸ���ֻ�
					</td>
					<td align=center>
						${rs.mqsj }
					</td>
				</tr>
				<tr>
					<td align=center>
						��֤��
					</td>
					<td align=center>
						${rs.jzr1 }
					</td>
					<td colspan=4 align=center>
						���֤��
					</td>
					<td colspan=5 align="center">
						${rs.sfzh1 }
					</td>
					<td align=center>
						��֤��ǩ��
					</td>
					<td align="center">
						&nbsp;
					</td>
				</tr>
				<tr>
					<td align=center>
						��֤��
					</td>
					<td align=center>
						${rs.jzr2}
					</td>
					<td colspan=4 align=center>
						���֤��
					</td>
					<td colspan=5 align=center>
						${rs.sfzh2 }
					</td>
					<td align=center>
						��֤��ǩ��
					</td>
					<td align=center>
						&nbsp;
					</td>
				</tr>
				<tr>
					<td colspan=6 align=left>
						�Ͷ���ѧϰ����������ۣ�<br/>
						<input type="hidden" id="xxqkztpj" value="${rs.xxqkztpj }"/>
							�ţ�<input type="checkbox" name="ztpj" disabled="disabled" value="��"/>
							����<input type="checkbox" name="ztpj" disabled="disabled" value="��"/>
							һ�㣺<input type="checkbox" name="ztpj" disabled="disabled" value="һ��"/>
							�<input type="checkbox" name="ztpj" disabled="disabled" value="��"/>
							<script type="text/javascript">
								var pj = document.getElementsByName("ztpj");
								var xxqkztpj = $('xxqkztpj').value;
								switch(xxqkztpj){
									case '��':pj[0].checked='checked';break;
									case '��':pj[1].checked='checked';break;
									case 'һ��':pj[2].checked='checked';break;
									case '��':pj[3].checked='checked';break;
								}
						</script>				
					</td>
					<td colspan=3 align=center>
						�Ͷ��ڼ��ۼƲ�����<br/><br/>
						���޿�Ŀ��
					</td>
					<td width="10%" colspan=2 align=center>
						${rs.bjgbxkms }
					</td>
					<td align=center>
						����Υ��<br/><br/>����
					</td>
					<td align=center>
						${rs.ywwjcf }
					</td>
				</tr>
				<tr>
					<td colspan=2 align=center>
							��ѧ��Ʒ�µȵ�
					</td>
					<td colspan=4 align=center>
						${rs.sxnpddd }
					</td>
					<td colspan=3 align=center>
						���޲������ü�¼
					</td>
					<td colspan=4>
						<p align=center>
							<input type="hidden" id="ywblxyjl" value="${rs.ywblxyjl }"/>
							�У�<input type="checkbox" name="bljl" disabled="disabled" value="��"/>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							�ޣ�<input type="checkbox" name="bljl" disabled="disabled" value="��"/>
							<script type="text/javascript">
								var bljl = document.getElementsByName("bljl");
								var ywblxyjl = $('ywblxyjl').value;
								switch(ywblxyjl){
									case '��':bljl[0].checked='checked';break;
									case '��':bljl[1].checked='checked';break;
								}
							</script>			
						</p>
					</td>
				</tr>
				<tr>
					<td rowspan=3 align=center>
							������
							<br/><br/>
							����
							<br/><br/>
							�ܶ�
					</td>
					<td rowspan=3 align=center>
						&nbsp;${mxndkxx.dkze.dkze }
					</td>
					<td colspan=4 rowspan=3 align=center>
						��ѧ��<br/><br/>�����<br/><br/>����
					</td>
					<td colspan=3 align=center>
						��һѧ��<u>&nbsp;&nbsp;${mxndkxx.xn1.dkje }&nbsp;&nbsp;</u>Ԫ
					</td>
					<td colspan=4 align=center>
								<input type="hidden" id="xn1_sqzk" value="${mxndkxx.xn1.sqzk}"/>
								������ɹ���<input type="checkbox" name="xn1_box" disabled="disabled"/>
								
								���������У�<input type="checkbox" name="xn1_box" disabled="disabled"/>
								<script type="text/javascript">
									choicesqzk('xn1_box','xn1_sqzk');
								</script>
					</td>
				</tr>
				<tr>
					<td colspan=3 align=center>
							�ڶ�ѧ��<u>&nbsp;&nbsp;${mxndkxx.xn2.dkje }&nbsp;&nbsp;</u>Ԫ
					</td>
					<td colspan=4 align=center>
								<input type="hidden" id="xn2_sqzk" value="${mxndkxx.xn2.sqzk}"/>
								������ɹ���<input type="checkbox" name="xn2_box" disabled="disabled"/>
								
								���������У�<input type="checkbox" name="xn2_box" disabled="disabled"/>
								<script type="text/javascript">
									choicesqzk('xn2_box','xn2_sqzk');
								</script>
					</td>
				</tr>
				<tr>
					<td colspan=3 align=center>
						����ѧ��<u>&nbsp;&nbsp;${mxndkxx.xn3.dkje }&nbsp;&nbsp;</u>Ԫ
					</td>
					<td colspan=4 align=center>
								<input type="hidden" id="xn3_sqzk" value="${mxndkxx.xn3.sqzk}"/>
								������ɹ���<input type="checkbox" name="xn3_box" disabled="disabled"/>
					
								���������У�<input type="checkbox" name="xn3_box" disabled="disabled"/>
								<script type="text/javascript">
									choicesqzk('xn3_box','xn3_sqzk');
								</script>
					</td>
				</tr>
				<tr>
					<td colspan=13>
						<p align=left>
							<input type="hidden" id="hkfs" value="${rs.hkfs }"/>
 							���ʽ��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							 �ȶϢ���<input type="checkbox" name="hkfs_box" disabled="disabled"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							 �ȶ�𻹿<input type="checkbox" name="hkfs_box" disabled="disabled"/>
							 <script type="text/javascript">
								var hkfs_box = document.getElementsByName('hkfs_box');
								var hkfs = $('hkfs').value;
								switch(hkfs){
									case '�ȶϢ���':hkfs_box[0].checked='checked';break;
									case '�ȶ�𻹿':hkfs_box[1].checked='checked';break;
								}
							 </script>
						</p>
					</td>
				</tr>
				<tr>
					<td colspan=13 valign=top>
						<p>
							���������Ｐ��������
						</p>
						<p>&nbsp;&nbsp;${rs.fdypy }</p>
						<p align="right">
							<span>ǩ&nbsp;&nbsp;&nbsp;��:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							</span>
							<span>&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;</span>
						</p>
					</td>
				</tr>
				<tr>
					<td colspan=4 valign=top>
						<p>
							Ժϵ��������
						</p>
						<p>&nbsp;&nbsp;${rs.xyshyj }</p>
						<p align="right">
							����&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</p>
						<p align="right">
						<span>&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;</span>
						</p>
					</td>
					<td colspan=6 valign=top>
						<p>
							ѧ����������������������
						</p>
							<p>
								&nbsp;&nbsp;${rs.xxshyj }
							</p>
					<p align="right">
							����&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</p>
						<p align="right">
						<span>&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;</span>
						</p>
					</td>
					<td colspan=3 valign=top>
						<p>
							<span><bean:message key="lable.xb" />�������:</span>
						</p>
						<p>
							&nbsp;&nbsp;${rs.xxshyj }
						</p>
						<p align="right">
							����&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</p>
						<p align="right">
						<span>&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;</span>
						</p>
					</td>
				</tr>
			</table>
			<table width="90%" border="0">
				<tr>
				<td>
				<span>�������</span> 
				<span>��</span>
				<span style='font-family:����_GB2312'></span>
				<span lang=EN-US style='font-family:����_GB2312'>1.</span>
				<span style='font-family:����_GB2312;color:black'>��</span>
				<span style='font-family:����_GB2312'>�й��������й�����ѧ���������
				<span style='color:black'>��</span>��<span lang=EN-US>2.</span>
				<span style='color:black'>��</span>������һְҵ����<bean:message key="lable.xsgzyxpzxy" />������ѧ������˱�
				<span style='color:black'>��</span>��<span lang=EN-US>3.</span>
				<span style='color:black'>���ߵ�ѧУѧ������ͥ���������͡��ߵ�ѧУ��ͥ��������ѧ���϶�������ĸ�ӡ����
				<span lang=EN-US>4.</span>�����ٻ����ŵ�顷���������ں�ʵ����ʵ�Ժ�ע�����Ѻ�ʵ��ŵ��ȷΪ����˸�ĸ������ǩ�����ѵ绰��ϵ����˸�ĸ���丸ĸ��֪�����£���ŵ��ȷΪ����˸�ĸ��ǩ������ǩ��������
				<span lang=EN-US>5.</span>ѧ��֤�����֤�ͼ�ͥ���ڲ�<span lang=EN-US>(</span>�򻧼�֤��<span lang=EN-US>)</span>�ĸ�ӡ����
				<span lang=EN-US>6.</span>��ĸ���֤����ĸ���ڲ���ͬһ���ڲ�ʱ�����ṩ���֤���ĸ�ӡ����
				<span lang=EN-US> 7.</span>������֤�����֤������֤�ĸ�ӡ����
				<span lang=EN-US>8.</span>��������ѧͨ</span>֪��ĸ�ӡ
				<span style='color:black'>����</span>��<span style='color:black'>����</span>��У������һѧ��ѧϰ�ɼ������ɸ�<bean:message key="lable.xsgzyxpzxy" />ͳһ�ṩ����
				<span lang=EN-US>9.</span>����˿ۿ��ʻ���ӡ����</span><br/>					
				&nbsp;&nbsp;
				<span style='font-family:����_GB2312'>���в��Ͼ�ͳһʹ��<span lang=EN-US>A4</span>ֽ��֤��Ӧÿҳ����ӡ�����ڸ�ӡ���ϼӸǡ���ԭ���˶������¼��˶���ʦ�����£����ϰ�����˳�����С�װ����</span>
				</td>
				</tr>
			</table>
		</div>
		<div align="center" class='noPrin'>
			<button type="button" class='button2' onclick="WebBrowser.ExecWB(8,1);return false;">
				ҳ������
			</button>
			<button type="button" class='button2' onclick="WebBrowser.ExecWB(7,1);return false;">
				��ӡԤ��
			</button>
			<button type="button" class='button2' onclick="WebBrowser.ExecWB(6,6);return false;">
				ֱ�Ӵ�ӡ
			</button>
		</div>
	</body>
</html>
