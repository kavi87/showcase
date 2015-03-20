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

import com.google.inject.Inject;
import org.seedstack.seed.persistence.jpa.api.JpaUnit;
import org.seedstack.seed.transaction.api.Transactional;
import org.seedstack.showcase.productinfo.service.ProductInfoService;

import javax.jws.WebService;

/**
 * The Class CalculatorServiceWebImpl.
 *
 * @author aymen.abbes@ext.mpsa.com
 */
@WebService(endpointInterface = "org.seedstack.showcase.ws.productinfo.ProductInfoWS", targetNamespace = "http://seedstack.org/wsdl/seed/ProductInfo/", serviceName = "ProductInfoWSService", portName = "ProductInfoWSPort")
public class ProductInfoServiceWebImpl implements ProductInfoWS {

    /**
     * The product info service.
     */
    @Inject
    ProductInfoService productInfoService;

    @Override
    @Transactional
    @JpaUnit("seed-ecommerce-domain")
    public ProductInfo productInfoByID(long idProduct) throws NumberFormatException {
        if (idProduct <= 0) {
            throw new NumberFormatException("Illegal Argument", new NumberFormat());
        }

        return productInfoService.getProductInfoByID(idProduct);
    }
}
