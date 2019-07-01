<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<link rel="stylesheet" type="text/css" href="js/jquery/themes/default/easyui.css" />
	    <link rel="stylesheet" type="text/css" href="js/jquery/themes/icon.css" />
	    <script language="javascript" src="js/check.js"></script>
	    <script language="javascript" src="js/pjpy/szgyyq/pjpy_szgyyq.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" defer="defer">
		
		//查询结果集
		function searchRs(){
			var url = "rcsw_qjgl.do?method=getQjlcList";
	
			$("divWaiting").style.display="none";
			$("divDisable").style.display="none";
			
			var lxmc = $("lxmc").value;
			if(lxmc != ""){
				lxmc = escape(lxmc);
			}else{
				lxmc = " ";
			}
			
			var kfxg = " ";
			
			
			var otherValue = [
				lxmc,
				kfxg
			];
			
			searchRsByAjax(url,otherValue);
		}
		
		//显示请假管理详细
		function showQjlcDetail(type){
		
			var id="";
			var flag = false;
			
			if(type == "edit"){
				var num = jQuery("input[name=checkVal]:checked").length;
				
				if(num == 0){
					alertError("&nbsp;&nbsp;请勾选您需要维护记录");
					flag = false;
				}else if(num > 1){
					alertError("不能勾选多条记录，请勾选一条您需要维护的记录");
					flag = false;
				}else{
					id=jQuery("input[name=checkVal]:checked")[0].value;
					flag = true;
				}				
			}else{
				flag = true;
			}

			if(flag){
			
				var url = "/xgxt/rcsw_qjgl.do?method=qjlcDetail";
					url+= "&id="+id;
					showDialog('', 600, 380, url);
				//showTopWin(url,'600','480');	
			}	
		}

		//删除请假流程
		function delQjlc(){
		
				var flag = true;
				
				var num = jQuery("input[name=checkVal]:checked").length;
				
				if(num == 0){
					alertError("请勾选您需要删除的记录");
					
				}else{	
					confirmInfo('确认要删除所选的流程吗？',function(t){
						if(t=="ok"){
							$("divWaiting").style.display="";
							$("divDisable").style.display="";
							var id = new Array();
							var n=0;
							
							for(var i=0;i<num;i++){
								var obj = jQuery("input[name=checkVal]:checked")[i];
								id[n] = obj.value;
								n++;
							}
								
							var url="rcsw_qjgl.do?method=delQjlc";
							
							//参数
						 	var parameter = {
								"id":id.join("!!@@!!")
							};
							
							jQuery.post(url,parameter,function(result){
								$("divWaiting").style.display="none";
								$("divDisable").style.display="none";
								alertInfo(result,function(){searchRs();});
							});	
						}
					});
				}
			
		}
		</script>
	</head>
	<body onload="searchRs()" >
	
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv()">使用帮助</a>
			</p>
		</div>
		<!-- 标题 end-->
		
		<!-- 提示信息 end-->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>提示：</span>
			</h3>
			<p>
				<span>
				1.本模块维护请假流程，如果没有维护流程的话，<font color="blue">不能</font>被学生申请。</br>
				2.如果某个请假还存在未审核完毕的记录，则不可删除或者修改。
				</span>
			</p>
			<a class="close" title="隐藏" onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- 提示信息 end-->
		
		<html:form action="/rcsw_qjgl" method="post">

			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- 多功能操作区 -->
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
							<li>
								<a href="#" onclick="showQjlcDetail('add');return false;" class="btn_zj">
									增加
								</a>
							</li>
							<li>
								<a href="#" onclick="showQjlcDetail('edit');return false;" class="btn_xg">
									修改
								</a>
							</li>
							<li>
								<a href="#" onclick="delQjlc();return false;" class="btn_sc">
									删除
								</a>
							</li>
						</logic:equal>
					</ul>
				</div>
				<!-- 按钮 end-->
				
				<div class="searchtab">
					<table width="100%" border="0">
						<tfoot>
		        			<tr>
		          				<td colspan="6">
		            				<div class="btn">
				              		<button type="button" class="btn_cx" id="search_go" 
					              		onclick="searchRs();return false;">
					              			查 询
					              		</button>
					             		<button type="button" class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
					              			重 置
					             		</button>
				            		</div>
		          				</td>
		       				</tr>
		     			</tfoot>
		     			<tbody>
			              	<tr>
			              		<th width="10%">请假名称</th>
			              		<td colspan="5">
			              			<html:text property="lxmc" onkeypress="if(pressEnter(event)){searchRs();return false;}" style="width:150px" styleId="lxmc"/>
								</td>
								
								
			              	</tr>	      		
						</tbody>
					</table>
				</div>
		              	
				<!-- 过滤条件 -->
				<div id="div_superSh" style="display:none">
					<%@ include file="/comm/search/superSearchArea.jsp"%>
				</div>
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
					<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=rcswQjglForm"></jsp:include>
					 <script type="text/javascript">
				     $('choose').className="hide";
				     </script>
				</div>
				<!--分页显示-->
			</div>
			<!-- 内容显示区开始 end-->	
			
			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>