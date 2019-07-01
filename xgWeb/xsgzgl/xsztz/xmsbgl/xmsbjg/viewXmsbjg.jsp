<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type='text/javascript' src="js/check.js"></script>
		<script type='text/javascript'>
		jQuery(function(){
			jQuery("#shlccx").load(
					"comm_spl.do?method=lccx&sqid=$${model.xmdm}&tt="
							+ new Date().getTime());
		});
		</script>
	</head>
	<body style="width: 100%">
		<html:form action="/xmsbXmsbjg" method="post" styleId="XmsbjgForm" onsubmit="return false;">
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>��Ŀ�걨</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="20%">
								ѧ��
							</th>
							<td width="30%">
								${rs.xn}
							</td>
							</td>
							<th>ѧ��</th>
							<td>
								${rs.xqmc}
							</td>
						</tr>
						<tr>
							<th width="20%">
								��Ŀ����
							</th>
							<td width="30%">
								${rs.xmmc}
							</td>
							<th width="20%">
								��Ŀ����
							</th>
							<td width="30%">
								${rs.xmjbmc}
							</td>
						</tr>
						<tr>
							<th width="20%">
								�걨����
							</th>
							<td width="30%">
								${rs.sbbmmc}
							</td>
							<th width="20%">
								������Ŀ
							</th>
							<td width="30%">
								${rs.sskmmc}
							</td>
						</tr>
						<tr>
							<th width="20%">
								�ɲ�����/����
							</th>
							<td width="30%">
								<logic:equal name="rs" property="csms" value="1">����   ${rs.kcyrs}��</logic:equal> 
								<logic:equal name="rs" property="csms" value="2">����   ${rs.kcyrs}��</logic:equal> 
							</td>
							<th width="20%">
								��Ŀ��ʼʱ��
							</th>
							<td width="30%">
								${rs.xmkssj}
							</td>
						</tr>
						<tr>
							<th width="20%">
								�걨��
							</th>
							<td width="30%" >
								${rs.xm}
							</td>
							<th width="20%">
								�걨����ϵ��ʽ
							</th>
							<td width="30%">
								${rs.lxdh}
							</td>																			
						</tr>
						<tr>
							<th width="20%">
								�Ƿ���������
							</th>
							<td width="30%" >
								${rs.sfsljxmc}
							</td>
							<th width="20%">
								����ѧ��
							</th>
							<td width="30%" >
								${rs.jcxf}
							</td>
						</tr>
						<logic:equal value="13627" name="xxdm">
						<tr>
							<th width="20%">
								��Ŀ����
							</th>
							<td width="30%" >
								${rs.xmcd}
							</td>
							<th width="20%">
								������
							</th>
							<td width="30%" >
								${rs.bkgsmc}
							</td>
						</tr>
						</logic:equal>
					<tr>
							<th width="20%">
								��Ŀ������Ϣ
							</th>
							<td width="30%"  colspan="3">
								<logic:notEmpty name="xmjxList">
								 <div style="overflow-y:auto;" id="jxDiv">
								 <table width="100%" border="0" class="formlist">
									<thead>
										<tr>
											<td width='15%'>��������</td>
											<td width='15%'>����ѧ��</td>
											<td width='15%'>����˳��</td>
										</tr>
									</thead>
									<tbody id="tbody_xmjxxx">
									<logic:iterate id="i" name="xmjxList" indexId="index01">
										<tr>
										<input type="hidden" name="jxid" value='${i.jgid}'/>
										<td name="jxArr">${i.jxmc}</td>
										<td>${i.fjxf}</td>
										<td>${i.xssx}</td>
										</tr>
										</logic:iterate>
								</tbody>
								</table>
								</div>
								</logic:notEmpty>
								<logic:empty name="xmjxList">
								��
								</logic:empty>
							</td>
						</tr>
						<logic:equal value="11032" name="xxdm">
					<tr>
					<th>
						�ɲ���ѧԺ
					</th>
					<td colspan="3">
						<logic:iterate id="t" name="xyList" indexId="index">
						<font style="width:100px;">
						${t.xymc}
						<%if((index+1)%5==0){%> </br> <%}%>
						</font>
						</logic:iterate>
					</td>
					</tr>
					</logic:equal>
					<tr><th width="20%">��Ŀ����
								</th>
							<td colspan="3">
								${rs.xmms}
							</td>
					</tr>
					<tr><th width="20%">��/�۷�����
								</th>
							<td colspan="3">
								${rs.dkfyj}
							</td>
					</tr>
					<tr><th width="20%">����Ҫ��
								</th>
							<td colspan="3">
								${rs.cyyq}
							</td>
					</tr>
				</tbody>
				</table>
				</div>
			  <div style="height:35px"></div>   
			  <div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
				<tfoot>
					<tr>
						<td colspan="4">
							<div class="btn">
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

