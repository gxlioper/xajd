<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xsztz/xstzxm/sq/js/xsxmsqsearch.js"></script>
		<script type="text/javascript">
	
			jQuery(function(){
				jQuery("#xh").attr({readonly:"readonly"});
				jQuery("#selhd").unbind('click').bind('click', function(){
					if(jQuery("#xh").val() == ''){
						showAlert("������д������Ϣ��");
						return false;
					}else{
					  var url = "xmsqgl_xmsq.do?method=getXmSelectList&xh="+jQuery("#xh").val()+"&flag=sq";
					  showDialog('��ѡ����Ŀ',600,400,url);
					}
			   });
			});
		</script>
		<style type = "text/css">
			#xxdz{width:250px;}
			.fontstyl{float:left}
			.fontstyl1{margin-left:5px}
		</style>
	</head>
	<body>
		<html:form action="/xmsqgl_xmsq" method="post" styleId="XsXmSqForm">
			<input type="hidden" id="xmdm" name="xmdm"/>
			<input type="hidden" name="xn"/>
			<input type="hidden" name="xq"/>
			<input type="hidden" name="splc" id="splc"/>
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
								<input type="text" name="xmmc" value="" id="xmmc" style="width:120px;"  readonly="readonly"/>
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
							<th><font color="red">*</font>��������</br><font color="red">(��500��)</font></th>
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
										����ݸ�
									</button>
									<button type="button" id="tjsq" onclick="saveSqjg('submit');">
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