<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
		<script type='text/javascript' src="js/uicomm.js"></script>
		<script type="text/javascript" src="xsgzgl/axcs/wpsq/js/wpsqJs.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
			jQuery(function(){

			});
			function selectAxwp(){
				if(null==jQuery("#xh").val()||""==jQuery("#xh").val()){
					showAlertDivLayer("����ѡ��һ��ѧ����")
					return false;
				}
				showDialog('ѡ���ĳ�����Ʒ',500,350,'axcswpsqjs.do?method=selectWp&xh=${jbxx.xh}');

				}
		</script>
	</head>
	<body>
		<html:form action="/axcswpsqjs" method="post" styleId="WpsqJsForm" onsubmit="return false;">
			<html:hidden property="sqid" />
			<html:hidden property="xmdm" styleId="xmdm" value="${rs.xmdm}" />
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>������Ϣ</span>
							</th>
						</tr>
					</thead>
						<%@ include file="/xsgzgl/axcs/comm/selectStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>
									���밮�ĳ�����Ʒ��Ϣ
									&nbsp;&nbsp;
									<a onclick="selectAxwp();return false;" 
									   href="javascript:void(0);">
									   <font color="blue"><u>���밮�ĳ�����Ʒ��Ϣ</u></font>	
									</a>
								</span>
							</th>
						</tr>
					</thead>
					<tbody>
					<tr>
							<th width="20%">
								<span class="red">*</span>
								<bean:message key="lable.axcswpmc" />
							</th>
							<td>
								${rs.xmmc}
							</td>
							<th width="20%" rowspan="4">
								��ƷͼƬ
							</th>
							<td rowspan="4">
							<div id="wpImg" class="open_img" style="margin-left:0px;margin-top: 0px;height: 130px">
					         <img style="width:180px;height:128px" id="axwptp"
						src="axcsWpsz.do?method=showPhoto&xmdm=${rs.xmdm}&type=view"
						border="0">
				           </div>
							</td>
						</tr>
						<tr>
							<th width="20%">
								<bean:message key="lable.axcswplb" />
							</th>
							<td>
								${rs.xmlbmc}
							</td>
						</tr>
						<tr>
							<th width="20%">
								<bean:message key="lable.axcswpxxjs" />
							</th>
							<td>
								${rs.xmxxjs}
							</td>
						</tr>
						<tr>
							<th width="20%">
								��������
							</th>
							<td>
								${rs.sqtj}
							</td>
						</tr>
						<tr>
							<th>
								<span class="red">*</span>��������
								</br><font color="red">(��500��)</font>
							</th>
							<td colspan="3" style="word-break:break-all;">
								<div id="txsmDiv" style="color:red"></div>
								<html:textarea property="sqly" styleId="sqly" style="width:95%;" rows="5" onkeypress="checkLen(this,200);"></html:textarea>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">	
				<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"Ϊ������</div>
								<div class="btn">
									<button type="button" onclick="saveWpsq('save');return false;">
										����ݸ�
									</button>
									<button type="button" onclick="saveWpsq('submit');return false;">
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
		</html:form>
	</body>
</html>

