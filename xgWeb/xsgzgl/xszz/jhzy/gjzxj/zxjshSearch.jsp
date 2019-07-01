<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	    <script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" defer="defer">
		
		//初始化
		function onShow(){ 
			searchRs();
		}
		
		//查询结果集
		function searchRs(){
			
			jQuery.ajaxSetup({async:false});
			
			var url = "jhzy_gjzxj.do?method=searchZxjsh";
			var ie = "ie";
			
			var otherValue = [ie];

			$("divWaiting").style.display="";
			$("divDisable").style.display="";
			
			searchRsByAjax(url,otherValue);

			$("divWaiting").style.display="none";
			$("divDisable").style.display="none";
			
			jQuery.ajaxSetup({async:true});
		}
		
		//检测勾选数
		function checkLineNum(){
			var len=jQuery("[name=primarykey_checkVal]:checked").length;
	
			if(len==0){	
				alertError("请<font color='blue'>勾选</font>您希望审核的记录！");	
				return false;
			}else if(len==1){	
				var pkValue=jQuery("[name=primarykey_checkVal]:checked").eq(0).val();
				var url="jhzy_gjzxj.do?method=zxjshUpdate";
					url+="&pkValue="+pkValue;
					showTopWin(url,800,600);
			}else{	
				tipsWindown("系统提示","id:div_plsh","500","300","true","","true","id");
			}
		}
		
		//保存国家助学金审核
		function saveGjzxjSh(shzt){
			var message = "";
			if(shzt == "tg"){
				message = "您确认审核<font color='blue'>通过</font>所勾选学生的国家助学金申请吗？";
			}else if(shzt == "btg"){
				message = "您确认审核<font color='blue'>不通过</font>所勾选学生的国家助学金申请吗？";
			}
			
			confirmInfo(message,function(tag){
				if(tag=="ok"){
					
					//路径
					var url = "jhzy_gjzxj.do?method=saveGjzxjSh";
					
					var tjdc = jQuery("[name=tjdc]:checked").eq(0).val();
			
					var pkValue=new Array();
					var i=0;
					
					jQuery("input[name=primarykey_checkVal]:checked").each(function(){
						pkValue[i]=escape(jQuery(this).val());
						i++;
					});
					
					//参数
				 	var parameter = {
						"array_pkValue":pkValue.join("!!array!!"),
						"str_shzt":escape(shzt),
						"str_shyj":escape(jQuery("#shyj").val()),
						"str_tjdc":escape(tjdc)
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
	<body  >
	
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;" 
					onmouseover ="showHelpDiv()"
					onmouseout="showHelpDiv()"
				>
				使用帮助</a>
			</p>
		</div>
		<!-- 标题 end-->
		
		<html:form action="/general_pjpy" method="post">
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<!-- 多功能操作区 -->
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<!-- 读写权 -->
						<logic:equal name="writeAble" value="yes">
							<li>
								<a href="#" onclick="checkLineNum();return false;" id="btn_shtg" class="btn_shtg">
									审核
								</a>
							</li>
						</logic:equal>
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
				<div id="div_rs" style="height:380px;overflow-x:auto;overflow-y:auto;">
				</div>
				
				<!--分页显示-->
				<div style="clear:both;">
					<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=jhzyXszzGjzxjForm"></jsp:include>
					 <script type="text/javascript">
				     //$('choose').className="hide";
				     </script>
				</div>
				<!--分页显示-->
			</div>
			<!-- 内容显示区开始 end-->
			
			<!-- 批量审核弹出层 -->
			<div id="div_plsh" style="display:none">
				<div class="open_win01">
					<table width="100%" border="0" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>批量审核</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th width="25%">
									<font color="red">*</font>推荐档次
								</th>
								<td colspan="3">
									<input type="radio" name="tjdc" value="一等" checked="checked"/>一等（￥4000）
									<input type="radio" name="tjdc" value="二等"/>二等（￥2500）				
								</td>
							</tr>
							<tr>
								<th width="25%">
									审核意见
									<br/><font color="red">(限制字数100)</font>
								</th>
								<td>
									<textarea rows="3" id="shyj" cols="" 
										onblur="chLeng(this,100);"
										style="word-break:break-all;width:99%" ></textarea>
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="2">
									<div class="btn">
										<button type="button" onclick="saveGjzxjSh('tg')">通 过</button>
										<button type="button" onclick="saveGjzxjSh('btg')">不通过</button>
										<button type="button" onclick="closeWindown();return false;">关闭</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
			<!-- 流程跟踪弹出层 end-->
			
			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>