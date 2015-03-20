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
package org.seedstack.showcase.productinfo.service.impl;


import org.seedstack.samples.ecommerce.domain.product.Product;
import org.seedstack.showcase.productinfo.service.ProductInfoService;
import org.seedstack.showcase.ws.productinfo.ProductInfo;

import javax.inject.Inject;
import javax.persistence.EntityManager;

// TODO: Auto-generated Javadoc
/**
 * The Class ProductInfoServiceImpl.
 * 
 * @author aymen.abbes@ext.mpsa.com
 */
public class ProductInfoServiceImpl implements ProductInfoService {

	/** The entity manager. */
	@Inject
	private EntityManager entityManager;

	@Override
	public ProductInfo getProductInfoByID(long idProduct) {
		Product product = entityManager.find(Product.class, idProduct);

		if (product != null) {

			ProductInfo productInfo = new ProductInfo();
			productInfo.setCategoryId(product.getCategoryId());
			productInfo.setDesignation(product.getDesignation());
			productInfo.setDetails(product.getDetails());
			productInfo.setEntityId(product.getEntityId());
			productInfo.setPicture(product.getPicture());
			productInfo.setPrice(product.getPrice());
			productInfo.setSummary(product.getSummary());
			return productInfo;
		}

		return null;
	}

}
