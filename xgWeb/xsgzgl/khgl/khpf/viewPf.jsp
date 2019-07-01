<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>	
		<script type="text/javascript" src="xsgzgl/khgl/khpf/js/khpf.js"></script>	
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script language="javascript" >
			var gridSetting = {
					caption : "考核对象列表",
					pager : "pager",
					url : "khglKhpf.do?method=viewPf&type=query",
					multiselect:false,
					radioselect:true
				}

			var colList = [ 
					   {label : 'zbmxid',name : 'zbmxid',index : 'zbmxid',key:true,hidden:true},
					   {label : 'fzlx',name : 'fzlx',index : 'fzlx',hidden:true},
					   {label : 'pflx',name : 'pflx',index : 'pflx',hidden:true},
					   {label : 'fzqj',name : 'fzqj',index : 'fzqj',hidden:true},
					   {label : 'fzzgf',name : 'fzzgf',index : 'fzzgf',hidden:true},
					   {label : 'fzzdf',name : 'fzzdf',index : 'fzzdf',hidden:true},
                		{label : 'dxq',name : 'dxq',index : 'dxq',hidden:true},
					   <logic:notEqual name="xxdm" value="11527">
					   {label : '考核内容',name : 'khnr',index : 'khnr',width : '58%'}, 
					   </logic:notEqual>
					   {label : '一级指标',name : 'yjzb',index : 'yjzb',width : '16%'}, 
					   {label : '二级指标',name : 'ejzb',index : 'ejzb',width : '16%'}, 
					   {label : '分值',name : 'fzqj',index : 'fzqj',width : '8%'}, 
					   {label : '评分类型',name : 'pflxmc',index : 'pflxmc',width : '8%'},
						<logic:equal name="model" property="pflx" value="bzpf">
						{label : '自评分',name : 'zpf',index : 'zpf',width : '8%'},
						</logic:equal>
						<logic:equal name="model" property="pflx" value="bzrpf">
						{label : '自评分',name : 'zpf',index : 'zpf',width : '8%'},
						{label : '班组评分',name : 'bzpf',index : 'bzpf',width : '8%'},
						</logic:equal>
					   {label : '得分',name : 'fs',index : 'fs',width : '8%',formatter:fsLink},
					   {label : '备注',name : 'bz',index : 'bz',width : '8%'}
					   
				   ];

			gridSetting["colList"] = colList;

			jQuery(function() {
				var map = {xmid:jQuery("#xmid").val(),khbid:jQuery("#khbid").val(),khdxr:jQuery("#khdxr").val(),xmszid:jQuery("#xmszid").val(),pfr:jQuery("#pfr").val(),pflx:jQuery("#pflx").val()};
				gridSetting["params"] = map;
				jQuery.ajaxSetup({async:false});
				jQuery("#dataTable").initGrid(gridSetting);
				viewTotal();
				autoRowSpan();
				jQuery.ajaxSetup({async:true});
			});

			function fsLink(cellValue,rowObject){
			if(null==cellValue||"null"==cellValue){
			cellValue="";
			}
				if( "2"==rowObject.pflx ){
					return "<span style='color: red' class='data-type' lx='"+rowObject.pflx+"' >"+cellValue+"</span>";
				}else{
					return "<span class='data-type' lx='"+rowObject.pflx+"' >"+cellValue+"</span>";
				}
			}
						
		</script>
	</head>
	<body>
	<html:form action="/khglKhpf" method="post">
	<%@ include file="/comm/hiddenValue.jsp"%>
	<input type="hidden" name="xmid" id="xmid" value=${xmInfo.xmid} />
	<input type="hidden" name="xmszid" id="xmszid" value=${model.xmszid} />
	<input type="hidden" name="khbid" id="khbid" value=${model.khbid} />
	<input type="hidden" name="pfr" id="pfr" value=${model.pfr} />
	<input type="hidden" name="khdxr" id="khdxr" value=${ryInfo.ry} />
	<input type="hidden" name="pflx" id="pflx" value=${model.pflx} />
	</html:form>
	<div class="main_box">
		<h3 class=datetitle_01>
			<span>
			项目:<font color="blue">${xmInfo.xmmc}</font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			被评人:<font color="blue">${ryInfo.xm}</font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			所在部门:<font color="blue">${ryInfo.xymc}</font>
			</span>
		</h3>
		<div class="con_overlfow">
			<table id="dataTable" ></table>
			<table width="100%" border="0" class="formlist">
			<!-- 武昌首义个性化 -->
				<logic:equal value="12309" name="xxdm">
				<!-- 考核对象为老师 -->
					<logic:equal value="2" name="khlx">
						<!-- 登录用户为学生 -->					
						<logic:equal value="stu" name="userStatus">
							<tr>					
								<th width="20%">
									对辅导员意见和建议								
								</th>
								<td colspan="3">
									${model.yjjy}
								</td>
							</tr>										
						</logic:equal>					
					</logic:equal>
				</logic:equal>
				
				<!-- 浙江商业职业技术学院个性化 -->
				<logic:equal value="33333" name="xxdm">
				<!-- 考核对象为老师 -->
					<logic:equal value="2" name="khlx">
						<!-- 登录用户为学生 -->					
						<logic:equal value="1" name="model" property="pflx">
							<tr>					
								<th width="20%">
									对班级与班主任<br/>的建议和意见								
								</th>
								<td colspan="3">
									${model.yjjy}
								</td>
							</tr>										
						</logic:equal>					
					</logic:equal>
				</logic:equal>
			</table>
		</div>
		<div style="height: 30px"></div>
		<div>
			<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
				<tfoot>
					<tr>
						<td colspan="4">
							<div class="bz" style="margin-left:30px;">
								<b> 得分合计：<span class="blue" id="total" >0</span></b>
							</div>
							<div class="btn">
								<button type="button"  onclick="iFClose();" id="buttonClose">
									关 闭
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
		</div>
	</div>		
	</body>
</html>
