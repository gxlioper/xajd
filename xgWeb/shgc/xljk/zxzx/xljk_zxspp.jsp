<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/jsFunction.js"></script>
		<script language="javascript" src="js/function.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/xzsxxPp.js'></script>
		<script language="javascript" src="js/shgz/xljk/zxzx/xljk_zxspp.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>	
		<script type="text/javascript">
		</script>
	</head>
		
		
		<body onload="">
		<html:form action="xljk_zxsxx_pp" method="post">
			<input type="hidden" name="zxsPkValue" id="zxsPkValue" value="${zxsPkValue}"/>
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>心理健康 - 心理咨询 - 咨询师匹配</a>
				</p>
			</div>
			<input type="hidden" id="userType" name="userType"
				value="${userType}" />	
			<div class="tab">
			  <table width="100%"  border="0" class="formlist">
			    <thead>
			    	<tr>
			        	<th colspan="4"><span>咨询师匹配</span></th>
			        </tr>
			    </thead>
			    <tfoot>
			    	<tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
			          <div class="btn">
			          		<button name="提交" onclick="saveZxsPp()">保存</button>
			          
			          </div></td>
			      </tr>
			    	
			    </tfoot>
					<tbody>
						
						<tr>
							<th align="center">
								辅导员
							</th>
							<th align="center">
								咨询师
							</th>
							<th align="center">
								已匹配咨询师
							</th>
						</tr>
						<tr>
							<td align="left">
								职工号：
								<html:text property="zgh" styleId="zgh" style="width:105px"></html:text>
								<br>
								姓&nbsp;&nbsp;&nbsp;名：
								<html:text property="xm" styleId="xm" style="width:105px"></html:text>
								<br>
								部门列表：
								<html:select property="bmdm" styleId="bmdm" style="width:240px"
									onchange="">
									<html:option value=""></html:option>
									<html:options collection="bmList" property="bmdm"
										labelProperty="bmmc" />
								</html:select>
								<div class="btn">
								<button name="查询" onclick="fdyxxCx()">查询</button>
								</div>
							</td>
							<td align="left">
								咨询师编号：
								<html:text property="zxxbh" styleId="zxxbh" style="width:105px"></html:text>
								<br>
								咨询师姓名：
								<html:text property="zxxxm" styleId="zxxxm" style="width:105px"></html:text>
								<br>
								<div class="btn">
								<button name="查询" onclick="zxsxxCx()"  />查询</button>
								</div>
							</td>
							<td align="left" rowspan="3">
								<html:select property="ppzxs"  styleId="ppzxs"  size="20"   style="width:240px">
										<html:options collection="ppzxsList" property="dm"
										labelProperty="mc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<td>
							<html:select property="fdyxx" styleId="fdyxx" size="15" style="width:240px">
										<html:options collection="fdyList" property="fdy"
										labelProperty="fdy" />	
							</html:select>
							</td>
							<td>
							<html:select property="zxsxx" styleId="zxsxx" size="15"  style="width:240px">
										<html:options collection="zxsList" property="zxs"
										labelProperty="zxs" />	
									</html:select>
							</td>
						</tr>
						<tr>
							<td colspan="2" >
							<div class="btn">
								<button onclick="zxsFdyPp()"  />+</button>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<button  onclick="deleteZxsFdyPp()"  />-</button>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							</div>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<logic:equal name="result" value="true">
				<script>
						alert('操作成功！');
						if(window.dialogArguments){
			 				window.dialogArguments.document.getElementById('search_go').onclick();
			 			}
						Close();
					</script>		
			</logic:equal>
			<logic:equal name="result" value="false">
				<script>
						alert('操作失败！');
					</script>
			</logic:equal>
		</html:form>
		
	</body>
</html>
