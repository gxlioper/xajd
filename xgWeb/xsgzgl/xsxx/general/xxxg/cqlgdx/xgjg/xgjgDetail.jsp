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
		<style>
			.include_tab{border-collapse:collapse;border:0px;}
			.include_tab td{border-top:0;bordedr-right:1px solid red!importalt;border-bottom:0;border-left:0;}
			.include_tab_r{}
		</style>
	</head>
	<body ondrag="return false">
		<html:form action="/general_xsxx"  method="post">
		
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<!-- ѧ����Ϣ begin-->
			<div class="demo_xxxx" 
				style="overflow-x:hidden;overflow-y:auto;height:550px;" 
				id="demo_xxxx" >
				
				<!-- ������Ϣ begin-->
				
				<table width="100%" border="0" class="formlist" id="tab_jbxx">
					<thead>
						<tr onclick="" style="cursor: hand;">
							<th colspan="5" >
								<span>������Ϣ��ע��<font color="red">��ɫ</font>����Ϊѧ���޸ĺ����Ϣ������ƶ�����ֵ�Ϸ��ɲ鿴����Ϣ��ԭʼֵ��</span>
							</th>
						</tr>
					</thead>					
					<tbody id="hi_jbxx">
						<tr>
						<th width="13%">
							ѧ��
							</th>
							<td width="30%">
								${rs.xh }
								<input type="text" style="width:10px" class="include_tab" readonly="readonly"/>
							</td>
							<th width="13%">
								����
							</th>
							<td width="30%">
								${rs.xm }
							</td>
							<td rowspan="5" class="nohover"
								style="vertical-align:middle; text-align:left;width:115px">
								<div id="stuImg" class="open_img" style="margin-left:0px;">
									<img style="width:100px;height:140px;" 
										src="<%=request.getContextPath()%>/stuPic.jsp?xh=${rs.xh }"
										border="0"/>
								</div>
							</td>
						</tr>
						<tr>
							<th width="13%">
							�Ա�
							</th>
							<td width="30%">
								${rs.xb }
							</td>
							<th width="13%">
								��������
							</th>
							<td >
								${rs.csrq }
							</td>
							
						</tr>
						
						<tr>
							<th width="13%">
							�꼶
							</th>
							<td width="30%">
								${rs.nj }
							</td>
							<th>
								ѧ��(��)
							</th>
							<td colspan="">
								${rs.xz }
							</td>
						</tr>
						<tr>
							<th>
								
								<bean:message key="lable.xsgzyxpzxy" />
							</th>
							<td>
								${rs.xymc }
							</td>
							<th>
								������ò
							</th>
							<td>
								${rs.zzmmmc }
							</td>
						</tr>
						<tr>
							<th>
								רҵ
							</th>
							<td>
								${rs.zymc }
							</td>
							
							<th>
								����
							</th>
							<td>
								${rs.mzmc }
							</td>
							
						</tr>
						<tr>
							<th>
								�༶
							</th>
							<td colspan="">
								
								${rs.bjmc }
							</td>
							<th>
								ѧ��
							</th>
							<td align="left" colspan="2">
								${rs.xjztm }
							</td>
						</tr>
					
						<tr>
							<th>
								��ѧʱ��
							</th>
							<td colspan="">
								${rs.rxrq }				
							</td>
							<th>
							���֤��
							</th>
							<td align="left" colspan="2">
								${rs.sfzh}
							</td>
						</tr>
						
						<tr>
							<th>
								����
							</th>
							<td colspan="4">
								${rs.jgmc }
							</td>
						</tr>
						
						<tr>
							<th>
								�������ڵ�
							</th>
							<td colspan="4">
								${rs.hkszdmc }	
							</td>
						</tr>
						<tr>
							<th>
								��Դ����(��Դ��)
							</th>
							<td colspan="4">
								${rs.sydmc }
							</td>
						</tr>
					</tbody>
					
				</table>
				
				<!-- ������Ϣ end-->
				
				<table width="100%" border="0" class="formlist" id="tab_lxfs">
					<!-- ѧ����ϵ��ʽ begin-->
					<thead>
						<tr onclick="" style="cursor: hand;">
							<th colspan="5">
								<span>��ϵ��ʽ</span>
							</th>
						</tr>
					</thead>
					<tbody id="hi_lxfs" >
						<tr>
							<th width="13%">
								��ϵ�绰
							</th>
							<td colspan="" width="30%">
							${rs.sjhm }
								
							</td>
							<th width="13%">
								��������
							</th>
							<td align="left" colspan="2">
								${rs.dzyx }
							</td>
						</tr>
						<tr>
							<th>
								QQ����
							</th>
							<td>
								${rs.qqhm }
							</td>
							<th>
								��ͥ�绰
							</th>
							<td align="left" colspan="2">
								${rs.jtdh }
							</td>
							</tr>
							<tr>
							<th>
								��ͥ�ʱ�
							</th>
							<td>
								${rs.jtyb }
							</td>
							<th>
								��ͥ��ַ
							</th>
							<td align="left" colspan="2">
								${rs.jtszd }
							</td>
							</tr>
							<tr>
							<th>
								סַ�ʱ�
							</th>
							<td>
								${rs.zd1 }
							</td>
							<th>
								��סַ
							</th>
							<td align="left" colspan="2">
								${rs.xwzsxxdz }
							</td>
							</tr>
					</tbody>
				</table>
				
				
				<table width="100%" border="0" class="formlist" id="tab_jtcyxx">
					<!-- ѧ����ͥ��Ա��Ϣ begin-->
					<thead>
						<tr onclick="" style="cursor: hand;">
							<th colspan="5">
								<span>��ͥ��Ա��Ϣ</span>
							</th>
						</tr>
					</thead>
					
					<tbody id="hi_jtcyxx" >
						<tr>
							<th width="13%">
								<div align="center">����</div>
							</th>
							<th >
								<div align="center">�뱾�˹�ϵ</div>
							</th>
							<th>
								<div align="center">������λ����ַ</div>
							</th>
							<th>
								<div align="center">��λ�绰</div>
							</th>
							<th>
								<div align="center">���˵绰</div>
							</th>
						</tr>
						<tr>
							<td align="center">
								${rs.jtcy1_xm }&nbsp;
							</td>
							<td align="center">
							${rs.jtcy1_gx }
							</td>
							<td align="center">
							${rs.jtcy1_gzdz }
							</td>
							<td align="center">
							${rs.jtcy1_lxdh2 }
							</td>
							<td align="center">
							${rs.jtcy1_lxdh1}
							</td>
						</tr>
						<tr>
							<td align="center">
								${rs.jtcy2_xm }&nbsp;
							</td>
							<td align="center">
							${rs.jtcy2_gx }
							</td>
							<td align="center">
							${rs.jtcy2_gzdz }
							</td>
							<td align="center">
							${rs.jtcy2_lxdh2 }
							</td>
							<td align="center">
							${rs.jtcy2_lxdh1}
							</td>
						</tr>
						<tr>
							<td align="center">
								${rs.jtcy3_xm }&nbsp;
							</td>
							<td align="center">
								${rs.jtcy3_gx }
							</td>
							<td align="center">
								${rs.jtcy3_gzdz }
							</td>
							<td align="center">
								${rs.jtcy3_lxdh2 }
							</td>
							<td align="center">
								${rs.jtcy3_lxdh1}
							</td>
						</tr>
					</tbody>
					
				</table>
			
				<table width="100%" border="0" class="formlist" id="tab_qtxx">
					<!-- ѧ��������Ϣ begin-->
					<thead>
						<tr onclick="" style="cursor: hand;">
							<th colspan="5">
								<span>������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody id="hi_qtxx" >
					
						<tr>
							<th>
								��������
							</th>
							<td align="left">
								${rs.yhdm }
							</td>
							<th>
								���п���
							</th>
							<td colspan="2">
								${rs.yhkh}
							</td>
						</tr>
					
					<tr>
							<th width="13%">
								����ƴ��
							</th>
							<td width="30%">
								${rs.xmpy }
							</td>
							<th width="13%">
								������
							</th>
							<td align="left" colspan="2">
								${rs.cym }
							</td>

						</tr>
						<tr>
							<th>
								���(cm)
							</th>
							<td align="left">
								${rs.sg }
							</td>
							<th>
								����(kg)
							</th>
							<td colspan="2">
								${rs.tz}
							</td>
						</tr>
						<tr>
						<th>
								�س�
							</th>
							<td>
								${rs.tc }
							</td>
							<th>
								����״��
							</th>
							<td colspan="2">
								${rs.jkzk }
							</td>
							</tr>
								<tr>
							<th>
								�������
							</th>
							<td colspan="">
								${rs.pyccmc }
							</td>
							<th>
								�Ƿ��߶���
							</th>
							<td align="left" colspan="2">
								${rs.sfzd }
							</td>
						</tr>
						<tr>
							<th>
								�������
							</th>
							<td>
							${rs.kslbmc }
							</td>
							<th>
								��ѧ��ʽ
							</th>
							<td colspan="2">
								${rs.rxfsmc }
							</td>
						</tr>
						<tr>
							<th>
								������ʽ
							</th>
							<td>
								${rs.pyfsmc }
							</td>
							<th>
								��������
							</th>
							<td>
								${rs.zd5}
							</td>
						</tr>
						<tr>
							<th>
								��ע
							</th>
							<td colspan="4">
								<div  style="word-break:break-all;width:97%">${rs.bz }</div>
							</td>
						</tr>
					</tbody>
				</table>
				
				<!-- ������Ϣ begin -->
				<logic:notEmpty name="rsList">
					<table width="100%" border="0" class="formlist" id="tab_shxx">
						<thead>
							<tr onclick="" style="cursor: hand;">
								<th colspan="5">
									<span>�����Ϣ</span>
								</th>
							</tr>
						</thead>
						<tbody id="hi_shxx" >
							<logic:iterate name="rsList" id="shxx">						
								<tr>
									<th width="13%">
										${shxx.gwmc }���״̬
									</th>
									<td width="30%">
										<logic:equal name="shxx" property="shzt" value="wsh">
											<p><img src="<%=stylePath%>images/ico_dsh.gif" width="52" height="18" /></p>
										</logic:equal>
										<logic:equal name="shxx" property="shzt"  value="tg">
											<p><img src="<%=stylePath%>images/ico_shtg.gif" width="52" height="18" /></p>
										</logic:equal>
										<logic:equal name="shxx" property="shzt"  value="btg">
											<p><img src="<%=stylePath%>images/ico_shbtg.gif" width="52" height="18" /></p>
										</logic:equal>
										<logic:equal name="shxx" property="shzt"  value="th">
											<p><img src="<%=stylePath%>images/ico_shth.gif" width="52" height="18" /></p>
										</logic:equal>
										<logic:equal name="shxx" property="shzt"  value="xcs">
											<p><img src="<%=stylePath%>images/ico_shxcs.gif" width="52" height="18" /></p>
										</logic:equal>
									</td>
									<th width="13%">
										${shxx.gwmc }�����
									</th>
									<td align="left" colspan="2">
										${shxx.shr }
									</td>
								</tr>
								<tr>
									<th width="13%">
										${shxx.gwmc }���ʱ��
									</th>
									<td width="30%">
										${shxx.shsj }
									</td>
									<th width="13%">
										${shxx.gwmc }������
									</th>
									<td align="left" colspan="2">
										<div  style="word-break:break-all;width:97%">
											${shxx.shyj }
										</div>
									</td>
								</tr>
							</logic:iterate>
						</tbody>
					</table>
				</logic:notEmpty>
				<!-- ������Ϣ end -->
			</div>
			<!--  ѧ����Ϣend -->
			
			<!-- �������o begin --> 
			<div style="height:15px">
				<table width="100%" border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="5">
								<div class="btn">
									<button type="button" onclick="Close();return false;" id="buttonClose">�� ��</button>					           
								</div>
							</td>
						</tr>
					</tfoot>
				</table>			
			</div>
			<!-- �������o end -->	
			
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>