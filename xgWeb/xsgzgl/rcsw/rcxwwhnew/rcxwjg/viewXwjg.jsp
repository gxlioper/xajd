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
		<script type="text/javascript" src="xsgzgl/rcsw/rcxwwhnew/rcxwjg/rcxwjg.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f_q.js"></script>
		<script type="text/javascript">
<%--		jQuery(function() {--%>
<%--			rcxwjl();  //�ճ���Ϊ��¼չʾ--%>
<%--		});--%>
<%----%>
<%--		//�ճ���Ϊ��¼--%>
<%--		function rcxwjl(){--%>
<%--			var index=5;--%>
<%--			if("1"==jQuery("#zq").val()){--%>
<%--				index=4;--%>
<%--				}--%>
<%--			jQuery("#tab_rcxw").find("tr").each(function(){--%>
<%--				var rcxwjlsj=jQuery(this).find("td").eq(index).text();--%>
<%--				if(jQuery.trim(rcxwjlsj)=="0"){--%>
<%--					jQuery(this).find("td").eq(index-3).attr("colspan","4");--%>
<%--					jQuery(this).find("td").eq(index-2).hide();--%>
<%--					jQuery(this).find("td").eq(index-1).hide();--%>
<%--					jQuery(this).find("td").eq(index).hide();--%>
<%--					}--%>
<%--				if(jQuery.trim(rcxwjlsj)=="9999999999"){--%>
<%--					var obj=jQuery(this);--%>
<%--					jQuery(this).attr("style","cursor:pointer");--%>
<%--					jQuery(this).find("td").eq(index-3).attr("colspan","4");--%>
<%--					jQuery(this).find("td").eq(index-2).hide();--%>
<%--					jQuery(this).find("td").eq(index-1).hide();--%>
<%--					jQuery(this).find("td").eq(index).hide();--%>
<%--					jQuery(this).bind("click",function(){--%>
<%--						jQuery(obj).nextAll("tr").each(function(){--%>
<%--							var rcxw=jQuery(this).find("td").eq(index).text();--%>
<%--							if(jQuery.trim(rcxw)!="0"&&jQuery.trim(rcxw)!="9999999999"){--%>
<%--								if(jQuery(this).is(":hidden")){--%>
<%--									jQuery(this).show();--%>
<%--								}else{--%>
<%--									jQuery(this).hide();--%>
<%--								}--%>
<%--							}else{--%>
<%--								return false;--%>
<%--							}--%>
<%--						});--%>
<%--					});--%>
<%--				}--%>
<%--			});	--%>
<%--			jQuery("#zdybdfl_xsxx_query_rcsw_rcxwjl table tr th span").append("<font size='0' color='blue'>&nbsp;&nbsp;&nbsp;�����Ϊ�����С��ɲ鿴��ϸ</font>");--%>
<%--			--%>
<%--			jQuery("#tab_rcxw").find("tr").click();--%>
<%--		}--%>
<%--		function getXwlbList(obj) {--%>
<%--			jQuery.post('rcsw_rcxwwhnew_rcxwxxwhgl.do?method=getXwlbList', {--%>
<%--				rcxwlbdldm : obj.value--%>
<%--			}, function(data) {--%>
<%--				var option = "<option value=''></option>";--%>
<%--				for ( var i = 0; i < data.length; i++) {--%>
<%--					option += "<option value='"+data[i].rcxwlbdm+"'>"--%>
<%--							+ data[i].rcxwlbmc + "</option>";--%>
<%--				}--%>
<%--				jQuery('#rcxwlbdm').empty().append(option);--%>
<%--				//jQuery("#rcxwlbdm").val(jQuery("#rcxwlbdm_value").val());	--%>
<%--			}, 'json');--%>
<%--		}--%>

			jQuery(function(){

			});
			function yl() {
				var fjmc=jQuery("#fjmc").val();
                var fjlj=jQuery("#fjlj").val();
				var url="rcsw_rcxwwhnew_rcxwjggl.do?method=downloadFile";
				var URL="rcsw_rcxwwhnew_wjyl.do?method=preview&fjlj="+fjlj+"&fjmc="+fjmc+"&url="+url;
                window.open(encodeURI(URL));
            }
			
		</script>

	</head>
	<body>

		<html:form action="/rcsw_rcxwwhnew_rcxwxxwhgl" method="post"
			styleId="rcxwxxwhForm">
			<html:hidden property="id" styleId="id" />
			<input type="hidden" id="zq" value="${zq}"/>
			<div style="height:480px;overflow-x:hidden;overflow-y:auto;">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ��������Ϣ</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/rcsw/rcxwwhnew/comm/viewStudent.jsp"%>

					<thead>
						<tr>
							<th colspan="4">
								<span>
									<logic:equal value="10704" name="xxdm">
										�ۺϲ�����¼��Ϣ
									</logic:equal>
									<logic:notEqual value="10704" name="xxdm">
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
								<logic:equal value="10704" name="xxdm">
									�ۺϲ������
								</logic:equal>
								<logic:notEqual value="10704" name="xxdm">
									��Ϊ���
								</logic:notEqual>
							</th>
							<td>
								${rs.rcxwlbmc}
							</td>
							<th>
								<logic:equal value="10704" name="xxdm">
									�ۺϲ�������
								</logic:equal>
								<logic:notEqual value="10704" name="xxdm">
									��Ϊ����
								</logic:notEqual>
							</th>
							<td>
								${rs.rcxwlbdlmc}(${rs.ssxymc})
							</td>
						</tr>
						<tr>
							<th>
								<logic:equal value="10704" name="xxdm">
									�ۺϲ���С��
								</logic:equal>
								<logic:notEqual value="10704" name="xxdm">
									��ΪС��
								</logic:notEqual>
							</th>
							<td>
								${rs.rcxwlbxlmc}
							</td>
							<th>
							   	����˵��
							</th>
							<td>
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
					    			<a href="javascript:void(0);" onclick="window.open('rcsw_rcxwwhnew_rcxwjggl.do?method=downloadFile&id=${rs.id }');return false;" class="name">����</a>&nbsp;${rs.fjmc }
					    		</logic:notEmpty>
					    	</td>
					    </tr>
					    </logic:equal>
					    <logic:equal value="12942" name="xxdm">						
					    <tr>
					    	<th>����</th>
					    	<td colspan="3" id="fileTd">
					    		<logic:notEmpty name="rs" property="fjlj">
					    			<a href="javascript:void(0);" onclick="window.open('rcsw_rcxwwhnew_rcxwjggl.do?method=downloadFile&id=${rs.id }');return false;" class="name">����</a>&nbsp;${rs.fjmc }
					    		</logic:notEmpty>
					    	</td>
					    </tr>
					    </logic:equal>
						<logic:equal value="10699" name="xxdm">
						<tr>
							<th>����</th>
							<td colspan="3" id="fileTd">
								<input type="hidden" value="${rs.fjmc}" id="fjmc"/>
								<input type="hidden" value="${rs.fjlj}" id="fjlj"/>
								<logic:notEmpty name="rs" property="fjlj">
									<a href="javascript:void(0);" id="yl" onclick="yl();return false;" class="name">Ԥ��</a>
									<a href="javascript:void(0);" onclick="window.open('rcsw_rcxwwhnew_rcxwjggl.do?method=downloadFile&id=${rs.id }');return false;" class="name">����</a>&nbsp;${rs.fjmc }
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
								<span>
									<logic:equal value="10704" name="xxdm">
										�ۺϲ�����¼
									</logic:equal>
									<logic:notEqual value="10704" name="xxdm">
										�ճ���Ϊ��¼
									</logic:notEqual>
								</span>
							</th>
						</tr>
					</thead>

					<tr>
						<td colspan="5">
							<div>
								<table class="formList" width="100%" id="tab_rcxw">
									<thead>
										<tr align="left">
											<td align="center">
												<logic:equal value="10704" name="xxdm">
													�ۺϲ�����¼ѧ��
												</logic:equal>
												<logic:notEqual value="10704" name="xxdm">
													��Ϊ��¼ѧ��
												</logic:notEqual>
											</td>
											<logic:equal value="0" name="zq">
											<td align="center">
												<logic:equal value="10704" name="xxdm">
													�ۺϲ�����¼ѧ��
												</logic:equal>
												<logic:notEqual value="10704" name="xxdm">
													��Ϊ��¼ѧ��
												</logic:notEqual>
											</td>
											</logic:equal>
											<td align="center" >
												<logic:equal value="10704" name="xxdm">
													�ۺϲ������
												</logic:equal>
												<logic:notEqual value="10704" name="xxdm">
													��Ϊ��¼���
												</logic:notEqual>
											</td>
											<td align="center" >
												<logic:equal value="10704" name="xxdm">
													�ۺϲ����ܷ�
												</logic:equal>
												<logic:notEqual value="10704" name="xxdm">
													��Ϊ��¼�ܷ�
												</logic:notEqual>
											</td>
											<td align="center">
												ѧ��
											</td>
										</tr>
									</thead>
									<logic:empty name="rsArrList">
										<tr>
											<td align="center" colspan="5">
												��ѧ������ʷ��¼��
											</td>
										</tr>
									</logic:empty>
									<logic:notEmpty name="rsArrList">
										<logic:iterate name="rsArrList" id="v">
											<tr>
													<td align="center">
														${v.rdnd}
													</td>
													<logic:equal value="0" name="zq">
													<td align="center">
														${v.xqmc}
													</td>
													</logic:equal>
													<td align="center">
														${v.rcxwlbmc}<a data="${v.xh}_${v.xqdm}_${v.rdnd}_${v.rcxwlbdm}" onclick = "showInfo(this);" class="up" href="javascript:void(0);" value="${v.xh}-${v.xqdm}-${v.rdnd}-${v.rcxwlbdm}"/>
													</td>
													<td align="center">
														${v.fz}
													</td>
													<td align="center">
														${v.xf}
													</td>
												
												
											</tr>
											<tr>
												<td colspan="5" data="${v.xh}_${v.xqdm}_${v.rdnd}_${v.rcxwlbdm}" type="detail-data" style="display: none;" >
														<table class="formList" width="100%" id="tab_rcxw">
															<thead>
																<tr align="left">
																	<td align="center" width="32%">
																		<logic:equal value="10704" name="xxdm">
																			�ۺϲ�������
																		</logic:equal>
																		<logic:notEqual value="10704" name="xxdm">
																			��Ϊ��¼����
																		</logic:notEqual>
																	</td>
																	<td align="center" width="15%">
																		<logic:equal value="10704" name="xxdm">
																			�ۺϲ���С��
																		</logic:equal>
																		<logic:notEqual value="10704" name="xxdm">
																			��Ϊ��¼С��
																		</logic:notEqual>
																	</td>
																	<td align="center" width="20%">
																		<logic:equal value="10704" name="xxdm">
																			�ۺϲ�����¼����ʱ��
																		</logic:equal>
																		<logic:notEqual value="10704" name="xxdm">
																			��Ϊ��¼����ʱ��
																		</logic:notEqual>
																	</td>
																	<td align="center" width="13%">
																		<logic:equal value="10704" name="xxdm">
																			�ۺϲ�����¼��ֵ
																		</logic:equal>
																		<logic:notEqual value="10704" name="xxdm">
																			��Ϊ��¼��ֵ
																		</logic:notEqual>
																	</td>
																</tr>
															</thead>
															<tbody class="tbody_rcxw">
															</tbody>
														</table>
												</td>	
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

