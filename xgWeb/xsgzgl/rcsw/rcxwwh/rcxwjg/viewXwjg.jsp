<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript"
			src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?self=true"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script language="javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>

		<script type="text/javascript">
		jQuery(function() {
			rcxwjl();  //�ճ���Ϊ��¼չʾ
		});

		//�ճ���Ϊ��¼
		function rcxwjl(){
			var index=4;
			if("1"==jQuery("#zq").val()){
				index=3;
				}
			jQuery("#tab_rcxw").find("tr").each(function(){
				var rcxwjlsj=jQuery(this).find("td").eq(index).text();
				if(jQuery.trim(rcxwjlsj)=="0"){
					jQuery(this).find("td").eq(index-2).attr("colspan","3");
					jQuery(this).find("td").eq(index-1).hide();
					jQuery(this).find("td").eq(index).hide();
					}
				if(jQuery.trim(rcxwjlsj)=="9999999999"){
					var obj=jQuery(this);
					jQuery(this).attr("style","cursor:pointer");
					jQuery(this).find("td").eq(index-2).attr("colspan","3");
					jQuery(this).find("td").eq(index-1).hide();
					jQuery(this).find("td").eq(index).hide();
					jQuery(this).bind("click",function(){
						jQuery(obj).nextAll("tr").each(function(){
							var rcxw=jQuery(this).find("td").eq(index).text();
							if(jQuery.trim(rcxw)!="0"&&jQuery.trim(rcxw)!="9999999999"){
								if(jQuery(this).is(":hidden")){
									jQuery(this).show();
								}else{
									jQuery(this).hide();
								}
							}else{
								return false;
							}
						});
					});
				}
			});
            var temp = "��Ϊ";
            if(jQuery("#xxdm").val() == "13431") temp="�ӷ�";
			jQuery("#zdybdfl_xsxx_query_rcsw_rcxwjl table tr th span").append("<font size='0' color='blue'>&nbsp;&nbsp;&nbsp;���"+temp+"�����С��ɲ鿴��ϸ</font>");
			
			jQuery("#tab_rcxw").find("tr").click();
		}
		function getXwlbList(obj) {
			jQuery.post('rcsw_rcxwwh_rcxwxxwhgl.do?method=getXwlbList', {
				rcxwlbdldm : obj.value
			}, function(data) {
				var option = "<option value=''></option>";
				for ( var i = 0; i < data.length; i++) {
					option += "<option value='"+data[i].rcxwlbdm+"'>"
							+ data[i].rcxwlbmc + "</option>";
				}
				jQuery('#rcxwlbdm').empty().append(option);
				//jQuery("#rcxwlbdm").val(jQuery("#rcxwlbdm_value").val());	
			}, 'json');
		}

