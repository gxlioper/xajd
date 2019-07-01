<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript">

		jQuery(function(){
			var gridSetting = {
					caption:"申请列表",
					pager:"pager",
					url:"xpjpy_pjpybjpy_jglrgl.do?method=jglrManage&type=query",
					colList:[      
				         {label:'key',name:'sqid', index: 'sqid',hidden:true,key:true},
				         {label:'学号',name:'xh', index: 'xh',width:'11%',formatter:xhLink},
						   {label:'姓名',name:'xm', index: 'xm',width:'8%'},
						   {label:'性别',name:'xb', index: 'xb',width:'5%'},
						   //{label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xydm',width:'13%'},
						   //{label:'班级',name:'bjmc', index: 'bjdm',width:'13%'},
						   {label:'学年',name:'xn', index: 'xn',width:'8%'},
						   {label:'学期',name:'xqmc', index: 'xqmc',width:'3%'},
						   {label:'申请时间',name:'sqsj', index: 'sqsj',width:'11%'},
						   {label:'申请奖项',name:'xmmc', index: 'xmmc',width:'8%'},
						   {label:'评议结果',name:'ylzd1', index: 'ylzd1',width:'8%',formatter:function(cellValue,rowObject){
							   if(!cellValue){
								   cellValue = "";
							   }
							   if(rowObject['tjzt'] == '1' || rowObject['shzt'] != '6'){
								   var ylzd1mc = rowObject['ylzd1mc'];
								   if(!ylzd1mc){
									   ylzd1mc = "";
								   }	
								    return jQuery("<span title='"+ylzd1mc+"'>"+ylzd1mc+"</span>");
							   }else{
								   var html = "<select name='ylzd1' >";
								   //html += "<option value=''></option>";
								   <logic:iterate name="pyjgList" id="pyjgMap">
								   var dmV = "${pyjgMap.dm}";
								   html += '<option value="'+dmV+'" ';
								   if(dmV == cellValue){
									   html += ' selected="selected" ';
								   }
								   html += '>${pyjgMap.mc}</option>';
								   </logic:iterate>
								   html += "</select>";
							   		return jQuery(html);
							   }
							 }
							},
						   {label:'评议意见',name:'ylzd2', index: 'ylzd2',width:'26%',formatter:function(cellValue,rowObject){
							   if(!cellValue){
								   cellValue = "";
							   }
							   var cellValueTemp = cellValue;
							   if(cellValue.length > 15){
								   cellValueTemp = cellValue.substring(0,15)+"...";
							   }
							   if(rowObject['tjzt'] == '1' || rowObject['shzt'] != '6'){
								    return jQuery("<span title='"+cellValue+"'>"+cellValueTemp+"</span>");
							   }else{
							   		return jQuery("<textarea rows='3' style='width:99%;' name='ylzd2' sqid='"+rowObject['sqid']+"' xn='"+rowObject['xn']+"' xq='"+rowObject['xq']+"' xh='"+rowObject['xh']+"' xm='"+rowObject['xm']+"' xmdm='"+rowObject['xmdm']+"'>"+cellValue+"</textarea>");
							   }
							 }
							},
						   {label:'提交状态',name:'tjzt', index: 'tjzt',hidden:true},
						   {label:'审核状态',name:'shzt', index: 'shzt',hidden:true},
						   {label:'学年',name:'xn', index: 'xn',hidden:true},
						   {label:'学期',name:'xq', index: 'xq',hidden:true},
						   {label:'项目代码',name:'xmdm', index: 'xmdm',hidden:true},
						   {label:'评议结果名称',name:'ylzd1mc', index: 'ylzd1mc',hidden:true}
						],
						multiselect:false,
						radioselect:false,
						callBack:setBjmc,
						sortname: "sqsj",
					 	sortorder: "desc"
				};
			gridSetting["params"]=getSuperSearch();
			jQuery("#dataTable").initGrid(gridSetting);
		});

		function setBjmc(){
			jQuery("#jglr_manage_title").html("<span>申请列表&nbsp;&nbsp; </span><font color='#0000ff'>${xsxxMap.xymc} ${xsxxMap.bjmc}</font>");
		}
		function xhLink(cellValue,rowObject){
			return "<a href='javascript:void(0);' class='name' onclick='pjpybjpyView(\""+rowObject["sqid"]+"\",\""+rowObject["xh"]+"\");'>"+cellValue+"</a>";
		}
		function pjpybjpyView(sqid,xh){
			showDialog('查看申请表',780,520,'xpj_sqsh.do?method=viewSqb&sqid='+sqid+'&xh='+xh);
		}
		function searchRs(){
			var map = getSuperSearch();
			jQuery("#dataTable").reloadGrid(map);
		}

		function bcJglr(){
			var isopen = jQuery("#isopen").val();
			if(isopen==null||isopen==''){
				showAlertDivLayer('基本设置未初始化，请联系管理员！');
				return false;
			}
			if ("false" == isopen){
				showAlertDivLayer("当前未开放班级评议，请联系管理员！");
				return false;
			}
			
			var ylzd1Arr = new Array();
			jQuery("select[name='ylzd1']").each(function(){
				var temp = jQuery(this);
				ylzd1Arr.push(temp.val());
			});

			var sqidArr = new Array();
			var sqrArr = new Array();
			var xnArr = new Array();
			var xqArr = new Array();
			var xmdmArr = new Array();
			var ylzd2Arr = new Array();
			var ylzd2Flag = true;
			jQuery("textarea[name='ylzd2']").each(function(){
				var temp = jQuery(this);
				sqidArr.push(temp.attr("sqid"));
				sqrArr.push(temp.attr("xh"));
				xnArr.push(temp.attr("xn"));
				xqArr.push(temp.attr("xq"));
				xmdmArr.push(temp.attr("xmdm"));
				var v = temp.val();
				if(v.length > 100){
					showAlertDivLayer("<font class='red'>" + temp.attr("xh") + " " + temp.attr("xm") + "</font>的评议意见超过100字！");
					ylzd2Flag = false;
					return false;
				}
				ylzd2Arr.push(v);
			});
			if(!ylzd2Flag){
				return false;
			}

			var rows = jQuery("#dataTable").getSeletRow();
			var url = "xpjpy_pjpybjpy_jglrgl.do?method=bcJglr";
			jQuery.post(url,
				{
					values:sqrArr.join(","),
					sqids:sqidArr,
					sqrs:sqrArr,
					xns:xnArr,
					xqs:xqArr,
					xmdms:xmdmArr,
					ylzd1s:ylzd1Arr,
					ylzd2s:ylzd2Arr,
				},function(data){
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				},'json');
		}

		</script>
	</head>

	<body>
		<html:form action="/xpjpy_pjpybjpy_jglrgl">
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置：</em><a>${title }</a>
				</p>
			</div>
			<input type="hidden" name="isopen" id="isopen" value="${jcszModel.bjpyisopen }"/>
			<input type="hidden" id="xymc" value="<bean:message key="lable.xy" />"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- 按钮 -->
				<logic:equal name="writeAble" value="yes">
				<div class="buttonbox">
					<ul>
						<li>
							<a href="javascript:void(0);" class="btn_ccg" onclick="bcJglr();return false;" >保存</a>
						</li>
					</ul>
				</div>
				</logic:equal>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchAreaStu.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01 id="jglr_manage_title">
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
