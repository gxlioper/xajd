<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/bzjl/wdbzjl/bzjljg/js/bzjljg.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script><!-- 导入功能需要 -->
		<script language="javascript" src="js/LodopFuncs.js"></script>
		<object  id="LODOP_OB" classid="clsid:2105C259-1E0C-4534-8141-A753534CB4CA" width=0 height=0> 
		       <embed id="LODOP_EM" type="application/x-print-lodop" width=0 height=0></embed>
		</object>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript">
			// 设置模板
			function szRyzsCommon(csdm) {
			 	jQuery.post("bzjl_bzjg.do?method=cxRyzs",{csdm:csdm},function(data){
				 	 var csz = data["csz"];
					 if(csz == null){
						 showAlertDivLayer("模板未初始化！");
						 return false;
					 }else{
						// 初始化组件
						var LODOP=getLodop(document.getElementById('LODOP_OB'),document.getElementById('LODOP_EM'));
						LODOP.PRINT_INIT("打印");
						// 布局模板元素
						eval(csz); 
						// 设置返回值
						LODOP.SET_PRINT_MODE("PRINT_SETUP_PROGRAM",true);
						
						//var csz = LODOP.PRINT_SETUP(); // 普通用户模式（只能调整模板内容的位置）
						var csz = LODOP.PRINT_DESIGN(); // 开发人员模式（随意修改模板内容）
						
						confirmInfo("是否保存模板？",function(data){
							if("ok"==data){
								jQuery.post("bzjl_bzjg.do?method=bcRyzs",{csdm:csdm, csz:csz},function(data){
									showAlertDivLayer(data["message"]);
								},'json');
							}
						});
					 }
				},'json');
			}
			// 打印模板
			function dyRyzsCommon(csdm) {
				 var rows = jQuery("#dataTable").getSeletRow();
				
				 if (rows.length == 0){
					showAlertDivLayer("请选择您要打印的记录！");
				 } else {
					 var ids = jQuery("#dataTable").getSeletIds();
					 jQuery.post("bzjl_bzjg.do?method=dyRyzs",{csdm:csdm, value:ids+""},function(data){
							 var csz = data["csz"];
							 if(csz == null){
								 showAlertDivLayer("模板未初始化！");
								 return false;
							 }else{
								// 初始化组件
								var LODOP=getLodop(document.getElementById('LODOP_OB'),document.getElementById('LODOP_EM'));
								LODOP.PRINT_INIT("打印");
								// 布局模板元素
								eval(csz);
								// 打印预览
								LODOP.PREVIEW();
							 }
						},'json');
			     }
			}

		  var gridSetting1 = {
					caption:"表彰奖励结果列表",
					pager:"pager",
					url:"bzjl_bzjg.do?method=viewBzjljgList&type=query",
					colList:[
						{label:'key',name:'id', index: 'id',key:true ,hidden:true},
						{label:'学号',name:'xh', index: 'xh',width:'13%',formatter:xhLink },
						{label:'姓名',name:'xm', index: 'xm',width:'10%'},
						{label:'性别',name:'xb', index: 'xb',width:'3%'},
						{label:'sjly',name:'sjly', index: 'sjly',hidden:true},
						{label:'年级',name:'nj', index: 'nj',width:'7%'},
						{label:'行政班级',name:'bjmc', index: 'bjdm',width:'13%'},
                        {label:'专业班级',name:'zybjmc', index: 'zybjmc',width:'13%'},
						{label:'评奖周期',name:'pjzq', index: 'pjzq',width:'13%'},
						{label:'项目名称',name:'xmmc', index: 'xmmc',width:'16%'},
						{label:'金额',name:'xmje', index: 'xmje',width:'11%'}
					],
					sortname: "xn,xq,sqsj",
				 	sortorder: "desc"
				};
			
			jQuery(function(){

				gridSetting1["params"]=getSuperSearch();
				jQuery("#dataTable").initGrid(gridSetting1);
			});

			
			function getWord(){
				var rows = jQuery("#dataTable").getSeletRow();
				
				 if (rows.length == 0){
					showAlertDivLayer("请选择您要下载的记录！");
				 } else if (rows.length > 1){
					 var ids = jQuery("#dataTable").getSeletIds();
					 post('bzjl_bzjg.do?method=getDjbZip', {value:ids});
				 } else {
					var url="bzjl_bzjg.do?method=getDjbWord&id="+rows[0]["id"];
					url = addSuperSearchParams(url);
					document.forms[0].action = url;
					document.forms[0].submit();
			     }
			}

			function post(URL, PARAMS) {        
			    var temp = document.createElement("form");        
			    temp.action = URL;        
			    temp.method = "post";        
			    temp.style.display = "none";        
			    for (var x in PARAMS) {        
			        var opt = document.createElement("textarea");        
			        opt.name = x;        
			        opt.value = PARAMS[x];        
			        // alert(opt.name)         
			        temp.appendChild(opt);        
			    }        
			    document.body.appendChild(temp);        
			    temp.submit();        
			    return temp;        
			}        

			function drxx(){
				toImportData("IMPORT_N100102");
				return false;
			}
			function drxxNew(){
				toImportDataNew("IMPORT_N100102");
				return false;
			}

			// 荣誉证书、奖学金打印
			function ryzsJxjDy(dytype){
				var rows = jQuery("#dataTable").getSeletRow();
				
				 if (rows.length == 0){
					showAlertDivLayer("请选择您要打印的记录！");
				 } else if (rows.length > 1){
					var ids = jQuery("#dataTable").getSeletIds();
					var url="bzjl_bzjg.do?method=getRyzsJxjZip&value="+ids+"&dytype="+dytype;
					window.open(url);
				 } else {
					var url="bzjl_bzjg.do?method=getRyzsJxjWord&id="+rows[0]["id"]+"&dytype="+dytype;
					window.open(url);
			     }
			}

			function checkSearch_10022(){
				var flag = true;
				var xn_num =  jQuery("a[name=a_name_xn]").length;
				if(xn_num != "1"){
					alertError("请选择一个学年！");
					flag = false;
				}
				return flag;
			}
			
			//导出汇总表
			function getHzbPrint(){
				var xn_num =  jQuery("a[name=a_name_xn]").length;
				if(xn_num != "1"){
					alertError("请选择一个学年！");
					return false;
				}
				var xmmc_num =  jQuery("a[name=a_name_xmmc]").length;
				if(xmmc_num != "1"){
					alertError("请选择一个项目名称！");
					return false;
				}
				setSearchTj();
				var url="bzjl_bzjg.do?method=getHzbPrint";
				url = addSuperSearchParams(url);
				document.forms[0].action = url;
				document.forms[0].submit();
			}
			function fw(){
				showDialog("名单发布", 500, 200, "bzjl_bzjg.do?method=mdfb");
			}

			//奖学金账号汇总表
			function jxjzhhzb(){
				var tjXnArray = jQuery("[name='tj_xn'].selectedValue");
				var tjXqArray = jQuery("[name='tj_xq'].selectedValue");
				if(tjXnArray.length != 1 || tjXqArray.length !=1){
					showAlertDivLayer("学年、学期只能必须各选择一个！ ");
					return false;
				}
				setSearchTj();//设置高级查询条件
				var url = "bzjl_bzjg.do?method=jxjzhhzb";//dcclbh,导出功能编号
				url = addSuperSearchParams(url);//设置高级查询参数
				jQuery("form").eq(0).attr("action", url);
				jQuery("form").eq(0).submit();
			}
		</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;"	onmousedown ="showHelpDiv();" >使用帮助</a>
			</p>
		</div>
		<html:form action="/bzjl_bzjg">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" id="pjzq" name="pjzq" value="${pjzq}"/>
			<input type="hidden" id="xzdm" name="xzdm" value="${xzdm}"/>
			<!-- 提示信息 end-->
			<div id="div_help" class="prompt" style="display: none">
				<h3>
					<span>提示：</span>
				</h3>
				<p>
					<span>
						对于未经申请审核流程而获奖的学生，可在此处直接进行数据维护
					</span>
				</p>
				<a class="close" title="隐藏"
				   onclick="this.parentNode.style.display='none';"></a>
			</div>
			<!-- 提示信息 end-->
			
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
				<ul>
				<logic:equal name="writeAble" value="yes">	
				
					
						<li><a href="javascript:void(0);" onclick="add();" class="btn_zj">增加</a></li>
						<li><a href="javascript:void(0);" onclick="update();" class="btn_xg">修改</a></li>
						<li><a href="javascript:void(0);" onclick="del();" class="btn_sc">删除</a></li>						
						
						<%--<li><a href="#" onclick="drxxNew();return false;" class="btn_dr">导入</a></li>--%>
				</logic:equal>

				<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">导出</a></li>
				

				</ul>
				</div>

				<!-- 过滤条件 -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
			
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>表彰奖励结果</span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
