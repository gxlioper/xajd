<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<style>
.include_tab{border-collapse:collapse;border:0px;}
.include_tab td{border-top:0;bordedr-right:1px solid red!importalt;border-bottom:0;border-left:0;}
.include_tab_r{}
</style>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	    <script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" defer="defer">
		//�������״̬
		function saveKnsrdSh(shzt){
			var message = "";
			if(shzt == "tg"){
				message = "��ȷ�����<font color='blue'>ͨ��</font>��ѧ���Ĺ�����־��ѧ��������";
			}else if(shzt == "btg"){
				message = "��ȷ�����<font color='blue'>��ͨ��</font>��ѧ���Ĺ�����־��ѧ��������";
			}
			
			confirmInfo(message,function(tag){
				if(tag=="ok"){
					
					//·��
					var url = "jhzyGjlzjxj.do?method=gjlzjxjshUpdate&act=save&shzt="+shzt+"&shyj="+jQuery('#shyj').val();
					refreshForm(url);
					
				}
			});
		}
		</script>
	</head>
	<body onload="" >
		<html:form action="/jhzyGjlzjxj" method="post">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>	
			<input type="hidden" id="xh" value="${xh }"/>
			<input type="hidden" id="xn" value="${xn }"/>
			
			<input type="hidden" name="pkValue" value="${pkValue }"/>
			<table class="formlist" border="0" align="center" style="width: 100%">
				<tr style="height: 23px">
					<td align="center" colspan="4">
						<font size="5">
							${xn }ѧ�������־��ѧ�����<input type="text" class="include_tab" readonly="readonly"/>
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
								<span>������־��ѧ��������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
						<th align="right" width="20%">
								�ɼ�����<br/>������/��������
							</th>
							<td align="left" width="30%" >
								${map.cjpm}/${map.bjzrs }
							</td>
							<th align="right" width="20%">
								�������
							</th>
							<td align="left" width="30%" >
								���޿�${map.bxkms}���ţ�
								<br/>��������${map.jgms}���ţ�
							</td>
							
						</tr>
						<tr>
							<th align="right" width="20%">
								ʵ���ۺϿ�������
							</th>
							<td align="left" width="30%" >
							${map.sxzhkppm }
							</td>
							<th align="right" width="20%">
								���ǣ�����<br/>������/��������
							</th>
							<td align="left" width="30%" >
								${map.zhkppm}/${map.bjzrs }
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
				<table width="100%" border="0" class="formlist">
						<!-- ѧ��������Ϣ begin-->
						<thead>
							<tr>
								<th colspan="3">
									<span>��ѧ�ڼ���Ҫ�����</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
							<th style="width:20%">
								<div align="center"><font color="red">*</font>������</div>
							</th>
							<th style="width:40%">
								<div align="center"><font color="red">*</font>������</div> 
							</th>
							<th style="width:40%">
								<div align="center"><font color="red">*</font>�佱��λ</div>
							</th>
							</tr>
							<tr>
								<td align="center">
								${map.hjsj1}
								</td>
								<td align="center">
									${map.hjmc1}
								</td>
								<td align="center">
									${map.bjdw1}
								</td>
							</tr>
							<tr>
								<td align="center">
									${map.hjsj2}
								</td>
								<td align="center">
								${map.hjmc2}
								</td>
								<td align="center">
								${map.bjdw2}
								</td>
							</tr>
							<tr>
								<td align="center">
									${map.hjsj3}
								</td>
								<td align="center">
									${map.hjmc3}
								</td>
								<td align="center">
									${map.bjdw3}
								</td>
							</tr>
							<tr>
								<td align="center">
									${map.hjsj4}
								</td>
								<td align="center">
									${map.hjmc4}
								</td>
								<td align="center">
									${map.bjdw4}
								</td>
							</tr>
						</tbody>
						</table>
				<!-- �����������Ϣ -->
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>������־��ѧ�������Ϣ</span>
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
											<th>
												��˼���
											</th>
											<th>
												�����
											</th>
											<th>
												��˽��
											</th>
											<th>
												���ʱ��
											</th>
											<th>
												������
											</th>
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
												${map.bzrshyj }
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
												${map.fdyshyj }
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
												${map.xyshyj }
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
												${map.xxshyj }
											</td>
										</tr>
									</tbody>
								</table>
							</td>
						</tr>
					
						<tr>
							<th width="">
								������
								<br/><font color="red">(��������100)</font>
							</th>
							<td colspan="3">
								<textarea rows="3" id="shyj" cols="" name="shyj"
									onblur="chLeng(this,100);"
									style="word-break:break-all;width:99%" >${shyj }</textarea>
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
									<button type="button" onclick="saveKnsrdSh('tg');return false;" >ͨ ��</button>
									<button type="button" onclick="saveKnsrdSh('btg');return false;" >��ͨ��</button>
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