<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script type='text/javascript' src="js/uicomm.js"></script>
		<script type='text/javascript' src="js/String.js"></script>
		
	    <script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript" src="js/commit.js"></script>
		<script language="javascript" defer="defer">
		//初始化
		function onShow(){ 
			searchRs();
		}

		//查看
		function viewCfjc(){
			var objs = jQuery("input[type=checkbox][name=primarykey_checkVal]:checked");
			var cfId="";
			if(objs.length!=1){
				alertError("请选择一条记录！");
				return false;
			}else{
				cfId+=objs[0].value;
			}
			var url="general_wjcf_cfjc_ajax.do?method=ckCfjc&cfId="+cfId;
			//showTopWin(url,'780','660');
			showDialog("", 780, 525, url);
		}


		//执行查询操作
		function searchRs(){
			jQuery.ajaxSetup({async:false});
			var v4Path = stylePath;//v4样式路径
			var url = "general_wjcf_cfjc_ajax.do?method=searchWjcfResult";
			var ie = "ie";
			
			var otherValue = [ie,v4Path];

			$("divWaiting").style.display="";
			$("divDisable").style.display="";
			
			searchRsByAjax(url,otherValue);
			setTimeout("setDivHeight()","1000")
			
			jQuery.ajaxSetup({async:true});
		}

		//申请解除
		function sqJc(cfId){
<%--			tipsWindown("处分解除申请","id:tmpdiv1","410","280","true","","true","id");--%>
<%--			jQuery("#cfId").val(cfId);--%>
<%--			jQuery('#sqly').val('');--%>
<%--			jQuery('#lx').val('zj');--%>
			var url="general_wjcf_cfjc_ajax.do?method=zjJcSq&cfId="+cfId;
			showDialog("", 400, 220, url);
			}

		//保存 
		function save(){
			var lx = $("lx").value;
			var sqly = $("sqly").value;
			 if(""==sqly){
					alertError("请将带*的项目填写完整！");
					return false;
					}
				var v4Path = stylePath;//v4样式路径
				var ie = "ie";	
			refreshForm('general_wjcf_cfjc_ajax.do?method=saveJcSq&lx='+lx);
		}

		//修改解除
		function xgJc(cfId){
<%--			jQuery("#cfId").val(cfId);--%>
<%--			jQuery.ajaxSetup({async:false});	--%>
<%--			jQuery('#lx').val('xg');			--%>
<%--			tipsWindown("处分解除申请","id:tmpdiv1","410","280","true","","true","id");--%>
<%--			jQuery.post("general_wjcf_cfjc_ajax.do?method=xgJcSq",--%>
<%--							{cfId:cfId},--%>
<%--							function(result){--%>
<%--								var json=eval(result);--%>
<%--									jQuery("#sqly").val(json[0].sqly);--%>
<%--							}--%>
<%--						);--%>
<%--			--%>
<%--			jQuery.ajaxSetup({async:true});	--%>
			
			var url="general_wjcf_cfjc_ajax.do?method=xgJcSq&cfId="+cfId;
			showDialog("", 400, 220, url);
			}

		//撤销解除
		function cxJc(cfId){
			confirmInfo("此操作将会删除申请解除的记录，是否确定？",function(tag){
				if(tag=="ok"){
					jQuery.ajaxSetup({async:false});
					var url = "general_wjcf_cfjc_ajax.do?method=cxJcSq";
					//参数
				 	var parameter = {
				 			"cfId":cfId
					};
					
				 	$("divWaiting").style.display="";
					$("divDisable").style.display="";
					
					jQuery.post(url,
						parameter,
						function(result){
							alertInfo(result);
							searchRs();
						}
					);
					jQuery.ajaxSetup({async:true});
				}
			});
			
			}
		
		jQuery(function(){
			onShow();
		})
		
		function cfjcwhExportConfig() {
		//DCCLBH导出功能编号,执行导出函数 
		customExport("general_wjcf_cfjc_ajax.do", cfjcwhExportData);
		}
			
		
			
		// 导出方法
		function cfjcwhExportData() {
			setSearchTj();//设置高级查询条件
			var url = "general_wjcf_cfjc_ajax.do?method=cfjcwhExportData&dcclbh=" + "general_wjcf_cfjc_ajax.do";//dcclbh,导出功能编号
			url = addSuperSearchParams(url);//设置高级查询参数
			jQuery("form").eq(0).attr("action", url);
			jQuery("form").eq(0).submit();
		}
		
		</script>
	</head>
	<body  ondrag="return false">
	
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<!-- 标题 end-->
		
		<html:form action="/general_wjcf_cfjc_ajax" method="post">
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="message" id="message" value="${message }">	
			<html:hidden property="cfId" styleId="cfId"/>	
			<!-- 多功能操作区 -->
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<li>
							<a href="#" onclick="viewCfjc();return false;" class="btn_ck">
								查看
							</a>
						</li>
						<li><a href="#" class="btn_dc" onclick="cfjcwhExportConfig();return false;">导出</a></li>
						<%--<li><a href="#" class="btn_dc" onclick="configureExportData();return false;">导出数据</a></li>

					--%></ul>
				</div>
				<!-- 按钮 end-->
				
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
			
			<!-- 内容显示区开始 -->
			<div class="main_box">
			<h3 class="datetitle_01">
					<span> 查询结果</span>
				</h3>
				<div id="div_rs"
					style="width:100%;height:380px;overflow-x:auto;overflow-y:hidden;">
				</div>

			<!--弹出层 begin-->
		
				<input type="hidden" name="lx" id="lx" />
				<div id="tmpdiv1" style="display: none">
				<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>处分解除申请</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr id="">
								<td align="right" width="25%">
									<font color="red">*</font>申请理由：<br/>
								<font color="red"><B>(限1000字)</B></font>
								</td>
								<td align="left" style="width:80%" >
									<html:textarea  property='sqly' styleId="sqly" style="width:250px;height: 90%; margin: 1px;"
									rows='8' onblur="checkLen(this,1000)"/>
								</td>
							</tr>
							
						</tbody>
						<tfoot>
							<tr>
								<td colspan="2">
									<div class="bz">
										"<span class="red">*</span>"为必填项
									</div>
									<div class="btn">
										<button type="button"  onclick="save();return false;">
											保 存
										</button>
										
										<button type="button"  onclick="closeWindown();">
											关 闭
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
				
				
			<!--弹出层 end-->

				<!--分页显示-->
				<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=wjcfGeneralForm"></jsp:include>
				<!--分页显示-->
			</div>
			<!-- 内容显示区 end-->
			
			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>