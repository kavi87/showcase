/**
 * Copyright (c) 2013-2015 by The SeedStack authors. All rights reserved.
 *
 * This file is part of SeedStack, An enterprise-oriented full development stack.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.seedstack.showcase;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.seedstack.business.api.interfaces.assembler.AssemblerTypes;
import org.seedstack.business.api.interfaces.assembler.FluentAssembler;
import org.seedstack.business.api.interfaces.assembler.ModelMapper;
import org.seedstack.samples.ecommerce.domain.category.Category;
import org.seedstack.samples.ecommerce.domain.category.CategoryFactory;
import org.seedstack.seed.it.SeedITRunner;
import org.seedstack.showcase.rest.category.CategoryRepresentation;

import javax.inject.Inject;


/**
 * @author pierre.thirouin@ext.mpsa.com (Pierre Thirouin)
 */
@RunWith(SeedITRunner.class)
public class CategoryRepresentationAssemblingIT {

    @Inject
    private FluentAssembler fluently;

    @Inject
    private CategoryFactory factory;

    @Test
    public void testAssemblingWithModelMapper() {
        Category category = factory.createCategory(1L, "category1", "http://img.png");
        // Specify to use the default assembler based on ModelMapper
        // Passing the ModelMapper.class qualifier is the same that using AssemblerTypes.MODEL_MAPPER
        CategoryRepresentation representation = fluently.assemble(category).with(ModelMapper.class).to(CategoryRepresentation.class);

        Assertions.assertThat(representation.getId()).isEqualTo(1L);
        Assertions.assertThat(representation.getName()).isEqualTo("category1");
        Assertions.assertThat(representation.getUrlImg()).isEqualTo("http://img.png");
    }

    @Test
    public void testMergingWithModelMapper() {
        CategoryRepresentation representation = new CategoryRepresentation(2L, "category2", "http://img2.png");

        Category category = factory.createCategory(1L, "category1", "http://img.png");
        fluently.merge(representation).with(AssemblerTypes.MODEL_MAPPER).into(category);

        Assertions.assertThat(category.getEntityId()).isEqualTo(1L); // should not have changed (works because setEntityId() is in visibility package)
        Assertions.assertThat(category.getName()).isEqualTo("category2");
        Assertions.assertThat(category.getUrlImg()).isEqualTo("http://img2.png");
    }
}
