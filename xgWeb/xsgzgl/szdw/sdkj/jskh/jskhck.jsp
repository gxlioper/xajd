<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script language="javascript" src="js/xgutil.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/exportData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script> 
		<script type="text/javascript">
			var gridSetting = {
				caption:"辅导员考核录入列表",
				pager:"pager",
				url:"sdkj_zdjskh.do?method=ckxx&type=query",
				colList:[
				   {label:'id',name:'id', index: 'id',width:'1%',hidden:true,key:true},
				   {label:'系部  &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;',name:'xb', index: 'xb',width:'20%'},
				   {label:'指导老师',name:'zdls', index: 'zdls',width:'30%'},
				   {label:'总人数',name:'zrs', index: 'zrs',width:'20%'},
				   {label:'在校人数',name:'zxrs', index: 'zxrs',width:'20%'},
				   {label:'扣分合计',name:'kfhj', index: 'kfhj',width:'20%'},
				   {label:'校风得分',name:'xfdf', index: 'xfdf',width:'20%'},
				   {label:'计算机过级率',name:'jsjgjl', index: 'jsjgjl',width:'20%'},
				   {label:'英语过级率',name:'yygjl', index: 'yygjl',width:'20%'},
				   {label:'公寓秩序',name:'gycx', index: 'gycx',width:'20%'},
				   {label:'就业率',name:'jyl', index: 'jyl',width:'20%'},
				   {label:'信息机 &nbsp; &nbsp; ',name:'xxj', index: 'xxj',width:'20%'},
				   {label:'使用率得分',name:'syldf', index: 'syldf',width:'20%'},
				   {label:'团队建设',name:'tdjs', index: 'tdjs',width:'20%'},
				   {label:'到课率  &nbsp; &nbsp; ',name:'dkl', index: 'dkl',width:'20%'},
				   {label:'到课率得分',name:'dkldf', index: 'dkldf',width:'20%'},
				   {label:'职业生涯规划合格率',name:'zysyghhgl', index: 'zysyghhgl',width:'20%'},
				   {label:'职业生涯规划得分',name:'zysyghdf', index: 'zysyghdf',width:'20%'},
				   {label:'教师指导手册',name:'jszdsc', index: 'jszdsc',width:'20%'},
				   {label:'学生流失',name:'xsls', index: 'xsls',width:'20%'},
				   {label:'学生专利',name:'xszl', index: 'xszl',width:'20%'},
				   {label:'安全',name:'aq', index: 'aq',width:'20%'},
				   {label:'大赛获奖',name:'dshj', index: 'dshj',width:'20%'},
				   {label:'得分  &nbsp; &nbsp; ',name:'df', index: 'df',width:'20%'},
				   {label:'修正后得分',name:'xzhdf', index: 'xzhdf',width:'20%'}
				],
				sortname: "xb",
			 	sortorder: "asc"
			}

			
			jQuery(function(){
				var nf = jQuery("#nf").val();
				var yf = jQuery("#yf").val();
				gridSetting.params={nf:nf,yf:yf};
				jQuery("#dataTable").initGrid(gridSetting);
			});

			function query(){
				var map = {};
				map["nf"] = jQuery("#nf").val();
				map["yf"] = jQuery("#yf").val();
				map["xb"] = jQuery("#xb").val();
				map["zdls"] = jQuery("#zdls").val();
				map["zgh"] = jQuery("#zgh").val();

				jQuery("#dataTable").reloadGrid(map);
			}

			function delKhjl(){
				var ids = jQuery("#dataTable").getSeletIds();
				if (ids.length == 0){
					alertInfo("请选择您要删除的记录！");
				} else {
					jQuery.post("sdkj_zdjskh.do?method=delKhjl",{values:ids.toString()},function(data){
						alert(data["message"]);
						jQuery("#dataTable").reloadGrid();
					},'json');
				}
			}
			function goHome(){
				url='szdw_sdkj_zdjskh.do';
				refreshForm(url);
			}




			
		</script>
	</head>

	<body>
	
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/sdkj_zdjskh">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<li><a href="#" onclick="goHome();return false;" class="btn_fh">返回</a></li>
						<li><a href="javascript:void(0);" onclick="delKhjl()" class="btn_sc">删除</a></li>
						<li><a href="#" class="btn_qx" onclick="choiceFields();return false;">导出设置</a></li>
						<li><a href="#" class="btn_dc" onclick="configureExportData();return false;">导出数据</a></li>
					</ul>
				</div>
				<div class="searchtab">
				<table width="100%" border="0">
					<tfoot>
						<tr>
							<td colspan="6">
								<div class="btn">
									<button type="button" class="btn_cx" id="search_go" onclick="query()">
										查 询
									</button>
									<button type="button" class="btn_cz" id="btn_cz" onclick="searchReset()">
										重 置
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<tr>
							<th>系部</th>
							<td><input type="text" id="xb" name="xb"/>
							</td>
							<th>年份</th>
							<td>
								<html:select name="map" property="nf" styleId="nf">
									<html:options collection="nfList" property="nd" labelProperty="nd"/>
								</html:select>
							</td>
							<th>月份</th>
							<td>
								<html:select name="map" property="yf" styleId="yf">
									<html:option value=""></html:option>
									<html:options collection="yfList" property="yf" labelProperty="yf"/>
								</html:select>
							</td>
						</tr>
						<tr>
							<th>教师姓名</th>
							<td><input type="text" id="zdls" name="zdls"/>
							</td>
							<th>职工号</th>
							<td><input type="text" id="zgh" name="zgh"/>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
				

			</div>
		</html:form>
		<div class="formbox">
			<!--标题start-->
			<h3 class="datetitle_01">
				<span> 指导老师考核录入信息列表 </span>
			</h3>

			<div  style="hoverflow-x:hidden;overflow-y:auto;">
				<table id="dataTable" ></table>
			</div>
			<div id="pager"></div>

		</div>
	</body>
</html>
