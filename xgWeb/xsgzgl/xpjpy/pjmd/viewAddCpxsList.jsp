<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/xpjpy/pjmd/js/pjmd.js"></script>
		<script type="text/javascript" src="js/comm/jcsjld.js"></script>
		<script type="text/javascript" src="js/uicomm.js"></script>
		<script type="text/javascript">

			jQuery(function(){
				var gridSetting = {
					caption:"学生信息列表",
					pager:"pager",
					rowNum:10,
					url:"xpj_cpmd.do?method=viewAddCpxsList&type=query",
					colList:[
					   {label:'学号',name:'xh', index: 'xh',width:'13%',key:true},
					   {label:'姓名',name:'xm', index: 'xm',width:'13%'},
					   {label:'年级',name:'cpnj', index: 'cpnj',width:'10%'},
					   {label:'<bean:message key="lable.xb" />',name:'cpxymc', index: 'cpxydm',width:'18%'},
					   {label:'专业',name:'cpzymc', index: 'cpzydm',width:'18%'},
					   {label:'班级',name:'cpbjmc', index: 'cpbjdm',width:'18%'},
					   {label:'备注',name:'tjzt', index: 'tjzt',width:'10%',noSort:true,formatter:function(cell,rowObject){
						   if (cell == "1"){
							   return "<font color='red' title='该学生所在班级已提交综测分。'>("+rowObject["cpxymc"]+")已锁定</font>";
						   } else {
							   return "<font color='blue'>未参评</font>";
						   }
					   }}
					],
					params:{
						bjdm:jQuery("#bjdm").val()
					}
				}
				var map = getSuperSearch();
				gridSetting["params"] = map;
				
				jQuery("#dataTable").initGrid(gridSetting);
			});

			function searchRs(){
				var map = getSuperSearch();
				map["bjdm"] = jQuery("#bjdm").val();
				jQuery("#dataTable").reloadGrid(map);
			}

		</script>
	</head>

	<body>
	
		<html:form action="/xpj_cpmd" >
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<li><a href="javascript:void(0);" onclick="showBjtzDiv();" class="btn_zj">调整班级</a></li>						
					</ul>
				</div>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		
		<div class="formbox">
			<!--标题start-->
			<h3 class="datetitle_01">
				<span> 学生信息列表
				 </span>
			</h3>

			<table id="dataTable" ></table>
			<div id="pager"></div>

		</div>
		<!-- 班级调整弹出层 -->
		<div id="div_bjtz" style="display: none">
			<div style='tab'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="2">
								<span>参评班级</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jbxx">
						<tr>
							<th width="30%">年级</th>
							<td >
								<html:select property="nj" styleId="nj" 
									onchange="onChangJcsj('nj','xydm','zydm','bjdm','nj','1','1');" style="width:100px">
									<html:option value="">&nbsp;</html:option>
									<html:options collection="njList" property="nj"
										labelProperty="nj" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th ><bean:message key="lable.xb" /></th>
							<td>											
								<html:select property="xydm" styleId="xydm"
								onchange="onChangJcsj('nj','xydm','zydm','bjdm','xy','1','1');"  style="width:180px;">
									<html:option value="">&nbsp;</html:option>
								</html:select>
				
							</td>
						</tr>
						<tr>
							<th >专业</th>
							<td>
								<html:select property="zydm" styleId="zydm"
								onchange="onChangJcsj('nj','xydm','zydm','bjdm','zy','1','1');"   style="width:180px;">
									<html:option value="">&nbsp;</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<th ><span class="red">*</span>班级</th>
							<td>
								<html:select property="bjdm" styleId="bjdm" style="width:180px;">
									<html:option value="">&nbsp;</html:option>
								</html:select>
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="2">
								<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<button type="button" type="button" onclick="tzCpry();return false;">
										确 定
									</button>
									<button type="button" type="button" onclick="closeWindown();return false;">
										关 闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
		</div>
	</html:form>
	</body>
</html>
