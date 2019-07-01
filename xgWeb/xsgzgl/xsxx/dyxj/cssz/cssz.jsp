<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/xsxx/dyxj/cssz/js/cssz.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script language="javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript">
			function saveForm(){
				var splc = jQuery("#splc").val();
				var sqxn = jQuery("#sqxn").val();
				var sqxq = jQuery("#sqxq").val();
				if (splc == "" || sqxn == "" || sqxq == ""){
					showAlertDivLayer("请将必填项填写完整！");
					return false;
				}
				
				var id = jQuery("#id").val();
				var url = id == "" ? "xsxxDyxjCssz.do?method=save" : "xsxxDyxjCssz.do?method=update";
				ajaxSubFormWithFun("csszForm",url,function(data){
					showAlertDivLayer(data["message"],{},{"clkFun":function(){
						document.location.href = location.href;
					}});
				});
			}

			jQuery(function(){
				var gridSetting = {
					caption : "",
					pager : "pager",
					url : "xsxxDyxjCssz.do?method=cssz&type=query",
					colList : [ {
						label : 'key',
						name : 'id',
						index : 'id',
						key : true,
						hidden : true
					}, {
						label : '学号',
						name : 'xh',
						index : 'xh',
						width : '10%'
						//,formatter : xhLink
						
					}, {
						label : '姓名',
						name : 'xm',
						index : 'xm',
						width : '6%'
					}, {
						label : '年级',
						name : 'nj',
						index : 'nj',
						width : '6%'
					}, {
						label : '学院',
						name : 'xymc',
						index : 'xydm',
						width : '10%'
					}, {
						label : '专业',
						name : 'zymc',
						index : 'zydm',
						width : '15%'
					},{
						label : '班级',
						name : 'bjmc',
						index : 'bjdm',
						width : '15%'
					}],
					sortname : "xn,xq",
					sortorder : "desc"
				}
				var map = getSuperSearch();
				map["sqxn"] = jQuery("#sqxn").val();
				map["sqxq"] = jQuery("#sqxq").val();
				gridSetting["params"] = map;
				jQuery("#dataTable").initGrid(gridSetting);
				jQuery("#sqxn").change(function(){
					searchRs();
				})
				jQuery("#sqxq").change(function(){
					searchRs();
				})
			})
			function searchRs() {
				var map = getSuperSearch();
				map["sqxn"] = jQuery("#sqxn").val();
				map["sqxq"] = jQuery("#sqxq").val();
				jQuery("#dataTable").reloadGrid(map);
            }
		</script>
	</head>
  	<body >
	<html:form action="/xsxxDyxjCssz" method="post" styleId="csszForm">
			<html:hidden property="id" styleId="id"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
			</div>
			<div class="tab">
			<table width="100%" border="0" class="formlist">
				<thead>
					<tr>
						<th colspan="2"><span>参数设置</span></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th width="40%"><span class="red">*</span>申请开关</th>
						<td>
							<logic:present name="onOff">
								<logic:iterate id="o" name="onOff">
									<html:radio property="sqkg" value="${o.dm}">${o.mc}</html:radio>
								</logic:iterate>
							</logic:present>
						</td>
					</tr>
					<tr>
						<th>
							起始时间
						</th>
						<td>
							<html:text  property="sqkssj" styleId="sqkssj"
										onfocus="showCalendar('sqkssj','y-mm-dd',true,'sqjssj');" 
										readonly="true"></html:text>
								至
							<html:text  property="sqjssj" styleId="sqjssj"
										onfocus="showCalendar('sqjssj','y-mm-dd',false,'sqkssj');" 
								 		readonly="true"></html:text>
									
						</td>
					</tr>
					<tr>
						<th>
							<span class="red">*</span>审核流程
						</th>
						<td>
							<html:select property="splc" styleId="splc">
								<html:option value=""></html:option>
								<html:options collection="shlcList" property="splc" labelProperty="lcxx"/>
							</html:select>
						</td>
					</tr>
					<tr>
						<th><span class="red">*</span>申请学年</th>
						<td>
							<html:select property="sqxn" styleId="sqxn">
								<html:options collection="sqxnList" property="xn" labelProperty="xn"/>
							</html:select>
						</td>
					</tr>
					<tr>
						<th><span class="red">*</span>申请学期</th>
						<td>
							<html:select property="sqxq" styleId="sqxq">
								<html:option value=""></html:option>
								<html:options collection="sqxqList" property="xqdm" labelProperty="xqmc"/>
							</html:select>	
						</td>
					</tr>
				</tbody>
				<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
			          <div class="btn">		
			          	<logic:equal name="writeAble" value="yes">		            
							<button type="button" class="button" onclick="saveForm();" >
								保 存
							</button>
						</logic:equal>
			          </div>
			        </td>
			      </tr>
			    </tfoot>
			</table>
			</div>
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<li>
							<a href="javascript:void(0);" class="btn_zj" onclick="add();return false;"  >增加</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="del();return false" class="btn_sc" >删除</a>
						</li>
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
				<span>资格库维护列表&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
		
	</body>
</html>
