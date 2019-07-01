<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript">
		function displayQzsj(){
			var pjkg = jQuery(":radio:checked").val();
			//����
			if (pjkg == 1){
				jQuery("#qzsjTr").show();
			} else {
				//�ر�
				jQuery("#qzsjTr").hide();
			}
		}
		
		//�����������
		function saveCssz(zdKey,zdValue){
			if (zdValue != null){
				jQuery.post("xpj_cssz.do?method=saveCssz",{"zdKey":zdKey,"zdValue":zdValue},function(data){
					if(data["message"] != null){
						alert(data["message"]);
					}
				});
			}
		}
		
		jQuery(function(){
			displayQzsj();
			
			jQuery(":radio").bind("click",function(){
				saveCssz("pjkg",jQuery(this).val());
			});
			
			jQuery("#pjzq").bind("change",function(){
				jQuery.ajaxSetup({async:false});
				
				if (jQuery(this).val() == ""){
					return ;
				}
				
				saveCssz("pjzq",jQuery(this).val());
				var frameWindow = new ForceWindow("zcxmFrame");
				frameWindow.open("xpj_zcxm.do?method=viewZcxm");
			});
		});
		</script>
	</head>
	<body>
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv()">ʹ�ð���</a>
			</p>
		</div>
		<!-- ���� end-->
		<!-- ��ʾ��Ϣ -->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>��ʾ��</span>
			</h3>
			<p>
				<span>
					1����ǰҳ���û��������ݻἴʱ������Ч;<br/>
					2���۲���Ŀ�����ֺ���С�������۲��ά���������ơ�
				</span>
			</p>
			<a class="close" title="����" onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- ��ʾ��Ϣ end-->
		<html:form action="/xpj_cssz" method="post" styleId="csszForm">
			
			<div class='tab'>
				<table width="100%" border="0" class="formlist">
					
					<thead>
						<tr>
							<th colspan="4">
								<span>��������</span>
							</th>
						</tr>
					</thead>
					<tbody>
					    <tr>
							<th width="16%">��������</th>
							<td width="34%">
								<logic:iterate id="k" name="pjkgList">
									<lable><html:radio property="pjkg" value="${k.dm }" onclick="displayQzsj();"></html:radio>${k.mc }</lable>
								</logic:iterate>
							</td>
							<th width="16%">��������</th>
							<td width="34%">
								<html:select property="pjzq" styleId="pjzq">
									<html:option value=""></html:option>
									<html:options collection="pjzqList" property="zqdm" labelProperty="zqmc"/>
								</html:select>
							</td>
					    </tr>
					    <tr id="qzsjTr">
							<th>��ֹʱ��</th>
							<td colspan="3">
								<html:text  property="kssj" styleId="kssj"
											onfocus="showCalendar('kssj','yyyy-MM-dd HH:mm',true,'jssj');" 
											onchange="saveCssz('kssj',this.value)"
											readonly="true"
											></html:text>
								��
								<html:text  property="jssj" styleId="jssj"
											onfocus="showCalendar('jssj','yyyy-MM-dd HH:mm',false,'kssj');" 
									 		onchange="saveCssz('jssj',this.value)"
									 		readonly="true"
									 		></html:text>
							</td>
					    </tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>�۲���Ŀ</span>
								<div style="margin-left: 500px;">
								<logic:equal value="1" name="szyf">
								<button class="btn_common"type="button"
												onclick="showDialog('�����·�',550,180,'xpj_zcxm.do?method=showYf');"
										>
											�����·�
										</button>
								</logic:equal>
								<logic:equal value="1" name="zcxxb">
									<logic:equal value="1" property="zcxmjb" name="xpjCsszModel">
										<button class="btn_common"  type="button"
												onclick="showDialog('���꼶��ϸ����',750,380,'xpj_zcxm.do?method=showXxbl');"
										>
											���꼶��ϸ����
										</button>
									</logic:equal>
									<logic:equal value="2" property="zcxmjb" name="xpjCsszModel">
										<button class="btn_common"  type="button"
												onclick="showDialog('��Ժϵ��ϸ����',750,480,'xpj_zcxm.do?method=showXxbl');"
										>
											��Ժϵ��ϸ����
										</button>
									</logic:equal>
								</logic:equal>
								</div>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="4">
								<iframe src="xpj_zcxm.do?method=viewZcxm"
										width="100%" id="zcxmFrame" name="zcxmFrame"
										height="550px"
										frameborder="0" marginwidth="0" marginheight="0"
										scrolling="auto">
								></iframe>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</html:form>
	</body>
</html>

