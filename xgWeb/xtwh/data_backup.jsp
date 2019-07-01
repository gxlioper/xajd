<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>系统维护 - 数据备份 - 系统备份</a>
			</p>
		</div>
	
	
		<html:form action="/log_search" method="post">
		
		<div class="formbox">
				<h3 class="datetitle_01">
					<span> 查询结果&nbsp;&nbsp; <logic:empty name="rs">
							<font color="red">未找到任何记录！</font>
						</logic:empty> </span>
				</h3>

				<logic:notEmpty name="rs">
					<logic:iterate name="rs" id="s">
						<table summary="" class="dateline" width="100%">
							<tbody>
								<tr onclick="rowOnClick(this)">
									<th>备份时间</th>
									<td>
										<bean:write name="s" property="BFSJ" />
										<input type="hidden" name="bfsj" id="bfsj" value="<bean:write name="s" property="BFSJ" />"/>
									</td>
									<th>备份文件名</th>
									<td><bean:write name="s" property="BFWJM" /></td>
									<th>备份人</th>
									<td>
										<bean:write name="s" property="BFR" />
										<input type="hidden" name="bfr" id="bfr" value="<bean:write name="s" property="BFR" />"/>
									</td>
									<td>
										<button type="button" onclick="refreshForm('/xgxt/data_backup.do?act=del&pkValue='+'<bean:write name='s' property='BFR' />'+'<bean:write name='s' property='BFSJ' />')">
											删 除
										</button>
									</td>
								</tr>
								<tr>	
									<th>备份说明</th>
									<td colspan="6">
										<bean:write name="s" property="BFSM" />
									</td>
								</tr>
							</tbody>
						</table>
					</logic:iterate>
				</logic:notEmpty>
			</div>
			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>数据备份</span>
							</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button type="button" name="保  存" onclick="if(confirm('确定要备份吗？')){refreshForm('/xgxt/data_backup.do?act=save')}return false;">
										保  存
									</button>
									<button type="button" name="备份设置" onclick="showTopWin('/xgxt/bak_set.do',400,310);">
										备份设置
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<tr>
							<th width="20%">新备份－备份说明</th>
							<td>
								<textarea name="newBakInfo" style="width:100%" rows="3"
								onfocus="if(this.value=='定时备份')this.value=''"
								onblur="if(this.value=='')this.value='定时备份'" type="_moz">定时备份</textarea>
							</td>
						</tr>
					</tbody>
				</table>
				</div>
		</html:form>
	</body>
</html>
