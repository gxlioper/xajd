<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type='text/javascript' src="js/uicomm.js"></script>
		<script type='text/javascript' src="js/String.js"></script>
		
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript">
		//查询方法
		function searchRs(){
			if(yzshgw()){
				var url = "wjcfCfssgl_cfsssh.do?method=cfssshCxSj";

				var ie = "ie";
				var stylePath = "<%=stylePath %>";
				var otherValue = [ie,stylePath];
	 			var xtgwid=jQuery("#xtgwid").val();
	 			if("" != xtgwid){
	 				otherValue=[ie,stylePath,xtgwid];
	 	 		}

				searchRsByAjax(url,otherValue);

				setTimeout("setDivHeight()","1000")
			}else{
				setPage();
			}
		}

		//单个审核
		function ssshDg(){
			var objs = jQuery("input[type=checkbox][name=checkVal]:checked");
			var pkValues="";
			if(objs.length!=1){
				alertError("请选择一条记录！");
				return false;
			}else{
				pkValues+=objs[0].value;
			}
			var xtgwid=jQuery("#xtgwid").val();
			var url="wjcfCfssgl_cfsssh.do?method=cfssshSh&pkValue="+pkValues+"&xtgwid="+xtgwid;
			//showTopWin(url,780,660);
			showDialog("", 800, 500, url);
		}

		//批量审核
		function ssshPl(){
			var checkBoxArr = document.getElementsByName("checkVal");
			var pkValue="";
			var len=0;
			for(var i=0;i<checkBoxArr.length;i++){
				if(checkBoxArr[i].checked==true){
					if(pkValue==""){
						pkValue=checkBoxArr[i].value;
					}else{
						pkValue=pkValue+"@@"+checkBoxArr[i].value;
					}
					len++;
				}
			}
			if(len==0){
				alertError("请勾选希望审核的记录！");
				return false;
				}
			var xtgwid=jQuery("#xtgwid").val();
			var url="wjcfCfssgl_cfsssh.do?method=cfssshPlsh&pkValue="+pkValue+"&xtgwid="+xtgwid;
			//showTopWin(url,800,600);
			showDialog("", 800, 210, url);
		}

		//初始化页面
		function setPage(){

			var xtgwid=jQuery("#xtgwid").val();

			if (xtgwid == ""){
				jzSpgw();
			} else {
				searchRs();
			}
		}

		//加载审批岗位
		function jzSpgw(){
			var url="wjcfCfssgl_cfsssh.do?method=spgwCx";


			jQuery("#div_spgw").load(url,parameter,function(){
				var qhshgw=jQuery("#qhshgw");
				var len=jQuery("[name=spgws]").length;
				if(len==1 || qhshgw.length==0){
					$("xtgwid").value=jQuery("[name=spgws]:checked").val();
					searchRs();
					
				}else{
					tipsWindown("系统提示","id:div_spgw","300","170","true","","true","id");
				}
			});
			
		}

		//点击确定后查询
		function checkSpgw(){
			jQuery("#xtgwid").val(jQuery("[name=spgws]:checked").val());
			searchRs();
			closeWindown();
		}

		//切换审核岗位
		function qhgw(){
			tipsWindown("系统提示","id:div_spgw","300","170","true","","true","id");
		}

		//申诉提交
		function sstj(){
			var url="wjcfCfssgl_cfsssh.do?method=cfssshTj";
			//showTopWin(url,400,250);
			
			showDialog("", 500, 350, url);
		}
		
		//验证审核岗位
		function yzshgw(){
			if(jQuery("#xtgwid").val()==""){
				return false;
			}
			return true;
		}
		</script>
	</head>
	<body onload="setPage();">
		<html:form action="/wjcfCfssgl_cfsssh.do?method=cfssshCx" method="post">
			<div class="tab_cur" >
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
				<html:hidden property="xtgwid" styleId="xtgwid"/>
			</div>
			
			<!-- 提示信息 end-->	
			<!-- 模块类型 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox" id="dgncz">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>						
						<li><a href="#" onclick="ssshDg();return false;" class="btn_sh"> 审核 </a></li>
						<li><a href="#" onclick="ssshPl();return false;" class="btn_sh"> 批量审核 </a></li>
						<logic:present name="spgwList">
							<logic:notEmpty name="spgwList">
								<logic:iterate id="spgw" name="spgwList" offset="1" length="1">
									<li><a href="#" onclick="jzSpgw();return false;" id="qhshgw" class="btn_sz"> 切换审核岗位</a></li>
								</logic:iterate>
							</logic:notEmpty>
						</logic:present>
						
						<logic:equal name="isZgjyh" value="true">
							<li><a href="#" onclick="sstj();return false;" class="btn_shtg"> 提交 </a></li>
						</logic:equal>
						
					</ul>
				</div>
				<!-- 过滤条件 -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
			<div class="formbox" id="cxjg">
				<h3 class="datetitle_01">
					<span>
						查询结果&nbsp;&nbsp;<font color="red">申诉成立的数据不能再修改。</font>
					</span>
				</h3>
				<div id="div_rs"
					style="width:100%;height:380px;overflow-x:auto;overflow-y:hidden;">
				</div>
				
				<!--分页显示-->
				<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=wjcfCfssglForm"></jsp:include>
				<!--分页显示-->
				
			</div>
			<!-- 审批岗位-->
			<div id="div_spgw" style="display:none"></div>
			<!-- 审批岗位-->
			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
		<div id="tmpdiv1" style="display:none">
				<iframe name='mainFrame' style='height:250px; width:400px; '
					marginwidth='0' marginheight='0' framespacing='0' frameborder='0'
					scrolling='yes'
					src=''></iframe>
		</div>
		<logic:notEmpty name="result">
				<logic:equal value="false" name="result">
					<script language="javascript">
					alertInfo("操作失败！");
					</script>
				</logic:equal>
				<logic:equal value="true" name="result">
					<script language="javascript">
					alertInfo("操作成功！");
					</script>
				</logic:equal>
		</logic:notEmpty>
		
	</body>
</html>
