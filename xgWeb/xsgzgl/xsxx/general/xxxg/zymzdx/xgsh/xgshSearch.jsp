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
		<script type='text/javascript' src='dwr/interface/exportData.js'></script>
		<script type='text/javascript' src="js/xgutil.js"></script>
		
		
		<script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" defer="defer">
		
		//初始化
		function onShow(){ 
			//searchRs();
			var num = jQuery("input[name=radio_gwid]").length;
			
			if(num == 1){
				jQuery("#btn_qh").attr("style","display:none");
				searchRs();
			} else if (jQuery("#spgw").val() != ""){
				jQuery("#hidden_gwid").val(jQuery("#spgw").val());
				searchRs();
			} else {
				showSpgwDiv();
			}
		}
		
		//显示选择审批岗位Div
		function showSpgwDiv(){
			var gwid = jQuery("#hidden_gwid").val();
			jQuery("input[name=radio_gwid]").each(function(){
				if(jQuery(this).val() == gwid){
					jQuery(this).attr("checked",true);
				}
			});
			
			tipsWindown(" ","id:div_spgw","300","170","true","","true","id");
		}
		
		//提交審批崗位
		function submitSpgw(){
		
			var spgw = jQuery("input[name=radio_gwid]:checked").eq(0).val();
			
			jQuery("#hidden_gwid").val(spgw);
			
			searchRs();
			
			closeWindown();
		}
		
		//查询结果集
		function searchRs(){
			
			jQuery.ajaxSetup({async:false});
			
			var url = "xsxx_xxxg_xgsh.do?method=searchXgsh";
			var ie = "ie";
			var splc = jQuery("#hidden_splc").val();
			var gwid = jQuery("#hidden_gwid").val();
			
			var otherValue = [ie,stylePath,splc,gwid];

			$("divWaiting").style.display="";
			$("divDisable").style.display="";
			
			searchRsByAjax(url,otherValue);

			$("divWaiting").style.display="none";
			$("divDisable").style.display="none";
			
			jQuery.ajaxSetup({async:true});
		}
		
		//展示审核详细页面
		function showXgshDetail(){
			var num = jQuery("input[name=primarykey_checkVal]:checked").length;
			
			if(num == 0){
				alertError("请勾选您希望审核的记录");
				return false;
			}else if(num == 1){
			
				var sqid = jQuery("input[name=primarykey_checkVal]:checked")[0].value;
				var gwid = jQuery("#hidden_gwid").val();
				
				var url = "general_xsxx.do?method=xgshDetail";
					url+= "&sqid="+sqid;
					url+= "&gwid="+gwid;

				//showTopWin(url,'800','620');
				showDialog("信息修改审核",800,550,url);
			}else{
				tipsWindown(" ","id:div_plsh","400","250","true","","true","id");
			}
		}
		
		//保存審核狀態
		function saveShzt(shzt){
		
			var shyj = jQuery("#textarea_shyj").val();
			var gwid = jQuery("#hidden_gwid").val();
			
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
			msg += "所勾选学生的信息修改申请";
				
			confirmInfo(msg,function(tag){
				if(tag=="ok"){
					var url = "xsxx_xxxg_xgsh.do?method=saveShzt";

					var gwid = jQuery("#hidden_gwid").val();
					var pkValue=new Array();	
					var i=0;
					
					jQuery("input[name=primarykey_checkVal]:checked").each(function(){
						pkValue[i]=jQuery(this).val();
						i++;
					});
			
					var parameter={
						"array_pkValue":pkValue.join("!!array!!"),
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
							searchRs();
							alertInfo(result);
							closeWindown();
						}
					);
			
					jQuery.ajaxSetup({async:true});
				}
			});
			
			
		}
		
		jQuery(function(){
			onShow();
		})
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
		
		<html:form action="/general_xsxx" method="post">
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="splc" id="hidden_splc" value="${splc }"/>
			<input type="hidden" name="gwid" id="hidden_gwid" value="${gwid }"/>
			<input type="hidden" id="spgw" value="${xsxxGeneralForm.spgw }"/>
					
			<!-- 多功能操作区 -->
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<!-- 读写权 begin-->
						<logic:equal name="writeAble" value="yes">
							<li>
								<a href="#" class="btn_shtg" onclick="showXgshDetail();return false;">
									审核
								</a>
							</li>
							<li>
								<a href="#" class="btn_sx" id="btn_qh" onclick="showSpgwDiv();return false;">
									切换审核岗位
								</a>
							</li>
						</logic:equal>
						<!-- 读写权 end-->
					</ul>
				</div>
				<!-- 按钮 end-->
				
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
			
			<!-- 内容显示区开始 -->
			<div class="main_box">
				<div class="mid_box">
					<div class="title">
						<p>
							<!-- 查询得到的数据量显示区域 -->
						</p>
					</div>
				</div>
				<!-- From内容 -->
				<div id="div_rs" style="" class="con_overlfow">
				</div>
				
				<!--分页显示-->
				<div style="clear:both;">
					<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=xsxxGeneralForm"></jsp:include>
					 <script type="text/javascript">
				     //$('choose').className="hide";
				     </script>
				</div>
				<!--分页显示-->
			</div>
			<!-- 内容显示区开始 end-->
			
			<!-- 審核崗位選擇Div begin-->
			<div id="div_spgw" style="display:none">
				<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th>
									<span>审核岗位选择</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<logic:iterate name="spgwList" id="spgw">
								<tr>
									<td>
										<input type="radio" name="radio_gwid" value="${spgw.spgw }"/>${spgw.gwmc }
									</td>
								</tr>
							</logic:iterate>
						</tbody>
						<tfoot>
							<tr>
								<td>
									<div class="btn">
										<button type="button" onclick="submitSpgw();">确 定</button>
<!--										<button type="button" onclick="closeWindown();">关 闭</button>-->
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
			<!-- 審核崗位選擇Div end-->
			
			<!-- 批量審核Div begin-->
			<div id="div_plsh" style="display:none">
				<div class="open_win01">
					<table align="center" class="formlist" width="99%">
						<thead>
							<tr>
								<th colspan="2">
									<span>学生信息修改批量审核</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th width="25%">
									审核意见
									<br/>
									<font color="blue">(限500字)</font>
								</th>
								<td>
									<textarea rows="5" id="textarea_shyj" style="word-break:break-all;width:90%"></textarea>
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="2">
									<div class="btn">
										<button type="button" onclick="saveShzt('tg');">通 过</button>
										<button type="button" onclick="saveShzt('th');">退 回</button>
										<button type="button" onclick="closeWindown();">关 闭</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
			<!-- 批量審核Div end-->
			
			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>