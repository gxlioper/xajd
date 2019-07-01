<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/qgzx/kycxgl/sjxmgl/sjxm/js/sjxmsq.js"></script>
		<script type='text/javascript' src="js/check.js"></script>
		<script type='text/javascript'>
		
		</script>
	</head>
	<body style="width: 100%">
		<html:form action="/qgzx_kycxsjxmgl" method="post" styleId="SjxmglForm" onsubmit="return false;">
		<html:hidden property="xmid" styleId="xmid" value="${rs.xmid}"/>
		<input type="hidden" id="jsxxStr" name="jsxxStr"/>
		<input type="hidden" id="xsxxStr" name="xsxxStr"/>
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>��Ŀ��Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="20%">
								��Ŀ���
							</th>
							<td width="30%">
								${rs.xmbh}
							</td>
							<th width="20%">
								��Ŀ����
							</th>
							<td width="30%">
								${rs.xmmc}
							</td>
						</tr>
						<tr>
							<th width="20%">
								��Ŀ����
							</th>
							<td width="30%">
								${rs.xmsxmc}
							</td>
							<th width="20%">
								��Ŀ������λ
							</th>
							<td width="30%">
								${rs.ssdwmc}
							</td>
						</tr>
						<tr>
							<th width="20%">
								��Ŀ������ѧ��
							</th>
							<td width="30%">
								${rs.xh}
							</td>
							<th width="20%">
								��Ŀ����������
							</th>
							<td width="30%">
								${rs.xm}
							</td>
						</tr>
						<tr>
							<th width="20%">
								��ϵ��ʽ
							</th>
							<td width="30%">
								${rs.lxfs}
							</td>
							<th width="20%">
								�ƻ���������
							</th>
							<td width="30%">
								${rs.jhcyrs}
							</td>
							
						</tr>
						<tr>
							<th width="20%">
								��Ŀ��ʼʱ��
							</th>
							<td width="30%">
								${rs.kssj}
							</td>
							<th width="20%">
								��Ŀ����ʱ��
							</th>
							<td width="30%">
								${rs.jssj}
							</td>																			
						</tr>
						<tr>
							<th width="20%">
								����Ԥ��
							</th>
							<td width="30%" >
								${rs.jfys}
							</td>
							<th width="20%">
								��׼����
							</th>
							<td width="30%" >
								${rs.pzjf}
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>С���Ա��Ϣ
								<img src="xsxx/fbgl/images/add-icons-1.gif" alt="����" onclick="addXs(this);return false;"/>
						    	<img src="xsxx/fbgl/images/delete-icons-2.gif" alt="ɾ��" onclick="delXs(this);return false;"/>
								</span>
							</th>
						</tr>
					</thead>
				<tbody>
				<tr>
					<td colspan="4">
					<div class="con_overlfow">
					<table width="100%" border="0" class="datelist" style="margin:2px auto;">
					<thead>
						
						<tr>
							<td width='5%'><input type="checkbox" name="th" onclick="selectAll(this);" /></td>
							<td width='15%'>ѧ��</td>
							<td width='15%'>����</td>
							<td width='5%'>�Ա�</td>
							<td width='15%'>ѧԺ</td>
							<td width='15%'>�༶</td>
							<td width='8%'>��Ŀ�еķֹ�</td>
							<td width='8%'>��ϵ�绰</td>
							<td width='10%'>��Ŀ��״̬</td>
						</tr>
					</thead>
					<tbody id="tbody_xsxx">
						<logic:iterate id="i" name="cyList" indexId="index01">
						<tr>
						<td>
						<input type="checkbox" id="checkbox_${index01}"/>
						</td>
						<td name="xh">${i.xh}</td>
						<td>${i.xm}</td>
						<td>${i.xb}</td>
						<td>${i.xymc}</td>
						<td>${i.bjmc}</td>
						<td>
						<html:text property="xmfg" styleId="xmfg_${index01}" value="${i.xmfg}" style='width:100px' maxlength='20'></html:text>
						</td>
						<td>${i.lxdh}</td>
						<td>
						<html:select property="xmnzt" styleId="xmnzt_${index01}" value="${i.xmnzt}">
								<html:options collection="xmnztList" labelProperty="mc" property="dm"/>
						</html:select>
						</td>
						</tr>
						</logic:iterate>
						</tbody>
						</table>
						</div>
						</td>
						</tr>
						</tbody>
				 <thead>
						<tr>
							<th colspan="4">
								<span>ָ����ʦ
								<img src="xsxx/fbgl/images/add-icons-1.gif" alt="����" onclick="addJs(this);return false;"/>
						    	<img src="xsxx/fbgl/images/delete-icons-2.gif" alt="ɾ��" onclick="delJs(this);return false;"/>
								</span>
							</th>
						</tr>
					</thead>
				
				<tbody>
				<tr>
					<td colspan="4">
					<div class="con_overlfow">
				<table width="100%" border="0" class="datelist" style="margin:2px auto;">
					<thead>
						<tr>
							<td width='5%'><input type="checkbox" name="th" onclick="selectAll(this);" /></td>
							<td width='15%'>����</td>
							<td width='15%'>����</td>
							<td width='15%'>ѧԺ</td>
							<td width='15%'>ְ��</td>
							<td width='15%'>�о�����</td>
							<td width='15%'>��ϵ�绰</td>
						</tr>
					</thead>
					<tbody id="tbody_jsxx">
						<logic:iterate id="i" name="zdlsList" indexId="index01">
						<tr>
						<td>
						<input type="checkbox" id="checkbox_${index01}"/>
						</td>
						<td name="xh">${i.zgh}</td>
						<td>${i.xm}</td>
						<td>${i.xymc}</td>
						<td>
						<html:text property="zc" styleId="zc_${index01}" value="${i.zc}" maxlength='10'></html:text>
						</td>
						<td>
						<html:text property="yjfx" styleId="yjfx_${index01}" value="${i.yjfx}" maxlength='100'></html:text>
						</td>
						<td>
						${i.lxdh}
						</td>
						</tr>
						</logic:iterate>
						</tbody>
				</table>
				</div>
				</td>
				</tr>
				</tbody>
				<tbody>
				<tr>
					<td colspan="4">
				<div style=" width:100%; margin:0 auto; overflow-x:auto; height:50px; *padding-bottom:20px;">
				</div>
				</td>
				</tr>
				</tbody>
				</table>
				</div>
			  <div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
				<tfoot>
					<tr>
						<td colspan="4">
							<div class="btn">
							<button type="button" onclick="saveCywh('save');">
										����
									</button>
								<button type="button" onclick="Close();return false;">
									�� ��
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
				
				</div>
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>

