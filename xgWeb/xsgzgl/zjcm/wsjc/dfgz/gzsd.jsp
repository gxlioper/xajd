<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/zjcm/wsjc/dfgz/js/gzsd.js"></script>
		<script language="javascript">
		</script>
	</head>
	<body>
		<html:form action="/cjWsfDfgz" method="post" styleId="DfgzForm">
			<input type="hidden" name="xn" value="${rs.xn}" id="xn"/>
			<input type="hidden" name="xq" value="${rs.xq}" id="xq"/>
			<input type="hidden" name="ccny" id="ccny" value="${rs.ccny}"/>
			<input type="hidden" name="dfszid" id="dfszid" value="${rs.dfszid}"/>
			<input type="hidden" id="pfzJson" name="pfzJson"/>
			<div style='tab;width:100%;overflow:hidden;'>
				<table align="center" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>时间设置</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="18%">
								学年
							</th>
							<td width="32%">
								${rs.xn}
							</td>
							<th width="18%">
								学期
							</th>
							<td width="32%">
								${xqmc}
							</td>
						</tr>
						<tr>
							<th>抽查年月</th>
					    	<td>
								${rs.ccny}
							</td>
							<th>开放维护时间</th>
					    	<td>
								${rs.wwsj}
								  至  
								${rs.wwzzsj } 
							</td>	
						</tr>
					</tbody>
				</table>
				<table align="center" class="formlist">
					<div class="toolbox">
					<!-- 按钮 -->
					<div class="buttonbox">
						<ul>
							<li><a href="javascript:void(0);" onclick="createRow();" class="btn_zj">增加</a></li>
							<li><a href="javascript:void(0);" onclick="del();" class="btn_sc">删除</a></li>		
							<li><a href="javascript:void(0);" onclick="update();" class="btn_xg">保存</a></li>
						</ul>
					</div>
				</div>
				<div class="formbox">
					<table id="dataTable" class="dateline">
						<thead>
							<tr>
								<td width="2%">&nbsp;
								</td>
								<td width="28%">评分组<span class="red">*</span></td>
								<td width="18%">抽查比例 <span class="red">*</span></td>
								<td width="19%">是否包含毕业班 <input type='checkbox' onclick="pcCheck(this)" name='pl_bhbyb'> </td>
							</tr>
						</thead>
						<tbody class="pfzTbody"></tbody>
					</table>
				</div>
			</table>
		</div>
		</html:form>
	</body>
</html>

