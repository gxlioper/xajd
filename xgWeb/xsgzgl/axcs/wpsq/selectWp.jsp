<%@ page language="java" pageEncoding="GBK"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				//条件
				jQuery.each(jQuery("img[name=faidImg]"),function(i,n){
					jQuery(n).parent().parent().find(":checkbox").attr("disabled",true);
				});
			});
			function selectXm() {
				var wpxm = jQuery("input[name=xmdm]:checked");
				var gotoPath = jQuery("#gotoPath").val();
				if (wpxm.size() != 1) {
					showAlert("请选择一项您要申请的物品！");
					return false;
				}
				var xmdm = jQuery("input[name=xmdm]:checked").val();
				gotoPath = gotoPath + "&xmdm=" + xmdm + "&xh=" + jQuery("#xh").val();
				var api = frameElement.api;
				if (api) {
					if (api.get('childDialog')) {
						api.reload(api.get('parentDialog'), gotoPath);
					} else {
						var W = api.opener;
						W.location = gotoPath;
					}
				} else if (parent.window) {
					parent.window.document.location = gotoPath;
				}
				api.close();
			}
			/**
			 * 切换已申请、未申请
			 * 
			 * @param obj
			 * @param tabId
			 */
			function selectSqwp(obj, tabId) {

				jQuery(".ha").removeClass("ha");
				jQuery(obj).parent().addClass("ha");

				jQuery(".con_overlfow table").css("display", "none");
				jQuery("#" + tabId).css("display", "");

			}
		</script>
	</head>
	<body>
	<input type="hidden" value="${gotoPath}" id="gotoPath"/>
	<input type="hidden" value="${xh}" id="xh"/>
		<div class="comp_title" id="comp_title">
	      <ul style="width:90%">
	        <li class="ha"><a href="javascript:void(0);" onclick="selectSqwp(this,'wsq');"><span>未申请</span></a></li>
	        <li><a href="javascript:void(0);" onclick="selectSqwp(this,'ysq');"><span>已申请</span></a></li>
	      </ul>
	    </div>
		<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
				<tbody>
					<tr>
						<td>
							<div class="con_overlfow" 
								 style="width:100%;height:240px;overflow-x:hidden;overflow-y:auto;vertical-align: top;" id="xmList">
								<table class="dateline" width="100%" id="wsq">
									<thead>
										<tr>
											<td width="2%"></td>
											<td width="20%">
												物品名称
											</td>
											<td width="10%">
												物品类别
											</td>
											<td>
												限制条件
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
															${map.xmlbmc }
														</td>
														<td style="display:none">
															${map.kgbz} 
														</td>
														<td>
															<logic:present name="resultMap" property="${map.xmdm }">
																<logic:iterate id="check" name="resultMap" property="${map.xmdm }" indexId="i">
																	<logic:equal value="true" name="check" property="result">
																		<img src='images/ico_38.gif' title='符合条件' /> ${check.sqtj }<br/>
																	</logic:equal>
																	<logic:equal value="false" name="check" property="result">
																		<img src='images/ico_39.gif' name='faidImg' title='不符合条件'/>${check.sqtj }<br/>
																	</logic:equal>
																</logic:iterate>
															</logic:present>
															<logic:notPresent name="resultMap" property="${map.xmdm }">
																<font color='green'>无限制条件</font>
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
											<td width="20%">
												物品名称
											</td>
											<td width="10%">
												物品类别
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
															${map.xmlbmc }
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
		<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
		<tfoot>
					<tr>
						<td>
							<div class="btn">
								<button type="button" onclick="selectXm();">
									确定
								</button>
								<button type="button" name="关 闭" onclick="iFClose();">
									关 闭
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
		</table>		
	</body>
</html>
