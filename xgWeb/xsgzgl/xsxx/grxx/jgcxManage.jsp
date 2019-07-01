<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<link rel="stylesheet" type="text/css" href="js/jquery/themes/default/easyui.css" />
	    <link rel="stylesheet" type="text/css" href="js/jquery/themes/icon.css" />
	    <script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" defer="defer">
		
		//查询结果集
		function searchRs(){
		
			var url = "xsxx_grxx.do?method=getJgcxList";

			$("divWaiting").style.display="none";
			$("divDisable").style.display="none";
			
			var ie = "ie";
			
			var otherValue = [ie,stylePath];

			searchRsByAjax(url,otherValue);
		}
		
		//展示审核详细页面
		function showJgDetail(){
		
			var num = jQuery("input[name=checkVal]:checked").length;
			
			if(num == 0){
				alertError("请勾选您希望查看的记录");
				return false;
			}else if(num == 1){
			
				var sqid = jQuery("input[name=checkVal]:checked")[0].value;
				
				var url = "xsxx_grxx.do?method=jgcxDetail";
					url+= "&sqid="+sqid;
					url+= "&stylePath="+stylePath;

				showTopWin(url,'800','620');
			}else{
				alertError("只能勾选一条记录进行查看，请确认");
				return false;
			}
		}
		</script>
	</head>
	<body onload="searchRs();" >
	
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
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
				<span id="div_help" style="display: none">
				1.如果在过滤条件中的申请结果选择<font color="blue">审核中</font>的话，将过滤出申请记录非未审核和非审核通过的所有记录。</br>
				2.如果某次个人信息修改<font color="blue">无需审核</font>的话，将直接更新学生信息库，无法在结果查询中查询到。
				</span>
			</p>
			<a class="close" title="隐藏"  onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- 提示信息 end-->
		
		<html:form action="/xsxx_grxx" method="post">

			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- 多功能操作区 -->
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<li>
							<a href="#" onclick="showJgDetail();return false;" class="btn_yl">
								查看
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
					<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=xsxxGrxxForm"></jsp:include>
					 <script type="text/javascript">
				     $('choose').className="hide";
				     </script>
				</div>
				<!--分页显示-->
			</div>
			<!-- 内容显示区开始 end-->	
			
			<!-- 岗位信息弹出层 -->
			<div id="div_gwxx" style="display:none">
				<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>岗位信息(您拥有多个岗位，请选择本次审核的岗位)</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th width="30%">
									您的岗位
								</th>
								<td>
									<p id="p_gwxx" style="height: 50px">
									
									</p>
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="2">
									<div class="bz">
										
									</div>
									<div class="btn">
										<button type="button" id="btn_bc" onclick="$('shgw').value=$('hid_gwid').value;searchRs();closeWindown();">
											确 定
										</button>
										
										<button type="button" id="btn_gb" onclick="closeWindown();">
											关 闭
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
			
			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>