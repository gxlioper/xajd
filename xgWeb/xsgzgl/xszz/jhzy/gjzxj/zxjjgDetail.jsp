<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	    <script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" defer="defer">
		//�������״̬
		function saveGjzxjSh(shzt){
			var message = "";
			if(shzt == "tg"){
				message = "��ȷ�����<font color='blue'>ͨ��</font>��ѧ���Ĺ�����ѧ��������";
			}else if(shzt == "btg"){
				message = "��ȷ�����<font color='blue'>��ͨ��</font>��ѧ���Ĺ�����ѧ��������";
			}
			
			confirmInfo(message,function(tag){
				if(tag=="ok"){
					
					//·��
					var url = "jhzy_gjzxj.do?method=saveGjzxjSh";
					
					var tjdc = jQuery("[name=tjdc]:checked").eq(0).val();
					
					//����
				 	var parameter = {
						"str_xh":escape(jQuery("#xh").val()),
						"str_xn":escape(jQuery("#xn").val()),
						"str_shzt":escape(shzt),
						"str_shyj":escape(jQuery("#shyj").val()),
						"str_tjdc":escape(tjdc)
					};
			
				 	$("divWaiting").style.display="";
					$("divDisable").style.display="";
					
					jQuery.ajaxSetup({async:false});
					
					jQuery.post(url,
						parameter,
						function(result){
							$("divWaiting").style.display="none";
							$("divDisable").style.display="none";
							alertInfo(result);
							closeWindown();		
						}
					);
			
					jQuery.ajaxSetup({async:true});
				}
			});
		}
		</script>
	</head>
	<body onload="" >
		<html:form action="/general_pjpy" method="post">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>	
			<input type="hidden" id="xh" value="${xh }"/>
			<input type="hidden" id="xn" value="${xn }"/>
			<table class="formlist" border="0" align="center" style="width: 100%">
				<tr style="height: 23px">
					<td align="center" colspan="4">
						<font size="5">
							${xn }ѧ�������ѧ��
						</font>
					</td>
				</tr>
			</table>
			
			<div style="width:100%;height:500px;overflow-x:hidden;overflow-y:auto;">
				<!-- ��ͥ������� -->	
				<%@ include file="/xsgzgl/xszz/jhzy/xsjtknxx.jsp"%>
				<!-- ������������Ϣ -->
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th align="right" width="20%">
								�������϶�����
							</th>
							<td align="left" width="" colspan="3">
								${map.knstjdc }
							</td>
						</tr>
						<tr>
							<th align="right" width="20%">
								<font color="red">*</font>��������
							</th>
							<td align="left" width="" colspan="3" style="word-break:break-all;width:99%">
								${map.sqly }
							</td>
						</tr>
					</tbody>
				</table>
				<!-- �����Ϣ -->
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
							<th width="20%">
								����������
							</th>
							<td colspan="3">
								<table width="100%" border="0">
									<tbody>
										<tr>
											<td>
												��˼���
											</td>
											<td>
												�����
											</td>
											<td>
												��˽��
											</td>
											<td>
												���ʱ��
											</td>
											<td>
												�Ƽ�����
											</td>
											<td>
												������
											</td>
										</tr>
										<tr>
											<td>
												������
											</td>
											<td>
												${map.bzrxm }
											</td>
											<td>
												${map.bzrsh }
											</td>
											<td>
												${map.bzrshsj }
											</td>
											<td>
												${map.bzrtjdc }
											</td>
											<td title="${map.bzrshyj }">
												${map.bzrshyjxs }
											</td>
										</tr>
										<tr>
											<td>
												����Ա
											</td>
											<td>
												${map.fdyxm }
											</td>
											<td>
												${map.fdysh }
											</td>
											<td>
												${map.fdyshsj }
											</td>
											<td>
												${map.fdytjdc }
											</td>
											<td title="${map.fdyshyj }">
												${map.fdyshyjxs }
											</td>
										</tr>
										<tr>
											<td>
												<bean:message key="lable.xb" />
											</td>
											<td>
												${map.xyxm }
											</td>
											<td>
												${map.xysh }
											</td>
											<td>
												${map.xyshsj }
											</td>
											<td>
												${map.xytjdc }
											</td>
											<td title="${map.xyshyj }">
												${map.xyshyjxs }
											</td>
										</tr>
										<tr>
											<td>
												ѧУ
											</td>
											<td>
												${map.xxxm }
											</td>
											<td>
												${map.xxsh }
											</td>
											<td>
												${map.xxshsj }
											</td>
											<td>
												${map.xxtjdc }
											</td>
											<td title="${map.xxshyj }">
												${map.xxshyjxs }
											</td>
										</tr>
									</tbody>
								</table>
							</td>
						</tr>
						<tr>
							<th width="">
								<font color="red">*</font>�Ƽ�����
							</th>
							<td colspan="3">
								${map.xxtjdc }			
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div>
				<table width="100%" border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button type="button" onclick="Close();return false;">�� ��</button>					           
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>