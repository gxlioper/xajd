<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/xsgzgl/qgzx/yjscjffgl/js/yjscjffgl.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
				//为button注册事件
				jQuery("#btn_zj").click(add);
				jQuery("#btn_xg").click(update);
				jQuery("#btn_sc").click(deletes);
				jQuery("#search_go").click(query);
				jQuery("#btn_cz").click(function(){searchReset()});
			});
		</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
	
		<div class="toolbox">
			<!-- 按钮 -->
			<div class="buttonbox">
				<ul>
					<li><a href="javascript:void(0);" id="btn_zj" class="btn_zj">增加</a></li>
					<li><a href="javascript:void(0);" id="btn_xg" class="btn_xg">修改</a></li>
					<li><a href="javascript:void(0);" id="btn_sc" class="btn_sc">删除</a></li>						
				</ul>
			</div>
			<!-- 过滤条件 -->
			<div class="searchtab">
				<html:form action="/cjff.do" method="post" styleId="myForm">
				<table width="100%" border="0">
					<tfoot>
						<tr>
							<td colspan="6">
								<div class="btn">
									<button type="button" class="btn_cx" id="search_go">
										查 询
									</button>
									<button type="button" class="btn_cz" id="btn_cz">
										重 置
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<tr>
							<th width="16%">
								学号
							</th>
							<td width="34%">
								<html:text property="xh" name="model" ></html:text>
							</td>
							<th width="16%">
								学年
							</th>
							<td width="34%">
								<html:select  property="xn" styleId="xn"  style="width:150px">
									<html:options collection="xnList" labelProperty="xn" property="xn" />
								</html:select>
							</td>
							<th width="16%">
								用人部门2
							</th>
							<td width="34%">
								<html:select   property="yrbm" styleId="yrbm"  disabled="${rs.dis}" style="width:150px">
									<option value=''>全部</option>
									<html:options collection="bmList" property="bmdm" labelProperty="bmmc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								在岗状态
							</th>
							<td>
								<html:select name="rs" property="zgzt" styleId="zgzt" style="width:150px">
									<option value=''>全部</option>
									<option value='zg' selected="selected">在岗</option>
								</html:select>
							</td>
							<th>
								<font class="red">*</font>发放年月
							</th>
							<td>
								<html:select name="rs" property="ffny" styleId="ffny"  disabled="${rs.dis}" style="width:150px">
									<html:option value="${rs.ffny}">${rs.ffny}</html:option>
									<html:options collection="ffnyList" property="ffny" labelProperty="ffny" />
								</html:select>
							</td>
							<th>
								岗位名称
							</th>
							<td>
								<html:select name="rs" property="gwdm" styleId="gwdm" style="width:150px">
									<option value="" selected="selected">全部岗位</option>
									<html:options collection="gwList" property="gwmc" labelProperty="gwmc" />
								</html:select>
							</td>
							
						</tr>
					</tbody>
				</table>
				</html:form>
			</div>
		</div>
		
	
		<div class="formbox">
			<!--标题start-->
			<h3 class="datetitle_01">
				<span> 酬金发放列表 </span>
			</h3>

			<table id="dataTable"></table>
			<div id="pager"></div>

		</div>
	</body>
</html>
