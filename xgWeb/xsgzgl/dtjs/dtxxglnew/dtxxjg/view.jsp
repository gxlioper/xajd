<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/dtjs/dtxxglnew/js/editorview.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/browser/js/browser.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f_q.js"></script>
		<script type="text/javascript">
			jQuery(function() {
			<logic:equal value="13871" name="xxdm">
				var jdmc=jQuery("#jdmc").text().trim();
				if("Ԥ����Ա"==jdmc){
					jQuery("[name='rdzys'").show();
				}
				</logic:equal>
			});
		</script>
	</head>
	<body>
		<html:form method="post" styleId="form" action="/dtxxjg">
		<%@ include file="/comm/hiddenValue.jsp"%>
		<input type="hidden" id="ishave" value="${ishave }"/>
			<div id="bj">
				<table width="98%" border="0" class="formlist">
					<tbody>
						<tr>
							<th align="right" width="20%">
								����׶�����
							</th>
							<td align="left">
								<label id="jdmc">${data.jdmc}</label>
							</td>
						</tr>
						<tr>
							<th hidden align="right" width="10%" name="rdzys">
							�뵳־Ը����
							</th>
							<td hidden align="left"  name="rdzys">
								${data.zd3}
							</td>
						</tr>
						<tr>
							<th align="right" width="20%">
								��ʼʱ��
							</th>
							<td align="left">
								${data.kssj}
							</td>
						</tr>
						<logic:equal name="dxjy" value="true">
							<tr>
								<th align="right" width="20%">
									��ҵʱ��
								
								</th>
								<td align="left">
								${data.zd1}
							    </td>
							</tr>
							<tr>
								<th align="right" width="20%">
									�ɼ�¼��&nbsp;
									<br />
								</th>
								<td>
									${data.zd2}
								</td>
							</tr>
						</logic:equal>
						<logic:equal name="ishave" value="true">
							<tr style="height: 200px;">
								<th align="right" width="20%">
									����С��&nbsp;
								</th>
								<td>
									${data.grxj}
								</td>
							</tr>
								<tr>
									<th align="right" width="10%">
										������Ϣ
									</th>
									<td>
										<div id="commonfileupload-list-0" style="padding: 5px;"></div>
										<script type="text/javascript">
											//���ø��� 
											jQuery(function(){
												var gid = "${data.zd5}";
												jQuery.MultiUploader_q({
													gid : gid,
													targetEl : 'commonfileupload-list-0'
													});
											});
										</script>
									</td>
								</tr>
						</logic:equal>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="2">
								<div class="btn">
									&nbsp;&nbsp;&nbsp;&nbsp;
									<button type="button" onclick="editClose();" id="buttonClose">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
		</html:form>
	</body
</html>