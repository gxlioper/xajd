<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/syscommon/head.ini"%>
</head>
<body style="width: 100%">
<html:form action="/jjgl_xqwhgl" method="post" styleId="xqwhForm" onsubmit="return false;">
	<div style='width:100%;overflow-x:hidden;overflow-y:auto;'>
		<table width="100%" border="0" class="formlist">
			<thead>
			<tr>
				<th colspan="4">
					<span>�ҽ���Ϣ</span>
				</th>
			</tr>
			</thead>
			<tbody>
			<tr>
				<th width="18%">�ҽ̱��</th>
				<td width="82%" colspan="3">
						${xqwhMap.xqid}
				</td>
			</tr>
			<tr>
				<th width="18%">
					�ҳ�����
				</th>
				<td width="32%">
						${xqwhMap.jzxm}
				</td>
				<th width="18%">
					�Ǽ���
				</th>
				<td width="32%">
						${xqwhMap.djr}
				</td>
			</tr>
			<tr>
				<th width="18%">
					��Ů����
				</th>
				<td width="32%">
						${xqwhMap.znxm}
				</td>
				<th width="18%">
					��Ů�Ա�
				</th>
				<td width="32%">
					${xqwhMap.znxb}
				</td>
			</tr>

			<tr>
				<th width="18%">
					�貹��Ŀ
				</th>
				<td width="32%">
					${xqwhMap.jjxk}
				</td>
				<th width="18%">
					��Ů�꼶
				</th>
				<td width="32%">
					${xqwhMap.jjnj}
				</td>
			</tr>

			<tr>
				<th width="18%">
					�ҽ̵ص�
				</th>
				<td width="32%">
						${xqwhMap.jjdd}
				</td>
				<th width="18%">
					���ʱн
				</th>
				<td width="32%">
						${xqwhMap.jjsx}
				</td>
			</tr>

			<tr>
				<th width="18%">
					��ϵ�绰
				</th>
				<td width="32%" colspan="3">
						${xqwhMap.lxdh}
				</td>
			</tr>

			<tr>
				<th width="18%">
					�ҽ�Ҫ��
				</th>
				<td colspan="3" width="82%">
						${xqwhMap.jjlsyq}
				</td>

			</tr>
			<tr>
				<th width="18%">
					��ע
				</th>
				<td colspan="3" width="82%">
						${xqwhMap.bz}
				</td>

			</tr>
			</tbody>
		</table>
	</div>
</html:form>
</body>
</html>

