<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- 头文件 -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<body onload="checkWinType();">
		<script language="javascript" src="js/stuinfoFunction.js"></script>
		<html:form action="/data_search" method="post">
		<input type="hidden" name="realTable" id="realTable" value="stu_archives_auditing"/>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>历届学生档案 - 转档申请 - 单个审核</a>
			</p>
		</div>
		<div class="tab">
		  <table width="100%" border="0" class="formlist">
		    <thead>
		    	<tr>
		        	<th colspan="4"><span>单个审核</span></th>
		        </tr>
		    </thead>
		    <tbody>
		      <tr>
					<th>学号</th>
					<td>
						${rs.xh}
						<input type="hidden" value="${rs.xh}" name="xh" id="xh"/>
					</td>
					<th>年度</th>
					<td>
						${rs.nd}
						<input type="hidden" value="${rs.nd}" name="nd" id="nd"/>
					</td>
				</tr>
				<tr>
					<th>姓名</th>
					<td>
						${rs.xm}
					</td>
					<th>户口所属区县</th>
					<td>
						${rs.hkssqx}
					</td>
				</tr>				
				<tr>
					<th>年级</th>
					<td>
						${rs.nj}
					</td>
					<th>户口所属街道</th>
					<td>
						${rs.hkssjd}
					</td>
				</tr>
				<tr>
					<th><bean:message key="lable.xsgzyxpzxy" /></th>
					<td>
						${rs.xymc}
					</td>
					<th>户口详细地址</th>
					<td>
						${rs.hkxxdz}
					</td>
				</tr>
				<tr>
					<th>专业</th>
					<td>
						${rs.zymc}
					</td>
					<th>转档单位名称</th>
					<td>
						${rs.zddwmc}
					</td>
					
				</tr>
				<tr>
					<th>班级</th>
					<td>
						${rs.bjmc}
					</td>
					<th>转档单位地址</th>
					<td>
						${rs.zddwdz}
					</td>	
				</tr>
				<tr>
					<th>申请日期</th>
					<td>
						${rs.sqrq}
					</td>
					<th>申请理由</th>
					<td>
						${rs.sqly}
					</td>	
				</tr>
				<tr>
					<th>审核</th>
					<td colspan="3">
					<html:select property="yesNo" name="rs">
							<html:option value="未审核">未审核</html:option>
							<html:option value="通过">通过</html:option>
							<html:option value="不通过">不通过</html:option>
						</html:select>
					</td>
				</tr>	
				<tr>					
					<th>备注</th>
					<td colspan="3">
						<html:textarea property="bz" name="rs" cols="66" style="width:680px" rows="4"></html:textarea>
					</td>
				</tr>
				</tbody>
				<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz"></div>
			          <div class="btn">
						<button class="btn_bc"
							onclick="viewAdd('/xgxt/abroad_query.do?act=txsq_sh&doType=save','add');"
							style="width:80px" id="buttonSave">
							保 存
						</button>
						<button class="btn_gb" onclick="Close();return false;" style="width:80px"
							id="buttonClose">
							关 闭
						</button>
			          </div>
			        </td>
			      </tr>
			    </tfoot>
			</table>
		</html:form>
		<logic:equal value="true" name="result">
			<script>
				alert("操作成功");
				Close();
				if(window.dialogArguments){
					window.dialogArguments.document.getElementById('search_go').click();
				}
			</script>
		</logic:equal>
	</body>
</html>
