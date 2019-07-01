<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- 头文件 -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/xsxx/xsxxzgkdFunction.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/xsxxZgkd.js'></script>
</head>
<body onload="changeList()">		
	<html:form action="/xsxx_zgkd.do" method="post">
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>${title}</a>
			</p>
		</div>
		<div class="tab">
		  <table width="100%" border="0" class="formlist">
		    <thead>
		      <tr>
		      	<th colspan="4"><span>字段分配</span></th>
			  </tr>
		      <tr>		
			  	<th colspan="3">
					用户
					<html:select property="yhjs" style="width:80px" onchange="changeList();" styleId="yhjs">
					<html:option value=""></html:option>
					<html:options collection="yhList" property="en" labelProperty="cn"/>
					</html:select>	
				</th>
			  </tr>
			  <tr>					
				<th>学生信息字段</th>
				<th>
				</th>
				<th>用户可维护的字段</th>
			  </tr>
			</thead>
			<tbody>
				<tr>
					<td>
						<html:select property="xsxxzd" style="width:300px;" size="10"
								styleId="xsxxzd" ondblclick="addOneItemList()">
								<html:options collection="xsxxzdList" property="en" labelProperty="cn" />								
						</html:select>
					</td>
					<td>
						<button type="button" name="button1"
								onclick="defaultAllItemList()"
								style="width:70px">
								 >> 
						</button>
						<br/><br/><br/>
						<button type="button" name="button1"
								onclick="addOneItemList()"
								style="width:70px">
								 >  
						</button>
						<br/><br/><br/>
						<button type="button" name="button1"
								onclick="deleteItemList()"
								style="width:70px">
								 <  
						</button>
						<br/><br/><br/>
						<button type="button" name="button1"
								onclick="clearList()"
								style="width:70px">
								 << 
						</button>
					</td>
					<td>
						<html:select property="xsxxzdV" style="width:300px;" size="10"
								styleId="mappingList" ondblclick="deleteItemList()">		
						</html:select>
					</td>
					</tr>
				</tbody>
			</table>
			<table width="100%" class="formlist" id="rsTable">
				<thead>
					<tr>					
						<th>学生家庭信息字段</th>
						<th>
						
						</th>
						<th>
						
						</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>
							<html:select property="jtxxzd" style="width:300px;" size="10"
									styleId="jtxxzd" ondblclick="addOneJtxxList()">
									<html:options collection="jtxxzdList" property="en" labelProperty="cn" />								
							</html:select>
						</td>
						<td>
							<button type="button" name="button1"
								onclick="defaultAllJtxxList()"
								style="width:70px">
								 >> 
							</button>
							<br/><br/><br/>
							<button type="button" name="button1"
								onclick="addOneJtxxList()"
								style="width:70px">
								 >  
							</button>
							<br/><br/><br/>
							<button type="button" name="button1"
								onclick="deleteJtxxList()"
								style="width:70px">
								 <   
							</button>
							<br/><br/><br/>
							<button type="button" name="button1"
								onclick="clearJtxxList()"
								style="width:70px">
								 <<    
							</button>
						</td>
						<td>
							<html:select property="jtxxzdV" style="width:300px;" size="10"
									styleId="jtxxMappingList" ondblclick="deleteJtxxList()">		
							</html:select>
						</td>
					</tr>
				</tbody>
				<tfoot>
			      <tr>
			        <td colspan="4">
			          <div class="btn">
						<logic:equal value="yes" name="writeAble">
			            <button type="button" class="button2" id="btn_add"
								onclick="zdwhCommit()"
								style="width:80px">
								保 存
						</button>
						</logic:equal>
			          </div>
			        </td>
			      </tr>
			    </tfoot>
			</table>
		<logic:present name="result">
			<script>
					alert('操作成功!');
			</script>
		</logic:present>
	</html:form>
</body>
</html>
