<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
		<title><bean:message key="lable.title" /></title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control" http-equiv="no-cache" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="正方软件 zfsoft" />
		<link id="csss" rel="stylesheet" rev="stylesheet"
			href="style/main.css" type="text/css" media="all" />
		<link id="csssDate" rel="stylesheet" rev="stylesheet"
			href="js/calendar.css" type="text/css" media="all" />
		<script type='text/javascript' src='/xgxt/dwr/interface/getXljkSjm.js'></script>
		<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/xljkFunction.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
	</head>
	<script language="javascript">
		function zxpc_sj_view(){
			var sjlsh = curr_row.cells[0].innerText;
			showTopWin('/xgxt/xljk_xlcs_zxpc.do?act=zxpc&doType=sj_view&sjlsh='+sjlsh,550,400);
		}
		function create_testPaper(){
			var sjlsh = curr_row.cells[0].innerText;
			showTopWin('/xgxt/xljk_xlcs_zxpc.do?act=zxpc&doType=create_testPaper&sjlsh='+sjlsh,650,600);
		}
		function check_stu(){
			var sjlsh = curr_row.cells[0].innerText;
			showTopWin('/xgxt/xljk_xlcs_zxpc.do?act=zxpc&doType=stu_check&sjlsh='+sjlsh,450,300);
		}

		function checkStuAlredayDo(){
			if(curr_row!=null) {
				var xh = document.getElementById("xh").value;
				getXljkSjm.isStuAlreadyDid(xh,function(data){
					if(data){
						//alert("您已经做了该份试卷了！");
						test_xljk();
					}else{
						test_xljk();
					}
				});
			}else {
				alert('请先选择试卷!');
				return false;
			}
		}
	</script>
	<body >
		<%
		response.setHeader("Pragma","No-cache");
		response.setHeader("Cache-Control","no-cache");
		response.setDateHeader("Expires", 0);
		%>
	<logic:equal name="message" value="record_success">
		<script>
			alert("提交成功！");
		</script>
	</logic:equal>
		<html:form action="/xljk_xlcs_zxpc" method="post">
		
		<input type="hidden" name="xh" id="xh" value="<bean:write name="userName" scope="session"/>"/>
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>心理健康 - 心理测试 - 在线测试</a>
				</p>
			</div>
			
			<div class="toolbox">
				<!-- 按钮 -->
					<div class="buttonbox">
						<ul>
						<li>
						<a class="btn_sz"
							onclick="if(curr_row!=null) zxpc_sj_view();else {alert('请先选择试卷!');return false;}">
							试卷详情
						</a>
						</li>
						<li>
						<a class="btn_cs"
							onclick="checkStuAlredayDo()">
							开始测试
						</a>
					</li>
						</ul>
					</div>
				<div class="searchtab">
					<table width="100%" border="0">
						<tfoot>
							<tr>
								<td colspan="6">
									<div class="btn">
										<button class="" style="" id="search_go"
											onclick="allNotEmpThenGo('/xgxt/xljk_xlcs_zxpc.do?act=zxpc&doType=sj_search')">
											查询
										</button>
										<input type="hidden" name="go" value="a" />
										&nbsp;&nbsp;&nbsp;&nbsp;
										<button class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
											重置
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
						<tbody>
							<tr align="left">
								<th align="left">
								<div  align="left">
									试卷名&nbsp;&nbsp;
									<html:select property="sjlsh" style="width:160px" styleId="sjlsh">
									<html:option value=""></html:option>
									<html:options collection="sjList" property="SJLSH"
										labelProperty="SJM" />
								</html:select>
								</div>
								</th>
								<td >
								&nbsp;&nbsp;&nbsp;&nbsp;
								</td>
								<th colspan="4">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
								
								</tr>
						</tbody>
					</table>
				</div>
			</div>			
			
			<div class="formbox">
				<h3 class="datetitle_01">
					<span> 查询结果&nbsp;&nbsp; <logic:empty name="rs">
							<font color="red">未找到任何记录！</font>
						</logic:empty> <logic:notEmpty name="rs">
			 		当前试卷数：
							<bean:write name="rsNum" />
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<font color="blue">提示：双击一行可以查看详细信息；单击表头可以排序</font>
						</logic:notEmpty> </span>
				</h3>

				<logic:notEmpty name="rs">

						<table summary=""  class="dateline tablenowrap" align="center"
							width="100%" id="rsTable">
							<thead>
								<tr align="center" style="cursor:hand">
									<logic:iterate id="tit" name="topTr" offset="1">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)" nowrap>
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<tbody>
								<logic:iterate name="rs" id="s">
								<tr onclick="rowOnClick(this)" style="cursor:hand"
									ondblclick="zxpc_sj_view()">
									<td>
									<input type="hidden" name="sjlsh" id="sjlsh" value="<bean:write name="s" property="SJLSH" />"/>
										<bean:write name="s" property="SJLSH" />
									</td>
									<td>
										<bean:write name="s" property="SJM" />
									</td>
									<td>
										<bean:write name="s" property="SJXSBJ" />
									</td>
									<td>
										<bean:write name="s" property="SJXD" />
									</td>
									<td>
										<bean:write name="s" property="JRSJ" />
									</td>
								</tr>
							</logic:iterate>
							</tbody>
						</table>
						<!--分页显示-->
					
					<!--分页显示-->
						</logic:notEmpty>
					
			</div>		
				
		
		</html:form>
	</body>
</html>
