<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<%@ include file="/syscommon/autocomplete.ini"%>
		<script type="text/javascript" src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?self=true"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script language="javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xpjpy/zjdxwdpj/pjjg/js/pjjg.js"></script>
		<script type="text/javascript">
		jQuery(function(){																								
			changeXmmc(false);
		});
		
		function changeXmmc(flg){
			if(flg){
				/*����*/
				jQuery("#xmmc").val("");
				jQuery("#xmje").val("");
			}
			
			/*ȡ�����ݱ�xg_pjpy_new_pjxmb; �ֶΣ�xmmc*/																							
			var autoSetting = {
				dataTable:"xg_zjdx_pjpy_pjxmb",
				dataField:"xmmc",
				dataFieldKey:"xmje",
				dataFieldKeyId:"xmje",
				sqlTj: getSqlTj,
				scrollHeight:135									
			}
			/*ģ��������������Ŀ���ơ�*/
			jQuery("#xmmc").setAutocomplete(autoSetting);
		}
		
		function getSqlTj(){
			var xn = jQuery("#xn").val();
			var xq = jQuery("#xq").val();
			var sqlTj = " and xn = '"+xn+"' ";
			return sqlTj;
		}
		</script>
	</head>
	<body>
		<html:form action="/xpjpy_pjjg" method="post" styleId="pjjgForm">
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
			<html:hidden property="id"/>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ��������Ϣ</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/xpjpy/zjdxwdpj/bdpz/selectStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>������Ŀ������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
				    		<th><span class="red">*</span>ѧ��</th>
							<td colspan="4">
								<html:select property="xn" styleId="xn" style="width:160px" onchange="changeXmmc(true);">
									<html:options collection="xnList" labelProperty="xn" property="xn" />
								</html:select>
							</td>
					    </tr>
					    <tr>
							<th><span class="red">*</span>��Ŀ����</th>
							<td>
								<html:select  property="lxdm" styleId="lxdm" style="width:160px">
								<html:option value="">---��ѡ��---</html:option>
								<html:options collection="xmlxList" labelProperty="lxmc" property="lxdm"/>
								</html:select>
							</td>
							<th><span class="red">*</span>��Ŀ����</th>
							<td>
								<html:select  property="xzdm" styleId="xzdm" style="width:160px">
								<html:option value="">---��ѡ��---</html:option>
								<html:options collection="xmxzList" labelProperty="xzmc" property="xzdm"/>
								</html:select>
							</td>
						</tr>
						<tr>
							<th><span class="red">*</span>��Ŀ����</th>
							<td>
								<html:text property="xmmc" styleId="xmmc" style="width:155px;" maxlength="20" styleClass="text_nor"></html:text>
							</td>
							<th>���</th>
							<td>
								<html:text property="xmje" styleId="xmje" style="width:155px;" maxlength="5" styleClass="text_nor" onblur="checkInputNum(this)"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>����ˮƽ
							</th>
							<td>
								 <html:text property="wysp"  styleId="wysp" maxlength="16" style="width:150px"></html:text>
							</td>
							<th>
								����绰
							</th>
							<td>
								 <html:text property="ssdh"  styleId="ssdh" maxlength="12" style="width:150px" onkeyup="checkInputLxfx(this)"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								������Ṥ��ְ��
							</th>
							<td>
								 <html:text property="gzzw"  styleId="gzzw" maxlength="32" style="width:150px"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								����ѧϰ����<br/>
								<font color="red">&lt;��200��&gt;</font>&nbsp;&nbsp;
							</th>
							<td colspan="3" style="word-break:break-all;">
								<html:textarea property="grxxjl" styleId="grxxjl" style="width:100%;" rows="3" onblur="checkLen(this,200);"></html:textarea>
							</td>
						</tr>
						<tr>
							<th>
								�μӿ������<br/>
								<font color="red">&lt;��200��&gt;</font>&nbsp;&nbsp;
							</th>
							<td colspan="3" style="word-break:break-all;">
								<html:textarea property="cjkyqk" styleId="cjkyqk" style="width:100%;" rows="3" onblur="checkLen(this,200);"></html:textarea>
							</td>
						</tr>
						<tr>
							<th>
								���轱��λ����ʶ<br/>
								<font color="red">&lt;��200��&gt;</font>&nbsp;&nbsp;&nbsp;
							</th>
							<td colspan="3" style="word-break:break-all;">
								<html:textarea property="dwrs" styleId="dwrs" style="width:100%;" rows="3" onblur="checkLen(this,200);"></html:textarea>
							</td>
						</tr>
						<tr>
							<th>������Ϣ</th>
							<td colspan="3">
								<html:hidden property="ylzd5" styleId="ylzd5" />
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
											elementid : 'ylzd5'
										});
									});
								</script>
							</td>
						</tr>
						<tr>	
							<th><span class="red">*</span>����ʱ��</th>
							<td colspan="3">
								<html:text property="sqsj"  styleId="sqsj" onclick="return showCalendar('sqsj','yyyy-MM-dd HH:mm:ss');" readonly="true" style="width:155px;"></html:text>
							</td>
					    </tr>
					    <tr>
							<th>
								��������
								<br /><font color="red">(��500��)</font>
							</th>
							<td colspan="3">
								<html:textarea property='sqly' style="width:98%" styleId="sqly" rows='8' onblur="checkLen(this,500);"/>
							</td>
			      		</tr>
					</tbody>
				</table>
			</div>
			<div style="height:30px"></div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"Ϊ������</div>
								<div class="btn">
									<button type="button" type="button" onclick="saveFormUpdate();">
										�� ��
									</button>
									<button type="button" type="button" onclick="iFClose();">
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