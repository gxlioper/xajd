 <%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：cq -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%--<%@ include file="/syscommon/pagehead_V4.ini"%>	
		--%><%@ include file="/syscommon/head.ini"%>		
		<script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type='text/javascript' src="js/uicomm.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script><!-- 导入功能需要 -->
		<script language="javascript" defer="defer">
		//初始化
		jQuery(document).ready(function(){ 
			searchRs();
		});

		function setDivHeight(){
			if($("table_rs")){
				jQuery("#div_rs").height(jQuery("#table_rs").height()+20);	
			}
		}


		//查询
		function searchRs(){
			var url = "gyjl_gyjlglnew_ajax.do?method=gyjlxxwh";
			var ie = "ie";
			var otherValue = [ie];
			searchRsByAjax(url,otherValue);
		    setTimeout("setDivHeight()","1000")
		}

		//增加
		function addGyjl(){
			var url="gyjl_gyjlglnew.do?method=gyjlZj";
			//showTopWin(url,'800','600');
			showDialog("增加公寓记录信息维护", 800, 480, url);
		}

		//修改
		function showModi(){
			
			var len=jQuery("[name=div_pkValue]:checked").length;
			
			if(len==1){	
				
				var pk_value=jQuery("[name=div_pkValue]:checked").eq(0).val();

				var xh=pk_value.split("!!shen!!")[0];

				var jlsj=pk_value.split("!!shen!!")[2];
				
				var gyjllbdm=pk_value.split("!!shen!!")[1];
				
				var url="gyglnew_gyjlgl_gyjlxg.do";  
				
				url+="?xh="+xh+"&jlsj="+jlsj+"&gyjllbdm="+gyjllbdm;
				 
				//showTopWin(url,800,600);
				showDialog("修改公寓记录信息维护", 800,480, url);
			}else{
				
				alertInfo("请勾选一条需要修改的记录！");
				
				return false;
			}
		}

		//删除
		function del(){
			var len=jQuery("[name=div_pkValue]:checked").length;
			var blog=true;
			if(len>=1){	
				if(blog){
					confirmInfo("该操作将会删除您所勾选的数据，是否确定继续操作？",function(tag){
						if(tag=="ok"){
							var array = jQuery("[name=div_pkValue]:checked");
							var str = "";
							for (var i=0;i<array.length;i++) {
								var pkValue = jQuery(array[i]).parent().parent().find("td").eq(0).find("input[type='checkbox']").val();
								str += pkValue+"!!@@";
							}
							var parameter={}
							var url="gyjl_gyjlglnew.do?method=gyjlSc";	
							parameter["str"]=str;							
							jQuery.ajaxSetup({async:false});	
							jQuery.post(url,
								parameter,
								function(result){
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
			}else{
				alertInfo("请勾选需要删除的数据！",function(tag){
					if(tag=="ok"){
						return false;
					}
				});
			}
		}

		//双击查看
		function ShowView(){
			
			var pk_value=curr_row.getElementsByTagName('input')[0].value;
			
			var xh=pk_value.split("!!shen!!")[0];
			
			var jlsj=pk_value.split("!!shen!!")[2];
				
			var gyjllbdm=pk_value.split("!!shen!!")[1];
			
			var url="gyglnew_gyjlgl.do?method=gyjlcxView";
			
			url+="&xh="+xh+"&wjsj="+jlsj+"&gyjllbdm="+gyjllbdm;

			//showTopWin(url,800,650);
			if(jQuery("#xxdm").val() == '11799'){
				showDialog('查看学生公寓奖惩信息', 850, 500, url);
			}else{
				showDialog('查看学生公寓纪律信息', 850, 500, url);
			}
		}
		//单击学号查看
		function zxsxxView(xh){
			
				var pkValue=xh;
				var url="xsxx_tygl.do?method=ckZxsxx";
				url+="&xh="+pkValue;
				var width=850;
				//showTopWin(url,850,640);
				showDialog('查看学生详细信息', 850, 500, url);
		}
		
		function gyjlxxwhExportConfig() {
			//DCCLBH导出功能编号,执行导出函数 
			customExport("gyjl_gyjlglnew_ajax.do", gyjlxxwhExportData);
			}
			
		
			
		// 导出方法
		function gyjlxxwhExportData() {
			setSearchTj();//设置高级查询条件
			var url = "gyjl_gyjlglnew_ajax.do?method=gyjlxxwhexportData&dcclbh=" + "gyjl_gyjlglnew_ajax.do";//dcclbh,导出功能编号
			url = addSuperSearchParams(url);//设置高级查询参数
			jQuery("form").eq(0).attr("action", url);
			jQuery("form").eq(0).submit();
		}
		/**
		 * 导入
		 */
		function importConfig() {
			toImportData("IMPORT_N380803");
			return false;
		}
		</script>
	</head>
	<body>
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>


		<html:form action="/gyjl_gyjlglnew" method="post">

			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="xmdm" id="xmdm" value="${xmdm }" />
			<!-- 隐藏域 -->
			<!-- 多功能操作区 -->
			<div class="toolbox" id="dgncz">
				<logic:equal name="writeAble" value="yes">
					<!-- 按钮 -->
					<div class="buttonbox">
						<ul>
						
							<li>
								<a href="#" onclick="addGyjl();return false;" class="btn_zj">
									增加
								</a>
							</li>
							<li>
								<a href="#" onclick="showModi();return false;" class="btn_xg">
									修改
								</a>
							</li>
							<logic:notEqual name="xxdm" value="11647">
								<li>
									<a href="#" onclick="del();return false;" class="btn_sc">
										删除
									</a>
								</li>
							</logic:notEqual>
							<logic:equal name="xxdm" value="11647">
								<logic:equal name="userType" value="admin">
									<li>
										<a href="#" onclick="del();return false;" class="btn_sc">
											删除
										</a>
									</li>
								</logic:equal>
							</logic:equal>
							<li><a href="#" class="btn_dc" onclick="gyjlxxwhExportConfig();return false;">导出</a></li>
						</ul>
					</div>
					<!-- 按钮 end-->
				</logic:equal>
				<!-- 过滤条件 -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
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
				<h3 class="datetitle_01">
					<span> 查询结果&nbsp;&nbsp;<font color="blue">双击记录可查看详细信息;</font></span>
				</h3>
				<div id="div_rs"
					style="width:100%;overflow-x:auto;overflow-y:hidden;">
				</div>

				<!--分页显示-->
				<jsp:include flush="true"
					page="/sjcz/turnpage.jsp?form=gyjlxxglNewForm"></jsp:include>
			</div>
			<div id="div_detail" style="display:none">
			</div>		
			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxxNew.jsp"%>
		</html:form>
	</body>
</html>