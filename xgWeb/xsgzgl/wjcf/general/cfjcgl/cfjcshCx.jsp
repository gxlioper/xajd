<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script type='text/javascript' src="js/uicomm.js"></script>
		<script type='text/javascript' src="js/String.js"></script>
		
	    <script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript" src="js/commit.js"></script>
		<script language="javascript" defer="defer">
		//初始化
		function onShow(){ 

			var spgw=$("spgw").value;

			if (spgw == ""){
				showSpgw();
			} else {
				searchRs();
			}
		}

		//审批岗位
		function showSpgw(){
			var url="general_wjcf_cfjcsh_ajax.do?method=showShgwDiv";

			//其他数据
		 	var parameter = {
			};
		  	
			jQuery("#div_spgw").load(url,parameter,function(){
			
				var len=jQuery("[name=spgws]").length;
		
				if(len==1){
					$("spgw").value=jQuery("[name=spgws]:checked").val();
					searchRs();
					
				}else{
					tipsWindown("系统提示","id:div_spgw","300","170","true","","true","id");
				}
			});
		}

		//点击确定后查询
		function checkSpgw(){
			$("spgw").value=jQuery("[name=spgws]:checked").val();
			searchRs();
			closeWindown();
		}

		//执行查询操作
		function searchRs(){
			jQuery.ajaxSetup({async:false});
			var v4Path = stylePath;//v4样式路径
			var url = "general_wjcf_cfjcsh_ajax.do?method=searchWjcfResult";
			var ie = "ie";
			var spgw=$("spgw").value;
			var otherValue = [ie,v4Path,spgw];
			searchRsByAjax(url,otherValue);
			setTimeout("setDivHeight()","1000")
			
			jQuery.ajaxSetup({async:true});
		}


		//审核操作
		function modCfjcsh(){
			var objs = jQuery("input[type=checkbox][name=primarykey_checkVal]:checked");
			var cfId="";
			if(objs.length!=1){
				alertError("请选择一条记录！");
				return false;
			}else{
				cfId+=objs[0].value;
			}
			var spgw=$("spgw").value;
			var url="general_wjcf_cfjcsh_ajax.do?method=cfjcSh&cfId="+cfId+"&spgw="+spgw;
			//showTopWin(url,'780','660');
			showDialog("", 760, 525, url);
		}

		//批量解除审核
		function plCfjcsh(){
			var spgw=$("spgw").value;
			var objs = jQuery("input[type=checkbox][name=primarykey_checkVal]:checked");
			var RowsStr="";
			if(objs.length>0){
				for (i=0; i<objs.length; i++){
			     RowsStr+=objs[i].value+",";
				}
			}
			if(""==RowsStr){
				alertError("请勾选希望审核的记录！");
				return false;
				}
			var url="general_wjcf_cfjcsh_ajax.do?method=plJcsh&cfId="+RowsStr+"&spgw="+spgw;
			//showTopWin(url,'600','400');
			showDialog("", 760, 200, url);
			}

		//提交进入正式库
		function tijiao(){
			var url="general_wjcf_cfjcsh_ajax.do?method=tongJi";
			//showTopWin(url,'600','400');
			showDialog("", 760, 525, url);
		}
		
		
		jQuery(function(){
			onShow();
		})
		</script>
	</head>
	<body  ondrag="return false">
	
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
			
		</div>
		<!-- 标题 end-->
		
		<html:form action="/general_wjcf_cfjcsh_ajax" method="post">
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<html:hidden property="spgw" styleId="spgw"/>
			<input type="hidden" name="message" id="message" value="${message }">		
			<!-- 多功能操作区 -->
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<li>
							<a href="#" onclick="modCfjcsh();return false;" class="btn_sh">
								审核
							</a>
						</li>
						<li>
							<a href="#" onclick="plCfjcsh();return false;" class="btn_sh">
								批量审核
							</a>
						</li>
						<logic:equal name ="isZgjyh" value="true">
							<li>
								<a href="#" onclick="tijiao();return false;" class="btn_shtg">
									提交
								</a>
							</li>
						</logic:equal>
						<logic:present name="spgwList">
							<logic:notEmpty name="spgwList">
								<logic:iterate id="spgw" name="spgwList" offset="1" length="1">
									<li><a href="#" onclick="showSpgw();return false;" class="btn_sz"> 切换审核岗位</a></li>
								</logic:iterate>
							</logic:notEmpty>
						</logic:present>
					</ul>
				</div>
				<!-- 按钮 end-->
				
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
			
			<!-- 内容显示区开始 -->
			<div class="main_box">
			<h3 class="datetitle_01">
					<span> 查询结果</span>
				</h3>
			
				<div id="div_rs"
					style="width:100%;height:380px;overflow-x:auto;overflow-y:hidden;">
				</div>

				<!--分页显示-->
				<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=wjcfGeneralForm"></jsp:include>
				<!--分页显示-->
			</div>
			<!-- 内容显示区 end-->
			<div id="div_spgw" style="display:none"></div>
			
			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>