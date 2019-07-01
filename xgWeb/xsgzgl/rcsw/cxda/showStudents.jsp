<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript">
			var gridSetting = {
				caption:"学生信息列表",
				pager:"pager",
				multiselect:false,
				rowNum:10,
				url:"xsxx_xsgl.do?method=showStudents&type=query",
				colList:[
				   {label:'学号',name:'xh', index: 'xh',width:'13%',key:true},
				   {label:'姓名',name:'xm', index: 'xm',width:'13%'},
				   {label:'性别',name:'xb', index: 'xb',width:'6%'},
				   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xydm',width:'19%'},
				   {label:'专业',name:'zymc', index: 'zydm',width:'19%'},
				   {label:'班级',name:'bjmc', index: 'bjdm',width:'18%'},
				   {label:'操作',name:'xh', index: '',width:'12%',noSort:true,formatter:function(cell,rowObject){
					 //  return "<label class='btn_01' onclick=\"selectStudent('"+cell+"',"++"');\">选择</label>";
						return "<label href='javascript:void(0);' class='btn_01' onclick='selectStudent(\"" + cell + "\",\""
						+ rowObject["bjmc"] + "\",\"" + rowObject["xm"] + "\");'>" 
						+ "选择</label>";
				   }}
				],
				sortname: "xh",
			 	sortorder: "asc"
			}

			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
			});
	
			function selectStudent(xh,bjmc,xm){
				var gotoPath = jQuery("#gotoPath").val();

				if (gotoPath.split("?").length > 1){
					gotoPath = gotoPath+"&xh="+xh;
				} else {
					gotoPath = gotoPath+"?xh="+xh;
				}
				var api = frameElement.api;
				if (api){
					var test = api.get('parentDialog');
					if (test){
						var isExist = false;
						test.jQuery("#tbody_self tr").each(function(b){
							if(test.jQuery(this).find("td:eq(1) input").val() == xh){
								isExist = true;
								var falserownum = b+1;
								showAlert("选择的学生与第"+falserownum+"行学生重复，请重新选择！");
								//closeDialog();
								return false;
							}
						})
						if(!isExist){
							var rownum = '${fzrow}'-1;
							test.jQuery('#tbody_self tr:eq('+rownum+')').find("td:eq(1) input").val(xh);
							test.jQuery("#tbody_self tr:eq("+rownum+")").find("td:eq(2)").text(xm);
							test.jQuery("#tbody_self tr:eq("+rownum+")").find("td:eq(3)").text(bjmc);
						//	test.jQuery('#tbody_self tr:eq('+rownum+')').find("td:eq(1) input").attr('disabled',true);
							closeDialog();
						}else{
							return;
						}
						
					} else {
						var W = api.opener;
						W.location=gotoPath;			
					}
				} else if (parent.window){
					parent.window.document.location=gotoPath;						
				}
				
				iFClose();
			}
			
			
			function searchRs(){
				var map = getSuperSearch();
				jQuery("#dataTable").reloadGrid(map);
			}
		</script>
	</head>

	<body>
		<input type="hidden" value="${gotoPath}" id="gotoPath"/>
	
		<html:form action="/xsxx_xsgl">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- 按钮 -->
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		<div>
					<!--标题start-->
			<h3 class="datetitle_01">
				<span> 学生信息列表
				 </span>
			</h3>
		</div>
		<div class="formbox" style="width:100%;height:330px;overflow-x:hidden;overflow-y:auto;">
			<table id="dataTable" ></table>
		</div>
			<div id="pager"></div>
	</body>
</html>
