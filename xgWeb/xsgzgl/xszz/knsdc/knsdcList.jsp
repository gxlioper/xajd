<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/xszz/knsdc/js/knsdclist.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript">
			function newChgCode(obj){
				allNotEmpThenGo(obj.id);
			}
		</script>
	</head>
	
	<body>
	<html:form action="/xszz_knsdc" method="post">
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
	
		<div class="toolbox">
			<!-- 按钮 -->
			
			<logic:equal name="writeAble" value="yes">	
			<div class="buttonbox">
				<ul>
					<li><a href="javascript:void(0);" onclick="add();" class="btn_zj">增加</a></li>
					<li><a href="javascript:void(0);" onclick="update();" class="btn_xg">修改</a></li>
					<li><a href="javascript:void(0);" onclick="del();" class="btn_sc">删除</a></li>
					<li><a href="javascript:void(0);" onclick="updateQyzt();" class="btn_xg">变更启用状态</a></li>
				</ul>
			</div>
			</logic:equal>				
			<!-- 上海体育个性化-->
			<logic:equal value="10277" name="xxdm">
			<div class="compTab" id="card">
				<div class="comp_title">
					<ul>
						<li class="ha"><a href="#" onclick="newChgCode(this);return false;" id="xszz_knsdc.do?method=dcwhList"><span>困难档次</span></a></li>
						<li ><a href="#" onclick="newChgCode(this);return false;" id="xszz_knsdc.do?method=knyyList"><span>困难原因</span></a></li>
						
					</ul>
				</div>
			</div>	
			</logic:equal>
			<!-- 过滤条件 -->
			<div class="searchtab">
				<table width="100%" border="0">
					<tr>
						<th width="10%">档次名称</th>
						<td>
							<input type="text" id="dcmc" name="dcmc" maxleng="20" 
							onkeypress="if(pressEnter(event)){query();return false;}"/>
						</td>
						<td>
							<div class="btn">
								<button type="button" class="btn_cx" id="search_go" onclick="query()">
									查 询
								</button>
							</div>
						</td>
					</tr>					
				</table>
			</div>
		</div>
		<div class="formbox">
			<!--标题start-->
			<h3 class="datetitle_01">
				<span> 档次维护列表 </span>
			</h3>

			<table id="dataTable"></table>
			<div id="pager"></div>

		</div>
		</html:form>
	</body>
</html>
