<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
		
			jQuery(function(){
				var api = frameElement.api;
				W = api.opener;
				var grid = W.jQuery("#dataTable");
				var rows = grid.getSeletRow();
				var tbody = jQuery('#sqjx');
				var xmmc = "";
				for(i = 0 ; i < rows.length ; i ++){
					var tr = jQuery("<tr></tr>");
					var item = rows[i];
					var xmdm = jQuery("<input type='hidden' name='xmdms' value='"+item['xmdm']+"'/>");
					var td1 = jQuery("<td></td>").append(item['xmmc']).append(xmdm);
					var td2 = jQuery("<td></td>").append(item['xmlx']);
					var td3 = jQuery("<td></td>").append(item['xmje']);

					tr.append(td1);
					tr.append(td2);
					tr.append(td3);
					tbody.append(tr);
					jQuery("#txsmDiv").html(item['kgbz']);
					xmmc += item['xmmc']
				}

                //ie9及以下无此事件
                jQuery("#sqly").bind("input",function(){
                    chCount($(this),180,200);
                });

				if('11799' == ${xxdm}){
					if("单项奖学金（体育运动）" == xmmc.trim() || 
							"单项奖学金" == xmmc.trim()||
							 "单项奖学金（文艺活动）" == xmmc.trim() || 
							 "单项奖学金（科技创新）" == xmmc.trim() || 
							 "单项奖学金（学习优秀1）" == xmmc.trim() || 
							 "单项奖学金（学习优秀3）" == xmmc.trim() || 
							 "单项奖学金（学习进步）" == xmmc.trim()){
						jQuery("#dxjxjTr").show();
						jQuery("#xmmc_11799").val(xmmc.trim());
					}
				}
				//中国美术学院个性化
				if('10355' == "${xxdm}" && jQuery("[name='xmdms']").length == 1){
					var kg = "true";
					var url = "xpj_sqsh.do?method=getSfkqHjxx";
					jQuery.ajax({
					type:'post',
					url:url,
					dataType:'json',
					contentType:"application/x-www-form-urlencoded; charset=UTF-8",
					data:'xmdm='+jQuery("[name='xmdms']").eq(0).val(),
					async: false,
					success:function(result){
						kg = result["message"];
					}
					
				});
					if(kg == "true"){
						jQuery("#hjxx").load("xpj_sqsh.do?method=loadHjxx&xh="+jQuery("#xh").val());
						jQuery("#hjbutton").show();
						jQuery("#hjtr").show();
					}
				}
				
			});


			
			/**
			 * 保存申请
			 * @returns {Boolean}
			 */
			function saveJxsq(type){
				
				if (jQuery("#sqly").val() == ""){
					showAlert("请将必填项填写完整！");
					return false;
				}

				if("10355"==${xxdm}){
					if(jQuery("#sqly").val().indexOf("我") >= 0){
						showAlert("申请理由不能带'我'等特殊字符！");
						return false;
					}
				}
				
				var url = "xpj_sqsh.do?method=saveJxsq&type="+type;
				//var url = "xpj_pjxmsq.do?method=saveJxsq";
				ajaxSubFormWithFun("sqshForm",url,function(data){
					showAlert(data["message"],{},{"clkFun":function(){
						if (parent.window){
							var api = frameElement.api;
							W = api.opener;
							var grid = W.jQuery("#dataTable").reloadGrid();
							iFClose();
						}
					}});
				});
			}
			 function showSqly(){
					var url = "xpj_sqsh.do?method=sqlyPdf";
	                 window.open(url);

				}

            //增加获奖信息
            function addHjxx(){
                var xh = jQuery("#xh").val();
                var url = "xpj_sqsh.do?method=getHjjgAdd&xh="+xh;
                showDialog('选择申请奖项',800,550,url);
            }
		</script>
	</head>
	<body>
		<html:form action="/xpj_sqsh" method="post" styleId="sqshForm">
			<html:hidden property="sqid" />
			<input type="hidden" name="xh" value="${jbxx.xh}"  id="xh" style="width:120px;"/>
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;' >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>
									评奖项目申请信息
									&nbsp;&nbsp;
									<a href="javascript:void(0);" onclick="addHjxx()" >
										<font color="blue" style="display:none"  id="hjbutton" ><u>选择获奖信息</u></font>
									</a>
								</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="20%">评奖周期</th>
							<td colspan="3">${pjzqmc}</td>
						</tr>
						<tr>
							<th>奖项</th>
							<td colspan="3">
								<table width="95%">
									<thead>
										<tr>
											<td>奖项名称</td>
											<td>奖项类别</td>
											<td>金额</td>
										</tr>
									</thead>
									<tbody id="sqjx">
										
									</tbody>
								</table>
							</td>
						</tr>
						<tr id="hjtr" style="display:none">
								<th>获奖信息</th>
								<td colspan="3">
									<table width="100%">
									<thead>
										<tr>
											<td width="30%">获奖名称</td>
											<td width="20%">获奖时间</td>
											<td width="50%">颁奖单位</td>
										</tr>
									</thead>
									<tbody id="hjxx">
										
									</tbody>
								</table>
								</td>
							</tr>
						<tr>
							<th>
								附件信息
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
											});
										</script>
							</td>
						</tr>
						<tr>
							<th>申请理由填写说明</th>
							<td colspan="3"><p id="txsmDiv"></p></td>
						</tr>
						<tr>
							<logic:notEqual value="12056" name="xxdm">
							<th>
								<span class="red">*</span>申请理由</br>(思想政治、社会实践、综合素质得分、学习排名等)</br>
								<font color="red" >(限180-200字)</font>
							</th>
							<td colspan="3" style="word-break:break-all;">
								 <!-- <div id="txsmDiv" style="color:red"></div> -->
								 <%--重庆工商大学单项奖学金个性化--%>
									<div  id="dxjxjTr" style="display: none;margin-bottom:5px; ">
										<span  style="color:red">请申请单项奖学金的同学在申请理由处按规则填写表格的内容</span>
										<input type="hidden" name="xmmc_11799" id="xmmc_11799" value=""/>
										<button type="button"  onclick="showSqly();" id="buttonClose">
											填写规则
										</button>
									</div>
								<html:textarea property="sqly" styleId="sqly" style="width:100%;" rows="5" onblur="checkLenBtw(this,180,200);"></html:textarea>
							</td>
							</logic:notEqual>
							<logic:equal value="12056" name="xxdm">
							<th>
								<span class="red">*</span>申请理由</br>(思想政治、社会实践、综合素质得分、学习排名等)</br><font color="red">(限150-250字)</font>
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
				<div style="height: 50px"></div>
				<div>
					<table  width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
						<tfoot>
							<tr>
								<td colspan="4">
									<div class="bz">"<span class="red">*</span>"为必填项</div>
									<div class="btn">
										<button type="button"  onclick="saveJxsq('save');">
											保存草稿
										</button>
									
										<button type="button"  onclick="saveJxsq('submit');">
											提交申请
										</button>
										
										<button type="button"  onclick="iFClose();">
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

