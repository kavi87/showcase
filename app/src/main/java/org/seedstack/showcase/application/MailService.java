/**
 * Copyright (c) 2013-2015 by The SeedStack authors. All rights reserved.
 *
 * This file is part of SeedStack, An enterprise-oriented full development stack.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.seedstack.showcase.application;

import javax.mail.MessagingException;
import java.util.Map;

/**
 * Mail service.
 */
public interface MailService {

    /**
     * Sends an email.
     *
     * @param to     the receiver
     * @param values the values
     * @throws MessagingException if an exception occurs during the sending
     */
    public void sendMail(String to, Map<String, Object> values) throws Exception;
}