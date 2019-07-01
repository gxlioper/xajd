<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/gygl/ggwpgl/js/ggwpjy.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript">
			var gridSetting = {
				pager:"pager",
				url:"xgygl_ggwpjydj.do?method=getGgwpjyList&type=query",
				colList:[
				   {label:'id',name:'id', index: 'id',hidden:true,key:true},
				   {label:'学号',name:'xh', index: 'xh', width:'10%', formatter:function(v,r){
					   return "<a class='name' href='javascript:view(\""+r["id"]+"\")'>"+v+"</a>";
				   }},
				   {label:'姓名',name:'xm', index: 'xm', width: '10%'},
				   {label:'学院',name:'xymc', index: 'xymc', width: '10%'},
				   {label:'班级',name:'bjmc', index: 'bjmc', width: '10%'},
				   {label:'借用物品种类',name:'flmc', index: 'flmc', width: '10%'},
				   {label:'借用物品名称',name:'sbmc', index: 'sbmc', width: '10%'},
				   {label:'借用时间',name:'jysj', index: 'jysj', width: '15%'},
				   {label:'归还时间',name:'ghsj', index: 'ghsj', width: '15%'},
				   {label:'归还状态',name:'ghztmc', index: 'ghztmc', width: '10%'},
				   {label:'ghzt',name:'ghzt', index: 'ghztmc',hidden:true}
				],
				sortname: "jysj",
			 	sortorder: "desc"
			};

			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
			});			
		</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
	<html:form action="/xgygl_ggwpjydj" method="post" styleId="form">
		<%@ include file="/comm/hiddenValue.jsp"%>
		<div class="toolbox">
			<!-- 按钮 -->
			<div class="buttonbox">
				<ul>
					<logic:equal name="writeAble" value="yes">
					<li><a href="javascript:void(0);" onclick="add()" class="btn_zj">登记</a></li>
					<li><a href="javascript:void(0);" onclick="update();" class="btn_xg">修改</a></li>
					<li><a href="javascript:void(0);" onclick="del();" class="btn_sc">删除</a></li>						
					<li><a href="javascript:void(0);" onclick="ghsb();" class="btn_gx">归还</a></li>						
					<li><a href="javascript:void(0);" onclick="dr();" class="btn_dr">导入</a></li>	
					</logic:equal>					
					<li><a href="javascript:void(0);" onclick="exportConfig();" class="btn_dc">导出</a></li>						
				</ul>
			</div>
			<!-- 过滤条件 -->	
			<%@ include file="/comm/search/superSearchArea.jsp"%>
			<!-- 过滤条件 end-->
		</div>
	
		<div class="formbox">
			<!--标题start-->
			<h3 class="datetitle_01">
				<span> 公共物品借用记录列表 </span>
			</h3>

			<table id="dataTable"></table>
			<div id="pager"></div>
		</div>
		</html:form>
		
		<div id="sbgh" style="display:none;">
			<table class="formlist">
				<thead>
					<tr>
						<th colspan="4">
							<span>设备归还</span>
						</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th align="right" width="20%">
							归还时间
						</th>
						<td width="30%">
							<input type="text" value="${now }" readonly="readonly" name="ghsj" id="ghsj"
							onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',skin:'whyGreen'})"
							/>
						</td>
						<th align="right" width="20%">
							经办人
						</th>
						<td width="30%">
							${userNameReal }
							<input type="hidden" name="ghjbr" id="ghjbr" value="${userName }"/>
						</td>
					</tr>
					<tr>
						<th align="right" width="20%">
							归还备注 <br/>
							<font color="red">（限400字）</font>
						</th>
						<td colspan="3">
							<textarea rows="5" style="width:95%;" id="ghbz"
								onblur="checkLen(this,400);"
							></textarea>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</body>
</html>
