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
				api.reload(window,"xpjpy_xmwh_bbsz.do?method=showYlbb&bbdm="+bbid+"&xmdm="+xmdm);
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
				api.reload(window,"xpjpy_xmwh_bbsz.do?method=showYlbb&bbdm="+bbid+"&xmdm="+xmdm);
			}
			
			function updateXmbb(){
				var bbdm = jQuery("#bbdm").val();
				var xmdm = jQuery("#xmdm").val();
				var bblx = jQuery("#bblx").val();
				jQuery.post("xpjpy_xmwh_bbsz.do?method=updateXmbb",{bbdm:bbdm,xmdm:xmdm,bblx:bblx},function(data){
					if (Boolean(data)){
						showAlert("���óɹ���",{},{"clkFun":function(){
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
				jQuery.post("xpjpy_xmwh_bbsz.do?method=updateXmbb",{bbdm:"",xmdm:xmdm,bblx:bblx},function(data){
					if (Boolean(data)){
						showAlert("���óɹ���",{},{"clkFun":function(){
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
		<input type="hidden" id="bbdm" value="${bbszForm.bbdm }"/>
		<input type="hidden" id="xmdm" value="${bbszForm.xmdm }"/>
		<input type="hidden" id="bblx" value="${bbxxList[0].bblx }"/>
		<div class="djbx">
			<div class="djbx_left">
				<div class="djbx_left1">
					<a href="javascript:toBefore('${bbszForm.bbdm }');" id="prev" title="��һ�ű��"></a>
				</div>
				<div class="djbx_left2">
					<div class="djbx_left2_zz" style="display: block">
					<logic:equal  name="bbwh" property="bblx" value="1">
						<img src="<%=stylePath%>images/djbx.gif" id="bgImg"
							<logic:notEqual value="${bbszForm.bbdm }" name="xmxx" property="djb">
							style="display:none;"
							</logic:notEqual>
						/>
					</logic:equal>
					<logic:notEqual  name="bbwh" property="bblx" value="1">
						<img src="<%=stylePath%>images/djbx.gif" id="bgImg"
							<logic:notEqual value="${bbszForm.bbdm }" name="xmxx" property="sbb">
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
					<a href="javascript:toNext('${bbszForm.bbdm }');" id="next" title="��һ�ű��"></a>
				</div>
			</div>
			<div class="djbx_right">
				<h3>
				</h3>
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
									��${i+1 }ҳ
								</li>
							</logic:iterate>
						</logic:present>
					</ul>
					<div>
						<img src="<%=stylePath%>images/djbx_xian.gif" />
					</div>
					<div class="dy">
						<logic:equal name="bbwh" property="bblx" value="1">
							<logic:equal value="${bbszForm.bbdm }" name="xmxx" property="djb">
								<a id="cz" href="#" class="nocur" onclick="qx();"></a>
							</logic:equal>
							<logic:notEqual value="${bbszForm.bbdm }" name="xmxx" property="djb">
								<a id="cz" href="#" class="cur" onclick="updateXmbb();"></a>
							</logic:notEqual>
						</logic:equal>
						<logic:notEqual name="bbwh" property="bblx" value="1">
							<logic:equal value="${bbszForm.bbdm }" name="xmxx" property="sbb">
								<a id="cz" href="#" class="nocur" onclick="qx();"></a>
							</logic:equal>
							<logic:notEqual value="${bbszForm.bbdm }" name="xmxx" property="sbb">
								<a id="cz" href="#" class="cur" onclick="updateXmbb();"></a>
							</logic:notEqual>
						</logic:notEqual>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>