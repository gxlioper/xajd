<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script language="javascript" defer="defer">
		//初始化
		function onShow(){ 
			searchRs();
		}
		
		function searchRs(){
			var url = "gyglnew_fscx_ajax.do?method=fscxCx";

			var ie = "10.0";

			var otherValue = [ie];

			searchRsByAjax(url,otherValue);
			
			setTimeout("setDivHeight()","1000")
		}
		
		function setDivHeight(){
			if($("table_rs")){
				jQuery("#div_rs").height(jQuery("#table_rs").height()+20);	
			}
		}
		
		function showModi(){
			var len=jQuery("[name=div_pkValue]:checked").length;
			if(len==1){	
				var pkValue=jQuery("[name=div_pkValue]:checked").val();
				
				var lddm=jQuery("[name=div_pkValue]:checked").parent().parent().find("td").eq(0).find("input[type='hidden']").val();
				
				var qsh=jQuery("[name=div_pkValue]:checked").parent().parent().find("td").eq(3).html();
				
				var url="gyglnew_fscx.do?method=fscxWh";
				
				url+="&pkValue="+pkValue;
				
				url+="&lddm="+lddm;
				
				url+="&qsh="+qsh;
				
				//showTopWin(url,600,450);
				showDialog("修改卫生分",600,390, url);
			}else{
				alertInfo("请勾选一条需要修改的记录！");
				return false;
			}
		}
		
		function showView(){
			var len=jQuery("[name=div_pkValue]:checked").length;
			if(len==1){	
				var pkValue=jQuery("[name=div_pkValue]:checked").val();
				
				var lddm=jQuery("[name=div_pkValue]:checked").parent().parent().find("td").eq(0).find("input[type='hidden']").val();
				
				var qsh=jQuery("[name=div_pkValue]:checked").parent().parent().find("td").eq(3).html();
				
				var url="gyglnew_fscx.do?method=fscxCk";
				
				url+="&pkValue="+pkValue;
				
				url+="&lddm="+lddm;
				
				url+="&qsh="+qsh;
				
				//showTopWin(url,650,500);
				showDialog("查看卫生分",600,390, url);
			}else{
				alertInfo("请勾选一条需要查看的记录！");
				return false;
			}
		}
		
		function deleteFsxc(){
			var len=jQuery("[name=div_pkValue]:checked").length;
			var blog=true;
			if(len>=1){	
				if(blog){
					confirmInfo("该操作将会删除卫生分录入信息，是否确定继续操作？",function(tag){
						if(tag=="ok"){
							var array = jQuery("[name=div_pkValue]:checked");
							var str = "";
							for (var i=0;i<array.length;i++) {
								var pkValue = jQuery(array[i]).parent().parent().find("td").eq(0).find("input[type='checkbox']").val();
								var lddm = jQuery(array[i]).parent().parent().find("td").eq(0).find("input[type='hidden']").val();
								var qsh = jQuery(array[i]).parent().parent().find("td").eq(3).html();
								str += pkValue+"!@"+lddm+"!@"+qsh+"!!@@";
							}
							var parameter={}
							var url="gyglnew_fscx.do?method=fscxSc";	
							parameter["str"]=str;							
							jQuery.ajaxSetup({async:false});	
							jQuery.post(url,
								parameter,
								function(result){
									alertInfo(result,function(tag){
										if(tag=="ok"){
											onShow();
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
		
		jQuery(function(){
			onShow();
		})
		
		//导出数据
		function wsfcxExportConfig() {
			if(checkDcCxTj()){
				customExport("gyglnew_wsjc_fscx.do", wsfcxExportData);
			}
		}
			
		
			
		// 导出方法
		function wsfcxExportData() {
			setSearchTj();//设置高级查询条件
			var url = "gyglnew_fscx_ajax.do?method=wsfcxExportData&dcclbh=" + "gyglnew_wsjc_jcrcgl.do";//dcclbh,导出功能编号
			url = addSuperSearchParams(url);//设置高级查询参数
			jQuery("form").eq(0).attr("action", url);
			jQuery("form").eq(0).submit();
		}
		//温大卫生分统计导出
		function wsfTjDc(){
			var url ="gyglnew_fscx_ajax.do?method=wsfDcOfWd";
			var xqLength=jQuery("a[name=a_name_xq]").length;
			var xnLength=jQuery("a[name=a_name_xn]").length;
			var ldLength=jQuery("a[name=a_name_ld]").length;
			if(xqLength != "1"){
				showAlertDivLayer("请选择一个学期查询条件！");
				return false;
			}
			if(xnLength != "1"){
				showAlertDivLayer("请选择一个学年查询条件！");
				return false;
			}
			if(ldLength != "1"){
				showAlertDivLayer("请选择一个楼栋查询条件！");
				return false;
			}
			setSearchTj();
			url = addSuperSearchParams(url);
			document.forms[0].action = url;
			document.forms[0].submit();
			
			}

		//周统计导出
		function zTjDc(){
			var url ="gyglnew_fscx_ajax.do?method=zTjDc";
			setSearchTj();
			var kssj = jQuery("#kssj_kssj").val();
			var jssj = jQuery("#kssj_jssj").val();
			if(kssj == null || kssj == '' ||  jssj == null || jssj == ''){
				showAlertDivLayer("请输入检查起止时间！");
				return false;
			}
			url = addSuperSearchParams(url);
			document.forms[0].action = url;
			document.forms[0].submit();			
		}

		//公寓月卫生分统计导出
		function gywsfDc(){
			showDialog("公寓月卫生分统计导出", 400, 250, "gyglnew_fscx.do?method=gyywsfDc");
		}

		//验证导出查询条件   可以导出：true， 不可以导出：false
		function checkDcCxTj(){
			var xxdm = jQuery("#xxdm").val();
			var xxdmMap = {"10338":"10338"};

			//学校代码成立是验证数据
			if(xxdmMap[xxdm] != null && xxdmMap[xxdm] != ""){
				var xqLength=jQuery("a[name=a_name_xq]").length;
				var xnLength=jQuery("a[name=a_name_xn]").length;
				if(xqLength != "1"){
					alert("请选择一个学期查询条件！");
					return false;
				}
				if(xnLength != "1"){
					alert("请选择一个学年查询条件！");
					return false;
				}
			}
			return true;
		}

		//评定分数等级
		function pdFsDj(){

			var xn_num = jQuery("a[name=a_name_xn]").length;

			if(xn_num!=1){
				alertInfo("学年必须且只能选择一个");
				return false;
			}

			var url="gyglnew_fscx_ajax.do?method=pdFsDj";
			//模糊查询
			var input_mhcx = "";
			if($("input_mhcx")){
				input_mhcx = $("input_mhcx").value;
			}
			
			var mhcx_lx = "";
			if($("mhcx_lx")){
				mhcx_lx = $("mhcx_lx").value;
			}
			//点击查询
			var searchLx = new Array();
			var searchTj = new Array();
			var searchTjz = new Array();
			
			var n=0;
			var m=3;
			
			searchLx[0]="xy";
			searchLx[1]="zy";
			searchLx[2]="bj";
			for(var i=0;i<jytj.length;i++){
				searchLx[m]=jytj[i];
				m++;
			}

			var tj_num = $("searchTjDiv").getElementsByTagName('input').length;
				
			for(var j=0;j<tj_num;j++){
				var obj = $("searchTjDiv").getElementsByTagName('input')[j];
				searchTj[n]=obj.name;
				searchTjz[n]=escape(obj.value);
				n++;
			}
			
			//其他数据
		 	var parameter = {
				"input_mhcx":escape(input_mhcx),
				"mhcx_lx":mhcx_lx,
				"searchTj":searchTj.join("!!@@!!"),
				"searchTjz":searchTjz.join("!!@@!!"),
				"searchLx":searchLx.join("!!@@!!")
			};

			//评定分数加载中
		 	pdFsLoad();
			jQuery.post(url,parameter,function(data){
				//评定分数完成
				pdFsComplete();
				alert(data);
			});
		}

		//评定分数加载中
		function pdFsLoad(){
			jQuery("#divWaiting").show();
			jQuery("#divDisable").show();
			jQuery("#div_rs").hide();
			jQuery("#main_box").hide();
			jQuery(".btn_cx").attr("disabled","disabled");
			jQuery(".btn_sz").attr("disabled","disabled");
			jQuery(".buttonbox ul li a").attr("disabled","disabled");
		}

		//评定分数完成
		function pdFsComplete(){
			jQuery("#divWaiting").hide();
			jQuery("#divDisable").hide();
			jQuery("#div_rs").show();
			jQuery("#main_box").show();
			jQuery(".btn_cx").removeAttr("disabled");
			jQuery(".btn_sz").removeAttr("disabled");
			jQuery(".buttonbox ul li a").removeAttr("disabled");
		}

		// 不及格学生名单导出
		function bjgmdExportConfig() {
			if(checkDcCxTj()){
				customExport("gyglnew_wsjc_bjgmd.do", bjgmdExportData);
			}
		}
			
		
			
		// 导出方法
		function bjgmdExportData() {
			setSearchTj();//设置高级查询条件
			var url = "gyglnew_fscx_ajax.do?method=bjgmdExportData&dcclbh=" + "gyglnew_wsjc_bjgmd.do";//dcclbh,导出功能编号
			url = addSuperSearchParams(url);//设置高级查询参数
			jQuery("form").eq(0).attr("action", url);
			jQuery("form").eq(0).submit();
		}


	</script>
	</head>
	<body >

		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>


		<html:form action="/gyglnew_fslr" method="post">

			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- 隐藏域 -->
			<input type="hidden" name="xmdm" id="xmdm" value="${xmdm }" />
			<!-- 多功能操作区 -->
			<div class="toolbox" id="dgncz">
				<!-- 按钮 -->	
				<div class="buttonbox">
					<ul>
						<li>
							<a href="#" onclick="showView();return false;" class="btn_ck">查看</a>
						</li>
						<logic:notEqual name="userType" value="stu">
							<logic:equal name="writeAble" value="yes">
								<li>
									<a href="#" onclick="showModi();return false;" class="btn_xg">修改</a>
								</li>
								<li>
									<a href="#" onclick="deleteFsxc();return false;" class="btn_sc">删除</a>
								</li>
								
								<%--<li><a href="#" class="btn_qx" onclick="choiceFields();return false;">导出设置</a></li>
								--%>
							</logic:equal>
								<li><a href="#" class="btn_dc" onclick="wsfcxExportConfig();return false;">导出</a></li>
							<logic:equal name="xxdm" value="10338">
								<li><a href="#" class="btn_dc" onclick="pdFsDj();return false;">评定等级</a></li>
							</logic:equal>
								
	<%--						录入方式为分数时可以导出。60分为不及格--%>
	
	<%--							功能已完成，未测试。V513版本隐藏--%>
							<logic:equal name="sfsdj" value="0">
								<li><a href="#" class="btn_dc" onclick="bjgmdExportConfig();return false;"
								title="低于60分的学生名单。"
								>不及格名单导出</a></li>
							</logic:equal>
							<logic:equal name="xxdm" value="10351">
								<li><a href="#" class="btn_dc" onclick="wsfTjDc();return false;">寝室卫生检查分统计</a></li>
							</logic:equal>
							<logic:equal name="xxdm" value="33333">
								<li><a href="#" class="btn_dc" onclick="zTjDc();return false;">周情况卫生分导出</a></li>
							</logic:equal>
							<!-- 徐州医药高等职业学校-->
							<logic:equal name="xxdm" value="70002">
								<li><a href="#" class="btn_dc" onclick="gywsfDc();return false;">公寓月卫生分统计导出</a></li>
							</logic:equal>
						</logic:notEqual>					
					</ul>
				</div>
				
				<!-- 过滤条件 -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
			</div>
			<!-- 多功能操作区 end-->

			<!-- 内容显示区开始 -->
			<div class="main_box" id="main_box">
				<div class="mid_box">
					<div class="title">
						<p>
							<!-- 查询得到的数据量显示区域 -->
						</p>
					</div>
				</div>
				<h3 class="datetitle_01">
					<span> 查询结果&nbsp;&nbsp; <logic:notEmpty name="rsList">
							<font color="blue">提示：单击表头可以排序；双击记录可查看详细信息</font>
						</logic:notEmpty> </span>
				</h3>
				<div id="div_rs"
					style="width:100%;height:380px;overflow-x:auto;overflow-y:hidden;">
				</div>

				<!--分页显示-->
				<jsp:include flush="true"
					page="/sjcz/turnpage.jsp?form=fscxForm"></jsp:include>
<%--				<script type="text/javascript">--%>
<%--						$('choose').className="hide";--%>
<%--				</script>--%>
				<!--分页显示-->
			</div>
			<!-- 内容显示区 end-->
			
			
			<div id="div_detail" style="display:none">
			</div>		
				
			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>
