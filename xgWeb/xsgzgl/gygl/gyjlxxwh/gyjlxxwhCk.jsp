<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<link rel="stylesheet" type="text/css" href="js/jquery/themes/default/easyui.css" />
	<link rel="stylesheet" type="text/css" href="js/jquery/themes/icon.css" />
	
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript" src="js/check.js"></script>
	<script language="javascript" src="js/qtsjFunction.js"></script>
	<script type="text/javascript" src="dwr/interface/systemFunction.js"></script>
</head>
<body>
		<div class="tab" style="width:100%;overflow-x:hidden;overflow-y:auto;" >
		<table class="formlist" width="95%">
			<thead>
				<tr>
					<th colspan="4">
						<span>ѧ����Ϣ</span>
					</th>
				</tr>
			</thead>
			<tbody>
			<tr>
				<th align="right" width="20%">
					ѧ��
				</th>
				<td align="left" width="30%" nowrap="nowrap">
					${rs.xh }
				</td>
				
				<th  align="right" width="20%">
					����			
				</th>
				<td  width="30%">
					${rs.xm }
				</td>
			</tr>
			<tr>
				<th><bean:message key="lable.xsgzyxpzxy" /></th>
				<td>${rs.xymc }</td>
				<th>רҵ</th>
				<td>${rs.zymc }</td>
			</tr>
			<tr>
				<th>�༶</th>
				<td>${rs.bjmc }</td>
				<th>�꼶</th>
				<td>${rs.nj }</td>
			</tr>
			<tr>
				<th>¥������</th>
				<td>${rs.ldmc }</td>
				<th>���Һ�</th>
				<td>${rs.qsh }</td>
			</tr>
			<tr>
				<th>��λ��</th>
				<td>${rs.cwh }</td>
				<th>���ҵ绰</th>
				<td>${rs.qsdh }</td>
			</tr>
			</tbody>
			</table>
			<table width="95%" border="0" class="formlist">
			<thead>
				<tr>
					<th colspan="4">
						<span>��Ԣ������Ϣ</span>
					</th>
				</tr>
			</thead>
			<tbody>
			<tr>
				<th align="right" width="20%">
					Υ��ѧ��
				</th>
				<td align="left" width="30%">
					${rs.wjxn}
				</td>
					<th align="right" width="20%">
						Υ��ѧ��
					</th>
				<td align="left" width="30%">
					${rs.xqmc}
				</td>
			</tr>
			<tr>
				<th align="right" width="20%">
					���ɴ���				
				</th>
				<td align="left" width="30%">
					${rs.gyjllbdlmc}
				</td>
				<th align="right" width="20%">
					�������				
				</th>
				<td align="left" width="30%" >
					${rs.gyjllbmc}
				</td>
			</tr>
			<tr>
					<th width="20%">
						Υ��ʱ��				
					</th>
					<td align="left" width="30%" colspan="3">
						${rs.wjsj}
					</td>
			</tr>
			<tr>
					<th>
						��ע
					</th>
					<td colspan="3" style="word-wrap: break-word!important; word-break: break-all!important" >
						${rs.bz }
					</td>
			</tr>
			</tbody>
			</table>
			
			<table width="80%" border="0" class="formlist">
			<thead>
				<tr>
					<th colspan="4">
						<span>��ʷ������Ϣ</span>
					</th>
				</tr>
			</thead>
			
				<tr>
				<td colspan="4">
					<div style="overflow-x:hidden;overflow-y:auto;">
					<table class="formList" width="100%">
						<thead>
							<tr align="left">
								<td align="center">Υ��ѧ��</td>
								<td align="center">Υ��ѧ��</td>
								<td align="center">�������</td>
								<td align="center">Υ��ʱ��</td>
							</tr>
						</thead>
			<logic:empty name="rsArrList">
				<tr><td align="center" colspan="4">��ѧ������ʷΥ�ͼ�¼��</td></tr>
			</logic:empty>
			<logic:notEmpty name="rsArrList">
				<logic:iterate name="rsArrList" id="s">
					<tr>
						<td align="center">
							${s.wjxn}
						</td>
						<td align="center">
						${s.xqmc}
						</td>
						<td align="center">
						${s.wjlb}
						</td>
						<td align="center">
						${s.wjsj}
						</td>
					</tr>
				</logic:iterate>
			</logic:notEmpty>
		</table>
		</div>
		</td>
		</tr>
		</table>
		</div>
		<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
			<tfoot>
					<tr>
						<td colspan="4">
							<div class="btn">
								<button type="button" name="�ر�" id="buttonClose" onclick="Close();return false;">
									�� ��
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
		</table>
</body>
</html>
