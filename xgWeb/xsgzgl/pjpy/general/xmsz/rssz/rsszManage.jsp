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
			
			var url = "general_xmsz_rssz_ajax.do?method=searchXmszRssz";
			var ie = "ie";
			var xmdm = jQuery("#xmdm").val();

			var arrange = jQuery("#arrange").val();
			var fashion = jQuery("#fashion").val();
			
			if(arrange == ""){
				arrange = "no";
			}
			
			var otherValue = [ie,xmdm,arrange,fashion];

			$("divWaiting").style.display="";
			$("divDisable").style.display="";
			
			searchRsByAjax(url,otherValue);

			$("divWaiting").style.display="none";
			$("divDisable").style.display="none";
			
			jQuery.ajaxSetup({async:true});
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
				<em>您的当前位置：</em><a>评奖评优 - 基本设置 - 人数设置</a>
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
				1.结果集中的部门是根据您所设置的<font color="blue">人数控制范围</font>定的。<br/>
				2.如果您<font color="blue">勾选</font>了相关记录，点击<font color="blue">比例设置</font>，系统将对勾选记录进行相应的操作。<br />
				3.如果您<font color="blue">未勾选</font>任一记录，点击<font color="blue">比例设置</font>，系统将以<font color="blue">过滤条件</font>为准，对相应部门进行相应的操作。<br />
				4.如果您<font color="blue">修改</font>了最终人数中的数据，必须要执行<font color="blue">保存最终人数</font>，数据才会起效<br/>
				</span>
			</p>
			<a class="close" title="隐藏"  onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- 提示信息 end-->
		
		<html:form action="/general_pjpy" method="post">
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" id="xmdm" name="str_xmdm" value="${xmdm }" />
			
			<!-- 多功能操作区 -->
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						
						<li>
							<a href="#" onclick="goPjszPjxm();return false;" class="btn_fh">
								返回项目设置
							</a>
						</li>
						<logic:equal name="checkXssq" value="true">
						<li>
							<a href="#" onclick="showSzblDiv();return false;" class="btn_sz">
								比例设置
							</a>
						</li>
						<li>
							<a href="#" onclick="checkSaveZzrs();return false;" class="btn_ccg">
								保存最终人数
							</a>
						</li>
						</logic:equal>
						<logic:equal name="checkXssq" value="false">
						<li>
							<a href="#" onclick="return false;" disabled="true" class="btn_sz">
								比例设置
							</a>
						</li>
						<li>
							<a href="#" onclick="return false;" disabled="true" class="btn_ccg">
								保存最终人数
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
				<div id="div_rs"
					style="width:100%;overflow-x:auto;overflow-y:hidden;">
				</div>

				<!--分页显示-->
				<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=generalPjpyGeneralForm"></jsp:include>
				<script type="text/javascript">
					//$('choose').className="hide";
				</script>
				<!--分页显示-->
			</div>
			<!-- 内容显示区 end-->
			
			<!-- 设置比例弹出层 -->
			<div id="div_szbl" style="display:none">
				<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>比例设置</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th width="30%">
									<font color="red">*</font>
									比例
								</th>
								<td width="">
									<input type="text" name="str_szbl" id="szbl" 
										onblur="checkInputNum(this);chMax(this,100);"
										maxlength="5" 
										style="width : 50%;ime-mode:disabled"
									/>%
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="2">
									<div class="bz">
										
									</div>
									<div class="btn">
										<button type="button"  onclick="checkSaveSzbl()">
											确 定
										</button>
										
										<button type="button"  onclick="closeWindown();return false;">
											关 闭
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
			<!-- 设置比例弹出层 end-->
			
			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>