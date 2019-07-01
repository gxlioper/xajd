<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/wjcfFuction.js"></script>
	<script language="javascript" src="pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
	<body>	   
	    <html:form action="/wjcf_shscheck" method="post">
	    <input type="hidden" id="pkValue" name="pkValue"
				value="<bean:write name="pkValue" scope="request"/>"/>
		<input type="hidden" id="act" name="act"
				value="<bean:write name="act" scope="request"/>" />	
		<input type="hidden" id="xh" name="xh"
				value="<bean:write name="xh" scope="request"/>" />
		<input type="hidden" id="act" name="act"
				value="<bean:write name="act" scope="request"/>" />	
		<input type="hidden" id="realTable" name="realTable"
				value="<bean:write name="realTable" scope="request"/>" />									
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>
						<logic:equal value="11049" name="xxdm">
							违纪处理 - 申诉处理 - 委员会讨论
						</logic:equal>
						<logic:notEqual value="11049" name="xxdm">
							违纪处分 - 申诉申请审核 - 委员会讨论
						</logic:notEqual>
					</a>
				</p>
			</div>
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					有错误发生！
				</p>
			</logic:empty>	
			<logic:notEmpty name="rs">
			       <logic:notEmpty name="rswj"> 	
  					<table border="0" id="rsTable" width="100%" class="formlist"> 
  						<thead><tr><th colspan="5"><span>材 料 或 证 明 附 件 列 表</span></th></tr></thead>
    					<logic:iterate id="list" name="rswj"> 
    						<tr onmouseover="rowOnClick(this)"> 
      							<td> <a href="downloadfilewj.do?len=<bean:write name="list" property="len"/>&wjsclj=<bean:write name="list" property="wjsclj" />"  target="_blank">下载     							
      							</a> </td> 
      							<td > <bean:write name="list" property="cfwh" /> </td> 
      							<td > <bean:write name="list" property="cflbmc" /> </td> 
       							<td > <bean:write name="list" property="cfyymc" /> </td>
       							<td > <bean:write name="list" property="sssj" /> </td>
