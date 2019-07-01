<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- 头文件 -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script type="text/javascript" src="js/String.js"></script>
	<script language="javascript" src="js/qgzxFunction.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getOtherData.js'></script>
	<script language="javascript">	
	function saveGwxxInfo(url,obj){
		var value = obj.split("-"); 
	    for(var i=0; i<value.length; i++){
		 	if(document.getElementById(value[i]).value==""){
		 		alert("请将带\*号的项目填写完整！");
		 		return false;
		 	}
	 	}	 
		refreshForm(url);
		BatAlert.showTips('正在操作中，请稍等...'); 
	}	
	</script>
</head>
	<body>		
		<html:form action="/whlggwgl" method="post">
		<html:hidden name="rs" property="sqsj" styleId="sqsj"/>			
		<input type="hidden" name="doType" id="doType" value="${doType}"/>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>${title }</a>
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
			<div class="tab">	
			<table width="100%" id="rsT" class="formlist" border="1">
				<thead>
					<tr>
						<th colspan="4">
							<span>岗位信息</span>
						</th>
					</tr>
				</thead>
				<tbody>
				<tr>
					<th><span class="red">*</span>岗位名称</th>
					<td>
						<logic:equal value="add" name="doType">
							<html:text name="rs" property="gwmc" styleId="gwmc" onkeyup="value=value.replace('-','') "/>
						</logic:equal>
						<logic:equal value="modi" name="doType">
							<html:text name="rs" property="gwmc" styleId="gwmc" readonly="true"/>
						</logic:equal>							
					</td>
					<th><span class="red">*</span>单位名称</th>
					<td>
						<html:text name="rs" property="sqdwmc" styleId="sqdwmc"/>							
					</td>
				</tr>
				<tr>
					<th>单位地址</th>
					<td colspan="3">
						<html:text name="rs" property="sqdwdz" style="width:100%" />
					</td>
				</tr>
				<tr>
					<th>学年</th>
					<td>
						<html:text name="rs" property="xn" style="width: 90px" readonly="true" />
					</td>
					<th>年度</th>
					<td>
						<html:text name="rs" property="nd" style="width: 90px"
							readonly="true" />							
					</td>
				</tr>
				<tr>
					<th>学期</th>
					<td>
						<html:text name="rs" property="xq" style="width: 90px" readonly="true" />
					</td>
					<th>联系电话</th>
					<td>
						<html:text name="rs" property="lxdh"/>
					</td>
				</tr>
				<tr>
					<th>工作开始日期</th>
					<td>
						<html:text name='rs' property="gzkssj" styleId="gzkssj"
							onblur="dateFormatChg(this)" style="cursor:hand;"
							onclick="return showCalendar('gzkssj','y-mm-dd');" readonly="true"/>
					</td>
					<th><span class="red">*</span>工作结束日期</th>
					<td>
						<html:text name='rs' property="gzjssj" styleId="gzjssj"
							onblur="dateFormatChg(this)" style="cursor:hand;"
							onclick="return showCalendar('gzjssj','y-mm-dd');" readonly="true"/>
					</td>
				</tr>
				<tr>
					<th><span class="red">*</span>需求人数</th>
					<td>
						<html:text name="rs" property="xyrs" styleId="xyrs" onkeyup="value=value.replace(/[^\d]/g,'') "/>
					</td>
					<th><span class="red">*</span>使用困难生数</th>
					<td>
						<html:text name="rs" property="xyknsrs" styleId="xyknsrs" onkeyup="value=value.replace(/[^\d]/g,'') "/>
					</td>
				</tr>
				<tr>
					<th><span class="red">*</span>计酬方式</th>
					<td>
						<html:select name="rs" property="jcfs" onchange="subloadPost()" styleId="jcfs">
							<html:option value="">------请选择------</html:option>
							<html:option value="h">按小时</html:option>
							<html:option value="d">按天</html:option>
							<html:option value="w">按周</html:option>
							<html:option value="m">按月</html:option>
						</html:select>
					</td>
					<th><span class="red">*</span>建议报酬标准</th>
					<td>
						<html:text name="rs" property="jcbz" styleId="jybcbz" onkeyup="value=value.replace(/[^\d]/g,'') "/>
						<span id="jybcbzDw"></span>
					</td>
				</tr>			
				<tr>
				  <th>工作时间</th>
				  <td colspan="3">
				  	<html:textarea name="rs" property="gzsj" style="width:100%" styleId="gzsj"/>
				  </td>
			  </tr>
				<tr>
				  <th>工作内容</th>
				  <td colspan="3">
				  	<html:textarea name="rs" property="gznr" style="width:100%" styleId="gznr"/>
				  </td>
			  </tr>		
			  <tr>
				  <th>工作要求</th>
				  <td colspan="3">
				  	<html:textarea name="rs" property="gzyq" style="width:100%" styleId="gzyq"/>
				  </td>
			  </tr>			
			  <tr>
					<th>备注</th>
					<td colspan="3">
						<html:textarea name="rs" property="bz" style="width:100%"
							rows="5"></html:textarea>
					</td>
			  </tr>
			  </tbody>
			  <tfoot>
		      <tr>
		        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
		          <div class="btn">
		            <button type="button" class="button2"
						onclick="saveGwxxInfo('qgzxZgdzdx.do?method=saveGwxx','gwmc-sqdwmc-xyrs-xyknsrs-gzjssj-jcfs-jcbz')"
						style="width:80px" id="buttonSave">
						保 存
					</button>
					<button type="button" class="button2" onclick="expAppTab('rsT','勤工助学岗位发布信息','')">
						打印报表
					</button>
		          </div>
		        </td>
		      </tr>
		    </tfoot>
			</table>
		  </div>
		</logic:notEmpty>

		<logic:present name="result">
		<logic:equal value="true" name="result">
			<script>
				alert("操作成功！");			
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
