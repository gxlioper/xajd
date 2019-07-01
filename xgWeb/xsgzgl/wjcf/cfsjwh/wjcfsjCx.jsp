<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	
		<%@ include file="/syscommon/head.ini"%> 
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/uicomm.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		
		
		<script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="xsgzgl/wjcf/cfsjwh/js/wjcfsjCx.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script><!-- 导入功能需要-->
		<script language="javascript" defer="defer">

		//初始化
		function onShow(){ 
			searchRs();
		}
		
		
		//查询结果集
		function searchRs(){
		
			var url = "wjcfCfshwh_cfsjwh.do?method=wjcfsjCxAjax";

			var ie = "ie";

			var otherValue = [ie];

			searchRsByAjax(url,otherValue);
			
			setTimeout("setDivHeight()","1000")
		}
		
		
		
		jQuery(function(){
			onShow();
		})
		
		
		function sjkwhExportConfig() {
				//DCCLBH导出功能编号,执行导出函数 
				customExport("wjcfCfshwh_cfsjwh.do", sjkwhExportData);
		}
				
				
		// 导出方法
		function sjkwhExportData() {
			setSearchTj();//设置高级查询条件
			var url = "wjcfCfshwh_cfsjwh.do?method=sjkwhExportData&dcclbh=" + "wjcfCfshwh_cfsjwh.do";//dcclbh,导出功能编号
			url = addSuperSearchParams(url);//设置高级查询参数
			jQuery("form").eq(0).attr("action", url);
			jQuery("form").eq(0).submit();
		}
		</script>
	</head>
	<body >

		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>


		<html:form action="/wjcfCfshwh_cfsjwh" method="post">
			<input type="hidden" id="text" value="<bean:message key="wjcf.text" />"/>

			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- 隐藏域 -->
			<input type="hidden" name="xmdm" id="xmdm" value="${xmdm }" />

			<!-- 多功能操作区 -->
			<div class="toolbox" id="dgncz">
				<!-- 按钮 -->
				
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<logic:equal value="yes" name="czqx">
						<li>
							<a href="#" onclick="showDialog('', 800, 500,'wjcfsjZj.do');return false;" class="btn_zj">增加</a>
						</li>
						<li>
							<a href="#" onclick="showModi();return false;" class="btn_xg">修改</a>
						</li>
						<li>
							<a href="#" onclick="deleteJcrcgl();return false;" class="btn_sc">删除</a>
						</li>
						
						<li>
							<a href="#" onclick="cfsscl();return false;" class="btn_xg">处分申诉</a>
						</li>
						<li>
							<a href="#" onclick="cfjccl();return false;" class="btn_xg">处分<bean:message key="wjcf.text" /></a>
						</li>
						<li>
							<a href="#" onclick="cfzzcl();return false;" class="btn_xg">处分终止</a>
						</li>
						<li>
							<a href="#" onclick="toImportData('IMPORT_N100109'); return false;" class="btn_dr">导入数据</a>
						</li>
						</logic:equal>
						</logic:equal>
						<li>
							<a href="#" onclick="showView();return false;" class="btn_ck">查看</a>
						</li>
						<li><a href="#" class="btn_dc" onclick="sjkwhExportConfig();return false;">导出</a></li>
						
						<%--
						
						<li><a href="#" class="btn_dc" onclick="configureExportData();return false;">导出数据</a></li>--%>
						<logic:equal value="10351" name="xxdm">
							<li><a href="#" class="btn_down" onclick="getWord();return false;">下载<bean:message key="wjcf.text" />处分申请</a></li>
						</logic:equal>
						
						<logic:equal value="12915" name="xxdm">
							<li><a href="#" class="btn_dy" onclick="getWjCfWord();return false;">违纪处分通知书打印</a></li>
						</logic:equal>
						<logic:equal value="12865" name="xxdm">
							<li><a href="#" class="btn_down" onclick="getCfjdWord();return false;">处分决定书下载</a></li>
						</logic:equal>
						</ul>
				</div>
				
				<!-- 过滤条件 -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
			</div>
			<!-- 多功能操作区 end-->

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
					<span> 查询结果&nbsp;&nbsp; <logic:notEmpty name="rsList">
							<font color="blue">提示：单击表头可以排序；</font>
						</logic:notEmpty> </span>
				</h3>
				<div id="div_rs"
					style="width:100%;height:380px;overflow-x:auto;overflow-y:hidden;">
				</div>

				<!--分页显示-->
				<jsp:include flush="true"
					page="/sjcz/turnpage.jsp?form=wjcfCfsjwhActionForm"></jsp:include>
