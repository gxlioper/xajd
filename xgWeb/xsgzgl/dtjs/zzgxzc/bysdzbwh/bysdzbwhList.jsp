<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/dtjs/zzgxzc/bysdzbwh/js/bysdzbwh.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
				jQuery("#search_go").bind("click",query);
				jQuery("#btn_cz").bind("click",searchReset);
				jQuery("#dzbmc").bind("keypress",function(event){
					if(event.keyCode==13||"13"==event.keyCode){
						query();
						return false;
					}
				});
			});
		</script>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/xpj_pjxzdm">
		<div class="toolbox">
			<!-- 按钮 -->
			<div class="buttonbox">
				<ul>
					<logic:equal name="writeAble" value="yes">	
					<li><a href="javascript:void(0);" onclick="add();" class="btn_zj">增加</a></li>
					<li><a href="javascript:void(0);" onclick="update();" class="btn_xg">修改</a></li>
					<li><a href="javascript:void(0);" onclick="del();" class="btn_sc">删除</a></li>	
					<li><a href="javascript:void(0);" class="btn_dr" onclick="importConfig();return false;" >导入</a></li>
					</logic:equal>
					<li><a href="javascript:void(0);" class="btn_dc" onclick="exportConfig();return false;">导出</a></li>					
				</ul>
			</div>
			<!-- 单个代码维护 不显示页签
			<div class="comp_title" id="comp_title">
		      <ul style="width:90%" id="tabUl">
				<li class="ha" ><a href="javascript:void(0);"><span>党支部</span></a></li>
		      </ul>
		    </div>
		     -->
			<!-- 过滤条件 -->
			<div class="searchtab">
				<table width="100%" border="0">
					<tr>
						<th width="15%">党支部名称</th>
						<td>
							<input type="text" id="dzbmc" name="dzbmc" maxleng="20" />
						</td>
						<td>
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
				</table>
			</div>
		</div>
		</html:form>
		<div class="formbox">
			<!--标题start-->
			<h3 class="datetitle_01">
				<span>毕业生党支部维护 </span>
			</h3>

			<table id="dataTable"></table>
			<div id="pager"></div>

		</div>
	</body>
</html>
