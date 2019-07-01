<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- 头文件 -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/stuinfoFunction.js"></script>
		<script language="javascript" src="js/qgzxFunction.js"></script>
		<html:form action="/xsxxgl.do" method="post">			
			<input type="hidden" id="userType" name="userType" value="${userType}"/>
			<input type="hidden" name="realTable" id="realTable" value="${realTable}"/>
			<input type="hidden" name="tableName" id="tableName" value="${tableName}"/>
			<input type="hidden" name="xyV" value=""/>
			<input type="hidden" name="zyV" value=""/>
			<input type="hidden" name="bjV" value=""/>
			<input type="hidden" name="mappingItems" value=""/>
			<input type="hidden" name="whereSql" id="whereSql" value="${whereSql}"/>
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>勤工助学 - 岗位直接分配 - 数据导出</a>
				</p>
			</div>
			<div class="tab">
			<fieldset>
				<legend>
					选择导出字段	
				</legend>				
					<table width="100%" class="formlist" id="rsTable">
					<thead>
					<tr>					
						<th>数据库表字段</th>
						<th>
						
						</th>
						<th>需导出的字段</th>
					</tr>
					</thead>
					<tbody>
					<tr><td align="right">
						<html:select property="xydm" style="width:300px;" size="18"
								styleId="excelList" ondblclick="addOneItemList()">
								<html:options collection="rs" property="en" labelProperty="cn" />								
						</html:select>
						</td>
						<td align="center">
						<div class="btn">
						<button type="button" onclick="defaultAllItemList()" style="width:60px;"> >> </button>
								<br/><br/><br/>
						<button type="button" onclick="addOneItemList()" style="width:60px;"> >  </button>
								<br/><br/><br/>
						<button type="button" onclick="deleteItemList()" style="width:60px;"> <  </button>
							<br/><br/><br/>
						<button type="button" onclick="clearList()" style="width:60px;"> <<  </button>
						</div>
						</td>
						<td>
						<html:select property="keyval" style="width:300px;" size="18"
								styleId="mappingList" ondblclick="deleteItemList()">		
						</html:select>
						</td>
						</tr>
					</tbody>
					
					<tfoot>
				      <tr>
				        <td colspan="4">
				          <div class="btn">
				            <button type="button" id="btn_add"
							onclick="submitList()">
							数据输出
							</button>
				          </div>
				        </td>
				      </tr>
				    </tfoot>
				</table>				
			</fieldset>
			</div>
		<logic:present name="result">
			<script>
				alert('操作成功!');
				document.getElementById('search_go').click();
			</script>
		</logic:present>
		</html:form>
	</body>
</html>
