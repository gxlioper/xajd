<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f_q_href.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="xsgzgl/xpjpy/zjdxwdpj/xmsq/js/xmsq.js"></script>
		<script type="text/javascript" src="xsgzgl/xpjpy/zjdxwdpj/bdpz/js/bdpz.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				//��������ѡ��
				loadMkxxSelectOptions();
				//����radioѡ��
				loadMkxxRadioOptions();
				var xh = jQuery("#xh").val();
				if (xh != ""){
					showDialog("ѡ�����뽱��",500,400,"xpjpy_xmsq.do?method=selectPjxm&xh="+xh);
				}
			});

		</script>
	</head>
	<body>
		<html:form action="/xpjpy_xmsq" method="post" styleId="xmsqForm" onsubmit="return false;">
			<html:hidden property="id" />
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>������Ϣ</span>
							</th>
						</tr>
					</thead>
						<%@ include file="/xsgzgl/xpjpy/zjdxwdpj/bdpz/selectStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>
									���뽱��
									<a onclick="showDialog('ѡ�����뽱��',500,400,'xpjpy_xmsq.do?method=selectPjxm&xh=${jbxx.xh}');"
									   href="javascript:void(0);">
									  <font color="blue"><u>ѡ�����뽱��</u></font>
									</a>
								</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>��������</th>
							<td colspan="3">${cssz.xn}</td>
						</tr>
						<tr>
							<th>����</th>
							<td colspan="3">
								<table width="100%">
									<thead>
										<tr>
											<td width="20%">��������</td>
											<td width="10%">���</td>
											<td width="25%">�����</td>
											<td width="45%">����������д˵��</td>
										</tr>
									</thead>
									<tbody id="sqjx"></tbody>
								</table>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>����ˮƽ
							</th>
							<td>
								 <html:text property="wysp"  styleId="wysp" maxlength="16" style="width:150px" value="${latestSqxx.wysp}"></html:text>
							</td>
							<th>
								����绰
							</th>
							<td>
								 <html:text property="ssdh"  styleId="ssdh" maxlength="12" style="width:150px" value="${latestSqxx.ssdh}" onkeyup="checkInputLxfx(this)"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								������Ṥ��ְ��
							</th>
							<td>
								 <html:text property="gzzw"  styleId="gzzw" maxlength="32" style="width:150px" value="${latestSqxx.gzzw}"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								����ѧϰ����<br/>
								<font color="red">&lt;��200��&gt;</font>&nbsp;&nbsp;
							</th>
							<td colspan="3" style="word-break:break-all;">
								<html:textarea property="grxxjl" styleId="grxxjl" style="width:100%;" value="${latestSqxx.grxxjl}" rows="3" onblur="checkLen(this,200);"></html:textarea>
							</td>
						</tr>
						<tr>
							<th>
								�μӿ������<br/>
								<font color="red">&lt;��200��&gt;</font>&nbsp;&nbsp;
							</th>
							<td colspan="3" style="word-break:break-all;">
								<html:textarea property="cjkyqk" styleId="cjkyqk" style="width:100%;" value="${latestSqxx.cjkyqk}" rows="3" onblur="checkLen(this,200);"></html:textarea>
							</td>
						</tr>
						<tr>
							<th>
								���轱��λ����ʶ<br/>
								<font color="red">&lt;��200��&gt;</font>&nbsp;&nbsp;&nbsp;
							</th>
							<td colspan="3" style="word-break:break-all;">
								<html:textarea property="dwrs" styleId="dwrs" style="width:100%;" value="${latestSqxx.dwrs}" rows="3" onblur="checkLen(this,200);"></html:textarea>
							</td>
						</tr>
						<tr id="fjxxid">
							<th>
								������Ϣ
							</th>
							<td colspan="3">
								<html:hidden property="fjxx" styleId="fjxx" />
								<%@ include file="/xsgzgl/comm/fileUpload/f.jsp"%>
								<script type="text/javascript">
									//���ø��� 
									jQuery(function(){
										jQuery.MultiUploader({
											maxcount : 3,
											//��׺
											accept : 'png|gif|jpg|zip|rar|doc|docx|pdf',
											//����ļ���С ��λM
											maxsize: 10,
											//��Ÿ������������id
											elementid : 'fjxx'
										});
									});
								</script>
							</td>
						</tr>
						<tr>
							<th>
								<span class="red">*</span>��������
								<br /><font color="red">&lt;��500��&gt;</font>
							</th>
							<td colspan="3" style="word-break:break-all;">
								<html:textarea property="sqly" styleId="sqly" style="width:100%;" rows="5" onblur="checkLen(this,500);"></html:textarea>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div style="height: 30px"></div>
			<div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<%@ include file="/xsgzgl/comm/fileUpload/f_q.jsp"%>
								<script type="text/javascript">

								</script>
								<div class="bz">"<span class="red">*</span>"Ϊ������</div>
								<div class="btn">
									<button type="button" onclick="saveXmsq('save');return false;">
										����ݸ�
									</button>
									<button type="button" onclick="saveXmsq('submit');return false;">
										�ύ����
									</button>
									<button type="button"  onclick="iFClose();" id="buttonClose">
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