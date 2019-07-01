<%@ page language="java" pageEncoding="GBK"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="xsgzgl/xpjpy/zjdxwdpj/xmsq/js/xmsq.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f_q_href.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				//���
				jQuery(":checkbox",jQuery("#wsq")).bind("click",function(){
					var _self = jQuery(this);
					
					if (_self.prop("checked")){
						jQuery.post("xpjpy_xmsq.do?method=getBjdxm",{xmdm:_self.val()},function(data){
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

				//��Ŀ˵��<br/>ת��Ϊ������ʾ��ҳ����
				jQuery("#xmList>table>tbody>tr").each(function(){
					var title = jQuery(this).attr("title");
					title = title.replaceAll("<br/>","\n");
					jQuery(this).attr("title",title);
				});
			});
		</script>
	</head>
	<body>
		<div class="comp_title" id="comp_title">
	      <ul style="width:90%">
	        <li class="ha"><a href="javascript:void(0);" onclick="selectSqxm(this,'wsq');"><span>δ����</span></a></li>
	        <li><a href="javascript:void(0);" onclick="selectSqxm(this,'ysq');"><span>������</span></a></li>
	      </ul>
	    </div>
		<div class="tab">
			<table class="formlist">
				<tbody>
					<tr>
						<td>
							<div class="con_overlfow" 
								 style="width:100%;height:320px;overflow-x:hidden;overflow-y:auto;vertical-align: top;" id="xmList">
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
													<tr title="${map.xmsm }">
														<td>
															<input type="checkbox" value="${map.xmdm }" name="xmdm"/>
														</td>
														<td>
															${map.xmmc }
														</td>
														<td>
															${map.xmje }
														</td>
														<td style="display:none">
															${map.kgbz} 
														</td>
														<td style="display:none">
																${map.xmdyb}
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
													<tr title="${map.xmsm }">
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
		<div>
			<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
				<tfoot>
					<tr>
						<td>
							<div class="btn">
								<button type="button" onclick="selectXm();">
									ȷ��
								</button>
								<button type="button" name="�� ��" onclick="iFClose();">
									�� ��
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
		</div>
	</body>
</html>