<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：yyp -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>	
		<script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript" src="js/xsgzgl/jxgl/general/jxxxwh/jxxx.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script language="javascript" defer="defer">
		//初始化
		jQuery(document).ready(function(){ 
			searchRs();
		});
		</script>
	</head>
	<body>

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
				<span>
				1.军训信息维护主要管理每次军训的<font color="blue">基本参数</font>，<font color="blue">参训名单</font>以及军训信息的<font color="blue">运行</font>与<font color="blue">停止</font>状态。<br/>
				2.每次<font color="blue">只能运行一条</font>军训信息，勾选一条记录<font color="blue">修改运行</font>后，其他军训信息将会<font color="blue">停止</font>。<br/>
				3.勾选一条记录后点击<font color="blue">参训名单</font>维护来进行参训人员的修改。
				</span>
			</p>
			<a class="close" title="隐藏"  onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- 提示信息 end-->

		<html:form action="/jxgl_jxxxwh" method="post">

			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="xmdm" id="xmdm" value="${xmdm }" />
			<!-- 隐藏域 -->
			<!-- 多功能操作区 -->
			<div class="toolbox" id="dgncz">
				<!-- 按钮 -->
				<logic:equal name="writeAble" value="yes">
				<div class="buttonbox">
					<ul>
						<li>
							<a href="#" class="btn_zj" onclick="jxxxZj();return false;">增加</a>
						</li>
						<li>
							<a href="#" class="btn_xg" onclick="jxxxModi('update');return false;">修改</a>
						</li>
						<li>
							<a href="#" class="btn_sc" onclick="jxxxSc();return false;">删除</a>
						</li>
						<li>
							<a href="#" class="btn_ck" onclick="jxxxModi('cxmd');return false;">参训名单管理</a>
						</li>
						<li>
							<a href="#" class="btn_shtg" onclick="jxxxModi('start');return false;">运行</a>
						</li>
						<li>
							<a href="#" class="btn_shbtg" onclick="jxxxModi('stop');return false;">停止</a>
						</li>
						<li>
							<a href="#" class="btn_dr" onclick="drCxmd();return false;">导入参训名单</a>
						</li>
					</ul>
				</div>
				</logic:equal>
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
					<span> 查询结果&nbsp;&nbsp; </span>
				</h3>
				<div id="div_rs"
					style="width:100%;overflow-x:auto;overflow-y:hidden;">
				</div>

				<!--分页显示-->
				<jsp:include flush="true"
					page="/sjcz/turnpage.jsp?form=jxglJxxxwhForm"></jsp:include>
			</div>
			<div id="div_detail" style="display:none">
			</div>		
			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
			<div id="tempDiv" style="display: none">
				<table align="center" class="formlist">
					<thead>
						<tr>
							<th colspan="2">
								<span>军训信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>
								<span class="red">*</span>军训名称
							</th>
							<td>
								<input type="hidden" name="jxid" id="jxid" value=""/>
								<input type="text" name="jxmc" id="jxmc" value="" maxlength="50" style="width: 150px;"/>
							</td>
						</tr>
						<tr>
							<th>
								<span class="red">*</span>开始时间
							</th>
							<td>
								<input type="text" id="kssj" name="kssj" style="width: 150px;"
									onclick="return showCalendar('kssj','y-mm-dd');" 
									onblur="dateFormatChg(this)" readonly="true"/>
							</td>
						</tr>
						<tr>
							<th>
								<span class="red">*</span>结束时间
							</th>
							<td>
								<input type="text" id="jssj" name="jssj" style="width: 150px;"
									onclick="return showCalendar('jssj','y-mm-dd');" 
									onblur="dateFormatChg(this)" readonly="true"/>
							</td>
						</tr>
						<tr>
							<th>
								<span class="red">*</span>参加年级
							</th>
							<td>
								<select id="cjnj" name="cjnj" style="width: 150px;"></select>
							</td>
						</tr>
						<tr>
							<th>
								军训状态
							</th>
							<td>
								<input type="radio" name="jxzt" value="start" checked="checked"/>运行
								<input type="radio" name="jxzt" value="stop"/>停止
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="2">
								<div class="bz">
									"<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
									<button type="button" name="保存" onclick="jxxxDivSave();return false;">
										保存
									</button>
									<button type="button" name="关闭" onclick="closeWindown();return false;">
										关闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
		</html:form>
	</body>
</html>