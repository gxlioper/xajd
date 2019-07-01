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
				api.reload(window,"jtpjsz.do?method=showYlbg&bbdm="+bbid+"&jxid="+xmdm);
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
				api.reload(window,"jtpjsz.do?method=showYlbg&bbdm="+bbid+"&jxid="+xmdm);
			}
			
			function updateXmbb(){
				var bbdm = jQuery("#bbdm").val();
				var xmdm = jQuery("#xmdm").val();
				jQuery.post("jtpjsz.do?method=updateXmbb",{bbdm:bbdm,xmdm:xmdm},function(data){
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
				var xmdm = jQuery("#xmdm").val();
				jQuery.post("jtpjsz.do?method=updateXmbb",{bbdm:"",xmdm:xmdm},function(data){
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
		<input type="hidden" id="bbdm" value="${bbdm }"/>
		<input type="hidden" id="xmdm" value="${jtpjszForm.jxid}"/>
	
		<div class="djbx">
			<div class="djbx_left">
				<div class="djbx_left1">
					<a href="javascript:toBefore('${bbdm }');" id="prev" title="��һ�ű��"></a>
				</div>
				<div class="djbx_left2">
					<div class="djbx_left2_zz" style="display: block">
						<img src="<%=stylePath%>images/djbx.gif" id="bgImg"
							<logic:notEqual value="${bbdm }" name="xmxx" property="dybbid">
							style="display:none;"
							</logic:notEqual>
						/>
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
					<a href="javascript:toNext('${bbdm }');" id="next" title="��һ�ű��"></a>
				</div>
			</div>

			<div class="djbx_right">
				<h3>
<%--					�ǼǱ�--%>
				</h3>
<%--				<div class="djbx_con">--%>
<%--					�������������������ͥ�����������ͥ��Ա������������ɡ�Ժ��ϵ�������ѧУ�������ģ�顣--%>
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
									��${i+1 }ҳ
								</li>
							</logic:iterate>
						</logic:present>
					</ul>
					<div>
						<img src="<%=stylePath%>images/djbx_xian.gif" />
					</div>
					<div class="dy">
						<logic:equal value="${bbdm}" name="xmxx" property="dybbid">
							<a id="cz" href="#" class="nocur" onclick="qx();"></a>
						</logic:equal>
						<logic:notEqual value="${bbdm}" name="xmxx" property="dybbid">
							<a id="cz" href="#" class="cur" onclick="updateXmbb();"></a>
						</logic:notEqual>
						
					</div>
				</div>
			</div>
		</div>

	</body>
</html>
