<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 --> 
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
	    <script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="js/xsgzgl/szdw/dwwh.js"></script>
		<script language="javascript" src="js/xsgzgl/xtwh/commUtil.js"></script>
		<script type='text/javascript' src="js/xgutil.js"></script>
			<script type='text/javascript' src='/xgxt/dwr/engine.js'></script> 
			<script type='text/javascript' src='/xgxt/dwr/interface/exportData.js'></script>	
		<script language="javascript" defer="defer">
		
		//初始化
		function onShow(){ 
			searchRs();
		}
		
		//查询结果集
		function searchRs(){
			
			jQuery.ajaxSetup({async:false});
			var path = jQuery("#path").val();
			var url = "szdw_dwwh.do?method=searchDwwh&path="+path;
			var ie = "ie";
			
			var otherValue = [ie];

			$("divWaiting").style.display="";
			$("divDisable").style.display="";
			
			searchRsByAjax(url,otherValue);

			$("divWaiting").style.display="none";
			$("divDisable").style.display="none";
			
			jQuery.ajaxSetup({async:true});
		}
		
		jQuery(function(){
			onShow();
		})
		function viewjgz(zgh){
			var url='szdw_dwwh.do?method=ckJzgxx&zgh='+zgh;
			showDialog('', 830, 500, url);
		}
		function fdyxxwhExportConfig() {
			customExport("szdw_general_dwwh.do", fdyxxwhExportData);
		}
		
		// 导出方法
		function fdyxxwhExportData() {
			setSearchTj();//设置高级查询条件
			var url = "szdw_dwwh.do?method=fdyxxwhExportData&dcclbh=" + "szdw_general_dwwh.do";//dcclbh,导出功能编号
			url = addSuperSearchParams(url);//设置高级查询参数
			jQuery("form").eq(0).attr("action", url);
			jQuery("form").eq(0).submit();
		}
		</script>
	</head>
	<body  >
		<input type="hidden" name="isopen" id="isopen" value="${bbsjModel.isopen }"/>
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
		<!-- 提示信息 -->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>提示：</span>
			</h3>
			<p>
				<span>
				<logic:equal value="12834" name="xxdm">
					选择大队长，为大队长分配管辖的班级
				</logic:equal>
				<logic:notEqual value="12834" name="xxdm">
					选择辅导员，为辅导员分配管辖的班级
				</logic:notEqual>
				</span>
			</p>
			<a class="close" title="隐藏" onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- 提示信息 end-->
		<html:form action="/general_szdw" method="post">
			<!-- 隐藏域 -->
			<input type="hidden" id="path" value="${path}" />
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- 多功能操作区 -->
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						
						<!-- 读写权 begin-->
						<logic:equal name="writeAble" value="yes">
							<logic:equal name="bbsjModel" property="isopen" value="true" >
							<li>
								<a href="#" class="btn_sr" onclick="goFdybb();return false;">
									<logic:equal value="12834" name="xxdm">
												大队长编班
									</logic:equal>
									<logic:notEqual value="12834" name="xxdm">
												辅导员编班
									</logic:notEqual>
								</a>
							</li>
							<logic:notEqual value="10264" name="xxdm"> <!-- 上海海洋大学不需要显示  -->
								<li>
									<a href="#" class="btn_gx" onclick="goBzrbb();return false;">
									<logic:equal value="12834" name="xxdm">
												中队长编班
									</logic:equal>
									<logic:notEqual value="12834" name="xxdm">
												班主任编班
									</logic:notEqual>
									</a>
								</li>
							</logic:notEqual>
							</logic:equal>
							<li>
								<a href="#" class="btn_csh" onclick="createYxjrDiv();return false;">
									院校兼任情况设置
								</a>
							</li>
						<li><a href="#" class="btn_dc" onclick="fdyxxwhExportConfig();return false;">导出</a></li>
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
					<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=generalSzdwGeneralForm"></jsp:include>
					 <script type="text/javascript">
				     //$('choose').className="hide";
				     </script>
				</div>
				<!--分页显示-->
			</div>
			<!-- 内容显示区开始 end-->
			
			<!-- 队伍维护Div begin -->
			<div id="div_dwwh" style="display:none">
				
			</div>
			<!-- 队伍维护Div end -->

			<!-- 用户库Div begin -->
			<div id="div_yhk" style="display:none">
				
			</div>
			<!-- 用户库Div end -->
			
			<!-- 院校兼任Div begin -->
			<div id="div_yxjr" style="display:none">
				
			</div>
			<!-- 院校兼任Div end -->
			
			<!-- 班级信息Div begin -->
			<div id="div_bjxx" style="display:none">
				
			</div>
			<!-- 班级信息Div end -->
			
			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>