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
		<div class="tab">
		<table class="formlist" width="95%">
			<thead>
				<tr style="height:22px">
					<th colspan="4">
						<span>��Ԣ������Ϣ�鿴</span>
					</th>
				</tr>
			</thead>
			<tbody>
			<tr>
				<td align="left" colspan="4"><span>ѧ����Ϣ</span></td>
			</tr>
			</tbody>
			<tbody>
			<tr>
				<th align="right" width="16%">
					ѧ��
				</th>
				<td align="left" width="34%" nowrap="nowrap">
					${rs.xh }
				</td>
				
				<th  align="right" width="16%">
					����			
				</th>
				<td  width="34%">
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
				<th>ס������</th>
				<td>${rs.zsqs }</td>
				<th>���ҵ绰</th>
				<td>${rs.qsdh }</td>
			</tr>
			</tbody>
			<tbody>
				<tr>
					<td align="left" colspan="4">��Ԣ������Ϣ</td>
				</tr>
			</tbody>
			<tbody>
				<tr>
					<td align="center">Υ��ʱ��</td>
					<td align="center">Υ�����</td>
					<td align="center" colspan="2">��ע</td>
				</tr>
				<logic:empty name="rsArrList">
					<tr><td align="center" colspan="4">��ѧ����Υ�ͼ�¼��</td></tr>
				</logic:empty>
				<logic:notEmpty name="rsArrList">
							<logic:iterate name="rsArrList" id="s">
								<tr>
									<!-- ��ʾ��Ϣ -->
									<logic:iterate id="v" name="s" offset="0" length="2">
										<td align="center">
											${v }
										</td>
									</logic:iterate>
									<logic:iterate id="v" name="s" offset="2" length="1">
										<td align="center" colspan="2">
											${v }
										</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
						</logic:notEmpty>
			</tbody>
			<tfoot>
			      <tr>
			        <td colspan="4">
			          <div class="btn">
			            <button type="button" name="�ر�" id="buttonClose" onclick="window.close();return false;">�ر�</button>
			          </div></td>
			      </tr>
			    </tfoot>
		</table>
		</div>
</body>
</html>
