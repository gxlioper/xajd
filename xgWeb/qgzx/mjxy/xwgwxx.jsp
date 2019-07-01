<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- 头文件 -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script type="text/javascript" src="js/xgutil.js"></script>
</head>
<body>
	<html:form action="/gwfb.do?method=xwgwfb" method="post">
		<input type="hidden" id="path" name="path" value="${path}"/>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>${title}</a>
			</p>
		</div>
		<div class="tab">
		  <table width="100%" class="formlist" id="rsT">
				<thead>
					<tr>
						<th colspan="4">
							<span>岗位信息</span>
						</th>
					</tr>
				</thead>
				<tbody>
				<tr>
					<th>招聘公司</th>
					<td>
						${rs.yrdwmc}
					</td>
					<th>岗位名称</th>
					<td>
						${rs.gwdm}
					</td>
				</tr>
				<tr>
					<th>学年</th>
					<td>
						${rs.xn}
					</td>
					<th>年度</th>
					<td>
						${rs.nd}
					</td>
				</tr>
				<tr>
					<th>工作开始日期</th>
					<td>
						${rs.gzksrq}
					</td>
					<th>工作结束日期</th>
					<td>
						${rs.gzjsrq}
					</td>
				</tr>
				<tr>
					<th>招聘人数</th>
					<td height="22" align="left">
						${rs.xyrs}
					</td>
					<th>联系电话</th>
					<td>
						${rs.lxdh}
					</td>	
				</tr>
				<tr>
					<th>计酬方式</th>
					<td>
						${rs.jcfsmc}
					</td>
					<th>报酬标准</th>
					<td>
						${rs.jybcbz}元(${rs.jcfsmc})
					</td>
				</tr>
				<tr>
					<th>面试时间</th>
					<td>
						${rs.mssj}
					</td>
					<th>面试地点</th>
					<td>
						${rs.msdd}
					</td>
				</tr>
				<tr>
					<th>工作时间</th>
					<td>
						${rs.gzsj}
					</td>							
					<th>工作地点</th>
					<td>
						${rs.gzdd}
					</td>						
				</tr>
				<tr>
					<th>工作内容</th>
					<td colspan="3">
						${rs.gznr}
					</td>
				</tr>		
				<tr>
					<th>招聘要求</th>
					<td colspan="3">
						${rs.gwtsyq}
					</td>
				</tr>
								
				<tr>
					<th>备注</th>
					<td colspan="3">
						${rs.bz}
					</td>
				</tr>				
				</tbody>
				<!--显示参加岗位的学生列表-->
				<%@ include file="/qgzx/gwxsxx.jsp"%>
				<tfoot>
			      <tr>
			        <td colspan="4">
			          <div class="btn">
							<button type="button"  id="buttonClose" onclick="Close();return false;">
								关闭
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
