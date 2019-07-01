<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript">
    </script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/fdyglFunction.js"></script>
    <script type='text/javascript' src='/xgxt/dwr/interface/getSztzData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>	
</head>
	<body >
		<html:form action="/wjstxxwh_make" method="post">
		   <input type="hidden" name="xn" id="xn" value="<bean:write name="fdyglForm" property="xn" />"/>
		   <input type="hidden" name="xq" id="xq" value="<bean:write name="fdyglForm" property="xq" />"/>	
		   <div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>辅导员管理 - 信息维护 - 辅导员工作调查问卷维护 - 问题选项维护</a>
				</p>
			</div>
			<input type="hidden" id="id" name="id"
				value="<bean:write name="id" scope="request"/>" />				
			
			<div class="tab">
			 	<table width="100%"  border="0" class="formlist">
			    <thead>
			    	<tr>
			        	<th colspan="4"><span>问题选项维护</span></th>
			        </tr>
			    </thead>
			     <tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
			          <div class="btn"  id="btn">
			          <logic:notEqual name="doType" value="view">
			          		<button name="提交" onclick="refreshForm('/xgxt/wjstxxwh_make.do?doType=save');">保 存</button>
			          </logic:notEqual>
			            <button name="关闭"  onclick="Close();return false;">关闭</button>
			          </div></td>
			      </tr>
			    </tfoot>
			    <tbody>
					<tr>
						<th align="right" style="width:80px">
							<font color="red">*</font>选项列：
						</th>
						<logic:equal value="modi" name="doType">
							<td >
								第
								<html:text name="rs" property="xxl" styleId="xxl"
									style="width:30px"  readonly="true"/>
								选项
							</td>
						</logic:equal>
						<logic:notEqual value="modi" name="doType">
							<td>
								第
								<html:text name="rs" property="xxl" styleId="xxl"
									style="width:30px" onkeypress="return onlyNumWordInputValue(this,2)"
									 maxlength="2" onblur="checkXxL(this)"/>
								选项
							</td>
						</logic:notEqual>
					</tr>
					<tr>
						<th align="right" style="width:80px">
							<font color="red">*</font>选项内容：
						</th>
						<td>
							<html:text name="rs" property="xxnr" styleId="xxnr"
								style="width:350px" />
						</td>
					</tr>
					<tr>
						<th align="right" style="width:80px">
							<font color="red">*</font>选项分值：
						</th>
						<td>
							<html:text name="rs" property="xxfz" styleId="xxfz"
								style="width:350px" onkeyup="value=value.replace(/[^\d]/g,'')" />
						</td>
					</tr>		
					</tbody>					
				</table>
			</div>
		</html:form>
	</body>	
		<logic:equal value="yes" name="done">
	<script type="text/javascript" >
	    alert("操作成功！");
	    // window.dialogArguments.Close();
	  	window.dialogArguments.refreshForm("/xgxt/wjstxxwh.do?id=<bean:write name='id' scope='request'/>");
	    Close();
	</script> 
	</logic:equal>
		<logic:equal value="no" name="done">
	<script type="text/javascript" >	   
	    alert("操作失败！");
	    Close();
	    // dialogArgumentsQueryChick();	    	    	    
	</script> 
	</logic:equal>
</html>
