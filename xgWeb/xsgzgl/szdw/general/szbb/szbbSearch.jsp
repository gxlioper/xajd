<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 --> 
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
	    <script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
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
			var isopen = jQuery("#isopen").val();
			if(isopen==null||isopen==''){
				showAlertDivLayer('基础设置未初始化，请联系管理员！');
				return false;
			}
			if ("false" == isopen){
				showAlertDivLayer("当前未开放编班，请联系管理员！");
				return false;
			}
		
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

		//湖南涉外经济学院增加QQ群号--
		function SetQQqh(){
			var length=jQuery("[name=div_pkValue]:checked").length;
			var bjdm="";
			if(length!=1){
				
				alertInfo("请选择且仅选择一个班级!");
				return false;
			}else if(length==1){
			
				var pkValue=jQuery("[name=div_pkValue]:checked").eq(0).val();
				var pkArr=new Array();
				pkArr=pkValue.split('!!luojw!!');
				nj=pkArr[0];
				bjdm=pkArr[3];
				xymc=pkArr[4];
				zymc=pkArr[5];
				rs=pkArr[6];
				fdyxm=pkArr[7];
				bzrxm=pkArr[8];
				var url = "general_szdw.do?method=setQQqh&nj="+nj+"&xymc="+xymc+"&zymc="+zymc+"&bjdm="+bjdm+"&rs="+rs+"&fdyxm="+fdyxm+"&bzrxm="+bzrxm;
				showDialog("填写班级QQ群",600,355,url);
			}
		}
		
		jQuery(function(){
			onShow();
		})
		
		function szdwbbExportConfig() {
			customExport("general_szdw.do", szdwbbExportData);
		}
		
		// 导出方法
		function szdwbbExportData() {
			setSearchTj();//设置高级查询条件
			var url = "szdw_szbb.do?method=szdwbbExportData&dcclbh=" + "general_szdw.do";//dcclbh,导出功能编号
			url = addSuperSearchParams(url);//设置高级查询参数
			jQuery("form").eq(0).attr("action", url);
			jQuery("form").eq(0).submit();
		}

		//导入方法
		function drxx(){
			toImportData("IMPORT_SZDW_BBXX");
			return false;
		}

		// 学生信息
		function xsxxView(pk,n){
			 pk = encodeURI(encodeURI(pk));
			if(0 == n) {
				alertError("班级人数为0，无法查看详情！");
				return false;
			}
			showDialog("学生详细信息",800,430,"gygl_lfrydj.do?method=xsxxView&pk="+pk);
		}

		function jslx(){
			var length=jQuery("[name=div_pkValue]:checked").length;
			if(length < 1){
				showAlertDivLayer("请选择一个班级！");
			}else{
				//top.location.href = "";
				showDialog("请选择编班的教师类型", 400, 200, "szdw_szbb.do?method=jslx&quantity="+length);
			}
		}
	
		</script>
	</head>
	<body  >
		<input type="hidden" name="isopen" id="isopen" value="${bbsjModel.isopen }"/>
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title}</a>
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
					选择相应的班级，为班级分配大队长或中队长
				</logic:equal>
				<logic:notEqual value="12834" name="xxdm">
					选择相应的班级，为班级分配辅导员或班主任
				</logic:notEqual>
					
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
							<logic:equal name="bbsjModel" property="isopen" value="true" >
							<li>
								<a href="#" class="btn_sr" onclick="fdybbSetting();return false;">
									
											<logic:equal value="12834" name="xxdm">
												增加大队长
											</logic:equal>
											<logic:notEqual value="12834" name="xxdm">
												增加辅导员
											</logic:notEqual>
								</a>
							</li>
							<logic:notEqual value="10264" name="xxdm">  <!-- 上海海洋大学不需要显示  -->
								<%--<li>
									<a href="#" class="btn_gx" onclick="bzrbbSetting();return false;">
										<logic:equal value="12834" name="xxdm">
												增加中队长
											</logic:equal>
											<logic:notEqual value="12834" name="xxdm">
												增加班主任
											</logic:notEqual>
									</a>
								</li>--%>
								<logic:equal value="12303" name="xxdm">
									<li>
										<a href="#" class="btn_zj" onclick="SetQQqh();return false;">
												增加班级信息
										</a>	
									</li>
								</logic:equal>
							</logic:notEqual>
							</logic:equal>
							<li><a href="#" class="btn_dc" onclick="szdwbbExportConfig();return false;">导出</a></li>
							<!-- <li><a href="javascript:;" onclick="drxx();return false;" id="btn_dr" class="btn_dr">导入</a></li> -->							
						</logic:equal>
						<logic:equal value="00000" name="xxdm">
						<li><a href="javascript:void(0);" onclick="jslx();" class="btn_sz">批量编班</a></li>
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