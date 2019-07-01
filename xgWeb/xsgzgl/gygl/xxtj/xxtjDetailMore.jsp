<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@page import="xsgzgl.gygl.xxtj.LdModel"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="xsgzgl.gygl.xxtj.QsModel"%>
<%@page import="java.util.HashMap"%>
<%@page import="xgxt.utils.String.StringUtils"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>

		<script language="javascript" src="js/String.js"></script>
		<script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/qtsjFunction.js"></script>
		<script language="javascript" src="js/BatAlert.js"></script>
		<script type="text/javascript" src="dwr/interface/systemFunction.js"></script>

		<script> 
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
		</script>
		<!--[if IE 6]> 
		<script src="../../js/ie6comm.js"></script> 
		<script> 
		DD_belatedPNG.fix('img,.mainbody,.topframe,.mainframe,.botframe,.menu,.type_mainframe'); 
		</script> 
		<![endif]-->
		<style>
			table.formlist thead tr.cur-tr th {
				background-color: #d5ebf8;
			}
		</style>
	</head>
	<body>
		<div class="tab">
			<table width="100%" border="0" class="formlist">
				<thead class="thead_title">
					<tr>
						<td colspan="6">
							<span>楼栋信息</span>
						</td>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td colspan="6">
							<table width="100%" border="0" class="table_nobor">
								<tr>
									<td>
										楼栋代码: 001
									</td>
									<td>
										楼栋名称: A楼
									</td>
									<td>
										所属校区: 玉泉校区
									</td>
									<td>
										所属园区: 御花园
									</td>
									<td>
										层数: 7
									</td>
									<td>
										性别限制:女
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</tbody>
				<thead class="thead_title">
					<tr>
						<td colspan="6">
							<span>寝室信息</span>
						</td>
					</tr>
				</thead>
				<thead>
					<tr>
						<th>
							<a href="#" class="up"
								onclick="showTbody(this,'myTbody1');return false">1楼</a>
						</th>
						<th colspan="5">
							<em class="chamber">寝室（个）：10</em><em class="chamber">已分配（个）：15</em><em
								class="chamber">已入住（人）：20</em>
						</th>
					</tr>
				</thead>
				<tbody id="myTbody1">
					<tr>
						<td>
							<span class="num">101</span>
						</td>
						<td>
							<span class="num">102</span>
						</td>
						<td>
							<span class="num">103</span>
						</td>
						<td>
							<span class="num">104</span>
						</td>
						<td>
							<span class="num">105</span>
						</td>
						<td>
							<span class="num">106</span>
						</td>
					</tr>
					<tr>
						<td>
							<p>
								床位数：6
								<br />
								分配对象：理学院
								<br />
								已入住：5人
							</p>
							<div
								onmouseover="javascript:document.getElementById('helpcon1').style.display='block';this.style.position='relative'"
								onmouseout="javascript:document.getElementById('helpcon1').style.display='none';this.style.position=''">
								<a href="#" class="check" onclick="showhid('helpcon1');">查看人员</a>
								<div class="check_people" id="helpcon1" style="display: none;">
									<ul>
										<li>
											<span>1号床：诸葛小燕</span>
										</li>
										<li>
											<span>2号床：陈小明</span>
										</li>
										<li>
											<span>3号床：李小小</span>
										</li>
										<li>
											<span>4号床：李幸福</span>
										</li>
										<li>
											<span>5号床：李小小</span>
										</li>
										<li>
											<span>6号床：李幸福</span>
										</li>
									</ul>
								</div>
							</div>

						</td>
						<td>
							<p>
								床位数：6
								<br />
								分配对象：理学院
								<br />
								已入住：5人
							</p>
							<a href="#" class="check">查看人员</a>
						</td>
						<td>
							<p>
								床位数：6
								<br />
								分配对象：理学院
								<br />
								已入住：5人
							</p>
							<a href="#" class="check">查看人员</a>
						</td>
						<td>
							<p>
								床位数：6
								<br />
								分配对象：理学院
								<br />
								已入住：5人
							</p>
							<a href="#" class="check">查看人员</a>
						</td>
						<td>
							<p>
								床位数：6
								<br />
								分配对象：理学院
								<br />
								已入住：5人
							</p>
							<a href="#" class="check">查看人员</a>
						</td>
						<td>
							<p>
								床位数：6
								<br />
								分配对象：理学院
								<br />
								已入住：5人
							</p>
							<a href="#" class="check">查看人员</a>
						</td>
					</tr>
					<tr>
						<td>
							<span class="num">101</span>
						</td>
						<td>
							<span class="num">102</span>
						</td>
						<td>
							<span class="num">103</span>
						</td>
						<td>
							<span class="num">104</span>
						</td>
						<td>
							<span class="num">105</span>
						</td>
						<td>
							<span class="num">106</span>
						</td>
					</tr>
					<tr>
						<td>
							<p>
								床位数：6
								<br />
								分配对象：理学院
								<br />
								已入住：5人
							</p>
							<a href="#" class="check">查看人员</a>
						</td>
						<td>
							<p>
								床位数：6
								<br />
								分配对象：理学院
								<br />
								已入住：5人
							</p>
							<a href="#" class="check">查看人员</a>
						</td>
						<td>
							<p>
								床位数：6
								<br />
								分配对象：理学院
								<br />
								已入住：5人
							</p>
							<a href="#" class="check">查看人员</a>
						</td>
						<td>
							<p>
								床位数：6
								<br />
								分配对象：理学院
								<br />
								已入住：5人
							</p>
							<a href="#" class="check">查看人员</a>
						</td>
						<td>
							<p>
								床位数：6
								<br />
								分配对象：理学院
								<br />
								已入住：5人
							</p>
							<a href="#" class="check">查看人员</a>
						</td>
						<td>
							<p>
								床位数：6
								<br />
								分配对象：理学院
								<br />
								已入住：5人
							</p>
							<a href="#" class="check">查看人员</a>
						</td>
					</tr>
				</tbody>
				<thead>
					<tr>
						<th>
							<a href="#" class="down"
								onclick="showTbody(this,'myTbody2');return false">2楼</a>
						</th>
						<th colspan="5">
							<em class="chamber">寝室（个）：10</em><em class="chamber">已分配（个）：15</em><em
								class="chamber">已入住（人）：20</em>
						</th>
					</tr>
				</thead>
				<tbody id="myTbody2" style="display: none;">
					<tr>
						<td>
							<span class="num">101</span>
						</td>
						<td>
							<span class="num">102</span>
						</td>
						<td>
							<span class="num">103</span>
						</td>
						<td>
							<span class="num">104</span>
						</td>
						<td>
							<span class="num">105</span>
						</td>
						<td>
							<span class="num">106</span>
						</td>
					</tr>
					<tr>
						<td>
							<p>
								床位数：6
								<br />
								分配对象：理学院
								<br />
								已入住：5人
							</p>
							<a href="#" class="check">查看人员</a>
						</td>
						<td>
							<p>
								床位数：6
								<br />
								分配对象：理学院
								<br />
								已入住：5人
							</p>
							<a href="#" class="check">查看人员</a>
						</td>
						<td>
							<p>
								床位数：6
								<br />
								分配对象：理学院
								<br />
								已入住：5人
							</p>
							<a href="#" class="check">查看人员</a>
						</td>
						<td>
							<p>
								床位数：6
								<br />
								分配对象：理学院
								<br />
								已入住：5人
							</p>
							<a href="#" class="check">查看人员</a>
						</td>
						<td>
							<p>
								床位数：6
								<br />
								分配对象：理学院
								<br />
								已入住：5人
							</p>
							<a href="#" class="check">查看人员</a>
						</td>
						<td>
							<p>
								床位数：6
								<br />
								分配对象：理学院
								<br />
								已入住：5人
							</p>
							<a href="#" class="check">查看人员</a>
						</td>
					</tr>
					<tr>
						<td>
							<span class="num">101</span>
						</td>
						<td>
							<span class="num">102</span>
						</td>
						<td>
							<span class="num">103</span>
						</td>
						<td>
							<span class="num">104</span>
						</td>
						<td>
							<span class="num">105</span>
						</td>
						<td>
							<span class="num">106</span>
						</td>
					</tr>
					<tr>
						<td>
							<p>
								床位数：6
								<br />
								分配对象：理学院
								<br />
								已入住：5人
							</p>
							<a href="#" class="check">查看人员</a>
						</td>
						<td>
							<p>
								床位数：6
								<br />
								分配对象：理学院
								<br />
								已入住：5人
							</p>
							<a href="#" class="check">查看人员</a>
						</td>
						<td>
							<p>
								床位数：6
								<br />
								分配对象：理学院
								<br />
								已入住：5人
							</p>
							<a href="#" class="check">查看人员</a>
						</td>
						<td>
							<p>
								床位数：6
								<br />
								分配对象：理学院
								<br />
								已入住：5人
							</p>
							<a href="#" class="check">查看人员</a>
						</td>
						<td>
							<p>
								床位数：6
								<br />
								分配对象：理学院
								<br />
								已入住：5人
							</p>
							<a href="#" class="check">查看人员</a>
						</td>
						<td>
							<p>
								床位数：6
								<br />
								分配对象：理学院
								<br />
								已入住：5人
							</p>
							<a href="#" class="check">查看人员</a>
						</td>
					</tr>
				</tbody>
				<thead>
					<tr>
						<th>
							<a href="#" class="down"
								onclick="showTbody(this,'myTbody3');return false">3楼</a>
						</th>
						<th colspan="5">
							<em class="chamber">寝室（个）：10</em><em class="chamber">已分配（个）：15</em><em
								class="chamber">已入住（人）：20</em>
						</th>
					</tr>
				</thead>
				<tbody id="myTbody3" style="display: none;">
					<tr>
						<td>
							<span class="num">101</span>
						</td>
						<td>
							<span class="num">102</span>
						</td>
						<td>
							<span class="num">103</span>
						</td>
						<td>
							<span class="num">104</span>
						</td>
						<td>
							<span class="num">105</span>
						</td>
						<td>
							<span class="num">106</span>
						</td>
					</tr>
					<tr>
						<td>
							<p>
								床位数：6
								<br />
								分配对象：理学院
								<br />
								已入住：5人
							</p>
							<a href="#" class="check">查看人员</a>
						</td>
						<td>
							<p>
								床位数：6
								<br />
								分配对象：理学院
								<br />
								已入住：5人
							</p>
							<a href="#" class="check">查看人员</a>
						</td>
						<td>
							<p>
								床位数：6
								<br />
								分配对象：理学院
								<br />
								已入住：5人
							</p>
							<a href="#" class="check">查看人员</a>
						</td>
						<td>
							<p>
								床位数：6
								<br />
								分配对象：理学院
								<br />
								已入住：5人
							</p>
							<a href="#" class="check">查看人员</a>
						</td>
						<td>
							<p>
								床位数：6
								<br />
								分配对象：理学院
								<br />
								已入住：5人
							</p>
							<a href="#" class="check">查看人员</a>
						</td>
						<td>
							<p>
								床位数：6
								<br />
								分配对象：理学院
								<br />
								已入住：5人
							</p>
							<a href="#" class="check">查看人员</a>
						</td>
					</tr>
					<tr>
						<td>
							<span class="num">101</span>
						</td>
						<td>
							<span class="num">102</span>
						</td>
						<td>
							<span class="num">103</span>
						</td>
						<td>
							<span class="num">104</span>
						</td>
						<td>
							<span class="num">105</span>
						</td>
						<td>
							<span class="num">106</span>
						</td>
					</tr>
					<tr>
						<td>
							<p>
								床位数：6
								<br />
								分配对象：理学院
								<br />
								已入住：5人
							</p>
							<a href="#" class="check">查看人员</a>
						</td>
						<td>
							<p>
								床位数：6
								<br />
								分配对象：理学院
								<br />
								已入住：5人
							</p>
							<a href="#" class="check">查看人员</a>
						</td>
						<td>
							<p>
								床位数：6
								<br />
								分配对象：理学院
								<br />
								已入住：5人
							</p>
							<a href="#" class="check">查看人员</a>
						</td>
						<td>
							<p>
								床位数：6
								<br />
								分配对象：理学院
								<br />
								已入住：5人
							</p>
							<a href="#" class="check">查看人员</a>
						</td>
						<td>
							<p>
								床位数：6
								<br />
								分配对象：理学院
								<br />
								已入住：5人
							</p>
							<a href="#" class="check">查看人员</a>
						</td>
						<td>
							<p>
								床位数：6
								<br />
								分配对象：理学院
								<br />
								已入住：5人
							</p>
							<a href="#" class="check">查看人员</a>
						</td>
					</tr>
				</tbody>
				<thead>
					<tr>
						<th>
							<a href="#" class="down"
								onclick="showTbody(this,'myTbody4');return false">4楼</a>
						</th>
						<th colspan="5">
							<em class="chamber">寝室（个）：10</em><em class="chamber">已分配（个）：15</em><em
								class="chamber">已入住（人）：20</em>
						</th>
					</tr>
				</thead>
				<tbody id="myTbody4" style="display: none;">
					<tr>
						<td>
							<span class="num">101</span>
						</td>
						<td>
							<span class="num">102</span>
						</td>
						<td>
							<span class="num">103</span>
						</td>
						<td>
							<span class="num">104</span>
						</td>
						<td>
							<span class="num">105</span>
						</td>
						<td>
							<span class="num">106</span>
						</td>
					</tr>
					<tr>
						<td>
							<p>
								床位数：6
								<br />
								分配对象：理学院
								<br />
								已入住：5人
							</p>
							<a href="#" class="check">查看人员</a>
						</td>
						<td>
							<p>
								床位数：6
								<br />
								分配对象：理学院
								<br />
								已入住：5人
							</p>
							<a href="#" class="check">查看人员</a>
						</td>
						<td>
							<p>
								床位数：6
								<br />
								分配对象：理学院
								<br />
								已入住：5人
							</p>
							<a href="#" class="check">查看人员</a>
						</td>
						<td>
							<p>
								床位数：6
								<br />
								分配对象：理学院
								<br />
								已入住：5人
							</p>
							<a href="#" class="check">查看人员</a>
						</td>
						<td>
							<p>
								床位数：6
								<br />
								分配对象：理学院
								<br />
								已入住：5人
							</p>
							<a href="#" class="check">查看人员</a>
						</td>
						<td>
							<p>
								床位数：6
								<br />
								分配对象：理学院
								<br />
								已入住：5人
							</p>
							<a href="#" class="check">查看人员</a>
						</td>
					</tr>
					<tr>
						<td>
							<span class="num">101</span>
						</td>
						<td>
							<span class="num">102</span>
						</td>
						<td>
							<span class="num">103</span>
						</td>
						<td>
							<span class="num">104</span>
						</td>
						<td>
							<span class="num">105</span>
						</td>
						<td>
							<span class="num">106</span>
						</td>
					</tr>
					<tr>
						<td>
							<p>
								床位数：6
								<br />
								分配对象：理学院
								<br />
								已入住：5人
							</p>
							<a href="#" class="check">查看人员</a>
						</td>
						<td>
							<p>
								床位数：6
								<br />
								分配对象：理学院
								<br />
								已入住：5人
							</p>
							<a href="#" class="check">查看人员</a>
						</td>
						<td>
							<p>
								床位数：6
								<br />
								分配对象：理学院
								<br />
								已入住：5人
							</p>
							<a href="#" class="check">查看人员</a>
						</td>
						<td>
							<p>
								床位数：6
								<br />
								分配对象：理学院
								<br />
								已入住：5人
							</p>
							<a href="#" class="check">查看人员</a>
						</td>
						<td>
							<p>
								床位数：6
								<br />
								分配对象：理学院
								<br />
								已入住：5人
							</p>
							<a href="#" class="check">查看人员</a>
						</td>
						<td>
							<p>
								床位数：6
								<br />
								分配对象：理学院
								<br />
								已入住：5人
							</p>
							<a href="#" class="check">查看人员</a>
						</td>
					</tr>
				</tbody>
				<thead>
					<tr>
						<th>
							<a href="#" class="down"
								onclick=
	showTbody(this, 'myTbody5');
	return false;
