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
		<script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/jmf.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f.js"></script>
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
		<input type="hidden" id="dxjy" value="${dxjy }"/>
		<input type="hidden" id="zjsydxjy" value="${zjsydxjy }"/>
			<div id="bj">
				<table width="80%" border="0" class="formlist">
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
								<input type="text" id="zd3"  maxlength="30"  value="${data.zd3}"/>
							</td>
						</tr>
						<tr>
							<th align="right" width="20%">
								��ʼʱ��
							</th>
							<td align="left">
								<input type="text" id="kssj" onfocus="return showCalendar(this.id,'yyyy-MM-dd',true);" value="${data.kssj}"/>
							</td>
						</tr>
						<logic:equal name="dxjy" value="true">
							<tr>
								<th align="right" width="20%">
									��ҵʱ��
								
								</th>
								<td align="left">
								<input type="text" id="zd1" onfocus="return showCalendar(this.id,'yyyy-MM-dd',true);" value="${data.zd1}"/>
							</td>
							</tr>
							<tr>
								<th align="right" width="20%">
									�ɼ�¼��&nbsp;
									<br />
								</th>
								<td>
									<html:text property="zd2" styleId="zd2" maxlength="3" onkeyup="value=value.replace(/[^\d]/g,'')" value="${data.zd2}"></html:text>
								</td>
							</tr>
						</logic:equal>
						<logic:equal name="ishave" value="true">
							<tr>
								<th align="right" width="20%">
									����С��&nbsp;
									<br />
									<font color="red">(��2000��)</font>
								</th>
								<td>
									<html:textarea rows="5" property="grxj" styleId="grxj"
										style="width:80%" onblur="checkLen(this,2000);" value="${data.grxj}"></html:textarea>
								</td>
							</tr>
								<tr>
									<th align="right">
										������Ϣ
									</th>
									<td>
										<html:hidden property="zd5" styleId="zd5" />
										<input type="file" id="filepath_f" name="zd5" />
										<script type="text/javascript">
											//���ø��� 
											jQuery(function(){
												jQuery('#filepath_f').multiUploader({
													maxcount : 3,
													//��׺
													accept : 'png|gif|jpg|zip|rar|doc|docx',
													//����ļ���С ��λM
													maxsize: 10,
													//��Ÿ������������id
													elementid : 'zd5',
		
													eid : 'filepath_f'
													});
											});
										</script>
									</td>
								</tr>
						</logic:equal>
						<logic:equal name="zjsydxjy" value="true">
						   <tr>
								<th align="right" width="30%">
									��ʱ�εزμӵ�У��ѵ
								</th>
								<td>
									<html:text property="zd8" styleId="zd8" maxlength="100" value="${data.zd8}"></html:text>
								</td>
							</tr>
							<tr>
								<th align="right" width="20%">
									��ϵ��
								</th>
								<td>
									<html:text property="zd9" styleId="zd9" maxlength="100" value="${data.zd9}"></html:text>
								</td>
							</tr>
							<tr>
								<th align="right" width="20%">
									־Ը����
								</th>
								<td>
									<html:text property="zd10" styleId="zd10" maxlength="100" value="${data.zd10}"></html:text>
								</td>
							</tr>
						</logic:equal>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="2">
								<div class="btn">
									<button type="button" onclick="editOk();" id="buttonSave">
										�� ��
									</button>
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