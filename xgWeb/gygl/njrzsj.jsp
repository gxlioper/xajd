<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/gyglShareData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>		
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script language="javascript" src="js/gygl/gyglFunction.js"></script>
		<script language="javascript" src="js/AjaxFunction.js"></script>		
			<script type="text/javascript">
	      function save(){
	      	var nj = document.getElementById('nj').value;
	      	if(nj==''){
	      		alert('年级不能为空！');
	      		return false;
	      	}
	      	refreshForm('/xgxt/njrzsj.do?method=setNjrzsj&doType=save');
	      }
	      

	</script>
	</head>
	<body>
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>公寓管理 - 信息维护 - 年级入住时间</a>
			</p>
		</div>
		<!-- 标题 end-->
		
		<html:form action="/njrzsj" method="post">
			<input type="hidden" name="zyV" id="zyV" />
			<input type="hidden" name="bjV" id="bjV" />		
			
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
							<li>
								<a href="#"
									onclick="save();return false;"
									class="btn_ccg">保存</a>
							</li>
							<li>
								<a href="#"
									onclick="document.getElementById('rzsj').value='';"
									class="btn_sx">重新录入日期</a>
							</li>	
					</ul>
				</div>
				<!-- 按钮 end-->	
				<!-- 过滤条件 -->
				<div class="searchtab">
					<table width="100%" border="0">
						<tbody>
							<!-- 第一行 -->
							<tr>
								<th>
									年级								
								</th>
								<td>
									<html:select property="nj" style="width:90px"
										 styleId="nj" onchange="refreshForm('/xgxt/njrzsj.do?method=setNjrzsj')">										
										<html:option value="">--请选择--</html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
								</html:select>												
								</td>
								<th>
									入住日期
								</th>
								<td>
									<input name="rzsj" value="<bean:write name="rzsj"/>" id="rzsj" 
										onblur="dateFormatChg(this)" style="cursor:hand;"
										onclick="return showCalendar('rzsj','y-mm-dd','aa');" readonly="true"/>
								</td>								
							</tr>							
						</tbody>
					</table>
				</div>
				<!-- 过滤条件 end-->
				<!-- 查询结果-->
				<div class="formbox">
					<h3 class="datetitle_01">
						<span> 查询结果&nbsp;&nbsp; 
							<logic:notEmpty name="rs">
								<font color="blue">提示：单击表头可以排序.</font>
							</logic:notEmpty>
							<logic:empty name="rs">
								<font color="red">未找到任何记录！</font>
							</logic:empty>
						</span>
					</h3>
					<logic:notEmpty name="rs">
						<table summary="" class="dateline" align="" width="100%">
							<thead>
							<tr align="center" style="cursor:hand" >
									<td onclick="tableSort(this)" id="nj">年级</td>
									<td onclick="tableSort(this)" id="sj">入住时间</td>
							</tr>
						</thead>
						<logic:iterate name="rs" id="s">
							<tr style="cursor:hand">
								<logic:iterate id="v" name="s" offset="0">
									<td nowrap>
										<bean:write name="v" />
									</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
						</table>
					</logic:notEmpty>
				</div>
				<!-- 查询结果 end-->
			</div>	
			<logic:equal value="yes" name="result">
				<script type="text/javascript">
					alert('保存成功！');
				</script>
			</logic:equal>
			<logic:equal value="no" name="result">
				<script type="text/javascript">
					alert('保存失败！');
				</script>
			</logic:equal>
		</html:form>
	</body>
</html>
