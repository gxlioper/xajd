<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	    <script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" defer="defer">
		
		</script>
	</head>
	<body onload="" >
		<html:form action="/jhzyGjlzjxj" method="post">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>

			<input type="hidden" name="pkValue" value="${pkValue }"/>
			<div style="width:100%;height:630px;overflow-x:hidden;overflow-y:auto;">
			<table class="formlist" border="0" align="center" style="width: 100%">
				<tr style="height: 23px">
					<td align="center" colspan="4">
						<font size="5">
							${rs.xn }ѧ�������־��ѧ������
						</font>
					</td>
				</tr>
			</table>
			
				<table width="100%" border="0" class="formlist">
					<!-- ѧ��������Ϣ begin-->
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ��������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr >
							<th align="right" width="20%">
								ѧ��
							</th>
							<td align="left" width="30%">
					
									<input type="hidden" id="xh" name="xh" value="${rs.xh }"/>
									<input type="hidden" id="xn" name="xn" value="${rs.xn }"/>
									${rs.xh }
							</td>
							<th align="right" width="20%">
								����
							</th>
							<td align="left" width="30%">
								${rs.xm }
							</td>
						</tr>
						<tr >
							<th align="right" width="">
								�Ա�
							</th>
							<td align="left" width="">
								${rs.xb }
							</td>
							<th align="right" width="">
								ѧ��
							</th>
							<td align="left" width="">
								${rs.xz }
							</td>
						</tr>
						<tr >
							<th align="right" width="">
								�꼶
							</th>
							<td align="left" width="">
								${rs.nj }
							</td>
							<th align="right" width="">
								<bean:message key="lable.xb" />
							</th>
							<td align="left" width="">
								${rs.xymc }
							</td>
						</tr>
						<tr >
							<th align="right" width="">
								רҵ
							</th>
							<td align="left" width="">
								${rs.zymc }
							</td>
							<th align="right" width="">
								�༶
							</th>
							<td align="left" width="">
								${rs.bjmc}
							</td>
						</tr>
						<tr >
							<th align="right" width="">
								����
							</th>
							<td align="left" width="">
								${rs.mzmc }
							</td>
							<th align="right" width="">
								������ò
							</th>
							<td align="left" width="">
								${rs.zzmmmc }
							</td>
						</tr>
						<tr >
							<th align="right" width="">
								���֤��
							</th>
							<td align="left" width="">
								${rs.sfzh }
							</td>
							<th align="right" width="">
								��������
							</th>
							<td align="left" width="">
								${rs.csrq }
							</td>
						</tr>
					</tbody>
					<!-- ѧ��������Ϣend -->
					
					<!-- ��������Ϣ begin-->
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧϰ���</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
						<th align="right" width="20%">
								�ɼ�����<br/>������/��������
							</th>
							<td align="left" width="30%" >
								${rs.cjpm}
								/${rs.bjzrs }

							</td>
							<th align="right" width="20%">
								�������
							</th>
							<td align="left" width="30%" >
								���޿�&nbsp;&nbsp;&nbsp;${rs.bxkms}���ţ�
								<br/>��������${rs.jgms}���ţ�
							</td>
							
						</tr>
						<tr>
							<th align="right" width="20%">
							ʵ���ۺϿ�������
							</th>
							<td align="left" width="30%" >
							${rs.sxzhkppm }
							</td>
							<th align="right" width="20%">
								���ǣ�����<br/>������/��������
							</th>
							<td align="left" width="30%" >
								${rs.zhkppm}/${rs.bjzrs }
				
							</td>
						</tr>
						</tbody>
						</table>
					
						<table width="100%" border="0" class="formlist">
						<!-- ѧ��������Ϣ begin-->
						<thead>
							<tr>
								<th colspan="3">
									<span>��ѧ�ڼ���Ҫ�����</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
							<th style="width:20%">
								<div align="center">������</div>
							</th>
							<th style="width:40%">
								<div align="center">������</div> 
							</th>
							<th style="width:40%">
								<div align="center">�佱��λ</div>
							</th>
							</tr>
							<tr>
								<td align="center">
									${rs.hjsj1}
								</td>
								<td align="center">
								${rs.hjmc1}
								</td>
								<td align="center">
									${rs.bjdw1}
								</td>
							</tr>
							<tr>
								<td align="center">
									${rs.hjsj2}
								</td>
								<td align="center">
									${rs.hjmc2}
								</td>
								<td align="center">
									${rs.bjdw2}
								</td>
							</tr>
							<tr>
								<td align="center">
									${rs.hjsj3}
								</td>
								<td align="center">
									${rs.hjmc3}
								</td>
								<td align="center">
								${rs.bjdw3}
								</td>
							</tr>
							<tr>
								<td align="center">
								${rs.hjsj4}
								</td>
								<td align="center">
									${rs.hjmc4}
								</td>
								<td align="center">
									${rs.bjdw4}
								</td>
							</tr>
						</tbody>
						</table>
						
						<table width="100%" border="0" class="formlist">
						<tbody>
						<tr>
							<th align="right" width="20%" >
							��������
								
							</th>
							<td align="left" width="" colspan="3">
									<div style="word-break:break-all;width:99%" >${rs.sqly }</div>
							</td>
						</tr>
						
					</tbody>
				
					<!-- ��������Ϣ end-->			
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button type="button" name="�ر�" onclick="Close();return false;" id="buttonClose">�� ��</button>					           
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
		</html:form>
	</body>
</html>