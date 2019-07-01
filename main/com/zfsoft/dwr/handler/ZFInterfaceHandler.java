package com.zfsoft.dwr.handler;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.directwebremoting.servlet.InterfaceHandler;
import org.directwebremoting.servlet.PathConstants;
import org.directwebremoting.util.LocalUtil;
import org.directwebremoting.util.MimeConstants;

/**
 * <p>
 *   <h3>zftal框架<h3>
 *   <br>说明：TODO
 *	 <br>class：com.zfsoft.dwr.handler.ZFInterfaceHandler.java
 * <p>
 * @author <a href="#">Zhangxiaobin[1036]<a>
 * @version 2016年8月15日上午8:50:29
 */
public class ZFInterfaceHandler extends InterfaceHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String scriptName = request.getPathInfo();
        scriptName = LocalUtil.replace(scriptName, interfaceHandlerUrl, "");
        scriptName = LocalUtil.replace(scriptName, PathConstants.EXTENSION_JS, "");
        if (!isJavaIdentifier(scriptName))
        {
            throw new SecurityException("Script names may only contain Java Identifiers");
        }

        String path = request.getContextPath() + request.getServletPath();

        String script;
		try {
			script = remoter.generateInterfaceScript(scriptName, path);
		} catch (SecurityException e) {
			throw new SecurityException("Remote Not found", e);
		}

        // Officially we should use MimeConstants.MIME_JS, but if we cheat and
        // use MimeConstants.MIME_PLAIN then it will be easier to read in a
        // browser window, and will still work just fine.
        response.setContentType(MimeConstants.MIME_PLAIN);
        PrintWriter out = response.getWriter();
        out.print(script);
	}

	public boolean isJavaIdentifier(String test){
		 if (test == null || test.length() == 0)
	        {
	            return false;
	        }

	        if (!Character.isJavaIdentifierStart(test.charAt(0)))
	        {
	            return false;
	        }

	        for (int i = 1; i < test.length(); i++)
	        {
	            if (!Character.isJavaIdentifierPart(test.charAt(i)))
	            {
	                return false;
	            }
	        }

	        return true;
	}
}
