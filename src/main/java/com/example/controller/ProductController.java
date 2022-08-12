package com.example.controller;

import com.example.Product;
import com.kawaii.service.proto.product.ProductServiceGrpc;
import io.grpc.stub.StreamObserver;
import io.quarkus.grpc.GrpcService;
import io.smallrye.common.annotation.Blocking;
import kawaii.proto.common.Common;
import kawaii.proto.product.service.Model;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@GrpcService
public class ProductController extends ProductServiceGrpc.ProductServiceImplBase {

    @Inject
    EntityManager em;

    @Override
    @Transactional
    @Blocking
    public void getProduct(Common.UUID request, StreamObserver<Model.Product> responseObserver) {
        Product product = new Product();
        em.persist(product);
        responseObserver.onNext(Model.Product.newBuilder().build());
        responseObserver.onCompleted();
    }
}
