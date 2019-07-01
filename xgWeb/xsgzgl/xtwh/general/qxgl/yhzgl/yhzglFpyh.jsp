<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="js/Basic.js"></script>
		<script language="javascript" src="/xgxt/dwr/interface/systemFunction.js"></script>
		<script language="javascript" src="xsgzgl/xtwh/general/qxgl/yhzgl/yhzglFpyh.js"></script>
	</head>
	<body >
		<html:form action="/xtwh_qxgl_yhgl" method="post">

			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- 隐藏域 -->
			<input type="hidden" id="oldZdm" value="${zdm}">
			<input type="hidden" id="fpzt" value="${fpzt}">
			  <!-- 多功能操作区 -->
			<div class="toolbox" id="dgncz">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<li><a href="#" onclick="reBack();return false;" class="btn_fh">返回</a></li>
						<li id="fpButtonId"><a href="#" onclick="fpyh();return false;" class="btn_sz">分配</a></li>
						<li id="tyButtonId" style="display:none"><a href="#" onclick="qxfpyh();return false;" class="btn_sc">取消分配</a></li>
					</ul>
				</div>
				<table style="" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>组信息</span>
							</th>
						</tr>
					</thead>
					
					<tbody>
						<tr>
							<th width="15%">组名称</th>
							<td width="30%">
								${rs.zmc }
							</td>
							<th width="15%">用户数</th>
							<td>
								<span id ="yhs">${rs.num }</span>
							</td>
						</tr><tr height="2px"></tr>
					</tbody>		
			</table>
			<div class="comp_title" id="comp_title">
		      <ul style="width:90%" id="tabUl">
		      	<li class="ha" id="kfpTabId"><a href="javascript:void(0);" onclick="gofpList('wfp');return false;"><span>可分配用户列表</span></a></li>
				<li id="yfpTabId"><a href="javascript:void(0);" onclick="gofpList('yfp');return false;"><span>已分配用户列表</span></a></li>
		      </ul>
		    </div>
		  	</div>
				<!-- 过滤条件 -->
				<div class="searchtab"  id ="searchTjId">
				
					<table width="100%" border="0"  class="formlist">
						<tfoot>
							<tr>
								<td colspan="4">
									<input type="hidden" name="go" value="a" />
									<div class="btn">
										<button type="button"  class="btn_cx" id="search_go" onclick="searchRs();return false;">
											查 询
										</button>
										&nbsp;&nbsp;&nbsp;&nbsp;
										<button type="button"  class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
											重 置
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
						<tbody id="tbody_search_query">
							<tr>
								<th>
									用户名
								</th>
								<td>
									<input type="text" name="yhm" id="yhm" style="width:200px" maxlength="20" 
										onkeypress="if(pressEnter(event)){searchRs();return false;}"/>
								</td>								
								<th>
									姓名
								</th>
								<td>
									<input type="text" name="xm" id="xm" style="width:200px" maxlength="20" 
										onkeypress="if(pressEnter(event)){searchRs();return false;}"/>
								</td>						
							</tr>
							<tr>
								<!-- 
								<th>
									所属组
								</th>
								<td>
									<html:select property="zdm" style="width:200px" styleId="zdm">
										<html:option value="">--请选择--</html:option>
										<html:options collection="yhzList" property="dm" labelProperty="mc" />
									</html:select>
								</td>
								 -->
								<th>
									启用状态
								</th>
								<td>
									<html:select property="qx" style="width:200px" styleId="qx">
										<html:option value="">--请选择--</html:option>
										<html:option value="1">启用</html:option>
										<html:option value="0">停用</html:option>
									</html:select>
								</td>
								<th>
									所属部门
								</th>
								<td>							
									<html:select property="szbm" style="width:200px" styleId="szbm">
										<html:option value="">--请选择--</html:option>
										<html:options collection="yjbmList" property="bmdm" labelProperty="bmqc" />
									</html:select>
								</td>	
							</tr>
							<tr>
								<th>
									是否辅导员
								</th>
								<td>
									<html:select property="sffdy" style="width:200px" styleId="sffdy">
										<html:option value="">--请选择--</html:option>
										<html:option value="是">是</html:option>
										<html:option value="否">否</html:option>
									</html:select>
								</td>
								<th>
									是否班主任
								</th>
								<td>
									<html:select property="sfbzr" style="width:200px" styleId="sfbzr">
										<html:option value="">--请选择--</html:option>
										<html:option value="是">是</html:option>
										<html:option value="否">否</html:option>
									</html:select>
								</td>
							</tr>
						</tbody>
					</table>
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
				<div id="div_rs"
					style="width:100%;overflow-x:auto;overflow-y:hidden;">
				</div>

				<!--分页显示-->
				<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=yhglNewForm"></jsp:include>
				<!--分页显示-->
			</div>
			<!-- 内容显示区 end-->
			<%@ include file="/xsgzgl/xtwh/general/qxgl/yhgl/yhglCz.jsp"%>

			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</div>
		</html:form>
	</body>
</html>
