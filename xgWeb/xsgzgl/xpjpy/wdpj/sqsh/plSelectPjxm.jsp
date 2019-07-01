<%@ page language="java" pageEncoding="GBK"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				//���
				jQuery(":checkbox",jQuery("#wsq")).bind("click",function(){
					var _self = jQuery(this);
					
					if (_self.prop("checked")){
						jQuery.post("xpj_xmwh_jdsz.do?method=getBjdxm",{xmdm:_self.val()},function(data){
							for (var i = 0 , j = data.length; i < j ; i++){
								var ysq = jQuery("input[name=xmdm][value="+data[i]["xmdm"]+"]",jQuery("#ysq"));
								
								if (ysq.size() > 0){
									_self.attr("checked",false);
									showAlert("�������롰"+data[i]["xmmc"]+"�������������뵱ǰ�����ȷ�ϣ�");
									break;
								}
								
								var yxz = jQuery("input[value="+data[i]["xmdm"]+"]:checked",jQuery("#wsq"));
								
								if (yxz.size() > 0){
									_self.attr("checked",false);
									showAlert("�ý����롰 "+yxz.parent().next().text()+"������ͬʱ���룬��ȷ�ϣ�");
									break;
								}
							}
						},"json");
					}
				});
				
				//����
				jQuery.each(jQuery("img[name=faidImg]"),function(i,n){
					jQuery(n).parent().parent().find(":checkbox").attr("disabled",true);
				});
			});

			//���潱������
			function saveJxsq(){
				var xh = jQuery("#xh").val();
				if (xh == ""){
					showAlert("ѧ�Ŵ���");
					return false;
				}
				/*bug�޸ģ�����ѡ��û�п����� */
				if(jQuery("input[name=xmdm]:checked").length == 0){
					showAlert("��ѡ����Ҫ����Ľ��");
					return false;
				}
				
				jQuery("input[name=xmdm]:checked").each(function(){
					var html="<input type=\"hidden\" name=\"xmdms\" value=\""+jQuery(this).val()+"\">";
					jQuery("#xmdmH").append(html);
				});
				/*
				if (jQuery("#xmdmH").html() == ""){
					showAlert("��ѡ����Ҫ����Ľ��");
					return false;
				}*/
				var url = "xpj_sqsh.do?method=saveJxsq&type=submit";
				jQuery("#sqshForm").ajaxSubmit( {
					url : url,
					type : "post",
					dataType : "json",
					success : function(data) {
						if (data["message"] == "�ύ�ɹ���") {
							showAlert(data["message"], {}, {
								"clkFun" : function() {
									if (parent.window) {
										var api = frameElement.api,W = api.opener;
										W.refreshself();
										closeDialog();
									}
								}
							});
						} else {
							showAlert(data["message"]);
						}
					},
					contentType : "application/x-www-form-urlencoded;charset=utf-8"
					});
			}

			//�л������롢δ����
			function selectSqxm(obj,tabId){
	
				jQuery(".ha").removeClass("ha");
				jQuery(obj).parent().addClass("ha");
				
				jQuery(".con_overlfow table").css("display","none");
				jQuery("#"+tabId).css("display","");
				
			}
		</script>
	</head>
	<body>
		<html:form action="/xpj_sqsh" method="post" styleId="sqshForm">
		<input type="hidden" id="xh" name="xh" value="${xh }" />
		<div class="comp_title" id="comp_title">
	      <ul style="width:90%">
	        <li class="ha"><a href="javascript:void(0);" onclick="selectSqxm(this,'wsq');"><span>δ����</span></a></li>
	        <li><a href="javascript:void(0);" onclick="selectSqxm(this,'ysq');"><span>������</span></a></li>
	      </ul>
	    </div>
	    <div id="xmdmH">
	    
	    </div>
		<div class="tab"  >
			<table class="formlist">
				<tfoot>
					<tr>
						<td>
							<div class="btn">
								<button type="button" onclick="saveJxsq();">
									ȷ��
								</button>
								<button type="button" name="�� ��" onclick="iFClose();">
									�� ��
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
				<tbody>
					<tr>
						<td>
							<div class="con_overlfow" 
								 style="width:100%;height:240px;overflow-x:hidden;overflow-y:auto;vertical-align: top;">
								<table class="dateline" width="100%" id="wsq">
									<thead>
										<tr>
											<td width="2%"></td>
											<td width="20%">
												��Ŀ����
											</td>
											<td width="10%">
												���
											</td>
											<td>
												��������
											</td>
										</tr>
									</thead>
									<tbody>
										<logic:present name="resultMap" property="wsqList">
											<logic:iterate name="resultMap" property="wsqList" id="map" indexId="i">
													<tr>
														<td>
															<input type="checkbox" value="${map.xmdm }" name="xmdm"/>
														</td>
														<td>
															${map.xmmc }
														</td>
														<td>
															${map.xmje }
														</td>
														<td>
															<logic:present name="resultMap" property="${map.xmdm }">
																<logic:iterate id="check" name="resultMap" property="${map.xmdm }" indexId="i">
																	<logic:equal value="true" name="check" property="result">
																		<img src='images/ico_38.gif' title='��������' /> ${i+1 }��${check.sqts }<br/>
																	</logic:equal>
																	<logic:equal value="false" name="check" property="result">
																		<img src='images/ico_39.gif' name='faidImg' title='����������'/> ${i+1 }��${check.sqts }<br/>
																	</logic:equal>
																</logic:iterate>
															</logic:present>
															<logic:notPresent name="resultMap" property="${map.xmdm }">
																<font color='green'>����������</font>
															</logic:notPresent>
														</td>
													</tr>
											</logic:iterate>
										</logic:present>
									</tbody>
									</table>
									
									<table class="dateline" width="100%" id="ysq" style="display:none;">
									<thead>
										<tr>
											<td>
												��Ŀ����
											</td>
											<td>
												���
											</td>
										</tr>
									</thead>
									<tbody >
										<logic:present name="resultMap" property="ysqList">
											<logic:iterate name="resultMap" property="ysqList" id="map" indexId="i">
													<tr>
														<td>
															<input type="hidden" name="xmdm" value="${map.xmdm }"/>
															${map.xmmc }
														</td>
														<td>
															${map.xmje }
														</td>
													</tr>
											</logic:iterate>
										</logic:present>
									</tbody>
								</table>
							</div>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		</html:form>
	</body>
</html>
