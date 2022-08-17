package com.example.controller;

import com.example.Product;
import com.kawaii.service.proto.product.AnotherHelloGrpcGrpc;
import com.kawaii.service.proto.product.AnotherHelloReply;
import com.kawaii.service.proto.product.AnotherHelloRequest;
import io.grpc.stub.StreamObserver;
import io.quarkus.grpc.GrpcService;
import io.smallrye.common.annotation.Blocking;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@GrpcService
public class ProductController extends AnotherHelloGrpcGrpc.AnotherHelloGrpcImplBase {

    @Inject
    EntityManager em;

    @Override
    @Transactional
    @Blocking
    public void sayHello(AnotherHelloRequest request, StreamObserver<AnotherHelloReply> responseObserver) {
        Product product = new Product();
        em.persist(product);
        responseObserver.onNext(AnotherHelloReply.newBuilder().build());
        responseObserver.onCompleted();
    }

}
