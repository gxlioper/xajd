<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	
	
	<style type="text/css">
		.left{
			width:120px;
			float:left;
		}
		.left table{
			background:#E0ECFF;
		}
		.left td{
			background:#eee;
		}
		.right{
			float:right;
			width:600px;
		}
		.right table{
			background:#E0ECFF;
			width:100%;
		}
		.right td{
			background:#fafafa;
			text-align:center;
			padding:2px;
		}
		.right td{
			background:#E0ECFF;
		}
		.right td.zdname{
			background:#fafafa;
			width:100px;
		}
		.right td.over{
			background:#FBEC88;
		}
		.item{
			text-align:center;
			border:1px solid #499B33;
			background:#fafafa;
			width:100px;
		}
		.assigned{
			border:1px solid #BC2A4D;
		}
		
	</style>
	<script>
		jQuery(function(){
			jQuery('.left .item').draggable({
				revert:true,
				proxy:'clone'
			});
			jQuery('.right td.zdname').droppable({
				onDragEnter:function(){
					jQuery(this).addClass('over');
				},
				onDragLeave:function(){
					jQuery(this).removeClass('over');
				},
				onDrop:function(e,source){
					jQuery(this).removeClass('over');
					if (jQuery(source).hasClass('assigned')){
						jQuery(this).append(source);
					} else {	
						var c = jQuery(source).clone().addClass('assigned');
						jQuery(this).empty().append(c);
						$("text_"+this.id).innerHTML="<input type='text' value='"+source.innerHTML+"'>";
						c.draggable({
							revert:true
						});
					}
				}
			});
		});
	</script>
</head>
<body>
<div style="width:750px;">
	<div class="left">
		<table>
			<logic:iterate name="swsqzdList" id="zdList">
				<tr>
					<td>
						<div class="item">
							${zdList.zdm }
						</div>
					</td>
				</tr>
			</logic:iterate>
			<logic:iterate name="xsxxzdList" id="xsxxzd">
				<tr>
					<td>
						<div class="item">
							${xsxxzd.zdm }
						</div>
					</td>
				</tr>
			</logic:iterate>
		</table>
	</div>
	<div class="right">
		<table>
			<tr>
				<td class="zdname" id="zdname_0" style="width:16%">
					&nbsp;
				</td>
				<td class="drop" id="text_zdname_0" style="width:34%">
					&nbsp;
				</td>
				<td class="zdname" id="zdname_1" style="width:16%">
					&nbsp;
				</td>
				<td class="drop" id="text_zdname_1" style="width:34%">
					&nbsp;
				</td>
			</tr>
			<tr>
				<td class="zdname"></td>
				<td class="drop"></td>
				<td class="zdname"></td>
				<td class="drop"></td>
			</tr>
			<tr>
				<td class="zdname"></td>
				<td class="drop"></td>
				<td class="zdname"></td>
				<td class="drop"></td>
			</tr>
			<tr>
				<td class="zdname"></td>
				<td class="drop"></td>
				<td class="zdname"></td>
				<td class="drop"></td>
			</tr>
			<tr>
				<td class="zdname"></td>
				<td class="drop"></td>
				<td class="zdname"></td>
				<td class="drop"></td>
			</tr>
			<tr>
				<td class="zdname"></td>
				<td class="drop"></td>
				<td class="zdname"></td>
				<td class="drop"></td>
			</tr>
		</table>
	</div>
</div>
</body>
</html>
