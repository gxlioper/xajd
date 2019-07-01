<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<%--<script type="text/javascript" src="xsgzgl/xpjpy/wdpj/sqsh/js/bdpz.js"></script>--%>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
			function saveJxsb(type){
				if (jQuery("#sqly").val() == ""){
						showAlert("请将必填项填写完整！");
						return false;
				}
				if (jQuery("#sqly").val().length > 500){
					showAlert("申请理由最大字数为500，现已经超过，请确认！！");
					return false;
				}
				var url = "xpj_sqsh.do?method=saveUpdateSqb&type="+type;
				ajaxSubFormWithFun("sqshForm",url,function(data){
					showAlert(data["message"],{},{"clkFun":function(){
						if (parent.window){
							refershParent();
						}
					}});
				});
			}
			function showSqly(){
				var url = "xpj_sqsh.do?method=sqlyPdf";
                 window.open(url);

			}
			
			jQuery(function(){
				if("11799" == jQuery("#xxdm").val()){
					 if(
						"单项奖学金" == jQuery("#xmmc1").text().trim() || 
						"单项奖学金（科技创新）" == jQuery("#xmmc1").text().trim() || 
						"单项奖学金（体育运动）" == jQuery("#xmmc1").text().trim() || 
						"单项奖学金（文艺活动）" == jQuery("#xmmc1").text().trim() || 
						"单项奖学金（学习优秀1）" == jQuery("#xmmc1").text().trim() || 
						"单项奖学金（学习优秀3）" == jQuery("#xmmc1").text().trim() || 
						"单项奖学金（学习进步）" == jQuery("#xmmc1").text().trim() ){
						jQuery("#dxjxjTr").show();
					}
				};

                //ie9及以下无此事件
                jQuery("#sqly").bind("input",function(){
                    chCount($(this),180,200);
                });
			});
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
			<input type="hidden" id="xmdm" name="xmdm" value="${xmwhModel.xmdm }"/>
			<html:hidden property="xh" styleId="xh" />
			<html:hidden property="sqid" styleId="sqid" />
			<input type="hidden" name="xxdm" id="xxdm" value="${xxdm }"/>
			<div style='tab;width:100%;height:415px;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>基本信息</span>
							</th>
						</tr>
					</thead>
						<%@ include file="/xsgzgl/xpjpy/wdpj/bdpz/viewStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>申请奖项</span>&nbsp;&nbsp;	
								<logic:equal value="1" name="hjjgxskg">
								<a href="javascript:void(0);" onclick="addHjxx()" >
										<font color="blue"  id="hjbutton" ><u>选择获奖信息</u></font>	
									</a>
								</logic:equal>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>
								评奖周期
							</th>
							<td colspan="3">
								<bean:write property="xn" name="xpjSqshModel"/>&nbsp;<bean:write property="xqmc" name="xpjSqshModel"/>
							</td>
						</tr>
						<tr>
							<th>
								项目名称
							</th>
							<td id="xmmc1">
								${xmwhModel.xmmc }
							</td>
							<th>
								项目金额
							</th>
							<td>
								${xmwhModel.xmje }
							</td>
						</tr>
						<logic:equal value="	10279" name="xxdm">
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
						<logic:equal value="10355" name="xxdm">
							<logic:equal value="1" name="hjjgxskg">
								<tr id="hjtr">
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
										<logic:iterate id="i" name="hjjgList">
											<tr>
												<td>${i.hjmc}</td>
												<td>${i.hjsj}</td>
												<td>${i.fjdw}</td>
											</tr>									
										</logic:iterate>
									</tbody>
								</table>
								</td>
							</tr>
							</logic:equal>
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
						<tr>
							<th><logic:equal value="70002" name="xxdm">
									主要事迹
								</logic:equal>
								<logic:notEqual value="70002" name="xxdm">
									申请理由
								</logic:notEqual>填写说明</th>
							<td colspan="3"><p>${xmwhModel.kgbz }</p></td>
						</tr>
						<tr>
							<th><span class="red">*</span>申请理由</th>
							<td colspan="3">
								<html:select property="sqlyyy" styleId="sqlyyy">
									<html:option value="A" >家庭遭受自然灾害</html:option>
									<html:option value="B" >家庭遭受突发意外事件</html:option>
									<html:option value="C" >家庭成员因残疾</html:option>
									<html:option value="D" >年迈而劳动能力弱情况</html:option>
									<html:option value="E" >家庭适龄就学子女较多</html:option>
									<html:option value="F" >家庭成员失业</html:option>
									<html:option value="G" >家庭欠债</html:option>
									<html:option value="H" >其他</html:option>
									<html:option value="I" >建档立卡家庭</html:option>
									<html:option value="J">低保</html:option>
								</html:select>
							</td>
						</tr>
						<tr id="sqlyTr">
							<logic:notEqual value="12056" name="xxdm">
							<th>
								<span class="red">*</span><logic:equal value="70002" name="xxdm">
									主要事迹
								</logic:equal>
								<logic:notEqual value="70002" name="xxdm">
									具体理由
								</logic:notEqual>
								<br />(限100字)
							</th>
							<td colspan="3" style="word-break:break-all;">
							<%-- <div id="txsmDiv" style="color:red">${xmwhModel.kgbz }</div> --%>
								<%--重庆工商大学单项奖学金个性化--%>
								<div  id="dxjxjTr" style="display: none;margin-bottom:5px; ">
									<span  style="color:red">请申请单项奖学金的同学在申请理由处按规则填写表格的内容</span>
									<button type="button"  onclick="showSqly();" id="buttonClose">
										填写规则
									</button>
								</div>
								<html:textarea property="sqly" styleId="sqly" style="width:95%;" rows="5" onblur="checkLenBtw(this,0,100);"></html:textarea>
							</td>
							</logic:notEqual>
							<logic:equal value="12056" name="xxdm">
							<th>
								<span class="red">*</span>申请理由
								<br />(限150-250字)
							</th>
							<td colspan="3" style="word-break:break-all;">
								<%-- <div id="txsmDiv" style="color:red">${xmwhModel.kgbz }</div> --%>
								<html:textarea property="sqly" styleId="sqly" style="width:95%;" rows="5" onblur="checkLenBtw(this,150,250);"></html:textarea>
							</td>
							</logic:equal>
						</tr>
					</tbody>
				</table>
			</div>
			<div style="height: 50px"></div>
			<div class='tab'>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<button type="button" onclick="saveJxsb('save');return false;">
										保存草稿
									</button>
									<button type="button" onclick="saveJxsb('submit');return false;">
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
		<script type="text/javascript">
		jQuery(function(){			
			var xxdm = jQuery("#xxdm").val();
			if('11527' == xxdm){
				var xmmc = jQuery("#xmmc1").text();
				if(xmmc.trim() == '湖南省2017届优秀毕业生'){															
					var html = '<tr id="djb"><th>登记表下载</th><td colspan="3"><a href="xsgzgl/xpjpy/wdpj/sqsh/sjdjb_11527.docx" target="_blank">省级登记表下载</a></td></tr>'
					jQuery("#fjxx").before(html);
					
				}
			    if(xmmc.trim() == '湖南城市学院2017届优秀毕业生'){			    				    					    	
			    	var html = '<tr id="djb"><th>登记表下载</th><td colspan="3"><a href="xsgzgl/xpjpy/wdpj/sqsh/xjdjb_11527.docx" target="_blank">校级登记表下载</a></td></tr>'
					jQuery("#fjxx").before(html);			    	
			    }
			}
		})
		</script>
	</body>
</html>

