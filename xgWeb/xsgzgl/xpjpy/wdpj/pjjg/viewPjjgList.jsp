<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/xpjpy/wdpj/pjjg/js/pjjg.js"></script>
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
			 	jQuery.post("xpj_pjjg.do?method=cxRyzs",{csdm:csdm},function(data){
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
								jQuery.post("xpj_pjjg.do?method=bcRyzs",{csdm:csdm, csz:csz},function(data){
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
					 jQuery.post("xpj_pjjg.do?method=dyRyzs",{csdm:csdm, value:ids+""},function(data){
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
					caption:"评奖结果列表",
					pager:"pager",
					url:"xpj_pjjg.do?method=viewPjjgList&type=query&xzdm=${xzdm}",
					colList:[
						{label:'key',name:'id', index: 'id',key:true ,hidden:true},
						{label:'学号',name:'xh', index: 'xh',width:'13%',formatter:xhLink },
						{label:'姓名',name:'xm', index: 'xm',width:'10%'},
						{label:'性别',name:'xb', index: 'xb',width:'3%'},
						{label:'sjly',name:'sjly', index: 'sjly',hidden:true},
						{label:'年级',name:'nj', index: 'nj',width:'7%'},
						<logic:equal name="xxdm" value="10466">
		   					{label:'学院',name:'xymc',index:'xymc',width:'13%'},
		   				</logic:equal>
                        {label:'书院',name:'symc', index: 'sydm',width:'13%'},
						{label:'行政班级',name:'bjmc', index: 'bjdm',width:'13%'},
                        {label:'专业班级',name:'zybjmc', index: 'zybjmc',width:'13%'},
						<logic:equal name="xxdm" value="10466">
							{label:'宿舍号',name:'qsh',index:'qsh',width:'10%'},
						</logic:equal>
						<logic:equal name="xxdm" value="10264">
				   			{label:'文体骨干生',name:'sfwtgg',index:'sfwtgg',width:'13%'},
				   		</logic:equal>
						{label:'评奖周期',name:'pjzq', index: 'pjzq',width:'13%'},
						{label:'项目名称',name:'xmmc', index: 'xmmc',width:'16%'},
						{label:'金额',name:'xmje', index: 'xmje',width:'11%'}
					],
					sortname: "xn,xq,sqsj",
				 	sortorder: "desc"
				};
		  var gridSetting2 = {
					caption:"评奖结果列表",
					pager:"pager",
					url:"xpj_pjjg.do?method=viewPjjgList&type=query",
					colList:[
						{label:'key',name:'id', index: 'id',key:true ,hidden:true},
						{label:'学号',name:'xh', index: 'xh',width:'13%',formatter:xhLink },
						{label:'姓名',name:'xm', index: 'xm',width:'10%'},
						{label:'性别',name:'xb', index: 'xb',width:'3%'},
						{label:'sjly',name:'sjly', index: 'sjly',hidden:true},
						{label:'所在大队',name:'ddmc', index: 'ddmc'},
						{label:'年级',name:'nj', index: 'nj',width:'7%'},
						{label:'班级',name:'bjmc', index: 'bjdm',width:'28%'},
						{label:'评奖周期',name:'pjzq', index: 'pjzq',width:'13%'},
						{label:'项目名称',name:'xmmc', index: 'xmmc',width:'16%'},
						{label:'金额',name:'xmje', index: 'xmje',width:'11%'}
					],
					sortname: "xn,xq,sqsj",
				 	sortorder: "desc"
				};
			
		  var gridSetting3 = {
					caption:"评奖结果列表",
					pager:"pager",
					url:"xpj_pjjg.do?method=viewPjjgList&type=query",
					colList:[
						{label:'key',name:'id', index: 'id',key:true ,hidden:true},
						{label:'学号',name:'xh', index: 'xh',width:'13%',formatter:xhLink },
						{label:'姓名',name:'xm', index: 'xm',width:'10%'},
						{label:'性别',name:'xb', index: 'xb',width:'3%'},
						{label:'sjly',name:'sjly', index: 'sjly',hidden:true},
						{label:'年级',name:'nj', index: 'nj',width:'7%'},
						{label:'班级',name:'bjmc', index: 'bjdm',width:'28%'},
						<logic:equal name="xxdm" value="10264">
				   			{label:'文体骨干生',name:'sfwtgg',index:'sfwtgg',width:'13%'},
				   		</logic:equal>
						{label:'评奖周期',name:'pjzq', index: 'pjzq',width:'13%'},
						{label:'项目名称',name:'xmmc', index: 'xmmc',width:'16%'},
						{label:'金额',name:'ylzd1', index: 'ylzd1',width:'11%'}
					],
					sortname: "xn,xq,sqsj",
				 	sortorder: "desc"
				};
			
			
			jQuery(function(){
				var xxdm = jQuery("#xxdm").val();
				if(xxdm=="11483"){
					gridSetting2["params"]=getSuperSearch();
					jQuery("#dataTable").initGrid(gridSetting2);
				} else if(xxdm=="11488"){
					gridSetting3["params"]=getSuperSearch();
					jQuery("#dataTable").initGrid(gridSetting3);
				}else {
					gridSetting1["params"]=getSuperSearch();
					jQuery("#dataTable").initGrid(gridSetting1);
				}
			});

			
			function getWord(){
				var rows = jQuery("#dataTable").getSeletRow();
				
				 if (rows.length == 0){
					showAlertDivLayer("请选择您要下载的记录！");
				 } else if (rows.length > 1){
					 var ids = jQuery("#dataTable").getSeletIds();
					 post('xpj_pjjg.do?method=getDjbZip', {value:ids});
				 } else {
					var url="xpj_pjjg.do?method=getDjbWord&id="+rows[0]["id"];
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
					var url="xpj_pjjg.do?method=getRyzsJxjZip&value="+ids+"&dytype="+dytype;
					window.open(url);
				 } else {
					var url="xpj_pjjg.do?method=getRyzsJxjWord&id="+rows[0]["id"]+"&dytype="+dytype;
					window.open(url);
			     }
			}
			// 江西科技师范大学 荣誉证书、奖学金打印
			function ryzsJxjDy_11318(dytype_11318){
				var rows = jQuery("#dataTable").getSeletRow();
				
				 if (rows.length == 0){
					showAlertDivLayer("请选择您要打印的记录！");
				 } else if (rows.length > 1){
					var ids = jQuery("#dataTable").getSeletIds();
					var url="xpj_pjjg.do?method=getRyzsJxjZip_11318&value="+ids+"&dytype_11318="+dytype_11318;
					window.open(url);
				 } else {
					var url="xpj_pjjg.do?method=getRyzsJxjWord_11318&id="+rows[0]["id"]+"&dytype_11318="+dytype_11318;
					window.open(url);
			     }
			}
			// 生成优秀奖学金（浙江大学）
			function scyxjxj(){
				var tips = loading("正在生成优秀奖学金,请耐心等待");
				try{ // 解决：先增加，关闭增加窗口，再提交时等待条无法关闭
					tips.show();
				}catch(e){
				}
				jQuery.post("xpj_pjjg.do?method=scyxjxj",{},function(data){
					setTimeout(function(){
						tips.close();
						showAlertDivLayer(data["message"]);
						jQuery("#dataTable").reloadGrid();
					}, 30000);
				},'json');
			}

			// 上海海洋大学证明打印
			function ryzsJxjDy_10264(){
				var rows = jQuery("#dataTable").getSeletRow();
				
				 if (rows.length == 0){
					showAlertDivLayer("请选择您要打印的记录！");
				 } else {
					var ids = jQuery("#dataTable").getSeletIds();
					var url="xpj_pjjg.do?method=ryzsJxjDy_10264&value="+ids;
					window.open(url);
				 } 
			}
			//===北京林业大学评奖登记表个性化begin===
			function getPjdjb() {
				var rows = jQuery("#dataTable").getSeletRow();
				var ids = '';
				var user = jQuery("#userType").val();
				if(user != "stu"){
					if(checkSearch_10022()){
						var xn = jQuery("a[name=a_name_xn]").eq(0).attr("id").replace("a_id_","");
						if (rows.length == 1) {
							var url = "xpj_pjjg.do?method=getPjdjb";					
							var xn = jQuery("a[name=a_name_xn]").attr("id").replace("a_id_","");
							url += "&xh=" + rows[0]["xh"];
							url += "&xn=" + xn;
							window.open(url);
						} else if (rows.length == 0) {
							showAlertDivLayer("请选择您要打印的记录！");
							return false;
						} else {
							for(var i=0;i<rows.length;i++){
								ids = ids+rows[i]['xh']+',';
							}
							var url = "xpj_pjjg.do?method=getPjdjbZip&value="+ids;
							url += "&xn=" + xn;
							window.open(url);
						}
					}
				}else{
					if (rows.length == 1) {
						var url = "xpj_pjjg.do?method=getPjdjb&xn="+rows[0]["pjzq"].substr(0,9)
						url += "&xh=" + rows[0]["xh"];
						window.open(url);
					} else{
						showAlertDivLayer("请选择一条您要打印的记录！");
					}
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

			//山东畜牧兽医职业学院（社会奖学金汇总表）
			function getshjxjHzexcel(){
				setSearchTj();
				var xmlb = '';
				jQuery("a[name='tj_xxmlx'].selectedValue").each(function(){
					xmlb = xmlb+jQuery(this).text()+',';
				})
				var value = xmlb.split(",");
				if(value.length != 2  ){
					showAlertDivLayer("请选择一个项目类型，请确认！");
					return false;
				}
				var url="xpj_pjjg.do?method=getSdxm_shjxjhzexcel&value="+xmlb+"&path=pj_pjjg.do";
				addSuperSearchParams(url);
				document.forms[0].action = url;
				document.forms[0].submit();
			}

			//山东畜牧兽医职业学院（省励志奖学金汇总表）
			function getslzjxjHzexcel(){
				var rows = jQuery("#dataTable").getSeletRow();
				
				 if (rows.length == 0){
					showAlertDivLayer("请选择您要打印的记录！");
				 } else {
					var ids = jQuery("#dataTable").getSeletIds();
					var url="xpj_pjjg.do?method=getSdxm_slzjhzexcel&value="+ids;
					window.open(url);
				 } 
			}



			//山东畜牧推荐表打印
			function getSdxmTjb(){
			 	setSearchTj();
				var xmmc = '';
				jQuery("a[name='a_xmmc_mc'].selectedValue").each(function(){
					xmmc = xmmc+jQuery(this).text()+',';
				})
				var value = xmmc.split(",");
				if(value.length != 2  ){
					showAlertDivLayer("请选择一个项目名称，请确认！");
					return false;
				}
				var url="xpj_pjjg.do?method=getSdxmTjb&value="+xmmc+"&path=pj_pjjg.do";
				addSuperSearchParams(url);
				document.forms[0].action = url;
				document.forms[0].submit();
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
				var url="xpj_pjjg.do?method=getHzbPrint";
				url = addSuperSearchParams(url);
				document.forms[0].action = url;
				document.forms[0].submit();
			}
			function fw(){
				showDialog("名单发布", 500, 200, "xpj_pjjg.do?method=mdfb");
			}

			//表彰名册导出 (南京高等职业技术学院)
			function bzmcdc(){
				setSearchTj();
				var xn_num = jQuery("a[name=a_name_xn]").length;
				var xq_num = jQuery("a[name=a_name_xq]").length;
				//var xy_num = jQuery("a[name=a_name_xy]").length;

				if(xn_num != 1){
					showAlertDivLayer("必须选择一个学年，且只能选择一个！");
				}else if (xq_num != 1){
					showAlertDivLayer("必须选择一个学期，且只能选择一个！ ");
				}
<%--				else if (Number(xy_num) > 1){--%>
<%--					showAlertDivLayer("只能选择一个学院进行表彰名单导出！ ");--%>
<%--				}--%>
				else{
					var flg = true;
					var yzUrl = 'xpj_pjjg.do?method=yzbzmddc';
					jQuery("form").eq(0).attr("id","pjjgForm");
					
					jQuery.ajaxSetup({async:false});
						ajaxSubFormWithFun("pjjgForm", yzUrl, function(data) {
							if(data["result"] != true){
								flg = false;
							}
						});
						if(!flg){
							showAlertDivLayer("无表彰名单可以导出，请重新选择学院过滤！");
							return false;
						}
					jQuery.ajaxSetup({async:true});
					
					var url = "xpj_pjjg.do?method=bzmddc";
					url = addSuperSearchParams(url);//设置高级查询参数	
					jQuery("form").eq(0).attr("action", url);
					jQuery("form").eq(0).submit();
				}
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
				var url = "xpj_pjjg.do?method=jxjzhhzb";//dcclbh,导出功能编号
				url = addSuperSearchParams(url);//设置高级查询参数
				jQuery("form").eq(0).attr("action", url);
				jQuery("form").eq(0).submit();
			}
			// 苏州卫生职业技术学院财务用表打印
			function cwyb(){
					//var url ="rcsw_rcxwwh_rcxwjggl.do?method=sxpdcjhzDc";
					var url="xpj_pjjg.do?method=cwybPrint";
					
					var xnLength=jQuery("a[name=a_name_xn]").length;
					var xmmcLength=jQuery("a[name=a_name_xmmc]").length;
					
					if(xnLength != "1"){
						showAlertDivLayer("请选择一个学年查询条件！");
						return false;
					}
					if(xmmcLength < "1"){
						showAlertDivLayer("请至少选择一个项目名称查询条件！");
						return false;
					}
					setSearchTj();
					url = addSuperSearchParams(url);
					document.forms[0].action = url;
					document.forms[0].submit();
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
		<html:form action="/xpj_pjjg">
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
						
						<li><a href="#" onclick="drxxNew();return false;" class="btn_dr">导入</a></li>
				</logic:equal>
				<logic:equal value="00000" name="xxdm">
					<li><a href="#" onclick="fw();return false;" class="btn_xg">发文名单公示</a></li>
				</logic:equal>
				
				<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">导出</a></li>
				
				<logic:equal value="10279" name="xxdm">
				<li><a href="#" class="btn_dc" onclick="getHzbPrint();return false;">导出汇总表</a></li>
				</logic:equal>
				<!--内蒙古电子-->
				<logic:equal value="12673" name="xxdm">
					<li><a href="javascript:void(0);" onclick="jxjzhhzb()" class="btn_dy">获奖汇总导出</a></li>
				</logic:equal>
				<!-- 浙江大学不显示下载等级表 -->
				<logic:notEqual value="10335" name="xxdm">
				<logic:notEqual value="10022" name="xxdm">
					<li><a href="javascript:void(0);" onclick="getWord();return false;" class="btn_down">下载登记表</a></li>	
				</logic:notEqual>
				</logic:notEqual>
				<!-- 10022北京林业大学个性化 -->
				<logic:equal name="xxdm" value="10022">
				<logic:notEqual value="stu" name="userType">
					<li><a href="javascript:void(0);" onclick="getWord();return false;" class="btn_down">下载登记表</a></li>	
				</logic:notEqual>
				<li><a href="javascript:void(0);" onclick="getPjdjb();return false;" class="btn_down">评优登记表</a></li>
				</logic:equal>
				<!-- 江西科技师范大学 begin -->
				<logic:equal name="xxdm" value="11318">	
					<li><a href="javascript:void(0);" onclick="ryzsJxjDy_11318('ryzs');return false;" class="btn_dy">打印荣誉证书</a></li>
					<li><a href="javascript:void(0);" onclick="ryzsJxjDy_11318('jxj');return false;" class="btn_dy">打印奖学金</a></li>
				</logic:equal>
				<!-- 江西科技师范大学 end -->
				<!-- 重庆工商大学 begin -->
				<logic:equal name="xxdm" value="11799">	
					<li><a href="javascript:void(0);" onclick="ryzsJxjDy('ryzs');return false;" class="btn_dy">打印荣誉证书</a></li>
					<li><a href="javascript:void(0);" onclick="ryzsJxjDy('jxj');return false;" class="btn_dy">打印奖学金</a></li>
				</logic:equal>
				<!-- 重庆工商大学 end -->
				
				<!-- 上海海洋大学 begin -->
				<logic:equal name="xxdm" value="10264">	
					<li><a href="javascript:void(0);" onclick="ryzsJxjDy_10264();return false;" class="btn_dy">证明打印</a></li>
				</logic:equal>
				<!-- 上海海洋大学 end -->
				<!-- 苏州卫生职业技术学院-begin -->
				<logic:equal name="xxdm" value="12688">	
					<li><a href="javascript:void(0);" onclick="cwyb();return false;" class="btn_dy">财务用表打印</a></li>
				</logic:equal>
				<!-- 苏州卫生职业技术学院 end -->
				
				<!-- 荣誉证书 begin -->
				<logic:equal name="ryzsFlag" value="true">	
                <%--<li><a href="javascript:void(0);" onclick="szRyzsCommon('ryzs_${xxdm }');return false;" class="btn_dy">设置荣誉证书（仅开发用）</a></li>--%>
					<li><a href="javascript:void(0);" onclick="dyRyzsCommon('ryzs_${xxdm }');return false;" class="btn_dy">打印荣誉证书</a></li>
				</logic:equal>
				<!-- 荣誉证书 end -->
				<!-- 生成优秀奖学金（浙江大学） begin -->
				<logic:equal name="xxdm" value="10335">
					<logic:equal value="true" name="cssz" property="kgzt">
						<logic:equal value="xy" name="userType">
							<li><a href="javascript:void(0);" onclick="scyxjxj();return false;" class="btn_tj">生成优秀奖学金</a></li>
						</logic:equal>
					</logic:equal>
				</logic:equal>
				<!-- 生成优秀奖学金（浙江大学） end -->
				<logic:equal name="writeAble" value="yes">	
					<li><a href="javascript:void(0);" onclick="hjwhDr();return false;" class="btn_dr">获奖文号导入</a></li>
				</logic:equal>
				<!-- 南京高等职业技术学院 表彰名册导出 -->
				<logic:equal value="10874" name="xxdm">
					<li><a href="javascript:void(0);" onclick="bzmcdc();return false;" class="btn_dc">表彰名册导出</a></li>
				</logic:equal>
				</ul>
				</div>
				<!-- 山东畜牧兽医 begin -->
				<logic:equal name="xxdm" value="12947">	
				<div>
					<ul class="buttonbox">
							<li><a href="javascript:void(0);" onclick="getSdxmTjb();return false;" class="btn_dy">推荐表打印</a></li>
							<%-- 
							<li><a href="javascript:void(0);" onclick="getYxXsTjb();return false;" class="btn_dy">优秀学生推荐表打印</a></li>
							<li><a href="javascript:void(0);" onclick="getYxXsGbTjb();return false;" class="btn_dy">优秀学生干部推荐表打印</a></li>
							--%>
							<li><a href="javascript:void(0);" onclick="getshjxjHzexcel();return false;" class="btn_dc">社会奖学金汇总导出</a></li>
							<%-- 
							<li><a href="javascript:void(0);" onclick="getslzjxjHzexcel();return false;" class="btn_dc">省励志奖学金汇总导出</a></li>--%>
					</ul>
				</div>
				</logic:equal>
				<!-- 山东畜牧兽医 end -->
				
			
				<!--内蒙古电子end -->
				
				
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
			
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>评奖结果</span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
