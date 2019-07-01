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
		<script type="text/javascript" src="xsgzgl/xpjpy/zjdxzhcp/zcwh/js/zcwh.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				var gridSetting = {
					caption:"参评学生列表 ",
					pager:"pager",
					url:"xpjpy_zcwh.do?method=zcwhView&doType=query" + "&zcxmdmForTop=" + jQuery("#zcxmdmForTop").val(),
					params:{"id":jQuery("#id").val()},
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
		<html:form action="/xpjpy_zcwh">
			<html:hidden property="id" styleId="id"/>
			<input type="hidden" value="${cssz.xn}" id="xn" name="xn" />
			<input type="hidden" value="${zcwhForm.id }" id="id" name="id" />
			<input type="hidden" value='${zcxmdmForTop}' id='zcxmdmForTop' name ='zcxmdmForTop'/>
		
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
					<span><font color="blue">${cssz.xn}&nbsp;&nbsp;${xyxxMap.xymc }</font>&nbsp;&nbsp;参评学生列表 </span>
				</h3>
				<div style="overflow: auto;">
					<table id="dataTable" ></table>
				</div>
				<div id="pager"></div>
			</div>
			<div style="height: 30px"></div>
			<div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
								</div>
								<div class="btn">
									<button type="button" onclick="iFClose();">
										关闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
		</html:form>
	</body>
</html>
