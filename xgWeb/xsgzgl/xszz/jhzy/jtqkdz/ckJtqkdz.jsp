<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>	
		
	</head>
	<body >
		<html:form action="/jtqkdzGl" method="post" >
		
		<div style="width:100%;height:630px;overflow-x:hidden;overflow-y:auto;">
		
					<table class="formlist" border="0" align="center" style="width: 100%">
				<tr style="height: 23px">
					<td align="center" colspan="4">
						<font size="5">
							${rs.xn }ѧ���ͥ������� 
						</font>
					</td>
				</tr>
			</table>
				
					<jsp:include page="/xsgzgl/xszz/jhzy/jtqkdz/xsxx.jsp" flush="true"></jsp:include>
					
				<logic:notEmpty name="jtxxList">
						<table width="100%" border="0" class="formlist">
						<thead>
							<tr>
								<th colspan="8">
									<span>��ͥ�������</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								
								<th align="" >
									<div align="center">����</div>
								</th>
								<th align="right" >
									<div align="center">����</div>
								</th>
								<th align="right" >
									<div align="center">��ϵ</div>
								</th>
								<th align="right" >
									<div align="center">����(ѧϰ)��λ</div>
								</th>
								<th align="right" >
									<div align="center">ְҵ</div>
								</th>
								<th align="right" >
									<div align="center">��ϵ�绰</div>
								</th>
								<th align="right" >
									<div align="center">������</div>
								</th>
								<th align="right" >
									<div align="center">����״��</div>
								</th>
								</tr>
								
							
								<logic:iterate id="jtxx" name="jtxxList" >
									<tr>
								<td>
									<div align="center">${jtxx.cyxm }</div>
								</td>
								<td>
									<div align="center">${jtxx.cynl }</div>
								</td>
								<td>
									<div align="center">${jtxx.cygx }</div>
								</td>
								<td>
									<div align="center">${jtxx.cygzxxdw }</div>
								</td>
								<td>
									<div align="center">${jtxx.cyzy }</div>
								</td>
								<td>
									<div align="center">${jtxx.cylxdh }</div>
								</td>
								<td>
									<div align="center">${jtxx.cynsr }</div>
								</td>
								<td>
									<div align="center">${jtxx.cyjkzk }</div>
								</td>
							</tr>
								</logic:iterate>
							
						</tbody>
					</table>
					</logic:notEmpty>	
					<table width="100%" border="0" class="formlist">
						<thead>
							<tr>
								<th colspan="4">
									<span>��ͥ�������</span>
								</th>
							</tr>
						</thead>
						<tbody>
					<tr>
						<th align="right" width="20%">
							�Ƿ�²�
						</th>
						<td align="left" width="30%">
							
							${rs.sfgc }
						</td>
						<th align="right" width="20%">
							�Ƿ���
						</th>
						<td align="left" width="30%">
							${rs.sfdq }
						</td>
					</tr>
					<tr>
						<th align="right" width="20%">
							��ʿ��Ů
						</th>
						<td align="left" width="30%">
							${rs.sflszn }
						</td>
						<th align="right" width="20%">
							�Ƿ�ͱ�
						</th>
						<td align="left" width="30%">
							${rs.sfdb }
						</td>
					</tr>
					<tr>
						<th align="right" width="20%">
							��ͥ����
						</th>
						<td align="left" width="30%">
							${rs.jthk }
						</td>
						<th></th>
						<td></td>
						
					</tr>
					<tr>
						<th align="right" width="20%">
							����ʡ����
						</th>
						<td align="left" colspan="3">
							${rs.smc }${rs.simc }${rs.xmc }
						</td>
					</tr>
					
					<tr>
						<th align="right" width="20%">
							��ͥ��ַ
						</th>
						<td align="left" width="30%">
						${rs.jtdz}
						</td>
						<th align="right" width="20%">
							��ͥ�绰
						</th>
						<td align="left" width="30%">
							${rs.jtdh}
						</td>
					</tr>
					<tr>
						<th align="right" width="20%">
							��ͥ�ʱ�
						</th>
						<td align="left" width="30%">
							${rs.jtyb}
						</td>
						<th align="right" width="20%">
							��ͥ�˿���
						</th>
						<td align="left" width="30%">
							${rs.jtrks}
						</td>
					</tr>
					<tr>
						<th align="right" width="20%">
							��ͥ�˾�����
						</th>
						<td align="left" width="30%">
						${rs.jtrjsr}��Ԫ��
						</td>
						<th align="right" width="20%">
							��ͥ��������
						</th>
						<td align="left" width="30%">
							${rs.jtnzsr}��Ԫ��
						</td>
					</tr>
					<tr>
						<th align="right" width="20%">
							������Դ
						</th>
						<td align="left" width="30%">
							${rs.srly}
						</td>
						<th align="right" width="20%">
							
						</th>
						<td align="left" width="30%">
							
						</td>
					</tr>
					<tr>
						<th align="right" width="20%">
					�ѻ��������
						</th>
						<td align="left" colspan="3">
							<div style="word-break:break-all;width:97%">${rs.yhzzqk}</div>
						</td>
					</tr>
					<tr>
						<th align="right" width="20%">
						��ͥ�������
						</th>
						<td align="left" colspan="3">
										<div style="word-break:break-all;width:97%">${rs.jtszqk }</div>
						</td>
					</tr>
					<tr>
						<th align="right" width="20%">
				ͻ���¼����
						</th>
						<td align="left" colspan="3">
										<div style="word-break:break-all;width:97%">${rs.tfsjqk }</div>
						</td>
					</tr>
					<tr>
						<th align="right" width="20%">
						�м��������
						</th>
						<td align="left" colspan="3">
										<div style="word-break:break-all;width:97%">${rs.cjnmqk }</div>
						</td>
					</tr>
					<tr>
						<th align="right" width="20%">
							��ͥ��ҵ���
						</th>
						<td align="left" colspan="3">
										<div style="word-break:break-all;width:97%">${rs.jtsyqk }</div>
						</td>
					</tr>
					<tr>
						<th align="right" width="20%">
						��ͥǷծ���
						</th>
						<td align="left" colspan="3">
										<div style="word-break:break-all;width:97%">${rs.jtqzqk }</div>
						</td>
					</tr>
					<tr>
						<th align="right" width="20%">
							��ͥ�������
						</th>
						<td align="left" colspan="3">
										<div style="word-break:break-all;width:97%">${rs.jtqtqk }</div>
						</td>
					</tr>
					</tbody>
				</table>
				
				
<!--				<iframe src="xszz_jhzy_ckXsjtknxx.do?pkStr=2012380200105122011-2012" width="900px" height="450px"></iframe>-->
				
				</div>
				<table class="formlist">
								<tfoot>
					<tr>
						<td colspan="4">
							
							<div class="btn">
								
								<button type="button" onclick="Close();return false;" id="buttonClose">
									�� ��
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
				</table>
		</html:form>
	</body>
</html>
