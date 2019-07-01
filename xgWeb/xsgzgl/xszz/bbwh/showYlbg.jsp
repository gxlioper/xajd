<%@ page language="java" contentType="text/html; charset=GBK"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<style type="text/css">
			.djbx .djbx_right .djbx_page .dy a.nocur{
				background:url("<%=stylePath %>images/djbx_but2.gif") no-repeat scroll 0 0 rgba(0, 0, 0, 0)
			}
		</style>
		<script type="text/javascript">
			jQuery(function(){
				jQuery("#currenImg").attr("src",jQuery("#imgList img").eq(0).attr("src"));
			});
			
			function toBefore(bbdm){
				var api = frameElement.api, W = api.opener;
				var beforeLi = jQuery(W.document).find("#"+bbdm).prev();
				var bbid;
				var xmdm = jQuery("#xmdm").val();
				
				if (beforeLi.html() != null){
					bbid = beforeLi.attr("id");
				} else {
					bbid = jQuery(W.document).find(".djb_list_con li").last().attr("id");
				}
				api.reload(window,"xszz_bbwh.do?method=showYlbg&bbdm="+bbid+"&xmdm="+xmdm);
			}
			
			function toNext(bbdm){
				var api = frameElement.api, W = api.opener;
				var nextLi = jQuery(W.document).find("#"+bbdm).next();
				var bbid;
				var xmdm = jQuery("#xmdm").val();
				
				if (nextLi.html() != null){
					bbid = nextLi.attr("id");
				} else {
					bbid = jQuery(W.document).find(".djb_list_con li").first().attr("id");
				}
				
				api.reload(window,"xszz_bbwh.do?method=showYlbg&bbdm="+bbid+"&xmdm="+xmdm);
			}
			
			function updateXmbb(){
				
				var bbdm = jQuery("#bbdm").val();
				var xmdm = jQuery("#xmdm").val();
				var bblx = jQuery("#bblx").val();
				
				jQuery.post("xszz_bbwh.do?method=updateXmbb",{bbdm:bbdm,xmdm:xmdm,bblx:bblx},function(data){
					if (Boolean(data)){
						showAlert("设置成功！",{},{"clkFun":function(){
							jQuery("#cz").removeAttr("onclick");
							jQuery("#cz").unbind("click");
							jQuery("#cz").bind("click",qx);
							jQuery("#cz").attr("class","nocur");
							jQuery("#bgImg").show();
						}});
						
					}
				});
			}
			function qx(){
				var bbdm = jQuery("#bbdm").val();
				var xmdm = jQuery("#xmdm").val();
				var bblx = jQuery("#bblx").val();
				jQuery.post("xszz_bbwh.do?method=updateXmbb",{bbdm:"",xmdm:xmdm,bblx:bblx},function(data){
					if (Boolean(data)){
						showAlert("设置成功！",{},{"clkFun":function(){
							jQuery("#cz").removeAttr("onclick");
							jQuery("#cz").unbind("click");
							jQuery("#cz").bind("click",updateXmbb);
							jQuery("#cz").attr("class","cur");
							jQuery("#bgImg").hide();
						}});
						
					}
				});
			}
			
	    </script>
		
	</head>

	<body>
		<input type="hidden" id="bbdm" value="${bbwhForm.bbdm }"/>
		<input type="hidden" id="xmdm" value="${bbwhForm.xmdm }"/>
		<input type="hidden" id="bblx" value="${bbxxList[0].bblx }"/>
	
		<div class="djbx">
			<div class="djbx_left">
				<div class="djbx_left1">
					<a href="javascript:toBefore('${bbwhForm.bbdm }');" id="prev" title="上一张表格"></a>
				</div>
				<div class="djbx_left2">
					<div class="djbx_left2_zz" style="display: block">
					<logic:equal  name="bbwh" property="bblx" value="1">
						<img src="<%=stylePath%>images/djbx.gif" id="bgImg"
							<logic:notEqual value="${bbwhForm.bbdm }" name="xmxx" property="dybb">
							style="display:none;"
							</logic:notEqual>
						/>
					</logic:equal>
					<logic:notEqual  name="bbwh" property="bblx" value="1">
						<img src="<%=stylePath%>images/djbx.gif" id="bgImg"
							<logic:notEqual value="${bbwhForm.bbdm }" name="xmxx" property="dysbbb">
							style="display:none;"
							</logic:notEqual>
						/>
					</logic:notEqual>
					</div>
					<div class="djbx_left2_img">
						<div>
							<ul>
								<li>
									<img src="" width="441" height="582" id="currenImg"/>
								</li>
							</ul>
						</div>
					</div>
				</div>
				<div class="djbx_left3">
					<a href="javascript:toNext('${bbwhForm.bbdm }');" id="next" title="下一张表格"></a>
				</div>
			</div>

			<div class="djbx_right">
				<h3>
<%--					登记表--%>
				</h3>
<%--				<div class="djbx_con">--%>
<%--					本表格包括本人情况、家庭经济情况、家庭成员情况、申请理由、院（系）意见、学校意见六大模块。--%>
<%--				</div>--%>
				<div class="djbx_page">
					<ul id="imgList">
						<logic:present name="bbxxList">
							<logic:iterate id="bbxx" name="bbxxList" indexId="i">
								<li>
									<a href="javascript:void(0);">
										<img src="<%=request.getContextPath() %>${bbxx.bblj }" width="44" height="57" 
											 onclick="jQuery('#currenImg').attr('src',this.src);"
										/>
									</a>
									<br />
									第${i+1 }页
								</li>
							</logic:iterate>
						</logic:present>
					</ul>
					<div>
						<img src="<%=stylePath%>images/djbx_xian.gif" />
					</div>
					<div class="dy">
						<logic:equal  name="bbwh" property="bblx" value="1">
							<logic:equal value="${bbwhForm.bbdm }" name="xmxx" property="dybb">
								<a id="cz" href="#" class="nocur" onclick="qx();"></a>
							</logic:equal>
							<logic:notEqual value="${bbwhForm.bbdm }" name="xmxx" property="dybb">
								<a id="cz" href="#" class="cur" onclick="updateXmbb();"></a>
							</logic:notEqual>
						</logic:equal>
						<logic:notEqual  name="bbwh" property="bblx" value="1">
							<logic:equal value="${bbwhForm.bbdm }" name="xmxx" property="dysbbb">
								<a id="cz" href="#" class="nocur" onclick="qx();"></a>
							</logic:equal>
							<logic:notEqual value="${bbwhForm.bbdm }" name="xmxx" property="dysbbb">
								<a id="cz" href="#" class="cur" onclick="updateXmbb();"></a>
							</logic:notEqual>
						</logic:notEqual>
					</div>
				</div>
			</div>
		</div>

	</body>
</html>
