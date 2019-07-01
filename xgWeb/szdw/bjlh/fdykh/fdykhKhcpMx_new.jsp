<%@ page language="java" pageEncoding="GBK"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="<%=request.getContextPath() %>/szdw/bjlh/fdykh/js/fdykhcptj_new.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				jQuery(".clickDetail1").css("cursor" , "pointer").css("color" , "blue").css("text-decoratione" , "underlin");
				jQuery(".clickDetail2").css("cursor" , "pointer").css("color" , "blue").css("text-decoratione" , "underlin");
				
				jQuery(".clickDetail1").bind("click" , function(){
						var _xh = jQuery(this).parent().find("input[name='_xh']");
						var _fdyid = jQuery(this).parent().find("input[name='_fdyid']");
						var _khbid = jQuery(this).parent().find("input[name='_khbid']");
						//var tjlx = jQuery("#tjlx").val();
						var data = {yhm:_xh.val(),fdyid:_fdyid.val(),khbid:_khbid.val(),type:"1"};
						var url = "bjlh_fdykh.do?method=fdykhKhMxDetailNew";
						jQuery.post(url, data , function(dataList){
							var html = "";
							jQuery.each(dataList , function(i , n){
								if(n['r'] == undefined ){
										jQuery(".zfClass1").find(".tjfz").replaceWith('<b class="tjfz">' + n + '</b>');;
									}
										else
									html += '<tr class="dataListR1"><td width="15%">' + n['r'] + '</td><td>'+ n['khnr'] + '</td><td>'+ n['fz'] + '</td></tr>';
								});
							jQuery('.dataListR1').empty();
							jQuery('.zfClass1').before(html);
							} , "json");
					});

				jQuery(".clickDetail2").bind("click" , function(){
					var _fdyid = jQuery(this).parent().find("input[name='_fdyid']");
					var _xmid = jQuery(this).parent().find("input[name='_xmid']");
					var _bjdm = jQuery(this).parent().find("input[name='_bjdm']");
					var _zzlbmc = jQuery(this).parent().find("input[name='_zzlbmc']");
					var tjlx = jQuery('input[name="tjlx"]').val();
					var data = {fdyid:_fdyid.val(),xmid:_xmid.val(),bjdm:_bjdm.val(),zzlbmc:_zzlbmc.val(),type:tjlx + "_2"};
					var url = "bjlh_fdykh.do?method=fdykhKhMxDetailNew";
					jQuery.post(url, data , function(dataList){
						var html = "";
						jQuery.each(dataList , function(i , n){
							if(n['r'] == undefined ){
									jQuery(".zfClass2").find(".tjfz").replaceWith('<b class="tjfz">' + n + '</b>');;
								}
									else
								html += '<tr class="dataListR2"><td width="15%">' + n['xm'] + '</td><td>'+ n['fz'] + '</td></tr>';
							});
						jQuery('.dataListR2').empty();
						jQuery('.zfClass2').before(html);
						} , "json");
				});
				
			});
		</script>
	</head>
	<body>
		<input type="hidden" name="tjlx" value="${tjlx}"/>
		<div class="comp_title" id="comp_title">
	      <ul style="width:90%">
	        <li class="ha"><a href="javascript:void(0);" onclick="selecttjlb(this,'xsmx');"><span>按学生查看明细</span></a></li>
	        <li><a href="javascript:void(0);" onclick="selecttjlb(this,'tmmx');"><span>按题目查看明细</span></a></li>
	      </ul>
	    </div>
		<div class="tab"  >
			<table class="formlist">
				<%--<tfoot>
					<tr>
						<td>
							<div class="btn">
								<button type="button" name="关 闭" onclick="iFClose();">
									关 闭
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
				--%><tbody>
					<tr>
						<td>
							<div class="con_overlfow" 
								 style="width:100%;overflow-x:hidden;overflow-y:auto;vertical-align: top;">
								<table class="dateline" width="100%" id="xsmx">
									<thead>
										<tr>
											<td >
												学生
											</td>
											<td >
												分值
											</td>
										</tr>
									</thead>
									<tbody>
										<logic:present name="xsmxList" >
											<logic:iterate name="xsmxList" id="map" indexId="i">
													<tr class="xsmxRow" title="点击查看详情">
														<input type="hidden" name="_xh" value="${map.yhm}"/>
														<input type="hidden" name="_khbid" value="${map.khbid}"/>
														<input type="hidden" name="_fdyid" value="${map.fdyid}"/>
														<input type="hidden" name="_bjdm" value="${map.bjdm}"/>
														<td width="20%">
															${map.xm }
														</td>
														<td class="clickDetail1">
															${map.fz }
														</td>
													</tr>
											</logic:iterate>
											<tr >
												<td style="color:green"><b>平均分<b/></td>
												<td style="color:green"><b>
													<%=request.getAttribute("xsmxPjf") %>
												<b/></td>
											</tr>
										</logic:present>
									</tbody>
									</table>
									
									<table class="dateline" width="100%" id="tmmx" style="display:none;">
									<thead>
										<tr>
											<td>
												题目序号
											</td>
											<td>
												题目内容
											</td>
											<td>
												平均分
											</td>
										</tr>
									</thead>
									<tbody >
										<logic:present name="tmmxList" >
											<logic:iterate name="tmmxList"  id="map" indexId="i">
													<tr class="tmxmRow" title="点击查看详情">
														<input type="hidden" name="_xmid" value="${map.xmid}"/>
														<input type="hidden" name="_fdyid" value="${map.fdyid}"/>
														<input type="hidden" name="_bjdm" value="${map.bjdm}"/>
														<input type="hidden" name="_zzlbmc" value="${map.zzlbmc}"/>
														<td width="10%">
															${map.r }
														</td>
														<td>
															${map.khnr }
														</td>
														<td class="clickDetail2">
															${map.xspjf }
														</td>
													</tr>
											</logic:iterate>
											<tr >
												<td style="color:green" colspan="2"><b>总分<b/></td>
												<td style="color:green"><b>
													<%=request.getAttribute("tmmxZf") %>
												<b/></td>
											</tr>
										</logic:present>
									</tbody>
								</table>
							</div>
						</td>
					</tr>
					<tr>
						<td>
							<div class="con_overlfow_1"
								 style="width:100%;overflow-x:hidden;overflow-y:auto;vertical-align: top;">
								<table width="100%" border="0" class="formlist" id="xsmx_1">
									<thead>
										<tr>
											<td colspan = "3">
												<b/>明细
											</td>
										</tr>
									</thead>
								
									<tbody id="dataTB1">
									
										<tr>
											<td><b>题目序号</td>
											<td><b>题目内容</td>
											<td><b>分值</td>
										</tr>
										
										<tr class="zfClass1">
												<td style="color:green" colspan="2"><b>总分<b/></td>
												<td style="color:green"><b class="tjfz">
													0
												<b/></td>
											</tr>
									</tbody>
								</table>
								<table width="100%" border="0" id="tmmx_1"  class="formlist" style="display:none;">
									<thead>
										<tr>
											<td colspan = "3">
												<b/>明细
											</td>
										</tr>
									</thead>
								
									<tbody id="dataTB2">
									
										<tr>
											<td><b>学生</td>
											<td><b>分值</td>
										</tr>
										
										<tr class="zfClass2">
												<td style="color:green"><b>平均分<b/></td>
												<td style="color:green" ><b class="tjfz">
													0
												<b/></td>
											</tr>
									</tbody>
								</table>
							</div>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</body>
</html>
