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
package org.seedstack.showcase.productinfo.service;


import org.seedstack.business.api.application.annotations.ApplicationService;
import org.seedstack.showcase.ws.productinfo.ProductInfo;


/**
 * The Interface ProductInfoService.
 * 
 * @author aymen.abbes@ext.mpsa.com
 */
@ApplicationService
public interface ProductInfoService {

	/**
	 * Gets the product info by id.
	 *
	 * @param idProduct the id product
	 * @return the product info by id
	 */
	public ProductInfo getProductInfoByID(long idProduct);

}