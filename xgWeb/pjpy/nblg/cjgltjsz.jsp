<%@ page language="java" contentType="text/html; charset=GBK"%>
<jsp:directive.page import="java.util.List" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
<%@ include file="/syscommon/pagehead_V4.ini"%>
<script type="text/javascript">
<!--
	function modi() {
		if (curr_row==null||curr_row=='') {
			alert('请选择要操作的数据行!');
			return;
		}
		var pk = curr_row.cells[0].getElementsByTabName("input")[0].value;
		var url = 'pjpy_nblg_cjtjmodi.do?pkValue=';
		url += pk;
		showTopWin(url, 400, 250);
	}
//-->
</script>
</head>
<body>
	<html:form action="/pjpynblgwh" method="post">
		<!-- 返回页面时,执行查询操作 -->
		<input type="hidden" name="search_go" id="search_go"
			onclick="refreshForm('pjpy_nblg_cjtjgl.do');" />
		<div class="tab_cur">
			<p class="location">
				<em>当前所在位置：</em><a>评奖评优 - 信息维护 - 课程成绩与等级考试成绩维护 - 成绩条件过滤设置</a>
			</p>
		</div>	
		<logic:equal value="yes" name="writable">
					<div class="toolbox">
					<div class="buttonbox">	
						<input type="hidden" name="ts" id="ts"/>
						<ul>
							<li><a href="#" class="btn_zj" onclick="showTopWin('pjpy_nblg_cjtjadd.do','400','250')">增加</a></li>
							<li><a href="#" class="btn_xg" onclick="" style="width:80px">修改</a></li>
							<li><a href="#" class="btn_sc" onclick="">删除</a></li>
						</ul>
					</div>
					</div>
		</logic:equal>	
			 <div class="formbox">
				<logic:empty name="rs">
				    <h3 class="datetitle_01">
				    <span>
				    	查询结果&nbsp;&nbsp;
							<font color="red">未找到任何记录！</font> 
				    </span>
				    </h3>
				 </logic:empty>
				<logic:notEmpty name="rs">
						<table width="98%" id="rsTable" class="dateline">
							<thead>
								<tr align="center" style="cursor:hand">
									<td align="center" onclick="tableSort(this)" width="50%" nowrap>
										性质
									</td>
									<td align="center" onclick="tableSort(this)" width="48%" nowrap>
										名称(课程名称或课程类型名称)
									</td>
								</tr>
							</thead>
							<logic:iterate name="rs" id="s">
								<tr onclick="rowOnClick(this)" align="center"
									style="cursor:hand;">
									<logic:iterate id="v" name="s">
										<td align=center>
											<bean:write name="v" />
										</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
						</table>
			</logic:notEmpty>
			<div id="tmp"></div>
			<!--<logic:equal value="yes" name="writable">
				<div class="buttontool" align="center" id="btn" style="position:absolute;width:100%;">
					<button id="btn_add" class="button2"
						onclick="showTopWin('pjpy_nblg_cjtjadd.do','400','250')" style="width:80px">
						增加
					</button>
					&nbsp;&nbsp;&nbsp;
					<button id="btn_add" class="button2"
						onclick="" style="width:80px">
						修改
					</button>
										&nbsp;&nbsp;&nbsp;
					<button id="btn_add" class="button2"
						onclick="" style="width:80px">
						删除
					</button>
					
				</div>
			</logic:equal>-->
			<script type="text/javascript" src="js/bottomButton.js"></script>
	</html:form>
</body>
</html>
