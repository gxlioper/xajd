<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript">
			var gridSetting = {
				caption:"家教需求列表",
				pager:"pager",
				multiselect:false,
				rowNum:10,
				url:"jjgl_jjxq.do?method=getJjxqList",
				colList:[
				   {label:'家教编号',name:'xqid', index: 'xqid',key:true},
				   {label:'sqzt',name:'sqzt', index: 'sqzt',hidden:true},
				   {label:'年级',name:'jjnj', index: 'jjnj'},
				   {label:'家教科目',name:'jjxk', index: 'jjxk'},
				   {label:'家教地点',name:'jjdd', index: 'jjdd'},
				   {label:'家教要求',name:'jjlsyq', index: 'jjlsyq'},
				   {label:'申请状态',name:'sqztmc', index: 'sqztmc',formatter:function(v,r){
					   if (v == "未申请" || v == "退回"){
                           return "<a class='btn_common' title='点击可申请' onclick='jjsq(\""+r["xqid"]+"\")'>未申请</a>";
					   }else{
                           return "<a class='btn_common disabled'>"+v+"</a>";
					   }

				   }}
				]
			};

			function jjsq(xqid){

                var title = "申请家教";
                var url = "jjgl_jjxq.do?method=jjaqxys&xqid="+xqid;
                showDialog(title, 700, 480, url);
			}
			
			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
			});
	
			function searchRs(){
				var map = {};
                map["jjxk"] = jQuery("#jjxk").val();
                map["jjnj"] = jQuery("#jjnj").val();
				jQuery("#dataTable").reloadGrid(map);
			}
		</script>
	</head>

	<body>
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<!-- 标题 end-->
	
		<div class="toolbox">
			<!-- 过滤条件 -->
			<div class="searchtab">
				<html:form action="/jjgl_jjxq" method="post" >
					<table width="100%" border="0">
						<tr>
							<th width="10%">家教科目</th>
							<td>
								<html:select property="jjxk" styleId="jjxk" style="width:173px">
					    			<html:option value=""></html:option>
					    			<html:options collection="jjxkList" property="jjxk" labelProperty="jjxk"/>
					    		</html:select>
							</td>
							<th width="10%">年级</th>
							<td>
								<html:select property="jjnj" styleId="jjnj" style="width:173px">
					    			<html:option value=""></html:option>
					    			<html:options collection="jjnjList" property="jjnj" labelProperty="jjnj"/>
					    		</html:select>
							</td>
							<td>
								<div class="btn">
									<button type="button" class="btn_cx" id="search_go" onclick="searchRs()">
										查 询
									</button>
								</div>
							</td>
						</tr>					
					</table>
				</html:form>
			</div>
		</div>
	
		<div>
			<h3 class="datetitle_01">
				<span>家教需求列表</span>
			</h3>
		</div>
		<div class="formbox" style="width:100%;height:330px;overflow-x:hidden;overflow-y:auto;">
			<table id="dataTable" ></table>
		</div>
		<div id="pager"></div>
	</body>
</html>
