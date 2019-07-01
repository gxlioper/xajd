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
			
			var url = "szdw_szbb.do?method=searchSzbb";
			var ie = "ie";
			
			var otherValue = [ie];

			$("divWaiting").style.display="";
			$("divDisable").style.display="";
			
			searchRsByAjax(url,otherValue);
			setDivHeight();
			$("divWaiting").style.display="none";
			$("divDisable").style.display="none";
			
			jQuery.ajaxSetup({async:true});
		}
		
		// 辅导员编班
		function fdybbSetting(){
		
			var length=jQuery("[name=div_pkValue]:checked").length;
			var nj="";
			var xydm="";
			var zydm="";
			var bjdm="";
			if(length>1){
				
				alertInfo("仅可选择一个班级!");
				return false;
			}else if(length==1){
				
				var pkValue=jQuery("[name=div_pkValue]:checked").eq(0).val();
				var pkArr=new Array();
				pkArr=pkValue.split('!!luojw!!');
				nj=pkArr[0];
				xydm=pkArr[1];
				zydm=pkArr[2];
				bjdm=pkArr[3];
				
			}
			
			var url = "general_szdw.do?method=szbbSetting&fplx=fdy";
			url+="&nj="+nj;
			url+="&xydm="+xydm
			url+="&zydm="+zydm
			url+="&bjdm="+bjdm
			refreshForm(url);
			
		}
		
		// 班主任编班
		function bzrbbSetting(){
		
			var length=jQuery("[name=div_pkValue]:checked").length;
			var nj="";
			var xydm="";
			var zydm="";
			var bjdm="";
			if(length>1){
				
				alertInfo("仅可选择一个班级!");
				return false;
			}else if(length==1){
				
				var pkValue=jQuery("[name=div_pkValue]:checked").eq(0).val();
				var pkArr=new Array();
				pkArr=pkValue.split('!!luojw!!');
				nj=pkArr[0];
				xydm=pkArr[1];
				zydm=pkArr[2];
				bjdm=pkArr[3];
				
			}
			var url = "general_szdw.do?method=szbbSetting&fplx=bzr";
			
			url+="&nj="+nj;
			url+="&xydm="+xydm
			url+="&zydm="+zydm
			url+="&bjdm="+bjdm
			refreshForm(url);
			
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
					选择相应的班级，进行分配辅导员或班主任的操作。
				</span>
			</p>			
			<a class="close" title="隐藏" onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- 提示信息 end-->
		<html:form action="/general_szdw" method="post">
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
								<a href="#" class="btn_sr" onclick="fdybbSetting();return false;">
									增加辅导员
								</a>
							</li>
							<li>
								<a href="#" class="btn_gx" onclick="bzrbbSetting();return false;">
									增加班主任
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
				<div id="div_rs" style="height:380px;overflow-x:auto;overflow-y:hidden;">
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
			
			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>