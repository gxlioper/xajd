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
			
			var url = "jhzy_gjzxj.do?method=searchZxjsq";
			var ie = "ie";
			
			var otherValue = [ie];

			$("divWaiting").style.display="";
			$("divDisable").style.display="";
			
			searchRsByAjax(url,otherValue);

			$("divWaiting").style.display="none";
			$("divDisable").style.display="none";
			
			jQuery.ajaxSetup({async:true});
		}
		
		//申请
		function showZxjsqAdd(){
			var url = "jhzy_gjzxj.do?method=zxjsqInsert";
			showTopWin(url,"800","480");
		}
		
		//修改
		function showZxjsqEdit(){
			var len=jQuery("[name=primarykey_checkVal]:checked").length;
	
			if(len==1){	
				var flag = true;	
				jQuery("[name=primarykey_checkVal]:checked").each(function(){
					var shzt = jQuery(this).parents().children("td").eq(8).html();
					if(shzt != "未审核"){
						flag = false;
					}
				});
				
				if(!flag){
					alertError("只能修改<font color='blue'>未审核</font>的记录，请您确认^_^||");
				}
				
				if(flag){
					var pkValue=jQuery("[name=primarykey_checkVal]:checked").eq(0).val();
					var url="jhzy_gjzxj.do?method=zxjsqUpdate";
						url+="&pkValue="+pkValue;
						showTopWin(url,800,480);
				}
			}else{	
				alertError("请<font color='blue'>勾选一条</font>您希望修改的记录！");	
				return false;
			}
		}
		
		//删除
		function deleteGjzxjSq(){
			var len=jQuery("[name=primarykey_checkVal]:checked").length;
	
			if(len!=0){	
				var flag = true;	
				jQuery("[name=primarykey_checkVal]:checked").each(function(){
					var shzt = jQuery(this).parents().children("td").eq(8).html();
					if(shzt != "未审核"){
						flag = false;
					}
				});
				
				if(!flag){
					alertError("只能删除<font color='blue'>未审核</font>的记录，请您确认^_^||");
				}
				
				if(flag){
				
					confirmInfo("请您确认<font color='blue'>是否删除</font>所勾选的申请记录？",function(tag){
						if(tag=="ok"){
							var url = "jhzy_gjzxj.do?method=deleteGjzxjSq";
							var pkValue=new Array();
							var i=0;
							
							jQuery("input[name=primarykey_checkVal]:checked").each(function(){
								pkValue[i]=jQuery(this).val();
								i++;
							});
							
							var parameter={};
							parameter["array_pkValue"]=escape(pkValue.join("!!array!!"));
					
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
			}else{	
				alertError("请<font color='blue'>勾选</font>您希望删除的记录！");	
				return false;
			}
		}
		
		//流程跟踪
		function showLcgz(){
			var len=jQuery("[name=primarykey_checkVal]:checked").length;
	
			if(len==1){	
				var pk=jQuery("[name=primarykey_checkVal]:checked").eq(0).val();
				jQuery.ajaxSetup({async:false});

				//路径
				var url = "jhzy_gjzxj.do?method=createLcgzHtml";
				//参数
			 	var parameter = {
					"str_pk":pk
				};
				
				$("divWaiting").style.display="";
				$("divDisable").style.display="";
				
				jQuery("#div_lcgz").load(
					url,
					parameter,
					function(){
						$("divWaiting").style.display="none";
						$("divDisable").style.display="none";
					}
				);
					
				tipsWindown("系统提示","id:div_lcgz","500","300","true","","true","id");
			
				jQuery.ajaxSetup({async:true});
			}else{	
				alertError("请<font color='blue'>勾选一条</font>您希望跟踪流程的记录！");	
				return false;
			}	
		}
		
		function showPrint(){
		
			var n=jQuery("[name=primarykey_checkVal]:checked").length;
			
			if(n==1){
				var pk=jQuery("[name=primarykey_checkVal]:checked").eq(0).val();

				var pkArr=pk.split("luojw");
				var xn=pkArr[0];
				var xh=pkArr[1];
				showOpenWindow("jhzy_gjzxj.do?method=gjzxjb&xh="+xh+"&xn="+xn);
			}else{
				
				alertInfo("请勾选一条需要打印的记录！");
				return false;
			}
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
								<a href="#" onclick="showZxjsqAdd();return false;" id="btn_zj" class="btn_zj">
									申请
								</a>
							</li>
							<li>
								<a href="#" onclick="showZxjsqEdit();return false;" id="btn_xg" class="btn_xg">
									修改
								</a>
							</li>
							<li>
								<a href="#" onclick="deleteGjzxjSq();return false;" id="btn_sc" class="btn_sc">
									删除
								</a>
							</li>
						</logic:equal>
						<li>
							<a href="#" onclick="showLcgz();return false;" id="btn_ccg" class="btn_ccg">
								流程跟踪
							</a>
						</li>
						<li><a href="#" class="btn_qx" onclick="choiceFields();return false;">导出设置</a></li>
						<li><a href="#" class="btn_dc" onclick="configureExportData();return false;">导出数据</a></li>
						<li>
							<a href="#" class="btn_dy" onclick="showPrint();return false;" onclick="return false;">打印报表</a>
						</li>
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
			
			<!-- 流程跟踪弹出层 -->
			<div id="div_lcgz" style="display:none">
				<div class="open_win01">
					sss
				</div>
			</div>
			<!-- 流程跟踪弹出层 end-->
			
			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>