<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/syscommon/pagehead_V4.ini"%>
<!-- 头文件 -->
</head>

<body onload="checkWinType();">
	<html:form action="/data_search" method="post">
		<input type="hidden" id="pk" name="pk" value="${pk}"/>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>学生信息 - 个人信息 - 信息维护<</a>
			</p>
		</div>
				
		<div class="tab">
		  <table width="100%" border="0" class="formlist"id="rsT">
			<tbody>
			<tr>
				<td height="27" align="center">
					是否在分校：
				</td>
				<td align="center">
					<html:select property="sfzfx" styleId="sfzfx" style="width:120px">
						<html:option value="是">是</html:option>
						<html:option value="否">否</html:option>								
					</html:select>
				</td>
			</tr>
			</tbody>
			<tfoot>
		      <tr>
		        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
		          <div class="btn">
		            <button 
						onclick="refreshForm('stu_info_add.do?method=saveStuinfoBatch');BatAlert.showTips('正在执行操作，请等待...');"
						id="buttonSave">
						保 存
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;				
					<button onclick="Close();return false;">
						关 闭
					</button>
		          </div>
		        </td>
		      </tr>
		    </tfoot>									
			</table>
		</div>
		<logic:present name="result">
			<logic:equal value="true" name="result">
			<script>	
				alert("操作成功！");
				Close();
			</script>
			</logic:equal>
			<logic:equal value="false" name="result">
			<script>	
				alert("操作失败！");
			</script>
			</logic:equal>
		</logic:present>
	</html:form>
</body>
</html>

