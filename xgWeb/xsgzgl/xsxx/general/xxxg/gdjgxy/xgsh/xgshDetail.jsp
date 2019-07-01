<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 --> 
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type='text/javascript' src="js/uicomm.js"></script>
		<script type='text/javascript' src="js/String.js"></script>
		<script type='text/javascript' src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" defer="defer">
		//保存審核狀態
		function saveShzt(shzt){
		
			var shyj = jQuery("#textarea_shyj").val();
			
			if(shyj != ""){
				if(shyj.length > 500){
					alertError("审核意见不能超过500字，请确认");
					return false;
				}
			}
			
			var msg = "请您确认是否";
				if(shzt == "tg"){
					msg += "<font color='blue'>通过</font>";
				}else if(shzt == "th"){
					msg += "<font color='blue'>退回</font>";
				}
				msg += "该学生的信息修改申请";
				
			confirmInfo(msg,function(tag){
				if(tag=="ok"){
					var url = "xsxx_xxxg_xgsh.do?method=saveShzt";
					var sqid = jQuery("#hidden_sqid").val();
					var gwid = jQuery("#hidden_gwid").val();
					var pkValue=new Array();	
					
					
					var parameter={
						"array_pkValue":sqid,
	 					"str_gwid":gwid,
	 					"str_shyj":escape(shyj),
	 					"str_shzt":shzt
					};
			
				 	$("divWaiting").style.display="";
					$("divDisable").style.display="";
					
					jQuery.ajaxSetup({async:false});
					
					jQuery.post(url,
						parameter,
						function(result){
							$("divWaiting").style.display="none";
							$("divDisable").style.display="none";
							//alertInfo(result);
							showAlert(result,{},{"clkFun":function(){
				 				if (parent.window){
				 					refershParent();
				 				}
				 			}});
						}
					);
			
					jQuery.ajaxSetup({async:true});
				}
			});
		}
		</script>
		<style>
			.include_tab{border-collapse:collapse;border:0px;}
			.include_tab td{border-top:0;bordedr-right:1px solid red!importalt;border-bottom:0;border-left:0;}
			.include_tab_r{}
		</style>
	</head>
	<body ondrag="return false">
		<html:form action="/general_xsxx"  method="post">
		
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="sqid" id="hidden_sqid" value="${sqid }"/>
			<input type="hidden" name="gwid" id="hidden_gwid" value="${gwid }"/>
			
			<!-- 学生信息 begin-->
			<div class="demo_xxxx" 
				style="overflow-x:hidden;overflow-y:auto;height:510px;margin-bottom: 0px;" 
				id="demo_xxxx" >
				
				<!-- 基本信息 begin-->
				
				<table width="100%" border="0" class="formlist" id="tab_jbxx">
					<thead>
						<tr onclick="" style="cursor: hand;">
							<th colspan="5" >
								<span>基本信息【注：<font color="red">红色</font>内容为学生修改后的信息，鼠标移动到该值上方可查看该信息的原始值】</span>
							</th>
						</tr>
					</thead>					
					<tbody id="hi_jbxx">
						<tr>
						<th width="13%">
							学号
							</th>
							<td width="30%">
								${rs.xh }
								<input type="text" style="width:10px" class="include_tab" readonly="readonly"/>
							</td>
							<th width="13%">
								姓名
							</th>
							<td >
								${rs.xm }
							</td>
							<td rowspan="5" class="nohover"
								style="vertical-align:middle; text-align:left;width:115px">
								<div id="stuImg" class="open_img" style="margin-left:0px;">
									<img style="width:100px;height:140px;" 
										src="<%=request.getContextPath()%>/stuPic.jsp?xh=${rs.xh }"
										border="0"/>
								</div>
							</td>
						</tr>
						<tr>
						<th width="13%">
							学警号
							</th>
							<td width="30%">
								${rs.zd1 }
							</td>
							
							<th width="13%">
							性别
							</th>
							<td width="30%">
								${rs.xb }
							</td>
							
						</tr>
						
						<tr>
						<th width="13%">
								出生日期
							</th>
							<td >
								${rs.csrq }
							</td>
							<th width="13%">
							年级
							</th>
							<td width="30%">
								${rs.nj }
							</td>
							
						</tr>
						<tr>
						<th>
								学制(年)
							</th>
							<td colspan="">
								${rs.xz }
							</td>
							<th>
								考生类别
							</th>
							<td>
							${rs.kslbmc }
							</td>
							
						</tr>
						<tr>
							<!-- <th>
								学籍异动
							</th>
							<td align="left" >
								${rs.zd2}
							</td> -->
							<th>
								籍贯
							</th>
							<td >
								${rs.jgmc }
							</td>
							<th>
								系部
							</th>
							<td>
								${rs.xymc }
							</td>
						</tr>
						<tr>
						<th>
								政治面貌
							</th>
							<td>
								${rs.zzmmmc }
							</td>
							<th >
								专业
							</th >
							<td colspan="2">
								${rs.zymc }
							</td>
						</tr>
						<tr>
						<th>
								民族
							</th>
							<td>
								${rs.mzmc }
							</td>
							<th>
								班级
							</th>
							<td colspan="2">
								
								${rs.bjmc }
							</td>
						
						</tr>
					
						<tr>
								<th>
							身份证件类型
							</th>
							<td align="left">
								居民身份证
							</td>
							<th>
							身份证号
							</th>
							<td align="left" colspan="2">
								${rs.sfzh}
							</td>
						</tr>
						
						<!--  	<tr>
						<th>
								入学时间
							</th>
							<td >
								${rs.rxrq }				
							</td>
							<th>
								毕业时间
							</th>
							<td colspan="2">
								${rs.bysj }				
							</td>
							
						</tr>
					<tr>
						<th>
								国籍
							</th>
							<td align="left" >
								${rs.gj}
							</td>
						<th>
								出生地
							</th>
							<td align="left" colspan="2">
								${rs.csd}
							</td>
						</tr>
						-->
						<tr>
							<th>
								入学时间
							</th>
							<td >
								${rs.rxrq }				
							</td>
							<th>
								户口所在地
							</th>
							<td colspan="2">
								${rs.hkszdmc }	
							</td>
						</tr>
						<tr>
							<th>
								来源地区(生源地)
							</th>
							<td colspan="4">
								${rs.sydmc }
							</td>
						</tr>
					</tbody>
					
				</table>
				
				<!-- 基本信息 end-->
				
				<table width="100%" border="0" class="formlist" id="tab_lxfs">
					<!-- 学生联系方式 begin-->
					<thead>
						<tr onclick="" style="cursor: hand;">
							<th colspan="5">
								<span>联系方式</span>
							</th>
						</tr>
					</thead>
					<tbody id="hi_lxfs" >
						<tr>
							<th width="13%">
								联系电话
							</th>
							<td colspan="" width="30%">
							${rs.sjhm }
								
							</td>
							<th width="13%">
								手机短号
							</th>
							<td align="left" colspan="2">
								${rs.zd2 }
							</td>
						</tr>
						<tr>
							<th>
								QQ号码
							</th>
							<td>
								${rs.qqhm }
							</td>
							<th width="13%">
								电子邮箱
							</th>
							<td align="left" colspan="2">
								${rs.dzyx }
							</td>
							</tr>
						<tr>
						<th>
								家庭电话
							</th>
							<td align="left" >
								${rs.jtdh }
							</td>
							<th>
								家庭邮编
							</th>
							<td colspan="2">
								${rs.jtyb }
							</td>
						</tr>
						<tr>
							<th>
								家庭地址
							</th>
							<td align="left" colspan="2">
								${rs.jtszd }
							</td>
						</tr>
					</tbody>
				</table>
				
				
				<table width="100%" border="0" class="formlist" id="tab_jtcyxx">
					<!-- 学生家庭成员信息 begin-->
					<thead>
						<tr onclick="" style="cursor: hand;">
							<th colspan="5">
								<span>家庭成员信息</span>
							</th>
						</tr>
					</thead>
					
					<tbody id="hi_jtcyxx" >
						<tr>
							<th width="13%">
								<div align="center">姓名</div>
							</th>
							<th >
								<div align="center">与本人关系</div>
							</th>
							<th>
								<div align="center">工作单位及地址</div>
							</th>
							<th>
								<div align="center">单位电话</div>
							</th>
							<th>
								<div align="center">个人电话</div>
							</th>
						</tr>
						<tr>
							<td align="center">
								${rs.jtcy1_xm }&nbsp;
							</td>
							<td align="center">
							${rs.jtcy1_gx }
							</td>
							<td align="center">
							${rs.jtcy1_gzdz }
							</td>
							<td align="center">
							${rs.jtcy1_lxdh2 }
							</td>
							<td align="center">
							${rs.jtcy1_lxdh1}
							</td>
						</tr>
						<tr>
							<td align="center">
								${rs.jtcy2_xm }&nbsp;
							</td>
							<td align="center">
							${rs.jtcy2_gx }
							</td>
							<td align="center">
							${rs.jtcy2_gzdz }
							</td>
							<td align="center">
							${rs.jtcy2_lxdh2 }
							</td>
							<td align="center">
							${rs.jtcy2_lxdh1}
							</td>
						</tr>
						<tr>
							<td align="center">
								${rs.jtcy3_xm }&nbsp;
							</td>
							<td align="center">
								${rs.jtcy3_gx }
							</td>
							<td align="center">
								${rs.jtcy3_gzdz }
							</td>
							<td align="center">
								${rs.jtcy3_lxdh2 }
							</td>
							<td align="center">
								${rs.jtcy3_lxdh1}
							</td>
						</tr>
					</tbody>
					
				</table>
			
				<table width="100%" border="0" class="formlist" id="tab_qtxx">
					<!-- 学生其他信息 begin-->
					<thead>
						<tr onclick="" style="cursor: hand;">
							<th colspan="5">
								<span>其他信息</span>
							</th>
						</tr>
					</thead>
					<tbody id="hi_qtxx" >
					<tr>
							<th>
								银行名称
							</th>
							<td align="left">
								${rs.yhdm }
							</td>
							<th>
								银行卡号
							</th>
							<td colspan="2">
								${rs.yhkh}
							</td>
						</tr>
					
					<tr>
							<th width="13%">
								姓名拼音
							</th>
							<td width="30%">
								${rs.xmpy }
							</td>
							<th width="13%">
								曾用名
							</th>
							<td align="left" colspan="2">
								${rs.cym }
							</td>

						</tr>
						<tr>
							<th>
								身高(cm)
							</th>
							<td align="left">
								${rs.sg }
							</td>
							<th>
								体重(kg)
							</th>
							<td colspan="2">
								${rs.tz}
							</td>
						</tr>
						<tr>
						<th>
								特长
							</th>
							<td>
								${rs.tc }
							</td>
							<th>
								健康状况
							</th>
							<td colspan="2">
								${rs.jkzk }
							</td>
							</tr>
								<tr>
							<th>
								培养层次
							</th>
							<td colspan="">
								${rs.pyccmc }
							</td>
							<th>
								是否走读生
							</th>
							<td align="left" colspan="2">
								${rs.sfzd }
							</td>
						</tr>
						
						<tr>
							<th>
								培养方式
							</th>
							<td>
								${rs.pyfsmc }
							</td>
							<th>
								户籍性质
							</th>
							<td colspan="2">
								${rs.zd5 }
							</td>
						</tr>
						
						<tr>
							<th>
								高考报名号
							</th>
							<td>
								${rs.ksh }
							</td>
							<th>
								高中毕业中学
							</th>
							<td colspan="2">
								${rs.rxqdw }
							</td>
						</tr>
						
						<tr>
							<th>
								婚姻状态
							</th>
							<td>
								${rs.sfjh }
							</td>
							<th>
								宗教信仰
							</th>
							<td colspan="2">
								${rs.zjdm }
							</td>
						</tr>
						
						<tr>
							<th>
								血型
							</th>
							<td>
								${rs.xx }
							</td>
							<th>
								家庭结构
							</th>
							<td colspan="2">
								${rs.jtcygc }
							</td>
						</tr>
						<tr>
							<th>
								乘车区间
							</th>
							<td>
								${rs.ccqj }
							</td>
							<th>
								担任班干部
							</th>
							<td colspan="2">
								${rs.zw }
							</td>
						</tr>
						<tr>
							
							<th>
								入学方式
							</th>
							<td colspan="4">
								${rs.rxfsmc }
							</td>
						</tr>
						
						<tr>
							<th>
								备注
							</th>
							<td colspan="4">
								<div  style="word-break:break-all;width:97%">${rs.bz }</div>
							</td>
							</tr>
					</tbody>
				</table>
				
				<!-- 審核信息 begin -->
				<table width="100%" border="0" class="formlist" id="tab_shxx">
					<thead>
						<tr onclick="" style="cursor: hand;">
							<th colspan="5">
								<span>审核信息</span>
							</th>
						</tr>
					</thead>
					<tbody id="hi_shxx" >
						<logic:iterate name="rsList" id="shxx">
							<logic:equal name="shxx" property="sfxs" value="yes">
								<tr>
									<th width="13%">
										${shxx.gwmc }审核状态
									</th>
									<td width="30%">
										<logic:equal name="shxx" property="shzt" value="wsh">
											<p><img src="<%=stylePath%>images/ico_dsh.gif" width="52" height="18" /></p>
										</logic:equal>
										<logic:equal name="shxx" property="shzt"  value="tg">
											<p><img src="<%=stylePath%>images/ico_shtg.gif" width="52" height="18" /></p>
										</logic:equal>
										<logic:equal name="shxx" property="shzt"  value="btg">
											<p><img src="<%=stylePath%>images/ico_shbtg.gif" width="52" height="18" /></p>
										</logic:equal>
										<logic:equal name="shxx" property="shzt"  value="th">
											<p><img src="<%=stylePath%>images/ico_shth.gif" width="52" height="18" /></p>
										</logic:equal>
										<logic:equal name="shxx" property="shzt"  value="xcs">
											<p><img src="<%=stylePath%>images/ico_shxcs.gif" width="52" height="18" /></p>
										</logic:equal>
									</td>
									<th width="13%">
										${shxx.gwmc }审核人
									</th>
									<td align="left" colspan="2">
										${shxx.shr }
									</td>
								</tr>
								<tr>
									<th width="13%">
										${shxx.gwmc }审核时间
									</th>
									<td width="30%">
										${shxx.shsj }
									</td>
									<th width="13%">
										${shxx.gwmc }审核意见
									</th>
									<td align="left" colspan="2">
										<div  style="word-break:break-all;width:97%">
											${shxx.shyj }
										</div>
									</td>
								</tr>
							</logic:equal>
						</logic:iterate>
						<tr>
							<th width="13%">
								审核意见
								<br/>
								<font color="blue">(限500字)</font>
							</th>
							<td align="left" colspan="4">
								<textarea rows="5" id="textarea_shyj" style="word-break:break-all;width:90%"><logic:iterate name="rsList" id="shxx"><logic:equal name="shxx" property="bjyj" value="yes">${shxx.shyj }</logic:equal></logic:iterate></textarea>
							</td>
						</tr>
					</tbody>
				</table>
				<!-- 審核信息 end -->
			</div>
			<!--  学生信息end -->
			
			<!-- 操作按鈕 begin --> 
			<div style="height:15px">
				<table width="100%" border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="5">
								<div class="btn">
									<button type="button" onclick="saveShzt('tg');">通 过</button>
									<button type="button" onclick="saveShzt('th');">退 回</button>		
									<button type="button" onclick="Close();return false;" id="buttonClose">关 闭</button>					           
								</div>
							</td>
						</tr>
					</tfoot>
				</table>			
			</div>
			<!-- 操作按鈕 end -->	
			
			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>