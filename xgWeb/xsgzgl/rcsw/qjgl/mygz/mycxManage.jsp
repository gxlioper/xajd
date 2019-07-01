<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
	    <script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="js/xgutil.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/exportData.js'></script>	
		<script language="javascript" defer="defer">
		//初始化
		function onShow(){
			searchRs();
		}
		
		function getShgwInfo(){
			alert(22);
			var czxm = $("czxm").value;
			var url="rcsw_qjgl.do?method=getShgwInfo";
				url+="&czxm="+czxm;
			
			jQuery.ajax({
				type:'post',
				url:url,
				dataType:'json',
				async: false,
				success:function(result){
					if(result.length==1){
						$('shgw').value=result[0].gwid;
						searchRs();
					}else{
						$("p_gwxx").innerHTML="";
						
						var html = "";
						for(var i=0;i<result.length;i++){
							var gwid = result[i].gwid;
							var gwmc = result[i].gwmc;
							
							html+="<input type=\"radio\" name=\"rad_gwid\" value=\""+gwid+"\" onclick=\"$('hid_gwid').value=this.value\"/>";
							html+=gwmc;
							html+="<br/>";
						}
						
						html+="<input type=\"hidden\" id=\"hid_gwid\" value=\"\"/>";
						$("p_gwxx").innerHTML=html;
						
						tipsWindown("系统提示","id:div_gwxx","350","250","true","","true","id");
					}
				}
			});
		}
		
		//查询结果集
		function searchRs(){
			var url = "rcsw_qjgl.do?method=getMycxList";
			
			var ie = "ie";
			
			var otherValue = [ie];

			showWaitingDiv("1000");
			searchRsByAjax(url,otherValue);
		}
		
		function showJgcxDetail(obj){
			var inputValue=obj.getElementsByTagName("input")[0].value;
			///showTopWin("rcsw_qjgl.do?method=jgcxDetail&sqid="+inputValue,800,600);

			showDialog('', 800, 500, "rcsw_qjgl.do?method=jgcxDetail&sqid="+inputValue);
		}
		
		function print(){
			var checkVal=document.getElementsByName("checkVal");
			var n=0;
			var inputValue="";
			for(var i=0;i<checkVal.length;i++){
				if(checkVal[i].checked){
					n++;
					inputValue=checkVal[i].value;
				}
			}
			if(n!=1){
				alertInfo("请勾选一条记录进行打印!");
				return false;
			}
			window.open("rcsw_qjgl.do?method=jgcxDetail&doType=print&sqid="+inputValue);
			//showOpenWindow("rcsw_qjgl.do?method=jgcxDetail&doType=print&sqid="+inputValue,800,600);
		}
		
		jQuery(function(){
			onShow();
		})
		
		function jgcxExportConfig() {
			//DCCLBH导出功能编号,执行导出函数 
			customExport("rcsw_qjgl_jgcx.do", jgcxExportData);
		}
			
		
			
		// 导出方法
		function jgcxExportData() {
			setSearchTj();//设置高级查询条件
			var url = "rcsw_qjgl.do?method=jgcxExportData&dcclbh=" + "rcsw_qjgl_jgcx.do";//dcclbh,导出功能编号
			url = addSuperSearchParams(url);//设置高级查询参数
			jQuery("form").eq(0).attr("action", url);
			jQuery("form").eq(0).submit();
		}
		</script>
	</head>
	<body  >
	
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>日常事务 - 请假管理 - 结果查询</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv()">使用帮助</a>
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
				1.勾选一条记录，点击“打印”按钮可以打印请假信息表。</br>
				2.双击一条记录可以查看详细信息。</br>
				</span>
			</p>
			<a class="close" title="隐藏" onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- 提示信息 end-->
		
		<html:form action="/rcsw_qjgl" method="post">

			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- 是否初始化  -->
			<input type="hidden" name="is_default" id="is_default" value=""/>
			<!-- 是否修改 -->
			<input type="hidden" id="had_edit" value="no"/>

			
			<!-- 隐藏域 end-->
			
			<!-- 多功能操作区 -->
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<li id="li_js">
							<a href="#" onclick="refreshForm('rcsw_qjgl_mygz_tea.do');return false;" class="btn_fh">
								返回
							</a>
						</li>
						<li id="li_js">
							<a href="#" onclick="print();return false;" class="btn_dy">
								打印
							</a>	
						</li>
						<li><a href="#" class="btn_qx" onclick="jgcxExportConfig();return false;">导出</a></li>
						<%--<li><a href="#" class="btn_dc" onclick="configureExportData();return false;">导出数据</a></li>
					--%></ul>
				</div>
				<!-- 按钮 end-->
		
				<!-- 过滤条件 -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
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
				<!-- From内容 -->
				<table align="center" width="100%">
					<tr>
						<td >
							<h3 class="datetitle_01">
								<span>
									&nbsp;
								</span>
							</h3>
						</td>
					</tr>
					<tr>
						<td width="" valign="top" style="position: relative;">
							<div id="div_rs" style="width:100%;height:400px;overflow-x:auto;overflow-y:auto;">
							</div>
						</td>
					</tr>
				</table>
				<!--分页显示-->
				<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=rcswQjglForm"></jsp:include>
				<script type="text/javascript">
						$('choose').className="hide";
				</script>
				<!--分页显示-->
			</div>
			<!-- 内容显示区 end-->
					
			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
			
			
		</html:form>
	</body>
</html>