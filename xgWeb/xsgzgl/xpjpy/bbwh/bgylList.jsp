<%@ page language="java" contentType="text/html; charset=GBK"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript">
			function viewDjbg(bbdm,xmdm,bblx) {
				showDialog("登记表设置",765,675,"xpj_bbwh.do?method=showYlbg&bbdm="+bbdm+"&xmdm="+xmdm+"&bblx="+bblx,{
					fixed:false,
					close:function(){
						window.reload();
					}
				});
			}
		</script>

	</head>

	<body>
		<div class="djb_list">
			<div class="djb_list_nav">
				<h4>
					（${xmxx.xn }&nbsp;学年）（${xqmc}&nbsp;学期）${xmxx.xmmc }
				</h4>
				<%--<a href="xpj_xmwh.do?method=xmwhCx">返回</a>--%>
				<a href="javascript:window.history.back()">返回</a>
			</div>
			<div class="djb_list_con">
				<ul>
				    <logic:present name="bbxxList">
				    	<logic:iterate id="bbxx" name="bbxxList">
				    		<li id="${bbxx.bbdm }">
								<a href="javascript:void(0);"  onclick="viewDjbg('${bbxx.bbdm }','${xmxx.xmdm }','${bbxx.bblx }');">
								   <logic:notEqual  name="bblx"  value="2">
									   <logic:equal value="${bbxx.bbdm }" name="xmxx" property="dybb">
									   		<div class="djb_list_li" style="display:block"></div>
									   </logic:equal>
								   </logic:notEqual>
								   <logic:equal  name="bblx"  value="2">
									   <logic:equal value="${bbxx.bbdm }" name="xmxx" property="dysbbb">
									   		<div class="djb_list_li" style="display:block"></div>
									   </logic:equal>
								   </logic:equal>
								   
								  <img src="<%=request.getContextPath() %>${bbxx.bblj }" width="166" height="218" />
								</a>
								<h5>
									${bbxx.bbmc }
								</h5>
							</li>
				    	</logic:iterate>
				    </logic:present>
				</ul>
			</div>
		</div>
	</body>
</html>
