<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xsztz/xstzxm/jg/js/xmjg.js"></script>
		<script type="text/javascript">
	
			jQuery(function(){
				jQuery("#xh").attr({readonly:"readonly"});
				jQuery("#selhd").unbind('click').bind('click', function(){
					if(jQuery("#xh").val() == ''){
						showAlert("������д������Ϣ��");
						return false;
					}else{
					  var url = "xmsqgl_xmjg.do?method=getXmSelectList&xh="+jQuery("#xh").val()+"&flag=jg";
					  showDialog('��ѡ����Ŀ',600,400,url);
					}
			   });
			});
			function check(){
				var xh = jQuery('#xh').val();
				var xmdm = jQuery('#xmdm').val();
				var xn = jQuery("input[name = 'xn']").val();
				var xq = jQuery("input[name = 'xq']").val();
				if(xmdm == ''){
					return false;
				}
				var flag = true;
				var url = "xmsqgl_xmjg.do?method=check&xh="+xh+"&xmdm="+xmdm+"&xn="+xn+"&xq="+xq;
				jQuery.ajax({   
			           type:"POST",
			           url:url,  
			           dataType:"json",  
			           async:false,
			           success:function(data){
			         	  if(data["flag"] == "1"){
				         	  flag = false;
			         	  }else{
				         	  flag = true;
			         	  }
			           },
		               error:function(data){
			        	   showAlert("��������");
			           }
			   });
			   if(flag == false){
				   showAlert("�����ظ���д���룡");
				   return false;
			   }
			}
		</script>
		<style type = "text/css">
			#xxdz{width:250px;}
			.fontstyl{float:left}
			.fontstyl1{margin-left:5px}
		</style>
	</head>
	<body>
		<html:form action="/xmsqgl_xmjg" method="post" styleId="XsXmJgForm">
			<input type="hidden" id="xmdm" name="xmdm"/>
			<input type="hidden" name="xn"/>
			<input type="hidden" name="xq"/>
			<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom:0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>������Ϣ</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/selectStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>��Ŀ��Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th><font color="red">*</font>��Ŀ����</th>
							<td>
								<input name="xmmc" id="xmmc" readonly="readonly" />
								<button class="btn_01" id="selhd" type="button" >ѡ��</button>
							</td>
							<th>��Ŀ����</th>
							<td id="xmjbmc" >
							  
                             </td>
						</tr>
						<tr>
							<th>ѧ��</th>
							<td id="xn" >
							</td>
							<th>ѧ��</th>
							<td id="xq" >
							</td>
						</tr>
						<tr>
							<th>�걨����</th>
							<td id="sbbmmc" >
							</td>
							<th>��ϵ��ʽ</th>
							<td id="lxdh" name="lxdh">
							</td>
						</tr>
						<tr>
							<th>������Ŀ</th>
							<td id="sskmmc" name="sskmmc">
							</td>
							<th>����ѧ��</th>
							<td id="jcxf" name="jcxf">
							</td>
						</tr>
						<tr>
							<th>�ɲ�������</th>
							<td id="kcyrs" name="kcyrs">
							</td>
							<th>����������</th>
							<td id="sqrs" name="sqrs">
							</td>
						</tr>
						<tr>
							<th>��ͨ������</th>
							<td id="tgrs" name="tgrs">
							</td>
							<th>��Ŀ��ʼʱ��</th>
							<td id="xmkssj" name="xmkssj">
							</td>
						</tr>
						<tr>
							<th>��������</br><font color="red">(��500��)</font></th>
							<td colspan="3">
								<html:textarea property="sqly" styleId="sqly" 
								   onkeyup="checkzs();" 
								   style="width:99%;" rows="3"></html:textarea>
							</td>
						</tr>
					</tbody>
				</table>
				</div>	
				<div style="height:30px;"></div>
				<%--;height:520px --%>
			<div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"<span class="red">*</span>"Ϊ������
								</div>
								<div class="btn">
									<button type="button" id="bc" onclick="saveSqjg('save');">
										��    ��
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