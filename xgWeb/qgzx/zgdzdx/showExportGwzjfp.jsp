<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- ͷ�ļ� -->
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
					<em>���ĵ�ǰλ��:</em><a>�ڹ���ѧ - ��λֱ�ӷ��� - ���ݵ���</a>
				</p>
			</div>
			<div class="tab">
			<fieldset>
				<legend>
					ѡ�񵼳��ֶ�	
				</legend>				
					<table width="100%" class="formlist" id="rsTable">
					<thead>
					<tr>					
						<th>���ݿ���ֶ�</th>
						<th>
						
						</th>
						<th>�赼�����ֶ�</th>
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
							�������
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
				alert('�����ɹ�!');
				document.getElementById('search_go').click();
			</script>
		</logic:present>
		</html:form>
	</body>
</html>
