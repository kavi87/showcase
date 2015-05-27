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
import org.seedstack.business.api.interfaces.assembler.FluentAssembler;
import org.seedstack.samples.ecommerce.domain.product.Product;
import org.seedstack.samples.ecommerce.domain.product.ProductFactory;
import org.seedstack.seed.it.SeedITRunner;
import org.seedstack.showcase.rest.product.ProductRepresentation;

import javax.inject.Inject;

/**
 * @author pierre.thirouin@ext.mpsa.com (Pierre Thirouin)
 */
@RunWith(SeedITRunner.class)
public class ProductionRepresentationAssemblingIT {

    @Inject
    private FluentAssembler fluently;

    @Inject
    private ProductFactory factory;

    @Test
    public void testAssemblingWithModelMapper() {
        Product product = factory.createProduct(1L, "destination", "summary", "details", "http://img.png", 14.90, 3L);
        ProductRepresentation representation = fluently.assemble(product).to(ProductRepresentation.class);

        Assertions.assertThat(representation.getId()).isEqualTo(1L);
        Assertions.assertThat(representation.getDesignation()).isEqualTo("destination");
        Assertions.assertThat(representation.getSummary()).isEqualTo("summary");
        Assertions.assertThat(representation.getCategoryId()).isEqualTo(3L);
    }
}
