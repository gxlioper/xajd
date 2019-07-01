<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<link rel="stylesheet" type="text/css" href="js/jquery/themes/default/easyui.css" />
	    <link rel="stylesheet" type="text/css" href="js/jquery/themes/icon.css" />
	    <script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" defer="defer">
		//初始化
		function showXgInfo(){
		
			var xh = jQuery("#xh").val();
			var sqid = jQuery("#sqid").val();
			
			var url = "xsxx_grxx.do?method=getXgInfo";
	   			url+= "&sqid="+sqid;
	   			url+= "&xh="+xh;

			jQuery.ajaxSetup({async:false});
			
			jQuery.ajax({
				type:'post',
				url:url,
				dataType:'json',
				async: false,
				success:function(result){
					if(result != null && result.length>0){
						for (var i = 0 ; i < result.length; i++){
							var zd = result[i].zd;
							var zdlx = result[i].zdlx;
							var source_table = result[i].source_table;
							var select_dm = result[i].select_dm;
							var select_mc = result[i].select_mc;
							var xgz = result[i].xgz;
							var ysz = result[i].ysz;
							
							if(zdlx=="select"){
								
								if(source_table != "" && source_table != null){
								
									url="customTable.do?method=getListBySource";
									url+= "&tablename="+source_table;
						   			url+= "&dm="+select_dm;
						   			url+= "&mc="+select_mc;
						   			
									jQuery.ajax({
										type:'post',
										url:url,
										dataType:'json',
										async: false,
										success:function(source){
											if(source == false){
												
											}else{
												if(source!=null && source.length!=0){	
													for(var j=0;j<source.length;j++){
														var dm = source[j].dm;
														var mc = source[j].mc;
														
														if(dm == xgz){
															xgz = mc;
															break;
														}
													}
												}
											}
										}
									});
								}else{
									var dm = select_dm.split("!!luojw!!");
									var mc = select_mc.split("!!luojw!!");
									
									for(var j=0;j<dm.length;j++){
										if(dm[j] == xgz){
											xgz = mc[j];
											break;
										}
									}
								}
							}else if(zdlx=="ssx"){
							
								url="xsxx_grxx.do?method=getSsx";
						
								//参数
							 	var parameter = {
									"ssx":xgz
								};
								
								jQuery.post(url,parameter,function(ssx){
									xgz = ssx;
								});
								
							}else if(zdlx=="szbm"){
							
								url="xsxx_grxx.do?method=getSzbm";
						
								//参数
							 	var parameter = {
									"bjdm":xgz
								};
								
								jQuery.post(url,parameter,function(bjdm){
									xgz = bjdm;
								});
							}
							
							var html = "";
								html+= "<font color=\"blue\">";
								html+= xgz;
								html+= "</font>";
<%--								if(ysz !=""){--%>
<%--									html+= "<br/>";--%>
<%--									html+= "(";--%>
<%--									html+= ysz;--%>
<%--									html+= ")";--%>
<%--								}--%>
								
							$("p_"+zd).innerHTML = html;
						}
					}
					
				}
			});
			
			jQuery.ajaxSetup({async:true});
		}
		
		//保存审核状态
		function saveShzt(shzt){
			$("shzt").value = shzt;
			confirmInfo('您确定要'+shzt+"该学生的修改申请吗？",submitShzt);
		}
		
		//提交修改审核
		function submitShzt(tag){
			
			var xh = $("xh").value;
			var sqid = $("sqid").value;
			var shzt = $("shzt").value;
			var shgw = $("shgw").value;
			var shyj = $("shyj").value;
			
			if(tag == "ok"){
			
				var url="xsxx_grxx.do?method=saveShzt";
				$("divWaiting").style.display="";
				$("divDisable").style.display="";
					
				//参数
			 	var parameter = {
			 		"xh":xh,
					"sqid":sqid,
					"shzt":escape(shzt),
					"shyj":escape(shyj),
					"shgw":shgw
				};
				
				jQuery.post(url,parameter,function(result){
					$("divWaiting").style.display="none";
					$("divDisable").style.display="none";
					alertInfo(result);
				});
			}
		}
		</script>
	</head>
	<body onload="showXgInfo()" >
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv();">使用帮助</a>
			</p>
		</div>			
		<!-- 标题 end-->
		<!-- 提示信息 START-->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>提示：</span>
			</h3>
			<p>
				<span id="div_help" style="display: none">
				1.<font color="blue">黑色字样</font>为该信息在学生信息库中的内容，<font color="blue">蓝色字样</font>为该学生本次修改后的信息。</br>
				2.为了页面的整洁，系统隐藏了部分内容，点击相关信息的表头，如<font color="blue">联系方式</font>，可以展开该信息的具体内容。</br>
				</span>
			</p>
			<a class="close" title="隐藏"  onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- 提示信息 end-->
		
		<html:form action="/xsxx_grxx" method="post">

			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="xh" id="xh" value="${rs.xh }"/>
			<input type="hidden" name="sqid" id="sqid" value="${sqid }"/>
			<input type="hidden" name="shgw" id="shgw" value="${shgw }"/>
			<input type="hidden" name="shzt" id="shzt" value=""/>
			<!-- 隐藏域 end-->
			
			<div style="width:100%;height:350px;overflow-x:hidden;overflow-y:auto;">			
				<!-- 基本信息 -->
				<table class="formlist" style="width:95%">
					<thead onclick="">
						<tr onclick="hiddenMk('tb_jbxx')" style="cursor:hand">
							<th colspan="4">
								<span>基本信息</span>
							</th>
						</tr>
					</thead>
					<tbody id="tb_jbxx">
						<tr>
							<th width="20%">
								学号
							</th>
							<td width="30%">
								<p id="p_xh">${rs.xh }</p>
							</td>
							<th width="20%">
								姓名
							</th>
							<td width="">
								<span style="float:left;" id="p_xm">${rs.xm }</span>
							</td>
						</tr>
						<tr>
							<th width="">
								性别
							</th>
							<td width="">
								<span style="float:left;" id="p_xb">${rs.xb }</span>
							</td>
							<th width="">
								出生日期
							</th>
							<td width="">
								<span style="float:left;" id="p_csrq">${rs.csrq }</span>
							</td>
						</tr>
						<tr>
							<th width="">
								民族
							</th>
							<td width="">
								<span style="float:left;" id="p_mz">${rs.mz }</span>
							</td>
							<th width="">
								政治面貌
							</th>
							<td width="">
								<span style="float:left;" id="p_zzmm">${rs.zzmm }</span>
							</td>
						</tr>
						<tr>
							<th width="">
								身份证号
							</th>
							<td width="">
								<span style="float:left;" id="p_sfzh">${rs.sfzh }</span>
							</td>
							<th width="">
								培养层次
							</th>
							<td width="">
								<span style="float:left;" id="p_pycc">${rs.pycc }</span>
							</td>
						</tr>
						<tr>
							<th width="">
								户口所在地
							</th>
							<td width="" colspan="3">
								<span style="float:left;" id="p_hkszd">${rs.hkszd }</span>
							</td>
						</tr>
						<tr>
							<th width="">
								籍贯
							</th>
							<td width="" colspan="3">
								<span style="float:left;" id="p_jg">${rs.jg }</span>
							</td>
						</tr>
						<tr>
							<th width="">
								来源地区(生源地)
							</th>
							<td width="" colspan="3">
								<span style="float:left;" id="p_syd">${rs.syd }</span>
							</td>
						</tr>
					</tbody>
				</table>
				<!-- 基本信息 end-->
				
				<!-- 学籍信息 -->
				<table class="formlist" style="width:95%">
					<thead onclick="">
						<tr onclick="hiddenMk('tb_xjxx')" style="cursor:hand">
							<th colspan="4">
								<span>学籍信息</span>
							</th>
						</tr>
					</thead>
					<tbody  id="tb_xjxx" style="">
						<tr>
							<th width="20%">
								所在部门
							</th>
							<td width="" colspan="3">
								<span style="float:left;" id="p_bjdm">${rs.bjdm }</span>
							</td>
						</tr>
						<tr>
							<th width="20%">
								学籍状态
							</th>
							<td width="30%">
								<span style="float:left;" id="p_xjztm">${rs.xjztm }</span>
							</td>
							<th width="20%">
								学制(年)
							</th>
							<td width="">
								<span style="float:left;" id="p_xz">${rs.xz }</span>
							</td>
						</tr>
						<tr>
							<th width="">
								是否注册
							</th>
							<td width="">
								<span style="float:left;" id="p_sfzc">${rs.sfzc }</span>
							</td>
							<th width="">
								是否走读
							</th>
							<td width="">
								<span style="float:left;" id="p_sfzd">${rs.sfzd }</span>
							</td>
						</tr>
						<tr>
							<th width="">
								入学时间
							</th>
							<td width="">
								<span style="float:left;" id="p_rxrq">${rs.rxrq }</span>
							</td>
							<th width="">
								毕业时间
							</th>
							<td width="">
