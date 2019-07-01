<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@page import="java.util.List"%>
<%@page import="xgxt.utils.Pages"%>
<%@page import="xsgzgl.qgzx.jcdmwh.QgzxJcdmwhForm"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<%@ include file="/syscommon/autocomplete.ini"%>
		<script type="text/javascript" src="js/qgzx/jcdmwh/jcdmwh.js"></script>
		<script>
		function setDivHeight(){
			if($("table_rs")){
				jQuery("#div_rs").height(jQuery("#table_rs").height()+20);	
			}
		}
		function searchRs(){
			if($("nd") && $("nd").value.trim()==""){
				alertInfo("年度不能为空！",function(tag){
					if(tag=="ok"){
						return false;
					}
				});
				return false;
			}
			var url = "qgzx_cxtj_ajax.do?method=grcjffTj";
			url+="&nd="+jQuery("#nd").val();
			url+="&yf="+jQuery("#yf").val();
			url+="&bmdm="+jQuery("#bmdm").val();
			url+="&gwmc="+encodeURI(encodeURI(jQuery("#gwmc").val()));
			
			var ie = "ie";
			var otherValue = [ie];
			searchRsByAjax(url,otherValue);
		    setTimeout("setDivHeight()","1000")
		}
		
		//导出
		function Dc(){
			var url = "qgzx_cxtj_ajax.do?method=grcjffDc";
			document.forms[0].action = url;
			document.forms[0].target = "_blank";
			document.forms[0].submit();
			document.forms[0].target = "_self";	
		}
		
		function grcjffyfdc(){
			var yf= jQuery("#yf").val();
			var nd= jQuery("#nd").val();
			if(""==yf){
				alertInfo("请填写月份！");
				return false;
			}
			var url="qgzx_cxtj_ajax.do?method=grcjffDcyf";
			document.forms[0].action=url+"&yf="+yf+"&nd="+nd;
			document.forms[0].submit();
		}

		//浙江交通职业技术学院导出
		function exportDc(){
			var yf= jQuery("#yf").val();
			//var bmdm = jQuery("#bmdm").val();
			if(yf == ""){
				alertInfo("请选择月份！");
				return false;
			}
			/*if(bmdm == ""){
				alertInfo("请选择用人部门！");
				return false;
			}*/
			var url = "qgzx_cxtj_ajax.do?method=grcjffyfdc";
			document.forms[0].action = url;
			document.forms[0].target = "_blank";
			document.forms[0].submit();
			document.forms[0].target = "_self";	
		}

        jQuery(function(){
            changeXmmc();
        });
        function changeXmmc(){
            // 重置
            jQuery("#gwmc").val("");
            //取得数据表：xg_xszz_new_zzxmdmb; 字段：xmmc
            var autoSetting = {
                dataTable:"(select a.*,b.bmlb from view_xg_qgzx_gwxxb a left join ZXBZ_XXBMDM b on b.bmdm=a.yrdwdm)",
                dataField:"gwmc",
                /*dataFieldKey:"je",//要修改的值
                dataFieldKeyId:"je",//要修改值的元素id*/
                sqlTj: getSqlTj,
                scrollHeight:135
            }
            // 模糊搜索下拉【项目名称】
            jQuery("#gwmc").setAutocomplete(autoSetting);
        }

        function getSqlTj(){
            var bmdm = jQuery("#bmdm").val();
            var sqlTj = " ";
            if(bmdm != '' && bmdm != 'xj' && bmdm != 'yj'  ){
                sqlTj += " and yrdwdm = '"+bmdm+"' ";
            }
            if(bmdm == 'xj' ){
                sqlTj += " and bmlb = '1' ";
            }
            if(bmdm == 'yj' ){
                sqlTj += " and bmlb = '5' ";
            }
            return sqlTj;
        }
		</script>
	</head>
	<body onload="searchRs();">
		<div class="tab_cur" >
			<p class="location">
				<em>您的当前位置:</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/qgzx_cxtj" method="post">
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- 隐藏域 -->
			<input type="hidden" name="xmdm" id="xmdm" value="${xmdm }" />
			<div class="toolbox" id="dgncz">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<!-- 通用导出 -->
						<logic:notEqual name="xxdm" value="12036">
							<li><a href="#" onclick="Dc();return false;" class="btn_dc">导出</a></li>
						</logic:notEqual>
						<!-- 浙江交通职业技术学院-->
						<logic:equal value="12036" name="xxdm">
							<li><a href="#" onclick="exportDc();return false;" class="btn_dc">导出</a></li>
						</logic:equal>
						<logic:equal value="10277" name="xxdm">
						<li><a href="#" onclick="grcjffyfdc();return false;" class="btn_dc">个人酬金发放月份导出</a></li>
						</logic:equal>
					</ul>
				</div>
				<div style="display: none;">
				<!-- 过滤条件 -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				</div>
			</div>
			<div class="comp_title">
				<ul>
		       	 	<li><a href="javascript:refreshForm('qgzx_cxtj.do?method=bmcjffTj');"><span>部门酬金发放统计</span></a></li>
		      	 	<li><a href="javascript:refreshForm('qgzx_cxtj.do?method=gwcjffTj');"><span>岗位酬金发放统计</span></a></li>
		      	 	<li class="ha"><a href="#"><span>个人酬金发放统计</span></a></li>
		     	</ul>
	     	</div>
			<div class="searchtab">
				<table>
					<tbody>
						<tr>
							<th>年度</th>
							<td>
								<html:select name="rs" property="nd" style="width:180px" styleId="nd">
									<html:options collection="ndList" property="nd" labelProperty="nd"/>
								</html:select>
							</td>
							<th>月份</th>
							<td>
								<html:select name="rs" property="yf" style="width:180px" styleId="yf" >
									<html:option value="">全部</html:option>
									<html:options collection="yfList" property="yf" labelProperty="yf"/>
								</html:select>
							</td>
							<th>用人部门</th>
							<td>
								<logic:equal name="rs" property="dis" value="true">
									<input type="hidden" id="bmdm" name="bmdm" value="${rs.bmdm}"/>
									<html:select name="rs" property="bmdm" style="width:180px" styleId="bmdm" disabled="${rs.dis}">
										<html:option value="">全部</html:option>
										<html:option value="xj">所有校级</html:option>
										<html:option value="yj">所有院级</html:option>
										<html:options collection="bmList" property="bmdm" labelProperty="bmmc"/>
									</html:select>
								</logic:equal>
								<logic:notEqual name="rs" property="dis" value="true">
									<html:select name="rs" property="bmdm" style="width:180px" styleId="bmdm" disabled="${rs.dis}"  onchange="changeXmmc();">
										<html:option value="">全部</html:option>
										<html:option value="xj">所有校级</html:option>
										<html:option value="yj">所有院级</html:option>
										<html:options collection="bmList" property="bmdm" labelProperty="bmmc"/>
									</html:select>
								</logic:notEqual>
							</td>
						</tr>
						<tr>
							<th>岗位名称</th>
							<td>
								<input type="text" id="gwmc" name="gwmc" maxleng="50"/>
							</td>
						</tr>
						<tr>
							<td colspan="6">
								<div class="btn">
									<input type="hidden" name="go" value="a" />
									<button type="button" class="btn_cx" id="search_go" onclick="searchRs();return false;">
										查询
									</button>
									<button type="button" class="btn_cz" id="btn_cz" onclick="reset()">
										重置
									</button>
								</div>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<!-- 内容显示区开始 -->
			<div class="main_box">
				<div class="mid_box">
					<div class="title">
						<p>
							<!-- 查询得到的数据量显示区域 -->
						</p>
					</div>
				</div>
				<h3 class="datetitle_01">
					<span> 统计结果&nbsp;&nbsp;</span>
				</h3>
				<div id="div_rs"
					style="width:100%;overflow-x:auto;overflow-y:hidden;">
				</div>
			</div>
			<div id="div_detail" style="display:none">
			</div>		
				
			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>
