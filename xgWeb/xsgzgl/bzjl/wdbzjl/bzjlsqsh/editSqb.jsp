<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="xsgzgl/xpjpy/wdpj/bdpz/js/bdpz.js"></script>
		<script type="text/javascript" src="xsgzgl/xpjpy/wdpj/sqsh/js/jxsq.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
			
<%--			jQuery(function(){--%>
<%--				var xh = jQuery("#xh").val();--%>
<%--				--%>
<%--				if (xh != ""){--%>
<%--					showDialog("选择申请奖项",500,350,"bzjl_sqsh.do?method=selectPjxm&xh="+xh);--%>
<%--				}--%>
<%--			});--%>

			jQuery(function(){

				//加载下拉选项
				loadMkxxSelectOptions();
				//加载radio选项
				loadMkxxRadioOptions();

				var xh = jQuery("#xh").val();
				if (xh != ""){
					showDialog("选择申请奖项",500,350,"bzjl_sqsh.do?method=selectPjxm&xh="+xh);
					if(jQuery("#xxdm").val() == "10355"){
						jQuery("#hjxx").load("bzjl_sqsh.do?method=loadHjxx&xh="+xh);
					}
				}
				
			});
			function showSqly(){
				var url = "bzjl_sqsh.do?method=sqlyPdf";
                 window.open(url);

			}

			//增加获奖信息
			function addHjxx(){
				var xh = jQuery("#xh").val();
				var url = "bzjl_sqsh.do?method=getHjjgAdd&xh="+xh;
				showDialog('选择申请奖项',800,550,url);
			}

		/**
		 * 选择评奖项目
		 * @return
		 */
		function showPjxm(){
			var xh = jQuery("#xh").val();
			var url = "bzjl_sqsh.do?method=selectPjxm&xh="+xh;
			if (jQuery.trim(xh) != ""){
                showDialog('选择申请奖项',500,350,url);
			} else {
				showAlertDivLayer("请先选择学生！");
			}
		}
		</script>
	</head>
	<body>
		<html:form action="/bzjl_sqsh.do?method=editSqb" method="post" styleId="sqshForm" onsubmit="return false;">
			<html:hidden property="sqid" />
			<input type="hidden" name="xxdm" id="xxdm" value="${xxdm }"/>
			<input type="hidden" name="xmmc_11799" id="xmmc_11799" value=""/>
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">			
					<thead>
						<tr>
							<th colspan="4">
								<span>基本信息</span>
							</th>
						</tr>
					</thead>
						<%@ include file="/xsgzgl/xpjpy/wdpj/bdpz/selectStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>
									申请奖项
									&nbsp;&nbsp;
									<a onclick="showPjxm()"
									   href="javascript:void(0);">
									   <font color="blue"><u>选择申请奖项</u></font>
									   	
									</a>
									<a href="javascript:void(0);" onclick="addHjxx()" >
										<font color="blue" style="display:none" id="hjbutton" ><u>选择获奖信息</u></font>	
									</a>
								</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>评奖周期</th>
							<td colspan="3">${pjzq }</td>
						</tr>
						<tr>
							<th>奖项</th>
							<td colspan="3">
								<table width="100%">
									<thead>
										<tr>
											<td width="30%">奖项名称</td>
											<td width="15%">金额</td>
											<td width="55%">申请理由填写说明</td>
										</tr>
									</thead>
									<tbody id="sqjx"></tbody>
								</table>
							</td>
						</tr>
						<logic:equal value="10355" name="xxdm">
							<tr id="hjtr" style="display:none">
								<th>获奖信息</th>
								<td colspan="3">
									<table width="100%">
									<thead>
										<tr>
											<td width="30%">获奖名称</td>
											<td width="15%">获奖时间</td>
											<td width="55%">颁奖单位</td>
										</tr>
									</thead>
									<tbody id="hjxx">
										
									</tbody>
								</table>
								</td>
							</tr>
						</logic:equal>
						<logic:equal value="10279" name="xxdm">
						<tr>
							<th>
								奖项名称<br/><font color="red">(限500字)</font>
							</th>
							<td colspan="3">
								<html:textarea  property="ylzd1" styleId="ylzd1"  style="width:94%;height:50px"  onblur="chLengs(this,500);"></html:textarea>
							</td>
						</tr>
						<tr>
							<th>
								获奖时间<br/><font color="red">(限200字)</font>
							</th>
							<td colspan="3">
								<div>
									<html:textarea  property="ylzd3" styleId="ylzd3"  style="width:94%;height:50px"  onblur="chLengs(this,200);"></html:textarea>
								</div>
							</td>
						</tr>
						<tr>
							<th >
								主办单位<br/><font color="red">(限200字)</font>
							</th>
							<td colspan="3">
								<html:textarea  property="ylzd4" styleId="ylzd4"  style="width:94%;height:50px"  onblur="chLengs(this,200);"></html:textarea>
							</td>
						</tr>
						</logic:equal>
						
						<tr id="fjxx">
							<th>
								附件信息
								<logic:equal value="10279" name="xxdm">								
									</br>								
									<font color="red">（新生奖和专业奖必须上传证书及材料）</font>
								</logic:equal>
							</th>
							<td colspan="3">
								<html:hidden property="ylzd5" styleId="ylzd5" />
								<%@ include file="/xsgzgl/comm/fileUpload/f.jsp"%>
								<script type="text/javascript">
									//调用附件 
									jQuery(function(){
										jQuery.MultiUploader({
											maxcount : ${xxdm=='12713'?10:3},
											//后缀
											accept : 'png|gif|jpg|zip|rar|doc|docx|pdf',
											//最大文件大小 单位M
											maxsize: ${xxdm=='12713'?30:10},
											//存放附件的隐藏域的id
											elementid : 'ylzd5'
											});
										
										//浙江同济科技职业技术学院，追加2条附件上传提示
										if(${xxdm=='12647'}){
											var tipsPrepend = "★ 文件请根据获奖情况命名，如：优秀毕业生、积极分子佐证材料照片。</br>"+
															  "★ 如需上传多个文件，请成功选择一个后，再次点击选择文件继续。</br>";
											jQuery('#tips').prepend(tipsPrepend);
										}

										//上海戏剧学院，增加1条附件上传提示
										if(${xxdm=='10279'}){
											var html = "</br><span style='margin-left:36px'>4.上传文件以学号命名！</span>";
											jQuery('#tips').append(html);
										}
									
									});
								</script>
							</td>
						</tr>
						<%--徐州医药高等专科个性化字段--%>
						<logic:equal value="70002" name="xxdm">
						<tr>
							<th>
								曾受何奖励
								<br />(限500字)
							</th>
							<td colspan="3" style="word-break:break-all;">
								<!-- <div id="txsmDiv" style="color:red"></div> -->
								<html:textarea property="djjl" styleId="djjl" style="width:100%;" rows="5" onblur="checkLen(this,500);"></html:textarea>
							</td>
						</tr>
						</logic:equal>
						<tr id="sqlyTr">
							<logic:notEqual value="12056" name="xxdm">
							<th>
								<span class="red">*</span>	<logic:equal value="70002" name="xxdm">
									主要事迹
								</logic:equal>
								<logic:notEqual value="70002" name="xxdm">
									申请理由
								</logic:notEqual>
								<br />(限500字)
							</th>
							<td colspan="3" style="word-break:break-all;">
								<!-- <div id="txsmDiv" style="color:red"></div> -->
								<%--重庆工商大学单项奖学金个性化--%>
									<div  id="dxjxjTr" style="display: none;margin-bottom:5px; ">
										<span  style="color:red">请申请单项奖学金的同学在申请理由处按规则填写表格的内容</span>
										<button type="button"  onclick="showSqly();" id="buttonClose">
											填写规则
										</button>
									</div>
								<html:textarea property="sqly" styleId="sqly" style="width:100%;" rows="5" onblur="checkLen(this,500);"></html:textarea>
							</td>
							</logic:notEqual>
							<logic:equal value="12056" name="xxdm">
							<th>
								<span class="red">*</span>申请理由
								<br />(限150-250字)
							</th>
							<td colspan="3" style="word-break:break-all;">
								<!-- <div id="txsmDiv" style="color:red"></div> -->
								<html:textarea property="sqly" styleId="sqly" style="width:100%;" rows="5" onblur="checkLenBtw(this,150,250);"></html:textarea>
							</td>
							</logic:equal>
						</tr>
					</tbody>
				</table>
			</div>
			<div style="height: 30px"></div>				
			<div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<button type="button" onclick="saveJxsq('save');return false;">
										保存草稿
									</button>
									<button type="button" onclick="saveJxsq('submit');return false;">
										提交申请
									</button>
									<button type="button"  onclick="iFClose();" id="buttonClose">
										关 闭
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

