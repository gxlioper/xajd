<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：yyp -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>	
		<script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
<%--		<script type='text/javascript' src="js/comm/message.js"></script>--%>
		<script type="text/javascript" src="js/xsgzgl/jxgl/general/jxxxwh/jxmd.js"></script>
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
				1.军训名单管理参加本次军训的<font color="blue">参训人员</font>。<br/>
				2.根据在校生<font color="blue">年级</font>以及<font color="blue">往届缓训，免训</font>的学生自动生成军训名单。<br/>
				3.通过<font color="blue">取消军训资格，缓训，免训，参训</font>的操作对名单进行调整。
				</span>
			</p>
			<a class="close" title="隐藏"  onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- 提示信息 end-->

		<html:form action="/jxgl_jxxxwh" method="post">

			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="xmdm" id="xmdm" value="${xmdm }" />
			<input type="hidden" name="ysfxm" id="ysfxm" value="no"/>
			<input type="hidden" name="ysfhx" id="ysfhx" value="yes"/>
			<!-- 隐藏域 -->
			<!-- 多功能操作区 -->
			<div class="toolbox" id="dgncz">
				<!-- 按钮 -->
				
				<div class="buttonbox">
					<ul>
						<li>
							<a href="#" class="btn_fh" onclick="refreshForm('jxgl_jcxxwh_jxxxwh.do');return false;">返回</a>
							<logic:equal name="rs" property="jxzt" value="start">
								<a href="#" class="btn_zj" onclick="jxmdsc();return false;">名单生成</a>
								<a href="#" class="btn_sc" onclick="jxxx('sc');return false;">取消军训资格</a>
								<a href="#" class="btn_xg" onclick="jxxx('hx');return false;">缓训</a>
								<a href="#" class="btn_xg" onclick="jxxx('mx');return false;">免训</a>
								<a href="#" class="btn_xg" onclick="jxmdModi('cx');return false;">参训</a>
							</logic:equal>
							<a href="#" class="btn_dc" onclick="exportConfig();return false;">导出</a>
							<a href="#" class="btn_ck" onclick="viewJxxx();return false;">查看</a>
							
							<logic:equal name="xxdm" value="14073">
								<li>
									<a href="javascript:void(0);" onclick="printHx();return false;" class="btn_dy">缓训申请表</a>
									<a href="javascript:void(0);" onclick="printMx();return false;" class="btn_dy">免训申请表</a>
								</li>
							</logic:equal>
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
			<div id="tempDiv" style="display:none">
				<table align="center" class="formlist">
					<thead>
						<tr>
							<th colspan="2">
								<span>参训名单生成</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="30%">
								<span class="red">*</span>参加年级
							</th>
							<td>
								<logic:iterate id="cjnj" name="cjnjList">
									<logic:equal name="rs" property="cjnj" value="${cjnj.cjnj}">
										<input type="checkbox" name="cjnj" value = "${cjnj.cjnj}" onclick="getJxrs()" checked="checked"/>${cjnj.cjnj}
									</logic:equal>
									<logic:notEqual name="rs" property="cjnj" value="${cjnj.cjnj}">
										<input type="checkbox" name="cjnj" value = "${cjnj.cjnj}" onclick="getJxrs()"/>${cjnj.cjnj}
									</logic:notEqual>
								</logic:iterate>
								
							</td>
						</tr>
						<tr>
							<th>
								往届缓训学生
							</th>
							<td>
								<input type="radio" name="sfhx" value="yes" onclick="getJxrs()" checked="checked"/>是
								<input type="radio" name="sfhx" value="no" onclick="getJxrs()"/>否
							</td>
						</tr>
						<tr>
							<th>
								往届免训学生
							</th>
							<td>
								<input type="radio" name="sfmx" value="yes" onclick="getJxrs()"/>是
								<input type="radio" name="sfmx" value="no" onclick="getJxrs()" checked="checked"/>否
							</td>
						</tr>
						<tr>
							<th>
								所选年级未参训人数
							</th>
							<td>
								<span id="njrs"></span>
							</td>
						</tr>
						<tr>
							<th>
								往届缓训人数
							</th>
							<td >
								 <span id="hxrs"></span>
							</td>
						</tr>
						<tr>
							<th>
								往届免训人数
							</th>
							<td >
							  <span id="mxrs"></span>
							</td>
						</tr>
						<tr>
							<th>
								本次军训已参训人数
							</th>
							<td>
								<font id="cjrs"></font>
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
									<button type="button" id="bcBtn" name="保存" onclick="jxmdDivSave();return false;">
										生成
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