<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/xstgl/comm/js/comm.js"></script>
		<script type="text/javascript" src="js/provicecitylocal.js"></script>
		<script type="text/javascript">
		jQuery(function() {
			rcxwjl();  //�ճ���Ϊ��¼չʾ
			jQuery(function(){
				proviceCiyyLocalMain({type:"view",id:"fwddssx",flag:"yxxdz"});
			})
		});

		//�ճ���Ϊ��¼
		function rcxwjl(){
			var index=4;
			jQuery("#tab_sthddj").find("tr").each(function(){
				var fwdd=jQuery(this).find("td").eq(index).text();
				if(jQuery.trim(fwdd)=="0"){
					var obj=jQuery(this);
					jQuery(this).attr("style","cursor:pointer");
					jQuery(this).find("td").eq(index-3).attr("colspan","4");
					jQuery(this).find("td").eq(index-1).hide();
					jQuery(this).find("td").eq(index-2).hide();
					jQuery(this).find("td").eq(index).hide();
					jQuery(this).bind("click",function(){
						jQuery(obj).nextAll("tr").each(function(){
							var rcxw=jQuery(this).find("td").eq(index).text();
							if(jQuery.trim(rcxw)!="0" && jQuery.trim(rcxw)!="9999999999"){
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
		
			
			jQuery("#tab_sthddj").find("tr").click();
		}
		</script>
	</head>
	<body>
		<html:form action="/sthdglSthdjg" method="post" styleId="SthdjgForm">
			<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom: 0px;" >
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
								<span><font id="gnmkmc_prompt_span"></font></span>
							</th>
						</tr>
					</thead>
					<tbody>
					<tr>
						<th width="20%">
							�����
						</th>
						<td width="30%">
								${rs.hdmc}
						</td>
						<th  width="20%">ʱ��</th>
						<td width="30%">
								${rs.fwsj}
						</td>
					</tr>
					<tr>
						<th width="20%">
							���쵥λ
						</th>
						<td width="30%">
								${rs.zbdw}
						</td>
						<th>����ʱ��</th>
						<td>
								${rs.fwsc}
						</td>

					</tr>
					<tr>
						<th width="20%">
							����ص�
						</th>
						<td width="30%" colspan="3">
							<html:hidden  property="fwddssx" styleId="fwddssx"/>
								${rs.fwdd}
						</td>
					</tr>
					<tr>
					<tr>
						<th>����</th>
						<td colspan="3">
							<%@ include file="/xsgzgl/comm/fileUpload/f_q.jsp"%>
							<html:hidden property="fjid" styleId="fjid" value="${rs.fjid}"/>
							<script type="text/javascript">
                                //���ø���
                                jQuery(function(){
                                    var gid = jQuery('#fjid').val();
                                    jQuery.MultiUploader_q({
                                        gid : gid
                                    });
                                });
							</script>
						</td>
					</tr>
					<%--<thead>
						<tr>
							<th colspan="4">
								<span><font id="gnmkmc_prompt_span"></font>��ʷ��Ϣ
								<font size="0" color="blue">&nbsp;&nbsp;&nbsp;�����������С��ɲ鿴��ϸ</font>
								</span>
							</th>
						</tr>
					</thead>--%>

					<%--<tr>
						<td colspan="5">
							<div>
								<table class="formList" width="100%" id="tab_sthddj">
									<thead>
										<tr align="left">
											<td align="center" width="12%">
												�����
											</td>
											<td align="center" width="14%">
												ʱ��
											</td>
											<td align="center" width="18%">
												����ʱ��
											</td>
											<td align="center" width="12%">
												�ص�
											</td>
											<td align="center" width="20%">
												���쵥λ
											</td>
											<td align="center" width="10%">
												����
											</td>
											
										</tr>
									</thead>
									<logic:empty name="rsArrList">
										<tr>
											<td align="center" colspan="6">
												��ѧ�������ŵǼǼ�¼��
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
								</table>--%>
					</tbody>
				</table>
			</div>
			<div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
								</div>
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