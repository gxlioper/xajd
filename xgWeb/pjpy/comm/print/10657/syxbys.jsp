<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead_V4.ini"%>
<!-- ͷ�ļ� -->
<html:html>
<head>
	<!-- ��ӡ�ؼ�begin -->
	<object id="WebBrowser" width=0 height=0
		classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
	<style media='print'>
			.noPrin{display:none;}
		</style>
	<!-- end -->
	<style>
		.radic {
		position:relative;
		}
		.radic em {
		position:absolute;
		top:3px; 
		left:-4px;
		font-family:Arial;
		font-size:22px;
		}
	</style>
	<script type="text/javascript">
		function dyConfirm(){
			var ie8 = jQuery.browser.msie && jQuery.browser.version=='8.0';
			if (ie8){
				return true;
			} else {
				return confirm('��ʹ�õ��������IE8�����ܻ�Ӱ�쵽��ӡЧ����ȷ��Ҫ������?');
			}
		}
	</script>	
</head>
<body>
	<center>
		<br />
		<br />
		<p align=center>
			<b><span
				style='font-size:21px;font-family:����;font-weight:normal;'>
					${rs.pjnd }�����ʡ��ͨ��У�����ѧ��ҵ�������� </span> </b>
		</p>
		<br />

		<p style="width:98%;" align="right">
			�ʱ�䣺 &nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��
		</p>
		<table class="printtab" style="width:98%">
			<tr>
				<td width="60" >
					<p align="center" >
						����
					</p>
				</td>
				<td width="84" align="center">
					${rs.xm }
				</td>
				<td width="60">
					<p align="center">
						�Ա�
					</p>
				</td>
				<td width="48" align="center">
					${rs.xb }
				</td>
				<td width="60">
					<p align="center">
						����
					</p>
					<p align="center">
						����
					</p>
				</td>
				<td width="96" align="center">
					${rs.csrq }
				</td>
				<td width="60">
					<p align="center">
						����
					</p>
				</td>
				<td width="60" align="center">
					${rs.mzmc }
				</td>
				<td width="120" rowspan="3" style="padding:0px 0px 0px 0px">
					<img
							src="<%=request.getContextPath()%>/sjcz/xszptp.jsp?xh=${rs.xh }"
							border="0" align="absmiddle" style="width:120px;height:160px" />
				</td>
			</tr>
			<tr>
				<td width="60">
					<p align="center">
						����
					</p>
					<p align="center">
						��ò
					</p>
				</td>
				<td width="84" align="center">
					${rs.zzmmmc }
				</td>
				<td width="108" colspan="2">
					<p align="center">
						�������
					</p>
					<p align="center">
						����ְ��
					</p>
				</td>
				<td width="156" colspan="2" align="center">
					${rs.xszw }
				</td>
				<td width="60">
					<p align="center">
						ѧ��
					</p>
				</td>
				<td width="60" align="center">
					${rs.xlmc }
				</td>
			</tr>
			<tr>
				<td width="60">
					<p align="center">
						��ҵ
					</p>
					<p align="center">
						ԺУ
					</p>
				</td>
				<td width="192" colspan="3" align="center">
					${xxmc }
				</td>
				<td width="60">
					<p align="center">
						רҵ
					</p>
					<p align="center">
						����
					</p>
				</td>
				<td width="216" colspan="3" align="center">
					${rs.zymc }
				</td>
			</tr>
			<tr>
				<td width="648" colspan="9" valign="top">
					<p align="left">
						���˼�����
					</p>
					<p style="height:175px">
						&nbsp;&nbsp;&nbsp;&nbsp;${rs.���˼��� }
					</p>
				</td>
			</tr>
			<tr>
				<td width="648" colspan="9" valign="top">
					<p align="left">
						����� :
					</p>
					<p style="height:170px">
						&nbsp;&nbsp;&nbsp;&nbsp;${rs.����� }
					</p>
				</td>
			</tr>
			<tr>
				<td width="648" colspan="9" valign="top">
					<p align="left">
						��Ҫ�¼������������ϣ�
					</p>
					<p style="height:250px">
						&nbsp;&nbsp;&nbsp;&nbsp;${rs.��Ҫ�¼� }
					</p>
				</td>
			</tr>
		</table>
		<p>
			&nbsp;
		</p>
		<p>
			&nbsp;
		</p>
		<p>
			&nbsp;
		</p>
		<p>
			&nbsp;
		</p>
		<table class="printtab" style="width:98%">
			<tr>
				<td width="104">
					<p align="center" >
						��<br/>��<br/>��<br/>��<br/>��<br/>��
					</p>
				</td>
				<td width="544" valign="top">
					<p style="height:140px">
						&nbsp;&nbsp;&nbsp;&nbsp;
					</p>
					<p align="right">
						����Ա�������ǩ����&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
						��&nbsp;&nbsp;&nbsp;&nbsp; ��&nbsp;&nbsp;&nbsp;&nbsp; ��&nbsp;&nbsp;&nbsp;&nbsp;
					</p>
				</td>
			</tr>
			<tr>
				<td width="74">
					<p align="center" style="writing-mode: tb-rl">
						Ժ ( ϵ ) ��ѡ���
					</p>
				</td>
				<td width="544" valign="top">
					<p style="height:140px">
						&nbsp;&nbsp;&nbsp;&nbsp;${rs.xyyj }
					</p>
					<p align="right">
						�����£�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</p>
					<p align="right">
						&nbsp;&nbsp;${rs.xynian}&nbsp;&nbsp; �� &nbsp;&nbsp;${rs.xyyue}&nbsp;&nbsp;�� &nbsp;&nbsp;${rs.xyri}&nbsp;&nbsp;��
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</p>
				</td>
			</tr>
			<tr>
				<td width="104">
					<p align="center">
						ѧ<br/>У<br/>��<br/>��<br/>��<br/>��
					</p>
				</td>
				<td width="544" valign="top">
					<p style="20px">
						&nbsp;
					</p>
					<p style="height:90px">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�����󣬲���У�ڹ�ʾ �������գ������飬�ֱ�����׼��ͬѧΪ ${rs.pjnd } �����ʡ�����ѧ��ҵ����
					</p>
					<p style="height:20px">
						&nbsp;
					</p>
					<p align="right">
						�����£�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</p>
					<p align="right">
						&nbsp;&nbsp;${rs.xxnian}&nbsp;&nbsp; �� &nbsp;&nbsp;${rs.xxyue}&nbsp;&nbsp;�� &nbsp;&nbsp;${rs.xxri}&nbsp;&nbsp;��
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</p>
				</td>
			</tr>
			<tr>
				<td width="104">
					<p align="center">
						ʡ<br/>��<br/>��<br/>��<br/>��<br/>��<br/>��<br/>��
					</p>
				</td>
				<td width="544" valign="top">
					<p style="height:140px">
						&nbsp;
						&nbsp;
					</p>
					<p align="right">
						�����£�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</p>
					<p align="right">
						&nbsp;&nbsp;&nbsp;&nbsp; �� &nbsp;&nbsp;&nbsp;&nbsp;�� &nbsp;&nbsp;&nbsp;&nbsp;��
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</p>
				</td>
			</tr>
			<tr>
				<td width="104">
					<p align="center">
						�� ע
					</p>
				</td>
				<td width="544">
					<p style="height:90px">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${rs.��ע }
					</p>
				</td>
			</tr>
		</table>
	</center>
	<div align="center" class='noPrin'>
		<button type="button" class='button2' onclick="if(dyConfirm()){WebBrowser.ExecWB(8,1)}">
			ҳ������
		</button>
		<button type="button" class='button2' onclick="if(dyConfirm()){WebBrowser.ExecWB(7,1)}">
			��ӡԤ��
		</button>
		<button type="button" class='button2' onclick="if(dyConfirm()){WebBrowser.ExecWB(6,6)}">
			ֱ�Ӵ�ӡ
		</button>
	</div>
</body>
</html:html>
