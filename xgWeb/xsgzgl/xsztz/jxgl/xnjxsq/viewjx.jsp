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
					  var url = "jxgl_xnjxsq.do?method=getXmSelectList&xh="+jQuery("#xh1").val();
						showDialog('��ѡ����Ŀ',600,350,url);
					}
			   });
				jQuery("#shlccx").load(
						"comm_spl.do?method=lccx&sqid=${id}&tt="
								+ new Date().getTime());
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
			<html:hidden property="id"  styleId="id"/>
			<html:hidden property="xn"  styleId="xq"/>
			<html:hidden property="xq"  styleId="xq"/>
			<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom:0px;" >
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
								<span>��Ŀ��Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>��Ŀ����</th>
							<td>
								<input id="xh1" value="${xh}" type="hidden"/>
								<html:hidden property="jgid"  styleId="jgid"/>
								<html:hidden property="xmdm"  styleId="xmdm"/>
								${rs.xmmc}				
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
									<logic:iterate id="i" name="xmjxList" indexId="index01">
										<tr>
										<input type="hidden" name="jxId" value='${i.jgid}'/>
										<td name="jxArr">${i.jxmc}</td>
										<td>${i.fjxf}</td>
										<td>${i.xssx}</td>
										</tr>
										</logic:iterate>
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
											<th width="20%">��ý���</th>
											<td>
												${jxmc}																							
											</td>
										</tr>
										<tr>
											<th width="20%">��ѧ��</th>
											<td id="zxf">
												${zf}
											</td>
										</tr>									
									</table>
								</div>
						</tr>
						<logic:notEqual value="�������" name="shztmc">
					<table width="100%" border="0" class="formlist">
						<thead>
							<tr>
								<th colspan="4">
									<span>�����Ϣ</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td colspan="4" id="shlccx">

								</td>
							</tr>

						</tbody>

					</table>
				</logic:notEqual>
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
								<div class="btn">
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