<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript">
			jQuery(function(){
				var gnbz = jQuery("#gnbz").val();
				
				jQuery("td[name=labelTd]").bind("click",function(event){
					var _self = jQuery(this);
					if (_self.children("input").length > 0){
						return false;
					}
					var guid = _self.parents("tr").attr("rowid");
					var text = jQuery.trim(jQuery(_self).text());
					var input = jQuery("<input value='"+text+"' style='width:100px;'/>");
					jQuery(_self).html(input);
					input.focus();
					
					input.bind("blur",function(){
						var text = jQuery(this).val();
						_self.html(text);
						saveCol(guid,"label",text);
					});
				});
				
				jQuery("td[name=widthTd]").bind("click",function(event){
					var _self = jQuery(this);
					if (_self.children("input").length > 0){
						return false;
					}
					
					var guid = _self.parents("tr").attr("rowid");
					var text = jQuery.trim(jQuery(_self).text());
					var input = jQuery("<input value='"+text+"' style='width:20px;'/>");
					jQuery(_self).html(input);
					input.focus();
					
					input.bind("blur",function(){
						var text = jQuery(this).val();
						_self.html(text);
						saveCol(guid,"width",text);
					});
				});
				
				jQuery("#dataGrid input:checkbox").bind("click",function(){
					var guid = jQuery(this).parents("tr").attr("rowid");
					var isHidden = jQuery(this).prop("checked") ? "N" : "Y";
					saveCol(guid,"ishidden",isHidden);
				});
				
				jQuery("img[name=down]").bind("click",function(){
					var tr = jQuery(this).parents("tr");
					var index = tr.index();
					var nextTr = tr.next();
					
					jQuery.post("xtwh_cxpz.do?method=updateColInfo",{guid:tr.attr("rowid"),key:"xssx",value:index+1});
					jQuery.post("xtwh_cxpz.do?method=updateColInfo",{guid:nextTr.attr("rowid"),key:"xssx",value:index});
					jQuery("#demoPage").load("xtwh_cxpz.do?method=ylxg&gnbz="+gnbz);
					tr.insertAfter(nextTr);
				});
				
				jQuery("img[name=up]").bind("click",function(){
					var tr = jQuery(this).parents("tr");
					var index = tr.index();
					var beforeTr = tr.prev();
					
					jQuery.post("xtwh_cxpz.do?method=updateColInfo",{guid:tr.attr("rowid"),key:"xssx",value:index-1});
					jQuery.post("xtwh_cxpz.do?method=updateColInfo",{guid:beforeTr.attr("rowid"),key:"xssx",value:index});
					jQuery("#demoPage").load("xtwh_cxpz.do?method=ylxg&gnbz="+gnbz);
					tr.insertBefore(beforeTr);
				});

			});
			
			
			function saveCol(guid,key,value){
				jQuery.post("xtwh_cxpz.do?method=updateColInfo",{guid:guid,key:key,value:value});
				
				var gnbz = jQuery("#gnbz").val();
				jQuery("#demoPage").load("xtwh_cxpz.do?method=ylxg&gnbz="+gnbz);
			}
			
			
		</script>
	</head>
	<body>
		<table class="dateline" width="100%" id="dataGrid">
			<thead>
				<tr>
					<td width="40%">
						列名
					</td>
					<td width="25%">
						宽度(%)
					</td>
					<td width="25%" title="打勾的显示，不打勾的不显示。">
						显示/隐藏
					</td>
					<td width="10%">排序</td>
				</tr>
			</thead>
			<tbody>
				<logic:present name="colList">
					<logic:iterate id="col" name="colList" indexId="i">
						<tr rowid="${col.guid }">
							<td name="labelTd">${col.label }</td>
							<td name="widthTd">${col.width }</td>
							<td name="hiddenTd">
								<logic:equal value="Y" name="col" property="ishidden">
									<input type="checkbox"  name="ishidden" value="Y"/>
								</logic:equal>
								<logic:equal value="N" name="col" property="ishidden">
									<input type="checkbox" name="ishidden" checked="checked" value="N"/>
								</logic:equal>
							</td>
							<td>
								<img src="<%=stylePath %>/images/down.gif" alt="down" class="pointer" name="down"/>
								<img src="<%=stylePath %>/images/up.gif" alt="up" class="pointer" name="up"/>
							</td>
						</tr>
					</logic:iterate>
				</logic:present>
			</tbody>
		</table>
	</body>
</html>
