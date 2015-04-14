/**
 * Copyright (c) 2013-2015 by The SeedStack authors. All rights reserved.
 *
 * This file is part of SeedStack, An enterprise-oriented full development stack.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.seedstack.showcase.rest.category;

import org.seedstack.business.api.interfaces.assembler.BaseAssembler;
import org.seedstack.samples.ecommerce.domain.category.Category;

/**
 * CategoryAssembler.
 */
public class CategoryRepresentationAssembler extends BaseAssembler<Category, CategoryRepresentation> {

    @Override
    protected void doAssembleDtoFromAggregate(CategoryRepresentation targetDto, Category sourceEntity) {
        targetDto.setId(sourceEntity.getEntityId());
        targetDto.setName(sourceEntity.getName());
        targetDto.setUrlImg(sourceEntity.getUrlImg());
    }

    @Override
    protected void doMergeAggregateWithDto(Category targetEntity, CategoryRepresentation sourceDto) {
        targetEntity.setEntityId(sourceDto.getId());
        targetEntity.setName(sourceDto.getName());
        targetEntity.setUrlImg(sourceDto.getUrlImg());
    }
}
