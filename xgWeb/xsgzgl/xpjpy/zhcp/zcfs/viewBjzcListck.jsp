<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/xpjpy/zhcp/zcfs/js/zcfs.js"></script>
		<script type="text/javascript" src="js/json.js"></script>
		<script type="text/javascript">
			var gridSetting = {
				caption:"综测班级列表",
				pager:"pager",
				url:"xpj_zcfs.do?method=viewBjzcListCk&doType=query",
				colList:[
				   {label:'key',name:'id', index: 'id',hidden:true,key:true},
				   {label:'年级',name:'nj', index: 'nj',width:'8%'},
				   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xydm',width:'24%'},
				   {label:'专业',name:'zymc', index: 'zydm',width:'23%'},
				   {label:'班级',name:'bjdm', index: 'bjdm',hidden:true},
				   {label:'班级',name:'bjmc', index: 'bjdm',width:'17%'},
				   {label:'班级人数',name:'bjrs', index: 'bjrs',width:'8%',
					formatter:function(cellValue,rowObject){
								var html = jQuery("<a href='javascript:void(0);' class='name'>"+cellValue+"</a>");
								html.bind("click",function(){
									showDialog("查看综测分",700,500,"xpj_zcfs.do?method=viewZcfs&id="+rowObject["id"]);
								});
								return html;
							 }
				   }
				],
				sortname: "tjzt,nj,xydm,zydm",
			 	sortorder: "desc"
			};
			
			jQuery(function(){
				gridSetting["params"] = {tjzt:jQuery("#tjzt").val(),yf:jQuery("#yf").val()};
				jQuery("#dataTable").initGrid(gridSetting);
			});
			
			function searchRs(){
				var map = getSuperSearch();
				map["tjzt"]= jQuery("#tjzt").val();
				map["yf"]= jQuery("#yf").val();
				jQuery("#dataTable").reloadGrid(map);
			}

			var DCCLBH = "tjqkdc.do";//dcclbh,导出功能编号

			//自定义导出 功能
			function exportConfig() {
				//DCCLBH导出功能编号,执行导出函数 
				customExport(DCCLBH, bjExportData);
			}

			//导出方法
			function bjExportData() {
				setSearchTj();//设置高级查询条件
				var url = "xpj_zcfs.do?method=exportDataDc&dcclbh=" + DCCLBH+"&yf="+jQuery("#yf").val()+"&tjzt="+jQuery("#tjzt").val();//dcclbh,导出功能编号
				url = addSuperSearchParams(url);//设置高级查询参数
				jQuery("form").eq(0).attr("action", url);
				jQuery("form").eq(0).submit();
			}
						
			
		</script>
	</head>

	<body>
		<logic:present name="zcxmList">
			<logic:iterate id="z" name="zcxmList">
				<font style="display:none;" xmdm="${z.xmdm }"  zdfs="${z.zdfs }" zxfs="${z.zxfs }"
				      xmmc="${z.xmmc }" xmlx="${z.xmlx }" jktb="${z.jktb }" name="zcxm"></font>
			</logic:iterate>
		</logic:present>
		<input type="hidden" value="${cssz.xn }" id="xn"/>
		<input type="hidden" value="${cssz.xq }" id="xq"/>
		<input type="hidden" value="${szyf}"  id="szyf"/>
		<input type="hidden" value="${tjzt}" id="tjzt"/>
		<input type="hidden" value="${yf}" id="yf"/>
		
		<div class="tab_cur">
		</div>
		<html:form action="/xpj_zcfs" >
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<!-- 提示信息 end-->
			<div id="div_help" class="prompt" style="display: none">
				<h3>
					<span>提示：</span>
				</h3>
				<p>
					<span>
						按班级调整<font color="red">评奖人员名单</font>，维护参加评奖学生的<font color="red">综测成绩</font>
					</span>
				</p>
				<a class="close" title="隐藏"
				   onclick="this.parentNode.style.display='none';"></a>
			</div>
			<!-- 提示信息 end-->
			
			<div class="toolbox">
				<!-- 按钮 -->
				<logic:equal name="writeAble" value="yes">	
					<div class="buttonbox">
						<ul>
							<logic:equal value="true" name="cssz" property="kgzt">
							</logic:equal>
							<li><a href="javascript:void(0);" onclick="exportConfig();" class="btn_dc">导出</a></li>		
						</ul>
					</div>
				</logic:equal>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		<div class="formbox">
			<!--标题start-->
			<h3 class="datetitle_01">
				<span><font color="blue">${cssz.zqmc}&nbsp;</font>综测班级列表 </span>
			</h3>

			<table id="dataTable" ></table>
			<div id="pager"></div>

		</div>
	</body>
</html>
