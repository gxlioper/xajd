<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<style>
			/*当终测项目过多时，本页列表会显示不全。增加列表滚动条显示*/
			.formbox table tbody tr td{white-space:nowrap;}
		</style>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/xpjpy/zhcp/zcfs/js/zcfs.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				var gridSetting = {
					caption:"参评学生列表 ",
					pager:"pager",
					url:"xpj_zcfs.do?method=viewZcfs&doType=query",
					params:{"id":jQuery("#id").val(),"bjdm":"${bjxxMap.bjdm }","xn":jQuery("#xn").val(),"xq":jQuery("#xq").val()},
					multiselect:false
				};
					
				var zcxm = jQuery("font[name=zcxm]");
				var colList=[
					   {label:'key',name:'id', index: 'id',hidden:true,key:true},
					   {label:'学号',name:'xh', index: 'xh',width:'15%'},
					   {label:'姓名',name:'xm', index: 'xm',width:'15%'}
					];
		
				jQuery.each(zcxm,function(i,n){
					var json = {label:jQuery(n).attr("xmmc"),
								name:"fs"+i,
								index:"fs"+i
							};
					colList.push(json);
				});
				gridSetting["colList"] = colList;
				jQuery("#dataTable").initGrid(gridSetting);
			});
			
		</script>
	</head>

	<body>
		<html:form action="/xpj_zcfs">
		<html:hidden property="id" styleId="id"/>
		<input type="hidden" value="${cssz.xn }" id="xn" name="xn" />
		<input type="hidden" value="${cssz.xq }" id="xq" name="xq" />
		<input type="hidden" value="${bjxxMap.bjdm }" id="bjdm" name="bjdm" />
		<input type="hidden" value="${xpjZcfsModel.id }" id="id" name="id" />
	
		<logic:present name="zcxmList">
			<logic:iterate id="z" name="zcxmList">
				<font style="display:none;" xmdm="${z.xmdm }" xmmc="${z.xmmc }" name="zcxm"></font>
			</logic:iterate>
		</logic:present>
		
		
		<div class="toolbox">
			<!-- 过滤条件 -->	
			<div class="searchtab">
				<table width="100%" border="0">
					<tr>
						<th width="12%">学号 / 姓名</th>
						<td>
							<input type="text" id="xh" name="xh" maxleng="20" onkeypress="if(event.keyCode==13){doQuery();}"/>
						</td>
						<td>
							<div class="btn">
								<button type="button" class="btn_cx" id="search_go" onclick="doQuery();">
									查 询
								</button>
								<button type="button" class="btn_cx" onclick="exportZcf();">
									导 出
								</button>
							</div>
						</td>
					</tr>					
				</table>
			</div>
			<!-- 过滤条件 end-->
		</div>
		<div class="formbox">
			<!--标题start-->
			<h3 class="datetitle_01">
				<span><font color="blue">${cssz.zqmc}&nbsp;${bjxxMap.bjmc }班</font>参评学生列表 </span>
			</h3>
		
			<div style="overflow: auto;">
				<table id="dataTable" ></table>
			</div>
			<div id="pager"></div>
		</div>
		</html:form>
	</body>
</html>
