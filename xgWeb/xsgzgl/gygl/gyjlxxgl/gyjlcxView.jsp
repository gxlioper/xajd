<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<link rel="stylesheet" type="text/css" href="js/jquery/themes/default/easyui.css" />
	<link rel="stylesheet" type="text/css" href="js/jquery/themes/icon.css" />
	
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript" src="js/check.js"></script>
	<script language="javascript" src="js/qtsjFunction.js"></script>
	<script type="text/javascript" src="dwr/interface/systemFunction.js"></script>
	<script type="text/javascript" src="xsgzgl/comm/fileUpload/f_q.js"></script>
</head>
<body>
		<div style="overflow-x:hidden;overflow-y:auto;padding-right:18px;">
		<table class="formlist" width="95%">
			<thead>
				<tr>
					<th colspan="4">
						<span>学生信息</span>
					</th>
				</tr>
			</thead>
			<tbody>
			<tr>
				<th align="right" width="20%">
					学号
				</th>
				<td align="left" width="30%" nowrap="nowrap">
					${rs.xh }
				</td>
				
				<th  align="right" width="20%">
					姓名			
				</th>
				<td  width="30%">
					${rs.xm }
				</td>
			</tr>
			<tr>
				<th><bean:message key="lable.xsgzyxpzxy" /></th>
				<td>${rs.xymc }</td>
				<th>专业</th>
				<td>${rs.zymc }</td>
			</tr>
			<tr>
				<th>班级</th>
				<td>${rs.bjmc }</td>
				<th>年级</th>
				<td>${rs.nj }</td>
			</tr>
			<tr>
				<th>楼栋名称</th>
				<td>${rs.ldmc }</td>
				<th>寝室号</th>
				<td>${rs.qsh }</td>
			</tr>
			<tr>
				<th>1111床位号</th>
				<td>${rs.cwh }</td>
				<th>寝室电话1111</th>
				<td>${rs.qsdh }</td>
			</tr>
			</tbody>
			</table>
			<table width="95%" border="0" class="formlist">
			<thead>
				<tr>
					<th colspan="4">
						<span>
							<logic:equal value="11799" name="xxdm">
									公寓奖惩信息
							</logic:equal>
							<logic:notEqual value="11799" name="xxdm">
									公寓纪律信息
							</logic:notEqual>
						</span>
					</th>
				</tr>
			</thead>
			<tbody>
			<tr>
				<th align="right" width="20%">
					违纪学年
				</th>
				<td align="left" width="30%">
					${rs.wjxn}
				</td>
					<th align="right" width="20%">
						违纪学期
					</th>
				<td align="left" width="30%">
					${rs.xqmc}
				</td>
			</tr>
			<tr>
				<th align="right" width="20%">
					<logic:equal value="11799" name="xxdm">
							奖惩大类
					</logic:equal>
					<logic:notEqual value="11799" name="xxdm">
							纪律大类
					</logic:notEqual>				
				</th>
				<td align="left" width="30%">
					${rs.jldl}
				</td>
				<th align="right" width="20%">
					<logic:equal value="11799" name="xxdm">
							奖惩类别
					</logic:equal>
					<logic:notEqual value="11799" name="xxdm">
							纪律类别
					</logic:notEqual>
				</th>
				<td align="left" width="30%" >
					${rs.jldb}
				</td>
			</tr>
			<tr>
					<th width="20%">
						违纪时间				
					</th>
					<td align="left" width="30%">
						${rs.wjsj}
					</td>
					<th width="20%">
						处理结果				
					</th>
					<td align="left" width="30%">
						${rs.cljgmc}
					</td>
			</tr>
			<logic:equal name="xxdm" value="70002">
				<tr>
					<th align="right" width="20%">
								附件信息
						</th>
							<td colspan="3">
								<div id="commonfileupload-list-0" style="padding: 5px;"></div>
								<script type="text/javascript">
									//调用附件 
									jQuery(function(){
										var gid = "${rs.fileid}";
										jQuery.MultiUploader_q({
											gid : gid,
											targetEl : 'commonfileupload-list-0'
										});
									});
								</script>
							</td>
				</tr>
			</logic:equal>
			<tr></tr>
			<tr>
					<th>
						备注
					</th>
					<td colspan="3" style="word-wrap: break-word!important; word-break: break-all!important" >
						${rs.bz }
					</td>
			</tr>
			</tbody>
			</table>
			
			<table width="80%" border="0" class="formlist">
			<thead>
				<tr>
					<th colspan="4">
						<span>历史违纪记录</span>
					</th>
				</tr>
			</thead>
			
				<tr>
				<td colspan="4">
					<div style="height:240px;overflow-x:hidden;overflow-y:auto;">
					<table class="formList" width="100%">
						<thead>
							<tr align="left">
								<td align="center">违纪学年</td>
								<td align="center">违纪学期</td>
								<td align="center">
									<logic:equal value="11799" name="xxdm">
										奖惩类别
									</logic:equal>
									<logic:notEqual value="11799" name="xxdm">
										违纪类别
									</logic:notEqual>										
								</td>
								<td align="center">违纪时间</td>
								<td align="center">处理结果</td>
							</tr>
						</thead>
			<logic:empty name="rsArrList">
				<tr><td align="left" colspan="5">该学生无历史违纪记录！</td></tr>
			</logic:empty>
			<logic:notEmpty name="rsArrList">
				<logic:iterate name="rsArrList" id="s">
					<tr>
						<!-- 显示信息 -->
						<logic:iterate id="v" name="s" offset="0" length="5">
							<td align="center">
								${v }
							</td>
						</logic:iterate>
					</tr>
				</logic:iterate>
			</logic:notEmpty>
		</table>
		</div>
		</td>
		</tr>
		</table>
		</div>
		<table width="95%" border="0" class="formlist">	
			<tfoot>
					<tr>
						<td colspan="4">
							<div class="btn">
								<button type="button" name="关闭" id="buttonClose" onclick="Close();return false;">
									关 闭
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
		</table>
</body>
</html>
