<%@ page language="java" contentType="text/html; charset=GBK"%>
<jsp:directive.page import="xsgzgl.xszz.jhzy.jtqkdz.XszzJtqkdzService" />
<jsp:directive.page import="xsgzgl.xszz.jhzy.jtqkdz.XszzJtqkdzActionForm" />
<jsp:directive.page import="java.util.HashMap" />
<jsp:directive.page import="java.util.List" />
<jsp:directive.page import="xgxt.form.CommanForm" />
<jsp:directive.page import="xgxt.studentInfo.ynys.XsxxYnysService" />

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<script type="text/javascript">
		function deploy(id){
			//document.getElementById(id).style.display = (document.getElementById(id).style.display == 'none') ? '' : 'none';  
		}
		<%
			XszzJtqkdzService serivce = new XszzJtqkdzService();
			XszzJtqkdzActionForm model = new XszzJtqkdzActionForm();
			model.setXh((String)request.getAttribute("xh"));
			model.setXn((String)request.getAttribute("xn"));
			HashMap<String, String> rs = serivce.ckJtqkdzxx(model);
			
			List<HashMap<String, String>> jtxxList = serivce.cxJtcyxxList(model);
			request.setAttribute("rs", rs);
			request.setAttribute("jtxxList", jtxxList);
			//CommanForm dataSearchForm =new CommanForm();
			//XsxxYnysService ynysService = new XsxxYnysService();
			//request.setAttribute("ssList", ynysService.getSsList());
			//request.setAttribute("shiList", ynysService.getShiList(dataSearchForm.getJgshen()).get("shiList"));
			//request.setAttribute("xianList", ynysService.getShiList(dataSearchForm.getJgshen()).get("xianList"));
		%>
		</script>
		
	</head>
	<body >
		<html:form action="/jtqkdzGl" method="post" >
				
					<jsp:include page="/xsgzgl/xszz/jhzy/jtqkdz/xsxx.jsp" flush="true"></jsp:include>
					
				<logic:notEmpty name="jtxxList">
						<table width="100%" border="0" class="formlist">
						<thead>
							<tr>
								<th colspan="8" onclick="deploy('jtcy')" style="cursor: hand;">
									<span>��ͥ��Ա���</span>
								</th>
							</tr>
						</thead>
						<tbody id="jtcy">
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
								<th colspan="4" onclick="deploy('jtxx')" style="cursor: hand;">
									<span>��ͥ�������</span>
								</th>
							</tr>
						</thead>
						<tbody id="jtxx" >
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
							${rs.smc }
							${rs.simc }
							${rs.xmc }
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
		
		</html:form>
	</body>
</html>
