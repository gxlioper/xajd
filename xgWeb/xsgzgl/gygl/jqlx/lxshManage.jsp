<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	    <script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="js/xsgzgl/pjpy/pjpyComm.js"></script>
		<script language="javascript" defer="defer">
		//页面初始化
		function onShow(){
			var spgw=$("spgw").value;

			if (spgw == ""){
				showSpgw();
			} else {
				searchRs();
			}
			
		}

		//查询结果集
		function searchRs(){
			
			var spgw=$("spgw").value;
			if(spgw!=""){
				jQuery.ajaxSetup({async:false});
				
				var url = "gyglnew_jqlx_ajax.do?method=jqlxshSearch";
				var ie = "ie";// ie版本
				var v4Path = stylePath;//v4样式路径
				var spgw = $("spgw").value;//审批岗位
				
				// 需要传入后台的其它数据
				var otherValue = [ie,v4Path,spgw];
	
				// loding
				$("divWaiting").style.display="";
				$("divDisable").style.display="";
				
				// 查询操作
				searchRsByAjax(url,otherValue);
	
				// 隐藏 loding
				$("divWaiting").style.display="none";
				$("divDisable").style.display="none";
				
				
				jQuery.ajaxSetup({async:true});
			}else {
				showSpgw();
			}
		}

		//评奖项目审核(单个审核、批量审核)
		function showShxxDiv(){
			var pk = jQuery("input[name=div_pkValue]:checked").eq(0).val();
			var len = jQuery("input[name=div_pkValue]:checked").length;
			
			tipsWindown("系统提示","id:div_002","500","300","true","","true","id");
			
		}
		
		function jqlxsh(){
			
			var len=jQuery("[name=div_pkValue]:checked").length;
			
			if(len==0){
				
				alertInfo("请勾选需要审核的记录！");
				return false;
			}else if(len==1){
				var spgw=$("spgw").value;
				var sqid=jQuery("[name=div_pkValue]:checked").eq(0).val();
				var url = "gyglnew_jqlx.do?method=lxshDetail";
				url+="&sqid="+sqid;
				url+="&spgw="+spgw;
				showTopWin(url,"800","600");
			}else {
				showShxxDiv();
			}
		}


		//前往项目审核
		function showSpgw(){
		
			var url="gyglnew_jqlx_ajax.do?method=showShgwDiv";
			
			var parameter={};
			
			
			jQuery("#div_spgw").load(url,parameter,function(){
				
				var len=jQuery("[name=spgw]").length;
				
				if(!($("spgwNum").value=="1")){
					$("btn_sx").disabled=false;
					tipsWindown("系统提示","id:div_spgw","300","170","true","","true","id");
				}else{
				
					$("btn_sx").disabled=true;
					$("spgw").value=jQuery("[type=radio][id=spgw_0]").eq(0).val();
					// 获取选中的审批岗位ID
				
					$("shjb").value=jQuery("#shjb_0").val();
					// 审核级别为第一级 隐藏退回按钮 否则显示
					
					searchRs();
				}
					
			});
		}
		
		function checkSpgw(){
		
			$("spgw").value=jQuery("[name=spgw]:checked").val();
			// 获取选中的审批岗位ID
			var id=jQuery("[name=spgw]:checked").attr("id");
			// 设置审核级别的值
			$("shjb").value=jQuery("#shjb_"+id.split('_')[1]).val();
			
			// 审核级别为第一级 隐藏退回按钮 否则显示
<%--			if($("shjb").value=="1"){--%>
<%--				$("btn_th").style.display="none";--%>
<%--			}else{--%>
<%--				$("btn_th").style.display="";--%>
<%--			}--%>
			closeWindown();
			searchRs();
			
		}
		
		function saveShzt(shzt){

			jQuery.ajaxSetup({async:false});
			
			var spgw = $("spgw").value;
		
			confirmInfo("是否要保存已修改的数据？",function(tag){
				
				if(tag=="ok"){
				
					closeWindown();
					
					//主键
					var sqid=new Array();
					
					jQuery("[name=div_pkValue]:checked").each(function(i){
						sqid[i]=jQuery(this).val();
					});
					
					jQuery.ajaxSetup({async:false});
					
					// 得到JSON对象
			        var parameter ={};
					
					parameter["str_spgw"]=spgw;
					
					parameter["array_sqid"]=sqid.join("!!array!!");
					
					parameter["str_shzt"]=shzt;
					
					parameter["str_shyj"]=escape(jQuery("#shyj").val());
					
					var url = "gyglnew_jqlx_ajax.do?method=plsh";
		          	
				 	$("divWaiting").style.display="";
					$("divDisable").style.display="";
					
					jQuery.post(url,parameter,
						function(result){
							$("divWaiting").style.display="none";
							$("divDisable").style.display="none";
							
							alertInfo(result,function(tag){
								
								if(tag=="ok"){
								
									
									searchRs();
								}
								
							});
							
						}
					);
					
					jQuery.ajaxSetup({async:true});
				}
				
			});
			
		}
		
		function checkItsDis(obj){
			
			if(obj.disabled){
				
				return false;
			}else{
				
				return true;
			}		
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
	
		<html:form action="/gyglnew_jqlx" method="post">
		
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<html:hidden property="sqkg" styleId="sqkg" />
			<html:hidden property="lcid" styleId="lcid" />
			<html:hidden property="spgw" styleId="spgw" />
			<input type="hidden" name="shjb" id="shjb" />
			<input type="hidden" name="spgwNum" id="spgwNum" value="${spgwNum}" />
			<!-- 多功能操作区 -->
			<div class="toolbox">
			
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<li>
							<a href="#" id="btn_sh"
								onclick="jqlxsh();return false;" 
								class="btn_sh">
								<span>审核</span>
							</a>
						</li>
						</logic:equal>
						<li>
							<a href="#" id="btn_sx" disabled="true"
								onclick="if(checkItsDis(this)){showSpgw();};return false;"
								class="btn_sx">
								<span>切换审核岗位</span>
							</a>
						</li>
					</ul>
					<button type="button" class="btn_cx" id="search_go" style="display: none" onclick="searchRs();return false;"></button>
				</div>
				<!-- 按钮 end-->
				
				<!-- 过滤条件 -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
			<!-- 多功能操作区 end-->
			
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
			
				<div id="div_rs" style="width:100%;height:360px;overflow-x:auto;overflow-y:auto;">
				</div>
						
				<!--分页显示-->
				<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=jqlxForm"></jsp:include>
				<!--分页显示-->
			</div>
			<!-- 内容显示区 end-->
				
			<!-- 002 begin -->
			<div id="div_002" style="display:none">
				<div class="open_win01">
					<table width="100%" border="0" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>项目批量审核</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th width="25%">
									审核意见
								</th>
								<td>
									<textarea rows="5" name="shyj" id="shyj" cols="" style="width:400px"></textarea>
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="2">
									<div class="btn">
										<button type="button" onclick="saveShzt('tg');return false;">通过</button>
										<button type="button" onclick="saveShzt('btg');return false;">不通过</button>
<%--										<button type="button" id="btn_th" onclick="saveShzt('th')">退回</button>--%>
										<button type="button" onclick="closeWindown();return false;">关闭</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
			<!-- 002 end-->
			
			<div id="div_spgw" style="display:none">

			</div>
			
			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>