<%--				<script type="text/javascript">--%>
<%--						$('choose').className="hide";--%>
<%--				</script>--%>
				<!--分页显示-->
			</div>
			<!-- 内容显示区 end-->
			
			
			<div id="div_detail" style="display:none">
			</div>		
			<!-- 处分申诉 -->
			<div id="cfssDiv" style="display: none">
				<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="4">
									<span></span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr id="">
								<th>
									<span class="red">*</span>申诉文号
								</th>
								<td>
									<html:text property="sswh" styleId="sswh" maxlength="30"></html:text>
								</td>
							</tr>
							<tr id="">
								<th>
									<span class="red">*</span>申诉时间
								</th>
								<td>
									<html:text property="sssj" styleId="sssj" onblur="dateFormatChg(this)" style="cursor:hand;"
								onclick="return showCalendar('sssj','y-mm-dd');" ></html:text>
								</td>
							</tr>
							<tr id="" style="">
								<th>
									<span class="red">*</span>申诉结果
								</th>
								<td>
									<html:select property="ssjg" styleId="ssjg" style="width:140px" onchange="discfgg()">
										<html:option value=""></html:option>
										<html:option value="维持原处分">维持原处分</html:option>
										<html:option value="撤销处分">撤销处分</html:option>
										<html:option value="更改处分">更改处分</html:option>
									</html:select>
								</td> 
							</tr>
							<tr id="cfggw" style="display: none">
								<th>
									<span class="red">*</span>处分更改为
								</th>
								<td>
									<html:select property="cflbdm" styleId="cflbdm" style="width:140px" >
										<html:option value=""></html:option>
										<html:options collection="cflbsList" property="dm" labelProperty="mc"/>
									</html:select>
								</td> 
							</tr>
							<tr id="">
								<th>
									申诉意见
								</th>
								<td>
									<html:textarea property="ssyj" styleId="ssyj" rows="5" style="width:290px"></html:textarea>
								</td> 
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="2">
									<div class="bz">
										"<span class="red">*</span>"为必填项
									</div>
									<div class="btn">
										<button type="button"  name="保存" onclick="ssjgsave();">
											保 存
										</button>
										<button type="button"  name="取消" onclick="closeWindown();return false;">
											取 消
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
			
			<!-- 处分解除 -->
			<div id="jcssDiv" style="display: none">
				<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="4">
									<span></span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr id="">
								<th>
									<span class="red">*</span><bean:message key="wjcf.text" />文号
								</th>
								<td>
									<html:text property="jcwh" styleId="jcwh" maxlength="30"></html:text>
								</td>
							</tr>
							<tr id="">
								<th>
									<span class="red">*</span><bean:message key="wjcf.text" />时间
								</th>
								<td>
									<html:text property="jcsj" styleId="jcsj" onblur="dateFormatChg(this)" style="cursor:hand;"
								onclick="return showCalendar('jcsj','y-mm-dd');" ></html:text>
								</td>
							</tr>
							<tr id="">
								<th>
									<bean:message key="wjcf.text" />意见
								</th>
								<td>
									<html:textarea property="jcyj" styleId="jcyj" rows="4" style="width:280px"></html:textarea>
								</td> 
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="2">
									<div class="bz">
										"<span class="red">*</span>"为必填项
									</div>
									<div class="btn">
										<button type="button"  name="保存" onclick="jcjgsave()">
											保 存
										</button>
										<button type="button"  name="取消" onclick="Close();return false;">
											取消
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
				
			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxxNew.jsp"%>
		</html:form>
	</body>
</html>
