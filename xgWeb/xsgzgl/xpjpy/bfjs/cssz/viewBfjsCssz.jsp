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
		function saveBfjsCssz(zdKey,zdValue){
			
			if (zdValue != null){
				jQuery.post("xpjpyBfjsCssz.do?method=saveBfjsCssz",{"zdKey":zdKey,"zdValue":zdValue},function(data){
					if(data["message"] != null){
						alert(data["message"]);
					}
				});
			}
		}
		
		jQuery(function(){
			displayQzsj();
			
			jQuery(":radio").bind("click",function(){
				saveBfjsCssz("sqkg",jQuery(this).val());
			});
			
			jQuery("#pdzq").bind("change",function(){
				jQuery.ajaxSetup({async:false});
				
				if (jQuery(this).val() == ""){
					return ;
				}
				
				saveBfjsCssz("pdzq",jQuery(this).val());
				var frameWindow = new ForceWindow("jsxmFrame");
				frameWindow.open("xpjpyBfjsJsxm.do?method=viewBfjsJsxm");
				jQuery.ajaxSetup({async:true});
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
					2��������Ŀ�����ֺ���С�����ھ�����ά���������ơ�
				</span>
			</p>
			<a class="close" title="����" onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- ��ʾ��Ϣ end-->
		<html:form action="/xpjpyBfjsCssz" method="post" styleId="BfjsCsszModel">
			
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
							<th width="16%">���뿪��</th>
							<td width="34%">
								<logic:iterate id="k" name="sqkgList">
									<lable><html:radio property="sqkg" value="${k.dm }" onclick="displayQzsj();"></html:radio>${k.mc }</lable>
								</logic:iterate>
							</td>
							<th width="16%">��������</th>
							<td width="34%">
								<html:select property="pdzq" styleId="pdzq">
									<html:option value=""></html:option>
									<html:options collection="pdzqList" property="zqdm" labelProperty="zqmc"/>
								</html:select>
							</td>
					    </tr>
					    <tr id="qzsjTr">
							<th>��ֹʱ��</th>
							<td colspan="3">
								<html:text  property="kssj" styleId="kssj"
											onfocus="showCalendar('kssj','yyyy-MM-dd HH:mm',true,'jssj');" 
											onchange="saveBfjsCssz('kssj',this.value)"
											readonly="true"
											></html:text>
								��
								<html:text  property="jssj" styleId="jssj"
											onfocus="showCalendar('jssj','yyyy-MM-dd HH:mm',false,'kssj');" 
									 		onchange="saveBfjsCssz('jssj',this.value)"
									 		readonly="true"
									 		></html:text>
							</td>
					    </tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>������Ŀ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="4">
								<iframe src="xpjpyBfjsJsxm.do?method=viewBfjsJsxm"
										width="100%" id="jsxmFrame" name="jsxmFrame"
										height="550px"
										frameborder="0" marginwidth="0" marginheight="0"
										scrolling="auto">
								</iframe>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</html:form>
	</body>
</html>

