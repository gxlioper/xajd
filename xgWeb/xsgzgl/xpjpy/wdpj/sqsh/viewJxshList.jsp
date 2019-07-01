<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/xpjpy/wdpj/sqsh/js/jxsh.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.cookie.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript">
		/**
		 * 获取待处理表格参数
		 * @returns {___anonymous54_1173}
		 */
		function getDclGird(){
			
			var colList = [
			   {label:'key',name:'sqid', index: 'sqid',hidden:true,key:true},
			   {label:'学号',name:'xh', index: 'xh',width:'12%',formatter:function(cell,rowObject){
				   return "<a href='javascript:void(0);' onclick=\"showDialog('查看申请表',800,500,'xpj_sqsh.do?method=viewSqb&sqid="+rowObject["sqid"]+"');\" class='name'>"+cell+"</a>";
			   }},
			   {label:'姓名',name:'xm', index: 'xm',width:'10%'},
			   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xydm',width:'13%'},
                {label:'书院',name:'symc', index: 'sydm',width:'13%'},
                {label:'行政班级',name:'bjmc', index: 'bjmc',width:'13%'},
			   {label:'专业班级',name:'zybjmc', index: 'zybjmc',width:'13%'},
			   <logic:equal name="xxdm" value="10466">
					{label:'宿舍号',name:'qsh',index:'qsh',width:'10%'},
				</logic:equal>
			   {label:'申请奖项',name:'xmmc', index: 'xmdm',width:'12%'},
			   {label:'调整奖项',name:'tzxmmc', index: 'tzxmmc',width:'12%'},
			   {label:'奖项金额',name:'xmje', index: 'xmje',width:'10%'},
			   {label:'申请时间',name:'sqsj', index: 'sqsj',width:'19%'}
			];
	 	   if(jQuery('#xxdm').val() == '10264'){//上海海洋 显示文体骨干  10264  文体骨干生通过特殊学生导入
	 		  colList.push({label:"文体骨干生",name:"sfwtgg",index:"sfwtgg",idth:'13%'})
	 	   }
	 	   if(jQuery('#xxdm').val() == '13627'){//重庆邮电大学 个性化添加辅导员列
	 		  colList.push({label:"辅导员",name:"fdyxm",index:"fdyxm",idth:'10%'})
	 	   }

			var zcxm = jQuery("[name=zcxm]");
			jQuery.each(zcxm,function(i,n){
				var checked = jQuery(n).prop("checked");
				var xmfsJson = {
						label:jQuery(n).attr("xmmc"),
						name:"fs"+i,
						index:"fs"+i,
						hidden:(!checked)
				};
				var xmpmJson = {
						label:"排名",
						name:"pm"+i,
						index:"pm"+i,
						hidden:(!checked)
				};
				colList.push(xmfsJson);
				colList.push(xmpmJson);
				
			});

			if(jQuery("#xxdm").val() == '11527'){
				var zfJson = {
                    label:'综测总分',
                    name:'zf',
                    index:'zf'
				};
				var bjpmJson = {
                    label:'班级排名',
                    name:'bjpm',
                    index:'bjpm'
				};
				var zypmJson = {
                    label:'专业排名',
                    name:'zypm',
                    index:'zypm'
				};
				var xypmJson = {
                    label:'学院排名',
                    name:'xypm',
                    index:'xypm'
				};
                colList.push(zfJson);
                colList.push(bjpmJson);
                colList.push(zypmJson);
                colList.push(xypmJson);
			}


			colList.push({label:'shid',name:'shid', index: 'shid',hidden:true});
			colList.push({label:'splc',name:'splc', index: 'splc',hidden:true});
			colList.push({label:'gwid',name:'gwid', index: 'gwid',hidden:true});
			colList.push({label:'审核状态',name:'shztmc', index: 'shztmc',width:'13%'});
			
			return {
				caption:"申请学生列表 ",
				pager:"pager",
				url:"xpj_sqsh.do?method=viewJxshList&type=query&xzdm="+"${xzdm}",
				colList:colList,
				sortname:"nj",
				sortorder:"asc",
				radioselect:false
			};
		}

		 /**
		  * 获取已处理表格参数
		  * @returns {___anonymous1466_2571}
		  */
		 function getYclGrid(){
		 	
		 	var colList = [
		 	   {label:'key',name:'sqid', index: 'sqid',hidden:true,key:true},
		 	   {label:'学号',name:'xh', index: 'xh',width:'12%',formatter:function(cell,rowObject){
		 		   return "<a href='javascript:void(0);' onclick=\"showDialog('查看申请表',800,500,'xpj_sqsh.do?method=viewSqb&sqid="+rowObject["sqid"]+"');\" class='name'>"+cell+"</a>";
		 	   }},
		 	   {label:'姓名',name:'xm', index: 'xm',width:'10%'},
		 	   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xydm',width:'13%'},
                {label:'书院',name:'symc', index: 'sydm',width:'13%'},
                {label:'行政班级',name:'bjmc', index: 'bjmc',width:'13%'},
                {label:'专业班级',name:'zybjmc', index: 'zybjmc',width:'13%'},
		 	    <logic:equal name="xxdm" value="10466">
					{label:'宿舍号',name:'qsh',index:'qsh',width:'10%'},
				</logic:equal>
		 	   {label:'申请奖项',name:'xmmc', index: 'xmdm',width:'13%'},
		 	   {label:'调整奖项',name:'tzxmmc', index: 'tzxmmc',width:'12%'},
		 	   {label:'奖项金额',name:'xmje', index: 'xmje',width:'10%'}
		 	];

	 	   if(jQuery('#xxdm').val() == '10264'){//上海海洋 显示文体骨干 是通过特殊学生导入的
	 		  colList.push({label:"文体骨干生",name:"sfwtgg",index:"sfwtgg",idth:'13%'})
	 	   }
	 	  if(jQuery('#xxdm').val() == '13627'){//重庆邮电大学 个性化添加辅导员列
	 		  colList.push({label:"辅导员",name:"fdyxm",index:"fdyxm",idth:'10%'})
	 	   }
		 	var zcxm = jQuery("[name=zcxm]");
		 	jQuery.each(zcxm,function(i,n){
		 		var checked = jQuery(n).prop("checked");
		 		var xmfsJson = {
		 				label:jQuery(n).attr("xmmc"),
		 				name:"fs"+i,
		 				index:"fs"+i,
		 				hidden:(!checked)
		 		};
		 		var xmpmJson = {
		 				label:"排名",
		 				name:"pm"+i,
		 				index:"pm"+i,
		 				hidden:(!checked)
		 		};
		 		colList.push(xmfsJson);
		 		colList.push(xmpmJson);
		 		
		 	});

             if(jQuery("#xxdm").val() == '11527'){
                 var zfJson = {
                     label:'综测总分',
                     name:'zf',
                     index:'zf'
                 };
                 var bjpmJson = {
                     label:'班级排名',
                     name:'bjpm',
                     index:'bjpm'
                 };
                 var zypmJson = {
                     label:'专业排名',
                     name:'zypm',
                     index:'zypm'
                 };
                 var xypmJson = {
                     label:'学院排名',
                     name:'xypm',
                     index:'xypm'
                 };
                 colList.push(zfJson);
                 colList.push(bjpmJson);
                 colList.push(zypmJson);
                 colList.push(xypmJson);
             }

		 	colList.push({label:'审核时间',name:'shsj', index: 'shsj',width:'19%'});
		 	colList.push({label:'shid',name:'shid', index: 'shid',hidden:true});
		 	colList.push({label:'splc',name:'splc', index: 'splc',hidden:true});
		 	colList.push({label:'gwid',name:'gwid', index: 'gwid',hidden:true});
		 	colList.push({label:'xmdm',name:'xmdm', index: 'xmdm',hidden:true});
		 	colList.push({label:'审核状态',name:'shztmc', index: 'shztmc',width:'13%'});
		 	
		 	return {
		 		caption:"申请学生列表 ",
		 		pager:"pager",
		 		url:"xpj_sqsh.do?method=viewJxshList&type=query&xzdm="+"${xzdm}",
		 		colList:colList,
		 		sortname:"nj",
		 		sortorder:"asc",
		 		radioselect:false
		 	};
		 }
			jQuery(function(){
				
				jQuery("[name=zcxm]:not(:disabled)").bind("click",function(){
					var index = jQuery(this).attr("index");
					var liName = jQuery("#tabUl li.ha").attr("clzt");
					
					if ("dcl" == liName){
						var gridSetting = getDclGird();
						jQuery("#dataTable").initGrid(gridSetting);
					} else {
						var gridSetting = getYclGrid();
						jQuery("#dataTable").initGrid(gridSetting);
					}
				});
				
				loadCookie();
				
				var dclGrid = getDclGird();
				var map = getSuperSearch();
					map["shzt"] = "0";
					dclGrid["params"]=map;
				jQuery("#dataTable").initGrid(dclGrid);
			});
			
			//装载cookie
			function loadCookie(){
				var indexStr = jQuery.cookie("jxshGrid");
				if(indexStr != null && indexStr != undefined){
					var indexArray = indexStr.split("-");
					
					jQuery.each(indexArray,function(i,n){
						
						if (n != ""){
							jQuery("[name=zcxm][index="+n+"]").attr("checked",true);
						}
					});
				}
				
			}
			
			//设置cookie
			function setJxshCookie(){
				var chekedZcxm = jQuery("[name=zcxm]:checked:not(:disabled)");
				var indexStr = "";
				
				jQuery.each(chekedZcxm,function(i,n){
					var index = jQuery(n).attr("index");
					indexStr += index+"-";
				});
				jQuery.cookie("jxshGrid",indexStr, { expires: 7});
			}

			//浙江大学批量审核
			function zjdxPlsh(){
				
				var ids = jQuery("#dataTable").getSeletIds();
				var map = getSuperSearch();
				var jsonStr = JSON.stringify(map);
				showDialog("奖项审核",750,570,"xpj_sqsh.do?method=zjdxPlsh&id="+ids.toString()+"&jsonStr="+jsonStr);
			}

			/**
			*	下载登记表
			*/
			function getWord(){
				var rows = jQuery("#dataTable").getSeletRow();
				
				 if (rows.length == 0){
					showAlertDivLayer("请选择您要下载的记录！");
				 } else if (rows.length > 1){
					var ids = jQuery("#dataTable").getSeletIds();
					var url="xpj_pjxmsq.do?method=getDjbZip&value="+ids + "&actionFrom=sqsh";
					window.open(url);
				 } else {
					var url="xpj_pjxmsq.do?method=getDjbWord&sqid="+rows[0]["sqid"] + "&actionFrom=sqsh";
					window.open(url);
			     }
			}
			
			//导出汇总表
			function getHzbPrint(){
				var xn_num =  jQuery("a[name=a_name_xn]").length;
				if(xn_num != "1"){
					alertError("请选择一个学年！");
					return false;
				}
				var xmmc_num =  jQuery("a[name=a_name_pjsqxm]").length;
				if(xmmc_num != "1"){
					alertError("请选择一个项目名称！");
					return false;
				}
				setSearchTj();
				var url="xpj_sqsh.do?method=getHzbPrint";
				url = addSuperSearchParams(url);
				document.forms[0].action = url;
				document.forms[0].submit();
			}
			
			//浙江同济学生成绩汇总导出
			function xscjhzdc(){
				var url ="xpj_sqsh.do?method=xscjhzdc";
				
				var xnLength=jQuery("a[name=a_name_xn]").length;
				var xqLength=jQuery("a[name=a_name_xq]").length;
				var xyLength=jQuery("a[name=a_name_xy]").length;
				var bjLength=jQuery("a[name=a_name_bj]").length;
				
				if(xnLength != "1"){
					showAlertDivLayer("请选择一个学年查询条件！");
					return false;
				}
				if(xqLength != "1"){
					showAlertDivLayer("请选择一个学期查询条件！");
					return false;
				}
				if(xyLength != "1"){
					showAlertDivLayer("请选择一个学院查询条件！");
					return false;
				}
				setSearchTj();
				url = addSuperSearchParams(url);
				document.forms[0].action = url;
				document.forms[0].submit();
			}
			
			//浙江同济学生成绩汇总查看
			function xscjhzck(){
				var url ="xpj_sqsh.do?method=xscjhzck";
				
				var xnLength=jQuery("a[name=a_name_xn]").length;
				var xyLength=jQuery("a[name=a_name_xy]").length;
				var bjLength=jQuery("a[name=a_name_bj]").length;
				
				if(xnLength != "1"){
					showAlertDivLayer("请选择一个学年查询条件！");
					return false;
				}
				if(xyLength != "1" && bjLength == "0"){
					showAlertDivLayer("请选择一个学院查询条件！");
					return false;
				}
				setSearchTj();
				url = addSuperSearchParams(url);
				document.forms[0].target = '_blank';
				document.forms[0].action = url;
				document.forms[0].submit();
			}

			//南京高等职业技术学校
			function xsglmddc(){
				setSearchTj();
				var xn_num = jQuery("a[name=a_name_xn]").length;
				var xq_num = jQuery("a[name=a_name_xq]").length;
				var xy_num = jQuery("a[name=a_name_xy]").length;

				if(xn_num != "1"){
					showAlertDivLayer("必须选择一个学年，且只能选择一个！");
				}else if (xq_num != "1"){
					showAlertDivLayer("必须选择一个学期，且只能选择一个！ ");
				}else if (xy_num < 1){
					showAlertDivLayer("请至少选择一个学院进行导出！ ");
				}else{
					var flg = true;
					var yzUrl = 'xpj_sqsh.do?method=yzxsglmddc';
					
					jQuery.ajaxSetup({async:false});
						ajaxSubFormWithFun("zcxmForm", yzUrl, function(data) {
							if(data["result"] != true){
								flg = false;
							}
						});
						if(!flg){
							showAlertDivLayer("无表彰名单可以导出，请另外选择过滤条件 ");
							return false;
						}
					jQuery.ajaxSetup({async:true});
					
					var url = "xpj_sqsh.do?method=xsglmddc";
					url = addSuperSearchParams(url);//设置高级查询参数	
					jQuery("form").eq(0).attr("action", url);
					jQuery("form").eq(0).submit();
				}
			}
		</script>
	</head>

	<body onunload="setJxshCookie();">
	
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;"	onmousedown ="showHelpDiv()" >使用帮助</a>
			</p>
		</div>
	
		<html:form action="/xpj_sqsh" method="post" styleId="zcxmForm">
			<input type="hidden" id="xxdm" value="${xxdm }" />
			<input type="hidden" id="xbmc" value="<bean:message key="lable.xb" />"/>
			<input type="hidden" id="shzt" value="0"/>
			<html:hidden styleId="xzdm" property="xzdm" value="${xzdm}"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- 提示信息 end-->
			<div id="div_help" class="prompt" style="display: none">
				<h3>
					<span>提示：</span>
				</h3>
				<p>
					<span>
						建议先使用高级查询过滤评奖项目名称，如果一人多岗，建议批量选择同一岗位的记录进行批量审核。
					</span>
				</p>
				<a class="close" title="隐藏"
				   onclick="this.parentNode.style.display='none';"></a>
			</div>
			<!-- 提示信息 end-->
			
			<div class="toolbox">
					
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
							<logic:equal value="true" name="cssz" property="kgzt">
								<li id="li_sh">
									<logic:notEqual value="10335" name="xxdm">
										<a href="javascript:void(0);" onclick="viewJxsh();return false;" 
										   title="选中您要审核的记录，点击该按钮可以打开审核页面。"
										   class="btn_sh">审核</a>
									</logic:notEqual>
									<logic:equal value="10335" name="xxdm">
										<a href="javascript:void(0);" onclick="zjdxPlsh();return false;" 
										   title="点击查看审核情况汇总统计。"
										   class="btn_sh">批量审核</a>
									</logic:equal>
								</li>
								<li id="li_qx" style="display: none;">
									<a href="javascript:void(0);" onclick="cancelAuding();return false;" 
									   title="选中一条记录，点击该按钮您可以撤消失误的审核操作。"
									   class="btn_qxsh">撤消</a>
								</li>	
							</logic:equal>
							<li><a href="javascript:void(0);" onclick="viewLcgz();return false;" 
								   title="选中一条记录，点击该按钮可以查看审核流程。"
								   class="btn_cs">流程跟踪</a></li>
							<logic:equal value="10335" name="xxdm">
								<li><a href="javascript:void(0);" onclick="viewZdtj();return false;" 
									   title="选中一条记录，点击该按钮可以查看审核流程。"
									   class="btn_tj">奖项审核统计</a></li>
							</logic:equal>
							<logic:notEqual value="10335" name="xxdm">
								<li><a href="javascript:void(0);" onclick="viewShqk();return false;" 
									   title="点击查看审核情况汇总统计。"
									   class="btn_tj">审核统计</a></li>
							</logic:notEqual>
						</logic:equal>
						<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">导出</a></li>
						<logic:equal value="	10279" name="xxdm">
						<li><a href="#" class="btn_dc" onclick="getHzbPrint();return false;">导出汇总表</a></li>
						</logic:equal>
						<li><a href="javascript:void(0);" class="btn_down" onclick="getWord();return false;">下载登记表</a></li>
						<logic:equal value="12647" name="xxdm">
						<!-- <li><a href="#" class="btn_dc" onclick="xscjhzdc();return false;">学生成绩汇总导出</a></li> -->
						<li><a href="#" class="btn_ck" onclick="xscjhzck();return false;">学生成绩汇总查看</a></li>
						</logic:equal>
						<!-- 上海戏剧个性化 -->
						<logic:equal value="10279" name="xxdm">
							<li><a href="#" class="btn_down" onclick="fjdc();return false;">下载附件</a></li>
						</logic:equal>
						<!-- 南京高等职业技术学校 -->
						<logic:equal value="10874" name="xxdm">
							<li><a href="#" class="btn_down" onclick="xsglmddc();return false;">表彰审核下载</a></li>
						</logic:equal>
					</ul>
				</div>
				
			</div>
		
		<!-- 过滤条件 -->	
			<%@ include file="/comm/search/superSearchArea.jsp"%>
		<!-- 过滤条件 end-->
		<div class="comp_title" id="comp_title">
	      <ul style="width:90%" id="tabUl">
			<li class="ha" clzt="dcl"><a href="javascript:void(0);"  onclick="chageShzt(this,'0');"><span>待处理</span></a></li>
			<li clzt="ycl"><a href="javascript:void(0);"  onclick="chageShzt(this,'1');"><span>已处理</span></a></li>
	      </ul>
	    </div>
	    	 
		</html:form>
		<div class="formbox">
			<!--标题start-->
			<h3 class="datetitle_01">
				<span>奖项申请列表 </span>
			</h3>

			<logic:notEqual name="xxdm" value="11527">
				<logic:present name="zcxmList">
					<logic:iterate id="z" name="zcxmList" indexId="i">
						<logic:equal value="N" name="z" property="fjdm">
							<input type="checkbox" xmmc="${z.xmmc }" value="${z.xmdm }" checked="checked" disabled="disabled" name="zcxm"/> ${z.xmmc }
						</logic:equal>

						<logic:notEqual value="N" name="z" property="fjdm">
							<input type="checkbox" xmmc="${z.xmmc }" value="${z.xmdm }" name="zcxm" index="${i }"
							/> ${z.xmmc }
						</logic:notEqual>
					</logic:iterate>
				</logic:present>
			</logic:notEqual>

			<div class="con_overlfow">
			<table id="dataTable"></table>
			<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
