<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type='text/javascript' src="js/uicomm.js"></script>
		<script type='text/javascript' src="js/String.js"></script>
		<script type='text/javascript' src="js/xgutil.js"></script>
		
		
		
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/jquery.dragsort-0.4.min.js"></script>
		<style>
		.demo_college li {position:relative;}
		.choose_yx{display: block;height: 13px;position: absolute;right: -3px;top: 2px;width: 13px;cursor:pointer;background: url("<%=stylePath %>/images/blue/ico_90.gif") no-repeat 0 0 !important;}
		.choose_wx{display: block;height: 13px;position: absolute;right: -3px;top: 2px;width: 13px;cursor:pointer;background: url("<%=stylePath %>/images/blue/ico_91.gif") no-repeat 0 0 !important;}
		</style>
		<script language="javascript">

		function xgZdmc(obj){
			var zd = jQuery("#"+obj+"").val();
			var gnlj = jQuery("#gnlj").val();
			//tipsWindown("字段修改","id:tmpdiv1","320","150","true","","true","id");
			showDialog('', 330, 170, 'xtwh_cxjgpz.do?method=xgZdmc&zd='+zd+"&gnlj="+gnlj);
			//jQuery.ajaxSetup({async:false});	
			//jQuery.post("xtwh_cxjgpz.do?method=xgZdmc",
							//{"zd":zd,"gnlj":gnlj},
							//function(result){
							//	var json=eval(result);
							//		jQuery("#zd").val(json[0].zd);
							//		jQuery("#zdmc").val(json[0].zdmc);
							//		jQuery("#xgzdmc").val(json[0].xgzdmc);
						//	}
					 // );
			//jQuery.ajaxSetup({async:true});	
			}

		//保存 
		function bcxgZdmc(){
			var xgzdmc = jQuery("#xgzdmc").val();
			refreshForm('xtwh_cxjgpz.do?method=bcxgZdmc');
		}

		function cxjgManage(){
			allNotEmpThenGo("xtwh_cxjgpz.do?method=cxjgpzManage");
			}
		
		function saveOrder() {
			jQuery("#list1").find(":input").attr("name","unselectCol");
			var unspan = jQuery("#list1").find(".choose_yx");
			unspan.parent().append("<span class='choose_wx' onclick='select(this)'></span>");
			unspan.remove();
			
			var span = jQuery("#list2").find(".choose_wx");
			span.parent().append("<span class='choose_yx' onclick='unselect(this)'></span>");
			span.remove();

			var cxjg = jQuery("#list2").find('.12');
			var zds = "";
			for (var i = 0;i<cxjg.length;i++){
				zds+=cxjg[i].value+"!@!";
				}
			jQuery.post('xtwh_cxjgpz.do?method=cxjgPz',
					{"zds":zds},
					function(data){
						cxjgManage();
				},'json')
			
		};
		
		jQuery(function(){
			jQuery("#list1, #list2").dragsort({
				dragSelector : "label",
				dragBetween : true,
				dragEnd : saveOrder,
				placeHolderTemplate : "<li><label class='college_li college_checkbox' style='border:#155FBE 1px dotted;background:#CBE4F8;height:20px;line-height:20px!important;*height:20px;width:60px;'></label></li>"
			});
		})
			
			//点击加号
			function select(obj){
				var li = jQuery(obj).parent();
				jQuery(obj).parent().appendTo(jQuery("#list2"));
				jQuery(obj).remove();
				li.append("<span class='choose_yx' onclick='unselect(this)'></span>");
				saveOrder();
			}
			
			//点击减号
			function unselect(obj){
				var li = jQuery(obj).parent();
				jQuery(obj).parent().appendTo(jQuery("#list1"));
				jQuery(obj).remove();
				li.append("<span class='choose_wx' onclick='select(this)'></span>");
				saveOrder();
			}

		</script>
	</head>
	<body >

		<html:form styleId="page_form" action="/xtwh_cxjgpz" method="post">
		 <input type="hidden" name="message" id="message" value="${message }">	
		  <input type="hidden" name="gnlj" id="gnlj" value="${gnlj}">
		    <input type="hidden" name="zd" id="zd" value="">
		  <input type="hidden" id="search_go" onclick="cxjgManage()"></input>
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
				<p class="help">
					<a href="#" onclick="return false;" onmousedown ="showHelpDiv()">使用帮助</a>
				</p>
			</div>
			<!-- 提示信息 end-->
			<div class="prompt" id="div_help" style="display: none">
				<h3>
					<span>提示：</span>
				</h3>
				<p>
					<span>
						此处选择显示的字段，是对“在校生信息”与“非在校生信息”查询数据结果集表头的设置
					</span>
				</p>
				<a class="close" title="隐藏" onclick="this.parentNode.style.display='none';"></a>
			</div>
			<!-- 提示信息 end-->
			<div class="toolbox" style="overflow: auto">

					 <table width="100%" border="0" class="formlist">
						<tbody>
						<thead>
							<th>
							可选字段
							</th>
							<th>
							已选字段<font color="red">（拖拽可以排序）</font>
							</th>
						</thead>
						<tr>
							<td width="150px" style='vertical-align: top'>
							
									<div class="demo_college" style='vertical-align: top'>
									<ul id="list1" class="ul1_class">
										<logic:iterate id="wpzzd" name="wpzlist" indexId="index">	
											<li>
												<label  class="college_li college_checkbox" style="height:20px;line-height:20px!important;font-size:12px!important;*height:20px;width:60px;">
													<input class = "12" type="hidden" name="cxjg_ypzzd" value="${wpzzd.zd}"/>${wpzzd.zdmc}
												</label>
												<span class="choose_wx" onclick="select(this);"></span>
											</li>
										</logic:iterate>
									</ul>
									</div>
								
							</td>
							<td width="100%" style='vertical-align: top'>
								 <table width="100%" border="0" class="formlist">
								<tbody>
								<tr>
								<td>
									<div class="demo_college" style="width:100%;height:150px;overflow-x:hidden;overflow-y:auto;vertical-align: top">
										<ul id="list2" class="ul2_class">
											<logic:iterate id="ypzzd" name="ypzlist" indexId="index">	
												<li >
													<label  class="college_li college_checkbox" style="height:20px;line-height:20px!important;font-size:12px!important;*height:20px;width:60px;">
														<input class = "12" type="hidden" id="zd_${index}" name="cxjg_ypzzd" value="${ypzzd.zd}"/>
														${ypzzd.zdmc}
													</label>
													<span class="choose_yx" onclick="unselect(this);"></span>
												</li>
											</logic:iterate>
										</ul>
										</div>
										</td>
									</tr>
									<tr>
									<td>
									<h3 class="datetitle_01">
													<span> 查询结果
														</span>
												</h3>
												<div class="formbox" id="div_rs" style="height:500px;overflow-x:auto;overflow-y:hidden;">
												<table summary="" class="dateline" align="" width="100%">
												<logic:notEmpty name="ypzlist">
												<thead>
														<tr align="center" style="cursor:hand">
															<logic:iterate id="ypzzd" name="ypzlist" indexId="index" >
																<td  >
																	<input type="hidden" id="zd_${index}" name="zd" value="${ypzzd.zd}"/>
																	<div class="tab_szcd">
																	<a class="ico_sz" onclick="xgZdmc('zd_${index}');return false;" href="#" title="编辑表头">
																	${ypzzd.zdmc}</a>
																	</div>
																</td>
																
															</logic:iterate>
														</tr>
													</thead>
													</logic:notEmpty>
													<logic:notEmpty name="xsxxlist">
													<logic:iterate id="xsxx" name="xsxxlist" indexId="index" >
														<tr align="center" style="cursor:hand">
														<logic:iterate id="v" name="xsxx" >
															<td>
															 ${v}
															</td>
														</logic:iterate>		
														</tr>
													</logic:iterate>
													</logic:notEmpty>
												</table>
										</div>
										
										
										<div id="tmpdiv1" style="display: none">
											<div class="open_win01">
												<table align="center" class="formlist">
													<thead>
														<tr>
															<th colspan="2">
																<span>字段信息</span>
															</th>
														</tr>
													</thead>
													<tbody>
													<tr id="">
															<th align="right" width="25%">
																字段名称：
															</th>
															<td align="left"  >
																<input name="zdmc" id="zdmc" disabled="disabled"/>
															</td>
														</tr>
														<tr id="">
															<th align="right" width="25%">
																字段别名：
															</th>
															<td align="left"  >
																<input name="xgzdmc" id="xgzdmc"/>
															</td>
														</tr>
													 </tbody>
													<tfoot>
														<tr>
															<td colspan="2">
																<div class="btn">
																	<button type="button" onclick="bcxgZdmc()">
																		保 存
																	</button>
																	
																	<button type="button" onclick="closeWindown();">
																		关 闭
																	</button>
																</div>
															</td>
														</tr>
													</tfoot>
												</table>
											</div>
										</div>
										</td>
									</tr>	
								</tbody>
								</table>
							</td>
						</tr>				
					</tbody>
			</table>
		</html:form>
		<%@ include file="/comm/other/tsxx.jsp"%>
	</body>
</html>
