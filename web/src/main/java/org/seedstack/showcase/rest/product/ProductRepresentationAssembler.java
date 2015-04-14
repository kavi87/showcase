/**
 * Copyright (c) 2013-2015 by The SeedStack authors. All rights reserved.
 *
 * This file is part of SeedStack, An enterprise-oriented full development stack.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.seedstack.showcase.rest.product;

import org.seedstack.business.api.interfaces.assembler.BaseAssembler;
import org.seedstack.samples.ecommerce.domain.product.Product;

/**
 * ProductAssembler implementation
 */
public class ProductRepresentationAssembler extends BaseAssembler<Product, ProductRepresentation> {

    @Override
    protected void doAssembleDtoFromAggregate(ProductRepresentation targetDto, Product sourceEntity) {
        targetDto.setId(sourceEntity.getEntityId());
        targetDto.setDesignation(sourceEntity.getDesignation());
        targetDto.setDetails(sourceEntity.getDetails());
        targetDto.setPicture(sourceEntity.getPicture());
        targetDto.setPrice(sourceEntity.getPrice());
        targetDto.setSummary(sourceEntity.getSummary());
        targetDto.setCategoryId(sourceEntity.getCategoryId());
    }

    @Override
    protected void doMergeAggregateWithDto(Product targetEntity, ProductRepresentation sourceDto) {
        targetEntity.setEntityId(sourceDto.getId());
        targetEntity.setDesignation(sourceDto.getDesignation());
        targetEntity.setDetails(sourceDto.getDetails());
        targetEntity.setPicture(sourceDto.getPicture());
        targetEntity.setPrice(sourceDto.getPrice());
        targetEntity.setCategoryId(sourceDto.getCategoryId());
        targetEntity.setSummary(sourceDto.getSummary());
    }

}
