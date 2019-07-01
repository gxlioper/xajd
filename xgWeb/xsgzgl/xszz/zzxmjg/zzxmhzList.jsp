<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/xszz/zzxmjg/js/zzxmhzList.js"></script>
		<script type="text/javascript">
		//检验可否查询
		function checkSearch(){
			var flag = true;
			var xn_num =  jQuery("a[name=a_name_xn]").length;
			var xq_num =  jQuery("a[name=a_name_xq]").length;
			var xmlb_num = jQuery("a[name=a_name_zzxmlb]").length;
			
			if(xn_num != "1"){
				alertError("学年条件不可为空，且只能选择一个！");
				flag = false;
			}else if(xmlb_num < 1){
				alertError("请至少选择一个项目类别！");
				flag = false;
			}else if(xq_num > 0 && xq_num != "1"){
				alertError("学期条件只能选择一个！");
				flag = false;
			}
			return flag;
		}
		/*获奖名单导出*/
		function hjmddc(){
			if(checkSearch()){
				var xn = jQuery("a[name=a_name_xn]").eq(0).attr("id").replace("a_id_","");
				var xq_num = jQuery("a[name=a_name_xq]").length;
				var xq = "";
				if(xq_num > 0){
					xq = jQuery("a[name=a_name_xq]").eq(0).attr("id").replace("a_id_","");
				}
				var url = "xszz_zzxmjg.do?method=expHjmdtj";
					url+= "&str_xn="+xn;
					url+= "&str_xq="+xq;

				var xmlb = new Array();//项目类别

				var m = 0;
				jQuery("a[name=a_name_zzxmlb]").each(function(){
					var id = jQuery(this).attr("id");
					var xmlbmc = id.replace("a_id_","");
					if(xmlbmc !=null && xmlbmc!=""){
						xmlb[m] = xmlbmc;
						m++;
					}
				});

				if(xmlb != null && xmlb.length>0){
					url+= "&array_xmlb="+xmlb.join("!!array!!");
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


        // 项目资助情况汇总
        function getXmzzqkhz_10098() {
		    var sxxm="";
            var rows = jQuery("#dataTable").getSeletRow();
            if (rows.length ==0){
                showAlertDivLayer("请至少选择一条记录！");
                return false;
            }else{
                for(var i=0 ; i< rows.length;i++){
                    if(i==0)
					{
                        sxxm=sxxm+rows[i]['xmmc'];
					}
					else{
                        sxxm=sxxm+','+rows[i]['xmmc'];
                    }
				}
			}
			jQuery("#sxxm").val(sxxm);
            setSearchTj();//设置高级查询条件
            var url = "xszz_zzxmjg.do?method=gjjxdc&dcclbh=xg_xszz_xmzzqkhz.do";//dcclbh,导出功能编号
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
				<a href="#" onclick="return false;"	onmousedown ="showHelpDiv()" >使用帮助</a>
			</p>
		</div>
		<html:form action="/xszz_zzxmjg" >
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- 提示信息 end-->
			<div id="div_help" class="prompt" style="display: none">
				<h3>
					<span>提示：</span>
				</h3>
				<p>
					<span>
						必须选择 同学年、同项目名称的资助条目。
					</span>
				</p>
				<a class="close" title="隐藏"
				   onclick="this.parentNode.style.display='none';"></a>
			</div>
			<div id="shtyhzdcdiv" style="display:none">
				
			</div>
			<input type="hidden" id="sxxm" name="sxxm" value=""/>
			<!-- 提示信息 end-->
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<!--<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">导出</a></li>-->
						<li><a href="javascript:void(0);" onclick="getExcel();return false;" class="btn_down">资助信息上报</a></li>
						<logic:equal name="xxdm" value="12036">
							<logic:equal value="xx" name="userStatus">
								<li><a href="javascript:void(0);" onclick="getExcelByXy();return false;" class="btn_down">学院上报表导出</a></li>
							</logic:equal>
						</logic:equal>
						<logic:equal name="xxdm" value="11318">
						<li><a href="#" class="btn_dc" onclick="hjmddc();return false;">导出</a></li>
						</logic:equal>
						<logic:equal name="xxdm" value="12947">
					    <li><a href="javascript:void(0);" onclick="getshzxjHzexcel();return false;" class="btn_down">社会助学金汇总</a></li>	
						</logic:equal>
						<logic:equal name="xxdm" value="10277">
						 <li><a href="javascript:void(0);" onclick="getZzxmHz_10277();return false;" class="btn_down">资助项目汇总表</a></li>	
						</logic:equal>
						<logic:equal name="xxdm" value="10098">
							<li><a href="javascript:void(0);" onclick="getXmzzqkhz_10098();return false;" class="btn_down">项目资助情况汇总表</a></li>
						</logic:equal>
					</ul>
				</div>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		<div class="formbox">
			<!--标题start-->
			<h3 class="datetitle_01">
				<span> 资助项目汇总列表 </span>
			</h3>

			<table id="dataTable" ></table>
			<div id="pager"></div>

		</div>
	</body>
</html>