<%--								<span style="float:left;" id="p_bysj">${rs.bysj }</span>--%>
								<span style="float:left;" id="p_byny">${rs.byny }</span>
							</td>
						</tr>
						<tr>
							<th width="">
								是否毕业生
							</th>
							<td width="">
								<span style="float:left;" id="p_sfbys">${rs.sfbys }</span>
							</td>
							<th width="">
								是否已毕业
							</th>
							<td width="">
								<span style="float:left;" id="p_sfyby">${rs.sfyby }</span>
							</td>
						</tr>
						<tr>
							<th width="">
								是否在校
							</th>
							<td width="">
								<span style="float:left;" id="p_sfzx">${rs.sfzx }</span>
							</td>
							<th width="">
								
							</th>
							<td width="">
	
							</td>
						</tr>
					</tbody>
				</table>
				<!-- 学籍信息 end-->
	
				<!-- 联系方式 -->
				<table class="formlist" style="width:95%">
					<thead onclick="">
						<tr onclick="hiddenMk('tb_lxfs')" style="cursor:hand">
							<th colspan="4">
								<span>联系方式</span>
							</th>
						</tr>
					</thead>
					<tbody id="tb_lxfs" style="display: none">
						<tr>
							<th width="20%">
								固定电话
							</th>
							<td width="30%">
								<span style="float:left;" id="p_lxdh">${rs.lxdh }</span>
							</td>
							<th width="20%">
								手机号码
							</th>
							<td width="">
								<span style="float:left;" id="p_sjhm">${rs.sjhm }</span>
							</td>
						</tr>
						<tr>
							<th width="">
								ＱＱ号码
							</th>
							<td width="">
								<span style="float:left;" id="p_qqhm">${rs.qqhm }</span>
							</td>
							<th width="">
								电子邮箱
							</th>
							<td width="">
								<span style="float:left;" id="p_dzyx">${rs.dzyx }</span>
							</td>
						</tr>
					</tbody>
				</table>
				<!-- 联系方式 end-->
				
				<!-- 家庭信息 -->
				<table class="formlist" style="width:95%">
					<thead onclick="">
						<tr onclick="hiddenMk('tb_jtxx')" style="cursor:hand">
							<th colspan="4">
								<span>家庭信息</span>
							</th>
						</tr>
					</thead>
					<tbody id="tb_jtxx" style="display: none">
						<tr>
							<th width="20%">
								家庭电话
							</th>
							<td width="30%">
								<span style="float:left;" id="p_lxdh1">${rs.lxdh1 }</span>
							</td>
							<th width="20%">
								邮政编码
							</th>
							<td width="">
								<span style="float:left;" id="p_yb">${rs.yb }</span>
							</td>
						</tr>
						<tr>
							<th width="">
								家庭地址
							</th>
							<td width="" colspan="3">
								<span style="float:left;" id="p_jtszd">${rs.jtszd }</span>
							</td>
						</tr>
						<tr>
							<th width="">
								家庭经济情况
							</th>
							<td width="" colspan="3">
								<span style="float:left;" id="p_jjzk">${rs.jjzk }</span>
							</td>
						</tr>
						<tr>
							<td width="" colspan="4">
								<table width="100%">
									<!-- 家庭成员1 -->
									<tbody>
										<tr>
											<td colspan="4" align="center">
												<span>家庭成员1</span>
											</td>
										</tr>
										<tr>
											<th width="20%">
												姓名
											</th>
											<td width="30%">
												<span style="float:left;" id="p_jtcy1_xm">${rs.jtcy1_xm }</span>
											</td>
											<th width="20%">
												与本人关系
											</th>
											<td width="">
												<span style="float:left;" id="p_jtcy1_gx">${rs.jtcy1_gx }</span>
											</td>
										</tr>
										<tr>
											<th width="">
												出生日期
											</th>
											<td width="">
												<span style="float:left;" id="p_jtcy1_nl">${rs.jtcy1_nl }</span>
											</td>
											<th width="">
												身份证号
											</th>
											<td width="">
												<span style="float:left;" id="p_jtcy1_sfzh">${rs.jtcy1_sfzh }</span>
											</td>
										</tr>
										<tr>
											<th width="">
												民族
											</th>
											<td width="">
												<span style="float:left;" id="p_jtcy1_mz">${rs.jtcy1_mz }</span>
											</td>
											<th width="">
												政治面貌
											</th>
											<td width="">
												<span style="float:left;" id="p_jtcy1_zzmm">${rs.jtcy1_zzmm }</span>
											</td>
										</tr>
										<tr>
											<th width="">
												职业
											</th>
											<td width="">
												<span style="float:left;" id="p_jtcy1_zy">${rs.jtcy1_zy }</span>
											</td>
											<th width="">
												职务
											</th>
											<td width="">
												<span style="float:left;" id="p_jtcy1_zw">${rs.jtcy1_zw }</span>
											</td>
										</tr>
										<tr>
											<th width="">
												工作单位电话
											</th>
											<td width="">
												<span style="float:left;" id="p_jtcy1_lxdh1">${rs.jtcy1_lxdh1 }</span>
											</td>
											<th width="">
												个人电话
											</th>
											<td width="">
												<span style="float:left;" id="p_jtcy1_lxdh2">${rs.jtcy1_lxdh2 }</span>
											</td>
										</tr>
										<tr>
											<th width="">
												工作地址
											</th>
											<td width="" colspan="3">
												<span style="float:left;" id="p_jtcy1_gzdz">${rs.jtcy1_gzdz }</span>
											</td>
										</tr>
									</tbody>
									<!-- 家庭成员1 end-->
									
									<!-- 家庭成员2 -->
									<tbody>
										<tr>
											<td colspan="4" align="center">
												<span>家庭成员2</span>
											</td>
										</tr>
										<tr>
											<th width="20%">
												姓名
											</th>
											<td width="30%">
												<span style="float:left;" id="p_jtcy2_xm">${rs.jtcy2_xm }</span>
											</td>
											<th width="20%">
												与本人关系
											</th>
											<td width="">
												<span style="float:left;" id="p_jtcy2_gx">${rs.jtcy2_gx }</span>
											</td>
										</tr>
										<tr>
											<th width="">
												出生日期
											</th>
											<td width="">
												<span style="float:left;" id="p_jtcy2_nl">${rs.jtcy2_nl }</span>
											</td>
											<th width="">
												身份证号
											</th>
											<td width="">
												<span style="float:left;" id="p_jtcy2_sfzh">${rs.jtcy2_sfzh }</span>
											</td>
										</tr>
										<tr>
											<th width="">
												民族
											</th>
											<td width="">
												<span style="float:left;" id="p_jtcy2_mz">${rs.jtcy2_mz }</span>
											</td>
											<th width="">
												政治面貌
											</th>
											<td width="">
												<span style="float:left;" id="p_jtcy2_zzmm">${rs.jtcy2_zzmm }</span>
											</td>
										</tr>
										<tr>
											<th width="">
												职业
											</th>
											<td width="">
												<span style="float:left;" id="p_jtcy2_zy">${rs.jtcy2_zy }</span>
											</td>
											<th width="">
												职务
											</th>
											<td width="">
												<span style="float:left;" id="p_jtcy2_zw">${rs.jtcy2_zw }</span>
											</td>
										</tr>
										<tr>
											<th width="">
												工作单位电话
											</th>
											<td width="">
												<span style="float:left;" id="p_jtcy2_lxdh1">${rs.jtcy2_lxdh1 }</span>
											</td>
											<th width="">
												个人电话
											</th>
											<td width="">
												<span style="float:left;" id="p_jtcy2_lxdh2">${rs.jtcy2_lxdh2 }</span>
											</td>
										</tr>
										<tr>
											<th width="">
												工作地址
											</th>
											<td width="" colspan="3">
												<span style="float:left;" id="p_jtcy2_gzdz">${rs.jtcy2_gzdz }</span>
											</td>
										</tr>
									</tbody>
									<!-- 家庭成员2 end-->
									
									<!-- 家庭成员3 -->
									<tbody>
										<tr>
											<td colspan="4" align="center">
												<span>家庭成员3</span>
											</td>
										</tr>
										<tr>
											<th width="20%">
												姓名
											</th>
											<td width="30%">
												<span style="float:left;" id="p_jtcy3_xm">${rs.jtcy3_xm }</span>
											</td>
											<th width="20%">
												与本人关系
											</th>
											<td width="">
												<span style="float:left;" id="p_jtcy3_gx">${rs.jtcy3_gx }</span>
											</td>
										</tr>
										<tr>
											<th width="">
												出生日期
											</th>
											<td width="">
												<span style="float:left;" id="p_jtcy3_nl">${rs.jtcy3_nl }</span>
											</td>
											<th width="">
												身份证号
											</th>
											<td width="">
												<span style="float:left;" id="p_jtcy3_sfzh">${rs.jtcy3_sfzh }</span>
											</td>
										</tr>
										<tr>
											<th width="">
												民族
											</th>
											<td width="">
												<span style="float:left;" id="p_jtcy3_mz">${rs.jtcy3_mz }</span>
											</td>
											<th width="">
												政治面貌
											</th>
											<td width="">
												<span style="float:left;" id="p_jtcy3_zzmm">${rs.jtcy3_zzmm }</span>
											</td>
										</tr>
										<tr>
											<th width="">
												职业
											</th>
											<td width="">
												<span style="float:left;" id="p_jtcy3_zy">${rs.jtcy3_zy }</span>
											</td>
											<th width="">
												职务
											</th>
											<td width="">
												<span style="float:left;" id="p_jtcy3_zw">${rs.jtcy3_zw }</span>
											</td>
										</tr>
										<tr>
											<th width="">
												工作单位电话
											</th>
											<td width="">
												<span style="float:left;" id="p_jtcy3_lxdh1">${rs.jtcy3_lxdh1 }</span>
											</td>
											<th width="">
												个人电话
											</th>
											<td width="">
												<span style="float:left;" id="p_jtcy3_lxdh2">${rs.jtcy3_lxdh2 }</span>
											</td>
										</tr>
										<tr>
											<th width="">
												工作地址
											</th>
											<td width="" colspan="3">
												<span style="float:left;" id="p_jtcy3_gzdz">${rs.jtcy3_gzdz }</span>
											</td>
										</tr>
									</tbody>
									<!-- 家庭成员3 end-->
								</table>
							</td>
						</tr>
					</tbody>
				</table>
				<!-- 家庭信息 end-->
				
				<!-- 其他信息 -->
				<table class="formlist" style="width:95%">
					<thead>
						<tr onclick="hiddenMk('tb_qtxx')" style="cursor:hand">
							<th colspan="4">
								<span>其他信息</span>
							</th>
						</tr>
					</thead>
					<tbody id="tb_qtxx" style="display: none">
						<tr>
							<th width="20%">
								姓名拼音
							</th>
							<td width="30%">
								<span style="float:left;" id="p_xmpy">${rs.xmpy }</span>
							</td>
							<th width="20%">
								曾用名
							</th>
							<td width="">
								<span style="float:left;" id="p_cym">${rs.cym }</span>
							</td>
						</tr>
						<tr>
							<th width="">
								身高(cm)
							</th>
							<td width="">
								<span style="float:left;" id="p_sg">${rs.sg }</span>
							</td>
							<th width="">
								体重(kg)
							</th>
							<td width="">
								<span style="float:left;" id="p_tz">${rs.tz }</span>
							</td>
						</tr>
						<tr>
							<th width="">
								辅导员姓名
							</th>
							<td width="">
								<span style="float:left;" id="p_fdyxm">${rs.fdyxm }</span>
							</td>
							<th width="">
								一卡通号
							</th>
							<td width="">
								<span style="float:left;" id="p_kh">${rs.kh }</span>
							</td>
						</tr>
						<tr>
							<th width="">
								银行名称
							</th>
							<td width="">
								<span style="float:left;" id="p_yhdm">${rs.yhdm }</span>
							</td>
							<th width="">
								银行卡号
							</th>
							<td width="">
								<span style="float:left;" id="p_yhkh">${rs.yhkh }</span>
							</td>
						</tr>
						<tr>
							<th width="">
								特长
							</th>
							<td width="">
								<span style="float:left;" id="p_tc">${rs.tc }</span>
							</td>
							<th width="">
								考生类别
							</th>
							<td width="">
								<span style="float:left;" id="p_kslb">${rs.kslb }</span>
							</td>
						</tr>
						<tr>
							<th width="">
								入学方式
							</th>
							<td width="">
								<span style="float:left;" id="p_rxfs">${rs.rxfs }</span>
							</td>
							<th width="">
								培养方式
							</th>
							<td width="">
								<span style="float:left;" id="p_pyfs">${rs.pyfs }</span>
							</td>
						</tr>
						<tr>
							<th width="">
								健康状况
							</th>
							<td width="" colspan="3">
								<span style="float:left;" id="p_jkzk">${rs.jkzk }</span>
							</td>
						</tr>
					</tbody>
				</table>
				<!-- 其他信息 end-->
			</div>
			
			<div style="width:100%;height:150px;overflow-x:hidden;overflow-y:auto;">	
				<logic:present name="rsList">
					<table class="formlist" style="width:95%">
						<thead onclick="">
							<tr>
								<th colspan="4">
									<span>审核情况</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<logic:iterate id="shInfo" name="rsList">
								<tr>
									<th width="20%">
										${shInfo.gwmc }审核人
									</th>
									<td width="30%">
										${shInfo.xm }
									</td>
									<th width="20%">
										${shInfo.gwmc }审核时间
									</th>
									<td>
										${shInfo.shsj }
									</td>
								</tr>
								<tr>
									<th>
										${shInfo.gwmc }审核状态
									</th>
									<td colspan="3">
										${shInfo.shzt }
									</td>
								</tr>
								<tr>
									<th>
										${shInfo.gwmc }审核意见
									</th>
									<td style="word-break:break-all;" colspan="3">
										${shInfo.shyj }
									</td>
								</tr>
							</logic:iterate>
						</tbody>
					</table>
				</logic:present>
			</div>	
			<table class="formlist" style="width:95%">
				<tfoot>
					<tr>
						<td colspan='4'>
							<div class="btn">		
								<button type="button" onclick="Close();return false;">
									<bean:message key="lable.btn_gb_space" />
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>

		</html:form>
	</body>
</html>