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
		<script type="text/javascript" src="xsgzgl/axcs/wpjg/js/wpjg.js"></script>
		<script	type="text/javascript">

		function getJllbList(obj){
			jQuery.post('axcswpjg.do?method=getWpmcList',{xn:obj.value},function(data){
					var option ;
					for(var i = 0; i < data.length; i++){
						option +="<option value='"+data[i].xmdm+"'>"+data[i].xmmc+"</option>";
					}
					jQuery('#xmdm').empty().append(option);			
			},'json');
		}
		
		function saveForm(){
			if(!checkNull("xh-xn-xmdm-sqly")){
				return false;
			}
			if (jQuery("#sqly").val().length>500) {
				showAlert("���������������500�֣�");
				return false;
			}
			var	 url = "axcswpjg.do?method=wpjgXg&type=update";
		     ajaxSubFormWithFun("WpjgForm",url,function(data){
		    	 if(data["message"]=="����ɹ���"){
		    		 showAlert(data["message"],{},{"clkFun":function(){
							if (parent.window){
								refershParent();
							}
						}});
		    	 }else{
		    		 showAlert(data["message"]);
		    	 }
				});
		  }
		</script>
	</head>
		<body>
		<html:form action="/axcswpjg" method="post" styleId="WpjgForm">
			<html:hidden property="jgid"  styleId="jgid"/>
			<html:hidden property="xh" styleId="xh" />
			
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ��������Ϣ</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/rcsw/lstd/comm/viewStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<tr>
							<th><span class="red">*</span>ѧ��</th>
							<td>
								<html:select  property="xn" styleId="xn" style="width:130px" onchange="getJllbList(this)">
								<html:options collection="xnList" labelProperty="xn" property="xn"/>
								</html:select>
							</td>
							<th rowspan="4">
								��ƷͼƬ
							</th>
							<td rowspan="4" id="xmtp">
							<div id="wpImg" class="open_img" style="margin-left:0px;margin-top: 0px;height: 130px">
					         <img style="width:180px;height:128px" id="axwptp"
						src="axcsWpsz.do?method=showPhoto&xmdm=${rs.xmdm}&type=view"
						border="0">
				           </div>
							</td>
							</tr>
							<tr>
							<th><span class="red">*</span>ר����Ʒ����</th>
							<td>
								<html:select  property="xmdm" styleId="xmdm" style="width:130px" onchange="getWpxx(this)">
								<html:options collection="wpmcList" labelProperty="xmmc" property="xmdm"/>
								</html:select>
							</td>
					    </tr>
					     <tr>
							<th width="20%">
								<bean:message key="lable.axcswplb" />
							</th>
							<td id="xmlbid">
								${rs.xmlbmc}
							</td>
						</tr>
					    <tr>
							<th>����ʱ��</th>
							<td>
								<html:hidden property="sqsj" />
								<bean:write name="WpjgForm" property="sqsj"/>
							</td>
					    </tr>
					    <tr>
							<th>
								<span class="red">*</span>�������� <br /><font color="red">(��500��)</font>
							</th>
							<td colspan="3">
								<html:textarea property='sqly' style="width:98%" styleId="sqly" rows='7' onblur="checkLen(this,100);"/>
							</td>
			    	  </tr>
					</tbody>
				</table>
			</div>
				<table width="100%" border="0" class="formlist"
					style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"Ϊ������</div>
								<div class="btn">
									<button type="button" type="button" onclick="saveForm();">
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

