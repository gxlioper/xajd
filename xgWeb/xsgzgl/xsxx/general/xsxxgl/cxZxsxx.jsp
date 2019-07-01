<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：lt -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
<%--		<%@ include file="/syscommon/pagehead_V4.ini"%>--%>
	    <script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
				
		<script type='text/javascript' src='dwr/engine.js'></script> 
		<script type='text/javascript' src='dwr/util.js'></script>
		<script type='text/javascript' src='dwr/interface/exportData.js'></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type='text/javascript' src="js/xgutil.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		
		<script language="javascript" defer="defer">
		
		//初始化
		function onShow(){ 
			searchRs();
		}
		
		//查询结果集
		function searchRs(){
			
			jQuery.ajaxSetup({async:false});
			
			var url = "xsxx_tygl.do?method=cxZxsxxjg";
			var ie = "ie";
			
			var otherValue = [ie];

			
			searchRsByAjax(url,otherValue);

			
			jQuery.ajaxSetup({async:true});
			
		}
		
		//增加
		function showzxsxxAdd(){
			var url = "xsxx_tygl.do?method=zjZxsxx";
			showDialog("增加学生信息",750,380,url);
		}
		
		//修改
		function showzxsxxEdit(){
			var len=jQuery("[name=primarykey_checkVal]:checked").length;
	
			if(len==1){	
				var pkValue=jQuery("[name=primarykey_checkVal]:checked").eq(0).val();
				var url="xsxx_tygl.do?method=xgZxsxx";
				url+="&xh="+pkValue;
				showDialog("修改学生信息",850,550,url);
			}else{	
				alertInfo("请<font color='blue'>勾选一条</font>您希望修改的记录！");	
				return false;
			}
		}
		
		//查看
		function showzxsxxView(){
			var len=jQuery("[name=primarykey_checkVal]:checked").length;
	
			if(len==1){	
				var pkValue=jQuery("[name=primarykey_checkVal]:checked").eq(0).val();
				var url="xsxx_tygl.do?method=ckZxsxx";
				url+="&xh="+pkValue;
				var width=850;
				showDialog("查看学生信息",850,500,url);
				var height = 640;
				var left = (screen.width/2) - width/2;
				var top = (screen.height/2) - height/2;
				//var styleStr = 'toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=yes,copyhistory=yes,width='+width+',height='+height+',left='+left+',top='+top+',screenX='+left+',screenY='+top;
				//window.open(url,"msgWindow", styleStr);
			}else{	
				alertInfo("请<font color='blue'>勾选一条</font>您希望查看的记录！");
				return false;
			}
		}

		//新版查看弹出层
		function zxsxxView(xh){

				showDialog("学生信息查询",850,500,"xsxx_tygl.do?method=ckZxsxx&xh="+xh+"&xs");
		}

		
		//删除
		function deletezxsxx(){
			var len=jQuery("[name=primarykey_checkVal]:checked").length;
	
			if(len!=0){	
					confirmInfo("请您确认<font color='blue'>是否删除</font>所勾选的记录？",function(tag){
						if(tag=="ok"){
							var url = "xsxx_tygl.do?method=scZxsxx";
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
			}else{	
				alertInfo("请<font color='blue'>勾选</font>您希望删除的记录！");	
				return false;
			}
		}
		//密码初始化
		function mmcsh(){
			
			var n = jQuery("[name=primarykey_checkVal]:checked").length;
			
			if(n>0){
				showDialog("密码初始化",350,210,"xsxx_tygl.do?method=showCshmm");
			}else{	
				alertInfo("请<font color='blue'>勾选</font>您需要初始化的数据！");				
			}
		}
		
		jQuery(function(){
			onShow();
		})
		
		//打印通用学籍卡
		function printTyXjk(){
			var len=jQuery("[name=primarykey_checkVal]:checked").length;
			if(len==1){
				var pkValue=jQuery("[name=primarykey_checkVal]:checked").eq(0).val();
				var url="xsxx_tygl.do?method=getXjk";
				
				var url= url+"&xh="+pkValue;
				window.open(url);
			}else if(len==0){	
				alertInfo("请<font color='blue'>勾选一条</font>您希望下载的记录！");	
				return false;
			}else{
				var url="xsxx_tygl.do?method=getXjkZipTy";
				var pkValue=jQuery("[name=primarykey_checkVal]:checked")
				var ids=pkValue.eq(0).val();
				for(i=1;i<pkValue.length;i++){
					ids+=","+pkValue.eq(i).val();
				}
				var url= url+"&value="+ids;
				window.open(url);
			}
		}
		
		//打印报表
		function printZxsxx(){
			var len=jQuery("[name=primarykey_checkVal]:checked").length;
			if(len==1){	
				var pkValue=jQuery("[name=primarykey_checkVal]:checked").eq(0).val();
				var url="xsxx_tygl.do?method=printJsp";
				url+="&xh="+pkValue;
				window.open(url);
			}else{	
				alertInfo("请<font color='blue'>勾选一条</font>您希望修改的记录！");	
				return false;
			}
		}

		function getWord(){
			var len=jQuery("[name=primarykey_checkVal]:checked").length;
			if(len==1){
				var pkValue=jQuery("[name=primarykey_checkVal]:checked").eq(0).val();
				var url="xsxx_tygl.do?method=getXjkWord";
				
				var url= url+"&xh="+pkValue;
				window.open(url);
			}else if(len==0){	
				alertInfo("请<font color='blue'>勾选一条</font>您希望下载的记录！");	
				return false;
			}else{
				var url="xsxx_tygl.do?method=getXjkZip";
				var pkValue=jQuery("[name=primarykey_checkVal]:checked")
				var ids=pkValue.eq(0).val();
				for(i=1;i<pkValue.length;i++){
					ids+=","+pkValue.eq(i).val();
				}
				var url= url+"&value="+ids;
				window.open(url);
			}
		}
		
		function printXjk(){
			var len=jQuery("[name=primarykey_checkVal]:checked").length;
			if(len>=1){	
				var xhstr=jQuery("[name=primarykey_checkVal]:checked").eq(0).val();
				var pkValue="";
				for(i=1;i<len;i++){
					xhstr+=","+jQuery("[name=primarykey_checkVal]:checked").eq(i).val();
				}
				jQuery("#xhstr").val(xhstr);
				var url="xsxx_tygl.do?method=plPrintJsp";
				document.forms[0].action=url;
				document.forms[0].target="_blank";
				document.forms[0].submit();
				document.forms[0].target="_self";
			}else{
				alertInfo("请<font color='blue'>勾选一条</font>您希望修改的记录！");	
				return false;
			}
		}
		
		
		function zxsxxExportConfig() {
			//DCCLBH导出功能编号,执行导出函数 
			customExport("xsxx_tygl_cxzxs.do", zxsxxExportData,1000,500);
		}
		
	
		
		// 导出方法
		function zxsxxExportData() {
			setSearchTj();//设置高级查询条件
			var url = "xsxx_tygl.do?method=zxsxxExportData&dcclbh=" + "xsxx_tygl_cxzxs.do";
			url = addSuperSearchParams(url);//设置高级查询参数
			jQuery("form").eq(0).attr("action", url);
			jQuery("form").eq(0).submit();
		}
		function drxx(){
			toImportData("IMPORT_N100101");
			return false;
		}
		</script>
	</head>
	<body  >
	
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
<!--			<p class="help">-->
<!--				<a href="#" onclick="return false;" -->
<!--					onmouseover ="showHelpDiv()"-->
<!--					onmouseout="showHelpDiv()"-->
<!--				>-->
<!--				使用帮助</a>-->
<!--			</p>-->
		</div>
		<!-- 标题 end-->
		
		<html:form action="/xsxx_tygl" method="post">
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="xhstr" id="xhstr" />
			
			<!-- 多功能操作区 -->
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<!-- 读写权 -->
						<logic:equal name="writeAble" value="yes">
							<!-- 非四川机电职业技术学院 -->
							<logic:notEqual name="xxdm" value="12751" >
							<li>
								<a href="#" onclick="showzxsxxAdd();return false;" id="btn_zj" class="btn_zj">
									增加
								</a>
							</li>
							<li>
								<a href="#" onclick="showzxsxxEdit();return false;" id="btn_xg" class="btn_xg">
									修改
								</a>
							</li>
							<li>
								<a href="#" onclick="deletezxsxx();return false;" id="btn_sc" class="btn_sc">
									删除
								</a>
							</li>
							<!-- end 非四川机电职业技术学院 -->
							</logic:notEqual>
							<!-- 四川机电职业技术学院 -->
							<logic:equal name="xxdm" value="12751">
								<logic:notEqual value="xx" name="userStatus">	
									<li>
										<a href="#" onclick="showzxsxxEdit();return false;" id="btn_xg" class="btn_xg">
											修改
										</a>
									</li>
								</logic:notEqual>
							<!-- end 四川机电职业技术学院 -->
							</logic:equal>
						</logic:equal>
						<li>
							<a href="#" onclick="showzxsxxView();return false;" id="btn_ck" class="btn_ck">
								查看
							</a>
						</li>
						<!--  -->
						<logic:notEqual name="xxdm" value="11600">
							<logic:notEqual name="xxdm" value="10834">
								<logic:notEqual name="xxdm" value="9800003">
									<logic:notEqual name="xxdm" value="110501">
										<li><a href="javascript:void(0);" onclick="printTyXjk();return false;" class="btn_dy">学生登记表打印</a></li>
									</logic:notEqual>
								</logic:notEqual>
							</logic:notEqual>
						</logic:notEqual>
						<!-- 湖北经济学院个性化 -->
						<logic:equal name="xxdm" value="11600">
						<li><a href="javascript:void(0);" onclick="printZxsxx();return false;" class="btn_dy">学生登记表打印</a></li>
						</logic:equal>
						<!-- 武汉职业技术学院个性化 -->
						<logic:equal name="xxdm" value="10834">
						<li><a href="javascript:void(0);" onclick="printZxsxx();return false;" class="btn_dy">学生登记表打印</a></li>
						</logic:equal>
						<!-- 广东轻工 -->
						<logic:equal name="xxdm" value="9800003">
						<li><a href="javascript:void(0);" onclick="printXjk();return false;" class="btn_dy">学生学籍卡打印</a></li>
						</logic:equal>
						<!-- 南通高等师范学校 -->
						<logic:equal name="xxdm" value="110501"><!-- update 张昌路[982] -->
						<li><a href="javascript:void(0);" onclick="getWord();return false;" class="btn_dy">下载学生学籍卡</a></li>
						<li><a href="#" onclick="impAndChkData();return false;" class="btn_dr">导入基本信息</a></li>
						</logic:equal>
						<li><a href="#" class="btn_qx" onclick="zxsxxExportConfig();return false;">导出</a></li>
						<logic:equal name="writeAble" value="yes">
							<li><a href="#" onclick="drxx();return false;" class="btn_dr">导入</a></li>
						</logic:equal>
						<%--<li><a href="#" class="btn_dc" onclick="configureExportData();return false;">导出数据</a></li>
						--%>
						<li><a href="#" onclick="mmcsh('show');return false;" class="btn_csh">密码初始化</a></li>
						
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
				<div id="div_rs" style="height:420px;overflow-x:hidden;overflow-y:auto;">
				</div>
				
				<!--分页显示-->
				<div style="clear:both;">
					<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=xsxxtyglForm"></jsp:include>
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
				</div>
			</div>
			<!-- 流程跟踪弹出层 end-->
			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
			
		</html:form>
	</body>
</html>