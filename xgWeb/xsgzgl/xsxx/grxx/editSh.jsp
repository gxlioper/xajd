<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<link rel="stylesheet" type="text/css" href="js/jquery/themes/default/easyui.css" />
	    <link rel="stylesheet" type="text/css" href="js/jquery/themes/icon.css" />
	    <script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" defer="defer">
		
		//查询结果集
		function searchRs(){
		
			var url = "xsxx_grxx.do?method=getXgshList";

			$("divWaiting").style.display="none";
			$("divDisable").style.display="none";
			
			var ie = "ie";
			var shgw = $("shgw").value;
			
			var otherValue = [ie,stylePath,shgw];

			if(shgw == ""){
				checkYhgw();
			}else{
				searchRsByAjax(url,otherValue);
			}
		}
		
		//校验用户岗位
		function checkYhgw(){
			
			var num = jQuery("input[name=yhgw_id]").length;
			
			if(num == "0"){
				alertInfo("你并不在本次个人信息修改审核流程中，无法展开审核操作");
				return false;
			}else if(num == "1"){
				 $("shgw").value = jQuery("input[name=yhgw_id]")[0].value;
				 searchRs();
			}else{	
				var html = "";
				
				for(var i=0;i<num;i++){
					if(i!=0){
						html+="<br/>";
					}
					html+="<input type=\"radio\" name=\"rad_yhgw\" value=\""+jQuery("input[name=yhgw_id]")[i].value+"\" onclick=\"$('hid_gwid').value=this.value\">";
					html+=jQuery("input[name=yhgw_mc]")[i].value;
				}
				
				html+="<input type=\"hidden\" id=\"hid_gwid\" value=\"\">";
				
				$("p_gwxx").innerHTML = html;
				
				tipsWindown("系统提示","id:div_gwxx","350","250","true","","true","id");
			}
		}
		
		//展示审核详细页面
		function showShDetail(){
			var num = jQuery("input[name=checkVal]:checked").length;
			
			if(num == 0){
				alertError("请勾选您希望审核的记录");
				return false;
			}else if(num == 1){
			
				var sqid = jQuery("input[name=checkVal]:checked")[0].value;
				var shgw = $("shgw").value;
				
				var url = "xsxx_grxx.do?method=editDetail";
					url+= "&sqid="+sqid;
					url+= "&shgw="+shgw;

				showTopWin(url,'800','620');
			}else{
				alertError("只能勾选一条记录进行审核，请确认");
				return false;
			}
		}
		</script>
	</head>
	<body onload="searchRs();" >
	
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv();">使用帮助</a>
			</p>
		</div>			
		<!-- 标题 end-->
		<!-- 提示信息 START-->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>提示：</span>
			</h3>
			<p>
				<span id="div_help" style="display: none">
				1.如果您在审核流程中拥有多个岗位，请先选择本次需要执行审核操作的<font color="blue">岗位</font>。</br>
				2.如果您希望换一个岗位进行审核操作，请点击<font color="blue">换岗审核</font>。</br>
				3.如果您只拥有一个岗位的话，系统会自动指定该岗位，并且没有<font color="blue">换岗审核</font>操作。</br>
				4.查询到的数据为本级别审核状态(<font color="blue">未审核或需重审</font>),前一级别(<font color="blue">审核通过</font>)，后一级别(<font color="blue">未审核或退回</font>)的申请记录。</br>
				5.勾选一条记录后，点击<font color="blue">审核按钮</font>，将对该申请记录执行审核操作。</br>
				</span>
			</p>
			<a class="close" title="隐藏"  onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- 提示信息 end-->
		
		<html:form action="/xsxx_grxx" method="post">

			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- 审核岗位 -->
			<input type="hidden" id="shgw" name="shgw" value=""/>
			<!-- 用户岗位 -->
			<logic:iterate name="yhgwList" id="yhgw">
				<input type="hidden" id="yhgw_id_${yhgw.gwid }" name="yhgw_id" value="${yhgw.gwid }"/>
				<input type="hidden" id="yhgw_mc_${yhgw.gwid }" name="yhgw_mc" value="${yhgw.gwmc }"/>
			</logic:iterate>
			<!-- 多功能操作区 -->
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<!-- 读写权 -->
						<logic:equal name="writeAble" value="yes">
							<li>
								<a href="#" onclick="showShDetail();return false;" class="btn_sh">
									审核
								</a>
							</li>
							<logic:notEqual name="gwnum" value="1">
								<li>
									<a href="#" onclick="checkYhgw();return false;" class="btn_cx">
										换岗审核
									</a>
								</li>
							</logic:notEqual>
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
					<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=xsxxGrxxForm"></jsp:include>
					 <script type="text/javascript">
				     $('choose').className="hide";
				     </script>
				</div>
				<!--分页显示-->
			</div>
			<!-- 内容显示区开始 end-->	
			
			<!-- 岗位信息弹出层 -->
			<div id="div_gwxx" style="display:none">
				<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>岗位信息(您拥有多个岗位，请选择本次审核的岗位)</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th width="30%">
									您的岗位
								</th>
								<td>
									<p id="p_gwxx" style="height: 50px">
									
									</p>
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="2">
									<div class="bz">
										
									</div>
									<div class="btn">
										<button type="button" id="btn_bc" onclick="$('shgw').value=$('hid_gwid').value;searchRs();closeWindown();">
											确 定
										</button>
										
										<button type="button" id="btn_gb" onclick="closeWindown();">
											关 闭
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
			
			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>