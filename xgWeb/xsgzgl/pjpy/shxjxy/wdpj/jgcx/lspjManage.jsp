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
			
			var url = "general_wdpj_jgcx_ajax.do?method=searchWdpjLspj";
			var ie = "ie";
			
			var otherValue = [ie];

			$("divWaiting").style.display="";
			$("divDisable").style.display="";
			
			searchRsByAjax(url,otherValue);
			
			jQuery.ajaxSetup({async:true});
		}
		
		//打印报表
		function printPj(url){
			
			var num = jQuery("input[name=primarykey_checkVal]:checked").length;
			
			var flag = false;
			var n = 0;
			var jxjName = "";
			var xh = "";
			
			jQuery("input[name=primarykey_checkVal]:checked").each(function(i){
				jxjName = jQuery(this).parents("tr").children("td").eq(7).html();
				xh = jQuery(this).parents("tr").children("td").eq(2).html();
				xn = jQuery(this).parents("tr").children("td").eq(1).html();
			});
			
			var xxdm = jQuery("#xxdm").val();
			
			//浙江传媒学院
			if("11647" == xxdm || "12741" == xxdm || "10338" == xxdm || "10653" == xxdm||"12867"==xxdm||"10657"==xxdm){
				url = "general_pjpy_djbg.do?method=printDjbg";
				url+= "";
				jQuery("input[name=primarykey_checkVal]:checked").each(function(i){
					var pkValue = jQuery(this).val();
<%--					url+= "&str_xn=" + (pkValue.split("luojw")[0]);--%>
<%--					url+= "&str_xq=" + (pkValue.split("luojw")[1]);--%>
<%--					url+= "&str_xh=" + (pkValue.split("luojw")[2]);--%>
<%--					url+= "&str_xmmc=" + (pkValue.split("luojw")[3]);							--%>
<%--					url+= "&str_lx=lspj";--%>
						url+= "&str_xh=" + xh;
						url+= "&str_xmmc=" + jxjName;
						url+= "&str_lx=bcpj";
				})
			}else{
				url=url+"&jxjName="+jxjName+"&xh="+xh+"&xn="+xn;
			}
			
			if(num==1){	
				 document.forms[0].action = url;
				 document.forms[0].target = "_blank";
				 document.forms[0].submit();
				 document.forms[0].target = "_self";
			}else{
				alertInfo("请勾选一条需要打印记录！");	
			}
			
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
				<em>您的当前位置：</em><a>评奖评优 - 历史评奖信息</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv();">使用帮助</a>
			</p>
		</div>
		<!-- 标题 end-->
		
		<!-- 提示信息 end-->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>提示：</span>
			</h3>
			<p>
				<span>
				1.本功能的增删改操作只能由<font color="blue">学校用户(管理员)</font>进行操作。<br/>
				2.如果过滤条件中<font color="blue">无法显示</font>评奖时间和评奖项目，请至少维护一条记录，重新进入本功能。<br/>
				3.<font color="blue">勾选一条</font>记录，点击<font color="blue">打印登记表</font>，可以输出相应的登记表。<br/>
				4.登记表打印如果不符合学校实际情况，请联系本公司客服人员。<br/>
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
						<!-- 页面来源 -->
						<logic:equal name="forward" value="jbsz">
						<li>
							<a href="#" onclick="goPjpyWdpj();return false;" class="btn_fh">
								返回我的评奖
							</a>
						</li>
						</logic:equal>
						<li>
							<a href="#" class="btn_ck" onclick="showWdpjLspj('view');return false;">
								查看
							</a>
						</li>
						<!-- 管理员操作  begin -->
						<logic:equal name="userStatus" value="xx">
							<li>
								<a href="#" onclick="showWdpjLspj('add');return false;" class="btn_zj">
									增加
								</a>
							</li>
							<li>
								<a href="#" onclick="showWdpjLspj('edit');return false;" class="btn_xg">
									修改
								</a>
							</li>
							<li>
								<a href="#" onclick="checkDelWdpjLspj();return false;" class="btn_sc">
									删除
								</a>
							</li>
							
							<li>
								<a href="#" onclick="impAndChkData();return false;" class="btn_dr">
									导入
								</a>
							</li>
						</logic:equal>
						<!-- 管理员操作  end -->
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
						<li>
							<a href="#" onclick="printPj('pjpyCommPrint.do?method=printJsp');return false;" class="btn_dy">
								打印登记表
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
				<div id="div_rs" style="height:380px;overflow-x:auto;overflow-y:auto;">
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