<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/gygl/xyzs/xyzssq/js/xyzssq.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			jQuery("input[name=zwjzyy]").each(function(){
				if(jQuery(this).val() == '002'){
					jQuery(this).addClass("fontstyl");
				}
			})

			var isopen = jQuery("#sqkg").val();
			var shzt = jQuery("#shzt").val();
			if('3' != shzt && (isopen==null||isopen==''||"0" == isopen)){
				jQuery("#btn_submit").hide();
			}
		})
		</script>
		<style type = "text/css">
			#xxdz{width:250px;}
			.fontstyl{float:left}
		</style>
	</head>
	<body>
		<html:form action="/gygl_xyzssqgl" method="post" styleId="XyzsSqForm">
			<input type="hidden" name="xxdm" id="xxdm" value="${xxdm}"/>
			<input type="hidden" id="sqkg" value="${sqkg}"/>
			<input type="hidden" id="shzt" value="${zsjgxx.shzt}"/>
			<html:hidden property="sqbh"/>
			<html:hidden property="xh" styleId="xh"/>
			<div style="tab;overflow-x:hidden;overflow-y:auto;height:520px;margin-bottom: 0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>������Ϣ</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
					<thead> 
						<tr>
							<th colspan="4">
								<span>У��ס����Ϣ</span>&nbsp;<lable style="line-height:20px">(${zsjgxx.xn}&nbsp;ѧ��)</lable>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th><font color="red">*</font>������ʼʱ��</th>
							<td>
								<html:text property="sqsjstart" styleId="start" onfocus="return showCalendar('start','y-mm-dd',true,'end');"  onblur="dateFormatChg(this);"/>
							</td>
							<th><font color="red">*</font>�������ʱ��</th>
							<td>
								<html:text property="sqsjend" styleId="end" onfocus="return showCalendar('end','y-mm-dd',false,'start');"  onblur="dateFormatChg(this);"/>
							</td>
						</tr>
						<tr>
							<th><font color="red">*</font>ѧ��</th>
							<td>
								<html:select   property="xl" styleId="xl"  style="width:150px;">
									<html:options collection="xl" property="xldm" labelProperty="xlmc" />
								</html:select>
								<%-- 
								<html:text property="xl" styleId="xl" maxlength="50" ></html:text>--%>
							</td>
							<th><font color="red">*</font>��ϵ�绰</th>
							<td>
								<html:text property="lxdh" styleId="lxdh" maxlength="12" onkeyup="checkInputLxfx(this)" ></html:text>
								
							</td>
						</tr>
						<tr>
							<th><font color="red">*</font>�ҳ���ϵ��ʽ</th>
							<td >
								<html:text property="parentslxdy" styleId="parentslxdy" maxlength="12" onkeyup="checkInputLxfx(this)"></html:text>
							</td>
								<th><font color="red">*</font>У��ס�޵���ϸ��ַ</th>
							<td>
								<html:text property="xxdz" styleId="xxdz" maxlength="25" ></html:text>
							</td>
						</tr>
						<tr>
							<th><font color="red">*</font>�����סԭ��</th>
							<td colspan="3">
								<div style="height:150px;">
									<logic:iterate id="zwjz"  name="zwjzyy">
							    		<html:radio idName="zwjz" property="zwjzyy" value="dm">
							          		${zwjz.mc}
							         	</html:radio><br/>
							 		</logic:iterate>	
								</div>
							</td>
						</tr>
						<tr>
							<th>��ע
								</br><font color="red">(��50��)</font></th>
							<td colspan="3">
								<html:textarea property="bz" styleId="bz" 
											   onkeyup="checkzs();"
											   style="width:99%;" rows="2"></html:textarea>
							</td>
						</tr>
						<tr>
							<th>
								<logic:equal value="13871" name="xxdm">
									<font color="red">*</font>
								</logic:equal>����
							</th>
							<td colspan="3">
								<html:hidden property="filepath" styleId="filepath"/>
								<%@ include file="/xsgzgl/comm/fileUpload/f.jsp"%>
								<script type="text/javascript">
									//���ø��� 
									jQuery(function(){
										jQuery.MultiUploader({
											maxcount : 3,
											//��׺
											accept : 'png|gif|jpg|zip|rar|doc|docx',
											//����ļ���С ��λM
											maxsize: 10,
											//��Ÿ������������id
											elementid : 'filepath'
											});
									});
								</script>						
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
								<div class="bz">
									"<span class="red">*</span>"Ϊ������
								</div>
								<div class="btn">
									<button type="button" onclick="saveZsjg('update');">
										����ݸ�
									</button>
									<button type="button" id="btn_submit" onclick="saveZsjg('updatesubmit');">
									         �ύ����
									</button>
									<button type="button" onclick="iFClose();">
										�ر�
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