<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript"
			src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?self=true"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/gygl/gzwpcmdjgl/js/gzwpcmdj.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
	</head>
	<body>
		<button id="search_go" type="button" style="display: none" onclick="reloadWindow();"></button>
		<html:form action="/gygl_gzwpcmdj" method="post" styleId="gzwpcmdjForm">
			<html:hidden property="gzwpdjid" value="${gzwpcmdjxx.gzwpdjid}"/>
			<div style="tab;overflow-x:hidden;overflow-y:auto;height:362px;margin-bottom: 0px;">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>�޸Ĺ�����Ʒ���ŵǼ�</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>
								ѧ��
							</th>
							<td>
								<html:hidden property="xh" styleId="xh" value="${gzwpcmdjxx.xh}"/>
								<span id="xh">${gzwpcmdjxx.xh}</span>
							</td>
							<th >����</th><td>	<span id="xm">${gzwpcmdjxx.xm}</span></td>
						</tr>
						
						<tr>
							<th >�Ա�</th><td>	<span id="xb">${gzwpcmdjxx.xb}</span></td>
							<th >ѧԺ����</th><td>	<span id="xymc">${gzwpcmdjxx.xymc}</span></td>
						</tr>
						
						<tr>
							<th >רҵ����</th><td>	<span id="zymc">${gzwpcmdjxx.zymc}</span></td>
							<th >�༶����</th><td>	<span id="bjmc">${gzwpcmdjxx.bjmc}</span></td>
						</tr>
						
						<tr>
							<th >¥������</th><td>	<span id="ldmc">${gzwpcmdjxx.ldmc}</span></td>	
							<th >���Һ�</th><td>	<span id="qsh">${gzwpcmdjxx.qsh}</span></td>			
						</tr>	
						
						<tr>
							<th >���֤��</th><td>	<span id="sfzh">${gzwpcmdjxx.sfzh}</span></td>	
							<th >
								<font color="red">* </font>��Ʒ����
							</th>
							<td>
								<html:text property="wpmc" styleId="wpmc" value="${gzwpcmdjxx.wpmc}" maxlength="30"></html:text>
							</td>		
						</tr>
						
						<tr>
							<th>
								<font color="red">* </font>����ʱ��
							</th>
							<td>
								<html:text property="cmsj" value="${gzwpcmdjxx.cmsj}" styleId="cmsj" style="width:145px;" onclick="return showCalendar(this.id,'yyyy-MM-dd HH:mm','','',250,10);"></html:text>
							</td>	
							<th >
								<font color="red">* </font>ֵ����Ա
							</th>
							<td>
								<html:text property="zbry" styleId="zbry"  maxlength="30" value="${gzwpcmdjxx.zbry}"></html:text>
							</td>	
						</tr>
						
						<tr>
							<th>
								��ע
								<br />
								<font color="red">(��500��)</font>
							</th>
							<td colspan="3" style="word-break: break-all;">
								<html:textarea property="bz" styleId="bz" style="width:95%;" rows="8" value="${gzwpcmdjxx.bz}"></html:textarea>
							</td>
						</tr>					
					</tbody>
				</table>
			</div>
			<div>
				<table class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button id="save_button" type="button"
										onclick="updateGzwpcmdjxx();">
										�� ��
									</button>
									<button type="button" name="�� ��" onclick="iFClose();">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
		</html:form>
	</body>
</html>

