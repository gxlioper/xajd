<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：yyp -->
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
		<script type='text/javascript' src='dwr/interface/exportData.js'></script>
		<script language="javascript" defer="defer">
		//初始化
		function onShow(){ 
			searchRs();
		}
		
		function setDivHeight(){
			if($("table_rs")){
				jQuery("#div_rs").height(jQuery("#table_rs").height()+20);	
			}
		}
		
		function searchRs(){
			var url = "qgzx_cjffsq_ajax.do?method=cjxxCx";
			var ie = "ie";
			var otherValue = [ie];
			searchRsByAjax(url,otherValue);
		    setTimeout("setDivHeight()","1000");
		}
		
		//修改或者查看
		function showModi(type){
			var isGly = jQuery("#isGly").val();
			var len=jQuery("[name=div_pkValue]:checked").length;
			var xxdm = jQuery("#xxdm").val();
			if("update"==type){
				if(($("kssj") && $("kssj").value=="") || ($("jssj") && $("jssj").value=="")){
					alertInfo("未设定发放时间，请确认！",function(tag){
						if(tag=="ok"){
							return false;
						}
					});
					return false;
				}
				var url="qgzx_cjffsq.do?method=cjxxFf";
				if(len==1){
					var shzt = jQuery("[name=div_pkValue]:checked").parents("tr").find("td").last().text();
					if("未提交"!=shzt&&"已退回"!=shzt){
						alertInfo("只有未提交和已退回的记录才能修改！",function(tag){
							if(tag=="ok"){
								return false;
							}
						});
						return false;
					}
					var pkValue=jQuery("[name=div_pkValue]:checked").val();
					url+="&pkValue="+pkValue;
				}
				showDialog('酬金发放', 1000, 525, url);
			}else if("view"==type){
				if(len==1){
					var pkValue=jQuery("[name=div_pkValue]:checked").val();
					var ztArray = jQuery("[name=div_pkValue]:checked");
					var sqid = jQuery(ztArray[0]).parent().parent().find("td").eq(2).text();
					var url="qgzx_cjffsq.do?method=cjxxCk&shck=1&sqid="+sqid;
					url+="&pkValue="+pkValue;
					showDialog('', 900, 525, url);
				}else{
					alertInfo("请勾选一条需要查看的数据！",function(tag){
						if(tag=="ok"){
							return false;
						}
					});
					return false;
				}
			}
		}
		//撤销提交
		function tjcx() {
			var len=jQuery("[name=div_pkValue]:checked").length;
			if(len==0){
				showAlertDivLayer("请选择您要撤销的记录！");
				return false;
			}
			if(len>1){
				showAlertDivLayer("请选择一条您要撤销的记录！");
				return false;
			}
			var array = jQuery("[name=div_pkValue]:checked");
			var str = "";
			for (var i=0;i<array.length;i++) {
				var pkValue = jQuery(array[i]).parent().parent().find("td").eq(0).find("input[type='checkbox']").val();
				str += pkValue+"!!splitOne!!";
			}
			var url = "qgzx_cjffsq_ajax.do?method=cxtj";
			showConfirmDivLayer("您确定撤销选择的记录吗？", {
				"okFun" : function() {
					jQuery.post(url, {
						pkValue:str
					}, function(result) {
						showAlertDivLayer(result.message);;
						searchRs();
					}, 'json');
				}
			});
		}

		//提交（删除）
		function czCjff(type){
			if(($("kssj") && $("kssj").value=="") || ($("jssj") && $("jssj").value=="")){
				alertInfo("未设定发放时间，请确认！");
				return false;
			}
			//不允许重复提交
			var isttj=false;
			var ztArray = jQuery("[name=div_pkValue]:checked");
			for(var i=0;i<ztArray.length;i++){
				var zt = jQuery(ztArray[i]).parent().parent().find("td").last().text();
				if(zt != "未提交"&&zt != "已退回"){
					isttj=true;
					break;
				}
			}
			if(isttj){
				alertInfo("请选择未提交或已退回的记录！");
				return false;
			}

			var len=jQuery("[name=div_pkValue]:checked").length;
			var message = "删除";
			if(type=="tj"){
				message = "提交";
				if(len>1){
					alertInfo("请选择一条您要提交的记录！");
					return false;
				}
				var shzt=jQuery(ztArray[0]).parent().parent().find("td").last().text();
				
				if ("已退回"!=shzt && "0" == jQuery("#sqkg").val()) {
					showAlertDivLayer("当前申请已关闭，请与管理员联系！");
					return false;
				}
				
			}
			if(len>=1){
				confirmInfo("是否确定"+message+"勾选的记录？",function(tag){
					if(tag=="ok"){
						var array = jQuery("[name=div_pkValue]:checked");
						var str = "";
						for (var i=0;i<array.length;i++) {
							var pkValue = jQuery(array[i]).parent().parent().find("td").eq(0).find("input[type='checkbox']").val();
							str += pkValue+"!!splitOne!!";
						}
						var parameter={};
						var url="qgzx_cjffsq_ajax.do?method=czCjffxx";
						url+="&doType="+type;
						parameter["pkValue"]=str;
						flg = true;
						//青岛酒店管理个性化
						if(jQuery("#xxdm").val() == '13011'){
							jQuery.ajaxSetup({async:false});								
								jQuery.post("qgzx_cjffsq_ajax.do?method=checkSfktj",parameter,function(data){
									if(data != ""){
										flg = false;
										showAlertDivLayer(data+"岗位中有金额未录入，请录入后提交");
									}
									
								},'text');
							jQuery.ajaxSetup({async:true});
							if(!flg){
								return false;
							}
						}
													
						jQuery.ajaxSetup({async:false});	
						/* jQuery.post("qgzx_cjffsq_ajax.do?method=isHaveFfxx",parameter,function(result){
									if(result.message=="false"){ */
										jQuery.post(url,	parameter,function(result){
											alertInfo(result);
											searchRs();
										});
							/* 		}else{
										alertInfo("存在已经发放过的学生，请检查!");
									}
								},"json"
							); */
						
						jQuery.ajaxSetup({async:true});
					}
				});
			}else{
				alertInfo("请勾选需要"+message+"的数据！",function(tag){
					if(tag=="ok"){
						return false;
					}
				});
			}
		}
		//检查是否过期，当前时间是否过了酬金发放日
		function aotuChecksfPass(){
				jQuery.ajaxSetup({async:false});	
				jQuery.post("qgzx_cjffsq_ajax.do?method=aotoTjCjffxx",{},'');
				jQuery.ajaxSetup({async:true});
		}
		
		function splcInfo(){
			var ztArray = jQuery("[name=div_pkValue]:checked");
			jQuery(ztArray[0]).parent().parent().eq(2).last().text();
			
			var sqid = jQuery(ztArray[0]).parent().parent().find("td").eq(2).text();
			var splc = jQuery(ztArray[0]).parent().parent().find("td").eq(3).text();
			if (1!=ztArray.length){
				showAlertDivLayer("请选择一条流程跟踪记录！");
			} else {		
				showDialog("审批流程跟踪",480,380,'comm_spl.do?method=lcgz&sqid='+sqid+"&splc="+splc);
			}
		}
		
		
		jQuery(function(){
			onShow();
			var dqsj=jQuery("#dqsj").val();
			var kssj=jQuery("#kssj").val();
			var jssj=jQuery("#jssj").val();
			if(jQuery("#xxdm").val()!="12309"){
				if(parseInt(dqsj,10)<parseInt(kssj,10)||parseInt(dqsj,10)>parseInt(jssj,10)){
					jQuery("li[id=cjffId]").hide();
					jQuery("li[id=czId]").hide();
					jQuery("li[id=cx]").hide();
					jQuery("li[id=czsc]").hide();
					jQuery("#div_help").show();
					jQuery("#helpshow1").show();
				}
			}
			if("0"==jQuery("#sqkg").val()){
				jQuery("#div_help").show();
				jQuery("#helpshow2").show();
			}
			//aotuChecksfPass();
		});

        function toImportData(drmkdm){
            if(drmkdm == null || drmkdm == ""){
                alert("导入模块代码不能为空。");
                return false;
            }
            var url='qgzx_cjffsq.do?method=gzsqImport';
            url+='&drmkdm='+drmkdm;
            showDialog('导入',700,500,url);
            return false;
        }
        //dcglbh,导出功能编号
        var DCGLBH = "qgzx_cjfgfdc";

        //自定义导出 功能
        function exportConfig() {
            //DCCLBH导出功能编号,执行导出函数
            customExport(DCGLBH, exprotData);
        }

        //导出方法
        function exprotData() {
            setSearchTj();//设置高级查询条件
            var url = "qgzx_cjffsq_ajax.do?method=exportData&dcglbh=" + DCGLBH;//dcglbh,导出功能编号
            url = addSuperSearchParams(url);//设置高级查询参数
            jQuery("form").eq(0).attr("action", url);
            jQuery("form").eq(0).submit();
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
		<!-- 提示信息 -->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>提示：</span>
			</h3>
			<p>
				<span id="helpshow1"  style="display: none">
					酬金发放时间截止后<font color="blue">不能再次修改</font>酬金信息 </br >
				</span>
				<span id="helpshow2"  style="display: none">
					当前未开放申请，只能提交【已退回】的记录！
				</span>
			</p>
			<a class="close" title="隐藏" onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- 提示信息 end-->

		<html:form action="/qgzx_cjffsq" method="post">

			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- 隐藏域 -->
			<input type="hidden" name="xmdm" id="xmdm" value="${xmdm }" />
			<input type="hidden" name="isGly" id="isGly" value="${rs.isGly }"/>
			<input type="hidden" name="kssj" id="kssj" value="${rs.kssj }" />
			<input type="hidden" name="jssj" id="jssj" value="${rs.jssj }" />
			<input type="hidden" name="dqsj" id="dqsj" value="${rs.dqsj }" />
			<input type="hidden" name="sqkg" id="sqkg" value="${sqkg }" />
			<!-- 多功能操作区 -->
			<div class="toolbox" id="dgncz">
				<!-- 按钮 -->
				
				<div class="buttonbox">
					<ul>
							
							<logic:equal name="writeAble" value="yes">
							<logic:equal name="sfkfsq" value="1">
								<li id="cjffId">
									<a href="#" onclick="showModi('update');return false;" class="btn_zj">酬金发放</a>
								</li>
								<li id="czId">
									<a href="#" onclick="czCjff('tj');return false;" class="btn_shuc">提交</a>
								</li>
								<li id="cx">
									<a href="#" onclick="tjcx();return false;" class="btn_sr">撤销</a>
								</li>
								<li id="czsc">
									<a href="#" onclick="czCjff('del');return false;" class="btn_sc">删除</a>
								</li>
								<li><a href="#" onclick="toImportData('IMPORT_XAJD_GZSQ');return false;" class="btn_dr">导入</a></li>
							</logic:equal>
							</logic:equal>
						<li><a href="javascript:void(0);" onclick="splcInfo();return false;" 
							   title="选中一条记录，点击该按钮可以查看审核流程。"
							   class="btn_cs">流程跟踪</a>
						</li>
						<li>
							<a href="#" onclick="showModi('view');return false;" class="btn_ck">查看明细</a>
						</li>
						<li>
							<a href="#" onclick="exportConfig();return false;" class="btn_dc">导出</a>
						</li>
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
					<span> 查询结果&nbsp;&nbsp;</span>
					<logic:equal value="10264" name="xxdm">
						<%-- <font color="blue">总经费：${jftx.hbzje }&nbsp;&nbsp;&nbsp;&nbsp;已使用：${jftx.syje}&nbsp;&nbsp;&nbsp;&nbsp;剩余：${jftx.yffje }</font> --%>
					</logic:equal>
				</h3>
				<div id="div_rs"
					style="width:100%;overflow-x:auto;overflow-y:hidden;">
				</div>

				<!--分页显示-->
				<jsp:include flush="true"
					page="/sjcz/turnpage.jsp?form=qgzxCjffsqForm"></jsp:include>
			</div>
			<div id="div_detail" style="display:none">
			</div>		
				
			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>