/**
 * Copyright (c) 2013-2015 by The SeedStack authors. All rights reserved.
 *
 * This file is part of SeedStack, An enterprise-oriented full development stack.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.seedstack.showcase.rest.customer;


import org.apache.commons.lang.StringUtils;
import org.seedstack.business.api.interfaces.assembler.Assemblers;
import org.seedstack.business.api.interfaces.query.range.Range;
import org.seedstack.business.api.interfaces.query.result.Result;
import org.seedstack.business.api.interfaces.query.view.page.PaginatedView;
import org.seedstack.samples.ecommerce.domain.customer.Customer;
import org.seedstack.samples.ecommerce.domain.customer.CustomerFactory;
import org.seedstack.samples.ecommerce.domain.customer.CustomerRepository;
import org.seedstack.seed.persistence.jpa.api.JpaUnit;
import org.seedstack.seed.transaction.api.Transactional;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

/**
 * CustomersResource
 */
@Path("/customers")
@Transactional
@JpaUnit("seed-ecommerce-domain")
public class CustomersResource {

    @Inject
    private CustomerFinder customerFinder;

    @Inject
    private Assemblers assemblers;

    @Inject
    private CustomerRepository customerRepository;

    @Inject
    private CustomerFactory customerFactory;

    @Context
    private UriInfo uriInfo;

    /**
     * Gets Customers with pagination and search parameters.
     *
     * @param searchString the string to search
     * @param pageIndex    the page index
     * @param pageSize     the page size
     * @return a paginated view of customer representations
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response customers(@QueryParam("searchString") String searchString, @QueryParam("pageIndex") int pageIndex, @QueryParam("pageSize") int pageSize) {
        Map<String, Object> criteria = new HashMap<String, Object>();
        if (StringUtils.isNotEmpty(searchString)) {
            criteria.put("searchString", searchString);
        }
        Result<CustomerRepresentation> result = customerFinder.findAllCustomers(Range.rangeFromPageInfo(pageIndex, pageSize), criteria);
        PaginatedView<CustomerRepresentation> paginatedView = new PaginatedView<CustomerRepresentation>(result, pageSize, pageIndex);
        return Response.ok(paginatedView).build();
    }

    /**
     * Gets a customer by id.
     *
     * @param customerId the customer id
     * @return a customer representation or 404 if not found
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{customerId}")
    public Response customers(@PathParam("customerId") String customerId) {
        CustomerRepresentation customer = customerFinder.findCustomerById(customerId);
        if (customer != null) {
            return Response.ok(customer).build();
        } else {
            return Response.status(Status.NOT_FOUND).build();
        }
    }

    /**
     * Updates a Customer.
     *
     * @param customerRepresentation the customer representation
     * @param customerId             the customer id
     * @return the updated customer
     */
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{customerId}")
    public Response updateCustomer(CustomerRepresentation customerRepresentation, @PathParam("customerId") String customerId) {
        if (!customerId.equals(customerRepresentation.getId())) {
            throw new IllegalArgumentException("Cannot change customer email");
        }
        Customer customer = assemblers.retrieveThenMergeAggregateWithDto(customerRepresentation, Customer.class);
        customer = customerRepository.save(customer);

        if (customer == null) {
            return Response.status(Status.NOT_MODIFIED).build();
        }
        CustomerRepresentation customerRepresentation1 = assemblers.assembleDtoFromAggregate(CustomerRepresentation.class, customer);
        return Response.ok(customerRepresentation1).build();
    }

    /**
     * Adds a Customer.
     *
     * @param customerRepresentation the customer representation
     * @return the new customer
     * @throws URISyntaxException if an URI error occurs
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createCustomer(CustomerRepresentation customerRepresentation) throws URISyntaxException {
        Customer customer = assemblers.retrieveOrCreateThenMergeAggregateWithDto(customerRepresentation, Customer.class);

        customerRepository.persist(customer);

        URI newUri = new URI(this.uriInfo.getRequestUri().toString() + "/" + customerRepresentation.getId());
        CustomerRepresentation customerRepresentation1 = assemblers.assembleDtoFromAggregate(CustomerRepresentation.class, customer);
        return Response.created(newUri).entity(customerRepresentation1).build();
    }

    /**
     * Deletes a Customer.
     *
     * @param customerId the customer id
     * @return ok or 404 if the customer did exist
     */
    @DELETE
    @Path("/{customerId}")
    public Response deleteCategory(@PathParam("customerId") String customerId) {
        Customer customer = customerRepository.load(customerFactory.createCustomerId(customerId));
        if (customer == null) {
            return Response.status(Status.NOT_FOUND).build();
        }
        customerRepository.delete(customer);
        return Response.status(Status.OK).build();
    }

}
