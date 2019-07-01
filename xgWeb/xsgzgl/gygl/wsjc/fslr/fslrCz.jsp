<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type='text/javascript' src="js/uicomm.js"></script>
		<script type='text/javascript' src="js/String.js"></script>
		
		<script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" defer="defer">		
		//查询结果集
		function searchRs(){
		
				var pkValue=jQuery("#pkValue").val();		
				
				var url = "gyglnew_fslr_ajax.do?method=fslrCz";
				
				url+="&pkValue="+pkValue;

				var ie = "10.0";

				var otherValue = [ie];

				searchRsByAjax(url,otherValue);
				
				setTimeout("setDivHeight()","1000")
		}
		
		function skipNext(obj){
			
			var trID = jQuery(obj).parents('tr').attr('id');
			var j=Number(trID.replace('tr',''))+1;
			var newID = "tr"+j;
			if (jQuery('#'+newID)){
				jQuery('#'+newID).click();
				jQuery('#'+newID).find('#fz_'+j).focus();
			}
		}
		
		function setDivHeight(){
			if($("table_rs")){
				jQuery("#div_rs").height(jQuery("#table_rs").height()+20);	
			}
		}
		
		function checkIn(obj){
			var val=jQuery(obj).val();
			if(val!=null && val!=""){
					jQuery(obj).parent().parent().find("td").eq(0).find("input[type='checkbox']").attr("checked", true);
			}
			if(val==""){
				jQuery(obj).parent().parent().find("td").eq(0).find("input[type='checkbox']").attr("checked", false);
			}
		}
		function checkIs(obj){
			var val=jQuery(obj).val();
			var reg=/^(?:0|[1-9][0-9]?|[1-9][0-9]+\.\d{0,2}|[0-9]+\.\d{0,2}|100)$/;
			if(val!=null && val!=""){
				if(reg.test(val)){
					jQuery(obj).parent().parent().find("td").eq(0).find("input[type='checkbox']").attr("checked", true);
				}else{
					jQuery(obj).val("");
					showAlertDivLayer("请输入0-100的数字！",{},{"clkFun":function(){
						obj.focus();
					}});
					return;
				}	
			}
			if(val==""){
				jQuery(obj).parent().parent().find("td").eq(0).find("input[type='checkbox']").attr("checked", false);
			}
		}
		function checkBz(obj){
			var val=jQuery(obj).val();
			if(val!=null && val!=""){
				jQuery(obj).parent().parent().find("td").eq(0).find("input[type='checkbox']").attr("checked", true);
			}
		}
		
		function checkIss(obj){
			var sfsdj= jQuery("#sfsdj").val();
			var val;
			if(sfsdj=="0"){
				val=jQuery(obj).parent().parent().find("td").eq(8).find("input[type='text']").val();
			}else{
				val=jQuery(obj).parent().parent().find("td").eq(8).find("select").val();
			}
			var checkVal=jQuery(obj).parent().parent().find("td").eq(0).find("input[type='checkbox']").prop("checked");
			if(!checkVal){
				jQuery(obj).parent().parent().find("td").eq(8).find("input[type='text']").val(null);
			}
			if(val==""){
				jQuery(obj).parent().parent().find("td").eq(0).find("input[type='checkbox']").attr("checked", false);
				alertInfo("保存时不能为空值");
			}
		}
		
		function save(){
		
			var len=jQuery("[name=div_pkValue]:checked").length;
			if(len>=1){	
				
				var pkValue=jQuery("#pkValue").val();
				var array = jQuery("[name=div_pkValue]:checked");
				var sfsdj = jQuery("#sfsdj").val();
				var bzStr="";
				var str = "";
				var flg = true;
				var kfyjStr = "";
				var byqs = "";
				for (var i=0;i<array.length;i++) {
					var lddm = jQuery(array[i]).val();
					var bz = jQuery(array[i]).parent().parent().find("td input[id^='bz']").val();
					//天津经济贸易个性化需求
					if(jQuery("#xxdm").val() == '1103202'){
						bz = "";
						var bzObj =  jQuery(array[i]).parent().parent().find("input[name='pfbz']:checkbox:checked");
						var bzObjlen = bzObj.length;
						 jQuery(bzObj).each(function(i,o){
							 bz += this.value.split("-")[0];
							 if(i != bzObjlen-1){
								 bz +=",";
							 }
						 })
					}
					bzStr += lddm+"!!@@"+bz+"!!!@@@";
					if(sfsdj=="1"){
						var dj = jQuery(array[i]).parent().parent().find("td").eq(6).find("select").val();
						if(dj == null || dj == ''){
							flg = false;
							showAlertDivLayer('请选择等级！');
							break;
						}
						str += lddm+"!!@@"+dj+"!!!@@@";
					}else{
						var fz = jQuery(array[i]).parent().parent().find("td input[id^='fz']").val();
						if(fz == null || fz == ''){
							flg = false;
							showAlertDivLayer("请填写分值！");
							break;	
						}
						str += lddm+"!!@@"+fz+"!!!@@@";
						if(jQuery("#xxdm").val() == '11647'){
							byqs += lddm+"!!@@"+jQuery(array[i]).parent().parent().find("[name='byqs']").val()+"!!!@@@";
						}
						if(jQuery("#xxdm").val() == '33333'){
							var kfyj = jQuery(array[i]).parent().parent().find("td input[id^='kfyj']").val();
							kfyjStr += lddm+"_"+kfyj+'!!';
						}
					}
				}
				if(!flg){
					return false;
				}
				var params = 
					{
						sfsdj:sfsdj,
						pkValue:pkValue
					};
				var urlEncoeded = jQuery.param(params);

				if(jQuery("#xxdm").val() == '33333'){
					showDialog("保存卫生分", 600, 350, 'gyglnew_fslr.do?method=fslrCk&' + urlEncoeded+'&bzStr='+bzStr+'&str='+str+'&kfyjStr='+kfyjStr.substr(0,kfyjStr.length-2));
				}else if(jQuery("#xxdm").val() == '11647'){
					showDialog("保存卫生分", 600, 350, 'gyglnew_fslr.do?method=fslrCk&' + urlEncoeded+'&bzStr='+bzStr+'&str='+str+'&byqs='+byqs);
				}else{
					showDialog("保存卫生分", 600, 350, 'gyglnew_fslr.do?method=fslrCk&' + urlEncoeded+'&bzStr='+bzStr+'&str='+str);
				}
				//showTopWin(url,600,450);
			}else{
				alertInfo("请勾选需要保存的记录！");
				return false;
			}
		}

		//扣分明细
		function kfmx(obj){
			var jcrcid = jQuery("#pkValue").val();
			var lddmm = jQuery(obj).parents("tr").eq(0).find("td").eq(0).find("input:first").val();
			var lddmmarr = lddmm.split("!");
			var lddm = lddmmarr[0];
			var qsh = jQuery(obj).parents("tr").eq(0).find("td").eq(3).text();
			url = 'gyglnew_fslr.do?method=fslrDetail&jcrcid='+jcrcid+'&lddm='+lddm+'&qsh='+qsh;
			showDialog('明细', 800, 550, url);
		}

		function showMessage(){
			jQuery(".datetitle_01").find("span").append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color='blue'>请点击扣分依据录入分数</font>");
		}

		jQuery(function(){
			searchRs();
			if(jQuery("#xxdm").val() == '33333' && jQuery("#sfsdj").val() == "0"){
				showMessage();
			}
		})
		
		//选择备注
		function selectBz(obj){
			var fzObj = jQuery(obj).parent().parent().find("td input[id^='fz']");
			var zf = parseFloat(jQuery(fzObj).val()) || 0;
			if(obj.checked){
				zf = zf-parseFloat(obj.value.split("-")[1]);
			}else{
				zf = zf+parseFloat(obj.value.split("-")[1]);
			}
			jQuery(fzObj).val(zf);
		}
		//全选或反选
		function selectOrCancelAll(obj){
			var allCheckbox = jQuery(obj).parent().find("input[name='pfbz']");
			jQuery(allCheckbox).each(function(i,o){
				if(o.checked){
					jQuery(o).attr("checked",false);
				}else{
					jQuery(o).attr("checked",true);
				}
				selectBz(o);
			})
		}
	</script>
	</head>
	<body >

		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>公寓管理-卫生检查-卫生分录入 </a>
			</p>
		</div>


		<html:form action="/gyglnew_fslr" method="post">

			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- 隐藏域 -->
			<input type="hidden" name="xmdm" id="xmdm" value="${xmdm }" />
			<input type="hidden" name="sfsdj" id="sfsdj" value="${sfsdj }" />
			<input type="hidden" name="pkValue" id="pkValue" value="${pkValue}" />
			<!-- 多功能操作区 -->
			<div class="toolbox" id="dgncz">
				<!-- 按钮 -->
				
				<div class="buttonbox">
					<ul>
						<li><a href="#" onclick="save();return false;" class="btn_ccg">保 存</a></li>
						<li><a href="#" onclick="refreshForm('gyglnew_wsjc_fslr.do');" class="btn_fh">返 回</a></li>
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
					<span> 查询结果&nbsp;&nbsp; <font color="blue">检查日程：${jcrc }</font><logic:notEmpty name="rsList">
							<font color="blue">提示：单击表头可以排序；双击记录可查看详细信息</font>
						</logic:notEmpty> </span>
				</h3>
				<div id="div_rs"
					style="width:100%;height:380px;overflow-x:auto;overflow-y:hidden;">
				</div>

				<!--分页显示-->
				<jsp:include flush="true"
					page="/sjcz/turnpage.jsp?form=fslrForm"></jsp:include>
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