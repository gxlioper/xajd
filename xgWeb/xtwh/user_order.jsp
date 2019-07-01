<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/jsFunction.js"></script>
	<script language="javascript" src='/xgxt/dwr/interface/getTowdays.js'></script>
	<script language="javascript" src='/xgxt/dwr/interface/getScoreinfo.js'></script>
	<script language="javascript">
		function viewyhz(){
			var pxlx = $('pxlx').value;
			if(pxlx == 'yh'){
				$('pxlx').style.display='block';
			}else{
				$('pxlx').style.display='none';
			}
		}
		function checknum(obj){
			obj.value = obj.value.replace(/[^(\d)]/g,'');
			var inputStr = obj.value;
			if(!inputStr.match(/\d/g)){
				if(inputStr == null ){
					obj.value = '';
				}else{
					alert("请输入数字类型的数据！");
					obj.value='';
					obj.focus();
					return false;
				}
			}
		}
	</script> 
	</head>
	<body>
		<html:form action="/user_order" method="post">
			<div class="tab_cur">
				<p class="location">
						<em>您的当前位置:</em><a>系统维护 - 权限维护 - 用户排序</a>
				</p>
			</div>
			<div class="toolbox">
			  <!-- 按钮 -->
			  <div class="buttonbox">
			    <ul>
			    	<logic:equal value="yh" name="pxlx">
			      <li> <a href="#" onclick="refreshForm('/xgxt/user_order.do?doType=save')" class="btn_ccg"> 保存 </a> </li>
			      </logic:equal>
			      <logic:equal value="yhz" name="pxlx">
			      <li> <a href="#" onclick="refreshForm('/xgxt/user_order.do?doType=save')" class="btn_ccg"> 保存 </a> </li>
			      </logic:equal>
			    </ul>
			  </div>
			  <div class="searchtab">
		    <table width="100%" border="0">
			<tbody>
		      	<tr>
		      		<th width="100px">排序类别</th>
		      		<td>
		      			<html:select property="pxlx" onchange="refreshForm('/xgxt/user_order.do')" styleId="pxlx">
							<html:option value=""></html:option>
							<html:option value="yhz">用户组</html:option>
							<html:option value="yh">用户</html:option>
						</html:select>
		      		</td>
		      		<logic:equal value="yh" name="pxlx">
		      			<th width="100px">用户组</th>
		      			<td>
		      				<html:select property="zdm" onchange="refreshForm('/xgxt/user_order.do')" styleId="zdm">
								<html:option value=""></html:option>
								<html:options collection="yhzlist" property="zdm" labelProperty="zmc"/>
							</html:select>
		      			</td>
					</logic:equal>
		        </tr>
			  </tbody>
			</table>
		  </div>
		  </div>
			<div class="formbox">
			    <h3 class="datetitle_01">
				    <span>
				    	查询结果&nbsp;&nbsp;
				    	<logic:empty name="rs">
							<font color="red">未找到任何记录！</font> 
				 		 </logic:empty>
				    </span>
			    </h3>
			  <logic:notEmpty name="rs">
			  <logic:equal value="yhz" name="pxlx">
			  <table summary="" class="dateline" align="" width="100%">
			    <thead>
					<tr align="center" style="cursor:hand">
						<td> 
							组名称
						</td>
						<td>
							排列顺序
						</td>
					</tr>
				</thead>
			    <tbody>
			      <logic:iterate name="rs" id="s">
						<tr onclick="rowOnClick(this)" style="cursor:hand"　align="center">
							<td>
								<bean:write name="s" property="zmc"/>
							</td>
							<td>
								<input type="text" name="xssx" value="<bean:write name="s" property="xssx"/>" onchange="checknum(this);"/>
								<input type="hidden" name="id" value="<bean:write name="s" property="zdm"/>"/>
							</td>
						</tr>
				 </logic:iterate>
			    </tbody>
			  </table>
			  </logic:equal>
			  <logic:equal value="yh" name="pxlx">
			  <table summary="" class="dateline" align="" width="100%">
			    <thead>
					<tr align="center" style="cursor:hand">
						<td>
							姓名
						</td>
						<td>
							排列顺序
						</td>
					</tr>
				</thead>
			    <tbody>
			      <logic:iterate name="rs" id="s">
						<tr onclick="rowOnClick(this)" style="cursor:hand"　align="center">
							<td>
								<bean:write name="s" property="xm"/>
							</td>
							<td>
									<input type="text" name="xssx" value="<bean:write name="s" property="xssx"/>" onchange="checknum(this);"/>
									<input type="hidden" name="id" value="<bean:write name="s" property="yhm"/>"/>
							</td>
						</tr>
				 </logic:iterate>
			    </tbody>
			  </table>
			  </logic:equal>
			  
			</logic:notEmpty>
			<logic:equal value="ok" name="result">
				<script language="javascript">
					alert('保存成功');
				</script>				
			</logic:equal>
			<logic:equal value="no" name="result">
				<script language="javascript">
					alert('保存失败');
				</script>
			</logic:equal>
		</html:form>
	</body>
</html>
