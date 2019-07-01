<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：yyp -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>		
		<script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
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
		function searchRs(){
			var url = "qgzx_cxtj_ajax.do?method=cjffCx";
			var ie = "ie";
			var otherValue = [ie];
			searchRsByAjax(url,otherValue);
		    setTimeout("setDivHeight()","1000")
		}

		//查看
		function showView(){
			var len=jQuery("[name=div_pkValue]:checked").length;
			if(len==1){	
				var pkValue=jQuery("[name=div_pkValue]:checked").val();	
				var url="qgzx_cxtj.do?method=cjffCk";		
				url+="&pkValue="+pkValue;
				//showTopWin(url,800,600);
				showDialog("", 760, 525, url);
			}else{
				alertInfo("请勾选一条记录进行查看！");
				return false;
			}
		}
		
		
		function cjffcxExportConfig() {
			if(jQuery("#xxdm").val()=="13855"){
				var url = "qgzx_cxtj_ajax.do?method=cjffCxExportDataJn";
				setSearchTj();
				url = addSuperSearchParams(url);
				document.forms[0].action = url;
				document.forms[0].submit();
				}
			else if("12874"==jQuery("#xxdm").val()){
				//嘉兴职业技术学院勤工助学工作统计及补贴发放清单
				if("0"==jQuery("#rsSize").val()){
					showAlertDivLayer("没有数据可以导出！");
					return false;
					}
				var url = "qgzx_cxtj_ajax.do?method=cjffCxExportDataJzy";
				setSearchTj();
				url = addSuperSearchParams(url);
				document.forms[0].action = url;
				document.forms[0].submit();
				
			}
			else{
			customExport("cjffCx_qgzx_cxtj_ajax.do", cjffcxExportData);
			}
		}
			
		
				
		// 导出方法
		function cjffcxExportData() {
			setSearchTj();//设置高级查询条件
			var url = "qgzx_cxtj_ajax.do?method=cjffCxExportData&dcclbh=" + "cjffCx_qgzx_cxtj_ajax.do";//dcclbh,导出功能编号
			url = addSuperSearchParams(url);//设置高级查询参数
			jQuery("form").eq(0).attr("action", url);
			jQuery("form").eq(0).submit();
		}
				
		// 北京林业大学 下载申报表
		function cjffcxExportData_10022() {
			var ndNum = jQuery("a[name=a_name_nd]").length;
			if(ndNum != 1){
				showAlertDivLayer("请选择一个发放年度！");
				return false;
			}
			var yfs = jQuery("a[name=a_name_yf]");
			var yfNum = yfs.length;
			if(yfNum > 0){
				if(yfNum > 1){
					var yfNewArr = new Array();
					for(var i = 0; i < yfNum; i++){
						// 先替换a_id_得到"01","02"..."10","11","12"，再替换后得到"1","2"..."10","11","12"
						yfNewArr[i] = yfs.eq(i).attr("id").replace("a_id_","").replace(/0(\d)/g, "$1");
					}
					var count = 0; // 次数
					var yfNewStr = yfNewArr.join("!!@@!!") + "!!@@!!"; // 所有月份组成的字符串
					for(var i = 0; i < yfNewArr.length; i++){
						// 遍历时，把当前的月份加1，然后在【所有月份组成的字符串】中查找，没找到的话，次数加1
						if(yfNewStr.indexOf((parseInt(yfNewArr[i]) + 1) + "!!@@!!") < 0){
							count++;
						}
					}
					if(count != 1){
						showAlertDivLayer("请选择连续的发放月份！");
						return false;
					}
				}
			}else{
				showAlertDivLayer("请选择至少一个发放月份！");
				return false;
			}
			setSearchTj();//设置高级查询条件
			var url = "qgzx_cxtj.do?method=getCjffSbbDclist";
			url = addSuperSearchParams(url);//设置高级查询参数
			jQuery("form").eq(0).attr("action", url);
			jQuery("form").eq(0).submit();
		}

		// 传媒个性化导出方法
		function cjffcxExportConfigCm() {
			
			var url = "qgzx_cxtj_ajax.do?method=cjffCxExportDataCm";

			var njs = new Array();//年级
			var xydms = new Array();//学院
			var zydms = new Array();//专业
			var bjdms = new Array();//班级
			var ffyf = new Array();//发放月份
			var ffnd = new Array();//发放年度
			
			
			//年级
		    n=0;
			
			jQuery("a[name=a_name_nj]").each(function(){
				var id = jQuery(this).attr("id");
				var nj = id.replace("a_id_","");
				if(nj !=null && nj!=""){
					njs[n] = nj;
					n++;
				}
			});
			
			if(njs != null && njs.length>0){
				url+= "&array_njs="+njs.join("!!array!!");
			}
				
			//学院		
			n=0;
			
			jQuery("a[name=a_name_xy]").each(function(){
				var id = jQuery(this).attr("id");
				var xy = id.replace("a_id_","");
				if(xy !=null && xy!=""){
					xydms[n] = xy;
					n++;
				}
			});
			
			if(xydms != null && xydms.length>0){
				url+= "&array_xydms="+xydms.join("!!array!!");
			}
				
			//专业		
			n=0;

			jQuery("a[name=a_name_zy]").each(function(){
				var id = jQuery(this).attr("id");
				var zy = id.replace("a_id_","");
				if(zy !=null && zy!=""){
					zydms[n] = zy;
					n++;
				}
			});
	
			if(zydms != null && zydms.length>0){
				url+= "&array_zydms="+zydms.join("!!array!!");
			}
			
			//班级		
			n=0;
			
			jQuery("a[name=a_name_bj]").each(function(){
				var id = jQuery(this).attr("id");
				var bj = id.replace("a_id_","");
				if(bj !=null && bj!=""){
					bjdms[n] = bj;
					n++;
				}
			});
			
			if(bjdms != null && bjdms.length>0){
				url+= "&array_bjdms="+bjdms.join("!!array!!");
			}


			//年度		
			n=0;
			
			jQuery("a[name=a_name_nd]").each(function(){
				var id = jQuery(this).attr("id");
				var nd = id.replace("a_id_","");
				if(nd !=null && nd!=""){
					ffnd[n] = nd;
					n++;
				}
			});
			
			if(ffnd != null && ffnd.length>0){
				url+= "&array_ffnd="+ffnd.join("!!array!!");
			}

			//月份		
			n=0;
			
			jQuery("a[name=a_name_yf]").each(function(){
				var id = jQuery(this).attr("id");
				var yf = id.replace("a_id_","");
				if(yf !=null && yf!=""){
					ffyf[n] = yf;
					n++;
				}
			});
			
			if(ffyf != null && ffyf.length>0){
				url+= "&array_ffyf="+ffyf.join("!!array!!");
			}

			document.forms[0].action = url;
			document.forms[0].target = "_blank";
			document.forms[0].submit();
			document.forms[0].target = "_self";
		}


		// 新版查看弹出层
		function zxsxxView(xh) {
			showDialog("学生信息查询", 850, 500, "xsxx_xsxxgl.do?method=xsxxglCk&xh=" + xh
					+ "&xs");
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


		<html:form action="/qgzx_cxtj" method="post">

			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="xmdm" id="xmdm" value="${xmdm }" />
			<input type="hidden" name="xxdm" id="xxdm" value="${xxdm }" />
			<!-- 隐藏域 -->
			<!-- 多功能操作区 -->
			<div class="toolbox" id="dgncz">
				<!-- 按钮 -->
				
				<div class="buttonbox">
					<ul>
						<li>
							<a href="#" class="btn_ck" onclick="showView();return false;">查看明细</a>
						</li>
						
						<li><a href="#" onclick="cjffcxExportConfig();return false;" class="btn_dc">导出</a></li>
						
						<%--<li><a href="#" onclick="configureExportData();return false;" class="btn_dc">导出数据</a></li>--%>
						<logic:equal name="xxdm" value="11647">
							<li><a href="#" onclick="cjffcxExportConfigCm();return false;" class="btn_dc">审批汇总导出</a></li>
						</logic:equal>
						<logic:equal name="xxdm" value="10022">
							<li><a href="#" onclick="cjffcxExportData_10022();return false;" class="btn_down">下载申报表</a></li>
						</logic:equal>
					</ul>
				</div>
				
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
					<span> 查询结果&nbsp;&nbsp; </span>
				</h3>
				<div id="div_rs"
					style="width:100%;overflow-x:auto;overflow-y:hidden;">
				</div>

				<!--分页显示-->
				<jsp:include flush="true"
					page="/sjcz/turnpage.jsp?form=qgzxCxtjForm"></jsp:include>
			</div>
			<div id="div_detail" style="display:none">
			</div>		
			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>