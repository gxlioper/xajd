<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	</head>
	<script language="javascript" src="js/check.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/jsFunction.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script type="text/javascript" src="js/jxglFunction.js"></script>
	<script language="javascript" src="js/comm/commFunction.js"></script>
	<script type='text/javascript'
		src='/xgxt/dwr/interface/getXmlgSzdwDAO.js'></script>
	<script language="javascript">
	function saveFdylr(obj,doType){
		var xmlx = document.getElementsByName("xmlx");
		var wcqk = document.getElementsByName("wcqk");
		for(var i=0;i<xmlx.length;i++){
			if(xmlx[i].value=="必选项"){
				if(wcqk[i].value==""){
					alert("必选项的完成情况不能空！");
					return false;
				}
			}
		}	

		var msg= "你确定要保存吗？";
		if(doType=="tj"){
			msg="确定提交吗？提交后信息将不可修改！";
		}
		
		confirmInfo(msg,function(data){
			if("ok"==data){
				showTips('处理数据中，请等待......');
				refreshForm("/xgxt/bjlh_fdyzp.do?method=fdyzpFdylr&doType="+doType);
				obj.disabled=true;
			}
		});
	}
</script>
	<body>
		<html:form action="/bjlh_fdyzp" method="post">
			<!-- 标题 -->
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置：</em><a><bean:write name="title" /> </a>
				</p>
				<p class="help">
					<a href="#" onclick="return false;" onmousedown ="showHelpDiv();">使用帮助</a>
				</p>
			</div>			
			<!-- 标题 end-->
			<!-- 提示信息 START-->
			<div class="prompt" id="div_help" style="display: none">
				<h3>
					<span>提示：</span>
				</h3>
				<p>
					填写信息点击“保存”后可保存信息，并可继续修改；若确认无修改时，点击“提交”完成自评，提交后信息不可修改；
				</p>
				<a class="close" title="隐藏"
				   onclick="this.parentNode.style.display='none';"></a>
			</div>
			<!-- 提示信息 end-->	
			<div class="tab">
				<table class="formlist" border="0" align="center"
					style="width: 100%">					
					<thead>
						<tr>
							<th colspan="4">
								<span>基本信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="4">
								学年：${rs.xn}
								&nbsp;&nbsp;
								<html:hidden property="xn" value="${rs.xn}"/>
								所在部门：${rs.bmmc}
								&nbsp;&nbsp;
								辅导员姓名：${rs.xm }
								<html:hidden property="yhm" value="${rs.zgh}"/>
							</td>
						</tr>
					</tbody>									
					<thead>
						<tr>
							<th colspan="4">
								<span>考核项目信息</span>
							</th>
						</tr>
						<tr>
							<th>
								考核项目
							</th>
							<th width="10%">
								项目类型
							</th>
							<th>
								完成情况
							</th>
							<th>
								备注
							</th>
						</tr>
					</thead>
					<tbody width="100%" >
						<logic:present name="xmList">
							<logic:iterate id="j" name="xmList">
								<tr>
									<td width="180px" style="word-break:break-all">
										<html:hidden name="j" property="xmid" />
										<html:hidden name="j" property="zpbid" />
										<html:hidden name="j" property="xmlx" />
										${j.khxm }
									</td>									
									<td width="60px">
										${j.xmlx}
										<logic:equal name="j" property="xmlx" value="必选项">
										<font class="red">*</font>
										</logic:equal>
									</td>
									<td width="360px">
										<html:text name="j" property="wcqk" style="width:98%" maxlength="50"/>
									</td>
									<td width="180px" style="word-break:break-all">
										${j.bz }
									</td>
								</tr>
							</logic:iterate>
						</logic:present>

					</tbody>
					<tfoot>
						<tr>
							<td align="center" colspan="4">
								<div class="btn">
									<logic:notEqual name="rs" property="sftj" value="是">
										<button type="button" class="button2" onclick="saveFdylr(this,'save');" id="buttonSave">
											保存
										</button>
										<button type="button" class="button2" onclick="saveFdylr(this,'tj');" id="buttonSave">
											提交
										</button>
									</logic:notEqual>
									<button type="button" name="重置" type="reset">重 置</button>	
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
		</html:form>
		<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
	</body>
</html>