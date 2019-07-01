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
			
			var url = "jhzy_knsrd.do?method=searchKnsjg";
			var ie = "ie";
			
			var otherValue = [ie];

			$("divWaiting").style.display="";
			$("divDisable").style.display="";
			
			searchRsByAjax(url,otherValue);

			$("divWaiting").style.display="none";
			$("divDisable").style.display="none";
			
			jQuery.ajaxSetup({async:true});
		}
		
		//详细
		function showKnsjgDetail(){
		var len=jQuery("[name=primarykey_checkVal]:checked").length;
			if(len!=1){	
				alertError("请<font color='blue'>勾选一条</font>您希望查看的记录！");	
				return false;
			}else if(len==1){	
				var pkValue=jQuery("[name=primarykey_checkVal]:checked").eq(0).val();
				var url="jhzy_knsrd.do?method=knsjgDetail";
					url+="&pkValue="+pkValue;
					showTopWin(url,800,600);
			}
		}
		
		//展示推荐档次DIV
		function showTjdcDiv(){
			var len=jQuery("[name=primarykey_checkVal]:checked").length;
			if(len==0){	
				alertError("请<font color='blue'>勾选</font>您希望修改的记录！");	
				return false;
			}else{	
				tipsWindown("系统提示","id:div_tjdc","500","200","true","","true","id");
			}
		}
		
		//保存推荐档次
		function saveKnsrdTjdc(){
			var url = "jhzy_knsrd.do?method=saveKnsrdTjdc";
			var pkValue=new Array();
			var i=0;
			
			jQuery("input[name=primarykey_checkVal]:checked").each(function(){
				pkValue[i]=jQuery(this).val();
				i++;
			});
			
			var tjdc = jQuery("[name=tjdc]:checked").eq(0).val();
			
			var parameter={};
			parameter["array_pkValue"]=escape(pkValue.join("!!array!!"));
			parameter["str_tjdc"]=escape(tjdc);
	
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
		
		function showPrint(){
		
			var n=jQuery("[name=primarykey_checkVal]:checked").length;
			
			if(n==1){
				var pk=jQuery("[name=primarykey_checkVal]:checked").eq(0).val();

				var pkArr=pk.split("luojw");
				var xn=pkArr[0];
				var xh=pkArr[1];
				showOpenWindow("jhzy_knsrd.do?method=knsrdb&xh="+xh+"&xn="+xn);
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
						<li>
							<a href="#" onclick="showKnsjgDetail();return false;" id="btn_shtg" class="btn_shtg">
								查看
							</a>
						</li>
						<!-- 读写权 -->
						<logic:equal name="writeAble" value="yes">
							<li>
								<a href="#" onclick="showTjdcDiv();return false;" id="btn_xg" class="btn_xg">
									修改推荐档次
								</a>
							</li>
							<li><a href="#" class="btn_qx" onclick="choiceFields();return false;">导出设置</a></li>
							<li><a href="#" class="btn_dc" onclick="configureExportData();return false;">导出数据</a></li>
						</logic:equal>
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
					<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=jhzyXszzKnsrdForm"></jsp:include>
					 <script type="text/javascript">
				     //$('choose').className="hide";
				     </script>
				</div>
				<!--分页显示-->
			</div>
			<!-- 内容显示区开始 end-->
			
			<!-- 推荐档次弹出层 -->
			<div id="div_tjdc" style="display:none">
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
									<input type="radio" name="tjdc" value="一般困难" checked="checked"/>一般困难<br/>
									<input type="radio" name="tjdc" value="特别困难"/>特别困难<br/>
									<input type="radio" name="tjdc" value="不困难"/>不困难
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="2">
									<div class="btn">
										<button type="button" onclick="saveKnsrdTjdc()">保存</button>
										<button type="button" onclick="closeWindown();return false;">关闭</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
			<!-- 推荐档次弹出层 end-->
			
			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>