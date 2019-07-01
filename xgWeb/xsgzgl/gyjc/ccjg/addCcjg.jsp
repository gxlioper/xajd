<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/gyjc/ccjg/js/ccjg.js"></script>
		<script type="text/javascript" src="xsgzgl/gyjc/comm/js/comm.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
	</head>
	<body>
		<html:form action="/gyjc_ccjgcx" method="post" styleId="ccjgForm" onsubmit="return false;">
		<html:hidden property="lddm" styleId="lddm"/>
		<html:hidden property="xydm" styleId="xydm"/>
		<html:hidden property="qsh" styleId="qsh"/>
		<html:hidden property="js" styleId="js" value="xx"/>
		<input type="hidden" value="1,2,3" name="jjlx"/>
		<input type="hidden"  name="pfid" id="pfid"/>
		<table width="100%" border="0" class="formlist">
			<thead>
			<tr>
				<th colspan="4">
				<span>寝室信息&nbsp;&nbsp;
				<a class="name" href="javascript:;" onclick="showDialog('请选择一个寝室',750,500,'gyjc_ccjgcx.do?method=showQsxx&goto=${path}');return false;">选择寝室</a>
				</span>
				</th>
			</tr>
			</thead>
			<tbody>
			    <tr>
					<th>楼栋</th>
					<td id="ldmc" width="30%"></td>
					<th width="20%">层号</th>
					<td id="ch"></td>
				</tr>
				<tr>
					<th width="20%">寝室</th>
					<td id="qshTd" width="30%"></td>
					<th width="20%">学院</th>
					<td id="xymc"></td>
				</tr>
			</tbody>
			<thead>
				<tr>
					<th colspan="4">
					<span>卫生检查&nbsp;&nbsp;
					<a class="name" href="javascript:;" onclick="addConMoreUpdateTr('1','wsjc');">增加检查项</a>
					</span>
					</th>
				</tr>
			</thead>
			  <tbody id="wsjcTbody">
			  <tr>
			  <th><div align="center">卫生区块</div></th>
			  <th colspan="2"><div align="center">要求</div></th>
			  <th ><div align="center">操作</div></th>
			  </tr>
			  </tbody>
			  <thead>
				<tr>
					<th colspan="4">
					<span>安全检查&nbsp;&nbsp;
					<a class="name" href="javascript:;" onclick="addConMoreUpdateTr('2','aqjc');">增加检查项</a>
					</span>
					</th>
				</tr>
			</thead>
			  <tbody id="aqjcTbody">
			  <tr>
			  <th><div align="center">项目</div></th>
			  <th colspan="2"><div align="center">要求</div></th>
			  <th ><div align="center">操作</div></th>
			  </tr>
			  </tbody>
			 <thead>
				<tr>
					<th colspan="4">
					<span>纪律检查&nbsp;&nbsp;
					<a class="name" href="javascript:;" onclick="addConMoreUpdateTr('3','jljc');">增加检查项</a>
					</span>
					</th>
				</tr>
			</thead>
			  <tbody id="jljcTbody">
			  <tr>
			  <th><div align="center">项目</div></th>
			  <th colspan="2"><div align="center">要求</div></th>
			  <th ><div align="center">操作</div></th>
			  </tr>
			  </tbody>
			  <thead>
				<tr>
					<th colspan="4">
					<span>其他信息
					</span>
					</th>
				</tr>
			</thead>
			  <tbody>
			  <tr><th><span class="red">*</span>检查日期
			  </th>
			  <td colspan="3">
				<html:text property="jcrq" styleId="jcrq"
					onfocus="showCalendar('jcrq','y-mm-dd',true);" />
			</td>
			  </tr>
			  </tbody>
			  <thead>
				<tr>
					<th colspan="4">
					<span>附件信息<html:hidden property="fjid" styleId="fjid" />
					</span>
							<button type="button" onclick="addFj();return false;" class="btn_01">增加</button>
					</th>
				</tr>
			</thead>
			  <tbody>
			  <tr>
				<td colspan="4">
					<table width="100%">
						<thead>
							<tr>
								<th width="50%" colspan="2">
									文件
								</th>
								<th width="50%"  colspan="2">
									操作
								</th>
							</tr>
						</thead>
						<tbody id="tbody_fj">
						</tbody>
					</table>
				</td>			  
			  </tr>
			  </tbody>
			  </table>
				<div style="height:35px"></div>
			    <div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
				<tfoot>
					<tr>
						<td colspan="4">
							<div class="bz">
								"<span class="red">*</span>"为必填项
							</div>
							<div class="btn">
								<button type="button" onclick="saveCcjg('save')">
									保 存
								</button>
								<button type="button" onclick="Close();return false;">
									关 闭
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