<%@ page language="java" pageEncoding="GBK"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type='text/javascript' src='/xgxt/dwr/interface/gyglYcsjjc.js'></script>

		<script type="text/javascript">
			function getYcsjInfo(obj,yclx){
				gyglYcsjjc.getCountByType(yclx,function(data){
					jQuery('span:first',jQuery(obj)).attr('style','display:black');
					jQuery('b',jQuery(obj)).attr('innerHTML',data);
					
					if (data>0){
						jQuery(obj).attr('href','gyglYcjc.do?method=queryYcsj&yclx='+yclx);
					}
					
				});
			
				obj.onmouseover = null;
			}
			
			function showTbody(obj,objTbody){
				if(obj.className=="up"){
					obj.className="down";
					obj.parentNode.parentNode.className="cur-tr";
					document.getElementById(objTbody).style.display="none";
				}else{
					obj.className="up";
					obj.parentNode.parentNode.className="";
					document.getElementById(objTbody).style.display="";
				}
			}
			
			function yjjc(obj){
				var tr = jQuery(obj).parents('tbody');
				
				for (var i = 0 ; i < tr.length; i++){
					jQuery('a',tr[i]).mouseover();
				}
			}
			
			function checkAll(){
				var btns = jQuery('.btn_common');
				for (var i = 0 ; i < btns.length; i++){
					btns[i].click();
				}
			}
			
		</script>
		
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
	<body>
		<div class="tab">
			<div class="check_data">
				<img src="<%=stylePath%>/images/blue/ico_70.gif" />
				<div class="con">
					<h4>
						全面数据检测
					</h4>
					<h5>
						对导入的数据全方面的一个导常数据检测，包括指定数据检测的所有内容。
					</h5>
				</div>
				<a class="check_all" href="#" onclick="checkAll()">全面检测</a>
			</div>
			<table width="100%" border="0" class="formlist">
				<thead>
					<tr>
						<td colspan="2">
							<span>指定数据检测</span>
						</td>
					</tr>
				</thead>
				<thead>
					<tr>
						<th colspan="2">
							<a href="#" class="up"
								onclick="showTbody(this,'myTbody2');return false">宿舍相关</a>
						</th>
					</tr>
				</thead>
				<tbody id="myTbody2">
					<tr>
						<td rowspan="4">
							<button class="btn_common" onclick="yjjc(this);">
								一键检测
							</button>
							<br />
							<p style="margin-top:5px;">
								对宿舍相关的
								<br />
								信息进行检测
							</p>
						</td>
						<td>
							楼栋里有分配的宿舍超过最高楼层
							<a href="#" class="but_show"
								onmouseover="getYcsjInfo(this,'fpxxbfjbsz')"> 
								<img src="<%=stylePath%>/images/blue/ico_59.gif" /> 
								<span class="show_data"  style="display:none;">
									异常数据<font color="red"><b></b>
									</font>条 
								</span> </a>
						</td>
					</tr>
					<tr>
						<td>
							被分配的保留寝室
							<a href="#" class="but_show"
								onmouseover="getYcsjInfo(this,'BLQSBFP')"> <img
									src="<%=stylePath%>/images/blue/ico_59.gif" /> <span
								class="show_data"  style="display:none;">
									异常数据<font color="red"><b></b>
								</font>条 </span> </a>
						</td>
					</tr>
					<tr>
						<td>
							超过楼栋最高层数的宿舍
							<a href="#" class="but_show"
								onmouseover="getYcsjInfo(this,'SSCGZGC')"> <img
									src="<%=stylePath%>/images/blue/ico_59.gif" /> <span
								class="show_data"  style="display:none;">
									异常数据<font color="red"><b></b>
								</font>条 </span> </a>
						</td>
					</tr>
					<tr>
						<td>
							不符合楼栋性别要求的宿舍
							<a href="#" class="but_show"
								onmouseover="getYcsjInfo(this,'YXBFCSS')"> <img
									src="<%=stylePath%>/images/blue/ico_59.gif" /> <span
								class="show_data"  style="display:none;">
									异常数据<font color="red"><b></b>
								</font>条 </span> </a>
						</td>
					</tr>
				</tbody>

				<thead>
					<tr>
						<th colspan="2">
							<a href="#" class="up"
								onclick="showTbody(this,'myTbody3');return false">床位相关</a>
						</th>
					</tr>
				</thead>
				<tbody id="myTbody3">
					<tr>
						<td rowspan="2">
							<button class="btn_common" onclick="yjjc(this);">
								一键检测
							</button>
							<br />
							<p style="margin-top:5px;">
								对床位相关的
								<br />
								信息进行检测
							</p>
						</td>
						<td>
							超过楼栋最高层数的床位
							<a href="#" class="but_show"
								onmouseover="getYcsjInfo(this,'CWCGZGC')"> <img
									src="<%=stylePath%>/images/blue/ico_59.gif" /> <span
								class="show_data"  style="display:none;">
									异常数据<font color="red"><b></b>
								</font>条 </span> </a>
						</td>
					</tr>
					<tr>
						<td>
							超过楼栋最高层数的其它床位
							<a href="#" class="but_show"
								onmouseover="getYcsjInfo(this,'QTCWCGZGC')"> <img
									src="<%=stylePath%>/images/blue/ico_59.gif" /> <span
								class="show_data"  style="display:none;">
									异常数据<font color="red"><b></b>
								</font>条 </span> </a>
						</td>
					</tr>
				</tbody>
				<thead>
					<tr>
						<th colspan="2">
							<a href="#" class="up"
								onclick="showTbody(this,'myTbody4');return false">学生相关</a>
						</th>
					</tr>
				</thead>
				<tbody id="myTbody4">
					<tr>
						<td rowspan="6">
							<button class="btn_common" onclick="yjjc(this);">
								一键检测
							</button>
							<br />
							<p style="margin-top:5px;">
								对学生相关的
								<br />
								信息进行检测
							</p>
						</td>
						<td>
							多人分配在同张床的学生
							<a href="#" class="but_show"
								onmouseover="getYcsjInfo(this,'DRZTC')"> <img
									src="<%=stylePath%>/images/blue/ico_59.gif" /> <span
								class="show_data"  style="display:none;">
									异常数据<font color="red"><b></b>
								</font>条 </span> </a>
						</td>
					</tr>
					<tr>
						<td>
							分错部门所在寝室学生
							<a href="#" class="but_show"
								onmouseover="getYcsjInfo(this,'FCBM')"> <img
									src="<%=stylePath%>/images/blue/ico_59.gif" /> <span
								class="show_data"  style="display:none;">
									异常数据<font color="red"><b></b>
								</font>条 </span> </a>
						</td>
					</tr>
					<tr>
						<td>
							入住保留寝室的学生
							<a href="#" class="but_show"
								onmouseover="getYcsjInfo(this,'BLQSBZR')"> <img
									src="<%=stylePath%>/images/blue/ico_59.gif" /> <span
								class="show_data"  style="display:none;">
									异常数据<font color="red"><b></b>
								</font>条 </span> </a>
						</td>
					</tr>
					<tr>
						<td>
							入住行李床位的学生
							<a href="#" class="but_show"
								onmouseover="getYcsjInfo(this,'XLCWBZR')"> <img
									src="<%=stylePath%>/images/blue/ico_59.gif" /> <span
								class="show_data"  style="display:none;">
									异常数据<font color="red"><b></b>
								</font>条 </span> </a>
						</td>
					</tr>
					<tr>
						<td>
							因性别不符分错宿舍的学生
							<a href="#" class="but_show"
								onmouseover="getYcsjInfo(this,'YXBFCSS')"> <img
									src="<%=stylePath%>/images/blue/ico_59.gif" /> <span
								class="show_data"  style="display:none;">
									异常数据<font color="red"><b></b>
								</font>条 </span> </a>
						</td>
					</tr>
					<tr>
						<td>
							在已住人数超过床位总数宿舍入住的学生
							<a href="#" class="but_show"
								onmouseover="getYcsjInfo(this,'RZRSCGCWS')"> <img
									src="<%=stylePath%>/images/blue/ico_59.gif" /> <span
								class="show_data"  style="display:none;">
									异常数据<font color="red"><b></b>
								</font>条 </span> </a>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		<br />
	</body>
</html>
