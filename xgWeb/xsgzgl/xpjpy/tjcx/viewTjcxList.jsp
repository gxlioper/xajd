<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript">
			var gridSetting = {
				caption:"综测排名组",
				pager:"pager",
				url:"xpj_tjcx.do?method=viewTjcxList&type=query",
				colList:[
					{label:'key',name:'id', index: 'id',key:true ,hidden:true},
					{label:'评奖周期',name:'pjzq', index: 'xn',width:'8%'},				
					{label:'学号',name:'xh', index: 'xh',width:'10%'},
					{label:'姓名',name:'xm', index: 'xm',width:'8%'},
					{label:'<bean:message key="lable.xb" />'+'名称',name:'cpxymc', index: 'cpxymc',width:'10%'},
					{label:'项目类型',name:'xmlxmc', index: 'xmlxmc',width:'5%'},
					{label:'项目名称',name:'xmmc', index: 'xmmc',width:'13%'},
					{label:'获得时间',name:'hdsj', index: 'hdsj',width:'13%'}
				]
			};

			var gridSetting1 = {
					caption:"综测排名组",
					pager:"pager",
					url:"xpj_tjcx.do?method=viewTjcxList&type=query",
					colList:[
						{label:'key',name:'id', index: 'id',key:true ,hidden:true},
						{label:'评奖周期',name:'pjzq', index: 'xn',width:'8%'},				
						{label:'学号',name:'xh', index: 'xh',width:'10%'},
						{label:'姓名',name:'xm', index: 'xm',width:'8%'},
						{label:'<bean:message key="lable.xb" />'+'名称',name:'cpxymc', index: 'cpxymc',width:'10%'},
						{label:'项目类型',name:'xmlxmc', index: 'xmlxmc',width:'5%'},
						{label:'项目名称',name:'xmmc', index: 'xmmc',width:'13%'},
						{label:'金额',name:'jgyz', index: 'jgyz',width:'8%'},
						{label:'获得时间',name:'hdsj', index: 'hdsj',width:'13%'}
					]
				};
			
			jQuery(function(){
				if('${xxdm}' == '10277' ){
					gridSetting1["params"]=getSuperSearch();
					jQuery("#dataTable").initGrid(gridSetting1);
				}else{
					gridSetting["params"]=getSuperSearch();
					jQuery("#dataTable").initGrid(gridSetting);
				}
				
			});

			//高级查询
			function searchRs(){
				var map = getSuperSearch();
				jQuery("#dataTable").reloadGrid(map);
			}


			//检验可否查询
			function checkSearch(){

				var flag = true;

				var xn_num =  jQuery("a[name=a_name_xn]").length;
				var xn_xq =  jQuery("a[name=a_name_xq]").length;
				var xmlx_num = jQuery("a[name=a_name_xxmlx]").length;
				var pjzq = jQuery("#pjzq").val();
				
				if(xn_num != "1"){
					alertError("请选择一个学年！");
					flag = false;
				}else if(xmlx_num < 1){
					alertError("请至少选择一个项目类型！");
					flag = false;
				}else if(xn_xq != "1" && pjzq == "1"){
						alertError("请选择一个学期！");
						flag = false;
					}

				return flag;
			}
			

			/*获奖名单导出*/
			
			function hjmddc(dcfs){
				var pjzq = jQuery("#pjzq").val();
				if(checkSearch()){
					var xn = jQuery("a[name=a_name_xn]").eq(0).attr("id").replace("a_id_","");
					//var xmlx = jQuery("a[name=a_name_xxmlx]").eq(0).attr("id").replace("a_id_","");
					if(pjzq == "1") {
						var xq = jQuery("a[name=a_name_xq]").eq(0).attr("id").replace("a_id_","");
					}
					var url = "xpj_tjcx.do?method=expHjmdtj&dcfs="+dcfs;
						url+= "&str_xn="+xn;
						if(pjzq == "1") {
							url+= "&str_xq="+xq;
						}
						//url+= "&str_xmlx="+xmlx;

					var xmlx = new Array();//项目类型

					var m = 0;
					jQuery("a[name=a_name_xxmlx]").each(function(){
						var id = jQuery(this).attr("id");
						var xmlxmc = id.replace("a_id_","");
						if(xmlxmc !=null && xmlxmc!=""){
							xmlx[m] = xmlxmc;
							m++;
						}
					});

					if(xmlx != null && xmlx.length>0){
						url+= "&array_xmlx="+xmlx.join("!!array!!");
					}

						
					var xydm = new Array();//学院代码
					
					var n=0;
					
					jQuery("a[name=a_name_xy]").each(function(){
						var id = jQuery(this).attr("id");
						var xy = id.replace("a_id_","");
						if(xy !=null && xy!=""){
							xydm[n] = xy;
							n++;
						}
					});
					
					if(xydm != null && xydm.length>0){
						url+= "&array_xydm="+xydm.join("!!array!!");
					}

					var xmxz = new Array();//项目性质
					
					var o=0;
					
					jQuery("a[name=a_name_xxmxz]").each(function(){
						var id = jQuery(this).attr("id");
						var xmxzmc = id.replace("a_id_","");
						if(xmxzmc !=null && xmxzmc!=""){
							xmxz[o] = xmxzmc;
							o++;
						}
					});
					
					if(xmxz != null && xmxz.length>0){
						url+= "&array_xmxz="+xmxz.join("!!array!!");
					}


					var xmmc = new Array();//项目名称
					
					var q=0;
					
					jQuery("a[name=a_name_xmmc]").each(function(){
						var id = jQuery(this).attr("id");
						var xm = id.replace("a_id_","");
						if(xm !=null && xm!=""){
							xmmc[n] = xm;
							q++;
						}
					});
					
					if(xmmc != null && xmmc.length>0){
						url+= "&array_xmmc="+xmmc.join("!!array!!");
					}
					
					document.forms[0].action = encodeURI(encodeURI(url)); ;
					document.forms[0].target = "_blank";
					document.forms[0].submit();
					document.forms[0].target = "_self";	
						
					}
				}

			//上海电机学院 检验可否查询
			function checkSearch_11458(){
			
				var flag = true;

				var xn_num =  jQuery("a[name=a_name_xn]").length;
				var xmxz_num = jQuery("a[name=a_name_xxmxz]").length;
				
				if(xn_num != "1"){
					alertError("学年条件不可为空，且只能选择一个！");
					flag = false;
				}else if(xmxz_num != "1"){
					alertError("项目性质条件不可为空，且只能选择一个！");
					flag = false;
				}

				return flag;
			}

            function hjmddc_11458(){
                if(checkSearch_11458()){
                    var xn = jQuery("a[name=a_name_xn]").eq(0).attr("id").replace("a_id_","");
                    var url = "xpj_tjcx.do?method=expHjmdtj_11458";
                    url+= "&str_xn="+xn;

                    var xmxz = new Array();//项目代码

                    var m = 0;
                    jQuery("a[name=a_name_xxmxz]").each(function(){
                        var id = jQuery(this).attr("id");
                        var xmxzmc = id.replace("a_id_","");
                        if(xmxzmc !=null && xmxzmc!=""){
                            xmxz[m] = xmxzmc;
                            m++;
                        }
                    });

                    if(xmxz != null && xmxz.length>0){
                        url+= "&array_xmxz="+xmxz.join("!!array!!");
                    }


                    var xydm = new Array();//学院代码

                    var n=0;

                    jQuery("a[name=a_name_xy]").each(function(){
                        var id = jQuery(this).attr("id");
                        var xy = id.replace("a_id_","");
                        if(xy !=null && xy!=""){
                            xydm[n] = xy;
                            n++;
                        }
                    });

                    if(xydm != null && xydm.length>0){
                        url+= "&array_xydm="+xydm.join("!!array!!");
                    }

                    document.forms[0].action = url;
                    document.forms[0].target = "_blank";
                    document.forms[0].submit();
                    document.forms[0].target = "_self";

                }
            }

            /*湘潭大学 获奖名单导出*/
            function hjmddc_10530(){

                var xn_num =  jQuery("a[name=a_name_xn]").length;
                var xy_num = jQuery("a[name=a_name_xy]").length;

                if(xn_num != "1"){
                    alertError("请选择一个学年！");
					return false;
                }else if(xy_num < 1){
                    alertError("请至少选择一个学院！");
                    return false;
                }

				var xn = jQuery("a[name=a_name_xn]").eq(0).attr("id").replace("a_id_","");
				var url = "xpj_tjcx.do?method=getPymdhzb";
				url+= "&xn="+xn;

				var xyArr = [];//学院代码

				jQuery("a[name=a_name_xy]").each(function(){
					var id = jQuery(this).attr("id");
					var xy = id.replace("a_id_","");
					if(xy !=null && xy!=""){
						xyArr.push(xy);
					}
				});

				if(xyArr != null && xyArr.length>0){
					url+= "&xyArr="+xyArr;
				}

				document.forms[0].action = url;
				document.forms[0].target = "_blank";
				document.forms[0].submit();
				document.forms[0].target = "_self";
            }

			var DCCLBH = "pj_tjcx.do";//dcclbh,导出功能编号

			//自定义导出 功能
			function jgdc_11458(){
				//DCCLBH导出功能编号,执行导出函数 
				customExport(DCCLBH, ExportData);
			}

			//导出方法
			function ExportData() {
				setSearchTj();//设置高级查询条件
				var url = "xpj_tjcx.do?method=exportDataShty&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
				url = addSuperSearchParams(url);//设置高级查询参数
				jQuery("form").eq(0).attr("action", url);
				jQuery("form").eq(0).submit();
			}
            function getJxjExcel(type){
                var url="xpj_tjcx.do?method=getJxjExcel&type="+type;
                var xnArr = jQuery("a[name=a_name_xn]");
                var xqArr = jQuery("a[name=a_name_xq]");
                if(xnArr.length != 1){
                    showAlertDivLayer("请选择一个学年条件！");
                    return false;
                }
                if(xqArr.length != 1){
                    showAlertDivLayer("请选择一个学期条件！");
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
		</div>
		<html:form action="/xpj_pjjg">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" id="xbmc" value="<bean:message key="lable.xy" />"/>
			<input type="hidden" name="pjzq" id="pjzq" value="${pjzq}"/>
			<div class="toolbox">
				<!-- 按钮 -->	
				<div class="buttonbox">
					<ul>
						<!-- 通用导出 begin-->
						<logic:notEqual name="xxdm" value="11458">
						<logic:notEqual name="xxdm" value="10277">
						<li><a href="#" class="btn_dc" onclick="hjmddc('dc');return false;">导出</a></li>
						</logic:notEqual>
						</logic:notEqual>
						<!-- 通用导出 end-->
						<!-- 上海体育学院定制导出 begin-->
						<logic:equal name="xxdm" value="10277">
						<li><a href="#" class="btn_dc" onclick="hjmddc('dc');return false;">榜单打印</a></li>
						<li><a href="#" class="btn_dc" onclick="jgdc_11458();return false;">结果导出</a></li>
						</logic:equal>
						<!-- 上海体育学院定制导出 end-->
						<!-- 上海电机学院定制导出 begin-->
						<logic:equal name="xxdm" value="11458">
						<li><a href="#" class="btn_dc" onclick="hjmddc_11458();return false;">导出</a></li>						
						</logic:equal>
						<!-- 上海电机学院定制导出 end-->
						<!-- 浙江大学定制导出 begin-->
						<logic:equal name="xxdm" value="10335">
						<li><a href="#" class="btn_dc" onclick="hjmddc('dcwd');return false;">导出word</a></li>
						<li><a href="#" class="btn_dc" onclick="hjmddc('njdc');return false;">年鉴导出</a></li>
					    <li><a href="#" class="btn_dc" onclick="hjmddc('njdcwd');return false;">年鉴导出word</a></li>		 				
						</logic:equal>
						<!-- 浙江大学定制导出 end-->
						<%--湘潭大学定制导出：begin--%>
						<logic:equal name="xxdm" value="10530">
						<li><a href="#" class="btn_dc" onclick="hjmddc_10530();return false;">院系评优汇总名单</a></li>
						</logic:equal>
						<%--湘潭大学定制导出：end--%>
						<%--南京高等职业技术学校--%>
						<logic:equal name="xxdm" value="10874">
							<li><a href="javascript:void(0);" onclick="getJxjExcel('tj');return false;" class="btn_down">奖学金统计表导出</a></li>
							<li><a href="javascript:void(0);" onclick="getJxjExcel('lq');return false;" class="btn_down">奖学金领取表导出</a></li>
						</logic:equal>
					</ul>
				</div>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>获奖名单统计</span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>