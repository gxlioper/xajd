<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<!-- ͷ�ļ� -->
<html:html>
<body>
	<html:form action="/typj" method="post">
		<table width="100%" class="tbstyle">
			<tr>
				<td width="7%"></td>
				<td width="13%"></td>
				<td width="10%"></td>
				<td width="10%"></td>
				<td width="8%"></td>
				<td width="8%"></td>
				<td width="14%"></td>
				<td width="14%"></td>
				<td width="2%"></td>
				<td width="14%"></td>
			</tr>
			<tr height="40px">
				<td align="center" colspan="2">��&nbsp;&nbsp;&nbsp;&nbsp;��</td>
				<td align="center" colspan="2">${rs.xm }</td>
				<td align="center">�Ա�</td>
				<td align="center">${rs.xb }</td>
				<td align="center">ϵ�𡢰༶</td>
				<td align="center" colspan="3">${rs.zymc }&nbsp;&nbsp;${rs.bjmc }</td>
			</tr>
			<tr height="40px">
				<td align="center" colspan="2">��ͥסַ</td>
				<td align="center" colspan="5">${rs.jtdz }</td>
				<td align="center">��ͥ�̶��绰</td>
				<td align="center" colspan="2">${rs.jtdh }</td>
			</tr>
			<tr height="40px">
				<td align="center" colspan="3">����ƶ��֤������</td>
				<td align="center" colspan="4">${rs.zmbm }</td>
				<td align="center">���ᣨ��Դ�أ�</td>
				<td align="center"colspan="2">${rs.jg }</td>
			</tr>
			<tr height="40px">
				<td align="center" colspan="3">Ӧ�ɷ���(Ԫ)</td>
				<td colspan="7">
					�̲Ĵ���ѣ�${rs.jcdbf }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					ס�޷ѣ�${rs.zsf }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					ѧ�ѣ�${rs.xf }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</td>
			</tr>
			<tr height="40px">
				<td align="center" colspan="3">���Ƚɷ���(Ԫ)</td>
				<td align="center" colspan="3">${rs.xjfy }</td>
				<td align="center" colspan="3">Ƿ�ɷ��ã����뻺������Ԫ��</td>
				<td align="center">${rs.qjfy }</td>
			</tr>
			<tr height="40px">
				<td align="center">����<br/>����</td>
				<td colspan="9" >
					<p style="height:100px">
						&nbsp;&nbsp;&nbsp;&nbsp;${rs.sqly }
					</p>
					<div align="right">
						������ǩ����
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/>
						������ϵ�绰��
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						��&nbsp;&nbsp;&nbsp;
					</div>
				</td>
			</tr>
			<tr height="40px">
				<td align="center">Ժϵ<br/>���</td>
				<td colspan="9">
					<p style="height:80px">
						&nbsp;&nbsp;&nbsp;&nbsp;${rs.xyshyj }
					</p>
					<div align="right">
						Ժϵ���������鸺����ǩ���Ӹ�Ժϵ�£�
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<br/>
						��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						��&nbsp;&nbsp;&nbsp;
					</div>
				</td>
			</tr>
			<tr height="40px">
				<td align="center">��ע</td>
				<td colspan="9">
					1����������ȡ����ǰ��Ӧ���ṩ���ƶ��֤�����ϣ��硶�ߵ�ѧУѧ������ͥ����������<br/>
					2��������Ӧ����ʵ��д������Ժϵ��׼�󣬿���ƾ�˱����񴦰�����ѧ�ɷ�������<br/>
					3������һʽ���ݣ���һ��Ժϵ���棬�ڶ����������档
				</td>
			</tr>
		</table>
	</html:form>
</body>
</html:html>
