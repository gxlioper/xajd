<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 --> 
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%--<%@ include file="/syscommon/pagehead_V4.ini"%>
	    --%>
	    <%@ include file="/syscommon/head.ini"%>
	    <script language="javascript" src="js/check.js"></script>
	    <script language="javascript" src="js/comm/commFunction.js"></script>
	    
	    <script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type='text/javascript' src="js/uicomm.js"></script>
		<script type='text/javascript' src="js/String.js"></script>
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
			
			var url = "szdw_tjcx_szbb.do?method=statSzry";
			var ie = "ie";
			
			var otherValue = [ie];

			$("divWaiting").style.display="";
			$("divDisable").style.display="";
			
			searchRsByAjax(url,otherValue);

			$("divWaiting").style.display="none";
			$("divDisable").style.display="none";
			
			jQuery.ajaxSetup({async:true});
		}


		function showSzryInfo(url){

			setSearchTj();
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
			
			var input_mhcx=input_mhcx;
			var mhcx_lx=mhcx_lx;
			var searchTj=searchTj.join("!!@@!!");
			var searchTjz=searchTjz.join("!!@@!!");
			var searchLx=searchLx.join("!!@@!!");
			url+='&input_mhcx='+input_mhcx;
			url+='&mhcx_lx='+mhcx_lx;
			url+='&searchTj='+searchTj;
			url+='&searchTjz='+searchTjz;
			url+='&searchLx='+searchLx;
	
			//showTopWin(url,700,550);
			showDialog("查看详细情况", 700, 480, url);
		}

		
		//导出思政人员统计
		function expSzryStat(){
			var url="szdw_tjcx_szbb.do?method=export";
			document.forms[0].action=url;
			document.forms[0].target="_blank";
			document.forms[0].submit();
			document.forms[0].target = "_self";	
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
		</div>
		<!-- 标题 end-->
		
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
								<a href="#" class="btn_dc" onclick="expSzryStat();return false;">
									导出
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
				<div id="div_rs" style="height:380px;overflow-x:auto;overflow-y:auto;">
				</div>
				
				<!--分页显示-->
				<div style="clear:both; display: none">
					<input type="hidden" id="ajax_max_record" value=""/>
					<input type="hidden" id="ajax_max_page" value=""/>
					<input type="hidden" id="ajax_max_current" value=""/>
					<input type="hidden" id="ajax_page_size" value=""/>
					<input type="hidden" id="ajax_page_maxpage" value=""/>
				</div>
				<!--分页显示-->
			</div>
			<!-- 内容显示区开始 end-->
			
			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>