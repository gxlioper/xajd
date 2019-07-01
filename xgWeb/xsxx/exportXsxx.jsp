<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/syscommon/pagehead_V4.ini"%>
<script language="javascript" src="js/stuinfoFunction.js"></script>
</head>
<!-- 头文件 -->
	<body>		
		<html:form action="/xsxxgl.do" method="post">
			<input type="hidden" id="userType" name="userType" value="${userType}"/>
			<input type="hidden" name="realTable" id="realTable" value="${realTable}"/>
			<input type="hidden" name="tableName" id="tableName" value="${tableName}"/>			
			<input type="hidden" name="mappingItem" value=""/>
			<input type="hidden" name="whereSql" id="whereSql" value="${whereSql}"/>
			<input type="hidden" name="xyV" value=""/>
			<input type="hidden" name="zyV" value=""/>
			<input type="hidden" name="bjV" value=""/>

			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>${title}-数据导出</a>
				</p>
			</div>
			<div class="tab">				
				<table width="100%" border="0" class="formlist" id="rsTable">
					<thead>
						<tr>
							<th><span>数据库表字段</span></th>					
							<th></th>
							<th><span>需导出的字段</span></th>
						</tr>
					</thead>
					<tbody>
					<tr>
						<td align="right">
							<html:select property="xydm" style="width:300px;" size="18"
									styleId="excelList" ondblclick="addOneItemList()">
									<html:options collection="rs" property="dm" labelProperty="mc" />								
							</html:select>
						</td>
						<td align="center">
							<div class="btn">
							<button type="button" name="button1"
									onclick="defaultAllItemList()"
									style="width:80px">
									 >> 
							</button>
							<br/><br/><br/>

							<button type="button" name="button2"
									onclick="addOneItemList()"
									style="width:80px">
									 > 
							</button>							
							<br/><br/><br/>

							<button type="button" name="button3"
									onclick="deleteItemList()"
									style="width:80px">
									 < 
							</button>
							<br/><br/><br/>

							<button type="button" name="button4"
									onclick="clearList()"
									style="width:80px">
									  << 
							</button>
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
									onclick="submitList()"
									style="width:80px">
									数据输出
							</button>
							<logic:equal value="no" name="sfzxk">
								<button type="button" id="btn_return"
									onclick="document.URL='stu_info_query.do?method=stuInfo&sfzxk=no'"
									style="width:80px">
									返回									
								</button>
							</logic:equal>
							<logic:notEqual value="no" name="sfzxk">
								<button type="button" id="btn_return"
									onclick="document.URL='stu_info_query.do?method=stuInfo'"
									style="width:80px">
									返回									
								</button>
							</logic:notEqual>
							
				          </div>
				        </td>
				      </tr>
				    </tfoot>
				</table>
		</html:form>
	</body>
</html>
