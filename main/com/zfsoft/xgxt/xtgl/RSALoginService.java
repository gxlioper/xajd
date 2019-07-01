package com.zfsoft.xgxt.xtgl;

import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.util.RSAUtils;

/*******RSA��¼����**********/
public class RSALoginService {

	private static final String PRIVATE_KEY_ATTRIBUTE_NAME = "privateKey";

	/*****������Կ�ԣ����ع�Կ��˽Կ��session********/
	public RSAPublicKey generateKey(HttpServletRequest request) {
		KeyPair keyPair = RSAUtils.generateKeyPair();
		RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
		RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
		HttpSession session = request.getSession();
		session.setAttribute(PRIVATE_KEY_ATTRIBUTE_NAME, privateKey);
		return publicKey;
	}

	/*******��session�����˽Կ*************/
	public void removePrivateKey(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute(PRIVATE_KEY_ATTRIBUTE_NAME);
	}

	
	/*********�����ַ���*****************/
	public String decryptParameter(String parameter, HttpServletRequest request) {
		if (parameter != null) {
			HttpSession session = request.getSession();
			RSAPrivateKey privateKey = (RSAPrivateKey) session.getAttribute(PRIVATE_KEY_ATTRIBUTE_NAME);
			if (privateKey != null && StringUtils.isNotNull(parameter)) {
				return RSAUtils.decrypt(privateKey, parameter);
			}
		}
		return null;
	}
	
}