<%--       							<td>--%>
<%--        							<a href="#" onclick="if(confirm('确实要删除当前文件吗？'))location='fileDel.do?wjh=<bean:write name="list" property="wjh"/>';" >删除</a> </td>--%>
    						</tr> 
    					</logic:iterate> 
  					</table> 
  				</logic:notEmpty> 
  				<logic:empty name="rswj"> 
  	
  				<table border="0" id="rsTable" width="100%" class="formlist"> 
  						<thead><tr><th colspan="5"><span>材 料 或 证 明 附 件 列 表</span></th></tr></thead>
    					<tr><td colspan="5" align="center">暂无材料或证明附件</td></tr> 
  				</table>
  				</logic:empty> 
  				<div class="tab">
					<table width="100%" class="formlist">
						<thead>
							<tr>
								<th colspan="5">
									<span>单个申诉申请讨论</span>
								</th>
							</tr>
						</thead>
						<tbody>
						<tr>
							<th align="right" width="15%">
								学号
							</th>
							<td align="left" width="30%">
								<bean:write name="rs" property="xh" />
							</td>
							<th align="right" width="18%">
								处分文件号
							</th>
							<td align="left">
								<bean:write name="rs" property="cfwh" />
							</td>
							<td align="left" width="15%" rowspan="5">
								<img border="0" style="height:133px;width:100px;"
									src="/xgxt/pictures/<bean:write name="rs" property="xh" />.jpg">
							</td>
						</tr>
						<tr>
							<th align="right">
								姓名
							</th>
							<td align="left">
								<bean:write name="rs" property="xm" />
							</td>
							<th align="right">
								年度
							</th>
							<td align="left">
								<bean:write name="rs" property="nd" />
							</td>
						</tr>
						<tr>
							<th align="right">
								性别
							</th>
							<td align="left">
								<bean:write name="rs" property="xb" />
							</td>
							<th align="right">
								学年
							</th>
							<td align="left">
								<bean:write name="rs" property="xn" />
							</td>
						</tr>
						<tr>
							<th align="right">
								年级
							</th>
							<td align="left">
								<bean:write name="rs" property="nj" />
							</td>
							<th align="right">
								学期
							</th>
							<td align="left">
								<bean:write name="rs" property="xq" />
							</td>
						</tr>
						<tr>
							<td align="right">
								<bean:message key="lable.xsgzyxpzxy" />
							</td>
							<td align="left">
								<bean:write name="rs" property="xymc" />
							</td>
							<th align="right">
								处分时间
							</th>
							<td align="left">
								<bean:write name="rs" property="cfsj" />
							</td>
						</tr>
						<tr>
							<th align="right">
								专业
							</th>
							<td align="left">
								<bean:write name="rs" property="zymc" />
							</td>
							<th align="right">
								处分类别
							</th>
							<td align="left" colspan="2">
								<bean:write name="rs" property="cflbmc" />
							</td>
						</tr>
						<tr>
							<th align="right">
								班级
							</th>
							<td align="left">
								<bean:write name="rs" property="bjmc" />
							</td>
							<th align="right">
								处分事由
							</th>
							<td align="left" colspan="2">
								<bean:write name="rs" property="cfyymc" />
							</td>
						</tr>
						<tr>
							<th align="right">
								联系地址
							</th>
							<td align="left">
								<bean:write name="rs" property="dz" />
							</td>
							<th align="right">
								<logic:present name="isZJJDZYJSXY">
					  			  	申诉/解除申请时间
					    		</logic:present>
								<logic:notPresent name="isZJJDZYJSXY">
					      	 		申诉时间
					     		</logic:notPresent>
							</th>
							<td align="left" colspan="2">
								<bean:write name="rs" property="sssj" />
							</td>
						</tr>
						<tr>
							<th align="right">
								邮政编码
							</th>
							<td align="left">
								<bean:write name="rs" property="yb" />
							</td>
							<th align="right">
								委员会讨论
							</th>
							<td align="left" colspan="2">
								<html:select name="rs" property="sh" style="width:100px"
									styleId="sh">
									<html:options collection="shList" property="sh"
										labelProperty="sh" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th align="right">
								联系电话
							</th>
							<td align="left">
								<bean:write name="rs" property="lxdh" />
							</td>
							<td align="right">

							</td>
							<td align="left" colspan="2">

							</td>
						</tr>
						<logic:present name="isZJJDZYJSXY">
							<tr>
								<th align="right">
									申请处分改为
								</th>
								<td align="left" colspan="4">
									<bean:write name="rs" property="cfxg" />
								</td>
							</tr>
						</logic:present>
						<tr>
							<th align="right">
								<logic:present name="isZJJDZYJSXY">
					  				  申诉/解除<br/>申请理由<br/>
								</logic:present>
								<logic:notPresent name="isZJJDZYJSXY">
					   				改变<bean:message key="lable.xsgzyxpzxy" />处分要求
								</logic:notPresent>
							</th>
							<td align="left" colspan="4">
								<bean:write name="rs" property="yq" />
							</td>
						</tr>
						<tr>
							<th align="right">
								讨论结果、
								<br/>
								内容或理由
								<br/>
								<font color="red">(限制在500字以内)</font>
							</th>
							<td align="left" colspan="4">
								<html:textarea name="rs" property="tlly" rows="7"
									style="width:550px" styleId="tlly" onkeyup="checkLen(this,500)">
								</html:textarea>
							</td>
						</tr>
						</tbody>
						<tfoot>
					      <tr>
					        <td colspan="5"><div class="bz">"<span class="red">*</span>"为必填项</div>
					          <div class="btn">
								 <button type="button" onclick="refreshForm('/xgxt/wjcf_shscheck.do?doType=save');"
									id="buttonSave">
									保存
								</button>
								  <button type="button" name="关闭" onclick="window.close();return false;" id="buttonClose">关闭</button>
					          </div></td>
					      </tr>
					    </tfoot>
					</table>
				</div>
			</logic:notEmpty>
		  <logic:equal value="yes" name="done">
			<script>
				alert("操作成功!");
				Close();
				if(window.dialogArguments && window.dialogArguments.document.all("search_go")){
				    window.dialogArguments.document.getElementById('search_go').click(); 
				   }
			</script>
		   </logic:equal>
		   <logic:equal value="no" name="done">
			<script>
				alert("操作失败!");
				Close();
				if(window.dialogArguments && window.dialogArguments.document.all("search_go")){
				    window.dialogArguments.document.getElementById('search_go').click(); 
				   }
			</script>
		   </logic:equal>
	  </html:form>	
	</body>
</html>
