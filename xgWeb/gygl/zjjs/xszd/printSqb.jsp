<%@ page language="java" pageEncoding="GB18030"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<object id="WebBrowser" width=0 height=0
			classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
		<style media='print'>
			.noPrin{display:none;}
		</style>
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
	</head>
	<body>
		<br/><br/>
		<p align="center">
			<span style="font-size:21px;font-family:����">�߶������</span>
		</p>
		<br/>
		<table style="font-family:����_GB2312;font-size:14px;width:17.67cm;" class="printtab">
			<tr height="45px" class="nowrap">
				<td width="43" align="center">
						ϵ
				</td>
				<td width="84" align="center">
						${rs.xymc }
				</td>
				<td width="48" align="center">
						�༶
				</td>
				<td width="120" align="center">
						${rs.bjmc }
				</td>
				<td width="48" align="center">
						����
				</td>
				<td width="120" align="center">
						${rs.xm }
				</td>
				<td width="48" align="center">
						�Ա�
				</td>
				<td width="57" align="center">
						${rs.xb }
				</td>
			</tr>
			<tr height="45px">
				<td width="343" colspan="5" >
						ѧ����ϵ�绰��${rs.lxdh }
				</td>
				<td width="225" colspan="3" >
						ס�޵ص㣺${rs.zsdd }
				</td>
			</tr>
			<tr height="45px">
				<td width="343" colspan="5" >
						��ͥ��ס��ַ��${rs.jtdz }
				</td>
				<td width="225" colspan="3" >
						��ͥ��ϵ�绰��${rs.jtdh }
				</td>
			</tr>
			<tr height="45px">
				<td width="568" colspan="8" >
						�߶�ʱ��Ϊ��
					<script defer>
						var kssj = '${rs.zdkssj }';
						jQuery('#kssj').text(kssj.substring(0,4)+"��"+kssj.substring(5,6)+"��"+kssj.substring(7,8)+"��") ;
					</script>
					<span id="kssj"></span>
					&nbsp;&nbsp; ���� &nbsp;&nbsp;
					<span id="jssj"></span>
					<script defer>
						var jssj = '${rs.zdjssj }';
						jQuery('#jssj').text(jssj.substring(0,4)+"��"+jssj.substring(5,6)+"��"+jssj.substring(7,8)+"��") ;
					</script>
				</td>
			</tr>
			<tr>
				<td colspan="8" valign="top">
					<p>
						�������ɣ�
					</p>
					<p style="height:110px">
						&nbsp;&nbsp;&nbsp;&nbsp;${rs.sqly }
					</p>
					<p>
						���˼��ҳ�����ϸ�Ķ�����ѧ���ֲᡷ����ص�ѧ���߶��涨��
					</p>
					<p align="right">
						��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</p>
				</td>
			</tr>
			<tr>
				<td colspan="8" valign="top">
					<p>
						�ҳ������
					</p>
					<p style="height:100px">
						&nbsp;
					</p>
					<p align="right" >
						�ҳ�ǩ�� :<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</p>
					<p align="right">
						��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</p>
				</td>
			</tr>
			<tr>
				<td colspan="8" valign="top">
					<p>
						�����������
					</p>
					<p style="height:100px">
						&nbsp;&nbsp;&nbsp;&nbsp;${rs.bjshyj }
					</p>
					<p align="right" >
						������ǩ�� :<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</p>
					<p align="right">
						��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</p>
				</td>
			</tr>
			<tr>
				<td colspan="8" valign="top">
					<p>
						ϵ�쵼�����
					</p>
					<p style="height:100px">
						&nbsp;&nbsp;&nbsp;&nbsp;${rs.xyshyj }
					</p>
					<p align="right" >
						ϵ�쵼ǩ�� :<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</p>
					<p align="right">
						��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
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

		<div align="center" class='noPrin'>
			<button class='button2' onclick="WebBrowser.ExecWB(8,1);return false;">
				ҳ������
			</button>
			<button class='button2' onclick="WebBrowser.ExecWB(7,1);return false;">
				��ӡԤ��
			</button>
			<button class='button2' onclick="WebBrowser.ExecWB(6,6);return false;"
				id="printButton">
				ֱ�Ӵ�ӡ
			</button>
		</div>
	</body>
</html>
