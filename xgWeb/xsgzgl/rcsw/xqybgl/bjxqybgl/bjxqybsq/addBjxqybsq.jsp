<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?self=true"></script>		
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script language="javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		
		<script type="text/javascript" src="js/calendar/calendar.js"></script>		
		<script	type="text/javascript">		
			
			function saveForm(type){
				
				var yf = jQuery("#yf").val();
				var bjmc = jQuery("#bjmc").val();
				var bygzkzqk = jQuery("#bygzkzqk").val();
				var xsgzrd = jQuery("#xsgzrd").val();
				var xssxdt = jQuery("#xssxdt").val();
				var xstsjgzjy = jQuery("#xstsjgzjy").val();	

				var checkids = "yf-bjmc-bygzkzqk-xsgzrd-xssxdt-xstsjgzjy";
				
				if(!checkNotNull(checkids)){
					showAlert("�뽫��<font color='red'>*</font>����Ŀ��д����!");
					return false;
				}
				
				if (bygzkzqk.length > 1000){
					showAlert("���¹�����չ������1000�֣�");
					return false;
				}
								 
				if (xsgzrd.length > 1000){
					showAlert("ѧ����ע�ȵ����1000�֣�");
					return false;
				}
								
				if (xssxdt.length > 1000){
					showAlert("ѧ��˼�붯̬���1000�֣�");
					return false;
				}
				
				if (xstsjgzjy.length > 1000){
					showAlert("ѧ�����󼰹����������1000�֣�");
					return false;
				}
				
				var url = "rcsw_xqybgl_bjxqybgl_bjxqybsqgl.do?method=addBjxqybsq&type="+type;
		      	ajaxSubFormWithFun("bjxqybsqForm",url,function(data){
		    	 if(data["message"]=="����ɹ���" || data["message"]=="�ύ�ɹ���"){
		    		 showAlert(data["message"],{},{"clkFun":function(){
							if (parent.window){
								refershParent();
							}
						}});
		    	 }else{
		    		 showAlert(data["message"]);
		    	 }});
		  	}
		  	
		</script>
		
	</head>
	<body>
		
		<html:form action="/rcsw_xqybgl_bjxqybgl_bjxqybsqgl" method="post" styleId="bjxqybsqForm">
			
			
			<div style='tab;width:100%;height:415px;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>�±���Ϣ</span>
							</th>
						</tr>
					</thead>
					
					<tbody>
					    <tr>
							<th align="right" width="20%">
									ѧ��
							</th>
							<td align="left" width="30%" >
								${xn}
							</td>
							<th align="right" width="20%">
									ѧ��
							</th>							
							<td align="left" width="30%">
								${xq}
							</td>
					    </tr>
					    <tr>
					    	<th align="right" width="20%">
									��д��
							</th>
							<td align="left" width="30%">
								${txr}						
							</td>
							<th align="right" width="20%">
								<span class="red">*</span>�·�
							</th>
							<td >
									
								<html:text  property="yf" styleId="yf"   size="10"
									onclick="return showCalendar('yf','yyyy-MM');" 
									 readonly="true"></html:text>				    	
							</td>
					    </tr>
					   <tr>
							<th align="right" width="20%">
							<span class="red">*</span>�༶
							</th>
							<td width="80%" colspan="3">
								<input type="text" id="bjmc" value="" style="width:200px;" readonly="readonly" title=""/>
								<input type="hidden" name="bjdm" id="bjdm" value=""/>
								<button class="btn_01" type="button" onclick="showDialog('��ѡ��һ���༶',800,500,'rcsw_xqybgl_bjxqybgl_bjxqybsqgl.do?method=bjManage');return false;">ѡ��</button>
								<span id="bj_span"></span>
							</td>
					    </tr>
					    <tr>
							<th align="right" width="20%">
								<span class="red">*</span>���¹�����չ���
								<br /><font color="red">&lt;��1000��&gt;</font>
							</th>
							<td colspan="3">
								<html:textarea property='bygzkzqk' style="width:98%" styleId="bygzkzqk" rows='5' onblur="checkLen(this,1000);"/>
							</td>
			      		</tr>
			      		<tr>
							<th align="right" width="20%">
								<span class="red">*</span>ѧ����ע�ȵ� 
								<br /><font color="red">&lt;��1000��&gt;</font>
							</th>
							<td colspan="3">
								<html:textarea property='xsgzrd' style="width:98%" styleId="xsgzrd" rows='5' onblur="checkLen(this,1000);"/>
							</td>
			      		</tr>
			      		<tr>
							<th align="right" width="20%">
								<span class="red">*</span>ѧ��˼�붯̬ 
								<br /><font color="red">&lt;��1000��&gt;</font>
							</th>
							<td colspan="3">
								<html:textarea property='xssxdt' style="width:98%" styleId="xssxdt" rows='5' onblur="checkLen(this,1000);"/>
							</td>
			      		</tr>
			      		<tr>
							<th align="right" width="20%">
								<span class="red">*</span>ѧ�����󼰹������� 
								<br /><font color="red">&lt;��1000��&gt;</font>
							</th>
							<td colspan="3">
								<html:textarea property='xstsjgzjy' style="width:98%" styleId="xstsjgzjy" rows='5' onblur="checkLen(this,1000);"/>
							</td>
			      		</tr>
					</tbody>
				</table>
			</div>
				<table width="100%" border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"Ϊ������</div>
								<div class="btn">
									<button type="button" type="button" onclick="saveForm('save');">
										����ݸ�
									</button>
									
									<button type="button" type="button" onclick="saveForm('submit');">
										�ύ����
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

