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
					${rs.pjnd }����ݴ�ѧ�����ѧ��ҵ�������� </span> </b>
		</p>
		<br />

		<p class="nowrap">
			ѧ��&nbsp;&nbsp;${rs.xh }&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  
			�ʱ�䣺 &nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��
		</p>
		<table class="printtab" style="width:98%">
			<tr>
				<td width="8%">
					<p align="center" class="nowrap">
						����
					</p>
				</td>
				<td width="16%" colspan="3" align="center">
					${rs.xm }
				</td>
				<td width="9%">
					<p align="center">
						�Ա�
					</p>
				</td>
				<td width="9%" colspan="2" align="center">
					${rs.xb }
				</td>
				<td width="9%" colspan="3">
					<p align="center">
						��������
					</p>
				</td>
				<td width="11%" align="center">
					${rs.csrq }
				</td>
				<td width="8%" colspan="2">
					<p align="center" class="nowrap">
						����
					</p>
				</td>
				<td width="8%" colspan="3" align="center">
					${rs.mzmc }
				</td>
				<td width="17%" colspan="2" rowspan="3" style="padding:0px 0px 0px 0px">
					<img
							src="<%=request.getContextPath()%>/sjcz/xszptp.jsp?xh=${rs.xh }"
							border="0" align="absmiddle" style="width:110px;height:140px" />
				</td>
			</tr>
			<tr>
				<td width="8%">
					<p align="center">
						������ò
					</p>
				</td>
				<td width="16%" colspan="3" align="center">
					${rs.zzmmmc }
				</td>
				<td width="9%">
					<p align="center">
						����
					</p>
					<p align="center">
						ְ��
					</p>
				</td>
				<td width="30%" colspan="6" align="center">
					${rs.xszw }
				</td>
				<td width="8%" colspan="2">
					<p align="center">
						ѧ��
					</p>
				</td>
				<td width="8%" colspan="3" align="center">
					${rs.xlmc }
				</td>
			</tr>
			<tr>
				<td width="8%">
					<p align="center">
						<bean:message key="lable.xb" />
					</p>
				</td>
				<td width="16%" colspan="3" align="center">
					${rs.xymc }
				</td>
				<td width="9%">
					<p align="center">
						רҵ
					</p>
				</td>
				<td width="30%" colspan="6" align="center">
					${rs.zymc }
				</td>
				<td width="8%" colspan="2">
					<p align="center">
						ѧ��
					</p>
				</td>
				<td width="8%" colspan="3" align="center">
					${rs.xz }
				</td>
			</tr>
			<tr>
				<td width="8%">
					<p align="center">
						��
					</p>
					<p align="center">
						&nbsp;
					</p>
					<p align="center">
						&nbsp;
					</p>
					<p align="center">
						��
					</p>
					<p align="center">
						&nbsp;
					</p>
					<p align="center">
						&nbsp;
					</p>
					<p align="center">
						��
					</p>
					<p align="center">
						&nbsp;
					</p>
					<p align="center">
						&nbsp;
					</p>
					<p align="center">
						��
					</p>
				</td>
				<td width="91%" colspan="17" valign="top">
					<p align="left" style="height:220px">
						&nbsp;&nbsp;&nbsp;&nbsp;${rs.���˼��� }
					</p>
				</td>
			</tr>
			<tr>
				<td width="8%">
					<p align="center">
						��
					</p>
					<p align="center">
						Ҫ
					</p>
					<p align="center">
						��
					</p>
					<p align="center">
						��
					</p>
					<p align="center">
						��
					</p>
					<p align="center">
						��
					</p>
					<p align="center">
						&nbsp;
					</p>
					<p align="center">
						&nbsp;
					</p>
					<p align="center">
						��
					</p>
					<p align="center">
						��
					</p>
					<p align="center">
						��
					</p>
					<p align="center">
						��
					</p>
					<p align="center">
						&nbsp;
					</p>
					<p align="center">
						&nbsp;
					</p>
					<p align="center">
						&nbsp;
					</p>
					<p align="center">
						&nbsp;
					</p>
					<p align="center">
						&nbsp;
					</p>
				</td>
				<td width="91%" colspan="17" valign="top">
					<p align="left" style="height:210px">
						&nbsp;&nbsp;&nbsp;&nbsp;${rs.��Ҫ�¼� }
					</p>
					<br/>
					<p align="left" style="height:210px">
						&nbsp;&nbsp;&nbsp;&nbsp;${rs.����� }
					</p>
				</td>
			</tr>
			</table>
			<p style="height:70px">&nbsp;</p>
			<table class="printtab" style="width:98%">
			<tr>
				<td width="13%" colspan="2">
					<p align="center">
						�༶����
					</p>
				</td>
				<td width="6%" align="center">
					${rs.bjrs }
				</td>
				<td width="15%" colspan="2">
					<p align="center">
						��У�ڼ��Ƿ��ܹ�����
					</p>
				</td>
				<td width="9%" colspan="3" align="center">
					${rs.sfwj }
				</td>
				<td width="22%" colspan="4">
					<p align="center">
						�۲�����
					</p>
				</td>
				<td width="7%" colspan="2" align="center">
					${rs.zd1 }
				</td>
				<td width="14%" colspan="3">
					<p align="center">
						�۲�ɼ�
					</p>
				</td>
				<td width="9%" align="center">
					${rs.zcfbjpm }
				</td>
			</tr>
			<tr>
				<td width="37%" colspan="6" >
					<p align="center">
						��У�ڼ䲹����Ŀ��
					</p>
				</td>
				<td width="15%" colspan="3" align="center">
					${rs.bkkms }
				</td>
				<td width="30%" colspan="6" align="center">
						δȡ��ѧ�ֵĿ�Ŀ��
				</td>
				<td width="17%" colspan="3" align="center">
					${rs.wdxfkms }
				</td>
			</tr>
			<tr>
				<td width="12%" colspan="2" valign="top">
					<p>
						&nbsp;
					</p>
					<p align="center">
						��
					</p>
					<p align="center">
						��
					</p>
					<p align="center">
						��
					</p>
					<p align="center">
						��
					</p>
					<p align="center">
						��
					</p>
					<p align="center">
						��
					</p>
				</td>
				<td width="87%" colspan="16" valign="bottom">
					<p align="right">
						&nbsp;
					</p>
					<p align="right">
						&nbsp;
					</p>
					<p align="right">
						&nbsp;
					</p>
					<p align="right">
						&nbsp;
					</p>
					<p align="right">
						&nbsp;
					</p>
					<p align="right">
						������ǩ����&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</p>
					<p align="right">
						��&nbsp;&nbsp;&nbsp;&nbsp; ��&nbsp;&nbsp;&nbsp;&nbsp; ��&nbsp;&nbsp;&nbsp;&nbsp;
					</p>
				</td>
			</tr>
			<tr>
				<td width="12%" colspan="2" valign="top">
					<p align="center">
						&nbsp;
					</p>
					<p align="center">
						&nbsp;
					</p>
					<p align="center">
						Ժ
					</p>
					<p align="center">
						��
					</p>
					<p align="center">
						��
					</p>
					<p align="center">
						��
					</p>
					<p align="center">
						��
					</p>
				</td>
				<td width="87%" colspan="16">
					<p align="center" style="height:160px">
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
				<td width="12%" colspan="2" valign="top">
					<p align="center">
						&nbsp;
					</p>
					<p align="center">
						&nbsp;
					</p>
					<p align="center">
						&nbsp;
					</p>
					<p align="center">
						У
					</p>
					<p align="center">
						��
					</p>
					<p align="center">
						��
					</p>
					<p align="center">
						��
					</p>
					<p align="center">
						��
					</p>
				</td>
				<td width="87%" colspan="16" valign="top">
					<p style="height:160px">
						&nbsp;&nbsp;&nbsp;&nbsp;${rs.xxyj }
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
				<td width="12%" colspan="2">
					<p align="center">
						��
					</p>
					<p align="center">
						&nbsp;
					</p>
					<p align="center">
						&nbsp;
					</p>
					<p align="center">
						&nbsp;
					</p>
					<p align="center">
						ע
					</p>
				</td>
				<td width="87%" colspan="16" valign="top">
					<p style="height:160px">
						&nbsp;&nbsp;&nbsp;&nbsp;${rs.��ע }
					</p>
				</td>
			</tr>
		</table>
		<p width="98%" align="left">
			&nbsp;&nbsp;&nbsp;&nbsp;ע : 1. �����˽�У���������Ҽ����ͳɼ�����ӡ������Ժ���£�һʽһ�� <br/>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2. �˱���һʽһ�ݣ������ҵ��������
		</p>
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
