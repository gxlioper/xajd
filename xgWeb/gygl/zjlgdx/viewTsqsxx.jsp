<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type='text/javascript' src='/xgxt/dwr/interface/gyglShareData.js'></script>
		<script language="javascript" src="js/gygl/gyglFunction.js"></script>
	</head>
	<body>
		<html:form action="/zjlg_gygl" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>��Ԣ���� - ��ɫ���ҹ��� - ��� - �鿴��ϸ��Ϣ</a>
				</p>
			</div>
			<div class="tab">
			<table width="99%" class="formlist">
				<thead><tr><th colspan="4"><span>��Ϣά��</span></th></tr></thead>
				<tbody>
				<tr>
					<th>
						������
					</th>
					<td align="left">
						<bean:write name="rs" property="ssbh" />
					</td>
					<th>
						¥������
					</th>
					<td align="left">
						<bean:write name="rs" property="ldmc" />
					</td>
				</tr>
				<tr>
					<th>
						¥��
					</th>
					<td align="left">
						<bean:write name="rs" property="cs" />
					</td>
					<th>
						���Һ�
					</th>
					<td align="left">
						<bean:write name="rs" property="qsh" />
					</td>
				</tr>
				<tr>
					<th>
						ѧ��
					</th>
					<td align="left">
						<bean:write name="rs" property="xn" />
					</td>
					<th>
						��ϵ�绰
					</th>
					<td align="left">
						<bean:write name="rs" property="lxdh" />
					</td>
				</tr>
				<tr>				
					<th>
						����ʱ��
					</th>
					<td align="left">
						<bean:write name="rs" property="sqsj" />
					</td>
					<th>
						<bean:message key="lable.xsgzyxpzxy" />���
					</th>
					<td align="left">
						<bean:write name="rs" property="xysh" />
					</td>
				</tr>
				<tr>
					<th>
						ѧУ���
					</th>
					<td align="left">
						<bean:write name="rs" property="xxsh" />
					</td>
					<th>

					</th>
					<td align="left">
					</td>
				</tr>
				<tr>
					<th>
						���ҳ�Ա
					</th>
					<td colspan="3">
						<logic:present name="rs" property="qscy">
						<logic:iterate id="s" name="rs" property="qscy">
							<bean:write name="s"/>&nbsp;&nbsp;&nbsp;&nbsp;
						</logic:iterate>
						</logic:present>
					</td>
				</tr>
				<tr>
					<th>
						�걨����
					</th>
					<td colspan="2">
						<logic:present name="rs" property="qstj">
								&nbsp;${rs.qstj.xn }ѧ��&nbsp;${rs.qstj.xq }&nbsp;ѧ�ڵ�&nbsp;<bean:write name="rs" property="zs"/>&nbsp;�ܱ���׼ΪУ��������
						</logic:present>
					</td>
				</tr>
				<tr>
					<th>
						���幤������
					</th>
					<td colspan="3">
						<html:textarea name="rs" property="jtgznr" cols="60" rows="8"></html:textarea>
					</td>
				</tr>
				<tr>
					<th>
						�ѿ�չ�������
					</th>
					<td colspan="3">
						<html:textarea name="rs" property="ykzgzqk" cols="60" rows="8"></html:textarea>
					</td>
				</tr>
				</tbody>
				<tfoot>
			      <tr>
			        <td colspan="4">
			          <div class="btn">
						  <button name="�ر�" onclick="window.close();return false;">�ر�</button>
			          </div></td>
			      </tr>
			    </tfoot>
			</table>
			</div>
		</html:form>
	</body>
</html>
