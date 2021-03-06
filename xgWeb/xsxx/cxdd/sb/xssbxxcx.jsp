<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsxx/cxdd/sb/js/cxddsb.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {
				caption : "学生列表",
				pager : "pager",
				url : "cxdd_pysb.do?method=getXsPageList&type=query",
				colList : [ {
					label : 'key',
					name : 'xsid',
					index : 'xsid',
					key : true,
					hidden : true
				}, {
					label : '学号',
					name : 'xh',
					index : 'xh',
					width : '10%'
				}, {
					label : '姓名',
					name : 'xm',
					index : 'xm',
					width : '10%'
				},{
					label : '评价',
					name : 'pj',
					index : 'pj',
					width : '10%',
					formatter : pjLink
				},{
					label : '评语(80-120字)',
					name : 'py',
					index : 'py',
					width : '35%',
					formatter : pyLink
				},{
					label : '班级',
					name : 'bjdm',
					index : 'bjdm',
					hidden : true
				}],
				radioselect:true
			}
			var map = getSuperSearch();
			map['bjdms']=jQuery("#bjdms").val();
			map['xh']=jQuery('#xh').val();
			gridSetting["params"] = map;
			jQuery("#dataTable").initGrid(gridSetting);
		})
		</script>
	</head>

	<body>
		<input type="hidden" name="isopen" id="isopen" value="${jcszModel.isopen }"/>
		<input type="hidden" name="bjdms" id="bjdms" value="${bjdms}"/>
		<input type="hidden" name="bjid" id="bjid" value="${bjid}"/>
		<div class="tab_cur">
		</div>
		<html:form action="/cxdd_pysb" onsubmit="return false;" >
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="sqkg" id="sqkg" value="${sqkg}"/>
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<li>
                             <a href="javascript:void(0);" onclick="document.location.href='xsxx_cxdd_pysb.do';" class="btn_fh">返回</a>	</li>
						<li>
							<a href="javascript:void(0);" onclick="submittj();" class="btn_up">提交</a>
						</li>
					</ul>
				
					
				
				</div>
				<div class="searchtab">
				<table width="100%" border="0">
					<tr>
						<th width="12%">学号 / 姓名</th>
						<td>
							<input type="text" id="xh" name="xh" maxleng="20" onkeypress="if(event.keyCode==13){doQuery();}"/>
						</td>
						<td>
							<div class="btn">
								<button type="button"  class="btn_cx" id="search_go" onclick="doQuery();">
									查 询
								</button>
							</div>
						</td>
					</tr>					
				</table>
			</div>
		
				<!-- 过滤条件 -->	
				<div style="display:none">
					<%@ include file="/comm/search/superSearchArea.jsp"%>
					<html:select property="pj" styleId="pjhtml" >
						<html:option value=""></html:option>
						<html:options collection="pylist" property="cxdjdm" labelProperty="cxdjmc"/>
					</html:select>
				</div>
				
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span><font color = "blue">${xn}${xqmc}</font> 学生列表&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
