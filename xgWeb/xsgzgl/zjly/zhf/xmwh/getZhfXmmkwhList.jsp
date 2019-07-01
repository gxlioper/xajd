<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/zjly/zhf/js/xmwh.js"></script>
		<script type="text/javascript">
			var gridSetting = {
				caption:"模块项目列表",
				pager:"pager",
				url:"zhf_xmmkwh.do?method=getZhfXmmkwhList&type=query",
				colList:[
			       {label:'模块代码',name:'xmmkdm', index: 'xmmkdm',key:true,width:'15%'},
				   {label:'模块名称',name:'xmmkmc', index: 'xmmkmc',width:'50%'},
				   {label:'总分',name:'xf', index: 'xf',width:'15%'},
				   {label:'合格分',name:'hgf', index: 'hgf',width:'15%'}
				],
				sortname: "xmmkdm",
			 	sortorder: "asc"
			};

			var gridSetting2 = {
					caption:"计分项目列表",
					pager:"pager",
					url:"zhf_jfxmwh.do?method=getZhfJfxmwhList&type=query",
					colList:[
                       {label:'jfxmdm',name:'jfxmdm', index: 'jfxmdm',key:true,hidden:true},
				       {label:'所属模块',name:'xmmkmc', index: 'xmmkmc',width:'20%'},
					   {label:'计分项目',name:'jfxmmc', index: 'jfxmmc',width:'20%'},
					   {label:'考核要点',name:'khyd', index: 'khyd',width:'20%'},
					   {label:'分数',name:'fs', index: 'fs',width:'10%'},
					   {label:'是否限分',name:'sfxfmc', index: 'sfxfmc',width:'10%'},
					   {label:'限制分数',name:'xdfs', index: 'xdfs',width:'10%'},
					   {label:'已授权部门数',name:'bms', index: 'bms',width:'10%'}
					],
					
					sortname: "xmmkdm,to_number(jfxmdm)",
				 	sortorder: "asc"
				};
<%--			function dcmcLink(cellValue, rowObject) {--%>
<%--				var rddjdm = rowObject["rddjdm"];--%>
<%--				return "<a href='javascript:void(0);' onclick=\"ckxx('" + rddjdm--%>
<%--						+ "')\" class='name'>" + cellValue + "</a>";--%>
<%--			}--%>
			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
			});			
		</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/zhf_xmmkwh">
		<%@ include file="/comm/hiddenValue.jsp"%>
		<div class="toolbox">
			<!-- 按钮 -->			
			<logic:equal value="yes" name="writeAble">
				<div class="buttonbox">
					<ul>
					<div id="xmmk">
						<li>
							<a href="javascript:void(0);" onclick="addXmmk()" class="btn_zj" id="addXmmk">增加</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="updateXmmk();" class="btn_xg" id="xgXmmk">修改</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="delXmmk();" class="btn_sc" id="scXmmk">删除</a>
						</li>
					</div>						
						<div style="display: none" id="jfxm">
							<li>
								<a href="javascript:void(0);" onclick="addJfxm()" class="btn_zj" id="addJfxm">增加</a>
							</li>
							<li>
								<a href="javascript:void(0);" onclick="updateJfxm();" class="btn_xg" id="xgJfxm">修改</a>
							</li>
							<li>
								<a href="javascript:void(0);" onclick="delJfxm();" class="btn_sc" id="scJfxm">删除</a>
							</li>
							<li>
								<a href="javascript:void(0);" onclick="bmsq()" class="btn_sz" id="bmsq">部门授权</a>
							</li>
							<li>
								<a href="javascript:void(0);" onclick="jdsz()" class="btn_sz" id="jdsz">兼得设置</a>
							</li>
							<li>
								<a href="javascript:void(0);" onclick="exportConfig()" class="btn_dc" id="jfxmdc">导出</a>
							</li>
						</div>						
					</ul>
				</div>
			</logic:equal>
			
			<div class="comp_title" id="comp_title">
			      <ul style="width:90%">
			        <li class="ha"><a href="javascript:void(0);" onclick="selectTab(this,'xmmk');"><span>项目模块</span></a></li>
			        <li><a href="javascript:void(0);" onclick="selectTab(this,'jfxm');"><span>计分项目</span></a></li>
			      </ul>
			</div>
			<div class="searchtab">
			<div id="xmmkGjcx">
				<table width="100%" border="0">
					<tbody>
						<tr>
							<th width="15%" style="margin-left:20px" id="xsmc">
								项目模块名称
							</th>
							<td>
								<input type="text" id="cxzd" />
							</td>
							<td>
								<div class="btn">
									<button type="button" class="btn_cx" id="search_go"
										onclick="queryXmmk()">
										查 询
									</button>
								</div>
							</td>
						</tr>
					</tbody>
				</table>
				</div>
				<div id="jfxmGjcx" style="display: none">
					<%@ include file="/comm/search/superSearchArea.jsp"%>
				</div>
			</div>		
		</div>
		</html:form>

		<div class="formbox">
			<!--标题start-->
			<h3 class="datetitle_01">
				<span>项目模块维护列表</span>
			</h3>

			<table id="dataTable"></table>
			<div id="pager"></div>

		</div>
	</body>
</html>
