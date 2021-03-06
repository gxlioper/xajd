<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	    <script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="js/xsgzgl/pjpy/pjpyComm.js"></script>
		<script language="javascript" defer="defer">
		//初始化
		function onShow(){ 
			//
			searchRs();
		}

		//查询结果集
		function searchRs(){
			
			jQuery.ajaxSetup({async:false});
			
			var url = "general_wdpj_jgcx_ajax.do?method=searchWdpjBcpj";
			var ie = "ie";
			
			var otherValue = [ie];

			$("divWaiting").style.display="";
			$("divDisable").style.display="";
			
			searchRsByAjax(url,otherValue);

			$("divWaiting").style.display="none";
			$("divDisable").style.display="none";
			
			jQuery.ajaxSetup({async:true});
		}
		
		function showWdpjBcpj(){
			var len = jQuery("input[name=div_pkValue]:checked").length;
			if(len==1){
				var pk=jQuery("input[name=div_pkValue]:checked").eq(0).val()
				var url = "general_pjpy.do?method=bcpjDetail";
				url+="&pkValue="+pk;
				showTopWin(url,"800","600");
			}else{
				alertInfo("请勾选一条需要查看的数据！");
			}
		}
		
		//打印报表
		function printDjbg(){
				
				var num = jQuery("input[name=div_pkValue]:checked").length;
				var jxjName =  new Array();
				var xh = new Array();
				
				if(num == 0){
					alertError("请<font color='blue'>勾选</font>您希望打印的记录");
					return false;
				}
				
				jQuery("input[name=div_pkValue]:checked").each(function(i){
					jxjName[i] = jQuery(this).parents("tr").children("td").eq(7).html();
					xh[i] = jQuery(this).parents("tr").children("td").eq(1).html();
				})
					
				var flag = true;
				
				for(var i=0;i<jxjName.length;i++){
					if(i!=0){
						if(jxjName[i] != jxjName[i-1]){
							flag = false;
							break;
						}
					}
				}
				
				if(!flag){
					alertError("您共勾选了"+num+"条记录，但他们的评奖项目并不一致，无法批量打印，请您确认^_^||");
					return false;
				}
				
				var url = "general_pjpy_djbg.do?method=printDjbg";
					url+= "&array_print_xh=" + xh.join("!!array!!");
					url+= "&str_xmmc=" + jxjName[0];	
					url+= "&str_lx=bcpj";
					
				document.forms[0].action = url;
				document.forms[0].target = "_blank";
				document.forms[0].submit();
			    document.forms[0].target = "_self";								
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
				<em>您的当前位置：</em><a>评奖评优 - 我的评奖 - 本次评奖结果</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv();">使用帮助</a>
			</p>
		</div>			
		<!-- 标题 end-->
		<!-- 提示信息 START-->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>提示：</span>
			</h3>
			<p>
				<span>
				1.本功能展示的是<font color="blue">本评奖周期</font>的学生申请记录。<br/>
				2.审核状态为<font color="blue">未审核</font>的记录，标记着没有任何一级用户审核过该申请记录。<br/>
				3.审核状态为<font color="blue">审核不通过</font>的记录，标记着有某一级审核为不通过的申请记录。<br/>
				4.审核状态为<font color="blue">审核通过</font>的记录，标记着最后一级用户审核通过的申请记录。<br/>
				5.其他情况为<font color="blue">审核中</font>的申请记录。<br/>
				6.<font color="blue">勾选一条</font>记录，点击<font color="blue">查看详细</font>，可以查看该申请记录的详细信息。<br/>
				7.<font color="blue">勾选一条</font>记录，点击<font color="blue">打印登记表</font>，可以输出相应的登记表。<br/>
				8.登记表打印如果不符合学校实际情况，请联系本公司客服人员。<br/>
				</span>
			</p>
			<a class="close" title="隐藏"  onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- 提示信息 end-->
		
		<html:form action="/general_pjpy" method="post">
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<!-- 多功能操作区 -->
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<li>
							<a href="#" onclick="goPjpyWdpj();return false;" class="btn_fh">
								返回我的评奖
							</a>
						</li>
						<li>
							<a href="#" onclick="showWdpjBcpj();return false;" class="btn_ck">
								查看详细
							</a>
						</li>
						<li>
							<a href="#" onclick="printDjbg();return false;" class="btn_dy">
								打印登记表
							</a>
						</li>
						<li>
							<a href="#" class="btn_qx" onclick="choiceFields();return false;">
								导出设置
							</a>
						</li>
						<li>
							<a href="#" onclick="configureExportData();return false;" class="btn_dc">
								导出
							</a>
						</li>
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
				<div id="div_rs" style="height:400px;overflow-x:auto;overflow-y:auto;">
				</div>
				
				<!--分页显示-->
				<div style="clear:both;">
					<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=generalPjpyGeneralForm"></jsp:include>
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