</script>

	</head>
	<body>

		<html:form action="/rcsw_rcxwwh_rcxwxxwhgl" method="post"
			styleId="rcxwxxwhForm">
			<html:hidden property="id" styleId="id" />
			<input type="hidden" id="zq" value="${zq}"/>
			<input type="hidden" id="xxdm" value="${xxdm}"/>
			<div style="overflow-x:hidden;overflow-y:auto;">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ��������Ϣ</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/rcsw/rcxwwh/comm/viewStudent.jsp"%>

					<thead>
						<tr>
							<th colspan="4">
								<span>
									<logic:equal name="xxdm" value="13431">
										�ӷּ�¼��Ϣ
									</logic:equal>
									<logic:notEqual name="xxdm" value="13431">
										��Ϊ��¼��Ϣ
									</logic:notEqual>
								</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>
								ѧ��
							</th>
							<td>
								${rs.xn }
							</td>
							<th>
								ѧ��
							</th>
							<td>
								${rs.xqmc}
							</td>
						</tr>
						<tr>
							<th>
								<logic:equal name="xxdm" value="13431">
									�ӷִ���
								</logic:equal>
								<logic:notEqual name="xxdm" value="13431">
									��Ϊ����
								</logic:notEqual>
							</th>
							<td>
								${rs.rcxwlbdlmc}
							</td>
							<th>
								<logic:equal name="xxdm" value="13431">
									�ӷ����
								</logic:equal>
								<logic:notEqual name="xxdm" value="13431">
									��Ϊ���
								</logic:notEqual>
							</th>
							<td>
								${rs.rcxwlbmc}
							</td>
						</tr>

						<tr>
							<th>
							   	����˵��
							</th>
							<td colspan="3">
								${rs.rcxwlbbz}
							</td>
			      		</tr>
					    <tr>
							<th>������ֵ</th>
							<td>${rs.fz }</td>							
							<th >����ʱ��</th>
							<td >
								${rs.fssj}
							</td>
					    </tr>
					    <tr>
							<th>��¼��</th>
							<td>${rs.jlrxm }</td>							
							<th >��¼ʱ��</th>
							<td >
								${rs.rcxwjlsj}
							</td>
					    </tr>
						<logic:equal value="13022" name="xxdm">						
					    <tr>
					    	<th>����</th>
					    	<td colspan="3" id="fileTd">
					    		<logic:notEmpty name="rs" property="fjlj">
					    			<a href="javascript:void(0);" onclick="window.open('rcsw_rcxwwh_rcxwjggl.do?method=downloadFile&id=${rs.id }');return false;" class="name">����</a>&nbsp;${rs.fjmc }
					    		</logic:notEmpty>
					    	</td>
					    </tr>
					    </logic:equal>
						<logic:equal value="13431" name="xxdm">
						<tr>
							<th>����</th>
							<td colspan="3" id="fileTd">
								<logic:notEmpty name="rs" property="fjlj">
									<a href="javascript:void(0);" onclick="window.open('rcsw_rcxwwh_rcxwjggl.do?method=downloadFile&id=${rs.id }');return false;" class="name">����</a>&nbsp;${rs.fjmc }
								</logic:notEmpty>
							</td>
						</tr>
						</logic:equal>
					    <logic:equal value="10344" name="xxdm">						
						    <tr>
						    	<th>����</th>
						    	<td colspan="3" id="fileTd">
						    		<logic:notEmpty name="rs" property="fjlj">
						    			<a href="javascript:void(0);" onclick="window.open('rcsw_rcxwwh_rcxwjggl.do?method=downloadFile&id=${rs.id }');return false;" class="name">����</a>&nbsp;${rs.fjmc }
						    		</logic:notEmpty>
						    	</td>
						    </tr>
					    </logic:equal>
					    <logic:equal value="13871" name="xxdm">						
						    <tr>
						    	<th>����</th>
						    	<td colspan="3" id="fileTd">
						    		<logic:notEmpty name="rs" property="fjlj">
						    			<a href="javascript:void(0);" onclick="window.open('rcsw_rcxwwh_rcxwjggl.do?method=downloadFile&id=${rs.id }');return false;" class="name">����</a>&nbsp;${rs.fjmc }
						    		</logic:notEmpty>
						    	</td>
						    </tr>
					    </logic:equal>
					    <logic:equal value="70002" name="xxdm">						
						    <tr>
						    	<th>����</th>
						    	<td colspan="3" id="fileTd">
						    		<logic:notEmpty name="rs" property="fjlj">
						    			<a href="javascript:void(0);" onclick="window.open('rcsw_rcxwwh_rcxwjggl.do?method=downloadFile&id=${rs.id }');return false;" class="name">����</a>&nbsp;${rs.fjmc }
						    		</logic:notEmpty>
						    	</td>
						    </tr>
					    </logic:equal>
					    <tr>
							<th>
							   	��������	
							</th>
							<td colspan="3">
								${rs.gfly}
							</td>
			      		</tr>
					    <tr>
							<th>
							   	��ע	
							</th>
							<td colspan="3">
								${rs.bz}
							</td>
			      		</tr>

						<%--<tr>
							<th>
								���״̬
							</th>
							<td colspan="3">
								${rs.shztmc}
							</td>
						</tr>
						--%><%--<tr>
							<th>
								������
							</th>
							<td colspan="3">
								${rs.shyj}
							</td>
						</tr>
					--%><thead>
						<tr>
							<th colspan="4">
								<logic:equal name="xxdm" value="13431">
									<span>�ѻ�ӷּ�¼��Ϣ
								<font size="0" color="blue">&nbsp;&nbsp;&nbsp;����ӷִ����С��ɲ鿴��ϸ</font>
								</span>
								</logic:equal>
								<logic:notEqual name="xxdm" value="13431">
									<span>�ѻ���Ϊ��¼��Ϣ
								<font size="0" color="blue">&nbsp;&nbsp;&nbsp;�����Ϊ�����С��ɲ鿴��ϸ</font>
								</span>
								</logic:notEqual>
							</th>
						</tr>
					</thead>

					<tr>
						<td colspan="6">
							<div>
								<table class="formList" width="100%" id="tab_rcxw">
									<thead>
										<tr align="left">
											<td align="center" width="12%">
												ѧ��
											</td>
											<logic:equal value="0" name="zq">
											<td align="center" width="12%">
												ѧ��
											</td>
											</logic:equal>
											<td align="center" width="18%">
												<logic:equal name="xxdm" value="13431">
													�ӷִ���
												</logic:equal>
												<logic:notEqual name="xxdm" value="13431">
													��Ϊ����
												</logic:notEqual>
											</td>
											<td align="center" width="18%">
												<logic:equal name="xxdm" value="13431">
													�ӷ����
												</logic:equal>
												<logic:notEqual name="xxdm" value="13431">
													��Ϊ���
												</logic:notEqual>
											</td>
											<td align="center" width="18%">
												����ʱ��
											</td>
											<td align="center" width="11%">
												������ֵ
											</td>
											<td align="center" width="11%">
												��¼��
											</td>
										</tr>
									</thead>
									<logic:empty name="rsArrList">
										<tr>
											<td align="left" colspan="6">
												��ѧ������ʷΥ�ͼ�¼��
											</td>
										</tr>
									</logic:empty>
									<logic:notEmpty name="rsArrList">
										<logic:iterate name="rsArrList" id="s">
											<tr>
												<!-- ��ʾ��Ϣ -->
												<logic:iterate id="v" name="s" offset="0" length="7">
													<td align="center">
														${v }
													</td>
												</logic:iterate>
											</tr>
										</logic:iterate>
									</logic:notEmpty>
								</table>
									</tbody>
									<%--<tfoot>
										<tr>
											<td colspan="5">
												<div class="btn">
													<button type="button" type="button" onclick="iFClose();">
														�� ��
													</button>
												</div>
											</td>
										</tr>
									</tfoot>
								--%></table>
							</div>
							
			<table width="100%" border="0" class="formlist">
				<tfoot>
					<tr>
						<td colspan="5">
							<div class="btn">
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

