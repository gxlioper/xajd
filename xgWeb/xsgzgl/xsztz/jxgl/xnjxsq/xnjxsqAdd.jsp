<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xsztz/jxgl/xnjxsq/js/xnjxsq.js"></script>
		<script type="text/javascript">
			function jifen(){
				var fen = jQuery("#jcxf").html();
				var jichu = jQuery("#jxid").val();
				jQuery("input[name='jxId']").each(function(i,n){
					if(jQuery(n).val()==jichu){
						jQuery("#zxf").html(Number(fen)+Number((jQuery(n).parent().find("td").eq(1).html())));
						return false;
					}
				})
				return false;
			}
			jQuery(function(){
				jifen();
				jQuery("#xh").attr({readonly:"readonly"});
				jQuery("#selhd").unbind('click').bind('click', function(){
					if(jQuery("#xh").val() == ''){
						showAlert("������д������Ϣ��");
						return false;
					}else{
					  var url = "jxgl_xnjxsq.do?method=getXmSelectList&xh="+jQuery("#xh").val();
						showDialog('��ѡ����Ŀ',600,350,url);				
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
		<html:form action="/jxgl_xnjxsq" method="post" styleId="XnjxsqForm">
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
								<input type="hidden" name="jgid" id="jgid"/>
								<input type="hidden" id="xmdm" name="xmdm"/>
								<input type="text" name="xmmc" value="" id="xmmc" style="width:120px;"  readonly="readonly"/>
								<button class="btn_01" id="selhd" type="button" >ѡ��</button>
							</td>
							<th>��Ŀ����</th>
							<td id="xmjbmc" >
							  ${rs.xmjbmc}
                             </td>
						</tr>
						<tr>
							<th>ѧ��</th>
							<td id="xn" >
								${rs.xn}
							</td>
							<th>ѧ��</th>
							<td id="xq" >
								${rs.xqmc}
							</td>
						</tr>
						<tr>
							<th>�걨����</th>
							<td id="sbbmmc" >
								${rs.sbbmmc} 
							</td>
							<th>������Ŀ</th>
							<td id="sskmmc" name="sskmmc">
								${rs.sskmmc}
							</td>						
						</tr>
						<tr>
							<th>�ɲ�������</th>
							<td id="kcyrs" name="kcyrs">
								${rs.kcyrs}
							</td>
							<th>��Ŀ��ʼʱ��</th>
							<td id="xmkssj" name="xmkssj">
								${rs.xmkssj}
							</td>
						</tr>
						<tr>
							<th>�걨��</th>
							<td id="sbr" name="sbr">
								${rs.sbrxm}
							</td>
							<th>�걨����ϵ��ʽ</th>
							<td id="lxdh" name="lxdh">
								${rs.lxdh}
							</td>
						</tr>
						<tr>
							<th>
								�Ƿ���������
							</th>
							<td id="sfsljxmc">
								${rs.sfsljxmc}
							</td>
							<th>
								����ѧ��
							</th>
							<td id="jcxf">
								${rs.jcxf}
							</td>
						</tr>
						<tr>
							<th width="20%">
								��Ŀ������Ϣ
							</th>
							<td width="30%"  colspan="3">
								
								 <div style="overflow-y:auto;" id="jxDiv">
								 <table width="100%" border="0" class="formlist">
									<thead>
										<tr>
											<td width='15%'>��������</td>
											<td width='15%'>����ѧ��</td>
											<td width='15%'>����˳��</td>
										</tr>
									</thead>
									<tbody id="tbody_xmjxxx">
									
								</tbody>
								</table>
								</div>								
							</td>
						</tr>
						<thead>
						<tr>
							<th colspan="4">
								<span>�����</span>
							</th>
						</tr>
					   </thead>
						<tr>
							<div style="overflow-y:auto;">
									<table width="100%" border="0" class="formlist">
										
										<tr>
											<th width="20%"><font color="red">*</font>��ý���</th>
											<td>
												<select name="jxid" id="jxid" style="width:200px" onchange="jifen()">
												
												</select>
												
											</td>
										</tr>
										<tr>
											<th width="20%">��ѧ��</th>
											<td id="zxf">
												
											</td>
										</tr>									
									</table>
								</div>
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