<%@ page language="java" contentType="text/html; charset=GBK"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript">
		function viewDjbg(guid) {
				var ywid = jQuery("input[name='ywid']").val();
				var mkdm = jQuery("input[name='mkdm']").val();
				var dybb = jQuery("input[name='dybb']").val();
				showDialog("登记表设置",765,675,"comm_bbdmpz.do?method=queryBbdm&guid=" + guid +  "&ywid=" + ywid + "&mkdm=" + mkdm +"&dybb=" + dybb, {
					fixed:false,
					close:function(){
						jQuery('#commBbdmpzForm').submit();
					}
				});
			}
		</script>

	</head>

	<body>
		<%@ include file="/xsgzgl/comm/bbdmpz/csszForm.jsp" %>
		<div class="djb_list">
			<div class="djb_list_nav">
				<h4>
						<logic:notEmpty name="bbdmModel" property="h_title">
							${bbdmModel.h_title }&nbsp;
						</logic:notEmpty>
						<logic:notEmpty name="bbdmModel" property="h_xn">
							（${bbdmModel.h_xn }&nbsp;学年）
						</logic:notEmpty>
						<logic:notEmpty name="bbdmModel" property="h_xqmc">
							（（${bbdmModel.h_xqmc}&nbsp;学期）
						</logic:notEmpty>
						<logic:notEmpty name="bbdmModel" property="h_zd1">
							 ${bbdmModel.h_zd1 }&nbsp;
						</logic:notEmpty>
						<logic:notEmpty name="bbdmModel" property="h_zd2">
							 ${bbdmModel.h_zd2 }&nbsp;
						</logic:notEmpty>
						<logic:notEmpty name="bbdmModel" property="h_zd3">
							 ${bbdmModel.h_zd3 }&nbsp;
						</logic:notEmpty>
						<logic:notEmpty name="bbdmModel" property="h_zd4">
							 ${bbdmModel.h_zd4 }&nbsp;
						</logic:notEmpty>
						<logic:notEmpty name="bbdmModel" property="h_zd5">
							 ${bbdmModel.h_zd5 }&nbsp;
						</logic:notEmpty>
						<logic:notEmpty name="bbdmModel" property="h_zd6">
							 ${bbdmModel.h_zd6 }&nbsp;
						</logic:notEmpty>
						<logic:notEmpty name="bbdmModel" property="h_zd7">
							 ${bbdmModel.h_zd7 }&nbsp;
						</logic:notEmpty>
						<logic:notEmpty name="bbdmModel" property="h_zd8">
							 ${bbdmModel.h_zd8 }&nbsp;
						</logic:notEmpty>
						<logic:notEmpty name="bbdmModel" property="h_zd9">
							 ${bbdmModel.h_zd9 }&nbsp;
						</logic:notEmpty>
						<logic:notEmpty name="bbdmModel" property="h_zd10">
							 ${bbdmModel.h_zd10 }&nbsp;
						</logic:notEmpty>
				</h4>
				
				<a href="${bbdmModel.thlj}" id="returnBackUrl">返回</a>
			</div>
			<div class="djb_list_con">
				<ul>
				    <logic:present name="bbxxList">
				    	<logic:iterate id="bbxx" name="bbxxList">
				    		<li id="${bbxx.guid }">
								<a href="javascript:void(0);" 
								   onclick="viewDjbg('${bbxx.guid}');">
								   <logic:equal value="${bbxx.guid }" name="dybb">
								   		<div class="djb_list_li" style="display:block"></div>
								   </logic:equal>
								  <img src="<%=request.getContextPath() %>${bbxx.tplj }" width="166" height="218" />
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