>5楼</a>
						</th>
						<th colspan="5">
							<em class="chamber">寝室（个）：10</em><em class="chamber">已分配（个）：15</em><em
								class="chamber">已入住（人）：20</em>
						</th>
					</tr>
				</thead>
				<tbody id="myTbody5" style="display: none;">
					<tr>
						<td>
							<span class="num">101</span>
						</td>
						<td>
							<span class="num">102</span>
						</td>
						<td>
							<span class="num">103</span>
						</td>
						<td>
							<span class="num">104</span>
						</td>
						<td>
							<span class="num">105</span>
						</td>
						<td>
							<span class="num">106</span>
						</td>
					</tr>
					<tr>
						<td>
							<p>
								床位数：6
								<br />
								分配对象：理学院
								<br />
								已入住：5人
							</p>
							<a href="#" class="check">查看人员</a>
						</td>
						<td>
							<p>
								床位数：6
								<br />
								分配对象：理学院
								<br />
								已入住：5人
							</p>
							<a href="#" class="check">查看人员</a>
						</td>
						<td>
							<p>
								床位数：6
								<br />
								分配对象：理学院
								<br />
								已入住：5人
							</p>
							<a href="#" class="check">查看人员</a>
						</td>
						<td>
							<p>
								床位数：6
								<br />
								分配对象：理学院
								<br />
								已入住：5人
							</p>
							<a href="#" class="check">查看人员</a>
						</td>
						<td>
							<p>
								床位数：6
								<br />
								分配对象：理学院
								<br />
								已入住：5人
							</p>
							<a href="#" class="check">查看人员</a>
						</td>
						<td>
							<p>
								床位数：6
								<br />
								分配对象：理学院
								<br />
								已入住：5人
							</p>
							<a href="#" class="check">查看人员</a>
						</td>
					</tr>
					<tr>
						<td>
							<span class="num">101</span>
						</td>
						<td>
							<span class="num">102</span>
						</td>
						<td>
							<span class="num">103</span>
						</td>
						<td>
							<span class="num">104</span>
						</td>
						<td>
							<span class="num">105</span>
						</td>
						<td>
							<span class="num">106</span>
						</td>
					</tr>
					<tr>
						<td>
							<p>
								床位数：6
								<br />
								分配对象：理学院
								<br />
								已入住：5人
							</p>
							<a href="#" class="check">查看人员</a>
						</td>
						<td>
							<p>
								床位数：6
								<br />
								分配对象：理学院
								<br />
								已入住：5人
							</p>
							<a href="#" class="check">查看人员</a>
						</td>
						<td>
							<p>
								床位数：6
								<br />
								分配对象：理学院
								<br />
								已入住：5人
							</p>
							<a href="#" class="check">查看人员</a>
						</td>
						<td>
							<p>
								床位数：6
								<br />
								分配对象：理学院
								<br />
								已入住：5人
							</p>
							<a href="#" class="check">查看人员</a>
						</td>
						<td>
							<p>
								床位数：6
								<br />
								分配对象：理学院
								<br />
								已入住：5人
							</p>
							<a href="#" class="check">查看人员</a>
						</td>
						<td>
							<p>
								床位数：6
								<br />
								分配对象：理学院
								<br />
								已入住：5人
							</p>
							<a href="#" class="check">查看人员</a>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		<br />
	</body>
</html>
