/**
 * Copyright (c) 2013-2015 by The SeedStack authors. All rights reserved.
 *
 * This file is part of SeedStack, An enterprise-oriented full development stack.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
/*
 * 
 */
package org.seedstack.showcase.ws.productinfo;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import com.sun.xml.wss.impl.callback.PasswordValidationCallback;

/**
 * The Class PlainTextPasswordValidator.
 * 
 * @author aymen.abbes@ext.mpsa.com
 */
public class PlainTextPasswordValidator implements
		PasswordValidationCallback.PasswordValidator {

	/**
	 * {@inheritDoc}
	 * 
	 * @see com.sun.xml.wss.impl.callback.PasswordValidationCallback.PasswordValidator#validate(com.sun.xml.wss.impl.callback.PasswordValidationCallback.Request)
	 */
	@Override
	public boolean validate(PasswordValidationCallback.Request request)
			throws PasswordValidationCallback.PasswordValidationException {
		Configuration props;
		try {
			props = new PropertiesConfiguration(
					"META-INF/configuration/org.seedstack.showcase-web-backend.props");

		} catch (ConfigurationException e) {
			e.printStackTrace();
			throw new RuntimeException("Configuration error");
		}
		PasswordValidationCallback.PlainTextPasswordRequest plainTextRequest = (PasswordValidationCallback.PlainTextPasswordRequest) request;

		if (props.getString("usernametoken.username").equals(
				plainTextRequest.getUsername())
				&& props.getString("usernametoken.password").equals(
						plainTextRequest.getPassword()))
			return true;

		return false;
	}
}