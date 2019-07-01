<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script language="javascript">
		//查看失败数据
		function ckSbsj(obj){
			var jcb = jQuery('#jcb_'+obj+'').val();	
			var jcsj = 	jQuery('#jcsj_'+obj+'').val();	
			showTopWin("xtwh_xssjtb.do?method=ckSbsj&jcb="+jcb+'&jcsj='+jcsj,800,500);
			}

		function sjTb(){
			confirmInfo("本次操作将会从中间库中同步<bean:message key="lable.xb" />，专业，班级及学生信息数据。确定要开始同步吗?",function(tag){
				if(tag=="ok"){
				hiddenMessage(true,true);
				BatAlert.showTips('正在同步，可能需要"1"分钟，请稍等...');
				jQuery.post('xtwh_xssjtb.do?method=xssjTb',
						function(data){
						if(data==true){
							alertInfo('同步成功！',function(m){
								if ('ok'==m) {
									document.getElementById('search_go').click();
								}
							});
							}else{
								alertError("同步失败！");
								return false;
								}
					},'json')
				}
			});
			}

		function tbCsh(){
			confirmInfo("本次操作将会删除同步异常数据以及同步日志信息。确定要初始化吗?",function(tag){
				if(tag=="ok"){
				hiddenMessage(true,true);
				BatAlert.showTips('正在初始化，请稍等...');
				jQuery.post('xtwh_xssjtb.do?method=cshsjTb',
						function(data){
						if(data==true){
							alertInfo('操作成功！',function(m){
								if ('ok'==m) {
									document.getElementById('search_go').click();
								}
							});
							}else{
								alertError("操作失败！");
								return false;
								}
					},'json')
				}
			});
			}
	
		</script>
	</head>
	<body >
		<html:form action="/xtwh_xssjtb" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
			</div>
			<div class="toolbox">
				<!-- 按钮 -->
				<logic:equal name="writeAble" value="yes">
				<div class="buttonbox">
					<ul>
						<li>
							<a href="#" onclick="sjTb();return false;" class="btn_sz">
								手工同步 </a>
						</li>
						<li>
							<a href="#" onclick="tbCsh();return false;" class="btn_sz">
								删除同步日志 </a>
						</li>
					</ul>
				</div>
				</logic:equal>
				<div class="searchtab">
					<table width="100%" border="0">
						
						<tbody>
							<tr>
								<th>
									检测起始时间
								</th>
								<td>
									<html:text property="jckssj" styleId="jckssj" style="width:140px" onkeypress="onlyBackSpace(this,event);"
							onclick="return showCalendar(this.id,'yyyy-MM-dd HH:mm:ss')" readonly="readonly"></html:text>-
									<html:text property="jcjssj" styleId="jcjssj" style="width:140px" onkeypress="onlyBackSpace(this,event);"
							onclick="return showCalendar(this.id,'yyyy-MM-dd HH:mm:ss')" readonly="readonly"></html:text>
								</td>
								<td colspan="6">
									<div class="btn">
										<button type="button" class="btn_cx" id="search_go"
											onclick="allNotEmpThenGo('xtwh_xssjtb.do?method=tbrzManage')">
											查 询
										</button>
										&nbsp;&nbsp;&nbsp;&nbsp;
										<button type="button" class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
											重 置
										</button>
									</div>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			
			</div>
				<div class="formbox" id="div_rs">
				<h3 class="datetitle_01">
					<span> 查询结果&nbsp;&nbsp; <logic:empty name="rs">
							<font color="red">未找到任何记录！</font>
						</logic:empty> 
						</span>
				</h3>
				<logic:notEmpty name="rs">
					<table summary="" class="dateline" align="" width="100%">
					<thead>
							<tr align="center" style="cursor:hand">
								<logic:iterate id="tit" name="topTr" indexId="index" offset="0">
									<td id="<bean:write name="tit" property="en"/>" nowrap>
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<tbody>
							<logic:notEmpty name="rs">
								<logic:iterate name="rs" id="s" indexId="index">
								<tr>
									<td align="left">
										<logic:iterate id="v" name="s" offset="0" length="1">${v}</logic:iterate>
									<input type = 'hidden' id = 'jcb_${index}' name="jcb"
										value="<logic:iterate id="v" name="s" offset="1" length="1">${v}</logic:iterate>"
									/>
									<input type = 'hidden' id = 'jcsj_${index}' name="jcsj"
										value="<logic:iterate id="v" name="s" offset="0" length="1">${v}</logic:iterate>"
									/>
									</td>
									<td align="left">
										<logic:iterate id="v" name="s" offset="2" length="1">${v}</logic:iterate>
										此次应同步<logic:iterate id="v" name="s" offset="3" length="1">${v}</logic:iterate>条，
										成功<logic:iterate id="v" name="s" offset="4" length="1">${v}</logic:iterate>条，
										失败<a href="javascript:ckSbsj('${index}')"  class="name" ><logic:iterate id="v" name="s" offset="5" length="1">${v}</logic:iterate></a>条
									</td>
									</tr>
								</logic:iterate>
							</logic:notEmpty>
						</tbody>
					</table>
					</logic:notEmpty>
					<jsp:include flush="true"
						page="/sjcz/turnpage.jsp?form=xssjtbForm"></jsp:include>
						<script type="text/javascript">
								$('choose').className="hide";
						</script>
			</div>
		</html:form>
		<%@ include file="/comm/other/tsxx.jsp"%>
	</body>
</html>
