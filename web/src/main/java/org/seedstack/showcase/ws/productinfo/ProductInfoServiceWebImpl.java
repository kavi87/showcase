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

import org.seedstack.business.api.interfaces.assembler.FluentAssembler;
import org.seedstack.samples.ecommerce.domain.product.Product;
import org.seedstack.samples.ecommerce.domain.product.ProductRepository;
import org.seedstack.seed.persistence.jpa.api.JpaUnit;
import org.seedstack.seed.transaction.api.Transactional;

import javax.inject.Inject;
import javax.jws.WebService;

/**
 * The product info WS implementation.
 *
 * @author aymen.abbes@ext.mpsa.com
 */
@WebService(endpointInterface = "org.seedstack.showcase.ws.productinfo.ProductInfoWS", targetNamespace = "http://seedstack.org/wsdl/seed/ProductInfo/", serviceName = "ProductInfoWSService", portName = "ProductInfoWSPort")
public class ProductInfoServiceWebImpl implements ProductInfoWS {
    @Inject
    FluentAssembler fluentAssembler;

    @Inject
    ProductRepository productRepository;

    @Override
    @Transactional
    @JpaUnit("seed-ecommerce-domain")
    public ProductInfo productInfoByID(long idProduct) throws BadProductRequestException {
        if (idProduct <= 0) {
            throw new BadProductRequestException("Error retrieving product", buildBadProductFaultInfo("Product identifier cannot be negative or 0", idProduct));
        }

        Product product = productRepository.load(idProduct);

        if (product == null) {
            throw new BadProductRequestException("Error retrieving product", buildBadProductFaultInfo("Product not found", idProduct));
        }

        return fluentAssembler.assemble(product).to(ProductInfo.class);
    }

    private BadProductRequest buildBadProductFaultInfo(String message, long idProduct) {
        BadProductRequest faultInfo = new BadProductRequest();
        faultInfo.setMessage(message);
        faultInfo.setRequestedProduct(idProduct);
        return faultInfo;
    }
}
