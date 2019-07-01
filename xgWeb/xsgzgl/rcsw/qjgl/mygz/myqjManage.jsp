<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<link rel="stylesheet" type="text/css" href="js/jquery/themes/default/easyui.css" />
	    <link rel="stylesheet" type="text/css" href="js/jquery/themes/icon.css" />
	    <script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" defer="defer">
		//查询结果集
		function searchRs(){
			var url = "rcsw_qjgl.do?method=getMyqjList";
	
			$("divWaiting").style.display="none";
			$("divDisable").style.display="none";
			
			var xn = $("xn").value;
			if(xn == ""){
				xn = " ";
			}
			
			var xq = $("xq").value;
			if(xq == ""){
				xq = " ";
			}
			
			var kssj = $("kssj").value;
			var jssj = $("jssj").value;
			
			if(kssj!="" && jssj!="" && parseInt(kssj) > parseInt(jssj)){
				alertError("【申请开始时间】不能晚于【申请结束时间】，请确认");
				return false;
			}
			
			if(kssj == ""){
				kssj = " ";
			}
			
			if(jssj == ""){
				jssj = " ";
			}
			
			var otherValue = [xn,xq,kssj,jssj];
			
			searchRsByAjax(url,otherValue);
		}
		
		//显示我的请假详细
		function showMyqjDetail(type){
		
			var id="";
			var flag = false;
			
			if(type == "edit"){
				var num = jQuery("input[name=checkVal]:checked").length;
				
				if(num == 0){
					alertError("请勾选您需要维护记录");
					flag = false;
				}else if(num > 1){
					alertError("不能勾选多条记录，请勾选一位您需要维护的记录");
					flag = false;
				}else{
					id=jQuery("input[name=checkVal]:checked")[0].value;
					flag = true;
				}				
			}else{
				flag = true;
			}

			if(flag){
			
				var url = "/xgxt/rcsw_qjgl.do?method=myqjDetail";
					url+= "&id="+id;
					
					showDialog('','800','570',url);	
			}	
		}
		
		//删除我的请假
		function delMysq(){
			
			
				var num = jQuery("input[name=checkVal]:checked").length;
					
				if(num == 0){
					alertError("请勾选您希望取消的申请记录");
					flag = false;
					return false;
				}else{
					confirmInfo('确认要取消申请吗？',function(t){
						if(t=="ok"){
							var id = new Array();
							var n=0;
							
							for(var i=0;i<num;i++){
								var obj = jQuery("input[name=checkVal]:checked")[i];
								id[n] = obj.value;
								n++;
							}
							
							var url = "/xgxt/rcsw_qjgl.do?method=delMyqj";
				
							$("divWaiting").style.display="";
							$("divDisable").style.display="";
								
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
		
		function print(sqid){
			window.open("rcsw_qjgl.do?method=jgcxDetail&doType=print&sqid="+sqid);
		}

		function viewdetail(sqid){
			var url = "rcsw_qjgl_viewdetail.do?sqid="+sqid; 
			showDialog('','800','600',url);
			//showOpenWindow("rcsw_qjgl_viewdetail.do?sqid="+sqid);
		}
		
		//重置 只读input
		function resetReadOnly(){
			if($("roInput") && $("roInput").value!=""){
				var roInputArr=$("roInput").value.split("-");
				for(i=0;i<roInputArr.length;i++){
					
					$(roInputArr[i]).readonly=false;
					$(roInputArr[i]).value="";
					$(roInputArr[i]).readonly=true;
				}
			}
		}
		</script>
	</head>
	<body onload="searchRs()" >
		<input type="hidden" name="roInput" id="roInput" value="kssj-jssj"/>
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
				<span>系统提示：</span>
			</h3>
			<p>
				<span>
				1.以下展现您申请过的所有请假，根据申请时间，倒叙排列。</br>
				2.如果您想申请新的请假，请点击<font color="blue">请假申请</font>按钮。</br>
				3.如果您觉得申请不合适时，可以勾选记录，执行<font color="blue">取消申请</font>，取消这些勾选的请假申请记录。</br>
				4.如果您发现无法取消某条申请记录时，可能是该申请记录已经被<font color="blue">审核过了</font>。</br>
				5.点击申请记录的<font color="blue">打印申请表</font>，系统可以输出一份申请表格，方便您打印。
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
								<a href="#" onclick="showMyqjDetail('add');return false;" class="btn_zj">
									请假申请
								</a>
							</li>
							<li>
								<a href="#" onclick="delMysq();return false;" class="btn_sc">
									取消申请
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
					             		<button type="button" class="btn_cz" id="btn_cz" onclick="resetReadOnly();searchReset();">
					              			重 置
					             		</button>
				            		</div>
		          				</td>
		       				</tr>
		     			</tfoot>
		     			<tbody>
			              	<tr>
			              		<th>学年</th>
			              		<td>
			              			<html:select property="xn" style="width: 150px"styleId="xn">
										<html:options collection="xnList" property="xn" labelProperty="xn" />
									</html:select>
								</td>
								<th>学期</th>
								<td>
									<html:select property="xq" style="width: 150px"styleId="xq">
										<html:option value=""></html:option>
										<html:options collection="xqList" property="xqdm" labelProperty="xqmc" />
									</html:select>
								</td>
								<th>申请时间</th>
								<td>
									<html:text property="kssj" styleId="kssj" style="width: 80px"
										onclick="return showCalendar('kssj','ymmdd');" readonly="true" />
									-
									<html:text property="jssj" styleId="jssj"  style="width: 80px"
										onclick="return showCalendar('jssj','ymmdd');" readonly="true" />
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