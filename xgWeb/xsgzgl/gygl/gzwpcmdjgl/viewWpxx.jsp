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
	</head>
	<body>
		<button id="search_go" type="button" style="display: none" onclick="reloadWindow();"></button>
		<html:form action="/gygl_gzwpcmdj" method="post" styleId="gzwpcmdjForm">
			<html:hidden property="gzwpdjid" value="${gzwpcmdjxx.gzwpdjid}"/>
			<div style=''>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>������Ʒ���ŵǼ���Ϣ�鿴</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="20%">ѧ��</th><td width="30%">	<span id="xh">${gzwpcmdjxx.xh}</span></td>
							<th width="20%">����</th><td width="30%">	<span id="xm">${gzwpcmdjxx.xm}</span></td>
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
								��Ʒ����
							</th>
							<td>
								<span id="wpmc">${gzwpcmdjxx.wpmc}</span>
							</td>		
						</tr>
						
						<tr>
							<th>
								����ʱ��
							</th>
							<td>
								<span id="cmsj">${gzwpcmdjxx.cmsj}</span>
							</td>	
							<th >
								ֵ����Ա
							</th>
							<td>
								<span id="zbry">${gzwpcmdjxx.zbry}</span>
							</td>	
						</tr>
						<tr>
							<th>
								��ע
							</th>
							<td colspan="3" style="word-break: break-all;">
								<span id="bz">${gzwpcmdjxx.bz}</span>
							</td>
						</tr>						
					</tbody>
					
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
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

